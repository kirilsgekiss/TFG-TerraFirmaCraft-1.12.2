package net.dries007.tfc.mixins.gregtech.recipes;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.DustProperty;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.loaders.recipe.handlers.MaterialRecipeHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = MaterialRecipeHandler.class, remap = false)
public class MaterialRecipeHandlerMixin {

    /**
     * Disable 2x ingot -> plate recipe generation
     * */
    @Redirect(method = "processIngot", at = @At(value = "INVOKE", target = "Lgregtech/api/recipes/ModHandler;addShapedRecipe(Ljava/lang/String;Lnet/minecraft/item/ItemStack;[Ljava/lang/Object;)V", ordinal = 2), remap = false)
    private static void onProcessIngot(OrePrefix orePrefix, Material material, DustProperty property) {}
}
