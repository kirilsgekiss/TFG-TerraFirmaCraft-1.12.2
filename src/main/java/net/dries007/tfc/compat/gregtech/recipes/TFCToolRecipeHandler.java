package net.dries007.tfc.compat.gregtech.recipes;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.ToolProperty;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;
import gregtech.common.items.ToolItems;
import net.dries007.tfc.compat.gregtech.items.TFCMetaItems;
import net.dries007.tfc.compat.gregtech.items.tools.TFCToolItems;
import net.dries007.tfc.compat.gregtech.oreprefix.TFCOrePrefix;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static gregtech.api.unification.material.properties.PropertyKey.GEM;

public class TFCToolRecipeHandler {
    public static void register()
    {
        // TFCOrePrefix.toolHeadKnife.addProcessingHandler(PropertyKey.TOOL, TFCToolRecipeHandler::processKnifeHead);
        // TFCOrePrefix.toolHeadPropick.addProcessingHandler(PropertyKey.TOOL, TFCToolRecipeHandler::processPropickHead);
        // TFCOrePrefix.toolHeadChisel.addProcessingHandler(PropertyKey.TOOL, TFCToolRecipeHandler::processChiselHead);
        // TFCOrePrefix.toolHeadJavelin.addProcessingHandler(PropertyKey.TOOL, TFCToolRecipeHandler::processJavelinHead);
    }

    /*
    private static void processKnifeHead(OrePrefix toolPrefix, Material material, ToolProperty property) {
        int voltageMultiplier = IToolRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasProperty(GEM)) {
            if (material != Materials.Stone)
                RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                        .input(OrePrefix.ingot, material)
                        .notConsumable(TFCMetaItems.SHAPE_EXTRUDER_KNIFE)
                        .outputs(OreDictUnifier.get(toolPrefix, material))
                        .duration((int) material.getMass() * 2)
                        .EUt(2 * voltageMultiplier)
                        .buildAndRegister();
        }
        else {
            if (material != Materials.Flint)
                RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                        .input(OrePrefix.gem, material)
                        .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.Red))
                        .outputs(OreDictUnifier.get(toolPrefix, material))
                        .duration((int) material.getMass() * 2)
                        .EUt(2 * voltageMultiplier)
                        .buildAndRegister();
        }
    }*/

    /*
    private static void processPropickHead(OrePrefix toolPrefix, Material material, ToolProperty property) {
        int voltageMultiplier = IToolRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasProperty(GEM)) {
            if (material != Materials.Stone)
                RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                        .input(OrePrefix.ingot, material)
                        .notConsumable(TFCMetaItems.SHAPE_EXTRUDER_PROPICK)
                        .outputs(OreDictUnifier.get(toolPrefix, material))
                        .duration((int) material.getMass() * 2)
                        .EUt(2 * voltageMultiplier)
                        .buildAndRegister();
        }
        else {
            if (material != Materials.Flint)
                RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                        .input(OrePrefix.gem, material)
                        .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.Black))
                        .outputs(OreDictUnifier.get(toolPrefix, material))
                        .duration((int) material.getMass() * 2)
                        .EUt(2 * voltageMultiplier)
                        .buildAndRegister();
        }
    }*/

    /*
    private static void processChiselHead(OrePrefix toolPrefix, Material material, ToolProperty property) {
        int voltageMultiplier = IToolRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasProperty(GEM)) {
            if (material != Materials.Stone)
                RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                        .input(OrePrefix.ingot, material)
                        .notConsumable(TFCMetaItems.SHAPE_EXTRUDER_CHISEL)
                        .outputs(OreDictUnifier.get(toolPrefix, material))
                        .duration((int) material.getMass() * 2)
                        .EUt(2 * voltageMultiplier)
                        .buildAndRegister();
        }
        else {
            if (material != Materials.Flint)
                RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                        .input(OrePrefix.gem, material)
                        .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.Lime))
                        .outputs(OreDictUnifier.get(toolPrefix, material))
                        .duration((int) material.getMass() * 2)
                        .EUt(2 * voltageMultiplier)
                        .buildAndRegister();
        }
    }*/

    /*
    private static void processJavelinHead(OrePrefix toolPrefix, Material material, ToolProperty property) {
        int voltageMultiplier = IToolRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasProperty(GEM)) {
            if (material != Materials.Stone)
                RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                        .input(OrePrefix.ingot, material)
                        .notConsumable(TFCMetaItems.SHAPE_EXTRUDER_JAVELIN)
                        .outputs(OreDictUnifier.get(toolPrefix, material))
                        .duration((int) material.getMass() * 2)
                        .EUt(2 * voltageMultiplier)
                        .buildAndRegister();
        }
        else {
            if (material != Materials.Flint)
                RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                        .input(OrePrefix.gem, material)
                        .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.Gray))
                        .outputs(OreDictUnifier.get(toolPrefix, material))
                        .duration((int) material.getMass() * 2)
                        .EUt(2 * voltageMultiplier)
                        .buildAndRegister();
        }
    }*/
}
