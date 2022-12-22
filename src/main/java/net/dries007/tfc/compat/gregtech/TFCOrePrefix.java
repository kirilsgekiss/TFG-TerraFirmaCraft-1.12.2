package net.dries007.tfc.compat.gregtech;

import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialIconType;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.common.ConfigHolder;
import javafx.beans.property.Property;
import net.dries007.tfc.compat.gregtech.materials.TFCMaterialFlags;
import net.dries007.tfc.compat.gregtech.materials.TFCMaterialIconType;
import net.dries007.tfc.compat.gregtech.materials.TFCMaterials;

import static gregtech.api.GTValues.M;
import static gregtech.api.unification.ore.OrePrefix.Conditions.*;
import static gregtech.api.unification.ore.OrePrefix.Flags.ENABLE_UNIFICATION;

public class TFCOrePrefix {
    public static final OrePrefix toolHeadKnife = new OrePrefix("toolHeadKnife", M, null, TFCMaterialIconType.toolHeadKnife, ENABLE_UNIFICATION, hasToolProperty);
    public static final OrePrefix toolHeadPropick = new OrePrefix("toolHeadPropick", M * 3, null, TFCMaterialIconType.toolHeadPropick, ENABLE_UNIFICATION, hasToolProperty);
    public static final OrePrefix toolHeadChisel = new OrePrefix("toolHeadChisel", M * 2, null, TFCMaterialIconType.toolHeadChisel, ENABLE_UNIFICATION, hasToolProperty);
    public static final OrePrefix toolHeadJavelin = new OrePrefix("toolHeadJavelin", M * 3, null, TFCMaterialIconType.toolHeadJavelin, ENABLE_UNIFICATION, hasToolProperty);
    public static final OrePrefix toolHeadTuyere = new OrePrefix("toolHeadTuyere", M * 6, null, TFCMaterialIconType.toolHeadTuyere, ENABLE_UNIFICATION, hasToolProperty);
    public static final OrePrefix oreChunk = new OrePrefix("oreChunk", -1, null, TFCMaterialIconType.oreChunk, ENABLE_UNIFICATION, hasOreProperty);

    public static final OrePrefix ingotDouble = new OrePrefix("ingotDouble", M * 2, null, MaterialIconType.ingotDouble, ENABLE_UNIFICATION, s -> s.hasProperty(PropertyKey.INGOT) && !s.hasFlag(TFCMaterialFlags.UNUSABLE_IN_TFC));
    public static final OrePrefix ingotTriple = new OrePrefix("ingotTriple", M * 3, null, MaterialIconType.ingotTriple, ENABLE_UNIFICATION, s -> s.hasProperty(PropertyKey.INGOT) && !s.hasFlag(TFCMaterialFlags.UNUSABLE_IN_TFC));
    public static final OrePrefix ingotHex = new OrePrefix("ingotHex", M * 6, null, TFCMaterialIconType.ingotHex, ENABLE_UNIFICATION, s -> s.hasProperty(PropertyKey.INGOT) && !s.hasFlag(TFCMaterialFlags.UNUSABLE_IN_TFC));

    public static final OrePrefix oreRockSalt = new OrePrefix("oreRockSalt", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix oreQuartzite = new OrePrefix("oreQuartzite", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix oreBreccia = new OrePrefix("oreBreccia", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix oreCatlinite = new OrePrefix("oreCatlinite", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix oreChalk = new OrePrefix("oreChalk", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix oreChert = new OrePrefix("oreChert", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix oreClaystone = new OrePrefix("oreClaystone", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix oreConglomerate = new OrePrefix("oreConglomerate", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix oreDacite = new OrePrefix("oreDacite", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix oreDolomite = new OrePrefix("oreDolomite", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix oreGabbro = new OrePrefix("oreGabbro", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix oreGneiss = new OrePrefix("oreGneiss", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix oreKomatiite = new OrePrefix("oreKomatiite", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix oreLimestone = new OrePrefix("oreLimestone", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix oreMudstone = new OrePrefix("oreMudstone", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix oreNovaculite = new OrePrefix("oreNovaculite", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix orePeridotite = new OrePrefix("orePeridotite", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix orePhyllite = new OrePrefix("orePhyllite", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix orePorphyry = new OrePrefix("orePorphyry", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix oreRhyolite = new OrePrefix("oreRhyolite", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix oreSandstone = new OrePrefix("oreSandstone", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix oreSchist = new OrePrefix("oreSchist", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix oreShale = new OrePrefix("oreShale", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix oreSiltStone = new OrePrefix("oreSiltStone", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix oreSlate = new OrePrefix("oreSlate", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);

    static
    {
        oreChunk.addSecondaryMaterial(new MaterialStack(Materials.Stone, M));
        if (ConfigHolder.worldgen.allUniqueStoneTypes) {

            oreRockSalt.addSecondaryMaterial(new MaterialStack(Materials.RockSalt, M));
            //oreRockSalt.addSecondaryMaterial(new MaterialStack(Materials.RockSalt, M));
            oreQuartzite.addSecondaryMaterial(new MaterialStack(Materials.Quartzite, M));
            // oreBreccia.addSecondaryMaterial(new MaterialStack(TFCMaterials.Breccia, M));
            oreChalk.addSecondaryMaterial(new MaterialStack(TFCMaterials.Chalk, M));
            oreChert.addSecondaryMaterial(new MaterialStack(TFCMaterials.Chert, M));
            // oreClaystone.addSecondaryMaterial(new MaterialStack(TFCMaterials.Claystone, 1));
            oreConglomerate.addSecondaryMaterial(new MaterialStack(TFCMaterials.Conglomerate, M));
            oreDacite.addSecondaryMaterial(new MaterialStack(TFCMaterials.Dacite, M));
            oreDolomite.addSecondaryMaterial(new MaterialStack(TFCMaterials.Dolomite, M));
            oreGabbro.addSecondaryMaterial(new MaterialStack(TFCMaterials.Gabbro, M));
            oreGneiss.addSecondaryMaterial(new MaterialStack(TFCMaterials.Gneiss, M));
            // oreKomatiite.addSecondaryMaterial(new MaterialStack(TFCMaterials.Komatiite, M));
            oreLimestone.addSecondaryMaterial(new MaterialStack(TFCMaterials.Limestone, M));
            // oreMudstone.addSecondaryMaterial(new MaterialStack(TFCMaterials.Mudstone, M));
            // oreNovaculite.addSecondaryMaterial(new MaterialStack(TFCMaterials.Novaculite, M));
            // orePeridotite.addSecondaryMaterial(new MaterialStack(TFCMaterials.Peridotite, M));
            orePhyllite.addSecondaryMaterial(new MaterialStack(TFCMaterials.Phyllite, M));
            // orePorphyry.addSecondaryMaterial(new MaterialStack(TFCMaterials.Porphyry, M));
            oreRhyolite.addSecondaryMaterial(new MaterialStack(TFCMaterials.Rhyolite, M));
            // oreSandstone.addSecondaryMaterial(new MaterialStack(TFCMaterials.Sandstone, M));
            oreSchist.addSecondaryMaterial(new MaterialStack(TFCMaterials.Schist, M));
            oreShale.addSecondaryMaterial(new MaterialStack(TFCMaterials.Shale, M));
            // oreSiltStone.addSecondaryMaterial(new MaterialStack(TFCMaterials.SiltStone, 1));
            oreSlate.addSecondaryMaterial(new MaterialStack(TFCMaterials.Slate, M));
        }
    }
}
