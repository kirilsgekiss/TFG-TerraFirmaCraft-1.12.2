package tfcflorae.compat.jei.wrappers;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;
import net.dries007.tfc.api.capability.IMaterialHandler;
import net.dries007.tfc.objects.items.ceramics.ItemMold;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;

import tfcflorae.objects.items.ceramics.ItemEarthenwareMold;

public class CastingRecipeWrapperEarthenwareTFCF implements IRecipeWrapper
{
    private final ItemStack mold;
    private final FluidStack input;

    public CastingRecipeWrapperEarthenwareTFCF(Material material, OrePrefix orePrefix)
    {
        input = new FluidStack(material.getFluid(), ItemEarthenwareMold.get(orePrefix).moldCapacity);
        mold = new ItemStack(ItemEarthenwareMold.get(orePrefix));
        IFluidHandler cap = mold.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
        if (cap instanceof IMaterialHandler)
        {
            cap.fill(input, true);
        }
    }

    @Override
    public void getIngredients(IIngredients ingredients)
    {
        ingredients.setInput(VanillaTypes.FLUID, input);
        ingredients.setOutput(VanillaTypes.ITEM, mold);
    }
}
