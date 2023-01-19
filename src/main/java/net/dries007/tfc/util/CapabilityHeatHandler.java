package net.dries007.tfc.util;

import net.dries007.tfc.objects.items.food.ItemFoodTFC;
import net.dries007.tfc.types.DefaultTrees;
import net.dries007.tfc.util.agriculture.Food;
import net.minecraft.init.Items;
import net.dries007.tfc.api.capability.heat.CapabilityItemHeat;
import net.dries007.tfc.api.capability.heat.ItemHeatHandler;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.objects.blocks.plants.BlockPlantTFC;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.objects.items.ItemsTFC;
import net.dries007.tfc.types.DefaultPlants;

import tfcflorae.objects.blocks.BlocksTFCF;
import net.dries007.tfc.objects.blocks.wood.fruitwood.BlockFruitLog;
import net.dries007.tfc.objects.items.ItemsTFC;

public class CapabilityHeatHandler
{
    public static void init()
    {
        /*ItemStack woodenBucket = new ItemStack(ItemsTFC.WOODEN_BUCKET);
        IFluidHandler woodenBucketSaltWater = woodenBucket.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
        woodenBucketSaltWater.fill(new FluidStack(FluidsTFC.SALT_WATER.get(), Fluid.BUCKET_VOLUME), true);
        IFluidHandler woodenBucketSweetSap = woodenBucket.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
        woodenBucketSweetSap.fill(new FluidStack(FluidsTFC.SWEET_SAP.get(), Fluid.BUCKET_VOLUME), true);
        IFluidHandler woodenBucketSweetSyrup = woodenBucket.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
        woodenBucketSweetSyrup.fill(new FluidStack(FluidsTFC.SWEET_SYRUP.get(), Fluid.BUCKET_VOLUME), true);*/

        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(BlockPlantTFC.get(TFCRegistries.PLANTS.getValue(DefaultPlants.ARTISTS_CONK))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(BlockPlantTFC.get(TFCRegistries.PLANTS.getValue(DefaultPlants.SULPHUR_SHELF))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(BlockPlantTFC.get(TFCRegistries.PLANTS.getValue(DefaultPlants.TURKEY_TAIL))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(BlockPlantTFC.get(TFCRegistries.PLANTS.getValue(DefaultPlants.PORCINI))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(BlockPlantTFC.get(TFCRegistries.PLANTS.getValue(DefaultPlants.AMANITA))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(BlockPlantTFC.get(TFCRegistries.PLANTS.getValue(DefaultPlants.BLACK_POWDERPUFF))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(BlockPlantTFC.get(TFCRegistries.PLANTS.getValue(DefaultPlants.CHANTERELLE))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(BlockPlantTFC.get(TFCRegistries.PLANTS.getValue(DefaultPlants.DEATH_CAP))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(BlockPlantTFC.get(TFCRegistries.PLANTS.getValue(DefaultPlants.GIANT_CLUB))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(BlockPlantTFC.get(TFCRegistries.PLANTS.getValue(DefaultPlants.PARASOL_MUSHROOM))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(BlockPlantTFC.get(TFCRegistries.PLANTS.getValue(DefaultPlants.STINKHORN))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(BlockPlantTFC.get(TFCRegistries.PLANTS.getValue(DefaultPlants.WEEPING_MILK_CAP))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(BlockPlantTFC.get(TFCRegistries.PLANTS.getValue(DefaultPlants.WOOD_BLEWIT))), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(BlockPlantTFC.get(TFCRegistries.PLANTS.getValue(DefaultPlants.WOOLLY_GOMPHUS))), () -> new ItemHeatHandler(null, 1, 480));

        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemsTFC.WOODEN_BUCKET), () -> new ItemHeatHandler(null, 1.76f, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemsTFC.WOODEN_BUCKET_SALT), () -> new ItemHeatHandler(null, 3.993f, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemsTFC.WOODEN_BUCKET_SUGAR), () -> new ItemHeatHandler(null, 1.255f, 480));
        /*CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of((Item) woodenBucketSaltWater), () -> new ItemHeatHandler(null, 3.993f, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of((Item) woodenBucketSweetSap), () -> new ItemHeatHandler(null, 4.01f, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of((Item) woodenBucketSweetSyrup), () -> new ItemHeatHandler(null, 2.1f, 480));*/

        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(Items.CLAY_BALL), () -> new ItemHeatHandler(null, 1, 1599));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemsTFC.EARTHENWARE_CLAY), () -> new ItemHeatHandler(null, 1, 1599));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemsTFC.KAOLINITE_CLAY), () -> new ItemHeatHandler(null, 1, 1599));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemsTFC.STONEWARE_CLAY), () -> new ItemHeatHandler(null, 1, 1599));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(BlockFruitLog.get(TFCRegistries.TREES.getValue(DefaultTrees.EUCALYPTUS))), () -> new ItemHeatHandler(null, 1, 1599));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemsTFC.STRAW), () -> new ItemHeatHandler(null, 1, 30));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(BlocksTFCF.TWIG), () -> new ItemHeatHandler(null, 1, 50));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(BlocksTFCF.DRIFTWOOD), () -> new ItemHeatHandler(null, 1, 60));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(BlocksTFCF.BONES), () -> new ItemHeatHandler(null, 1, 425));

        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemFoodTFC.get(Food.BLACK_TEA)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemFoodTFC.get(Food.GREEN_TEA)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemFoodTFC.get(Food.WHITE_TEA)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemFoodTFC.get(Food.CANNABIS_BUD)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemFoodTFC.get(Food.CANNABIS_LEAF)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemFoodTFC.get(Food.COCA_LEAF)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemFoodTFC.get(Food.OPIUM_POPPY_BULB)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemFoodTFC.get(Food.PEYOTE)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemFoodTFC.get(Food.TOBACCO_LEAF)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemFoodTFC.get(Food.DRIED_COFFEA_CHERRIES)), () -> new ItemHeatHandler(null, 1, 480));

        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemsTFC.CHAMOMILE_HEAD), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemsTFC.DANDELION_HEAD), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemsTFC.LABRADOR_TEA_HEAD), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemsTFC.SUNFLOWER_HEAD), () -> new ItemHeatHandler(null, 1, 480));

        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemFoodTFC.get(Food.DRIED_BLACK_TEA)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemFoodTFC.get(Food.DRIED_GREEN_TEA)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemFoodTFC.get(Food.DRIED_WHITE_TEA)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemFoodTFC.get(Food.DRIED_CANNABIS_BUD)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemFoodTFC.get(Food.DRIED_CANNABIS_LEAF)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemFoodTFC.get(Food.DRIED_COCA_LEAF)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemFoodTFC.get(Food.DRIED_OPIUM_POPPY_BULB)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemFoodTFC.get(Food.DRIED_PEYOTE)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemFoodTFC.get(Food.DRIED_TOBACCO_LEAF)), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemFoodTFC.get(Food.ROASTED_COFFEE_BEANS)), () -> new ItemHeatHandler(null, 1, 480));

        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemsTFC.DRIED_CHAMOMILE_HEAD), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemsTFC.DRIED_DANDELION_HEAD), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemsTFC.DRIED_LABRADOR_TEA_HEAD), () -> new ItemHeatHandler(null, 1, 480));
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(ItemsTFC.DRIED_SUNFLOWER_HEAD), () -> new ItemHeatHandler(null, 1, 480));
    }
}
