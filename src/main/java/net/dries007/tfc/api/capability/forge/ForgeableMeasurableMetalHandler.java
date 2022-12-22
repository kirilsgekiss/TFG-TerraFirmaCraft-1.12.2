/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.api.capability.forge;

import java.util.List;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import gregtech.api.GregTechAPI;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import net.dries007.tfc.compat.gregtech.properties.TFCPropertyKey;
import net.dries007.tfc.compat.tfc.TFCMaterialExtended;
import net.dries007.tfc.compat.tfc.TFGUtils;
import net.dries007.tfc.compat.gregtech.TFCMaterials;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Extension of forgeable heatable handler for blooms
 */
public class ForgeableMeasurableMetalHandler extends ForgeableHeatableHandler implements IForgeableMeasurableMetal
{
    private int metalAmount;
    private Material material;

    public ForgeableMeasurableMetalHandler(Material material, int metalAmount)
    {
        this.metalAmount = metalAmount;
        this.material = material;
        this.heatCapacity = material.getProperty(TFCPropertyKey.TFC).getMaterialHeatCapacity();
        this.meltTemp = material.getFluid().getTemperature();
    }

    public ForgeableMeasurableMetalHandler(@Nonnull NBTTagCompound nbt)
    {
        this.metalAmount = 0;
        this.material = TFCMaterials.Unknown;
        this.heatCapacity = TFCMaterials.Unknown.getFluid().getTemperature();
        this.meltTemp = material.getProperty(TFCPropertyKey.TFC).getMaterialHeatCapacity();
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

    public Material getMaterial()
    {
        return material;
    }

    public void setMaterial(Material material)
    {
        this.material = material;
    }

    @Override
    @Nonnull
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound nbt = super.serializeNBT();
        nbt.setInteger("metalAmount", metalAmount);
        nbt.setString("metal", material.getUnlocalizedName());
        return nbt;
    }

    @Override
    public void deserializeNBT(@Nullable NBTTagCompound nbt)
    {
        if (nbt != null)
        {
            metalAmount = nbt.getInteger("metalAmount");
            String materialName = nbt.getString("metal");
            this.material = getMaterialFromName(materialName);
            System.out.println(this.material);
            if (this.material == null)
            {
                this.material = TFCMaterials.Unknown;
            }
            this.meltTemp = this.material.getFluid().getTemperature();
            this.heatCapacity = this.material.getProperty(TFCPropertyKey.TFC).getMaterialHeatCapacity();
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

    public Material getMaterialFromName(String name) {
        return GregTechAPI.MaterialRegistry.getAllMaterials().stream()
                .filter(s -> Objects.equals(s.getUnlocalizedName(), name))
                .findFirst().orElse(null);
    }
}
