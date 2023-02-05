/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.metal;

import git.jbredwards.fluidlogged_api.api.util.FluidState;
import git.jbredwards.fluidlogged_api.api.util.FluidloggedUtils;
import mcp.MethodsReturnNonnullByDefault;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.client.TFCGuiHandler;
import net.dries007.tfc.client.TFCSounds;
import net.dries007.tfc.client.particle.TFCParticles;
import net.dries007.tfc.objects.items.metal.ItemAnvil;
import net.dries007.tfc.objects.te.TEAnvilTFC;
import net.dries007.tfc.util.Helpers;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static net.dries007.tfc.Constants.RNG;
import static net.dries007.tfc.objects.te.TEAnvilTFC.SLOT_HAMMER;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class TFCBlockMetalAnvil extends Block {
    public static final PropertyDirection AXIS = PropertyDirection.create("axis", EnumFacing.Plane.HORIZONTAL);

    private static final AxisAlignedBB AABB_Z = new AxisAlignedBB(0.1875, 0, 0, 0.8125, 0.6875, 1);
    private static final AxisAlignedBB AABB_X = new AxisAlignedBB(0, 0, 0.1875, 1, 0.6875, 0.8125);

    private static final Map<gregtech.api.unification.material.Material, TFCBlockMetalAnvil> MAP = new HashMap<>();

    public static TFCBlockMetalAnvil get(gregtech.api.unification.material.Material metal) {
        return MAP.get(metal);
    }

    public static ItemStack get(gregtech.api.unification.material.Material metal, int amount) {
        return new ItemStack(MAP.get(metal), amount);
    }

    private final gregtech.api.unification.material.Material metal;

    public TFCBlockMetalAnvil(gregtech.api.unification.material.Material metal) {
        super(Material.IRON);

        this.metal = metal;
        if (MAP.put(metal, this) != null) throw new IllegalStateException("There can only be one.");

        setHardness(4.0F);
        setResistance(10F);
        setHarvestLevel("pickaxe", 0);

        setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumFacing.NORTH));
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(AXIS, EnumFacing.byHorizontalIndex(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(AXIS).getHorizontalIndex();
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
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return state.getValue(AXIS).getAxis() == EnumFacing.Axis.X ? AABB_Z : AABB_X;
    }

    @SuppressWarnings("deprecation")
    @Override
    @Nonnull
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    @SideOnly(Side.CLIENT)
    @Override
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
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TEAnvilTFC te = Helpers.getTE(worldIn, pos, TEAnvilTFC.class);
        if (te != null) {
            te.onBreakBlock(worldIn, pos, state);
        }
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ItemAnvil.get(metal);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (hand == EnumHand.OFF_HAND) //Avoid issues with insertion/extraction
        {
            return false;
        }
        TEAnvilTFC te = Helpers.getTE(worldIn, pos, TEAnvilTFC.class);
        if (te == null) {
            return false;
        }
        IItemHandler cap = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        if (cap == null) {
            return false;
        }
        if (playerIn.isSneaking()) {
            ItemStack heldItem = playerIn.getHeldItem(hand);
            // Extract requires main hand empty
            if (heldItem.isEmpty()) {
                // Only check the input slots
                for (int i = 0; i < 2; i++) {
                    ItemStack stack = cap.getStackInSlot(i);
                    if (!stack.isEmpty()) {
                        // Give the item to player in the main hand
                        ItemStack result = cap.extractItem(i, 1, false);
                        playerIn.setHeldItem(hand, result);
                        return true;
                    }
                }
            }
            // Welding requires a hammer in main hand
            else if (te.isItemValid(SLOT_HAMMER, heldItem)) {
                if (!worldIn.isRemote && te.attemptWelding(playerIn)) {
                    // Valid welding occurred.
                    worldIn.playSound(null, pos, TFCSounds.ANVIL_IMPACT, SoundCategory.PLAYERS, 1.0f, 1.0f);
                    double x = pos.getX() + 0.5;
                    double y = pos.getY() + 0.69;
                    double z = pos.getZ() + 0.5;
                    for (int i = 0; i < RNG.nextInt(5) + 3; i++)
                        TFCParticles.SPARK.sendToAllNear(worldIn, x + (RNG.nextFloat() - 0.5) / 7, y, z + (RNG.nextFloat() - 0.5) / 7, 6 * (RNG.nextFloat() - 0.5), 2D, 6 * (RNG.nextFloat() - 0.5), 22);
                    return true;
                }
            }
            //If main hand isn't empty and is not a hammer
            else {
                //Try inserting items
                for (int i = 0; i < 4; i++) {
                    // Check the input slots and flux. Do NOT check the hammer slot
                    if (i == SLOT_HAMMER) continue;
                    // Try to insert an item
                    // Hammers will not be inserted since we already checked if heldItem is a hammer for attemptWelding
                    if (te.isItemValid(i, heldItem) && te.getSlotLimit(i) > cap.getStackInSlot(i).getCount()) {
                        ItemStack result = cap.insertItem(i, heldItem, false);
                        playerIn.setHeldItem(hand, result);
                        TerraFirmaCraft.getLog().info("Inserted {} into slot {}", heldItem.getDisplayName(), i);
                        return true;
                    }
                }
            }
        } else {
            // not sneaking, so try and open GUI
            if (!worldIn.isRemote) {
                TFCGuiHandler.openGui(worldIn, pos, playerIn, TFCGuiHandler.Type.ANVIL);
            }
            return true;
        }
        return false;
    }

    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, AXIS);
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

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TEAnvilTFC();
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(ItemAnvil.get(metal));
    }

    public gregtech.api.unification.material.Material getMetal() {
        return metal;
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
