package net.dries007.tfc.compat.gregtech;

import gregtech.common.items.MetaItems;
import scala.tools.cmd.Meta;

public class TFGOrePrefixHandler {
    public static void init()
    {
        MetaItems.addOrePrefix(TFGOrePrefix.toolHeadKnife);
        MetaItems.addOrePrefix(TFGOrePrefix.oreChunk);
    }
}
