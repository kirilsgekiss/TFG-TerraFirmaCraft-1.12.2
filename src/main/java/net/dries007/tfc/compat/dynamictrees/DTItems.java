package net.dries007.tfc.compat.dynamictrees;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class DTItems
{
    public static void register(IForgeRegistry<Item> registry)
    {
        ArrayList<Item> treeItems = new ArrayList<>();
        DTTrees.TFCTrees.forEach(tree -> tree.getRegisterableItems(treeItems));
        registry.registerAll(treeItems.toArray(new Item[treeItems.size()]));
    }

}
