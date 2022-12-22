package net.dries007.tfc.compat.gregtech;

import gregtech.api.unification.material.info.MaterialFlag;
import gregtech.api.unification.material.properties.PropertyKey;

public class TFCMaterialFlags {

    // All those metals that were in the TFC + can be unmolded
    public static final MaterialFlag USABLE_MATERIALS = new MaterialFlag.Builder("usable_materials")
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
