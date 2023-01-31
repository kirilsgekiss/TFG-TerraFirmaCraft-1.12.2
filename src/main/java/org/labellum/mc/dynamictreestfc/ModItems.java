package org.labellum.mc.dynamictreestfc;

import java.util.ArrayList;

import net.dries007.tfc.types.TFCTrees;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems
{

    public static void preInit() { }

    public static void register(IForgeRegistry<Item> registry)
    {
        ArrayList<Item> treeItems = new ArrayList<>();
        TFCTrees.tfcTrees.forEach(tree -> tree.getRegisterableItems(treeItems));
        registry.registerAll(treeItems.toArray(new Item[treeItems.size()]));
    }

}
