package net.dries007.tfc.compat.gregtech.items;

import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.stack.ItemMaterialInfo;
import gregtech.api.unification.stack.MaterialStack;

import static gregtech.api.GTValues.M;

public class TFCMetaItems extends StandardMetaItem {
    public TFCMetaItems() {
        super();
    }

    public static MetaItem<?>.MetaValueItem SHAPE_EXTRUDER_KNIFE;
    public static MetaItem<?>.MetaValueItem SHAPE_EXTRUDER_SENSE;
    public static MetaItem<?>.MetaValueItem SHAPE_EXTRUDER_PROPICK;
    public static MetaItem<?>.MetaValueItem SHAPE_EXTRUDER_CHISEL;
    public static MetaItem<?>.MetaValueItem SHAPE_EXTRUDER_JAVELIN;
    public static MetaItem<?>.MetaValueItem WOODEN_BUCKET_WITH_SALT;

    @Override
    public void registerSubItems() {
        // Here u can register items and behaviors and other for them
        SHAPE_EXTRUDER_KNIFE = addItem(1, "shape.extruder.knife").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
        SHAPE_EXTRUDER_SENSE = addItem(2, "shape.extruder.sense").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
        SHAPE_EXTRUDER_PROPICK = addItem(3, "shape.extruder.propick").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
        SHAPE_EXTRUDER_CHISEL = addItem(4, "shape.extruder.chisel").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
        SHAPE_EXTRUDER_JAVELIN = addItem(5, "shape.extruder.javelin").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));

        WOODEN_BUCKET_WITH_SALT = addItem(6, "wooden.bucket.with.salt").setMaxStackSize(1);
    }
}
