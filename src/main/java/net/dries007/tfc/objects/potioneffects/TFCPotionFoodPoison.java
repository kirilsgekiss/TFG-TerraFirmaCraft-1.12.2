/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.potioneffects;

import net.dries007.tfc.util.TFCDamageSources;
import net.minecraft.entity.EntityLivingBase;

import javax.annotation.Nonnull;

public class TFCPotionFoodPoison extends TFCPotion {
    protected TFCPotionFoodPoison() {
        super(true, 0x90EE90);
        setPotionName("effectsTFC.food_poison");
        setIconIndex(2, 0);
    }

    @Override
    public void performEffect(@Nonnull EntityLivingBase entity, int amplifier) {
        entity.attackEntityFrom(TFCDamageSources.FOOD_POISON, 1.0F * (amplifier + 1));
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return duration % 40 == 0; // 2 secs = damage
    }

    @Override
    public boolean isInstant() {
        return false;
    }
}
