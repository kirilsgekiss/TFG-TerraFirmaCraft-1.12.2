/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.types;

import net.dries007.tfc.api.registries.TFCRegistryEvent;
import net.dries007.tfc.api.types.Wood;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

@SuppressWarnings("WeakerAccess")
@Mod.EventBusSubscriber(modid = MOD_ID)
public final class DefaultWoods {

    // Default Tree ResourceLocations
    public static final ResourceLocation ACACIA = new ResourceLocation(MOD_ID, "acacia");
    public static final ResourceLocation ASH = new ResourceLocation(MOD_ID, "ash");
    public static final ResourceLocation ASPEN = new ResourceLocation(MOD_ID, "aspen");

    // Birch Trees
    public static final ResourceLocation BIRCH = new ResourceLocation(MOD_ID, "birch");
    public static final ResourceLocation SILVER_BIRCH = new ResourceLocation(MOD_ID, "silver_birch");

    public static final ResourceLocation BLACKWOOD = new ResourceLocation(MOD_ID, "blackwood");
    public static final ResourceLocation CHESTNUT = new ResourceLocation(MOD_ID, "chestnut");
    public static final ResourceLocation DOUGLAS_FIR = new ResourceLocation(MOD_ID, "douglas_fir");
    public static final ResourceLocation HICKORY = new ResourceLocation(MOD_ID, "hickory");
    public static final ResourceLocation MAPLE = new ResourceLocation(MOD_ID, "maple");

    // Oak Trees
    public static final ResourceLocation OAK = new ResourceLocation(MOD_ID, "oak");
    public static final ResourceLocation DARK_OAK = new ResourceLocation(MOD_ID, "dark_oak");

    public static final ResourceLocation PALM = new ResourceLocation(MOD_ID, "palm");
    public static final ResourceLocation PINE = new ResourceLocation(MOD_ID, "pine");
    public static final ResourceLocation ROSEWOOD = new ResourceLocation(MOD_ID, "rosewood");
    public static final ResourceLocation SEQUOIA = new ResourceLocation(MOD_ID, "sequoia");

    // Spruce Trees
    public static final ResourceLocation SPRUCE = new ResourceLocation(MOD_ID, "spruce");
    public static final ResourceLocation JAPANESE_SPRUCE = new ResourceLocation(MOD_ID, "japanese_spruce");
    public static final ResourceLocation NORWAY_SPRUCE = new ResourceLocation(MOD_ID, "norway_spruce");

    public static final ResourceLocation SYCAMORE = new ResourceLocation(MOD_ID, "sycamore");
    public static final ResourceLocation WHITE_CEDAR = new ResourceLocation(MOD_ID, "white_cedar");
    public static final ResourceLocation WHITE_ELM = new ResourceLocation(MOD_ID, "white_elm");
    public static final ResourceLocation WILLOW = new ResourceLocation(MOD_ID, "willow");
    public static final ResourceLocation KAPOK = new ResourceLocation(MOD_ID, "kapok");

    // Hevea Trees
    public static final ResourceLocation HEVEA = new ResourceLocation(MOD_ID, "hevea");
    public static final ResourceLocation RUBBERWOOD = new ResourceLocation(MOD_ID, "rubberwood");

    // Latex Trees
    public static final ResourceLocation SAPODILLA = new ResourceLocation(MOD_ID, "sapodilla");
    public static final ResourceLocation CASTILLA = new ResourceLocation(MOD_ID, "castilla");

    // Manufactured Woods
    public static final ResourceLocation TREATED_WOOD = new ResourceLocation(MOD_ID, "treated_wood");
    public static final ResourceLocation LACQUERED_WOOD = new ResourceLocation(MOD_ID, "lacquered_wood");

    // Misc Wood
    public static final ResourceLocation WOODEN = new ResourceLocation(MOD_ID, "wooden");


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


    // Simple ITreeGenerator instances.

    @SubscribeEvent
    public static void onPreRegisterWood(TFCRegistryEvent.RegisterPreBlock<Wood> event) {
        event.getRegistry().registerAll(

                new Wood.Builder(ACACIA, 650f, 1000).setColor(0x8B3929).build(),
                new Wood.Builder(ASH, 696f, 1250).setColor(0xAE604E).build(),
                new Wood.Builder(ASPEN, 611f, 1000).setColor(0x373727).build(),
                new Wood.Builder(BLACKWOOD, 720f, 1750).setColor(0x1A1A1A).build(),
                new Wood.Builder(CHESTNUT, 651f, 1500).setColor(0x642C1E).setTannin().build(),
                new Wood.Builder(DOUGLAS_FIR, 707f, 1500).setColor(0xD7BC8D).setTannin().build(),
                new Wood.Builder(HICKORY, 762f, 2000).setColor(0x4E3418).setTannin().build(),
                new Wood.Builder(KAPOK, 645f, 1000).setColor(0xAD879F).build(),
                new Wood.Builder(MAPLE, 745f, 2000).setColor(0xC3782F).setTannin().build(),

                // Oak Trees
                new Wood.Builder(OAK, 728f, 2250).setColor(0xC29D62).setTannin().build(),
                new Wood.Builder(DARK_OAK, 728f, 2250).setColor(0x584429).setTannin().build(),

                new Wood.Builder(PALM, 730f, 1250).setColor(0xB56F38).build(),
                new Wood.Builder(PINE, 627f, 1250).setColor(0xD1BD9A).build(),
                new Wood.Builder(ROSEWOOD, 640f, 1500).setColor(0x912222).build(),
                new Wood.Builder(SEQUOIA, 612f, 1750).setColor(0x965B3B).setTannin().build(),

                // Spruce Trees
                new Wood.Builder(SPRUCE, 608f, 1500).setColor(0xBF806F).build(),
                new Wood.Builder(NORWAY_SPRUCE, 608f, 1500).setColor(0xfbe3b2).build(),
                new Wood.Builder(JAPANESE_SPRUCE, 608f, 1500).setColor(0x987c50).build(),
                new Wood.Builder(SYCAMORE, 653f, 1750).setColor(0xDCA448).build(),

                // Birch Trees
                new Wood.Builder(BIRCH, 652f, 1750).setColor(0x897658).setTannin().build(),
                new Wood.Builder(SILVER_BIRCH, 652f, 1750).setColor(0xe7dfaa).setTannin().build(),
                new Wood.Builder(WHITE_CEDAR, 625f, 1500).setColor(0xD4D4D4).build(),
                new Wood.Builder(WHITE_ELM, 625f, 1500).setColor(0xAAB06E).build(),
                new Wood.Builder(WILLOW, 603f, 1000).setColor(0x3A430B).build(),


                // Hevea Trees
                new Wood.Builder(HEVEA, 762f, 2000).setColor(0xCC6F36).build(),
                new Wood.Builder(RUBBERWOOD, 762f, 2000).build(),


                // Latex Trees
                new Wood.Builder(SAPODILLA, 762f, 2000).build(),
                new Wood.Builder(CASTILLA, 762f, 2000).build(),

                // Manufactured Woods
                new Wood.Builder(TREATED_WOOD, 762f, 2000).setColor(0x96502f).build(),
                new Wood.Builder(LACQUERED_WOOD, 762f, 2000).setColor(0x633500).build(),

                // Misc Wood
                new Wood.Builder(WOODEN, 762f, 2000).setColor(0x736049).build(),


                new Wood.Builder(BAOBAB, 478f, 1000).setColor(0xBFA677).build(),
                new Wood.Builder(EUCALYPTUS, 705f, 1000).setColor(0xEB9D6C).build(),
                new Wood.Builder(HAWTHORN, 683f, 1500).setColor(0xCF7744).build(),
                new Wood.Builder(MULBERRY, 705f, 1860).build(),
                new Wood.Builder(MACLURA, 773f, 1930).setColor(0xD69D14).build(),
                new Wood.Builder(MAHOGANY, 773f, 1000).setColor(0x86695F).build(),
                new Wood.Builder(PINK_IVORY, 773f, 1000).setColor(0xF7667F).build(),
                new Wood.Builder(RED_CEDAR, 618f, 1750).setColor(0x9A6C5B).setTannin().build(),
                new Wood.Builder(ROWAN, 645f, 2000).setColor(0xA88F8C).build(),
                new Wood.Builder(SYZYGIUM, 745f, 2000).setColor(0xD6ABAF).setTannin().build(),
                new Wood.Builder(YEW, 813f, 2150).setColor(0xE8A074).build(),
                new Wood.Builder(JACARANDA, 795f, 1250).setColor(0x9B7967).build(),
                new Wood.Builder(JOSHUA_TREE, 696f, 1250).build(),
                new Wood.Builder(JUNIPER, 632f, 1750).setColor(0xA3987F).setTannin().build(),
                new Wood.Builder(IPE, 785f, 1200).setColor(0x452417).build(),
                new Wood.Builder(PINK_CHERRY, 795f, 1250).setColor(0xD99C85).build(),
                new Wood.Builder(WHITE_CHERRY, 795f, 1250).setColor(0xCFAEA1).build(),
                new Wood.Builder(SWEETGUM, 745f, 2000).setColor(0xBE8243).setTannin().build(),
                new Wood.Builder(LARCH, 632f, 1250).setColor(0xAE887C).build(),
                new Wood.Builder(ALDER, 601f, 1000).setColor(0x9E7242).build(),
                new Wood.Builder(BEECH, 703f, 1750).setColor(0xD6A67C).setTannin().build(),
                new Wood.Builder(BLACK_WALNUT, 758f, 1800).setColor(0x604632).build(),
                new Wood.Builder(BUTTERNUT, 758f, 1800).setColor(0xCDA276).build(),
                new Wood.Builder(EUROPEAN_OAK, 728f, 2250).setColor(0xCDAC8B).setTannin().build(),
                new Wood.Builder(GINKGO, 710f, 1000).setColor(0xF3E2AD).build(),
                new Wood.Builder(HAZEL, 683f, 1500).setColor(0xDEB18C).build(),
                new Wood.Builder(HORNBEAM, 728f, 2250).setColor(0xB58C3D).setTannin().build(),
                new Wood.Builder(LOCUST, 653f, 1750).setColor(0xBA895E).build(),
                new Wood.Builder(POPLAR, 609f, 1000).setColor(0xDACFA7).build(),
                new Wood.Builder(RED_ELM, 618f, 1750).setColor(0xDA9E66).setTannin().build(),
                new Wood.Builder(WALNUT, 758f, 1800).setColor(0x281B12).build(),
                new Wood.Builder(WHITE_ELM, 653f, 1750).setTannin().build(),
                new Wood.Builder(WHITEBEAM, 728f, 1750).setColor(0xB7AEA6).setTannin().build(),
                new Wood.Builder(BALD_CYPRESS, 770f, 1300).build(),
                new Wood.Builder(CYPRESS, 783f, 1100).setColor(0xbec193).build(),
                new Wood.Builder(HEMLOCK, 609f, 1000).setColor(0xa9a271).build(),
                new Wood.Builder(NORDMANN_FIR, 628f, 1500).setColor(0x70522e).build(),
                new Wood.Builder(NORWAY_SPRUCE, 628f, 1500).setColor(0xe3c7ad).build(),
                new Wood.Builder(REDWOOD, 618f, 1750).setColor(0x984d2f).setTannin().build(),
                new Wood.Builder(AFRICAN_PADAUK, 745f, 1500).setColor(0x772418).build(),
                new Wood.Builder(ANGELIM, 773f, 1200).setColor(0x976634).build(),
                new Wood.Builder(BOX, 683f, 1500).setColor(0xe6d9b4).build(),
                new Wood.Builder(BRAZILWOOD, 710f, 1000).setColor(0x692647).build(),
                new Wood.Builder(COCOBOLO, 773f, 1000).setColor(0x411c16).build(),
                new Wood.Builder(EBONY, 795f, 1000).setColor(0x1e1b17).build(),
                new Wood.Builder(FEVER, 590f, 1000).setColor(0xe5c890).build(),
                new Wood.Builder(FRUITWOOD, 720f, 1000).setColor(0xb38660).build(),
                new Wood.Builder(GREENHEART, 793f, 1700).setColor(0x47694f).build(),
                new Wood.Builder(HOLLY, 609f, 1000).setColor(0xf0ead7).build(),
                new Wood.Builder(IROKO, 785f, 1200).setColor(0x702800).build(),
                new Wood.Builder(IRONWOOD, 694f, 1170).setColor(0xa08466).build(),
                new Wood.Builder(KAURI, 730f, 1250).setColor(0xaa6c38).build(),
                new Wood.Builder(LIMBA, 710f, 1000).setColor(0xe8bd58).build(),
                new Wood.Builder(LOGWOOD, 695f, 1000).setColor(0x8e3021).build(),
                new Wood.Builder(MAHOE, 783f, 1100).setColor(0x727b7f).build(),
                new Wood.Builder(MANGROVE, 783f, 1100).build(),
                new Wood.Builder(MARBLEWOOD, 837f, 1200).setColor(0xaa6c38).build(),
                new Wood.Builder(MESSMATE, 696f, 1250).setColor(0xb0a487).build(),
                new Wood.Builder(MOUNTAIN_ASH, 696f, 1250).setColor(0xd3aa77).build(),
                new Wood.Builder(PURPLEHEART, 793f, 1700).setColor(0x6a1c42).build(),
                new Wood.Builder(RUBBER_FIG, 785f, 1440).setColor(0xdc7300).build(),
                new Wood.Builder(TEAK, 695f, 1000).setColor(0xb38138).build(),
                new Wood.Builder(WENGE, 773f, 1250).setColor(0x5e5852).build(),
                new Wood.Builder(YELLOW_MERANTI, 837f, 1200).setColor(0xb59470).build(),
                new Wood.Builder(ZEBRAWOOD, 822f, 1570).setColor(0x9c7c51).build()
        );
    }
}
