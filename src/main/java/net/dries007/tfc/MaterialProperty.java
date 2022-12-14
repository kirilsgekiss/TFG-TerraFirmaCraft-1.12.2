package net.dries007.tfc;

import gregtech.api.unification.material.Material;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.fluids.properties.FluidProperty;
import net.dries007.tfc.objects.fluids.properties.MetalProperty;

import javax.annotation.Nonnull;

public class MaterialProperty {

    public static final FluidProperty<MetalProperty> METAL = new FluidProperty<>("material");

    private final Material material;

    public MaterialProperty(@Nonnull Material material)
    {
        this.material = material;
    }

    @Nonnull
    public Material getMetal()
    {
        return material;
    }
}
