/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.items.ceramics.fired.molds;

import gregtech.api.fluids.MetaFluids;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.LocalizationUtils;
import net.dries007.tfc.api.capability.IMaterialHandler;
import net.dries007.tfc.api.capability.heat.CapabilityItemHeat;
import net.dries007.tfc.api.capability.heat.Heat;
import net.dries007.tfc.api.capability.heat.ItemHeatHandler;
import net.dries007.tfc.compat.gregtech.materials.properties.TFCPropertyKey;
import net.dries007.tfc.objects.container.CapabilityContainerListener;
import net.dries007.tfc.objects.items.ceramics.ItemPottery;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.FluidTankPropertiesWrapper;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.List;

import static net.minecraftforge.fluids.capability.CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;

@ParametersAreNonnullByDefault
public class ItemClayMold extends ItemPottery {
    private static final HashMap<OrePrefix, ItemClayMold> MAP = new HashMap<>();

    public static ItemClayMold get(OrePrefix itemMold) {
        return MAP.get(itemMold);
    }

    private final OrePrefix type;
    public final int moldCapacity;

    public ItemClayMold(OrePrefix type, int moldCapacity) {
        this.type = type;
        this.moldCapacity = moldCapacity;
        if (MAP.put(type, this) != null) {
            throw new IllegalStateException("There can only be one.");
        }
    }

    public OrePrefix getOrePrefix() {
        return type;
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        IFluidHandler capFluidHandler = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
        if (capFluidHandler instanceof IMaterialHandler) {
            Material material = ((IMaterialHandler) capFluidHandler).getMaterial();
            if (material != null) {
                return LocalizationUtils.format("item.tfc.ceramics.clay.fired.mold.name", LocalizationUtils.format("item.material.oreprefix." + type.name, material.getLocalizedName()));
            }
        }
        return LocalizationUtils.format("item.tfc.ceramics.clay.fired.mold.empty.name", LocalizationUtils.format("item.material.oreprefix." + type.name + ".empty"));
    }

    @Nullable
    @Override
    public NBTTagCompound getNBTShareTag(ItemStack stack) {
        return CapabilityContainerListener.readShareTag(stack);
    }

    @Override
    public void readNBTShareTag(ItemStack stack, @Nullable NBTTagCompound nbt) {
        CapabilityContainerListener.applyShareTag(stack, nbt);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new FilledMoldCapability(nbt);
    }

    @Override
    public boolean canStack(ItemStack stack) {
        IMaterialHandler moldHandler = (IMaterialHandler) stack.getCapability(FLUID_HANDLER_CAPABILITY, null);
        return moldHandler == null || moldHandler.getMaterial() == null;
    }

    // Extends ItemHeatHandler for ease of use
    private class FilledMoldCapability extends ItemHeatHandler implements ICapabilityProvider, IMaterialHandler {
        private final FluidTank tank;
        private IFluidTankProperties[] fluidTankProperties;

        FilledMoldCapability(@Nullable NBTTagCompound nbt) {
            tank = new FluidTank(moldCapacity);

            if (nbt != null) {
                deserializeNBT(nbt);
            }
        }

        @Nullable
        @Override
        public Material getMaterial() {
            return tank.getFluid() != null ? MetaFluids.getMaterialFromFluid(tank.getFluid().getFluid()) : null;
        }

        @Override
        public int getAmount() {
            return tank.getFluidAmount();
        }

        @Override
        public IFluidTankProperties[] getTankProperties() {
            if (fluidTankProperties == null) {
                fluidTankProperties = new IFluidTankProperties[]{new FluidTankPropertiesWrapper(tank)};
            }
            return fluidTankProperties;
        }

        @Override
        public int fill(FluidStack resource, boolean doFill) {
            if (resource != null) {
                Material material = MetaFluids.getMaterialFromFluid(resource.getFluid());
                if (material != null)  // if (metal != null && type.hasMold(metal))
                {
                    int fillAmount = tank.fill(resource, doFill);
                    if (fillAmount == tank.getFluidAmount()) {
                        updateFluidData();
                    }
                    return fillAmount;
                }
            }
            return 0;
        }

        @Nullable
        @Override
        public FluidStack drain(FluidStack resource, boolean doDrain) {
            return getTemperature() >= meltTemp ? tank.drain(resource, doDrain) : null;
        }

        @Nullable
        @Override
        public FluidStack drain(int maxDrain, boolean doDrain) {
            if (getTemperature() > meltTemp) {
                FluidStack stack = tank.drain(maxDrain, doDrain);
                if (tank.getFluidAmount() == 0) {
                    updateFluidData();
                }
                return stack;
            }
            return null;
        }

        @SideOnly(Side.CLIENT)
        @Override
        public void addHeatInfo(@Nonnull ItemStack stack, @Nonnull List<String> text) {
            Material material = getMaterial();
            if (material != null) {
                String desc = TextFormatting.DARK_GREEN + I18n.format(material.getUnlocalizedName()) + ": " + I18n.format("tfc.tooltip.units", getAmount());
                if (isMolten()) {
                    desc += I18n.format("tfc.tooltip.liquid");
                } else {
                    desc += I18n.format("tfc.tooltip.solid");
                }
                text.add(desc);
            }
            IMaterialHandler.super.addHeatInfo(stack, text);
        }

        @Override
        public float getHeatCapacity() {
            return heatCapacity;
        }

        @Override
        public float getMeltTemp() {
            return meltTemp;
        }

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY
                    || capability == CapabilityItemHeat.ITEM_HEAT_CAPABILITY;
        }

        @Nullable
        @Override
        @SuppressWarnings("unchecked")
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            return hasCapability(capability, facing) ? (T) this : null;
        }

        @Override
        @Nonnull
        public NBTTagCompound serializeNBT() {
            NBTTagCompound nbt = new NBTTagCompound();

            // Duplicated from ItemHeatHandler
            if (getTemperature() <= 0) {
                nbt.setLong("ticks", -1);
                nbt.setFloat("heat", 0);
            } else {
                nbt.setLong("ticks", lastUpdateTick);
                nbt.setFloat("heat", temperature);
            }
            return tank.writeToNBT(nbt);
        }

        @Override
        public void deserializeNBT(@Nullable NBTTagCompound nbt) {
            if (nbt != null) {
                temperature = nbt.getFloat("heat");
                lastUpdateTick = nbt.getLong("ticks");
                tank.readFromNBT(nbt);
            }
            updateFluidData();
        }

        private void updateFluidData() {
            updateFluidData(tank.getFluid());
        }

        private void updateFluidData(@Nullable FluidStack fluid) {
            meltTemp = Heat.maxVisibleTemperature();
            heatCapacity = 1;
            if (fluid != null) {
                Material material = MetaFluids.getMaterialFromFluid(fluid.getFluid());
                if (material != null) {
                    meltTemp = material.getFluid().getTemperature();
                    heatCapacity = material.getProperty(TFCPropertyKey.TFC).getMaterialHeatCapacity();
                }
            }
        }


    }
}
