package net.dries007.tfc.objects.items.ceramics.unfired.molds;

import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.LocalizationUtils;
import net.dries007.tfc.objects.items.ceramics.ItemPottery;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;

@ParametersAreNonnullByDefault
public class ItemUnfiredStonewareMold extends ItemPottery {
    private static final HashMap<OrePrefix, ItemUnfiredStonewareMold> MAP = new HashMap<>();

    public static ItemUnfiredStonewareMold get(OrePrefix category) {
        return MAP.get(category);
    }

    public final OrePrefix type;

    public ItemUnfiredStonewareMold(OrePrefix type) {
        this.type = type;
        if (MAP.put(type, this) != null) {
            throw new IllegalStateException("There can only be one.");
        }
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return LocalizationUtils.format("item.tfc.ceramics.stoneware.unfired.mold.name", LocalizationUtils.format("item.material.oreprefix." + type.name + ".empty"));
    }
}
