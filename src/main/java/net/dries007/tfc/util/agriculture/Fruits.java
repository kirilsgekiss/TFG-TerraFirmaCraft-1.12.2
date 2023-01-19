package net.dries007.tfc.util.agriculture;

import net.dries007.tfc.objects.items.food.ItemFoodTFC;
import net.minecraft.item.Item;

import net.dries007.tfc.api.capability.food.FoodData;

import static net.dries007.tfc.api.capability.food.FoodData.*;
import static net.dries007.tfc.api.capability.food.FoodData.*;
import static net.dries007.tfc.api.capability.food.FoodData.DRIED_FRUIT_DECAY;
import static net.dries007.tfc.util.agriculture.Food.*;

/**
 * This is an easy way to wrap all the TFC fruits with our data
 */
public enum Fruits
{
    // TFC Foods
    BANANA(ItemFoodTFC.get(Food.BANANA), true, DRIED_FRUIT_DECAY, true),
    BLACKBERRY(ItemFoodTFC.get(Food.BLACKBERRY), true, DRIED_FRUIT_CATEGORY, true),
    BLUEBERRY(ItemFoodTFC.get(Food.BLUEBERRY), true, DRIED_FRUIT_CATEGORY, true),
    BUNCH_BERRY(ItemFoodTFC.get(Food.BUNCH_BERRY), true, DRIED_FRUIT_SATURATION, true),
    CHERRY(ItemFoodTFC.get(Food.CHERRY), true, DRIED_FRUIT_DECAY, true),
    CLOUD_BERRY(ItemFoodTFC.get(Food.CLOUD_BERRY), true, DRIED_FRUIT_DECAY, true),
    CRANBERRY(ItemFoodTFC.get(Food.CRANBERRY), true, DRIED_FRUIT_SATURATION, true),
    ELDERBERRY(ItemFoodTFC.get(Food.ELDERBERRY), true, DRIED_FRUIT_CATEGORY, true),
    GOOSEBERRY(ItemFoodTFC.get(Food.GOOSEBERRY), true, DRIED_FRUIT_SATURATION, true),
    GREEN_APPLE(ItemFoodTFC.get(Food.GREEN_APPLE), true, DRIED_FRUIT_DECAY, true),
    LEMON(ItemFoodTFC.get(Food.LEMON), true, DRIED_FRUIT_DECAY, true),
    OLIVE(ItemFoodTFC.get(Food.OLIVE), true, DRIED_FRUIT_DECAY, true),
    ORANGE(ItemFoodTFC.get(Food.ORANGE), true, DRIED_FRUIT_DECAY, true),
    PEACH(ItemFoodTFC.get(Food.PEACH), true, DRIED_FRUIT_SATURATION, true),
    PLUM(ItemFoodTFC.get(Food.PLUM), true, DRIED_FRUIT_SATURATION, true),
    RASPBERRY(ItemFoodTFC.get(Food.RASPBERRY), true, DRIED_FRUIT_SATURATION, true),
    RED_APPLE(ItemFoodTFC.get(Food.RED_APPLE), true, DRIED_FRUIT_DECAY, true),
    SNOW_BERRY(ItemFoodTFC.get(Food.SNOW_BERRY), true, DRIED_FRUIT_CATEGORY, true),
    STRAWBERRY(ItemFoodTFC.get(Food.STRAWBERRY), true, DRIED_FRUIT_SATURATION, true),
    WINTERGREEN_BERRY(ItemFoodTFC.get(Food.WINTERGREEN_BERRY), true, DRIED_FRUIT_CATEGORY, true),

    // Fruit Tree Fruits
    ABIU(ItemFoodTFC.get(Food.ABIU), true, DRIED_FRUIT_DECAY, false),
    AMLA(ItemFoodTFC.get(Food.AMLA), true, DRIED_FRUIT_DECAY, false),
    APRICOT(ItemFoodTFC.get(Food.APRICOT), true, DRIED_FRUIT_DECAY, false),
    AVOCADO(ItemFoodTFC.get(Food.AVOCADO), true, DRIED_FRUIT_DECAY, false),
    BAEL(ItemFoodTFC.get(Food.BAEL), true, DRIED_FRUIT_DECAY, false),
    BAY_LAUREL(ItemFoodTFC.get(Food.BAY_LAUREL), true, DRIED_FRUIT_DECAY, false),
    BER(ItemFoodTFC.get(Food.BER), true, DRIED_FRUIT_DECAY, false),
    BERGAMOT(ItemFoodTFC.get(Food.BERGAMOT), true, DRIED_FRUIT_DECAY, false),
    BLACK_CHERRY(ItemFoodTFC.get(Food.BLACK_CHERRY), true, DRIED_FRUIT_DECAY, false),
    BLACK_PEPPER(ItemFoodTFC.get(Food.BLACK_PEPPER), true, DRIED_FRUIT_DECAY, false),
    BLACKCURRANT(ItemFoodTFC.get(Food.BLACKCURRANT), true, DRIED_FRUIT_DECAY, false),
    BLACKTHORN(ItemFoodTFC.get(Food.BLACKTHORN), true, DRIED_FRUIT_SATURATION, false),
    BUDDHA_HAND(ItemFoodTFC.get(Food.BUDDHA_HAND), true, DRIED_FRUIT_DECAY, false),
    CACAO(ItemFoodTFC.get(Food.CACAO), true, DRIED_FRUIT_DECAY, false),
    CHERRY_PLUM(ItemFoodTFC.get(Food.CHERRY_PLUM), true, DRIED_FRUIT_SATURATION, false),
    CITRON(ItemFoodTFC.get(Food.CITRON), true, DRIED_FRUIT_DECAY, false),
    CRABAPPLE(ItemFoodTFC.get(Food.CRABAPPLE), true, DRIED_FRUIT_DECAY, false),
    DAMSON_PLUM(ItemFoodTFC.get(Food.DAMSON_PLUM), true, DRIED_FRUIT_SATURATION, false),
    DATE(ItemFoodTFC.get(Food.DATE), true, DRIED_FRUIT_DECAY, false),
    ELDER(ItemFoodTFC.get(Food.ELDER), true, DRIED_FRUIT_DECAY, false),
    FIG(ItemFoodTFC.get(Food.FIG), true, DRIED_FRUIT_DECAY, false),
    FINGER_LIME(ItemFoodTFC.get(Food.FINGER_LIME), true, DRIED_FRUIT_DECAY, false),
    GRAPEFRUIT(ItemFoodTFC.get(Food.GRAPEFRUIT), true, DRIED_FRUIT_DECAY, false),
    GUAVA(ItemFoodTFC.get(Food.GUAVA), true, DRIED_FRUIT_DECAY, false),
    ICE_CREAM_BEAN(ItemFoodTFC.get(Food.ICE_CREAM_BEAN), true, DRIED_FRUIT_SATURATION, false),
    JACKFRUIT(ItemFoodTFC.get(Food.JACKFRUIT), true, DRIED_FRUIT_SATURATION, false),
    JUJUBE(ItemFoodTFC.get(Food.JUJUBE), true, DRIED_FRUIT_DECAY, false),
    JUNIPER(ItemFoodTFC.get(Food.BER), true, DRIED_FRUIT_DECAY, false),
    KAKI(ItemFoodTFC.get(Food.KAKI), true, DRIED_FRUIT_DECAY, false),
    KEY_LIME(ItemFoodTFC.get(Food.KEY_LIME), true, DRIED_FRUIT_DECAY, false),
    KLUWAK(ItemFoodTFC.get(Food.KLUWAK), true, DRIED_FRUIT_DECAY, false),
    KUMQUAT(ItemFoodTFC.get(Food.KUMQUAT), true, DRIED_FRUIT_SATURATION, false),
    PERSIAN_LIME(ItemFoodTFC.get(Food.PERSIAN_LIME), true, DRIED_FRUIT_DECAY, false),
    LONGAN(ItemFoodTFC.get(Food.LONGAN), true, DRIED_FRUIT_DECAY, false),
    LOQUAT(ItemFoodTFC.get(Food.LOQUAT), true, DRIED_FRUIT_SATURATION, false),
    LYCHEE(ItemFoodTFC.get(Food.LYCHEE), true, DRIED_FRUIT_DECAY, false),
    MAMEY_SAPOTE(ItemFoodTFC.get(Food.MAMEY_SAPOTE), true, DRIED_FRUIT_DECAY, false),
    MANDERIN(ItemFoodTFC.get(Food.MANDERIN), true, DRIED_FRUIT_SATURATION, false),
    MANGO(ItemFoodTFC.get(Food.MANGO), true, DRIED_FRUIT_DECAY, false),
    MANGOSTEEN(ItemFoodTFC.get(Food.MANGOSTEEN), true, DRIED_FRUIT_DECAY, false),
    NECTARINE(ItemFoodTFC.get(Food.NECTARINE), true, DRIED_FRUIT_DECAY, false),
    OHIA_AI(ItemFoodTFC.get(Food.OHIA_AI), true, DRIED_FRUIT_DECAY, false),
    PAPAYA(ItemFoodTFC.get(Food.PAPAYA), true, DRIED_FRUIT_DECAY, false),
    PASSION_FRUIT(ItemFoodTFC.get(Food.PASSION_FRUIT), true, DRIED_FRUIT_DECAY, false),
    PEAR(ItemFoodTFC.get(Food.PEAR), true, DRIED_FRUIT_SATURATION, false),
    PERSIMMON(ItemFoodTFC.get(Food.PERSIMMON), true, DRIED_FRUIT_DECAY, false),
    PERUVIAN_PEPPER(ItemFoodTFC.get(Food.PERUVIAN_PEPPER), true, DRIED_FRUIT_DECAY, false),
    PLANTAIN(ItemFoodTFC.get(Food.PLANTAIN), true, DRIED_FRUIT_DECAY, false),
    POMEGRANATE(ItemFoodTFC.get(Food.POMEGRANATE), true, DRIED_FRUIT_DECAY, false),
    POMELO(ItemFoodTFC.get(Food.POMELO), true, DRIED_FRUIT_SATURATION, false),
    QUINCE(ItemFoodTFC.get(Food.QUINCE), true, DRIED_FRUIT_DECAY, false),
    RAINIER_CHERRY(ItemFoodTFC.get(Food.RAINIER_CHERRY), true, DRIED_FRUIT_DECAY, false),
    RED_BANANA(ItemFoodTFC.get(Food.RED_BANANA), true, DRIED_FRUIT_DECAY, false),
    RED_CURRANT(ItemFoodTFC.get(Food.RED_CURRANT), true, DRIED_FRUIT_DECAY, false),
    SAND_PEAR(ItemFoodTFC.get(Food.SAND_PEAR), true, DRIED_FRUIT_DECAY, false),
    SAPODILLA(ItemFoodTFC.get(Food.SAPODILLA), true, DRIED_FRUIT_DECAY, false),
    SATSUMA(ItemFoodTFC.get(Food.SATSUMA), true, DRIED_FRUIT_SATURATION, false),
    SOUR_CHERRY(ItemFoodTFC.get(Food.SOUR_CHERRY), true, DRIED_FRUIT_DECAY, false),
    SOURSOP(ItemFoodTFC.get(Food.SOURSOP), true, DRIED_FRUIT_DECAY, false),
    STARFRUIT(ItemFoodTFC.get(Food.STARFRUIT), true, DRIED_FRUIT_DECAY, false),
    TAMARILLO(ItemFoodTFC.get(Food.TAMARILLO), true, DRIED_FRUIT_DECAY, false),
    TANGERINE(ItemFoodTFC.get(Food.TANGERINE), true, DRIED_FRUIT_SATURATION, false),
    TROPICAL_APRICOT(ItemFoodTFC.get(Food.TROPICAL_APRICOT), true, DRIED_FRUIT_SATURATION, false),
    VANILLA(ItemFoodTFC.get(Food.VANILLA), true, DRIED_FRUIT_DECAY, false),


    // Normal Tree Fruits
    BAOBAB_FRUIT(ItemFoodTFC.get(Food.BAOBAB_FRUIT), true, DRIED_FRUIT_DECAY, false),
    BARREL_CACTUS_FRUIT(ItemFoodTFC.get(Food.BARREL_CACTUS_FRUIT), true, DRIED_FRUIT_DECAY, false),
    HAWTHORN(ItemFoodTFC.get(Food.HAWTHORN), true, DRIED_FRUIT_DECAY, false),
    OSAGE_ORANGE(ItemFoodTFC.get(Food.OSAGE_ORANGE), true, DRIED_FRUIT_DECAY, false),
    PINK_IVORY_DRUPE(ItemFoodTFC.get(Food.PINK_IVORY_DRUPE), true, DRIED_FRUIT_DECAY, false),
    RIBERRY(ItemFoodTFC.get(Food.RIBERRY), true, DRIED_FRUIT_DECAY, false),
    ROWAN_BERRY(ItemFoodTFC.get(Food.ROWAN_BERRY), true, DRIED_FRUIT_DECAY, false),
    SKY_FRUIT(ItemFoodTFC.get(Food.SKY_FRUIT), true, DRIED_FRUIT_DECAY, false),
    YEW_BERRY(ItemFoodTFC.get(Food.YEW_BERRY), true, DRIED_FRUIT_DECAY, false),

    // Crop Foods
    GREEN_GRAPE(ItemFoodTFC.get(Food.GREEN_GRAPE), true, DRIED_FRUIT_DECAY, false),
    PURPLE_GRAPE(ItemFoodTFC.get(Food.PURPLE_GRAPE), true, DRIED_FRUIT_DECAY, false);

    private final Item fruit;
    private final boolean dry;
    private final FoodData driedData;
    public final boolean isVanillaFood;

    Fruits(Item fruit, boolean dry, FoodData driedData, boolean isVanillaFood)
    {
        this.fruit = fruit;
        this.dry = dry;
        this.driedData = driedData;
        this.isVanillaFood = isVanillaFood;
    }


    public Item getFruit()
    {
        return this.fruit;
    }

    public boolean canDry()
    {
        return this.dry;
    }

    public FoodData getDriedData()
    {
        return this.driedData;
    }
}
