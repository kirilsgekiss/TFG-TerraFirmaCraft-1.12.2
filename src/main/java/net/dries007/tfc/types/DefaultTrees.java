/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.types;

import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.registries.TFCRegistryEvent;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.api.util.ITreeGenerator;
import net.dries007.tfc.world.classic.worldgen.trees.TreeGenBushes;
import net.dries007.tfc.compat.dynamictrees.TreeGenDynamic;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;
import static net.dries007.tfc.types.DefaultWoods.*;

@Mod.EventBusSubscriber(modid = MOD_ID)
public final class DefaultTrees {

    /**
     * Simple ITreeGenerator instances.
     */

    public static final ITreeGenerator GEN_BUSHES = new TreeGenBushes();
    public static final ITreeGenerator GEN_DYNAMIC = new TreeGenDynamic();

    @SubscribeEvent
    public static void onPreRegisterTree(TFCRegistryEvent.RegisterPreBlock<Tree> event) {
        event.getRegistry().registerAll(
                new Tree.Builder(ACACIA, 30f, 210f, 19f, 31f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(ACACIA)).setHeight(12).setGrowthTime(11).setDensity(0.1f, 0.6f).build(),
                // new Tree.Builder(AFRICAN_PADAUK, 275f, 500f, 22f, 50f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(AFRICAN_PADAUK)).setRadius(1).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.5f, 2f).build(),
                // new Tree.Builder(ALDER, 60f, 400f, -4f, 13f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(ALDER)).setGrowthTime(8).setBushes().setDensity(0.25f, 2f).build(),
                // new Tree.Builder(ANGELIM, 320f, 500f, 22f, 50f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(ANGELIM)).setRadius(1).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.5f, 2f).build(),
                new Tree.Builder(ASH, 60f, 140f, -6f, 12f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(ASH)).build(),
                new Tree.Builder(ASPEN, 10f, 80f, -10f, 16f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(ASPEN)).setGrowthTime(8).build(),
                // new Tree.Builder(BALD_CYPRESS, 180f, 500f, 10f, 38f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(BALD_CYPRESS)).setDominance(0f).setDensity(0f, 0f).setGrowthTime(8).setBushes().setConifer().build(),
                // new Tree.Builder(BAOBAB, 10f, 150f, 21f, 40f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(BAOBAB)).setDecayDist(6).setGrowthTime(20).setDensity(0.1f, 0.3f).build(),
                // new Tree.Builder(BEECH, 220f, 300f, -15f, 9f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(BEECH)).setGrowthTime(8).setBushes().setDensity(0.25f, 1f).build(),
                new Tree.Builder(BIRCH, 20f, 180f, -15f, 7f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(BIRCH)).build(),
                new Tree.Builder(BLACKWOOD, 0f, 120f, 4f, 33f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(BLACKWOOD)).setHeight(12).setGrowthTime(8).build(),
                // new Tree.Builder(BLACK_WALNUT, 180f, 300f, -10f, 16f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(BLACK_WALNUT)).setGrowthTime(9).setBushes().build(),
                // new Tree.Builder(BOX, 180f, 400f, -8f, 15f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(BOX)).setGrowthTime(8).setBushes().setDensity(0.25f, 1f).build(),
                // new Tree.Builder(BRAZILWOOD, 290f, 550f, 14f, 37f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(BRAZILWOOD)).setGrowthTime(8).setBushes().setDensity(0.25f, 1f).build(),
                // new Tree.Builder(BUTTERNUT, 180f, 320f, -8f, 17f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(BUTTERNUT)).setGrowthTime(9).setBushes().build(),
                new Tree.Builder(CASTILLA, 140f, 350f, 7f, 27f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(CASTILLA)).setDensity(0.1f, 0.6f).setRadius(2).setHeight(26).setGrowthTime(10).build(),
                new Tree.Builder(CHESTNUT, 160f, 320f, 11f, 35f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(CHESTNUT)).build(),
                // new Tree.Builder(COCOBOLO, 255f, 500f, 20f, 50f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(COCOBOLO)).setRadius(1).setGrowthTime(8).setBushes().setDensity(0.5f, 2f).build(),
                // new Tree.Builder(CYPRESS, 140f, 350f, 4f, 33f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(CYPRESS)).setGrowthTime(8).setBushes().setConifer().build(),
                new Tree.Builder(DARK_OAK, 180f, 430f, -8f, 12f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(DARK_OAK)).setHeight(12).setRadius(2).setGrowthTime(10).build(),
                new Tree.Builder(DOUGLAS_FIR, 280f, 480f, -2f, 14f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(DOUGLAS_FIR)).setDominance(5.2f).setHeight(16).setBushes().setDensity(0.25f, 2f).build(),
                // new Tree.Builder(EBONY, 180f, 320f, 19f, 38f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(EBONY)).setGrowthTime(8).setBushes().build(),
                // new Tree.Builder(EUCALYPTUS, 120f, 300f, 18f, 39f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(EUCALYPTUS)).setGrowthTime(8).setBushes().setDensity(0.35f, 2f).build(),
                // new Tree.Builder(EUROPEAN_OAK, 140f, 430f, -8f, 15f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(EUROPEAN_OAK)).setGrowthTime(10).setBushes().build(),
                // new Tree.Builder(FEVER, 70f, 220f, 19f, 50f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(FEVER)).setGrowthTime(10).setBushes().setDensity(0.25f, 1f).build(),
                // new Tree.Builder(FLOWERING_OAK, 180f, 430f, -8f, 12f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(DefaultTrees.OAK)).setHeight(16).setGrowthTime(10).build(),
                // new Tree.Builder(FRUITWOOD, 180f, 550f, 11f, 30f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(FRUITWOOD)).setDominance(0).setGrowthTime(9).setBushes().setDensity(0f, 0f).build(),
                // new Tree.Builder(GINKGO, 240f, 550f, 6f, 20f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(GINKGO)).setGrowthTime(8).setDensity(0.25f, 1f).build(),
                // new Tree.Builder(GREENHEART, 310f, 500f, 23f, 50f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(GREENHEART)).setRadius(1).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.5f, 2f).build(),
                // new Tree.Builder(HAWTHORN, 180f, 400f, -8f, 14f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(HAWTHORN)).setGrowthTime(8).setDensity(0.25f, 1f).build(),
                // new Tree.Builder(HAZEL, 60f, 400f, -10f, 14f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(HAZEL)).setGrowthTime(8).setBushes().setDensity(0.25f, 1f).build(),
                // new Tree.Builder(HEMLOCK, 140f, 400f, -9f, 10f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(HEMLOCK)).setGrowthTime(8).setConifer().setDensity(0.25f, 1f).build(),
                // new Tree.Builder(HEVEA_BENTHAMIANA, 200f, 450f, 12f, 40f, GEN_TALL, TFCRegistries.TREES.getValue(HEVEA_BENTHAMIANA)).setDensity(0.1f, 0.6f).setRadius(2).setHeight(27).setGrowthTime(10).build(),
                // new Tree.Builder(HEVEA_BRAZILIENSIS, 200f, 500f, 15f, 40f, GEN_TALL, TFCRegistries.TREES.getValue(HEVEA_BRAZILIENSIS)).setDensity(0.1f, 0.6f).setRadius(2).setHeight(20).setGrowthTime(10).build(),
                // new Tree.Builder(HEVEA_CAMARGOANA, 200f, 450f, 12f, 40f, GEN_TALL, TFCRegistries.TREES.getValue(HEVEA_CAMARGOANA)).setDensity(0.1f, 0.6f).setRadius(2).setHeight(22).setGrowthTime(10).build(),
                // new Tree.Builder(HEVEA_CAMPORUM, 200f, 450f, 12f, 40f, GEN_TALL, TFCRegistries.TREES.getValue(HEVEA_CAMPORUM)).setDensity(0.1f, 0.6f).setRadius(2).setHeight(22).setGrowthTime(10).build(),
                // new Tree.Builder(HEVEA_GUIANENSIS, 200f, 450f, 12f, 40f, GEN_TALL, TFCRegistries.TREES.getValue(HEVEA_GUIANENSIS)).setDensity(0.1f, 0.6f).setRadius(2).setHeight(22).setGrowthTime(10).build(),
                // new Tree.Builder(HEVEA_MICROPHYLLA, 200f, 450f, 12f, 40f, GEN_TALL, TFCRegistries.TREES.getValue(HEVEA_MICROPHYLLA)).setDensity(0.1f, 0.6f).setRadius(2).setHeight(22).setGrowthTime(10).build(),
                // new Tree.Builder(HEVEA_NITIDA, 200f, 450f, 12f, 40f, GEN_TALL, TFCRegistries.TREES.getValue(HEVEA_NITIDA)).setDensity(0.1f, 0.6f).setRadius(2).setHeight(22).setGrowthTime(10).build(),
                // new Tree.Builder(HEVEA_PAUCIFLORA, 200f, 450f, 12f, 40f, GEN_TALL, TFCRegistries.TREES.getValue(HEVEA_PAUCIFLORA)).setDensity(0.1f, 0.6f).setRadius(2).setHeight(22).setGrowthTime(10).build(),
                // new Tree.Builder(HEVEA_RIGIDOFOLIA, 200f, 450f, 12f, 40f, GEN_TALL, TFCRegistries.TREES.getValue(HEVEA_RIGIDOFOLIA)).setDensity(0.1f, 0.6f).setRadius(2).setHeight(22).setGrowthTime(10).build(),
                // new Tree.Builder(HEVEA_SPRUCEANA, 200f, 450f, 12f, 40f, GEN_TALL, TFCRegistries.TREES.getValue(HEVEA_SPRUCEANA)).setDensity(0.1f, 0.6f).setRadius(2).setHeight(22).setGrowthTime(10).build(),
                new Tree.Builder(HICKORY, 80f, 250f, 7f, 29f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(HICKORY)).setGrowthTime(10).build(),
                // new Tree.Builder(HOLLY, 140f, 400f, -4f, 16f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(HOLLY)).setGrowthTime(8).setBushes().setDensity(0.25f, 1f).build(),
                // new Tree.Builder(HORNBEAM, 140f, 430f, -10f, 12f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(HORNBEAM)).setGrowthTime(10).setBushes().build(),
                // new Tree.Builder(IPE, 150f, 350f, 15f, 32f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(IPE)).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.2f, 2f).build(),
                // new Tree.Builder(IROKO, 300f, 500f, 21f, 50f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(IROKO)).setRadius(1).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.5f, 2f).build(),
                // new Tree.Builder(IRONWOOD, 30f, 210f, 11f, 36f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(IRONWOOD)).setDecayDist(6).setGrowthTime(11).setBushes().setDensity(0.1f, 0.6f).build(),
                // new Tree.Builder(JACARANDA, 180f, 300f, 10f, 34f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(JACARANDA)).setGrowthTime(8).setDensity(0.25f, 2f).build(),
                new Tree.Builder(JAPANESE_SPRUCE, 120f, 380f, -11f, 6f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(JAPANESE_SPRUCE)).setConifer().setDensity(0.1f, 0.8f).build(),
                // new Tree.Builder(JOSHUA_TREE, 20f, 150f, 15f, 40f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(JOSHUA_TREE)).setDominance(0f).setDensity(0f, 0f).setGrowthTime(8).setConifer().build(),
                // new Tree.Builder(JUNIPER, 80f, 350f, -8f, 20f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(JUNIPER)).setGrowthTime(8).setConifer().setDensity(0.25f, 0.75f).build(),
                new Tree.Builder(KAPOK, 210f, 500f, 15f, 35f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(KAPOK)).setDominance(8.5f).setRadius(3).setHeight(36).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.6f, 2f).build(),
                // new Tree.Builder(KAURI, 330f, 500f, 23f, 50f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(KAURI)).setRadius(1).setGrowthTime(10).setBushes().setDensity(0.5f, 2f).build(),
                // new Tree.Builder(LARCH, 60f, 400f, -12f, 15f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(LARCH)).setGrowthTime(8).setConifer().setDensity(0.25f, 1f).build(),
                // new Tree.Builder(LIMBA, 290f, 550f, 14f, 37f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(LIMBA)).setGrowthTime(9).setBushes().setDensity(0.25f, 1f).build(),
                // new Tree.Builder(LOCUST, 120f, 290f, -6f, 15f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(LOCUST)).setGrowthTime(8).setBushes().build(),
                // new Tree.Builder(LOGWOOD, 180f, 430f, 12f, 35f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(LOGWOOD)).setGrowthTime(8).setBushes().setDensity(0.25f, 1f).build(),
                // new Tree.Builder(MACLURA, 140f, 400f, -1f, 17f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(MACLURA)).setGrowthTime(8).setBushes().setDensity(0.25f, 1f).build(),
                // new Tree.Builder(MAHOE, 180f, 350f, 13f, 32f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(MAHOE)).setHeight(16).setGrowthTime(8).setBushes().build(),
                // new Tree.Builder(MAHOGANY, 270f, 500f, 23f, 42f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(MAHOGANY)).setRadius(1).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.5f, 2f).build(),
                // new Tree.Builder(MANGROVE, 200f, 500f, 15f, 40f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(MANGROVE)).setDominance(0f).setDensity(0f, 0f).setRadius(1).setGrowthTime(8).setBushes().build(),
                new Tree.Builder(MAPLE, 140f, 360f, 3f, 20f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(MAPLE)).setDominance(6.3f).build(),
                // new Tree.Builder(MARBLEWOOD, 180f, 500f, 16f, 35f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(MARBLEWOOD)).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.2f, 2f).build(),
                // new Tree.Builder(MESSMATE, 120f, 270f, 2f, 27f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(MESSMATE)).setGrowthTime(10).setBushes().setDensity(0.2f, 2f).build(),
                // new Tree.Builder(MOUNTAIN_ASH, 80f, 270f, 9f, 33f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(MOUNTAIN_ASH)).setGrowthTime(10).setBushes().setDensity(0.4f, 2f).build(),
                // new Tree.Builder(MULBERRY, 140f, 420f, -30f, 28f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(MULBERRY)).setGrowthTime(10).setDensity(0.25f, 1f).build(),
                // new Tree.Builder(NORDMANN_FIR, 100f, 380f, -16f, 7f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(NORDMANN_FIR)).setGrowthTime(8).setConifer().setDensity(0.1f, 0.9f).build(),
                new Tree.Builder(NORWAY_SPRUCE, 120f, 380f, -11f, 6f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(NORWAY_SPRUCE)).setConifer().setGrowthTime(8).setDensity(0.1f, 0.8f).build(),
                new Tree.Builder(OAK, 180f, 430f, -8f, 12f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(OAK)).setHeight(16).setGrowthTime(10).build(),
                new Tree.Builder(PALM, 280f, 500f, 16f, 35f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(PALM)).setDecayDist(6).build(),
                new Tree.Builder(PINE, 60f, 250f, -15f, 7f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(PINE)).setConifer().setDensity(0.1f, 0.8f).build(),
                // new Tree.Builder(PINK_CHERRY, 180f, 300f, 0f, 20f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(PINK_CHERRY)).setGrowthTime(8).setDensity(0.25f, 2f).build(),
                // new Tree.Builder(PINK_IVORY, 210f, 500f, 18f, 31f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(PINK_IVORY)).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.2f, 2f).build(),
                // new Tree.Builder(POPLAR, 140f, 400f, -7f, 14f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(POPLAR)).setGrowthTime(8).setDensity(0.25f, 1f).build(),
                // new Tree.Builder(PURPLEHEART, 310f, 500f, 22f, 50f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(PURPLEHEART)).setRadius(1).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.5f, 2f).build(),
                // new Tree.Builder(REDWOOD, 160f, 400f, 0f, 17f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(REDWOOD)).setDecayDist(6).setGrowthTime(18).setConifer().setBushes().setDensity(0.4f, 2f).build(),
                // new Tree.Builder(RED_CEDAR, 10f, 240f, -8f, 17f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(RED_CEDAR)).setDecayDist(6).setGrowthTime(18).setBushes().setConifer().setBushes().setDensity(0.4f, 2f).build(),
                // new Tree.Builder(RED_ELM, 60f, 290f, 2f, 20f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(RED_ELM)).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.4f, 2f).build(),
                new Tree.Builder(ROSEWOOD, 10f, 190f, 8f, 18f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(ROSEWOOD)).setHeight(12).setGrowthTime(8).build(),
                // new Tree.Builder(ROWAN, 180f, 400f, -15f, 8f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(ROWAN)).setGrowthTime(8).setDensity(0.25f, 1f).build(),
                // new Tree.Builder(RUBBER_FIG, 210f, 550f, 16f, 35f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(RUBBER_FIG)).setDecayDist(6).setGrowthTime(16).setBushes().setDensity(0.2f, 1f).build(),
                // new Tree.Builder(SAPODILLA, 140f, 350f, 7f, 27f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(SAPODILLA)).setDensity(0.1f, 0.6f).setRadius(2).setHeight(30).setGrowthTime(10).build(),
                new Tree.Builder(SEQUOIA, 250f, 420f, -5f, 12f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(SEQUOIA)).setRadius(3).setHeight(24).setDecayDist(6).setGrowthTime(18).setConifer().setBushes().setDensity(0.4f, 0.9f).build(),
                new Tree.Builder(SILVER_BIRCH, 20f, 180f, -15f, 7f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(SILVER_BIRCH)).build(),
                new Tree.Builder(SPRUCE, 120f, 380f, -11f, 6f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(SPRUCE)).setConifer().setDensity(0.1f, 0.8f).build(),
                // new Tree.Builder(SWEETGUM, 140f, 360f, -2f, 18f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(SWEETGUM)).setDecayDist(6).setGrowthTime(16).setBushes().setDensity(0.2f, 1f).build(),
                new Tree.Builder(SYCAMORE, 120f, 290f, 17f, 33f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(SYCAMORE)).setGrowthTime(8).setBushes().setDensity(0.25f, 2f).build(),
                //  new Tree.Builder(SYZYGIUM, 140f, 360f, 13f, 35f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(SYZYGIUM)).setDecayDist(6).setGrowthTime(16).setBushes().setDensity(0.2f, 1f).build(),
                // new Tree.Builder(TEAK, 180f, 430f, 17f, 35f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(TEAK)).setGrowthTime(8).setBushes().setDensity(0.25f, 1f).build(),
                // new Tree.Builder(WALNUT, 180f, 300f, -10f, 16f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(WALNUT)).setGrowthTime(9).setBushes().build(),
                // new Tree.Builder(WEEPING_WILLOW, 230f, 400f, 15f, 32f, GEN_WILLOW, TFCRegistries.TREES.getValue(WILLOW)).setGrowthTime(11).setBushes().setDensity(0.7f, 2f).build(),
                // new Tree.Builder(WENGE, 255f, 500f, 20f, 50f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(WENGE)).setRadius(1).setGrowthTime(8).setBushes().setDensity(0.5f, 2f).build(),
                // new Tree.Builder(WHITEBEAM, 140f, 430f, -10f, 12f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(WHITEBEAM)).setGrowthTime(10).setBushes().build(),
                new Tree.Builder(WHITE_CEDAR, 10f, 240f, -8f, 17f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(WHITE_CEDAR)).setHeight(16).build(),
                // new Tree.Builder(WHITE_CHERRY, 180f, 300f, 0f, 20f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(WHITE_CHERRY)).setGrowthTime(8).setDensity(0.25f, 2f).build(),
                new Tree.Builder(WHITE_ELM, 140f, 360f, 3f, 20f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(WHITE_ELM)).setGrowthTime(8).setBushes().setHeight(16).build(),
                new Tree.Builder(WILLOW, 230f, 400f, 15f, 32f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(WILLOW)).setGrowthTime(11).setBushes().setDensity(0.7f, 2f).build()
                // new Tree.Builder(YELLOW_MERANTI, 260f, 500f, 21f, 50f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(YELLOW_MERANTI)).setRadius(1).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.5f, 2f).build(),
                // new Tree.Builder(YEW, 180f, 350f, -15f, 11f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(YEW)).setGrowthTime(10).setBushes().build(),
                // new Tree.Builder(ZEBRAWOOD, 280f, 500f, 23f, 50f, GEN_DYNAMIC, TFCRegistries.TREES.getValue(ZEBRAWOOD)).setRadius(1).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.5f, 2f).build()
        );
    }
}
