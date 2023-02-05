package net.dries007.tfc.compat.jei.wrappers;

import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.dries007.tfc.api.capability.IMaterialHandler;
import net.dries007.tfc.objects.items.ceramics.fired.molds.ItemEarthenwareMold;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class UnmoldRecipeWrapperEarthenware implements IRecipeWrapper {
    private final ItemStack mold;
    private final ItemStack output;

    public UnmoldRecipeWrapperEarthenware(Material material, OrePrefix orePrefix) {
        mold = new ItemStack(ItemEarthenwareMold.get(orePrefix));
        IFluidHandler cap = mold.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
        if (cap instanceof IMaterialHandler) {
            cap.fill(new FluidStack(material.getFluid(), ItemEarthenwareMold.get(orePrefix).moldCapacity), true);
        }
        output = OreDictUnifier.get(orePrefix, material);
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.ITEM, mold);
        ingredients.setOutput(VanillaTypes.ITEM, output);
    }
}
