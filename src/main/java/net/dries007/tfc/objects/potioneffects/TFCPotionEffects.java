/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.potioneffects;


import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;
import static net.dries007.tfc.util.Helpers.getNull;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = MOD_ID)
@GameRegistry.ObjectHolder(MOD_ID)
public final class TFCPotionEffects {
    public static final Potion OVERBURDENED = getNull();
    public static final Potion THIRST = getNull();
    public static final Potion FOOD_POISON = getNull();

    @SubscribeEvent
    public static void registerPotionEffects(RegistryEvent.Register<Potion> event) {
        event.getRegistry().registerAll(
                new TFCPotionOverburdened().setRegistryName(MOD_ID, "overburdened"),
                new TFCPotionThirst().setRegistryName(MOD_ID, "thirst"),
                new TFCPotionFoodPoison().setRegistryName(MOD_ID, "food_poison")
        );
    }
}
