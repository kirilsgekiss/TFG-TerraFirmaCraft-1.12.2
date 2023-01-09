package net.dries007.tfc.compat.top.providers;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.util.climate.ClimateTFC;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class InfoProvider implements IProbeInfoProvider {

    @Override
    public String getID() {
        return TerraFirmaCraft.MOD_ID + ":info";
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
        int temperature = Math.round(ClimateTFC.getActualTemp(world, data.getPos(), 0));
        int rainfall = Math.round(ClimateTFC.getRainfall(world, data.getPos()));
        probeInfo.text(new TextComponentTranslation("waila.tfc.temperature", temperature).getFormattedText());
        probeInfo.text(new TextComponentTranslation("waila.tfc.rainfall", rainfall).getFormattedText());
    }
}
