package net.dries007.tfc.world.classic.worldgen.cave;

import net.dries007.tfc.objects.blocks.TFCBlocks;
import net.dries007.tfc.objects.blocks.plants.TFCBlockCaveMushroom;
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
public class WorldGenCaveMushrooms extends WorldGenerator {
    @Override
    public boolean generate(World worldIn, Random rng, BlockPos pos) {
        int chance = rng.nextInt(5);
        if (chance == 0) {
            TFCBlockCaveMushroom mushroomBlock = TFCBlocks.BLUESHROOM;
            IBlockState state = mushroomBlock.getDefaultState();

            for (int i = 0; i < ChunkDataTFC.getRainfall(worldIn, pos) / 16; ++i) {
                BlockPos blockpos = pos.add(rng.nextInt(4) - rng.nextInt(4), rng.nextInt(4) - rng.nextInt(4), rng.nextInt(4) - rng.nextInt(4));

                if (worldIn.isAirBlock(blockpos) &&
                        pos.getY() < WorldTypeTFC.SEALEVEL - 3 &&
                        worldIn.getLightFor(EnumSkyBlock.SKY, blockpos) < 14 &&
                        !worldIn.canSeeSky(blockpos) &&
                        mushroomBlock.canBlockStay(worldIn, blockpos, state)) {
                    setBlockAndNotifyAdequately(worldIn, blockpos, state);
                }
            }
        } else if (chance == 1) {
            TFCBlockCaveMushroom mushroomBlock = TFCBlocks.GLOWSHROOM;
            IBlockState state = mushroomBlock.getDefaultState();

            for (int i = 0; i < ChunkDataTFC.getRainfall(worldIn, pos) / 16; ++i) {
                BlockPos blockpos = pos.add(rng.nextInt(4) - rng.nextInt(4), rng.nextInt(4) - rng.nextInt(4), rng.nextInt(4) - rng.nextInt(4));

                if (worldIn.isAirBlock(blockpos) &&
                        pos.getY() < WorldTypeTFC.SEALEVEL - 3 &&
                        worldIn.getLightFor(EnumSkyBlock.SKY, blockpos) < 14 &&
                        !worldIn.canSeeSky(blockpos) &&
                        mushroomBlock.canBlockStay(worldIn, blockpos, state)) {
                    setBlockAndNotifyAdequately(worldIn, blockpos, state);
                }
            }
        } else if (chance == 2) {
            TFCBlockCaveMushroom mushroomBlock = TFCBlocks.MAGMA_SHROOM;
            IBlockState state = mushroomBlock.getDefaultState();

            for (int i = 0; i < ChunkDataTFC.getRainfall(worldIn, pos) / 16; ++i) {
                BlockPos blockpos = pos.add(rng.nextInt(4) - rng.nextInt(4), rng.nextInt(4) - rng.nextInt(4), rng.nextInt(4) - rng.nextInt(4));

                if (worldIn.isAirBlock(blockpos) &&
                        pos.getY() < WorldTypeTFC.SEALEVEL - 3 &&
                        worldIn.getLightFor(EnumSkyBlock.SKY, blockpos) < 14 &&
                        !worldIn.canSeeSky(blockpos) &&
                        mushroomBlock.canBlockStay(worldIn, blockpos, state)) {
                    setBlockAndNotifyAdequately(worldIn, blockpos, state);
                }
            }
        } else if (chance == 3) {
            TFCBlockCaveMushroom mushroomBlock = TFCBlocks.POISON_SHROOM;
            IBlockState state = mushroomBlock.getDefaultState();

            for (int i = 0; i < ChunkDataTFC.getRainfall(worldIn, pos) / 16; ++i) {
                BlockPos blockpos = pos.add(rng.nextInt(4) - rng.nextInt(4), rng.nextInt(4) - rng.nextInt(4), rng.nextInt(4) - rng.nextInt(4));

                if (worldIn.isAirBlock(blockpos) &&
                        pos.getY() < WorldTypeTFC.SEALEVEL - 3 &&
                        worldIn.getLightFor(EnumSkyBlock.SKY, blockpos) < 14 &&
                        !worldIn.canSeeSky(blockpos) &&
                        mushroomBlock.canBlockStay(worldIn, blockpos, state)) {
                    setBlockAndNotifyAdequately(worldIn, blockpos, state);
                }
            }
        } else if (chance == 4) {
            TFCBlockCaveMushroom mushroomBlock = TFCBlocks.SULPHUR_SHROOM;
            IBlockState state = mushroomBlock.getDefaultState();

            for (int i = 0; i < ChunkDataTFC.getRainfall(worldIn, pos) / 16; ++i) {
                BlockPos blockpos = pos.add(rng.nextInt(4) - rng.nextInt(4), rng.nextInt(4) - rng.nextInt(4), rng.nextInt(4) - rng.nextInt(4));

                if (worldIn.isAirBlock(blockpos) &&
                        pos.getY() < WorldTypeTFC.SEALEVEL - 3 &&
                        worldIn.getLightFor(EnumSkyBlock.SKY, blockpos) < 14 &&
                        !worldIn.canSeeSky(blockpos) &&
                        mushroomBlock.canBlockStay(worldIn, blockpos, state)) {
                    setBlockAndNotifyAdequately(worldIn, blockpos, state);
                }
            }
        }
        return true;
    }
}
