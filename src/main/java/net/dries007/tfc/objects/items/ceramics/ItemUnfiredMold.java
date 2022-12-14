/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.items.ceramics;

import java.util.EnumMap;
import java.util.HashMap;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import net.dries007.tfc.api.types.Metal;

public class ItemUnfiredMold extends ItemPottery
{
    private static final HashMap<OrePrefix, ItemUnfiredMold> MAP = new HashMap<>();

    public static ItemUnfiredMold get(OrePrefix category)
    {
        return MAP.get(category);
    }

    public final OrePrefix type;

    public ItemUnfiredMold(OrePrefix type)
    {
        this.type = type;
        if (MAP.put(type, this) != null)
        {
            throw new IllegalStateException("There can only be one.");
        }
    }
}
