/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.metal;

import git.jbredwards.fluidlogged_api.api.util.FluidState;
import git.jbredwards.fluidlogged_api.api.util.FluidloggedUtils;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import net.dries007.tfc.util.OreDictionaryHelper;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class BlockTrapDoorMetalTFC extends BlockTrapDoor
{
    private static final Map<gregtech.api.unification.material.Material, BlockTrapDoorMetalTFC> MAP = new HashMap<>();

    public static BlockTrapDoorMetalTFC get(gregtech.api.unification.material.Material metal) {
        return MAP.get(metal);
    }

    public final gregtech.api.unification.material.Material metal;

    public BlockTrapDoorMetalTFC(gregtech.api.unification.material.Material metal)
    {
        super(Material.IRON);

        this.metal = metal;
        if (MAP.put(metal, this) != null) throw new IllegalStateException("There can only be one.");

        setHardness(1F);
        setSoundType(SoundType.METAL);
        OreDictionaryHelper.register(this, "trapdoorMetal" + metal.toCamelCaseString());
    }

    /**
     * use this if your block can be fluidlogged.
     * @author jbred
     *
     */
    public interface IFluidloggable
    {
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
        default boolean shouldFluidRender(@Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull IBlockState here, @Nonnull FluidState fluidState) { return true; }

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
