package net.dries007.tfc.compat.gregtech;

import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.ToolProperty;

public class TFGMaterialHandler {

    public static void init()
    {
        Materials.Copper.setProperty(PropertyKey.TOOL, new ToolProperty(7f, 1f, 85, 1, false));
    }
}
