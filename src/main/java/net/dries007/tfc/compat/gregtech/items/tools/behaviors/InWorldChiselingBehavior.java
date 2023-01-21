package net.dries007.tfc.compat.gregtech.items.tools.behaviors;

import gregtech.api.items.toolitem.ToolHelper;
import gregtech.api.items.toolitem.behavior.IToolBehavior;
import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.api.capability.player.CapabilityPlayerData;
import net.dries007.tfc.api.capability.player.IPlayerData;
import net.dries007.tfc.api.recipes.ChiselRecipe;
import net.dries007.tfc.api.util.FallingBlockManager;
import net.dries007.tfc.objects.blocks.rock.TFCBlockRockSmooth;
import net.dries007.tfc.objects.blocks.wood.TFCBlockWoodSupport;
import net.dries007.tfc.objects.container.ContainerEmpty;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import net.dries007.tfc.api.recipes.ChiselRecipe.Mode;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static net.dries007.tfc.compat.gregtech.items.tools.TFCToolHelper.IN_WORLD_CHISELING_KEY;

public class InWorldChiselingBehavior implements IToolBehavior {

    public static final InWorldChiselingBehavior INSTANCE = new InWorldChiselingBehavior();

    protected InWorldChiselingBehavior() {/**/}

    @Override
    @Nonnull
    public EnumActionResult onItemUse(@NotNull EntityPlayer player, @NotNull World worldIn, @NotNull BlockPos pos, @NotNull EnumHand hand, @NotNull EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        // Find the block to place for this action
        IBlockState newState = getChiselResultState(player, worldIn, pos, facing, hitX, hitY, hitZ);
        if (newState != null)
        {
            // play a sound matching the new block
            SoundType soundType = newState.getBlock().getSoundType(newState, worldIn, pos, player);
            worldIn.playSound(player, pos, soundType.getHitSound(), SoundCategory.BLOCKS, 1.0f, soundType.getPitch());

            // only update the world state on the server side
            if (!worldIn.isRemote)
            {
                // replace the block with a new block
                if (ConfigTFC.General.FALLABLE.chiselCausesCollapse)
                {
                    IBlockState oldState = worldIn.getBlockState(pos);
                    FallingBlockManager.Specification oldSpec = FallingBlockManager.getSpecification(oldState);
                    if (oldSpec != null && oldSpec.isCollapsable() && !TFCBlockWoodSupport.isBeingSupported(worldIn, pos))
                    {
                        worldIn.setBlockToAir(pos); // Set block to air before attempting a collapse mechanic
                        if (FallingBlockManager.checkCollapsingArea(worldIn, pos))
                        {
                            return EnumActionResult.SUCCESS; // Collapse mechanic triggered, cancel chisel!
                        }
                    }
                }
                if (newState.getProperties().containsKey(TFCBlockRockSmooth.CAN_FALL))
                {
                    newState = newState.withProperty(TFCBlockRockSmooth.CAN_FALL, true);
                }
                worldIn.setBlockState(pos, newState);

                // spawn a slab if necessary
                IPlayerData capability = player.getCapability(CapabilityPlayerData.CAPABILITY, null);
                if (capability != null)
                {
                    if (capability.getChiselMode() == ChiselRecipe.Mode.SLAB)
                    {
                        InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(newState.getBlock(), 1));
                    }
                }

                ToolHelper.damageItem(player.getHeldItem(hand), player);
                if (ConfigTFC.Devices.CHISEL.hasDelay)
                {
                    // if setting is on for chisel cooldown, trigger cooldown
                    player.getCooldownTracker().setCooldown(player.getHeldItem(hand).getItem(), COOLDOWN);
                }
            }
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }

    @Override
    public void addBehaviorNBT(@Nonnull ItemStack stack, @Nonnull NBTTagCompound tag) {
        tag.setBoolean(IN_WORLD_CHISELING_KEY, true);
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World world, @Nonnull List<String> tooltip, @Nonnull ITooltipFlag flag) {
        tooltip.add(I18n.format("item.gt.tool.behavior.in_world_chiseling"));
    }

    private static final int[] STAIR_PATTERN_INDICES = {0, 3, 4, 6, 7, 8};
    private static final int[] SLAB_PATTERN_INDICES = {0, 1, 2};

    private static final int COOLDOWN = 10;

    /**
     * Calculates the block that would be set in the specified position if the chisel were used.
     * In most conditions will return null. If not null, then a successful chisel operation can be completed.
     * <br><br>
     * code logic overview for finding result state:<br>
     * state = world.getStateAt(pos)<br>
     * block = state.getBlock()<br>
     * itemStack = block.getPickBlock(state, pos)<br>
     * resultStack = CraftingInventory.getResult(itemStack)<br>
     * resultBlock = (resultStack.getItem() as ItemBlock).getBlock()<br>
     * resultState = resultBlock.getPlacementState()<br>
     * <br>
     *
     * @param player  player who clicked on the block
     * @param worldIn world the block is in
     * @param pos     pos of block interacted with
     * @param facing  side of block that was hit
     * @return null if the operation would not succeed. resulting state for if it would succeed.
     */
    @Nullable
    public static IBlockState getChiselResultState(EntityPlayer player, World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        // no chiseling if no hammer is present
        if (hasHammerForChisel(player))
        {
            IBlockState state = worldIn.getBlockState(pos);
            // get the capability that tells us the current player selected mode for chiseling
            IPlayerData capability = player.getCapability(CapabilityPlayerData.CAPABILITY, null);
            if (capability != null)
            {
                return getRecipeResult(player, worldIn, pos, facing, capability.getChiselMode(), state, hitX, hitY, hitZ);
            }
        }
        return null;
    }

    public static boolean hasHammerForChisel(EntityPlayer player)
    {
        // offhand always counts as a hammer slot
        if (OreDictionaryHelper.doesStackMatchOre(player.inventory.offHandInventory.get(0), "craftingToolHammer"))
            return true;

        // config alters whether toolbar counts as a hammer slot or not.
        if (!ConfigTFC.Devices.CHISEL.requireHammerInOffHand)
        {
            for (int i = 0; i < 9; i++)
            {
                if (OreDictionaryHelper.doesStackMatchOre(player.inventory.mainInventory.get(i), "craftingToolHammer"))
                {
                    return true;
                }
            }
        }

        return false;
    }

    @Nullable
    @SuppressWarnings("deprecation")
    private static IBlockState getRecipeResult(EntityPlayer player, World worldIn, BlockPos pos, EnumFacing facing, Mode chiselMode, IBlockState targetState, float hitX, float hitY, float hitZ)
    {
        if (chiselMode == Mode.SMOOTH)
        {
            ChiselRecipe recipe = ChiselRecipe.get(targetState);
            if (recipe != null)
            {
                return recipe.getOutputState();
            }
        }
        else if (chiselMode == Mode.SLAB || chiselMode == Mode.STAIR)
        {
            //noinspection ConstantConditions
            ItemStack targetStack = targetState.getBlock().getPickBlock(targetState, null, worldIn, pos, player);
            ItemStack resultItemStack = findCraftingResult(worldIn, targetStack, (chiselMode == Mode.SLAB ? SLAB_PATTERN_INDICES : STAIR_PATTERN_INDICES));

            if (resultItemStack != null)
            {
                Item resultItem = resultItemStack.getItem();
                Block block;
                if (resultItem instanceof ItemBlock)
                {
                    block = ((ItemBlock) resultItem).getBlock();

                    if ((chiselMode == Mode.SLAB && block instanceof BlockSlab) || (chiselMode == Mode.STAIR && block instanceof BlockStairs))
                    {
                        if (facing.getAxis().getPlane() != EnumFacing.Plane.VERTICAL)
                            hitY = 1 - hitY;

                        return block.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, resultItemStack.getMetadata(), player);
                    }
                }
            }
        }
        return null;
    }

    /**
     * Determines what ItemBlocks can be crafted from a single ingredient in a given pattern
     *
     * @param world              world instance
     * @param craftingIngredient ingredient to use to try to craft stairs or slabs with
     * @param craftingIndices    stair pattern or slab pattern indices
     * @return the result of crafting the ingredient in the listed pattern, null if no crafting result
     */
    @Nullable
    private static ItemStack findCraftingResult(World world, ItemStack craftingIngredient, int[] craftingIndices)
    {
        InventoryCrafting craftMatrix = new InventoryCrafting(new ContainerEmpty(), 3, 3);
        for (int index : craftingIndices)
        {
            craftMatrix.setInventorySlotContents(index, craftingIngredient.copy());
        }

        for (IRecipe recipe : ForgeRegistries.RECIPES.getValuesCollection())
        {
            if (recipe.matches(craftMatrix, world))
            {
                // Found matching recipe, make sure it represents a block first
                ItemStack stackOut = recipe.getCraftingResult(craftMatrix);
                if (stackOut.getItem() instanceof ItemBlock)
                {
                    return stackOut;
                }
            }
        }
        return null;
    }
}
