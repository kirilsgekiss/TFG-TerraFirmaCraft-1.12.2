package net.dries007.tfc.compat.gregtech;

import gregtech.api.unification.material.info.MaterialFlag;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.properties.PropertyKey;

import static gregtech.api.unification.material.info.MaterialFlags.*;

public class TFCMaterialFlags {

    public static final MaterialFlag GENERATE_LAMP = new MaterialFlag.Builder("generate_lamp")
            .requireProps(PropertyKey.DUST)
            .build();

    public static final MaterialFlag GENERATE_TRAPDOOR = new MaterialFlag.Builder("generate_trapdoor")
            .requireProps(PropertyKey.DUST)
            .build();

    public static final MaterialFlag GENERATE_ANVIL = new MaterialFlag.Builder("generate_anvil")
            .requireProps(PropertyKey.DUST)
            .build();

    public static final MaterialFlag GENERATE_CLADDING = new MaterialFlag.Builder("generate_cladding")
            .requireProps(PropertyKey.DUST)
            .build();
}
