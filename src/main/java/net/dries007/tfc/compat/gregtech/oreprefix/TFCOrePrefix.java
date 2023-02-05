package net.dries007.tfc.compat.gregtech.oreprefix;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialIconType;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import net.dries007.tfc.compat.gregtech.materials.TFCMaterialFlags;
import net.dries007.tfc.compat.gregtech.materials.TFCMaterialIconType;
import net.dries007.tfc.compat.gregtech.materials.TFCMaterials;

import java.util.function.Predicate;

import static gregtech.api.GTValues.M;
import static gregtech.api.unification.ore.OrePrefix.Conditions.*;
import static gregtech.api.unification.ore.OrePrefix.Flags.ENABLE_UNIFICATION;
import static net.dries007.tfc.compat.gregtech.oreprefix.TFCOrePrefix.Conditions.hasTFCProperty;

public class TFCOrePrefix {

    public static final OrePrefix toolHeadSword = new OrePrefix("toolHeadSword", M * 2, null, MaterialIconType.toolHeadSword, ENABLE_UNIFICATION, hasToolProperty);
    public static final OrePrefix toolHeadPickaxe = new OrePrefix("toolHeadPickaxe", M * 3, null, MaterialIconType.toolHeadPickaxe, ENABLE_UNIFICATION, hasToolProperty);
    public static final OrePrefix toolHeadShovel = new OrePrefix("toolHeadShovel", M, null, MaterialIconType.toolHeadShovel, ENABLE_UNIFICATION, hasToolProperty);
    public static final OrePrefix toolHeadAxe = new OrePrefix("toolHeadAxe", M * 3, null, MaterialIconType.toolHeadAxe, ENABLE_UNIFICATION, hasToolProperty);
    public static final OrePrefix toolHeadHoe = new OrePrefix("toolHeadHoe", M * 2, null, MaterialIconType.toolHeadHoe, ENABLE_UNIFICATION, hasToolProperty);
    public static final OrePrefix toolHeadSense = new OrePrefix("toolHeadSense", M * 3, null, TFCMaterialIconType.toolHeadSense, ENABLE_UNIFICATION, hasToolProperty);
    public static final OrePrefix toolHeadFile = new OrePrefix("toolHeadFile", M * 2, null, MaterialIconType.toolHeadFile, ENABLE_UNIFICATION, hasNoCraftingToolProperty);
    public static final OrePrefix toolHeadHammer = new OrePrefix("toolHeadHammer", M * 6, null, MaterialIconType.toolHeadHammer, ENABLE_UNIFICATION, hasNoCraftingToolProperty);
    public static final OrePrefix toolHeadSaw = new OrePrefix("toolHeadSaw", M * 2, null, MaterialIconType.toolHeadSaw, ENABLE_UNIFICATION, hasNoCraftingToolProperty);
    public static final OrePrefix toolHeadKnife = new OrePrefix("toolHeadKnife", M, null, TFCMaterialIconType.toolHeadKnife, ENABLE_UNIFICATION, hasToolProperty);
    public static final OrePrefix toolHeadPropick = new OrePrefix("toolHeadPropick", M * 3, null, TFCMaterialIconType.toolHeadPropick, ENABLE_UNIFICATION, hasToolProperty);
    public static final OrePrefix toolHeadChisel = new OrePrefix("toolHeadChisel", M * 2, null, TFCMaterialIconType.toolHeadChisel, ENABLE_UNIFICATION, hasToolProperty);
    public static final OrePrefix toolHeadJavelin = new OrePrefix("toolHeadJavelin", M * 3, null, TFCMaterialIconType.toolHeadJavelin, ENABLE_UNIFICATION, hasToolProperty);
    public static final OrePrefix oreChunk = new OrePrefix("oreChunk", -1, null, TFCMaterialIconType.oreChunk, ENABLE_UNIFICATION, hasOreProperty);

    public static final OrePrefix ingotDouble = new OrePrefix("ingotDouble", M * 2, null, MaterialIconType.ingotDouble, ENABLE_UNIFICATION, hasTFCProperty);
    public static final OrePrefix ingotTriple = new OrePrefix("ingotTriple", M * 3, null, MaterialIconType.ingotTriple, ENABLE_UNIFICATION, hasTFCProperty);
    public static final OrePrefix ingotHex = new OrePrefix("ingotHex", M * 6, null, TFCMaterialIconType.ingotHex, ENABLE_UNIFICATION, hasTFCProperty);

    public static final OrePrefix oreRockSalt = new OrePrefix("oreRockSalt", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix oreSoapstone = new OrePrefix("oreSoapstone", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
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
    public static final OrePrefix oreSiltstone = new OrePrefix("oreSiltStone", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);
    public static final OrePrefix oreSlate = new OrePrefix("oreSlate", -1, null, MaterialIconType.ore, ENABLE_UNIFICATION, hasOreProperty);

    static {
        oreChunk.addSecondaryMaterial(new MaterialStack(Materials.Stone, M));

        oreRockSalt.addSecondaryMaterial(new MaterialStack(Materials.RockSalt, M));
        oreQuartzite.addSecondaryMaterial(new MaterialStack(Materials.Quartzite, M));
        oreBreccia.addSecondaryMaterial(new MaterialStack(TFCMaterials.Breccia, M));
        oreChalk.addSecondaryMaterial(new MaterialStack(TFCMaterials.Chalk, M));
        oreChert.addSecondaryMaterial(new MaterialStack(TFCMaterials.Chert, M));
        oreClaystone.addSecondaryMaterial(new MaterialStack(TFCMaterials.Claystone, M));
        oreConglomerate.addSecondaryMaterial(new MaterialStack(TFCMaterials.Conglomerate, M));
        oreDacite.addSecondaryMaterial(new MaterialStack(TFCMaterials.Dacite, M));
        oreDolomite.addSecondaryMaterial(new MaterialStack(TFCMaterials.Dolomite, M));
        oreGabbro.addSecondaryMaterial(new MaterialStack(TFCMaterials.Gabbro, M));
        oreGneiss.addSecondaryMaterial(new MaterialStack(TFCMaterials.Gneiss, M));
        oreKomatiite.addSecondaryMaterial(new MaterialStack(TFCMaterials.Komatiite, M));
        oreLimestone.addSecondaryMaterial(new MaterialStack(TFCMaterials.Limestone, M));
        oreMudstone.addSecondaryMaterial(new MaterialStack(TFCMaterials.Mudstone, M));
        oreNovaculite.addSecondaryMaterial(new MaterialStack(TFCMaterials.Novaculite, M));
        orePeridotite.addSecondaryMaterial(new MaterialStack(TFCMaterials.Peridotite, M));
        orePhyllite.addSecondaryMaterial(new MaterialStack(TFCMaterials.Phyllite, M));
        orePorphyry.addSecondaryMaterial(new MaterialStack(TFCMaterials.Porphyry, M));
        oreRhyolite.addSecondaryMaterial(new MaterialStack(TFCMaterials.Rhyolite, M));
        oreSandstone.addSecondaryMaterial(new MaterialStack(TFCMaterials.Sandstone, M));
        oreSchist.addSecondaryMaterial(new MaterialStack(TFCMaterials.Schist, M));
        oreShale.addSecondaryMaterial(new MaterialStack(TFCMaterials.Shale, M));
        oreSiltstone.addSecondaryMaterial(new MaterialStack(TFCMaterials.Siltstone, M));
        oreSlate.addSecondaryMaterial(new MaterialStack(TFCMaterials.Slate, M));
    }

    public static class Conditions {
        public static final Predicate<Material> hasTFCProperty = (material) ->
                material.hasProperty(PropertyKey.INGOT) &&
                        material.hasFlag(TFCMaterialFlags.TFC_MATERIAL) &&
                        !material.hasFlag(TFCMaterialFlags.UNUSABLE_IN_TFC);
    }
}
