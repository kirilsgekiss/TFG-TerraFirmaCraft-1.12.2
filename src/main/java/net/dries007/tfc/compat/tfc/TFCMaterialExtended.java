package net.dries007.tfc.compat.tfc;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;

public class TFCMaterialExtended {

    private final Material material;
    private final boolean hasTool;
    private final float heatCapacity;
    private final int materialTier;

    public TFCMaterialExtended(Material material, boolean hasTool, float heatCapacity, int materialTier) {
        this.material = material;
        this.hasTool = hasTool;
        this.heatCapacity = heatCapacity;
        this.materialTier = materialTier;
    }

    public TFCMaterialExtended(Material material, boolean hasTool, int fluidTemp, float heatCapacity, int materialTier) {
        this(material, hasTool, heatCapacity, materialTier);

        material.getProperty(PropertyKey.FLUID).setFluidTemperature(fluidTemp);
    }

    public Material getMaterial() {
        return material;
    }

    public boolean isHasTool() {
        return hasTool;
    }

    public float getHeatCapacity() {
        return heatCapacity;
    }

    public int getMaterialTier() {
        return materialTier;
    }
}
