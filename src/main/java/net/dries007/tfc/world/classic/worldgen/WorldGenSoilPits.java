/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.world.classic.worldgen;

import net.dries007.tfc.TFCConfig;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Plant;
import net.dries007.tfc.api.types.Rock.Type;
import net.dries007.tfc.objects.blocks.TFCBlocks;
import net.dries007.tfc.objects.blocks.plants.TFCBlockPlant;
import net.dries007.tfc.objects.blocks.rock.TFCBlockRockVariant;
import net.dries007.tfc.util.climate.TFCClimate;
import net.dries007.tfc.world.classic.ChunkGenTFC;
import net.dries007.tfc.world.classic.WorldTypeTFC;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * todo: make these bigger without causing cascading lag.
 * This will require larger re-writes on the scale of oregen
 * Wait for 1.14+ as AlcatrazEscapee is doing a worldgen rewrite anyway
 */
public class WorldGenSoilPits implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (!(chunkGenerator instanceof ChunkGenTFC)) return;
        final BlockPos chunkBlockPos = new BlockPos(chunkX << 4, 0, chunkZ << 4);

        BlockPos pos = world.getTopSolidOrLiquidBlock(chunkBlockPos.add(8 + random.nextInt(16), 0, 8 + random.nextInt(16)));
        generateClay(world, random, pos);

        pos = world.getTopSolidOrLiquidBlock(chunkBlockPos.add(8 + random.nextInt(16), 0, 8 + random.nextInt(16)));
        generatePeat(world, random, pos);
    }

    private void generateClay(World world, Random rng, BlockPos start) {
        // If this has to have a radius that is >= 8, then it needs to be moved to a cascading-lag safe model
        // Otherwise, do not change this unless you are prepared to do some fairly large re-writes, similar to how ore gen is handled
        int radius = rng.nextInt(6) + 2;
        int depth = rng.nextInt(3) + 1;
        if (rng.nextInt(TFCConfig.General.WORLD.clayRarity) != 0 || start.getY() > WorldTypeTFC.SEALEVEL + 6) return;
        if (ChunkDataTFC.getRainfall(world, start) < TFCConfig.General.WORLD.clayRainfallThreshold) return;

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if (x * x + z * z > radius * radius) continue;
                final BlockPos posHorizontal = start.add(x, 0, z);

                boolean flag = false;
                for (int y = -depth; y <= +depth; y++) {
                    final BlockPos pos = posHorizontal.add(0, y, 0);
                    final IBlockState current = world.getBlockState(pos);
                    if (TFCBlocks.isDirt(current)) {
                        world.setBlockState(pos, TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos), Type.CLAY).getDefaultState(), 2);
                        flag = true;
                    } else if (TFCBlocks.isGrass(current)) {
                        world.setBlockState(pos, TFCBlockRockVariant.get(ChunkDataTFC.getRockHeight(world, pos), Type.CLAY_GRASS).getDefaultState(), 2);
                        flag = true;
                    }
                }
                if (flag && rng.nextInt(15) == 0) {
                    final BlockPos pos = world.getTopSolidOrLiquidBlock(posHorizontal);

                    for (Plant plant : TFCRegistries.PLANTS.getValuesCollection()) {
                        if (plant.getIsClayMarking()) {
                            TFCBlockPlant plantBlock = TFCBlockPlant.get(plant);
                            IBlockState state = plantBlock.getDefaultState();
                            int plantAge = plant.getAgeForWorldgen(rng, TFCClimate.getActualTemp(world, pos));

                            if (!world.provider.isNether() && !world.isOutsideBuildHeight(pos) &&
                                    plant.isValidLocation(TFCClimate.getActualTemp(world, pos), ChunkDataTFC.getRainfall(world, pos), world.getLightFor(EnumSkyBlock.SKY, pos)) &&
                                    world.isAirBlock(pos) &&
                                    plantBlock.canBlockStay(world, pos, state)) {
                                world.setBlockState(pos, state.withProperty(TFCBlockPlant.AGE, plantAge), 2);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean generatePeat(World world, Random rng, BlockPos start) {
        // If this has to have a radius that is >= 8, then it needs to be moved to a cascading-lag safe model
        // Otherwise, do not change this unless you are prepared to do some fairly large re-writes, similar to how ore gen is handled
        int radius = rng.nextInt(4) + 4;
        byte depth = 2;

        if (rng.nextInt(30) != 0 || start.getY() > WorldTypeTFC.SEALEVEL) return false;
        ChunkDataTFC data = ChunkDataTFC.get(world, start);
        if (data.isInitialized() && data.getRainfall() >= 375f && data.getFloraDiversity() >= 0.5f && data.getFloraDensity() >= 0.5f && world.getBiome(start).getHeightVariation() < 0.15)
            return false;

        for (int x = -radius; x <= radius; ++x) {
            for (int z = -radius; z <= radius; ++z) {
                if (x * x + z * z > radius * radius) continue;

                for (int y = -depth; y <= depth; ++y) {
                    final BlockPos pos = start.add(x, y, z);
                    final IBlockState current = world.getBlockState(pos);

                    if (TFCBlocks.isGrass(current)) {
                        world.setBlockState(pos, TFCBlocks.PEAT_GRASS.getDefaultState(), 2);
                    } else if (TFCBlocks.isDirt(current) || TFCBlocks.isClay(current)) {
                        world.setBlockState(pos, TFCBlocks.PEAT.getDefaultState(), 2);
                    }
                }
            }
        }
        return true;
    }
}
