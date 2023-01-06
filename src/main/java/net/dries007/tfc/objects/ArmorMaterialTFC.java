/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects;

import javax.annotation.Nonnull;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

import net.dries007.tfc.api.types.IArmorMaterialTFC;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

/**
 * This is an extension enum for the vanilla's ArmorMaterials.
 * We register a new material in vanilla and bind crushing, slashing and piercing resistances.
 */
public class ArmorMaterialTFC implements IArmorMaterialTFC
{
    // todo tweak all these values
    // currently, modifiers = classic / 40. Should give about 45% resistance(damage = 55%) to red/blue steel before letting vanilla mechanic do the rest.
    // red/blue steel has the same base resistance(eg: the damage you take on generic/the "base" reduction using vanilla mechanics) as vanilla's diamond armor
    // black and normal steel has the same as vanilla's iron armor
    // wrought iron is equivalent to chain mail
    // copper is a little better than vanilla's leather and bronzes are in between wrought iron and copper.

    //LEATHER?
    public static final IArmorMaterialTFC QUIVER = new ArmorMaterialTFC(EnumHelper.addArmorMaterial("quiver", MOD_ID + ":quiver", 10, new int[] {0, 0, 0, 0}, 9, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F), 0, 0, 0f);

    private final float piercingRes, slashingRes, crushingRes;
    private final ArmorMaterial material;

    @SuppressWarnings("WeakerAccess")
    public ArmorMaterialTFC(ArmorMaterial material, float piercingRes, float slashingRes, float crushingRes)
    {
        this.material = material;
        this.crushingRes = crushingRes;
        this.piercingRes = piercingRes;
        this.slashingRes = slashingRes;
    }

    @Override
    public float getCrushingModifier()
    {
        return crushingRes;
    }

    @Override
    public float getPiercingModifier()
    {
        return piercingRes;
    }

    @Override
    public float getSlashingModifier()
    {
        return slashingRes;
    }

    @Override
    @Nonnull
    public ArmorMaterial getMaterial()
    {
        return material;
    }
}
