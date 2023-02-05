package net.dries007.tfc.compat.top.providers;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.types.ICrop;
import net.dries007.tfc.objects.blocks.agriculture.TFCBlockCrop;
import net.dries007.tfc.objects.blocks.agriculture.TFCBlockCropDead;
import net.dries007.tfc.objects.te.TECropBase;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.climate.ClimateTFC;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class CropProvider implements IProbeInfoProvider {

    @Override
    public String getID() {
        return TerraFirmaCraft.MOD_ID + ":crop";
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
        IBlockState state = world.getBlockState(data.getPos());
        TECropBase te = Helpers.getTE(world, data.getPos(), TECropBase.class);
        if (state.getBlock() instanceof TFCBlockCrop && te != null) {
            TFCBlockCrop bs = (TFCBlockCrop) state.getBlock();
            ICrop crop = bs.getCrop();

            boolean isWild = state.getValue(TFCBlockCrop.WILD);
            float temp = ClimateTFC.getActualTemp(world, data.getPos(), -te.getLastUpdateTick());
            float rainfall = ChunkDataTFC.getRainfall(world, data.getPos());

            if (isWild) {
                probeInfo.text(new TextComponentTranslation("waila.tfc.crop.wild").getFormattedText());
            } else if (crop.isValidForGrowth(temp, rainfall)) {
                probeInfo.text(new TextComponentTranslation("waila.tfc.crop.growing").getFormattedText());
            } else {
                probeInfo.text(new TextComponentTranslation("waila.tfc.crop.not_growing").getFormattedText());
            }

            int curStage = state.getValue(bs.getStageProperty());
            int maxStage = crop.getMaxStage();

            if (curStage == maxStage) {
                probeInfo.text(new TextComponentTranslation("waila.tfc.crop.growth", new TextComponentTranslation("waila.tfc.crop.mature").getFormattedText()).getFormattedText());
            } else {
                float remainingTicksToGrow = Math.max(0, (crop.getGrowthTicks() * (float) ConfigTFC.General.FOOD.cropGrowthTimeModifier) - te.getTicksSinceUpdate());
                float curStagePerc = 1.0F - remainingTicksToGrow / crop.getGrowthTicks();
                // Don't show 100% since it still needs to check on randomTick to grow
                float totalPerc = Math.min(0.99f, curStagePerc / maxStage + (float) curStage / maxStage) * 100;
                String growth = String.format("%d%%", Math.round(totalPerc));
                probeInfo.text(new TextComponentTranslation("waila.tfc.crop.growth", growth).getFormattedText());
            }
        } else if (state.getBlock() instanceof TFCBlockCropDead) {
            probeInfo.text(new TextComponentTranslation("waila.tfc.crop.dead_crop").getFormattedText());
        }
    }
}
