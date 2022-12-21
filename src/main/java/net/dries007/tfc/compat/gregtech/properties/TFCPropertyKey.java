package net.dries007.tfc.compat.gregtech.properties;

import gregtech.api.unification.material.properties.BlastProperty;
import gregtech.api.unification.material.properties.IMaterialProperty;
import gregtech.api.unification.material.properties.PropertyKey;

public class TFCPropertyKey<T extends IMaterialProperty<T>> {
    public static final PropertyKey<TFCProperty> TFC = new PropertyKey("tfc", TFCProperty.class);
}
