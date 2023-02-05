/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.items.itemblock;

import net.dries007.tfc.api.capability.heat.ItemHeatHandler;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class ItemBlockHeat extends TFCItemBlock {
    private final float heatCapacity;
    private final float meltingPoint;

    public ItemBlockHeat(Block block, float heatCapacity, float meltingPoint) {
        super(block);

        this.heatCapacity = heatCapacity;
        this.meltingPoint = meltingPoint;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new ItemHeatHandler(nbt, heatCapacity, meltingPoint);
    }
}
