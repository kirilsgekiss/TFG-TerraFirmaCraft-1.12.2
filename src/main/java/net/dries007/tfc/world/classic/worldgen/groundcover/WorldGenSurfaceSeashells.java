package net.dries007.tfc.world.classic.worldgen.groundcover;

import java.util.Random;

import net.dries007.tfc.ConfigTFC;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import net.dries007.tfc.objects.blocks.TFCBlocks;
import net.dries007.tfc.world.classic.ChunkGenTFC;
import net.dries007.tfc.world.classic.WorldTypeTFC;
import net.dries007.tfc.world.classic.biomes.TFCBiomes;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;

public class WorldGenSurfaceSeashells implements IWorldGenerator
{
    private double factor;

    public WorldGenSurfaceSeashells()
    {
        factor = 1;
    }

    public void setFactor(double factor)
    {
        if (factor < 0) factor = 0;
        if (factor > 1) factor = 1;
        this.factor = factor;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        if (chunkGenerator instanceof ChunkGenTFC && world.provider.getDimension() == 0)
        {
            int xoff = chunkX * 16 + 8;
            int zoff = chunkZ * 16 + 8;

            final BlockPos chunkBlockPos = new BlockPos(chunkX << 4, 0, chunkZ << 4);
            final ChunkDataTFC baseChunkData = ChunkDataTFC.get(world, chunkBlockPos);

            for (int i = 0; i < ((1 + baseChunkData.getRainfall()) / ConfigTFC.FloraeGeneral.WORLD.groundcoverSeashellFrequency) * factor; i++)
            {
                BlockPos pos = new BlockPos(
                    xoff + random.nextInt(16),
                    0,
                    zoff + random.nextInt(16)
                );
                generateRock(world, random, pos.up(world.getTopSolidOrLiquidBlock(pos).getY()));
            }
        }
    }
    
    private void generateRock(World world, Random rand, BlockPos pos)
    {
        if (pos.getY() >= WorldTypeTFC.SEALEVEL && pos.getY() < WorldTypeTFC.SEALEVEL + 2)
        {
            final Biome b = world.getBiome(pos);
            if (b == TFCBiomes.OCEAN || b == TFCBiomes.DEEP_OCEAN || b == TFCBiomes.BEACH || b == TFCBiomes.GRAVEL_BEACH)
            {
                if (world.isAirBlock(pos) && world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP) && TFCBlocks.isGround(world.getBlockState(pos.down())))
                {
                    world.setBlockState(pos, TFCBlocks.SEASHELLS.getDefaultState());
                }
            }
        }
    }
}
