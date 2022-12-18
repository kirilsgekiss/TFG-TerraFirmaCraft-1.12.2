/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.api.recipes;

import javax.annotation.Nullable;

import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.IForgeRegistryEntry;

import net.dries007.tfc.api.capability.metal.CapabilityMetalItem;
import net.dries007.tfc.api.capability.metal.IMetalItem;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;

@SuppressWarnings("WeakerAccess")
public class BlastFurnaceRecipe extends IForgeRegistryEntry.Impl<BlastFurnaceRecipe>
{
    @Nullable
    public static BlastFurnaceRecipe get(ItemStack inputItem)
    {
        return TFCRegistries.BLAST_FURNACE.getValuesCollection().stream().filter(x -> x.isValidInput(inputItem)).findFirst().orElse(null);
    }

    @Nullable
    public static BlastFurnaceRecipe get(Material inputMetal)
    {
        return TFCRegistries.BLAST_FURNACE.getValuesCollection().stream().filter(x -> x.input == inputMetal).findFirst().orElse(null);
    }

    protected Material output;
    protected Material input;
    protected IIngredient<ItemStack> additive;

    /**
     * Creates a new blast furnace recipe
     *
     * @param output   the metal output of this recipe
     * @param input    the metal input of this recipe
     * @param additive additive to make this recipe (for pig iron, this means flux)
     */
    public BlastFurnaceRecipe(Material output, Material input, IIngredient<ItemStack> additive)
    {
        this.output = output;
        this.input = input;
        this.additive = additive;

        //Ensure one blast furnace recipe per input metal
        //noinspection ConstantConditions
        setRegistryName(input.getUnlocalizedName());
    }

    @Nullable
    public FluidStack getOutput(ItemStack stack)
    {
        IMetalItem metal = CapabilityMetalItem.getMetalItem(stack);
        int value = metal != null && metal.getMetal(stack) == input ? metal.getSmeltAmount(stack) : 0;
        if (value > 0)
        {
            if (metal.getMetal(stack).getFluid() != null)
            {
                return new FluidStack(metal.getMetal(stack).getFluid(), value);
            }
        }
        else
        {
            return null;
        }

        return null;
    }

    public boolean isValidInput(ItemStack stack)
    {
        IMetalItem metal = CapabilityMetalItem.getMetalItem(stack);
        return metal != null && metal.getMetal(stack) == input;
    }

    public boolean isValidAdditive(ItemStack stack)
    {
        return additive.testIgnoreCount(stack);
    }

    /**
     * For JEI only, gets an ingot of this recipe metal
     *
     * @return itemstack containing ingot of the specified metal
     */
    public ItemStack getOutput()
    {
        return OreDictUnifier.get(OrePrefix.ingot, output);
    }
}
