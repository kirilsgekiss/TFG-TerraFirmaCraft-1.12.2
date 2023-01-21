package net.dries007.tfc.objects.items.itemblock;

import javax.annotation.Nonnull;

import net.dries007.tfc.objects.blocks.TFCBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.dries007.tfc.util.Helpers;

import net.dries007.tfc.objects.blocks.plants.BlockSaguaroCactusTFC;
import net.dries007.tfc.objects.te.TESaguaroCactus;

public class ItemBlockSaguaroCactus extends ItemBlockTFC
{
    protected final BlockSaguaroCactusTFC block;

    public ItemBlockSaguaroCactus(BlockSaguaroCactusTFC block)
    {
        super(block);
        this.block = block;
    }

    @Override
    @Nonnull
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack stack = player.getHeldItem(hand);
        if (!ItemStack.areItemStacksEqual(new ItemStack(stack.getItem(), stack.getCount()), stack))
        {
            return EnumActionResult.FAIL;
        }
        IBlockState state = worldIn.getBlockState(pos);

        BlockPos posAt = pos.offset(facing);
        IBlockState stateAt = worldIn.getBlockState(posAt);

        if (stateAt.getBlock().isReplaceable(worldIn, posAt))
        {
            if (!worldIn.isRemote)
            {
                switch(facing)
                {
                    case EAST:
                        worldIn.setBlockState(pos, state.withProperty(BlockSaguaroCactusTFC.EAST, Boolean.valueOf(true)));
                        worldIn.setBlockState(posAt, TFCBlocks.SAGUARO_CACTUS.getDefaultState().withProperty(BlockSaguaroCactusTFC.HORIZONTAL, Boolean.valueOf(true))
                                                                        .withProperty(BlockSaguaroCactusTFC.HORIZONTAL_DIRECTION, facing.getOpposite())
                                                                        .withProperty(BlockSaguaroCactusTFC.WEST, Boolean.valueOf(true)));
                        updateRoot(worldIn, pos, EnumFacing.EAST);
                        placeBlock(worldIn, posAt, EnumFacing.WEST, true, facing.getOpposite());
                        break;
                    case NORTH:
                        worldIn.setBlockState(pos, state.withProperty(BlockSaguaroCactusTFC.NORTH, Boolean.valueOf(true)));
                        worldIn.setBlockState(posAt, TFCBlocks.SAGUARO_CACTUS.getDefaultState().withProperty(BlockSaguaroCactusTFC.HORIZONTAL, Boolean.valueOf(true))
                                                                        .withProperty(BlockSaguaroCactusTFC.HORIZONTAL_DIRECTION, facing.getOpposite())
                                                                        .withProperty(BlockSaguaroCactusTFC.SOUTH, Boolean.valueOf(true)));
                        updateRoot(worldIn, pos, EnumFacing.NORTH);
                        placeBlock(worldIn, posAt, EnumFacing.SOUTH, true, facing.getOpposite());
                        break;
                    case SOUTH:
                        worldIn.setBlockState(pos, state.withProperty(BlockSaguaroCactusTFC.SOUTH, Boolean.valueOf(true)));
                        worldIn.setBlockState(posAt, TFCBlocks.SAGUARO_CACTUS.getDefaultState().withProperty(BlockSaguaroCactusTFC.HORIZONTAL, Boolean.valueOf(true))
                                                                        .withProperty(BlockSaguaroCactusTFC.HORIZONTAL_DIRECTION, facing.getOpposite())
                                                                        .withProperty(BlockSaguaroCactusTFC.NORTH, Boolean.valueOf(true)));
                        updateRoot(worldIn, pos, EnumFacing.SOUTH);
                        placeBlock(worldIn, posAt, EnumFacing.NORTH, true, facing.getOpposite());
                        break;
                    case WEST:
                        worldIn.setBlockState(pos, state.withProperty(BlockSaguaroCactusTFC.WEST, Boolean.valueOf(true)));
                        worldIn.setBlockState(posAt, TFCBlocks.SAGUARO_CACTUS.getDefaultState().withProperty(BlockSaguaroCactusTFC.HORIZONTAL, Boolean.valueOf(true))
                                                                        .withProperty(BlockSaguaroCactusTFC.HORIZONTAL_DIRECTION, facing.getOpposite())
                                                                        .withProperty(BlockSaguaroCactusTFC.EAST, Boolean.valueOf(true)));
                        updateRoot(worldIn, pos, EnumFacing.WEST);
                        placeBlock(worldIn, posAt, EnumFacing.EAST, true, facing.getOpposite());
                        break;
                    case UP:
                        worldIn.setBlockState(posAt, TFCBlocks.SAGUARO_CACTUS.getDefaultState());
                        placeBlock(worldIn, posAt, EnumFacing.UP, false, EnumFacing.NORTH);
                        break;
                    default:
                        break;
                }                                                                        
            }
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }

    public void updateRoot(World world, BlockPos pos, EnumFacing facing)
    {
        TESaguaroCactus tile = Helpers.getTE(world, pos, TESaguaroCactus.class);
        if (tile != null)
        {
            if (!world.isRemote)
            {
                if (facing != EnumFacing.UP) tile.setFace(facing, true);
            }
        }
    }

    public void placeBlock(World world, BlockPos pos, EnumFacing facing, boolean horizontal, EnumFacing horizontal_facing)
    {
        TESaguaroCactus tile = Helpers.getTE(world, pos, TESaguaroCactus.class);
        if (tile != null)
        {
            if (!world.isRemote)
            {
                if (facing != EnumFacing.UP) tile.setFace(facing, true);
                tile.setHorizontal(horizontal);
                tile.setFacing(horizontal_facing);
                tile.set();
            }
        }
    }
}
