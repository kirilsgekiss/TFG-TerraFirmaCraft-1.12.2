/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.types;

import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.registries.TFCRegistryEvent;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.api.util.ITreeGenerator;
import net.dries007.tfc.world.classic.worldgen.trees.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public final class DefaultTrees {
    //Default Tree ResourceLocations
    public static final ResourceLocation ACACIA = new ResourceLocation(MOD_ID, "acacia");
    public static final ResourceLocation ASH = new ResourceLocation(MOD_ID, "ash");
    public static final ResourceLocation ASPEN = new ResourceLocation(MOD_ID, "aspen");

    //Birch Trees
    public static final ResourceLocation BIRCH = new ResourceLocation(MOD_ID, "birch");
    public static final ResourceLocation SILVER_BIRCH = new ResourceLocation(MOD_ID, "silver_birch");

    public static final ResourceLocation BLACKWOOD = new ResourceLocation(MOD_ID, "blackwood");
    public static final ResourceLocation CHESTNUT = new ResourceLocation(MOD_ID, "chestnut");
    public static final ResourceLocation DOUGLAS_FIR = new ResourceLocation(MOD_ID, "douglas_fir");
    public static final ResourceLocation HICKORY = new ResourceLocation(MOD_ID, "hickory");
    public static final ResourceLocation MAPLE = new ResourceLocation(MOD_ID, "maple");

    //Oak Trees
    public static final ResourceLocation OAK = new ResourceLocation(MOD_ID, "oak");
    public static final ResourceLocation FLOWERING_OAK = new ResourceLocation(MOD_ID, "flowering_oak");
    public static final ResourceLocation DARK_OAK = new ResourceLocation(MOD_ID, "dark_oak");

    public static final ResourceLocation PALM = new ResourceLocation(MOD_ID, "palm");
    public static final ResourceLocation PINE = new ResourceLocation(MOD_ID, "pine");
    public static final ResourceLocation ROSEWOOD = new ResourceLocation(MOD_ID, "rosewood");
    public static final ResourceLocation SEQUOIA = new ResourceLocation(MOD_ID, "sequoia");

    //Spruce Trees
    public static final ResourceLocation SPRUCE = new ResourceLocation(MOD_ID, "spruce");
    public static final ResourceLocation JAPANESE_SPRUCE = new ResourceLocation(MOD_ID, "japanese_spruce");
    public static final ResourceLocation NORWAY_SPRUCE = new ResourceLocation(MOD_ID, "norway_spruce");

    public static final ResourceLocation SYCAMORE = new ResourceLocation(MOD_ID, "sycamore");
    public static final ResourceLocation WHITE_CEDAR = new ResourceLocation(MOD_ID, "white_cedar");
    public static final ResourceLocation WHITE_ELM = new ResourceLocation(MOD_ID, "white_elm");
    public static final ResourceLocation WILLOW = new ResourceLocation(MOD_ID, "willow");
    public static final ResourceLocation WEEPING_WILLOW = new ResourceLocation(MOD_ID, "weeping_willow");
    public static final ResourceLocation KAPOK = new ResourceLocation(MOD_ID, "kapok");


    //Hevea Tree Species
    public static final ResourceLocation HEVEA_BENTHAMIANA = new ResourceLocation(MOD_ID, "hevea_benthamiana");
    public static final ResourceLocation HEVEA_BRAZILIENSIS = new ResourceLocation(MOD_ID, "hevea_braziliensis");
    public static final ResourceLocation HEVEA_CAMARGOANA = new ResourceLocation(MOD_ID, "hevea_camargoana");
    public static final ResourceLocation HEVEA_CAMPORUM = new ResourceLocation(MOD_ID, "hevea_camporum");
    public static final ResourceLocation HEVEA_GUIANENSIS = new ResourceLocation(MOD_ID, "hevea_guianensis");
    public static final ResourceLocation HEVEA_MICROPHYLLA = new ResourceLocation(MOD_ID, "hevea_microphylla");
    public static final ResourceLocation HEVEA_NITIDA = new ResourceLocation(MOD_ID, "hevea_nitida");
    public static final ResourceLocation HEVEA_PAUCIFLORA = new ResourceLocation(MOD_ID, "hevea_pauciflora");
    public static final ResourceLocation HEVEA_RIGIDOFOLIA = new ResourceLocation(MOD_ID, "hevea_rigidifolia");
    public static final ResourceLocation HEVEA_SPRUCEANA = new ResourceLocation(MOD_ID, "hevea_spruceana");


    //Latex Trees
    public static final ResourceLocation SAPODILLA = new ResourceLocation(MOD_ID, "sapodilla");
    public static final ResourceLocation CASTILLA = new ResourceLocation(MOD_ID, "castilla");


    public static final ResourceLocation AFRICAN_PADAUK = new ResourceLocation(MOD_ID, "african_padauk");
    public static final ResourceLocation ALDER = new ResourceLocation(MOD_ID, "alder");
    public static final ResourceLocation ANGELIM = new ResourceLocation(MOD_ID, "angelim");
    public static final ResourceLocation BALD_CYPRESS = new ResourceLocation(MOD_ID, "bald_cypress");
    public static final ResourceLocation BAOBAB = new ResourceLocation(MOD_ID, "baobab");
    public static final ResourceLocation BEECH = new ResourceLocation(MOD_ID, "beech");
    public static final ResourceLocation BLACK_WALNUT = new ResourceLocation(MOD_ID, "black_walnut");
    public static final ResourceLocation BOX = new ResourceLocation(MOD_ID, "box");
    public static final ResourceLocation BRAZILWOOD = new ResourceLocation(MOD_ID, "brazilwood");
    public static final ResourceLocation BUTTERNUT = new ResourceLocation(MOD_ID, "butternut");
    public static final ResourceLocation COCOBOLO = new ResourceLocation(MOD_ID, "cocobolo");
    public static final ResourceLocation CYPRESS = new ResourceLocation(MOD_ID, "cypress");
    public static final ResourceLocation EBONY = new ResourceLocation(MOD_ID, "ebony");
    public static final ResourceLocation EUCALYPTUS = new ResourceLocation(MOD_ID, "eucalyptus");
    public static final ResourceLocation EUROPEAN_OAK = new ResourceLocation(MOD_ID, "european_oak");
    public static final ResourceLocation FEVER = new ResourceLocation(MOD_ID, "fever");
    public static final ResourceLocation FRUITWOOD = new ResourceLocation(MOD_ID, "fruitwood");
    public static final ResourceLocation GINKGO = new ResourceLocation(MOD_ID, "ginkgo");
    public static final ResourceLocation GREENHEART = new ResourceLocation(MOD_ID, "greenheart");
    public static final ResourceLocation HAWTHORN = new ResourceLocation(MOD_ID, "hawthorn");
    public static final ResourceLocation HAZEL = new ResourceLocation(MOD_ID, "hazel");
    public static final ResourceLocation HEMLOCK = new ResourceLocation(MOD_ID, "hemlock");
    public static final ResourceLocation HOLLY = new ResourceLocation(MOD_ID, "holly");
    public static final ResourceLocation HORNBEAM = new ResourceLocation(MOD_ID, "hornbeam");
    public static final ResourceLocation IPE = new ResourceLocation(MOD_ID, "ipe");
    public static final ResourceLocation IROKO = new ResourceLocation(MOD_ID, "iroko");
    public static final ResourceLocation IRONWOOD = new ResourceLocation(MOD_ID, "ironwood");
    public static final ResourceLocation JACARANDA = new ResourceLocation(MOD_ID, "jacaranda");
    public static final ResourceLocation JOSHUA_TREE = new ResourceLocation(MOD_ID, "joshua_tree");
    public static final ResourceLocation JUNIPER = new ResourceLocation(MOD_ID, "juniper");
    public static final ResourceLocation KAURI = new ResourceLocation(MOD_ID, "kauri");
    public static final ResourceLocation LARCH = new ResourceLocation(MOD_ID, "larch");
    public static final ResourceLocation LIMBA = new ResourceLocation(MOD_ID, "limba");
    public static final ResourceLocation LOCUST = new ResourceLocation(MOD_ID, "locust");
    public static final ResourceLocation LOGWOOD = new ResourceLocation(MOD_ID, "logwood");
    public static final ResourceLocation MACLURA = new ResourceLocation(MOD_ID, "maclura");
    public static final ResourceLocation MAHOE = new ResourceLocation(MOD_ID, "mahoe");
    public static final ResourceLocation MAHOGANY = new ResourceLocation(MOD_ID, "mahogany");
    public static final ResourceLocation MANGROVE = new ResourceLocation(MOD_ID, "mangrove");
    public static final ResourceLocation MARBLEWOOD = new ResourceLocation(MOD_ID, "marblewood");
    public static final ResourceLocation MESSMATE = new ResourceLocation(MOD_ID, "messmate");
    public static final ResourceLocation MOUNTAIN_ASH = new ResourceLocation(MOD_ID, "mountain_ash");
    public static final ResourceLocation MULBERRY = new ResourceLocation(MOD_ID, "mulberry");
    public static final ResourceLocation NORDMANN_FIR = new ResourceLocation(MOD_ID, "nordmann_fir");
    public static final ResourceLocation PINK_CHERRY = new ResourceLocation(MOD_ID, "pink_cherry");
    public static final ResourceLocation PINK_IVORY = new ResourceLocation(MOD_ID, "pink_ivory");
    public static final ResourceLocation POPLAR = new ResourceLocation(MOD_ID, "poplar");
    public static final ResourceLocation PURPLEHEART = new ResourceLocation(MOD_ID, "purpleheart");
    public static final ResourceLocation RED_CEDAR = new ResourceLocation(MOD_ID, "red_cedar");
    public static final ResourceLocation RED_ELM = new ResourceLocation(MOD_ID, "red_elm");
    public static final ResourceLocation REDWOOD = new ResourceLocation(MOD_ID, "redwood");
    public static final ResourceLocation ROWAN = new ResourceLocation(MOD_ID, "rowan");
    public static final ResourceLocation RUBBER_FIG = new ResourceLocation(MOD_ID, "rubber_fig");
    public static final ResourceLocation SWEETGUM = new ResourceLocation(MOD_ID, "sweetgum");
    public static final ResourceLocation SYZYGIUM = new ResourceLocation(MOD_ID, "syzygium");
    public static final ResourceLocation TEAK = new ResourceLocation(MOD_ID, "teak");
    public static final ResourceLocation WALNUT = new ResourceLocation(MOD_ID, "walnut");
    public static final ResourceLocation WENGE = new ResourceLocation(MOD_ID, "wenge");
    public static final ResourceLocation WHITE_CHERRY = new ResourceLocation(MOD_ID, "white_cherry");
    public static final ResourceLocation WHITEBEAM = new ResourceLocation(MOD_ID, "whitebeam");
    public static final ResourceLocation YELLOW_MERANTI = new ResourceLocation(MOD_ID, "yellow_meranti");
    public static final ResourceLocation YEW = new ResourceLocation(MOD_ID, "yew");
    public static final ResourceLocation ZEBRAWOOD = new ResourceLocation(MOD_ID, "zebrawood");
    public static final ResourceLocation HEVEA = new ResourceLocation(MOD_ID, "hevea");

    /**
     * Simple ITreeGenerator instances.
     */
    // public static final ITreeGenerator GEN_NORMAL = new TreeGenNormal(1, 3); // TFC variant
    // public static final ITreeGenerator GEN_MEDIUM = new TreeGenNormal(2, 2); // TFC variant
    // public static final ITreeGenerator GEN_TALL = new TreeGenNormal(3, 3); // TFC variant
    public static final ITreeGenerator GEN_NORMAL = new TreeGenRandom(1, 3, 3);
    public static final ITreeGenerator GEN_MEDIUM = new TreeGenRandom(2, 2, 3);
    public static final ITreeGenerator GEN_TALL = new TreeGenRandom(3, 3, 3);
    public static final ITreeGenerator GEN_CONIFER = new TreeGenVariants(false, 7);
    public static final ITreeGenerator GEN_TROPICAL = new TreeGenVariants(true, 7);
    public static final ITreeGenerator GEN_WILLOW = new TreeGenWillow();
    public static final ITreeGenerator GEN_ACACIA = new TreeGenAcacia();
    public static final ITreeGenerator GEN_KAPOK = new TreeGenKapok();
    public static final ITreeGenerator GEN_SEQUOIA = new TreeGenSequoia();
    public static final ITreeGenerator GEN_KAPOK_COMPOSITE = new TreeGenComposite().add(0.4f, GEN_TALL).add(0.6f, GEN_KAPOK);
    public static final ITreeGenerator GEN_BUSHES = new TreeGenBushes();
    public static final ITreeGenerator GEN_ASPEN = new TreeGenVariants(true, 54);
    public static final ITreeGenerator GEN_AFRICAN_PADAUK = new TreeGenVariants(true, 34);
    public static final ITreeGenerator GEN_ALDER = new TreeGenVariants(true, 30);
    public static final ITreeGenerator GEN_ANGELIM = new TreeGenVariants(true, 34);
    public static final ITreeGenerator GEN_BALD_CYPRESS = new TreeGenVariants(true, 13);
    public static final ITreeGenerator GEN_BAOBAB = new TreeGenVariants(true, 2);
    public static final ITreeGenerator GEN_BEECH = new TreeGenVariants(true, 33);
    public static final ITreeGenerator GEN_BLACK_WALNUT = new TreeGenVariants(true, 45);
    //public static final ITreeGenerator GEN_BOX = new TreeGenVariants(true, 9);			// Tall Tree Model
    public static final ITreeGenerator GEN_BRAZILWOOD = new TreeGenVariants(true, 14);
    public static final ITreeGenerator GEN_BUTTERNUT = new TreeGenVariants(true, 45);
    public static final ITreeGenerator GEN_COCOBOLO = new TreeGenVariants(true, 34);
    public static final ITreeGenerator GEN_CYPRESS = new TreeGenVariants(true, 5);
    public static final ITreeGenerator GEN_EBONY = new TreeGenVariants(true, 20);
    public static final ITreeGenerator GEN_EUCALYPTUS = new TreeGenVariants(true, 48);
    public static final ITreeGenerator GEN_EUROPEAN_OAK = new TreeGenVariants(true, 45);
    public static final ITreeGenerator GEN_FEVER = new TreeGenVariants(true, 13);
    public static final ITreeGenerator GEN_FRUITWOOD = new TreeGenVariants(true, 3);
    //public static final ITreeGenerator GEN_GIGANTEUM = new TreeGenVariants(true, 10);		// Redwood
    public static final ITreeGenerator GEN_GINKGO = new TreeGenVariants(true, 20);
    public static final ITreeGenerator GEN_GREENHEART = new TreeGenVariants(true, 34);
    public static final ITreeGenerator GEN_HAWTHORN = new TreeGenVariants(true, 15);
    public static final ITreeGenerator GEN_HAZEL = new TreeGenVariants(true, 45);
    public static final ITreeGenerator GEN_HEMLOCK = new TreeGenVariants(true, 16);
    public static final ITreeGenerator GEN_HOLLY = new TreeGenVariants(true, 16);
    public static final ITreeGenerator GEN_HORNBEAM = new TreeGenVariants(true, 35);
    public static final ITreeGenerator GEN_IPE = new TreeGenVariants(true, 66);
    public static final ITreeGenerator GEN_IROKO = new TreeGenVariants(true, 34);
    public static final ITreeGenerator GEN_IRONWOOD = new TreeGenVariants(true, 16);
    public static final ITreeGenerator GEN_JACARANDA = new TreeGenVariants(true, 30);
    public static final ITreeGenerator GEN_JOSHUA_TREE = new TreeGenVariants(true, 1);
    public static final ITreeGenerator GEN_JUNIPER = new TreeGenVariants(true, 17);
    public static final ITreeGenerator GEN_KAURI = new TreeGenVariants(true, 34);
    public static final ITreeGenerator GEN_LARCH = new TreeGenVariants(true, 10);
    public static final ITreeGenerator GEN_LIMBA = new TreeGenVariants(true, 13);
    public static final ITreeGenerator GEN_LOCUST = new TreeGenVariants(true, 15);
    public static final ITreeGenerator GEN_LOGWOOD = new TreeGenVariants(true, 10);
    public static final ITreeGenerator GEN_MACLURA = new TreeGenVariants(true, 15);
    public static final ITreeGenerator GEN_MAHOE = new TreeGenVariants(true, 15);
    public static final ITreeGenerator GEN_MAHOGANY = new TreeGenVariants(true, 34);
    public static final ITreeGenerator GEN_MANGROVE = new TreeGenVariants(true, 13);
    public static final ITreeGenerator GEN_MARBLEWOOD = new TreeGenVariants(true, 24);
    public static final ITreeGenerator GEN_MESSMATE = new TreeGenVariants(true, 18);
    public static final ITreeGenerator GEN_MOUNTAIN_ASH = new TreeGenVariants(true, 20);
    public static final ITreeGenerator GEN_MULBERRY = new TreeGenVariants(true, 15);
    public static final ITreeGenerator GEN_NORDMANN_FIR = new TreeGenVariants(true, 26);
    public static final ITreeGenerator GEN_NORWAY_SPRUCE = new TreeGenVariants(true, 16);
    public static final ITreeGenerator GEN_PINK_CHERRY = new TreeGenVariants(true, 45);
    public static final ITreeGenerator GEN_PINK_IVORY = new TreeGenVariants(true, 19);
    public static final ITreeGenerator GEN_POPLAR = new TreeGenVariants(true, 51);
    public static final ITreeGenerator GEN_PURPLEHEART = new TreeGenVariants(true, 34);
    public static final ITreeGenerator GEN_RED_CEDAR = new TreeGenVariants(true, 25);
    public static final ITreeGenerator GEN_RED_ELM = new TreeGenVariants(true, 60);
    public static final ITreeGenerator GEN_REDWOOD = new TreeGenVariants(true, 22);
    public static final ITreeGenerator GEN_ROWAN = new TreeGenVariants(true, 18);
    public static final ITreeGenerator GEN_RUBBER_FIG = new TreeGenVariants(true, 9);
    public static final ITreeGenerator GEN_SWEETGUM = new TreeGenVariants(true, 60);
    public static final ITreeGenerator GEN_SYZYGIUM = new TreeGenVariants(true, 14);
    public static final ITreeGenerator GEN_TEAK = new TreeGenVariants(true, 13);
    public static final ITreeGenerator GEN_WALNUT = new TreeGenVariants(true, 45);
    public static final ITreeGenerator GEN_WENGE = new TreeGenVariants(true, 34);
    public static final ITreeGenerator GEN_WHITE_CHERRY = new TreeGenVariants(true, 45);
    public static final ITreeGenerator GEN_WHITE_ELM = new TreeGenVariants(true, 60);
    public static final ITreeGenerator GEN_WHITEBEAM = new TreeGenVariants(true, 42);
    public static final ITreeGenerator GEN_YELLOW_MERANTI = new TreeGenVariants(true, 34);
    public static final ITreeGenerator GEN_YEW = new TreeGenVariants(true, 19);
    public static final ITreeGenerator GEN_ZEBRAWOOD = new TreeGenVariants(true, 34);

    public static final ITreeGenerator GEN_TALL_TFC = new TreeGenNormal(3, 3);

    public static final ITreeGenerator GEN_TALL_SINGLE = new TreeGenRandom(3, 3, 1);

    public static final ITreeGenerator GEN_CASSIA_CINNAMON = new TreeGenVariants(true, 6);
    public static final ITreeGenerator GEN_CEYLON_CINNAMON = new TreeGenVariants(true, 6);

    public static final ITreeGenerator GEN_ARROW_BAMBOO = new TreeGenVariants(true, 4);    // Pseudosasa japonica
    public static final ITreeGenerator GEN_BLACK_BAMBOO = new TreeGenVariants(true, 4); // Phyllostachys nigra
    public static final ITreeGenerator GEN_BLUE_BAMBOO = new TreeGenVariants(true, 4); // Himalayacalamus hookerianus
    public static final ITreeGenerator GEN_DRAGON_BAMBOO = new TreeGenVariants(true, 4); // Dendrocalamus giganteus
    public static final ITreeGenerator GEN_GOLDEN_BAMBOO = new TreeGenVariants(true, 4); // Alphonse Karr
    public static final ITreeGenerator GEN_NARROW_LEAF_BAMBOO = new TreeGenVariants(true, 4); // Guadua angustifolia
    public static final ITreeGenerator GEN_RED_BAMBOO = new TreeGenVariants(true, 4); // Fargesia nitida Jiuzhaigou
    public static final ITreeGenerator GEN_TEMPLE_BAMBOO = new TreeGenVariants(true, 4); // Semiarundinaria fastuosa
    public static final ITreeGenerator GEN_THORNY_BAMBOO = new TreeGenVariants(true, 4); // Chimonobambusa pachystachys
    public static final ITreeGenerator GEN_TIMBER_BAMBOO = new TreeGenVariants(true, 4); // Phyllostachys vivax
    public static final ITreeGenerator GEN_TINWA_BAMBOO = new TreeGenVariants(true, 4); // Cephalostachyum pergracile
    public static final ITreeGenerator GEN_WEAVERS_BAMBOO = new TreeGenVariants(true, 4); // Bambusa textilis

    // Seasonal & Fruit-bearing Trees
    // This trees florae replaces in tfc (we can remove this, i think)
    // new Tree.Builder(ASH, 60f, 140f, -6f, 12f, GEN_NORMAL).setBurnInfo(696f, 1250).build(),
    // new Tree.Builder(ASPEN, 10f, 80f, -10f, 16f, GEN_MEDIUM).setRadius(1).setGrowthTime(8).setBurnInfo(611f, 1000).build(),
    // new Tree.Builder(BIRCH, 20f, 180f, -15f, 7f, GEN_TALL).setRadius(1).setTannin().setBurnInfo(652f, 1750).build(),
    // new Tree.Builder(CHESTNUT, 160f, 320f, 11f, 35f, GEN_NORMAL).setTannin().setBurnInfo(651f, 1500).build(),
    // new Tree.Builder(HICKORY, 80f, 250f, 7f, 29f, GEN_TALL).setGrowthTime(10).setTannin().setBurnInfo(762f, 2000).build(),
    // new Tree.Builder(MAPLE, 140f, 360f, 3f, 20f, GEN_MEDIUM).setDominance(6.3f).setRadius(1).setTannin().setBurnInfo(745f, 2000).build(),
    // new Tree.Builder(OAK, 180f, 430f, -8f, 12f, GEN_TALL).setHeight(16).setGrowthTime(10).setTannin().setBurnInfo(728f, 2250).build(),
    // new Tree.Builder(SYCAMORE, 120f, 290f, 17f, 33f, GEN_MEDIUM).setGrowthTime(8).setBushes().setDensity(0.25f, 2f).setBurnInfo(653f, 1750).build(),
    // new Tree.Builder(WHITE_CEDAR, 10f, 240f, -8f, 17f, GEN_TALL).setHeight(16).setBurnInfo(625f, 1500).build(),

    @SubscribeEvent
    public static void onPreRegisterTree(TFCRegistryEvent.RegisterPreBlock<Tree> event) {
        event.getRegistry().registerAll(

                //minTemp maxTemp minRain maxRain ???

                new Tree.Builder(ACACIA, 30f, 210f, 19f, 31f, GEN_ACACIA, TFCRegistries.TREES.getValue(DefaultTrees.ACACIA))
                        .setHeight(12)
                        .setGrowthTime(11)
                        .setDensity(0.1f, 0.6f)
                        .build(),

                new Tree.Builder(ASH, 60f, 140f, -6f, 12f, GEN_NORMAL, TFCRegistries.TREES.getValue(DefaultTrees.ASH))
                        .build(),

                new Tree.Builder(ASPEN, 10f, 80f, -10f, 16f, GEN_MEDIUM, TFCRegistries.TREES.getValue(DefaultTrees.ASPEN))
                        .setGrowthTime(8)
                        .build(),

                new Tree.Builder(BLACKWOOD, 0f, 120f, 4f, 33f, GEN_MEDIUM, TFCRegistries.TREES.getValue(DefaultTrees.BLACKWOOD))
                        .setHeight(12)
                        .setGrowthTime(8)
                        .build(),

                new Tree.Builder(CHESTNUT, 160f, 320f, 11f, 35f, GEN_NORMAL, TFCRegistries.TREES.getValue(DefaultTrees.CHESTNUT))
                        .build(),

                new Tree.Builder(DOUGLAS_FIR, 280f, 480f, -2f, 14f, GEN_TALL, TFCRegistries.TREES.getValue(DefaultTrees.DOUGLAS_FIR))
                        .setDominance(5.2f)
                        .setHeight(16)
                        .setBushes()
                        .setDensity(0.25f, 2f)
                        .build(),

                new Tree.Builder(HICKORY, 80f, 250f, 7f, 29f, GEN_TALL, TFCRegistries.TREES.getValue(DefaultTrees.HICKORY))
                        .setGrowthTime(10)
                        .build(),

                new Tree.Builder(KAPOK, 210f, 500f, 15f, 35f, GEN_KAPOK_COMPOSITE, TFCRegistries.TREES.getValue(DefaultTrees.KAPOK))
                        .setDominance(8.5f)
                        .setRadius(3)
                        .setHeight(36)
                        .setDecayDist(6)
                        .setGrowthTime(18)
                        .setBushes()
                        .setDensity(0.6f, 2f)
                        .build(),

                new Tree.Builder(MAPLE, 140f, 360f, 3f, 20f, GEN_MEDIUM, TFCRegistries.TREES.getValue(DefaultTrees.MAPLE))
                        .setDominance(6.3f)
                        .build(),

                //Oak Trees
                new Tree.Builder(OAK, 180f, 430f, -8f, 12f, GEN_NORMAL, TFCRegistries.TREES.getValue(DefaultTrees.OAK))
                        .setHeight(16)
                        .setGrowthTime(10)
                        .build(),

                new Tree.Builder(FLOWERING_OAK, 180f, 430f, -8f, 12f, GEN_NORMAL, TFCRegistries.TREES.getValue(DefaultTrees.OAK))
                        .setHeight(16)
                        .setGrowthTime(10)
                        .build(),


                new Tree.Builder(DARK_OAK, 180f, 430f, -8f, 12f, GEN_NORMAL, TFCRegistries.TREES.getValue(DefaultTrees.DARK_OAK))
                        .setHeight(12)
                        .setRadius(2)
                        .setGrowthTime(10)
                        .build(),

                new Tree.Builder(PALM, 280f, 500f, 16f, 35f, GEN_TROPICAL, TFCRegistries.TREES.getValue(DefaultTrees.PALM))
                        .setDecayDist(6)
                        .build(),

                new Tree.Builder(PINE, 60f, 250f, -15f, 7f, GEN_CONIFER, TFCRegistries.TREES.getValue(DefaultTrees.PINE))
                        .setConifer()
                        .setDensity(0.1f, 0.8f)
                        .build(),

                new Tree.Builder(ROSEWOOD, 10f, 190f, 8f, 18f, GEN_MEDIUM, TFCRegistries.TREES.getValue(DefaultTrees.ROSEWOOD))
                        .setHeight(12)
                        .setGrowthTime(8)
                        .build(),

                new Tree.Builder(SEQUOIA, 250f, 420f, -5f, 12f, GEN_SEQUOIA, TFCRegistries.TREES.getValue(DefaultTrees.SEQUOIA))
                        .setRadius(3)
                        .setHeight(24)
                        .setDecayDist(6)
                        .setGrowthTime(18)
                        .setConifer()
                        .setBushes()
                        .setDensity(0.4f, 0.9f)
                        .build(),

                //Spruce Trees
                new Tree.Builder(SPRUCE, 120f, 380f, -11f, 6f, GEN_CONIFER, TFCRegistries.TREES.getValue(DefaultTrees.SPRUCE))
                        .setConifer()
                        .setDensity(0.1f, 0.8f)
                        .build(),

                new Tree.Builder(NORWAY_SPRUCE, 120f, 380f, -11f, 6f, GEN_CONIFER, TFCRegistries.TREES.getValue(DefaultTrees.NORWAY_SPRUCE))
                        .setConifer()
                        .setDensity(0.1f, 0.8f)
                        .build(),

                new Tree.Builder(JAPANESE_SPRUCE, 120f, 380f, -11f, 6f, GEN_CONIFER, TFCRegistries.TREES.getValue(DefaultTrees.JAPANESE_SPRUCE))
                        .setConifer()
                        .setDensity(0.1f, 0.8f)
                        .build(),

                new Tree.Builder(SYCAMORE, 120f, 290f, 17f, 33f, GEN_MEDIUM, TFCRegistries.TREES.getValue(DefaultTrees.SYCAMORE))
                        .setGrowthTime(8)
                        .setBushes()
                        .setDensity(0.25f, 2f)
                        .build(),

                //Birch Trees
                new Tree.Builder(BIRCH, 20f, 180f, -15f, 7f, GEN_TALL, TFCRegistries.TREES.getValue(DefaultTrees.BIRCH))
                        .build(),

                new Tree.Builder(SILVER_BIRCH, 20f, 180f, -15f, 7f, GEN_TALL, TFCRegistries.TREES.getValue(DefaultTrees.SILVER_BIRCH))
                        .build(),

                new Tree.Builder(WHITE_CEDAR, 10f, 240f, -8f, 17f, GEN_TALL, TFCRegistries.TREES.getValue(DefaultTrees.WHITE_CEDAR))
                        .setHeight(16)
                        .build(),

                new Tree.Builder(WHITE_ELM, 140f, 360f, 3f, 20f, GEN_MEDIUM, TFCRegistries.TREES.getValue(DefaultTrees.WHITE_ELM))
                        .setHeight(16)
                        .build(),

                new Tree.Builder(WILLOW, 230f, 400f, 15f, 32f, GEN_WILLOW, TFCRegistries.TREES.getValue(DefaultTrees.WILLOW))
                        .setGrowthTime(11)
                        .setBushes()
                        .setDensity(0.7f, 2f)
                        .build(),

                new Tree.Builder(WEEPING_WILLOW, 230f, 400f, 15f, 32f, GEN_WILLOW, TFCRegistries.TREES.getValue(DefaultTrees.WILLOW))
                        .setGrowthTime(11)
                        .setBushes()
                        .setDensity(0.7f, 2f)
                        .build(),


                //---------------- Hevea Tree Species ---------------- //

                //https://en.wikipedia.org/wiki/Hevea_benthamiana
                new Tree.Builder(HEVEA_BENTHAMIANA, 200f, 450f, 12f, 40f, GEN_TALL, TFCRegistries.TREES.getValue(DefaultTrees.HEVEA_BENTHAMIANA))
                        .setDensity(0.1f, 0.6f)
                        .setRadius(2)
                        .setHeight(27)
                        .setGrowthTime(10)
                        .build(),

                //https://en.wikipedia.org/wiki/Hevea_brasiliensis
                new Tree.Builder(HEVEA_BRAZILIENSIS, 200f, 500f, 15f, 40f, GEN_TALL, TFCRegistries.TREES.getValue(DefaultTrees.HEVEA_BRAZILIENSIS))
                        .setDensity(0.1f, 0.6f)
                        .setRadius(2)
                        .setHeight(20)
                        .setGrowthTime(10)
                        .build(),

                //https://en.wikipedia.org/wiki/Hevea_camargoana
                new Tree.Builder(HEVEA_CAMARGOANA, 200f, 450f, 12f, 40f, GEN_TALL, TFCRegistries.TREES.getValue(DefaultTrees.HEVEA_CAMARGOANA))
                        .setDensity(0.1f, 0.6f)
                        .setRadius(2)
                        .setHeight(22)
                        .setGrowthTime(10)
                        .build(),

                //https://en.wikipedia.org/wiki/Hevea_camporum
                new Tree.Builder(HEVEA_CAMPORUM, 200f, 450f, 12f, 40f, GEN_TALL, TFCRegistries.TREES.getValue(DefaultTrees.HEVEA_CAMPORUM))
                        .setDensity(0.1f, 0.6f)
                        .setRadius(2)
                        .setHeight(22)
                        .setGrowthTime(10)
                        .build(),

                //https://en.wikipedia.org/wiki/Hevea_guianensis
                new Tree.Builder(HEVEA_GUIANENSIS, 200f, 450f, 12f, 40f, GEN_TALL, TFCRegistries.TREES.getValue(DefaultTrees.HEVEA_GUIANENSIS))
                        .setDensity(0.1f, 0.6f)
                        .setRadius(2)
                        .setHeight(22)
                        .setGrowthTime(10)
                        .build(),

                //https://en.wikipedia.org/wiki/Hevea_microphylla
                new Tree.Builder(HEVEA_MICROPHYLLA, 200f, 450f, 12f, 40f, GEN_TALL, TFCRegistries.TREES.getValue(DefaultTrees.HEVEA_MICROPHYLLA))
                        .setDensity(0.1f, 0.6f)
                        .setRadius(2)
                        .setHeight(22)
                        .setGrowthTime(10)
                        .build(),

                //https://en.wikipedia.org/wiki/Hevea_nitida
                new Tree.Builder(HEVEA_NITIDA, 200f, 450f, 12f, 40f, GEN_TALL, TFCRegistries.TREES.getValue(DefaultTrees.HEVEA_NITIDA))
                        .setDensity(0.1f, 0.6f)
                        .setRadius(2)
                        .setHeight(22)
                        .setGrowthTime(10)
                        .build(),

                //https://en.wikipedia.org/wiki/Hevea_pauciflora
                new Tree.Builder(HEVEA_PAUCIFLORA, 200f, 450f, 12f, 40f, GEN_TALL, TFCRegistries.TREES.getValue(DefaultTrees.HEVEA_PAUCIFLORA))
                        .setDensity(0.1f, 0.6f)
                        .setRadius(2)
                        .setHeight(22)
                        .setGrowthTime(10)
                        .build(),

                //https://en.wikipedia.org/wiki/Hevea_rigidifolia
                new Tree.Builder(HEVEA_RIGIDOFOLIA, 200f, 450f, 12f, 40f, GEN_TALL, TFCRegistries.TREES.getValue(DefaultTrees.HEVEA_RIGIDOFOLIA))
                        .setDensity(0.1f, 0.6f)
                        .setRadius(2)
                        .setHeight(22)
                        .setGrowthTime(10)
                        .build(),

                //https://en.wikipedia.org/wiki/Hevea_spruceana
                new Tree.Builder(HEVEA_SPRUCEANA, 200f, 450f, 12f, 40f, GEN_TALL, TFCRegistries.TREES.getValue(DefaultTrees.HEVEA_SPRUCEANA))
                        .setDensity(0.1f, 0.6f)
                        .setRadius(2)
                        .setHeight(22)
                        .setGrowthTime(10)
                        .build(),


                //Latex Trees
                new Tree.Builder(SAPODILLA, 140f, 350f, 7f, 27f, GEN_TALL, TFCRegistries.TREES.getValue(DefaultTrees.SAPODILLA))
                        .setDensity(0.1f, 0.6f)
                        .setRadius(2)
                        .setHeight(30)
                        .setGrowthTime(10)
                        .build(),

                new Tree.Builder(CASTILLA, 140f, 350f, 7f, 27f, GEN_TALL, TFCRegistries.TREES.getValue(DefaultTrees.CASTILLA))
                        .setDensity(0.1f, 0.6f)
                        .setRadius(2)
                        .setHeight(26)
                        .setGrowthTime(10)
                        .build(),


                // Florae Trees
                new Tree.Builder(BAOBAB, 10f, 150f, 21f, 40f, GEN_BAOBAB, TFCRegistries.TREES.getValue(DefaultTrees.BAOBAB)).setDecayDist(6).setGrowthTime(20).setDensity(0.1f, 0.3f).build(),
                new Tree.Builder(EUCALYPTUS, 120f, 300f, 18f, 39f, GEN_EUCALYPTUS, TFCRegistries.TREES.getValue(DefaultTrees.EUCALYPTUS)).setGrowthTime(8).setBushes().setDensity(0.35f, 2f).build(),
                new Tree.Builder(HAWTHORN, 180f, 400f, -8f, 14f, GEN_HAWTHORN, TFCRegistries.TREES.getValue(DefaultTrees.HAWTHORN)).setGrowthTime(8).setDensity(0.25f, 1f).build(),
                new Tree.Builder(MULBERRY, 140f, 420f, -30f, 28f, GEN_MULBERRY, TFCRegistries.TREES.getValue(DefaultTrees.MULBERRY)).setGrowthTime(10).setDensity(0.25f, 1f).build(),
                new Tree.Builder(MACLURA, 140f, 400f, -1f, 17f, GEN_MACLURA, TFCRegistries.TREES.getValue(DefaultTrees.MACLURA)).setGrowthTime(8).setBushes().setDensity(0.25f, 1f).build(),
                new Tree.Builder(MAHOGANY, 270f, 500f, 23f, 42f, GEN_MAHOGANY, TFCRegistries.TREES.getValue(DefaultTrees.MAHOGANY)).setRadius(1).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.5f, 2f).build(),
                new Tree.Builder(PINK_IVORY, 210f, 500f, 18f, 31f, GEN_PINK_IVORY, TFCRegistries.TREES.getValue(DefaultTrees.PINK_IVORY)).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.2f, 2f).build(),
                new Tree.Builder(RED_CEDAR, 10f, 240f, -8f, 17f, GEN_RED_CEDAR, TFCRegistries.TREES.getValue(DefaultTrees.RED_CEDAR)).setDecayDist(6).setGrowthTime(18).setBushes().setConifer().setBushes().setDensity(0.4f, 2f).build(),
                new Tree.Builder(ROWAN, 180f, 400f, -15f, 8f, GEN_ROWAN, TFCRegistries.TREES.getValue(DefaultTrees.ROWAN)).setGrowthTime(8).setDensity(0.25f, 1f).build(),
                new Tree.Builder(SYZYGIUM, 140f, 360f, 13f, 35f, GEN_SYZYGIUM, TFCRegistries.TREES.getValue(DefaultTrees.SYZYGIUM)).setDecayDist(6).setGrowthTime(16).setBushes().setDensity(0.2f, 1f).build(),
                new Tree.Builder(YEW, 180f, 350f, -15f, 11f, GEN_YEW, TFCRegistries.TREES.getValue(DefaultTrees.YEW)).setGrowthTime(10).setBushes().build(),
                new Tree.Builder(JACARANDA, 180f, 300f, 10f, 34f, GEN_JACARANDA, TFCRegistries.TREES.getValue(DefaultTrees.JACARANDA)).setGrowthTime(8).setDensity(0.25f, 2f).build(),
                new Tree.Builder(JOSHUA_TREE, 20f, 150f, 15f, 40f, GEN_JOSHUA_TREE, TFCRegistries.TREES.getValue(DefaultTrees.JOSHUA_TREE)).setDominance(0f).setDensity(0f, 0f).setGrowthTime(8).setConifer().build(),
                new Tree.Builder(JUNIPER, 80f, 350f, -8f, 20f, GEN_JUNIPER, TFCRegistries.TREES.getValue(DefaultTrees.JUNIPER)).setGrowthTime(8).setConifer().setDensity(0.25f, 0.75f).build(),
                new Tree.Builder(IPE, 150f, 350f, 15f, 32f, GEN_IPE, TFCRegistries.TREES.getValue(DefaultTrees.IPE)).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.2f, 2f).build(),
                new Tree.Builder(PINK_CHERRY, 180f, 300f, 0f, 20f, GEN_PINK_CHERRY, TFCRegistries.TREES.getValue(DefaultTrees.PINK_CHERRY)).setGrowthTime(8).setDensity(0.25f, 2f).build(),
                new Tree.Builder(WHITE_CHERRY, 180f, 300f, 0f, 20f, GEN_WHITE_CHERRY, TFCRegistries.TREES.getValue(DefaultTrees.WHITE_CHERRY)).setGrowthTime(8).setDensity(0.25f, 2f).build(),
                new Tree.Builder(SWEETGUM, 140f, 360f, -2f, 18f, GEN_SWEETGUM, TFCRegistries.TREES.getValue(DefaultTrees.SWEETGUM)).setDecayDist(6).setGrowthTime(16).setBushes().setDensity(0.2f, 1f).build(),
                new Tree.Builder(LARCH, 60f, 400f, -12f, 15f, GEN_LARCH, TFCRegistries.TREES.getValue(DefaultTrees.LARCH)).setGrowthTime(8).setConifer().setDensity(0.25f, 1f).build(),
                new Tree.Builder(ALDER, 60f, 400f, -4f, 13f, GEN_ALDER, TFCRegistries.TREES.getValue(DefaultTrees.ALDER)).setGrowthTime(8).setBushes().setDensity(0.25f, 2f).build(),
                new Tree.Builder(BEECH, 220f, 300f, -15f, 9f, GEN_BEECH, TFCRegistries.TREES.getValue(DefaultTrees.BEECH)).setGrowthTime(8).setBushes().setDensity(0.25f, 1f).build(),
                new Tree.Builder(BLACK_WALNUT, 180f, 300f, -10f, 16f, GEN_BLACK_WALNUT, TFCRegistries.TREES.getValue(DefaultTrees.BLACK_WALNUT)).setGrowthTime(9).setBushes().build(),
                new Tree.Builder(BUTTERNUT, 180f, 320f, -8f, 17f, GEN_BUTTERNUT, TFCRegistries.TREES.getValue(DefaultTrees.BUTTERNUT)).setGrowthTime(9).setBushes().build(),
                new Tree.Builder(EUROPEAN_OAK, 140f, 430f, -8f, 15f, GEN_EUROPEAN_OAK, TFCRegistries.TREES.getValue(DefaultTrees.EUROPEAN_OAK)).setGrowthTime(10).setBushes().build(),
                new Tree.Builder(GINKGO, 240f, 550f, 6f, 20f, GEN_GINKGO, TFCRegistries.TREES.getValue(DefaultTrees.GINKGO)).setGrowthTime(8).setDensity(0.25f, 1f).build(),
                new Tree.Builder(HAZEL, 60f, 400f, -10f, 14f, GEN_HAZEL, TFCRegistries.TREES.getValue(DefaultTrees.HAZEL)).setGrowthTime(8).setBushes().setDensity(0.25f, 1f).build(),
                new Tree.Builder(HORNBEAM, 140f, 430f, -10f, 12f, GEN_HORNBEAM, TFCRegistries.TREES.getValue(DefaultTrees.HORNBEAM)).setGrowthTime(10).setBushes().build(),
                new Tree.Builder(LOCUST, 120f, 290f, -6f, 15f, GEN_LOCUST, TFCRegistries.TREES.getValue(DefaultTrees.LOCUST)).setGrowthTime(8).setBushes().build(),
                new Tree.Builder(POPLAR, 140f, 400f, -7f, 14f, GEN_POPLAR, TFCRegistries.TREES.getValue(DefaultTrees.POPLAR)).setGrowthTime(8).setDensity(0.25f, 1f).build(),
                new Tree.Builder(RED_ELM, 60f, 290f, 2f, 20f, GEN_RED_ELM, TFCRegistries.TREES.getValue(DefaultTrees.RED_ELM)).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.4f, 2f).build(),
                new Tree.Builder(WALNUT, 180f, 300f, -10f, 16f, GEN_WALNUT, TFCRegistries.TREES.getValue(DefaultTrees.WALNUT)).setGrowthTime(9).setBushes().build(),
                new Tree.Builder(WHITE_ELM, 60f, 290f, 2f, 20f, GEN_WHITE_ELM, TFCRegistries.TREES.getValue(DefaultTrees.WHITE_ELM)).setGrowthTime(8).setBushes().build(),
                new Tree.Builder(WHITEBEAM, 140f, 430f, -10f, 12f, GEN_WHITEBEAM, TFCRegistries.TREES.getValue(DefaultTrees.WHITEBEAM)).setGrowthTime(10).setBushes().build(),
                new Tree.Builder(BALD_CYPRESS, 180f, 500f, 10f, 38f, GEN_BALD_CYPRESS, TFCRegistries.TREES.getValue(DefaultTrees.BALD_CYPRESS)).setDominance(0f).setDensity(0f, 0f).setGrowthTime(8).setBushes().setConifer().build(),
                new Tree.Builder(CYPRESS, 140f, 350f, 4f, 33f, GEN_CYPRESS, TFCRegistries.TREES.getValue(DefaultTrees.CYPRESS)).setGrowthTime(8).setBushes().setConifer().build(),
                new Tree.Builder(HEMLOCK, 140f, 400f, -9f, 10f, GEN_HEMLOCK, TFCRegistries.TREES.getValue(DefaultTrees.HEMLOCK)).setGrowthTime(8).setConifer().setDensity(0.25f, 1f).build(),
                new Tree.Builder(NORDMANN_FIR, 100f, 380f, -16f, 7f, GEN_NORDMANN_FIR, TFCRegistries.TREES.getValue(DefaultTrees.NORDMANN_FIR)).setGrowthTime(8).setConifer().setDensity(0.1f, 0.9f).build(),
                new Tree.Builder(NORWAY_SPRUCE, 100f, 380f, -20f, 5f, GEN_NORWAY_SPRUCE, TFCRegistries.TREES.getValue(DefaultTrees.NORWAY_SPRUCE)).setGrowthTime(8).setConifer().setDensity(0.1f, 0.9f).build(),
                new Tree.Builder(REDWOOD, 160f, 400f, 0f, 17f, GEN_REDWOOD, TFCRegistries.TREES.getValue(DefaultTrees.REDWOOD)).setDecayDist(6).setGrowthTime(18).setConifer().setBushes().setDensity(0.4f, 2f).build(),
                new Tree.Builder(AFRICAN_PADAUK, 275f, 500f, 22f, 50f, GEN_AFRICAN_PADAUK, TFCRegistries.TREES.getValue(DefaultTrees.AFRICAN_PADAUK)).setRadius(1).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.5f, 2f).build(),
                new Tree.Builder(ANGELIM, 320f, 500f, 22f, 50f, GEN_ANGELIM, TFCRegistries.TREES.getValue(DefaultTrees.ANGELIM)).setRadius(1).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.5f, 2f).build(),
                new Tree.Builder(BOX, 180f, 400f, -8f, 15f, GEN_TALL_TFC, TFCRegistries.TREES.getValue(DefaultTrees.BOX)).setGrowthTime(8).setBushes().setDensity(0.25f, 1f).build(),
                new Tree.Builder(BRAZILWOOD, 290f, 550f, 14f, 37f, GEN_BRAZILWOOD, TFCRegistries.TREES.getValue(DefaultTrees.BRAZILWOOD)).setGrowthTime(8).setBushes().setDensity(0.25f, 1f).build(),
                new Tree.Builder(COCOBOLO, 255f, 500f, 20f, 50f, GEN_COCOBOLO, TFCRegistries.TREES.getValue(DefaultTrees.COCOBOLO)).setRadius(1).setGrowthTime(8).setBushes().setDensity(0.5f, 2f).build(),
                new Tree.Builder(EBONY, 180f, 320f, 19f, 38f, GEN_EBONY, TFCRegistries.TREES.getValue(DefaultTrees.EBONY)).setGrowthTime(8).setBushes().build(),
                new Tree.Builder(FEVER, 70f, 220f, 19f, 50f, GEN_FEVER, TFCRegistries.TREES.getValue(DefaultTrees.FEVER)).setGrowthTime(10).setBushes().setDensity(0.25f, 1f).build(),
                new Tree.Builder(FRUITWOOD, 180f, 550f, 11f, 30f, GEN_FRUITWOOD, TFCRegistries.TREES.getValue(DefaultTrees.FRUITWOOD)).setDominance(0).setGrowthTime(9).setBushes().setDensity(0f, 0f).build(),
                new Tree.Builder(GREENHEART, 310f, 500f, 23f, 50f, GEN_GREENHEART, TFCRegistries.TREES.getValue(DefaultTrees.GREENHEART)).setRadius(1).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.5f, 2f).build(),
                new Tree.Builder(HOLLY, 140f, 400f, -4f, 16f, GEN_HOLLY, TFCRegistries.TREES.getValue(DefaultTrees.HOLLY)).setGrowthTime(8).setBushes().setDensity(0.25f, 1f).build(),
                new Tree.Builder(IROKO, 300f, 500f, 21f, 50f, GEN_IROKO, TFCRegistries.TREES.getValue(DefaultTrees.IROKO)).setRadius(1).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.5f, 2f).build(),
                new Tree.Builder(IRONWOOD, 30f, 210f, 11f, 36f, GEN_IRONWOOD, TFCRegistries.TREES.getValue(DefaultTrees.IRONWOOD)).setDecayDist(6).setGrowthTime(11).setBushes().setDensity(0.1f, 0.6f).build(),
                new Tree.Builder(KAURI, 330f, 500f, 23f, 50f, GEN_KAURI, TFCRegistries.TREES.getValue(DefaultTrees.KAURI)).setRadius(1).setGrowthTime(10).setBushes().setDensity(0.5f, 2f).build(),
                new Tree.Builder(LIMBA, 290f, 550f, 14f, 37f, GEN_LIMBA, TFCRegistries.TREES.getValue(DefaultTrees.LIMBA)).setGrowthTime(9).setBushes().setDensity(0.25f, 1f).build(),
                new Tree.Builder(LOGWOOD, 180f, 430f, 12f, 35f, GEN_LOGWOOD, TFCRegistries.TREES.getValue(DefaultTrees.LOGWOOD)).setGrowthTime(8).setBushes().setDensity(0.25f, 1f).build(),
                new Tree.Builder(MAHOE, 180f, 350f, 13f, 32f, GEN_MAHOE, TFCRegistries.TREES.getValue(DefaultTrees.MAHOE)).setHeight(16).setGrowthTime(8).setBushes().build(),
                new Tree.Builder(MANGROVE, 200f, 500f, 15f, 40f, GEN_MANGROVE, TFCRegistries.TREES.getValue(DefaultTrees.MANGROVE)).setDominance(0f).setDensity(0f, 0f).setRadius(1).setGrowthTime(8).setBushes().build(),
                new Tree.Builder(MARBLEWOOD, 180f, 500f, 16f, 35f, GEN_MARBLEWOOD, TFCRegistries.TREES.getValue(DefaultTrees.MARBLEWOOD)).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.2f, 2f).build(),
                new Tree.Builder(MESSMATE, 120f, 270f, 2f, 27f, GEN_MESSMATE, TFCRegistries.TREES.getValue(DefaultTrees.MESSMATE)).setGrowthTime(10).setBushes().setDensity(0.2f, 2f).build(),
                new Tree.Builder(MOUNTAIN_ASH, 80f, 270f, 9f, 33f, GEN_MOUNTAIN_ASH, TFCRegistries.TREES.getValue(DefaultTrees.MOUNTAIN_ASH)).setGrowthTime(10).setBushes().setDensity(0.4f, 2f).build(),
                new Tree.Builder(PURPLEHEART, 310f, 500f, 22f, 50f, GEN_PURPLEHEART, TFCRegistries.TREES.getValue(DefaultTrees.PURPLEHEART)).setRadius(1).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.5f, 2f).build(),
                new Tree.Builder(RUBBER_FIG, 210f, 550f, 16f, 35f, GEN_RUBBER_FIG, TFCRegistries.TREES.getValue(DefaultTrees.RUBBER_FIG)).setDecayDist(6).setGrowthTime(16).setBushes().setDensity(0.2f, 1f).build(),
                new Tree.Builder(TEAK, 180f, 430f, 17f, 35f, GEN_TEAK, TFCRegistries.TREES.getValue(DefaultTrees.TEAK)).setGrowthTime(8).setBushes().setDensity(0.25f, 1f).build(),
                new Tree.Builder(WENGE, 255f, 500f, 20f, 50f, GEN_WENGE, TFCRegistries.TREES.getValue(DefaultTrees.WENGE)).setRadius(1).setGrowthTime(8).setBushes().setDensity(0.5f, 2f).build(),
                new Tree.Builder(YELLOW_MERANTI, 260f, 500f, 21f, 50f, GEN_YELLOW_MERANTI, TFCRegistries.TREES.getValue(DefaultTrees.YELLOW_MERANTI)).setRadius(1).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.5f, 2f).build(),
                new Tree.Builder(ZEBRAWOOD, 280f, 500f, 23f, 50f, GEN_ZEBRAWOOD, TFCRegistries.TREES.getValue(DefaultTrees.ZEBRAWOOD)).setRadius(1).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.5f, 2f).build()
//
//    // Smart (not) trees
//    new Tree(new ResourceLocation(MOD_ID, "cassia_cinnamon"), GEN_CASSIA_CINNAMON, 20, 35, 250, 400, 0.1f, 1, 2, 4, 15, 6, false, null, false, 15, 710f, 1000);
//    new Tree(new ResourceLocation(MOD_ID, "ceylon_cinnamon"), GEN_CEYLON_CINNAMON, 20, 35, 250, 400, 0.1f, 1, 2, 4, 15, 6, false, null, false, 15, 710f, 1000);
//
//    new Tree(new ResourceLocation(MOD_ID, "arrow_bamboo"), GEN_ARROW_BAMBOO, 24, 35, 240, 420, 1, 2, 1, 4, 15, 6, false, null, false, 10, 400f, 800);
//    new Tree(new ResourceLocation(MOD_ID, "black_bamboo"), GEN_BLACK_BAMBOO, 24, 35, 240, 420, 1, 2, 1, 4, 15, 6, false, null, false, 10, 400f, 800);
//    new Tree(new ResourceLocation(MOD_ID, "blue_bamboo"), GEN_BLUE_BAMBOO, 24, 35, 240, 420, 1, 2, 1, 4, 15, 6, false, null, false, 10, 400f, 800);
//    new Tree(new ResourceLocation(MOD_ID, "dragon_bamboo"), GEN_DRAGON_BAMBOO, 24, 35, 240, 420, 1, 2, 1, 4, 15, 6, false, null, false, 10, 400f, 800);
//    new Tree(new ResourceLocation(MOD_ID, "golden_bamboo"), GEN_GOLDEN_BAMBOO, 24, 35, 240, 420, 1, 2, 1, 4, 15, 6, false, null, false, 10, 400f, 800);
//    new Tree(new ResourceLocation(MOD_ID, "narrow_leaf_bamboo"), GEN_NARROW_LEAF_BAMBOO, 24, 35, 240, 420, 1, 2, 1, 4, 15, 6, false, null, false, 10, 400f, 800);
//    new Tree(new ResourceLocation(MOD_ID, "red_bamboo"), GEN_RED_BAMBOO, 24, 35, 240, 420, 1, 2, 1, 4, 15, 6, false, null, false, 10, 400f, 800);
//    new Tree(new ResourceLocation(MOD_ID, "temple_bamboo"), GEN_TEMPLE_BAMBOO, 24, 35, 240, 420, 1, 2, 1, 4, 15, 6, false, null, false, 10, 400f, 800);
//    new Tree(new ResourceLocation(MOD_ID, "thorny_bamboo"), GEN_THORNY_BAMBOO, 24, 35, 240, 420, 1, 2, 1, 4, 15, 6, false, null, false, 10, 400f, 800);
//    new Tree(new ResourceLocation(MOD_ID, "timber_bamboo"), GEN_TIMBER_BAMBOO, 24, 35, 240, 420, 1, 2, 1, 4, 15, 6, false, null, false, 10, 400f, 800);
//    new Tree(new ResourceLocation(MOD_ID, "tinwa_bamboo"), GEN_TINWA_BAMBOO, 24, 35, 240, 420, 1, 2, 1, 4, 15, 6, false, null, false, 10, 400f, 800);
//    new Tree(new ResourceLocation(MOD_ID, "weavers_bamboo"), GEN_WEAVERS_BAMBOO, 24, 35, 240, 420, 1, 2, 1, 4, 15, 6, false, null, false, 10, 400f, 800);

        );
    }

}
