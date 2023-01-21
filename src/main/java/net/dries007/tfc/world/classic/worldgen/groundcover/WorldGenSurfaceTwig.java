package net.dries007.tfc.world.classic.worldgen.groundcover;

import java.util.Random;

import net.dries007.tfc.ConfigTFC;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import net.dries007.tfc.objects.blocks.TFCBlocks;
import net.dries007.tfc.world.classic.ChunkGenTFC;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;

public class WorldGenSurfaceTwig implements IWorldGenerator
{
    private double factor;

    public WorldGenSurfaceTwig()
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
        final BlockPos chunkBlockPos = new BlockPos(chunkX << 4, 0, chunkZ << 4);
        final ChunkDataTFC baseChunkData = ChunkDataTFC.get(world, chunkBlockPos);

        if (chunkGenerator instanceof ChunkGenTFC && world.provider.getDimension() == 0)
        {

            int xoff = chunkX * 16 + 8;
            int zoff = chunkZ * 16 + 8;

            for (int i = 0; i < ConfigTFC.FloraeGeneral.WORLD.groundcoverTwigFrequency * factor; i++)
            {
                BlockPos pos = new BlockPos(
                    xoff + random.nextInt(16),
                    0,
                    zoff + random.nextInt(16)
                );
                generateRock(world, pos.up(world.getTopSolidOrLiquidBlock(pos).getY()));
            }
        }
    }
    
    private void generateRock(World world, BlockPos pos)
    {
        ChunkDataTFC data = ChunkDataTFC.get(world, pos);
        if (pos.getY() > 146 && pos.getY() < 170 && data.getRainfall() >= 75)
        {
            if (world.isAirBlock(pos) && world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP))
            {
                if (TFCBlocks.isSoil(world.getBlockState(pos.down())) || TFCBlocks.isGround(world.getBlockState(pos.down())))
                {
                    world.setBlockState(pos, TFCBlocks.TWIG.getDefaultState());
                }
            }
        }
    }
}
