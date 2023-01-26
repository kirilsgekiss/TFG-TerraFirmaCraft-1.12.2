package net.dries007.tfc.compat.top.providers;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.objects.blocks.wood.BlockSaplingTFC;
import net.dries007.tfc.objects.te.TETickCounter;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.calendar.ICalendar;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class TreeProvider implements IProbeInfoProvider {

    @Override
    public String getID() {
        return TerraFirmaCraft.MOD_ID + ":tree";
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
        IBlockState state = world.getBlockState(data.getPos());
        if (state.getBlock() instanceof BlockSaplingTFC)
        {
            BlockSaplingTFC block = ((BlockSaplingTFC) state.getBlock());
            Tree wood = block.getTree();
            TETickCounter te = Helpers.getTE(world, data.getPos(), TETickCounter.class);
            if (te != null)
            {
                long days = te.getTicksSinceUpdate() / ICalendar.TICKS_IN_DAY;
                float perc = Math.min(0.99F, days / wood.getMinGrowthTime()) * 100;
                String growth = String.format("%d%%", Math.round(perc));
                probeInfo.text(new TextComponentTranslation("waila.tfc.crop.growth", growth).getFormattedText());
            }

        }
    }

}
