package net.dries007.tfc.objects.items;

import net.dries007.tfc.api.capability.food.CapabilityFood;
import net.dries007.tfc.api.capability.food.FoodTrait;
import net.dries007.tfc.api.capability.food.IFood;
import net.dries007.tfc.api.capability.size.CapabilityItemSize;
import net.dries007.tfc.api.capability.size.IItemSize;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.client.TFCGuiHandler;
import net.dries007.tfc.objects.container.CapabilityContainerListener;
import net.dries007.tfc.objects.inventory.capability.ISlotCallback;
import net.dries007.tfc.objects.inventory.slot.SlotCallback;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class TFCItemSack extends TFCItem {
    public TFCItemSack(Object... oreNameParts) {
        for (Object obj : oreNameParts) {
            if (obj instanceof Object[])
                OreDictionaryHelper.register(this, (Object[]) obj);
            else
                OreDictionaryHelper.register(this, obj);
        }
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (!worldIn.isRemote && !playerIn.isSneaking()) {
            TFCGuiHandler.openGui(worldIn, playerIn, TFCGuiHandler.Type.SACK);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

    @Override
    @Nonnull
    public String getTranslationKey(ItemStack stack) {
        return super.getTranslationKey(stack);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (isInCreativeTab(tab)) {
            items.add(new ItemStack(this));
        }
    }

    @Nullable
    @Override
    public NBTTagCompound getNBTShareTag(ItemStack stack) {
        return CapabilityContainerListener.readShareTag(stack);
    }

    @Override
    public void readNBTShareTag(ItemStack stack, @Nullable NBTTagCompound nbt) {
        CapabilityContainerListener.applyShareTag(stack, nbt);
    }

    @Override
    public boolean canStack(ItemStack stack) {
        return false;
    }

    @Nonnull
    @Override
    public Size getSize(ItemStack stack) {
        return Size.HUGE; // Can't be stored in itself
    }

    @Nonnull
    @Override
    public Weight getWeight(ItemStack stack) {
        return Weight.VERY_HEAVY; // Stacksize = 1
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new SackCapability(nbt);
    }

    // Extends ItemStackHandler for ease of use. Duplicates most of ItemHeatHandler functionality
    public class SackCapability extends ItemStackHandler implements ICapabilityProvider, ISlotCallback {
        SackCapability(@Nullable NBTTagCompound nbt) {
            super(4);

            if (nbt != null) {
                deserializeNBT(nbt);
            }
        }

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
        }

        @Nullable
        @Override
        @SuppressWarnings("unchecked")
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            return hasCapability(capability, facing) ? (T) this : null;
        }

        @Override
        public void setStackInSlot(int slot, @Nonnull ItemStack stack) {
            IFood cap = stack.getCapability(CapabilityFood.CAPABILITY, null);
            if (cap != null) {
                CapabilityFood.applyTrait(cap, FoodTrait.PRESERVED);
            }
            super.setStackInSlot(slot, stack);
        }

        @Nonnull
        @Override
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            if (!simulate) {
                IFood cap = stack.getCapability(CapabilityFood.CAPABILITY, null);
                if (cap != null) {
                    CapabilityFood.applyTrait(cap, FoodTrait.PRESERVED);
                }
            }
            return super.insertItem(slot, stack, simulate);
        }

        @Override
        @Nonnull
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            ItemStack stack = super.extractItem(slot, amount, simulate).copy();
            IFood cap = stack.getCapability(CapabilityFood.CAPABILITY, null);
            if (cap != null) {
                CapabilityFood.removeTrait(cap, FoodTrait.PRESERVED);
            }
            return stack;
        }

        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            IItemSize size = CapabilityItemSize.getIItemSize(stack);
            if (size != null) {
                return size.getSize(stack).isSmallerThan(Size.HUGE);
            }
            return false;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagCompound nbt = new NBTTagCompound();

            // Save item data
            nbt.setTag("items", super.serializeNBT());
            return nbt;
        }

        /**
         * This is used for a very unique situation, see #1083
         * By tracing the call path through {@link net.minecraft.inventory.Container#slotClick(int, int, ClickType, EntityPlayer)}, the *only* method that can possibly intercept in that massive chain, for clicking on a slot with a stack is either this one (in which case we handle the previous item stack in the slot which a reference has been obtained to)
         * Thus, we don't actually care about the stack being put in the slot. We do assume that since this stack is being put in the slot, a different stack is being taken out.
         */
        @Override
        public void beforePutStack(SlotCallback slot, @Nonnull ItemStack stack) {
            IFood cap = slot.getStack().getCapability(CapabilityFood.CAPABILITY, null);
            if (cap != null) {
                CapabilityFood.removeTrait(cap, FoodTrait.PRESERVED);
            }
        }
    }
}
