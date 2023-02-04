/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.api.types;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;

public class Wood extends IForgeRegistryEntry.Impl<Wood>
{
    private final boolean canMakeTannin;
    private final float burnTemp;
    private final int burnTicks;
    private final int color;


    /**
     * This is a registry object that will create a number of things:
     * 1. Wood logs, planks, and leaf blocks, and all the respective variants
     * 2. A Tree object to be used in TFC world gen
     *
     * Addon mods that want to add trees should subscribe to the registry event for this class
     * They also must put (in their mod) the required resources in /assets/tfc/...
     *
     * When using this class, use the provided Builder to create your trees. This will require all the default values, as well as
     * provide optional values that you can change
     *
     * @param name             the ResourceLocation registry name of this tree
     * @param burnTemp         the temperature at which this will burn in a fire pit or similar
     * @param burnTicks        the number of ticks that this will burn in a fire pit or similar
     */
    public Wood(@Nonnull ResourceLocation name, boolean canMakeTannin, float burnTemp, int burnTicks, int color)
    {
        this.canMakeTannin = canMakeTannin;
        this.burnTemp = burnTemp;
        this.burnTicks = burnTicks;
        this.color = color;

        setRegistryName(name);
    }

    public boolean canMakeTannin()
    {
        return canMakeTannin;
    }

    public float getBurnTemp()
    {
        return burnTemp;
    }

    public int getBurnTicks()
    {
        return burnTicks;
    }

    public int getColor() { return color; }

    @SuppressWarnings("ConstantConditions")
    @Override
    public String toString()
    {
        return getRegistryName().getPath();
    }

    public static class Builder
    {
        private final ResourceLocation name;
        private boolean canMakeTannin;
        private float burnTemp;
        private int burnTicks;
        private int color;

        public Builder(@Nonnull ResourceLocation name, float burnTemp, int burnTicks)
        {
            this.name = name;
            this.canMakeTannin = false;
            this.burnTemp = burnTemp;
            this.burnTicks = burnTicks;
            this.color = 0xFFFFFF;
        }

        public Builder setTannin()
        {
            canMakeTannin = true;
            return this;
        }

        public Builder setColor(int color)
        {
            this.color = color;
            return this;
        }

        public Wood build()
        {
            return new Wood(name, canMakeTannin, burnTemp, burnTicks, color);
        }
    }
}
