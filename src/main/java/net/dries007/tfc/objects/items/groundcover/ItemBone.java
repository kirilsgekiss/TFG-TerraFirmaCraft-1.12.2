package net.dries007.tfc.objects.items.groundcover;

import mcp.MethodsReturnNonnullByDefault;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.objects.blocks.groundcover.TFCBlockSurfaceBones;
import net.dries007.tfc.objects.items.itemblock.TFCItemBlock;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ItemBone extends TFCItemBlock {
    public ItemBone(TFCBlockSurfaceBones block) {
        super(block);
        OreDictionaryHelper.register(this, "bone");
        OreDictionaryHelper.register(this, "bones");
        OreDictionaryHelper.register(this, "dye_white");
    }

    @Nonnull
    @Override
    public Size getSize(ItemStack stack) {
        return Size.SMALL;
    }

    @Nonnull
    @Override
    public Weight getWeight(ItemStack stack) {
        return Weight.LIGHT;
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return getStackSize(stack);
    }
}
