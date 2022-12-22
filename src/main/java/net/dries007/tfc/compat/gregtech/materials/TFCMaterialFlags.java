package net.dries007.tfc.compat.gregtech.materials;

import gregtech.api.unification.material.info.MaterialFlag;
import gregtech.api.unification.material.properties.PropertyKey;

public class TFCMaterialFlags {

    // All those metals that were in the TFC + can be unmolded
    public static final MaterialFlag TFC_MATERIAL = new MaterialFlag.Builder("tfc_material")
            .requireProps(PropertyKey.FLUID)
            .build();

    public static final MaterialFlag UNUSABLE_IN_TFC = new MaterialFlag.Builder("unusable_in_tfc")
            .requireProps(PropertyKey.FLUID)
            .build();

    public static final MaterialFlag GENERATE_LAMP = new MaterialFlag.Builder("generate_lamp")
            .requireProps(PropertyKey.INGOT)
            .build();

    public static final MaterialFlag GENERATE_TRAPDOOR = new MaterialFlag.Builder("generate_trapdoor")
            .requireProps(PropertyKey.INGOT)
            .build();

    public static final MaterialFlag GENERATE_ANVIL = new MaterialFlag.Builder("generate_anvil")
            .requireProps(PropertyKey.INGOT)
            .build();

    public static final MaterialFlag GENERATE_CLADDING = new MaterialFlag.Builder("generate_cladding")
            .requireProps(PropertyKey.INGOT)
            .build();




}
