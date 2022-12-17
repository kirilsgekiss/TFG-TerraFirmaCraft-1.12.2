/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.items.ceramics;

import java.util.HashMap;

import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.LocalizationUtils;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
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

    @Nonnull
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.format("item.tfc.ceramics.unfired.mold.name", I18n.format("item.material.oreprefix." + type.name + ".empty"));
    }
}
