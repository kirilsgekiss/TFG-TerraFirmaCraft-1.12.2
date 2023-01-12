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

    public static MetaItem<?>.MetaValueItem SHAPE_EXTRUDER_SWORD;
    public static MetaItem<?>.MetaValueItem SHAPE_EXTRUDER_PICKAXE;
    public static MetaItem<?>.MetaValueItem SHAPE_EXTRUDER_SHOVEL;
    public static MetaItem<?>.MetaValueItem SHAPE_EXTRUDER_AXE;
    public static MetaItem<?>.MetaValueItem SHAPE_EXTRUDER_HOE;
    public static MetaItem<?>.MetaValueItem SHAPE_EXTRUDER_SENSE;
    public static MetaItem<?>.MetaValueItem SHAPE_EXTRUDER_FILE;
    public static MetaItem<?>.MetaValueItem SHAPE_EXTRUDER_HAMMER;
    public static MetaItem<?>.MetaValueItem SHAPE_EXTRUDER_SAW;
    public static MetaItem<?>.MetaValueItem SHAPE_EXTRUDER_KNIFE;
    public static MetaItem<?>.MetaValueItem SHAPE_EXTRUDER_PROPICK;
    public static MetaItem<?>.MetaValueItem SHAPE_EXTRUDER_CHISEL;
    public static MetaItem<?>.MetaValueItem SHAPE_EXTRUDER_JAVELIN;

    public static MetaItem<?>.MetaValueItem WOODEN_BUCKET_WITH_SALT;
    public static MetaItem<?>.MetaValueItem FLUX;

    @Override
    public void registerSubItems() {
        // Here u can register items and behaviors and other for them
        SHAPE_EXTRUDER_SWORD = addItem(1, "shape.extruder.sword").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
        SHAPE_EXTRUDER_PICKAXE = addItem(2, "shape.extruder.pickaxe").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
        SHAPE_EXTRUDER_SHOVEL = addItem(3, "shape.extruder.shovel").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
        SHAPE_EXTRUDER_AXE = addItem(4, "shape.extruder.axe").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
        SHAPE_EXTRUDER_HOE = addItem(5, "shape.extruder.hoe").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
        SHAPE_EXTRUDER_SENSE = addItem(6, "shape.extruder.sense").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
        SHAPE_EXTRUDER_FILE = addItem(7, "shape.extruder.file").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
        SHAPE_EXTRUDER_HAMMER = addItem(8, "shape.extruder.hammer").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
        SHAPE_EXTRUDER_SAW = addItem(9, "shape.extruder.saw").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
        SHAPE_EXTRUDER_KNIFE = addItem(10, "shape.extruder.knife").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
        SHAPE_EXTRUDER_PROPICK = addItem(11, "shape.extruder.propick").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
        SHAPE_EXTRUDER_CHISEL = addItem(12, "shape.extruder.chisel").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));
        SHAPE_EXTRUDER_JAVELIN = addItem(13, "shape.extruder.javelin").setMaterialInfo(new ItemMaterialInfo(new MaterialStack(Materials.Steel, M * 4)));

        WOODEN_BUCKET_WITH_SALT = addItem(14, "wooden.bucket.with.salt").setMaxStackSize(1);
        FLUX = addItem(15, "flux").addOreDict("dustFlux");
    }
}
