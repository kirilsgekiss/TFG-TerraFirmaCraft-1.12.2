/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public final class ToolMaterialsTFC
{
    // Damage here is for the sword.
    // Stone weapons have 75% the damage of a vanilla's wood sword while red/blue steel is like a diamond sword with sharpess V (3+ dmg)
    // All in-between weapons have an exponential growth (not much steep but still making it worth to upgrade)
    public static final Item.ToolMaterial IGNEOUS_INTRUSIVE = EnumHelper.addToolMaterial("tfc_igneous_intrusive", 1, 60, 7, 2.0f, 5); //Tier 0
    public static final Item.ToolMaterial SEDIMENTARY = EnumHelper.addToolMaterial("tfc_sedimentary", 1, 50, 7, 2.0f, 5);
    public static final Item.ToolMaterial IGNEOUS_EXTRUSIVE = EnumHelper.addToolMaterial("tfc_igneous_extrusive", 1, 70, 6, 2.0f, 5);
    public static final Item.ToolMaterial METAMORPHIC = EnumHelper.addToolMaterial("tfc_metamorphic", 1, 55, 6.5f, 2.0f, 5);

    private ToolMaterialsTFC() {}
}
