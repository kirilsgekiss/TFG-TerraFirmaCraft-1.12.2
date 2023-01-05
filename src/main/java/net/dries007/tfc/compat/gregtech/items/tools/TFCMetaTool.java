package net.dries007.tfc.compat.gregtech.items.tools;

import gregtech.api.items.toolitem.ToolMetaItem;
import net.dries007.tfc.compat.gregtech.items.TFCMetaItems;

public class TFCMetaTool extends ToolMetaItem<ToolMetaItem<?>.MetaToolValueItem>  {
    @Override
    public void registerSubItems() {
        TFCMetaItems.TONGS = (ToolMetaItem<?>.MetaToolValueItem) addItem(0, "tool.tongs").setToolStats(new TFCToolTongs())
                .setFullRepairCost(1)
                .addOreDict("craftingToolTongs");

        TFCMetaItems.CHISEL = (ToolMetaItem<?>.MetaToolValueItem) addItem(1, "tool.chisel")
                .setFullRepairCost(1)
                .addOreDict("toolChisel");

        TFCMetaItems.PROPICK = (ToolMetaItem<?>.MetaToolValueItem) addItem(2, "tool.propick")
                .setFullRepairCost(1)
                .addOreDict("toolPropick");

        TFCMetaItems.JAVELIN = (ToolMetaItem<?>.MetaToolValueItem) addItem(3, "tool.javelin")
                .setFullRepairCost(1)
                .addOreDict("toolJavelin");
    }
}
