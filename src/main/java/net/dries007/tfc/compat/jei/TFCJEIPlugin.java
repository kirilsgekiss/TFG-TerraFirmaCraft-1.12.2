/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.compat.jei;

import java.util.*;
import java.util.stream.Collectors;

import gregtech.api.GregTechAPI;
import gregtech.api.fluids.MetaFluids;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.ToolItems;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import net.dries007.tfc.compat.gregtech.materials.TFCMaterialFlags;
import net.dries007.tfc.compat.gregtech.materials.TFCMaterials;
import net.dries007.tfc.compat.tfc.TFCOrePrefixExtended;
import net.dries007.tfc.compat.tfc.TFGUtils;
import net.dries007.tfc.objects.items.metal.ItemAnvil;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.dries007.tfc.api.recipes.barrel.BarrelRecipeFoodPreservation;
import net.dries007.tfc.api.recipes.barrel.BarrelRecipeFoodTraits;
import net.dries007.tfc.api.recipes.heat.HeatRecipeMetalMelting;
import net.dries007.tfc.api.recipes.knapping.KnappingType;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.client.gui.*;
import net.dries007.tfc.compat.jei.categories.*;
import net.dries007.tfc.compat.jei.wrappers.*;
import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.objects.blocks.wood.BlockLoom;
import net.dries007.tfc.objects.container.ContainerInventoryCrafting;
import net.dries007.tfc.objects.items.ItemAnimalHide;
import net.dries007.tfc.objects.items.ItemAnimalHide.HideType;
import net.dries007.tfc.objects.items.ItemsTFC;
import net.dries007.tfc.objects.items.rock.ItemRock;
import net.dries007.tfc.objects.recipes.SaltingRecipe;
import net.dries007.tfc.compat.jei.categories.UnmoldEarthenwareCategory;
import net.dries007.tfc.compat.jei.categories.UnmoldKaoliniteCategory;
import net.dries007.tfc.compat.jei.categories.UnmoldStonewareCategory;
import net.dries007.tfc.compat.jei.categories.DryingRecipeCategory;
import net.dries007.tfc.compat.jei.categories.StickBundleRecipeCategory;
import tfcflorae.objects.items.rock.ItemMud;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

@JEIPlugin
public final class TFCJEIPlugin implements IModPlugin
{
    public static final String CRAFTING_UID = "minecraft.crafting";
    public static final String ALLOY_UID = MOD_ID + ".alloy";
    public static final String ANVIL_UID = MOD_ID + ".anvil";
    public static final String BARREL_UID = MOD_ID + ".barrel";
    public static final String BLAST_FURNACE_UID = MOD_ID + ".blast_furnace";
    public static final String BLOOMERY_UID = MOD_ID + ".bloomery";
    public static final String CHISEL_UID = MOD_ID + ".chisel";
    public static final String HEAT_UID = MOD_ID + ".heat";
    public static final String KNAP_CLAY_UID = MOD_ID + ".knap.clay";
    public static final String KNAP_FIRECLAY_UID = MOD_ID + ".knap.fireclay";
    public static final String KNAP_LEATHER_UID = MOD_ID + ".knap.leather";
    public static final String KNAP_STONE_UID = MOD_ID + ".knap.stone";
    public static final String KNAP_PINEAPPLE_LEATHER_UID = MOD_ID + ".knap.pineapple_leather";
    public static final String KNAP_BURLAP_CLOTH_UID = MOD_ID + ".knap.burlap_cloth";
    public static final String KNAP_WOOL_CLOTH_UID = MOD_ID + ".knap.wool_cloth";
    public static final String KNAP_SILK_CLOTH_UID = MOD_ID + ".knap.silk_cloth";
    public static final String KNAP_SISAL_CLOTH_UID = MOD_ID + ".knap.sisal_cloth";
    public static final String KNAP_COTTON_CLOTH_UID = MOD_ID + ".knap.cotton_cloth";
    public static final String KNAP_LINEN_CLOTH_UID = MOD_ID + ".knap.linen_cloth";
    public static final String KNAP_HEMP_CLOTH_UID = MOD_ID + ".knap.hemp_cloth";
    public static final String KNAP_YUCCA_CANVAS_UID = MOD_ID + ".knap.yucca_canvas";
    public static final String KNAP_MUD_UID = MOD_ID + ".knap.mud";
    public static final String KNAP_EARTHENWARE_CLAY_UID = MOD_ID + ".knap.earthenware_clay";
    public static final String KNAP_KAOLINITE_CLAY_UID = MOD_ID + ".knap.kaolinite_clay";
    public static final String KNAP_STONEWARE_CLAY_UID = MOD_ID + ".knap.stoneware_clay";
    public static final String KNAP_FLINT_UID = MOD_ID + ".knap.flint";
    public static final String METAL_HEAT_UID = MOD_ID + ".metal_heat";
    public static final String LOOM_UID = MOD_ID + ".loom";
    public static final String QUERN_UID = MOD_ID + ".quern";
    public static final String WELDING_UID = MOD_ID + ".welding";
    public static final String SCRAPING_UID = MOD_ID + ".scraping";
    public static final String CLAY_UNMOLD_UID = MOD_ID + ".clay.unmold";
    public static final String EARTHENWARE_UNMOLD_UID = MOD_ID + ".earthenware.unmold";
    public static final String KAOLINITE_UNMOLD_UID = MOD_ID + ".kaolinite.unmold";
    public static final String STONEWARE_UNMOLD_UID = MOD_ID + ".stoneware.unmold";
    public static final String DRY_UID = MOD_ID + ".drying";
    public static final String STICK_BUNDLE_UID = MOD_ID + ".stick_bundle";

    private static IModRegistry REGISTRY;

    /**
     * Helper method to return a collection containing all possible itemstacks registered in JEI
     *
     * @return Collection of ItemStacks
     */
    public static Collection<ItemStack> getAllIngredients()
    {
        return REGISTRY.getIngredientRegistry().getAllIngredients(VanillaTypes.ITEM);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        // Add new JEI recipe categories
        registry.addRecipeCategories(new AlloyCategory(registry.getJeiHelpers().getGuiHelper(), ALLOY_UID));
        registry.addRecipeCategories(new AnvilCategory(registry.getJeiHelpers().getGuiHelper(), ANVIL_UID));
        registry.addRecipeCategories(new BarrelCategory(registry.getJeiHelpers().getGuiHelper(), BARREL_UID));
        registry.addRecipeCategories(new BlastFurnaceCategory(registry.getJeiHelpers().getGuiHelper(), BLAST_FURNACE_UID));
        registry.addRecipeCategories(new BloomeryCategory(registry.getJeiHelpers().getGuiHelper(), BLOOMERY_UID));
        registry.addRecipeCategories(new ChiselCategory(registry.getJeiHelpers().getGuiHelper(), CHISEL_UID));
        registry.addRecipeCategories(new HeatCategory(registry.getJeiHelpers().getGuiHelper(), HEAT_UID));
        registry.addRecipeCategories(new KnappingCategory(registry.getJeiHelpers().getGuiHelper(), KNAP_CLAY_UID));
        registry.addRecipeCategories(new KnappingCategory(registry.getJeiHelpers().getGuiHelper(), KNAP_FIRECLAY_UID));
        registry.addRecipeCategories(new KnappingCategory(registry.getJeiHelpers().getGuiHelper(), KNAP_LEATHER_UID));
        registry.addRecipeCategories(new KnappingCategory(registry.getJeiHelpers().getGuiHelper(), KNAP_STONE_UID));
        registry.addRecipeCategories(new KnappingCategory(registry.getJeiHelpers().getGuiHelper(), KNAP_PINEAPPLE_LEATHER_UID));
        registry.addRecipeCategories(new KnappingCategory(registry.getJeiHelpers().getGuiHelper(), KNAP_BURLAP_CLOTH_UID));
        registry.addRecipeCategories(new KnappingCategory(registry.getJeiHelpers().getGuiHelper(), KNAP_WOOL_CLOTH_UID));
        registry.addRecipeCategories(new KnappingCategory(registry.getJeiHelpers().getGuiHelper(), KNAP_SILK_CLOTH_UID));
        registry.addRecipeCategories(new KnappingCategory(registry.getJeiHelpers().getGuiHelper(), KNAP_SISAL_CLOTH_UID));
        registry.addRecipeCategories(new KnappingCategory(registry.getJeiHelpers().getGuiHelper(), KNAP_COTTON_CLOTH_UID));
        registry.addRecipeCategories(new KnappingCategory(registry.getJeiHelpers().getGuiHelper(), KNAP_LINEN_CLOTH_UID));
        registry.addRecipeCategories(new KnappingCategory(registry.getJeiHelpers().getGuiHelper(), KNAP_HEMP_CLOTH_UID));
        registry.addRecipeCategories(new KnappingCategory(registry.getJeiHelpers().getGuiHelper(), KNAP_YUCCA_CANVAS_UID));
        registry.addRecipeCategories(new KnappingCategory(registry.getJeiHelpers().getGuiHelper(), KNAP_MUD_UID));
        registry.addRecipeCategories(new KnappingCategory(registry.getJeiHelpers().getGuiHelper(), KNAP_EARTHENWARE_CLAY_UID));
        registry.addRecipeCategories(new KnappingCategory(registry.getJeiHelpers().getGuiHelper(), KNAP_KAOLINITE_CLAY_UID));
        registry.addRecipeCategories(new KnappingCategory(registry.getJeiHelpers().getGuiHelper(), KNAP_STONEWARE_CLAY_UID));
        registry.addRecipeCategories(new KnappingCategory(registry.getJeiHelpers().getGuiHelper(), KNAP_FLINT_UID));
        registry.addRecipeCategories(new LoomCategory(registry.getJeiHelpers().getGuiHelper(), LOOM_UID));
        registry.addRecipeCategories(new MetalHeatingCategory(registry.getJeiHelpers().getGuiHelper(), METAL_HEAT_UID));
        registry.addRecipeCategories(new QuernCategory(registry.getJeiHelpers().getGuiHelper(), QUERN_UID));
        registry.addRecipeCategories(new WeldingCategory(registry.getJeiHelpers().getGuiHelper(), WELDING_UID));
        registry.addRecipeCategories(new ScrapingCategory(registry.getJeiHelpers().getGuiHelper(), SCRAPING_UID));
        registry.addRecipeCategories(new UnmoldClayCategory(registry.getJeiHelpers().getGuiHelper(), CLAY_UNMOLD_UID));
        registry.addRecipeCategories(new UnmoldEarthenwareCategory(registry.getJeiHelpers().getGuiHelper(), EARTHENWARE_UNMOLD_UID));
        registry.addRecipeCategories(new UnmoldKaoliniteCategory(registry.getJeiHelpers().getGuiHelper(), KAOLINITE_UNMOLD_UID));
        registry.addRecipeCategories(new UnmoldStonewareCategory(registry.getJeiHelpers().getGuiHelper(), STONEWARE_UNMOLD_UID));
        registry.addRecipeCategories(new DryingRecipeCategory(registry.getJeiHelpers().getGuiHelper(), DRY_UID));
        registry.addRecipeCategories(new StickBundleRecipeCategory(registry.getJeiHelpers().getGuiHelper(), STICK_BUNDLE_UID));
    }

    @Override
    public void register(IModRegistry registry)
    {
        REGISTRY = registry;

        // Recipe Catalysts
        {
            registry.addRecipeCatalyst(new ItemStack(BlocksTFC.QUERN), QUERN_UID);
            registry.addRecipeCatalyst(new ItemStack(BlocksTFC.FIREPIT), HEAT_UID);
            registry.addRecipeCatalyst(new ItemStack(BlocksTFC.CHARCOAL_FORGE), HEAT_UID);
            for (Tree tree : TFCRegistries.TREES.getValuesCollection()) {
                registry.addRecipeCatalyst(new ItemStack(BlockLoom.get(tree)), LOOM_UID);
            }
            registry.addRecipeCatalyst(new ItemStack(BlocksTFC.CRUCIBLE), ALLOY_UID);
            registry.addRecipeCatalyst(new ItemStack(ItemsTFC.FIRED_VESSEL), ALLOY_UID);
            for (ItemStack stack : OreDictionary.getOres("clay")) {
                registry.addRecipeCatalyst(stack, KNAP_CLAY_UID);
            }
            for (ItemStack stack : OreDictionary.getOres("fireClay")) {
                registry.addRecipeCatalyst(stack, KNAP_FIRECLAY_UID);
            }
            for (ItemStack stack : OreDictionary.getOres("leather")) {
                registry.addRecipeCatalyst(stack, KNAP_LEATHER_UID);
            }
            for (Rock rock : TFCRegistries.ROCKS.getValuesCollection()) {
                registry.addRecipeCatalyst(new ItemStack(ItemRock.get(rock)), KNAP_STONE_UID);
            }
            for (ItemStack stack : OreDictionary.getOres("barrel")) {
                registry.addRecipeCatalyst(stack, BARREL_UID);
            }
            for (ItemStack stack : OreDictionary.getOres("workbench")) {
                registry.addRecipeCatalyst(stack, CRAFTING_UID);
                registry.addRecipeCatalyst(stack, CLAY_UNMOLD_UID);
                registry.addRecipeCatalyst(stack, EARTHENWARE_UNMOLD_UID);
                registry.addRecipeCatalyst(stack, KAOLINITE_UNMOLD_UID);
                registry.addRecipeCatalyst(stack, STONEWARE_UNMOLD_UID);
            }
            registry.addRecipeCatalyst(new ItemStack(BlocksTFC.BLOOMERY), BLOOMERY_UID);
            registry.addRecipeCatalyst(new ItemStack(BlocksTFC.BLAST_FURNACE), BLAST_FURNACE_UID);
            registry.addRecipeCatalyst(new ItemStack(BlocksTFC.CRUCIBLE), METAL_HEAT_UID);
            registry.addRecipeCatalyst(new ItemStack(ItemsTFC.FIRED_VESSEL), METAL_HEAT_UID);
            for (Material material : GregTechAPI.MATERIAL_REGISTRY) {
                if (material.hasFlag(TFCMaterialFlags.GENERATE_ANVIL)) {
                    registry.addRecipeCatalyst(new ItemStack(ItemAnvil.get(material)), ANVIL_UID);
                    registry.addRecipeCatalyst(new ItemStack(ItemAnvil.get(material)), WELDING_UID);
                }
            }
            registry.addRecipeCatalyst(ToolItems.KNIFE.get(Materials.Neutronium), SCRAPING_UID);
            registry.addRecipeCatalyst(new ItemStack(BlocksTFC.DRYER), DRY_UID);
            registry.addRecipeCatalyst(new ItemStack(BlocksTFC.STICK_BUNDLE), STICK_BUNDLE_UID);
        }
        // Wrappers
        {
            // Wraps all quern recipes
            List<SimpleRecipeWrapper> quernList = TFCRegistries.QUERN.getValuesCollection()
                    .stream()
                    .map(SimpleRecipeWrapper::new)
                    .collect(Collectors.toList());
            registry.addRecipes(quernList, QUERN_UID); // Register recipes to quern category

            // Wraps all heating recipes, if they return ingredient(1 or more) -> itemstacks(1 or more)
            List<HeatRecipeWrapper> heatList = TFCRegistries.HEAT.getValuesCollection()
                    .stream()
                    .filter(r -> r.getOutputs().size() > 0 && r.getIngredients().size() > 0)
                    .map(HeatRecipeWrapper::new)
                    .collect(Collectors.toList());
            registry.addRecipes(heatList, HEAT_UID);


            // Wraps all anvil recipes
            List<AnvilRecipeWrapper> anvilList = TFCRegistries.ANVIL.getValuesCollection()
                    .stream()
                    .map(AnvilRecipeWrapper::new)
                    .collect(Collectors.toList());
            registry.addRecipes(anvilList, ANVIL_UID);

            // Wraps all welding recipes
            List<WeldingRecipeWrapper> weldList = TFCRegistries.WELDING.getValuesCollection()
                    .stream()
                    .map(WeldingRecipeWrapper::new)
                    .collect(Collectors.toList());
            registry.addRecipes(weldList, WELDING_UID);

            // Wraps all loom recipes
            List<SimpleRecipeWrapper> loomRecipes = TFCRegistries.LOOM.getValuesCollection()
                    .stream()
                    .map(SimpleRecipeWrapper::new)
                    .collect(Collectors.toList());
            registry.addRecipes(loomRecipes, LOOM_UID);

            // Wrap all alloy recipes
            List<AlloyRecipeWrapper> alloyRecipes = TFCRegistries.ALLOYS.getValuesCollection().stream()
                    .map(AlloyRecipeWrapper::new)
                    .collect(Collectors.toList());
            registry.addRecipes(alloyRecipes, ALLOY_UID);

            // Clay Knapping
            List<KnappingRecipeWrapper> clayknapRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                    .filter(recipe -> recipe.getType() == KnappingType.CLAY)
                    .map(recipe -> new KnappingRecipeWrapper(recipe, registry.getJeiHelpers().getGuiHelper()))
                    .collect(Collectors.toList());
            registry.addRecipes(clayknapRecipes, KNAP_CLAY_UID);

            // Knapping Pineapple Leather
            List<KnappingRecipeWrapper> leatherPineappleRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                    .filter(recipe -> recipe.getType() == KnappingType.PINEAPPLE_LEATHER)
                    .map(recipe -> new KnappingRecipeWrapper(recipe, registry.getJeiHelpers().getGuiHelper()))
                    .collect(Collectors.toList());
            registry.addRecipes(leatherPineappleRecipes, KNAP_PINEAPPLE_LEATHER_UID);
            NonNullList<ItemStack> leatherPineapple = OreDictionary.getOres("leatherPineapple");
            for (ItemStack itemStack : leatherPineapple) {
                registry.addRecipeCatalyst(itemStack, KNAP_PINEAPPLE_LEATHER_UID);
            }

            // Knapping Burlap Cloth
            List<KnappingRecipeWrapper> clothBurlapRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                    .filter(recipe -> recipe.getType() == KnappingType.BURLAP_CLOTH)
                    .map(recipe -> new KnappingRecipeWrapper(recipe, registry.getJeiHelpers().getGuiHelper()))
                    .collect(Collectors.toList());
            registry.addRecipes(clothBurlapRecipes, KNAP_BURLAP_CLOTH_UID);
            NonNullList<ItemStack> clothBurlap = OreDictionary.getOres("clothBurlap");
            for (ItemStack itemStack : clothBurlap) {
                registry.addRecipeCatalyst(itemStack, KNAP_BURLAP_CLOTH_UID);
            }

            // Knapping Wool Cloth
            List<KnappingRecipeWrapper> clothWoolRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                    .filter(recipe -> recipe.getType() == KnappingType.WOOL_CLOTH)
                    .map(recipe -> new KnappingRecipeWrapper(recipe, registry.getJeiHelpers().getGuiHelper()))
                    .collect(Collectors.toList());
            registry.addRecipes(clothWoolRecipes, KNAP_WOOL_CLOTH_UID);
            NonNullList<ItemStack> clothWool = OreDictionary.getOres("clothWool");
            for (ItemStack itemStack : clothWool) {
                registry.addRecipeCatalyst(itemStack, KNAP_WOOL_CLOTH_UID);
            }

            // Knapping Silk Cloth
            List<KnappingRecipeWrapper> clothSilkRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                    .filter(recipe -> recipe.getType() == KnappingType.SILK_CLOTH)
                    .map(recipe -> new KnappingRecipeWrapper(recipe, registry.getJeiHelpers().getGuiHelper()))
                    .collect(Collectors.toList());
            registry.addRecipes(clothSilkRecipes, KNAP_SILK_CLOTH_UID);
            NonNullList<ItemStack> clothSilk = OreDictionary.getOres("clothSilk");
            for (ItemStack itemStack : clothSilk) {
                registry.addRecipeCatalyst(itemStack, KNAP_SILK_CLOTH_UID);
            }

            // Knapping Sisal Cloth
            List<KnappingRecipeWrapper> clothSisalRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                    .filter(recipe -> recipe.getType() == KnappingType.SISAL_CLOTH)
                    .map(recipe -> new KnappingRecipeWrapper(recipe, registry.getJeiHelpers().getGuiHelper()))
                    .collect(Collectors.toList());
            registry.addRecipes(clothSisalRecipes, KNAP_SISAL_CLOTH_UID);
            NonNullList<ItemStack> clothSisal = OreDictionary.getOres("clothSisal");
            for (ItemStack itemStack : clothSisal) {
                registry.addRecipeCatalyst(itemStack, KNAP_SISAL_CLOTH_UID);
            }

            // Knapping Cotton Cloth
            List<KnappingRecipeWrapper> clothCottonRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                    .filter(recipe -> recipe.getType() == KnappingType.COTTON_CLOTH)
                    .map(recipe -> new KnappingRecipeWrapper(recipe, registry.getJeiHelpers().getGuiHelper()))
                    .collect(Collectors.toList());
            registry.addRecipes(clothCottonRecipes, KNAP_COTTON_CLOTH_UID);
            NonNullList<ItemStack> clothCotton = OreDictionary.getOres("clothCotton");
            for (ItemStack itemStack : clothCotton) {
                registry.addRecipeCatalyst(itemStack, KNAP_COTTON_CLOTH_UID);
            }

            // Knapping Linen Cloth
            List<KnappingRecipeWrapper> clothLinenRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                    .filter(recipe -> recipe.getType() == KnappingType.LINEN_CLOTH)
                    .map(recipe -> new KnappingRecipeWrapper(recipe, registry.getJeiHelpers().getGuiHelper()))
                    .collect(Collectors.toList());
            registry.addRecipes(clothLinenRecipes, KNAP_LINEN_CLOTH_UID);
            NonNullList<ItemStack> oresLinen = OreDictionary.getOres("clothLinen");
            for (ItemStack itemStack : oresLinen) {
                registry.addRecipeCatalyst(itemStack, KNAP_LINEN_CLOTH_UID);
            }

            // Knapping Hemp Cloth
            List<KnappingRecipeWrapper> clothHempRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                    .filter(recipe -> recipe.getType() == KnappingType.HEMP_CLOTH)
                    .map(recipe -> new KnappingRecipeWrapper(recipe, registry.getJeiHelpers().getGuiHelper()))
                    .collect(Collectors.toList());
            registry.addRecipes(clothHempRecipes, KNAP_HEMP_CLOTH_UID);
            NonNullList<ItemStack> oresHemp = OreDictionary.getOres("clothHemp");
            for (ItemStack itemStack : oresHemp) {
                registry.addRecipeCatalyst(itemStack, KNAP_HEMP_CLOTH_UID);
            }

            // Knapping Yucca Canvas
            List<KnappingRecipeWrapper> canvasYuccaRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                    .filter(recipe -> recipe.getType() == KnappingType.YUCCA_CANVAS)
                    .map(recipe -> new KnappingRecipeWrapper(recipe, registry.getJeiHelpers().getGuiHelper()))
                    .collect(Collectors.toList());
            registry.addRecipes(canvasYuccaRecipes, KNAP_YUCCA_CANVAS_UID);
            NonNullList<ItemStack> oresYucca = OreDictionary.getOres("canvasYucca");
            for (ItemStack itemStack : oresYucca) {
                registry.addRecipeCatalyst(itemStack, KNAP_YUCCA_CANVAS_UID);
            }

            // Knapping Mud
            List<KnappingRecipeWrapper> mudKnapRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                    .filter(recipe -> recipe.getType() == KnappingType.MUD)
                    .flatMap(recipe -> TFCRegistries.ROCKS.getValuesCollection().stream().map(rock -> new KnappingRecipeWrapper.Mud(recipe, registry.getJeiHelpers().getGuiHelper(), rock)))
                    //.map(recipe -> new KnappingRecipeWrapperTFCF(recipe, registry.getJeiHelpers().getGuiHelper()))
                    .collect(Collectors.toList());
            registry.addRecipes(mudKnapRecipes, KNAP_MUD_UID);
            NonNullList<ItemStack> oresMud = OreDictionary.getOres("mud");
            for (Rock rock : TFCRegistries.ROCKS.getValuesCollection()) {
                registry.addRecipeCatalyst(new ItemStack(ItemMud.get(rock)), KNAP_MUD_UID);
            }

            // Knapping Earthenware Clay
            List<KnappingRecipeWrapper> clayEarthenwareKnapRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                    .filter(recipe -> recipe.getType() == KnappingType.EARTHENWARE_CLAY)
                    .map(recipe -> new KnappingRecipeWrapper(recipe, registry.getJeiHelpers().getGuiHelper()))
                    .collect(Collectors.toList());
            registry.addRecipes(clayEarthenwareKnapRecipes, KNAP_EARTHENWARE_CLAY_UID);
            NonNullList<ItemStack> oresEarthenware = OreDictionary.getOres("clayEarthenware");
            for (ItemStack itemStack : oresEarthenware) {
                registry.addRecipeCatalyst(itemStack, KNAP_EARTHENWARE_CLAY_UID);
            }

            // Knapping Kaolinite Clay
            List<KnappingRecipeWrapper> clayKaoliniteKnapRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                    .filter(recipe -> recipe.getType() == KnappingType.KAOLINITE_CLAY)
                    .map(recipe -> new KnappingRecipeWrapper(recipe, registry.getJeiHelpers().getGuiHelper()))
                    .collect(Collectors.toList());
            registry.addRecipes(clayKaoliniteKnapRecipes, KNAP_KAOLINITE_CLAY_UID);
            NonNullList<ItemStack> oresKaolinite = OreDictionary.getOres("clayKaolinite");
            for (ItemStack itemStack : oresKaolinite) {
                registry.addRecipeCatalyst(itemStack, KNAP_KAOLINITE_CLAY_UID);
            }

            // Knapping Stoneware Clay
            List<KnappingRecipeWrapper> clayStonewareKnapRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                    .filter(recipe -> recipe.getType() == KnappingType.STONEWARE_CLAY)
                    .map(recipe -> new KnappingRecipeWrapper(recipe, registry.getJeiHelpers().getGuiHelper()))
                    .collect(Collectors.toList());
            registry.addRecipes(clayStonewareKnapRecipes, KNAP_STONEWARE_CLAY_UID);
            NonNullList<ItemStack> oresStoneware = OreDictionary.getOres("clayStoneware");
            for (ItemStack itemStack : oresStoneware) {
                registry.addRecipeCatalyst(itemStack, KNAP_STONEWARE_CLAY_UID);
            }

            // Knapping Flint
            List<KnappingRecipeWrapper> flintKnapRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                    .filter(recipe -> recipe.getType() == KnappingType.FLINT)
                    .map(recipe -> new KnappingRecipeWrapper(recipe, registry.getJeiHelpers().getGuiHelper()))
                    .collect(Collectors.toList());
            registry.addRecipes(flintKnapRecipes, KNAP_FLINT_UID);
            NonNullList<ItemStack> oresFlint = OreDictionary.getOres("flint");
            for (ItemStack itemStack : oresFlint) {
                registry.addRecipeCatalyst(itemStack, KNAP_FLINT_UID);
            }

            // Fire Clay Knapping
            List<KnappingRecipeWrapper> fireclayknapRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                    .filter(recipe -> recipe.getType() == KnappingType.FIRE_CLAY)
                    .map(recipe -> new KnappingRecipeWrapper(recipe, registry.getJeiHelpers().getGuiHelper()))
                    .collect(Collectors.toList());

            registry.addRecipes(fireclayknapRecipes, KNAP_FIRECLAY_UID);

            // Leather Knapping
            List<KnappingRecipeWrapper> leatherknapRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                    .filter(recipe -> recipe.getType() == KnappingType.LEATHER)
                    .map(recipe -> new KnappingRecipeWrapper(recipe, registry.getJeiHelpers().getGuiHelper()))
                    .collect(Collectors.toList());
            registry.addRecipes(leatherknapRecipes, KNAP_LEATHER_UID);

            // Leather Knapping Recipes
            List<KnappingRecipeWrapper> stoneknapRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                    .filter(recipe -> recipe.getType() == KnappingType.STONE)
                    .flatMap(recipe -> TFCRegistries.ROCKS.getValuesCollection().stream().map(rock -> new KnappingRecipeWrapper.Stone(recipe, registry.getJeiHelpers().getGuiHelper(), rock)))
                    .collect(Collectors.toList());
            registry.addRecipes(stoneknapRecipes, KNAP_STONE_UID);

            // Wraps all barrel recipes
            List<BarrelRecipeWrapper> barrelRecipes = TFCRegistries.BARREL.getValuesCollection()
                    .stream().filter(recipe -> recipe instanceof BarrelRecipeFoodTraits || recipe instanceof BarrelRecipeFoodPreservation || recipe.getOutputStack() != ItemStack.EMPTY || recipe.getOutputFluid() != null)
                    .map(BarrelRecipeWrapper::new)
                    .collect(Collectors.toList());
            registry.addRecipes(barrelRecipes, BARREL_UID);

            // Wraps all bloomery recipes
            List<BloomeryRecipeWrapper> bloomeryList = TFCRegistries.BLOOMERY.getValuesCollection()
                    .stream()
                    .map(BloomeryRecipeWrapper::new)
                    .collect(Collectors.toList());
            registry.addRecipes(bloomeryList, BLOOMERY_UID);


            // Wraps all blast furnace recipes
            List<BlastFurnaceRecipeWrapper> blastList = TFCRegistries.BLAST_FURNACE.getValuesCollection()
                    .stream()
                    .map(BlastFurnaceRecipeWrapper::new)
                    .collect(Collectors.toList());
            registry.addRecipes(blastList, BLAST_FURNACE_UID);


            // Wraps all metal melting recipes
            List<MetalHeatingRecipeWrapper> heatMetalList = new ArrayList<>();
            getAllIngredients().forEach(stack -> {
                HeatRecipeMetalMelting recipe = (HeatRecipeMetalMelting) TFCRegistries.HEAT.getValuesCollection()
                        .stream().filter(x -> x instanceof HeatRecipeMetalMelting)
                        .filter(x -> x.isValidInput(stack, 6))
                        .findFirst().orElse(null);
                if (recipe != null) {
                    FluidStack fluidStack = recipe.getOutputFluid(stack);
                    // Don't add not meltable (ie: iron ore)
                    if (fluidStack != null && MetaFluids.getMaterialFromFluid(fluidStack.getFluid()) == recipe.getMetal()) {
                        MetalHeatingRecipeWrapper wrapper = new MetalHeatingRecipeWrapper(stack, recipe.getMetal(), fluidStack.amount, recipe.getTransformTemp());
                        heatMetalList.add(wrapper);
                    }
                }
            });
            registry.addRecipes(heatMetalList, METAL_HEAT_UID);

            // Wraps all dry recipes
            List<SimpleRecipeWrapper> dryList = TFCRegistries.DRYING.getValuesCollection()
                    .stream()
                    .map(DryingRecipeWrapper::new)
                    .collect(Collectors.toList());
            registry.addRecipes(dryList, DRY_UID);

            // Wraps all bundle recipes
            List<SimpleRecipeWrapper> stickbundleList = TFCRegistries.STICK_BUNDLE.getValuesCollection()
                    .stream()
                    .map(StickBundleRecipeWrapper::new)
                    .collect(Collectors.toList());
            registry.addRecipes(stickbundleList, STICK_BUNDLE_UID);

            // Wraps all chisel recipes
            List<ChiselRecipeWrapper> chiselList = TFCRegistries.CHISEL.getValuesCollection()
                    .stream()
                    .map(ChiselRecipeWrapper::new)
                    .collect(Collectors.toList());
            registry.addRecipes(chiselList, CHISEL_UID);

            List<UnmoldRecipeWrapperClay> unmoldListClay = new ArrayList<>();
            List<UnmoldRecipeWrapperEarthenware> unmoldListEarthenware = new ArrayList<>();
            List<UnmoldRecipeWrapperKaolinite> unmoldListKaolinite = new ArrayList<>();
            List<UnmoldRecipeWrapperStoneware> unmoldListStoneware = new ArrayList<>();

            for (Material material : GregTechAPI.MATERIAL_REGISTRY) {
                for (TFCOrePrefixExtended extendedOrePrefix : TFGUtils.TFC_OREPREFIX_REGISTRY) {
                    if (material.hasFlag(TFCMaterialFlags.TFC_MATERIAL) && extendedOrePrefix.isHasMold() && material != TFCMaterials.Unknown) {
                        if (material.hasProperty(PropertyKey.TOOL)) {
                            unmoldListClay.add(new UnmoldRecipeWrapperClay(material, extendedOrePrefix.getOrePrefix()));
                            unmoldListEarthenware.add(new UnmoldRecipeWrapperEarthenware(material, extendedOrePrefix.getOrePrefix()));
                            unmoldListKaolinite.add(new UnmoldRecipeWrapperKaolinite(material, extendedOrePrefix.getOrePrefix()));
                            unmoldListStoneware.add(new UnmoldRecipeWrapperStoneware(material, extendedOrePrefix.getOrePrefix()));
                        } else if (extendedOrePrefix.getOrePrefix() == OrePrefix.ingot) {
                            unmoldListClay.add(new UnmoldRecipeWrapperClay(material, extendedOrePrefix.getOrePrefix()));
                            unmoldListEarthenware.add(new UnmoldRecipeWrapperEarthenware(material, extendedOrePrefix.getOrePrefix()));
                            unmoldListKaolinite.add(new UnmoldRecipeWrapperKaolinite(material, extendedOrePrefix.getOrePrefix()));
                            unmoldListStoneware.add(new UnmoldRecipeWrapperStoneware(material, extendedOrePrefix.getOrePrefix()));
                        }
                    }
                }
            }
            registry.addRecipes(unmoldListClay, CLAY_UNMOLD_UID);
            registry.addRecipes(unmoldListEarthenware, EARTHENWARE_UNMOLD_UID);
            registry.addRecipes(unmoldListKaolinite, KAOLINITE_UNMOLD_UID);
            registry.addRecipes(unmoldListStoneware, STONEWARE_UNMOLD_UID);
        }

        // Click areas
        registry.addRecipeClickArea(GuiKnapping.class, 97, 44, 22, 15, KNAP_CLAY_UID, KNAP_FIRECLAY_UID, KNAP_LEATHER_UID, KNAP_MUD_UID, KNAP_EARTHENWARE_CLAY_UID, KNAP_KAOLINITE_CLAY_UID, KNAP_STONEWARE_CLAY_UID, KNAP_FLINT_UID);
        registry.addRecipeClickArea(GuiAnvilTFC.class, 26, 24, 9, 14, ANVIL_UID, WELDING_UID);
        registry.addRecipeClickArea(GuiBarrel.class, 92, 21, 9, 14, BARREL_UID);
        registry.addRecipeClickArea(GuiCrucible.class, 139, 100, 10, 15, ALLOY_UID);
        registry.addRecipeClickArea(GuiCrucible.class, 82, 100, 10, 15, METAL_HEAT_UID);
        registry.addRecipeClickArea(GuiFirePit.class, 79, 37, 18, 10, HEAT_UID);

        // Fix inventory tab overlap see https://github.com/TerraFirmaCraft/TerraFirmaCraft/issues/646
        registry.addAdvancedGuiHandlers(new TFCInventoryGuiHandler<>(GuiInventory.class));
        registry.addAdvancedGuiHandlers(new TFCInventoryGuiHandler<>(GuiCalendar.class));
        registry.addAdvancedGuiHandlers(new TFCInventoryGuiHandler<>(GuiNutrition.class));
        registry.addAdvancedGuiHandlers(new TFCInventoryGuiHandler<>(GuiSkills.class));

        // Add JEI descriptions for basic mechanics

        registry.addIngredientInfo(new ItemStack(BlocksTFC.PIT_KILN, 1), VanillaTypes.ITEM, new TextComponentTranslation("jei.description.tfc.pit_kiln").getFormattedText());
        registry.addIngredientInfo(new ItemStack(BlocksTFC.PLACED_ITEM, 1), VanillaTypes.ITEM, new TextComponentTranslation("jei.description.tfc.placed_item").getFormattedText());
        registry.addIngredientInfo(new ItemStack(Items.COAL, 1, 1), VanillaTypes.ITEM, new TextComponentTranslation("jei.description.tfc.charcoal_pit").getFormattedText());

        List<ScrapingWrapper> scrapingList = new ArrayList<>();
        for (ItemAnimalHide.HideSize size : ItemAnimalHide.HideSize.values()) {
            scrapingList.add(new ScrapingWrapper(ItemAnimalHide.get(HideType.SOAKED, size), ItemAnimalHide.get(HideType.SCRAPED, size)));
        }
        registry.addRecipes(scrapingList, SCRAPING_UID);

        // Custom handlers
        registry.handleRecipes(SaltingRecipe.class, SaltingRecipeWrapper::new, VanillaRecipeCategoryUid.CRAFTING);

        // ContainerInventoryCrafting - Add ability to transfer recipe items
        IRecipeTransferRegistry transferRegistry = registry.getRecipeTransferRegistry();
        transferRegistry.addRecipeTransferHandler(ContainerInventoryCrafting.class, VanillaRecipeCategoryUid.CRAFTING, 1, 9, 10, 36);

        // Hide some stuff
        IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();

        blacklist.addIngredientToBlacklist(new ItemStack(Items.WOODEN_SWORD));
        blacklist.addIngredientToBlacklist(new ItemStack(Items.WOODEN_PICKAXE));
        blacklist.addIngredientToBlacklist(new ItemStack(Items.WOODEN_AXE));
        blacklist.addIngredientToBlacklist(new ItemStack(Items.WOODEN_SHOVEL));
        blacklist.addIngredientToBlacklist(new ItemStack(Items.WOODEN_HOE));

        blacklist.addIngredientToBlacklist(new ItemStack(Items.STONE_SWORD));
        blacklist.addIngredientToBlacklist(new ItemStack(Items.STONE_PICKAXE));
        blacklist.addIngredientToBlacklist(new ItemStack(Items.STONE_AXE));
        blacklist.addIngredientToBlacklist(new ItemStack(Items.STONE_SHOVEL));
        blacklist.addIngredientToBlacklist(new ItemStack(Items.STONE_HOE));

        blacklist.addIngredientToBlacklist(new ItemStack(Items.IRON_SWORD));
        blacklist.addIngredientToBlacklist(new ItemStack(Items.IRON_PICKAXE));
        blacklist.addIngredientToBlacklist(new ItemStack(Items.IRON_AXE));
        blacklist.addIngredientToBlacklist(new ItemStack(Items.IRON_SHOVEL));
        blacklist.addIngredientToBlacklist(new ItemStack(Items.IRON_HOE));

        blacklist.addIngredientToBlacklist(new ItemStack(Items.GOLDEN_SWORD));
        blacklist.addIngredientToBlacklist(new ItemStack(Items.GOLDEN_PICKAXE));
        blacklist.addIngredientToBlacklist(new ItemStack(Items.GOLDEN_AXE));
        blacklist.addIngredientToBlacklist(new ItemStack(Items.GOLDEN_SHOVEL));
        blacklist.addIngredientToBlacklist(new ItemStack(Items.GOLDEN_HOE));

        blacklist.addIngredientToBlacklist(new ItemStack(Items.GOLDEN_SWORD));
        blacklist.addIngredientToBlacklist(new ItemStack(Items.GOLDEN_PICKAXE));
        blacklist.addIngredientToBlacklist(new ItemStack(Items.GOLDEN_AXE));
        blacklist.addIngredientToBlacklist(new ItemStack(Items.GOLDEN_SHOVEL));
        blacklist.addIngredientToBlacklist(new ItemStack(Items.GOLDEN_HOE));

        blacklist.addIngredientToBlacklist(new ItemStack(Items.DIAMOND_SWORD));
        blacklist.addIngredientToBlacklist(new ItemStack(Items.DIAMOND_PICKAXE));
        blacklist.addIngredientToBlacklist(new ItemStack(Items.DIAMOND_AXE));
        blacklist.addIngredientToBlacklist(new ItemStack(Items.DIAMOND_SHOVEL));
        blacklist.addIngredientToBlacklist(new ItemStack(Items.DIAMOND_HOE));

        /*
        blacklist.addIngredientToBlacklist(OreDictUnifier.get(TFCOrePrefix.toolHeadSword, Materials.Stone));
        blacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.toolHeadPickaxe, Materials.Stone));
        blacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.toolHeadFile, Materials.Stone));
        blacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.toolHeadSaw, Materials.Stone));
        blacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.toolHeadDrill, Materials.Stone));
        blacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.toolHeadChainsaw, Materials.Stone));
        blacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.toolHeadWrench, Materials.Stone));
        blacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.toolHeadSense, Materials.Stone));
        blacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.toolHeadBuzzSaw, Materials.Stone));
        blacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.toolHeadScrewdriver, Materials.Stone));
        blacklist.addIngredientToBlacklist(OreDictUnifier.get(TFCOrePrefix.toolHeadChisel, Materials.Stone));
        blacklist.addIngredientToBlacklist(OreDictUnifier.get(TFCOrePrefix.toolHeadPropick, Materials.Stone));

        blacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.toolHeadSword, Materials.Flint));
        blacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.toolHeadPickaxe, Materials.Flint));
        blacklist.addIngredientToBlacklist(OreDictUnifier.get(OrePrefix.toolHeadSense, Materials.Flint));
        blacklist.addIngredientToBlacklist(OreDictUnifier.get(TFCOrePrefix.toolHeadPropick, Materials.Flint));
        blacklist.addIngredientToBlacklist(OreDictUnifier.get(TFCOrePrefix.toolHeadChisel, Materials.Flint));
        blacklist.addIngredientToBlacklist(OreDictUnifier.get(TFCOrePrefix.toolHeadJavelin, Materials.Flint));*/
    }
}
