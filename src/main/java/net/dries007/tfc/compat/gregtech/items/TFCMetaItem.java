package net.dries007.tfc.compat.gregtech.items;

import net.dries007.tfc.compat.gregtech.items.tools.TFCMetaTool;

public class TFCMetaItem {

    public static TFCMetaItems TFCMetaItem;

    public static void init()
    {
        // Items
        TFCMetaItem = new TFCMetaItems();
        TFCMetaItem.setRegistryName("tfc_meta_item");

        // Tools
        TFCMetaTool tool = new TFCMetaTool();
        tool.setRegistryName("tfc_meta_tool");
    }
}
