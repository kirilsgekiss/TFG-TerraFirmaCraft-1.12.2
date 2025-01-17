package net.dries007.tfc.util;

import net.dries007.tfc.api.capability.heat.CapabilityItemHeat;
import net.dries007.tfc.api.capability.heat.ItemHeatHandler;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.objects.blocks.TFCBlocks;
import net.dries007.tfc.objects.blocks.plants.TFCBlockPlant;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.objects.items.TFCItems;
import net.dries007.tfc.objects.items.food.TFCItemFood;
import net.dries007.tfc.types.DefaultPlants;
import net.dries007.tfc.util.agriculture.Food;
import net.minecraft.init.Items;

public class CapabilityHeatHandler {
    public static void init() {
        /*ItemStack woodenBucket = new ItemStack(ItemsTFC.WOODEN_BUCKET);
        IFluidHandler woodenBucketSaltWater = woodenBucket.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
        woodenBucketSaltWater.fill(new FluidStack(FluidsTFC.SALT_WATER.get(), Fluid.BUCKET_VOLUME), true);
        IFluidHandler woodenBucketSweetSap = woodenBucket.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
        woodenBucketSweetSap.fill(new FluidStack(FluidsTFC.SWEET_SAP.get(), Fluid.BUCKET_VOLUME), true);
        IFluidHandler woodenBucketSweetSyrup = woodenBucket.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
        woodenBucketSweetSyrup.fill(new FluidStack(FluidsTFC.SWEET_SYRUP.get(), Fluid.BUCKET_VOLUME), true);*/

        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.ARTISTS_CONK))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.SULPHUR_SHELF))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.TURKEY_TAIL))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.PORCINI))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.AMANITA))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.BLACK_POWDERPUFF))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.CHANTERELLE))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.DEATH_CAP))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.GIANT_CLUB))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.PARASOL_MUSHROOM))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.STINKHORN))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.WEEPING_MILK_CAP))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.WOOD_BLEWIT))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.WOOLLY_GOMPHUS))), () -> new ItemHeatHandler(null, 1, 480));

        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItems.WOODEN_BUCKET), () -> new ItemHeatHandler(null, 1.76f, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItems.WOODEN_BUCKET_SALT), () -> new ItemHeatHandler(null, 3.993f, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItems.WOODEN_BUCKET_SUGAR), () -> new ItemHeatHandler(null, 1.255f, 480));
        /*CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of((Item) woodenBucketSaltWater), () -> new ItemHeatHandler(null, 3.993f, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of((Item) woodenBucketSweetSap), () -> new ItemHeatHandler(null, 4.01f, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of((Item) woodenBucketSweetSyrup), () -> new ItemHeatHandler(null, 2.1f, 480));*/

        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(Items.CLAY_BALL), () -> new ItemHeatHandler(null, 1, 1599));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItems.EARTHENWARE_CLAY), () -> new ItemHeatHandler(null, 1, 1599));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItems.KAOLINITE_CLAY), () -> new ItemHeatHandler(null, 1, 1599));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItems.STONEWARE_CLAY), () -> new ItemHeatHandler(null, 1, 1599));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItems.STRAW), () -> new ItemHeatHandler(null, 1, 30));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCBlocks.TWIG), () -> new ItemHeatHandler(null, 1, 50));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCBlocks.DRIFTWOOD), () -> new ItemHeatHandler(null, 1, 60));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCBlocks.BONES), () -> new ItemHeatHandler(null, 1, 425));

        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItemFood.get(Food.BLACK_TEA)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItemFood.get(Food.GREEN_TEA)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItemFood.get(Food.WHITE_TEA)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItemFood.get(Food.CANNABIS_BUD)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItemFood.get(Food.CANNABIS_LEAF)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItemFood.get(Food.COCA_LEAF)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItemFood.get(Food.OPIUM_POPPY_BULB)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItemFood.get(Food.PEYOTE)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItemFood.get(Food.TOBACCO_LEAF)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItemFood.get(Food.DRIED_COFFEA_CHERRIES)), () -> new ItemHeatHandler(null, 1, 480));

        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItems.CHAMOMILE_HEAD), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItems.DANDELION_HEAD), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItems.LABRADOR_TEA_HEAD), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItems.SUNFLOWER_HEAD), () -> new ItemHeatHandler(null, 1, 480));

        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItemFood.get(Food.DRIED_BLACK_TEA)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItemFood.get(Food.DRIED_GREEN_TEA)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItemFood.get(Food.DRIED_WHITE_TEA)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItemFood.get(Food.DRIED_CANNABIS_BUD)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItemFood.get(Food.DRIED_CANNABIS_LEAF)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItemFood.get(Food.DRIED_COCA_LEAF)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItemFood.get(Food.DRIED_OPIUM_POPPY_BULB)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItemFood.get(Food.DRIED_PEYOTE)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItemFood.get(Food.DRIED_TOBACCO_LEAF)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItemFood.get(Food.ROASTED_COFFEE_BEANS)), () -> new ItemHeatHandler(null, 1, 480));

        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItems.DRIED_CHAMOMILE_HEAD), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItems.DRIED_DANDELION_HEAD), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItems.DRIED_LABRADOR_TEA_HEAD), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(TFCItems.DRIED_SUNFLOWER_HEAD), () -> new ItemHeatHandler(null, 1, 480));
    }
}
