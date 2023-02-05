package net.dries007.tfc.objects.items.groundcover;

import mcp.MethodsReturnNonnullByDefault;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.objects.blocks.groundcover.TFCBlockTwig;
import net.dries007.tfc.objects.items.itemblock.TFCItemBlock;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ItemTwig extends TFCItemBlock {
    public ItemTwig(TFCBlockTwig block) {
        super(block);
        OreDictionaryHelper.register(this, "wood");
        OreDictionaryHelper.register(this, "wood_twig");
        OreDictionaryHelper.register(this, "twig");
        OreDictionaryHelper.register(this, "wood_stick");
        OreDictionaryHelper.register(this, "stick");
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
