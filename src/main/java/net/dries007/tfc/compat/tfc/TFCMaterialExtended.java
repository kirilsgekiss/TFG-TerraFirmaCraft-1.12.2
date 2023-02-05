package net.dries007.tfc.compat.tfc;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;

public class TFCMaterialExtended {

    private final Material material;

    public TFCMaterialExtended(Material material) {
        this.material = material;
    }

    public TFCMaterialExtended(Material material, int customFluidTemp) {
        this(material);

        material.getProperty(PropertyKey.FLUID).setFluidTemperature(customFluidTemp);
    }

    public Material getMaterial() {
        return material;
    }
}
