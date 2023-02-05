package net.dries007.tfc.objects.items.itemblock;

import net.dries007.tfc.api.capability.size.IItemSize;
import net.dries007.tfc.objects.blocks.BlockCrate;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ItemBlockCrate extends TFCItemBlock implements IItemSize {
    public ItemBlockCrate(BlockCrate block) {
        super(block);
    }
}
