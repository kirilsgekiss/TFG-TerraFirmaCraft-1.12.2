package net.dries007.tfc.objects.blocks.wood.cinnamon;

import java.util.Random;
import javax.annotation.Nonnull;

import net.dries007.tfc.objects.items.TFCItems;
import net.dries007.tfc.objects.items.food.TFCItemFood;
import net.dries007.tfc.util.agriculture.Food;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.util.calendar.CalendarTFC;
import net.dries007.tfc.util.calendar.Month;

import net.dries007.tfc.util.OreDictionaryHelper;

import static net.dries007.tfc.api.stateproperty.StatePropertiesTFC.*;

public class BlockCeylonCinnamonLog extends Block
{
    public static final AxisAlignedBB SMALL_LOG = new AxisAlignedBB(0.25, 0, 0.25, 0.75, 1, 0.75);
    public static final AxisAlignedBB SMALLER_LOG = new AxisAlignedBB(0.375, 0, 0.375, 0.625, 1, 0.625);

    public BlockCeylonCinnamonLog()
    {
        super(Material.WOOD, MapColor.ORANGE_STAINED_HARDENED_CLAY);
        setHarvestLevel("axe", 0);
        setHardness(2.0F);
        setResistance(5.0F);
        Blocks.FIRE.setFireInfo(this, 5, 5);
        setTickRandomly(true);
        setSoundType(SoundType.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(GROWN, true).withProperty(CAN_GROW, false).withProperty(CONNECTED, false));
        OreDictionaryHelper.register(this, "log", "wood");
    }

    private boolean hasGoodConditions(World world, BlockPos pos, IBlockState state)
    {
        BlockPos checkPos = pos.offset(EnumFacing.UP, 1);
        IBlockState checkState = world.getBlockState(checkPos);
        int leafCount = 0;
        while (checkState.getBlock() instanceof BlockCeylonCinnamonLog)
        {
            if (checkState.getValue(CONNECTED))
            {
                for (EnumFacing d : EnumFacing.HORIZONTALS)
                {
                    if (world.getBlockState(checkPos.offset(d)).getBlock() instanceof BlockCeylonCinnamonLeaves)
                    {
                        leafCount++;
                        if (world.getBlockState(checkPos.offset(d, 2)).getBlock() instanceof BlockCeylonCinnamonLeaves)
                        {
                            leafCount++;
                            if (world.getBlockState(checkPos.offset(d, 3)).getBlock() instanceof BlockCeylonCinnamonLeaves)
                            {
                                leafCount++;
                            }
                        }
                    }
                }
            }
            checkPos = checkPos.offset(EnumFacing.UP, 1);
            checkState = world.getBlockState(checkPos);
        }
        return leafCount > 15;
    }


    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random)
    {
        super.updateTick(worldIn, pos, state, random);
        if (!worldIn.isRemote)
        {
            if (!state.getValue(GROWN))
            {
                Month month = CalendarTFC.CALENDAR_TIME.getMonthOfYear();
                if ((month == Month.APRIL || month == Month.MAY) && state.getValue(CAN_GROW) && hasGoodConditions(worldIn, pos, state))
                {
                    worldIn.setBlockState(pos, state.withProperty(GROWN, true).withProperty(CAN_GROW, false));
                }
                else if (!state.getValue(CAN_GROW) && hasGoodConditions(worldIn, pos, state) && !(month == Month.APRIL || month == Month.MAY))
                {
                    worldIn.setBlockState(pos, state.withProperty(CAN_GROW, true));
                }
            }
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            ItemStack held = player.getHeldItem(hand);
            if (OreDictionaryHelper.doesStackMatchOre(held, "knife"))
            {
                if (!state.getValue(CONNECTED) && state.getValue(GROWN))
                {
                    world.setBlockState(pos, state.withProperty(GROWN, false));
                    held.damageItem(1, player);
                    ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(TFCItemFood.get(Food.CEYLON_CINNAMON_BARK), 1));
                }

            }
        }
        return true;
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        drops.add(new ItemStack(TFCItems.CEYLON_CINNAMON_POLE, 1));
    }

    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
        IBlockState downState = worldIn.getBlockState(pos.down());
        boolean shouldDestroy = true;
        if (downState.getBlock() instanceof BlockCeylonCinnamonLog || BlocksTFC.isGrowableSoil(downState))
            shouldDestroy = false;
        if (shouldDestroy)
        {
            worldIn.destroyBlock(pos, true);
            return;
        }
        boolean shouldConnect = false;
        for (EnumFacing facing : EnumFacing.HORIZONTALS)
        {
            if (worldIn.getBlockState(pos.offset(facing)).getBlock() instanceof BlockCeylonCinnamonLeaves)
            {
                worldIn.setBlockState(pos, state.withProperty(CONNECTED, true));
                shouldConnect = true;
                break;
            }
        }
        worldIn.setBlockState(pos, state.withProperty(CONNECTED, shouldConnect));
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, GROWN, CAN_GROW, CONNECTED);
    }

    @Override
    @SuppressWarnings("deprecation")
    @Nonnull
    public IBlockState getStateFromMeta(int meta)
    {
        boolean can_grow = false;
        boolean grown = false;
        if (meta >= 4)
        {
            meta -= 4;
            can_grow = true;
        }
        if (meta >= 2)
        {
            meta -= 2;
            grown = true;
        }
        return this.getDefaultState().withProperty(CONNECTED, meta == 1).withProperty(GROWN, grown).withProperty(CAN_GROW, can_grow);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int wet = state.getValue(CONNECTED) ? 1 : 0;
        int grown = state.getValue(GROWN) ? 2 : 0;
        int can_grow = state.getValue(CAN_GROW) ? 4 : 0;
        return wet + grown + can_grow;
    }

    @Override
    @SuppressWarnings("deprecation")
    @Nonnull
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        if (state.getValue(CONNECTED))
        {
            return FULL_BLOCK_AABB;
        }
        if (!state.getValue(GROWN))
        {
            return SMALLER_LOG;
        }
        return SMALL_LOG;
    }

    @Override
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        if (state.getValue(CONNECTED))
        {
            return FULL_BLOCK_AABB;
        }
        if (!state.getValue(GROWN))
        {
            return SMALLER_LOG;
        }
        return SMALL_LOG;
    }

    @Override
    @SuppressWarnings("deprecation")
    @Nonnull
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
}
