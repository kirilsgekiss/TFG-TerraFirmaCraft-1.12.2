/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.types;

import net.dries007.tfc.TerraFirmaCraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import net.dries007.tfc.api.registries.TFCRegistryEvent;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.api.util.ITreeGenerator;
import net.dries007.tfc.world.classic.worldgen.trees.*;

import static net.dries007.tfc.TerraFirmaCraft.*;
import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

@SuppressWarnings("WeakerAccess")
@Mod.EventBusSubscriber(modid = MOD_ID)
public final class DefaultTrees
{
    /**
     * Default Tree ResourceLocations
     */
    public static final ResourceLocation ACACIA = new ResourceLocation(MOD_ID, "acacia");
    public static final ResourceLocation ASH = new ResourceLocation(MOD_ID, "ash");
    public static final ResourceLocation ASPEN = new ResourceLocation(MOD_ID, "aspen");
    public static final ResourceLocation BIRCH = new ResourceLocation(MOD_ID, "birch");
    public static final ResourceLocation BLACKWOOD = new ResourceLocation(MOD_ID, "blackwood");
    public static final ResourceLocation CHESTNUT = new ResourceLocation(MOD_ID, "chestnut");
    public static final ResourceLocation DOUGLAS_FIR = new ResourceLocation(MOD_ID, "douglas_fir");
    public static final ResourceLocation HICKORY = new ResourceLocation(MOD_ID, "hickory");
    public static final ResourceLocation MAPLE = new ResourceLocation(MOD_ID, "maple");
    public static final ResourceLocation OAK = new ResourceLocation(MOD_ID, "oak");
    public static final ResourceLocation PALM = new ResourceLocation(MOD_ID, "palm");
    public static final ResourceLocation PINE = new ResourceLocation(MOD_ID, "pine");
    public static final ResourceLocation ROSEWOOD = new ResourceLocation(MOD_ID, "rosewood");
    public static final ResourceLocation SEQUOIA = new ResourceLocation(MOD_ID, "sequoia");
    public static final ResourceLocation SPRUCE = new ResourceLocation(MOD_ID, "spruce");
    public static final ResourceLocation SYCAMORE = new ResourceLocation(MOD_ID, "sycamore");
    public static final ResourceLocation WHITE_CEDAR = new ResourceLocation(MOD_ID, "white_cedar");
    public static final ResourceLocation WILLOW = new ResourceLocation(MOD_ID, "willow");
    public static final ResourceLocation KAPOK = new ResourceLocation(MOD_ID, "kapok");
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
    public static final ResourceLocation NORWAY_SPRUCE = new ResourceLocation(MOD_ID, "norway_spruce");
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
    public static final ResourceLocation WHITE_ELM = new ResourceLocation(MOD_ID, "white_elm");
    public static final ResourceLocation WHITEBEAM = new ResourceLocation(MOD_ID, "whitebeam");
    public static final ResourceLocation YELLOW_MERANTI = new ResourceLocation(MOD_ID, "yellow_meranti");
    public static final ResourceLocation YEW = new ResourceLocation(MOD_ID, "yew");
    public static final ResourceLocation ZEBRAWOOD = new ResourceLocation(MOD_ID, "zebrawood");

    /**
     * Simple ITreeGenerator instances.
     */
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
    public static final ITreeGenerator GEN_BRAZILWOOD = new TreeGenVariants(true, 14);
    public static final ITreeGenerator GEN_BUTTERNUT = new TreeGenVariants(true, 45);
    public static final ITreeGenerator GEN_COCOBOLO = new TreeGenVariants(true, 34);
    public static final ITreeGenerator GEN_CYPRESS = new TreeGenVariants(true, 5);
    public static final ITreeGenerator GEN_EBONY = new TreeGenVariants(true, 20);
    public static final ITreeGenerator GEN_EUCALYPTUS = new TreeGenVariants(true, 48);
    public static final ITreeGenerator GEN_EUROPEAN_OAK = new TreeGenVariants(true, 45);
    public static final ITreeGenerator GEN_FEVER = new TreeGenVariants(true, 13);
    public static final ITreeGenerator GEN_FRUITWOOD = new TreeGenVariants(true, 3);
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

    // Seasonal & Fruit-bearing Trees
    public static final Tree ASH_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "ash"), 60f, 140f, -6f, 12f, GEN_NORMAL).setBushes().setBurnInfo(696f, 1250).build();
    public static final Tree ASPEN_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "aspen"), 70f, 280f, -10f, 16f, GEN_ASPEN).setGrowthTime(8).setDensity(0.25f, 1f).setBurnInfo(611f, 1000).build();
    public static final Tree BIRCH_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "birch"), 20f, 180f, -15f, 7f, GEN_TALL).setRadius(1).setTannin().setBurnInfo(652f, 1750).build();
    public static final Tree CHESTNUT_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "chestnut"), 160f, 320f, 11f, 35f, GEN_NORMAL).setTannin().setBushes().setBurnInfo(651f, 1500).build();
    public static final Tree HICKORY_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "hickory"), 80f, 250f, 7f, 29f, GEN_TALL).setGrowthTime(10).setBushes().setTannin().setBurnInfo(762f, 2000).build();
    public static final Tree MAPLE_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "maple"), 140f, 360f, 3f, 20f, GEN_MEDIUM).setDominance(6.3f).setRadius(1).setTannin().setBurnInfo(745f, 2000).build();
    public static final Tree OAK_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "oak"), 180f, 430f, -8f, 12f, GEN_TALL).setHeight(16).setGrowthTime(10).setBushes().setTannin().setBurnInfo(728f, 2250).build();
    public static final Tree SYCAMORE_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "sycamore"), 120f, 290f, 17f, 33f, GEN_MEDIUM).setGrowthTime(8).setBushes().setDensity(0.25f, 2f).setBurnInfo(653f, 1750).build();
    public static final Tree WHITE_CEDAR_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "white_cedar"), 10f, 240f, -8f, 17f, GEN_TALL_SINGLE).setHeight(16).setBushes().setBurnInfo(625f, 1500).build();

    public static final Tree BAOBAB_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "baobab"), 10f, 150f, 21f, 40f, GEN_BAOBAB).setDecayDist(6).setGrowthTime(20).setDensity(0.1f, 0.3f).setBurnInfo(478f, 1000).build();
    public static final Tree EUCALYPTUS_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "eucalyptus"), 120f, 300f, 18f, 39f, GEN_EUCALYPTUS).setGrowthTime(8).setBushes().setDensity(0.35f, 2f).setBurnInfo(705f, 1000).build();
    public static final Tree HAWTHORN_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "hawthorn"), 180f, 400f, -8f, 14f, GEN_HAWTHORN).setGrowthTime(8).setDensity(0.25f, 1f).setBurnInfo(683f, 1500).build();
    public static final Tree MULBERRY_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "mulberry"), 140f, 420f, -30f, 28f, GEN_MULBERRY).setGrowthTime(10).setDensity(0.25f, 1f).setBurnInfo(705f, 1860).build();
    public static final Tree MACLURA_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "maclura"), 140f, 400f, -1f, 17f, GEN_MACLURA).setGrowthTime(8).setBushes().setDensity(0.25f, 1f).setBurnInfo(773f, 1930).build();
    public static final Tree MAHOGANY_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "mahogany"), 270f, 500f, 23f, 42f, GEN_MAHOGANY).setRadius(1).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.5f, 2f).setBurnInfo(773f, 1000).build();
    public static final Tree PINK_IVORY_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "pink_ivory"), 210f, 500f, 18f, 31f, GEN_PINK_IVORY).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.2f, 2f).setBurnInfo(773f, 1000).build();
    public static final Tree RED_CEDAR_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "red_cedar"), 10f, 240f, -8f, 17f, GEN_RED_CEDAR).setDecayDist(6).setGrowthTime(18).setBushes().setConifer().setBushes().setTannin().setDensity(0.4f, 2f).setBurnInfo(618f, 1750).build();
    public static final Tree ROWAN_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "rowan"), 180f, 400f, -15f, 8f, GEN_ROWAN).setGrowthTime(8).setDensity(0.25f, 1f).setBurnInfo(645f, 2000).build();
    public static final Tree SYZYGIUM_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "syzygium"), 140f, 360f, 13f, 35f, GEN_SYZYGIUM).setDecayDist(6).setGrowthTime(16).setBushes().setTannin().setDensity(0.2f, 1f).setBurnInfo(745f, 2000).build();
    public static final Tree YEW_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "yew"), 180f, 350f, -15f, 11f, GEN_YEW).setGrowthTime(10).setBushes().setBurnInfo(813f, 2150).build();
    public static final Tree JACARANDA_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "jacaranda"), 180f, 300f, 10f, 34f, GEN_JACARANDA).setGrowthTime(8).setDensity(0.25f, 2f).setBurnInfo(795f, 1250).build();
    public static final Tree JOSHUA_TREE_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "joshua_tree"), 20f, 150f, 15f, 40f, GEN_JOSHUA_TREE).setDominance(0f).setDensity(0f, 0f).setGrowthTime(8).setConifer().setBurnInfo(696f, 1250).build();
    public static final Tree JUNIPER_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "juniper"), 80f, 350f, -8f, 20f, GEN_JUNIPER).setGrowthTime(8).setConifer().setDensity(0.25f, 0.75f).setTannin().setBurnInfo(632f, 1750).build();
    public static final Tree IPE_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "ipe"), 150f, 350f, 15f, 32f, GEN_IPE).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.2f, 2f).setBurnInfo(785f, 1200).build();
    public static final Tree PINK_CHERRY_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "pink_cherry"), 180f, 300f, 0f, 20f, GEN_PINK_CHERRY).setGrowthTime(8).setDensity(0.25f, 2f).setBurnInfo(795f, 1250).build();
    public static final Tree WHITE_CHERRY_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "white_cherry"), 180f, 300f, 0f, 20f, GEN_WHITE_CHERRY).setGrowthTime(8).setDensity(0.25f, 2f).setBurnInfo(795f, 1250).build();
    public static final Tree SWEETGUM_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "sweetgum"), 140f, 360f, -2f, 18f, GEN_SWEETGUM).setDecayDist(6).setGrowthTime(16).setBushes().setTannin().setDensity(0.2f, 1f).setBurnInfo(745f, 2000).build();
    public static final Tree LARCH_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "larch"), 60f, 400f, -12f, 15f, GEN_LARCH).setGrowthTime(8).setConifer().setDensity(0.25f, 1f).setBurnInfo(632f, 1250).build();
    public static final Tree ALDER_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "alder"), 60f, 400f, -4f, 13f, GEN_ALDER).setGrowthTime(8).setBushes().setDensity(0.25f, 2f).setBurnInfo(601f, 1000).build();
    public static final Tree BEECH_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "beech"), 220f, 300f, -15f, 9f, GEN_BEECH).setGrowthTime(8).setBushes().setTannin().setDensity(0.25f, 1f).setBurnInfo(703f, 1750).build();
    public static final Tree BLACK_WALNUT_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "black_walnut"), 180f, 300f, -10f, 16f, GEN_BLACK_WALNUT).setGrowthTime(9).setBushes().setBurnInfo(758f, 1800).build();
    public static final Tree BUTTERNUT_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "butternut"), 180f, 320f, -8f, 17f, GEN_BUTTERNUT).setGrowthTime(9).setBushes().setBurnInfo(758f, 1800).build();
    public static final Tree EUROPEAN_OAK_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "european_oak"), 140f, 430f, -8f, 15f, GEN_EUROPEAN_OAK).setGrowthTime(10).setBushes().setTannin().setBurnInfo(728f, 2250).build();
    public static final Tree GINKGO_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "ginkgo"), 240f, 550f, 6f, 20f, GEN_GINKGO).setGrowthTime(8).setDensity(0.25f, 1f).setBurnInfo(710f, 1000).build();
    public static final Tree HAZEL_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "hazel"), 60f, 400f, -10f, 14f, GEN_HAZEL).setGrowthTime(8).setBushes().setDensity(0.25f, 1f).setBurnInfo(683f, 1500).build();
    public static final Tree HORNBEAM_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "hornbeam"), 140f, 430f, -10f, 12f, GEN_HORNBEAM).setGrowthTime(10).setBushes().setTannin().setBurnInfo(728f, 2250).build();
    public static final Tree LOCUST_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "locust"), 120f, 290f, -6f, 15f, GEN_LOCUST).setGrowthTime(8).setBushes().setBurnInfo(653f, 1750).build();
    public static final Tree POPLAR_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "poplar"), 140f, 400f, -7f, 14f, GEN_POPLAR).setGrowthTime(8).setDensity(0.25f, 1f).setBurnInfo(609f, 1000).build();
    public static final Tree RED_ELM_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "red_elm"), 60f, 290f, 2f, 20f, GEN_RED_ELM).setDecayDist(6).setGrowthTime(18).setBushes().setTannin().setDensity(0.4f, 2f).setBurnInfo(618f, 1750).build();
    public static final Tree WALNUT_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "walnut"), 180f, 300f, -10f, 16f, GEN_WALNUT).setGrowthTime(9).setBushes().setBurnInfo(758f, 1800).build();
    public static final Tree WHITE_ELM_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "white_elm"), 60f, 290f, 2f, 20f, GEN_WHITE_ELM).setGrowthTime(8).setBushes().setTannin().setBurnInfo(653f, 1750).build();
    public static final Tree WHITEBEAM_TREE = new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "whitebeam"), 140f, 430f, -10f, 12f, GEN_WHITEBEAM).setGrowthTime(10).setBushes().setTannin().setBurnInfo(728f, 1750).build();

    @SubscribeEvent
    public static void onPreRegisterRockCategory(TFCRegistryEvent.RegisterPreBlock<Tree> event)
    {
        event.getRegistry().registerAll(
            new Tree.Builder(ACACIA, 30f, 210f, 19f, 31f, GEN_ACACIA).setHeight(12).setGrowthTime(11).setDensity(0.1f, 0.6f).setBurnInfo(650f, 1000).build(),
            new Tree.Builder(ASH, 60f, 140f, -6f, 12f, GEN_NORMAL).setBurnInfo(696f, 1250).build(),
            new Tree.Builder(ASPEN, 10f, 80f, -10f, 16f, GEN_MEDIUM).setRadius(1).setGrowthTime(8).setBurnInfo(611f, 1000).build(),
            new Tree.Builder(BIRCH, 20f, 180f, -15f, 7f, GEN_TALL).setRadius(1).setTannin().setBurnInfo(652f, 1750).build(),
            new Tree.Builder(BLACKWOOD, 0f, 120f, 4f, 33f, GEN_MEDIUM).setHeight(12).setGrowthTime(8).setBurnInfo(720f, 1750).build(),
            new Tree.Builder(CHESTNUT, 160f, 320f, 11f, 35f, GEN_NORMAL).setTannin().setBurnInfo(651f, 1500).build(),
            new Tree.Builder(DOUGLAS_FIR, 280f, 480f, -2f, 14f, GEN_TALL).setDominance(5.2f).setHeight(16).setBushes().setTannin().setDensity(0.25f, 2f).setBurnInfo(707f, 1500).build(),
            new Tree.Builder(HICKORY, 80f, 250f, 7f, 29f, GEN_TALL).setGrowthTime(10).setTannin().setBurnInfo(762f, 2000).build(),
            new Tree.Builder(KAPOK, 210f, 500f, 15f, 35f, GEN_KAPOK_COMPOSITE).setDominance(8.5f).setRadius(3).setHeight(24).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.6f, 2f).setBurnInfo(645f, 1000).build(),
            new Tree.Builder(MAPLE, 140f, 360f, 3f, 20f, GEN_MEDIUM).setDominance(6.3f).setRadius(1).setTannin().setBurnInfo(745f, 2000).build(),
            new Tree.Builder(OAK, 180f, 430f, -8f, 12f, GEN_TALL).setHeight(16).setGrowthTime(10).setTannin().setBurnInfo(728f, 2250).build(),
            new Tree.Builder(PALM, 280f, 500f, 16f, 35f, GEN_TROPICAL).setDecayDist(6).setBurnInfo(730f, 1250).build(),
            new Tree.Builder(PINE, 60f, 250f, -15f, 7f, GEN_CONIFER).setRadius(1).setConifer().setDensity(0.1f, 0.8f).setBurnInfo(627f, 1250).build(),
            new Tree.Builder(ROSEWOOD, 10f, 190f, 8f, 18f, GEN_MEDIUM).setHeight(12).setGrowthTime(8).setBurnInfo(640f, 1500).build(),
            new Tree.Builder(SEQUOIA, 250f, 420f, -5f, 12f, GEN_SEQUOIA).setRadius(3).setHeight(24).setDecayDist(6).setGrowthTime(18).setConifer().setBushes().setTannin().setDensity(0.4f, 0.9f).setBurnInfo(612f, 1750).build(),
            new Tree.Builder(SPRUCE, 120f, 380f, -11f, 6f, GEN_CONIFER).setRadius(1).setConifer().setDensity(0.1f, 0.8f).setBurnInfo(608f, 1500).build(),
            new Tree.Builder(SYCAMORE, 120f, 290f, 17f, 33f, GEN_MEDIUM).setGrowthTime(8).setBushes().setDensity(0.25f, 2f).setBurnInfo(653f, 1750).build(),
            new Tree.Builder(WHITE_CEDAR, 10f, 240f, -8f, 17f, GEN_TALL).setHeight(16).setBurnInfo(625f, 1500).build(),
            new Tree.Builder(WILLOW, 230f, 400f, 15f, 32f, GEN_WILLOW).setRadius(1).setGrowthTime(11).setBushes().setDensity(0.7f, 2f).setBurnInfo(603f, 1000).build()
        );

        // Other Trees
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "african_padauk"), 275f, 500f, 22f, 50f, GEN_AFRICAN_PADAUK).setRadius(1).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.5f, 2f).setBurnInfo(745f, 1500).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "angelim"), 320f, 500f, 22f, 50f, GEN_ANGELIM).setRadius(1).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.5f, 2f).setBurnInfo(773f, 1200).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "box"), 180f, 400f, -8f, 15f, GEN_TALL_TFC).setGrowthTime(8).setBushes().setDensity(0.25f, 1f).setBurnInfo(683f, 1500).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "brazilwood"), 290f, 550f, 14f, 37f, GEN_BRAZILWOOD).setGrowthTime(8).setBushes().setDensity(0.25f, 1f).setBurnInfo(710f, 1000).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "cocobolo"), 255f, 500f, 20f, 50f, GEN_COCOBOLO).setRadius(1).setGrowthTime(8).setBushes().setDensity(0.5f, 2f).setBurnInfo(773f, 1000).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "ebony"), 180f, 320f, 19f, 38f, GEN_EBONY).setGrowthTime(8).setBushes().setBurnInfo(795f, 1000).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "fever"), 70f, 220f, 19f, 50f, GEN_FEVER).setGrowthTime(10).setBushes().setDensity(0.25f, 1f).setBurnInfo(590f, 1000).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "fruitwood"), 180f, 550f, 11f, 30f, GEN_FRUITWOOD).setDominance(0).setGrowthTime(9).setBushes().setDensity(0f, 0f).setBurnInfo(720f, 1000).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "greenheart"), 310f, 500f, 23f, 50f, GEN_GREENHEART).setRadius(1).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.5f, 2f).setBurnInfo(793f, 1700).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "holly"), 140f, 400f, -4f, 16f, GEN_HOLLY).setGrowthTime(8).setBushes().setDensity(0.25f, 1f).setBurnInfo(609f, 1000).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "iroko"), 300f, 500f, 21f, 50f, GEN_IROKO).setRadius(1).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.5f, 2f).setBurnInfo(785f, 1200).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "ironwood"), 30f, 210f, 11f, 36f, GEN_IRONWOOD).setDecayDist(6).setGrowthTime(11).setBushes().setDensity(0.1f, 0.6f).setBurnInfo(694f, 1170).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "kauri"), 330f, 500f, 23f, 50f, GEN_KAURI).setRadius(1).setGrowthTime(10).setBushes().setDensity(0.5f, 2f).setBurnInfo(730f, 1250).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "limba"), 290f, 550f, 14f, 37f, GEN_LIMBA).setGrowthTime(9).setBushes().setDensity(0.25f, 1f).setBurnInfo(710f, 1000).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "logwood"), 180f, 430f, 12f, 35f, GEN_LOGWOOD).setGrowthTime(8).setBushes().setDensity(0.25f, 1f).setBurnInfo(695f, 1000).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "mahoe"), 180f, 350f, 13f, 32f, GEN_MAHOE).setHeight(16).setGrowthTime(8).setBushes().setBurnInfo(783f, 1100).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "mangrove"), 200f, 500f, 15f, 40f, GEN_MANGROVE).setDominance(0f).setDensity(0f, 0f).setRadius(1).setGrowthTime(8).setBushes().setBurnInfo(783f, 1100).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "marblewood"), 180f, 500f, 16f, 35f, GEN_MARBLEWOOD).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.2f, 2f).setBurnInfo(837f, 1200).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "messmate"), 120f, 270f, 2f, 27f, GEN_MESSMATE).setGrowthTime(10).setBushes().setDensity(0.2f, 2f).setBurnInfo(696f, 1250).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "mountain_ash"), 80f, 270f, 9f, 33f, GEN_MOUNTAIN_ASH).setGrowthTime(10).setBushes().setDensity(0.4f, 2f).setBurnInfo(696f, 1250).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "purpleheart"), 310f, 500f, 22f, 50f, GEN_PURPLEHEART).setRadius(1).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.5f, 2f).setBurnInfo(793f, 1700).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "rubber_fig"), 210f, 550f, 16f, 35f, GEN_RUBBER_FIG).setDecayDist(6).setGrowthTime(16).setBushes().setDensity(0.2f, 1f).setBurnInfo(785f, 1440).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "teak"), 180f, 430f, 17f, 35f, GEN_TEAK).setGrowthTime(8).setBushes().setDensity(0.25f, 1f).setBurnInfo(695f, 1000).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "wenge"), 255f, 500f, 20f, 50f, GEN_WENGE).setRadius(1).setGrowthTime(8).setBushes().setDensity(0.5f, 2f).setBurnInfo(773f, 1250).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "yellow_meranti"), 260f, 500f, 21f, 50f, GEN_YELLOW_MERANTI).setRadius(1).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.5f, 2f).setBurnInfo(837f, 1200).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "zebrawood"), 280f, 500f, 23f, 50f, GEN_ZEBRAWOOD).setRadius(1).setDecayDist(6).setGrowthTime(18).setBushes().setDensity(0.5f, 2f).setBurnInfo(822f, 1570).build());

        // Coniferous Trees
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "bald_cypress"), 180f, 500f, 10f, 38f, GEN_BALD_CYPRESS).setDominance(0f).setDensity(0f, 0f).setGrowthTime(8).setBushes().setConifer().setBurnInfo(770f, 1300).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "cypress"), 140f, 350f, 4f, 33f, GEN_CYPRESS).setGrowthTime(8).setBushes().setConifer().setBurnInfo(783f, 1100).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "hemlock"), 140f, 400f, -9f, 10f, GEN_HEMLOCK).setGrowthTime(8).setConifer().setDensity(0.25f, 1f).setBurnInfo(609f, 1000).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "nordmann_fir"), 100f, 380f, -16f, 7f, GEN_NORDMANN_FIR).setGrowthTime(8).setConifer().setDensity(0.1f, 0.9f).setBurnInfo(628f, 1500).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "norway_spruce"), 100f, 380f, -20f, 5f, GEN_NORWAY_SPRUCE).setGrowthTime(8).setConifer().setDensity(0.1f, 0.9f).setBurnInfo(628f, 1500).build());
        event.getRegistry().registerAll(new Tree.Builder(new ResourceLocation(TerraFirmaCraft.MOD_ID, "redwood"), 160f, 400f, 0f, 17f, GEN_REDWOOD).setDecayDist(6).setGrowthTime(18).setConifer().setBushes().setTannin().setDensity(0.4f, 2f).setBurnInfo(618f, 1750).build());

        // Seasonal & Fruit-bearing Trees
        event.getRegistry().registerAll(ASH_TREE);
        event.getRegistry().registerAll(ASPEN_TREE);
        event.getRegistry().registerAll(BIRCH_TREE);
        event.getRegistry().registerAll(CHESTNUT_TREE);
        event.getRegistry().registerAll(HICKORY_TREE);
        event.getRegistry().registerAll(MAPLE_TREE);
        event.getRegistry().registerAll(OAK_TREE);
        event.getRegistry().registerAll(SYCAMORE_TREE);
        event.getRegistry().registerAll(WHITE_CEDAR_TREE);
        event.getRegistry().registerAll(JOSHUA_TREE_TREE);

        event.getRegistry().registerAll(BAOBAB_TREE);
        event.getRegistry().registerAll(EUCALYPTUS_TREE);
        event.getRegistry().registerAll(HAWTHORN_TREE);
        event.getRegistry().registerAll(MULBERRY_TREE);
        event.getRegistry().registerAll(MACLURA_TREE);
        event.getRegistry().registerAll(MAHOGANY_TREE);
        event.getRegistry().registerAll(PINK_IVORY_TREE);
        event.getRegistry().registerAll(RED_CEDAR_TREE);
        event.getRegistry().registerAll(ROWAN_TREE);
        event.getRegistry().registerAll(SYZYGIUM_TREE);
        event.getRegistry().registerAll(YEW_TREE);
        event.getRegistry().registerAll(JACARANDA_TREE);
        event.getRegistry().registerAll(JUNIPER_TREE);
        event.getRegistry().registerAll(IPE_TREE);
        event.getRegistry().registerAll(PINK_CHERRY_TREE);
        event.getRegistry().registerAll(WHITE_CHERRY_TREE);
        event.getRegistry().registerAll(SWEETGUM_TREE);
        event.getRegistry().registerAll(LARCH_TREE);
        event.getRegistry().registerAll(ALDER_TREE);
        event.getRegistry().registerAll(BEECH_TREE);
        event.getRegistry().registerAll(BLACK_WALNUT_TREE);
        event.getRegistry().registerAll(BUTTERNUT_TREE);
        //event.getRegistry().registerAll(CYPRESS_TREE); // Whoops, it's a conifer, not deciduous!
        event.getRegistry().registerAll(EUROPEAN_OAK_TREE);
        event.getRegistry().registerAll(GINKGO_TREE);
        event.getRegistry().registerAll(HAZEL_TREE);
        event.getRegistry().registerAll(HORNBEAM_TREE);
        event.getRegistry().registerAll(LOCUST_TREE);
        event.getRegistry().registerAll(POPLAR_TREE);
        event.getRegistry().registerAll(RED_ELM_TREE);
        event.getRegistry().registerAll(WALNUT_TREE);
        event.getRegistry().registerAll(WHITE_ELM_TREE);
        event.getRegistry().registerAll(WHITEBEAM_TREE);
    }

    public static final ITreeGenerator GEN_CASSIA_CINNAMON = new TreeGenVariants(true, 6);
    public static final ITreeGenerator GEN_CEYLON_CINNAMON = new TreeGenVariants(true, 6);

    public static final Tree CASSIA_CINNAMON_TREE = new Tree(new ResourceLocation(TerraFirmaCraft.MOD_ID, "cassia_cinnamon"), GEN_CASSIA_CINNAMON, 20, 35, 250, 400, 0.1f, 1, 2, 4, 15, 6, false, null, false, 15, 710f, 1000);
    public static final Tree CEYLON_CINNAMON_TREE = new Tree(new ResourceLocation(TerraFirmaCraft.MOD_ID, "ceylon_cinnamon"), GEN_CEYLON_CINNAMON, 20, 35, 250, 400, 0.1f, 1, 2, 4, 15, 6, false, null, false, 15, 710f, 1000);

    public static final ITreeGenerator GEN_ARROW_BAMBOO = new TreeGenVariants(true, 4);	// Pseudosasa japonica
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

    public static final Tree ARROW_BAMBOO = new Tree(new ResourceLocation(TerraFirmaCraft.MOD_ID, "arrow_bamboo"), GEN_ARROW_BAMBOO, 24, 35, 240, 420, 1, 2, 1, 4, 15, 6, false, null, false, 10, 400f, 800);
    public static final Tree BLACK_BAMBOO = new Tree(new ResourceLocation(TerraFirmaCraft.MOD_ID, "black_bamboo"), GEN_BLACK_BAMBOO, 24, 35, 240, 420, 1, 2, 1, 4, 15, 6, false, null, false, 10, 400f, 800);
    public static final Tree BLUE_BAMBOO = new Tree(new ResourceLocation(TerraFirmaCraft.MOD_ID, "blue_bamboo"), GEN_BLUE_BAMBOO, 24, 35, 240, 420, 1, 2, 1, 4, 15, 6, false, null, false, 10, 400f, 800);
    public static final Tree DRAGON_BAMBOO = new Tree(new ResourceLocation(TerraFirmaCraft.MOD_ID, "dragon_bamboo"), GEN_DRAGON_BAMBOO, 24, 35, 240, 420, 1, 2, 1, 4, 15, 6, false, null, false, 10, 400f, 800);
    public static final Tree GOLDEN_BAMBOO = new Tree(new ResourceLocation(TerraFirmaCraft.MOD_ID, "golden_bamboo"), GEN_GOLDEN_BAMBOO, 24, 35, 240, 420, 1, 2, 1, 4, 15, 6, false, null, false, 10, 400f, 800);
    public static final Tree NARROW_LEAF_BAMBOO = new Tree(new ResourceLocation(TerraFirmaCraft.MOD_ID, "narrow_leaf_bamboo"), GEN_NARROW_LEAF_BAMBOO, 24, 35, 240, 420, 1, 2, 1, 4, 15, 6, false, null, false, 10, 400f, 800);
    public static final Tree RED_BAMBOO = new Tree(new ResourceLocation(TerraFirmaCraft.MOD_ID, "red_bamboo"), GEN_RED_BAMBOO, 24, 35, 240, 420, 1, 2, 1, 4, 15, 6, false, null, false, 10, 400f, 800);
    public static final Tree TEMPLE_BAMBOO = new Tree(new ResourceLocation(TerraFirmaCraft.MOD_ID, "temple_bamboo"), GEN_TEMPLE_BAMBOO, 24, 35, 240, 420, 1, 2, 1, 4, 15, 6, false, null, false, 10, 400f, 800);
    public static final Tree THORNY_BAMBOO = new Tree(new ResourceLocation(TerraFirmaCraft.MOD_ID, "thorny_bamboo"), GEN_THORNY_BAMBOO, 24, 35, 240, 420, 1, 2, 1, 4, 15, 6, false, null, false, 10, 400f, 800);
    public static final Tree TIMBER_BAMBOO = new Tree(new ResourceLocation(TerraFirmaCraft.MOD_ID, "timber_bamboo"), GEN_TIMBER_BAMBOO, 24, 35, 240, 420, 1, 2, 1, 4, 15, 6, false, null, false, 10, 400f, 800);
    public static final Tree TINWA_BAMBOO = new Tree(new ResourceLocation(TerraFirmaCraft.MOD_ID, "tinwa_bamboo"), GEN_TINWA_BAMBOO, 24, 35, 240, 420, 1, 2, 1, 4, 15, 6, false, null, false, 10, 400f, 800);
    public static final Tree WEAVERS_BAMBOO = new Tree(new ResourceLocation(TerraFirmaCraft.MOD_ID, "weavers_bamboo"), GEN_WEAVERS_BAMBOO, 24, 35, 240, 420, 1, 2, 1, 4, 15, 6, false, null, false, 10, 400f, 800);
}
