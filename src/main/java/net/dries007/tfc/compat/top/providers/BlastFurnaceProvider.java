package net.dries007.tfc.compat.top.providers;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.capability.heat.Heat;
import net.dries007.tfc.objects.blocks.devices.BlockBlastFurnace;
import net.dries007.tfc.objects.te.TEBlastFurnace;
import net.dries007.tfc.util.Helpers;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class BlastFurnaceProvider implements IProbeInfoProvider {

    @Override
    public String getID() {
        return TerraFirmaCraft.MOD_ID + ":blast_furnace";
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
        TEBlastFurnace blastFurnace = Helpers.getTE(world, data.getPos(), TEBlastFurnace.class);
        if (blastFurnace != null) {
            int chimney = BlockBlastFurnace.getChimneyLevels(blastFurnace.getWorld(), blastFurnace.getPos());
            if (chimney > 0) {
                int maxItems = chimney * 4;
                int oreStacks = blastFurnace.getOreStacks().size();
                int fuelStacks = blastFurnace.getFuelStacks().size();
                float temperature = blastFurnace.getTemperature();
                String heatTooltip = Heat.getTooltip(temperature);
                probeInfo.text(new TextComponentTranslation("waila.tfc.bloomery.ores", oreStacks, maxItems).getFormattedText());
                probeInfo.text(new TextComponentTranslation("waila.tfc.bloomery.fuel", fuelStacks, maxItems).getFormattedText());
                if (heatTooltip != null) {
                    probeInfo.text(heatTooltip);
                }
            } else {
                probeInfo.text(new TextComponentTranslation("waila.tfc.blast_furnace.not_formed").getFormattedText());
            }
        }
    }
}
