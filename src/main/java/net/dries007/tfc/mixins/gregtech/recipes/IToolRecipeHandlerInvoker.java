package net.dries007.tfc.mixins.gregtech.recipes;

import gregtech.api.unification.material.Material;
import gregtech.loaders.recipe.handlers.ToolRecipeHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = ToolRecipeHandler.class, remap = false)
public interface IToolRecipeHandlerInvoker {
    /*
    @Invoker
    public static int invokeGetVoltageMultiplier(Material material)
    {
        throw new AssertionError();
    }*/
}
