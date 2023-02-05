package net.dries007.tfc.compat.top.providers;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.types.IFruitTree;
import net.dries007.tfc.objects.blocks.agriculture.TFCBlockFruitTreeLeaves;
import net.dries007.tfc.objects.blocks.agriculture.TFCBlockFruitTreeSapling;
import net.dries007.tfc.objects.blocks.agriculture.TFCBlockFruitTreeTrunk;
import net.dries007.tfc.objects.te.TETickCounter;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.calendar.CalendarTFC;
import net.dries007.tfc.util.calendar.ICalendar;
import net.dries007.tfc.util.calendar.Month;
import net.dries007.tfc.util.climate.ClimateTFC;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class FruitTreeProvider implements IProbeInfoProvider {

    @Override
    public String getID() {
        return TerraFirmaCraft.MOD_ID + ":fruit_tree";
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
        BlockPos pos = data.getPos();

        IBlockState state = world.getBlockState(data.getPos());
        if (state.getBlock() instanceof TFCBlockFruitTreeLeaves) {
            TFCBlockFruitTreeLeaves block = (TFCBlockFruitTreeLeaves) state.getBlock();
            if (state.getValue(TFCBlockFruitTreeLeaves.HARVESTABLE) && block.getTree().isHarvestMonth(CalendarTFC.CALENDAR_TIME.getMonthOfYear())) {
                if (state.getValue(TFCBlockFruitTreeLeaves.LEAF_STATE) != TFCBlockFruitTreeLeaves.EnumLeafState.FRUIT) {
                    TETickCounter te = Helpers.getTE(world, pos, TETickCounter.class);
                    addInfo(block.getTree(), te, ClimateTFC.getActualTemp(world, pos), ChunkDataTFC.getRainfall(world, pos), probeInfo);
                }
            } else {
                probeInfo.text(new TextComponentTranslation("waila.tfc.agriculture.harvesting_months").getFormattedText());
                for (Month month : Month.values()) {
                    if (block.getTree().isHarvestMonth(month)) {
                        probeInfo.text(TerraFirmaCraft.getProxy().getMonthName(month, true));
                    }
                }
            }
        } else if (state.getBlock() instanceof TFCBlockFruitTreeSapling) {
            TFCBlockFruitTreeSapling block = (TFCBlockFruitTreeSapling) state.getBlock();
            TETickCounter te = Helpers.getTE(world, pos, TETickCounter.class);
            addInfo(block.getTree(), te, ClimateTFC.getActualTemp(world, pos), ChunkDataTFC.getRainfall(world, pos), probeInfo);
        } else if (state.getBlock() instanceof TFCBlockFruitTreeTrunk) {
            // Gets the top most trunk, since that one is the responsible for growth
            IBlockState topMost = state;
            while (world.getBlockState(pos.up()).getBlock() instanceof TFCBlockFruitTreeTrunk) {
                pos = pos.up();
                topMost = world.getBlockState(pos);
            }
            TFCBlockFruitTreeTrunk block = (TFCBlockFruitTreeTrunk) topMost.getBlock();
            TETickCounter te = Helpers.getTE(world, pos, TETickCounter.class);
            addInfo(block.getTree(), te, ClimateTFC.getActualTemp(world, pos), ChunkDataTFC.getRainfall(world, pos), probeInfo);
        }
    }

    private static void addInfo(IFruitTree tree, TETickCounter te, float temperature, float rainfall, IProbeInfo probeInfo) {
        if (tree.isValidForGrowth(temperature, rainfall)) {
            probeInfo.text(new TextComponentTranslation("waila.tfc.crop.growing").getFormattedText());
            if (te != null) {
                long hours = te.getTicksSinceUpdate() / ICalendar.TICKS_IN_HOUR;
                // Don't show 100% since it still needs to check on randomTick to grow
                float perc = Math.min(0.99F, hours / (tree.getGrowthTime() * (float) ConfigTFC.General.FOOD.fruitTreeGrowthTimeModifier)) * 100;
                String growth = String.format("%d%%", Math.round(perc));
                probeInfo.text(new TextComponentTranslation("waila.tfc.crop.growth", growth).getFormattedText());
            }
        } else {
            probeInfo.text(new TextComponentTranslation("waila.tfc.crop.not_growing").getFormattedText());
        }
    }
}
