package net.dries007.tfc.compat.gregtech.properties;

import gregtech.api.unification.material.properties.*;
import net.dries007.tfc.compat.tfc.TFGUtils;

public class TFCProperty implements IMaterialProperty<TFCProperty> {
    private final float materialHeatCapacity;
    private final int materialTier;

    public TFCProperty(float materialHeatCapacity, int materialTier)
    {
        this.materialHeatCapacity = materialHeatCapacity;
        this.materialTier = materialTier;
    }

    public int getMaterialTier() {
        return materialTier;
    }

    public float getMaterialHeatCapacity() {
        return materialHeatCapacity;
    }

    @Override
    public void verifyProperty(MaterialProperties materialProperties) {
        // materialProperties.ensureSet(PropertyKey.INGOT, true);
    }
}
