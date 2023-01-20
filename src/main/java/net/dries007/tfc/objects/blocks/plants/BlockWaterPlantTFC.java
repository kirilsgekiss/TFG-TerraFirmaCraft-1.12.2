/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.plants;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import git.jbredwards.fluidlogged_api.api.block.IFluidloggable;
import git.jbredwards.fluidlogged_api.api.util.FluidState;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import net.dries007.tfc.api.types.Plant;
import net.dries007.tfc.objects.blocks.TFCBlocks;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static net.dries007.tfc.world.classic.ChunkGenTFC.SEA_WATER;

// todo: either pull some trickery to make this look like water or simply wait until 1.13 and implement ILiquidContainer
@ParametersAreNonnullByDefault
public class BlockWaterPlantTFC extends TFCBlockPlant implements IFluidloggable
{
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 3);
    private static final Map<Plant, BlockWaterPlantTFC> MAP = new HashMap<>();

    public static BlockWaterPlantTFC get(Plant plant)
    {
        return MAP.get(plant);
    }
    protected final Plant plant;

    public BlockWaterPlantTFC(Plant plant)
    {
        super(plant);
        this.plant = plant;
        if (MAP.put(plant, this) != null) throw new IllegalStateException("There can only be one.");
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        IBlockState soil = worldIn.getBlockState(pos.down());
        if (plant.getWaterType() == SEA_WATER)
            return TFCBlocks.isSeaWater(worldIn.getBlockState(pos)) && this.canSustainBush(soil);
        return TFCBlocks.isFreshWater(worldIn.getBlockState(pos)) && this.canSustainBush(soil);
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
    {
        this.onBlockHarvested(world, pos, state, player);
        return world.setBlockState(pos, plant.getWaterType(), world.isRemote ? 11 : 3);
    }

    @Override
    protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(worldIn, pos, state))
        {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockState(pos, plant.getWaterType());
        }
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        return true;
    }

    @Override
    public boolean isFluidloggable(@Nonnull IBlockState state, @Nonnull World world, @Nonnull BlockPos pos) {
        return true;
    }

    @Override
    public boolean isFluidValid(@Nonnull IBlockState state, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull Fluid fluid) {
        return isFluidloggable(state, world, pos);
    }

    @Override
    public boolean canFluidFlow(@Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull IBlockState here, @Nonnull EnumFacing side) {
        return here.getBlockFaceShape(world, pos, side) != BlockFaceShape.SOLID;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldFluidRender(@Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull IBlockState here, @Nonnull FluidState fluidState) { return true; }

    @Override
    @Nonnull
    public EnumActionResult onFluidChange(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState here, @Nonnull FluidState newFluid, int blockFlags) {
        return newFluid.isEmpty() ? onFluidDrain(world, pos, here, blockFlags) : onFluidFill(world, pos, here, newFluid, blockFlags);
    }

    @Override
    @Nonnull
    public EnumActionResult onFluidFill(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState here, @Nonnull FluidState newFluid, int blockFlags) {
        return EnumActionResult.PASS;
    }

    @Override
    @Nonnull
    public EnumActionResult onFluidDrain(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState here, int blockFlags) {
        return EnumActionResult.PASS;
    }
}
