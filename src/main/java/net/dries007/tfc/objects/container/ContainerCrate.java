package net.dries007.tfc.objects.container;

import net.dries007.tfc.objects.blocks.TFCBlockCrate;
import net.dries007.tfc.objects.inventory.slot.SlotCallback;
import net.dries007.tfc.objects.te.TECrate;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public class ContainerCrate extends ContainerTE<TECrate> implements IButtonHandler {
    public ContainerCrate(InventoryPlayer playerInv, TECrate tile) {
        super(playerInv, tile);
    }

    @Override
    public void onButtonPress(int buttonID, @Nullable NBTTagCompound extraNBT) {
        // Slot will always be 0, extraNBT will be empty
        if (!tile.getWorld().isRemote) {
            TFCBlockCrate.toggleCrateSeal(tile.getWorld(), tile.getPos());
        }
    }

    @Override
    protected void addContainerSlots() {
        IItemHandler inventory = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        if (inventory != null) {
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 5; x++) {
                    addSlotToContainer(new SlotCallback(inventory, x * 3 + y, 34 + x * 18, 19 + y * 18, tile));
                }
            }
        }
    }
}
