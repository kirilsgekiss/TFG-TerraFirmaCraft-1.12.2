/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.api.capability.forge;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import net.dries007.tfc.compat.gregtech.TFCMaterials;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;

/**
 * Extension of forgeable heatable handler for blooms
 */
public class ForgeableMeasurableMetalHandler extends ForgeableHeatableHandler implements IForgeableMeasurableMetal
{
    private int metalAmount;
    private Material metal;

    public ForgeableMeasurableMetalHandler(Material metal, int metalAmount)
    {
        this.metalAmount = metalAmount;
        this.metal = metal;
        this.heatCapacity = 0.35F;
        this.meltTemp = metal.getFluid().getTemperature();
    }

    public ForgeableMeasurableMetalHandler(@Nonnull NBTTagCompound nbt)
    {
        this.metalAmount = 0;
        this.metal = TFCMaterials.Unknown;
        this.heatCapacity = TFCMaterials.Unknown.getFluid().getTemperature();
        this.meltTemp = 0.35F;
        deserializeNBT(nbt);
    }

    public int getMetalAmount()
    {
        return metalAmount;
    }

    public void setMetalAmount(int metalAmount)
    {
        this.metalAmount = metalAmount;
    }

    public Material getMetal()
    {
        return metal;
    }

    public void setMetal(Material metal)
    {
        this.metal = metal;
    }

    @Override
    @Nonnull
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound nbt = super.serializeNBT();
        nbt.setInteger("metalAmount", metalAmount);
        nbt.setString("metal", metal.getUnlocalizedName());
        return nbt;
    }

    @Override
    public void deserializeNBT(@Nullable NBTTagCompound nbt)
    {
        if (nbt != null)
        {
            metalAmount = nbt.getInteger("metalAmount");
            String material = nbt.getString("metal");
            metal = GregTechAPI.MATERIAL_REGISTRY.getObject(material);
            if (metal == null)
            {
                metal = TFCMaterials.Unknown;
            }
            this.meltTemp = metal.getFluid().getTemperature();
            this.heatCapacity = 0.35F; // TODO
        }
        super.deserializeNBT(nbt);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addHeatInfo(@Nonnull ItemStack stack, @Nonnull List<String> text)
    {
        String desc = TextFormatting.WHITE + I18n.format("tfc.tooltip.units", metalAmount);
        text.add(desc);
        super.addHeatInfo(stack, text);
    }
}
