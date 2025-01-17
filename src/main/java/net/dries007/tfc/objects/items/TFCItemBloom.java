/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.items;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import net.dries007.tfc.api.capability.forge.CapabilityForgeable;
import net.dries007.tfc.api.capability.forge.ForgeableMeasurableMetalHandler;
import net.dries007.tfc.api.capability.forge.IForgeable;
import net.dries007.tfc.api.capability.forge.IForgeableMeasurableMetal;
import net.dries007.tfc.api.capability.metal.IMetalItem;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.compat.gregtech.materials.TFCMaterials;
import net.dries007.tfc.compat.gregtech.materials.properties.TFCPropertyKey;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public class TFCItemBloom extends TFCItem implements IMetalItem {
    private final boolean meltable;

    public TFCItemBloom(boolean meltable) {
        this.meltable = meltable;
    }

    @Nonnull
    @Override
    public Size getSize(@Nonnull ItemStack stack) {
        return Size.LARGE; // Stored in chests
    }

    @Nonnull
    @Override
    public Weight getWeight(@Nonnull ItemStack stack) {
        return Weight.HEAVY; // Stacksize = 4
    }

    @Nullable
    @Override
    public Material getMetal(ItemStack stack) {
        IForgeable cap = stack.getCapability(CapabilityForgeable.FORGEABLE_CAPABILITY, null);
        if (cap instanceof IForgeableMeasurableMetal) {
            return ((IForgeableMeasurableMetal) cap).getMaterial();
        }
        return TFCMaterials.Unknown;
    }

    @Override
    public int getSmeltAmount(ItemStack stack) {
        IForgeable cap = stack.getCapability(CapabilityForgeable.FORGEABLE_CAPABILITY, null);
        if (cap instanceof IForgeableMeasurableMetal) {
            int amount = ((IForgeableMeasurableMetal) cap).getMetalAmount();
            if (amount > 144) amount = 144;
            return amount;
        }
        return 0;
    }

    @Override
    public boolean canMelt(ItemStack stack) {
        return meltable;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addMetalInfo(ItemStack stack, List<String> text) {
        IForgeable cap = stack.getCapability(CapabilityForgeable.FORGEABLE_CAPABILITY, null);
        if (cap instanceof IForgeableMeasurableMetal) {
            Material material = ((IForgeableMeasurableMetal) cap).getMaterial();
            int metalAmount = ((IForgeableMeasurableMetal) cap).getMetalAmount();

            text.add("");
            text.add(I18n.format("tfc.tooltip.metal", I18n.format(material.getUnlocalizedName())));
            text.add(I18n.format("tfc.tooltip.units", metalAmount));
            text.add(I18n.format("tfc.tooltip.tier", material.getProperty(TFCPropertyKey.TFC).getMaterialTier()));
        }
    }

    @Override
    @Nonnull
    public String getTranslationKey(ItemStack stack) {
        //noinspection ConstantConditions
        return super.getTranslationKey(stack) + "." + getMetal(stack).getUnlocalizedName(); // TODO
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (isInCreativeTab(tab)) {
            for (int i = 144; i <= 576; i += 144) {
                ItemStack stack = new ItemStack(this);
                IForgeable cap = stack.getCapability(CapabilityForgeable.FORGEABLE_CAPABILITY, null);
                if (cap instanceof IForgeableMeasurableMetal) {
                    IForgeableMeasurableMetal handler = (IForgeableMeasurableMetal) cap;
                    handler.setMaterial(Materials.WroughtIron);
                    handler.setMetalAmount(i);
                    items.add(stack);
                }
            }
        }
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        if (nbt == null) {
            return new ForgeableMeasurableMetalHandler(Materials.WroughtIron, 144);
        } else {
            return new ForgeableMeasurableMetalHandler(nbt);
        }
    }
}
