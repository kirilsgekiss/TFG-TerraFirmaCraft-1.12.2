package net.dries007.tfc.compat.gregtech.recipes;

import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.ToolProperty;
import gregtech.common.items.MetaItems;
import net.dries007.tfc.compat.gregtech.items.TFCMetaItems;
import net.dries007.tfc.compat.gregtech.oreprefix.TFCOrePrefix;
import gregtech.api.unification.ore.OrePrefix;
import net.dries007.tfc.mixins.gregtech.recipes.IMaterialRecipeHandlerInvoker;

import static gregtech.api.unification.material.properties.PropertyKey.GEM;

public class TFCToolHeadRecipeHandler {
    public static void register()
    {
        TFCOrePrefix.toolHeadSword.addProcessingHandler(PropertyKey.TOOL, TFCToolHeadRecipeHandler::processHeadSword);
        TFCOrePrefix.toolHeadPickaxe.addProcessingHandler(PropertyKey.TOOL, TFCToolHeadRecipeHandler::processHeadPickaxe);
        TFCOrePrefix.toolHeadShovel.addProcessingHandler(PropertyKey.TOOL, TFCToolHeadRecipeHandler::processHeadShovel);
        TFCOrePrefix.toolHeadAxe.addProcessingHandler(PropertyKey.TOOL, TFCToolHeadRecipeHandler::processHeadAxe);
        TFCOrePrefix.toolHeadHoe.addProcessingHandler(PropertyKey.TOOL, TFCToolHeadRecipeHandler::processHeadHoe);
        TFCOrePrefix.toolHeadSense.addProcessingHandler(PropertyKey.TOOL, TFCToolHeadRecipeHandler::processHeadSense);
        TFCOrePrefix.toolHeadFile.addProcessingHandler(PropertyKey.TOOL, TFCToolHeadRecipeHandler::processHeadFile);
        TFCOrePrefix.toolHeadHammer.addProcessingHandler(PropertyKey.TOOL, TFCToolHeadRecipeHandler::processHeadHammer);
        TFCOrePrefix.toolHeadSaw.addProcessingHandler(PropertyKey.TOOL, TFCToolHeadRecipeHandler::processHeadSaw);
        TFCOrePrefix.toolHeadKnife.addProcessingHandler(PropertyKey.TOOL, TFCToolHeadRecipeHandler::processHeadKnife);
        TFCOrePrefix.toolHeadPropick.addProcessingHandler(PropertyKey.TOOL, TFCToolHeadRecipeHandler::processHeadPropick);
        TFCOrePrefix.toolHeadChisel.addProcessingHandler(PropertyKey.TOOL, TFCToolHeadRecipeHandler::processHeadChisel);
        TFCOrePrefix.toolHeadJavelin.addProcessingHandler(PropertyKey.TOOL, TFCToolHeadRecipeHandler::processHeadJavelin);
    }

    private static void processHeadSword(OrePrefix toolPrefix, Material material, ToolProperty property) {
        int voltageMultiplier = IMaterialRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasProperty(GEM))
            RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                    .input(OrePrefix.ingot, material, 2)
                    .notConsumable(TFCMetaItems.SHAPE_EXTRUDER_SWORD)
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
        else
            RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .input(OrePrefix.gem, material, 2)
                    .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.Orange))
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
    }

    private static void processHeadPickaxe(OrePrefix toolPrefix, Material material, ToolProperty property) {
        int voltageMultiplier = IMaterialRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasProperty(GEM))
            RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                    .input(OrePrefix.ingot, material, 3)
                    .notConsumable(TFCMetaItems.SHAPE_EXTRUDER_PICKAXE)
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
        else
            RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .input(OrePrefix.gem, material, 3)
                    .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.Magenta))
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
    }

    private static void processHeadShovel(OrePrefix toolPrefix, Material material, ToolProperty property) {
        int voltageMultiplier = IMaterialRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasProperty(GEM))
            RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                    .input(OrePrefix.ingot, material, 1)
                    .notConsumable(TFCMetaItems.SHAPE_EXTRUDER_SHOVEL)
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
        else
            RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .input(OrePrefix.gem, material, 1)
                    .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.LightBlue))
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
    }

    private static void processHeadAxe(OrePrefix toolPrefix, Material material, ToolProperty property) {
        int voltageMultiplier = IMaterialRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasProperty(GEM))
            RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                    .input(OrePrefix.ingot, material, 3)
                    .notConsumable(TFCMetaItems.SHAPE_EXTRUDER_AXE)
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
        else
            RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .input(OrePrefix.gem, material, 3)
                    .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.Yellow))
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
    }

    private static void processHeadHoe(OrePrefix toolPrefix, Material material, ToolProperty property) {
        int voltageMultiplier = IMaterialRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasProperty(GEM))
            RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                    .input(OrePrefix.ingot, material, 2)
                    .notConsumable(TFCMetaItems.SHAPE_EXTRUDER_HOE)
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
        else
            RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .input(OrePrefix.gem, material, 2)
                    .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.Lime))
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
    }

    private static void processHeadSense(OrePrefix toolPrefix, Material material, ToolProperty property) {
        int voltageMultiplier = IMaterialRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasProperty(GEM))
            RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                    .input(OrePrefix.ingot, material, 3)
                    .notConsumable(TFCMetaItems.SHAPE_EXTRUDER_SENSE)
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
        else
            RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .input(OrePrefix.gem, material, 3)
                    .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.Pink))
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
    }

    private static void processHeadFile(OrePrefix toolPrefix, Material material, ToolProperty property) {
        int voltageMultiplier = IMaterialRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasProperty(GEM))
            RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                    .input(OrePrefix.ingot, material, 2)
                    .notConsumable(TFCMetaItems.SHAPE_EXTRUDER_FILE)
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
        else
            RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .input(OrePrefix.gem, material, 2)
                    .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.Gray))
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
    }

    private static void processHeadHammer(OrePrefix toolPrefix, Material material, ToolProperty property) {
        int voltageMultiplier = IMaterialRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasProperty(GEM))
            RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                    .input(OrePrefix.ingot, material, 6)
                    .notConsumable(TFCMetaItems.SHAPE_EXTRUDER_HAMMER)
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
        else
            RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .input(OrePrefix.gem, material, 6)
                    .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.LightGray))
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
    }

    private static void processHeadSaw(OrePrefix toolPrefix, Material material, ToolProperty property) {
        int voltageMultiplier = IMaterialRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasProperty(GEM))
            RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                    .input(OrePrefix.ingot, material, 2)
                    .notConsumable(TFCMetaItems.SHAPE_EXTRUDER_SAW)
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
        else
            RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .input(OrePrefix.gem, material, 2)
                    .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.Cyan))
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
    }

    private static void processHeadKnife(OrePrefix toolPrefix, Material material, ToolProperty property) {
        int voltageMultiplier = IMaterialRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasProperty(GEM))
            RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                    .input(OrePrefix.ingot, material, 1)
                    .notConsumable(TFCMetaItems.SHAPE_EXTRUDER_KNIFE)
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
        else
            RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .input(OrePrefix.gem, material, 1)
                    .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.Cyan))
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
    }

    private static void processHeadPropick(OrePrefix toolPrefix, Material material, ToolProperty property) {
        int voltageMultiplier = IMaterialRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasProperty(GEM))
            RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                    .input(OrePrefix.ingot, material, 3)
                    .notConsumable(TFCMetaItems.SHAPE_EXTRUDER_PROPICK)
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
        else
            RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .input(OrePrefix.gem, material, 3)
                    .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.Blue))
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
    }

    private static void processHeadChisel(OrePrefix toolPrefix, Material material, ToolProperty property) {
        int voltageMultiplier = IMaterialRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasProperty(GEM))
            RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                    .input(OrePrefix.ingot, material, 2)
                    .notConsumable(TFCMetaItems.SHAPE_EXTRUDER_CHISEL)
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
        else
            RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .input(OrePrefix.gem, material, 2)
                    .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.Brown))
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
    }

    private static void processHeadJavelin(OrePrefix toolPrefix, Material material, ToolProperty property) {
        int voltageMultiplier = IMaterialRecipeHandlerInvoker.invokeGetVoltageMultiplier(material);

        if (!material.hasProperty(GEM))
            RecipeMaps.EXTRUDER_RECIPES.recipeBuilder()
                    .input(OrePrefix.ingot, material, 3)
                    .notConsumable(TFCMetaItems.SHAPE_EXTRUDER_JAVELIN)
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
        else
            RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .input(OrePrefix.gem, material, 3)
                    .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.Green))
                    .outputs(OreDictUnifier.get(toolPrefix, material))
                    .duration((int) material.getMass() * 2)
                    .EUt(2 * voltageMultiplier)
                    .buildAndRegister();
    }
}
