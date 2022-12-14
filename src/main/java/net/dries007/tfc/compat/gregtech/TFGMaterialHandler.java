package net.dries007.tfc.compat.gregtech;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.ToolProperty;

import static gregtech.api.unification.material.Materials.EXT_METAL;
import static gregtech.api.unification.material.info.MaterialIconSet.SHINY;

import static net.dries007.tfc.compat.gregtech.TFCMaterials.*;

public class TFGMaterialHandler {

    public static void init()
    {
        Unknown = new Material.Builder(9999, "unknown")
                .ingot(1).fluid()
                .color(0x8B4513).iconSet(SHINY)
                .flags(EXT_METAL)
                .fluidPipeProperties(1696, 6, true)
                .fluidTemp(1358)
                .build();


        Materials.Copper.setProperty(PropertyKey.TOOL, new ToolProperty(7f, 1f, 85, 1, false));

    }
}
