package net.dries007.tfc.compat.top.providers;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.objects.blocks.agriculture.TFCBlockBerryBush;
import net.dries007.tfc.objects.te.TETickCounter;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.calendar.CalendarTFC;
import net.dries007.tfc.util.calendar.ICalendar;
import net.dries007.tfc.util.calendar.Month;
import net.dries007.tfc.util.climate.ClimateTFC;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class BerryBushProvider implements IProbeInfoProvider {

    @Override
    public String getID() {
        return TerraFirmaCraft.MOD_ID + ":berry_bush";
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
        IBlockState state = world.getBlockState(data.getPos());
        if (state.getBlock() instanceof TFCBlockBerryBush) {
            TFCBlockBerryBush block = (TFCBlockBerryBush) state.getBlock();
            if (block.getBush().isHarvestMonth(CalendarTFC.CALENDAR_TIME.getMonthOfYear()) && !state.getValue(TFCBlockBerryBush.FRUITING)) {
                float temp = ClimateTFC.getActualTemp(world, data.getPos());
                float rainfall = ChunkDataTFC.getRainfall(world, data.getPos());
                TETickCounter te = Helpers.getTE(world, data.getPos(), TETickCounter.class);
                if (te != null && block.getBush().isValidForGrowth(temp, rainfall)) {
                    long hours = te.getTicksSinceUpdate() / ICalendar.TICKS_IN_HOUR;
                    // Don't show 100% since it still needs to check on randomTick to grow
                    float perc = Math.min(0.99F, hours / (block.getBush().getGrowthTime() * (float) ConfigTFC.General.FOOD.berryBushGrowthTimeModifier)) * 100;
                    String growth = String.format("%d%%", Math.round(perc));
                    probeInfo.text(new TextComponentTranslation("waila.tfc.crop.growth", growth).getFormattedText());
                } else {
                    probeInfo.text(new TextComponentTranslation("waila.tfc.crop.not_growing").getFormattedText());
                }
            } else {
                probeInfo.text(new TextComponentTranslation("waila.tfc.agriculture.harvesting_months").getFormattedText());
                for (Month month : Month.values()) {
                    if (block.getBush().isHarvestMonth(month)) {
                        probeInfo.text(TerraFirmaCraft.getProxy().getMonthName(month, true));
                    }
                }
            }
        }
    }
}
