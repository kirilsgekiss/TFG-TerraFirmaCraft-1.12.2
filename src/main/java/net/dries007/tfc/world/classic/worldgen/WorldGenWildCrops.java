/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.world.classic.worldgen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import tfcflorae.objects.blocks.BlocksTFCF;
import net.dries007.tfc.objects.blocks.plants.BlockTallGrassWater;
import net.dries007.tfc.types.DefaultPlants;

import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.ICrop;
import net.dries007.tfc.api.types.Plant;
import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.objects.blocks.agriculture.BlockCropTFC;
import net.dries007.tfc.util.agriculture.Crop;
import net.dries007.tfc.util.calendar.CalendarTFC;
import net.dries007.tfc.util.climate.ClimateTFC;
import net.dries007.tfc.world.classic.ChunkGenTFC;
import net.dries007.tfc.world.classic.biomes.BiomesTFC;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;

@ParametersAreNonnullByDefault
public class WorldGenWildCrops implements IWorldGenerator
{
    private static final List<ICrop> CROPS = new ArrayList<>();

    public static void register(ICrop bush)
    {
        CROPS.add(bush);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        if (chunkGenerator instanceof ChunkGenTFC && world.provider.getDimension() == 0 && CROPS.size() > 0 && ConfigTFC.General.FOOD.cropRarity > 0)
        {
            // Guarantees crop generation if possible (easier to balance by config file while also making it random)
            BlockPos chunkBlockPos = new BlockPos(chunkX << 4, 0, chunkZ << 4);

            Collections.shuffle(CROPS);
            ChunkDataTFC data = ChunkDataTFC.get(world, chunkBlockPos);
            float temperature = ClimateTFC.getAvgTemp(world, chunkBlockPos);
            float rainfall = ChunkDataTFC.getRainfall(world, chunkBlockPos);
            float floraDensity = data.getFloraDensity();
            float floraDiversity = data.getFloraDiversity();
            Biome b = world.getBiome(chunkBlockPos);

            ICrop crop = CROPS.stream().filter(x -> x.isValidConditions(temperature, rainfall)).findFirst().orElse(null);
            if (crop != null)
            {
                if (random.nextInt(ConfigTFC.General.FOOD.cropRarity) == 0)
                {
                    BlockCropTFC cropBlock = BlockCropTFC.get(crop);
                    int cropsInChunk = 3 + random.nextInt(5);
                    for (int i = 0; i < cropsInChunk; i++)
                    {
                        final int x = (chunkX << 4) + random.nextInt(16) + 8;
                        final int z = (chunkZ << 4) + random.nextInt(16) + 8;
                        final BlockPos pos = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z));
                        if (isValidPosition(world, pos))
                        {
                            double yearProgress = CalendarTFC.CALENDAR_TIME.getMonthOfYear().ordinal() / 11.0;
                            int maxStage = crop.getMaxStage();
                            int growth = (int) (yearProgress * maxStage) + 3 - random.nextInt(2);
                            if (growth > maxStage)
                                growth = maxStage;
                            world.setBlockState(pos, cropBlock.getDefaultState().withProperty(cropBlock.getStageProperty(), growth).withProperty(BlockCropTFC.WILD, true), 2);

                        }
                    }
                }
                if (crop == Crop.BARLEY ||
                        crop == Crop.MAIZE ||
                        crop == Crop.OAT ||
                        crop == Crop.RYE ||
                        crop == Crop.WHEAT)
                {
                    BlockCropTFC cropBlock = BlockCropTFC.get(crop);
                    for (int i = random.nextInt(Math.round(1 / floraDiversity)); i < (4 + floraDensity + floraDiversity) * (10 + random.nextInt(10)); i++)
                    {
                        BlockPos pos = world.getHeight(chunkBlockPos.add(random.nextInt(16) + 8, 0, random.nextInt(16) + 8));

                        if (isValidPosition(world, pos) && floraDensity <= Math.abs(0.2f - (random.nextGaussian() / 20)) && b == BiomesTFC.FIELDS)
                        {
                            double yearProgress = CalendarTFC.CALENDAR_TIME.getMonthOfYear().ordinal() / 11.0;
                            int maxStage = crop.getMaxStage();
                            int growth = (int) (yearProgress * maxStage) + 3 - random.nextInt(2);
                            if (growth > maxStage)
                                growth = maxStage;
                            world.setBlockState(pos, cropBlock.getDefaultState().withProperty(cropBlock.getStageProperty(), growth).withProperty(BlockCropTFC.WILD, true), 2);
                        }
                    }
                }
                if (crop == Crop.RICE)
                {
                    // Can't be arsed to make this any different. If it works, it works, hurray for that.
                    Plant plant = TFCRegistries.PLANTS.getValue(DefaultPlants.SAWGRASS);
                    BlockTallGrassWater plantBlock = BlockTallGrassWater.get(plant);
                    IBlockState state = plantBlock.getDefaultState();
                    IBlockState water = plant.getWaterType();

                    BlockCropTFC cropBlock = BlockCropTFC.get(crop);
                    for (int i = random.nextInt(Math.round(6 / floraDiversity)); i < (2 + floraDensity + floraDiversity) * (5 + random.nextInt(15)); i++)
                    {
                        BlockPos pos = world.getHeight(chunkBlockPos.add(random.nextInt(16) + 8, 0, random.nextInt(16) + 8));

                        if (world.isAirBlock(pos) &&
                                plantBlock.canPlaceBlockAt(world, pos) &&
                                plant.isValidFloatingWaterDepth(world, pos, water) &&
                                plantBlock.canBlockStay(world, pos, state))
                        {
                            double yearProgress = CalendarTFC.CALENDAR_TIME.getMonthOfYear().ordinal() / 11.0;
                            int maxStage = crop.getMaxStage();
                            int growth = (int) (yearProgress * maxStage) + 3 - random.nextInt(2);
                            if (growth > maxStage)
                                growth = maxStage;
                            world.setBlockState(pos, cropBlock.getDefaultState().withProperty(cropBlock.getStageProperty(), growth).withProperty(BlockCropTFC.WILD, true), 2);
                        }
                    }
                }
            }
        }
    }

    protected boolean isValidPosition(World world, BlockPos pos)
    {
        return world.isAirBlock(pos) && (BlocksTFC.isSoil(world.getBlockState(pos.down())) || BlocksTFCF.isSoil(world.getBlockState(pos.down())));
    }
}
