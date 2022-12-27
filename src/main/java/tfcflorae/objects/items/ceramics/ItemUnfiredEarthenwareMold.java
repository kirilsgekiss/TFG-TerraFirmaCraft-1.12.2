package tfcflorae.objects.items.ceramics;

import java.util.EnumMap;
import java.util.HashMap;

import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.LocalizationUtils;
import net.dries007.tfc.objects.items.ceramics.ItemPottery;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class ItemUnfiredEarthenwareMold extends ItemPottery
{
    private static final HashMap<OrePrefix, ItemUnfiredEarthenwareMold> MAP = new HashMap<>();

    public static ItemUnfiredEarthenwareMold get(OrePrefix category)
    {
        return MAP.get(category);
    }

    public final OrePrefix type;

    public ItemUnfiredEarthenwareMold(OrePrefix type)
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
        return LocalizationUtils.format("item.tfc.ceramics.unfired.earthenware.mold.name", LocalizationUtils.format("item.material.oreprefix." + type.name + ".empty"));
    }
}
