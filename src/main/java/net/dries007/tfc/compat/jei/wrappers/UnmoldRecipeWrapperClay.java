/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.compat.jei.wrappers;

import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;
import net.dries007.tfc.api.capability.IMaterialHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.dries007.tfc.objects.items.ceramics.fired.molds.ItemClayMold;

public class UnmoldRecipeWrapperClay implements IRecipeWrapper
{
    private final ItemStack mold;
    private final ItemStack output;

    public UnmoldRecipeWrapperClay(Material material, OrePrefix orePrefix)
    {
        mold = new ItemStack(ItemClayMold.get(orePrefix));
        IFluidHandler cap = mold.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
        if (cap instanceof IMaterialHandler)
        {
            cap.fill(new FluidStack(material.getFluid(), ItemClayMold.get(orePrefix).moldCapacity), true);
        }
        output = OreDictUnifier.get(orePrefix, material);
    }


    @Override
    public void getIngredients(IIngredients ingredients)
    {
        ingredients.setInput(VanillaTypes.ITEM, mold);
        ingredients.setOutput(VanillaTypes.ITEM, output);
    }
}
