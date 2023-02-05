package net.dries007.tfc.compat.gregtech.items.tools.behaviors;

import gregtech.api.items.toolitem.ToolHelper;
import gregtech.api.items.toolitem.behavior.IToolBehavior;
import gregtech.common.blocks.BlockOre;
import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.capability.player.CapabilityPlayerData;
import net.dries007.tfc.api.events.ProspectEvent;
import net.dries007.tfc.network.PacketProspectResult;
import net.dries007.tfc.util.skills.ProspectingSkill;
import net.dries007.tfc.util.skills.SkillType;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

import static net.dries007.tfc.compat.gregtech.items.tools.TFCToolHelper.PROSPECTOR_KEY;

public class PropickBehavior implements IToolBehavior {

    public static final PropickBehavior INSTANCE = new PropickBehavior();

    private static final Random RANDOM = new Random();
    private static final int PROSPECT_RADIUS = 30;
    private static final int COOLDOWN = 10;

    protected PropickBehavior() {/**/}

    @Override
    @Nonnull
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, @Nullable EnumFacing facing, float hitX, float hitY, float hitZ) {
        IBlockState state = worldIn.getBlockState(pos);
        if (facing != null) {
            SoundType soundType = state.getBlock().getSoundType(state, worldIn, pos, player);
            worldIn.playSound(player, pos, soundType.getHitSound(), SoundCategory.BLOCKS, 1.0f, soundType.getPitch());

            if (!worldIn.isRemote) {
                ProspectEvent event;
                float falseNegativeChance = 0.3f; // Classic value was random(100) >= (60 + rank)
                ProspectingSkill skill = CapabilityPlayerData.getSkill(player, SkillType.PROSPECTING);
                if (skill != null) {
                    falseNegativeChance = 0.3f - (0.1f * skill.getTier().ordinal());
                }

                // Damage item and set cooldown
                ToolHelper.damageItem(player.getHeldItem(hand), player);
                player.getCooldownTracker().setCooldown(player.getHeldItem(hand).getItem(), COOLDOWN);

                /**
                 * We fix a terrible case of Random seeding with dx,dy=0 and small dz resulting in issue #1736
                 * where propick false negatives were rows in the z-axis, especially at 0 skill. setSeed() only uses 48 bits
                 * of pos.toLong(). Solved this by multiplying coordinates by primes and XOR's results. Verified produces
                 * more "random" results.
                 */
                RANDOM.setSeed((pos.getX() * 92853) ^ (pos.getY() * 1959302) ^ (pos.getZ() * 2839402));
                ItemStack targetStack = getOreStack(worldIn, pos, state, false);
                if (!targetStack.isEmpty()) {
                    // Just clicked on an ore block
                    event = new ProspectEvent.Server(player, pos, ProspectResult.Type.FOUND, targetStack);

                    // Increment skill
                    if (skill != null) {
                        skill.addSkill(pos);
                    }
                } else if (RANDOM.nextFloat() < falseNegativeChance) {
                    // False negative
                    event = new ProspectEvent.Server(player, pos, ProspectResult.Type.NOTHING, null);
                } else {
                    Collection<ProspectResult> results = scanSurroundingBlocks(worldIn, pos);
                    if (results.isEmpty()) {
                        // Found nothing
                        event = new ProspectEvent.Server(player, pos, ProspectResult.Type.NOTHING, null);
                    } else {
                        // Found something
                        ProspectResult result = (ProspectResult) results.toArray()[RANDOM.nextInt(results.size())];
                        event = new ProspectEvent.Server(player, pos, result.getType(), result.ore);

                        if (ConfigTFC.General.DEBUG.enable) {
                            for (ProspectResult debugResult : results) {
                                TerraFirmaCraft.getLog().debug(debugResult.ore.getDisplayName() + ": " + String.format("%.02f", debugResult.score));
                            }
                        }
                    }
                }

                MinecraftForge.EVENT_BUS.post(event);
                PacketProspectResult packet = new PacketProspectResult(event.getBlockPos(), event.getResultType(), event.getVein());
                TerraFirmaCraft.getNetwork().sendTo(packet, (EntityPlayerMP) player);
            } else {
                //client side, add hit particles
                addHitBlockParticle(worldIn, pos, facing, state);
            }
        }

        return EnumActionResult.SUCCESS;
    }

    /**
     * Loops through every block in a 25x25x25 cube around the center
     *
     * @param world  The world
     * @param center The center position
     * @return the collection of results
     */
    @Nonnull
    private Collection<ProspectResult> scanSurroundingBlocks(World world, BlockPos center) {
        Map<String, ProspectResult> results = new HashMap<>();
        for (BlockPos.MutableBlockPos pos : BlockPos.MutableBlockPos.getAllInBoxMutable(center.add(-PROSPECT_RADIUS, -PROSPECT_RADIUS, -PROSPECT_RADIUS), center.add(PROSPECT_RADIUS, PROSPECT_RADIUS, PROSPECT_RADIUS))) {
            ItemStack stack = getOreStack(world, pos, world.getBlockState(pos), true);
            if (!stack.isEmpty()) {
                String oreName = stack.getDisplayName();
                if (results.containsKey(oreName)) {
                    results.get(oreName).score += 1;
                } else {
                    results.put(oreName, new ProspectResult(stack, 1));
                }
            }
        }
        return results.values();
    }

    @Nonnull
    private ItemStack getOreStack(World world, BlockPos pos, IBlockState state, boolean ignoreGrade) {
        Block block = state.getBlock();
        if (block instanceof BlockOre) {
            BlockOre blockOre = (BlockOre) block;
            return blockOre.getPickBlock(state, null, world, pos, null);
        }
        return ItemStack.EMPTY;
    }

    private void addHitBlockParticle(World world, BlockPos pos, EnumFacing side, IBlockState state) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        AxisAlignedBB axisalignedbb = state.getBoundingBox(world, pos);
        for (int i = 0; i < 2; i++) {
            double xOffset = x + RANDOM.nextDouble() * (axisalignedbb.maxX - axisalignedbb.minX - 0.2D) + 0.1D + axisalignedbb.minX;
            double yOffset = y + RANDOM.nextDouble() * (axisalignedbb.maxY - axisalignedbb.minY - 0.2D) + 0.1D + axisalignedbb.minY;
            double zOffset = z + RANDOM.nextDouble() * (axisalignedbb.maxZ - axisalignedbb.minZ - 0.2D) + 0.1D + axisalignedbb.minZ;

            switch (side) {
                case WEST:
                    xOffset = x + axisalignedbb.minX - 0.1D;
                    break;
                case EAST:
                    xOffset = x + axisalignedbb.maxX + 0.1D;
                    break;
                case DOWN:
                    yOffset = y + axisalignedbb.minY - 0.1D;
                    break;
                case UP:
                    yOffset = y + axisalignedbb.maxY + 0.1D;
                    break;
                case NORTH:
                    zOffset = z + axisalignedbb.minZ - 0.1D;
                    break;
                case SOUTH:
                    zOffset = z + axisalignedbb.maxZ + 0.1D;
                    break;
            }

            world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, xOffset, yOffset, zOffset, 0.0D, 0.0D, 0.0D, Block.getStateId(state));
        }
    }

    public static final class ProspectResult {
        private final ItemStack ore;
        private double score;

        ProspectResult(ItemStack itemStack, double num) {
            ore = itemStack;
            score = num;
        }

        public Type getType() {
            if (score < 10) {
                return Type.TRACES;
            } else if (score < 20) {
                return Type.SMALL;
            } else if (score < 40) {
                return Type.MEDIUM;
            } else if (score < 80) {
                return Type.LARGE;
            } else {
                return Type.VERY_LARGE;
            }
        }

        public enum Type {
            VERY_LARGE("tfc.propick.found_very_large"),
            LARGE("tfc.propick.found_large"),
            MEDIUM("tfc.propick.found_medium"),
            SMALL("tfc.propick.found_small"),
            TRACES("tfc.propick.found_traces"),
            // right click on block
            FOUND("tfc.propick.found"),
            // nothing interesting here
            NOTHING("tfc.propick.found_nothing");

            private static final Type[] VALUES = values();
            public final String translation;

            Type(String translation) {
                this.translation = translation;
            }

            public static Type valueOf(int ordinal) {
                return VALUES[ordinal];
            }
        }
    }

    @Override
    public void addBehaviorNBT(@Nonnull ItemStack stack, @Nonnull NBTTagCompound tag) {
        tag.setBoolean(PROSPECTOR_KEY, true);
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World world, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flag) {
        tooltip.add(I18n.format("item.gt.tool.behavior.prospecting", PROSPECT_RADIUS));
    }
}
