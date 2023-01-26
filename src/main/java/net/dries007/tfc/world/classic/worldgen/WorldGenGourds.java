package net.dries007.tfc.world.classic.worldgen;

import java.util.Random;
import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.util.climate.ClimateTFC;
import net.dries007.tfc.world.classic.ChunkGenTFC;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;

@ParametersAreNonnullByDefault
public class WorldGenGourds implements IWorldGenerator
{
    public static final float RAINFALL_MELON = 160;
    public static final float RAINFALL_PUMPKIN = 140;

	@Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        if (!(chunkGenerator instanceof ChunkGenTFC) && true) return; // if firmalife added

        generateMelons(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
        generatePumpkins(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
    }

    private void generateMelons(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        // Guarantees crop generation if possible (easier to balance by config file while also making it random)
        BlockPos chunkBlockPos = new BlockPos(chunkX << 4, 0, chunkZ << 4);

        float temperature = ClimateTFC.getAvgTemp(world, chunkBlockPos);
        float rainfall = ChunkDataTFC.getRainfall(world, chunkBlockPos);
        float floraDensity = ChunkDataTFC.getFloraDensity(world, chunkBlockPos);

        if (chunkGenerator instanceof ChunkGenTFC && world.provider.getDimension() == 0 && random.nextInt(20) == 0 && rainfall >= RAINFALL_MELON && temperature >= 18f && floraDensity >= 0.3f)
        {
            int melonsInChunk = 3 + random.nextInt(8);
            for (int i = 0; i < melonsInChunk; i++)
            {
                final int x = (chunkX << 4) + random.nextInt(16) + 8;
                final int z = (chunkZ << 4) + random.nextInt(16) + 8;
                final BlockPos pos = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z));

                if (world.isAirBlock(pos) && (BlocksTFC.isSoil(world.getBlockState(pos.down()))))
                {
                    final int rotationValue = random.nextInt(4);
                }
            }
        }
    }

    private void generatePumpkins(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        // Guarantees crop generation if possible (easier to balance by config file while also making it random)
        BlockPos chunkBlockPos = new BlockPos(chunkX << 4, 0, chunkZ << 4);

        float temperature = ClimateTFC.getAvgTemp(world, chunkBlockPos);
        float rainfall = ChunkDataTFC.getRainfall(world, chunkBlockPos);
        float floraDensity = ChunkDataTFC.getFloraDensity(world, chunkBlockPos);

        if (chunkGenerator instanceof ChunkGenTFC && world.provider.getDimension() == 0 && random.nextInt(20) == 0 && rainfall >= RAINFALL_PUMPKIN && temperature >= 10f && floraDensity >= 0.2f)
        {
            int pumpkinsInChunk = 3 + random.nextInt(8);
            for (int i = 0; i < pumpkinsInChunk; i++)
            {
                final int x = (chunkX << 4) + random.nextInt(16) + 8;
                final int z = (chunkZ << 4) + random.nextInt(16) + 8;
                final BlockPos pos = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z));

                if (world.isAirBlock(pos) && (BlocksTFC.isSoil(world.getBlockState(pos.down()))))
                {
                    final int rotationValue = random.nextInt(4);
                }
            }
        }
    }
}
