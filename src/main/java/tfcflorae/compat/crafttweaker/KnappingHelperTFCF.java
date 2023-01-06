package tfcflorae.compat.crafttweaker;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryModifiable;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IItemStack;

import net.dries007.tfc.api.recipes.knapping.KnappingRecipe;
import net.dries007.tfc.api.recipes.knapping.KnappingRecipeSimple;
import net.dries007.tfc.api.recipes.knapping.KnappingType;
import net.dries007.tfc.api.registries.TFCRegistries;

public class KnappingHelperTFCF
{
    public static void addRecipe(ResourceLocation registryName, KnappingType type, IItemStack output, String... pattern)
    {
        if (output == null || pattern.length < 1 || pattern.length > 5)
            throw new IllegalArgumentException("Output item must be non-null and pattern must be a closed interval [1, 5]");
        ItemStack outputStack = (ItemStack) output.getInternal();
        KnappingRecipe recipe = new KnappingRecipeSimple(type, true, outputStack, pattern).setRegistryName(registryName);
        CraftTweakerAPI.apply(new IAction()
        {
            @Override
            public void apply()
            {
                TFCRegistries.KNAPPING.register(recipe);
            }

            @Override
            public String describe()
            {
                //noinspection ConstantConditions
                return "Adding knapping recipe " + recipe.getRegistryName().toString();
            }
        });
    }

    public static void removeRecipe(IItemStack output, KnappingType type)
    {
        if (output == null) throw new IllegalArgumentException("Output not allowed to be empty");
        ItemStack item = (ItemStack) output.getInternal();
        List<KnappingRecipe> removeList = new ArrayList<>();
        TFCRegistries.KNAPPING.getValuesCollection()
                .stream()
                .filter(x -> x.getType() == type && x.getOutput(ItemStack.EMPTY).isItemEqual(item))
                .forEach(removeList::add);
        for (KnappingRecipe rem : removeList)
        {
            CraftTweakerAPI.apply(new IAction()
            {
                @Override
                public void apply()
                {
                    IForgeRegistryModifiable modRegistry = (IForgeRegistryModifiable) TFCRegistries.KNAPPING;
                    modRegistry.remove(rem.getRegistryName());
                }

                @Override
                public String describe()
                {
                    //noinspection ConstantConditions
                    return "Removing knapping recipe " + rem.getRegistryName().toString();
                }
            });
        }
    }

    public static void removeRecipe(String registryName)
    {
        KnappingRecipe recipe = TFCRegistries.KNAPPING.getValue(new ResourceLocation(registryName));
        if (recipe != null)
        {
            CraftTweakerAPI.apply(new IAction()
            {
                @Override
                public void apply()
                {
                    IForgeRegistryModifiable modRegistry = (IForgeRegistryModifiable) TFCRegistries.KNAPPING;
                    modRegistry.remove(recipe.getRegistryName());
                }

                @Override
                public String describe()
                {
                    //noinspection ConstantConditions
                    return "Removing knapping recipe " + recipe.getRegistryName().toString();
                }
            });
        }
    }

    public static KnappingType getType(String type) {
        switch (type) {
            case "PineappleLeather":
                return KnappingType.PINEAPPLE_LEATHER;
            case "BurlapCloth":
                return KnappingType.BURLAP_CLOTH;
            case "WoolCloth":
                return KnappingType.WOOL_CLOTH;
            case "SilkCloth":
                return KnappingType.SILK_CLOTH;
            case "SisalCloth":
                return KnappingType.SISAL_CLOTH;
            case "CottonCloth":
                return KnappingType.COTTON_CLOTH;
            case "LinenCloth":
                return KnappingType.LINEN_CLOTH;
            case "HempCloth":
                return KnappingType.HEMP_CLOTH;
            case "YuccaCanvas":
                return KnappingType.YUCCA_CANVAS;
            case "Mud":
                return KnappingType.MUD;
            case "EarthenwareClay":
                return KnappingType.EARTHENWARE_CLAY;
            case "KaoliniteClay":
                return KnappingType.KAOLINITE_CLAY;
            case "StonewareClay":
                return KnappingType.STONEWARE_CLAY;
            case "Flint":
                return KnappingType.FLINT;
        }
        return null;
    }
}
