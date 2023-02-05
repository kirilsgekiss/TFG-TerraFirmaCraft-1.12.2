package net.dries007.tfc.compat.top.providers;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.objects.te.TELamp;
import net.dries007.tfc.util.Helpers;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class LampProvider implements IProbeInfoProvider {

    @Override
    public String getID() {
        return TerraFirmaCraft.MOD_ID + ":lamp";
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
        TELamp te = Helpers.getTE(world, data.getPos(), TELamp.class);
        if (te != null) {
            IFluidHandler fluidHandler = te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
            FluidStack fluid = fluidHandler != null ? fluidHandler.drain(Integer.MAX_VALUE, false) : null;
            if (fluid != null && fluid.amount > 0) {
                probeInfo.text(new TextComponentTranslation("waila.tfc.barrel.contents", fluid.amount, fluid.getLocalizedName()).getFormattedText());
            }
        }
    }
}
