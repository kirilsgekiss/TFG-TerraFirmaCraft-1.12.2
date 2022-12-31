package tfcflorae;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.Constants;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.*;
import net.dries007.tfc.objects.blocks.stone.BlockRockVariant;
import net.dries007.tfc.objects.container.CapabilityContainerListener;
import net.dries007.tfc.types.DefaultPlants;
import net.dries007.tfc.util.calendar.CalendarTFC;
import net.dries007.tfc.util.calendar.Month;
import net.dries007.tfc.util.skills.SmithingSkill;

import tfcflorae.objects.items.ItemsTFCF;
import net.dries007.tfc.api.types.Rock.Type;

import static tfcflorae.TFCFlorae.TFCFLORAE_MODID;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = TFCFLORAE_MODID)
public final class CommonEventHandlerTFCF
{
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    @SubscribeEvent
    public void onBlockHarvestDrops(BlockEvent.HarvestDropsEvent event)
    {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        IBlockState state = event.getState();
        Block block = state.getBlock();
        Month month = CalendarTFC.CALENDAR_TIME.getMonthOfYear();

        for (Plant plant : TFCRegistries.PLANTS.getValuesCollection())
        {
            if (plant == TFCRegistries.PLANTS.getValue(DefaultPlants.BARREL_CACTUS) && (month == Month.SEPTEMBER || month == Month.OCTOBER || month == Month.NOVEMBER))
            {
                int chance = Constants.RNG.nextInt(2);
                if (chance == 0)
                {
                    event.getDrops().clear();
                    event.getDrops().add(new ItemStack(ItemsTFCF.BARREL_CACTUS_FRUIT, 1 + Constants.RNG.nextInt(3)));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onBreakProgressEvent(BreakSpeed event)
    {
        EntityPlayer player = event.getEntityPlayer();
        if (player != null)
        {
            ItemStack stack = player.getHeldItemMainhand();
            float skillModifier = SmithingSkill.getSkillBonus(stack, SmithingSkill.Type.TOOLS);
            if (skillModifier > 0)
            {
                // Up to 2x modifier for break speed for skill bonuses on tools
                // New speed, so it will take into account other mods' modifications
                event.setNewSpeed(event.getNewSpeed() + (event.getNewSpeed() * skillModifier));
            }
        }
        if (event.getState().getBlock() instanceof BlockRockVariant)
        {
            event.setNewSpeed((float) (event.getNewSpeed() / ConfigTFC.General.MISC.rockMiningTimeModifier));
        }
    }

    @SubscribeEvent
    public static void onUseHoe(UseHoeEvent event)
    {
        if (ConfigTFCF.General.WORLD.enableAllBlockTypes)
        {
            World world = event.getWorld();
            BlockPos pos = event.getPos();
            IBlockState state = world.getBlockState(pos);

            if (ConfigTFC.General.OVERRIDES.enableHoeing)
            {
                if (state.getBlock() instanceof BlockRockVariant)
                {
                    BlockRockVariant blockRock = (BlockRockVariant) state.getBlock();
                    if 
                        (
                            blockRock.getType() == Type.PODZOL ||
                            blockRock.getType() == Type.SPARSE_GRASS
                        )
                    {
                        if (!world.isRemote)
                        {
                            world.playSound(null, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            world.setBlockState(pos,
                                    BlockRockVariant.get(blockRock.getRock(), Type.FARMLAND).getDefaultState());
                        }
                        event.setResult(Event.Result.ALLOW);
                    }
                    else if 
                        (
                            blockRock.getType() == Type.COARSE_DIRT
                        )
                    {
                        if (!world.isRemote)
                        {
                            world.playSound(null, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            world.setBlockState(pos,
                                    BlockRockVariant.get(blockRock.getRock(), Type.DIRT).getDefaultState());
                        }
                        event.setResult(Event.Result.ALLOW);
                    }
                    else if (ConfigTFCF.General.WORLD.enableAllFarmland && ConfigTFCF.General.WORLD.enableAllSpecialSoil &&
                        (
                            blockRock.getType() == Type.LOAMY_SAND ||
                            blockRock.getType() == Type.LOAMY_SAND_GRASS ||
                            blockRock.getType() == Type.LOAMY_SAND_PODZOL ||
                            blockRock.getType() == Type.DRY_LOAMY_SAND_GRASS ||
                            blockRock.getType() == Type.SPARSE_LOAMY_SAND_GRASS
                        ))
                    {
                        if (!world.isRemote)
                        {
                            world.playSound(null, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            world.setBlockState(pos, BlockRockVariant.get(blockRock.getRock(), Type.LOAMY_SAND_FARMLAND)
                                    .getDefaultState());
                        }
                        event.setResult(Event.Result.ALLOW);
                    }
                    else if (ConfigTFCF.General.WORLD.enableAllFarmland && ConfigTFCF.General.WORLD.enableAllSpecialSoil &&
                        (
                            blockRock.getType() == Type.SANDY_LOAM ||
                            blockRock.getType() == Type.SANDY_LOAM_GRASS ||
                            blockRock.getType() == Type.SANDY_LOAM_PODZOL ||
                            blockRock.getType() == Type.DRY_SANDY_LOAM_GRASS ||
                            blockRock.getType() == Type.SPARSE_SANDY_LOAM_GRASS
                        ))
                    {
                        if (!world.isRemote)
                        {
                            world.playSound(null, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            world.setBlockState(pos, BlockRockVariant.get(blockRock.getRock(), Type.SANDY_LOAM_FARMLAND)
                                    .getDefaultState());
                        }
                        event.setResult(Event.Result.ALLOW);
                    }
                    else if (ConfigTFCF.General.WORLD.enableAllFarmland && ConfigTFCF.General.WORLD.enableAllSpecialSoil &&
                        (
                            blockRock.getType() == Type.LOAM ||
                            blockRock.getType() == Type.LOAM_GRASS ||
                            blockRock.getType() == Type.LOAM_PODZOL ||
                            blockRock.getType() == Type.DRY_LOAM_GRASS ||
                            blockRock.getType() == Type.SPARSE_LOAM_GRASS
                        ))
                    {
                        if (!world.isRemote)
                        {
                            world.playSound(null, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            world.setBlockState(pos,
                                    BlockRockVariant.get(blockRock.getRock(), Type.LOAM_FARMLAND).getDefaultState());
                        }
                        event.setResult(Event.Result.ALLOW);
                    }
                    else if (ConfigTFCF.General.WORLD.enableAllFarmland && ConfigTFCF.General.WORLD.enableAllSpecialSoil &&
                        (
                            blockRock.getType() == Type.SILT_LOAM ||
                            blockRock.getType() == Type.SILT_LOAM_GRASS ||
                            blockRock.getType() == Type.SILT_LOAM_PODZOL ||
                            blockRock.getType() == Type.DRY_SILT_LOAM_GRASS ||
                            blockRock.getType() == Type.SPARSE_SILT_LOAM_GRASS
                        ))
                    {
                        if (!world.isRemote)
                        {
                            world.playSound(null, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            world.setBlockState(pos, BlockRockVariant.get(blockRock.getRock(), Type.SILT_LOAM_FARMLAND)
                                    .getDefaultState());
                        }
                        event.setResult(Event.Result.ALLOW);
                    }
                    else if (ConfigTFCF.General.WORLD.enableAllFarmland && ConfigTFCF.General.WORLD.enableAllSpecialSoil &&
                        (
                            blockRock.getType() == Type.SILT ||
                            blockRock.getType() == Type.SILT_GRASS ||
                            blockRock.getType() == Type.SILT_PODZOL ||
                            blockRock.getType() == Type.DRY_SILT_GRASS ||
                            blockRock.getType() == Type.SPARSE_SILT_GRASS
                        ))
                    {
                        if (!world.isRemote)
                        {
                            world.playSound(null, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            world.setBlockState(pos,
                                    BlockRockVariant.get(blockRock.getRock(), Type.SILT_FARMLAND).getDefaultState());
                        }
                        event.setResult(Event.Result.ALLOW);
                    }
                    else if (ConfigTFCF.General.WORLD.enableAllFarmland && ConfigTFCF.General.WORLD.enableAllSpecialSoil &&
                        (
                            blockRock.getType() == Type.HUMUS ||
                            blockRock.getType() == Type.HUMUS_GRASS ||
                            blockRock.getType() == Type.DRY_HUMUS_GRASS ||
                            blockRock.getType() == Type.SPARSE_HUMUS_GRASS
                        ))
                    {
                        if (!world.isRemote)
                        {
                            world.playSound(null, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            world.setBlockState(pos,
                                    BlockRockVariant.get(blockRock.getRock(), Type.HUMUS_FARMLAND).getDefaultState());
                        }
                        event.setResult(Event.Result.ALLOW);
                    }
                    else if (ConfigTFCF.General.WORLD.enableAllSpecialSoil &&
                        (
                            blockRock.getType() == Type.COARSE_LOAMY_SAND
                        ))
                    {
                        if (!world.isRemote)
                        {
                            world.playSound(null, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            world.setBlockState(pos,
                                    BlockRockVariant.get(blockRock.getRock(), Type.LOAMY_SAND).getDefaultState());
                        }
                        event.setResult(Event.Result.ALLOW);
                    }
                    else if (ConfigTFCF.General.WORLD.enableAllSpecialSoil &&
                        (
                            blockRock.getType() == Type.COARSE_SANDY_LOAM
                        ))
                    {
                        if (!world.isRemote)
                        {
                            world.playSound(null, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            world.setBlockState(pos,
                                    BlockRockVariant.get(blockRock.getRock(), Type.SANDY_LOAM).getDefaultState());
                        }
                        event.setResult(Event.Result.ALLOW);
                    }
                    else if (ConfigTFCF.General.WORLD.enableAllSpecialSoil &&
                        (
                            blockRock.getType() == Type.COARSE_LOAM
                        ))
                    {
                        if (!world.isRemote)
                        {
                            world.playSound(null, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            world.setBlockState(pos,
                                    BlockRockVariant.get(blockRock.getRock(), Type.LOAM).getDefaultState());
                        }
                        event.setResult(Event.Result.ALLOW);
                    }
                    else if (ConfigTFCF.General.WORLD.enableAllSpecialSoil &&
                        (
                            blockRock.getType() == Type.COARSE_SILT_LOAM
                        ))
                    {
                        if (!world.isRemote)
                        {
                            world.playSound(null, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            world.setBlockState(pos,
                                    BlockRockVariant.get(blockRock.getRock(), Type.SILT_LOAM).getDefaultState());
                        }
                        event.setResult(Event.Result.ALLOW);
                    }
                    else if (ConfigTFCF.General.WORLD.enableAllSpecialSoil &&
                        (
                            blockRock.getType() == Type.COARSE_SILT
                        ))
                    {
                        if (!world.isRemote)
                        {
                            world.playSound(null, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            world.setBlockState(pos,
                                    BlockRockVariant.get(blockRock.getRock(), Type.SILT).getDefaultState());
                        }
                        event.setResult(Event.Result.ALLOW);
                    }
                    else if (ConfigTFCF.General.WORLD.enableAllSpecialSoil &&
                        (
                            blockRock.getType() == Type.COARSE_HUMUS
                        ))
                    {
                        if (!world.isRemote)
                        {
                            world.playSound(null, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                            world.setBlockState(pos,
                                    BlockRockVariant.get(blockRock.getRock(), Type.HUMUS).getDefaultState());
                        }
                        event.setResult(Event.Result.ALLOW);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onContainerOpen(PlayerContainerEvent.Open event)
    {
        if (event.getEntityPlayer() instanceof EntityPlayerMP)
        {
            // Capability Sync Handler
            CapabilityContainerListener.addTo(event.getContainer(), (EntityPlayerMP) event.getEntityPlayer());
        }
    }
}
