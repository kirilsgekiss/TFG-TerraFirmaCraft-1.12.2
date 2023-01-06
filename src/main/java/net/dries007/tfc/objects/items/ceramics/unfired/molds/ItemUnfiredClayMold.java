/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.items.ceramics.unfired.molds;

import java.util.HashMap;

import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.LocalizationUtils;
import net.dries007.tfc.objects.items.ceramics.ItemPottery;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ItemUnfiredClayMold extends ItemPottery
{
    private static final HashMap<OrePrefix, ItemUnfiredClayMold> MAP = new HashMap<>();

    public static ItemUnfiredClayMold get(OrePrefix category)
    {
        return MAP.get(category);
    }

    public final OrePrefix type;

    public ItemUnfiredClayMold(OrePrefix type)
    {
        this.type = type;
        if (MAP.put(type, this) != null)
        {
            throw new IllegalStateException("There can only be one.");
        }
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return LocalizationUtils.format("item.tfc.ceramics.clay.unfired.mold.name", LocalizationUtils.format("item.material.oreprefix." + type.name + ".empty"));
    }
}
