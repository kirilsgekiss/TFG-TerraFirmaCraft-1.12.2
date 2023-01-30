package net.dries007.tfc.world.classic.worldgen.cave;

import java.util.Random;

import javax.annotation.ParametersAreNonnullByDefault;

import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.objects.blocks.plants.TFCBlockCaveMushroom;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.dries007.tfc.world.classic.WorldTypeTFC;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;

@ParametersAreNonnullByDefault
public class WorldGenCaveMushrooms extends WorldGenerator
{
	@Override
    public boolean generate(World worldIn, Random rng, BlockPos pos)
    {
        int chance = rng.nextInt(5);
        if (chance == 0)
        {
            TFCBlockCaveMushroom mushroomBlock = BlocksTFC.BLUESHROOM;
            IBlockState state = mushroomBlock.getDefaultState();

            for (int i = 0; i < ChunkDataTFC.getRainfall(worldIn, pos) / 16; ++i)
            {
                BlockPos blockpos = pos.add(rng.nextInt(4) - rng.nextInt(4), rng.nextInt(4) - rng.nextInt(4), rng.nextInt(4) - rng.nextInt(4));

                if (worldIn.isAirBlock(blockpos) &&
                    pos.getY() < WorldTypeTFC.SEALEVEL - 3 &&
                    worldIn.getLightFor(EnumSkyBlock.SKY, blockpos) < 14 && 
                    !worldIn.canSeeSky(blockpos) &&
                    mushroomBlock.canBlockStay(worldIn, blockpos, state))
                {
                    setBlockAndNotifyAdequately(worldIn, blockpos, state);
                }
            }
        }
        else if (chance == 1)
        {
            TFCBlockCaveMushroom mushroomBlock = BlocksTFC.GLOWSHROOM;
            IBlockState state = mushroomBlock.getDefaultState();

            for (int i = 0; i < ChunkDataTFC.getRainfall(worldIn, pos) / 16; ++i)
            {
                BlockPos blockpos = pos.add(rng.nextInt(4) - rng.nextInt(4), rng.nextInt(4) - rng.nextInt(4), rng.nextInt(4) - rng.nextInt(4));

                if (worldIn.isAirBlock(blockpos) &&
                    pos.getY() < WorldTypeTFC.SEALEVEL - 3 &&
                    worldIn.getLightFor(EnumSkyBlock.SKY, blockpos) < 14 && 
                    !worldIn.canSeeSky(blockpos) &&
                    mushroomBlock.canBlockStay(worldIn, blockpos, state))
                {
                    setBlockAndNotifyAdequately(worldIn, blockpos, state);
                }
            }
        }
        else if (chance == 2)
        {
            TFCBlockCaveMushroom mushroomBlock = BlocksTFC.MAGMA_SHROOM;
            IBlockState state = mushroomBlock.getDefaultState();

            for (int i = 0; i < ChunkDataTFC.getRainfall(worldIn, pos) / 16; ++i)
            {
                BlockPos blockpos = pos.add(rng.nextInt(4) - rng.nextInt(4), rng.nextInt(4) - rng.nextInt(4), rng.nextInt(4) - rng.nextInt(4));

                if (worldIn.isAirBlock(blockpos) &&
                    pos.getY() < WorldTypeTFC.SEALEVEL - 3 &&
                    worldIn.getLightFor(EnumSkyBlock.SKY, blockpos) < 14 && 
                    !worldIn.canSeeSky(blockpos) &&
                    mushroomBlock.canBlockStay(worldIn, blockpos, state))
                {
                    setBlockAndNotifyAdequately(worldIn, blockpos, state);
                }
            }
        }
        else if (chance == 3)
        {
            TFCBlockCaveMushroom mushroomBlock = BlocksTFC.POISON_SHROOM;
            IBlockState state = mushroomBlock.getDefaultState();

            for (int i = 0; i < ChunkDataTFC.getRainfall(worldIn, pos) / 16; ++i)
            {
                BlockPos blockpos = pos.add(rng.nextInt(4) - rng.nextInt(4), rng.nextInt(4) - rng.nextInt(4), rng.nextInt(4) - rng.nextInt(4));

                if (worldIn.isAirBlock(blockpos) &&
                    pos.getY() < WorldTypeTFC.SEALEVEL - 3 &&
                    worldIn.getLightFor(EnumSkyBlock.SKY, blockpos) < 14 && 
                    !worldIn.canSeeSky(blockpos) &&
                    mushroomBlock.canBlockStay(worldIn, blockpos, state))
                {
                    setBlockAndNotifyAdequately(worldIn, blockpos, state);
                }
            }
        }
        else if (chance == 4)
        {
            TFCBlockCaveMushroom mushroomBlock = BlocksTFC.SULPHUR_SHROOM;
            IBlockState state = mushroomBlock.getDefaultState();

            for (int i = 0; i < ChunkDataTFC.getRainfall(worldIn, pos) / 16; ++i)
            {
                BlockPos blockpos = pos.add(rng.nextInt(4) - rng.nextInt(4), rng.nextInt(4) - rng.nextInt(4), rng.nextInt(4) - rng.nextInt(4));

                if (worldIn.isAirBlock(blockpos) &&
                    pos.getY() < WorldTypeTFC.SEALEVEL - 3 &&
                    worldIn.getLightFor(EnumSkyBlock.SKY, blockpos) < 14 && 
                    !worldIn.canSeeSky(blockpos) &&
                    mushroomBlock.canBlockStay(worldIn, blockpos, state))
                {
                    setBlockAndNotifyAdequately(worldIn, blockpos, state);
                }
            }
        }
        return true;
    }
}
