package net.dries007.tfc.compat.gregtech.items.tools;

import gregtech.common.tools.ToolBase;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

public class TFCToolTongs extends ToolBase {
    @Override
    public int getToolDamagePerEntityAttack(ItemStack stack) {
        return 16;
    }

    @Override
    public boolean canApplyEnchantment(ItemStack stack, Enchantment enchantment) {
        return false;
    }
}
