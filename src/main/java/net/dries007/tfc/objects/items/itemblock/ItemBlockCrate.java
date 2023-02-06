package net.dries007.tfc.objects.items.itemblock;

import net.dries007.tfc.api.capability.size.IItemSize;
import net.dries007.tfc.objects.blocks.TFCBlockCrate;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ItemBlockCrate extends TFCItemBlock implements IItemSize {
    public ItemBlockCrate(TFCBlockCrate block) {
        super(block);
    }
}
