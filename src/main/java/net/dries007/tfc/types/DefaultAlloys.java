/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.types;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import net.dries007.tfc.api.recipes.AlloyRecipe;

import static gregtech.api.unification.material.Materials.*;
import static net.dries007.tfc.compat.gregtech.TFCMaterials.*;
import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

@SuppressWarnings("WeakerAccess")
@Mod.EventBusSubscriber(modid = MOD_ID)
public final class DefaultAlloys
{
    @SubscribeEvent
    public static void onRegisterAlloyRecipe(RegistryEvent.Register<AlloyRecipe> event)
    {
        event.getRegistry().registerAll(
            new AlloyRecipe.Builder(BismuthBronze).add(Zinc, 0.2, 0.3).add(Copper, 0.5, 0.65).add(Bismuth, 0.1, 0.2).build(),
            new AlloyRecipe.Builder(BlackBronze).add(Copper, 0.5, 0.7).add(Silver, 0.1, 0.25).add(Gold, 0.1, 0.25).build(),
            new AlloyRecipe.Builder(Bronze).add(Copper, 0.88, 0.92).add(Tin, 0.08, 0.12).build(),
            new AlloyRecipe.Builder(Brass).add(Copper, 0.88, 0.92).add(Zinc, 0.08, 0.12).build(),
            new AlloyRecipe.Builder(RoseGold).add(Copper, 0.15, 0.3).add(Gold, 0.7, 0.85).build(),
            new AlloyRecipe.Builder(SterlingSilver).add(Copper, 0.2, 0.4).add(Silver, 0.6, 0.8).build(),
            new AlloyRecipe.Builder(WeakSteel).add(Steel, 0.5, 0.7).add(Nickel, 0.15, 0.25).add(BlackBronze, 0.15, 0.25).build(),
            new AlloyRecipe.Builder(WeakBlueSteel).add(BlackSteel, 0.5, 0.55).add(Steel, 0.2, 0.25).add(BismuthBronze, 0.1, 0.15).add(SterlingSilver, 0.1, 0.15).build(),
            new AlloyRecipe.Builder(WeakRedSteel).add(BlackSteel, 0.5, 0.55).add(Steel, 0.2, 0.25).add(Brass, 0.1, 0.15).add(RoseGold, 0.1, 0.15).build()
        );
    }
}
