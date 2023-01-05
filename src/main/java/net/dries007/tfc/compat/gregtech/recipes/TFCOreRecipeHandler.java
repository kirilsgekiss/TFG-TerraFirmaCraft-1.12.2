package net.dries007.tfc.compat.gregtech.recipes;

import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.loaders.recipe.handlers.OreRecipeHandler;

import static net.dries007.tfc.compat.gregtech.oreprefix.TFCOrePrefix.*;

public class TFCOreRecipeHandler {

    public static void register()
    {
        oreChunk.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);

        oreRockSalt.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        oreQuartzite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        oreBreccia.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        oreChalk.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        oreChert.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        oreClaystone.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        oreConglomerate.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        oreDacite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        oreDolomite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        oreGabbro.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        oreGneiss.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        oreKomatiite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        oreLimestone.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        oreMudstone.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        oreNovaculite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        orePeridotite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        orePhyllite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        orePorphyry.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        oreRhyolite.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        oreSandstone.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        oreSchist.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        oreShale.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        oreSiltstone.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
        oreSlate.addProcessingHandler(PropertyKey.ORE, OreRecipeHandler::processOre);
    }
}
