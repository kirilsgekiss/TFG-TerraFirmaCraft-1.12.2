package net.dries007.tfc.compat.gregtech;

import gregtech.api.GTValues;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.BlastProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.ToolProperty;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.Materials.BlackSteel;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.material.info.MaterialIconSet.METALLIC;
import static gregtech.api.unification.material.info.MaterialIconSet.SHINY;

import static net.dries007.tfc.compat.gregtech.TFCMaterials.*;

public class TFGMaterialHandler {

    public static void init()
    {
        // TODO
        Unknown = new Material.Builder(9000, "unknown")
                .ingot(1).fluid()
                .color(0x8B4513).iconSet(METALLIC)
                .flags(EXT_METAL)
                .fluidPipeProperties(1696, 6, true)
                .fluidTemp(1358)
                .build();

        WeakSteel = new Material.Builder(9001, "weak_steel")
                .ingot().fluid()
                .color(0x808080).iconSet(METALLIC)
                .components(Iron, 1)
                .fluidPipeProperties(1855, 75, true)
                .blastTemp(1000, null, VA[MV], 800)
                .fluidTemp(2046)
                .build();

        WeakBlueSteel = new Material.Builder(9002, "weak_blue_steel")
                .ingot().fluid()
                .color(0x64648C).iconSet(METALLIC)
                .flags(EXT_METAL, GENERATE_FRAME, GENERATE_GEAR)
                .components(RoseGold, 1, Brass, 1, Steel, 2, BlackSteel, 4)
                .blastTemp(1400, BlastProperty.GasTier.LOW, VA[HV], 1000)
                .build();

        WeakRedSteel = new Material.Builder(9003, "weak_red_steel")
                .ingot().fluid()
                .color(0x8C6464).iconSet(METALLIC)
                .flags(EXT_METAL, GENERATE_GEAR)
                .components(SterlingSilver, 1, BismuthBronze, 1, Steel, 2, BlackSteel, 4)
                .toolStats(7.0f, 4.5f, 896, 21)
                .blastTemp(1300, BlastProperty.GasTier.LOW, VA[HV], 1000)
                .build();

        Materials.Copper.setProperty(PropertyKey.TOOL, new ToolProperty(7f, 1f, 85, 1, false));

    }
}
