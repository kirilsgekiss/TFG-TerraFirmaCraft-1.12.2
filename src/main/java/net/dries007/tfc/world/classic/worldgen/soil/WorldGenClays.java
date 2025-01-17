package net.dries007.tfc.world.classic.worldgen.soil;

import net.dries007.tfc.TFCConfig;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Plant;
import net.dries007.tfc.api.types.Rock.Type;
import net.dries007.tfc.objects.blocks.TFCBlocks;
import net.dries007.tfc.objects.blocks.plants.TFCBlockPlant;
import net.dries007.tfc.objects.blocks.rock.TFCBlockRockVariant;
import net.dries007.tfc.util.climate.TFCClimate;
import net.dries007.tfc.world.classic.ChunkGenTFC;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenClays implements IWorldGenerator {
    public static final float RAINFALL_SAND = 75;
    public static final float RAINFALL_SAND_SANDY_MIX = 125;
    public static final float RAINFALL_SANDY = 200; // Upper thresholds
    public static final float RAINFALL_SILTY = 275; // Lower thresholds
    public static final float RAINFALL_SILT_SILTY_MIX = 350;
    public static final float RAINFALL_SILT = 400;

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (!(chunkGenerator instanceof ChunkGenTFC)) return;
        final BlockPos chunkBlockPos = new BlockPos(chunkX << 4, 0, chunkZ << 4);

        if (TFCConfig.FloraeGeneral.WORLD.enableAllBlockTypes) {
            if (TFCConfig.FloraeGeneral.WORLD.enableAllSpecialSoil) {
                BlockPos pos = world.getTopSolidOrLiquidBlock(chunkBlockPos.add(8 + random.nextInt(16), 0, 8 + random.nextInt(16)));
                generateClaySurface(world, random, pos);
            }

            if (TFCConfig.FloraeGeneral.WORLD.enableAllEarthenwareClay) {
                BlockPos pos = world.getTopSolidOrLiquidBlock(chunkBlockPos.add(8 + random.nextInt(16), 0, 8 + random.nextInt(16)));
                generateEarthenwareClaySurface(world, random, pos);
            }

            if (TFCConfig.FloraeGeneral.WORLD.enableAllKaoliniteClay) {
                BlockPos pos = world.getTopSolidOrLiquidBlock(chunkBlockPos.add(8 + random.nextInt(16), 0, 8 + random.nextInt(16)));
                generateKaoliniteClaySurface(world, random, pos);
            }

            if (TFCConfig.FloraeGeneral.WORLD.enableAllStonewareClay) {
                BlockPos pos = world.getTopSolidOrLiquidBlock(chunkBlockPos.add(8 + random.nextInt(16), 0, 8 + random.nextInt(16)));
                generateStonewareClaySurface(world, random, pos);
            }
        }
    }

    private void generateClaySurface(World world, Random rng, BlockPos start) {
        if (TFCConfig.FloraeGeneral.WORLD.enableAllSpecialSoil && ChunkDataTFC.getDrainage(world, start) <= 1) {
            ChunkDataTFC data = ChunkDataTFC.get(world, start);

            int radius = rng.nextInt(6) + 2;
            int depth = rng.nextInt(3) + 1;

            int sandyClayRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.sandyClayRarity);
            int sandyClayLoamRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.sandyClayLoamRarity);
            int clayLoamRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.clayLoamRarity);
            int siltyClayLoamRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.siltyClayLoamRarity);
            int clayHumusRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.clayHumusRarity);
            int siltyClayRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.siltyClayRarity);

            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    if (x * x + z * z > radius * radius) continue;
                    final BlockPos posHorizontal = start.add(x, 0, z);

                    for (int y = -depth; y <= +depth; y++) {
                        final BlockPos pos = posHorizontal.add(0, y, 0);
                        final IBlockState current = world.getBlockState(pos);

                        if (data.getRainfall() < RAINFALL_SANDY) {
                            if (data.getRainfall() > RAINFALL_SAND_SANDY_MIX) {
                                if (sandyClayRarity == 0) {
                                    if (TFCBlocks.isDirt(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SANDY_CLAY)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isPodzol(current) && TFCConfig.FloraeGeneral.WORLD.enableAllPodzol) {
                                        world.setBlockState(pos, TFCBlockRockVariant
                                                .get(ChunkDataTFC.getRockHeight(world, pos),
                                                        Type.SANDY_CLAY_PODZOL)
                                                .getDefaultState(), 2);
                                    } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SPARSE_SANDY_CLAY_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isDryGrass(current)) {
                                        world.setBlockState(pos, TFCBlockRockVariant
                                                .get(ChunkDataTFC.getRockHeight(world, pos),
                                                        Type.DRY_SANDY_CLAY_GRASS)
                                                .getDefaultState(), 2);
                                    } else if (TFCBlocks.isGrass(current)) {
                                        world.setBlockState(pos, TFCBlockRockVariant
                                                .get(ChunkDataTFC.getRockHeight(world, pos),
                                                        Type.SANDY_CLAY_GRASS)
                                                .getDefaultState(), 2);
                                    }
                                }
                            } else if (data.getRainfall() > RAINFALL_SAND) {
                                if (sandyClayLoamRarity == 0) {
                                    if (TFCBlocks.isDirt(current)) {
                                        world.setBlockState(pos, TFCBlockRockVariant
                                                .get(ChunkDataTFC.getRockHeight(world, pos),
                                                        Type.SANDY_CLAY_LOAM)
                                                .getDefaultState(), 2);
                                    } else if (TFCBlocks.isPodzol(current) && TFCConfig.FloraeGeneral.WORLD.enableAllPodzol) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SANDY_CLAY_LOAM_PODZOL)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SPARSE_SANDY_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isDryGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.DRY_SANDY_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isGrass(current)) {
                                        world.setBlockState(pos, TFCBlockRockVariant
                                                .get(ChunkDataTFC.getRockHeight(world, pos),
                                                        Type.SANDY_CLAY_LOAM_GRASS)
                                                .getDefaultState(), 2);
                                    }
                                }
                            }
                        } else if (data.getRainfall() > RAINFALL_SANDY) {
                            if (data.getRainfall() < RAINFALL_SILTY) {
                                if (clayLoamRarity == 0) {
                                    if (TFCBlocks.isDirt(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.CLAY_LOAM)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isPodzol(current) && TFCConfig.FloraeGeneral.WORLD.enableAllPodzol) {
                                        world.setBlockState(pos, TFCBlockRockVariant
                                                .get(ChunkDataTFC.getRockHeight(world, pos),
                                                        Type.CLAY_LOAM_PODZOL)
                                                .getDefaultState(), 2);
                                    } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SPARSE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isDryGrass(current)) {
                                        world.setBlockState(pos, TFCBlockRockVariant
                                                .get(ChunkDataTFC.getRockHeight(world, pos),
                                                        Type.DRY_CLAY_LOAM_GRASS)
                                                .getDefaultState(), 2);
                                    } else if (TFCBlocks.isGrass(current)) {
                                        world.setBlockState(pos, TFCBlockRockVariant
                                                .get(ChunkDataTFC.getRockHeight(world, pos),
                                                        Type.CLAY_LOAM_GRASS)
                                                .getDefaultState(), 2);
                                    }
                                }
                            }
                        } else if (data.getRainfall() > RAINFALL_SILTY) {
                            if (data.getRainfall() < RAINFALL_SILT_SILTY_MIX) {
                                if (siltyClayLoamRarity == 0) {
                                    if (TFCBlocks.isDirt(current)) {
                                        world.setBlockState(pos, TFCBlockRockVariant
                                                .get(ChunkDataTFC.getRockHeight(world, pos),
                                                        Type.SILTY_CLAY_LOAM)
                                                .getDefaultState(), 2);
                                    } else if (TFCBlocks.isPodzol(current) && TFCConfig.FloraeGeneral.WORLD.enableAllPodzol) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SILTY_CLAY_LOAM_PODZOL)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SPARSE_SILTY_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isDryGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.DRY_SILTY_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isGrass(current)) {
                                        world.setBlockState(pos, TFCBlockRockVariant
                                                .get(ChunkDataTFC.getRockHeight(world, pos),
                                                        Type.SILTY_CLAY_LOAM_GRASS)
                                                .getDefaultState(), 2);
                                    }
                                }
                            } else if (data.getRainfall() < RAINFALL_SILT) {
                                if (clayHumusRarity == 0) {
                                    if (TFCBlocks.isDirt(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.CLAY_HUMUS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SPARSE_CLAY_HUMUS_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isDryGrass(current)) {
                                        world.setBlockState(pos, TFCBlockRockVariant
                                                .get(ChunkDataTFC.getRockHeight(world, pos),
                                                        Type.DRY_CLAY_HUMUS_GRASS)
                                                .getDefaultState(), 2);
                                    } else if (TFCBlocks.isGrass(current)) {
                                        world.setBlockState(pos, TFCBlockRockVariant
                                                .get(ChunkDataTFC.getRockHeight(world, pos),
                                                        Type.CLAY_HUMUS_GRASS)
                                                .getDefaultState(), 2);
                                    }
                                }
                            } else {
                                if (siltyClayRarity == 0) {
                                    if (TFCBlocks.isDirt(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SILTY_CLAY)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isPodzol(current) && TFCConfig.FloraeGeneral.WORLD.enableAllPodzol) {
                                        world.setBlockState(pos, TFCBlockRockVariant
                                                .get(ChunkDataTFC.getRockHeight(world, pos),
                                                        Type.SILTY_CLAY_PODZOL)
                                                .getDefaultState(), 2);
                                    } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SPARSE_SILTY_CLAY_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isDryGrass(current)) {
                                        world.setBlockState(pos, TFCBlockRockVariant
                                                .get(ChunkDataTFC.getRockHeight(world, pos),
                                                        Type.DRY_SILTY_CLAY_GRASS)
                                                .getDefaultState(), 2);
                                    } else if (TFCBlocks.isGrass(current)) {
                                        world.setBlockState(pos, TFCBlockRockVariant
                                                .get(ChunkDataTFC.getRockHeight(world, pos),
                                                        Type.SILTY_CLAY_GRASS)
                                                .getDefaultState(), 2);
                                    }
                                }
                            }
                        }
                        if (rng.nextInt(10) == 0) {
                            final BlockPos posTop = world.getTopSolidOrLiquidBlock(posHorizontal);

                            for (Plant plant : TFCRegistries.PLANTS.getValuesCollection()) {
                                if (plant.getIsClayMarking()) {
                                    TFCBlockPlant plantBlock = TFCBlockPlant.get(plant);
                                    IBlockState state = plantBlock.getDefaultState();
                                    int plantAge = plant.getAgeForWorldgen(rng, TFCClimate.getActualTemp(world, posTop));

                                    if (!world.provider.isNether() && !world.isOutsideBuildHeight(posTop) &&
                                            plant.isValidLocation(TFCClimate.getActualTemp(world, posTop), ChunkDataTFC.getRainfall(world, posTop), world.getLightFor(EnumSkyBlock.SKY, posTop)) &&
                                            world.isAirBlock(posTop) &&
                                            plantBlock.canBlockStay(world, posTop, state)) {
                                        if (TFCBlocks.isClay(current)) {
                                            world.setBlockState(posTop, state.withProperty(TFCBlockPlant.AGE, plantAge), 2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void generateEarthenwareClaySurface(World world, Random rng, BlockPos start) {
        if (TFCConfig.FloraeGeneral.WORLD.enableAllEarthenwareClay && ChunkDataTFC.getDrainage(world, start) <= 1) {
            ChunkDataTFC data = ChunkDataTFC.get(world, start);

            int radius = rng.nextInt(5) + 1;
            int depth = rng.nextInt(3) + 1;

            int sandyEarthenwareClayRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.sandyEarthenwareClayRarity);
            int sandyEarthenwareClayLoamRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.sandyEarthenwareClayLoamRarity);
            int kaoliniteClayRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.kaoliniteClayRarity);
            int kaoliniteClayLoamRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.kaoliniteClayLoamRarity);
            int siltyEarthenwareClayLoamRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.siltyEarthenwareClayLoamRarity);
            int kaoliniteClayHumusRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.kaoliniteClayHumusRarity);
            int siltyEarthenwareClayRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.siltyEarthenwareClayRarity);

            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    if (x * x + z * z > radius * radius) continue;
                    final BlockPos posHorizontal = start.add(x, 0, z);

                    for (int y = -depth; y <= +depth; y++) {
                        final BlockPos pos = posHorizontal.add(0, y, 0);
                        final IBlockState current = world.getBlockState(pos);

                        if (data.getRainfall() < RAINFALL_SANDY && TFCConfig.FloraeGeneral.WORLD.enableAllSpecialSoil) {
                            if (data.getRainfall() > RAINFALL_SAND_SANDY_MIX) {
                                if (sandyEarthenwareClayRarity == 0) {
                                    if (TFCBlocks.isDirt(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SANDY_EARTHENWARE_CLAY)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isPodzol(current) && TFCConfig.FloraeGeneral.WORLD.enableAllPodzol) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SANDY_EARTHENWARE_CLAY_PODZOL)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SPARSE_SANDY_EARTHENWARE_CLAY_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isDryGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.DRY_SANDY_EARTHENWARE_CLAY_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SANDY_EARTHENWARE_CLAY_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    }
                                }
                            } else if (data.getRainfall() > RAINFALL_SAND) {
                                if (sandyEarthenwareClayLoamRarity == 0) {
                                    if (TFCBlocks.isDirt(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SANDY_EARTHENWARE_CLAY_LOAM)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isPodzol(current) && TFCConfig.FloraeGeneral.WORLD.enableAllPodzol) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SANDY_EARTHENWARE_CLAY_LOAM_PODZOL)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SPARSE_SANDY_EARTHENWARE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isDryGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.DRY_SANDY_EARTHENWARE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SANDY_EARTHENWARE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    }
                                }
                            }
                        } else if (data.getRainfall() > RAINFALL_SANDY) {
                            if (kaoliniteClayRarity == 0) {
                                if (TFCBlocks.isDirt(current)) {
                                    world.setBlockState(pos,
                                            TFCBlockRockVariant
                                                    .get(ChunkDataTFC.getRockHeight(world, pos),
                                                            Type.EARTHENWARE_CLAY)
                                                    .getDefaultState(),
                                            2);
                                } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                    world.setBlockState(pos,
                                            TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                            Type.SPARSE_EARTHENWARE_CLAY_GRASS)
                                                    .getDefaultState(),
                                            2);
                                } else if (TFCBlocks.isDryGrass(current)) {
                                    world.setBlockState(pos,
                                            TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                            Type.DRY_EARTHENWARE_CLAY_GRASS)
                                                    .getDefaultState(),
                                            2);
                                } else if (TFCBlocks.isGrass(current)) {
                                    world.setBlockState(pos, TFCBlockRockVariant
                                            .get(ChunkDataTFC.getRockHeight(world, pos),
                                                    Type.EARTHENWARE_CLAY_GRASS)
                                            .getDefaultState(), 2);
                                }
                            }
                            if (data.getRainfall() < RAINFALL_SILTY && TFCConfig.FloraeGeneral.WORLD.enableAllSpecialSoil) {
                                if (kaoliniteClayLoamRarity == 0) {
                                    if (TFCBlocks.isDirt(current)) {
                                        world.setBlockState(pos, TFCBlockRockVariant
                                                .get(ChunkDataTFC.getRockHeight(world, pos),
                                                        Type.EARTHENWARE_CLAY_LOAM)
                                                .getDefaultState(), 2);
                                    } else if (TFCBlocks.isPodzol(current) && TFCConfig.FloraeGeneral.WORLD.enableAllPodzol) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.EARTHENWARE_CLAY_LOAM_PODZOL)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SPARSE_EARTHENWARE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isDryGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.DRY_EARTHENWARE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.EARTHENWARE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    }
                                }
                            }
                        } else if (data.getRainfall() > RAINFALL_SILTY && TFCConfig.FloraeGeneral.WORLD.enableAllSpecialSoil) {
                            if (data.getRainfall() < RAINFALL_SILT_SILTY_MIX) {
                                if (siltyEarthenwareClayLoamRarity == 0) {
                                    if (TFCBlocks.isDirt(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SILTY_EARTHENWARE_CLAY_LOAM)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isPodzol(current) && TFCConfig.FloraeGeneral.WORLD.enableAllPodzol) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SILTY_EARTHENWARE_CLAY_LOAM_PODZOL)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SPARSE_SILTY_EARTHENWARE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isDryGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.DRY_SILTY_EARTHENWARE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SILTY_EARTHENWARE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    }
                                }
                            } else if (data.getRainfall() < RAINFALL_SILT) {
                                if (kaoliniteClayHumusRarity == 0) {
                                    if (TFCBlocks.isDirt(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.EARTHENWARE_CLAY_HUMUS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SPARSE_EARTHENWARE_CLAY_HUMUS_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isDryGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.DRY_EARTHENWARE_CLAY_HUMUS_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.EARTHENWARE_CLAY_HUMUS_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    }
                                }
                            } else {
                                if (siltyEarthenwareClayRarity == 0) {
                                    if (TFCBlocks.isDirt(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SILTY_EARTHENWARE_CLAY)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isPodzol(current) && TFCConfig.FloraeGeneral.WORLD.enableAllPodzol) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SILTY_EARTHENWARE_CLAY_PODZOL)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SPARSE_SILTY_EARTHENWARE_CLAY_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isDryGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.DRY_SILTY_EARTHENWARE_CLAY_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SILTY_EARTHENWARE_CLAY_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    }
                                }
                            }
                        }
                        if (rng.nextInt(10) == 0) {
                            final BlockPos posTop = world.getTopSolidOrLiquidBlock(posHorizontal);

                            for (Plant plant : TFCRegistries.PLANTS.getValuesCollection()) {
                                if (plant.getIsClayMarking()) {
                                    TFCBlockPlant plantBlock = TFCBlockPlant.get(plant);
                                    IBlockState state = plantBlock.getDefaultState();
                                    int plantAge = plant.getAgeForWorldgen(rng, TFCClimate.getActualTemp(world, posTop));

                                    if (!world.provider.isNether() && !world.isOutsideBuildHeight(posTop) &&
                                            plant.isValidLocation(TFCClimate.getActualTemp(world, posTop), ChunkDataTFC.getRainfall(world, posTop), world.getLightFor(EnumSkyBlock.SKY, posTop)) &&
                                            world.isAirBlock(posTop) &&
                                            plantBlock.canBlockStay(world, posTop, state)) {
                                        if (TFCBlocks.isClay(current)) {
                                            world.setBlockState(posTop, state.withProperty(TFCBlockPlant.AGE, plantAge), 2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void generateKaoliniteClaySurface(World world, Random rng, BlockPos start) {
        if (TFCConfig.FloraeGeneral.WORLD.enableAllKaoliniteClay && ChunkDataTFC.getDrainage(world, start) <= 1) {
            ChunkDataTFC data = ChunkDataTFC.get(world, start);

            int radius = rng.nextInt(5) + 1;
            int depth = rng.nextInt(3) + 1;

            int sandyKaoliniteClayRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.sandyKaoliniteClayRarity);
            int sandyKaoliniteClayLoamRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.sandyKaoliniteClayLoamRarity);
            int kaoliniteClayRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.kaoliniteClayRarity);
            int kaoliniteClayLoamRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.kaoliniteClayLoamRarity);
            int siltyKaoliniteClayLoamRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.siltyKaoliniteClayLoamRarity);
            int kaoliniteClayHumusRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.kaoliniteClayHumusRarity);
            int siltyKaoliniteClayRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.siltyKaoliniteClayRarity);

            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    if (x * x + z * z > radius * radius) continue;
                    final BlockPos posHorizontal = start.add(x, 0, z);

                    for (int y = -depth; y <= +depth; y++) {
                        final BlockPos pos = posHorizontal.add(0, y, 0);
                        final IBlockState current = world.getBlockState(pos);

                        if (data.getRainfall() < RAINFALL_SANDY && TFCConfig.FloraeGeneral.WORLD.enableAllSpecialSoil) {
                            if (data.getRainfall() > RAINFALL_SAND_SANDY_MIX) {
                                if (sandyKaoliniteClayRarity == 0) {
                                    if (TFCBlocks.isDirt(current)) {
                                        world.setBlockState(pos, TFCBlockRockVariant
                                                .get(ChunkDataTFC.getRockHeight(world, pos),
                                                        Type.SANDY_KAOLINITE_CLAY)
                                                .getDefaultState(), 2);
                                    } else if (TFCBlocks.isPodzol(current) && TFCConfig.FloraeGeneral.WORLD.enableAllPodzol) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SANDY_KAOLINITE_CLAY_PODZOL)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SPARSE_SANDY_KAOLINITE_CLAY_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isDryGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.DRY_SANDY_KAOLINITE_CLAY_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SANDY_KAOLINITE_CLAY_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    }
                                }
                            } else if (data.getRainfall() > RAINFALL_SAND) {
                                if (sandyKaoliniteClayLoamRarity == 0) {
                                    if (TFCBlocks.isDirt(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SANDY_KAOLINITE_CLAY_LOAM)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isPodzol(current) && TFCConfig.FloraeGeneral.WORLD.enableAllPodzol) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SANDY_KAOLINITE_CLAY_LOAM_PODZOL)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SPARSE_SANDY_KAOLINITE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isDryGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SANDY_KAOLINITE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    }
                                }
                            }
                        } else if (data.getRainfall() > RAINFALL_SANDY) {
                            if (kaoliniteClayRarity == 0) {
                                if (TFCBlocks.isDirt(current)) {
                                    world.setBlockState(pos,
                                            TFCBlockRockVariant
                                                    .get(ChunkDataTFC.getRockHeight(world, pos),
                                                            Type.KAOLINITE_CLAY)
                                                    .getDefaultState(),
                                            2);
                                } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                    world.setBlockState(pos,
                                            TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                            Type.SPARSE_KAOLINITE_CLAY_GRASS)
                                                    .getDefaultState(),
                                            2);
                                } else if (TFCBlocks.isDryGrass(current)) {
                                    world.setBlockState(pos, TFCBlockRockVariant
                                            .get(ChunkDataTFC.getRockHeight(world, pos),
                                                    Type.DRY_KAOLINITE_CLAY_GRASS)
                                            .getDefaultState(), 2);
                                } else if (TFCBlocks.isGrass(current)) {
                                    world.setBlockState(pos, TFCBlockRockVariant
                                            .get(ChunkDataTFC.getRockHeight(world, pos),
                                                    Type.KAOLINITE_CLAY_GRASS)
                                            .getDefaultState(), 2);
                                }
                            }
                            if (data.getRainfall() < RAINFALL_SILTY && TFCConfig.FloraeGeneral.WORLD.enableAllSpecialSoil) {
                                if (kaoliniteClayLoamRarity == 0) {
                                    if (TFCBlocks.isDirt(current)) {
                                        world.setBlockState(pos, TFCBlockRockVariant
                                                .get(ChunkDataTFC.getRockHeight(world, pos),
                                                        Type.KAOLINITE_CLAY_LOAM)
                                                .getDefaultState(), 2);
                                    } else if (TFCBlocks.isPodzol(current) && TFCConfig.FloraeGeneral.WORLD.enableAllPodzol) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.KAOLINITE_CLAY_LOAM_PODZOL)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SPARSE_KAOLINITE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isDryGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.DRY_KAOLINITE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.KAOLINITE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    }
                                }
                            }
                        } else if (data.getRainfall() > RAINFALL_SILTY && TFCConfig.FloraeGeneral.WORLD.enableAllSpecialSoil) {
                            if (data.getRainfall() < RAINFALL_SILT_SILTY_MIX) {
                                if (siltyKaoliniteClayLoamRarity == 0) {
                                    if (TFCBlocks.isDirt(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SILTY_KAOLINITE_CLAY_LOAM)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isPodzol(current) && TFCConfig.FloraeGeneral.WORLD.enableAllPodzol) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SILTY_KAOLINITE_CLAY_LOAM_PODZOL)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SPARSE_SILTY_KAOLINITE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isDryGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SILTY_KAOLINITE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    }
                                }
                            } else if (data.getRainfall() < RAINFALL_SILT) {
                                if (kaoliniteClayHumusRarity == 0) {
                                    if (TFCBlocks.isDirt(current)) {
                                        world.setBlockState(pos, TFCBlockRockVariant
                                                .get(ChunkDataTFC.getRockHeight(world, pos),
                                                        Type.KAOLINITE_CLAY_HUMUS)
                                                .getDefaultState(), 2);
                                    } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SPARSE_KAOLINITE_CLAY_HUMUS_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isDryGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.DRY_KAOLINITE_CLAY_HUMUS_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.KAOLINITE_CLAY_HUMUS_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    }
                                }
                            } else {
                                if (siltyKaoliniteClayRarity == 0) {
                                    if (TFCBlocks.isDirt(current)) {
                                        world.setBlockState(pos, TFCBlockRockVariant
                                                .get(ChunkDataTFC.getRockHeight(world, pos),
                                                        Type.SILTY_KAOLINITE_CLAY)
                                                .getDefaultState(), 2);
                                    } else if (TFCBlocks.isPodzol(current) && TFCConfig.FloraeGeneral.WORLD.enableAllPodzol) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SILTY_KAOLINITE_CLAY_PODZOL)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SPARSE_SILTY_KAOLINITE_CLAY_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isDryGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.DRY_SILTY_KAOLINITE_CLAY_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SILTY_KAOLINITE_CLAY_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    }
                                }
                            }
                        }
                        if (rng.nextInt(10) == 0) {
                            final BlockPos posTop = world.getTopSolidOrLiquidBlock(posHorizontal);

                            for (Plant plant : TFCRegistries.PLANTS.getValuesCollection()) {
                                if (plant.getIsClayMarking()) {
                                    TFCBlockPlant plantBlock = TFCBlockPlant.get(plant);
                                    IBlockState state = plantBlock.getDefaultState();
                                    int plantAge = plant.getAgeForWorldgen(rng, TFCClimate.getActualTemp(world, posTop));

                                    if (!world.provider.isNether() && !world.isOutsideBuildHeight(posTop) &&
                                            plant.isValidLocation(TFCClimate.getActualTemp(world, posTop), ChunkDataTFC.getRainfall(world, posTop), world.getLightFor(EnumSkyBlock.SKY, posTop)) &&
                                            world.isAirBlock(posTop) &&
                                            plantBlock.canBlockStay(world, posTop, state)) {
                                        if (TFCBlocks.isClay(current)) {
                                            world.setBlockState(posTop, state.withProperty(TFCBlockPlant.AGE, plantAge), 2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void generateStonewareClaySurface(World world, Random rng, BlockPos start) {
        if (TFCConfig.FloraeGeneral.WORLD.enableAllStonewareClay && ChunkDataTFC.getDrainage(world, start) <= 1) {
            ChunkDataTFC data = ChunkDataTFC.get(world, start);

            int radius = rng.nextInt(5) + 1;
            int depth = rng.nextInt(3) + 1;

            int sandyStonewareClayRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.sandyStonewareClayRarity);
            int sandyStonewareClayLoamRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.sandyStonewareClayLoamRarity);
            int kaoliniteClayRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.kaoliniteClayRarity);
            int kaoliniteClayLoamRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.kaoliniteClayLoamRarity);
            int siltyStonewareClayLoamRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.siltyStonewareClayLoamRarity);
            int kaoliniteClayHumusRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.kaoliniteClayHumusRarity);
            int siltyStonewareClayRarity = rng.nextInt(TFCConfig.FloraeGeneral.WORLD.siltyStonewareClayRarity);

            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    if (x * x + z * z > radius * radius) continue;
                    final BlockPos posHorizontal = start.add(x, 0, z);

                    for (int y = -depth; y <= +depth; y++) {
                        final BlockPos pos = posHorizontal.add(0, y, 0);
                        final IBlockState current = world.getBlockState(pos);

                        if (data.getRainfall() < RAINFALL_SANDY && TFCConfig.FloraeGeneral.WORLD.enableAllSpecialSoil) {
                            if (data.getRainfall() > RAINFALL_SAND_SANDY_MIX) {
                                if (sandyStonewareClayRarity == 0) {
                                    if (TFCBlocks.isDirt(current)) {
                                        world.setBlockState(pos, TFCBlockRockVariant
                                                .get(ChunkDataTFC.getRockHeight(world, pos),
                                                        Type.SANDY_STONEWARE_CLAY)
                                                .getDefaultState(), 2);
                                    } else if (TFCBlocks.isPodzol(current) && TFCConfig.FloraeGeneral.WORLD.enableAllPodzol) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SANDY_STONEWARE_CLAY_PODZOL)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SPARSE_SANDY_STONEWARE_CLAY_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isDryGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.DRY_SANDY_STONEWARE_CLAY_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SANDY_STONEWARE_CLAY_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    }
                                }
                            } else if (data.getRainfall() > RAINFALL_SAND) {
                                if (sandyStonewareClayLoamRarity == 0) {
                                    if (TFCBlocks.isDirt(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SANDY_STONEWARE_CLAY_LOAM)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isPodzol(current) && TFCConfig.FloraeGeneral.WORLD.enableAllPodzol) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SANDY_STONEWARE_CLAY_LOAM_PODZOL)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SPARSE_SANDY_STONEWARE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isDryGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.DRY_SANDY_STONEWARE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SANDY_STONEWARE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    }
                                }
                            }
                        } else if (data.getRainfall() > RAINFALL_SANDY) {
                            if (kaoliniteClayRarity == 0) {
                                if (TFCBlocks.isDirt(current)) {
                                    world.setBlockState(pos,
                                            TFCBlockRockVariant
                                                    .get(ChunkDataTFC.getRockHeight(world, pos),
                                                            Type.STONEWARE_CLAY)
                                                    .getDefaultState(),
                                            2);
                                } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                    world.setBlockState(pos,
                                            TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                            Type.SPARSE_STONEWARE_CLAY_GRASS)
                                                    .getDefaultState(),
                                            2);
                                } else if (TFCBlocks.isDryGrass(current)) {
                                    world.setBlockState(pos, TFCBlockRockVariant
                                            .get(ChunkDataTFC.getRockHeight(world, pos),
                                                    Type.DRY_STONEWARE_CLAY_GRASS)
                                            .getDefaultState(), 2);
                                } else if (TFCBlocks.isGrass(current)) {
                                    world.setBlockState(pos, TFCBlockRockVariant
                                            .get(ChunkDataTFC.getRockHeight(world, pos),
                                                    Type.STONEWARE_CLAY_GRASS)
                                            .getDefaultState(), 2);
                                }
                            }
                            if (data.getRainfall() < RAINFALL_SILTY && TFCConfig.FloraeGeneral.WORLD.enableAllSpecialSoil) {
                                if (kaoliniteClayLoamRarity == 0) {
                                    if (TFCBlocks.isDirt(current)) {
                                        world.setBlockState(pos, TFCBlockRockVariant
                                                .get(ChunkDataTFC.getRockHeight(world, pos),
                                                        Type.STONEWARE_CLAY_LOAM)
                                                .getDefaultState(), 2);
                                    } else if (TFCBlocks.isPodzol(current) && TFCConfig.FloraeGeneral.WORLD.enableAllPodzol) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.STONEWARE_CLAY_LOAM_PODZOL)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SPARSE_STONEWARE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isDryGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.DRY_STONEWARE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.STONEWARE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    }
                                }
                            }
                        } else if (data.getRainfall() > RAINFALL_SILTY && TFCConfig.FloraeGeneral.WORLD.enableAllSpecialSoil) {
                            if (data.getRainfall() < RAINFALL_SILT_SILTY_MIX) {
                                if (siltyStonewareClayLoamRarity == 0) {
                                    if (TFCBlocks.isDirt(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SILTY_STONEWARE_CLAY_LOAM)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isPodzol(current) && TFCConfig.FloraeGeneral.WORLD.enableAllPodzol) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SILTY_STONEWARE_CLAY_LOAM_PODZOL)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SPARSE_SILTY_STONEWARE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isDryGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.DRY_SILTY_STONEWARE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SILTY_STONEWARE_CLAY_LOAM_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    }
                                }
                            } else if (data.getRainfall() < RAINFALL_SILT) {
                                if (kaoliniteClayHumusRarity == 0) {
                                    if (TFCBlocks.isDirt(current)) {
                                        world.setBlockState(pos, TFCBlockRockVariant
                                                .get(ChunkDataTFC.getRockHeight(world, pos),
                                                        Type.STONEWARE_CLAY_HUMUS)
                                                .getDefaultState(), 2);
                                    } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SPARSE_STONEWARE_CLAY_HUMUS_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isDryGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.DRY_STONEWARE_CLAY_HUMUS_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.STONEWARE_CLAY_HUMUS_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    }
                                }
                            } else {
                                if (siltyStonewareClayRarity == 0) {
                                    if (TFCBlocks.isDirt(current)) {
                                        world.setBlockState(pos, TFCBlockRockVariant
                                                .get(ChunkDataTFC.getRockHeight(world, pos),
                                                        Type.SILTY_STONEWARE_CLAY)
                                                .getDefaultState(), 2);
                                    } else if (TFCBlocks.isPodzol(current) && TFCConfig.FloraeGeneral.WORLD.enableAllPodzol) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SILTY_STONEWARE_CLAY_PODZOL)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isSparseGrass(current) && TFCConfig.FloraeGeneral.WORLD.enableAllSparseGrass) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant
                                                        .get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SPARSE_SILTY_STONEWARE_CLAY_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isDryGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.DRY_SILTY_STONEWARE_CLAY_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    } else if (TFCBlocks.isGrass(current)) {
                                        world.setBlockState(pos,
                                                TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos),
                                                                Type.SILTY_STONEWARE_CLAY_GRASS)
                                                        .getDefaultState(),
                                                2);
                                    }
                                }
                            }
                        }
                        if (rng.nextInt(10) == 0) {
                            final BlockPos posTop = world.getTopSolidOrLiquidBlock(posHorizontal);

                            for (Plant plant : TFCRegistries.PLANTS.getValuesCollection()) {
                                if (plant.getIsClayMarking()) {
                                    TFCBlockPlant plantBlock = TFCBlockPlant.get(plant);
                                    IBlockState state = plantBlock.getDefaultState();
                                    int plantAge = plant.getAgeForWorldgen(rng, TFCClimate.getActualTemp(world, posTop));

                                    if (!world.provider.isNether() && !world.isOutsideBuildHeight(posTop) &&
                                            plant.isValidLocation(TFCClimate.getActualTemp(world, posTop), ChunkDataTFC.getRainfall(world, posTop), world.getLightFor(EnumSkyBlock.SKY, posTop)) &&
                                            world.isAirBlock(posTop) &&
                                            plantBlock.canBlockStay(world, posTop, state)) {
                                        if (TFCBlocks.isClay(current)) {
                                            world.setBlockState(posTop, state.withProperty(TFCBlockPlant.AGE, plantAge), 2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
