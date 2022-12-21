package net.dries007.tfc.compat.gregtech.properties;

import gregtech.api.unification.material.properties.*;

public class TFCProperty implements IMaterialProperty<TFCProperty> {
    private final float materialHeatCapacity;
    private final int materialTier;
    private final int materialTemp;

    public TFCProperty(float materialHeatCapacity, int materialTier)
    {
        this.materialHeatCapacity = materialHeatCapacity;
        this.materialTier = materialTier;

        materialTemp = -1;
    }

    public TFCProperty(float materialHeatCapacity, int materialTemp, int materialTier)
    {
        this.materialHeatCapacity = materialHeatCapacity;
        this.materialTier = materialTier;
        this.materialTemp = materialTemp;
    }

    public int getMaterialTier() {
        return materialTier;
    }

    public float getMaterialHeatCapacity() {
        return materialHeatCapacity;
    }

    @Override
    public void verifyProperty(MaterialProperties materialProperties) {
        materialProperties.ensureSet(PropertyKey.FLUID, true);

        if (materialTemp != -1)
            materialProperties.getMaterial().getFluid().setTemperature(materialTemp);
    }
}
