/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.world.classic.genlayers;

import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.types.RockCategory;
import net.dries007.tfc.world.classic.biomes.TFCBiome;
import net.dries007.tfc.world.classic.biomes.TFCBiomes;
import net.dries007.tfc.world.classic.genlayers.biome.*;
import net.dries007.tfc.world.classic.genlayers.datalayers.rock.GenLayerRockInit;
import net.dries007.tfc.world.classic.genlayers.datalayers.stability.GenLayerStabilityInit;
import net.dries007.tfc.world.classic.genlayers.mountains.GenLayerMountainRangeInitTFC;
import net.dries007.tfc.world.classic.genlayers.mountains.GenLayerMountainRangeMixTFC;
import net.dries007.tfc.world.classic.genlayers.mountains.GenLayerMountainRangeTFC;
import net.dries007.tfc.world.classic.genlayers.ridge.GenLayerRidgeInitTFC;
import net.dries007.tfc.world.classic.genlayers.ridge.GenLayerRidgeMixTFC;
import net.dries007.tfc.world.classic.genlayers.ridge.GenLayerRidgeTFC;
import net.dries007.tfc.world.classic.genlayers.river.GenLayerRiverInitTFC;
import net.dries007.tfc.world.classic.genlayers.river.GenLayerRiverMixTFC;
import net.dries007.tfc.world.classic.genlayers.river.GenLayerRiverTFC;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraftforge.fml.common.FMLCommonHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.function.Function;
import java.util.function.IntFunction;

public abstract class GenLayerTFC extends GenLayer {
    protected long worldGenSeed;
    protected long chunkSeed;

    // Distinct colors for debug map gen
    private static final Color[] COLORS = new Color[]{
            new Color(0xFFB300),    // Vivid Yellow
            new Color(0x803E75),    // Strong Purple
            new Color(0xFF6800),    // Vivid Orange
            new Color(0xA6BDD7),    // Very Light Blue
            new Color(0xC10020),    // Vivid Red
            new Color(0xCEA262),    // Grayish Yellow
            new Color(0x817066),    // Medium Gray
            new Color(0x007D34),    // Vivid Green
            new Color(0xF6768E),    // Strong Purplish Pink
            new Color(0x00538A),    // Strong Blue
            new Color(0xFF7A5C),    // Strong Yellowish Pink
            new Color(0x53377A),    // Strong Violet
            new Color(0xFF8E00),    // Vivid Orange Yellow
            new Color(0xB32851),    // Strong Purplish Red
            new Color(0xF4C800),    // Vivid Greenish Yellow
            new Color(0x7F180D),    // Strong Reddish Brown
            new Color(0x93AA00),    // Vivid Yellowish Green
            new Color(0x593315),    // Deep Yellowish Brown
            new Color(0xF13A13),    // Vivid Reddish Orange
            new Color(0x232C16),    // Dark Olive Green
    };

    public static GenLayerTFC[] initializeBiomes(long seed) {
        byte var4 = 4;

        // Continent generator
        GenLayerTFC continent = new GenLayerIslandTFC(1L);
        continent = new GenLayerFuzzyZoomTFC(2000L, continent);
        continent = new GenLayerAddIslandTFC(1L, continent);
        continent = new GenLayerZoomTFC(2001L, continent);
        continent = new GenLayerAddIslandTFC(2L, continent);
        continent = new GenLayerZoomTFC(2002L, continent);
        continent = new GenLayerAddIslandTFC(3L, continent);
        continent = new GenLayerZoomTFC(2003L, continent);
        continent = new GenLayerAddIslandTFC(4L, continent);
        continent = new GenLayerDeepOcean(4L, continent);
        // At this point, the output of continent only contains PLAINS, OCEAN and DEEP OCEAN.
        drawImageBiomes(1024, continent, "continent");

        // Create Biomes
        GenLayerTFC continentCopy2 = GenLayerZoomTFC.magnify(1000L, continent, 0);
        GenLayerTFC var17 = new GenLayerBiomeTFC(200L, continentCopy2);
        GenLayerLakes lakes = new GenLayerLakes(200L, var17);
        continentCopy2 = GenLayerZoomTFC.magnify(1000L, lakes, 2);
        GenLayerTFC biomes = new GenLayerBiomeEdge(1000L, continentCopy2);
        for (int var7 = 0; var7 < var4; ++var7) {
            biomes = new GenLayerZoomTFC(1000 + var7, biomes);
            if (var7 == 0)
                biomes = new GenLayerAddIslandTFC(3L, biomes);
            if (var7 == 1) {
                biomes = new GenLayerBeachTFC(1000L, biomes);
            }
        }
        biomes = new GenLayerMountainRangeEdge(1L, biomes);
        biomes = new GenLayerFoothills(1L, biomes);
        biomes = new GenLayerShoreTFC(1L, biomes);
        biomes = new GenLayerLakeShore(1L, biomes);
        GenLayerSmoothTFC smoothContinent = new GenLayerSmoothTFC(1000L, biomes);
        drawImageBiomes(1024, biomes, "biomes");
        // Now we have a full on biome map


        // Create Rivers
        GenLayerTFC riverCont = GenLayerZoomTFC.magnify(1000L, biomes, 1);
        riverCont = new GenLayerRiverInitTFC(100L, riverCont);
        riverCont = GenLayerZoomTFC.magnify(1000L, riverCont, 0);
        riverCont = new GenLayerRiverTFC(1L, riverCont);
        //riverCont = new GenLayerBiomeSpecialEdge(1000L, riverCont);
        riverCont = new GenLayerSmoothTFC(1000L, riverCont);
        drawImageBiomes(1024, riverCont, "rivers");

        GenLayerRiverMixTFC riverMix = new GenLayerRiverMixTFC(100L, smoothContinent, riverCont);
        drawImageBiomes(1024, riverMix, "riversMixed");


        // Create Fault Lines
        GenLayerTFC faultLineCont = GenLayerZoomTFC.magnify(1000L, continent, 2);
        faultLineCont = new GenLayerRidgeInitTFC(100L, faultLineCont);
        faultLineCont = GenLayerZoomTFC.magnify(1000L, faultLineCont, 7);
        faultLineCont = new GenLayerRidgeTFC(1L, faultLineCont);
        //faultLineCont = new GenLayerBiomeSpecialEdge(1000L, faultLineCont);
        faultLineCont = new GenLayerSmoothTFC(1000L, faultLineCont);
        drawImageBiomes(1024, faultLineCont, "faultLines");

        GenLayerRidgeMixTFC faultLineMix = new GenLayerRidgeMixTFC(100L, riverMix, faultLineCont);
        drawImageBiomes(1024, faultLineMix, "faultLinesMixed");


        // Create Mountain Ranges
        GenLayerTFC mountainCont = GenLayerZoomTFC.magnify(1000L, continent, 20);
        mountainCont = new GenLayerMountainRangeInitTFC(100L, mountainCont);
        mountainCont = GenLayerZoomTFC.magnify(1000L, mountainCont, 7);
        mountainCont = new GenLayerMountainRangeTFC(1L, mountainCont);
        //mountainCont = new GenLayerBiomeSpecialEdge(1000L, mountainCont);
        mountainCont = new GenLayerSmoothTFC(1000L, mountainCont);
        drawImageBiomes(1024, mountainCont, "mountains");

        GenLayerMountainRangeMixTFC mountainMix = new GenLayerMountainRangeMixTFC(100L, faultLineMix, mountainCont);
        drawImageBiomes(1024, mountainMix, "mountainsMixed");

        GenLayerTFC finalCont = GenLayerZoomTFC.magnify(1000L, riverMix, 2);
        /*finalCont = new GenLayerRiverBank(1000L, finalCont);
        finalCont = new GenLayerMountainRangeEdge(1000L, finalCont);
        finalCont = new GenLayerFoothills(1000L, finalCont);*/
        //finalCont = new GenLayerBiomeSpecialEdge(1000L, finalCont);
        finalCont = new GenLayerSmoothTFC(1001L, finalCont);

        riverMix.initWorldGenSeed(seed);
		/*faultLineMix.initWorldGenSeed(seed);
		mountainMix.initWorldGenSeed(seed);*/
        finalCont.initWorldGenSeed(seed);
        drawImageBiomes(1024, finalCont, "Final World " + (seed));

        return new GenLayerTFC[]{riverMix, finalCont};
    }

    public static GenLayerTFC initializeRock(long seed, RockCategory.Layer level, int rockLayerSize) {
        GenLayerTFC layer = new GenLayerRockInit(1L, level);
        layer = new GenLayerFuzzyZoomTFC(2000L, layer);
        layer = new GenLayerZoomTFC(2001L, layer);
        layer = new GenLayerZoomTFC(2002L, layer);
        layer = new GenLayerZoomTFC(2003L, layer);
        layer = new GenLayerSmoothTFC(1000L, layer);

        for (int zoomLevel = 0; zoomLevel < rockLayerSize; ++zoomLevel) {
            layer = new GenLayerZoomTFC(1000 + zoomLevel, layer);
        }
        layer = new GenLayerSmoothTFC(1000L, layer);
        layer = new GenLayerVoronoiZoomTFC(10L, layer);
        layer.initWorldGenSeed(seed);
        drawImage(1024, layer, "rock" + level.name());
        return layer;
    }

    public static GenLayerTFC initializeStability(long seed) {
        GenLayerTFC continent = new GenLayerStabilityInit(1L + seed);
        continent = new GenLayerFuzzyZoomTFC(2000L, continent);
        continent = new GenLayerZoomTFC(2001L, continent);
        continent = new GenLayerZoomTFC(2002L, continent);
        continent = new GenLayerZoomTFC(2003L, continent);
        continent = GenLayerZoomTFC.magnify(1000L, continent, 2);
        continent = new GenLayerSmoothTFC(1000L, continent);
        continent = new GenLayerZoomTFC(1000, continent);
        continent = new GenLayerZoomTFC(1001, continent);
        continent = new GenLayerZoomTFC(1002, continent);
        continent = new GenLayerZoomTFC(1003, continent);
        continent = new GenLayerSmoothTFC(1000L, continent);
        continent = new GenLayerVoronoiZoomTFC(10L, continent);
        continent.initWorldGenSeed(seed);
        drawImage(1024, continent, "stability");
        return continent;
    }

    public static void drawImageBiomes(int size, GenLayerTFC genlayer, String name) {
        Function<Biome, Color> colorize = (x) -> x instanceof TFCBiome ? ((TFCBiome) x).debugColor : Color.BLACK;
        drawImage(size, genlayer, name, (i) -> colorize.apply(Biome.getBiomeForId(i)));
    }

    public static void drawImage(int size, GenLayerTFC genlayer, String name) {
        drawImage(size, genlayer, name, (i) -> COLORS[i % COLORS.length]);
    }

    public static void drawImage(int size, GenLayerTFC genlayer, String name, IntFunction<Color> gibColor) {
        if (!ConfigTFC.General.DEBUG.debugWorldGenSafe) return;
        if (FMLCommonHandler.instance().getEffectiveSide().isClient()) return;
        try {
            int[] ints = genlayer.getInts(-size / 2, -size / 2, size, size);
            BufferedImage outBitmap = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = (Graphics2D) outBitmap.getGraphics();
            graphics.clearRect(0, 0, size, size);
            for (int x = 0; x < size; x++) {
                for (int z = 0; z < size; z++) {
                    int i = ints[x * size + z];
                    if (i == -1 || x == size / 2 || z == size / 2) {
                        graphics.setColor(Color.WHITE);
                    } else {
                        graphics.setColor(gibColor.apply(i));
                    }
                    //noinspection SuspiciousNameCombination
                    graphics.drawRect(z, x, 1, 1);
                }
            }
            name = "_" + name + ".png";
            TerraFirmaCraft.getLog().info("Worldgen debug image {}", name);
            ImageIO.write(outBitmap, "PNG", new File(name));
        } catch (Exception e) {
            TerraFirmaCraft.getLog().catching(e);
        }
    }

    /**
     * Initialize layer's local worldGenSeed based on its own baseSeed and the
     * world's global seed (passed in as an
     * argument).
     */
    @Override
    public void initWorldGenSeed(long par1) {
        worldGenSeed = par1;
        if (this.parent != null)
            parent.initWorldGenSeed(par1);

        worldGenSeed *= worldGenSeed * 6364136223846793005L + 1442695040888963407L;
        worldGenSeed += baseSeed;
        worldGenSeed *= worldGenSeed * 6364136223846793005L + 1442695040888963407L;
        worldGenSeed += baseSeed;
        worldGenSeed *= worldGenSeed * 6364136223846793005L + 1442695040888963407L;
        worldGenSeed += baseSeed;
    }

    /**
     * Initialize layer's current chunkSeed based on the local worldGenSeed and
     * the (x,z) chunk coordinates.
     */
    @Override
    public void initChunkSeed(long par1, long par3) {
        chunkSeed = worldGenSeed;
        chunkSeed *= chunkSeed * 6364136223846793005L + 1442695040888963407L;
        chunkSeed += par1;
        chunkSeed *= chunkSeed * 6364136223846793005L + 1442695040888963407L;
        chunkSeed += par3;
        chunkSeed *= chunkSeed * 6364136223846793005L + 1442695040888963407L;
        chunkSeed += par1;
        chunkSeed *= chunkSeed * 6364136223846793005L + 1442695040888963407L;
        chunkSeed += par3;
    }

    /**
     * returns a LCG pseudo random number from [0, x). Args: int x
     */
    @Override
    protected int nextInt(int par1) {
        int var2 = (int) ((this.chunkSeed >> 24) % par1);
        if (var2 < 0)
            var2 += par1;
        chunkSeed *= chunkSeed * 6364136223846793005L + 1442695040888963407L;
        chunkSeed += worldGenSeed;
        return var2;
    }

    // Doing this lookup only once is quite a bit faster.
    protected final int oceanID = Biome.getIdForBiome(TFCBiomes.OCEAN);
    protected final int plainsID = Biome.getIdForBiome(TFCBiomes.PLAINS);
    protected final int highPlainsID = Biome.getIdForBiome(TFCBiomes.HIGH_PLAINS);
    protected final int deepOceanID = Biome.getIdForBiome(TFCBiomes.DEEP_OCEAN);
    protected final int lakeID = Biome.getIdForBiome(TFCBiomes.LAKE);
    protected final int lakeshoreID = Biome.getIdForBiome(TFCBiomes.LAKESHORE);
    protected final int shoreID = Biome.getIdForBiome(TFCBiomes.SHORE);
    protected final int riverID = Biome.getIdForBiome(TFCBiomes.RIVER);
    protected final int riverBankID = Biome.getIdForBiome(TFCBiomes.RIVERBANK);
    //protected final int riverSourceID = Biome.getIdForBiome(BiomesTFC.RIVER_SOURCE);
    protected final int estuaryID = Biome.getIdForBiome(TFCBiomes.ESTUARY);
    protected final int swamplandID = Biome.getIdForBiome(TFCBiomes.SWAMPLAND);
    protected final int highHillsID = Biome.getIdForBiome(TFCBiomes.HIGH_HILLS);
    protected final int highHillsEdgeID = Biome.getIdForBiome(TFCBiomes.HIGH_HILLS_EDGE);
    protected final int rollingHillsID = Biome.getIdForBiome(TFCBiomes.ROLLING_HILLS);
    protected final int beachID = Biome.getIdForBiome(TFCBiomes.BEACH);
    protected final int gravelBeachID = Biome.getIdForBiome(TFCBiomes.GRAVEL_BEACH);
    protected final int mountainsID = Biome.getIdForBiome(TFCBiomes.MOUNTAINS);
    protected final int mountainsEdgeID = Biome.getIdForBiome(TFCBiomes.MOUNTAINS_EDGE);
    protected final int flatlandsID = Biome.getIdForBiome(TFCBiomes.FLATLANDS);
    protected final int fieldsID = Biome.getIdForBiome(TFCBiomes.FIELDS);
    protected final int meadowsID = Biome.getIdForBiome(TFCBiomes.MEADOWS);
    protected final int bayouID = Biome.getIdForBiome(TFCBiomes.BAYOU);
    protected final int mangroveID = Biome.getIdForBiome(TFCBiomes.MANGROVE);
    protected final int marshID = Biome.getIdForBiome(TFCBiomes.MARSH);
    protected final int mountainRangeID = Biome.getIdForBiome(TFCBiomes.MOUNTAIN_RANGE);
    protected final int mountainRangeEdgeID = Biome.getIdForBiome(TFCBiomes.MOUNTAIN_RANGE_EDGE);
    protected final int foothillsID = Biome.getIdForBiome(TFCBiomes.FOOTHILLS);
    protected final int faultLineID = Biome.getIdForBiome(TFCBiomes.FAULT_LINE);
    protected final int cragID = Biome.getIdForBiome(TFCBiomes.CRAG);
    protected final int mesaID = Biome.getIdForBiome(TFCBiomes.MESA);
    protected final int mesaPlateauID = Biome.getIdForBiome(TFCBiomes.MESA_PLATEAU);
    protected final int mesaBryceID = Biome.getIdForBiome(TFCBiomes.MESA_BRYCE);
    protected final int mesaPlateauMID = Biome.getIdForBiome(TFCBiomes.MESA_PLATEAU_M);

    public GenLayerTFC(long seed) {
        super(seed);
    }

    public boolean isWetBiome(int id) {
        return swamplandID == id || bayouID == id || mangroveID == id || marshID == id;
    }

    public boolean isFlatBiome(int id) {
        return plainsID == id || flatlandsID == id || fieldsID == id || meadowsID == id;
    }

    public boolean isRiverOrLakeBiome(int id) {
        return riverID == id || riverBankID == id || lakeID == id || lakeshoreID == id;
    }

    public boolean isOceanicBiome(int id) {
        return oceanID == id || deepOceanID == id || mangroveID == id || shoreID == id;
    }

    public boolean isMountainBiome(int id) {
        return mountainsID == id || mountainsEdgeID == id || cragID == id || mountainRangeID == id || mountainRangeEdgeID == id || faultLineID == id;
    }

    public boolean isBeachBiome(int id) {
        return beachID == id || gravelBeachID == id;
    }

    public boolean isLandBiome(int id) {
        return plainsID == id || highPlainsID == id || highHillsID == id || highHillsEdgeID == id || rollingHillsID == id ||
                mountainsID == id || mountainsEdgeID == id || mountainRangeID == id || mountainRangeEdgeID == id || foothillsID == id ||
                faultLineID == id || cragID == id || mesaID == id || mesaPlateauID == id || mesaBryceID == id || mesaPlateauMID == id;
    }

    public boolean isWaterBiome(int id) {
        return isBeachBiome(id) || isOceanicBiome(id) || isRiverOrLakeBiome(id);
    }
}
