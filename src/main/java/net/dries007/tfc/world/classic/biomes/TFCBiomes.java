/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.world.classic.biomes;

import net.dries007.tfc.util.Helpers;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;
import static net.dries007.tfc.TerraFirmaCraft.MOD_NAME;

@Mod.EventBusSubscriber(modid = MOD_ID)
@GameRegistry.ObjectHolder(MOD_ID)
public final class TFCBiomes {
    public static final TFCBiome OCEAN = Helpers.getNull();
    public static final TFCBiome RIVER = Helpers.getNull();
    public static final TFCBiome RIVERBANK = Helpers.getNull();
    //public static final BiomeTFC RIVER_SOURCE = Helpers.getNull();
    public static final TFCBiome ESTUARY = Helpers.getNull();
    public static final TFCBiome BEACH = Helpers.getNull();
    public static final TFCBiome GRAVEL_BEACH = Helpers.getNull();
    public static final TFCBiome HIGH_HILLS = Helpers.getNull();
    public static final TFCBiome PLAINS = Helpers.getNull();
    public static final TFCBiome SWAMPLAND = Helpers.getNull();
    public static final TFCBiome HIGH_HILLS_EDGE = Helpers.getNull();
    public static final TFCBiome ROLLING_HILLS = Helpers.getNull();
    public static final TFCBiome MOUNTAINS = Helpers.getNull();
    public static final TFCBiome MOUNTAINS_EDGE = Helpers.getNull();
    public static final TFCBiome HIGH_PLAINS = Helpers.getNull();
    public static final TFCBiome DEEP_OCEAN = Helpers.getNull();
    public static final TFCBiome LAKE = Helpers.getNull();
    public static final TFCBiome LAKESHORE = Helpers.getNull();
    public static final TFCBiome SHORE = Helpers.getNull();

    public static final TFCBiome FLATLANDS = Helpers.getNull();
    public static final TFCBiome FIELDS = Helpers.getNull();
    public static final TFCBiome MEADOWS = Helpers.getNull();
    public static final TFCBiome BAYOU = Helpers.getNull();
    public static final TFCBiome MANGROVE = Helpers.getNull();
    public static final TFCBiome MARSH = Helpers.getNull();

    public static final TFCBiome MOUNTAIN_RANGE = Helpers.getNull();
    public static final TFCBiome MOUNTAIN_RANGE_EDGE = Helpers.getNull();
    public static final TFCBiome FOOTHILLS = Helpers.getNull();
    public static final TFCBiome FAULT_LINE = Helpers.getNull();

    public static final TFCBiomeMesa CRAG = Helpers.getNull();
    public static final TFCBiomeMesa MESA = Helpers.getNull();
    //public static final BiomeMesaTFC MESA_PLATEAU_F = Helpers.getNull();
    public static final TFCBiomeMesa MESA_PLATEAU = Helpers.getNull();
    public static final TFCBiomeMesa MESA_BRYCE = Helpers.getNull();
    //public static final BiomeMesaTFC MESA_PLATEAU_F_M = Helpers.getNull();
    public static final TFCBiomeMesa MESA_PLATEAU_M = Helpers.getNull();

    // TFC+ Biomes
    /*public static final BiomeTFC RIVERBANK = Helpers.getNull();
    public static final BiomeTFC RIVERSOURCE = Helpers.getNull();
    public static final BiomeTFC ESTUARY = Helpers.getNull();*/

    private static final List<Biome> SPAWN_BIOMES = new ArrayList<>();
    private static final List<Biome> WORLD_GEN_BIOMES = new ArrayList<>();

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
        IForgeRegistry<Biome> r = event.getRegistry();

        register(r, new TFCBiome(0x3232C8, new Biome.BiomeProperties(MOD_NAME + " Ocean").setBaseHeight(-2.6f).setHeightVariation(-2.69999f)), false, true, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);
        register(r, new TFCBiome(0x2B8CBA, new Biome.BiomeProperties(MOD_NAME + " River").setBaseHeight(-2.4f).setHeightVariation(-3f)), false, false, BiomeDictionary.Type.RIVER, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);
        register(r, new TFCBiome(0xAAAAAA, new Biome.BiomeProperties(MOD_NAME + " Riverbank").setBaseHeight(-2.1f).setHeightVariation(-3f)), false, false, BiomeDictionary.Type.RIVER, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);
        //register(r, new BiomeTFC(0xAAFFFF, new Biome.BiomeProperties(MOD_NAME + " River Source").setBaseHeight(-2.4f).setHeightVariation(-3f)), false, false, BiomeDictionary.Type.RIVER, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);
        register(r, new TFCBiome(0xAAAAFF, new Biome.BiomeProperties(MOD_NAME + " Estuary").setBaseHeight(-2.4f).setHeightVariation(-3f)), false, false, BiomeDictionary.Type.RIVER, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);
        register(r, new TFCBiome(0xC7A03B, new Biome.BiomeProperties(MOD_NAME + " Beach").setBaseHeight(-1.69f).setHeightVariation(-2.68f)), false, false, BiomeDictionary.Type.BEACH);
        register(r, new TFCBiome(0x7E7450, new Biome.BiomeProperties(MOD_NAME + " Gravel Beach").setBaseHeight(-1.69f).setHeightVariation(-2.68f).setBaseBiome("tfc:beach")), false, false, BiomeDictionary.Type.BEACH);
        register(r, new TFCBiome(0x920072, new Biome.BiomeProperties(MOD_NAME + " High Hills").setBaseHeight(-0.9000001f).setHeightVariation(-1.1f)), false, true, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.LUSH);
        register(r, new TFCBiome(0x346B25, new Biome.BiomeProperties(MOD_NAME + " Plains").setBaseHeight(-1.6000001f).setHeightVariation(-2.54f)).setSpawnBiome(), true, true, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.LUSH);
        register(r, new TFCBiome(0x099200, new Biome.BiomeProperties(MOD_NAME + " Swampland").setBaseHeight(-1.8f).setHeightVariation(-2.6000001f), 16, 45).setSpawnBiome(), true, true, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WET, BiomeDictionary.Type.LUSH);
        register(r, new TFCBiome(0x92567C, new Biome.BiomeProperties(MOD_NAME + " High Hills Edge").setBaseHeight(-1.5f).setHeightVariation(-2.3f).setBaseBiome("tfc:high_hills")), false, false, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.LUSH);
        register(r, new TFCBiome(0x734B92, new Biome.BiomeProperties(MOD_NAME + " Rolling Hills").setBaseHeight(-1.6000001f).setHeightVariation(-2.3f)).setSpawnBiome(), true, true, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.LUSH);
        register(r, new TFCBiome(0x920000, new Biome.BiomeProperties(MOD_NAME + " Mountains").setBaseHeight(-0.9000001f).setHeightVariation(-1.1f)).setSpawnBiome(), true, true, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.LUSH);
        register(r, new TFCBiome(0x924A4C, new Biome.BiomeProperties(MOD_NAME + " Mountains Edge").setBaseHeight(-1.3f).setHeightVariation(-1.9000001f).setBaseBiome("tfc:mountains")).setSpawnBiome(), true, false, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.LUSH);
        register(r, new TFCBiome(0x225031, new Biome.BiomeProperties(MOD_NAME + " High Plains").setBaseHeight(-1.3f).setHeightVariation(-2.27f)).setSpawnBiome(), true, true, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.LUSH);
        register(r, new TFCBiome(0x000080, new Biome.BiomeProperties(MOD_NAME + " Deep Ocean").setBaseHeight(-3.2f).setHeightVariation(-2.49999f).setBaseBiome("tfc:ocean")), false, false, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);
        register(r, new TFCBiome(0x5D8C8D, new Biome.BiomeProperties(MOD_NAME + " Lake").setBaseHeight(-2.4f).setHeightVariation(-2.5990001f).setBaseBiome("tfc:ocean"), 4, 5), false, false, BiomeDictionary.Type.RIVER, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);
        register(r, new TFCBiome(0x63BFD3, new Biome.BiomeProperties(MOD_NAME + " Lakeshore").setBaseHeight(-2.1f).setHeightVariation(-3f).setBaseBiome("tfc:ocean"), 4, 5), false, false, BiomeDictionary.Type.RIVER, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);
        register(r, new TFCBiome(0x0088FF, new Biome.BiomeProperties(MOD_NAME + " Shore").setBaseHeight(-2.15f).setHeightVariation(-2.9f), 8, 20), false, false, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER);

        register(r, new TFCBiome(0x5BC138, new Biome.BiomeProperties(MOD_NAME + " Flatlands").setBaseHeight(-1.7f).setHeightVariation(-2.88f)).setSpawnBiome(), true, true, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.LUSH);
        register(r, new TFCBiome(0x5BC138, new Biome.BiomeProperties(MOD_NAME + " Fields").setBaseHeight(-1.7f).setHeightVariation(-2.88f)).setSpawnBiome(), true, true, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.LUSH);
        register(r, new TFCBiome(0x5BC138, new Biome.BiomeProperties(MOD_NAME + " Meadows").setBaseHeight(-1.7f).setHeightVariation(-2.88f)).setSpawnBiome(), true, true, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.LUSH);
        register(r, new TFCBiome(0x6A7039, new Biome.BiomeProperties(MOD_NAME + " Bayou").setBaseHeight(-2.21f).setHeightVariation(-2.75f).setWaterColor(0xFFD932), 16, 45), true, true, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.RIVER, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER, BiomeDictionary.Type.LUSH);
        register(r, new TFCBiome(0x7DAD51, new Biome.BiomeProperties(MOD_NAME + " Mangrove").setBaseHeight(-2.21f).setHeightVariation(-2.75f).setWaterColor(0xFFD932), 16, 45), true, true, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER, BiomeDictionary.Type.LUSH);
        register(r, new TFCBiome(0x66A06E, new Biome.BiomeProperties(MOD_NAME + " Marsh").setBaseHeight(-1.9f).setHeightVariation(-2.95f), 8, 20).setSpawnBiome(), true, true, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.RIVER, BiomeDictionary.Type.WET, BiomeDictionary.Type.WATER, BiomeDictionary.Type.LUSH);

        register(r, new TFCBiome(0xFF7960, new Biome.BiomeProperties(MOD_NAME + " Mountain Range").setBaseHeight(-0.5f).setHeightVariation(-1.6f)).setSpawnBiome(), true, true, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.SPARSE);
        register(r, new TFCBiome(0xFF00BE, new Biome.BiomeProperties(MOD_NAME + " Mountain Range Edge").setBaseHeight(-0.92f).setHeightVariation(-2.45f)).setSpawnBiome(), true, true, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.SPARSE);
        register(r, new TFCBiome(0xAA00BE, new Biome.BiomeProperties(MOD_NAME + " Foothills").setBaseHeight(-1.25f).setHeightVariation(-2.9f)).setSpawnBiome(), true, true, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.SPARSE);
        register(r, new TFCBiome(0xFFA860, new Biome.BiomeProperties(MOD_NAME + " Fault Line").setBaseHeight(-1.75f).setHeightVariation(-2.68f)).setSpawnBiome(), true, true, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.SPARSE);

        register(r, new TFCBiomeMesa(true, true, 0x8AB689, (new Biome.BiomeProperties(MOD_NAME + " Crag")).setBaseHeight(0.5f).setHeightVariation(-1.7f)).setSpawnBiome(), true, true, BiomeDictionary.Type.MOUNTAIN);
        register(r, new TFCBiomeMesa(false, false, 0x90814D, (new Biome.BiomeProperties(MOD_NAME + " Mesa")).setBaseHeight(-1.1f).setHeightVariation(-1.85f)).setSpawnBiome(), true, true, BiomeDictionary.Type.MESA, BiomeDictionary.Type.HOT, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY, BiomeDictionary.Type.LUSH);
        register(r, new TFCBiomeMesa(false, false, 0x90814D, (new Biome.BiomeProperties(MOD_NAME + " Mesa Plateau")).setBaseHeight(-0.6f).setHeightVariation(-2.63f)).setSpawnBiome(), true, true, BiomeDictionary.Type.MESA, BiomeDictionary.Type.HOT, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.DRY, BiomeDictionary.Type.LUSH);
        register(r, new TFCBiomeMesa(true, false, 0x90814D, (new Biome.BiomeProperties(MOD_NAME + " Mesa Bryce")).setBaseBiome("tfc:mesa")).setSpawnBiome(), true, true, BiomeDictionary.Type.MESA, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.MOUNTAIN);
        register(r, new TFCBiomeMesa(false, false, 0x90814D, (new Biome.BiomeProperties(MOD_NAME + " Mesa Plateau M")).setBaseBiome("tfc:mesa_plateau").setBaseHeight(-0.1f).setHeightVariation(-1.5f)).setSpawnBiome(), true, true, BiomeDictionary.Type.MESA, BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.MOUNTAIN);
    }

    public static boolean isMesaBiome(Biome b) {
        return MESA == b || MESA_PLATEAU == b || MESA_BRYCE == b || MESA_PLATEAU_M == b;
    }

    public static boolean isOceanicBiome(Biome b) {
        return OCEAN == b || DEEP_OCEAN == b || MANGROVE == b || ESTUARY == b || SHORE == b;
    }

    public static boolean isRiverBiome(Biome b) {
        return RIVER == b || RIVERBANK == b /*|| RIVER_SOURCE == b*/;
    }

    public static boolean isLakeBiome(Biome b) {
        return LAKE == b || LAKESHORE == b;
    }

    public static boolean isMountainBiome(Biome b) {
        return MOUNTAINS == b || MOUNTAINS_EDGE == b || CRAG == b || MOUNTAIN_RANGE == b || MOUNTAIN_RANGE_EDGE == b || FAULT_LINE == b;
    }

    public static boolean isBeachBiome(Biome b) {
        return BEACH == b || GRAVEL_BEACH == b;
    }

    public static List<Biome> getSpawnBiomes() {
        return SPAWN_BIOMES;
    }

    public static List<Biome> getWorldGenBiomes() {
        return WORLD_GEN_BIOMES;
    }

    private static void register(IForgeRegistry<Biome> r, Biome biome, boolean isSpawn, boolean isWorldGen, BiomeDictionary.Type... types) {
        r.register(biome.setRegistryName(MOD_ID, biome.biomeName.replace(MOD_NAME + " ", "").replace(' ', '_').toLowerCase()));

        // Other biome registration stuff
        BiomeDictionary.addTypes(biome, types);

        // These need to happen after the biomes are constructed, otherwise they will be null
        if (isSpawn) {
            SPAWN_BIOMES.add(biome);
        }
        if (isWorldGen) {
            WORLD_GEN_BIOMES.add(biome);
        }

        if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.WATER)) {
            // Register aquatic creatures
            biome.getSpawnableList(EnumCreatureType.WATER_CREATURE).add(new Biome.SpawnListEntry(EntitySquid.class, 20, 3, 7));
            // todo add fish (either in 1.15+ or if someone makes fish entities)
        }
    }

    private TFCBiomes() {
    }
}
