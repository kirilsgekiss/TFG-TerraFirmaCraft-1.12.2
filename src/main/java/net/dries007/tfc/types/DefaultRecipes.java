/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.types;

import gregtech.api.GregTechAPI;
import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import gregtech.common.items.ToolItems;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.capability.forge.CapabilityForgeable;
import net.dries007.tfc.api.capability.forge.IForgeable;
import net.dries007.tfc.api.capability.forge.IForgeableMeasurableMetal;
import net.dries007.tfc.api.recipes.*;
import net.dries007.tfc.api.recipes.anvil.AnvilRecipe;
import net.dries007.tfc.api.recipes.anvil.AnvilRecipeMeasurable;
import net.dries007.tfc.api.recipes.anvil.AnvilRecipeSplitting;
import net.dries007.tfc.api.recipes.barrel.*;
import net.dries007.tfc.api.recipes.heat.HeatRecipe;
import net.dries007.tfc.api.recipes.heat.HeatRecipeMetalMelting;
import net.dries007.tfc.api.recipes.heat.HeatRecipeSimple;
import net.dries007.tfc.api.recipes.heat.HeatRecipeVessel;
import net.dries007.tfc.api.recipes.knapping.KnappingRecipe;
import net.dries007.tfc.api.recipes.knapping.KnappingRecipeSimple;
import net.dries007.tfc.api.recipes.knapping.KnappingRecipeStone;
import net.dries007.tfc.api.recipes.knapping.KnappingType;
import net.dries007.tfc.api.recipes.quern.QuernRecipe;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.types.Rock.Type;
import net.dries007.tfc.api.types.Wood;
import net.dries007.tfc.compat.gregtech.items.TFCMetaItems;
import net.dries007.tfc.compat.gregtech.items.tools.TFCToolItems;
import net.dries007.tfc.compat.gregtech.materials.TFCMaterialFlags;
import net.dries007.tfc.compat.gregtech.materials.TFCMaterials;
import net.dries007.tfc.compat.gregtech.materials.properties.TFCPropertyKey;
import net.dries007.tfc.compat.gregtech.oreprefix.TFCOrePrefix;
import net.dries007.tfc.compat.tfc.TFCOrePrefixExtended;
import net.dries007.tfc.compat.tfc.TFGUtils;
import net.dries007.tfc.objects.blocks.TFCBlocks;
import net.dries007.tfc.objects.blocks.plants.TFCBlockPlant;
import net.dries007.tfc.objects.blocks.rock.TFCBlockRockDecorative;
import net.dries007.tfc.objects.blocks.rock.TFCBlockRockSlab;
import net.dries007.tfc.objects.blocks.rock.TFCBlockRockStairs;
import net.dries007.tfc.objects.blocks.rock.TFCBlockRockVariant;
import net.dries007.tfc.objects.blocks.wood.*;
import net.dries007.tfc.objects.fluids.TFCFluids;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.objects.inventory.ingredient.IngredientFluidItem;
import net.dries007.tfc.objects.inventory.ingredient.IngredientItemFood;
import net.dries007.tfc.objects.items.TFCItemAnimalHide;
import net.dries007.tfc.objects.items.TFCItems;
import net.dries007.tfc.objects.items.ceramics.fired.molds.ItemClayMold;
import net.dries007.tfc.objects.items.ceramics.unfired.molds.ItemUnfiredClayMold;
import net.dries007.tfc.objects.items.ceramics.unfired.molds.ItemUnfiredEarthenwareMold;
import net.dries007.tfc.objects.items.ceramics.unfired.molds.ItemUnfiredKaoliniteMold;
import net.dries007.tfc.objects.items.ceramics.unfired.molds.ItemUnfiredStonewareMold;
import net.dries007.tfc.objects.items.food.TFCItemFood;
import net.dries007.tfc.objects.items.rock.ItemRock;
import net.dries007.tfc.objects.items.rock.ItemUnfiredMudBrick;
import net.dries007.tfc.objects.items.rock.TFCItemBrick;
import net.dries007.tfc.objects.items.wood.TFCItemBoat;
import net.dries007.tfc.objects.items.wood.TFCItemLumber;
import net.dries007.tfc.objects.recipes.StickBundleRecipe;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.dries007.tfc.util.agriculture.Food;
import net.dries007.tfc.util.calendar.ICalendar;
import net.dries007.tfc.util.fuel.FuelManager;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;

import static gregtech.api.recipes.RecipeMaps.*;
import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;
import static net.dries007.tfc.compat.gregtech.materials.TFCMaterialFlags.UNUSABLE_IN_TFC;
import static net.dries007.tfc.objects.fluids.TFCFluids.*;
import static net.dries007.tfc.util.forge.ForgeRule.*;
import static net.dries007.tfc.util.skills.SmithingSkill.Type.*;

@Mod.EventBusSubscriber(modid = MOD_ID)
public final class DefaultRecipes
{
    public static void register()
    {
        fixStoneToolsRecipes();
        fixFlintToolsRecipes();

        registerStoneRecipes();
        registerWoodRecipes();
        // registerFruitTreeRecipes();

        registerKnappingRecipes();
        registerAnvilRecipes();
        registerWeldingRecipes();
    }

    private static void fixStoneToolsRecipes() {
        GTRecipeHandler.removeRecipesByInputs(MACERATOR_RECIPES, OreDictUnifier.get(TFCOrePrefix.toolHeadSword, Materials.Flint));
        GTRecipeHandler.removeRecipesByInputs(MACERATOR_RECIPES, OreDictUnifier.get(TFCOrePrefix.toolHeadPickaxe, Materials.Flint));
        GTRecipeHandler.removeRecipesByInputs(MACERATOR_RECIPES, OreDictUnifier.get(TFCOrePrefix.toolHeadSense, Materials.Flint));
        GTRecipeHandler.removeRecipesByInputs(MACERATOR_RECIPES, OreDictUnifier.get(TFCOrePrefix.toolHeadPropick, Materials.Flint));
        GTRecipeHandler.removeRecipesByInputs(MACERATOR_RECIPES, OreDictUnifier.get(TFCOrePrefix.toolHeadChisel, Materials.Flint));
        GTRecipeHandler.removeRecipesByInputs(MACERATOR_RECIPES, OreDictUnifier.get(TFCOrePrefix.toolHeadJavelin, Materials.Flint));
    }

    private static void fixFlintToolsRecipes() {
        GTRecipeHandler.removeRecipesByInputs(MACERATOR_RECIPES, OreDictUnifier.get(TFCOrePrefix.toolHeadSword, Materials.Stone));
        GTRecipeHandler.removeRecipesByInputs(MACERATOR_RECIPES, OreDictUnifier.get(TFCOrePrefix.toolHeadPickaxe, Materials.Stone));
        GTRecipeHandler.removeRecipesByInputs(MACERATOR_RECIPES, OreDictUnifier.get(TFCOrePrefix.toolHeadSense, Materials.Stone));
        GTRecipeHandler.removeRecipesByInputs(MACERATOR_RECIPES, OreDictUnifier.get(TFCOrePrefix.toolHeadFile, Materials.Stone));
        GTRecipeHandler.removeRecipesByInputs(MACERATOR_RECIPES, OreDictUnifier.get(TFCOrePrefix.toolHeadSaw, Materials.Stone));
        GTRecipeHandler.removeRecipesByInputs(MACERATOR_RECIPES, OreDictUnifier.get(TFCOrePrefix.toolHeadPropick, Materials.Stone));
        GTRecipeHandler.removeRecipesByInputs(MACERATOR_RECIPES, OreDictUnifier.get(TFCOrePrefix.toolHeadChisel, Materials.Stone));
        GTRecipeHandler.removeRecipesByInputs(MACERATOR_RECIPES, OreDictUnifier.get(TFCOrePrefix.toolHeadJavelin, Materials.Stone));
    }

    public static void registerStoneRecipes()
    {
        IForgeRegistry<BarrelRecipe> barrelRecipes = TFCRegistries.BARREL;
        IForgeRegistry<ChiselRecipe> chiselRecipes = TFCRegistries.CHISEL;
        IForgeRegistry<QuernRecipe> quernRecipes = TFCRegistries.QUERN;

        for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
        {
            // Rock -> Flux
            if (rock.isFluxStone()) {
                ModHandler.addShapelessRecipe(String.format("flux_%s", rock),
                        TFCMetaItems.FLUX.getStackForm(2),
                        OreDictUnifier.get("rockFlux"),
                        ToolItems.HARD_HAMMER);

                quernRecipes.register(
                        new QuernRecipe(IIngredient.of("rockFlux"), TFCMetaItems.FLUX.getStackForm(2)).setRegistryName(MOD_ID, "flux_" + rock)
                );

                MACERATOR_RECIPES.recipeBuilder()
                        .input("rockFlux")
                        .output(TFCMetaItems.FLUX, 2)
                        .duration(32)
                        .EUt(7)
                        .buildAndRegister();
            }

            // Rock -> Cobblestone
            ModHandler.addShapedRecipe(String.format("cobblestone_%s", rock),
                    new ItemStack(TFCBlockRockVariant.get(rock, Type.COBBLE)), "XX", "XX",
                    'X', new ItemStack(ItemRock.get(rock))
            );

            ASSEMBLER_RECIPES.recipeBuilder()
                    .input(ItemRock.get(rock), 4)
                    .notConsumable(new IntCircuitIngredient(1))
                    .output(TFCBlockRockVariant.get(rock, Type.COBBLE))
                    .duration(32)
                    .EUt(7)
                    .buildAndRegister();

            // Rock -> Brick
            ModHandler.addShapelessRecipe(String.format("brick_%s", rock),
                    new ItemStack(TFCItemBrick.get(rock)),
                    new ItemStack(ItemRock.get(rock)),
                    TFCToolItems.CHISEL);

            ASSEMBLER_RECIPES.recipeBuilder()
                    .input(ItemRock.get(rock))
                    .notConsumable(new IntCircuitIngredient(2))
                    .output(TFCItemBrick.get(rock))
                    .duration(32)
                    .EUt(7)
                    .buildAndRegister();

            // Rock Ground Cover -> Rock (NOT NEEDED, I THINK)
            /*
            ModHandler.addShapelessRecipe(String.format("rock_%s_hammer", rock),
                    new ItemStack(ItemRock.get(rock), 4),
                    new ItemStack(BlockSurfaceRock.get(rock)),
                    ToolItems.HARD_HAMMER
            );

            FORGE_HAMMER_RECIPES.recipeBuilder()
                    .input(BlockSurfaceRock.get(rock))
                    .output(ItemRock.get(rock), 4)
                    .duration(32)
                    .EUt(8)
                    .buildAndRegister();
            */

            // Raw -> Raw Stairs
            ModHandler.addShapedRecipe(String.format("rock_stairs_%s", rock),
                    new ItemStack(TFCBlockRockStairs.get(rock, Type.RAW), 8), "X  ", "XX ", "XXX",
                    'X', new ItemStack(TFCBlockRockVariant.get(rock, Type.RAW))
            );

            ASSEMBLER_RECIPES.recipeBuilder()
                    .input(TFCBlockRockVariant.get(rock, Type.RAW), 6)
                    .notConsumable(new IntCircuitIngredient(7))
                    .output(TFCBlockRockStairs.get(rock, Type.RAW), 8)
                    .duration(200)
                    .EUt(7)
                    .buildAndRegister();

            // Raw -> Raw Slabs
            ModHandler.addShapedRecipe(String.format("rock_slab_%s", rock),
                    new ItemStack(TFCBlockRockSlab.Half.get(rock, Type.RAW), 6), "XXX",
                    'X', new ItemStack(TFCBlockRockVariant.get(rock, Type.RAW))
            );

            CUTTER_RECIPES.recipeBuilder()
                    .input(TFCBlockRockVariant.get(rock, Type.RAW))
                    .output(TFCBlockRockSlab.Half.get(rock, Type.RAW), 2)
                    .duration(200)
                    .EUt(7)
                    .buildAndRegister();

            // Raw -> Raw
            ROCK_BREAKER_RECIPES.recipeBuilder()
                    .notConsumable(new ItemStack(TFCBlockRockVariant.get(rock, Type.RAW)))
                    .output(TFCBlockRockVariant.get(rock, Type.RAW))
                    .duration(32)
                    .EUt(32)
                    .buildAndRegister();

            // Raw -> Mossy Raw
            barrelRecipes.register(
                    new BarrelRecipe(IIngredient.of(TFCFluids.HOT_WATER.get(), 200), IIngredient.of(TFCBlockRockVariant.get(rock, Rock.Type.RAW)), new FluidStack(TFCFluids.FRESH_WATER.get(), 50), new ItemStack(TFCBlockRockVariant.get(rock, Rock.Type.MOSSY_RAW), 1), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName(MOD_ID, "mossy_raw_" + rock)
            );

            CHEMICAL_BATH_RECIPES.recipeBuilder()
                    .input(TFCBlockRockVariant.get(rock, Type.RAW))
                    .fluidInputs(new FluidStack(FluidRegistry.getFluid("hot_water"), 200))
                    .fluidOutputs(new FluidStack(FluidRegistry.getFluid("fresh_water"), 50))
                    .output(TFCBlockRockVariant.get(rock, Type.MOSSY_RAW))
                    .duration(2000)
                    .EUt(32)
                    .buildAndRegister();

            // Raw -> Cobblestone
            FORGE_HAMMER_RECIPES.recipeBuilder()
                    .input(TFCBlockRockVariant.get(rock, Type.RAW))
                    .output(TFCBlockRockVariant.get(rock, Type.COBBLE))
                    .duration(32)
                    .EUt(8)
                    .buildAndRegister();

            // Raw -> Smooth
            chiselRecipes.register(
                    new ChiselRecipe(TFCBlockRockVariant.get(rock, Type.RAW), TFCBlockRockVariant.get(rock, Type.SMOOTH).getDefaultState()).setRegistryName("smooth_" + rock)
            );

            LASER_ENGRAVER_RECIPES.recipeBuilder()
                    .input(TFCBlockRockVariant.get(rock, Type.RAW))
                    .notConsumable(MetaItems.GLASS_LENSES.get(MarkerMaterials.Color.Orange))
                    .output(TFCBlockRockVariant.get(rock, Type.SMOOTH))
                    .duration(300)
                    .EUt(30)
                    .buildAndRegister();

            // Cobblestone -> Gravel
            FORGE_HAMMER_RECIPES.recipeBuilder()
                    .input(TFCBlockRockVariant.get(rock, Type.COBBLE))
                    .output(TFCBlockRockVariant.get(rock, Type.GRAVEL))
                    .duration(32)
                    .EUt(8)
                    .buildAndRegister();

            // Gravel -> Sand
            FORGE_HAMMER_RECIPES.recipeBuilder()
                    .input(TFCBlockRockVariant.get(rock, Type.GRAVEL))
                    .output(TFCBlockRockVariant.get(rock, Type.SAND))
                    .duration(32)
                    .EUt(8)
                    .buildAndRegister();
        }
    }

    public static void registerWoodRecipes() {
        for (Wood wood : TFCRegistries.WOODS.getValuesCollection())
        {
//            // Log -> Lumber
//            ModHandler.addShapelessRecipe(String.format("lumber_%s", wood),
//                    new ItemStack(TFCItemLumber.get(wood), 8),
//                    new ItemStack(TFCBlockLog.get(wood)),
//                    ToolItems.SAW);
//
//            CUTTER_RECIPES.recipeBuilder()
//                    .input(TFCBlockLog.get(wood))
//                    .output(TFCItemLumber.get(wood), 16)
//                    .output(OrePrefix.dust, Materials.Wood, 2)
//                    .duration(200)
//                    .EUt(7)
//                    .buildAndRegister();

            // Lumber -> Planks
            ModHandler.addShapedRecipe(String.format("plank_%s", wood),
                    new ItemStack(TFCBlockPlank.get(wood)), "XX", "XX",
                    'X', new ItemStack(TFCItemLumber.get(wood))
            );

            ASSEMBLER_RECIPES.recipeBuilder()
                    .input(TFCItemLumber.get(wood), 4)
                    .notConsumable(new IntCircuitIngredient(3))
                    .output(TFCBlockPlank.get(wood))
                    .duration(200)
                    .EUt(7)
                    .buildAndRegister();

            // Planks -> Lumber
            CUTTER_RECIPES.recipeBuilder()
                    .input(TFCBlockPlank.get(wood))
                    .output(TFCItemLumber.get(wood), 4)
                    .output(OrePrefix.dust, Materials.Wood, 2)
                    .duration(200)
                    .EUt(7)
                    .buildAndRegister();

            // Planks -> Slabs
            ModHandler.addShapedRecipe(String.format("wood_slab_%s", wood),
                    new ItemStack(TFCBlockWoodSlab.Half.get(wood), 6), "XXX",
                    'X', new ItemStack(TFCBlockPlank.get(wood))
            );

            CUTTER_RECIPES.recipeBuilder()
                    .input(TFCBlockPlank.get(wood))
                    .output(TFCBlockWoodSlab.Half.get(wood), 2)
                    .duration(200)
                    .EUt(7)
                    .buildAndRegister();

            // Planks -> Stairs
            /*
            ModHandler.addShapedRecipe(String.format("wood_stairs_%s", tree),
                    new ItemStack(BlockRockStairsTFC.get(tree), 8), "X  ", "XX ", "XXX",
                    'X', new ItemStack(BlockPlanksTFC.get(tree))
            );

            ASSEMBLER_RECIPES.recipeBuilder()
                    .input(BlockPlanksTFC.get(tree), 6)
                    .notConsumable(new IntCircuitIngredient(7))
                    .output(BlockRockStairsTFC.get(tree), 8)
                    .duration(200)
                    .EUt(7)
                    .buildAndRegister();*/

            // Lumber -> Wood Pressure Plates
            ModHandler.addShapedRecipe(String.format("wood_pressure_plate_%s", wood),
                    new ItemStack(TFCBlockWoodPressurePlate.get(wood)), "XXY",
                    'X', new ItemStack(TFCBlockPlank.get(wood)),
                    'Y', OreDictUnifier.get(OrePrefix.spring, Materials.Iron)
            );

            ASSEMBLER_RECIPES.recipeBuilder()
                    .input(TFCItemLumber.get(wood), 2)
                    .input(OrePrefix.spring, Materials.Iron)
                    .notConsumable(new IntCircuitIngredient(4))
                    .output(TFCBlockWoodPressurePlate.get(wood))
                    .duration(200)
                    .EUt(7)
                    .buildAndRegister();

            // Lumber -> Wood Buttons
            ModHandler.addShapedRecipe(String.format("wood_button_%s", wood),
                    new ItemStack(TFCBlockWoodButton.get(wood)), "XY",
                    'X', new ItemStack(TFCBlockPlank.get(wood)),
                    'Y', OreDictUnifier.get(OrePrefix.springSmall, Materials.Iron)
            );

            ASSEMBLER_RECIPES.recipeBuilder()
                    .input(TFCItemLumber.get(wood))
                    .input(OrePrefix.springSmall, Materials.Iron)
                    .notConsumable(new IntCircuitIngredient(5))
                    .output(TFCBlockWoodButton.get(wood))
                    .duration(200)
                    .EUt(7)
                    .buildAndRegister();

            // Planks -> Fences
            ModHandler.addShapedRecipe(String.format("wood_fence_%s", wood),
                    new ItemStack(TFCBlockFence.get(wood)), "XYX", "XYX",
                    'X', new ItemStack(TFCBlockPlank.get(wood)),
                    'Y', Items.STICK
            );

            ASSEMBLER_RECIPES.recipeBuilder()
                    .input(TFCBlockPlank.get(wood), 4)
                    .input(Items.STICK, 2)
                    .notConsumable(new IntCircuitIngredient(1))
                    .output(TFCBlockFence.get(wood))
                    .duration(200)
                    .EUt(7)
                    .buildAndRegister();

            // Planks -> Gates
            ModHandler.addShapedRecipe(String.format("wood_gate_%s", wood),
                    new ItemStack(TFCBlockFenceGate.get(wood)), "YXY", "YXY",
                    'X', new ItemStack(TFCBlockPlank.get(wood)),
                    'Y', Items.STICK
            );

            ASSEMBLER_RECIPES.recipeBuilder()
                    .input(TFCBlockPlank.get(wood), 2)
                    .input(Items.STICK, 4)
                    .notConsumable(new IntCircuitIngredient(2))
                    .output(TFCBlockFenceGate.get(wood))
                    .duration(200)
                    .EUt(7)
                    .buildAndRegister();

//            // Log -> Gates
//            ModHandler.addShapedRecipe(String.format("log_gate_%s", wood),
//                    new ItemStack(TFCBlockFenceGateLog.get(wood)), "YXY", "YXY",
//                    'X', new ItemStack(TFCBlockLog.get(wood)),
//                    'Y', Items.STICK
//            );
//
//            ASSEMBLER_RECIPES.recipeBuilder()
//                    .input(TFCBlockLog.get(wood), 2)
//                    .input(Items.STICK, 4)
//                    .notConsumable(new IntCircuitIngredient(2))
//                    .output(TFCBlockFenceGateLog.get(wood))
//                    .duration(200)
//                    .EUt(7)
//                    .buildAndRegister();

            // Bookshelf's
            ModHandler.addShapedRecipe(String.format("bookshelf_%s", wood),
                    new ItemStack(TFCBlockBookshelf.get(wood)), "XXX", "YYY", "XXX",
                    'X', new ItemStack(TFCBlockPlank.get(wood)),
                    'Y', Items.BOOK
            );

            ASSEMBLER_RECIPES.recipeBuilder()
                    .input(TFCBlockPlank.get(wood), 6)
                    .input(Items.BOOK, 3)
                    .notConsumable(new IntCircuitIngredient(10))
                    .output(TFCBlockBookshelf.get(wood))
                    .duration(200)
                    .EUt(7)
                    .buildAndRegister();

            // Lumber -> Trapdoors
            ModHandler.addShapedRecipe(String.format("wood_trapdoor_%s", wood),
                    new ItemStack(TFCBlockWoodTrapDoor.get(wood)), "XXX", "XXX",
                    'X', new ItemStack(TFCItemLumber.get(wood))
            );

            ASSEMBLER_RECIPES.recipeBuilder()
                    .input(TFCItemLumber.get(wood), 6)
                    .notConsumable(new IntCircuitIngredient(9))
                    .output(TFCBlockWoodTrapDoor.get(wood))
                    .duration(200)
                    .EUt(7)
                    .buildAndRegister();

            // Workbenches
            ModHandler.addShapedRecipe(String.format("workbench_%s", wood),
                    new ItemStack(TFCBlockWorkbench.get(wood)), "YY", "XX",
                    'X', new ItemStack(TFCBlockPlank.get(wood)),
                    'Y', new ItemStack(Items.FLINT)
            );

            ASSEMBLER_RECIPES.recipeBuilder()
                    .input(TFCBlockPlank.get(wood), 4)
                    .notConsumable(new IntCircuitIngredient(11))
                    .output(TFCBlockWorkbench.get(wood))
                    .duration(200)
                    .EUt(7)
                    .buildAndRegister();

            // Chest
            ModHandler.addShapedRecipe(String.format("chest_%s", wood),
                    new ItemStack(TFCBlockChest.getBasic(wood)), "XXX", "X X", "XXX",
                    'X', new ItemStack(TFCItemLumber.get(wood))
                    );

            ASSEMBLER_RECIPES.recipeBuilder()
                    .input(TFCItemLumber.get(wood), 8)
                    .notConsumable(new IntCircuitIngredient(12))
                    .output(TFCBlockChest.getBasic(wood))
                    .duration(200)
                    .EUt(7)
                    .buildAndRegister();

            // Trapped Chest
            ModHandler.addShapelessRecipe(String.format("trapped_chest_%s", wood),
                    new ItemStack(TFCBlockChest.getTrap(wood)),
                    new ItemStack(TFCBlockChest.getBasic(wood)),
                    new ItemStack(Blocks.TRIPWIRE));

            ASSEMBLER_RECIPES.recipeBuilder()
                    .input(TFCBlockChest.getBasic(wood))
                    .input(Blocks.TRIPWIRE)
                    .notConsumable(new IntCircuitIngredient(12))
                    .output(TFCBlockChest.getTrap(wood))
                    .duration(200)
                    .EUt(7)
                    .buildAndRegister();

            // Looms
            ModHandler.addShapedRecipe(String.format("loom_%s", wood),
                    new ItemStack(TFCBlockLoom.get(wood)), "XXX", "XYX", "X X",
                    'X', new ItemStack(TFCItemLumber.get(wood)),
                    'Y', Items.STICK
            );

            ASSEMBLER_RECIPES.recipeBuilder()
                    .input(TFCItemLumber.get(wood), 7)
                    .input(Items.STICK)
                    .notConsumable(new IntCircuitIngredient(13))
                    .output(TFCBlockLoom.get(wood))
                    .duration(200)
                    .EUt(7)
                    .buildAndRegister();

            // Barrels
            ModHandler.addShapedRecipe(String.format("barrel_%s", wood),
                    new ItemStack(TFCBlockBarrel.get(wood)), "X X", "X X", "XXX",
                    'X', new ItemStack(TFCItemLumber.get(wood))
            );

            ASSEMBLER_RECIPES.recipeBuilder()
                    .input(TFCItemLumber.get(wood), 7)
                    .notConsumable(new IntCircuitIngredient(15))
                    .output(TFCBlockBarrel.get(wood))
                    .duration(200)
                    .EUt(7)
                    .buildAndRegister();

            // Boats
            ModHandler.addShapedRecipe(String.format("boat_%s", wood),
                    new ItemStack(TFCItemBoat.get(wood)), "XXX", "YZY", "YYY",
                    'X', OreDictUnifier.get(OrePrefix.screw, Materials.Iron),
                    'Y', new ItemStack(TFCItemLumber.get(wood)),
                    'Z', MetaItems.STICKY_RESIN
            );

            ASSEMBLER_RECIPES.recipeBuilder()
                    .input(TFCItemLumber.get(wood), 5)
                    .input(MetaItems.STICKY_RESIN)
                    .input(OrePrefix.screw, Materials.Iron, 3)
                    .notConsumable(new IntCircuitIngredient(16))
                    .output(TFCItemBoat.get(wood))
                    .duration(200)
                    .EUt(7)
                    .buildAndRegister();

//            // Supports
//            ModHandler.addShapedRecipe(String.format("support_%s", wood),
//                    new ItemStack(TFCBlockWoodSupport.get(wood)), "ZX ", " X ", " X ",
//                    'X', TFCBlockLog.get(wood),
//                    'Z', ToolItems.SAW
//            );
//
//            ASSEMBLER_RECIPES.recipeBuilder()
//                    .input(TFCBlockLog.get(wood), 3)
//                    .notConsumable(new IntCircuitIngredient(7))
//                    .output(TFCBlockWoodSupport.get(wood), 8)
//                    .duration(200)
//                    .EUt(7)
//                    .buildAndRegister();
        }
    }

    //@SubscribeEvent
    public static void onRegisterBarrelRecipeEvent(RegistryEvent.Register<BarrelRecipe> event)
    {
        IForgeRegistry<BarrelRecipe> r = event.getRegistry();

        // Remove recipes
        IForgeRegistryModifiable modRegistry = (IForgeRegistryModifiable) TFCRegistries.BARREL;
        String[] regNames = {"sugar", "beer", "sake"};
        for (String name : regNames)
        {
            BarrelRecipe recipe = TFCRegistries.BARREL.getValue(new ResourceLocation("tfc", name));
            if (recipe != null)
            {
                modRegistry.remove(recipe.getRegistryName());
                TerraFirmaCraft.getLog().info("Removed barrel recipe tfc:{}", name);
            }
        }


        r.registerAll(
            // Hide Processing (all three conversions)
            new BarrelRecipe(IIngredient.of(FluidRegistry.WATER, 300), IIngredient.of(TFCItemAnimalHide.get(TFCItemAnimalHide.HideType.SCRAPED, TFCItemAnimalHide.HideSize.SMALL)), null, new ItemStack(TFCItemAnimalHide.get(TFCItemAnimalHide.HideType.PREPARED, TFCItemAnimalHide.HideSize.SMALL)), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("small_prepared_hide"),
            new BarrelRecipe(IIngredient.of(FluidRegistry.WATER, 400), IIngredient.of(TFCItemAnimalHide.get(TFCItemAnimalHide.HideType.SCRAPED, TFCItemAnimalHide.HideSize.MEDIUM)), null, new ItemStack(TFCItemAnimalHide.get(TFCItemAnimalHide.HideType.PREPARED, TFCItemAnimalHide.HideSize.MEDIUM)), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("medium_prepared_hide"),
            new BarrelRecipe(IIngredient.of(FluidRegistry.WATER, 500), IIngredient.of(TFCItemAnimalHide.get(TFCItemAnimalHide.HideType.SCRAPED, TFCItemAnimalHide.HideSize.LARGE)), null, new ItemStack(TFCItemAnimalHide.get(TFCItemAnimalHide.HideType.PREPARED, TFCItemAnimalHide.HideSize.LARGE)), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("large_prepared_hide"),
            new BarrelRecipe(IIngredient.of(LIMEWATER.get(), 300), IIngredient.of(TFCItemAnimalHide.get(TFCItemAnimalHide.HideType.RAW, TFCItemAnimalHide.HideSize.SMALL)), null, new ItemStack(TFCItemAnimalHide.get(TFCItemAnimalHide.HideType.SOAKED, TFCItemAnimalHide.HideSize.SMALL)), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("small_soaked_hide"),
            new BarrelRecipe(IIngredient.of(LIMEWATER.get(), 400), IIngredient.of(TFCItemAnimalHide.get(TFCItemAnimalHide.HideType.RAW, TFCItemAnimalHide.HideSize.MEDIUM)), null, new ItemStack(TFCItemAnimalHide.get(TFCItemAnimalHide.HideType.SOAKED, TFCItemAnimalHide.HideSize.MEDIUM)), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("medium_soaked_hide"),
            new BarrelRecipe(IIngredient.of(LIMEWATER.get(), 500), IIngredient.of(TFCItemAnimalHide.get(TFCItemAnimalHide.HideType.RAW, TFCItemAnimalHide.HideSize.LARGE)), null, new ItemStack(TFCItemAnimalHide.get(TFCItemAnimalHide.HideType.SOAKED, TFCItemAnimalHide.HideSize.LARGE)), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("large_soaked_hide"),
            new BarrelRecipe(IIngredient.of(TANNIN.get(), 300), IIngredient.of(TFCItemAnimalHide.get(TFCItemAnimalHide.HideType.PREPARED, TFCItemAnimalHide.HideSize.SMALL)), null, new ItemStack(Items.LEATHER), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("leather_small_hide"),
            new BarrelRecipe(IIngredient.of(TANNIN.get(), 400), IIngredient.of(TFCItemAnimalHide.get(TFCItemAnimalHide.HideType.PREPARED, TFCItemAnimalHide.HideSize.MEDIUM)), null, new ItemStack(Items.LEATHER, 2), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("leather_medium_hide"),
            new BarrelRecipe(IIngredient.of(TANNIN.get(), 500), IIngredient.of(TFCItemAnimalHide.get(TFCItemAnimalHide.HideType.PREPARED, TFCItemAnimalHide.HideSize.LARGE)), null, new ItemStack(Items.LEATHER, 3), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("leather_large_hide"),
            // Misc
            new BarrelRecipe(IIngredient.of(FluidRegistry.WATER, 1000), IIngredient.of("logWoodTannin"), new FluidStack(TANNIN.get(), 10000), ItemStack.EMPTY, 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("tannin"),
            new BarrelRecipe(IIngredient.of(FluidRegistry.WATER, 200), IIngredient.of("jute"), null, new ItemStack((Item) IIngredient.of("jute.fiber")), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("jute_fiber"),
            new BarrelRecipe(IIngredient.of(FluidRegistry.WATER, 600), new IngredientItemFood(IIngredient.of(TFCItemFood.get(Food.SUGARCANE), 5)), null, new ItemStack(Items.SUGAR), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("sugar"),
            new BarrelRecipe(IIngredient.of(LIMEWATER.get(), 500), IIngredient.of(new ItemStack(Items.DYE, 1, EnumDyeColor.WHITE.getDyeDamage())), null, new ItemStack(TFCItems.GLUE), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("glue"),
            // Alcohol - Classic created 1000mb with 4oz, which would be 8 items per full barrel at 5 oz/item. Instead we now require 20 items, so conversion is 2 oz/item here
            new BarrelRecipe(IIngredient.of(FluidRegistry.WATER, 500), new IngredientItemFood(IIngredient.of(TFCItemFood.get(Food.BARLEY_FLOUR))), new FluidStack(BEER.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("beer"),
            new BarrelRecipe(IIngredient.of(FluidRegistry.WATER, 500), new IngredientItemFood(IIngredient.of("apple")), new FluidStack(CIDER.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("cider"),
            new BarrelRecipe(IIngredient.of(FluidRegistry.WATER, 500), IIngredient.of(Items.SUGAR), new FluidStack(RUM.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("rum"),
            new BarrelRecipe(IIngredient.of(FluidRegistry.WATER, 500), new IngredientItemFood(IIngredient.of(TFCItemFood.get(Food.RICE_FLOUR))), new FluidStack(SAKE.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("sake"),
            new BarrelRecipe(IIngredient.of(FluidRegistry.WATER, 500), new IngredientItemFood(IIngredient.of(TFCItemFood.get(Food.POTATO))), new FluidStack(VODKA.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("vodka"),
            new BarrelRecipe(IIngredient.of(FluidRegistry.WATER, 500), new IngredientItemFood(IIngredient.of(TFCItemFood.get(Food.WHEAT_FLOUR))), new FluidStack(WHISKEY.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("whiskey"),
            new BarrelRecipe(IIngredient.of(FluidRegistry.WATER, 500), new IngredientItemFood(IIngredient.of(TFCItemFood.get(Food.CORNMEAL_FLOUR))), new FluidStack(CORN_WHISKEY.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("corn_whiskey"),
            new BarrelRecipe(IIngredient.of(FluidRegistry.WATER, 500), new IngredientItemFood(IIngredient.of(TFCItemFood.get(Food.RYE_FLOUR))), new FluidStack(RYE_WHISKEY.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("rye_whiskey"),
            // Vinegar - Classic created 1000mb with 10 oz, which would be 20 items per full barrel at 5 oz/item. Instead we now require 40 items, so conversion is 2.5 oz/item.
            new BarrelRecipe(IIngredient.of(250, BEER.get(), CIDER.get(), RUM.get(), SAKE.get(), VODKA.get(), WHISKEY.get(), CORN_WHISKEY.get(), RYE_WHISKEY.get()), new IngredientItemFood(IIngredient.of("categoryFruit")), new FluidStack(VINEGAR.get(), 250), ItemStack.EMPTY, 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("vinegar"),
            // Food preservation
            BarrelRecipeFoodTraits.pickling(new IngredientItemFood(IIngredient.of("categoryFruit"))).setRegistryName("pickling_fruit"),
            BarrelRecipeFoodTraits.pickling(new IngredientItemFood(IIngredient.of("categoryVegetable"))).setRegistryName("pickling_vegetable"),
            BarrelRecipeFoodTraits.pickling(new IngredientItemFood(IIngredient.of("categoryMeat"))).setRegistryName("pickling_meat"),
            BarrelRecipeFoodTraits.brining(new IngredientItemFood(IIngredient.of("categoryFruit"))).setRegistryName("brining_fruit"),
            BarrelRecipeFoodTraits.brining(new IngredientItemFood(IIngredient.of("categoryVegetable"))).setRegistryName("brining_vegetable"),
            BarrelRecipeFoodTraits.brining(new IngredientItemFood(IIngredient.of("categoryMeat"))).setRegistryName("brining_meat"),
            BarrelRecipeFoodPreservation.vinegar(new IngredientItemFood(IIngredient.of("categoryFruit"))).setRegistryName("vinegar_fruit"),
            BarrelRecipeFoodPreservation.vinegar(new IngredientItemFood(IIngredient.of("categoryVegetable"))).setRegistryName("vinegar_vegetable"),
            BarrelRecipeFoodPreservation.vinegar(new IngredientItemFood(IIngredient.of("categoryMeat"))).setRegistryName("vinegar_meat"),

            new BarrelRecipe(IIngredient.of(LIMEWATER.get(), 100), IIngredient.of("sand"), null, new ItemStack(TFCItems.MORTAR, 16), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("mortar"),
            new BarrelRecipe(IIngredient.of(FluidRegistry.WATER, 125), IIngredient.of("dustSalt"), new FluidStack(SEA_WATER.get(), 125), ItemStack.EMPTY, 0).setRegistryName("fresh_to_salt_water"),
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 125), IIngredient.of(new ItemStack(TFCItems.WOOD_ASH)), new FluidStack(LYE.get(), 125), ItemStack.EMPTY, 0).setRegistryName("lye"),
            new BarrelRecipe(IIngredient.of(MILK_VINEGAR.get(), 1), IIngredient.of(ItemStack.EMPTY), new FluidStack(CURDLED_MILK.get(), 1), ItemStack.EMPTY, 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("curdled_milk"),
            // based on eating 5 oz in classic, and 1 item in TNG, the full barrel recipe generated 160 oz of cheese, now 32 items. Therefore 625mb creates 2 cheese.
            new BarrelRecipe(IIngredient.of(CURDLED_MILK.get(), 625), IIngredient.of(ItemStack.EMPTY), null, new ItemStack(TFCItemFood.get(Food.CHEESE), 2), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("cheese"),

            // Instant recipes: set the duration to 0
            new BarrelRecipeFluidMixing(IIngredient.of(SEA_WATER.get(), 9), new IngredientFluidItem(VINEGAR.get(), 1), new FluidStack(BRINE.get(), 10), 0).setRegistryName("brine"),
            // this ratio works for 9b + 1b = 10b (full barrel) of brine/milk_vinegar, but leaves odd ninths of fluid around for other mixtures.
            new BarrelRecipeFluidMixing(IIngredient.of(MILK.get(), 9), new IngredientFluidItem(VINEGAR.get(), 1), new FluidStack(MILK_VINEGAR.get(), 10), 0).setRegistryName("milk_vinegar"),
            new BarrelRecipe(IIngredient.of(FluidRegistry.WATER, 500), IIngredient.of("dustFlux"), new FluidStack(LIMEWATER.get(), 500), ItemStack.EMPTY, 0).setRegistryName("limewater"),
            new BarrelRecipe(IIngredient.of(LIMEWATER.get(), 100), IIngredient.of("gemGypsum"), null, new ItemStack(TFCBlocks.ALABASTER_RAW_PLAIN), ICalendar.TICKS_IN_HOUR).setRegistryName("plain_alabaster"),

            //olive oil production
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 125), IIngredient.of(TFCItems.OLIVE_PASTE), new FluidStack(OLIVE_OIL_WATER.get(), 125), ItemStack.EMPTY, 2 * ICalendar.TICKS_IN_HOUR).setRegistryName("olive_water"),
            // Balance note: Classic gave 250mb for 160oz of olives ~= 32 items. We give 800 mb for that, so 3.2x more. Hopefully will help with lamp usage
            new BarrelRecipe(IIngredient.of(OLIVE_OIL_WATER.get(), 250), IIngredient.of("jute.net"), new FluidStack(OLIVE_OIL.get(), 50), new ItemStack((Item) IIngredient.of("dirty.jute.net")), 0).setRegistryName("olive_oil"),
            // Balance: switch to fresh water. Hot water use that way is broken
            new BarrelRecipe(IIngredient.of(FluidRegistry.WATER, 125), IIngredient.of("dirty.jute.net"), null, new ItemStack((Item) IIngredient.of("jute.net")), ICalendar.TICKS_IN_HOUR).setRegistryName("clean_net"),
            // Temperature recipes
            new BarrelRecipeTemperature(IIngredient.of(FluidRegistry.WATER, 1), 50).setRegistryName("water_cooling"),
            new BarrelRecipeTemperature(IIngredient.of(SEA_WATER.get(), 1), 50).setRegistryName("salt_water_cooling"),


            new BarrelRecipe(IIngredient.of(SALAMMONIAC.get(), 300), IIngredient.of(TFCItemAnimalHide.get(TFCItemAnimalHide.HideType.PREPARED, TFCItemAnimalHide.HideSize.SMALL)), null, new ItemStack(Items.LEATHER), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("leather_small_hide_salmiak"),
            new BarrelRecipe(IIngredient.of(SALAMMONIAC.get(), 400), IIngredient.of(TFCItemAnimalHide.get(TFCItemAnimalHide.HideType.PREPARED, TFCItemAnimalHide.HideSize.MEDIUM)), null, new ItemStack(Items.LEATHER, 2), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("leather_medium_hide_salmiak"),
            new BarrelRecipe(IIngredient.of(SALAMMONIAC.get(), 500), IIngredient.of(TFCItemAnimalHide.get(TFCItemAnimalHide.HideType.PREPARED, TFCItemAnimalHide.HideSize.LARGE)), null, new ItemStack(Items.LEATHER, 3), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("leather_large_hide_salmiak"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 1000), IIngredient.of("dustAmmoniumChloride"), new FluidStack(SALAMMONIAC.get(), 1000), ItemStack.EMPTY, 0).setRegistryName("salammoniac"),

            // Sugar
            //new BarrelRecipe(IIngredient.of(TFCFluids.FRESH_WATER.get(), 600), IIngredient.of("sugarcane", 5), null, new ItemStack(Items.SUGAR), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("sugar_from_sugar_cane"),

            // Base Potash Liquor
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 500), IIngredient.of("dustPotash"), new FluidStack(BASE_POTASH_LIQUOR.get(), 500), ItemStack.EMPTY, 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("base_potash_liquor_from_potash"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 500), IIngredient.of("dustAsh"), new FluidStack(BASE_POTASH_LIQUOR.get(), 500), ItemStack.EMPTY, 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("base_potash_liquor_from_ash"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 500), IIngredient.of("dustWood"), new FluidStack(BASE_POTASH_LIQUOR.get(), 500), ItemStack.EMPTY, 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("base_potash_liquor_from_wood_dust"),

            // Cellulose Fibers
            new BarrelRecipe(IIngredient.of(BASE_POTASH_LIQUOR.get(), 150), IIngredient.of(TFCItemFood.get(Food.SUGARCANE)), new FluidStack(WASTE.get(), 150), new ItemStack(TFCItems.CELLULOSE_FIBERS), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("cellulose_fibers_from_sugarcane_1"),
            new BarrelRecipe(IIngredient.of(BASE_POTASH_LIQUOR.get(), 150), IIngredient.of("sugarcane"), new FluidStack(WASTE.get(), 150), new ItemStack(TFCItems.CELLULOSE_FIBERS), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("cellulose_fibers_from_sugarcane_2"),
            new BarrelRecipe(IIngredient.of(BASE_POTASH_LIQUOR.get(), 150), IIngredient.of("pulp"), new FluidStack(WASTE.get(), 150), new ItemStack(TFCItems.CELLULOSE_FIBERS), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("cellulose_fibers_from_pulp"),
            new BarrelRecipe(IIngredient.of(BASE_POTASH_LIQUOR.get(), 150), IIngredient.of("cropAgave"), new FluidStack(WASTE.get(), 150), new ItemStack(TFCItems.CELLULOSE_FIBERS), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("cellulose_fibers_from_sisal_crop"),
            new BarrelRecipe(IIngredient.of(BASE_POTASH_LIQUOR.get(), 150), IIngredient.of("cropLinen"), new FluidStack(WASTE.get(), 150), new ItemStack(TFCItems.CELLULOSE_FIBERS), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("cellulose_fibers_from_linen_crop"),
            new BarrelRecipe(IIngredient.of(BASE_POTASH_LIQUOR.get(), 150), IIngredient.of("cropHemp"), new FluidStack(WASTE.get(), 150), new ItemStack(TFCItems.CELLULOSE_FIBERS), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("cellulose_fibers_from_hemp_crop"),
            new BarrelRecipe(IIngredient.of(BASE_POTASH_LIQUOR.get(), 150), IIngredient.of("pulpPapyrus"), new FluidStack(WASTE.get(), 150), new ItemStack(TFCItems.CELLULOSE_FIBERS), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("cellulose_fibers_from_papyrus_crop"),
            new BarrelRecipe(IIngredient.of(BASE_POTASH_LIQUOR.get(), 150), IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.YUCCA))), new FluidStack(WASTE.get(), 150), new ItemStack(TFCItems.CELLULOSE_FIBERS), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("cellulose_fibers_from_yucca_crop"),

            // Papyrus Fibers
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 600), IIngredient.of("pulpPapyrus", 3), null, new ItemStack((Item) IIngredient.of("papyrus.fiber")), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("papyrus_fiber_from_papyrus"),

            // Fiber Processing
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 200), IIngredient.of("cropAgave"), null, new ItemStack((Item) IIngredient.of("sisal.fiber")), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("sisal_fiber"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 200), IIngredient.of("cropLinen"), null, new ItemStack((Item) IIngredient.of("linen.fiber")), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("linen_fiber"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 200), IIngredient.of("cropHemp"), null, new ItemStack((Item) IIngredient.of("hemp.fiber")), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("hemp_fiber"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 300), IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.YUCCA))), null, new ItemStack((Item) IIngredient.of("yucca.fiber")), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("yucca_fiber"),

            // Fluid Production from paste

            // Olive
            new BarrelRecipe(IIngredient.of(OLIVE_OIL_WATER.get(), 250), IIngredient.of("sisal.net"), new FluidStack(OLIVE_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.sisal.net")), 0).setRegistryName("olive_oil_sisal"),
            new BarrelRecipe(IIngredient.of(OLIVE_OIL_WATER.get(), 250), IIngredient.of("silk.net"), new FluidStack(OLIVE_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.silk.net")), 0).setRegistryName("olive_oil_silk"),
            new BarrelRecipe(IIngredient.of(OLIVE_OIL_WATER.get(), 250), IIngredient.of("cotton.net"), new FluidStack(OLIVE_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.cotton.net")), 0).setRegistryName("olive_oil_cotton"),
            new BarrelRecipe(IIngredient.of(OLIVE_OIL_WATER.get(), 250), IIngredient.of("linen.net"), new FluidStack(OLIVE_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.linen.net")), 0).setRegistryName("olive_oil_linen"),
            new BarrelRecipe(IIngredient.of(OLIVE_OIL_WATER.get(), 250), IIngredient.of("hemp.net"), new FluidStack(OLIVE_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.hemp.net")), 0).setRegistryName("olive_oil_hemp"),

            // Soybean
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 125), IIngredient.of("pasteSoybean"), new FluidStack(SOYBEAN_WATER.get(), 125), ItemStack.EMPTY, 2 * ICalendar.TICKS_IN_HOUR).setRegistryName("soybean_water"),
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 125), IIngredient.of("groundSoybeans"), new FluidStack(SOYBEAN_WATER.get(), 125), ItemStack.EMPTY, 2 * ICalendar.TICKS_IN_HOUR).setRegistryName("soybean_water_firmalife"),

            new BarrelRecipe(IIngredient.of(SOYBEAN_WATER.get(), 250), IIngredient.of("jute.net"), new FluidStack(SOY_MILK.get(), 25), new ItemStack((Item) IIngredient.of("dirty.jute.net")), 0).setRegistryName("soy_milk_jute"),
            new BarrelRecipe(IIngredient.of(SOYBEAN_WATER.get(), 250), IIngredient.of("silk.net"), new FluidStack(SOY_MILK.get(), 25), new ItemStack((Item) IIngredient.of("dirty.silk.net")), 0).setRegistryName("soy_milk_silk"),
            new BarrelRecipe(IIngredient.of(SOYBEAN_WATER.get(), 250), IIngredient.of("sisal.net"), new FluidStack(SOY_MILK.get(), 25), new ItemStack((Item) IIngredient.of("dirty.sisal.net")), 0).setRegistryName("soy_milk_sisal"),
            new BarrelRecipe(IIngredient.of(SOYBEAN_WATER.get(), 250), IIngredient.of("cotton.net"), new FluidStack(SOY_MILK.get(), 25), new ItemStack((Item) IIngredient.of("dirty.cotton.net")), 0).setRegistryName("soy_milk_cotton"),
            new BarrelRecipe(IIngredient.of(SOYBEAN_WATER.get(), 250), IIngredient.of("linen.net"), new FluidStack(SOY_MILK.get(), 25), new ItemStack((Item) IIngredient.of("dirty.linen.net")), 0).setRegistryName("soy_milk_linen"),
            new BarrelRecipe(IIngredient.of(SOYBEAN_WATER.get(), 250), IIngredient.of("hemp.net"), new FluidStack(SOY_MILK.get(), 25), new ItemStack((Item) IIngredient.of("dirty.hemp.net")), 0).setRegistryName("soy_milk_hemp"),

                new BarrelRecipeFluidMixing(IIngredient.of(SOY_MILK.get(), 9), new IngredientFluidItem(VINEGAR.get(), 1), new FluidStack(MILK_VINEGAR.get(), 10), 0).setRegistryName("soy_milk_vinegar"),

            // Linseed
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 125), IIngredient.of("pasteLinseed"), new FluidStack(LINSEED_WATER.get(), 125), ItemStack.EMPTY, 2 * ICalendar.TICKS_IN_HOUR).setRegistryName("linseed_water"),

            new BarrelRecipe(IIngredient.of(LINSEED_WATER.get(), 250), IIngredient.of("jute.net"), new FluidStack(LINSEED_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.jute.net")), 0).setRegistryName("linseed_oil_jute"),
            new BarrelRecipe(IIngredient.of(LINSEED_WATER.get(), 250), IIngredient.of("sisal.net"), new FluidStack(LINSEED_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.sisal.net")), 0).setRegistryName("linseed_oil_sisal"),
            new BarrelRecipe(IIngredient.of(LINSEED_WATER.get(), 250), IIngredient.of("silk.net"), new FluidStack(LINSEED_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.silk.net")), 0).setRegistryName("linseed_oil_silk"),
            new BarrelRecipe(IIngredient.of(LINSEED_WATER.get(), 250), IIngredient.of("cotton.net"), new FluidStack(LINSEED_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.cotton.net")), 0).setRegistryName("linseed_oil_cotton"),
            new BarrelRecipe(IIngredient.of(LINSEED_WATER.get(), 250), IIngredient.of("linen.net"), new FluidStack(LINSEED_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.linen.net")), 0).setRegistryName("linseed_oil_linen"),
            new BarrelRecipe(IIngredient.of(LINSEED_WATER.get(), 250), IIngredient.of("hemp.net"), new FluidStack(LINSEED_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.hemp.net")), 0).setRegistryName("linseed_oil_hemp"),

            // Rape Seed
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 125), IIngredient.of("pasteRapeSeed"), new FluidStack(RAPE_SEED_WATER.get(), 125), ItemStack.EMPTY, 2 * ICalendar.TICKS_IN_HOUR).setRegistryName("rape_seed_water"),

            new BarrelRecipe(IIngredient.of(RAPE_SEED_WATER.get(), 250), IIngredient.of("jute.net"), new FluidStack(RAPE_SEED_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.jute.net")), 0).setRegistryName("rape_seed_oil_jute"),
            new BarrelRecipe(IIngredient.of(RAPE_SEED_WATER.get(), 250), IIngredient.of("sisal.net"), new FluidStack(RAPE_SEED_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.sisal.net")), 0).setRegistryName("rape_seed_oil_sisal"),
            new BarrelRecipe(IIngredient.of(RAPE_SEED_WATER.get(), 250), IIngredient.of("silk.net"), new FluidStack(RAPE_SEED_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.silk.net")), 0).setRegistryName("rape_seed_oil_silk"),
            new BarrelRecipe(IIngredient.of(RAPE_SEED_WATER.get(), 250), IIngredient.of("cotton.net"), new FluidStack(RAPE_SEED_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.cotton.net")), 0).setRegistryName("rape_seed_oil_cotton"),
            new BarrelRecipe(IIngredient.of(RAPE_SEED_WATER.get(), 250), IIngredient.of("linen.net"), new FluidStack(RAPE_SEED_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.linen.net")), 0).setRegistryName("rape_seed_oil_linen"),
            new BarrelRecipe(IIngredient.of(RAPE_SEED_WATER.get(), 250), IIngredient.of("hemp.net"), new FluidStack(RAPE_SEED_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.hemp.net")), 0).setRegistryName("rape_seed_oil_hemp"),

            // Sunflower Seed
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 125), IIngredient.of("pasteSunflowerSeed"), new FluidStack(SUNFLOWER_SEED_WATER.get(), 125), ItemStack.EMPTY, 2 * ICalendar.TICKS_IN_HOUR).setRegistryName("sunflower_seed_water"),

            new BarrelRecipe(IIngredient.of(SUNFLOWER_SEED_WATER.get(), 250), IIngredient.of("jute.net"), new FluidStack(SUNFLOWER_SEED_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.jute.net")), 0).setRegistryName("sunflower_seed_oil_jute"),
            new BarrelRecipe(IIngredient.of(SUNFLOWER_SEED_WATER.get(), 250), IIngredient.of("sisal.net"), new FluidStack(SUNFLOWER_SEED_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.sisal.net")), 0).setRegistryName("sunflower_seed_oil_sisal"),
            new BarrelRecipe(IIngredient.of(SUNFLOWER_SEED_WATER.get(), 250), IIngredient.of("silk.net"), new FluidStack(SUNFLOWER_SEED_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.silk.net")), 0).setRegistryName("sunflower_seed_oil_silk"),
            new BarrelRecipe(IIngredient.of(SUNFLOWER_SEED_WATER.get(), 250), IIngredient.of("cotton.net"), new FluidStack(SUNFLOWER_SEED_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.cotton.net")), 0).setRegistryName("sunflower_seed_oil_cotton"),
            new BarrelRecipe(IIngredient.of(SUNFLOWER_SEED_WATER.get(), 250), IIngredient.of("linen.net"), new FluidStack(SUNFLOWER_SEED_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.linen.net")), 0).setRegistryName("sunflower_seed_oil_linen"),
            new BarrelRecipe(IIngredient.of(SUNFLOWER_SEED_WATER.get(), 250), IIngredient.of("hemp.net"), new FluidStack(SUNFLOWER_SEED_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.hemp.net")), 0).setRegistryName("sunflower_seed_oil_hemp"),

            // Opium Poppy Seed
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 125), IIngredient.of("pasteOpiumPoppySeed"), new FluidStack(OPIUM_POPPY_SEED_WATER.get(), 125), ItemStack.EMPTY, 2 * ICalendar.TICKS_IN_HOUR).setRegistryName("opium_poppy_seed_water"),

            new BarrelRecipe(IIngredient.of(OPIUM_POPPY_SEED_WATER.get(), 250), IIngredient.of("jute.net"), new FluidStack(OPIUM_POPPY_SEED_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.jute.net")), 0).setRegistryName("opium_poppy_seed_oil_jute"),
            new BarrelRecipe(IIngredient.of(OPIUM_POPPY_SEED_WATER.get(), 250), IIngredient.of("sisal.net"), new FluidStack(OPIUM_POPPY_SEED_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.sisal.net")), 0).setRegistryName("opium_poppy_seed_oil_sisal"),
            new BarrelRecipe(IIngredient.of(OPIUM_POPPY_SEED_WATER.get(), 250), IIngredient.of("silk.net"), new FluidStack(OPIUM_POPPY_SEED_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.silk.net")), 0).setRegistryName("opium_poppy_seed_oil_silk"),
            new BarrelRecipe(IIngredient.of(OPIUM_POPPY_SEED_WATER.get(), 250), IIngredient.of("cotton.net"), new FluidStack(OPIUM_POPPY_SEED_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.cotton.net")), 0).setRegistryName("opium_poppy_seed_oil_cotton"),
            new BarrelRecipe(IIngredient.of(OPIUM_POPPY_SEED_WATER.get(), 250), IIngredient.of("linen.net"), new FluidStack(OPIUM_POPPY_SEED_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.linen.net")), 0).setRegistryName("opium_poppy_seed_oil_linen"),
            new BarrelRecipe(IIngredient.of(OPIUM_POPPY_SEED_WATER.get(), 250), IIngredient.of("hemp.net"), new FluidStack(OPIUM_POPPY_SEED_OIL.get(), 25), new ItemStack((Item) IIngredient.of("dirty.hemp.net")), 0).setRegistryName("opium_poppy_seed_oil_hemp"),

            // Sugar Beet Water
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 125), IIngredient.of("mashedSugarBeet"), new FluidStack(SUGAR_BEET_WATER.get(), 125), ItemStack.EMPTY, 2 * ICalendar.TICKS_IN_HOUR).setRegistryName("sugar_beet_water"),

            new BarrelRecipe(IIngredient.of(SUGAR_BEET_WATER.get(), 250), IIngredient.of("jute.net"), new FluidStack(SUGAR_WATER.get(), 25), new ItemStack((Item) IIngredient.of("dirty.jute.net")), 0).setRegistryName("sugar_beet_water_jute"),
            new BarrelRecipe(IIngredient.of(SUGAR_BEET_WATER.get(), 250), IIngredient.of("sisal.net"), new FluidStack(SUGAR_WATER.get(), 25), new ItemStack((Item) IIngredient.of("dirty.sisal.net")), 0).setRegistryName("sugar_beet_water_sisal"),
            new BarrelRecipe(IIngredient.of(SUGAR_BEET_WATER.get(), 250), IIngredient.of("silk.net"), new FluidStack(SUGAR_WATER.get(), 25), new ItemStack((Item) IIngredient.of("dirty.silk.net")), 0).setRegistryName("sugar_beet_water_silk"),
            new BarrelRecipe(IIngredient.of(SUGAR_BEET_WATER.get(), 250), IIngredient.of("cotton.net"), new FluidStack(SUGAR_WATER.get(), 25), new ItemStack((Item) IIngredient.of("dirty.cotton.net")), 0).setRegistryName("sugar_beet_water_cotton"),
            new BarrelRecipe(IIngredient.of(SUGAR_BEET_WATER.get(), 250), IIngredient.of("linen.net"), new FluidStack(SUGAR_WATER.get(), 25), new ItemStack((Item) IIngredient.of("dirty.linen.net")), 0).setRegistryName("sugar_beet_water_linen"),
            new BarrelRecipe(IIngredient.of(SUGAR_BEET_WATER.get(), 250), IIngredient.of("hemp.net"), new FluidStack(SUGAR_WATER.get(), 25), new ItemStack((Item) IIngredient.of("dirty.hemp.net")), 0).setRegistryName("sugar_beet_water_hemp"),

            // Sugarcane Water
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 125), IIngredient.of("mashedSugarCane"), new FluidStack(SUGAR_CANE_WATER.get(), 125), ItemStack.EMPTY, 2 * ICalendar.TICKS_IN_HOUR).setRegistryName("sugar_cane_water"),

            new BarrelRecipe(IIngredient.of(SUGAR_CANE_WATER.get(), 250), IIngredient.of("jute.net"), new FluidStack(SUGAR_WATER.get(), 25), new ItemStack((Item) IIngredient.of("dirty.jute.net")), 0).setRegistryName("sugar_cane_water_jute"),
            new BarrelRecipe(IIngredient.of(SUGAR_CANE_WATER.get(), 250), IIngredient.of("sisal.net"), new FluidStack(SUGAR_WATER.get(), 25), new ItemStack((Item) IIngredient.of("dirty.sisal.net")), 0).setRegistryName("sugar_cane_water_sisal"),
            new BarrelRecipe(IIngredient.of(SUGAR_CANE_WATER.get(), 250), IIngredient.of("silk.net"), new FluidStack(SUGAR_WATER.get(), 25), new ItemStack((Item) IIngredient.of("dirty.silk.net")), 0).setRegistryName("sugar_cane_water_silk"),
            new BarrelRecipe(IIngredient.of(SUGAR_CANE_WATER.get(), 250), IIngredient.of("cotton.net"), new FluidStack(SUGAR_WATER.get(), 25), new ItemStack((Item) IIngredient.of("dirty.cotton.net")), 0).setRegistryName("sugar_cane_water_cotton"),
            new BarrelRecipe(IIngredient.of(SUGAR_CANE_WATER.get(), 250), IIngredient.of("linen.net"), new FluidStack(SUGAR_WATER.get(), 25), new ItemStack((Item) IIngredient.of("dirty.linen.net")), 0).setRegistryName("sugar_cane_water_linen"),
            new BarrelRecipe(IIngredient.of(SUGAR_CANE_WATER.get(), 250), IIngredient.of("hemp.net"), new FluidStack(SUGAR_WATER.get(), 25), new ItemStack((Item) IIngredient.of("dirty.hemp.net")), 0).setRegistryName("sugar_cane_water_hemp"),

            // Dirty Nets
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 125), IIngredient.of("dirty.sisal.net"), null, new ItemStack((Item) IIngredient.of("sisal.net")), ICalendar.TICKS_IN_HOUR).setRegistryName("clean_net_sisal"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 125), IIngredient.of("dirty.silk.net"), null, new ItemStack((Item) IIngredient.of("silk.net")), ICalendar.TICKS_IN_HOUR).setRegistryName("clean_net_silk"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 125), IIngredient.of("dirty.cotton.net"), null, new ItemStack((Item) IIngredient.of("cotton.net")), ICalendar.TICKS_IN_HOUR).setRegistryName("clean_net_cotton"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 125), IIngredient.of("dirty.linen.net"), null, new ItemStack((Item) IIngredient.of("linen.net")), ICalendar.TICKS_IN_HOUR).setRegistryName("clean_net_linen"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 125), IIngredient.of("dirty.hemp.net"), null, new ItemStack((Item) IIngredient.of("hemp.net")), ICalendar.TICKS_IN_HOUR).setRegistryName("clean_net_hemp"),

            // Sugary Fluids
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 125), IIngredient.of(Items.SUGAR), new FluidStack(SUGAR_WATER.get(), 125), ItemStack.EMPTY, 0).setRegistryName("sugar_water_from_sugar_fresh"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 125), IIngredient.of("dropHoney"), new FluidStack(HONEY_WATER.get(), 125), ItemStack.EMPTY, 0).setRegistryName("honey_water_from_drop_honey_fresh"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 125), IIngredient.of("itemHoney"), new FluidStack(HONEY_WATER.get(), 125), ItemStack.EMPTY, 0).setRegistryName("honey_water_from_item_honey_fresh"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 125), IIngredient.of("rawHoney"), new FluidStack(HONEY_WATER.get(), 125), ItemStack.EMPTY, 0).setRegistryName("honey_water_from_raw_honey_fresh"),
            // new BarrelRecipe(IIngredient.of(TFCFluids.FRESH_WATER.get(), 125), IIngredient.of(ItemsFL.HONEYCOMB), new FluidStack(TFCFluids.HONEY_WATER.get(), 125), ItemStack.EMPTY, 0).setRegistryName("honey_water_from_fl_raw_honey_fresh"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 125), IIngredient.of("materialHoneycomb"), new FluidStack(HONEY_WATER.get(), 125), ItemStack.EMPTY, 0).setRegistryName("honey_water_from_material_honeycomb_fresh"),

            //new BarrelRecipe(IIngredient.of(TFCFluids.SUGAR_WATER.get(), 125), null, null, new ItemStack(Items.SUGAR), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("sugar_from_sugar_water"),
            //new BarrelRecipe(IIngredient.of(TFCFluids.HONEY_WATER.get(), 125), null, null, new ItemStack(Items.SUGAR), 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("sugar_from_honey_water"),

            // Dyes
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 1000), IIngredient.of("cropAgave"), new FluidStack(getFluidFromDye(EnumDyeColor.GREEN).get(), 1000), ItemStack.EMPTY, ICalendar.TICKS_IN_HOUR).setRegistryName("green_dye_sisal"),
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 1000), IIngredient.of("cropIndigo"), new FluidStack(getFluidFromDye(EnumDyeColor.BLUE).get(), 1000), ItemStack.EMPTY, ICalendar.TICKS_IN_HOUR).setRegistryName("blue_dye_indigo"),
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 1000), IIngredient.of("cropMadder"), new FluidStack(getFluidFromDye(EnumDyeColor.RED).get(), 1000), ItemStack.EMPTY, ICalendar.TICKS_IN_HOUR).setRegistryName("red_dye_madder"),
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 1000), IIngredient.of("cropWeld"), new FluidStack(getFluidFromDye(EnumDyeColor.YELLOW).get(), 1000), ItemStack.EMPTY, ICalendar.TICKS_IN_HOUR).setRegistryName("yellow_dye_weld"),
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 1000), IIngredient.of("cropWoad"), new FluidStack(getFluidFromDye(EnumDyeColor.BLUE).get(), 1000), ItemStack.EMPTY, ICalendar.TICKS_IN_HOUR).setRegistryName("blue_dye_woad"),
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 1000), IIngredient.of("cropRape"), new FluidStack(getFluidFromDye(EnumDyeColor.BLUE).get(), 1000), ItemStack.EMPTY, ICalendar.TICKS_IN_HOUR).setRegistryName("blue_dye_rape"),
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 1000), IIngredient.of("boneCharred"), new FluidStack(getFluidFromDye(EnumDyeColor.BLACK).get(), 1000), ItemStack.EMPTY, ICalendar.TICKS_IN_HOUR).setRegistryName("black_dye_charred_bones"),
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 1000), IIngredient.of("dustBlackPearl"), new FluidStack(getFluidFromDye(EnumDyeColor.BLACK).get(), 1000), ItemStack.EMPTY, ICalendar.TICKS_IN_HOUR).setRegistryName("black_dye_black_pearl_powder"),
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 1000), IIngredient.of("dustPearl"), new FluidStack(getFluidFromDye(EnumDyeColor.PINK).get(), 1000), ItemStack.EMPTY, ICalendar.TICKS_IN_HOUR).setRegistryName("pink_dye_pearl_powder"),
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 1000), IIngredient.of("dustLogwood"), new FluidStack(getFluidFromDye(EnumDyeColor.PURPLE).get(), 1000), ItemStack.EMPTY, ICalendar.TICKS_IN_HOUR).setRegistryName("purple_dye_logwood_powder"),

            // Teas
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 200), IIngredient.of("driedWhiteTea", 2), new FluidStack(WHITE_TEA.get(), 200), ItemStack.EMPTY, 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("white_tea"),
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 200), IIngredient.of("driedGreenTea", 2), new FluidStack(GREEN_TEA.get(), 200), ItemStack.EMPTY, 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("green_tea"),
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 200), IIngredient.of("driedBlackTea", 2), new FluidStack(BLACK_TEA.get(), 200), ItemStack.EMPTY, 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("black_tea"),
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 200), IIngredient.of("driedChamomile", 2), new FluidStack(CHAMOMILE_TEA.get(), 200), ItemStack.EMPTY, 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("chamomile_tea"),
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 200), IIngredient.of("driedDandelion", 2), new FluidStack(DANDELION_TEA.get(), 200), ItemStack.EMPTY, 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("dandelion_tea"),
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 200), IIngredient.of("driedLabradorTea", 2), new FluidStack(LABRADOR_TEA.get(), 200), ItemStack.EMPTY, 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("labrador_tea"),

            // Coffee
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 200), IIngredient.of("roastedCoffee", 2), new FluidStack(COFFEE.get(), 200), ItemStack.EMPTY, 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("coffee"),

            // Firma Cola
            new BarrelRecipe(IIngredient.of(SUGAR_WATER.get(), 250), IIngredient.of("blendFirmaCola"), new FluidStack(FIRMA_COLA.get(), 1000), ItemStack.EMPTY, 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("firma_cola"),

            // Wort
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 500), IIngredient.of("hops"), new FluidStack(WORT.get(), 500), ItemStack.EMPTY, 8 * ICalendar.TICKS_IN_HOUR).setRegistryName("wort"),

            // Fermented Alcohol
            new BarrelRecipe(IIngredient.of(JUICE_AGAVE.get(), 500), IIngredient.of("yeast"), new FluidStack(AGAVE_WINE.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("sisal_wine"),
            new BarrelRecipe(IIngredient.of(JUICE_BANANA.get(), 500), IIngredient.of("yeast"), new FluidStack(BANANA_WINE.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("banana_wine"),
            new BarrelRecipe(IIngredient.of(JUICE_CHERRY.get(), 500), IIngredient.of("yeast"), new FluidStack(CHERRY_WINE.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("cherry_wine"),
            new BarrelRecipe(IIngredient.of(JUICE_GREEN_GRAPE.get(), 500), IIngredient.of("yeast"), new FluidStack(WHITE_WINE.get(), 500), new ItemStack(TFCItems.POMACE), 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("white_wine"),
            new BarrelRecipe(IIngredient.of(JUICE_JUNIPER.get(), 500), IIngredient.of("yeast"), new FluidStack(JUNIPER_WINE.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("juniper_wine"),
            new BarrelRecipe(IIngredient.of(JUICE_LEMON.get(), 500), IIngredient.of("yeast"), new FluidStack(LEMON_WINE.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("lemon_wine"),
            new BarrelRecipe(IIngredient.of(HONEY_WATER.get(), 500), IIngredient.of("yeast"), new FluidStack(MEAD.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("mead"),
            new BarrelRecipe(IIngredient.of(JUICE_ORANGE.get(), 500), IIngredient.of("yeast"), new FluidStack(ORANGE_WINE.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("orange_wine"),
            new BarrelRecipe(IIngredient.of(JUICE_PAPAYA.get(), 500), IIngredient.of("yeast"), new FluidStack(PAPAYA_WINE.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("papaya_wine"),
            new BarrelRecipe(IIngredient.of(JUICE_PEACH.get(), 500), IIngredient.of("yeast"), new FluidStack(PEACH_WINE.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("peach_wine"),
            new BarrelRecipe(IIngredient.of(JUICE_PEAR.get(), 500), IIngredient.of("yeast"), new FluidStack(PEAR_WINE.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("pear_wine"),
            new BarrelRecipe(IIngredient.of(JUICE_PLUM.get(), 500), IIngredient.of("yeast"), new FluidStack(PLUM_WINE.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("plum_wine"),
            new BarrelRecipe(IIngredient.of(JUICE_PURPLE_GRAPE.get(), 500), IIngredient.of("yeast"), new FluidStack(RED_WINE.get(), 500), new ItemStack(TFCItems.POMACE), 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("red_wine"),
            new BarrelRecipe(IIngredient.of(RICE_WATER.get(), 500), IIngredient.of("yeast"), new FluidStack(SAKE.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("sake_rice_water"),

            // Berry Wine
            new BarrelRecipe(IIngredient.of(JUICE_BLACKBERRY.get(), 500), IIngredient.of("yeast"), new FluidStack(BERRY_WINE.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("berry_wine_blackberry"),
            new BarrelRecipe(IIngredient.of(JUICE_BLUEBERRY.get(), 500), IIngredient.of("yeast"), new FluidStack(BERRY_WINE.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("berry_wine_blueberry"),
            new BarrelRecipe(IIngredient.of(JUICE_BUNCH_BERRY.get(), 500), IIngredient.of("yeast"), new FluidStack(BERRY_WINE.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("berry_wine_bunch_berry"),
            new BarrelRecipe(IIngredient.of(JUICE_CLOUD_BERRY.get(), 500), IIngredient.of("yeast"), new FluidStack(BERRY_WINE.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("berry_wine_cloud_berry"),
            new BarrelRecipe(IIngredient.of(JUICE_CRANBERRY.get(), 500), IIngredient.of("yeast"), new FluidStack(BERRY_WINE.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("berry_wine_cranberry"),
            new BarrelRecipe(IIngredient.of(JUICE_ELDERBERRY.get(), 500), IIngredient.of("yeast"), new FluidStack(BERRY_WINE.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("berry_wine_elderberry"),
            new BarrelRecipe(IIngredient.of(JUICE_GOOSEBERRY.get(), 500), IIngredient.of("yeast"), new FluidStack(BERRY_WINE.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("berry_wine_gooseberry"),
            new BarrelRecipe(IIngredient.of(JUICE_RASPBERRY.get(), 500), IIngredient.of("yeast"), new FluidStack(BERRY_WINE.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("berry_wine_raspberry"),
            new BarrelRecipe(IIngredient.of(JUICE_SNOW_BERRY.get(), 500), IIngredient.of("yeast"), new FluidStack(BERRY_WINE.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("berry_wine_snow_berry"),
            new BarrelRecipe(IIngredient.of(JUICE_STRAWBERRY.get(), 500), IIngredient.of("yeast"), new FluidStack(BERRY_WINE.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("berry_wine_strawberry"),
            new BarrelRecipe(IIngredient.of(JUICE_WINTERGREEN_BERRY.get(), 500), IIngredient.of("yeast"), new FluidStack(BERRY_WINE.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("berry_wine_wintergreen_berry"),

            // "Distilled" Alcohol
            new BarrelRecipe(IIngredient.of(AGAVE_WINE.get(), 500), IIngredient.of(Items.SUGAR), new FluidStack(TEQUILA.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("tequila"),
            new BarrelRecipe(IIngredient.of(BANANA_WINE.get(), 500), IIngredient.of(Items.SUGAR), new FluidStack(BANANA_BRANDY.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("banana_brandy"),
            new BarrelRecipe(IIngredient.of(BERRY_WINE.get(), 500), IIngredient.of(Items.SUGAR), new FluidStack(BERRY_BRANDY.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("berry_brandy"),
            new BarrelRecipe(IIngredient.of(CIDER.get(), 500), IIngredient.of(Items.SUGAR), new FluidStack(CALVADOS.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("calvados"),
            new BarrelRecipe(IIngredient.of(CHERRY_WINE.get(), 500), IIngredient.of(Items.SUGAR), new FluidStack(CHERRY_BRANDY.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("cherry_brandy"),
            new BarrelRecipe(IIngredient.of(JUNIPER_WINE.get(), 500), IIngredient.of(Items.SUGAR), new FluidStack(GIN.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("gin"),
            new BarrelRecipe(IIngredient.of(LEMON_WINE.get(), 500), IIngredient.of(Items.SUGAR), new FluidStack(LEMON_BRANDY.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("lemon_brandy"),
            new BarrelRecipe(IIngredient.of(ORANGE_WINE.get(), 500), IIngredient.of(Items.SUGAR), new FluidStack(ORANGE_BRANDY.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("orange_brandy"),
            new BarrelRecipe(IIngredient.of(PAPAYA_WINE.get(), 500), IIngredient.of(Items.SUGAR), new FluidStack(PAPAYA_BRANDY.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("papaya_brandy"),
            new BarrelRecipe(IIngredient.of(PEACH_WINE.get(), 500), IIngredient.of(Items.SUGAR), new FluidStack(PEACH_BRANDY.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("peach_brandy"),
            new BarrelRecipe(IIngredient.of(PEAR_WINE.get(), 500), IIngredient.of(Items.SUGAR), new FluidStack(PEAR_BRANDY.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("pear_brandy"),
            new BarrelRecipe(IIngredient.of(PLUM_WINE.get(), 500), IIngredient.of(Items.SUGAR), new FluidStack(PLUM_BRANDY.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("plum_brandy"),
            new BarrelRecipe(IIngredient.of(RED_WINE.get(), 500), IIngredient.of(Items.SUGAR), new FluidStack(BRANDY.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("brandy"),
            new BarrelRecipe(IIngredient.of(SAKE.get(), 500), IIngredient.of(Items.SUGAR), new FluidStack(SHOCHU.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("shochu"),
            new BarrelRecipe(IIngredient.of(WHITE_WINE.get(), 500), IIngredient.of(Items.SUGAR), new FluidStack(COGNAC.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("cognac"),
            new BarrelRecipe(IIngredient.of(VODKA.get(), 500), IIngredient.of("pomace"), new FluidStack(GRAPPA.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("grappa"),

            // Malted Grain
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 200), IIngredient.of("grainBarley"), null, new ItemStack(TFCItems.MALT_BARLEY), 4 * ICalendar.TICKS_IN_HOUR).setRegistryName("malt_barley"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 200), IIngredient.of("grainMaize"), null, new ItemStack(TFCItems.MALT_CORN), 4 * ICalendar.TICKS_IN_HOUR).setRegistryName("malt_corn"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 200), IIngredient.of("grainRye"), null, new ItemStack(TFCItems.MALT_RYE), 4 * ICalendar.TICKS_IN_HOUR).setRegistryName("malt_rye"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 200), IIngredient.of("grainRice"), null, new ItemStack(TFCItems.MALT_RICE), 4 * ICalendar.TICKS_IN_HOUR).setRegistryName("malt_rice"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 200), IIngredient.of("grainWheat"), null, new ItemStack(TFCItems.MALT_WHEAT), 4 * ICalendar.TICKS_IN_HOUR).setRegistryName("malt_wheat"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 200), IIngredient.of("grainAmaranth"), null, new ItemStack(TFCItems.MALT_AMARANTH), 4 * ICalendar.TICKS_IN_HOUR).setRegistryName("malt_amaranth"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 200), IIngredient.of("grainBuckwheat"), null, new ItemStack(TFCItems.MALT_BUCKWHEAT), 4 * ICalendar.TICKS_IN_HOUR).setRegistryName("malt_buckwheat"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 200), IIngredient.of("grainFonio"), null, new ItemStack(TFCItems.MALT_FONIO), 4 * ICalendar.TICKS_IN_HOUR).setRegistryName("malt_fonio"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 200), IIngredient.of("grainMillet"), null, new ItemStack(TFCItems.MALT_MILLET), 4 * ICalendar.TICKS_IN_HOUR).setRegistryName("malt_millet"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 200), IIngredient.of("grainQuinoa"), null, new ItemStack(TFCItems.MALT_QUINOA), 4 * ICalendar.TICKS_IN_HOUR).setRegistryName("malt_quinoa"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 200), IIngredient.of("grainSpelt"), null, new ItemStack(TFCItems.MALT_SPELT), 4 * ICalendar.TICKS_IN_HOUR).setRegistryName("malt_spelt"),

            // Beer
            new BarrelRecipe(IIngredient.of(WORT.get(), 500), IIngredient.of("maltBarley"), new FluidStack(BEER_BARLEY.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("beer_barley"),
            new BarrelRecipe(IIngredient.of(WORT.get(), 500), IIngredient.of("maltCorn"), new FluidStack(BEER_CORN.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("beer_corn"),
            new BarrelRecipe(IIngredient.of(WORT.get(), 500), IIngredient.of("maltRye"), new FluidStack(BEER_RYE.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("beer_rye"),
            new BarrelRecipe(IIngredient.of(WORT.get(), 500), IIngredient.of("maltWheat"), new FluidStack(BEER_WHEAT.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("beer_wheat"),
            new BarrelRecipe(IIngredient.of(WORT.get(), 500), IIngredient.of("maltAmaranth"), new FluidStack(BEER_AMARANTH.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("beer_amaranth"),
            new BarrelRecipe(IIngredient.of(WORT.get(), 500), IIngredient.of("maltBuckwheat"), new FluidStack(BEER_BUCKWHEAT.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("beer_buckwheat"),
            new BarrelRecipe(IIngredient.of(WORT.get(), 500), IIngredient.of("maltFonio"), new FluidStack(BEER_FONIO.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("beer_fonio"),
            new BarrelRecipe(IIngredient.of(WORT.get(), 500), IIngredient.of("maltMillet"), new FluidStack(BEER_MILLET.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("beer_millet"),
            new BarrelRecipe(IIngredient.of(WORT.get(), 500), IIngredient.of("maltQuinoa"), new FluidStack(BEER_QUINOA.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("beer_quinoa"),
            new BarrelRecipe(IIngredient.of(WORT.get(), 500), IIngredient.of("maltSpelt"), new FluidStack(BEER_SPELT.get(), 500), ItemStack.EMPTY, 72 * ICalendar.TICKS_IN_HOUR).setRegistryName("beer_spelt"),

            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 500), IIngredient.of("rice"), new FluidStack(RICE_WATER.get(), 500), ItemStack.EMPTY, 2 * ICalendar.TICKS_IN_HOUR).setRegistryName("rice_water"),
            //new BarrelRecipe(IIngredient.of(TFCFluids.FRESH_WATER.get(), 500), IIngredient.of("wildRice"), new FluidStack(TFCFluids.RICE_WATER.get(), 500), ItemStack.EMPTY, 2 * ICalendar.TICKS_IN_HOUR).setRegistryName("wild_rice_water"),

            // Kaolinite Clay
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 100), IIngredient.of("dustKaolinite"), null, new ItemStack(TFCItems.KAOLINITE_CLAY), 0).setRegistryName("kaolinite_clay"),

            // Special Clay Washing
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 100), IIngredient.of("clayEarthenware"), null, new ItemStack(Items.CLAY_BALL), 0).setRegistryName("earthenware_clay_wash"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 100), IIngredient.of("clayKaolinite"), null, new ItemStack(Items.CLAY_BALL), 0).setRegistryName("kaolinite_clay_wash"),
            new BarrelRecipe(IIngredient.of(FRESH_WATER.get(), 100), IIngredient.of("clayStoneware"), null, new ItemStack(Items.CLAY_BALL), 0).setRegistryName("stoneware_clay_wash"),

            // Silk Worm Stuff
            new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 100), IIngredient.of("cocoonSilkWorm"), null, new ItemStack(TFCItems.SILK_WORM_COCOON_BOILED), 250).setRegistryName("boiled_cocoon"),

            // Cooling
            new BarrelRecipeTemperature(IIngredient.of(DISTILLED_WATER.get(), 1), 50).setRegistryName("distilled_water_cooling")

        );

        for (Food food : new Food[] {Food.SALAD_DAIRY, Food.SALAD_FRUIT, Food.SALAD_GRAIN, Food.SALAD_MEAT, Food.SALAD_VEGETABLE, Food.SOUP_DAIRY, Food.SOUP_FRUIT, Food.SOUP_GRAIN, Food.SOUP_MEAT, Food.SOUP_VEGETABLE})
        {
            event.getRegistry().register(new BarrelRecipeDynamicBowlFood(IIngredient.of(FluidRegistry.WATER, 200), IIngredient.of(TFCItemFood.get(food)), 0).setRegistryName(food.name().toLowerCase() + "_cleaning"));
        }

        //The many many many recipes that is dye. This assumes that the standard meta values for colored objects are followed.
        for (EnumDyeColor dyeColor : EnumDyeColor.values())
        {
            Fluid fluid = getFluidFromDye(dyeColor).get();
            String dyeName = dyeColor == EnumDyeColor.SILVER ? "light_gray" : dyeColor.getName();
            int dyeMeta = dyeColor.getMetadata();
            event.getRegistry().registerAll(
                // Dye fluid
                new BarrelRecipe(IIngredient.of(HOT_WATER.get(), 1000), IIngredient.of(OreDictionaryHelper.toString("dye_" + dyeName)), new FluidStack(getFluidFromDye(dyeColor).get(), 1000), ItemStack.EMPTY, ICalendar.TICKS_IN_HOUR).setRegistryName(dyeName),
                // Vanilla dye-able items
                new BarrelRecipe(IIngredient.of(fluid, 125), IIngredient.of("woolWhite"), null, new ItemStack(Blocks.WOOL, 1, dyeMeta), ICalendar.TICKS_IN_HOUR).setRegistryName("wool_" + dyeName),
                new BarrelRecipe(IIngredient.of(fluid, 25), IIngredient.of(new ItemStack(Blocks.CARPET, 1, 0)), null, new ItemStack(Blocks.CARPET, 1, dyeMeta), ICalendar.TICKS_IN_HOUR).setRegistryName("carpet_" + dyeName),
                new BarrelRecipe(IIngredient.of(fluid, 125), IIngredient.of(new ItemStack(Items.BED, 1, 0)), null, new ItemStack(Items.BED, 1, dyeMeta), ICalendar.TICKS_IN_HOUR).setRegistryName("bed_" + dyeName),
                new BarrelRecipe(IIngredient.of(fluid, 125), IIngredient.of(Blocks.HARDENED_CLAY), null, new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, dyeMeta), ICalendar.TICKS_IN_HOUR).setRegistryName("terracotta_" + dyeName),
                new BarrelRecipe(IIngredient.of(fluid, 125), IIngredient.of(Blocks.GLASS), null, new ItemStack(Blocks.STAINED_GLASS, 1, dyeMeta), ICalendar.TICKS_IN_HOUR).setRegistryName("glass_" + dyeName),
                new BarrelRecipe(IIngredient.of(fluid, 125), IIngredient.of(Blocks.GLASS_PANE), null, new ItemStack(Blocks.STAINED_GLASS_PANE, 1, dyeMeta), ICalendar.TICKS_IN_HOUR).setRegistryName("glass_pane_" + dyeName),
                // Glazed Vessels
                new BarrelRecipe(IIngredient.of(fluid, 125), IIngredient.of(TFCItems.UNFIRED_VESSEL), null, new ItemStack(TFCItems.UNFIRED_VESSEL_GLAZED, 1, 15 - dyeMeta), ICalendar.TICKS_IN_HOUR).setRegistryName("glazed_vessel_" + dyeName),
                // Concrete (vanilla + aggregate)
                new BarrelRecipe(IIngredient.of(fluid, 125), IIngredient.of(new ItemStack(Blocks.CONCRETE_POWDER, 1, 0)), null, new ItemStack(Blocks.CONCRETE_POWDER, 1, dyeMeta), ICalendar.TICKS_IN_HOUR).setRegistryName("concrete_" + dyeName),
                new BarrelRecipe(IIngredient.of(fluid, 125), IIngredient.of(TFCBlocks.AGGREGATE), null, new ItemStack(Blocks.CONCRETE_POWDER, 1, dyeMeta), ICalendar.TICKS_IN_HOUR).setRegistryName("aggregate_" + dyeName),
                // Alabaster
                new BarrelRecipe(IIngredient.of(fluid, 125), IIngredient.of(TFCBlocks.ALABASTER_BRICKS_PLAIN), null, new ItemStack(TFCBlockRockDecorative.ALABASTER_BRICKS.get(dyeColor)), ICalendar.TICKS_IN_HOUR).setRegistryName("alabaster_bricks_" + dyeColor.getName()),
                new BarrelRecipe(IIngredient.of(fluid, 125), IIngredient.of(TFCBlocks.ALABASTER_RAW_PLAIN), null, new ItemStack(TFCBlockRockDecorative.ALABASTER_RAW.get(dyeColor)), ICalendar.TICKS_IN_HOUR).setRegistryName("alabaster_raw_" + dyeColor.getName()),
                new BarrelRecipe(IIngredient.of(fluid, 125), IIngredient.of(TFCBlocks.ALABASTER_POLISHED_PLAIN), null, new ItemStack(TFCBlockRockDecorative.ALABASTER_POLISHED.get(dyeColor)), ICalendar.TICKS_IN_HOUR).setRegistryName("alabaster_polished_" + dyeColor.getName())
            );
        }
        // Un-dyeing Recipes
        event.getRegistry().registerAll(
            // Vanilla dye-able items
            new BarrelRecipe(IIngredient.of(LYE.get(), 125), IIngredient.of("wool"), null, new ItemStack(Blocks.WOOL, 1, 0), ICalendar.TICKS_IN_HOUR).setRegistryName("wool_undo"),
            new BarrelRecipe(IIngredient.of(LYE.get(), 25), IIngredient.of("carpet"), null, new ItemStack(Blocks.CARPET, 1, 0), ICalendar.TICKS_IN_HOUR).setRegistryName("carpet_undo"),
            new BarrelRecipe(IIngredient.of(LYE.get(), 125), IIngredient.of("bed"), null, new ItemStack(Items.BED, 1, 0), ICalendar.TICKS_IN_HOUR).setRegistryName("bed_undo"),
            new BarrelRecipe(IIngredient.of(LYE.get(), 125), IIngredient.of("terracotta"), null, new ItemStack(Blocks.HARDENED_CLAY), ICalendar.TICKS_IN_HOUR).setRegistryName("terracotta_undo"),
            new BarrelRecipe(IIngredient.of(LYE.get(), 125), IIngredient.of("blockGlass"), null, new ItemStack(Blocks.GLASS), ICalendar.TICKS_IN_HOUR).setRegistryName("glass_undo"),
            new BarrelRecipe(IIngredient.of(LYE.get(), 125), IIngredient.of("paneGlass"), null, new ItemStack(Blocks.GLASS_PANE), ICalendar.TICKS_IN_HOUR).setRegistryName("glass_pane_undo"),
            // Concrete
            new BarrelRecipe(IIngredient.of(LYE.get(), 125), IIngredient.of("powderConcrete"), null, new ItemStack(TFCBlocks.AGGREGATE), ICalendar.TICKS_IN_HOUR).setRegistryName("concrete_undo"),
            // Alabaster
            new BarrelRecipe(IIngredient.of(LYE.get(), 125), IIngredient.of("alabasterBricks"), null, new ItemStack(TFCBlocks.ALABASTER_BRICKS_PLAIN), ICalendar.TICKS_IN_HOUR).setRegistryName("alabaster_bricks_undo"),
            new BarrelRecipe(IIngredient.of(LYE.get(), 125), IIngredient.of("alabasterRaw"), null, new ItemStack(TFCBlocks.ALABASTER_RAW_PLAIN), ICalendar.TICKS_IN_HOUR).setRegistryName("alabaster_raw_undo"),
            new BarrelRecipe(IIngredient.of(LYE.get(), 125), IIngredient.of("alabasterPolished"), null, new ItemStack(TFCBlocks.ALABASTER_POLISHED_PLAIN), ICalendar.TICKS_IN_HOUR).setRegistryName("alabaster_polished_undo")
        );
        // Dye combinations.
        event.getRegistry().registerAll(
            //Orange
            new BarrelRecipeFluidMixing(IIngredient.of(getFluidFromDye(EnumDyeColor.RED).get(), 1), new IngredientFluidItem(getFluidFromDye(EnumDyeColor.YELLOW).get(), 1), new FluidStack(getFluidFromDye(EnumDyeColor.ORANGE).get(), 2), 0).setRegistryName("orange_dye_red_yellow_liquid"),
            new BarrelRecipeFluidMixing(IIngredient.of(getFluidFromDye(EnumDyeColor.YELLOW).get(), 1), new IngredientFluidItem(getFluidFromDye(EnumDyeColor.RED).get(), 1), new FluidStack(getFluidFromDye(EnumDyeColor.ORANGE).get(), 2), 0).setRegistryName("orange_dye_yellow_red_liquid"),
            new BarrelRecipe(IIngredient.of(getFluidFromDye(EnumDyeColor.RED).get(), 1000), IIngredient.of("dyeYellow"), new FluidStack(getFluidFromDye(EnumDyeColor.ORANGE).get(), 1000), ItemStack.EMPTY, 0).setRegistryName("orange_dye_red_yellow_solid"),
            new BarrelRecipe(IIngredient.of(getFluidFromDye(EnumDyeColor.YELLOW).get(), 1000), IIngredient.of("dyeRed"), new FluidStack(getFluidFromDye(EnumDyeColor.ORANGE).get(), 1000), ItemStack.EMPTY, 0).setRegistryName("orange_dye_yellow_red_solid"),
            //Light Blue
            new BarrelRecipeFluidMixing(IIngredient.of(getFluidFromDye(EnumDyeColor.BLUE).get(), 1), new IngredientFluidItem(getFluidFromDye(EnumDyeColor.WHITE).get(), 1), new FluidStack(getFluidFromDye(EnumDyeColor.LIGHT_BLUE).get(), 2), 0).setRegistryName("light_blue_dye_blue_white_liquid"),
            new BarrelRecipeFluidMixing(IIngredient.of(getFluidFromDye(EnumDyeColor.WHITE).get(), 1), new IngredientFluidItem(getFluidFromDye(EnumDyeColor.BLUE).get(), 1), new FluidStack(getFluidFromDye(EnumDyeColor.LIGHT_BLUE).get(), 2), 0).setRegistryName("light_blue_dye_white_blue_liquid"),
            new BarrelRecipe(IIngredient.of(getFluidFromDye(EnumDyeColor.BLUE).get(), 1000), IIngredient.of("dyeWhite"), new FluidStack(getFluidFromDye(EnumDyeColor.LIGHT_BLUE).get(), 1000), ItemStack.EMPTY, 0).setRegistryName("light_blue_dye_blue_white_solid"),
            new BarrelRecipe(IIngredient.of(getFluidFromDye(EnumDyeColor.WHITE).get(), 1000), IIngredient.of("dyeBlue"), new FluidStack(getFluidFromDye(EnumDyeColor.LIGHT_BLUE).get(), 1000), ItemStack.EMPTY, 0).setRegistryName("light_blue_dye_white_blue_solid"),
            //Magenta
            new BarrelRecipeFluidMixing(IIngredient.of(getFluidFromDye(EnumDyeColor.PURPLE).get(), 1), new IngredientFluidItem(getFluidFromDye(EnumDyeColor.PINK).get(), 1), new FluidStack(getFluidFromDye(EnumDyeColor.MAGENTA).get(), 2), 0).setRegistryName("magenta_dye_purple_pink_liquid"),
            new BarrelRecipeFluidMixing(IIngredient.of(getFluidFromDye(EnumDyeColor.PINK).get(), 1), new IngredientFluidItem(getFluidFromDye(EnumDyeColor.PURPLE).get(), 1), new FluidStack(getFluidFromDye(EnumDyeColor.MAGENTA).get(), 2), 0).setRegistryName("magenta_dye_pink_purple_liquid"),
            new BarrelRecipe(IIngredient.of(getFluidFromDye(EnumDyeColor.PURPLE).get(), 1000), IIngredient.of("dyePink"), new FluidStack(getFluidFromDye(EnumDyeColor.MAGENTA).get(), 1000), ItemStack.EMPTY, 0).setRegistryName("magenta_dye_purple_pink_solid"),
            new BarrelRecipe(IIngredient.of(getFluidFromDye(EnumDyeColor.PINK).get(), 1000), IIngredient.of("dyePurple"), new FluidStack(getFluidFromDye(EnumDyeColor.MAGENTA).get(), 1000), ItemStack.EMPTY, 0).setRegistryName("magenta_dye_pink_purple_solid"),
            //Pink
            new BarrelRecipeFluidMixing(IIngredient.of(getFluidFromDye(EnumDyeColor.RED).get(), 1), new IngredientFluidItem(getFluidFromDye(EnumDyeColor.WHITE).get(), 1), new FluidStack(getFluidFromDye(EnumDyeColor.PINK).get(), 2), 0).setRegistryName("pink_dye_red_white_liquid"),
            new BarrelRecipeFluidMixing(IIngredient.of(getFluidFromDye(EnumDyeColor.WHITE).get(), 1), new IngredientFluidItem(getFluidFromDye(EnumDyeColor.RED).get(), 1), new FluidStack(getFluidFromDye(EnumDyeColor.PINK).get(), 2), 0).setRegistryName("pink_dye_white_red_liquid"),
            new BarrelRecipe(IIngredient.of(getFluidFromDye(EnumDyeColor.RED).get(), 1000), IIngredient.of("dyeWhite"), new FluidStack(getFluidFromDye(EnumDyeColor.PINK).get(), 1000), ItemStack.EMPTY, 0).setRegistryName("pink_dye_red_white_solid"),
            new BarrelRecipe(IIngredient.of(getFluidFromDye(EnumDyeColor.WHITE).get(), 1000), IIngredient.of("dyeRed"), new FluidStack(getFluidFromDye(EnumDyeColor.PINK).get(), 1000), ItemStack.EMPTY, 0).setRegistryName("pink_dye_white_red_solid"),
            //Light Gray
            new BarrelRecipeFluidMixing(IIngredient.of(getFluidFromDye(EnumDyeColor.WHITE).get(), 1), new IngredientFluidItem(getFluidFromDye(EnumDyeColor.GRAY).get(), 1), new FluidStack(getFluidFromDye(EnumDyeColor.SILVER).get(), 2), 0).setRegistryName("light_gray_dye_white_gray_liquid"),
            new BarrelRecipeFluidMixing(IIngredient.of(getFluidFromDye(EnumDyeColor.WHITE).get(), 2), new IngredientFluidItem(getFluidFromDye(EnumDyeColor.BLACK).get(), 1), new FluidStack(getFluidFromDye(EnumDyeColor.SILVER).get(), 3), 0).setRegistryName("light_gray_dye_white_black_liquid"),
            new BarrelRecipeFluidMixing(IIngredient.of(getFluidFromDye(EnumDyeColor.GRAY).get(), 1), new IngredientFluidItem(getFluidFromDye(EnumDyeColor.WHITE).get(), 1), new FluidStack(getFluidFromDye(EnumDyeColor.SILVER).get(), 2), 0).setRegistryName("light_gray_dye_gray_white_liquid"),
            new BarrelRecipeFluidMixing(IIngredient.of(getFluidFromDye(EnumDyeColor.BLACK).get(), 1), new IngredientFluidItem(getFluidFromDye(EnumDyeColor.WHITE).get(), 2), new FluidStack(getFluidFromDye(EnumDyeColor.SILVER).get(), 3), 0).setRegistryName("light_gray_dye_black_white_liquid"),
            new BarrelRecipe(IIngredient.of(getFluidFromDye(EnumDyeColor.WHITE).get(), 1000), IIngredient.of("dyeGray"), new FluidStack(getFluidFromDye(EnumDyeColor.SILVER).get(), 1000), ItemStack.EMPTY, 0).setRegistryName("light_gray_dye_white_gray_solid"),
            new BarrelRecipe(IIngredient.of(getFluidFromDye(EnumDyeColor.WHITE).get(), 2000), IIngredient.of("dyeBlack"), new FluidStack(getFluidFromDye(EnumDyeColor.SILVER).get(), 1000), ItemStack.EMPTY, 0).setRegistryName("light_gray_dye_white_black_solid"),
            new BarrelRecipe(IIngredient.of(getFluidFromDye(EnumDyeColor.GRAY).get(), 1000), IIngredient.of("dyeWhite"), new FluidStack(getFluidFromDye(EnumDyeColor.SILVER).get(), 1000), ItemStack.EMPTY, 0).setRegistryName("light_gray_dye_gray_white_solid"),
            new BarrelRecipe(IIngredient.of(getFluidFromDye(EnumDyeColor.BLACK).get(), 500), IIngredient.of("dyeWhite"), new FluidStack(getFluidFromDye(EnumDyeColor.SILVER).get(), 1000), ItemStack.EMPTY, 0).setRegistryName("light_gray_dye_black_white_solid"),
            //Lime
            new BarrelRecipeFluidMixing(IIngredient.of(getFluidFromDye(EnumDyeColor.GREEN).get(), 1), new IngredientFluidItem(getFluidFromDye(EnumDyeColor.WHITE).get(), 1), new FluidStack(getFluidFromDye(EnumDyeColor.LIME).get(), 2), 0).setRegistryName("lime_dye_green_white_liquid"),
            new BarrelRecipeFluidMixing(IIngredient.of(getFluidFromDye(EnumDyeColor.WHITE).get(), 1), new IngredientFluidItem(getFluidFromDye(EnumDyeColor.GREEN).get(), 1), new FluidStack(getFluidFromDye(EnumDyeColor.LIME).get(), 2), 0).setRegistryName("lime_dye_white_green_liquid"),
            new BarrelRecipe(IIngredient.of(getFluidFromDye(EnumDyeColor.GREEN).get(), 1000), IIngredient.of("dyeWhite"), new FluidStack(getFluidFromDye(EnumDyeColor.LIME).get(), 1000), ItemStack.EMPTY, 0).setRegistryName("lime_dye_green_white_solid"),
            new BarrelRecipe(IIngredient.of(getFluidFromDye(EnumDyeColor.WHITE).get(), 1000), IIngredient.of("dyeGreen"), new FluidStack(getFluidFromDye(EnumDyeColor.LIME).get(), 1000), ItemStack.EMPTY, 0).setRegistryName("lime_dye_white_green_solid"),
            //Cyan
            new BarrelRecipeFluidMixing(IIngredient.of(getFluidFromDye(EnumDyeColor.GREEN).get(), 1), new IngredientFluidItem(getFluidFromDye(EnumDyeColor.BLUE).get(), 1), new FluidStack(getFluidFromDye(EnumDyeColor.CYAN).get(), 2), 0).setRegistryName("cyan_dye_green_blue_liquid"),
            new BarrelRecipeFluidMixing(IIngredient.of(getFluidFromDye(EnumDyeColor.BLUE).get(), 1), new IngredientFluidItem(getFluidFromDye(EnumDyeColor.GREEN).get(), 1), new FluidStack(getFluidFromDye(EnumDyeColor.CYAN).get(), 2), 0).setRegistryName("cyan_dye_blue_green_liquid"),
            new BarrelRecipe(IIngredient.of(getFluidFromDye(EnumDyeColor.GREEN).get(), 1000), IIngredient.of("dyeBlue"), new FluidStack(getFluidFromDye(EnumDyeColor.CYAN).get(), 1000), ItemStack.EMPTY, 0).setRegistryName("cyan_dye_green_blue_solid"),
            new BarrelRecipe(IIngredient.of(getFluidFromDye(EnumDyeColor.BLUE).get(), 1000), IIngredient.of("dyeGreen"), new FluidStack(getFluidFromDye(EnumDyeColor.CYAN).get(), 1000), ItemStack.EMPTY, 0).setRegistryName("cyan_dye_blue_green_solid"),
            //Purple
            new BarrelRecipeFluidMixing(IIngredient.of(getFluidFromDye(EnumDyeColor.RED).get(), 1), new IngredientFluidItem(getFluidFromDye(EnumDyeColor.BLUE).get(), 1), new FluidStack(getFluidFromDye(EnumDyeColor.PURPLE).get(), 2), 0).setRegistryName("purple_dye_red_blue_liquid"),
            new BarrelRecipeFluidMixing(IIngredient.of(getFluidFromDye(EnumDyeColor.BLUE).get(), 1), new IngredientFluidItem(getFluidFromDye(EnumDyeColor.RED).get(), 1), new FluidStack(getFluidFromDye(EnumDyeColor.PURPLE).get(), 2), 0).setRegistryName("purple_dye_blue_red_liquid"),
            new BarrelRecipe(IIngredient.of(getFluidFromDye(EnumDyeColor.RED).get(), 1000), IIngredient.of("dyeBlue"), new FluidStack(getFluidFromDye(EnumDyeColor.PURPLE).get(), 1000), ItemStack.EMPTY, 0).setRegistryName("purple_dye_red_blue_solid"),
            new BarrelRecipe(IIngredient.of(getFluidFromDye(EnumDyeColor.BLUE).get(), 1000), IIngredient.of("dyeRed"), new FluidStack(getFluidFromDye(EnumDyeColor.PURPLE).get(), 1000), ItemStack.EMPTY, 0).setRegistryName("purple_dye_blue_red_solid"),
            //Gray
            new BarrelRecipeFluidMixing(IIngredient.of(getFluidFromDye(EnumDyeColor.BLACK).get(), 1), new IngredientFluidItem(getFluidFromDye(EnumDyeColor.WHITE).get(), 1), new FluidStack(getFluidFromDye(EnumDyeColor.GRAY).get(), 2), 0).setRegistryName("gray_dye_black_white_liquid"),
            new BarrelRecipeFluidMixing(IIngredient.of(getFluidFromDye(EnumDyeColor.WHITE).get(), 1), new IngredientFluidItem(getFluidFromDye(EnumDyeColor.BLACK).get(), 1), new FluidStack(getFluidFromDye(EnumDyeColor.GRAY).get(), 2), 0).setRegistryName("gray_dye_white_black_liquid"),
            new BarrelRecipe(IIngredient.of(getFluidFromDye(EnumDyeColor.BLACK).get(), 1000), IIngredient.of("dyeWhite"), new FluidStack(getFluidFromDye(EnumDyeColor.GRAY).get(), 1000), ItemStack.EMPTY, 0).setRegistryName("gray_dye_black_white_solid"),
            new BarrelRecipe(IIngredient.of(getFluidFromDye(EnumDyeColor.WHITE).get(), 1000), IIngredient.of("dyeBlack"), new FluidStack(getFluidFromDye(EnumDyeColor.GRAY).get(), 1000), ItemStack.EMPTY, 0).setRegistryName("gray_dye_white_black_solid")
        );
    }

    //@SubscribeEvent
    public static void onRegisterLoomRecipeEvent(RegistryEvent.Register<LoomRecipe> event)
    {
        IForgeRegistry<LoomRecipe> r = event.getRegistry();

        r.registerAll(
            new LoomRecipe(new ResourceLocation(MOD_ID, "burlap_cloth"), IIngredient.of("jute.string", 12), new ItemStack((Item) IIngredient.of("jute.cloth")), 12, new ResourceLocation(MOD_ID, "textures/blocks/devices/loom/product/burlap.png")),
            new LoomRecipe(new ResourceLocation(MOD_ID, "wool_cloth"), IIngredient.of(TFCItems.WOOL_YARN, 16), new ItemStack((Item) IIngredient.of("wool.cloth")), 16, new ResourceLocation("minecraft", "textures/blocks/wool_colored_white.png")),
            new LoomRecipe(new ResourceLocation(MOD_ID, "silk_cloth"), IIngredient.of(Items.STRING, 24), new ItemStack((Item) IIngredient.of("silk.cloth")), 24, new ResourceLocation("minecraft", "textures/blocks/wool_colored_white.png")),
            new LoomRecipe(new ResourceLocation(MOD_ID, "cotton_cloth"), IIngredient.of("cotton.string", 12), new ItemStack((Item) IIngredient.of("cotton.cloth")), 12, new ResourceLocation(MOD_ID, "textures/blocks/devices/loom/product/cotton.png")),
            new LoomRecipe(new ResourceLocation(MOD_ID, "hemp_cloth"), IIngredient.of("hemp.string", 12), new ItemStack((Item) IIngredient.of("hemp.cloth")), 12, new ResourceLocation(MOD_ID, "textures/blocks/devices/loom/product/hemp.png")),
            new LoomRecipe(new ResourceLocation(MOD_ID, "linen_cloth"), IIngredient.of("linen.string", 12), new ItemStack((Item) IIngredient.of("linen.cloth")), 12, new ResourceLocation(MOD_ID, "textures/blocks/devices/loom/product/linen.png")),
            new LoomRecipe(new ResourceLocation(MOD_ID, "sisal_cloth"), IIngredient.of("sisal.string", 12), new ItemStack((Item) IIngredient.of("sisal.cloth")), 12, new ResourceLocation(MOD_ID, "textures/blocks/devices/loom/product/sisal.png")),
//            new LoomRecipe(new ResourceLocation(MOD_ID, "yucca_canvas"), IIngredient.of(TFCItems.YUCCA_STRING, 12), new ItemStack(TFCItems.YUCCA_CANVAS), 12, new ResourceLocation(MOD_ID, "textures/blocks/devices/loom/product/yucca.png")),

            new LoomRecipe(new ResourceLocation(MOD_ID, "wool_block"), IIngredient.of("wool.cloth", 4), new ItemStack(Blocks.WOOL, 8), 4, new ResourceLocation("minecraft", "textures/blocks/wool_colored_white.png")),

            new LoomRecipe(new ResourceLocation(MOD_ID, "wool_block_cotton"), IIngredient.of("cotton.cloth", 4), new ItemStack(Blocks.WOOL, 8), 4, new ResourceLocation("minecraft", "textures/blocks/wool_colored_white.png")),
            new LoomRecipe(new ResourceLocation(MOD_ID, "wool_block_linen"), IIngredient.of("linen.cloth", 4), new ItemStack(Blocks.WOOL, 8), 4, new ResourceLocation("minecraft", "textures/blocks/wool_colored_white.png")),
            new LoomRecipe(new ResourceLocation(MOD_ID, "wool_block_silk"), IIngredient.of("sulk.cloth", 4), new ItemStack(Blocks.WOOL, 8), 4, new ResourceLocation("minecraft", "textures/blocks/wool_colored_white.png"))
        );
    }

    @SubscribeEvent
    public static void onRegisterStickBundleRecipeEvent(RegistryEvent.Register<StickBundleRecipe> event)
    {
        IForgeRegistry<StickBundleRecipe> r = event.getRegistry();
        int day = ICalendar.TICKS_IN_DAY;

        r.registerAll(
                new StickBundleRecipe(IIngredient.of(TFCItems.SILK_WORM), new ItemStack(TFCItems.SILK_WORM_COCOON), 3 * day).setRegistryName("silk_worm_cocoon")
        );
    }

    //@SubscribeEvent
    public static void onRegisterQuernRecipeEvent(RegistryEvent.Register<QuernRecipe> event)
    {

        IForgeRegistry<QuernRecipe> r = event.getRegistry();

        r.registerAll(
            //Grain
            new QuernRecipe(IIngredient.of("grainBarley"), new ItemStack(TFCItemFood.get(Food.BARLEY_FLOUR), 1)).setRegistryName("barley"),
            new QuernRecipe(IIngredient.of("grainOat"), new ItemStack(TFCItemFood.get(Food.OAT_FLOUR), 1)).setRegistryName("oat"),
            new QuernRecipe(IIngredient.of("grainRice"), new ItemStack(TFCItemFood.get(Food.RICE_FLOUR), 1)).setRegistryName("rice"),
            new QuernRecipe(IIngredient.of("grainRye"), new ItemStack(TFCItemFood.get(Food.RYE_FLOUR), 1)).setRegistryName("rye"),
            new QuernRecipe(IIngredient.of("grainWheat"), new ItemStack(TFCItemFood.get(Food.WHEAT_FLOUR), 1)).setRegistryName("wheat"),
            new QuernRecipe(IIngredient.of("grainMaize"), new ItemStack(TFCItemFood.get(Food.CORNMEAL_FLOUR), 1)).setRegistryName("maize"),

            new QuernRecipe(new IngredientItemFood(IIngredient.of(TFCItemFood.get(Food.OLIVE))), new ItemStack(TFCItems.OLIVE_PASTE, 1)).setRegistryName("olive"),

            //Bone meal
            new QuernRecipe(IIngredient.of("bone"), new ItemStack(Items.DYE, 3, EnumDyeColor.WHITE.getDyeDamage())).setRegistryName("bone_meal_from_bone"),
            new QuernRecipe(IIngredient.of(Blocks.BONE_BLOCK), new ItemStack(Items.DYE, 9, EnumDyeColor.WHITE.getDyeDamage())).setRegistryName("bone_meal_from_bone_block"),

            //Dye from plants
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.HOUSTONIA))), new ItemStack(TFCItems.DYE_WHITE, 2)).setRegistryName("crushed_houstonia"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.OXEYE_DAISY))), new ItemStack(TFCItems.DYE_WHITE, 1)).setRegistryName("crushed_oxeye_daisy"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.PRIMROSE))), new ItemStack(TFCItems.DYE_WHITE, 2)).setRegistryName("crushed_primrose"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.SNAPDRAGON_WHITE))), new ItemStack(TFCItems.DYE_WHITE, 2)).setRegistryName("crushed_snapdragon_white"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.TRILLIUM))), new ItemStack(TFCItems.DYE_WHITE, 1)).setRegistryName("crushed_trillium"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.SPANISH_MOSS))), new ItemStack(TFCItems.DYE_WHITE, 2)).setRegistryName("crushed_spanish_moss"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.TULIP_WHITE))), new ItemStack(TFCItems.DYE_WHITE, 1)).setRegistryName("crushed_tulip_white"),

            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.BUTTERFLY_MILKWEED))), new ItemStack(Items.DYE, 2, EnumDyeColor.ORANGE.getDyeDamage())).setRegistryName("crushed_butterfly_milkweed"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.CANNA))), new ItemStack(Items.DYE, 2, EnumDyeColor.ORANGE.getDyeDamage())).setRegistryName("crushed_canna"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.NASTURTIUM))), new ItemStack(Items.DYE, 2, EnumDyeColor.ORANGE.getDyeDamage())).setRegistryName("crushed_nasturium"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.STRELITZIA))), new ItemStack(Items.DYE, 1, EnumDyeColor.ORANGE.getDyeDamage())).setRegistryName("crushed_strelitzia"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.TULIP_ORANGE))), new ItemStack(Items.DYE, 1, EnumDyeColor.ORANGE.getDyeDamage())).setRegistryName("crushed_tulip_orange"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.WATER_CANNA))), new ItemStack(Items.DYE, 2, EnumDyeColor.ORANGE.getDyeDamage())).setRegistryName("crushed_water_canna"),

            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.ATHYRIUM_FERN))), new ItemStack(Items.DYE, 2, EnumDyeColor.MAGENTA.getDyeDamage())).setRegistryName("crushed_athyrium"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.MORNING_GLORY))), new ItemStack(Items.DYE, 1, EnumDyeColor.MAGENTA.getDyeDamage())).setRegistryName("crushed_morning_glory"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.PULSATILLA))), new ItemStack(Items.DYE, 1, EnumDyeColor.MAGENTA.getDyeDamage())).setRegistryName("crushed_pulsatilla"),

            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.LABRADOR_TEA))), new ItemStack(Items.DYE, 2, EnumDyeColor.LIGHT_BLUE.getDyeDamage())).setRegistryName("crushed_labrador_tea"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.SAPPHIRE_TOWER))), new ItemStack(Items.DYE, 2, EnumDyeColor.LIGHT_BLUE.getDyeDamage())).setRegistryName("crushed_sapphire_tower"),

            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.CALENDULA))), new ItemStack(Items.DYE, 2, EnumDyeColor.YELLOW.getDyeDamage())).setRegistryName("crushed_marigold"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.DANDELION))), new ItemStack(Items.DYE, 1, EnumDyeColor.YELLOW.getDyeDamage())).setRegistryName("crushed_dandelion"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.MEADS_MILKWEED))), new ItemStack(Items.DYE, 1, EnumDyeColor.YELLOW.getDyeDamage())).setRegistryName("crushed_meads_milkweed"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.GOLDENROD))), new ItemStack(Items.DYE, 2, EnumDyeColor.YELLOW.getDyeDamage())).setRegistryName("crushed_goldenrod"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.SNAPDRAGON_YELLOW))), new ItemStack(Items.DYE, 2, EnumDyeColor.YELLOW.getDyeDamage())).setRegistryName("crushed_snapdragon_yellow"),

            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.MOSS))), new ItemStack(Items.DYE, 2, EnumDyeColor.LIME.getDyeDamage())).setRegistryName("crushed_moss"),

            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.FOXGLOVE))), new ItemStack(Items.DYE, 2, EnumDyeColor.PINK.getDyeDamage())).setRegistryName("crushed_foxglove"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.SACRED_DATURA))), new ItemStack(Items.DYE, 2, EnumDyeColor.PINK.getDyeDamage())).setRegistryName("crushed_sacred_datura"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.TULIP_PINK))), new ItemStack(Items.DYE, 1, EnumDyeColor.PINK.getDyeDamage())).setRegistryName("crushed_tulip_pink"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.SNAPDRAGON_PINK))), new ItemStack(Items.DYE, 2, EnumDyeColor.PINK.getDyeDamage())).setRegistryName("crushed_snapdragon_pink"),

            //No gray :c

            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.YUCCA))), new ItemStack(Items.DYE, 2, EnumDyeColor.SILVER.getDyeDamage())).setRegistryName("crushed_yucca"),

            //No Cyan :c

            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.ALLIUM))), new ItemStack(Items.DYE, 2, EnumDyeColor.PURPLE.getDyeDamage())).setRegistryName("crushed_allium"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.BLACK_ORCHID))), new ItemStack(Items.DYE, 2, EnumDyeColor.PURPLE.getDyeDamage())).setRegistryName("crushed_black_orchid"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.PEROVSKIA))), new ItemStack(Items.DYE, 2, EnumDyeColor.PURPLE.getDyeDamage())).setRegistryName("crushed_perovskia"),

            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.BLUE_ORCHID))), new ItemStack(TFCItems.DYE_BLUE, 2)).setRegistryName("crushed_blue_orchid"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.GRAPE_HYACINTH))), new ItemStack(TFCItems.DYE_BLUE, 2)).setRegistryName("crushed_grape_hyacinth"),

            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.ROUGH_HORSETAIL))), new ItemStack(TFCItems.DYE_BROWN, 2)).setRegistryName("crushed_rough_horsetail"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.SARGASSUM))), new ItemStack(TFCItems.DYE_BROWN, 2)).setRegistryName("crushed_sargassum"),

            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.BARREL_CACTUS))), new ItemStack(Items.DYE, 4, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("crushed_barrel_cactus"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.REINDEER_LICHEN))), new ItemStack(Items.DYE, 4, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("crushed_reindeer_lichen"),

            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.GUZMANIA))), new ItemStack(Items.DYE, 2, EnumDyeColor.RED.getDyeDamage())).setRegistryName("crushed_guzmania"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.POPPY))), new ItemStack(Items.DYE, 2, EnumDyeColor.RED.getDyeDamage())).setRegistryName("crushed_poppy"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.PORCINI))), new ItemStack(Items.DYE, 2, EnumDyeColor.RED.getDyeDamage())).setRegistryName("crushed_porcini"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.ROSE))), new ItemStack(Items.DYE, 4, EnumDyeColor.RED.getDyeDamage())).setRegistryName("crushed_rose"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.SNAPDRAGON_RED))), new ItemStack(Items.DYE, 2, EnumDyeColor.RED.getDyeDamage())).setRegistryName("crushed_snapdragon_red"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.TROPICAL_MILKWEED))), new ItemStack(Items.DYE, 2, EnumDyeColor.RED.getDyeDamage())).setRegistryName("crushed_tropical_milkweed"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.TULIP_RED))), new ItemStack(Items.DYE, 2, EnumDyeColor.RED.getDyeDamage())).setRegistryName("crushed_tulip_red"),
            new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.VRIESEA))), new ItemStack(Items.DYE, 2, EnumDyeColor.RED.getDyeDamage())).setRegistryName("crushed_vriesea"),

            //Misc
            new QuernRecipe(IIngredient.of(Items.BLAZE_ROD), new ItemStack(Items.BLAZE_POWDER, 2)).setRegistryName("blaze_powder"),



                new QuernRecipe(IIngredient.of("logWoodLogwood"), new ItemStack((TFCItems.LOGWOOD_CHIPS), 3)).setRegistryName("chipped_logwood_log"),
                new QuernRecipe(IIngredient.of("sugarcane"), new ItemStack((TFCItemFood.get(Food.MASHED_SUGAR_CANE)))).setRegistryName("mashed_sugar_cane_quern_1"),
                new QuernRecipe(IIngredient.of(TFCItemFood.get(Food.SUGARCANE)), new ItemStack((TFCItemFood.get(Food.MASHED_SUGAR_CANE)))).setRegistryName("mashed_sugar_cane_quern_2"),
                new QuernRecipe(IIngredient.of("cropSugarBeet"), new ItemStack((TFCItemFood.get(Food.MASHED_SUGAR_BEET)))).setRegistryName("mashed_sugar_beet_quern"),
                new QuernRecipe(IIngredient.of("grainAmaranth"), new ItemStack((TFCItemFood.get(Food.AMARANTH_FLOUR)))).setRegistryName("amaranth"),
                new QuernRecipe(IIngredient.of("grainBuckwheat"), new ItemStack((TFCItemFood.get(Food.BUCKWHEAT_FLOUR)))).setRegistryName("buckwheat"),
                new QuernRecipe(IIngredient.of("grainFonio"), new ItemStack((TFCItemFood.get(Food.FONIO_FLOUR)))).setRegistryName("fonio"),
                new QuernRecipe(IIngredient.of("grainMillet"), new ItemStack((TFCItemFood.get(Food.MILLET_FLOUR)))).setRegistryName("millet"),
                new QuernRecipe(IIngredient.of("grainQuinoa"), new ItemStack((TFCItemFood.get(Food.QUINOA_FLOUR)))).setRegistryName("quinoa"),
                new QuernRecipe(IIngredient.of("grainSpelt"), new ItemStack((TFCItemFood.get(Food.SPELT_FLOUR)))).setRegistryName("spelt"),
                new QuernRecipe(IIngredient.of(TFCItemFood.get(Food.CASSIA_CINNAMON_BARK)), new ItemStack(TFCItemFood.get(Food.GROUND_CASSIA_CINNAMON), 2)).setRegistryName("ground_cassia_cinnamon"),
                new QuernRecipe(IIngredient.of(TFCItemFood.get(Food.CEYLON_CINNAMON_BARK)), new ItemStack(TFCItemFood.get(Food.GROUND_CEYLON_CINNAMON), 2)).setRegistryName("ground_ceylon_cinnamon"),
                new QuernRecipe(IIngredient.of(TFCItemFood.get(Food.BLACK_PEPPER)), new ItemStack(TFCItemFood.get(Food.GROUND_BLACK_PEPPER), 2)).setRegistryName("ground_black_pepper"),
                new QuernRecipe(IIngredient.of(TFCItemFood.get(Food.ROASTED_COFFEE_BEANS)), new ItemStack(TFCItemFood.get(Food.COFFEE_POWDER), 2)).setRegistryName("ground_coffee_beans"),
//                new QuernRecipe(IIngredient.of("pearl"), new ItemStack(ItemPowderTFCF.get(PowderTFCF.PEARL))).setRegistryName("crushed_pearl"),
//                new QuernRecipe(IIngredient.of("pearlBlack"), new ItemStack(ItemPowderTFCF.get(PowderTFCF.BLACK_PEARL))).setRegistryName("crushed_black_pearl"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.PAPYRUS))), new ItemStack((Item) IIngredient.of("papyrus.pulp"), 3)).setRegistryName("crushed_papyrus"),
                new QuernRecipe(IIngredient.of("linseed"), new ItemStack(TFCItemFood.get(Food.LINSEED_PASTE), 1)).setRegistryName("crushed_linseed"),
                new QuernRecipe(IIngredient.of("rapeSeed"), new ItemStack(TFCItemFood.get(Food.RAPE_SEED_PASTE), 1)).setRegistryName("crushed_rape_seed"),
                new QuernRecipe(IIngredient.of("sunflowerSeed"), new ItemStack(TFCItemFood.get(Food.SUNFLOWER_SEED_PASTE), 1)).setRegistryName("crushed_sunflower_seed"),
                new QuernRecipe(IIngredient.of("opiumPoppySeed"), new ItemStack(TFCItemFood.get(Food.OPIUM_POPPY_SEED_PASTE), 1)).setRegistryName("crushed_opium_poppy_seed"),
                new QuernRecipe(IIngredient.of("cropSoybean"), new ItemStack(TFCItemFood.get(Food.SOYBEAN_PASTE), 1)).setRegistryName("crushed_soybean"),

                // Dye from plants
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.CHAMOMILE))), new ItemStack(TFCItems.DYE_WHITE, 2)).setRegistryName("crushed_chamomile"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.HYDRANGEA))), new ItemStack(TFCItems.DYE_WHITE, 2)).setRegistryName("crushed_hydrangea"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.LILY_OF_THE_VALLEY))), new ItemStack(TFCItems.DYE_WHITE, 2)).setRegistryName("crushed_lily_of_the_valley"),
                new QuernRecipe(IIngredient.of("cropMadder"), new ItemStack(Items.DYE, 2, EnumDyeColor.RED.getDyeDamage())).setRegistryName("crushed_madder"),
                new QuernRecipe(IIngredient.of("cropWoad"), new ItemStack(TFCItems.DYE_BLUE, 2)).setRegistryName("crushed_woad"),
                new QuernRecipe(IIngredient.of("cropIndigo"), new ItemStack(TFCItems.DYE_BLUE, 2)).setRegistryName("crushed_indigo"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.SUNFLOWER))), new ItemStack(Items.DYE, 2, EnumDyeColor.YELLOW.getDyeDamage())).setRegistryName("crushed_sunflower"),
                new QuernRecipe(IIngredient.of("cropWeld"), new ItemStack(Items.DYE, 2, EnumDyeColor.YELLOW.getDyeDamage())).setRegistryName("crushed_weld"),
                new QuernRecipe(IIngredient.of("cropRape"), new ItemStack(TFCItems.RAPE, 2)).setRegistryName("crushed_rape"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.LILAC))), new ItemStack(Items.DYE, 2, EnumDyeColor.PINK.getDyeDamage())).setRegistryName("crushed_lilac"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.PEONY))), new ItemStack(Items.DYE, 2, EnumDyeColor.PINK.getDyeDamage())).setRegistryName("crushed_peony"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.LAVANDULA))), new ItemStack(Items.DYE, 2, EnumDyeColor.PURPLE.getDyeDamage())).setRegistryName("crushed_lavandula"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.CATTAIL))), new ItemStack(TFCItems.DYE_BROWN, 2)).setRegistryName("crushed_cattail"),
                new QuernRecipe(IIngredient.of("cropAgave"), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("crushed_sisal"),
                new QuernRecipe(IIngredient.of("resin"), new ItemStack(Items.DYE, 2, EnumDyeColor.YELLOW.getDyeDamage())).setRegistryName("yellow_dye_resin"),
                new QuernRecipe(IIngredient.of("treeLeavesTeak"), new ItemStack(Items.DYE, 2, EnumDyeColor.RED.getDyeDamage())).setRegistryName("green_dye_teak_leaves"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.SUGAR_CANE))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_sugar_cane_1"),
                new QuernRecipe(IIngredient.of(Blocks.REEDS), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_sugar_cane_2"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.TACKWEED))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_tackweed"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.TAKAKIA))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_takakia"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.VOODOO_LILY))), new ItemStack(Items.DYE, 2, EnumDyeColor.PINK.getDyeDamage())).setRegistryName("pink_dye_voodoo_lily"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.DEVILS_TONGUE))), new ItemStack(Items.DYE, 2, EnumDyeColor.PINK.getDyeDamage())).setRegistryName("pink_dye_devils_tongue"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.BROMELIA_HEMISPHERICA))), new ItemStack(Items.DYE, 2, EnumDyeColor.MAGENTA.getDyeDamage())).setRegistryName("magenta_dye_bromelia_hemispherica"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.BROMELIA_LACINIOSA))), new ItemStack(Items.DYE, 2, EnumDyeColor.RED.getDyeDamage())).setRegistryName("red_dye_bromelia_laciniosa"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.KAIETEUR_FALLS))), new ItemStack(Items.DYE, 2, EnumDyeColor.RED.getDyeDamage())).setRegistryName("red_dye_kaieteur_falls"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.MATTEUCCIA))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_matteuccia"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.CORD_GRASS))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_cord_grass"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.REED_MANNAGRASS))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_reed_mannagrass"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.PRAIRIE_JUNEGRASS))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_prairie_junegrass"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.WOOLLY_BUSH))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_woolly_bush"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.CINNAMON_FERN))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_cinnamon_fern"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.JAPANESE_PIERIS))), new ItemStack(Items.DYE, 2, EnumDyeColor.PINK.getDyeDamage())).setRegistryName("pink_dye_japanese_pieris"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.BURNING_BUSH))), new ItemStack(TFCItems.DYE_BROWN, 2)).setRegistryName("brown_dye_burning_bush"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.UNDERGROWTH_SHRUB))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_undergrowth_shrub"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.UNDERGROWTH_SHRUB_SMALL))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_undergrowth_shrub_small"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.SEA_OATS))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_sea_oats"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.BUNCH_GRASS_FLOATING))), new ItemStack(TFCItems.DYE_BROWN, 2)).setRegistryName("brown_dyebunch_grass_floating"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.BUNCH_GRASS_REED))), new ItemStack(TFCItems.DYE_BROWN, 2)).setRegistryName("brown_dye_bunch_grass_reed"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.CROWNGRASS))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_crowngrass"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.CAT_GRASS))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_cat_grass"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.GOOSEGRASS))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_goosegrass"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.WHEATGRASS))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_wheatgrass"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.HALFA_GRASS))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_halfa_grass"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.LEYMUS))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_leymus"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.MARRAM_GRASS))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_marram_grass"),
                //new QuernRecipe(IIngredient.of(BlockPlantTFC.get(TFCRegistries.PLANTS.getValue(DefaultPlants.WILD_BARLEY))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_wild_barley"),
                //new QuernRecipe(IIngredient.of(BlockPlantTFC.get(TFCRegistries.PLANTS.getValue(DefaultPlants.WILD_RICE))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_wild_rice"),
                //new QuernRecipe(IIngredient.of(BlockPlantTFC.get(TFCRegistries.PLANTS.getValue(DefaultPlants.WILD_WHEAT))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_wild_wheat"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.RATTAN))), new ItemStack(TFCItems.DYE_BROWN, 2)).setRegistryName("brown_dye_rattan"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.GLOW_VINE))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_hanging_vines"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.BLUE_SKYFLOWER))), new ItemStack(Items.DYE, 2, EnumDyeColor.LIGHT_BLUE.getDyeDamage())).setRegistryName("light_blue_dye_blue_skyflower"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.JADE_VINE))), new ItemStack(Items.DYE, 2, EnumDyeColor.LIGHT_BLUE.getDyeDamage())).setRegistryName("light_blue_dye_jade_vine"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.JAPANESE_IVY))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_japanese_ivy"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.MADEIRA_VINE))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_madeira_vine"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.MYSORE_TRUMPETVINE))), new ItemStack(Items.DYE, 2, EnumDyeColor.RED.getDyeDamage())).setRegistryName("red_dye_mysore_trumpetvine"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.SILVERVEIN_CREEPER))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_silvervein_creeper"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.SWEDISH_IVY))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_swedish_ivy"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.VARIEGATED_PERSIAN_IVY))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_variegated_persian_ivy"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.APACHE_DWARF))), new ItemStack(Items.DYE, 2, EnumDyeColor.YELLOW.getDyeDamage())).setRegistryName("yellow_dye_apache_dwarf"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.ARTISTS_CONK))), new ItemStack(TFCItems.DYE_BROWN, 2)).setRegistryName("brown_dye_artists_conk"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.CLIMBING_CACTUS))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_climbing_cactus"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.CRIMSON_CATTLEYA))), new ItemStack(Items.DYE, 2, EnumDyeColor.PURPLE.getDyeDamage())).setRegistryName("purple_dye_crimson_cattleya"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.CREEPING_MISTLETOE))), new ItemStack(Items.DYE, 2, EnumDyeColor.ORANGE.getDyeDamage())).setRegistryName("orange_dye_creeping_mistletoe"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.CUTHBERTS_DENDROBIUM))), new ItemStack(Items.DYE, 2, EnumDyeColor.ORANGE.getDyeDamage())).setRegistryName("orange_dye_cuthberts_dendrobium"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.FISH_BONE_CACTUS))), new ItemStack(Items.DYE, 2, EnumDyeColor.MAGENTA.getDyeDamage())).setRegistryName("magenta_dye_fish_bone_cactus"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.FRAGRANT_FERN))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_fragrant_fern"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.HARLEQUIN_MISTLETOE))), new ItemStack(Items.DYE, 2, EnumDyeColor.ORANGE.getDyeDamage())).setRegistryName("orange_dye_harlequin_mistletoe"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.KING_ORCHID))), new ItemStack(TFCItems.DYE_WHITE, 2)).setRegistryName("white_dye_king_orchid"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.LANTERN_OF_THE_FOREST))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_lantern_of_the_forest"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.LARGE_FOOT_DENDROBIUM))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_large_foot_dendrobium"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.COMMON_MISTLETOE))), new ItemStack(TFCItems.DYE_WHITE, 2)).setRegistryName("white_dye_common_mistletoe"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.SKY_PLANT))), new ItemStack(Items.DYE, 2, EnumDyeColor.PINK.getDyeDamage())).setRegistryName("pink_dye_sky_plant"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.SULPHUR_SHELF))), new ItemStack(Items.DYE, 2, EnumDyeColor.ORANGE.getDyeDamage())).setRegistryName("orange_dye_sulphur_shelf"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.TAMPA_BUTTERFLY_ORCHID))), new ItemStack(Items.DYE, 2, EnumDyeColor.YELLOW.getDyeDamage())).setRegistryName("yellow_dye_tampa_butterfly_orchid"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.TURKEY_TAIL))), new ItemStack(TFCItems.DYE_BROWN, 2)).setRegistryName("brown_dye_turkey_tail"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.WILDFIRE))), new ItemStack(Items.DYE, 2, EnumDyeColor.RED.getDyeDamage())).setRegistryName("red_dye_wildfire"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.BELL_TREE_DAHLIA))), new ItemStack(Items.DYE, 2, EnumDyeColor.PINK.getDyeDamage())).setRegistryName("pink_dye_bell_tree_dahlia"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.BIG_LEAF_PALM))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_big_leaf_palm"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.DRAKENSBERG_CYCAD))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_drakensberg_cycad"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.DWARF_SUGAR_PALM))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_dwarf_sugar_palm"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.GIANT_CANE))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_giant_cane"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.GIANT_ELEPHANT_EAR))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_giant_elephant_ear"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.GIANT_FEATHER_GRASS))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_giant_feather_grass"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.MADAGASCAR_OCOTILLO))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_madagascar_ocotillo"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.MALAGASY_TREE_ALOE))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_malagasy_tree_aloe"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.MOUNTAIN_CABBAGE_TREE))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_mountain_cabbage_tree"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.PYGMY_DATE_PALM))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_pygmy_date_palm"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.QUEEN_SAGO))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_queen_sago"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.RED_SEALING_WAX_PALM))), new ItemStack(Items.DYE, 2, EnumDyeColor.RED.getDyeDamage())).setRegistryName("red_dye_red_sealing_wax_palm"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.SUMMER_ASPHODEL))), new ItemStack(Items.DYE, 2, EnumDyeColor.PINK.getDyeDamage())).setRegistryName("pink_dye_summer_asphodel"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.ZIMBABWE_ALOE))), new ItemStack(Items.DYE, 2, EnumDyeColor.RED.getDyeDamage())).setRegistryName("red_dye_zimbabwe_aloe"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.ANTHURIUM))), new ItemStack(Items.DYE, 2, EnumDyeColor.RED.getDyeDamage())).setRegistryName("red_dye_anthurium"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.ARROWHEAD))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_arrowhead"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.ARUNDO))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_arundo"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.BLUEGRASS))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_bluegrass"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.BLUE_GINGER))), new ItemStack(TFCItems.DYE_BLUE, 2)).setRegistryName("blue_dye_blue_ginger"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.BROMEGRASS))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_bromegrass"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.BUR_REED))), new ItemStack(TFCItems.DYE_WHITE, 2)).setRegistryName("white_dye_bur_reed"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.DESERT_FLAME))), new ItemStack(Items.DYE, 2, EnumDyeColor.YELLOW.getDyeDamage())).setRegistryName("yellow_dye_desert_flame"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.HELICONIA))), new ItemStack(Items.DYE, 2, EnumDyeColor.RED.getDyeDamage())).setRegistryName("red_dye_heliconia"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.HIBISCUS))), new ItemStack(TFCItems.DYE_WHITE, 2)).setRegistryName("white_dye_hibiscus"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.KANGAROO_PAW))), new ItemStack(Items.DYE, 2, EnumDyeColor.RED.getDyeDamage())).setRegistryName("red_dye_kangaroo_paw"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.KING_FERN))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_king_fern"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.LIPSTICK_PALM))), new ItemStack(Items.DYE, 2, EnumDyeColor.MAGENTA.getDyeDamage())).setRegistryName("magenta_dye_lipstick_palm"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.MARIGOLD))), new ItemStack(Items.DYE, 2, EnumDyeColor.YELLOW.getDyeDamage())).setRegistryName("yellow_dye_marigold"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.MONSTERA_EPIPHYTE))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_monstera_epiphyte"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.MONSTERA_GROUND))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_monstera_ground"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.PHRAGMITE))), new ItemStack(Items.DYE, 2, EnumDyeColor.PINK.getDyeDamage())).setRegistryName("pink_dye_phragmite"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.PICKERELWEED))), new ItemStack(Items.DYE, 2, EnumDyeColor.PURPLE.getDyeDamage())).setRegistryName("purple_dye_pickerelweed"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.BADDERLOCKS))), new ItemStack(TFCItems.DYE_BROWN, 2)).setRegistryName("brown_dye_badderlocks"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.COONTAIL))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_coontail"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.EEL_GRASS))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_eel_grass"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.GIANT_KELP))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_giant_kelp"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.GUTWEED))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_gutweed"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.HORNWORT))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_hornwort"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.LAMINARIA))), new ItemStack(TFCItems.DYE_BROWN, 2)).setRegistryName("brown_dye_laminaria"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.LEAFY_KELP))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_leafy_kelp"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.MANATEE_GRASS))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_manatee_grass"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.MILFOIL))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_milfoil"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.PONDWEED))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_pondweed"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.SAGO))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_sago"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.SEAGRASS))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_seagrass"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.SEAWEED))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_seaweed"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.STAR_GRASS))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_star_grass"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.TURTLE_GRASS))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_turtle_grass"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.WINGED_KELP))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_winged_kelp"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.RED_ALGAE))), new ItemStack(Items.DYE, 2, EnumDyeColor.RED.getDyeDamage())).setRegistryName("red_dye_red_algae"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.RED_SEA_WHIP))), new ItemStack(Items.DYE, 2, EnumDyeColor.RED.getDyeDamage())).setRegistryName("red_dye_red_sea_whip"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.SEA_ANEMONE))), new ItemStack(Items.DYE, 2, EnumDyeColor.PINK.getDyeDamage())).setRegistryName("pink_dye_sea_anemone"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.BEARDED_MOSS))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_bearded_moss"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.GLOW_VINE))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_glow_vine"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.HANGING_VINE))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_hanging_vine"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.JUNGLE_VINE))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_jungle_vine"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.LIANA))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_liana"),
                new QuernRecipe(IIngredient.of(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.IVY))), new ItemStack(Items.DYE, 2, EnumDyeColor.GREEN.getDyeDamage())).setRegistryName("green_dye_ivy"),
                new QuernRecipe(IIngredient.of(TFCBlocks.BLUESHROOM), new ItemStack(Items.DYE, 1, EnumDyeColor.CYAN.getDyeDamage())).setRegistryName("cyan_dye_blueshroom"),
                new QuernRecipe(IIngredient.of(TFCBlocks.GLOWSHROOM), new ItemStack(Items.GLOWSTONE_DUST, 1)).setRegistryName("glowstone_dust_glowshroom"),
                new QuernRecipe(IIngredient.of(TFCBlocks.MAGMA_SHROOM), new ItemStack(Items.MAGMA_CREAM, 1)).setRegistryName("magma_cream_magma_shroom"),
                new QuernRecipe(IIngredient.of(TFCBlocks.POISON_SHROOM), new ItemStack(Items.DYE, 1, EnumDyeColor.MAGENTA.getDyeDamage())).setRegistryName("magenta_dye_poison_shroom"),
                //new QuernRecipe(IIngredient.of(TFCBlocks.SULPHUR_SHROOM), new ItemStack(ItemPowder.get(Powder.SULFUR), 1)).setRegistryName("sulphur_powder_shroom"),
                new QuernRecipe(IIngredient.of(TFCBlocks.GLOWING_SEA_BANANA), new ItemStack(Items.GLOWSTONE_DUST, 2)).setRegistryName("glowing_sea_banana_glowstone_dust"),
                new QuernRecipe(IIngredient.of(TFCBlocks.LIGHTSTONE), new ItemStack(Items.GLOWSTONE_DUST, 2)).setRegistryName("glowstone_dust_lightstone")
        );
    }

    @SubscribeEvent
    @SuppressWarnings("ConstantConditions")
    public static void onRegisterChiselRecipeEvent(RegistryEvent.Register<ChiselRecipe> event)
    {
        // Alabaster smoothing
        for (EnumDyeColor color : EnumDyeColor.values())
        {
            Block rawColoredAlabaster = TFCBlockRockDecorative.ALABASTER_RAW.get(color);
            IBlockState smoothColoredAlabaster = TFCBlockRockDecorative.ALABASTER_POLISHED.get(color).getDefaultState();
            event.getRegistry().register(new ChiselRecipe(rawColoredAlabaster, smoothColoredAlabaster).setRegistryName("smooth_" + color.getName() + "_alabaster"));
        }
        // And plain
        event.getRegistry().register(new ChiselRecipe(TFCBlocks.ALABASTER_RAW_PLAIN, TFCBlocks.ALABASTER_POLISHED_PLAIN.getDefaultState()).setRegistryName("smooth_alabaster"));
    }

    @SubscribeEvent
    public static void onRegisterBloomeryRecipeEvent(RegistryEvent.Register<BloomeryRecipe> event)
    {
        event.getRegistry().registerAll(
                new BloomeryRecipe(Materials.WroughtIron, FuelManager::isItemBloomeryFuel)
        );
    }

    @SubscribeEvent
    public static void onRegisterBlastFurnaceRecipeEvent(RegistryEvent.Register<BlastFurnaceRecipe> event)
    {
        event.getRegistry().registerAll(
                new BlastFurnaceRecipe(TFCMaterials.PigIron, Materials.WroughtIron, IIngredient.of("dustFlux"))
        );
    }

    @SubscribeEvent
    public static void onRegisterHeatRecipeEvent(RegistryEvent.Register<HeatRecipe> event)
    {
        IForgeRegistry<HeatRecipe> r = event.getRegistry();

        // Any item with metal capability -> Metal
        for (Material material : GregTechAPI.MATERIAL_REGISTRY) {
            if (material.hasFlag(TFCMaterialFlags.TFC_MATERIAL)) {
                r.register(new HeatRecipeMetalMelting(material).setRegistryName(material.getUnlocalizedName() + "_melting"));
            }
        }

        // Pottery Items with metadata
        for (EnumDyeColor dye : EnumDyeColor.values())
        {
            r.register(
                    new HeatRecipeSimple(IIngredient.of(new ItemStack(TFCItems.UNFIRED_VESSEL_GLAZED, 1, dye.getMetadata())), new ItemStack(TFCItems.FIRED_VESSEL_GLAZED, 1, dye.getMetadata()), 1599f, 1).setRegistryName("unfired_vessel_glazed_" + dye.getName())
            );
        }

        // Unfired and Fired Molds
        for (TFCOrePrefixExtended extendedOrePrefix : TFGUtils.TFC_OREPREFIX_REGISTRY)
        {
            if (extendedOrePrefix.isHasMold())
            {
                ItemUnfiredClayMold unfiredMold = ItemUnfiredClayMold.get(extendedOrePrefix.getOrePrefix());
                ItemClayMold firedMold = ItemClayMold.get(extendedOrePrefix.getOrePrefix());

                r.register(new HeatRecipeSimple(IIngredient.of(unfiredMold), new ItemStack(firedMold), 1599f).setRegistryName("fired_mold_" + extendedOrePrefix.getOrePrefix().name));
            }
        }

        // Standard / Simple recipes
        r.registerAll(
                // Pottery
                new HeatRecipeSimple(IIngredient.of(TFCItems.UNFIRED_FIRE_BRICK), new ItemStack(TFCItems.FIRED_FIRE_BRICK), 1599f, 1).setRegistryName("unfired_fire_brick"),
                new HeatRecipeSimple(IIngredient.of(TFCItems.UNFIRED_VESSEL), new ItemStack(TFCItems.FIRED_VESSEL), 1599f, 1).setRegistryName("unfired_vessel"),
                new HeatRecipeSimple(IIngredient.of(TFCItems.UNFIRED_JUG), new ItemStack(TFCItems.FIRED_JUG), 1599f, 1).setRegistryName("unfired_jug"),
                new HeatRecipeSimple(IIngredient.of(TFCItems.UNFIRED_POT), new ItemStack(TFCItems.FIRED_POT), 1599f, 1).setRegistryName("unfired_pot"),
                new HeatRecipeSimple(IIngredient.of(TFCItems.UNFIRED_BOWL), new ItemStack(TFCItems.FIRED_BOWL), 1599f, 1).setRegistryName("unfired_bowl"),
                new HeatRecipeSimple(IIngredient.of(TFCItems.UNFIRED_SPINDLE), new ItemStack(TFCItems.FIRED_SPINDLE), 1599f, 1).setRegistryName("unfired_spindle"),
                new HeatRecipeSimple(IIngredient.of(TFCItems.UNFIRED_LARGE_VESSEL), new ItemStack(TFCBlocks.FIRED_LARGE_VESSEL), 1599f, 1).setRegistryName("unfired_large_vessel"),
                new HeatRecipeSimple(IIngredient.of(TFCItems.UNFIRED_CRUCIBLE), new ItemStack(TFCBlocks.CRUCIBLE), 1599f, 1).setRegistryName("unfired_crucible"),

                // Fired Pottery - doesn't burn up
                new HeatRecipeSimple(IIngredient.of(TFCItems.FIRED_FIRE_BRICK), new ItemStack(TFCItems.FIRED_FIRE_BRICK), 1599f, 1).setRegistryName("fired_fire_brick"),
                new HeatRecipeVessel(IIngredient.of(TFCItems.FIRED_VESSEL), 1599f, 1).setRegistryName("fired_vessel"),
                new HeatRecipeVessel(IIngredient.of(TFCItems.FIRED_VESSEL_GLAZED), 1599f, 1).setRegistryName("fired_vessel_glazed_all"),
                new HeatRecipeSimple(IIngredient.of(TFCItems.FIRED_JUG), new ItemStack(TFCItems.FIRED_JUG), 1599f, 1).setRegistryName("fired_jug"),
                new HeatRecipeSimple(IIngredient.of(TFCItems.FIRED_POT), new ItemStack(TFCItems.FIRED_POT), 1599f, 1).setRegistryName("fired_pot"),
                new HeatRecipeSimple(IIngredient.of(TFCItems.FIRED_BOWL), new ItemStack(TFCItems.FIRED_BOWL), 1599f, 1).setRegistryName("fired_bowl"),
                new HeatRecipeSimple(IIngredient.of(TFCItems.FIRED_SPINDLE), new ItemStack(TFCItems.FIRED_SPINDLE), 1599f, 1).setRegistryName("fired_spindle"),
                new HeatRecipeSimple(IIngredient.of(TFCBlocks.FIRED_LARGE_VESSEL), new ItemStack(TFCBlocks.FIRED_LARGE_VESSEL), 1599f, 1).setRegistryName("fired_large_vessel"),
                new HeatRecipeSimple(IIngredient.of(TFCBlocks.CRUCIBLE), new ItemStack(TFCBlocks.CRUCIBLE), 1599f, 1).setRegistryName("fired_crucible"),

                // Misc
                new HeatRecipeSimple(IIngredient.of("stickWood"), new ItemStack(Blocks.TORCH, 2), 40).setRegistryName("torch"),
                new HeatRecipeSimple(IIngredient.of(TFCItems.STICK_BUNCH), new ItemStack(Blocks.TORCH, 18), 60).setRegistryName("torch_stick_bunch"),
                new HeatRecipeSimple(IIngredient.of("sand"), new ItemStack(Blocks.GLASS), 600).setRegistryName("glass"),
                new HeatRecipeSimple(IIngredient.of(TFCItems.GLASS_SHARD), new ItemStack(Blocks.GLASS), 600).setRegistryName("glass_shard"),
                new HeatRecipeSimple(IIngredient.of("blockClay"), new ItemStack(Blocks.HARDENED_CLAY), 600).setRegistryName("terracotta"),
                new HeatRecipeSimple(IIngredient.of(TFCItems.UNFIRED_BRICK), new ItemStack(Items.BRICK), 1500).setRegistryName("unfired_brick"),
                new HeatRecipeSimple(IIngredient.of(TFCItems.UNFIRED_FLOWER_POT), new ItemStack(Items.FLOWER_POT), 1500).setRegistryName("unfired_flower_pot"),

                // Bread
                new HeatRecipeSimple(IIngredient.of(TFCItemFood.get(Food.BARLEY_DOUGH)), new ItemStack(TFCItemFood.get(Food.BARLEY_BREAD)), 200, 480).setRegistryName("barley_bread"),
                new HeatRecipeSimple(IIngredient.of(TFCItemFood.get(Food.CORNMEAL_DOUGH)), new ItemStack(TFCItemFood.get(Food.CORNBREAD)), 200, 480).setRegistryName("cornbread"),
                new HeatRecipeSimple(IIngredient.of(TFCItemFood.get(Food.OAT_DOUGH)), new ItemStack(TFCItemFood.get(Food.OAT_BREAD)), 200, 480).setRegistryName("oat_bread"),
                new HeatRecipeSimple(IIngredient.of(TFCItemFood.get(Food.RICE_DOUGH)), new ItemStack(TFCItemFood.get(Food.RICE_BREAD)), 200, 480).setRegistryName("rice_bread"),
                new HeatRecipeSimple(IIngredient.of(TFCItemFood.get(Food.RYE_DOUGH)), new ItemStack(TFCItemFood.get(Food.RYE_BREAD)), 200, 480).setRegistryName("rye_bread"),
                new HeatRecipeSimple(IIngredient.of(TFCItemFood.get(Food.WHEAT_DOUGH)), new ItemStack(TFCItemFood.get(Food.WHEAT_BREAD)), 200, 480).setRegistryName("wheat_bread"),

                // Meat
                new HeatRecipeSimple(IIngredient.of(TFCItemFood.get(Food.BEEF)), new ItemStack(TFCItemFood.get(Food.COOKED_BEEF)), 200, 480).setRegistryName("cooked_beef"),
                new HeatRecipeSimple(IIngredient.of(TFCItemFood.get(Food.PORK)), new ItemStack(TFCItemFood.get(Food.COOKED_PORK)), 200, 480).setRegistryName("cooked_pork"),
                new HeatRecipeSimple(IIngredient.of(TFCItemFood.get(Food.CHICKEN)), new ItemStack(TFCItemFood.get(Food.COOKED_CHICKEN)), 200, 480).setRegistryName("cooked_chicken"),
                new HeatRecipeSimple(IIngredient.of(TFCItemFood.get(Food.MUTTON)), new ItemStack(TFCItemFood.get(Food.COOKED_MUTTON)), 200, 480).setRegistryName("cooked_mutton"),
                new HeatRecipeSimple(IIngredient.of(TFCItemFood.get(Food.FISH)), new ItemStack(TFCItemFood.get(Food.COOKED_FISH)), 200, 480).setRegistryName("cooked_fish"),
                new HeatRecipeSimple(IIngredient.of(TFCItemFood.get(Food.BEAR)), new ItemStack(TFCItemFood.get(Food.COOKED_BEAR)), 200, 480).setRegistryName("cooked_bear"),
                new HeatRecipeSimple(IIngredient.of(TFCItemFood.get(Food.CALAMARI)), new ItemStack(TFCItemFood.get(Food.COOKED_CALAMARI)), 200, 480).setRegistryName("cooked_calamari"),
                new HeatRecipeSimple(IIngredient.of(TFCItemFood.get(Food.HORSE_MEAT)), new ItemStack(TFCItemFood.get(Food.COOKED_HORSE_MEAT)), 200, 480).setRegistryName("cooked_horse_meat"),
                new HeatRecipeSimple(IIngredient.of(TFCItemFood.get(Food.PHEASANT)), new ItemStack(TFCItemFood.get(Food.COOKED_PHEASANT)), 200, 480).setRegistryName("cooked_pheasant"),
                new HeatRecipeSimple(IIngredient.of(TFCItemFood.get(Food.VENISON)), new ItemStack(TFCItemFood.get(Food.COOKED_VENISON)), 200, 480).setRegistryName("cooked_venison"),
                new HeatRecipeSimple(IIngredient.of(TFCItemFood.get(Food.RABBIT)), new ItemStack(TFCItemFood.get(Food.COOKED_RABBIT)), 200, 480).setRegistryName("cooked_rabbit"),
                new HeatRecipeSimple(IIngredient.of(TFCItemFood.get(Food.WOLF)), new ItemStack(TFCItemFood.get(Food.COOKED_WOLF)), 200, 480).setRegistryName("cooked_wolf"),
                new HeatRecipeSimple(IIngredient.of(TFCItemFood.get(Food.CAMELIDAE)), new ItemStack(TFCItemFood.get(Food.COOKED_CAMELIDAE)), 200, 480).setRegistryName("cooked_camelidae"),
                new HeatRecipeSimple(IIngredient.of(TFCItemFood.get(Food.MONGOOSE)), new ItemStack(TFCItemFood.get(Food.COOKED_MONGOOSE)), 200, 480).setRegistryName("cooked_mongoose"),
                new HeatRecipeSimple(IIngredient.of(TFCItemFood.get(Food.GRAN_FELINE)), new ItemStack(TFCItemFood.get(Food.COOKED_GRAN_FELINE)), 200, 480).setRegistryName("cooked_gran_feline"),

                // Egg
                new HeatRecipeSimple(IIngredient.of(Items.EGG), new ItemStack(TFCItemFood.get(Food.COOKED_EGG)), 200, 480).setRegistryName("cooked_egg"),

                // Bread
                HeatRecipe.destroy(IIngredient.of(TFCItemFood.get(Food.BARLEY_BREAD)), 480).setRegistryName("burned_barley_bread"),
                HeatRecipe.destroy(IIngredient.of(TFCItemFood.get(Food.CORNBREAD)), 480).setRegistryName("burned_cornbread"),
                HeatRecipe.destroy(IIngredient.of(TFCItemFood.get(Food.OAT_BREAD)), 480).setRegistryName("burned_oat_bread"),
                HeatRecipe.destroy(IIngredient.of(TFCItemFood.get(Food.RICE_BREAD)), 480).setRegistryName("burned_rice_bread"),
                HeatRecipe.destroy(IIngredient.of(TFCItemFood.get(Food.RYE_BREAD)), 480).setRegistryName("burned_rye_bread"),
                HeatRecipe.destroy(IIngredient.of(TFCItemFood.get(Food.WHEAT_BREAD)), 480).setRegistryName("burned_wheat_bread"),

                // Meat
                HeatRecipe.destroy(IIngredient.of(TFCItemFood.get(Food.COOKED_BEEF)), 480).setRegistryName("burned_beef"),
                HeatRecipe.destroy(IIngredient.of(TFCItemFood.get(Food.COOKED_PORK)), 480).setRegistryName("burned_pork"),
                HeatRecipe.destroy(IIngredient.of(TFCItemFood.get(Food.COOKED_CHICKEN)), 480).setRegistryName("burned_chicken"),
                HeatRecipe.destroy(IIngredient.of(TFCItemFood.get(Food.COOKED_MUTTON)), 480).setRegistryName("burned_mutton"),
                HeatRecipe.destroy(IIngredient.of(TFCItemFood.get(Food.COOKED_FISH)), 480).setRegistryName("burned_fish"),
                HeatRecipe.destroy(IIngredient.of(TFCItemFood.get(Food.COOKED_BEAR)), 480).setRegistryName("burned_bear"),
                HeatRecipe.destroy(IIngredient.of(TFCItemFood.get(Food.COOKED_CALAMARI)), 480).setRegistryName("burned_calamari"),
                HeatRecipe.destroy(IIngredient.of(TFCItemFood.get(Food.COOKED_HORSE_MEAT)), 480).setRegistryName("burned_horse_meat"),
                HeatRecipe.destroy(IIngredient.of(TFCItemFood.get(Food.COOKED_PHEASANT)), 480).setRegistryName("burned_pheasant"),
                HeatRecipe.destroy(IIngredient.of(TFCItemFood.get(Food.COOKED_RABBIT)), 480).setRegistryName("burned_rabbit"),
                HeatRecipe.destroy(IIngredient.of(TFCItemFood.get(Food.COOKED_WOLF)), 480).setRegistryName("burned_wolf"),
                HeatRecipe.destroy(IIngredient.of(TFCItemFood.get(Food.COOKED_VENISON)), 480).setRegistryName("burned_venison"),
                HeatRecipe.destroy(IIngredient.of(TFCItemFood.get(Food.COOKED_CAMELIDAE)), 480).setRegistryName("burned_camelidae"),
                HeatRecipe.destroy(IIngredient.of(TFCItemFood.get(Food.COOKED_MONGOOSE)), 480).setRegistryName("burned_mongoose"),

                // Egg
                HeatRecipe.destroy(IIngredient.of(TFCItemFood.get(Food.COOKED_EGG)), 480).setRegistryName("burned_egg"),

                // Glazed terracotta, because minecraft decided *this* one should not use metadata.
                new HeatRecipeSimple(IIngredient.of(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.WHITE.getMetadata())), new ItemStack(Blocks.WHITE_GLAZED_TERRACOTTA), 1200).setRegistryName("white_glazed_terracotta"),
                new HeatRecipeSimple(IIngredient.of(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.ORANGE.getMetadata())), new ItemStack(Blocks.ORANGE_GLAZED_TERRACOTTA), 1200).setRegistryName("orange_glazed_terracotta"),
                new HeatRecipeSimple(IIngredient.of(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.MAGENTA.getMetadata())), new ItemStack(Blocks.MAGENTA_GLAZED_TERRACOTTA), 1200).setRegistryName("magenta_glazed_terracotta"),
                new HeatRecipeSimple(IIngredient.of(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.LIGHT_BLUE.getMetadata())), new ItemStack(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA), 1200).setRegistryName("light_blue_glazed_terracotta"),
                new HeatRecipeSimple(IIngredient.of(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.YELLOW.getMetadata())), new ItemStack(Blocks.YELLOW_GLAZED_TERRACOTTA), 1200).setRegistryName("yellow_glazed_terracotta"),
                new HeatRecipeSimple(IIngredient.of(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.LIME.getMetadata())), new ItemStack(Blocks.LIME_GLAZED_TERRACOTTA), 1200).setRegistryName("lime_glazed_terracotta"),
                new HeatRecipeSimple(IIngredient.of(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.PINK.getMetadata())), new ItemStack(Blocks.PINK_GLAZED_TERRACOTTA), 1200).setRegistryName("pink_glazed_terracotta"),
                new HeatRecipeSimple(IIngredient.of(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.GRAY.getMetadata())), new ItemStack(Blocks.GRAY_GLAZED_TERRACOTTA), 1200).setRegistryName("gray_glazed_terracotta"),
                new HeatRecipeSimple(IIngredient.of(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.SILVER.getMetadata())), new ItemStack(Blocks.SILVER_GLAZED_TERRACOTTA), 1200).setRegistryName("silver_glazed_terracotta"),
                new HeatRecipeSimple(IIngredient.of(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.CYAN.getMetadata())), new ItemStack(Blocks.CYAN_GLAZED_TERRACOTTA), 1200).setRegistryName("cyan_glazed_terracotta"),
                new HeatRecipeSimple(IIngredient.of(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.PURPLE.getMetadata())), new ItemStack(Blocks.PURPLE_GLAZED_TERRACOTTA), 1200).setRegistryName("purple_glazed_terracotta"),
                new HeatRecipeSimple(IIngredient.of(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.BLUE.getMetadata())), new ItemStack(Blocks.BLUE_GLAZED_TERRACOTTA), 1200).setRegistryName("blue_glazed_terracotta"),
                new HeatRecipeSimple(IIngredient.of(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.BROWN.getMetadata())), new ItemStack(Blocks.BROWN_GLAZED_TERRACOTTA), 1200).setRegistryName("brown_glazed_terracotta"),
                new HeatRecipeSimple(IIngredient.of(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.GREEN.getMetadata())), new ItemStack(Blocks.GREEN_GLAZED_TERRACOTTA), 1200).setRegistryName("green_glazed_terracotta"),
                new HeatRecipeSimple(IIngredient.of(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.RED.getMetadata())), new ItemStack(Blocks.RED_GLAZED_TERRACOTTA), 1200).setRegistryName("red_glazed_terracotta"),
                new HeatRecipeSimple(IIngredient.of(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, EnumDyeColor.BLACK.getMetadata())), new ItemStack(Blocks.BLACK_GLAZED_TERRACOTTA), 1200).setRegistryName("black_glazed_terracotta")
        );
    }

    public static void registerAnvilRecipes()
    {
        IForgeRegistry<AnvilRecipe> r = TFCRegistries.ANVIL;

        for (Material material : GregTechAPI.MATERIAL_REGISTRY) {
            if (material.hasFlag(TFCMaterialFlags.TFC_MATERIAL) && !material.hasFlag(UNUSABLE_IN_TFC)) {

                // Ingot -> Plate
                r.register(new AnvilRecipe(
                        new ResourceLocation(MOD_ID, "ingot_to_plate_" + material.getUnlocalizedName()),
                        IIngredient.of(OreDictUnifier.get(OrePrefix.ingot, material)),
                        OreDictUnifier.get(OrePrefix.plate, material),
                        material.getProperty(TFCPropertyKey.TFC).getMaterialTier(),
                        GENERAL,
                        HIT_LAST, HIT_SECOND_LAST, HIT_THIRD_LAST));

                // Ingot -> Stick
                r.register(new AnvilRecipe(
                        new ResourceLocation(MOD_ID, "ingot_to_stick_" + material.getUnlocalizedName()),
                        IIngredient.of(OreDictUnifier.get(OrePrefix.ingot, material)),
                        OreDictUnifier.get(OrePrefix.stick, material, 2),
                        material.getProperty(TFCPropertyKey.TFC).getMaterialTier(),
                        GENERAL,
                        DRAW_LAST, DRAW_NOT_LAST, PUNCH_NOT_LAST));

                if (material.hasProperty(PropertyKey.TOOL)) {
                    // Ingot x2 -> Sword Head
                    r.register(new AnvilRecipe(
                            new ResourceLocation(MOD_ID, "double_ingot_to_sword_" + material.getUnlocalizedName()),
                            IIngredient.of(OreDictUnifier.get(TFCOrePrefix.ingotDouble, material)),
                            OreDictUnifier.get(TFCOrePrefix.toolHeadSword, material),
                            material.getProperty(TFCPropertyKey.TFC).getMaterialTier(),
                            WEAPONS,
                            HIT_LAST, BEND_SECOND_LAST, BEND_THIRD_LAST));

                    // Ingot x3 -> Pickaxe Head
                    r.register(new AnvilRecipe(
                            new ResourceLocation(MOD_ID, "triple_ingot_to_pickaxe_" + material.getUnlocalizedName()),
                            IIngredient.of(OreDictUnifier.get(TFCOrePrefix.ingotTriple, material)),
                            OreDictUnifier.get(TFCOrePrefix.toolHeadPickaxe, material),
                            material.getProperty(TFCPropertyKey.TFC).getMaterialTier(),
                            TOOLS,
                            PUNCH_LAST, BEND_NOT_LAST, DRAW_NOT_LAST));

                    // Ingot x3 -> Axe Head
                    r.register(new AnvilRecipe(
                            new ResourceLocation(MOD_ID, "ingot_to_axe_" + material.getUnlocalizedName()),
                            IIngredient.of(OreDictUnifier.get(TFCOrePrefix.ingotTriple, material)),
                            OreDictUnifier.get(TFCOrePrefix.toolHeadAxe, material),
                            material.getProperty(TFCPropertyKey.TFC).getMaterialTier(),
                            TOOLS,
                            PUNCH_LAST, BEND_NOT_LAST, DRAW_NOT_LAST));

                    // Ingot x1 -> Shovel Head
                    r.register(new AnvilRecipe(
                            new ResourceLocation(MOD_ID, "ingot_to_shovel_" + material.getUnlocalizedName()),
                            IIngredient.of(OreDictUnifier.get(OrePrefix.ingot, material)),
                            OreDictUnifier.get(TFCOrePrefix.toolHeadShovel, material),
                            material.getProperty(TFCPropertyKey.TFC).getMaterialTier(),
                            TOOLS,
                            PUNCH_LAST, HIT_NOT_LAST));

                    // Ingot x2 -> Saw Head
                    r.register(new AnvilRecipe(
                            new ResourceLocation(MOD_ID, "ingot_to_saw_" + material.getUnlocalizedName()),
                            IIngredient.of(OreDictUnifier.get(TFCOrePrefix.ingotDouble, material)),
                            OreDictUnifier.get(TFCOrePrefix.toolHeadSaw, material),
                            material.getProperty(TFCPropertyKey.TFC).getMaterialTier(),
                            TOOLS,
                            HIT_LAST, HIT_SECOND_LAST));

                    // Ingot x6 -> Hammer Head
                    r.register(new AnvilRecipe(
                            new ResourceLocation(MOD_ID, "ingot_to_hammer_" + material.getUnlocalizedName()),
                            IIngredient.of(OreDictUnifier.get(TFCOrePrefix.ingotHex, material)),
                            OreDictUnifier.get(TFCOrePrefix.toolHeadHammer, material),
                            material.getProperty(TFCPropertyKey.TFC).getMaterialTier(),
                            TOOLS,
                            PUNCH_LAST, SHRINK_NOT_LAST));

                    // Ingot x3 -> Sense Head
                    r.register(new AnvilRecipe(
                            new ResourceLocation(MOD_ID, "ingot_to_sense_" + material.getUnlocalizedName()),
                            IIngredient.of(OreDictUnifier.get(TFCOrePrefix.ingotTriple, material)),
                            OreDictUnifier.get(TFCOrePrefix.toolHeadSense, material),
                            material.getProperty(TFCPropertyKey.TFC).getMaterialTier(),
                            WEAPONS,
                            HIT_LAST, DRAW_SECOND_LAST, BEND_THIRD_LAST));

                    // Ingot x1 -> Knife Head
                    r.register(new AnvilRecipe(
                            new ResourceLocation(MOD_ID, "ingot_to_knife_" + material.getUnlocalizedName()),
                            IIngredient.of(OreDictUnifier.get(OrePrefix.ingot, material)),
                            OreDictUnifier.get(TFCOrePrefix.toolHeadKnife, material),
                            material.getProperty(TFCPropertyKey.TFC).getMaterialTier(),
                            WEAPONS,
                            HIT_LAST, DRAW_SECOND_LAST, DRAW_THIRD_LAST));

                    // Ingot 3x -> Propick
                    r.register(new AnvilRecipe(
                            new ResourceLocation(MOD_ID, "ingot_to_propick_" + material.getUnlocalizedName()),
                            IIngredient.of(OreDictUnifier.get(TFCOrePrefix.ingotTriple, material)),
                            OreDictUnifier.get(TFCOrePrefix.toolHeadPropick, material),
                            material.getProperty(TFCPropertyKey.TFC).getMaterialTier(),
                            TOOLS,
                            PUNCH_LAST, DRAW_NOT_LAST, BEND_NOT_LAST));

                    // Ingot 2x -> Chisel Head
                    r.register(new AnvilRecipe(
                            new ResourceLocation(MOD_ID, "ingot_to_chisel_" + material.getUnlocalizedName()),
                            IIngredient.of(OreDictUnifier.get(TFCOrePrefix.ingotDouble, material)),
                            OreDictUnifier.get(TFCOrePrefix.toolHeadChisel, material),
                            material.getProperty(TFCPropertyKey.TFC).getMaterialTier(),
                            TOOLS,
                            HIT_LAST, HIT_NOT_LAST, DRAW_NOT_LAST));

                    // Ingot 3x -> Javelin Head
                    r.register(new AnvilRecipe(
                            new ResourceLocation(MOD_ID, "ingot_to_javelin_" + material.getUnlocalizedName()),
                            IIngredient.of(OreDictUnifier.get(TFCOrePrefix.ingotTriple, material)),
                            OreDictUnifier.get(TFCOrePrefix.toolHeadJavelin, material),
                            material.getProperty(TFCPropertyKey.TFC).getMaterialTier(),
                            WEAPONS,
                            HIT_LAST, HIT_SECOND_LAST, DRAW_THIRD_LAST));

                    // Ingot 6x -> TUYERE
                    r.register(new AnvilRecipe(
                            new ResourceLocation(MOD_ID, "ingot_to_tuyere_" + material.getUnlocalizedName()),
                            IIngredient.of(OreDictUnifier.get(TFCOrePrefix.ingotHex, material)),
                            TFCToolItems.TUYERE.get(material),
                            material.getProperty(TFCPropertyKey.TFC).getMaterialTier(),
                            GENERAL,
                            BEND_LAST, BEND_SECOND_LAST));
                }
            }
        }

        r.register(new AnvilRecipe(
                new ResourceLocation(MOD_ID, "high_carbon_steel"),
                IIngredient.of(OreDictUnifier.get(OrePrefix.ingot, TFCMaterials.PigIron)),
                OreDictUnifier.get(OrePrefix.ingot, TFCMaterials.HighCarbonSteel),
                TFCMaterials.HighCarbonSteel.getProperty(TFCPropertyKey.TFC).getMaterialTier(),
                null,
                HIT_ANY, HIT_ANY, HIT_ANY));

        r.register(new AnvilRecipe(
                new ResourceLocation(MOD_ID, "steel"),
                IIngredient.of(OreDictUnifier.get(OrePrefix.ingot, TFCMaterials.HighCarbonSteel)),
                OreDictUnifier.get(OrePrefix.ingot, Materials.Steel),
                Materials.Steel.getProperty(TFCPropertyKey.TFC).getMaterialTier(),
                null,
                HIT_ANY, HIT_ANY, HIT_ANY));

        r.register(new AnvilRecipe(
                new ResourceLocation(MOD_ID, "black_steel"),
                IIngredient.of(OreDictUnifier.get(OrePrefix.ingot, TFCMaterials.HighCarbonBlackSteel)),
                OreDictUnifier.get(OrePrefix.ingot,  Materials.BlackSteel),
                Materials.BlackSteel.getProperty(TFCPropertyKey.TFC).getMaterialTier(),
                null,
                HIT_ANY, HIT_ANY, HIT_ANY));

        r.register(new AnvilRecipe(
                new ResourceLocation(MOD_ID, "blue_steel"),
                IIngredient.of(OreDictUnifier.get(OrePrefix.ingot, TFCMaterials.HighCarbonBlueSteel)),
                OreDictUnifier.get(OrePrefix.ingot,  Materials.BlueSteel),
                Materials.BlueSteel.getProperty(TFCPropertyKey.TFC).getMaterialTier(),
                null,
                HIT_ANY, HIT_ANY, HIT_ANY));

        r.register(new AnvilRecipe(
                new ResourceLocation(MOD_ID, "red_steel"),
                IIngredient.of(OreDictUnifier.get(OrePrefix.ingot, TFCMaterials.HighCarbonRedSteel)),
                OreDictUnifier.get(OrePrefix.ingot,  Materials.RedSteel),
                Materials.RedSteel.getProperty(TFCPropertyKey.TFC).getMaterialTier(),
                null,
                HIT_ANY, HIT_ANY, HIT_ANY));

        // Misc
        // addAnvil(r, INGOT, LAMP, false, GENERAL, BEND_LAST, BEND_SECOND_LAST, DRAW_THIRD_LAST);
        // addAnvil(r, SHEET, TRAPDOOR, false, GENERAL, BEND_LAST, DRAW_SECOND_LAST, DRAW_THIRD_LAST);

        // Armor
        // addAnvil(r, DOUBLE_SHEET, UNFINISHED_HELMET, true, ARMOR, HIT_LAST, BEND_SECOND_LAST, BEND_THIRD_LAST);
        // addAnvil(r, DOUBLE_SHEET, UNFINISHED_CHESTPLATE, true, ARMOR, HIT_LAST, HIT_SECOND_LAST, UPSET_THIRD_LAST);
        // addAnvil(r, DOUBLE_SHEET, UNFINISHED_GREAVES, true, ARMOR, BEND_ANY, DRAW_ANY, HIT_ANY);
        // addAnvil(r, SHEET, UNFINISHED_BOOTS, true, ARMOR, BEND_LAST, BEND_SECOND_LAST, SHRINK_THIRD_LAST);

        // Shields
        // addAnvil(r, DOUBLE_SHEET, SHIELD, true, ARMOR, UPSET_LAST, BEND_SECOND_LAST, BEND_THIRD_LAST);

        // Blooms
        r.register(new AnvilRecipeMeasurable(new ResourceLocation(MOD_ID, "refining_bloom"), IIngredient.of(TFCItems.UNREFINED_BLOOM), new ItemStack(TFCItems.REFINED_BLOOM), 2, HIT_LAST, HIT_SECOND_LAST, HIT_THIRD_LAST));
        r.register(new AnvilRecipeSplitting(new ResourceLocation(MOD_ID, "splitting_bloom"), IIngredient.of(TFCItems.REFINED_BLOOM), new ItemStack(TFCItems.REFINED_BLOOM), 144, 2, PUNCH_LAST));
        r.register(new AnvilRecipe(new ResourceLocation(MOD_ID, "iron_bloom"), x -> {
            if (x.getItem() == TFCItems.REFINED_BLOOM)
            {
                IForgeable cap = x.getCapability(CapabilityForgeable.FORGEABLE_CAPABILITY, null);
                if (cap instanceof IForgeableMeasurableMetal)
                {
                    return ((IForgeableMeasurableMetal) cap).getMaterial() == Materials.WroughtIron && ((IForgeableMeasurableMetal) cap).getMetalAmount() == 144;
                }
            }
            return false;
        }, OreDictUnifier.get(OrePrefix.ingot, Materials.WroughtIron), 2, null, HIT_LAST, HIT_SECOND_LAST, HIT_THIRD_LAST));

        // Misc
        // addAnvil(r, "iron_bars", SHEET, WROUGHT_IRON, new ItemStack(Blocks.IRON_BARS, 8), Metal.Tier.TIER_III, GENERAL, UPSET_LAST, PUNCH_SECOND_LAST, PUNCH_THIRD_LAST);
        // addAnvil(r, "iron_bars_double", DOUBLE_SHEET, WROUGHT_IRON, new ItemStack(Blocks.IRON_BARS, 16), Metal.Tier.TIER_III, GENERAL, UPSET_LAST, PUNCH_SECOND_LAST, PUNCH_THIRD_LAST);
        // addAnvil(r, "iron_door", SHEET, WROUGHT_IRON, new ItemStack(Items.IRON_DOOR), Metal.Tier.TIER_III, GENERAL, HIT_LAST, DRAW_NOT_LAST, PUNCH_NOT_LAST);
        // addAnvil(r, "red_steel_bucket", SHEET, RED_STEEL, new ItemStack(ItemMetal.get(Metal.RED_STEEL, BUCKET)), Metal.Tier.TIER_VI, GENERAL, BEND_LAST, BEND_SECOND_LAST, BEND_THIRD_LAST);
        // addAnvil(r, "blue_steel_bucket", SHEET, BLUE_STEEL, new ItemStack(ItemMetal.get(Metal.BLUE_STEEL, BUCKET)), Metal.Tier.TIER_VI, GENERAL, BEND_LAST, BEND_SECOND_LAST, BEND_THIRD_LAST);
        // addAnvil(r, "wrought_iron_grill", DOUBLE_SHEET, WROUGHT_IRON, new ItemStack(ItemsTFC.WROUGHT_IRON_GRILL), Metal.Tier.TIER_III, GENERAL, DRAW_ANY, PUNCH_LAST, PUNCH_NOT_LAST);
        // addAnvil(r, "brass_mechanisms", INGOT, BRASS, new ItemStack(ItemsTFC.BRASS_MECHANISMS, 2), Metal.Tier.TIER_II, GENERAL, PUNCH_LAST, HIT_SECOND_LAST, PUNCH_THIRD_LAST);
    }

    public static void registerKnappingRecipes()
    {
        IForgeRegistry<KnappingRecipe> r = TFCRegistries.KNAPPING;

        // Mud Bricks Knapping
        {
            r.register(new KnappingRecipeStone(KnappingType.MUD, rockIn -> new ItemStack(ItemUnfiredMudBrick.get(rockIn), 3), "XXXXX", "     ", "XXXXX", "     ", "XXXXX").setRegistryName(MOD_ID, "knapping_mud_brick"));
        }

        // Stone + Flint Tool Heads
        for (TFCOrePrefixExtended orePrefixRegistry : TFGUtils.TFC_OREPREFIX_REGISTRY)
        {
            if (orePrefixRegistry.isHasStoneKnappingRecipe())
            {
                // This covers all stone -> single tool head recipes
                r.register(new KnappingRecipeStone(KnappingType.STONE, rockIn -> OreDictUnifier.get(orePrefixRegistry.getOrePrefix(), Materials.Stone), orePrefixRegistry.getStoneKnappingRecipe()).setRegistryName(MOD_ID, orePrefixRegistry.getOrePrefix().name().toLowerCase() + "_stone_head"));

                // This covers all flint -> single tool head recipes
                r.register(new KnappingRecipeSimple(KnappingType.FLINT, true, OreDictUnifier.get(orePrefixRegistry.getOrePrefix(), Materials.Flint), orePrefixRegistry.getStoneKnappingRecipe()).setRegistryName(MOD_ID, orePrefixRegistry.getOrePrefix().name().toLowerCase() + "_flint_head"));
            }

        }

        // these recipes cover all cases where multiple stone and flint items can be made
        // recipes are already mirror checked
        r.registerAll(
                new KnappingRecipeStone(KnappingType.STONE, rockIn -> OreDictUnifier.get(TFCOrePrefix.toolHeadKnife, Materials.Stone, 2), "X  X ", "XX XX", "XX XX", "XX XX", "XX XX").setRegistryName("stone_knife_head_1"),
                new KnappingRecipeStone(KnappingType.STONE, rockIn -> OreDictUnifier.get(TFCOrePrefix.toolHeadKnife, Materials.Stone, 2), "X   X", "XX XX", "XX XX", "XX XX", "XX XX").setRegistryName("stone_knife_head_2"),
                new KnappingRecipeStone(KnappingType.STONE, rockIn -> OreDictUnifier.get(TFCOrePrefix.toolHeadKnife, Materials.Stone, 2), " X X ", "XX XX", "XX XX", "XX XX", "XX XX").setRegistryName("stone_knife_head_3"),
                new KnappingRecipeStone(KnappingType.STONE, rockIn -> OreDictUnifier.get(TFCOrePrefix.toolHeadHoe, Materials.Stone, 2), "XXXXX", "XX   ", "     ", "XXXXX", "XX   ").setRegistryName("stone_hoe_head_1"),
                new KnappingRecipeStone(KnappingType.STONE, rockIn -> OreDictUnifier.get(TFCOrePrefix.toolHeadHoe, Materials.Stone, 2), "XXXXX", "XX   ", "     ", "XXXXX", "   XX").setRegistryName("stone_hoe_head_2"),

                new KnappingRecipeSimple(KnappingType.FLINT, true, OreDictUnifier.get(TFCOrePrefix.toolHeadKnife, Materials.Flint, 2), "X  X ", "XX XX", "XX XX", "XX XX", "XX XX").setRegistryName("flint_knife_head_1"),
                new KnappingRecipeSimple(KnappingType.FLINT, true, OreDictUnifier.get(TFCOrePrefix.toolHeadKnife, Materials.Flint, 2), "X   X", "XX XX", "XX XX", "XX XX", "XX XX").setRegistryName("flint_knife_head_2"),
                new KnappingRecipeSimple(KnappingType.FLINT, true, OreDictUnifier.get(TFCOrePrefix.toolHeadKnife, Materials.Flint, 2), " X X ", "XX XX", "XX XX", "XX XX", "XX XX").setRegistryName("flint_knife_head_3"),
                new KnappingRecipeSimple(KnappingType.FLINT, true, OreDictUnifier.get(TFCOrePrefix.toolHeadHoe, Materials.Flint, 2), "XXXXX", "XX   ", "     ", "XXXXX", "XX   ").setRegistryName("flint_hoe_head_1"),
                new KnappingRecipeSimple(KnappingType.FLINT, true, OreDictUnifier.get(TFCOrePrefix.toolHeadHoe, Materials.Flint, 2), "XXXXX", "XX   ", "     ", "XXXXX", "   XX").setRegistryName("flint_hoe_head_2")
        );

        // Clay Items
        for (TFCOrePrefixExtended extendedOrePrefix : TFGUtils.TFC_OREPREFIX_REGISTRY)
        {
            if (extendedOrePrefix.isHasMold())
            {
                int amount = extendedOrePrefix.getOrePrefix() == OrePrefix.ingot ? 2 : 1;
                r.register(new KnappingRecipeSimple(KnappingType.CLAY, true, new ItemStack(ItemUnfiredClayMold.get(extendedOrePrefix.getOrePrefix()), amount), extendedOrePrefix.getKnappingRecipe()).setRegistryName(MOD_ID, extendedOrePrefix.getOrePrefix().name() + "_clay_mold"));
                r.register(new KnappingRecipeSimple(KnappingType.EARTHENWARE_CLAY, true, new ItemStack(ItemUnfiredEarthenwareMold.get(extendedOrePrefix.getOrePrefix()), amount), extendedOrePrefix.getKnappingRecipe()).setRegistryName(MOD_ID, extendedOrePrefix.getOrePrefix().name() + "_earthenware_mold"));
                r.register(new KnappingRecipeSimple(KnappingType.KAOLINITE_CLAY, true, new ItemStack(ItemUnfiredKaoliniteMold.get(extendedOrePrefix.getOrePrefix()), amount), extendedOrePrefix.getKnappingRecipe()).setRegistryName(MOD_ID, extendedOrePrefix.getOrePrefix().name() + "_kaolinite_mold"));
                r.register(new KnappingRecipeSimple(KnappingType.STONEWARE_CLAY, true, new ItemStack(ItemUnfiredStonewareMold.get(extendedOrePrefix.getOrePrefix()), amount), extendedOrePrefix.getKnappingRecipe()).setRegistryName(MOD_ID, extendedOrePrefix.getOrePrefix().name() + "_stoneware_mold"));
            }
        }

        r.registerAll(
                // Clay
                new KnappingRecipeSimple(KnappingType.CLAY, true, new ItemStack(TFCItems.UNFIRED_VESSEL), " XXX ", "XXXXX", "XXXXX", "XXXXX", " XXX ").setRegistryName("clay_small_vessel"),
                new KnappingRecipeSimple(KnappingType.CLAY, true, new ItemStack(TFCItems.UNFIRED_JUG), " X   ", "XXXX ", "XXX X", "XXXX ", "XXX  ").setRegistryName("clay_jug"),
                new KnappingRecipeSimple(KnappingType.CLAY, true, new ItemStack(TFCItems.UNFIRED_POT), "X   X", "X   X", "X   X", "XXXXX", " XXX ").setRegistryName("clay_pot"),
                new KnappingRecipeSimple(KnappingType.CLAY, false, new ItemStack(TFCItems.UNFIRED_BOWL, 2), "X   X", " XXX ").setRegistryName(MOD_ID, "clay_bowl"),
                new KnappingRecipeSimple(KnappingType.CLAY, true, new ItemStack(TFCItems.UNFIRED_BOWL, 4), "X   X", " XXX ", "     ", "X   X", " XXX ").setRegistryName("clay_bowl_2"),
                new KnappingRecipeSimple(KnappingType.CLAY, true, new ItemStack(TFCItems.UNFIRED_LARGE_VESSEL), "X   X", "X   X", "X   X", "X   X", "XXXXX").setRegistryName("clay_large_vessel"),
                new KnappingRecipeSimple(KnappingType.CLAY, true, new ItemStack(TFCItems.UNFIRED_BRICK, 3), "XXXXX", "     ", "XXXXX", "     ", "XXXXX").setRegistryName("clay_brick"),
                new KnappingRecipeSimple(KnappingType.CLAY, true, new ItemStack(TFCItems.UNFIRED_FLOWER_POT, 2), " X X ", " XXX ", "     ", " X X ", " XXX ").setRegistryName("clay_flower_pot"),

                // Earthenware
                new KnappingRecipeSimple(KnappingType.EARTHENWARE_CLAY, true, new ItemStack(TFCItems.UNFIRED_EARTHENWARE_BRICK, 3), "XXXXX", "     ", "XXXXX", "     ", "XXXXX").setRegistryName(MOD_ID, "earthenware_clay_brick"),
                new KnappingRecipeSimple(KnappingType.EARTHENWARE_CLAY, true, new ItemStack(TFCItems.UNFIRED_EARTHENWARE_VESSEL), " XXX ", "XXXXX", "XXXXX", "XXXXX", " XXX ").setRegistryName(MOD_ID, "earthenware_clay_small_vessel"),
                new KnappingRecipeSimple(KnappingType.EARTHENWARE_CLAY, true, new ItemStack(TFCItems.UNFIRED_EARTHENWARE_JUG), " X   ", "XXXX ", "XXX X", "XXXX ", "XXX  ").setRegistryName(MOD_ID, "earthenware_clay_jug"),
                new KnappingRecipeSimple(KnappingType.EARTHENWARE_CLAY, true, new ItemStack(TFCItems.UNFIRED_EARTHENWARE_POT), "X   X", "X   X", "X   X", "XXXXX", " XXX ").setRegistryName(MOD_ID, "earthenware_clay_pot"),
                new KnappingRecipeSimple(KnappingType.EARTHENWARE_CLAY, false, new ItemStack(TFCItems.UNFIRED_EARTHENWARE_BOWL, 2), "X   X", " XXX ").setRegistryName(MOD_ID, "earthenware_clay_bowl"),
                new KnappingRecipeSimple(KnappingType.EARTHENWARE_CLAY, true, new ItemStack(TFCItems.UNFIRED_EARTHENWARE_BOWL, 4), "X   X", " XXX ", "     ", "X   X", " XXX ").setRegistryName(MOD_ID, "earthenware_clay_bowl_2"),
                new KnappingRecipeSimple(KnappingType.EARTHENWARE_CLAY, true, new ItemStack(TFCItems.UNFIRED_EARTHENWARE_LARGE_VESSEL), "X   X", "X   X", "X   X", "X   X", "XXXXX").setRegistryName(MOD_ID, "earthenware_clay_large_vessel"),

                // Kaolinite
                new KnappingRecipeSimple(KnappingType.KAOLINITE_CLAY, true, new ItemStack(TFCItems.UNFIRED_KAOLINITE_BRICK, 3), "XXXXX", "     ", "XXXXX", "     ", "XXXXX").setRegistryName(MOD_ID, "kaolinite_clay_brick"),
                new KnappingRecipeSimple(KnappingType.KAOLINITE_CLAY, true, new ItemStack(TFCItems.UNFIRED_KAOLINITE_VESSEL), " XXX ", "XXXXX", "XXXXX", "XXXXX", " XXX ").setRegistryName(MOD_ID, "kaolinite_clay_small_vessel"),
                new KnappingRecipeSimple(KnappingType.KAOLINITE_CLAY, true, new ItemStack(TFCItems.UNFIRED_KAOLINITE_JUG), " X   ", "XXXX ", "XXX X", "XXXX ", "XXX  ").setRegistryName(MOD_ID, "kaolinite_clay_jug"),
                new KnappingRecipeSimple(KnappingType.KAOLINITE_CLAY, true, new ItemStack(TFCItems.UNFIRED_KAOLINITE_POT), "X   X", "X   X", "X   X", "XXXXX", " XXX ").setRegistryName(MOD_ID, "kaolinite_clay_pot"),
                new KnappingRecipeSimple(KnappingType.KAOLINITE_CLAY, false, new ItemStack(TFCItems.UNFIRED_KAOLINITE_BOWL, 2), "X   X", " XXX ").setRegistryName(MOD_ID, "kaolinite_clay_bowl"),
                new KnappingRecipeSimple(KnappingType.KAOLINITE_CLAY, true, new ItemStack(TFCItems.UNFIRED_KAOLINITE_BOWL, 4), "X   X", " XXX ", "     ", "X   X", " XXX ").setRegistryName(MOD_ID, "kaolinite_clay_bowl_2"),
                new KnappingRecipeSimple(KnappingType.KAOLINITE_CLAY, true, new ItemStack(TFCItems.UNFIRED_KAOLINITE_LARGE_VESSEL), "X   X", "X   X", "X   X", "X   X", "XXXXX").setRegistryName(MOD_ID, "kaolinite_clay_large_vessel"),

                // Stoneware
                new KnappingRecipeSimple(KnappingType.STONEWARE_CLAY, true, new ItemStack(TFCItems.UNFIRED_STONEWARE_BRICK, 3), "XXXXX", "     ", "XXXXX", "     ", "XXXXX").setRegistryName(MOD_ID, "stoneware_clay_brick"),
                new KnappingRecipeSimple(KnappingType.STONEWARE_CLAY, true, new ItemStack(TFCItems.UNFIRED_STONEWARE_VESSEL), " XXX ", "XXXXX", "XXXXX", "XXXXX", " XXX ").setRegistryName(MOD_ID, "stoneware_clay_small_vessel"),
                new KnappingRecipeSimple(KnappingType.STONEWARE_CLAY, true, new ItemStack(TFCItems.UNFIRED_STONEWARE_JUG), " X   ", "XXXX ", "XXX X", "XXXX ", "XXX  ").setRegistryName(MOD_ID, "stoneware_clay_jug"),
                new KnappingRecipeSimple(KnappingType.STONEWARE_CLAY, true, new ItemStack(TFCItems.UNFIRED_STONEWARE_POT), "X   X", "X   X", "X   X", "XXXXX", " XXX ").setRegistryName(MOD_ID, "stoneware_clay_pot"),
                new KnappingRecipeSimple(KnappingType.STONEWARE_CLAY, false, new ItemStack(TFCItems.UNFIRED_STONEWARE_BOWL, 2), "X   X", " XXX ").setRegistryName(MOD_ID, "stoneware_clay_bowl"),
                new KnappingRecipeSimple(KnappingType.STONEWARE_CLAY, true, new ItemStack(TFCItems.UNFIRED_STONEWARE_BOWL, 4), "X   X", " XXX ", "     ", "X   X", " XXX ").setRegistryName(MOD_ID, "stoneware_clay_bowl_2"),
                new KnappingRecipeSimple(KnappingType.STONEWARE_CLAY, true, new ItemStack(TFCItems.UNFIRED_STONEWARE_LARGE_VESSEL), "X   X", "X   X", "X   X", "X   X", "XXXXX").setRegistryName(MOD_ID, "stoneware_clay_large_vessel")
        );

        /* LEATHER ITEMS */
        r.registerAll(
                new KnappingRecipeSimple(KnappingType.LEATHER, true, new ItemStack(Items.LEATHER_HELMET), "XXXXX", "X   X", "X   X", "     ", "     ").setRegistryName("leather_helmet"),
                new KnappingRecipeSimple(KnappingType.LEATHER, true, new ItemStack(Items.LEATHER_CHESTPLATE), "X   X", "XXXXX", "XXXXX", "XXXXX", "XXXXX").setRegistryName("leather_chestplate"),
                new KnappingRecipeSimple(KnappingType.LEATHER, true, new ItemStack(Items.LEATHER_LEGGINGS), "XXXXX", "XXXXX", "XX XX", "XX XX", "XX XX").setRegistryName("leather_leggings"),
                new KnappingRecipeSimple(KnappingType.LEATHER, true, new ItemStack(Items.LEATHER_BOOTS), "XX   ", "XX   ", "XX   ", "XXXX ", "XXXXX").setRegistryName("leather_boots"),
                new KnappingRecipeSimple(KnappingType.LEATHER, true, new ItemStack(Items.SADDLE), "  X  ", "XXXXX", "XXXXX", "XXXXX", "  X  ").setRegistryName("leather_saddle"),
                new KnappingRecipeSimple(KnappingType.LEATHER, true, new ItemStack(TFCItems.QUIVER), " XXXX", "X XXX", "X XXX", "X XXX", " XXXX").setRegistryName("leather_quiver")
        );

        /* FIRE CLAY ITEMS */
        r.registerAll(
                new KnappingRecipeSimple(KnappingType.FIRE_CLAY, true, new ItemStack(TFCItems.UNFIRED_CRUCIBLE), "X   X", "X   X", "X   X", "X   X", "XXXXX").setRegistryName("fire_clay_crucible"),
                new KnappingRecipeSimple(KnappingType.FIRE_CLAY, true, new ItemStack(TFCItems.UNFIRED_FIRE_BRICK, 3), "XXXXX", "     ", "XXXXX", "     ", "XXXXX").setRegistryName("fire_clay_brick")
        );
    }

    public static void registerWeldingRecipes()
    {
        IForgeRegistry<WeldingRecipe> r = TFCRegistries.WELDING;

        for (Material material : GregTechAPI.MATERIAL_REGISTRY) {
            if (material.hasFlag(TFCMaterialFlags.TFC_MATERIAL) && !material.hasFlag(UNUSABLE_IN_TFC)) {

                r.register(new WeldingRecipe(
                        new ResourceLocation(MOD_ID, "plate_" + material.getUnlocalizedName()),
                        IIngredient.of(OreDictUnifier.get(OrePrefix.ingot, material)),
                        IIngredient.of(OreDictUnifier.get(OrePrefix.ingot, material)),
                        OreDictUnifier.get(TFCOrePrefix.ingotDouble, material),
                        material.getProperty(TFCPropertyKey.TFC).getMaterialTier()
                ));

                r.register(new WeldingRecipe(
                        new ResourceLocation(MOD_ID, "double_plate_" + material.getUnlocalizedName()),
                        IIngredient.of(OreDictUnifier.get(TFCOrePrefix.ingotDouble, material)),
                        IIngredient.of(OreDictUnifier.get(OrePrefix.ingot, material)),
                        OreDictUnifier.get(TFCOrePrefix.ingotTriple, material),
                        material.getProperty(TFCPropertyKey.TFC).getMaterialTier()
                ));

                /*
                r.register(new WeldingRecipe(
                        new ResourceLocation(MOD_ID, "double_plate_invert_" + material.getUnlocalizedName()),
                        IIngredient.of(OreDictUnifier.get(OrePrefix.ingot, material)),
                        IIngredient.of(OreDictUnifier.get(TFCOrePrefix.ingotDouble, material)),
                        OreDictUnifier.get(TFCOrePrefix.ingotTriple, material),
                        material.getProperty(TFCPropertyKey.TFC).getMaterialTier()
                ));*/

                r.register(new WeldingRecipe(
                        new ResourceLocation(MOD_ID, "triple_plate_" + material.getUnlocalizedName()),
                        IIngredient.of(OreDictUnifier.get(TFCOrePrefix.ingotTriple, material)),
                        IIngredient.of(OreDictUnifier.get(TFCOrePrefix.ingotTriple, material)),
                        OreDictUnifier.get(TFCOrePrefix.ingotHex, material),
                        material.getProperty(TFCPropertyKey.TFC).getMaterialTier()
                ));
            }
        }

        r.register(new WeldingRecipe(
                new ResourceLocation(MOD_ID, "high_carbon_black_steel"),
                IIngredient.of(OreDictUnifier.get(OrePrefix.ingot, TFCMaterials.WeakSteel)),
                IIngredient.of(OreDictUnifier.get(OrePrefix.ingot, TFCMaterials.PigIron)),
                OreDictUnifier.get(OrePrefix.ingot, TFCMaterials.HighCarbonBlackSteel),
                TFCMaterials.HighCarbonBlackSteel.getProperty(TFCPropertyKey.TFC).getMaterialTier()
        ));

        r.register(new WeldingRecipe(
                new ResourceLocation(MOD_ID, "high_carbon_blue_steel"),
                IIngredient.of(OreDictUnifier.get(OrePrefix.ingot, TFCMaterials.WeakBlueSteel)),
                IIngredient.of(OreDictUnifier.get(OrePrefix.ingot, Materials.BlackSteel)),
                OreDictUnifier.get(OrePrefix.ingot, TFCMaterials.HighCarbonBlueSteel),
                TFCMaterials.HighCarbonBlueSteel.getProperty(TFCPropertyKey.TFC).getMaterialTier()
        ));

        r.register(new WeldingRecipe(
                new ResourceLocation(MOD_ID, "high_carbon_red_steel"),
                IIngredient.of(OreDictUnifier.get(OrePrefix.ingot, TFCMaterials.WeakRedSteel)),
                IIngredient.of(OreDictUnifier.get(OrePrefix.ingot, Materials.BlackSteel)),
                OreDictUnifier.get(OrePrefix.ingot, TFCMaterials.HighCarbonRedSteel),
                TFCMaterials.HighCarbonRedSteel.getProperty(TFCPropertyKey.TFC).getMaterialTier()
        ));

        // Armor
        // addWelding(r, UNFINISHED_HELMET, SHEET, HELMET, true, ARMOR);
        // addWelding(r, UNFINISHED_CHESTPLATE, DOUBLE_SHEET, CHESTPLATE, true, ARMOR);
        // addWelding(r, UNFINISHED_GREAVES, SHEET, GREAVES, true, ARMOR);
        // addWelding(r, UNFINISHED_BOOTS, SHEET, BOOTS, true, ARMOR);
        // addWelding(r, KNIFE_BLADE, KNIFE_BLADE, SHEARS, true, TOOLS);
    }
}
