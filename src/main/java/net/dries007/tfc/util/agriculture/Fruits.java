package net.dries007.tfc.util.agriculture;

import net.dries007.tfc.objects.items.food.TFCItemFood;
import net.minecraft.item.Item;

import net.dries007.tfc.api.capability.food.FoodData;

import static net.dries007.tfc.api.capability.food.FoodData.*;

/**
 * This is an easy way to wrap all the TFC fruits with our data
 */
public enum Fruits
{
    // TFC Foods
    /*
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
    ABIU(ItemFoodTFCF.get(ItemsTFC.ABIU), true, DRIED_FRUIT_DECAY, false),
    AMLA(ItemFoodTFCF.get(ItemsTFC.AMLA), true, DRIED_FRUIT_DECAY, false),
    APRICOT(ItemFoodTFCF.get(ItemsTFC.APRICOT), true, DRIED_FRUIT_DECAY, false),
    AVOCADO(ItemFoodTFCF.get(ItemsTFC.AVOCADO), true, DRIED_FRUIT_DECAY, false),
    BAEL(ItemFoodTFCF.get(ItemsTFC.BAEL), true, DRIED_FRUIT_DECAY, false),
    BAY_LAUREL(ItemFoodTFCF.get(ItemsTFC.BAY_LAUREL), true, DRIED_FRUIT_DECAY, false),
    BER(ItemFoodTFCF.get(ItemsTFC.BER), true, DRIED_FRUIT_DECAY, false),
    BERGAMOT(ItemFoodTFCF.get(ItemsTFC.BERGAMOT), true, DRIED_FRUIT_DECAY, false),
    BLACK_CHERRY(ItemFoodTFCF.get(ItemsTFC.BLACK_CHERRY), true, DRIED_FRUIT_DECAY, false),
    BLACK_PEPPER(ItemFoodTFCF.get(ItemsTFC.BLACK_PEPPER), true, DRIED_FRUIT_DECAY, false),
    BLACKCURRANT(ItemFoodTFCF.get(ItemsTFC.BLACKCURRANT), true, DRIED_FRUIT_DECAY, false),
    BLACKTHORN(ItemFoodTFCF.get(ItemsTFC.BLACKTHORN), true, DRIED_FRUIT_SATURATION, false),
    BUDDHA_HAND(ItemFoodTFCF.get(ItemsTFC.BUDDHA_HAND), true, DRIED_FRUIT_DECAY, false),
    CACAO(ItemFoodTFCF.get(ItemsTFC.CACAO), true, DRIED_FRUIT_DECAY, false),
    CHERRY_PLUM(ItemFoodTFCF.get(ItemsTFC.CHERRY_PLUM), true, DRIED_FRUIT_SATURATION, false),
    CITRON(ItemFoodTFCF.get(ItemsTFC.CITRON), true, DRIED_FRUIT_DECAY, false),
    CRABAPPLE(ItemFoodTFCF.get(ItemsTFC.CRABAPPLE), true, DRIED_FRUIT_DECAY, false),
    DAMSON_PLUM(ItemFoodTFCF.get(ItemsTFC.DAMSON_PLUM), true, DRIED_FRUIT_SATURATION, false),
    DATE(ItemFoodTFCF.get(ItemsTFC.DATE), true, DRIED_FRUIT_DECAY, false),
    ELDER(ItemFoodTFCF.get(ItemsTFC.ELDER), true, DRIED_FRUIT_DECAY, false),
    FIG(ItemFoodTFCF.get(ItemsTFC.FIG), true, DRIED_FRUIT_DECAY, false),
    FINGER_LIME(ItemFoodTFCF.get(ItemsTFC.FINGER_LIME), true, DRIED_FRUIT_DECAY, false),
    GRAPEFRUIT(ItemFoodTFCF.get(ItemsTFC.GRAPEFRUIT), true, DRIED_FRUIT_DECAY, false),
    GUAVA(ItemFoodTFCF.get(ItemsTFC.GUAVA), true, DRIED_FRUIT_DECAY, false),
    ICE_CREAM_BEAN(ItemFoodTFCF.get(ItemsTFC.ICE_CREAM_BEAN), true, DRIED_FRUIT_SATURATION, false),
    JACKFRUIT(ItemFoodTFCF.get(ItemsTFC.JACKFRUIT), true, DRIED_FRUIT_SATURATION, false),
    JUJUBE(ItemFoodTFCF.get(ItemsTFC.JUJUBE), true, DRIED_FRUIT_DECAY, false),
    JUNIPER(ItemFoodTFCF.get(ItemsTFC.BER), true, DRIED_FRUIT_DECAY, false),
    KAKI(ItemFoodTFCF.get(ItemsTFC.KAKI), true, DRIED_FRUIT_DECAY, false),
    KEY_LIME(ItemFoodTFCF.get(ItemsTFC.KEY_LIME), true, DRIED_FRUIT_DECAY, false),
    KLUWAK(ItemFoodTFCF.get(ItemsTFC.KLUWAK), true, DRIED_FRUIT_DECAY, false),
    KUMQUAT(ItemFoodTFCF.get(ItemsTFC.KUMQUAT), true, DRIED_FRUIT_SATURATION, false),
    PERSIAN_LIME(ItemFoodTFCF.get(ItemsTFC.PERSIAN_LIME), true, DRIED_FRUIT_DECAY, false),
    LONGAN(ItemFoodTFCF.get(ItemsTFC.LONGAN), true, DRIED_FRUIT_DECAY, false),
    LOQUAT(ItemFoodTFCF.get(ItemsTFC.LOQUAT), true, DRIED_FRUIT_SATURATION, false),
    LYCHEE(ItemFoodTFCF.get(ItemsTFC.LYCHEE), true, DRIED_FRUIT_DECAY, false),
    MAMEY_SAPOTE(ItemFoodTFCF.get(ItemsTFC.MAMEY_SAPOTE), true, DRIED_FRUIT_DECAY, false),
    MANDERIN(ItemFoodTFCF.get(ItemsTFC.MANDERIN), true, DRIED_FRUIT_SATURATION, false),
    MANGO(ItemFoodTFCF.get(ItemsTFC.MANGO), true, DRIED_FRUIT_DECAY, false),
    MANGOSTEEN(ItemFoodTFCF.get(ItemsTFC.MANGOSTEEN), true, DRIED_FRUIT_DECAY, false),
    NECTARINE(ItemFoodTFCF.get(ItemsTFC.NECTARINE), true, DRIED_FRUIT_DECAY, false),
    OHIA_AI(ItemFoodTFCF.get(ItemsTFC.OHIA_AI), true, DRIED_FRUIT_DECAY, false),
    PAPAYA(ItemFoodTFCF.get(ItemsTFC.PAPAYA), true, DRIED_FRUIT_DECAY, false),
    PASSION_FRUIT(ItemFoodTFCF.get(ItemsTFC.PASSION_FRUIT), true, DRIED_FRUIT_DECAY, false),
    PEAR(ItemFoodTFCF.get(ItemsTFC.PEAR), true, DRIED_FRUIT_SATURATION, false),
    PERSIMMON(ItemFoodTFCF.get(ItemsTFC.PERSIMMON), true, DRIED_FRUIT_DECAY, false),
    PERUVIAN_PEPPER(ItemFoodTFCF.get(ItemsTFC.PERUVIAN_PEPPER), true, DRIED_FRUIT_DECAY, false),
    PLANTAIN(ItemFoodTFCF.get(ItemsTFC.PLANTAIN), true, DRIED_FRUIT_DECAY, false),
    POMEGRANATE(ItemFoodTFCF.get(ItemsTFC.POMEGRANATE), true, DRIED_FRUIT_DECAY, false),
    POMELO(ItemFoodTFCF.get(ItemsTFC.POMELO), true, DRIED_FRUIT_SATURATION, false),
    QUINCE(ItemFoodTFCF.get(ItemsTFC.QUINCE), true, DRIED_FRUIT_DECAY, false),
    RAINIER_CHERRY(ItemFoodTFCF.get(ItemsTFC.RAINIER_CHERRY), true, DRIED_FRUIT_DECAY, false),
    RED_BANANA(ItemFoodTFCF.get(ItemsTFC.RED_BANANA), true, DRIED_FRUIT_DECAY, false),
    RED_CURRANT(ItemFoodTFCF.get(ItemsTFC.RED_CURRANT), true, DRIED_FRUIT_DECAY, false),
    SAND_PEAR(ItemFoodTFCF.get(ItemsTFC.SAND_PEAR), true, DRIED_FRUIT_DECAY, false),
    SAPODILLA(ItemFoodTFCF.get(ItemsTFC.SAPODILLA), true, DRIED_FRUIT_DECAY, false),
    SATSUMA(ItemFoodTFCF.get(ItemsTFC.SATSUMA), true, DRIED_FRUIT_SATURATION, false),
    SOUR_CHERRY(ItemFoodTFCF.get(ItemsTFC.SOUR_CHERRY), true, DRIED_FRUIT_DECAY, false),
    SOURSOP(ItemFoodTFCF.get(ItemsTFC.SOURSOP), true, DRIED_FRUIT_DECAY, false),
    STARFRUIT(ItemFoodTFCF.get(ItemsTFC.STARFRUIT), true, DRIED_FRUIT_DECAY, false),
    TAMARILLO(ItemFoodTFCF.get(ItemsTFC.TAMARILLO), true, DRIED_FRUIT_DECAY, false),
    TANGERINE(ItemFoodTFCF.get(ItemsTFC.TANGERINE), true, DRIED_FRUIT_SATURATION, false),
    TROPICAL_APRICOT(ItemFoodTFCF.get(ItemsTFC.TROPICAL_APRICOT), true, DRIED_FRUIT_SATURATION, false),
    VANILLA(ItemFoodTFCF.get(ItemsTFC.VANILLA), true, DRIED_FRUIT_DECAY, false),
    */

    // Normal Tree Fruits
    BAOBAB_FRUIT(TFCItemFood.get(Food.BAOBAB_FRUIT), true, DRIED_FRUIT_DECAY, false),
    BARREL_CACTUS_FRUIT(TFCItemFood.get(Food.BARREL_CACTUS_FRUIT), true, DRIED_FRUIT_DECAY, false),
    HAWTHORN(TFCItemFood.get(Food.HAWTHORN), true, DRIED_FRUIT_DECAY, false),
    OSAGE_ORANGE(TFCItemFood.get(Food.OSAGE_ORANGE), true, DRIED_FRUIT_DECAY, false),
    PINK_IVORY_DRUPE(TFCItemFood.get(Food.PINK_IVORY_DRUPE), true, DRIED_FRUIT_DECAY, false),
    RIBERRY(TFCItemFood.get(Food.RIBERRY), true, DRIED_FRUIT_DECAY, false),
    ROWAN_BERRY(TFCItemFood.get(Food.ROWAN_BERRY), true, DRIED_FRUIT_DECAY, false),
    SKY_FRUIT(TFCItemFood.get(Food.SKY_FRUIT), true, DRIED_FRUIT_DECAY, false),
    YEW_BERRY(TFCItemFood.get(Food.YEW_BERRY), true, DRIED_FRUIT_DECAY, false),

    // Crop Foods
    GREEN_GRAPE(TFCItemFood.get(Food.GREEN_GRAPE), true, DRIED_FRUIT_DECAY, false),
    PURPLE_GRAPE(TFCItemFood.get(Food.PURPLE_GRAPE), true, DRIED_FRUIT_DECAY, false);

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
