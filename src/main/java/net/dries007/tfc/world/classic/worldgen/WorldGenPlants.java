package net.dries007.tfc.world.classic.worldgen;

import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Plant;
import net.dries007.tfc.objects.blocks.plants.*;
import net.dries007.tfc.types.DefaultPlants;
import net.dries007.tfc.util.climate.ClimateTFC;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@ParametersAreNonnullByDefault
public class WorldGenPlants extends WorldGenerator {
    private Plant plant;

    public void setGeneratedPlant(Plant plantIn) {
        this.plant = plantIn;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position) {
        switch (plant.getPlantType()) {
            case HANGING: {
                if (plant == TFCRegistries.PLANTS.getValue(DefaultPlants.BEARDED_MOSS) ||
                        plant == TFCRegistries.PLANTS.getValue(DefaultPlants.HANGING_VINE) ||
                        plant == TFCRegistries.PLANTS.getValue(DefaultPlants.JUNGLE_VINE) ||
                        plant == TFCRegistries.PLANTS.getValue(DefaultPlants.LIANA)) {
                    TFCBlockHangingPlant plantBlock = TFCBlockHangingPlant.get(plant);
                    IBlockState state = plantBlock.getDefaultState();

                    for (int i = 0; i < ChunkDataTFC.getRainfall(worldIn, position) / 4; ++i) {
                        BlockPos blockpos = position.add(rand.nextInt(7) - rand.nextInt(7), rand.nextInt(16), rand.nextInt(7) - rand.nextInt(7));

                        int j = 1 + rand.nextInt(plant.getMaxHeight());

                        for (int k = 0; k < j; ++k) {
                            if (plant.isValidTemp(ClimateTFC.getActualTemp(worldIn, blockpos)) &&
                                    plant.isValidSunlight(worldIn.getLightFor(EnumSkyBlock.SKY, blockpos.down(k))) &&
                                    worldIn.isAirBlock(blockpos.down(k)) &&
                                    plantBlock.canBlockStay(worldIn, blockpos.down(k), state) &&
                                    plantBlock.canPlaceBlockAt(worldIn, blockpos.down(k))) {
                                int plantAge = plant.getAgeForWorldgen(rand, ClimateTFC.getActualTemp(worldIn, blockpos));
                                setBlockAndNotifyAdequately(worldIn, blockpos.down(k), state.withProperty(TFCBlockHangingPlant.AGE, plantAge));
                            }
                        }
                    }
                    break;
                }
            }
            case WATER:
            case WATER_SEA: {
                TFCBlockWaterPlant plantBlock = TFCBlockWaterPlant.get(plant);
                IBlockState state = plantBlock.getDefaultState();
                IBlockState water = plant.getWaterType();

                int depth = plant.getValidWaterDepth(worldIn, position, water);
                if (depth == -1) return false;

                BlockPos blockpos = position.add(rand.nextInt(7) - rand.nextInt(7), -depth + 1, rand.nextInt(7) - rand.nextInt(7));

                if (plant.isValidTemp(ClimateTFC.getActualTemp(worldIn, blockpos)) &&
                        plant.isValidSunlight(worldIn.getLightFor(EnumSkyBlock.SKY, blockpos)) &&
                        plantBlock.canPlaceBlockAt(worldIn, blockpos)) {
                    int plantAge = plant.getAgeForWorldgen(rand, ClimateTFC.getActualTemp(worldIn, blockpos));
                    setBlockAndNotifyAdequately(worldIn, blockpos, state.withProperty(TFCBlockWaterPlant.AGE, plantAge));
                }
                break;
            }
            case TALL_WATER:
            case TALL_WATER_SEA: {
                TFCBlockTallWaterPlant plantBlock = TFCBlockTallWaterPlant.get(plant);
                IBlockState state = plantBlock.getDefaultState();
                IBlockState water = plant.getWaterType();

                int depth = plant.getValidWaterDepth(worldIn, position, water);
                if (depth == -1) return false;
                BlockPos blockpos = position.add(rand.nextInt(7) - rand.nextInt(7), -depth + 1, rand.nextInt(7) - rand.nextInt(7));

                int j = 1 + rand.nextInt(plant.getMaxHeight());

                for (int k = 0; k < j; ++k) {
                    if (plant.isValidTemp(ClimateTFC.getActualTemp(worldIn, blockpos)) &&
                            plant.isValidSunlight(worldIn.getLightFor(EnumSkyBlock.SKY, blockpos.up(k))) &&
                            plantBlock.canPlaceBlockAt(worldIn, blockpos.up(k))) {
                        int plantAge = plant.getAgeForWorldgen(rand, ClimateTFC.getActualTemp(worldIn, blockpos));
                        setBlockAndNotifyAdequately(worldIn, blockpos.up(k), state.withProperty(TFCBlockTallWaterPlant.AGE, plantAge));
                        if (rand.nextInt(4) < plantAge && plantBlock.canGrow(worldIn, blockpos, state, worldIn.isRemote))
                            setBlockAndNotifyAdequately(worldIn, blockpos.up(k), state);
                    }
                }
                break;
            }
            case SHORT_GRASS: {
                TFCBlockShortGrass plantBlock = TFCBlockShortGrass.get(plant);
                IBlockState state = plantBlock.getDefaultState();

                for (int i = 0; i < ChunkDataTFC.getRainfall(worldIn, position) / 4; ++i) {
                    BlockPos blockpos = position.add(rand.nextInt(7) - rand.nextInt(7), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(7) - rand.nextInt(7));

                    if (plant.isValidGrowthTemp(ClimateTFC.getActualTemp(worldIn, blockpos)) &&
                            plant.isValidSunlight(worldIn.getLightFor(EnumSkyBlock.SKY, blockpos)) &&
                            worldIn.isAirBlock(blockpos) &&
                            plantBlock.canBlockStay(worldIn, blockpos, state)) {
                        int plantAge = plant.getAgeForWorldgen(rand, ClimateTFC.getActualTemp(worldIn, blockpos));
                        setBlockAndNotifyAdequately(worldIn, blockpos, state.withProperty(TFCBlockShortGrass.AGE, plantAge));
                    }
                }
                break;
            }
            case TALL_GRASS: {
                if (plant != TFCRegistries.PLANTS.getValue(DefaultPlants.SAWGRASS)) {
                    TFCBlockTallGrass plantBlock = TFCBlockTallGrass.get(plant);
                    IBlockState state = plantBlock.getDefaultState();

                    for (int i = 0; i < ChunkDataTFC.getRainfall(worldIn, position) / 16; ++i) {
                        BlockPos blockpos = position.add(rand.nextInt(7) - rand.nextInt(7), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(7) - rand.nextInt(7));

                        int j = 1 + rand.nextInt(plant.getMaxHeight());

                        for (int k = 0; k < j; ++k) {
                            if (plant.isValidTemp(ClimateTFC.getActualTemp(worldIn, blockpos)) &&
                                    plant.isValidSunlight(worldIn.getLightFor(EnumSkyBlock.SKY, blockpos.up(k))) &&
                                    worldIn.isAirBlock(blockpos.up(k)) &&
                                    plantBlock.canBlockStay(worldIn, blockpos.up(k), state)) {
                                int plantAge = plant.getAgeForWorldgen(rand, ClimateTFC.getActualTemp(worldIn, blockpos));
                                setBlockAndNotifyAdequately(worldIn, blockpos.up(k), state.withProperty(TFCBlockShortGrass.AGE, plantAge));
                            }
                        }
                    }
                }
                if (plant == TFCRegistries.PLANTS.getValue(DefaultPlants.SAWGRASS)) {
                    TFCBlockTallGrassWater plantBlock = TFCBlockTallGrassWater.get(plant);
                    IBlockState state = plantBlock.getDefaultState();
                    IBlockState water = plant.getWaterType();

                    for (int i = 0; i < ChunkDataTFC.getRainfall(worldIn, position) / 16; ++i) {
                        BlockPos blockpos = position.add(rand.nextInt(7) - rand.nextInt(7), 0, rand.nextInt(7) - rand.nextInt(7));

                        int j = 1 + rand.nextInt(plant.getMaxHeight());

                        for (int k = 0; k < j; ++k) {
                            if (plant.isValidTemp(ClimateTFC.getActualTemp(worldIn, blockpos)) &&
                                    plant.isValidSunlight(worldIn.getLightFor(EnumSkyBlock.SKY, blockpos.up(k))) &&
                                    worldIn.isAirBlock(blockpos.up(k)) &&
                                    plantBlock.canPlaceBlockAt(worldIn, blockpos.up(k)) &&
                                    plant.isValidFloatingWaterDepth(worldIn, blockpos.up(k), water) &&
                                    plantBlock.canBlockStay(worldIn, blockpos.up(k), state)) {
                                int plantAge = plant.getAgeForWorldgen(rand, ClimateTFC.getActualTemp(worldIn, blockpos));
                                setBlockAndNotifyAdequately(worldIn, blockpos.up(k), state.withProperty(TFCBlockTallGrassWater.AGE, plantAge));
                            }
                        }
                    }
                }
                break;
            }
            case TALL_PLANT: {
                TFCBlockTallPlant plantBlock = TFCBlockTallPlant.get(plant);
                IBlockState state = plantBlock.getDefaultState();

                for (int i = 0; i < ChunkDataTFC.getRainfall(worldIn, position) / 16; ++i) {
                    BlockPos blockpos = position.add(rand.nextInt(7) - rand.nextInt(7), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(7) - rand.nextInt(7));

                    int j = 1 + rand.nextInt(plant.getMaxHeight());

                    for (int k = 0; k < j; ++k) {
                        if (plant.isValidTemp(ClimateTFC.getActualTemp(worldIn, blockpos)) &&
                                plant.isValidSunlight(worldIn.getLightFor(EnumSkyBlock.SKY, blockpos.up(k))) &&
                                worldIn.isAirBlock(blockpos.up(k)) &&
                                plantBlock.canBlockStay(worldIn, blockpos.up(k), state)) {
                            int plantAge = plant.getAgeForWorldgen(rand, ClimateTFC.getActualTemp(worldIn, blockpos));
                            setBlockAndNotifyAdequately(worldIn, blockpos.up(k), state.withProperty(TFCBlockTallPlant.AGE, plantAge));
                        }
                    }
                }
                break;
            }
            case EPIPHYTE: {
                TFCBlockEpiphyte plantBlock = TFCBlockEpiphyte.get(plant);

                for (int i = 0; i < ChunkDataTFC.getRainfall(worldIn, position) / 4; ++i) {
                    BlockPos blockpos = position.add(rand.nextInt(7) - rand.nextInt(7), rand.nextInt(16), rand.nextInt(7) - rand.nextInt(7));

                    if (plant.isValidTemp(ClimateTFC.getActualTemp(worldIn, blockpos)) &&
                            plant.isValidSunlight(worldIn.getLightFor(EnumSkyBlock.SKY, blockpos)) &&
                            worldIn.getBlockState(blockpos).getBlock().isReplaceable(worldIn, blockpos) &&
                            plantBlock.canPlaceBlockAt(worldIn, blockpos)) {
                        int plantAge = plant.getAgeForWorldgen(rand, ClimateTFC.getActualTemp(worldIn, blockpos));
                        setBlockAndNotifyAdequately(worldIn, blockpos, plantBlock.getStateForWorldGen(worldIn, blockpos).withProperty(TFCBlockEpiphyte.AGE, plantAge));
                    }
                }
                break;
            }
            case DRY: {
                TFCBlockPlant plantBlock = TFCBlockPlant.get(plant);
                IBlockState state = plantBlock.getDefaultState();

                for (int i = 0; i < ChunkDataTFC.getRainfall(worldIn, position) / 4; ++i) {
                    BlockPos blockpos = position.add(rand.nextInt(7) - rand.nextInt(7), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(7) - rand.nextInt(7));

                    if (plant.isValidTemp(ClimateTFC.getActualTemp(worldIn, blockpos)) &&
                            plant.isValidSunlight(worldIn.getLightFor(EnumSkyBlock.SKY, blockpos)) &&
                            worldIn.isAirBlock(blockpos) &&
                            plantBlock.canBlockStay(worldIn, blockpos, state)) {
                        int plantAge = plant.getAgeForWorldgen(rand, ClimateTFC.getActualTemp(worldIn, blockpos));
                        setBlockAndNotifyAdequately(worldIn, blockpos, state.withProperty(TFCBlockPlant.AGE, plantAge));
                    }
                }
                break;
            }
            default: {
                TFCBlockPlant plantBlock = TFCBlockPlant.get(plant);
                IBlockState state = plantBlock.getDefaultState();

                for (int i = 0; i < ChunkDataTFC.getRainfall(worldIn, position) / 16; ++i) {
                    BlockPos blockpos = position.add(rand.nextInt(7) - rand.nextInt(7), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(7) - rand.nextInt(7));

                    if (plant.isValidTemp(ClimateTFC.getActualTemp(worldIn, blockpos)) &&
                            plant.isValidSunlight(worldIn.getLightFor(EnumSkyBlock.SKY, blockpos)) &&
                            worldIn.isAirBlock(blockpos) &&
                            plantBlock.canBlockStay(worldIn, blockpos, state)) {
                        int plantAge = plant.getAgeForWorldgen(rand, ClimateTFC.getActualTemp(worldIn, blockpos));
                        setBlockAndNotifyAdequately(worldIn, blockpos, state.withProperty(TFCBlockPlant.AGE, plantAge));
                    }
                }
            }
        }
        return true;
    }
}
