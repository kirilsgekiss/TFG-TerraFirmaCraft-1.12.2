/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects;

import net.dries007.tfc.api.types.IArmorMaterialTFC;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

import javax.annotation.Nonnull;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

/**
 * This is an extension enum for the vanilla's ArmorMaterials.
 * We register a new material in vanilla and bind crushing, slashing and piercing resistances.
 */
public class TFCArmorMaterial implements IArmorMaterialTFC {
    // todo tweak all these values
    // currently, modifiers = classic / 40. Should give about 45% resistance(damage = 55%) to red/blue steel before letting vanilla mechanic do the rest.
    // red/blue steel has the same base resistance(eg: the damage you take on generic/the "base" reduction using vanilla mechanics) as vanilla's diamond armor
    // black and normal steel has the same as vanilla's iron armor
    // wrought iron is equivalent to chain mail
    // copper is a little better than vanilla's leather and bronzes are in between wrought iron and copper.

    //LEATHER?
    public static final IArmorMaterialTFC QUIVER = new TFCArmorMaterial(EnumHelper.addArmorMaterial("quiver", MOD_ID + ":quiver", 10, new int[]{0, 0, 0, 0}, 9, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F), 0, 0, 0f);

    public static final IArmorMaterialTFC PINEAPPLE_LEATHER = new TFCArmorMaterial(EnumHelper.addArmorMaterial("pineapple_leather", MOD_ID + ":pineapple_leather", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F), 2, 2, 5);
    public static final IArmorMaterialTFC BURLAP_CLOTH = new TFCArmorMaterial(EnumHelper.addArmorMaterial("burlap_cloth", MOD_ID + ":burlap_cloth", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F), 1.5f, 1.5f, 4);
    public static final IArmorMaterialTFC WOOL_CLOTH = new TFCArmorMaterial(EnumHelper.addArmorMaterial("wool_cloth", MOD_ID + ":wool_cloth", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F), 1.5f, 1.5f, 4);
    public static final IArmorMaterialTFC SILK_CLOTH = new TFCArmorMaterial(EnumHelper.addArmorMaterial("silk_cloth", MOD_ID + ":silk_cloth", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F), 1.5f, 1.5f, 4);
    public static final IArmorMaterialTFC SISAL_CLOTH = new TFCArmorMaterial(EnumHelper.addArmorMaterial("sisal_cloth", MOD_ID + ":sisal_cloth", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F), 1.5f, 1.5f, 4);
    public static final IArmorMaterialTFC COTTON_CLOTH = new TFCArmorMaterial(EnumHelper.addArmorMaterial("cotton_cloth", MOD_ID + ":cotton_cloth", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F), 1.5f, 1.5f, 4);
    public static final IArmorMaterialTFC LINEN_CLOTH = new TFCArmorMaterial(EnumHelper.addArmorMaterial("linen_cloth", MOD_ID + ":linen_cloth", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F), 1.5f, 1.5f, 4);
    public static final IArmorMaterialTFC HEMP_CLOTH = new TFCArmorMaterial(EnumHelper.addArmorMaterial("hemp_cloth", MOD_ID + ":hemp_cloth", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F), 1.5f, 1.5f, 4);
    public static final IArmorMaterialTFC YUCCA_CLOTH = new TFCArmorMaterial(EnumHelper.addArmorMaterial("yucca_canvas", MOD_ID + ":yucca_canvas", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F), 1.5f, 1.5f, 4);


    private final float piercingRes, slashingRes, crushingRes;
    private final ArmorMaterial material;

    @SuppressWarnings("WeakerAccess")
    public TFCArmorMaterial(ArmorMaterial material, float piercingRes, float slashingRes, float crushingRes) {
        this.material = material;
        this.crushingRes = crushingRes;
        this.piercingRes = piercingRes;
        this.slashingRes = slashingRes;
    }

    @Override
    public float getCrushingModifier() {
        return crushingRes;
    }

    @Override
    public float getPiercingModifier() {
        return piercingRes;
    }

    @Override
    public float getSlashingModifier() {
        return slashingRes;
    }

    @Override
    @Nonnull
    public ArmorMaterial getMaterial() {
        return material;
    }
}
