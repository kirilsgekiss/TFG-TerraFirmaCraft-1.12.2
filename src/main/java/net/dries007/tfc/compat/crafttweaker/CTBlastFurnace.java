/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.compat.crafttweaker;

import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.Material;
import net.minecraftforge.registries.IForgeRegistryModifiable;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.liquid.ILiquidStack;
import net.dries007.tfc.api.recipes.BlastFurnaceRecipe;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.terrafirmacraft.BlastFurnace")
@ZenRegister
public class CTBlastFurnace
{
    @SuppressWarnings({"unchecked"})
    @ZenMethod
    public static void addRecipe(String outputMetal, String inputMetal, crafttweaker.api.item.IIngredient additive)
    {
        Material result = GregTechAPI.MATERIAL_REGISTRY.getObject(outputMetal);
        if (result == null)
        {
            throw new IllegalArgumentException("Output metal specified not found!");
        }
        Material input = GregTechAPI.MATERIAL_REGISTRY.getObject(inputMetal);
        if (input == null)
        {
            throw new IllegalArgumentException("Input metal specified not found!");
        }
        if (BlastFurnaceRecipe.get(input) != null)
        {
            throw new IllegalStateException("Recipe for that input metal already exists!");
        }
        if (additive == null)
            throw new IllegalArgumentException("Additive is not allowed to be empty");
        if (additive instanceof ILiquidStack)
            throw new IllegalArgumentException("There is a fluid where it's supposed to be an item!");
        //noinspection rawtypes
        IIngredient ingredient = CTHelper.getInternalIngredient(additive);
        BlastFurnaceRecipe recipe = new BlastFurnaceRecipe(result, input, ingredient);
        CraftTweakerAPI.apply(new IAction()
        {
            @Override
            public void apply()
            {
                TFCRegistries.BLAST_FURNACE.register(recipe);
            }

            @Override
            public String describe()
            {
                //noinspection ConstantConditions
                return "Adding blast furnace recipe for " + input.getUnlocalizedName() + " -> " + result.getUnlocalizedName();
            }
        });
    }

    @ZenMethod
    public static void removeRecipe(String inputMetal)
    {
        Material input = GregTechAPI.MATERIAL_REGISTRY.getObject(inputMetal);
        if (input == null)
        {
            throw new IllegalArgumentException("Metal specified not found!");
        }
        BlastFurnaceRecipe recipe = BlastFurnaceRecipe.get(input);
        if (recipe != null)
        {
            CraftTweakerAPI.apply(new IAction()
            {
                @Override
                public void apply()
                {
                    IForgeRegistryModifiable<BlastFurnaceRecipe> modRegistry = (IForgeRegistryModifiable<BlastFurnaceRecipe>) TFCRegistries.BLAST_FURNACE;
                    modRegistry.remove(recipe.getRegistryName());
                }

                @Override
                public String describe()
                {
                    //noinspection ConstantConditions
                    return "Removing blast furnace recipe " + recipe.getRegistryName().toString();
                }
            });
        }
    }
}
