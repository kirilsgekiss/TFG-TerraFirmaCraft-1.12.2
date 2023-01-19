/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.util.agriculture;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;

import net.dries007.tfc.api.capability.food.FoodData;
import net.dries007.tfc.util.OreDictionaryHelper;

import static net.dries007.tfc.util.agriculture.Food.Category.*;

public enum Food
{
    // BerryBush
    BLACK_BERRY(FRUIT, 4, 0.2f, 5f, 0f, 0f, 0.75f, 0f, 0f, 4.9f),
    BLUE_BERRY(FRUIT, 4, 0.2f, 5f, 0f, 0f, 0.75f, 0f, 0f, 4.9f),
    BUNCH_BERRY(FRUIT, 4, 0.5f, 5f, 0f, 0f, 0.75f, 0f, 0f, 4.9f),
    CLOUD_BERRY(FRUIT, 4, 0.5f, 5f, 0f, 0f, 0.75f, 0f, 0f, 4.9f),
    CRAN_BERRY(FRUIT, 4, 0.2f, 5f, 0f, 0f, 1f, 0f, 0f, 1.8f),
    ELDER_BERRY(FRUIT, 4, 0.2f, 5f, 0f, 0f, 1f, 0f, 0f, 4.9f),
    GOOSE_BERRY(FRUIT, 4, 0.5f, 5f, 0f, 0f, 0.75f, 0f, 0f, 4.9f),
    RASP_BERRY(FRUIT, 4, 0.5f, 5f, 0f, 0f, 0.75f, 0f, 0f, 4.9f),
    SNOW_BERRY(FRUIT, 4, 0.2f, 5f, 0f, 0f, 1f, 0f, 0f, 4.9f),
    STRAW_BERRY(FRUIT, 4, 0.5f, 10f, 0f, 0f, 0.5f, 0f, 0f, 4.9f),
    WINTERGREEN_BERRY(FRUIT, 4, 0.2f, 5f, 0f, 0f, 1f, 0f, 0f, 4.9f),
    ALLSPICE(FRUIT,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.5f),
    CLOVE(FRUIT,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.5f),
    CURRY_LEAF(FRUIT,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.8f),
    STAR_ANISE(FRUIT,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.5f),

    // Crop
    BARLEY(GRAIN, 4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 2f, "barley"),
    MAIZE(GRAIN, 4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 2f, "maize"),
    OAT(GRAIN, 4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 2f, "oat"),
    RICE(GRAIN, 4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 2f, "rice"),
    RYE(GRAIN, 4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 2f, "rye"),
    WHEAT(GRAIN, 4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 2f, "wheat"),
    BEET(VEGETABLE, 4, 2f, 0f, 0f, 1f, 0f, 0f, 0f, 0.7f),
    CABBAGE(VEGETABLE, 4, 0.5f, 0f, 0f, 1f, 0f, 0f, 0f, 1.2f),
    CARROT(VEGETABLE, 4, 2f, 0f, 0f, 1f, 0f, 0f, 0f, 0.7f, "carrot"),
    GARLIC(VEGETABLE, 4, 0.5f, 0f, 0f, 2f, 0f, 0f, 0f, 0.4f),
    GREEN_BEAN(VEGETABLE, 4, 0.5f, 0f, 0f, 1f, 0f, 0f, 0f, 3.5f),
    ONION(VEGETABLE, 4, 0.5f, 0f, 0f, 1f, 0f, 0f, 0f, 0.5f),
    POTATO(VEGETABLE, 4, 2f, 0f, 0f, 1.5f, 0f, 0f, 0f, 0.666f),
    SOYBEAN(VEGETABLE, 4, 2f, 0f, 0f, 0.5f, 0f, 1f, 0f, 2.5f),
    SQUASH(VEGETABLE, 4, 1f, 0f, 0f, 1.5f, 0f, 0f, 0f, 1.67f),
    SUGARCANE(GRAIN, 4, 0f, 0f, 0.5f, 0f, 0f, 0f, 0f, 1.6f),
    TOMATO(VEGETABLE, 4, 0.5f, 5f, 0f, 1.5f, 0f, 0f, 0f, 3.5f),
    RED_BELL_PEPPER(VEGETABLE, 4, 1f, 0f, 0f, 1f, 0f, 0f, 0f, 2.5f),
    YELLOW_BELL_PEPPER(VEGETABLE, 4, 1f, 0f, 0f, 1f, 0f, 0f, 0f, 2.5f),
    GREEN_BELL_PEPPER(VEGETABLE, 4, 0.5f, 0f, 0f, 1f, 0f, 0f, 0f, 2.7f),

    AMARANTH(GRAIN,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 2f),
    BUCKWHEAT(GRAIN,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 2f),
    FONIO(GRAIN,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 2f),
    MILLET(GRAIN,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 2f),
    QUINOA(GRAIN,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 2f),
    SPELT(GRAIN,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 2f),
    BLACK_EYED_PEAS(VEGETABLE,4, 0.4f, 3f, 0f, 0f, 0f, 1f, 0f, 3.5f),
    GREEN_CAYENNE_PEPPER(VEGETABLE,4, 0f, 1f, 0f, 0f, 1f, 0f, 0f, 2.5f),
    RED_CAYENNE_PEPPER(VEGETABLE,4, 0f, 1f, 0f, 0f, 1f, 0f, 0f, 2.5f),
    GINGER(VEGETABLE,4, 0f, 2f, 0f, 0f, 1f, 0f, 0f, 0.7f),
    GINSENG(VEGETABLE,4, 0f, 2f, 0f, 0f, 1f, 0f, 0f, 0.7f),
    RUTABAGA(VEGETABLE,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.5f),
    TURNIP(VEGETABLE,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.5f),
    SUGAR_BEET(VEGETABLE,4, 0f, 2f, 0f, 0f, 1f, 0f, 0f, 0.7f),
    PURPLE_GRAPE(FRUIT,4, 0.5f, 5f, 0f, 0f, 0.75f, 0f, 0f, 2.8f),
    GREEN_GRAPE(FRUIT,4, 0.5f, 5f, 0f, 0f, 0.75f, 0f, 0f, 2.8f),
    LIQUORICE_ROOT(VEGETABLE,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.2f),
    COFFEA_CHERRIES(FRUIT,4, 0.5f, 0.5f, 0f, 1f, 0f, 0f, 0f, 1.9f),
//    AGAVE(OTHER,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.8f),
    COCA_LEAF(OTHER,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.8f),
//    COTTON(OTHER,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 1f),
//    FLAX(OTHER,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 1f),
    CANNABIS_BUD(OTHER,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.8f),
//    HOP(OTHER,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 1f),
//    INDIGO(OTHER,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 1f),
//    MADDER(OTHER,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 1f),
    OPIUM_POPPY_BULB(OTHER,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.5f),
//    RAPE(OTHER,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 1f),
//    WELD(OTHER,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 1f),
//    WOAD(OTHER,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 1f),
    TOBACCO_LEAF(OTHER,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.8f),


    // Normal Tree Fruits
    BANANA(FRUIT, 4, 0.2f, 0f, 0f, 0f, 1f, 0f, 0f, 2f),
    CHERRY(FRUIT, 4, 0.2f, 5f, 0f, 0f, 1f, 0f, 0f, 4f),
    GREEN_APPLE(FRUIT, 4, 0.5f, 0f, 0f, 0f, 1f, 0f, 0f, 2.5f, "apple"),
    LEMON(FRUIT, 4, 0.2f, 5f, 0f, 0f, 0.75f, 0f, 0f, 2f),
    OLIVE(FRUIT, 4, 0.2f, 0f, 0f, 0f, 1f, 0f, 0f, 1.6f),
    ORANGE(FRUIT, 4, 0.5f, 10f, 0f, 0f, 0.5f, 0f, 0f, 2.2f),
    PEACH(FRUIT, 4, 0.5f, 10f, 0f, 0f, 0.5f, 0f, 0f, 2.8f),
    PLUM(FRUIT, 4, 0.5f, 5f, 0f, 0f, 0.75f, 0f, 0f, 2.8f),
    RED_APPLE(FRUIT, 4, 0.5f, 0f, 0f, 0f, 1f, 0f, 0f, 1.7f, "apple"),
    BAOBAB_FRUIT(FRUIT,4, 1f, 0.2f, 0f, 1f, 0f, 0f, 0f, 1.6f),
    BARREL_CACTUS_FRUIT(FRUIT,4, 1f, 0.5f, 0f, 1.2f, 0f, 0f, 0f, 1.7f),
    HAWTHORN(FRUIT,4, 5f, 0.2f, 0f, 1f, 0f, 0f, 0f, 1.8f),
    JUNIPER(FRUIT,4, 5f, 0.2f, 0f, 1f, 0f, 0f, 0f, 1.8f),
    OSAGE_ORANGE(FRUIT,4, 10f, 0.5f, 0f, 0.5f, 0f, 0f, 0f, 2.2f),
    PINK_IVORY_DRUPE(FRUIT,4, 5f, 0.5f, 0f, 0.75f, 0f, 0f, 0f, 2.8f),
    RIBERRY(FRUIT,4, 5f, 0.2f, 0f, 1f, 0f, 0f, 0f, 4.9f),
    ROWAN_BERRY(FRUIT,4, 5f, 0.2f, 0f, 1f, 0f, 0f, 0f, 4.9f),
    SKY_FRUIT(FRUIT,4, 5f, 0.2f, 0f, 0.75f, 0f, 0f, 0f, 2f),
    WILD_CHERRY(FRUIT,4, 5f, 0.2f, 0f, 1f, 0f, 0f, 0f, 4f),
    YEW_BERRY(FRUIT,4, 5f, 0.5f, 0f, 0.75f, 0f, 0f, 0f, 4.9f),
    ROASTED_YEW_BERRY(FRUIT,4, 0f, 2f, 0f, 1.1f, 0f, 0f, 0f, 2.6f),
    MULBERRY(FRUIT,4, 4.5f, 0.5f, 0f, 0.75f, 0f, 0f, 0f, 4.9f),
    GLOWBERRY(FRUIT,2, 7.5f, 0.25f, 0f, 1f, 0f, 0f, 0f, 4.9f),

    // Fruits
    ABIU(FRUIT,4, 0.2f, 0f, 0f, 0f, 1f, 0f, 0f, 2f),
    AMLA(FRUIT,4, 0.2f, 5f, 0f, 0f, 1f, 0f, 0f, 4f),
    APRICOT(FRUIT,4, 0.5f, 5f, 0f, 0f, 0.75f, 0f, 0f, 4.9f),
    AVOCADO(FRUIT,4, 0.2f, 5f, 0f, 0f, 1f, 0f, 0f, 1.8f),
    BAEL(FRUIT,4, 0.2f, 5f, 0f, 0f, 1f, 0f, 0f, 4.9f),
    BAY_LAUREL(FRUIT,4, 0.5f, 5f, 0f, 0f, 0.75f, 0f, 0f, 4.9f),
    BER(FRUIT,4, 0.2f, 5f, 0f, 0f, 0.75f, 0f, 0f, 2f),
    BERGAMOT(FRUIT,4, 0.2f, 5f, 0f, 0f, 0.75f, 0f, 0f, 2f),
    BLACK_CHERRY(FRUIT,4, 0.2f, 0f, 0f, 0f, 1f, 0f, 0f, 1.6f),
    BLACK_PEPPER(FRUIT,4, 0.2f, 0f, 0f, 0f, 1f, 0f, 0f, 1.6f),
    BLACKCURRANT(FRUIT,4, 0.5f, 10f, 0f, 0f, 0.5f, 0f, 0f, 2.8f),
    BLACKTHORN(FRUIT,4, 0.5f, 5f, 0f, 0f, 0.75f, 0f, 0f, 2.8f),
    BUDDHA_HAND(FRUIT,4, 0.2f, 5f, 0f, 0f, 1f, 0f, 0f, 4.9f),
    CACAO(FRUIT,4, 0.2f, 0f, 0f, 0f, 1f, 0f, 0f, 2f),
    CHERRY_PLUM(FRUIT,4, 0.5f, 5f, 0f, 0f, 0.75f, 0f, 0f, 2.8f),
    CITRON(FRUIT,4, 0.2f, 5f, 0f, 0f, 0.75f, 0f, 0f, 2f),
    CRABAPPLE(FRUIT,4, 0.5f, 0f, 0f, 0f, 1f, 0f, 0f, 1.7f),
    DAMSON_PLUM(FRUIT,4, 0.5f, 5f, 0f, 0f, 0.75f, 0f, 0f, 2.8f),
    DATE(FRUIT,4, 0.5f, 5f, 0f, 0f, 0.75f, 0f, 0f, 4.9f),
    ELDER(FRUIT,4, 0.2f, 5f, 0f, 0f, 1f, 0f, 0f, 4.9f),
    FIG(FRUIT,4, 0.5f, 10f, 0f, 0f, 0.5f, 0f, 0f, 2.8f),
    FINGER_LIME(FRUIT,4, 0.2f, 5f, 0f, 0f, 0.75f, 0f, 0f, 2f),
    GRAPEFRUIT(FRUIT,4, 0.4f, 7f, 0f, 0f, 0.6f, 0f, 0f, 2.1f),
    GUAVA(FRUIT,4, 0.5f, 10f, 0f, 0f, 0.5f, 0f, 0f, 2.8f),
    ICE_CREAM_BEAN(FRUIT,4, 2f, 0f, 0f, 0.5f, 0f, 1f, 0f, 2.5f),
    JACKFRUIT(FRUIT,4, 2f, 0f, 0f, 0.5f, 0f, 1f, 0f, 2.5f),
    JUJUBE(FRUIT,4, 0.5f, 0f, 0f, 0f, 1f, 0f, 0f, 1.7f),
    KAKI(FRUIT,4, 0.5f, 10f, 0f, 0f, 0.5f, 0f, 0f, 2.8f),
    KEY_LIME(FRUIT,4, 0.2f, 5f, 0f, 0f, 0.75f, 0f, 0f, 2f),
    KLUWAK(FRUIT,4, 0.5f, 5f, 0f, 0f, 0.75f, 0f, 0f, 4.9f),
    KUMQUAT(FRUIT,4, 0.5f, 10f, 0f, 0f, 0.5f, 0f, 0f, 2.2f),
    PERSIAN_LIME(FRUIT,4, 0.2f, 5f, 0f, 0f, 0.75f, 0f, 0f, 2f),
    LONGAN(FRUIT,4, 0.2f, 5f, 0f, 0f, 1f, 0f, 0f, 4f),
    LOQUAT(FRUIT,4, 0.5f, 10f, 0f, 0f, 0.5f, 0f, 0f, 2.2f),
    LYCHEE(FRUIT,4, 0.2f, 5f, 0f, 0f, 1f, 0f, 0f, 4f),
    MAMEY_SAPOTE(FRUIT,4, 0.5f, 0f, 0f, 0f, 1f, 0f, 0f, 2.5f),
    MANDERIN(FRUIT,4, 0.5f, 10f, 0f, 0f, 0.5f, 0f, 0f, 2.2f),
    MANGO(FRUIT,4, 0.2f, 5f, 0f, 0f, 1f, 0f, 0f, 4.9f),
    MANGOSTEEN(FRUIT,4, 0.2f, 5f, 0f, 0f, 1f, 0f, 0f, 4.9f),
    NECTARINE(FRUIT,4, 0.5f, 10f, 0f, 0f, 0.5f, 0f, 0f, 2.8f),
    OHIA_AI(FRUIT,4, 0.5f, 0f, 0f, 0f, 1f, 0f, 0f, 1.7f),
    PAPAYA(FRUIT,4, 0.2f, 5f, 0f, 0f, 1f, 0f, 0f, 4.9f),
    PASSION_FRUIT(FRUIT,4, 0.5f, 5f, 0f, 0f, 0.75f, 0f, 0f, 4.9f),
    PEAR(FRUIT,4, 0.5f, 5f, 0f, 0f, 0.75f, 0f, 0f, 2.8f),
    PERSIMMON(FRUIT,4, 0.5f, 10f, 0f, 0f, 0.5f, 0f, 0f, 2.8f),
    PERUVIAN_PEPPER(FRUIT,4, 1f, 0f, 0f, 1f, 0f, 0f, 0f, 2.5f),
    PLANTAIN(FRUIT,4, 0.2f, 0f, 0f, 0f, 1f, 0f, 0f, 2f),
    POMEGRANATE(FRUIT,4, 0.2f, 5f, 0f, 0f, 0.75f, 0f, 0f, 4.9f),
    POMELO(FRUIT,4, 0.5f, 10f, 0f, 0f, 0.5f, 0f, 0f, 2.2f),
    QUINCE(FRUIT,4, 0.5f, 0f, 0f, 0f, 1f, 0f, 0f, 1.7f),
    RAINIER_CHERRY(FRUIT,4, 0.2f, 5f, 0f, 0f, 1f, 0f, 0f, 4f),
    RED_BANANA(FRUIT,4, 0.2f, 0f, 0f, 0f, 1f, 0f, 0f, 2f),
    RED_CURRANT(FRUIT,4, 0.5f, 5f, 0f, 0f, 0.75f, 0f, 0f, 4.9f),
    SAND_PEAR(FRUIT,4, 0.5f, 0f, 0f, 0f, 1f, 0f, 0f, 1.7f),
    SAPODILLA(FRUIT,4, 0.5f, 10f, 0f, 0f, 0.5f, 0f, 0f, 2.8f),
    SATSUMA(FRUIT,4, 0.5f, 10f, 0f, 0f, 0.5f, 0f, 0f, 2.2f),
    SOUR_CHERRY(FRUIT,4, 0.2f, 5f, 0f, 0f, 1f, 0f, 0f, 4f),
    SOURSOP(FRUIT,4, 0.2f, 0f, 0f, 0f, 1f, 0f, 0f, 2f),
    STARFRUIT(FRUIT,4, 0.5f, 10f, 0f, 0f, 0.5f, 0f, 0f, 4.9f),
    TAMARILLO(FRUIT,4, 0.5f, 5f, 0f, 1.5f, 0f, 0f, 0f, 3.5f),
    TANGERINE(FRUIT,4, 0.5f, 10f, 0f, 0f, 0.5f, 0f, 0f, 2.2f),
    TROPICAL_APRICOT(FRUIT,4, 0.5f, 5f, 0f, 0f, 0.75f, 0f, 0f, 2.8f),
    VANILLA(FRUIT,4, 0.5f, 5f, 0f, 0f, 0.75f, 0f, 0f, 4.9f),

    // Nuts
    ALMOND(FRUIT,4, 0.5f, 5f, 0f, 0f, 0.75f, 0f, 0f, 4.9f),
    BRAZIL_NUT(FRUIT,4, 0.5f, 5f, 0f, 0f, 0.75f, 0f, 0f, 4.9f),
    BREADNUT(FRUIT,4, 0.5f, 0f, 0f, 0f, 1f, 0f, 0f, 1.7f),
    BUNYA_NUT(FRUIT,4, 0.5f, 10f, 0f, 0f, 0.5f, 0f, 0f, 4.9f),
    CANDLENUT(FRUIT,4, 0.2f, 5f, 0f, 0f, 0.75f, 0f, 0f, 4.9f),
    CASHEW(FRUIT,4, 0.2f, 5f, 0f, 0f, 0.75f, 0f, 0f, 4.9f),
    HEARTNUT(FRUIT,4, 0.2f, 5f, 0f, 0f, 0.75f, 0f, 0f, 4.9f),
    KOLA_NUT(FRUIT,4, 0.2f, 0f, 0f, 0f, 1f, 0f, 0f, 2f),
    KUKUI_NUT(FRUIT,4, 0.2f, 5f, 0f, 0f, 0.75f, 0f, 0f, 4.9f),
    MACADAMIA(FRUIT,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 2f),
    MONGONGO(FRUIT,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 2f),
    MONKEY_PUZZLE_NUT(FRUIT,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 2f),
    NUTMEG(FRUIT,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 2f),
    PARADISE_NUT(FRUIT,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 2f),
    PISTACHIO(FRUIT,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 2f),

    // Normal Trees Nuts
    ACORN(FRUIT,4, 0f, 0.5f, 0f, 0.75f, 0f, 0f, 0f, 0.25f),
    BEECHNUT(FRUIT,4, 0f, 0.5f, 0f, 0.75f, 0f, 0f, 0f, 0.25f),
    BLACK_WALNUT(FRUIT,4, 0f, 0.5f, 0f, 0.75f, 0f, 0f, 0f, 0.25f),
    BUTTERNUT(FRUIT,4, 0f, 0.5f, 0f, 0.75f, 0f, 0f, 0f, 0.25f),
    CHESTNUT(FRUIT,4, 0f, 0.5f, 0f, 0.75f, 0f, 0f, 0f, 0.25f),
    GINKGO_NUT(FRUIT,4, 0f, 0.5f, 0f, 0.75f, 0f, 0f, 0f, 0.25f),
    HAZELNUT(FRUIT,4, 0f, 0.5f, 0f, 0.75f, 0f, 0f, 0f, 0.25f),
    HICKORY_NUT(FRUIT,4, 0f, 0.5f, 0f, 0.75f, 0f, 0f, 0f, 0.25f),
    PECAN(FRUIT,4, 0f, 0.5f, 0f, 0.75f, 0f, 0f, 0f, 0.25f),
    PINE_NUT(FRUIT,4, 0f, 0.5f, 0f, 0.75f, 0f, 0f, 0f, 0.25f),
    WALNUT(FRUIT,4, 0f, 0.5f, 0f, 0.75f, 0f, 0f, 0f, 0.25f),

    // Dried Foods
    DRIED_FRUIT_SATURATION(FRUIT,4, 0f, 1f, 0f, 1f, 0f, 0f, 0f, 0.8f),
    DRIED_FRUIT_DECAY(FRUIT,4, 0f, 1f, 0f, 1f, 0f, 0f, 0f, 0.8f),
    DRIED_FRUIT_CATEGORY(FRUIT,4, 0f, 1f, 0f, 1f, 0f, 0f, 0f, 0.8f),

    // Cracked & Roasted Nuts
    UNCRACKED_NUT(FRUIT,4, 0f, 0.5f, 0f, 0.75f, 0f, 0f, 0f, 0.25f),
    NUT(FRUIT,4, 0f, 0.5f, 0f, 0.75f, 0f, 0f, 0f, 0.25f),
    ROASTED_NUT(FRUIT,4, 0f, 0.5f, 0f, 0.75f, 0f, 0f, 0f, 0.25f),

    // Raw Epiphytes
    RAW_ARTISTS_CONK(VEGETABLE,4, 0f, 0.5f, 0f, 0.1f, 0.1f, 0.5f, 0f, 2f),
    RAW_SULPHUR_SHELF(VEGETABLE,4, 0f, 0.5f, 0f, 0.1f, 0.1f, 0.5f, 0f, 2f),
    RAW_TURKEY_TAIL(VEGETABLE,4, 0f, 0.5f, 0f, 0.1f, 0.1f, 0.5f, 0f, 2f),

    // Raw Mushrooms
    RAW_PORCINI(VEGETABLE,4, 0f, 0.5f, 0f, 0.1f, 0.1f, 0.5f, 0f, 2f),
    RAW_AMANITA(VEGETABLE,4, 0f, 0.5f, 0f, 0.1f, 0.1f, 0.5f, 0f, 2f),
    RAW_BLACK_POWDERPUFF(VEGETABLE,4, 0f, 0.5f, 0f, 0.1f, 0.1f, 0.5f, 0f, 2f),
    RAW_CHANTERELLE(VEGETABLE,4, 0f, 0.5f, 0f, 0.1f, 0.1f, 0.5f, 0f, 2f),
    RAW_DEATH_CAP(VEGETABLE,4, 0f, 0.5f, 0f, 0.1f, 0.1f, 0.5f, 0f, 2f),
    RAW_GIANT_CLUB(VEGETABLE,4, 0f, 0.5f, 0f, 0.1f, 0.1f, 0.5f, 0f, 2f),
    RAW_PARASOL_MUSHROOM(VEGETABLE,4, 0f, 0.5f, 0f, 0.1f, 0.1f, 0.5f, 0f, 2f),
    RAW_STINKHORN(VEGETABLE,4, 0f, 0.5f, 0f, 0.1f, 0.1f, 0.5f, 0f, 2f),
    RAW_WEEPING_MILK_CAP(VEGETABLE,4, 0f, 0.5f, 0f, 0.1f, 0.1f, 0.5f, 0f, 2f),
    RAW_WOOD_BLEWIT(VEGETABLE,4, 0f, 0.5f, 0f, 0.1f, 0.1f, 0.5f, 0f, 2f),
    RAW_WOOLLY_GOMPHUS(VEGETABLE,4, 0f, 0.5f, 0f, 0.1f, 0.1f, 0.5f, 0f, 2f),
    RAW_BLUESHROOM(VEGETABLE,4, 0f, 0.5f, 0f, 0.1f, 0.1f, 0.5f, 0f, 2f),
    RAW_GLOWSHROOM(VEGETABLE,4, 0f, 0.5f, 0f, 0.1f, 0.1f, 0.5f, 0f, 2f),
    RAW_MAGMA_SHROOM(VEGETABLE,4, 0f, 0.5f, 0f, 0.1f, 0.1f, 0.5f, 0f, 2f),
    RAW_POISON_SHROOM(VEGETABLE,4, 0f, 0.5f, 0f, 0.1f, 0.1f, 0.5f, 0f, 2f),
    RAW_SULPHUR_SHROOM(VEGETABLE,4, 0f, 0.5f, 0f, 0.1f, 0.1f, 0.5f, 0f, 2f),

    // Roasted Epiphytes
    ROASTED_ARTISTS_CONK(VEGETABLE,4, 0f, 2f, 0f, 0.3f, 0.3f, 0.7f, 0f, 3.5f),
    ROASTED_SULPHUR_SHELF(VEGETABLE,4, 0f, 2f, 0f, 0.3f, 0.3f, 0.7f, 0f, 3.5f),
    ROASTED_TURKEY_TAIL(VEGETABLE,4, 0f, 2f, 0f, 0.3f, 0.3f, 0.7f, 0f, 3.5f),

    // Roasted Mushrooms
    ROASTED_PORCINI(VEGETABLE,4, 0f, 2f, 0f, 0.3f, 0.3f, 0.7f, 0f, 3.5f),
    ROASTED_AMANITA(VEGETABLE,4, 0f, 2f, 0f, 0.3f, 0.3f, 0.7f, 0f, 3.5f),
    ROASTED_BLACK_POWDERPUFF(VEGETABLE,4, 0f, 2f, 0f, 0.3f, 0.3f, 0.7f, 0f, 3.5f),
    ROASTED_CHANTERELLE(VEGETABLE,4, 0f, 2f, 0f, 0.3f, 0.3f, 0.7f, 0f, 3.5f),
    ROASTED_DEATH_CAP(VEGETABLE,4, 0f, 2f, 0f, 0.3f, 0.3f, 0.7f, 0f, 3.5f),
    ROASTED_GIANT_CLUB(VEGETABLE,4, 0f, 2f, 0f, 0.3f, 0.3f, 0.7f, 0f, 3.5f),
    ROASTED_PARASOL_MUSHROOM(VEGETABLE,4, 0f, 2f, 0f, 0.3f, 0.3f, 0.7f, 0f, 3.5f),
    ROASTED_STINKHORN(VEGETABLE,4, 0f, 2f, 0f, 0.3f, 0.3f, 0.7f, 0f, 3.5f),
    ROASTED_WEEPING_MILK_CAP(VEGETABLE,4, 0f, 2f, 0f, 0.3f, 0.3f, 0.7f, 0f, 3.5f),
    ROASTED_WOOD_BLEWIT(VEGETABLE,4, 0f, 2f, 0f, 0.3f, 0.3f, 0.7f, 0f, 3.5f),
    ROASTED_WOOLLY_GOMPHUS(VEGETABLE,4, 0f, 2f, 0f, 0.3f, 0.3f, 0.7f, 0f, 3.5f),

    CASSIA_CINNAMON_BARK(OTHER,4, 0f, 0.5f, 0f, 1f, 0f, 0f, 0f, 0.3f),
    GROUND_CASSIA_CINNAMON(OTHER,4, 0f, 0.5f, 0f, 1f, 0f, 0f, 0f, 0.2f),
    CEYLON_CINNAMON_BARK(OTHER,4, 0f, 0.5f, 0f, 1f, 0f, 0f, 0f, 0.3f),
    GROUND_CEYLON_CINNAMON(OTHER,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.2f),
    GROUND_BLACK_PEPPER(OTHER,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.2f),

    // Tea
    BLACK_TEA(OTHER,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.5f),
    DRIED_BLACK_TEA(OTHER,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.5f),
    GREEN_TEA(OTHER,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.5f),
    DRIED_GREEN_TEA(OTHER,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.5f),
    WHITE_TEA(OTHER,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.5f),
    DRIED_WHITE_TEA(OTHER,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.5f),

    DRIED_CANNABIS_BUD(OTHER,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.5f),
    CANNABIS_LEAF(OTHER,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.8f),
    DRIED_CANNABIS_LEAF(OTHER,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.5f),

    DRIED_COCA_LEAF(OTHER,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.5f),

    DRIED_OPIUM_POPPY_BULB(OTHER,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.5f),
    PEYOTE(FRUIT,4, 1f, 3f, 0f, 0f, 1f, 0f, 0f, 1f),
    DRIED_PEYOTE(FRUIT,4, 0f, 3f, 0f, 0f, 2f, 0f, 0f, 0.5f),
    DRIED_TOBACCO_LEAF(OTHER,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.5f),

    RAW_EEL(MEAT,4, 0f, 0f, 0f, 0f, 0f, 0.5f, 0f, 3f),
    COOKED_EEL(MEAT,4, 0f, 1f, 0f, 0f, 0f, 1.5f, 0f, 2.25f),
    RAW_CRAB(MEAT,4, 0f, 0f, 0f, 0f, 0f, 0.5f, 0f, 3f),
    COOKED_CRAB(MEAT,4, 0f, 1f, 0f, 0f, 0f, 1.5f, 0f, 2.25f),
    RAW_CLAM(MEAT,4, 0f, 0f, 0f, 0f, 0f, 0.5f, 0f, 3f),
    COOKED_CLAM(MEAT,4, 0f, 1f, 0f, 0f, 0f, 1.5f, 0f, 2.25f),
    RAW_SCALLOP(MEAT,4, 0f, 0f, 0f, 0f, 0f, 0.5f, 0f, 3f),
    COOKED_SCALLOP(MEAT,4, 0f, 1f, 0f, 0f, 0f, 1.5f, 0f, 2.25f),
    RAW_STARFISH(MEAT,4, 0f, 0f, 0f, 0f, 0f, 0.5f, 0f, 3f),
    COOKED_STARFISH(MEAT,4, 0f, 1f, 0f, 0f, 0f, 1.5f, 0f, 2.25f),
    RAW_SNAIL(MEAT,4, 0f, 0f, 0f, 0f, 0f, 0.5f, 0f, 3f),
    COOKED_SNAIL(MEAT,4, 0f, 1f, 0f, 0f, 0f, 1.5f, 0f, 2.25f),
    RAW_WORM(MEAT,4, 0f, 0f, 0f, 0f, 0f, 0.5f, 0f, 3f),
    COOKED_WORM(MEAT,4, 0f, 1f, 0f, 0f, 0f, 1.5f, 0f, 2.25f),

    DRIED_COFFEA_CHERRIES(FRUIT,4, 0f, 0.5f, 0f, 1f, 0f, 0f, 0f, 0.5f),
    ROASTED_COFFEE_BEANS(FRUIT,4, 0f, 0.5f, 0f, 1f, 0f, 0f, 0f, 0.5f),
    COFFEE_POWDER(FRUIT,4, 0f, 0.5f, 0f, 1f, 0f, 0f, 0f, 0.5f),

    AMARANTH_GRAIN(GRAIN,4, 0.5f, 0f, 0f, 0f, 0f, 0f, 0f, 0.25f),
    AMARANTH_FLOUR(GRAIN,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.5f),
    AMARANTH_DOUGH(GRAIN,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 3f),
    AMARANTH_BREAD(GRAIN,4, 0f, 1f, 1.5f, 0f, 0f, 0f, 0f, 1f),
    BUCKWHEAT_GRAIN(GRAIN,4, 0.5f, 0f, 0f, 0f, 0f, 0f, 0f, 0.4f),
    BUCKWHEAT_FLOUR(GRAIN,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.5f),
    BUCKWHEAT_DOUGH(GRAIN,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 3f),
    BUCKWHEAT_BREAD(GRAIN,4, 0f, 1f, 1.5f, 0f, 0f, 0f, 0f, 1f),
    FONIO_GRAIN(GRAIN,4, 0.5f, 0f, 0f, 0f, 0f, 0f, 0f, 0.4f),
    FONIO_FLOUR(GRAIN,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f,0.5f),
    FONIO_DOUGH(GRAIN,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 3f),
    FONIO_BREAD(GRAIN,4, 0f, 1f, 1.5f, 0f, 0f, 0f, 0f, 1f),
    MILLET_GRAIN(GRAIN,4, 0.5f, 0f, 0f, 0f, 0f, 0f, 0f, 0.4f),
    MILLET_FLOUR(GRAIN,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.5f),
    MILLET_DOUGH(GRAIN,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 3f),
    MILLET_BREAD(GRAIN,4, 0f, 1f, 1.5f, 0f, 0f, 0f, 0f, 1f),
    QUINOA_GRAIN(GRAIN,4, 0.5f, 0f, 0f, 0f, 0f, 0f, 0f, 0.4f),
    QUINOA_FLOUR(GRAIN,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.5f),
    QUINOA_DOUGH(GRAIN,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 3f),
    QUINOA_BREAD(GRAIN,4, 0f, 1f, 1.5f, 0f, 0f, 0f, 0f, 1f),
    SPELT_GRAIN(GRAIN,4, 0.5f, 0f, 0f, 0f, 0f, 0f, 0f, 0.4f),
    SPELT_FLOUR(GRAIN,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.5f),
    SPELT_DOUGH(GRAIN,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 3f),
    SPELT_BREAD(GRAIN,4, 0f, 1f, 1.5f, 0f, 0f, 0f, 0f, 1f),
    WILD_BARLEY(GRAIN,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 2f),
    WILD_RICE(GRAIN,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 2f),
    WILD_WHEAT(GRAIN,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 2f),


    // Sandwiches
    AMARANTH_BREAD_SANDWICH(MEAL,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 4.5f),
    BUCKWHEAT_BREAD_SANDWICH(MEAL,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 4.5f),
    FONIO_BREAD_SANDWICH(MEAL,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 4.5f),
    MILLET_BREAD_SANDWICH(MEAL,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 4.5f),
    QUINOA_BREAD_SANDWICH(MEAL,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 4.5f),
    SPELT_BREAD_SANDWICH(MEAL,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 4.5f),

    DOUGH(GRAIN,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 3f),
    FLATBREAD(GRAIN,4, 1.0f, 0f, 1.0f, 0f, 0f, 0f, 0f, 1.0f),
    SLICE(GRAIN,4, 0f, 0.5f, 0.5f, 0f, 0f, 0f, 0f, 1.5f),
    SANDWICH(GRAIN,4, 0f, 3.0f, 0f, 0f, 0f, 0f, 0f, 5f),
    HASH_MUFFIN_DOUGH(GRAIN,4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 3f),
    HASH_MUFFIN(MEAL,4, 0f, 1f, 1.5f, 0f, 0f, 0f, 0f, 1f),
    LINSEED(VEGETABLE,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.8f),
    RAPE_SEED(VEGETABLE,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.8f),
    SUNFLOWER_SEED(VEGETABLE,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.8f),
    OPIUM_POPPY_SEED(VEGETABLE,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.8f),


    LINSEED_PASTE(VEGETABLE,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.8f),
    RAPE_SEED_PASTE(VEGETABLE,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.8f),
    SUNFLOWER_SEED_PASTE(VEGETABLE,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.8f),
    OPIUM_POPPY_SEED_PASTE(VEGETABLE,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 0.8f),
    MASHED_SUGAR_BEET(VEGETABLE,4, 0f, 2f, 0f, 0f, 1f, 0f, 0f, 1.5f),
    MASHED_SUGAR_CANE(VEGETABLE,4, 0f, 0.5f, 0f, 0f, 1f, 0f, 0f, 2f),
    SOYBEAN_PASTE(VEGETABLE,4, 0f, 1f, 0f, 0f, 0.5f, 1f, 0f, 0.8f),



    BARLEY_GRAIN(GRAIN, 4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.25f, "grain_barley", "grain"),
    BARLEY_FLOUR(GRAIN, 4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.5f, "flour_barley", "flour"),
    BLACKBERRY(FRUIT, 4, 0.2f, 5f, 0f, 0f, 0.75f, 0f, 0f, 4.9f),
    BLUEBERRY(FRUIT, 4, 0.2f, 5f, 0f, 0f, 0.75f, 0f, 0f, 4.9f),
    CRANBERRY(FRUIT, 4, 0.2f, 5f, 0f, 0f, 1f, 0f, 0f, 1.8f),
    ELDERBERRY(FRUIT, 4, 0.2f, 5f, 0f, 0f, 1f, 0f, 0f, 4.9f),
    GOOSEBERRY(FRUIT, 4, 0.5f, 5f, 0f, 0f, 0.75f, 0f, 0f, 4.9f),
    RASPBERRY(FRUIT, 4, 0.5f, 5f, 0f, 0f, 0.75f, 0f, 0f, 4.9f),
    STRAWBERRY(FRUIT, 4, 0.5f, 10f, 0f, 0f, 0.5f, 0f, 0f, 4.9f),
    BARLEY_DOUGH(GRAIN, 4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 3f, 1f, 200f),
    BARLEY_BREAD(BREAD, 4, 1f, 0f, 1.5f, 0f, 0f, 0f, 0f, 1f, 1f, 480f),
    MAIZE_GRAIN(GRAIN, 4, 0.5f, 0f, 0f, 0f, 0f, 0f, 0f, 0.25f, "grain_maize", "grain"),
    CORNBREAD(BREAD, 4, 1f, 0f, 1f, 0f, 0f, 0f, 0f, 1f, 1f, 480f),
    CORNMEAL_FLOUR(GRAIN, 4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.5f, "flour_cornmeal", "flour"),
    CORNMEAL_DOUGH(GRAIN, 4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 3f, 1f, 200f),
    OAT_GRAIN(GRAIN, 4, 0.5f, 0f, 0f, 0f, 0f, 0f, 0f, 0.25f, "grain_oat", "grain"),
    OAT_FLOUR(GRAIN, 4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.5f, "flour_oat", "flour"),
    OAT_DOUGH(GRAIN, 4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 3f, 1f, 200f),
    OAT_BREAD(BREAD, 4, 1f, 0f, 1f, 0f, 0f, 0f, 0f, 1f, 1f, 480f),
    RICE_GRAIN(GRAIN, 4, 0.5f, 0f, 0f, 0f, 0f, 0f, 0f, 0.25f, "grain_rice", "grain"),
    RICE_FLOUR(GRAIN, 4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.5f, "flour_rice", "flour"),
    RICE_DOUGH(GRAIN, 4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 3f, 1f, 200f),
    RICE_BREAD(BREAD, 4, 1f, 0f, 1.5f, 0f, 0f, 0f, 0f, 1f, 1f, 480f),
    RYE_GRAIN(GRAIN, 4, 0.5f, 0f, 0f, 0f, 0f, 0f, 0f, 0.25f, "grain_rye", "grain"),
    RYE_FLOUR(GRAIN, 4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.5f, "flour_rye", "flour"),
    RYE_DOUGH(GRAIN, 4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 3f, 1f, 200f),
    RYE_BREAD(BREAD, 4, 1f, 0f, 1.5f, 0f, 0f, 0f, 0f, 1f, 1f, 480f),
    WHEAT_GRAIN(GRAIN, 4, 0.5f, 0f, 0f, 0f, 0f, 0f, 0f, 0.25f, "grain_wheat", "grain"),
    WHEAT_FLOUR(GRAIN, 4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.5f, "flour_wheat", "flour"),
    WHEAT_DOUGH(GRAIN, 4, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 3f, 1f, 200f),
    WHEAT_BREAD(BREAD, 4, 1f, 0f, 1f, 0f, 0f, 0f, 0f, 1f, 1f, 480f),
    SEAWEED(VEGETABLE, 4, 1f, 0f, 0f, 1f, 0f, 0f, 0f, 2.5f),
    CHEESE(DAIRY, 4, 2f, 0f, 0f, 0f, 0f, 0f, 3f, 0.3f),
    COOKED_EGG(OTHER, 4, 0.5f, 0f, 0f, 0f, 0f, 0.75f, 0.25f, 4f),
    BEEF(MEAT, 4, 0f, 0f, 0f, 0f, 0f, 2f, 0f, 2f, 1f, 200f),
    PORK(MEAT, 4, 0f, 0f, 0f, 0f, 0f, 1.5f, 0f, 2f, 1f, 200f),
    CHICKEN(MEAT, 4, 0f, 0f, 0f, 0f, 0f, 1.5f, 0f, 3f, 1f, 200f),
    MUTTON(MEAT, 4, 0f, 0f, 0f, 0f, 0f, 1.5f, 0f, 3f, 1f, 200f),
    FISH(MEAT, 4, 0f, 0f, 0f, 0f, 0f, 1f, 0f, 3f, 1f, 200f),
    BEAR(MEAT, 4, 0f, 0f, 0f, 0f, 0f, 1.5f, 0f, 2f, 1f, 200f),
    CALAMARI(MEAT, 4, 0f, 0f, 0f, 0f, 0f, 0.5f, 0f, 3f, 1f, 200f),
    HORSE_MEAT(MEAT, 4, 0f, 0f, 0f, 0f, 0f, 1.5f, 0f, 2f, 1f, 200f),
    PHEASANT(MEAT, 4, 0f, 0f, 0f, 0f, 0f, 1.5f, 0f, 3f, 1f, 200f),
    VENISON(MEAT, 4, 0f, 0f, 0f, 0f, 0f, 1f, 0f, 2f, 1f, 200f),
    WOLF(MEAT, 4, 0f, 0f, 0f, 0f, 0f, 0.5f, 0f, 3f, 1f, 200f),
    RABBIT(MEAT, 4, 0f, 0f, 0f, 0f, 0f, 0.5f, 0f, 3f, 1f, 200f),
    MONGOOSE(MEAT, 4, 0f, 0f, 0f, 0f, 0f, 0.5f, 0f, 3f, 1f, 200f),
    GRAN_FELINE(MEAT, 4, 0f, 0f, 0f, 0f, 0f, 0.5f, 0f, 3f, 1f, 200f),
    CAMELIDAE(MEAT, 4, 0f, 0f, 0f, 0f, 0f, 0.5f, 0f, 3f, 1f, 200f),
    COOKED_BEEF(COOKED_MEAT, 4, 2f, 0f, 0f, 0f, 0f, 2.5f, 0f, 1.5f),
    COOKED_PORK(COOKED_MEAT, 4, 2f, 0f, 0f, 0f, 0f, 2.5f, 0f, 1.5f),
    COOKED_CHICKEN(COOKED_MEAT, 4, 2f, 0f, 0f, 0f, 0f, 2.5f, 0f, 2.25f),
    COOKED_MUTTON(COOKED_MEAT, 4, 2f, 0f, 0f, 0f, 0f, 2.5f, 0f, 2.25f),
    COOKED_FISH(COOKED_MEAT, 4, 1f, 0f, 0f, 0f, 0f, 2f, 0f, 2.25f),
    COOKED_BEAR(COOKED_MEAT, 4, 1f, 0f, 0f, 0f, 0f, 2.5f, 0f, 1.5f),
    COOKED_CALAMARI(COOKED_MEAT, 4, 1f, 0f, 0f, 0f, 0f, 1.5f, 0f, 2.25f),
    COOKED_HORSE_MEAT(COOKED_MEAT, 4, 2f, 0f, 0f, 0f, 0f, 2.5f, 0f, 1.5f),
    COOKED_PHEASANT(COOKED_MEAT, 4, 1f, 0f, 0f, 0f, 0f, 2.5f, 0f, 2.25f),
    COOKED_VENISON(COOKED_MEAT, 4, 1f, 0f, 0f, 0f, 0f, 2f, 0f, 1.5f),
    COOKED_WOLF(COOKED_MEAT, 4, 1f, 0f, 0f, 0f, 0f, 1.5f, 0f, 2.25f),
    COOKED_RABBIT(COOKED_MEAT, 4, 1f, 0f, 0f, 0f, 0f, 1.5f, 0f, 2.25f),
    COOKED_MONGOOSE(COOKED_MEAT, 4, 1f, 0f, 0f, 0f, 0f, 1.5f, 0f, 2.25f),
    COOKED_GRAN_FELINE(COOKED_MEAT, 4, 2f, 0f, 0f, 0f, 0f, 2.5f, 0f, 2.25f),
    COOKED_CAMELIDAE(COOKED_MEAT, 4, 2f, 0f, 0f, 0f, 0f, 2.5f, 0f, 2.25f),
    BARLEY_BREAD_SANDWICH(MEAL, 4, 3f, 0f, 0f, 0f, 0f, 0f, 0f, 4.5f, "sandwich"),
    CORNBREAD_SANDWICH(MEAL, 4, 3f, 0f, 0f, 0f, 0f, 0f, 0f, 4.5f, "sandwich"),
    OAT_BREAD_SANDWICH(MEAL, 4, 3f, 0f, 0f, 0f, 0f, 0f, 0f, 4.5f, "sandwich"),
    RICE_BREAD_SANDWICH(MEAL, 4, 3f, 0f, 0f, 0f, 0f, 0f, 0f, 4.5f, "sandwich"),
    RYE_BREAD_SANDWICH(MEAL, 4, 3f, 0f, 0f, 0f, 0f, 0f, 0f, 4.5f, "sandwich"),
    WHEAT_BREAD_SANDWICH(MEAL, 4, 3f, 0f, 0f, 0f, 0f, 0f, 0f, 4.5f, "sandwich"),
    SOUP_GRAIN(MEAL, 4, 3f, 0f, 0f, 0f, 0f, 0f, 0f, 3.5f, "soup"),
    SOUP_FRUIT(MEAL, 4, 3f, 0f, 0f, 0f, 0f, 0f, 0f, 3.5f, "soup"),
    SOUP_VEGETABLE(MEAL, 4, 3f, 0f, 0f, 0f, 0f, 0f, 0f, 3.5f, "soup"),
    SOUP_MEAT(MEAL, 4, 3f, 0f, 0f, 0f, 0f, 0f, 0f, 3.5f, "soup"),
    SOUP_DAIRY(MEAL, 4, 3f, 0f, 0f, 0f, 0f, 0f, 0f, 3.5f, "soup"),
    SALAD_GRAIN(MEAL, 4, 3f, 0f, 0f, 0f, 0f, 0f, 0f, 5f, "salad"),
    SALAD_FRUIT(MEAL, 4, 3f, 0f, 0f, 0f, 0f, 0f, 0f, 5f, "salad"),
    SALAD_VEGETABLE(MEAL, 4, 3f, 0f, 0f, 0f, 0f, 0f, 0f, 5f, "salad"),
    SALAD_MEAT(MEAL, 4, 3f, 0f, 0f, 0f, 0f, 0f, 0f, 5f, "salad"),
    SALAD_DAIRY(MEAL, 4, 3f, 0f, 0f, 0f, 0f, 0f, 0f, 5f, "salad");

    private final Category category;
    private final FoodData foodData;

    private final boolean heatable;
    private final float heatCapacity;
    private final float cookingTemp;

    private final String[] oreDictNames;

    Food(@Nonnull Category category, int hunger, float saturation, float water, float grain, float veg, float fruit, float meat, float dairy, float decayModifier, String... oreNames)
    {
        this(category, hunger, saturation, water, grain, veg, fruit, meat, dairy, decayModifier, 0, -1, oreNames);
    }

    Food(@Nonnull Category category, int hunger, float saturation, float water, float grain, float veg, float fruit, float meat, float dairy, float decayModifier, float heatCapacity, float cookingTemp, String... oreNames)
    {
        this.category = category;
        this.foodData = new FoodData(hunger, water, saturation, grain, fruit, veg, meat, dairy, decayModifier);

        this.heatable = cookingTemp >= 0;
        this.heatCapacity = heatCapacity;
        this.cookingTemp = cookingTemp;

        this.oreDictNames = oreNames == null || oreNames.length == 0 ? null : oreNames;
    }

    @Nonnull
    public Category getCategory()
    {
        return category;
    }

    @Nonnull
    public FoodData getData()
    {
        return foodData;
    }

    public boolean isHeatable()
    {
        return heatable;
    }

    public float getHeatCapacity()
    {
        return heatCapacity;
    }

    public float getCookingTemp()
    {
        return cookingTemp;
    }

    @Nullable
    public String[] getOreDictNames()
    {
        return oreDictNames;
    }

    public enum Category
    {
        FRUIT,
        GRAIN,
        BREAD,
        VEGETABLE,
        MEAT,
        COOKED_MEAT,
        DAIRY,
        MEAL,
        OTHER; // Provided for addons / other mods

        public static boolean doesStackMatchCategories(ItemStack stack, Category... categories)
        {
            for (Category cat : categories)
            {
                if (OreDictionaryHelper.doesStackMatchOre(stack, OreDictionaryHelper.toString("category_" + cat.name())))
                {
                    return true;
                }
            }
            return false;
        }
    }
}
