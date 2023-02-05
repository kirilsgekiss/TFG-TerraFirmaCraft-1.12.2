/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.world.classic;

import mcp.MethodsReturnNonnullByDefault;
import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.types.Rock.Type;
import net.dries007.tfc.api.types.RockCategory;
import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.objects.blocks.stone.BlockRockVariant;
import net.dries007.tfc.objects.fluids.FluidsTFC;
import net.dries007.tfc.util.calendar.Month;
import net.dries007.tfc.util.climate.ClimateHelper;
import net.dries007.tfc.world.classic.biomes.TFCBiomes;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataProvider;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;
import net.dries007.tfc.world.classic.genlayers.GenLayerTFC;
import net.dries007.tfc.world.classic.genlayers.datalayers.drainage.GenDrainageLayer;
import net.dries007.tfc.world.classic.genlayers.datalayers.ph.GenPHLayer;
import net.dries007.tfc.world.classic.mapgen.MapGenCavesTFC;
import net.dries007.tfc.world.classic.mapgen.MapGenRavineTFC;
import net.dries007.tfc.world.classic.mapgen.MapGenRiverRavine;
import net.dries007.tfc.world.classic.spawner.WorldEntitySpawnerTFC;
import net.dries007.tfc.world.classic.worldgen.*;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.registries.ForgeRegistry;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static net.dries007.tfc.world.classic.WorldTypeTFC.ROCKLAYER2;
import static net.dries007.tfc.world.classic.WorldTypeTFC.ROCKLAYER3;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ANIMALS;

@SuppressWarnings("WeakerAccess")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ChunkGenTFC implements IChunkGenerator {
    public static final IBlockState STONE = Blocks.STONE.getDefaultState();
    public static final IBlockState AIR = Blocks.AIR.getDefaultState();
    public static final IBlockState SEA_WATER = FluidsTFC.SEA_WATER.get().getBlock().getDefaultState();
    public static final IBlockState WATER = FluidsTFC.FRESH_WATER.get().getBlock().getDefaultState();
    public static final IBlockState HOT_WATER = FluidsTFC.HOT_WATER.get().getBlock().getDefaultState();
    public static final IBlockState LAVA = Blocks.LAVA.getDefaultState();
    public static final IBlockState BEDROCK = Blocks.BEDROCK.getDefaultState();
    /* Layers must be one here - otherwise snow becomes non-replaceable and wrecks the rest of world gen */
    public static final IBlockState SNOW = Blocks.SNOW_LAYER.getDefaultState().withProperty(BlockSnow.LAYERS, 1);
    public static final IBlockState SALT_WATER_ICE = BlocksTFC.SEA_ICE.getDefaultState();
    public static final IBlockState WATER_ICE = Blocks.ICE.getDefaultState();
    private static final float[] parabolicField = new float[25];

    /* This is done here rather than GameRegistry.registerWorldGenerator since we need to control the ordering of them better */
    private static final IWorldGenerator LAVA_FISSURE_GEN = new RarityBasedWorldGen(x -> x.lavaFissureRarity, new WorldGenFissure(true));
    private static final IWorldGenerator WATER_FISSURE_GEN = new RarityBasedWorldGen(x -> x.waterFissureRarity, new WorldGenFissure(false));
    private static final IWorldGenerator SOIL_PITS_GEN = new WorldGenSoilPits();
    private static final IWorldGenerator LARGE_ROCKS_GEN = new RarityBasedWorldGen(x -> x.largeRockRarity, new WorldGenLargeRocks());
    private static final IWorldGenerator TREE_GEN = new WorldGenTrees();
    private static final IWorldGenerator BERRY_BUSH_GEN = new WorldGenBerryBushes();
    private static final IWorldGenerator FRUIT_TREE_GEN = new WorldGenFruitTrees();
    private static final IWorldGenerator WILD_CROPS_GEN = new WorldGenWildCrops();
    private static final IWorldGenerator LOOSE_ROCKS_GEN = new WorldGenLooseRocks();
    private static final IWorldGenerator STALACTITE_GEN = new WorldGenSpikes(true, 300);
    private static final IWorldGenerator STALAGMITE_GEN = new WorldGenSpikes(false, 300);
    private static final IWorldGenerator WATERFALL_GEN = new WorldGenFalls(WATER, 15);
    private static final IWorldGenerator LAVAFALL_GEN = new WorldGenFalls(Blocks.FLOWING_LAVA.getDefaultState(), 5);
    private static final IWorldGenerator SNOW_ICE_GEN = new WorldGenSnowIce();

    public static final float RAINFALL_SAND = 75;
    public static final float RAINFALL_SAND_SANDY_MIX = 125;
    public static final float RAINFALL_SANDY = 200; // Upper thresholds
    public static final float RAINFALL_SILTY = 275; // Lower thresholds
    public static final float RAINFALL_SILT_SILTY_MIX = 350;
    public static final float RAINFALL_SILT = 400;

    public static final float FLORA_DIVERSITY_SAND = 0.15f;
    public static final float FLORA_DIVERSITY_SAND_SANDY_MIX = 0.25f;
    public static final float FLORA_DIVERSITY_SANDY = 0.4f; // Upper thresholds
    public static final float FLORA_DIVERSITY_SILTY = 0.55f; // Lower thresholds
    public static final float FLORA_DIVERSITY_SILT_SILTY_MIX = 0.7f;
    public static final float FLORA_DIVERSITY_SILT = 0.8f;

    public static final float FLORA_DIVERSITY_SAND_SANDY_MEAN = (FLORA_DIVERSITY_SAND + FLORA_DIVERSITY_SAND_SANDY_MIX) / 2f;
    public static final float FLORA_DIVERSITY_SAND_SANDY_RANGE = (FLORA_DIVERSITY_SAND_SANDY_MIX - FLORA_DIVERSITY_SAND) / 2f;

    static {
        for (int x = -2; x <= 2; ++x) {
            for (int y = -2; y <= 2; ++y) {
                parabolicField[x + 2 + (y + 2) * 5] = 10.0F / MathHelper.sqrt(x * x + y * y + 0.2F);
                // Results in the following plot: http://i.imgur.com/rxrui67.png
            }
        }
    }

    public final WorldGenSettings s;
    public final World world;
    private final long seed;
    private final Random rand;
    private final NoiseGeneratorOctaves noiseGen1;
    private final NoiseGeneratorOctaves noiseGen2;
    private final NoiseGeneratorOctaves noiseGen3;
    private final NoiseGeneratorOctaves noiseGen4;
    private final NoiseGeneratorOctaves noiseGen5;
    private final NoiseGeneratorOctaves noiseGen6;
    private final NoiseGeneratorOctaves mobSpawnerNoise;
    private final NoiseGeneratorPerlin noiseGen7; // Rainfall
    private final NoiseGeneratorPerlin noiseGen8; // Flora Density
    private final NoiseGeneratorPerlin noiseGen9; // Flora Diversity
    private final NoiseGeneratorPerlin noiseGen10; // Temperature
    private final GenLayerTFC rocksGenLayer1;
    private final GenLayerTFC rocksGenLayer2;
    private final GenLayerTFC rocksGenLayer3;
    public final GenLayerTFC stabilityGenLayer;
    public final GenLayerTFC phGenLayer;
    public final GenLayerTFC drainageGenLayer;
    /*public final GenLayerTFC regionGenLayer;
    public final GenLayerTFC riverGenLayer;*/
    private final double[] noise1 = new double[425];
    private final double[] noise2 = new double[425];
    private final double[] noise3 = new double[425];
    private final double[] noise4 = new double[256];
    private final double[] noise5 = new double[425];
    private final double[] noise6 = new double[425];
    private final double[] heightMap = new double[425];
    private final Biome[] biomes = new Biome[324];
    public final DataLayer[] stabilityLayer = new DataLayer[256];
    //public final DataLayer[] phLayer = new DataLayer[256];
    public final DataLayer[] drainageLayer = new DataLayer[256];
    private final int[] seaLevelOffsetMap = new int[256];
    private final int[] chunkHeightMap = new int[256];

    private final MapGenBase caveGen;
    private final MapGenBase surfaceRavineGen;
    private final MapGenBase ravineGen;
    private final MapGenBase riverRavineGen;

    private final int seaLevel = 32;
    private final int yOffset = 112;
    private final float rainfallSpread, floraDensitySpread, floraDiversitySpread;
    private int[] rockLayer1 = new int[256];
    private int[] rockLayer2 = new int[256];
    private int[] rockLayer3 = new int[256];
    private float rainfall;
    private float averageTemp;
    /*public int[] regionLayer = new int[256];
    public int[] riverLayer = new int[256];*/

    public ChunkGenTFC(World w, String settingsString) {
        world = w;
        seed = world.getSeed();
        rand = new Random(seed);
        s = WorldGenSettings.fromString(settingsString).build();
        noiseGen1 = new NoiseGeneratorOctaves(rand, 4);
        noiseGen2 = new NoiseGeneratorOctaves(rand, 16);
        noiseGen3 = new NoiseGeneratorOctaves(rand, 8);
        noiseGen4 = new NoiseGeneratorOctaves(rand, 4);
        noiseGen5 = new NoiseGeneratorOctaves(rand, 2);
        noiseGen6 = new NoiseGeneratorOctaves(rand, 1);
        mobSpawnerNoise = new NoiseGeneratorOctaves(rand, 8);

        rocksGenLayer1 = GenLayerTFC.initializeRock(seed + 1, RockCategory.Layer.TOP, s.rockLayerSize);
        rocksGenLayer2 = GenLayerTFC.initializeRock(seed + 2, RockCategory.Layer.MIDDLE, s.rockLayerSize);
        rocksGenLayer3 = GenLayerTFC.initializeRock(seed + 3, RockCategory.Layer.BOTTOM, s.rockLayerSize);

        noiseGen7 = new NoiseGeneratorPerlin(new Random(seed + 4), 4);
        noiseGen8 = new NoiseGeneratorPerlin(new Random(seed + 5), 4);
        noiseGen9 = new NoiseGeneratorPerlin(new Random(seed + 6), 4);
        noiseGen10 = new NoiseGeneratorPerlin(new Random(seed + 7), 4);

        //regionCache = new ChunkDataCache(this, 0);
        //riverCache = new ChunkDataCache(this, 0);

        stabilityGenLayer = GenLayerTFC.initializeStability(seed + 9);
        phGenLayer = GenPHLayer.initializePH(seed + 10);
        drainageGenLayer = GenDrainageLayer.initialize(seed + 11);
		/*regionGenLayer = GenLayerTFC.initializeRegion(seed + 12, DataLayer.REGION_ARRAY);
		riverGenLayer = GenLayerTFC.initializeRiver(seed + 24, DataLayer.RIVER_ARRAY);*/

        caveGen = TerrainGen.getModdedMapGen(new MapGenCavesTFC(stabilityLayer), InitMapGenEvent.EventType.CAVE);
        surfaceRavineGen = new MapGenRavineTFC(s.surfaceRavineRarity, s.surfaceRavineHeight, s.surfaceRavineVariability);
        ravineGen = new MapGenRavineTFC(s.ravineRarity, s.ravineHeight, s.ravineVariability);
        riverRavineGen = new MapGenRiverRavine(s.riverRavineRarity);

        // Load these now, because if config changes, shit will break
        rainfallSpread = (float) ConfigTFC.General.WORLD.rainfallSpreadFactor;
        floraDiversitySpread = (float) ConfigTFC.General.WORLD.floraDiversitySpreadFactor;
        floraDensitySpread = (float) ConfigTFC.General.WORLD.floraDensitySpreadFactor;
        world.setSeaLevel(WorldTypeTFC.SEALEVEL); // Set sea level so squids can spawn
        WorldEntitySpawnerTFC.init(); // Called here so only TFC Worlds are affected
    }

    @Override
    public Chunk generateChunk(int chunkX, int chunkZ) {
        Arrays.fill(noise1, 0);
        Arrays.fill(noise2, 0);
        Arrays.fill(noise3, 0);
        Arrays.fill(noise4, 0);
        Arrays.fill(noise5, 0);
        Arrays.fill(noise6, 0);
        Arrays.fill(seaLevelOffsetMap, 0);
        Arrays.fill(chunkHeightMap, 0);
        Arrays.fill(heightMap, 0);

        rand.setSeed(chunkX * 341873128712L + chunkZ * 132897987541L);
        ChunkPrimer chunkPrimerIn = new ChunkPrimer();
        generateRoughTerrain(chunkX, chunkZ, chunkPrimerIn);

        world.getBiomeProvider().getBiomes(biomes, chunkX * 16 - 1, chunkZ * 16 - 1, 18, 18);

        loadLayerGeneratorData(stabilityGenLayer, stabilityLayer, chunkX * 16, chunkZ * 16, 16, 16);
        //loadLayerGeneratorData(phGenLayer, phLayer, chunkX * 16, chunkZ * 16, 16, 16);
        loadLayerGeneratorData(drainageGenLayer, drainageLayer, chunkX * 16, chunkZ * 16, 16, 16);
        //loadLayerGeneratorData(regionGenLayer, regionLayer, chunkX * 16, chunkZ * 16, 16, 16);
        //loadLayerGeneratorData(riverGenLayer, riverLayer, chunkX * 16, chunkZ * 16, 16, 16);

        rainfall = MathHelper.clamp(250f + 250f * rainfallSpread * (float) noiseGen7.getValue(chunkX * 0.005, chunkZ * 0.005), 0, 500);
        float floraDiversity = MathHelper.clamp(0.5f + 0.5f * floraDiversitySpread * (float) noiseGen9.getValue(chunkX * 0.005, chunkZ * 0.005), 0, 1);
        float floraDensity = MathHelper.clamp((0.3f + 0.2f * rainfall / 500f) + 0.4f * floraDensitySpread * (float) noiseGen8.getValue(chunkX * 0.05, chunkZ * 0.05), 0, 1);

        rockLayer1 = rocksGenLayer1.getInts(chunkX * 16, chunkZ * 16, 16, 16).clone();
        rockLayer2 = rocksGenLayer2.getInts(chunkX * 16, chunkZ * 16, 16, 16).clone();
        rockLayer3 = rocksGenLayer3.getInts(chunkX * 16, chunkZ * 16, 16, 16).clone();

        //regionLayer = regionGenLayer.getInts(chunkX * 16, chunkZ * 16, 16, 16).clone();
        //riverLayer = riverGenLayer.getInts(chunkX * 16, chunkZ * 16, 16, 16).clone();

        final float regionalFactor = 5f * 0.09f * (float) noiseGen10.getValue(chunkX * 0.05, chunkZ * 0.05); // Range -5 <> 5
        averageTemp = ClimateHelper.monthFactor(regionalFactor, Month.AVERAGE_TEMPERATURE_MODIFIER, chunkZ << 4);

        CustomChunkPrimer chunkPrimerOut = new CustomChunkPrimer();
        replaceBlocksForBiomeHigh(chunkX, chunkZ, chunkPrimerIn, chunkPrimerOut);

        if (caveGen instanceof MapGenCavesTFC) {
            // Since this may be replaced by other mods (we give them the option, since 1.12 caves are bad)
            ((MapGenCavesTFC) caveGen).setGenerationData(rainfall, rockLayer1.clone());
        }
        caveGen.generate(world, chunkX, chunkZ, chunkPrimerOut);
        surfaceRavineGen.generate(world, chunkX, chunkZ, chunkPrimerOut);
        ravineGen.generate(world, chunkX, chunkZ, chunkPrimerOut);
        riverRavineGen.generate(world, chunkX, chunkZ, chunkPrimerOut);

        if (ConfigTFC.General.DEBUG.debugWorldGenDanger) {
            for (int x = 0; x < 16; ++x) {
                for (int z = 0; z < 16; ++z) {
                    chunkPrimerOut.setBlockState(x, 240, z, Blocks.STAINED_GLASS.getStateFromMeta(Biome.getIdForBiome(getBiomeOffset(x, z)) & 15));

                    chunkPrimerOut.setBlockState(x, 230, z, Blocks.STAINED_GLASS.getStateFromMeta(rockLayer1[z << 4 | x] & 15));
                    chunkPrimerOut.setBlockState(x, 220, z, Blocks.STAINED_GLASS.getStateFromMeta(rockLayer2[z << 4 | x] & 15));
                    chunkPrimerOut.setBlockState(x, 210, z, Blocks.STAINED_GLASS.getStateFromMeta(rockLayer3[z << 4 | x] & 15));

                    chunkPrimerOut.setBlockState(x, 252, z, Blocks.STAINED_GLASS.getStateFromMeta(stabilityLayer[x << 4 | z].layerID & 15));
                    //chunkPrimerOut.setBlockState(x, 250, z, Blocks.STAINED_GLASS.getStateFromMeta(phLayer[x << 4 | z].layerID & 15));
                    chunkPrimerOut.setBlockState(x, 250, z, Blocks.STAINED_GLASS.getStateFromMeta(drainageLayer[x << 4 | z].layerID & 15));
                    /*chunkPrimerOut.setBlockState(x, 252, z, Blocks.STAINED_GLASS.getStateFromMeta(regionLayer[z << 4 | x] & 15));
                    chunkPrimerOut.setBlockState(x, 252, z, Blocks.STAINED_GLASS.getStateFromMeta(riverLayer[z << 4 | x] & 15));*/
                }
            }
        }

        Chunk chunk = new Chunk(world, chunkPrimerOut, chunkX, chunkZ);

        ChunkDataTFC chunkData = chunk.getCapability(ChunkDataProvider.CHUNK_DATA_CAPABILITY, null);
        if (chunkData == null) throw new IllegalStateException("ChunkData capability is missing.");
        //chunkData.setGenerationData(rockLayer1, rockLayer2, rockLayer3, stabilityLayer, phLayer, drainageLayer, /*regionLayer, riverLayer,*/ seaLevelOffsetMap, rainfall, regionalFactor, averageTemp, floraDensity, floraDiversity);
        chunkData.setGenerationData(rockLayer1, rockLayer2, rockLayer3, stabilityLayer, drainageLayer, /*regionLayer, riverLayer,*/ seaLevelOffsetMap, rainfall, regionalFactor, averageTemp, floraDensity, floraDiversity);

        byte[] biomeIds = chunk.getBiomeArray();
        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                biomeIds[z << 4 | x] = (byte) Biome.getIdForBiome(getBiomeOffset(x, z));
            }
        }

        chunk.setHeightMap(chunkHeightMap);
        chunk.generateSkylightMap();
        return chunk;
    }

    @Override
    public void populate(int chunkX, int chunkZ) {
        ForgeEventFactory.onChunkPopulate(true, this, world, rand, chunkX, chunkZ, false);
        BlockFalling.fallInstantly = true;
        final int worldX = chunkX << 4;
        final int worldZ = chunkZ << 4;
        BlockPos blockpos = new BlockPos(worldX, 0, worldZ);
        final Biome biome = world.getBiome(blockpos.add(16, 0, 16));
        rand.setSeed(world.getSeed());
        rand.setSeed((long) chunkX * (rand.nextLong() / 2L * 2L + 1L) + (long) chunkZ * (rand.nextLong() / 2L * 2L + 1L) ^ world.getSeed());

        // First, do all terrain related features
        SOIL_PITS_GEN.generate(rand, chunkX, chunkZ, world, this, world.getChunkProvider());
        LAVA_FISSURE_GEN.generate(rand, chunkX, chunkZ, world, this, world.getChunkProvider());
        WATER_FISSURE_GEN.generate(rand, chunkX, chunkZ, world, this, world.getChunkProvider());
        LARGE_ROCKS_GEN.generate(rand, chunkX, chunkZ, world, this, world.getChunkProvider());
        // todo: cave decorator

        // Next, larger plant type features
        TREE_GEN.generate(rand, chunkX, chunkZ, world, this, world.getChunkProvider());
        BERRY_BUSH_GEN.generate(rand, chunkX, chunkZ, world, this, world.getChunkProvider());
        FRUIT_TREE_GEN.generate(rand, chunkX, chunkZ, world, this, world.getChunkProvider());

        // Calls through biome decorator which includes all small plants
        biome.decorate(world, rand, blockpos);

        // Finally
        LOOSE_ROCKS_GEN.generate(rand, chunkX, chunkZ, world, this, world.getChunkProvider());
        WATERFALL_GEN.generate(rand, chunkX, chunkZ, world, this, world.getChunkProvider());
        LAVAFALL_GEN.generate(rand, chunkX, chunkZ, world, this, world.getChunkProvider());
        STALACTITE_GEN.generate(rand, chunkX, chunkZ, world, this, world.getChunkProvider());
        STALAGMITE_GEN.generate(rand, chunkX, chunkZ, world, this, world.getChunkProvider());
        SNOW_ICE_GEN.generate(rand, chunkX, chunkZ, world, this, world.getChunkProvider());

        if (TerrainGen.populate(this, world, rand, chunkX, chunkZ, false, ANIMALS)) {
            WorldEntitySpawnerTFC.performWorldGenSpawning(world, biome, worldX + 8, worldZ + 8, 16, 16, rand);
        }

        // To minimize the effects of this change, i'm putting this here, in the end of chunk generation
        WILD_CROPS_GEN.generate(rand, chunkX, chunkZ, world, this, world.getChunkProvider());

        ForgeEventFactory.onChunkPopulate(false, this, world, rand, chunkX, chunkZ, false);
        BlockFalling.fallInstantly = false;
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false; //todo
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        // This is a temporary measure for making 1.12 closer to playable
        return world.getBiome(pos).getSpawnableList(creatureType);
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null; //todo
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {
        //todo
    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false; //todo
    }

    @SuppressWarnings("SameParameterValue")
    private void loadLayerGeneratorData(GenLayerTFC gen, DataLayer[] layers, int x, int y, int width, int height) {
        IntCache.resetIntCache();
        int[] ints = gen.getInts(x, y, width, height);
        for (int i = 0; i < width * height; ++i) {
            layers[i] = DataLayer.get(ints[i]);
        }
    }

    @SuppressWarnings("PointlessArithmeticExpression")
    private void generateRoughTerrain(int chunkX, int chunkZ, ChunkPrimer primer) {
        world.getBiomeProvider().getBiomesForGeneration(biomes, chunkX * 4 - 2, chunkZ * 4 - 2, 10, 10);
        generateHeightMap(chunkX * 4, chunkZ * 4);

        for (int x = 0; x < 4; ++x) {
            for (int z = 0; z < 4; ++z) {
                for (int y = 0; y < 16; ++y) {
                    double noiseDL = heightMap[((x + 0) * 5 + z + 0) * 17 + y];
                    double noiseUL = heightMap[((x + 0) * 5 + z + 1) * 17 + y];
                    double noiseDR = heightMap[((x + 1) * 5 + z + 0) * 17 + y];
                    double noiseUR = heightMap[((x + 1) * 5 + z + 1) * 17 + y];
                    final double noiseDLA = (heightMap[((x + 0) * 5 + z + 0) * 17 + y + 1] - noiseDL) * 0.125D;
                    final double noiseULA = (heightMap[((x + 0) * 5 + z + 1) * 17 + y + 1] - noiseUL) * 0.125D;
                    final double noiseDRA = (heightMap[((x + 1) * 5 + z + 0) * 17 + y + 1] - noiseDR) * 0.125D;
                    final double noiseURA = (heightMap[((x + 1) * 5 + z + 1) * 17 + y + 1] - noiseUR) * 0.125D;

                    for (int yy = 0; yy < 8; ++yy) {
                        double var34 = noiseDL;
                        double var36 = noiseUL;
                        final double var38 = (noiseDR - noiseDL) * 0.25D;
                        final double var40 = (noiseUR - noiseUL) * 0.25D;

                        for (int xx = 0; xx < 4; ++xx) {
                            final double var49 = (var36 - var34) * 0.25D;
                            double var47 = var34 - var49;

                            for (int zz = 0; zz < 4; ++zz) {
                                if ((var47 += var49) > 0.0D)
                                    primer.setBlockState(x * 4 + xx, y * 8 + yy, z * 4 + zz, STONE);
                                else if (y * 8 + yy < seaLevel)
                                    primer.setBlockState(x * 4 + xx, y * 8 + yy, z * 4 + zz, SEA_WATER);
                                else primer.setBlockState(x * 4 + xx, y * 8 + yy, z * 4 + zz, AIR);
                            }
                            var34 += var38;
                            var36 += var40;
                        }
                        noiseDL += noiseDLA;
                        noiseUL += noiseULA;
                        noiseDR += noiseDRA;
                        noiseUR += noiseURA;
                    }
                }
            }
        }
    }

    private void generateHeightMap(int xPos, int zPos) {
        noiseGen6.generateNoiseOctaves(noise6, xPos, zPos, 5, 5, 200.0D, 200.0D, 0.5D);
        noiseGen3.generateNoiseOctaves(noise3, xPos, 0, zPos, 5, 17, 5, 12.5, 6.25, 12.5);
        noiseGen1.generateNoiseOctaves(noise1, xPos, 0, zPos, 5, 17, 5, 1000D, 1000D, 1000D);
        noiseGen2.generateNoiseOctaves(noise2, xPos, 0, zPos, 5, 17, 5, 1000D, 1000D, 1000D);

        int i = 0;
        int j = 0;

        for (int x = 0; x < 5; ++x) {
            for (int z = 0; z < 5; ++z) {
                float variationBlended = 0.0F;
                float rootBlended = 0.0F;
                float totalBlendedHeight = 0.0F;
                Biome baseBiome = biomes[x + 2 + (z + 2) * 10];

                for (int xR = -2; xR <= 2; ++xR) {
                    for (int zR = -2; zR <= 2; ++zR) {
                        Biome blendBiome = biomes[x + xR + 2 + (z + zR + 2) * 10];
                        float blendedHeight = parabolicField[xR + 2 + (zR + 2) * 5] / 2.0F;
                        if (blendBiome.getBaseHeight() > baseBiome.getBaseHeight())
                            blendedHeight *= 0.5F;

                        variationBlended += blendBiome.getHeightVariation() * blendedHeight;
                        rootBlended += blendBiome.getBaseHeight() * blendedHeight;
                        totalBlendedHeight += blendedHeight;
                    }
                }

                variationBlended /= totalBlendedHeight;
                rootBlended /= totalBlendedHeight;
                variationBlended = variationBlended * 0.9F + 0.1F;
                rootBlended = (rootBlended * 4.0F - 1.0F) / 8.0F;

                double scaledNoise6Value = noise6[j++] / 8000.0D;

                if (scaledNoise6Value < 0.0D)
                    scaledNoise6Value = -scaledNoise6Value * 0.3D; //If negative, make positive and shrink by a third?

                scaledNoise6Value = scaledNoise6Value * 3.0D - 2.0D;

                if (scaledNoise6Value < 0.0D) // Only true when noise6[index2] is between -17,777 and 0, scaledNoise6Value will be at maximum -2
                {
                    scaledNoise6Value /= 2.0D; // Results in values between 0 and -1
                    if (scaledNoise6Value < -1.0D) //Error Checking
                        scaledNoise6Value = -1.0D;
                    scaledNoise6Value /= 1.4D * 2.0D; // Results in values between 0 and -0.357143
                } else {
                    if (scaledNoise6Value > 1.0D)
                        scaledNoise6Value = 1.0D;
                    scaledNoise6Value /= 8.0D; // Results in values between 0 and 0.125
                }

                for (int y = 0; y < 17; ++y) {
                    double rootBlendedCopy = rootBlended;
                    rootBlendedCopy += scaledNoise6Value * 0.2D;
                    rootBlendedCopy = rootBlendedCopy * 17 / 16.0D;
                    double var28 = 17 / 2.0D + rootBlendedCopy * 4.0D;
                    double output;
                    double var32 = (y - var28) * 12.0D * 256.0D / 256.0D / (2.70 + variationBlended);

                    if (var32 < 0.0D)
                        var32 *= 4.0D;

                    double var34 = noise1[i] / 512.0D;
                    double var36 = noise2[i] / 512.0D;
                    double var38 = (noise3[i] / 10.0D + 1.0D) / 2.0D;

                    if (var38 < 0.0D)
                        output = var34;
                    else if (var38 > 1.0D)
                        output = var36;
                    else
                        output = var34 + (var36 - var34) * var38;

                    output -= var32;
                    if (y > 17 - 4) {
                        double var40 = (y - (17 - 4)) / 3.0F;
                        output = output * (1.0D - var40) + -10.0D * var40;
                    }

                    heightMap[i++] = output;
                }
            }
        }
    }

    private Biome getBiomeOffset(int x, int z) {
        return biomes[(z + 1) * 18 + (x + 1)]; //todo: check, was (z + 1) + (x + 1) * 18
    }

    private void replaceBlocksForBiomeHigh(int chunkX, int chunkZ, ChunkPrimer inp, CustomChunkPrimer outp) {
        double var6 = 0.03125D;
        noiseGen4.generateNoiseOctaves(noise4, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, var6 * 4.0D, var6, var6 * 4.0D);
        boolean[] cliffMap = new boolean[256];
        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                int colIndex = z << 4 | x;
                Biome biome = getBiomeOffset(x, z);

                Rock rock1 = ((ForgeRegistry<Rock>) TFCRegistries.ROCKS).getValue(rockLayer1[colIndex]);
                Rock rock2 = ((ForgeRegistry<Rock>) TFCRegistries.ROCKS).getValue(rockLayer2[colIndex]);
                Rock rock3 = ((ForgeRegistry<Rock>) TFCRegistries.ROCKS).getValue(rockLayer3[colIndex]);

                DataLayer drainage = drainageLayer[colIndex];
                //DataLayer ph = phLayer[colIndex];
                DataLayer ph = drainageLayer[colIndex];
                DataLayer stability = stabilityLayer[colIndex];
                int noise = (int) (noise4[colIndex] / 3.0D + 6.0D);
                int smooth = -1;

                float floraDiversity = MathHelper.clamp(0.5f + 0.5f * floraDiversitySpread * (float) noiseGen9.getValue(chunkX * 0.005, chunkZ * 0.005), 0, 1);
                float floraDensity = MathHelper.clamp((0.3f + 0.2f * rainfall / 500f) + 0.4f * floraDensitySpread * (float) noiseGen8.getValue(chunkX * 0.05, chunkZ * 0.05), 0, 1);

                IBlockState surfaceBlock;
                IBlockState subSurfaceBlock;
                if (ConfigTFC.FloraeGeneral.WORLD.enableAllBlockTypes && ConfigTFC.FloraeGeneral.WORLD.enableSoilPits) {
                    // Surface Block
                    if ((biome == TFCBiomes.SWAMPLAND || biome == TFCBiomes.MARSH) && ph.valueInt >= 4 && floraDiversity >= 0.25f && floraDensity >= 0.25f) {
                        surfaceBlock = BlocksTFC.PEAT_GRASS.getDefaultState();
                        subSurfaceBlock = BlocksTFC.PEAT.getDefaultState();
                    } else if (biome == TFCBiomes.MOUNTAIN_RANGE || biome == TFCBiomes.MOUNTAIN_RANGE_EDGE || biome == TFCBiomes.CRAG || biome == TFCBiomes.MOUNTAINS || biome == TFCBiomes.FAULT_LINE) {
                        double g1 = +rand.nextGaussian();
                        double g2 = rand.nextGaussian();
                        //int chance = (int) MathHelper.abs((float) rand.nextGaussian()) * world.getHeight();
                        //int y = (int) MathHelper.abs((float) rand.nextGaussian()) * noise;
                        if (floraDiversity < FLORA_DIVERSITY_SAND_SANDY_MIX + 0.05f * rand.nextGaussian()) {
                            if (floraDensity + 0.05f * rand.nextGaussian() >= 0.2f) {
                                if (g2 >= 0.4f)
                                    surfaceBlock = BlockRockVariant.get(rock1, Type.SPARSE_SANDY_LOAM_GRASS)
                                            .getDefaultState();
                                else if (g2 > 0.1f && g2 < 0.25f)
                                    surfaceBlock = BlockRockVariant.get(rock1, Type.COARSE_SANDY_LOAM)
                                            .getDefaultState();
                                else
                                    surfaceBlock = BlockRockVariant.get(rock1, Type.SANDY_LOAM_GRASS).getDefaultState();
                            } else {
                                if (stability.valueInt >= 1 + g1) {
                                    if (g2 >= 0.4f)
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.SPARSE_SANDY_LOAM_GRASS)
                                                .getDefaultState();
                                    else
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.COARSE_SANDY_LOAM)
                                                .getDefaultState();
                                } else {
                                    if (g2 >= 0.25f)
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.COARSE_SANDY_LOAM)
                                                .getDefaultState();
                                    else if (g2 > 0.1f && g2 < 0.25f)
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.GRAVEL).getDefaultState();
                                    else
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.COBBLE).getDefaultState();
                                }
                            }
                        } else if (floraDiversity > FLORA_DIVERSITY_SAND_SANDY_MIX + 0.05f * rand.nextGaussian() && floraDiversity < FLORA_DIVERSITY_SANDY + 0.05f * rand.nextGaussian()) {
                            if (floraDensity + 0.05f * rand.nextGaussian() >= 0.2f) {
                                if (g2 >= 0.4f)
                                    surfaceBlock = BlockRockVariant.get(rock1, Type.SPARSE_LOAMY_SAND_GRASS)
                                            .getDefaultState();
                                else if (g2 > 0.1f && g2 < 0.25f)
                                    surfaceBlock = BlockRockVariant.get(rock1, Type.COARSE_LOAMY_SAND)
                                            .getDefaultState();
                                else
                                    surfaceBlock = BlockRockVariant.get(rock1, Type.LOAMY_SAND_GRASS).getDefaultState();
                            } else {
                                if (stability.valueInt >= 1 + g1) {
                                    if (g2 >= 0.4f)
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.SPARSE_LOAMY_SAND_GRASS)
                                                .getDefaultState();
                                    else
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.COARSE_LOAMY_SAND)
                                                .getDefaultState();
                                } else {
                                    if (g2 >= 0.25f)
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.COARSE_LOAMY_SAND)
                                                .getDefaultState();
                                    else if (g2 > 0.1f && g2 < 0.25f)
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.GRAVEL).getDefaultState();
                                    else
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.COBBLE).getDefaultState();
                                }
                            }
                        } else if (floraDiversity > FLORA_DIVERSITY_SANDY + 0.05f * rand.nextGaussian() && floraDiversity < FLORA_DIVERSITY_SILTY + 0.05f * rand.nextGaussian()) {
                            if (floraDensity + 0.05f * rand.nextGaussian() >= 0.2f) {
                                if (g2 >= 0.4f)
                                    surfaceBlock = BlockRockVariant.get(rock1, Type.SPARSE_LOAM_GRASS)
                                            .getDefaultState();
                                else if (g2 > 0.1f && g2 < 0.25f)
                                    surfaceBlock = BlockRockVariant.get(rock1, Type.COARSE_LOAM).getDefaultState();
                                else
                                    surfaceBlock = BlockRockVariant.get(rock1, Type.LOAM_GRASS).getDefaultState();
                            } else {
                                if (stability.valueInt >= 1 + g1) {
                                    if (g2 >= 0.4f)
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.SPARSE_LOAM_GRASS)
                                                .getDefaultState();
                                    else
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.COARSE_LOAM).getDefaultState();
                                } else {
                                    if (g2 >= 0.25f)
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.COARSE_LOAM).getDefaultState();
                                    else if (g2 > 0.1f && g2 < 0.25f)
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.GRAVEL).getDefaultState();
                                    else
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.COBBLE).getDefaultState();
                                }
                            }
                        } else if (floraDiversity > FLORA_DIVERSITY_SILTY + 0.05f * rand.nextGaussian() && floraDiversity < FLORA_DIVERSITY_SILT + 0.05f * rand.nextGaussian()) {
                            if (floraDensity + 0.05f * rand.nextGaussian() >= 0.2f) {
                                if (g2 >= 0.4f)
                                    surfaceBlock = BlockRockVariant.get(rock1, Type.SPARSE_SILT_LOAM_GRASS)
                                            .getDefaultState();
                                else if (g2 > 0.1f && g2 < 0.25f)
                                    surfaceBlock = BlockRockVariant.get(rock1, Type.COARSE_SILT_LOAM).getDefaultState();
                                else
                                    surfaceBlock = BlockRockVariant.get(rock1, Type.SILT_LOAM_GRASS).getDefaultState();
                            } else {
                                if (stability.valueInt >= 1 + g1) {
                                    if (g2 >= 0.4f)
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.SPARSE_SILT_LOAM_GRASS)
                                                .getDefaultState();
                                    else
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.COARSE_SILT_LOAM)
                                                .getDefaultState();
                                } else {
                                    if (g2 >= 0.25f)
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.COARSE_SILT_LOAM)
                                                .getDefaultState();
                                    else if (g2 > 0.1f && g2 < 0.25f)
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.GRAVEL).getDefaultState();
                                    else
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.COBBLE).getDefaultState();
                                }
                            }
                        } else if (floraDiversity > FLORA_DIVERSITY_SILTY + 0.05f * rand.nextGaussian()) {
                            if (floraDensity + 0.05f * rand.nextGaussian() >= 0.2f) {
                                if (g2 >= 0.4f)
                                    surfaceBlock = BlockRockVariant.get(rock1, Type.SPARSE_SILT_GRASS)
                                            .getDefaultState();
                                else if (g2 > 0.1f && g2 < 0.25f)
                                    surfaceBlock = BlockRockVariant.get(rock1, Type.COARSE_SILT).getDefaultState();
                                else
                                    surfaceBlock = BlockRockVariant.get(rock1, Type.SILT_GRASS).getDefaultState();
                            } else {
                                if (stability.valueInt >= 1 + g1) {
                                    if (g2 >= 0.4f)
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.SPARSE_SILT_GRASS)
                                                .getDefaultState();
                                    else
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.COARSE_SILT).getDefaultState();
                                } else {
                                    if (g2 >= 0.25f)
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.COARSE_SILT).getDefaultState();
                                    else if (g2 > 0.1f && g2 < 0.25f)
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.GRAVEL).getDefaultState();
                                    else
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.COBBLE).getDefaultState();
                                }
                            }
                        } else {
                            if (floraDensity + 0.05f * rand.nextGaussian() >= 0.2f) {
                                if (g2 >= 0.4f)
                                    surfaceBlock = BlockRockVariant.get(rock1, Type.SPARSE_GRASS).getDefaultState();
                                else if (g2 > 0.1f && g2 < 0.25f)
                                    surfaceBlock = BlockRockVariant.get(rock1, Type.COARSE_DIRT).getDefaultState();
                                else
                                    surfaceBlock = BlockRockVariant.get(rock1, Type.GRASS).getDefaultState();
                            } else {
                                if (stability.valueInt >= 1 + g1) {
                                    if (g2 >= 0.4f)
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.SPARSE_GRASS).getDefaultState();
                                    else
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.COARSE_DIRT).getDefaultState();
                                } else {
                                    if (g2 >= 0.25f)
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.COARSE_DIRT).getDefaultState();
                                    else if (g2 > 0.1f && g2 < 0.25f)
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.GRAVEL).getDefaultState();
                                    else
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.COBBLE).getDefaultState();
                                }
                            }
                        }
                    } else {
                        if (floraDiversity < FLORA_DIVERSITY_SAND_SANDY_MIX + 0.05f * rand.nextGaussian()) {
                            if (rainfall + 3.3f * rand.nextGaussian() >= 150f)
                                surfaceBlock = BlockRockVariant.get(rock1, Type.SANDY_LOAM_GRASS).getDefaultState();
                            else {
                                double p = floraDensity + 0.05f * rand.nextGaussian();
                                if (p < 0.25f && rainfall + 4 * rand.nextGaussian() <= 115f) {
                                    double g = rand.nextGaussian();
                                    if (g > 0.5f)
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.GRAVEL).getDefaultState();
                                    else
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.COARSE_SANDY_LOAM)
                                                .getDefaultState();
                                } else {
                                    surfaceBlock = BlockRockVariant.get(rock1, Type.DRY_SANDY_LOAM_GRASS)
                                            .getDefaultState();
                                }
                            }
                        } else if (floraDiversity > FLORA_DIVERSITY_SAND_SANDY_MIX + 0.05f * rand.nextGaussian() && floraDiversity < FLORA_DIVERSITY_SANDY + 0.05f * rand.nextGaussian()) {
                            if (rainfall + 3.3f * rand.nextGaussian() >= 150f)
                                surfaceBlock = BlockRockVariant.get(rock1, Type.LOAMY_SAND_GRASS).getDefaultState();
                            else {
                                double p = floraDensity + 0.05f * rand.nextGaussian();
                                if (p < 0.25f && rainfall + 4 * rand.nextGaussian() <= 115f) {
                                    double g = rand.nextGaussian();
                                    if (g > 0.5f)
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.GRAVEL).getDefaultState();
                                    else
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.COARSE_LOAMY_SAND)
                                                .getDefaultState();
                                } else
                                    surfaceBlock = BlockRockVariant.get(rock1, Type.DRY_LOAMY_SAND_GRASS)
                                            .getDefaultState();
                            }
                        } else if (floraDiversity > FLORA_DIVERSITY_SANDY + 0.05f * rand.nextGaussian() && floraDiversity < FLORA_DIVERSITY_SILTY + 0.05f * rand.nextGaussian()) {
                            if (rainfall + 3.3f * rand.nextGaussian() >= 150f)
                                surfaceBlock = BlockRockVariant.get(rock1, Type.LOAM_GRASS).getDefaultState();
                            else {
                                double p = floraDensity + 0.05f * rand.nextGaussian();
                                if (p < 0.25f && rainfall + 4 * rand.nextGaussian() <= 115f) {
                                    double g = rand.nextGaussian();
                                    if (g > 0.5f)
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.GRAVEL).getDefaultState();
                                    else
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.COARSE_LOAM).getDefaultState();
                                } else
                                    surfaceBlock = BlockRockVariant.get(rock1, Type.DRY_LOAM_GRASS).getDefaultState();
                            }
                        } else if (floraDiversity > FLORA_DIVERSITY_SILTY + 0.05f * rand.nextGaussian() && floraDiversity < FLORA_DIVERSITY_SILT + 0.05f * rand.nextGaussian()) {
                            if (rainfall + 3.3f * rand.nextGaussian() >= 150f)
                                surfaceBlock = BlockRockVariant.get(rock1, Type.SILT_LOAM_GRASS).getDefaultState();
                            else {
                                double p = floraDensity + 0.05f * rand.nextGaussian();
                                if (p < 0.25f && rainfall + 4 * rand.nextGaussian() <= 115f) {
                                    double g = rand.nextGaussian();
                                    if (g > 0.5f)
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.GRAVEL).getDefaultState();
                                    else
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.COARSE_SILT_LOAM)
                                                .getDefaultState();
                                } else
                                    surfaceBlock = BlockRockVariant.get(rock1, Type.DRY_SILT_LOAM_GRASS)
                                            .getDefaultState();
                            }
                        } else if (floraDiversity > FLORA_DIVERSITY_SILTY + 0.05f * rand.nextGaussian()) {
                            if (rainfall + 3.3f * rand.nextGaussian() >= 150f)
                                surfaceBlock = BlockRockVariant.get(rock1, Type.SILT_GRASS).getDefaultState();
                            else {
                                double p = floraDensity + 0.05f * rand.nextGaussian();
                                if (p < 0.25f && rainfall + 4 * rand.nextGaussian() <= 115f) {
                                    double g = rand.nextGaussian();
                                    if (g > 0.5f)
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.GRAVEL).getDefaultState();
                                    else
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.COARSE_SILT).getDefaultState();
                                } else
                                    surfaceBlock = BlockRockVariant.get(rock1, Type.DRY_SILT_GRASS).getDefaultState();
                            }
                        } else {
                            if (rainfall + 3.3f * rand.nextGaussian() >= 150f) {
                                surfaceBlock = BlockRockVariant.get(rock1, Type.GRASS).getDefaultState();
                            } else {
                                double p = floraDensity + 0.05f * rand.nextGaussian();
                                if (p < 0.25f && rainfall + 4 * rand.nextGaussian() <= 115f) {
                                    double g = rand.nextGaussian();
                                    if (g > 0.5f)
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.GRAVEL).getDefaultState();
                                    else
                                        surfaceBlock = BlockRockVariant.get(rock1, Type.COARSE_DIRT).getDefaultState();
                                } else {
                                    surfaceBlock = BlockRockVariant.get(rock1, Type.DRY_GRASS).getDefaultState();
                                }
                            }
                        }
                    }

                    // Subsurface Block
                    if (floraDiversity < FLORA_DIVERSITY_SAND_SANDY_MIX + 0.05f * rand.nextGaussian()) {
                        subSurfaceBlock = BlockRockVariant.get(rock1, Type.SANDY_LOAM).getDefaultState();
                    } else if (floraDiversity > FLORA_DIVERSITY_SAND_SANDY_MIX + 0.05f * rand.nextGaussian() && floraDiversity < FLORA_DIVERSITY_SANDY + 0.05f * rand.nextGaussian()) {
                        subSurfaceBlock = BlockRockVariant.get(rock1, Type.LOAMY_SAND).getDefaultState();
                    } else if (floraDiversity > FLORA_DIVERSITY_SANDY + 0.05f * rand.nextGaussian() && floraDiversity < FLORA_DIVERSITY_SILTY + 0.05f * rand.nextGaussian()) {
                        subSurfaceBlock = BlockRockVariant.get(rock1, Type.LOAM).getDefaultState();
                    } else if (floraDiversity > FLORA_DIVERSITY_SILTY + 0.05f * rand.nextGaussian() && floraDiversity < FLORA_DIVERSITY_SILT + 0.05f * rand.nextGaussian()) {
                        subSurfaceBlock = BlockRockVariant.get(rock1, Type.SILT_LOAM).getDefaultState();
                    } else if (floraDiversity > FLORA_DIVERSITY_SILTY + 0.05f * rand.nextGaussian()) {
                        subSurfaceBlock = BlockRockVariant.get(rock1, Type.SILT).getDefaultState();
                    } else {
                        subSurfaceBlock = BlockRockVariant.get(rock1, Type.DIRT).getDefaultState();
                    }
                } else {
                    surfaceBlock = BlockRockVariant
                            .get(rock1, rainfall + 4.3f * rand.nextGaussian() >= 150f ? Type.GRASS : Type.DRY_GRASS)
                            .getDefaultState();
                    subSurfaceBlock = BlockRockVariant.get(rock1, Type.DIRT).getDefaultState();
                }

                if (TFCBiomes.isBeachBiome(getBiomeOffset(x - 1, z)) || TFCBiomes.isBeachBiome(getBiomeOffset(x + 1, z)) || TFCBiomes.isBeachBiome(getBiomeOffset(x, z + 1)) || TFCBiomes.isBeachBiome(getBiomeOffset(x, z - 1))) {
                    if (!TFCBiomes.isBeachBiome(getBiomeOffset(x, z))) cliffMap[colIndex] = true;
                }

                //Used to make better rivers
                int nonRiverTiles = 0;
                int nonBeachTiles = 0;
                for (int a = x - 1; a <= x + 1; a++) {
                    for (int b = z - 1; b <= z + 1; b++) {
                        Biome BiomeAtOffset = getBiomeOffset(a, b);
                        if (!TFCBiomes.isRiverBiome(BiomeAtOffset)) {
                            nonRiverTiles++;
                        }
                        if (!TFCBiomes.isBeachBiome(BiomeAtOffset) && !TFCBiomes.isOceanicBiome(BiomeAtOffset) && BiomeAtOffset != TFCBiomes.DEEP_OCEAN && BiomeAtOffset != TFCBiomes.OCEAN) {
                            nonBeachTiles++;
                        }
                    }
                }

                int highestStone = 0;

                for (int y = 255 - yOffset; y >= 0; y--) {
                    /*
                     * HIGH PART (yOffset is used)
                     */
                    if (outp.isEmpty(x, y + yOffset, z)) {
                        outp.setBlockState(x, y + yOffset, z, inp.getBlockState(x, y, z));
                        if (y + 1 < yOffset && outp.getBlockState(x, y + yOffset, z) == AIR/* no need to check again && BlocksTFC.isSoilOrGravel(outp.getBlockState(x, y + yOffset + 1, z))*/) {
                            for (int upCount = 1; BlocksTFC.isSoilOrGravel(outp.getBlockState(x, y + yOffset + upCount, z)); upCount++) {
                                outp.setBlockState(x, y + yOffset + upCount, z, AIR);
                            }
                        }
                    }

                    if (outp.getBlockState(x, y + yOffset, z) == STONE) {
                        highestStone = Math.max(highestStone, y);
                    }

                    int highestBeachTheoretical = (highestStone - seaLevel) / 4 + seaLevel;
                    int beachCliffHeight = nonBeachTiles > 0 ? (int) ((highestStone - highestBeachTheoretical) * (nonBeachTiles) / 6.0 + highestBeachTheoretical) : highestBeachTheoretical;

                    //Redo cliffs
                    if (TFCBiomes.isBeachBiome(biome) && y > seaLevel && outp.getBlockState(x, y + yOffset, z) != AIR && y >= beachCliffHeight) {
                        inp.setBlockState(x, y, z, AIR);
                        outp.setBlockState(x, y + yOffset, z, AIR);
                    }
                    //Ensure rivers can't get blocked
                    if (TFCBiomes.isRiverBiome(biome) && y >= seaLevel - 2 && outp.getBlockState(x, y + yOffset, z) != AIR) {

                        if (nonRiverTiles > 0) {
                            if (y >= seaLevel - 1) {
                                inp.setBlockState(x, y, z, y >= seaLevel ? AIR : SEA_WATER);
                                outp.setBlockState(x, y + yOffset, z, y >= seaLevel ? AIR : SEA_WATER);
                            }
                        } else {
                            inp.setBlockState(x, y, z, y >= seaLevel ? AIR : SEA_WATER);
                            outp.setBlockState(x, y + yOffset, z, y >= seaLevel ? AIR : SEA_WATER);
                        }


                        //outp.setBlockState(x, y + yOffset, z, y >= seaLevel ? AIR : SALT_WATER);
                    } else if (!TFCBiomes.isRiverBiome(biome) && nonRiverTiles < 9 && outp.getBlockState(x, y + yOffset, z) == STONE && ((y >= ((highestStone - seaLevel) / (10 - nonRiverTiles) + seaLevel)) || (nonRiverTiles <= 5 && y >= seaLevel))) {
                        inp.setBlockState(x, y, z, y >= seaLevel ? AIR : SEA_WATER);
                        outp.setBlockState(x, y + yOffset, z, y >= seaLevel ? AIR : SEA_WATER);
                    }

                    if (outp.getBlockState(x, y + yOffset, z) == STONE) {
                        if (seaLevelOffsetMap[colIndex] == 0 && y - seaLevel >= 0)
                            seaLevelOffsetMap[colIndex] = y - seaLevel;

                        if (chunkHeightMap[colIndex] == 0)
                            chunkHeightMap[colIndex] = y + yOffset;

                        if (y + yOffset <= ROCKLAYER3 + seaLevelOffsetMap[colIndex])
                            outp.setBlockState(x, y + yOffset, z,
                                    BlockRockVariant.get(rock3, Type.RAW).getDefaultState());
                        else if (y + yOffset <= ROCKLAYER2 + seaLevelOffsetMap[colIndex])
                            outp.setBlockState(x, y + yOffset, z,
                                    BlockRockVariant.get(rock2, Type.RAW).getDefaultState());
                        else
                            outp.setBlockState(x, y + yOffset, z,
                                    BlockRockVariant.get(rock1, Type.RAW).getDefaultState());

                        // Deserts / dry areas
                        if (rainfall < +1.3 * rand.nextGaussian() + 75f) {
                            subSurfaceBlock = surfaceBlock = BlockRockVariant.get(rock1, Type.RAW).getVariant(Type.SAND)
                                    .getDefaultState();
                        }

                        if (biome == TFCBiomes.BEACH || biome == TFCBiomes.OCEAN || biome == TFCBiomes.DEEP_OCEAN || biome == TFCBiomes.SHORE) {
                            subSurfaceBlock = surfaceBlock = BlockRockVariant.get(rock1, Type.SAND).getDefaultState();
                        } else if (biome == TFCBiomes.GRAVEL_BEACH || biome == TFCBiomes.ESTUARY || biome == TFCBiomes.RIVER || biome == TFCBiomes.RIVERBANK || biome == TFCBiomes.LAKESHORE /* || biome == BiomesTFC.RIVER_SOURCE*/) {
                            subSurfaceBlock = surfaceBlock = BlockRockVariant.get(rock1, Type.GRAVEL).getDefaultState();
                        }

                        if (smooth == -1) {
                            //The following makes dirt behave nicer and more smoothly, instead of forming sharp cliffs.
                            int arrayIndexx = x > 0 ? x - 1 + (z * 16) : -1;
                            int arrayIndexX = x < 15 ? x + 1 + (z * 16) : -1;
                            int arrayIndexz = z > 0 ? x + ((z - 1) * 16) : -1;
                            int arrayIndexZ = z < 15 ? x + ((z + 1) * 16) : -1;
                            for (int counter = 1; counter < noise / 3; counter++) {
                                if (arrayIndexx >= 0 && seaLevelOffsetMap[colIndex] - (3 * counter) > seaLevelOffsetMap[arrayIndexx] &&
                                        arrayIndexX >= 0 && seaLevelOffsetMap[colIndex] - (3 * counter) > seaLevelOffsetMap[arrayIndexX] &&
                                        arrayIndexz >= 0 && seaLevelOffsetMap[colIndex] - (3 * counter) > seaLevelOffsetMap[arrayIndexz] &&
                                        arrayIndexZ >= 0 && seaLevelOffsetMap[colIndex] - (3 * counter) > seaLevelOffsetMap[arrayIndexZ]) {
                                    seaLevelOffsetMap[colIndex]--;
                                    noise--;
                                    y--;
                                }
                            }
                            smooth = (int) (noise * (1d - Math.max(Math.min((y - 16) / 80d, 1), 0)));

                            // Set soil below water
                            for (int c = 1; c < 3; c++) {
                                if (yOffset + y + c > 256) continue;

                                IBlockState current = outp.getBlockState(x, yOffset + y + c, z);
                                if (current != surfaceBlock && current != subSurfaceBlock && !BlocksTFC.isWater(current)) {
                                    outp.setBlockState(x, yOffset + y + c, z, AIR);
                                    if (yOffset + y + c + 1 > 256) continue;
                                    if (outp.getBlockState(x, yOffset + y + c + 1, z) == SEA_WATER)
                                        outp.setBlockState(x, yOffset + y + c, z, subSurfaceBlock);
                                }
                            }

                            // Determine the soil depth based on world y
                            int dirtH = Math.max(8 - ((y + yOffset - 24 - WorldTypeTFC.SEALEVEL) / 16), 0);

                            if (smooth > 0) {
                                if (y >= seaLevel - 1 && y + 1 < yOffset && inp.getBlockState(x, y + 1, z) != SEA_WATER && dirtH > 0 && !(TFCBiomes.isBeachBiome(biome) && y > highestBeachTheoretical + 2)) {
                                    outp.setBlockState(x, y + yOffset, z, surfaceBlock);

                                    boolean mountains = TFCBiomes.isMountainBiome(biome) || biome == TFCBiomes.HIGH_HILLS || biome == TFCBiomes.HIGH_HILLS_EDGE || biome == TFCBiomes.MOUNTAINS || biome == TFCBiomes.MOUNTAINS_EDGE || biome == TFCBiomes.MOUNTAIN_RANGE || biome == TFCBiomes.MOUNTAIN_RANGE_EDGE || biome == TFCBiomes.FOOTHILLS || biome == TFCBiomes.FAULT_LINE || biome == TFCBiomes.CRAG;
                                    for (int c = 1; c < dirtH && !mountains && !cliffMap[colIndex]; c++) {
                                        outp.setBlockState(x, y - c + yOffset, z, subSurfaceBlock);
                                        if (c > 1 + (5 - drainage.valueInt))
                                            outp.setBlockState(x, y - c + yOffset, z,
                                                    BlockRockVariant.get(rock1, Type.GRAVEL).getDefaultState());
                                    }
                                }
                            }
                        }

                        if (y > seaLevel - 2 && y < seaLevel && inp.getBlockState(x, y + 1, z) == SEA_WATER ||
                                y < seaLevel && inp.getBlockState(x, y + 1, z) == SEA_WATER) {
                            if (biome != TFCBiomes.SWAMPLAND || biome != TFCBiomes.BAYOU || biome != TFCBiomes.MANGROVE || biome != TFCBiomes.MARSH) // Most areas have gravel and sand bottoms
                            {
                                if (outp.getBlockState(x, y + yOffset, z) != BlockRockVariant.get(rock1, Type.SAND)
                                        .getDefaultState() && rand.nextInt(5) == 0)
                                    outp.setBlockState(x, y + yOffset, z,
                                            BlockRockVariant.get(rock1, Type.GRAVEL).getDefaultState());
                            } else if ((biome == TFCBiomes.SWAMPLAND || biome == TFCBiomes.MARSH) && ph.valueInt >= 4 && floraDiversity >= 0.25f && floraDensity >= 0.25f) // Bogs with mostly peat and humus
                            {
                                if (outp.getBlockState(x, y + yOffset, z) != BlockRockVariant.get(rock1, Type.HUMUS)
                                        .getDefaultState() && rand.nextInt(5) == 0)
                                    outp.setBlockState(x, y + yOffset, z, BlocksTFC.PEAT.getDefaultState());
                            } else // Swamp biomes have bottoms that are mostly dirt
                            {
                                if (outp.getBlockState(x, y + yOffset, z) != BlockRockVariant.get(rock1, Type.SAND)
                                        .getDefaultState())
                                    outp.setBlockState(x, y + yOffset, z,
                                            BlockRockVariant.get(rock1, Type.DIRT).getDefaultState());
                            }
                        }
                    }
                    //  && biome != BiomesTFC.OCEAN && biome != BiomesTFC.DEEP_OCEAN && biome != BiomesTFC.BEACH && biome != BiomesTFC.GRAVEL_BEACH
                    /*else if ((inp.getBlockState(x, y, z) == SALT_WATER || inp.getBlockState(x, y, z) == FRESH_WATER) && (biome == BiomesTFC.SWAMPLAND || biome == BiomesTFC.MARSH) && ph.valueInt >= 4 && floraDiversity >= 0.25f && floraDensity >= 0.25f)
                    {
                        outp.setBlockState(x, y + yOffset, z, TANNIN);
                    }*/
                    else if (inp.getBlockState(x, y, z) == SEA_WATER && !(TFCBiomes.isOceanicBiome(biome) || TFCBiomes.isBeachBiome(biome))) {
                        outp.setBlockState(x, y + yOffset, z, WATER);
                    }
                }

                for (int y = yOffset - 1; y >= 0; y--) // This cannot be optimized with the prev for loop, because the sealeveloffset won't be ready yet.
                {
                    /*
                     * LOW PART (yOffset is NOT used)
                     */
                    if (y < 1 + (s.flatBedrock ? 0 : rand.nextInt(3))) //  + (seaLevelOffsetMap[colIndex] / 3)
                    {
                        outp.setBlockState(x, y, z, BEDROCK);
                    } else if (outp.isEmpty(x, y, z)) {
                        if (y <= ROCKLAYER3 + seaLevelOffsetMap[colIndex])
                            outp.setBlockState(x, y, z, BlockRockVariant.get(rock3, Type.RAW).getDefaultState());
                        else if (y <= ROCKLAYER2 + seaLevelOffsetMap[colIndex])
                            outp.setBlockState(x, y, z, BlockRockVariant.get(rock2, Type.RAW).getDefaultState());
                        else
                            outp.setBlockState(x, y, z, BlockRockVariant.get(rock1, Type.RAW).getDefaultState());

                        if (TFCBiomes.isBeachBiome(biome) || TFCBiomes.isOceanicBiome(biome)) {
                            if (outp.getBlockState(x, y + 1, z) == SEA_WATER) {
                                outp.setBlockState(x, y, z, BlockRockVariant.get(rock1, Type.SAND).getDefaultState());
                                outp.setBlockState(x, y - 1, z,
                                        BlockRockVariant.get(rock1, Type.SAND).getDefaultState());
                            }
                        }
                    }
                    if (y <= 6 && stability.valueInt == 1 && outp.getBlockState(x, y, z) == AIR) {
                        outp.setBlockState(x, y, z, LAVA);
                        if (outp.getBlockState(x, y + 1, z) != LAVA && rand.nextBoolean()) {
                            outp.setBlockState(x, y + 1, z, LAVA);
                        }
                    }
                }
            }
        }
    }
}
