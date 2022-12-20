package net.dries007.tfc.compat.tfc;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;

public class TFCMaterialExtended {

    private final Material material;
    private final float heatCapacity;
    private final int materialTier;

    public TFCMaterialExtended(Material material, float heatCapacity, int materialTier) {
        this.material = material;
        this.heatCapacity = heatCapacity;
        this.materialTier = materialTier;
    }

    public TFCMaterialExtended(Material material, int fluidTemp, float heatCapacity, int materialTier) {
        this(material, heatCapacity, materialTier);

        material.getProperty(PropertyKey.FLUID).setFluidTemperature(fluidTemp);
    }

    public Material getMaterial() {
        return material;
    }

    public float getHeatCapacity() {
        return heatCapacity;
    }

    public int getMaterialTier() {
        return materialTier;
    }
}
