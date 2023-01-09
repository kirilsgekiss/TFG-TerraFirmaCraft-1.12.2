package net.dries007.tfc.compat.top.providers;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.capability.heat.Heat;
import net.dries007.tfc.objects.te.TECrucible;
import net.dries007.tfc.util.Helpers;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class CrucibleProvider implements IProbeInfoProvider {

    @Override
    public String getID() {
        return TerraFirmaCraft.MOD_ID + ":crucible";
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
        TECrucible crucible = Helpers.getTE(world, data.getPos(), TECrucible.class);
        if (crucible != null) {
            if (crucible.getAlloy().getAmount() > 0) {
                probeInfo.text(new TextComponentTranslation("waila.tfc.metal.output", crucible.getAlloy().getAmount(), new TextComponentTranslation(crucible.getAlloyResult().getLocalizedName()).getFormattedText()).getFormattedText());
            }
            String heatTooltip = Heat.getTooltip(crucible.getTemperature());
            if (heatTooltip != null) {
                probeInfo.text(heatTooltip);
            }
        }
    }
}
