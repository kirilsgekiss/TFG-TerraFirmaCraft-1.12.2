package net.dries007.tfc.world.classic.worldgen;

import net.dries007.tfc.objects.blocks.TFCBlocks;
import net.dries007.tfc.objects.blocks.groundcover.TFCBlockCoral;
import net.dries007.tfc.objects.blocks.groundcover.TFCBlockCoralBlock;
import net.dries007.tfc.util.climate.TFCClimate;
import net.dries007.tfc.world.classic.ChunkGenTFC;
import net.dries007.tfc.world.classic.WorldTypeTFC;
import net.dries007.tfc.world.classic.biomes.TFCBiomes;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@ParametersAreNonnullByDefault
public class WorldGenGlowPlant implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (!(chunkGenerator instanceof ChunkGenTFC)) return;

        generateGlowPlant(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
    }

    private void generateGlowPlant(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        BlockPos chunkBlockPos = new BlockPos(chunkX << 4, 0, chunkZ << 4);
        ChunkDataTFC data = ChunkDataTFC.get(world, chunkBlockPos);

        Biome b = world.getBiome(chunkBlockPos);
        float avgTemperature = TFCClimate.getAvgTemp(world, chunkBlockPos);
        float rainfall = ChunkDataTFC.getRainfall(world, chunkBlockPos);
        float floraDensity = data.getFloraDensity();
        float floraDiversity = data.getFloraDiversity();

        int glowPlantsInChunk = 5 + random.nextInt(20);
        for (int i = 0; i < (glowPlantsInChunk); i++) {
            final int x = (chunkX << 4) + random.nextInt(16) + 8;
            final int z = (chunkZ << 4) + random.nextInt(16) + 8;
            final BlockPos pos = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z));

            if ((b == TFCBiomes.OCEAN || b == TFCBiomes.DEEP_OCEAN || b == TFCBiomes.BEACH || b == TFCBiomes.GRAVEL_BEACH) && world.provider.getDimension() == 0) {
                if (isValidPosition(world, pos) && pos.getY() < WorldTypeTFC.SEALEVEL - 1 && pos.getY() > 115 && floraDensity >= 0.4f && floraDiversity >= 0.3f && floraDensity <= 0.6f && floraDiversity <= 0.5f && avgTemperature >= 10f && avgTemperature <= 28f && rainfall >= 150f) {
                    world.setBlockState(pos, TFCBlocks.GLOWING_SEA_BANANA.getDefaultState());
                }
            }
        }
    }

    protected boolean isValidPosition(World world, BlockPos pos) {
        IBlockState current = world.getBlockState(pos);
        IBlockState up = world.getBlockState(pos.up());
        IBlockState down = world.getBlockState(pos.down());
        IBlockState north = world.getBlockState(pos.north());
        IBlockState south = world.getBlockState(pos.south());
        IBlockState east = world.getBlockState(pos.east());
        IBlockState west = world.getBlockState(pos.west());
        return ((up.getBlock() instanceof TFCBlockCoralBlock || down.getBlock() instanceof TFCBlockCoralBlock || north.getBlock() instanceof TFCBlockCoralBlock || south.getBlock() instanceof TFCBlockCoralBlock || east.getBlock() instanceof TFCBlockCoralBlock || west.getBlock() instanceof TFCBlockCoralBlock || current.getBlock() instanceof TFCBlockCoral) && !(world.isAirBlock(pos.up())));
    }
}
