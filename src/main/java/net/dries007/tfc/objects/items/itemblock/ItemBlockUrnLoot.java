package net.dries007.tfc.objects.items.itemblock;

import net.dries007.tfc.api.capability.heat.ItemHeatHandler;
import net.dries007.tfc.api.capability.size.IItemSize;
import net.dries007.tfc.objects.blocks.TFCBlockUrnLoot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ItemBlockUrnLoot extends TFCItemBlock implements IItemSize {
    public ItemBlockUrnLoot(TFCBlockUrnLoot block) {
        super(block);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        // Since this is technically still a pottery item, despite being a block
        return new ItemHeatHandler(nbt, 1.0f, 1599f);
    }
}
