package net.dries007.tfc.compat.gregtech;

import gregtech.common.items.MetaItems;

public class TFCOrePrefixHandler {
    public static void init()
    {
        MetaItems.addOrePrefix(TFCOrePrefix.toolHeadKnife);
        MetaItems.addOrePrefix(TFCOrePrefix.toolHeadPropick);
        MetaItems.addOrePrefix(TFCOrePrefix.toolHeadChisel);
        MetaItems.addOrePrefix(TFCOrePrefix.toolHeadJavelin);
        MetaItems.addOrePrefix(TFCOrePrefix.toolHeadTuyere);
        MetaItems.addOrePrefix(TFCOrePrefix.ingotDouble);
        MetaItems.addOrePrefix(TFCOrePrefix.ingotTriple);
        MetaItems.addOrePrefix(TFCOrePrefix.ingotHex);
        MetaItems.addOrePrefix(TFCOrePrefix.oreChunk);
    }
}
