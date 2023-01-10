package net.dries007.tfc.compat.gregtech.items;

public class TFCMetaItem {

    public static TFCMetaItems TFCMetaItem;

    public static void init()
    {
        // Items
        TFCMetaItem = new TFCMetaItems();
        TFCMetaItem.setRegistryName("tfc_meta_item");
    }
}
