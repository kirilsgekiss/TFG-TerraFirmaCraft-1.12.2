package net.dries007.tfc.compat.gregtech;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.ToolProperty;
import net.dries007.tfc.compat.gregtech.properties.TFCProperty;
import net.dries007.tfc.compat.gregtech.properties.TFCPropertyKey;
import net.dries007.tfc.compat.tfc.TFCMaterialExtended;
import net.dries007.tfc.compat.tfc.TFGUtils;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.Materials.BlackSteel;
import static gregtech.api.unification.material.info.MaterialFlags.GENERATE_PLATE;
import static gregtech.api.unification.material.info.MaterialIconSet.METALLIC;

import static net.dries007.tfc.compat.gregtech.TFCMaterialFlags.*;
import static net.dries007.tfc.compat.gregtech.TFCMaterials.*;

public class TFCMaterialHandler {

    public static void init()
    {
        // TODO
        Unknown = new Material.Builder(32000, "unknown")
                .fluid()
                .color(0x8B4513).iconSet(METALLIC)
                .fluidTemp(1250)
                .build();

        PigIron = new Material.Builder(32001, "pig_iron")
                .ingot().fluid()
                .color(0x8B4513).iconSet(METALLIC)
                .fluidTemp(1535)
                .build();

        HighCarbonSteel = new Material.Builder(32002, "high_carbon_steel")
                .ingot().fluid()
                .color(0x8B4513).iconSet(METALLIC)
                .fluidTemp(1540)
                .build();

        HighCarbonBlackSteel = new Material.Builder(32003, "high_carbon_black_steel")
                .ingot().fluid()
                .color(0x8B4513).iconSet(METALLIC)
                .fluidTemp(1540)
                .build();

        HighCarbonRedSteel = new Material.Builder(32004, "high_carbon_red_steel")
                .ingot().fluid()
                .color(0x8B4513).iconSet(METALLIC)
                .fluidTemp(1540)
                .build();

        HighCarbonBlueSteel = new Material.Builder(32005, "high_carbon_blue_steel")
                .ingot().fluid()
                .color(0x8B4513).iconSet(METALLIC)
                .fluidTemp(1540)
                .build();

        WeakSteel = new Material.Builder(32006, "weak_steel")
                .ingot().fluid()
                .color(0x808080).iconSet(METALLIC)
                .fluidTemp(1540)
                .build();

        WeakBlueSteel = new Material.Builder(32007, "weak_blue_steel")
                .ingot().fluid()
                .color(0x64648C).iconSet(METALLIC)
                .fluidTemp(1540)
                .build();

        WeakRedSteel = new Material.Builder(32008, "weak_red_steel")
                .ingot().fluid()
                .color(0x8C6464).iconSet(METALLIC)
                .fluidTemp(1540)
                .build();

        Breccia = new Material.Builder(32100, "breccia")
                .dust()
                .color(0x706B5F)
                .build();

        Catlinite = new Material.Builder(32101, "catlinite")
                .dust()
                .color(0xB46C62)
                .build();

        Chalk = new Material.Builder(32102, "chalk")
                .dust()
                .color(0xA4A39F)
                .build();

        Chert = new Material.Builder(32103, "chert")
                .dust()
                .color(0x7A6756)
                .build();

        Claystone = new Material.Builder(32104, "claystone")
                .dust()
                .color(0xAF9377)
                .build();

        Conglomerate = new Material.Builder(32105, "conglomerate")
                .dust()
                .color(0xA3977F)
                .build();

        Dacite = new Material.Builder(32106, "dacite")
                .dust()
                .color(0x979797)
                .build();

        Dolomite = new Material.Builder(32107, "dolomite")
                .dust()
                .color(0x515155)
                .build();

        Gabbro = new Material.Builder(32108, "gabbro")
                .dust()
                .color(0x7F8081)
                .build();

        Gneiss = new Material.Builder(32109, "gneiss")
                .dust()
                .color(0x6A6D60)
                .build();

        Komatiite = new Material.Builder(32110, "komatiite")
                .dust()
                .color(0x586455)
                .build();

        Limestone = new Material.Builder(32111, "limestone")
                .dust()
                .color(0xA09885)
                .build();

        Mudstone = new Material.Builder(32112, "mudstone")
                .dust()
                .color(0x938E84)
                .build();

        Novaculite = new Material.Builder(32113, "novaculite")
                .dust()
                .color(0xADA9A1)
                .build();

        Peridotite = new Material.Builder(32114, "peridotite")
                .dust()
                .color(0x565F56)
                .build();

        Phyllite = new Material.Builder(32115, "phyllite")
                .dust()
                .color(0x706B69)
                .build();

        Porphyry = new Material.Builder(32116, "porphyry")
                .dust()
                .color(0x422825)
                .build();

        Rhyolite = new Material.Builder(32117, "rhyolite")
                .dust()
                .color(0x726D69)
                .build();

        Sandstone = new Material.Builder(32118, "sandstone")
                .dust()
                .color(0xBAAE90)
                .build();

        Schist = new Material.Builder(32119, "schist")
                .dust()
                .color(0x6E735C)
                .build();

        Shale = new Material.Builder(32120, "shale")
                .dust()
                .color(0x686567)
                .build();

        SiltStone = new Material.Builder(32121, "siltstone")
                .dust()
                .color(0xA98D79)
                .build();

        Slate = new Material.Builder(32122, "slate")
                .dust()
                .color(0x989287)
                .build();

        // Material changes

        // GTCEu

        Bismuth.setProperty(PropertyKey.ORE, new OreProperty());
        Perlite.setProperty(PropertyKey.ORE, new OreProperty());
        Uvarovite.setProperty(PropertyKey.ORE, new OreProperty());
        Manganese.setProperty(PropertyKey.ORE, new OreProperty());
        Arsenic.setProperty(PropertyKey.ORE, new OreProperty());
        Iridium.setProperty(PropertyKey.ORE, new OreProperty());
        Osmium.setProperty(PropertyKey.ORE, new OreProperty());
        Chrome.setProperty(PropertyKey.ORE, new OreProperty());
        Vanadium.setProperty(PropertyKey.ORE, new OreProperty());
        Antimony.setProperty(PropertyKey.ORE, new OreProperty());
        Rutile.setProperty(PropertyKey.ORE, new OreProperty());
        Silicon.setProperty(PropertyKey.ORE, new OreProperty());
        Uranium238.setProperty(PropertyKey.ORE, new OreProperty());
        Uranium235.setProperty(PropertyKey.ORE, new OreProperty());
        Niobium.setProperty(PropertyKey.ORE, new OreProperty());
        Yttrium.setProperty(PropertyKey.ORE, new OreProperty());
        Gallium.setProperty(PropertyKey.ORE, new OreProperty());
        Titanium.setProperty(PropertyKey.ORE, new OreProperty());
        Borax.setProperty(PropertyKey.ORE, new OreProperty());
        Cadmium.setProperty(PropertyKey.ORE, new OreProperty());
        Caesium.setProperty(PropertyKey.ORE, new OreProperty());
        Samarium.setProperty(PropertyKey.ORE, new OreProperty());
        Cerium.setProperty(PropertyKey.ORE, new OreProperty());
        Lanthanum.setProperty(PropertyKey.ORE, new OreProperty());
        Tungsten.setProperty(PropertyKey.ORE, new OreProperty());
        NaquadahEnriched.setProperty(PropertyKey.ORE, new OreProperty());
        Glowstone.setProperty(PropertyKey.ORE, new OreProperty(1, 1, true));

        Copper.setProperty(PropertyKey.TOOL, new ToolProperty(7f, 1f, 85, 1, false));
        SaltWater.setMaterialRGB(0xFF1F5099);

        Bismuth.addFlags(GENERATE_PLATE);

        // TFC

        // All TFC Materials has USABLE_MATERIALS
        for (TFCMaterialExtended extendedMaterial : TFGUtils.EXTENDED_MATERIALS) {
            extendedMaterial.getMaterial().addFlags(USABLE_MATERIALS);
        }

        // Apply to all TFC materials new property key
        Unknown.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.5F, 1));

        Copper.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.35F, 1080, 1));
        Bismuth.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.35F, 270, 1));
        Lead.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.22F, 930, 1));
        Nickel.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.48F, 1453, 1));
        RoseGold.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.35F, 960, 1));
        Silver.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.48F, 961, 1));
        Tin.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.14F, 230, 1));
        Zinc.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.21F, 420, 1));
        SterlingSilver.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.35F, 900, 1));
        Bronze.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.35F, 950,2));
        BlackBronze.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.35F, 1070,2));
        BismuthBronze.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.35F, 985,2));
        Gold.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.6F, 1060,2));
        PigIron.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.35F,3));
        HighCarbonSteel.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.35F,3));
        WroughtIron.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.35F, 1535,3));
        HighCarbonBlackSteel.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.35F,4));
        Steel.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.35F, 1540,4));
        WeakSteel.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.35F,4));
        Platinum.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.35F, 1730,5));
        BlackSteel.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.35F, 1485,5));
        WeakBlueSteel.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.35F,5));
        WeakRedSteel.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.35F,5));
        HighCarbonBlueSteel.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.35F,5));
        HighCarbonRedSteel.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.35F,5));
        BlueSteel.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.35F, 1540, 6));
        RedSteel.setProperty(TFCPropertyKey.TFC, new TFCProperty(0.35F, 1540, 6));

        // Generate block flags
        Copper.addFlags(GENERATE_ANVIL, GENERATE_LAMP, GENERATE_TRAPDOOR);
        BismuthBronze.addFlags(GENERATE_ANVIL, GENERATE_LAMP, GENERATE_TRAPDOOR);
        Bronze.addFlags(GENERATE_ANVIL, GENERATE_LAMP, GENERATE_TRAPDOOR);
        BlackBronze.addFlags(GENERATE_ANVIL, GENERATE_LAMP, GENERATE_TRAPDOOR);
        WroughtIron.addFlags(GENERATE_ANVIL, GENERATE_LAMP, GENERATE_TRAPDOOR, GENERATE_CLADDING);
        BlackSteel.addFlags(GENERATE_ANVIL, GENERATE_LAMP, GENERATE_TRAPDOOR);
        RedSteel.addFlags(GENERATE_ANVIL, GENERATE_LAMP, GENERATE_TRAPDOOR);
        BlueSteel.addFlags(GENERATE_ANVIL, GENERATE_LAMP, GENERATE_TRAPDOOR);

        Brass.addFlags(GENERATE_LAMP, GENERATE_TRAPDOOR);
        Gold.addFlags(GENERATE_LAMP, GENERATE_TRAPDOOR);
        Lead.addFlags(GENERATE_LAMP, GENERATE_TRAPDOOR);
        Nickel.addFlags(GENERATE_LAMP, GENERATE_TRAPDOOR);
        Platinum.addFlags(GENERATE_LAMP, GENERATE_TRAPDOOR);
        RoseGold.addFlags(GENERATE_LAMP, GENERATE_TRAPDOOR);
        Silver.addFlags(GENERATE_LAMP, GENERATE_TRAPDOOR);
        SterlingSilver.addFlags(GENERATE_LAMP, GENERATE_TRAPDOOR);
        Tin.addFlags(GENERATE_LAMP, GENERATE_TRAPDOOR);
        Zinc.addFlags(GENERATE_LAMP, GENERATE_TRAPDOOR);
    }
}
