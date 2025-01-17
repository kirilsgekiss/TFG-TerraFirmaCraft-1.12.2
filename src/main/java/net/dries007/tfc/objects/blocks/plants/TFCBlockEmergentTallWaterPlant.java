/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.plants;

import net.dries007.tfc.api.types.Plant;
import net.dries007.tfc.objects.blocks.TFCBlocks;
import net.dries007.tfc.objects.blocks.property.ITallPlant;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;

import static net.dries007.tfc.world.classic.ChunkGenTFC.SEA_WATER;

@ParametersAreNonnullByDefault
public class TFCBlockEmergentTallWaterPlant extends TFCBlockTallWaterPlant implements ITallPlant {
    private static final Map<Plant, TFCBlockEmergentTallWaterPlant> MAP = new HashMap<>();

    public static TFCBlockEmergentTallWaterPlant get(Plant plant) {
        return TFCBlockEmergentTallWaterPlant.MAP.get(plant);
    }

    public TFCBlockEmergentTallWaterPlant(Plant plant) {
        super(plant);
        if (MAP.put(plant, this) != null) throw new IllegalStateException("There can only be one.");
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        IBlockState water = plant.getWaterType();
        int i;
        //noinspection StatementWithEmptyBody
        for (i = 1; worldIn.getBlockState(pos.down(i)).getBlock() == this; ++i) ;
        if (water == SEA_WATER)
            return i < plant.getMaxHeight() && (worldIn.isAirBlock(pos.up()) || TFCBlocks.isSeaWater(worldIn.getBlockState(pos.up()))) && canBlockStay(worldIn, pos.up(), state);
        else
            return i < plant.getMaxHeight() && (worldIn.isAirBlock(pos.up()) || TFCBlocks.isFreshWater(worldIn.getBlockState(pos.up()))) && canBlockStay(worldIn, pos.up(), state);
    }

    public void shrink(World worldIn, BlockPos pos) {
        boolean flag = false;
        for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
            if (TFCBlocks.isFreshWater(worldIn.getBlockState(pos.offset(enumfacing)))) {
                flag = true;
            }
        }

        if (flag) worldIn.setBlockState(pos, plant.getWaterType());
        else worldIn.setBlockToAir(pos);
        worldIn.getBlockState(pos).neighborChanged(worldIn, pos.down(), this, pos);
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        IBlockState soil = worldIn.getBlockState(pos.down());
        if (plant.getWaterType() == SEA_WATER)
            return (soil.getBlock() == this || TFCBlocks.isSeaWater(worldIn.getBlockState(pos))) && this.canSustainBush(soil);
        return (soil.getBlock() == this || TFCBlocks.isWater(worldIn.getBlockState(pos))) && this.canSustainBush(soil);
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        this.onBlockHarvested(world, pos, state, player);

        boolean flag = false;
        for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
            if (TFCBlocks.isWater(world.getBlockState(pos.offset(enumfacing)))) {
                flag = true;
            }
        }

        if (flag) return world.setBlockState(pos, plant.getWaterType(), world.isRemote ? 11 : 3);
        else return world.setBlockState(pos, net.minecraft.init.Blocks.AIR.getDefaultState(), world.isRemote ? 11 : 3);
    }

    @Override
    protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
        if (!this.canBlockStay(worldIn, pos, state)) {
            boolean flag = false;
            for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
                if (TFCBlocks.isWater(worldIn.getBlockState(pos.offset(enumfacing)))) {
                    flag = true;
                }
            }

            this.dropBlockAsItem(worldIn, pos, state, 0);
            if (flag) worldIn.setBlockState(pos, plant.getWaterType());
            else worldIn.setBlockToAir(pos);
        }
    }
}
