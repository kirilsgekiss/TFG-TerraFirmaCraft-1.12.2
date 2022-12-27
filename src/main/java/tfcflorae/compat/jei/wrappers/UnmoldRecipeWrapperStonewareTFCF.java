package tfcflorae.compat.jei.wrappers;

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

import tfcflorae.objects.items.ceramics.ItemStonewareMold;

public class UnmoldRecipeWrapperStonewareTFCF implements IRecipeWrapper
{
    private final ItemStack mold;
    private final ItemStack output;

    public UnmoldRecipeWrapperStonewareTFCF(Material material, OrePrefix orePrefix)
    {
        mold = new ItemStack(ItemStonewareMold.get(orePrefix));
        IFluidHandler cap = mold.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
        if (cap instanceof IMaterialHandler)
        {
            cap.fill(new FluidStack(material.getFluid(), ItemStonewareMold.get(orePrefix).moldCapacity), true);
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
