package net.dries007.tfc.compat.gregtech;

import gregtech.common.items.MetaItems;

public class TFCOrePrefixHandler {
    public static void init()
    {
        MetaItems.addOrePrefix(TFCOrePrefix.toolHeadKnife);
        MetaItems.addOrePrefix(TFCOrePrefix.oreChunk);

        // MetaItems.addOrePrefix(TFCOrePrefix.trapdoorTFC);
        // MetaItems.addOrePrefix(TFCOrePrefix.lampTFC);
        // MetaItems.addOrePrefix(TFCOrePrefix.anvilTFC);
        // MetaItems.addOrePrefix(TFCOrePrefix.claddingTFC);

    }
}
