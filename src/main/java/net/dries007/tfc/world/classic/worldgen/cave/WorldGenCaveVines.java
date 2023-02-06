package net.dries007.tfc.world.classic.worldgen.cave;

import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Plant;
import net.dries007.tfc.objects.blocks.plants.TFCBlockHangingGlowingPlant;
import net.dries007.tfc.objects.blocks.plants.TFCBlockHangingPlant;
import net.dries007.tfc.types.DefaultPlants;
import net.dries007.tfc.util.climate.TFCClimate;
import net.dries007.tfc.world.classic.WorldTypeTFC;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@ParametersAreNonnullByDefault
public class WorldGenCaveVines extends WorldGenerator {
    private Plant plant;

    public void setGeneratedPlant(Plant plantIn) {
        this.plant = plantIn;
    }

    @Override
    public boolean generate(World worldIn, Random rng, BlockPos pos) {
        if (plant == TFCRegistries.PLANTS.getValue(DefaultPlants.GLOW_VINE)) {
            TFCBlockHangingGlowingPlant plantBlock = TFCBlockHangingGlowingPlant.get(plant);
            IBlockState state = plantBlock.getDefaultState();

            for (int i = 0; i < ChunkDataTFC.getRainfall(worldIn, pos) / 4; ++i) {
                BlockPos blockpos = pos.add(rng.nextInt(7) - rng.nextInt(7), rng.nextInt(16), rng.nextInt(7) - rng.nextInt(7));

                int j = 1 + rng.nextInt(plant.getMaxHeight());

                for (int k = 0; k < j; ++k) {
                    if (plant.isValidTemp(TFCClimate.getActualTemp(worldIn, blockpos)) &&
                            plant.isValidSunlight(worldIn.getLightFor(EnumSkyBlock.SKY, blockpos.down(k))) &&
                            worldIn.isAirBlock(blockpos.down(k)) &&
                            pos.getY() < WorldTypeTFC.SEALEVEL - 3 &&
                            worldIn.getLightFor(EnumSkyBlock.SKY, blockpos) < 14 &&
                            plantBlock.canBlockStay(worldIn, blockpos.down(k), state) &&
                            plantBlock.canPlaceBlockAt(worldIn, blockpos.down(k))) {
                        int plantAge = plant.getAgeForWorldgen(rng, TFCClimate.getActualTemp(worldIn, blockpos));
                        setBlockAndNotifyAdequately(worldIn, blockpos.down(k), state.withProperty(TFCBlockHangingGlowingPlant.AGE, plantAge));
                    }
                }
            }
            return true;
        } else {
            TFCBlockHangingPlant plantBlock = TFCBlockHangingPlant.get(plant);
            IBlockState state = plantBlock.getDefaultState();

            for (int i = 0; i < ChunkDataTFC.getRainfall(worldIn, pos) / 4; ++i) {
                BlockPos blockpos = pos.add(rng.nextInt(7) - rng.nextInt(7), rng.nextInt(16), rng.nextInt(7) - rng.nextInt(7));

                int j = 1 + rng.nextInt(plant.getMaxHeight());

                for (int k = 0; k < j; ++k) {
                    if (plant.isValidTemp(TFCClimate.getActualTemp(worldIn, blockpos)) &&
                            plant.isValidSunlight(worldIn.getLightFor(EnumSkyBlock.SKY, blockpos.down(k))) &&
                            worldIn.isAirBlock(blockpos.down(k)) &&
                            pos.getY() < WorldTypeTFC.SEALEVEL - 3 &&
                            worldIn.getLightFor(EnumSkyBlock.SKY, blockpos) < 14 &&
                            plantBlock.canBlockStay(worldIn, blockpos.down(k), state) &&
                            plantBlock.canPlaceBlockAt(worldIn, blockpos.down(k))) {
                        int plantAge = plant.getAgeForWorldgen(rng, TFCClimate.getActualTemp(worldIn, blockpos));
                        setBlockAndNotifyAdequately(worldIn, blockpos.down(k), state.withProperty(TFCBlockHangingPlant.AGE, plantAge));
                    }
                }
            }
            return true;
        }
    }
}
