package net.dries007.tfc.compat.top.providers;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.objects.te.TELogPile;
import net.dries007.tfc.util.Helpers;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class LogPileProvider implements IProbeInfoProvider {
    @Override
    public String getID() {
        return TerraFirmaCraft.MOD_ID + ":log_pile";
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
        TELogPile logPile = Helpers.getTE(world, data.getPos(), TELogPile.class);
        if (logPile != null) {
            IItemHandler inventory = logPile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
            ItemStack icon = ItemStack.EMPTY;
            for (int i = 0; i < inventory.getSlots(); i++) {
                ItemStack slotStack = inventory.getStackInSlot(i);
                if (!slotStack.isEmpty()) {
                    if (icon.isEmpty()) {
                        icon = slotStack.copy();
                    } else if (slotStack.isItemEqual(icon)) {
                        icon.grow(slotStack.getCount());
                    }
                }
            }
            probeInfo.item(icon);
        }
    }
}
