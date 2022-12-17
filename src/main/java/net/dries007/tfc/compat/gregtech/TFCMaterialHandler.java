package net.dries007.tfc.compat.gregtech;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.BlastProperty;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.material.properties.ToolProperty;

import static gregtech.api.GTValues.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.Materials.BlackSteel;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.material.info.MaterialIconSet.METALLIC;

import static gregtech.api.unification.material.info.MaterialIconSet.ROUGH;
import static net.dries007.tfc.compat.gregtech.TFCMaterials.*;

public class TFCMaterialHandler {

    public static void init()
    {
        // TODO
        Unknown = new Material.Builder(32000, "unknown")
                .ingot(1).fluid()
                .color(0x8B4513).iconSet(METALLIC)
                .flags(EXT_METAL)
                .fluidPipeProperties(1696, 6, true)
                .fluidTemp(1358)
                .build();

        WeakSteel = new Material.Builder(32001, "weak_steel")
                .ingot().fluid()
                .color(0x808080).iconSet(METALLIC)
                .components(Iron, 1)
                .fluidPipeProperties(1855, 75, true)
                .blastTemp(1000, null, VA[MV], 800)
                .fluidTemp(2046)
                .build();

        WeakBlueSteel = new Material.Builder(32002, "weak_blue_steel")
                .ingot().fluid()
                .color(0x64648C).iconSet(METALLIC)
                .components(RoseGold, 1, Brass, 1, Steel, 2, BlackSteel, 4)
                .blastTemp(1400, BlastProperty.GasTier.LOW, VA[HV], 1000)
                .build();

        WeakRedSteel = new Material.Builder(32003, "weak_red_steel")
                .ingot().fluid()
                .color(0x8C6464).iconSet(METALLIC)
                .components(SterlingSilver, 1, BismuthBronze, 1, Steel, 2, BlackSteel, 4)
                .blastTemp(1300, BlastProperty.GasTier.LOW, VA[HV], 1000)
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

    }
}