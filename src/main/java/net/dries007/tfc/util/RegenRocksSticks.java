package net.dries007.tfc.util;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;

import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.objects.blocks.plants.BlockShortGrassTFC;
import net.dries007.tfc.world.classic.ChunkGenTFC;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;
import net.dries007.tfc.world.classic.worldgen.WorldGenLooseRocks;

public class RegenRocksSticks extends WorldGenLooseRocks
{
    public RegenRocksSticks()
    {
        super();
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        if (chunkGenerator instanceof ChunkGenTFC && world.provider.getDimension() == 0)
        {
            final BlockPos chunkBlockPos = new BlockPos(chunkX << 4, 0, chunkZ << 4);
            final ChunkDataTFC baseChunkData = ChunkDataTFC.get(world, chunkBlockPos);

            int xoff = chunkX * 16 + 8;
            int zoff = chunkZ * 16 + 8;

            for (int i = 0; i < ConfigTFC.General.WORLD.looseRocksFrequency * factor; i++)
            {
                BlockPos pos = new BlockPos(xoff + random.nextInt(16), 0, zoff + random.nextInt(16));
                Rock rock = baseChunkData.getRock1(pos);
                generateRock(world, pos.up(world.getTopSolidOrLiquidBlock(pos).getY()), rock);
            }
        }
    }

    @Override
    protected void generateRock(World world, BlockPos pos, Rock rock)
    {
        if (isReplaceable(world, pos))
        {
            super.generateRock(world, pos, rock);
        }
    }

    private static Boolean isReplaceable(World world, BlockPos pos)
    {
        // Modified to allow replacement of grass during spring regen
        Block test = world.getBlockState(pos).getBlock();
        return test instanceof BlockShortGrassTFC || test.isAir(world.getBlockState(pos), world, pos);
    }
}
