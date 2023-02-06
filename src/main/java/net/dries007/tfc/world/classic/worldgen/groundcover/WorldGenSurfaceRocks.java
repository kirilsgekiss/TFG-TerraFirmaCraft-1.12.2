package net.dries007.tfc.world.classic.worldgen.groundcover;

import net.dries007.tfc.TFCConfig;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.objects.blocks.TFCBlocks;
import net.dries007.tfc.objects.blocks.groundcover.TFCBlockSurfaceRock;
import net.dries007.tfc.world.classic.ChunkGenTFC;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenSurfaceRocks implements IWorldGenerator {
    private double factor;

    public WorldGenSurfaceRocks() {
        factor = 1;
    }

    public void setFactor(double factor) {
        if (factor < 0) factor = 0;
        if (factor > 1) factor = 1;
        this.factor = factor;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (chunkGenerator instanceof ChunkGenTFC && world.provider.getDimension() == 0) {
            final BlockPos chunkBlockPos = new BlockPos(chunkX << 4, 0, chunkZ << 4);
            final ChunkDataTFC baseChunkData = ChunkDataTFC.get(world, chunkBlockPos);

            int xoff = chunkX * 16 + 8;
            int zoff = chunkZ * 16 + 8;

            for (int i = 0; i < TFCConfig.FloraeGeneral.WORLD.groundcoverRockFrequency * factor; i++) {
                BlockPos pos = new BlockPos(
                        xoff + random.nextInt(16),
                        0,
                        zoff + random.nextInt(16)
                );
                Rock rock = baseChunkData.getRock1(pos);
                generateRock(world, pos.up(world.getTopSolidOrLiquidBlock(pos).getY()), rock);
            }
        }
    }

    private void generateRock(World world, BlockPos pos, Rock rock) {
        if (world.isAirBlock(pos) && world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP) && (TFCBlocks.isSoil(world.getBlockState(pos.down())) || TFCBlocks.isRawStone(world.getBlockState(pos.down())))) {
            world.setBlockState(pos, TFCBlockSurfaceRock.get(rock).getDefaultState());
        }
    }
}
