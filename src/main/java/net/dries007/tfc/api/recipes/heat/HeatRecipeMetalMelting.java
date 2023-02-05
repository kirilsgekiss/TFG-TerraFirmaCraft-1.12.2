/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.api.recipes.heat;

import gregtech.api.unification.material.Material;
import net.dries007.tfc.api.capability.metal.CapabilityMetalItem;
import net.dries007.tfc.api.capability.metal.IMetalItem;
import net.dries007.tfc.compat.gregtech.materials.TFCMaterials;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class HeatRecipeMetalMelting extends HeatRecipe {
    private final Material metal; // Used only in JEI to determine the metal registered in this recipe.

    public HeatRecipeMetalMelting(Material metal) {
        super(input -> {
            IMetalItem metalObject = CapabilityMetalItem.getMetalItem(input);
            if (metalObject != null) {
                return metalObject.getMetal(input) == metal;
            }
            return false;
        }, metal.getFluid().getTemperature());
        this.metal = metal;
    }

    @Nullable
    @Override
    public FluidStack getOutputFluid(ItemStack input) {
        IMetalItem metalObject = CapabilityMetalItem.getMetalItem(input);
        if (metalObject != null) {
            Material metal = metalObject.getMetal(input);
            if (metal != null) {
                if (metalObject.canMelt(input)) {
                    return new FluidStack(metal.getFluid(), metalObject.getSmeltAmount(input));
                } else {
                    // Melt into unknown alloy so items aren't simply voided and becomes something
                    return new FluidStack(TFCMaterials.Unknown.getFluid(), metalObject.getSmeltAmount(input));
                }
            }
        }
        return null;
    }

    // Used by JEI to determine valid inputs and the output
    public Material getMetal() {
        return metal;
    }
}
