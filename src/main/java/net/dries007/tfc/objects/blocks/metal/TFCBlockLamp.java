/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.metal;

import git.jbredwards.fluidlogged_api.api.util.FluidState;
import git.jbredwards.fluidlogged_api.api.util.FluidloggedUtils;
import net.dries007.tfc.TFCConfig;
import net.dries007.tfc.objects.blocks.property.ILightableBlock;
import net.dries007.tfc.objects.te.TELamp;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.calendar.ICalendar;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@ParametersAreNonnullByDefault
public class TFCBlockLamp extends Block implements ILightableBlock {
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.VERTICAL);
    private static final AxisAlignedBB AABB_UP = new AxisAlignedBB(0.3125, 0, 0.3125, 0.6875, 0.5, 0.6875);
    private static final AxisAlignedBB AABB_DOWN = new AxisAlignedBB(0.3125, 0, 0.3125, 0.6875, 1, 0.6875);

    private static final Map<gregtech.api.unification.material.Material, TFCBlockLamp> MAP = new HashMap<>();

    public static TFCBlockLamp get(gregtech.api.unification.material.Material metal) {
        return MAP.get(metal);
    }

    private final gregtech.api.unification.material.Material metal;

    public TFCBlockLamp(gregtech.api.unification.material.Material metal) {
        super(Material.REDSTONE_LIGHT);

        this.metal = metal;
        if (MAP.put(metal, this) != null) throw new IllegalStateException("There can only be one.");

        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.UP).withProperty(LIT, false));
        setHardness(1f);
        setTickRandomly(true);
        setSoundType(SoundType.METAL);
    }

    public gregtech.api.unification.material.Material getMetal() {
        return metal;
    }

    //Don't need to do any clientside display ticking, as no smoke particles... yet
    //public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)

    //may support wall attachments sometime
    @SuppressWarnings("deprecation")
    @Override
    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        IBlockState iblockstate = this.getDefaultState();

        return iblockstate.withProperty(FACING, EnumFacing.byIndex(meta % 2)).withProperty(LIT, meta >= 2);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex() + (state.getValue(LIT) ? 2 : 0);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isBlockNormalCube(IBlockState state) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isNormalCube(IBlockState state) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        switch (state.getValue(FACING)) {
            case UP:
                return AABB_UP;
            case DOWN:
            default:
                return AABB_DOWN;
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    @Nonnull
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    @SideOnly(Side.CLIENT)
    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
        return getBoundingBox(state, worldIn, pos).offset(pos);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
        if (state.getValue(LIT) && TFCConfig.Devices.LAMP.burnRate > 0) {
            TELamp tel = Helpers.getTE(worldIn, pos, TELamp.class);
            if (tel != null) {
                checkFuel(worldIn, pos, state, tel);
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        TELamp tel = Helpers.getTE(worldIn, pos, TELamp.class);
        if (tel != null) {
            if (worldIn.isBlockPowered(pos) && !tel.isPowered()) //power on
            {
                lightWithFuel(worldIn, pos, state, tel);
                tel.setPowered(true);
            } else if (!worldIn.isBlockPowered(pos) && tel.isPowered()) //power off
            {
                if (!checkFuel(worldIn, pos, state, tel)) //if it didn't run out turn it off anyway
                {
                    worldIn.setBlockState(pos, state.withProperty(LIT, false));
                    tel.setPowered(false);
                    tel.resetCounter();
                }
            }
        }
        if (!canPlaceAt(worldIn, pos, state.getValue(FACING))) {
            worldIn.destroyBlock(pos, true);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    @Nonnull
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    /**
     * Checks if this block can be placed exactly at the given position.
     */
    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        for (EnumFacing enumfacing : FACING.getAllowedValues()) {
            if (this.canPlaceAt(worldIn, pos, enumfacing)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TELamp tel = Helpers.getTE(worldIn, pos, TELamp.class);
        ItemStack stack = playerIn.getHeldItem(hand);
        if (!worldIn.isRemote && tel != null) {
            if (state.getValue(LIT)) {
                if (!checkFuel(worldIn, pos, state, tel)) //if it didn't run out turn it off
                {
                    worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(LIT, false));
                    tel.resetCounter();
                }
            } else if (stack.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)) { //refill only if not lit
                IFluidHandler fluidHandler = tel.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
                if (fluidHandler != null) {
                    FluidUtil.interactWithFluidHandler(playerIn, hand, fluidHandler);
                    tel.markDirty();
                }
            }
        }
        return true;
    }

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        if (this.canPlaceAt(worldIn, pos, facing)) {
            return this.getDefaultState().withProperty(FACING, facing);
        } else if (this.canPlaceAt(worldIn, pos, EnumFacing.UP)) {
            return this.getDefaultState().withProperty(FACING, EnumFacing.UP);
        } else if (this.canPlaceAt(worldIn, pos, EnumFacing.DOWN)) // last resort, must have matched in canPlaceAt test
        {
            return this.getDefaultState().withProperty(FACING, EnumFacing.DOWN);
        }
        return this.getDefaultState(); //should never happen
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack tool) {
        super.harvestBlock(world, player, pos, state, te, tool);
        world.setBlockToAir(pos);
    }

    // after BlockBarrel#onBlockPlacedBy
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (!worldIn.isRemote && stack.getTagCompound() != null) {
            // Set the initial counter value and fill from item
            TELamp tile = Helpers.getTE(worldIn, pos, TELamp.class);
            if (tile != null) {
                tile.resetCounter();
                tile.loadFromItemStack(stack);
            }
            worldIn.setBlockState(pos, state.withProperty(LIT, false));
        }
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, LIT);
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        return state.getValue(LIT) ? 15 : 0;
    }

    @Override
    public boolean isNormalCube(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return false;
    }

    //Lifted from BlockFlowerPot

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        return willHarvest || super.removedByPlayer(state, world, pos, player, willHarvest); // delay deletion of the block until after getDrops
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TELamp();
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        TELamp tile = Helpers.getTE(world, pos, TELamp.class);
        if (tile != null) {
            if (tile.getFuel() == 0) {
                super.getDrops(drops, world, pos, state, fortune);
            } else {
                drops.add(tile.getItemStack(tile, state));
            }
        }
    }

    @Override
    @Nonnull
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world,
                                  BlockPos pos, EntityPlayer player) {
        TELamp tile = Helpers.getTE(world, pos, TELamp.class);
        if (tile != null) {
            return tile.getItemStack(tile, state);
        }
        return new ItemStack(state.getBlock());
    }

    private boolean lightWithFuel(World worldIn, BlockPos pos, IBlockState state, TELamp tel) {
        if (tel.getFuel() > 0) {
            worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(LIT, true));
            tel.resetCounter();
            return true;
        }
        return false;
    }

    private boolean checkFuel(World worldIn, BlockPos pos, IBlockState state, TELamp tel) {
        IFluidHandler fluidHandler = tel.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
        boolean ranOut = false;
        if (!worldIn.isRemote && fluidHandler != null) {
            long ticks = tel.getTicksSinceUpdate();
            double usage = TFCConfig.Devices.LAMP.burnRate * ticks / ICalendar.TICKS_IN_HOUR;
            if (usage >= 1) // minimize rounding issues
            {
                FluidStack used = fluidHandler.drain((int) usage, true); // use fuel
                if (used == null || used.amount < (int) usage) {
                    worldIn.setBlockState(pos, state.withProperty(LIT, false));
                    ranOut = true;
                }
                tel.resetCounter();
            }
        }
        return ranOut;
    }

    private boolean canPlaceOn(World worldIn, BlockPos pos) {
        IBlockState state = worldIn.getBlockState(pos);
        return state.getBlock().canPlaceTorchOnTop(state, worldIn, pos);
    }

    private boolean canPlaceAt(World worldIn, BlockPos pos, EnumFacing facing) {
        if (!FACING.getAllowedValues().contains(facing)) {
            return false;
        }

        BlockPos blockpos = pos.offset(facing.getOpposite());
        IBlockState iblockstate = worldIn.getBlockState(blockpos);
        Block block = iblockstate.getBlock();
        BlockFaceShape blockfaceshape = iblockstate.getBlockFaceShape(worldIn, blockpos, facing);

        if (facing.equals(EnumFacing.UP) && this.canPlaceOn(worldIn, blockpos)) {
            return true;
        } else if (facing != EnumFacing.UP && facing != EnumFacing.DOWN) {
            return !isExceptBlockForAttachWithPiston(block) && blockfaceshape == BlockFaceShape.SOLID;
        } else return facing == EnumFacing.DOWN && blockfaceshape == BlockFaceShape.SOLID;
    }

    /**
     * use this if your block can be fluidlogged.
     *
     * @author jbred
     */
    public interface IFluidloggable {
        /**
         * @return true if the IBlockState is fluidloggable
         */
        default boolean isFluidloggable(@Nonnull IBlockState state, @Nonnull World world, @Nonnull BlockPos pos) {
            return true;
        }

        /**
         * @return true if the IBlockState can be fluidlogged with the input fluid
         */
        default boolean isFluidValid(@Nonnull IBlockState state, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull Fluid fluid) {
            return isFluidloggable(state, world, pos);
        }

        /**
         * called by {@link FluidloggedUtils#canFluidFlow},
         * which is invoked a lot, so try to keep the code for this fairly light.
         *
         * @return true if the contained fluid can flow from the specified side,
         * or if a fluid can flow into this block from the specified side
         */
        default boolean canFluidFlow(@Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull IBlockState here, @Nonnull EnumFacing side) {
            return here.getBlockFaceShape(world, pos, side) != BlockFaceShape.SOLID;
        }

        /**
         * @return true if the FluidState should be visible while this is fluidlogged
         */
        @SideOnly(Side.CLIENT)
        default boolean shouldFluidRender(@Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull IBlockState here, @Nonnull FluidState fluidState) {
            return true;
        }

        /**
         * called by {@link FluidloggedUtils#setFluidState}
         * when the stored FluidState is changed
         *
         * @return PASS - run & return {@link FluidloggedUtils#setFluidState_Internal},
         * FAIL - assume the change never happened,
         * SUCCESS - assume the change happened
         */
        @Nonnull
        default EnumActionResult onFluidChange(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState here, @Nonnull FluidState newFluid, int blockFlags) {
            return newFluid.isEmpty() ? onFluidDrain(world, pos, here, blockFlags) : onFluidFill(world, pos, here, newFluid, blockFlags);
        }

        /**
         * convenience method called by {@link IFluidloggable#onFluidChange} when a new FluidState is put here
         */
        @Nonnull
        default EnumActionResult onFluidFill(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState here, @Nonnull FluidState newFluid, int blockFlags) {
            return EnumActionResult.PASS;
        }

        /**
         * convenience method called by {@link IFluidloggable#onFluidChange} when the stored FluidState is removed
         */
        @Nonnull
        default EnumActionResult onFluidDrain(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState here, int blockFlags) {
            return EnumActionResult.PASS;
        }
    }
}
