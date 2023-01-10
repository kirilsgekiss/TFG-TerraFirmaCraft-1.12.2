package net.dries007.tfc.mixins.gregtech.recipes;

import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.ToolProperty;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import gregtech.loaders.recipe.handlers.ToolRecipeHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static gregtech.api.unification.material.info.MaterialFlags.NO_WORKING;
import static gregtech.api.unification.material.properties.PropertyKey.GEM;

@Mixin(value = ToolRecipeHandler.class, remap = false)
public class ToolRecipeHandlerMixin {

    /**
    * Disable all flint tool recipes
    * */
    /*
    @Inject(method = "registerFlintToolRecipes", at = @At(value = "HEAD"), remap = false, cancellable = true)
    private static void onRegisterFlintToolRecipes(CallbackInfo ci) {
        ci.cancel();
    }*/

    /**
     * Disable all material(ingot, plate etc) + sticks tool recipes
     * */
    /*
    @Inject(method = "processSimpleToolHead", at = @At(value = "INVOKE", target = "Lgregtech/loaders/recipe/handlers/ToolRecipeHandler;addSimpleToolRecipe(Lgregtech/api/unification/ore/OrePrefix;Lgregtech/api/unification/material/Material;Lgregtech/api/items/toolitem/ToolMetaItem$MetaToolValueItem;Lgregtech/api/unification/stack/UnificationEntry;Lgregtech/api/unification/stack/UnificationEntry;Z[Ljava/lang/Object;)V"), remap = false, cancellable = true)
    private static void onProcessSimpleToolHead(OrePrefix toolPrefix, Material material, ToolMetaItem.MetaToolValueItem toolItem, boolean mirrored, Object[] recipe, CallbackInfo ci) {
        ci.cancel();
    }*/

    /**
     * Allow to make gem axe heads in laser engraver
     * */
    /*
    @Inject(method = "processAxeHead", at = @At(value = "HEAD"), remap = false, cancellable = true)
    private static void onProcessAxeHead(OrePrefix toolPrefix, Material material, ToolProperty property, CallbackInfo ci) {
        processSimpleToolHead(toolPrefix, material, MetaItems.AXE, false, "PIh", "P  ", "f  ");

        int voltageMultiplier = IToolRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasProperty(GEM)) {
            if (material != Materials.Stone)
                RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                        .input(OrePrefix.ingot, material, 3)
                        .notConsumable(MetaItems.SHAPE_EXTRUDER_AXE)
                        .outputs(OreDictUnifier.get(toolPrefix, material))
                        .duration((int) material.getMass() * 2)
                        .EUt(2 * voltageMultiplier)
                        .buildAndRegister();
        }
        else
        {
            if (material != Materials.Flint)
                RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                        .input(OrePrefix.gem, material, 3)
                        .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.Purple))
                        .outputs(OreDictUnifier.get(toolPrefix, material))
                        .duration((int) material.getMass() * 2)
                        .EUt(2 * voltageMultiplier)
                        .buildAndRegister();
        }

        ci.cancel();
    }*/

    /**
     * Allow to make gem file heads in laser engraver
     * */
    /*
    @Inject(method = "processFileHead", at = @At(value = "HEAD"), remap = false, cancellable = true)
    private static void onProcessFileHead(OrePrefix toolPrefix, Material material, ToolProperty property, CallbackInfo ci) {
        processSimpleToolHead(toolPrefix, material, MetaItems.FILE, false," I ", " I ", " fh");

        int voltageMultiplier = IToolRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasProperty(GEM)) {
            if (material != Materials.Stone)
                RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                    .input(OrePrefix.ingot, material, 3)
                    .notConsumable(MetaItems.SHAPE_EXTRUDER_FILE)
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
        }
        else {
            if (material != Materials.Flint)
                RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                        .input(OrePrefix.gem, material, 3)
                        .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.Yellow))
                        .outputs(OreDictUnifier.get(toolPrefix, material))
                        .duration((int) material.getMass() * 2)
                        .EUt(2 * voltageMultiplier)
                        .buildAndRegister();
        }

        ci.cancel();
    }*/

    /**
     * Allow to make gem hammer heads in laser engraver
     * */
    /*
    @Inject(method = "processHammerHead", at = @At(value = "HEAD"), remap = false, cancellable = true)
    private static void onProcessHammerHead(OrePrefix toolPrefix, Material material, ToolProperty property, CallbackInfo ci) {
        int voltageMultiplier = IToolRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasFlag(NO_WORKING)) {
            processSimpleToolHead(toolPrefix, material, MetaItems.HARD_HAMMER, true, "II ", "IIh", "II ");

            if (!material.hasProperty(GEM)) {
                if (material != Materials.Stone)
                    RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                            .input(OrePrefix.ingot, material, 6)
                            .notConsumable(MetaItems.SHAPE_EXTRUDER_HAMMER)
                            .outputs(OreDictUnifier.get(toolPrefix, material))
                            .duration((int) material.getMass() * 2)
                            .EUt(2 * voltageMultiplier)
                            .buildAndRegister();
            }
            else {
                if (material != Materials.Flint)
                    RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                            .input(OrePrefix.gem, material, 6)
                            .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.LightGray))
                            .outputs(OreDictUnifier.get(toolPrefix, material))
                            .duration((int) material.getMass() * 2)
                            .EUt(2 * voltageMultiplier)
                            .buildAndRegister();
            }
        }

        ci.cancel();
    }*/

    /**
     * Allow to make gem hoe heads in laser engraver
     * */
    /*
    @Inject(method = "processHoeHead", at = @At(value = "HEAD"), remap = false, cancellable = true)
    private static void onProcessHoeHead(OrePrefix toolPrefix, Material material, ToolProperty property, CallbackInfo ci) {
        processSimpleToolHead(toolPrefix, material, MetaItems.HOE, false, "PIh", "f  ");

        int voltageMultiplier = IToolRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasProperty(GEM)) {
            if (material != Materials.Stone)
                RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                        .input(OrePrefix.ingot, material, 2)
                        .notConsumable(MetaItems.SHAPE_EXTRUDER_HOE)
                        .outputs(OreDictUnifier.get(toolPrefix, material))
                        .duration((int) material.getMass() * 2)
                        .EUt(2 * voltageMultiplier)
                        .buildAndRegister();
        }
        else {
            if (material != Materials.Flint)
                RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                        .input(OrePrefix.gem, material, 2)
                        .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.Brown))
                        .outputs(OreDictUnifier.get(toolPrefix, material))
                        .duration((int) material.getMass() * 2)
                        .EUt(2 * voltageMultiplier)
                        .buildAndRegister();
        }


        ci.cancel();
    }*/

    /**
     * Allow to make gem pickaxe heads in laser engraver
     * */
    /*
    @Inject(method = "processPickaxeHead", at = @At(value = "HEAD"), remap = false, cancellable = true)
    private static void onProcessPickaxeHead(OrePrefix toolPrefix, Material material, ToolProperty property, CallbackInfo ci) {
        processSimpleToolHead(toolPrefix, material, MetaItems.PICKAXE, false, "PII", "f h");

        int voltageMultiplier = IToolRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasProperty(GEM)) {
            if (material != Materials.Stone)
                RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                        .input(OrePrefix.ingot, material, 3)
                        .notConsumable(MetaItems.SHAPE_EXTRUDER_PICKAXE)
                        .outputs(OreDictUnifier.get(toolPrefix, material))
                        .duration((int) material.getMass() * 2)
                        .EUt(2 * voltageMultiplier)
                        .buildAndRegister();
        }
        else {
            if (material != Materials.Flint)
                RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                        .input(OrePrefix.gem, material, 2)
                        .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.LightBlue))
                        .outputs(OreDictUnifier.get(toolPrefix, material))
                        .duration((int) material.getMass() * 2)
                        .EUt(2 * voltageMultiplier)
                        .buildAndRegister();
        }

        ci.cancel();
    }*/

    /**
     * Allow to make gem saw heads in laser engraver
     * */
    /*
    @Inject(method = "processSawHead", at = @At(value = "HEAD"), remap = false, cancellable = true)
    private static void onProcessSawHead(OrePrefix toolPrefix, Material material, ToolProperty property, CallbackInfo ci) {
        processSimpleToolHead(toolPrefix, material, MetaItems.SAW, false, "PP", "fh");

        int voltageMultiplier = IToolRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasProperty(GEM)) {
            if (material != Materials.Stone)
                RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                        .input(OrePrefix.ingot, material, 2)
                        .notConsumable(MetaItems.SHAPE_EXTRUDER_SAW)
                        .outputs(OreDictUnifier.get(OrePrefix.toolHeadSaw, material))
                        .duration((int) material.getMass() * 2)
                        .EUt(2 * voltageMultiplier)
                        .buildAndRegister();
        }
        else {
            if (material != Materials.Flint)
                RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                        .input(OrePrefix.gem, material, 2)
                        .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.Green))
                        .outputs(OreDictUnifier.get(toolPrefix, material))
                        .duration((int) material.getMass() * 2)
                        .EUt(2 * voltageMultiplier)
                        .buildAndRegister();
        }

        ci.cancel();
    }*/

    /**
     * Allow to make gem sense heads in laser engraver
     * */
    /*
    @Inject(method = "processSenseHead", at = @At(value = "HEAD"), remap = false, cancellable = true)
    private static void onProcessSenseHead(OrePrefix toolPrefix, Material material, ToolProperty property, CallbackInfo ci) {
        int voltageMultiplier = IToolRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasProperty(GEM)) {
            if (material != Materials.Stone)
                RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                        .input(OrePrefix.ingot, material, 3)
                        .notConsumable(new ItemStack(Items.STICK)) // TFGModMetaItems.SHAPE_EXTRUDER_SENSE
                        .outputs(OreDictUnifier.get(toolPrefix, material))
                        .duration((int) material.getMass() * 2)
                        .EUt(2 * voltageMultiplier)
                        .buildAndRegister();
        }
        else {
            if (material != Materials.Flint)
                RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                        .input(OrePrefix.gem, material, 2)
                        .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.Magenta))
                        .outputs(OreDictUnifier.get(toolPrefix, material))
                        .duration((int) material.getMass() * 2)
                        .EUt(2 * voltageMultiplier)
                        .buildAndRegister();
        }

        ci.cancel();
    }*/

    /**
     * Allow to make gem shovel heads in laser engraver
     * */
    /*
    @Inject(method = "processShovelHead", at = @At(value = "HEAD"), remap = false, cancellable = true)
    private static void onProcessShovelHead(OrePrefix toolPrefix, Material material, ToolProperty property, CallbackInfo ci) {
        processSimpleToolHead(toolPrefix, material, MetaItems.SHOVEL, false, "fPh");

        int voltageMultiplier = IToolRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasProperty(GEM)) {
            if (material != Materials.Stone)
                RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                        .input(OrePrefix.ingot, material)
                        .notConsumable(MetaItems.SHAPE_EXTRUDER_SHOVEL)
                        .outputs(OreDictUnifier.get(toolPrefix, material))
                        .duration((int) material.getMass())
                        .EUt(2 * voltageMultiplier)
                        .buildAndRegister();
        }
        else {
            if (material != Materials.Flint)
                RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                        .input(OrePrefix.gem, material)
                        .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.Pink))
                        .outputs(OreDictUnifier.get(toolPrefix, material))
                        .duration((int) material.getMass() * 2)
                        .EUt(2 * voltageMultiplier)
                        .buildAndRegister();
        }

        ci.cancel();
    }*/

    /**
     * Allow to make gem sword heads in laser engraver
     * */
    /*
    @Inject(method = "processSwordHead", at = @At(value = "HEAD"), remap = false, cancellable = true)
    private static void onProcessSwordHead(OrePrefix toolPrefix, Material material, ToolProperty property, CallbackInfo ci) {
        processSimpleToolHead(toolPrefix, material, MetaItems.SWORD, false, " P ", "fPh");

        int voltageMultiplier = IToolRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasProperty(GEM)) {
            if (material != Materials.Stone)
                RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                        .input(OrePrefix.ingot, material, 2)
                        .notConsumable(MetaItems.SHAPE_EXTRUDER_SWORD)
                        .outputs(OreDictUnifier.get(toolPrefix, material))
                        .duration((int) material.getMass() * 2)
                        .EUt(2 * voltageMultiplier)
                        .buildAndRegister();
        }
        else {
            if (material != Materials.Flint)
                RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                        .input(OrePrefix.gem, material, 2)
                        .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.Orange))
                        .outputs(OreDictUnifier.get(toolPrefix, material))
                        .duration((int) material.getMass() * 2)
                        .EUt(2 * voltageMultiplier)
                        .buildAndRegister();
        }

        ci.cancel();
    }*/


    /**
     * Disable workbench recipes for File
     * */
    /*
    @Redirect(method = "processFileHead", at = @At(value = "INVOKE", target = "Lgregtech/loaders/recipe/handlers/ToolRecipeHandler;processSimpleToolHead(Lgregtech/api/unification/ore/OrePrefix;Lgregtech/api/unification/material/Material;Lgregtech/api/items/toolitem/ToolMetaItem$MetaToolValueItem;Z[Ljava/lang/Object;)V"), remap = false)
    private static void onProcessFileHead(OrePrefix toolPrefix, Material material, ToolMetaItem.MetaToolValueItem toolItem, boolean mirrored, Object[] recipe) {}
*/
    /**
     * Disable workbench recipes for Knife
     * */
    /*
    @Redirect(method = "processStick", at = @At(value = "INVOKE", target = "Lgregtech/api/recipes/ModHandler;addShapedRecipe(Ljava/lang/String;Lnet/minecraft/item/ItemStack;[Ljava/lang/Object;)V", ordinal = 0), remap = false)
    private static void onProcessKnifeHead(String regName, ItemStack result, Object[] recipe) {}*/
}
