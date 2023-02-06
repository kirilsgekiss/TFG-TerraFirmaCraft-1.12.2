package net.dries007.tfc.compat.top.providers;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.dries007.tfc.TFCConfig;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.objects.te.TEPitKiln;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.calendar.TFCCalendar;
import net.dries007.tfc.util.calendar.ICalendar;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class PitKilnProvider implements IProbeInfoProvider {

    @Override
    public String getID() {
        return TerraFirmaCraft.MOD_ID + ":pit_kiln";
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
        TEPitKiln te = Helpers.getTE(world, data.getPos(), TEPitKiln.class);
        if (te != null) {
            boolean isLit = te.isLit();

            if (isLit) {
                long remainingTicks = TFCConfig.Devices.PIT_KILN.ticks - (TFCCalendar.PLAYER_TIME.getTicks() - te.getLitTick());
                switch (TFCConfig.Client.TOOLTIP.timeTooltipMode) {
                    case NONE:
                        break;
                    case TICKS:
                        probeInfo.text(new TextComponentTranslation("waila.tfc.devices.ticks_remaining", remainingTicks).getFormattedText());
                        break;
                    case MINECRAFT_HOURS:
                        long remainingHours = Math.round(remainingTicks / (float) ICalendar.TICKS_IN_HOUR);
                        probeInfo.text(new TextComponentTranslation("waila.tfc.devices.hours_remaining", remainingHours).getFormattedText());
                        break;
                    case REAL_MINUTES:
                        long remainingMinutes = Math.round(remainingTicks / 1200.0f);
                        probeInfo.text(new TextComponentTranslation("waila.tfc.devices.minutes_remaining", remainingMinutes).getFormattedText());
                        break;
                }
            } else {
                int straw = te.getStrawCount();
                int logs = te.getLogCount();
                if (straw == 8 && logs == 8) {
                    probeInfo.text(new TextComponentTranslation("waila.tfc.pitkiln.unlit").getFormattedText());
                } else {
                    if (straw < 8) {
                        probeInfo.text(new TextComponentTranslation("waila.tfc.pitkiln.straw", 8 - straw).getFormattedText());
                    }
                    if (logs < 8) {
                        probeInfo.text(new TextComponentTranslation("waila.tfc.pitkiln.logs", 8 - logs).getFormattedText());
                    }
                }
            }
        }
    }
}
