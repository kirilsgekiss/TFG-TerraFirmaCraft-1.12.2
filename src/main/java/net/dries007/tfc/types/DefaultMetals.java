/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.types;

import gregtech.api.unification.material.Materials;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import net.dries007.tfc.api.recipes.AlloyRecipe;
import net.dries007.tfc.api.registries.TFCRegistryEvent;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.api.types.Ore;
import net.dries007.tfc.objects.ArmorMaterialTFC;
import net.dries007.tfc.objects.ToolMaterialsTFC;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;
import static net.dries007.tfc.api.types.Metal.Tier.*;

@SuppressWarnings("WeakerAccess")
@Mod.EventBusSubscriber(modid = MOD_ID)
public final class DefaultMetals
{
    /*
     * Metals
     */
    public static final ResourceLocation BRASS = new ResourceLocation(MOD_ID, "brass");
    public static final ResourceLocation GOLD = new ResourceLocation(MOD_ID, "gold");
    public static final ResourceLocation WROUGHT_IRON = new ResourceLocation(MOD_ID, "wrought_iron");
    public static final ResourceLocation PIG_IRON = new ResourceLocation(MOD_ID, "pig_iron");
    public static final ResourceLocation STEEL = new ResourceLocation(MOD_ID, "steel");
    public static final ResourceLocation BLACK_STEEL = new ResourceLocation(MOD_ID, "black_steel");
    public static final ResourceLocation BLUE_STEEL = new ResourceLocation(MOD_ID, "blue_steel");
    public static final ResourceLocation RED_STEEL = new ResourceLocation(MOD_ID, "red_steel");
    public static final ResourceLocation WEAK_STEEL = new ResourceLocation(MOD_ID, "weak_steel");
    public static final ResourceLocation WEAK_BLUE_STEEL = new ResourceLocation(MOD_ID, "weak_blue_steel");
    public static final ResourceLocation WEAK_RED_STEEL = new ResourceLocation(MOD_ID, "weak_red_steel");
    public static final ResourceLocation HIGH_CARBON_STEEL = new ResourceLocation(MOD_ID, "high_carbon_steel");
    public static final ResourceLocation HIGH_CARBON_BLUE_STEEL = new ResourceLocation(MOD_ID, "high_carbon_blue_steel");
    public static final ResourceLocation HIGH_CARBON_RED_STEEL = new ResourceLocation(MOD_ID, "high_carbon_red_steel");
    public static final ResourceLocation HIGH_CARBON_BLACK_STEEL = new ResourceLocation(MOD_ID, "high_carbon_black_steel");
    public static final ResourceLocation LIGNITE = new ResourceLocation(MOD_ID, "lignite");


    @SubscribeEvent
    public static void onRegisterAlloyRecipe(RegistryEvent.Register<AlloyRecipe> event)
    {

        event.getRegistry().registerAll(
            new AlloyRecipe.Builder(Materials.BismuthBronze).add(Materials.Zinc, 0.2, 0.3).add(Materials.Copper, 0.5, 0.65).add(Materials.Bismuth, 0.1, 0.2).build()
            /*
                new AlloyRecipe.Builder(BLACK_BRONZE).add(COPPER, 0.5, 0.7).add(SILVER, 0.1, 0.25).add(GOLD, 0.1, 0.25).build(),
            new AlloyRecipe.Builder(BRONZE).add(COPPER, 0.88, 0.92).add(TIN, 0.08, 0.12).build(),
            new AlloyRecipe.Builder(BRASS).add(COPPER, 0.88, 0.92).add(ZINC, 0.08, 0.12).build(),
            new AlloyRecipe.Builder(ROSE_GOLD).add(COPPER, 0.15, 0.3).add(GOLD, 0.7, 0.85).build(),
            new AlloyRecipe.Builder(STERLING_SILVER).add(COPPER, 0.2, 0.4).add(SILVER, 0.6, 0.8).build(),
            new AlloyRecipe.Builder(WEAK_STEEL).add(STEEL, 0.5, 0.7).add(NICKEL, 0.15, 0.25).add(BLACK_BRONZE, 0.15, 0.25).build(),
            new AlloyRecipe.Builder(WEAK_BLUE_STEEL).add(BLACK_STEEL, 0.5, 0.55).add(STEEL, 0.2, 0.25).add(BISMUTH_BRONZE, 0.1, 0.15).add(STERLING_SILVER, 0.1, 0.15).build(),
            new AlloyRecipe.Builder(WEAK_RED_STEEL).add(BLACK_STEEL, 0.5, 0.55).add(STEEL, 0.2, 0.25).add(BRASS, 0.1, 0.15).add(ROSE_GOLD, 0.1, 0.15).build()*/
        );
    }
}
