/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.wood;

import java.util.HashMap;
import java.util.Map;

import git.jbredwards.fluidlogged_api.api.util.FluidState;
import git.jbredwards.fluidlogged_api.api.util.FluidloggedUtils;
import net.dries007.tfc.api.types.Wood;
import net.dries007.tfc.api.util.IWoodHandler;
import net.dries007.tfc.client.CustomStateMap;
import net.dries007.tfc.client.model.IHasModel;
import net.minecraft.block.BlockButtonWood;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;

import net.dries007.tfc.api.types.Tree;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

public class TFCBlockWoodButton extends BlockButtonWood implements IWoodHandler, IHasModel
{
    private final ResourceLocation MODEL_LOCATION = new ResourceLocation(MOD_ID, "wood/button");
    private static final Map<Wood, TFCBlockWoodButton> MAP = new HashMap<>();
    public static TFCBlockWoodButton get(Wood wood)
    {
        return MAP.get(wood);
    }
    private final Wood wood;

    public TFCBlockWoodButton(Wood wood)
    {
        this.wood = wood;
        if (MAP.put(wood, this) != null) throw new IllegalStateException("There can only be one.");
        setHardness(0.5F);
        setSoundType(SoundType.WOOD);
        Blocks.FIRE.setFireInfo(this, 5, 20);
    }

    @Override
    public Wood getWood() {
        return wood;
    }

    @Override
    public void onModelRegister() {
        ModelLoader.setCustomStateMapper(this, new CustomStateMap.Builder().customPath(MODEL_LOCATION).build());

        for (IBlockState state : this.getBlockState().getValidStates()) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), this.getMetaFromState(state), new ModelResourceLocation(MODEL_LOCATION, "inventory"));
        }
    }

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
