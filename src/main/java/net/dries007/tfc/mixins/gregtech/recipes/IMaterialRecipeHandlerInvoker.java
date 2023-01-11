package net.dries007.tfc.mixins.gregtech.recipes;

import gregtech.api.unification.material.Material;
import gregtech.loaders.recipe.handlers.MaterialRecipeHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = MaterialRecipeHandler.class, remap = false)
public interface IMaterialRecipeHandlerInvoker {

    @Invoker
    static int invokeGetVoltageMultiplier(Material material) {
        throw new AssertionError();
    }

}
