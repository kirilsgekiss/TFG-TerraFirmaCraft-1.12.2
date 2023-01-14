package tfcflorae.types;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import net.dries007.tfc.api.registries.TFCRegistryEvent;
import net.dries007.tfc.api.types.Plant;

import tfcflorae.ConfigTFCF;
import tfcflorae.TFCFlorae;

import static tfcflorae.TFCFlorae.TFCFLORAE_MODID;
import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

@SuppressWarnings("WeakerAccess")
@Mod.EventBusSubscriber(modid = TFCFlorae.TFCFLORAE_MODID)
public final class PlantsTFCF
{
    public static final ResourceLocation CATTAIL = new ResourceLocation(MOD_ID, "cattail");
    public static final ResourceLocation CHAMOMILE = new ResourceLocation(MOD_ID, "chamomile");
    public static final ResourceLocation DEVILS_TONGUE = new ResourceLocation(MOD_ID, "devils_tongue");
    public static final ResourceLocation HYDRANGEA = new ResourceLocation(MOD_ID, "hydrangea");
    public static final ResourceLocation LAVANDULA = new ResourceLocation(MOD_ID, "lavandula");
    public static final ResourceLocation LEAF_LITTER = new ResourceLocation(MOD_ID, "leaf_litter");
    public static final ResourceLocation LILAC = new ResourceLocation(MOD_ID, "lilac");
    public static final ResourceLocation LILY_OF_THE_VALLEY = new ResourceLocation(MOD_ID, "lily_of_the_valley");
    public static final ResourceLocation MADDER = new ResourceLocation(MOD_ID, "madder");
    public static final ResourceLocation OCOTILLO = new ResourceLocation(MOD_ID, "ocotillo");
    public static final ResourceLocation PAPYRUS = new ResourceLocation(MOD_ID, "papyrus");
    public static final ResourceLocation PEONY = new ResourceLocation(MOD_ID, "peony");
    public static final ResourceLocation SHRUB = new ResourceLocation(MOD_ID, "shrub");
    public static final ResourceLocation CHAPARRAL_SHRUB = new ResourceLocation(MOD_ID, "chaparral_shrub");
    public static final ResourceLocation SUGAR_CANE = new ResourceLocation(MOD_ID, "sugar_cane");
    public static final ResourceLocation SUNFLOWER = new ResourceLocation(MOD_ID, "sunflower");
    public static final ResourceLocation TACKWEED = new ResourceLocation(MOD_ID, "tackweed");
    public static final ResourceLocation TAKAKIA = new ResourceLocation(MOD_ID, "takakia");
    public static final ResourceLocation LOW_UNDERGROWTH = new ResourceLocation(MOD_ID, "undergrowth_low");
    public static final ResourceLocation TROPICAL_UNDERGROWTH = new ResourceLocation(MOD_ID, "undergrowth_tropical");
    public static final ResourceLocation VOODOO_LILY = new ResourceLocation(MOD_ID, "voodoo_lily");
    public static final ResourceLocation BROMELIA_HEMISPHERICA = new ResourceLocation(MOD_ID, "bromelia_hemispherica");
    public static final ResourceLocation BROMELIA_LACINIOSA = new ResourceLocation(MOD_ID, "bromelia_laciniosa");
    public static final ResourceLocation KAIETEUR_FALLS = new ResourceLocation(MOD_ID, "kaieteur_falls");
    public static final ResourceLocation MATTEUCCIA = new ResourceLocation(MOD_ID, "matteuccia");
    public static final ResourceLocation CORD_GRASS = new ResourceLocation(MOD_ID, "cord_grass");
    public static final ResourceLocation REED_MANNAGRASS = new ResourceLocation(MOD_ID, "reed_mannagrass");
    public static final ResourceLocation PRAIRIE_JUNEGRASS = new ResourceLocation(MOD_ID, "prairie_junegrass");
    public static final ResourceLocation WOOLLY_BUSH = new ResourceLocation(MOD_ID, "woolly_bush");
    public static final ResourceLocation CINNAMON_FERN = new ResourceLocation(MOD_ID, "cinnamon_fern");
    public static final ResourceLocation JAPANESE_PIERIS = new ResourceLocation(MOD_ID, "japanese_pieris");
    public static final ResourceLocation BURNING_BUSH = new ResourceLocation(MOD_ID, "burning_bush");
    public static final ResourceLocation UNDERGROWTH_SHRUB = new ResourceLocation(MOD_ID, "undergrowth_shrub");
    public static final ResourceLocation UNDERGROWTH_SHRUB_SMALL = new ResourceLocation(MOD_ID, "undergrowth_shrub_small");
    public static final ResourceLocation SEA_OATS = new ResourceLocation(MOD_ID, "sea_oats");
    public static final ResourceLocation BUNCH_GRASS_FLOATING = new ResourceLocation(MOD_ID, "bunch_grass_floating");
    public static final ResourceLocation BUNCH_GRASS_REED = new ResourceLocation(MOD_ID, "bunch_grass_reed");
    public static final ResourceLocation CROWNGRASS = new ResourceLocation(MOD_ID, "crowngrass");
    public static final ResourceLocation CAT_GRASS = new ResourceLocation(MOD_ID, "cat_grass");
    public static final ResourceLocation GOOSEGRASS = new ResourceLocation(MOD_ID, "goosegrass");
    public static final ResourceLocation WHEATGRASS = new ResourceLocation(MOD_ID, "wheatgrass");
    public static final ResourceLocation HALFA_GRASS = new ResourceLocation(MOD_ID, "halfa_grass");
    public static final ResourceLocation LEYMUS = new ResourceLocation(MOD_ID, "leymus");
    public static final ResourceLocation MARRAM_GRASS = new ResourceLocation(MOD_ID, "marram_grass");
    //public static final ResourceLocation WILD_BARLEY = new ResourceLocation(MODID, "wild_barley");
    //public static final ResourceLocation WILD_RICE = new ResourceLocation(MODID, "wild_rice");
    //public static final ResourceLocation WILD_WHEAT = new ResourceLocation(MODID, "wild_wheat");
    public static final ResourceLocation SAWGRASS = new ResourceLocation(MOD_ID, "sawgrass");
    public static final ResourceLocation SAGUARO_CACTUS = new ResourceLocation(MOD_ID, "saguaro_cactus");

    public static final ResourceLocation CAVE_VINES = new ResourceLocation(MOD_ID, "cave_vines");
    public static final ResourceLocation GLOW_VINES = new ResourceLocation(MOD_ID, "glow_vines");
    public static final ResourceLocation ROOTS = new ResourceLocation(MOD_ID, "roots");
    public static final ResourceLocation ICICLE = new ResourceLocation(MOD_ID, "icicle");
    public static final ResourceLocation RESIN = new ResourceLocation(MOD_ID, "resin");

    // Vine/Ivy
    public static final ResourceLocation RATTAN = new ResourceLocation(MOD_ID, "rattan");
    public static final ResourceLocation BEARDED_MOSS = new ResourceLocation(MOD_ID, "bearded_moss");
    public static final ResourceLocation BLUE_SKYFLOWER = new ResourceLocation(MOD_ID, "blue_skyflower");
    public static final ResourceLocation GLOW_VINE = new ResourceLocation(MOD_ID, "glow_vine");
    public static final ResourceLocation HANGING_VINE = new ResourceLocation(MOD_ID, "hanging_vine");
    public static final ResourceLocation IVY = new ResourceLocation(MOD_ID, "ivy");
    public static final ResourceLocation JADE_VINE = new ResourceLocation(MOD_ID, "jade_vine");
    public static final ResourceLocation JAPANESE_IVY = new ResourceLocation(MOD_ID, "japanese_ivy");
    public static final ResourceLocation JUNGLE_VINE = new ResourceLocation(MOD_ID, "jungle_vine");
    public static final ResourceLocation LIANA = new ResourceLocation(MOD_ID, "liana");
    public static final ResourceLocation MADEIRA_VINE = new ResourceLocation(MOD_ID, "madeira_vine");
    public static final ResourceLocation MYSORE_TRUMPETVINE = new ResourceLocation(MOD_ID, "mysore_trumpetvine");
    public static final ResourceLocation SILVERVEIN_CREEPER = new ResourceLocation(MOD_ID, "silvervein_creeper");
    public static final ResourceLocation SWEDISH_IVY = new ResourceLocation(MOD_ID, "swedish_ivy");
    public static final ResourceLocation VARIEGATED_PERSIAN_IVY = new ResourceLocation(MOD_ID, "variegated_persian_ivy");

    // Epiphytes
    public static final ResourceLocation APACHE_DWARF = new ResourceLocation(MOD_ID, "apache_dwarf");
    public static final ResourceLocation ARTISTS_CONK = new ResourceLocation(MOD_ID, "artists_conk");
    public static final ResourceLocation CLIMBING_CACTUS = new ResourceLocation(MOD_ID, "climbing_cactus");
    public static final ResourceLocation CRIMSON_CATTLEYA = new ResourceLocation(MOD_ID, "crimson_cattleya");
    public static final ResourceLocation CREEPING_MISTLETOE = new ResourceLocation(MOD_ID, "creeping_mistletoe");
    public static final ResourceLocation CUTHBERTS_DENDROBIUM = new ResourceLocation(MOD_ID, "cuthberts_dendrobium");
    public static final ResourceLocation FISH_BONE_CACTUS = new ResourceLocation(MOD_ID, "fish_bone_cactus");
    public static final ResourceLocation FRAGRANT_FERN = new ResourceLocation(MOD_ID, "fragrant_fern");
    public static final ResourceLocation HARLEQUIN_MISTLETOE = new ResourceLocation(MOD_ID, "harlequin_mistletoe");
    public static final ResourceLocation KING_ORCHID = new ResourceLocation(MOD_ID, "king_orchid");
    public static final ResourceLocation LANTERN_OF_THE_FOREST = new ResourceLocation(MOD_ID, "lantern_of_the_forest");
    public static final ResourceLocation LARGE_FOOT_DENDROBIUM = new ResourceLocation(MOD_ID, "large_foot_dendrobium");
    public static final ResourceLocation COMMON_MISTLETOE = new ResourceLocation(MOD_ID, "common_mistletoe");
    public static final ResourceLocation SKY_PLANT = new ResourceLocation(MOD_ID, "sky_plant");
    public static final ResourceLocation SULPHUR_SHELF = new ResourceLocation(MOD_ID, "sulphur_shelf");
    public static final ResourceLocation TAMPA_BUTTERFLY_ORCHID = new ResourceLocation(MOD_ID, "tampa_butterfly_orchid");
    public static final ResourceLocation TURKEY_TAIL = new ResourceLocation(MOD_ID, "turkey_tail");
    public static final ResourceLocation WILDFIRE = new ResourceLocation(MOD_ID, "wildfire");

    // Water Plants
    public static final ResourceLocation BADDERLOCKS = new ResourceLocation(MOD_ID, "badderlocks");
    public static final ResourceLocation BROWN_ALGAE = new ResourceLocation(MOD_ID, "brown_algae");
    public static final ResourceLocation COONTAIL = new ResourceLocation(MOD_ID, "coontail");
    public static final ResourceLocation EEL_GRASS = new ResourceLocation(MOD_ID, "eel_grass");
    public static final ResourceLocation GIANT_KELP = new ResourceLocation(MOD_ID, "giant_kelp");
    public static final ResourceLocation GUTWEED = new ResourceLocation(MOD_ID, "gutweed");
    public static final ResourceLocation HORNWORT = new ResourceLocation(MOD_ID, "hornwort");
    public static final ResourceLocation LAMINARIA = new ResourceLocation(MOD_ID, "laminaria");
    public static final ResourceLocation LEAFY_KELP = new ResourceLocation(MOD_ID, "leafy_kelp");
    public static final ResourceLocation MANATEE_GRASS = new ResourceLocation(MOD_ID, "manatee_grass");
    public static final ResourceLocation MILFOIL = new ResourceLocation(MOD_ID, "milfoil");
    public static final ResourceLocation PONDWEED = new ResourceLocation(MOD_ID, "pondweed");
    public static final ResourceLocation SAGO = new ResourceLocation(MOD_ID, "sago");
    public static final ResourceLocation SEAGRASS = new ResourceLocation(MOD_ID, "seagrass");
    public static final ResourceLocation SEAWEED = new ResourceLocation(MOD_ID, "seaweed");
    public static final ResourceLocation STAR_GRASS = new ResourceLocation(MOD_ID, "star_grass");
    public static final ResourceLocation TURTLE_GRASS = new ResourceLocation(MOD_ID, "turtle_grass");
    public static final ResourceLocation WINGED_KELP = new ResourceLocation(MOD_ID, "winged_kelp");
    public static final ResourceLocation RED_ALGAE = new ResourceLocation(MOD_ID, "red_algae");
    public static final ResourceLocation RED_SEA_WHIP = new ResourceLocation(MOD_ID, "red_sea_whip");
    public static final ResourceLocation SEA_ANEMONE = new ResourceLocation(MOD_ID, "sea_anemone");

    // Mushrooms
    public static final ResourceLocation AMANITA = new ResourceLocation(MOD_ID, "amanita");
    public static final ResourceLocation BLACK_POWDERPUFF = new ResourceLocation(MOD_ID, "black_powderpuff");
    public static final ResourceLocation CHANTERELLE = new ResourceLocation(MOD_ID, "chanterelle");
    public static final ResourceLocation DEATH_CAP = new ResourceLocation(MOD_ID, "death_cap");
    public static final ResourceLocation GIANT_CLUB = new ResourceLocation(MOD_ID, "giant_club");
    public static final ResourceLocation PARASOL_MUSHROOM = new ResourceLocation(MOD_ID, "parasol_mushroom");
    public static final ResourceLocation STINKHORN = new ResourceLocation(MOD_ID, "stinkhorn");
    public static final ResourceLocation WEEPING_MILK_CAP = new ResourceLocation(MOD_ID, "weeping_milk_cap");
    public static final ResourceLocation WOOD_BLEWIT = new ResourceLocation(MOD_ID, "wood_blewit");
    public static final ResourceLocation WOOLLY_GOMPHUS = new ResourceLocation(MOD_ID, "woolly_gomphus");

    // Glowing Cave Mushrooms
    // public static final ResourceLocation GLOWSHROOM = new ResourceLocation(MODID, "glowshroom");

    // Large Plants
    public static final ResourceLocation BELL_TREE_DAHLIA = new ResourceLocation(MOD_ID, "bell_tree_dahlia");
    public static final ResourceLocation BIG_LEAF_PALM = new ResourceLocation(MOD_ID, "big_leaf_palm");
    public static final ResourceLocation DRAKENSBERG_CYCAD = new ResourceLocation(MOD_ID, "drakensberg_cycad");
    public static final ResourceLocation DWARF_SUGAR_PALM = new ResourceLocation(MOD_ID, "dwarf_sugar_palm");
    public static final ResourceLocation GIANT_CANE = new ResourceLocation(MOD_ID, "giant_cane");
    public static final ResourceLocation GIANT_ELEPHANT_EAR = new ResourceLocation(MOD_ID, "giant_elephant_ear");
    public static final ResourceLocation GIANT_FEATHER_GRASS = new ResourceLocation(MOD_ID, "giant_feather_grass");
    public static final ResourceLocation MADAGASCAR_OCOTILLO = new ResourceLocation(MOD_ID, "madagascar_ocotillo");
    public static final ResourceLocation MALAGASY_TREE_ALOE = new ResourceLocation(MOD_ID, "malagasy_tree_aloe");
    public static final ResourceLocation MOUNTAIN_CABBAGE_TREE = new ResourceLocation(MOD_ID, "mountain_cabbage_tree");
    public static final ResourceLocation PYGMY_DATE_PALM = new ResourceLocation(MOD_ID, "pygmy_date_palm");
    public static final ResourceLocation QUEEN_SAGO = new ResourceLocation(MOD_ID, "queen_sago");
    public static final ResourceLocation RED_SEALING_WAX_PALM = new ResourceLocation(MOD_ID, "red_sealing_wax_palm");
    public static final ResourceLocation SUMMER_ASPHODEL = new ResourceLocation(MOD_ID, "summer_asphodel");
    public static final ResourceLocation ZIMBABWE_ALOE = new ResourceLocation(MOD_ID, "zimbabwe_aloe");

    // Bamboo
    public static final ResourceLocation BAMBOO = new ResourceLocation(MOD_ID, "bamboo");
    public static final ResourceLocation ARROW_BAMBOO = new ResourceLocation(MOD_ID, "arrow_bamboo");
    public static final ResourceLocation BLACK_BAMBOO = new ResourceLocation(MOD_ID, "black_bamboo");
    public static final ResourceLocation BLUE_BAMBOO = new ResourceLocation(MOD_ID, "blue_bamboo");
    public static final ResourceLocation DRAGON_BAMBOO = new ResourceLocation(MOD_ID, "dragon_bamboo");
    public static final ResourceLocation GOLDEN_BAMBOO = new ResourceLocation(MOD_ID, "golden_bamboo");
    public static final ResourceLocation NARROW_LEAF_BAMBOO = new ResourceLocation(MOD_ID, "narrow_leaf_bamboo");
    public static final ResourceLocation RED_BAMBOO = new ResourceLocation(MOD_ID, "red_bamboo");
    public static final ResourceLocation TEMPLE_BAMBOO = new ResourceLocation(MOD_ID, "temple_bamboo");
    public static final ResourceLocation THORNY_BAMBOO = new ResourceLocation(MOD_ID, "thorny_bamboo");
    public static final ResourceLocation TIMBER_BAMBOO = new ResourceLocation(MOD_ID, "timber_bamboo");
    public static final ResourceLocation TINWA_BAMBOO = new ResourceLocation(MOD_ID, "tinwa_bamboo");
    public static final ResourceLocation WEAVERS_BAMBOO = new ResourceLocation(MOD_ID, "weavers_bamboo");

    // Plants courtesy of Therighton
    public static final ResourceLocation ANTHURIUM = new ResourceLocation(MOD_ID, "anthurium");
    public static final ResourceLocation ARROWHEAD = new ResourceLocation(MOD_ID, "arrowhead");
    public static final ResourceLocation ARUNDO = new ResourceLocation(MOD_ID, "arundo");
    public static final ResourceLocation AZURE_BLUET = new ResourceLocation(MOD_ID, "azure_bluet");
    public static final ResourceLocation BLUEGRASS = new ResourceLocation(MOD_ID, "bluegrass");
    public static final ResourceLocation BLUE_GINGER = new ResourceLocation(MOD_ID, "blue_ginger");
    public static final ResourceLocation BROMEGRASS = new ResourceLocation(MOD_ID, "bromegrass");
    public static final ResourceLocation BUR_MARIGOLD = new ResourceLocation(MOD_ID, "bur_marigold");
    public static final ResourceLocation BUR_REED = new ResourceLocation(MOD_ID, "bur_reed");
    public static final ResourceLocation CANNA_LILY = new ResourceLocation(MOD_ID, "canna_lily");
    public static final ResourceLocation COCKLESHELL_ORCHID = new ResourceLocation(MOD_ID, "cockleshell_orchid");
    public static final ResourceLocation DEAD_BUSH = new ResourceLocation(MOD_ID, "dead_bush");
    public static final ResourceLocation DESERT_FLAME = new ResourceLocation(MOD_ID, "desert_flame");
    public static final ResourceLocation HELICONIA = new ResourceLocation(MOD_ID, "heliconia");
    public static final ResourceLocation HIBISCUS = new ResourceLocation(MOD_ID, "hibiscus");
    public static final ResourceLocation HUNGARIAN_LILAC = new ResourceLocation(MOD_ID, "hungarian_lilac"); // Lilac
    public static final ResourceLocation KANGAROO_PAW = new ResourceLocation(MOD_ID, "kangaroo_paw");
    public static final ResourceLocation KING_FERN = new ResourceLocation(MOD_ID, "king_fern");
    public static final ResourceLocation LIPSTICK_PALM = new ResourceLocation(MOD_ID, "lipstick_palm"); // Red Sealing Wax Palm
    public static final ResourceLocation MARIGOLD = new ResourceLocation(MOD_ID, "marigold");
    public static final ResourceLocation MONSTERA_EPIPHYTE = new ResourceLocation(MOD_ID, "monstera_epiphyte");
    public static final ResourceLocation MONSTERA_GROUND = new ResourceLocation(MOD_ID, "monstera_ground");
    public static final ResourceLocation PHRAGMITE = new ResourceLocation(MOD_ID, "phragmite");
    public static final ResourceLocation PICKERELWEED = new ResourceLocation(MOD_ID, "pickerelweed");
    public static final ResourceLocation RADDIA_GRASS = new ResourceLocation(MOD_ID, "raddia_grass");
    public static final ResourceLocation SCARLET_STAR_BROMELIAD = new ResourceLocation(MOD_ID, "scarlet_star_bromeliad");
    public static final ResourceLocation SILVER_SPURFLOWER = new ResourceLocation(MOD_ID, "silver_spurflower");
    public static final ResourceLocation WATER_TARO = new ResourceLocation(MOD_ID, "water_taro");

    @SubscribeEvent
    public static void onPreRegisterPlant(TFCRegistryEvent.RegisterPreBlock<Plant> event)
    {
        event.getRegistry().registerAll(
            new Plant(BAMBOO, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 15f, 40f, 10f, 50f, 270f, 500f, 6, 15, 12, 0D, "bamboo"),
            new Plant(BROMELIA_HEMISPHERICA, Plant.PlantType.STANDARD, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 16f, 40f, 10f, 50f, 250f, 500f, 0, 15, 1, 0.8D, null),
            new Plant(BROMELIA_LACINIOSA, Plant.PlantType.STANDARD, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 16f, 40f, 10f, 50f, 250f, 500f, 0, 15, 1, 0.8D, null),
            new Plant(BUNCH_GRASS_FLOATING, Plant.PlantType.FLOATING, new int[] {0, 1, 1, 2, 2, 3, 3, 2, 2, 1, 1, 0}, false, false, -10f, 35f, -15f, 45f, 100f, 400f, 0, 15, 1, 1, 1, 0.7D, "reed"),
            new Plant(BUNCH_GRASS_REED, Plant.PlantType.TALL_REED, new int[] {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0}, false, false, -10f, 35f, -15f, 45f, 100f, 400f, 0, 15, 2, 0.7D, "reed"),
            new Plant(BURNING_BUSH, Plant.PlantType.STANDARD, new int[] {0, 1, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0}, false, false, 11f, 21f, -29f, 34f, 125f, 300f, 6, 15, 1, 0.5D, null),
            new Plant(CATTAIL, Plant.PlantType.FLOATING, new int[] {4, 0, 1, 1, 1, 2, 2, 2, 2, 3, 3, 4}, false, false, -16f, 28f, -36f, 40f, 150f, 500f, 0, 15, 1, 1, 1, 0.5D, "cattail"),
            new Plant(CAT_GRASS, Plant.PlantType.SHORT_GRASS, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 10f, 18f, -46f, 32f, 150f, 300f, 0, 15, 1, 0.7D, null),
            //new Plant(CAVE_VINES, Plant.PlantType.HANGING, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -50f, 50f, -50f, 50f, 0f, 500f, 0, 5, 32, 0.7D, null),
            new Plant(CHAMOMILE, Plant.PlantType.STANDARD, new int[] {0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 0, 0}, false, false, 5f, 25f, -40f, 40f, 75f, 400f, 7, 15, 1, 0.9D, "chamomile"),
            new Plant(CHAPARRAL_SHRUB, Plant.PlantType.DRY, new int[] {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0}, false, false, 10f, 40f, 0f, 60f, 100f, 250f, 0, 15, 1, 0.1D, "shrub"),
            new Plant(CINNAMON_FERN, Plant.PlantType.SHORT_GRASS, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 18f, 25f, -40f, 30f, 190f, 500f, 5, 15, 1, 0.5D, null),
            new Plant(CORD_GRASS, Plant.PlantType.TALL_REED, new int[] {0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0}, false, false, 17f, 40f, 0f, 50f, 190f, 500f, 0, 15, 2, 0.5D, null),
            new Plant(CROWNGRASS, Plant.PlantType.SHORT_GRASS, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 18f, 30f, -46f, 38f, 150f, 500f, 0, 15, 1, 0.7D, null),
            new Plant(DEVILS_TONGUE, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 16f, 45f, 10f, 50f, 270f, 500f, 0, 15, 2, 0.9D, "devils_tongue"),
            //new Plant(GLOW_VINES, Plant.PlantType.HANGING, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -50f, 50f, -50f, 50f, 0f, 500f, 0, 5, 32, 0.7D, null),
            new Plant(GOOSEGRASS, Plant.PlantType.SHORT_GRASS, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 13f, 20f, -29f, 30f, 75f, 300f, 0, 15, 1, 0.7D, null),
            new Plant(HALFA_GRASS, Plant.PlantType.SHORT_GRASS, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 3f, 25f, -15f, 32f, 100f, 150f, 0, 15, 1, 0.7D, null),
            new Plant(HYDRANGEA, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0}, false, false, 11f, 31f, -29f, 34f, 125f, 300f, 8, 15, 2, 0.9D, "hydrangea"),
            //new Plant(ICICLE, Plant.PlantType.HANGING, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -50f, 0f, -50f, 0f, 0f, 500f, 0, 15, 2, 0.9D, null),
            new Plant(IVY, Plant.PlantType.CREEPING, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -4f, 14f, -7f, 17f, 90f, 470f, 0, 15, 14, 0.5D, "ivy"),
            new Plant(JAPANESE_PIERIS, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 1, 1, 2, 2, 2, 1, 1, 1, 0, 0}, false, false, 15f, 25f, -29f, 30f, 220f, 500f, 6, 15, 1, 0.5D, null),
            new Plant(KAIETEUR_FALLS, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0}, false, false, 16f, 40f, 10f, 50f, 250f, 500f, 0, 15, 2, 0.8D, null),
            new Plant(LAVANDULA, Plant.PlantType.STANDARD, new int[] {5, 0, 0, 1, 2, 2, 3, 3, 3, 3, 4, 5}, false, false, 5f, 35f, -29f, 40f, 0f, 300f, 7, 15, 1, 0.8D, "lavender"),
            new Plant(LEAF_LITTER, Plant.PlantType.SHORT_GRASS, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -20f, 50f, -30f, 50f, 70f, 500f, 0, 14, 1, 0.9D, null),
            new Plant(LEYMUS, Plant.PlantType.DRY_TALL_PLANT, new int[] {0, 1, 1, 2, 2, 3, 3, 2, 2, 1, 1, 0}, false, false, 15f, 35f, -12f, 40f, 80f, 180f, 0, 15, 1, 0.7D, null),
            new Plant(LILAC, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0}, false, false, 11f, 31f, -29f, 34f, 125f, 300f, 8, 15, 2, 0.9D, "lilac"),
            new Plant(LILY_OF_THE_VALLEY, Plant.PlantType.STANDARD, new int[] {5, 0, 0, 1, 2, 2, 3, 3, 3, 3, 4, 5}, false, false, 5f, 35f, -29f, 40f, 0f, 300f, 8, 15, 1, 0.8D, "lily_of_the_valley"),
            new Plant(LOW_UNDERGROWTH, Plant.PlantType.SHORT_GRASS, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 18f, 40f, -1f, 50f, 250f, 500f, 0, 14, 1, 0.9D, null),
            new Plant(MARRAM_GRASS, Plant.PlantType.SHORT_GRASS, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 15f, 50f, -10f, 50f, 70f, 120f, 0, 15, 1, 0.7D, null),
            new Plant(MATTEUCCIA, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0}, false, false, 20f, 40f, 0f, 50f, 210f, 500f, 0, 15, 2, 0.5D, null),
            new Plant(OCOTILLO, Plant.PlantType.DRY_TALL_PLANT, new int[] {0, 0, 1, 1, 2, 2, 2, 1, 1, 0, 0, 0}, false, false, 15f, 50f, -10f, 50f, 70f, 90f, 9, 15, 2, 0.9D, "ocotillo"),
            new Plant(PAPYRUS, Plant.PlantType.STANDARD, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 17f, 40f, 10f, 50f, 150f, 400f, 0, 15, 1, 0.8D, "papyrus"),
            new Plant(PEONY, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0}, false, false, 11f, 21f, -29f, 34f, 125f, 300f, 6, 15, 2, 0.9D, "peony"),
            new Plant(PRAIRIE_JUNEGRASS, Plant.PlantType.SHORT_GRASS, new int[] {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0}, false, false, 13f, 25f, -29f, 32f, 75f, 200f, 0, 15, 1, 0.7D, null),
            new Plant(REED_MANNAGRASS, Plant.PlantType.TALL_REED, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 18f, 40f, 0f, 210f, 200f, 500f, 0, 15, 1, 0.7D, null),
            new Plant(RESIN, Plant.PlantType.EPIPHYTE, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 10f, 45f, 5f, 50f, 50f, 500f, 0, 14, 1, 0.0D, "resin"),
            //new Plant(ROOTS, Plant.PlantType.HANGING, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -50f, 50f, -50f, 50f, 0f, 500f, 0, 5, 2, 0.9D, null),
            new Plant(SEA_OATS, Plant.PlantType.DESERT_TALL_PLANT, new int[] {0, 1, 1, 2, 2, 3, 3, 2, 2, 1, 1, 0}, false, false, 3f, 25f, -15f, 32f, 100f, 300f, 0, 15, 2, 0.7D, null),
            new Plant(SHRUB, Plant.PlantType.TALL_GRASS, new int[] {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0}, false, false, -10f, 40f, 0f, 50f, 70f, 500f, 0, 15, 1, 0.1D, null),
            new Plant(SUGAR_CANE, Plant.PlantType.TALL_REED, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 18f, 40f, 10f, 40f, 300f, 500f, 0, 15, 3, 0.5D, "sugarcane"),
            new Plant(SUNFLOWER, Plant.PlantType.TALL_PLANT, new int[] {9, 0, 1, 2, 3, 4, 5, 5, 6, 7, 8, 9}, false, false, 8f, 31f, -29f, 34f, 100f, 300f, 6, 15, 2, 0.9D, "sunflower"),
            new Plant(TACKWEED, Plant.PlantType.CREEPING, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -2f, 25f, -10f, 40f, 250f, 500f, 0, 14, 1, 0.7D, "moss"),
            new Plant(TAKAKIA, Plant.PlantType.CREEPING, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -2f, 25f, -10f, 40f, 250f, 500f, 0, 14, 1, 0.7D, "moss"),
            new Plant(UNDERGROWTH_SHRUB, Plant.PlantType.TALL_PLANT, new int[] {4, 4, 0, 1, 1, 1, 2, 2, 2, 3, 3, 4}, false, false, -10f, 40f, 0f, 50f, 70f, 500f, 0, 15, 3, 0.2D, null),
            new Plant(UNDERGROWTH_SHRUB_SMALL, Plant.PlantType.TALL_PLANT, new int[] {4, 4, 0, 1, 1, 1, 2, 2, 2, 3, 3, 4}, false, false, -10f, 40f, 0f, 50f, 70f, 500f, 0, 15, 2, 0.3D, null),
            new Plant(VOODOO_LILY, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 16f, 45f, 10f, 50f, 270f, 500f, 0, 15, 2, 0.9D, "voodoo_lily"),
            new Plant(WHEATGRASS, Plant.PlantType.SHORT_GRASS, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 3f, 25f, -15f, 32f, 100f, 300f, 0, 15, 1, 0.7D, null),
            //new Plant(WILD_BARLEY, Plant.PlantType.SHORT_GRASS, new int[] {0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0}, false, false, 1f, 26f, -2f, 33f, 70f, 310f, 0, 15, 1, 0.7D, null),
            //new Plant(WILD_RICE, Plant.PlantType.SHORT_GRASS, new int[] {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0}, false, true, 22f, 40f, 20f, 45f, 300f, 450f, 0, 15, 1, 0.7D, null),
            //new Plant(WILD_WHEAT, Plant.PlantType.SHORT_GRASS, new int[] {0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0}, false, false, 0f, 30f, -2f, 34f, 100f, 350f, 0, 15, 1, 0.7D, null),
            new Plant(WOOLLY_BUSH, Plant.PlantType.SHORT_GRASS, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 20f, 30f, 5f, 33f, 85f, 190f, 0, 15, 1, 0.7D, null),
            new Plant(SAWGRASS, Plant.PlantType.TALL_GRASS, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -16f, 32f, -36f, 50f, 100f, 500f, 0, 15, 2, 1, 1, 0.7D, null),
            new Plant(SAGUARO_CACTUS, Plant.PlantType.CACTUS, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 18f, 40f, -6f, 50f, 0f, 75f, 12, 15, 15, 0D, "blockCactus"),

            // Epiphytes
            new Plant(APACHE_DWARF, Plant.PlantType.EPIPHYTE, new int[] {0, 1, 1, 2, 2, 3, 3, 2, 2, 1, 1, 0}, false, false, 0f, 40f, 5f, 50f, 100f, 500f, 0, 15, 1, 0.8D, null),
            new Plant(ARTISTS_CONK, Plant.PlantType.EPIPHYTE, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 0f, 40f, -12f, 50f, 200f, 500f, 0, 15, 1, 0.8D, "epiphyte_artists_conk"),
            new Plant(CLIMBING_CACTUS, Plant.PlantType.EPIPHYTE, new int[] {0, 1, 1, 2, 2, 3, 3, 2, 2, 1, 1, 0}, false, false, 11f, 31f, -29f, 34f, 0f, 150f, 0, 15, 1, 0.8D, null),
            new Plant(CRIMSON_CATTLEYA, Plant.PlantType.EPIPHYTE, new int[] {0, 1, 1, 2, 2, 3, 3, 2, 2, 1, 1, 0}, false, false, 15f, 40f, 10f, 50f, 270f, 500f, 0, 15, 1, 0.8D, null),
            new Plant(CREEPING_MISTLETOE, Plant.PlantType.EPIPHYTE, new int[] {0, 1, 1, 2, 2, 3, 3, 2, 2, 1, 1, 0}, false, false, 5f, 35f, -29f, 40f, 0f, 300f, 0, 15, 1, 0.8D, null),
            new Plant(CUTHBERTS_DENDROBIUM, Plant.PlantType.EPIPHYTE, new int[] {0, 1, 1, 2, 2, 3, 3, 2, 2, 1, 1, 0}, false, false, 15f, 40f, 10f, 50f, 270f, 500f, 0, 15, 1, 0.8D, null),
            new Plant(FISH_BONE_CACTUS, Plant.PlantType.EPIPHYTE, new int[] {0, 1, 1, 2, 2, 3, 3, 2, 2, 1, 1, 0}, false, false, 15f, 40f, 10f, 50f, 130f, 300f, 0, 15, 1, 0.8D, null),
            new Plant(FRAGRANT_FERN, Plant.PlantType.EPIPHYTE, new int[] {0, 1, 1, 2, 2, 3, 3, 2, 2, 1, 1, 0}, false, false, 0f, 40f, -5f, 50f, 200f, 500f, 0, 15, 1, 0.8D, null),
            new Plant(HARLEQUIN_MISTLETOE, Plant.PlantType.EPIPHYTE, new int[] {0, 1, 1, 2, 2, 3, 3, 2, 2, 1, 1, 0}, false, false, 0f, 40f, -12f, 50f, 200f, 500f, 0, 15, 1, 0.8D, null),
            new Plant(KING_ORCHID, Plant.PlantType.EPIPHYTE, new int[] {0, 1, 1, 2, 2, 3, 3, 2, 2, 1, 1, 0}, false, false, 0f, 40f, -5f, 50f, 190f, 500f, 0, 15, 1, 0.8D, null),
            new Plant(LANTERN_OF_THE_FOREST, Plant.PlantType.EPIPHYTE, new int[] {0, 1, 1, 2, 2, 3, 3, 2, 2, 1, 1, 0}, false, false, 0f, 40f, -5f, 50f, 190f, 500f, 0, 15, 1, 0.8D, null),
            new Plant(LARGE_FOOT_DENDROBIUM, Plant.PlantType.EPIPHYTE, new int[] {0, 1, 1, 2, 2, 3, 3, 2, 2, 1, 1, 0}, false, false, 0f, 40f, -5f, 50f, 190f, 500f, 0, 15, 1, 0.8D, null),
            new Plant(COMMON_MISTLETOE, Plant.PlantType.EPIPHYTE, new int[] {0, 1, 1, 2, 2, 3, 3, 2, 2, 1, 1, 0}, false, false, 5f, 35f, -29f, 40f, 0f, 300f, 0, 15, 1, 0.8D, null),
            new Plant(SKY_PLANT, Plant.PlantType.EPIPHYTE, new int[] {0, 1, 1, 2, 2, 3, 3, 2, 2, 1, 1, 0}, false, false, 15f, 40f, 10f, 50f, 130f, 300f, 0, 15, 1, 0.8D, null),
            new Plant(SULPHUR_SHELF, Plant.PlantType.EPIPHYTE, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 0f, 40f, -12f, 50f, 200f, 500f, 0, 15, 1, 0.8D, "epiphyte_sulphur_shelf"),
            new Plant(TAMPA_BUTTERFLY_ORCHID, Plant.PlantType.EPIPHYTE, new int[] {0, 1, 1, 2, 2, 3, 3, 2, 2, 1, 1, 0}, false, false, 0f, 40f, -5f, 50f, 190f, 500f, 0, 15, 1, 0.8D, null),
            new Plant(TURKEY_TAIL, Plant.PlantType.EPIPHYTE, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 0f, 40f, -12f, 50f, 200f, 500f, 0, 15, 1, 0.8D, "epiphyte_turkey_tail"),
            new Plant(WILDFIRE, Plant.PlantType.EPIPHYTE, new int[] {0, 1, 1, 2, 2, 3, 3, 2, 2, 1, 1, 0}, false, false, 15f, 40f, 10f, 50f, 130f, 300f, 0, 15, 1, 0.8D, null),

            // Mushrooms
            new Plant(AMANITA, Plant.PlantType.MUSHROOM, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -11f, 48f, -13f, 50f, 250f, 500f, 0, 12, 1, 0.8D, "mushroom_red"),
            new Plant(BLACK_POWDERPUFF, Plant.PlantType.MUSHROOM, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -11f, 48f, -13f, 50f, 250f, 500f, 0, 12, 1, 0.8D, "mushroom_black_powderpuff"),
            new Plant(CHANTERELLE, Plant.PlantType.MUSHROOM, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -11f, 48f, -13f, 50f, 250f, 500f, 0, 12, 1, 0.8D, "mushroom_chanterelle"),
            new Plant(DEATH_CAP, Plant.PlantType.MUSHROOM, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -11f, 48f, -13f, 50f, 250f, 500f, 0, 12, 1, 0.8D, "mushroom_death_cap"),
            new Plant(GIANT_CLUB, Plant.PlantType.MUSHROOM, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -11f, 48f, -13f, 50f, 250f, 500f, 0, 12, 1, 0.8D, "mushroom_giant_club"),
            new Plant(PARASOL_MUSHROOM, Plant.PlantType.MUSHROOM, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -11f, 48f, -13f, 50f, 250f, 500f, 0, 12, 1, 0.8D, "mushroom_parasol"),
            new Plant(STINKHORN, Plant.PlantType.MUSHROOM, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -11f, 48f, -13f, 50f, 250f, 500f, 0, 12, 1, 0.8D, "mushroom_stinkhorn"),
            new Plant(WEEPING_MILK_CAP, Plant.PlantType.MUSHROOM, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -11f, 48f, -13f, 50f, 250f, 500f, 0, 12, 1, 0.8D, "mushroom_weeping_milk_cap"),
            new Plant(WOOD_BLEWIT, Plant.PlantType.MUSHROOM, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -11f, 48f, -13f, 50f, 250f, 500f, 0, 12, 1, 0.8D, "mushroom_wood_blewit"),
            new Plant(WOOLLY_GOMPHUS, Plant.PlantType.MUSHROOM, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -11f, 48f, -13f, 50f, 250f, 500f, 0, 12, 1, 0.8D, "mushroom_woolly_gomphus"),

            // Glowing Cave Mushrooms
            // new Plant(GLOWSHROOM, Plant.PlantType.MUSHROOM, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -11f, 48f, -13f, 50f, 250f, 500f, 0, 5, 1, 0.8D, null),

            // Large Plants
            new Plant(BELL_TREE_DAHLIA, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0}, false, false, 20f, 30f, -12f, 36f, 75f, 200f, 0, 15, 2, 0.2D, null),
            new Plant(BIG_LEAF_PALM, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 20f, 40f, 10f, 50f, 250f, 500f, 0, 15, 2, 0.2D, null),
            new Plant(DRAKENSBERG_CYCAD, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 20f, 30f, -34f, 36f, 0f, 75f, 0, 15, 2, 0.2D, null),
            new Plant(DWARF_SUGAR_PALM, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 20f, 40f, 10f, 50f, 300f, 500f, 0, 15, 2, 0.2D, null),
            new Plant(GIANT_CANE, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 18f, 30f, -12f, 36f, 150f, 500f, 0, 15, 2, 0.2D, "sugarcane"),
            new Plant(GIANT_ELEPHANT_EAR, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 20f, 40f, 10f, 50f, 250f, 500f, 0, 15, 2, 0.2D, null),
            new Plant(GIANT_FEATHER_GRASS, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0}, false, false, 15f, 23f, -29f, 32f, 75f, 300f, 0, 15, 2, 0.2D, null),
            new Plant(MADAGASCAR_OCOTILLO, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 20f, 30f, -34f, 36f, 0f, 75f, 0, 15, 2, 0.2D, null),
            new Plant(MALAGASY_TREE_ALOE, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 20f, 30f, -34f, 36f, 0f, 75f, 0, 15, 2, 0.2D, null),
            new Plant(MOUNTAIN_CABBAGE_TREE, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 20f, 40f, 10f, 50f, 250f, 500f, 0, 15, 2, 0.2D, null),
            new Plant(PYGMY_DATE_PALM, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 20f, 40f, 10f, 50f, 250f, 500f, 0, 15, 2, 0.2D, null),
            new Plant(QUEEN_SAGO, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 20f, 40f, 10f, 50f, 250f, 500f, 0, 15, 2, 0.2D, null),
            new Plant(RED_SEALING_WAX_PALM, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 20f, 40f, 10f, 50f, 250f, 500f, 0, 15, 2, 0.2D, null),
            new Plant(SUMMER_ASPHODEL, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0}, false, false, 15f, 25f, -34f, 33f, 150f, 300f, 0, 15, 2, 0.2D, null),
            new Plant(ZIMBABWE_ALOE, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 20f, 30f, -34f, 36f, 0f, 75f, 0, 15, 2, 0.2D, null),

            // Plants courtesy of Therighton
            new Plant(ANTHURIUM, Plant.PlantType.STANDARD, new int[] {0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1}, false, false, 12f, 40f, -3f, 50f, 290f, 500f, 0, 15, 1, 0.8D, "anthurium"),
            new Plant(ARROWHEAD, Plant.PlantType.FLOATING, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -10f, 22f, -25f, 37f, 180f, 500f, 0, 15, 1, 1, 1, 0.6D, "arrowhead"),
            new Plant(ARUNDO, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 15f, 30f, 0f, 45f, 120f, 500f, 0, 15, 8, 0.5D, "arundo"),
            //new Plant(AZURE_BLUET, Plant.PlantType.STANDARD, new int[] {2, 2, 2, 0, 1, 1, 1, 2, 2, 2, 2, 2}, false, false, -12f, 10f, -27f, 25f, 150f, 500f, 0, 15, 1, 0.9D, "azure_bluet"),
            new Plant(BLUEGRASS, Plant.PlantType.SHORT_GRASS, new int[] {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0}, false, false, -4f, 12f, -19f, 27f, 110f, 280f, 0, 15, 1, 0.8D, null),
            new Plant(BLUE_GINGER, Plant.PlantType.STANDARD, new int[] {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1}, false, false, 16f, 26f, 1f, 41f, 300f, 450f, 0, 15, 1, 0.8D, "ginger"),
            //new Plant(BROADLEAF_CATTAIL, Plant.PlantType.TALL_WATER, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -16f, 22f, -31f, 37f, 150f, 500f, 0, 15, 1, 1, 64, 0.6D, "broadleaf_cattail"),
            new Plant(BROMEGRASS, Plant.PlantType.SHORT_GRASS, new int[] {0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0}, false, false, 4f, 20f, -11f, 35f, 140f, 360f, 0, 15, 1, 0.8D, null),
            //new Plant(BUR_MARIGOLD, Plant.PlantType.TALL_WATER, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -8f, 18f, -23f, 33f, 50f, 390f, 0, 15, 1, 1, 64, 0.4D, "bur_marigold"),
            new Plant(BUR_REED, Plant.PlantType.FLOATING, new int[] {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}, false, false, -16f, 4f, -31f, 19f, 250f, 400f, 0, 15, 1, 1, 1, 0.6D, "bur_reed"),
            //new Plant(CANNA_LILY, Plant.PlantType.STANDARD, new int[] {0, 0, 0, 0, 0, 1, 2, 3, 3, 3, 3, 0}, true, false, 10f, 40f, -5f, 50f, 270f, 500f, 0, 15, 1, 0.8D, "canna_lily"),
            //new Plant(COCKLESHELL_ORCHID, Plant.PlantType.STANDARD, new int[] {2, 2, 2, 0, 1, 1, 1, 2, 2, 2, 2, 2}, false, false, 14f, 40f, -1f, 50f, 290f, 410f, 0, 15, 1, 0.8D, "cockleshell_orchid"),
            new Plant(DEAD_BUSH, Plant.PlantType.DRY_TALL_PLANT, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -12f, 40f, -27f, 50f, 70f, 120f, 0, 15, 1, 0.9D, "dead_bush"),
            new Plant(DESERT_FLAME, Plant.PlantType.STANDARD, new int[] {1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1}, false, false, 0f, 20f, -15f, 35f, 40f, 170f, 0, 15, 1, 0.8D, "desert_flame"),
            new Plant(HELICONIA, Plant.PlantType.STANDARD, new int[] {0, 0, 1, 2, 0, 0, 0, 0, 1, 2, 0, 0}, false, false, 14f, 40f, -1f, 50f, 320f, 500f, 0, 15, 1, 0.8D, "heliconia"),
            new Plant(HIBISCUS, Plant.PlantType.TALL_PLANT, new int[] {2, 2, 2, 0, 0, 0, 0, 0, 0, 1, 2, 2}, false, false, 10f, 24f, -10f, 39f, 260f, 450f, 0, 15, 2, 0.9D, "hibiscus"),
            //new Plant(HUNGARIAN_LILAC, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0}, false, false, -10f, 6f, -25f, 21f, 150f, 300f, 0, 15, 2, 0.7D, "hungarian_lilac"),
            new Plant(KANGAROO_PAW, Plant.PlantType.STANDARD, new int[] {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, false, false, 14f, 40f, -1f, 50f, 100f, 300f, 0, 15, 1, 0.8D, "kangaroo_paw"),
            new Plant(KING_FERN, Plant.PlantType.TALL_GRASS, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 18f, 40f, -7f, 50f, 350f, 500f, 0, 15, 2, 0.4D, null),
            new Plant(LIPSTICK_PALM, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 18f, 40f, 3f, 50f, 280f, 500f, 0, 15, 2, 0.4D, "lipstick_palm"),
            new Plant(MARIGOLD, Plant.PlantType.TALL_PLANT, new int[] {0, 0, 0, 0, 0, 1, 2, 3, 3, 3, 3, 0}, false, false, 4f, 22f, -11f, 37f, 130f, 300f, 0, 15, 2, 0.8D, "marigold"),
            new Plant(MONSTERA_EPIPHYTE, Plant.PlantType.EPIPHYTE, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 20f, 40f, 0f, 50f, 200f, 500f, 0, 15, 1, 0.9D, "monstera"),
            new Plant(MONSTERA_GROUND, Plant.PlantType.TALL_GRASS, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 20f, 40f, 0f, 50f, 200f, 500f, 0, 15, 2, 0.9D, null),
            new Plant(PHRAGMITE, Plant.PlantType.FLOATING, new int[] {0, 0, 0, 1, 1, 1, 2, 2, 3, 1, 1, 0}, false, false, -6f, 18f, -21f, 33f, 50f, 250f, 0, 15, 1, 1, 1, 0.6D, "phragmite"),
            new Plant(PICKERELWEED, Plant.PlantType.FLOATING, new int[] {0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 0, 0}, false, false, -14f, 16f, -29f, 31f, 200f, 500f, 0, 15, 1, 1, 1, 0.6D, "pickerelweed"),
            new Plant(RADDIA_GRASS, Plant.PlantType.SHORT_GRASS, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 18f, 31f, 3f, 48f, 75f, 295f, 0, 15, 1, 0.8D, null),
            //new Plant(SCARLET_STAR_BROMELIAD, Plant.PlantType.EPIPHYTE, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 20f, 40f, 5f, 50f, 290f, 480f, 0, 15, 6, 0.9D, "scarlet_star_bromeliad"),
            new Plant(SILVER_SPURFLOWER, Plant.PlantType.STANDARD, new int[] {0, 0, 0, 0, 1, 2, 2, 2, 0, 0, 0, 0}, false, false, 14f, 24f, -1f, 39f, 230f, 400f, 0, 15, 1, 0.8D, "silver_spurflower"),
            new Plant(WATER_TARO, Plant.PlantType.FLOATING, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 12f, 40f, -3f, 50f, 260f, 500f, 0, 15, 1, 1, 1, 0.6D, "water_taro")
        );

        // Vine/Ivy
        if (ConfigTFCF.General.WORLD.enableAllVines)
        {
            event.getRegistry().register(new Plant(RATTAN, Plant.PlantType.HANGING, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 18f, 40f, 10f, 50f, 210f, 500f, 0, 15, 5, 0.5D, "vine"));
            event.getRegistry().register(new Plant(BEARDED_MOSS, Plant.PlantType.HANGING, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, true, -10f, 22f, -25f, 37f, 180f, 500f, 0, 15, 10, 0.7D, "vine")); // Special
            event.getRegistry().register(new Plant(BLUE_SKYFLOWER, Plant.PlantType.HANGING, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 0f, 40f, 15f, 50f, 300f, 500f, 0, 15, 5, 0.5D, "vine"));
            event.getRegistry().register(new Plant(GLOW_VINE, Plant.PlantType.HANGING, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 18f, 50f, 10f, 50f, 210f, 500f, 0, 15, 15, 0.7D, "vine")); // Special
            event.getRegistry().register(new Plant(HANGING_VINE, Plant.PlantType.HANGING, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 20f, 50f, 13f, 50f, 150f, 470f, 0, 15, 22, 0.5D, "vine")); // Special
            event.getRegistry().register(new Plant(JADE_VINE, Plant.PlantType.HANGING, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 20f, 40f, 10f, 50f, 250f, 500f, 0, 15, 5, 0.5D, "vine"));
            event.getRegistry().register(new Plant(JAPANESE_IVY, Plant.PlantType.HANGING, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 20f, 35f, -6f, 36f, 120f, 300f, 0, 15, 5, 0.5D, "vine"));
            event.getRegistry().register(new Plant(JUNGLE_VINE, Plant.PlantType.HANGING, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 20f, 50f, 15f, 50f, 150f, 470f, 0, 15, 22, 0.5D, "vine")); // Special
            event.getRegistry().register(new Plant(LIANA, Plant.PlantType.HANGING, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 20f, 50f, 15f, 50f, 150f, 470f, 0, 15, 16, 0.5D, "vine")); // Special
            event.getRegistry().register(new Plant(MADEIRA_VINE, Plant.PlantType.HANGING, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 20f, 40f, 5f, 50f, 100f, 350f, 0, 15, 5, 0.5D, "vine"));
            event.getRegistry().register(new Plant(MYSORE_TRUMPETVINE, Plant.PlantType.HANGING, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 20f, 40f, 10f, 50f, 250f, 500f, 0, 15, 5, 0.5D, "vine"));
            event.getRegistry().register(new Plant(SILVERVEIN_CREEPER, Plant.PlantType.HANGING, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 0f, 40f, 15f, 50f, 300f, 500f, 0, 15, 5, 0.5D, "vine"));
            event.getRegistry().register(new Plant(SWEDISH_IVY, Plant.PlantType.HANGING, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 20f, 40f, 5f, 50f, 50f, 300f, 0, 15, 5, 0.5D, "vine"));
            event.getRegistry().register(new Plant(VARIEGATED_PERSIAN_IVY, Plant.PlantType.HANGING, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 20f, 40f, 5f, 50f, 50f, 300f, 0, 15, 5, 0.5D, "vine"));
        }

        // Water Plants
        if (ConfigTFCF.General.WORLD.enableAllWaterPlants)
        {
            event.getRegistry().register(new Plant(BADDERLOCKS, Plant.PlantType.TALL_WATER_SEA, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -31f, 17f, -33f, 25f, 150f, 500f, 0, 15, 6, 1, 256, 0.8D, "seaweed"));
            //event.getRegistry().register(new Plant(BROWN_ALGAE, Plant.PlantType.WATER, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -37f, 11f, -39f, 13f, 100f, 500f, 0, 15, 1, 1, 256, 0.6D, "seaweed"));
            event.getRegistry().register(new Plant(COONTAIL, Plant.PlantType.WATER, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -11f, 31f, -13f, 33f, 250f, 500f, 0, 15, 1, 1, 256, 0.7D, "seaweed")); //3, 0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2
            event.getRegistry().register(new Plant(EEL_GRASS, Plant.PlantType.WATER, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -7f, 48f, -9f, 50f, 200f, 500f, 0, 15, 1, 1, 256, 0.9D, "seaweed")); //3, 0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2
            event.getRegistry().register(new Plant(GIANT_KELP, Plant.PlantType.TALL_WATER_SEA, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -31f, 38f, -33f, 43f, 0f, 500f, 0, 15, 26, 1, 256, 0.9D, "seaweed"));
            event.getRegistry().register(new Plant(GUTWEED, Plant.PlantType.WATER, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -19f, 31f, -21f, 33f, 100f, 500f, 0, 15, 1, 1, 256, 0.9D, "seaweed"));
            event.getRegistry().register(new Plant(HORNWORT, Plant.PlantType.WATER, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -11f, 31f, -13f, 33f, 250f, 500f, 0, 15, 1, 1, 256, 0.7D, "seaweed"));
            event.getRegistry().register(new Plant(LAMINARIA, Plant.PlantType.WATER_SEA, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -29f, 17f, -31f, 19f, 250f, 400f, 0, 15, 1, 1, 256, 0.6D, "seaweed"));
            event.getRegistry().register(new Plant(LEAFY_KELP, Plant.PlantType.TALL_WATER_SEA, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -33f, 37f, -35f, 41f, 0f, 500f, 0, 15, 21, 1, 256, 0.9D, "seaweed"));
            event.getRegistry().register(new Plant(MANATEE_GRASS, Plant.PlantType.WATER, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -1f, 48f, -3f, 50f, 250f, 500f, 0, 15, 1, 1, 256, 0.9D, "seaweed")); //3, 0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2
            event.getRegistry().register(new Plant(MILFOIL, Plant.PlantType.WATER, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -27f, 35f, -29f, 37f, 250f, 500f, 0, 15, 1, 1, 256, 0.7D, "seaweed"));
            event.getRegistry().register(new Plant(PONDWEED, Plant.PlantType.TALL_WATER, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -31f, 31f, -33f, 33f, 200f, 500f, 0, 15, 5, 1, 256, 0.7D, "seaweed"));
            event.getRegistry().register(new Plant(RED_ALGAE, Plant.PlantType.WATER_SEA, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 1f, 48f, -1f, 50f, 150f, 500f, 0, 15, 1, 1, 256, 0.9D, "seaweed"));
            event.getRegistry().register(new Plant(RED_SEA_WHIP, Plant.PlantType.WATER_SEA, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 1f, 48f, -1f, 50f, 150f, 500f, 0, 15, 1, 1, 256, 0.9D, "seaweed"));
            event.getRegistry().register(new Plant(SAGO, Plant.PlantType.WATER, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -32f, 36f, -34f, 38f, 0f, 500f, 0, 15, 1, 1, 256, 0.9D, "seaweed"));
            event.getRegistry().register(new Plant(SEA_ANEMONE, Plant.PlantType.WATER_SEA, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 1f, 48f, -1f, 50f, 150f, 500f, 0, 15, 1, 1, 256, 0.9D, "seaweed"));
            event.getRegistry().register(new Plant(SEAGRASS, Plant.PlantType.TALL_WATER_SEA, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -20f, 50f, -30f, 50f, 0f, 500f, 0, 15, 2, 1, 256, 0.9D, "seaweed"));
            event.getRegistry().register(new Plant(SEAWEED, Plant.PlantType.WATER_SEA, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -19f, 40f, -21f, 50f, 0f, 500f, 0, 15, 1, 1, 256, 0.9D, "seaweed"));
            event.getRegistry().register(new Plant(STAR_GRASS, Plant.PlantType.WATER_SEA, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -11f, 48f, -13f, 50f, 50f, 260f, 0, 15, 1, 1, 256, 0.9D, "seaweed")); //3, 0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2
            event.getRegistry().register(new Plant(TURTLE_GRASS, Plant.PlantType.WATER_SEA, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, 1f, 48f, -1f, 50f, 240f, 500f, 0, 15, 1, 1, 256, 0.9D, "seaweed")); //3, 0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2
            event.getRegistry().register(new Plant(WINGED_KELP, Plant.PlantType.TALL_WATER_SEA, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, false, false, -28f, 38f, -30f, 42f, 0f, 450f, 0, 15, 21, 1, 256, 0.8D, "seaweed"));
        }
    }
}
