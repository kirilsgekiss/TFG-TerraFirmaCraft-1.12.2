package tfcflorae.compat.jei;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.compat.gregtech.materials.TFCMaterialFlags;
import net.dries007.tfc.compat.gregtech.materials.TFCMaterials;
import net.dries007.tfc.compat.tfc.TFCOrePrefixExtended;
import net.dries007.tfc.compat.tfc.TFGUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;

import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.compat.jei.categories.*;
import net.dries007.tfc.compat.jei.wrappers.*;
import net.dries007.tfc.objects.container.ContainerInventoryCrafting;

import tfcflorae.TFCFlorae;
import tfcflorae.api.knapping.KnappingTypes;
import tfcflorae.api.registries.TFCFRegistries;
import tfcflorae.client.GuiKnappingTFCF;
import tfcflorae.compat.jei.category.*;
import tfcflorae.compat.jei.wrappers.*;
import tfcflorae.objects.blocks.BlocksTFCF;
import tfcflorae.objects.items.rock.ItemMud;


@JEIPlugin
public final class TFCFJEIPlugin implements IModPlugin
{
    private static IModRegistry REGISTRY;
    public static final String KNAP_PINEAPPLE_LEATHER_UID = TFCFlorae.MODID + ".knap.pineapple_leather";
    public static final String KNAP_BURLAP_CLOTH_UID = TFCFlorae.MODID + ".knap.burlap_cloth";
    public static final String KNAP_WOOL_CLOTH_UID = TFCFlorae.MODID + ".knap.wool_cloth";
    public static final String KNAP_SILK_CLOTH_UID = TFCFlorae.MODID + ".knap.silk_cloth";
    public static final String KNAP_SISAL_CLOTH_UID = TFCFlorae.MODID + ".knap.sisal_cloth";
    public static final String KNAP_COTTON_CLOTH_UID = TFCFlorae.MODID + ".knap.cotton_cloth";
    public static final String KNAP_LINEN_CLOTH_UID = TFCFlorae.MODID + ".knap.linen_cloth";
    public static final String KNAP_HEMP_CLOTH_UID = TFCFlorae.MODID + ".knap.hemp_cloth";
    public static final String KNAP_YUCCA_CANVAS_UID = TFCFlorae.MODID + ".knap.yucca_canvas";
    public static final String KNAP_MUD_UID = TFCFlorae.MODID + ".knap.mud";
    public static final String KNAP_EARTHENWARE_CLAY_UID = TFCFlorae.MODID + ".knap.earthenware_clay";
    public static final String KNAP_KAOLINITE_CLAY_UID = TFCFlorae.MODID + ".knap.kaolinite_clay";
    public static final String KNAP_STONEWARE_CLAY_UID = TFCFlorae.MODID + ".knap.stoneware_clay";
    public static final String KNAP_FLINT_UID = TFCFlorae.MODID + ".knap.flint";
    public static final String CASTING_UID = TFCFlorae.MODID + ".casting";
    public static final String DRY_UID = TFCFlorae.MODID + ".drying";
    public static final String STICK_BUNDLE_UID = TFCFlorae.MODID + ".stick_bundle";
    public static final String EARTHENWARE_UNMOLD_UID = TerraFirmaCraft.MOD_ID + ".earthenware.unmold";
    public static final String KAOLINITE_UNMOLD_UID = TerraFirmaCraft.MOD_ID + ".kaolinite.unmold";
    public static final String STONEWARE_UNMOLD_UID = TerraFirmaCraft.MOD_ID + ".stoneware.unmold";

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
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
        registry.addRecipeCategories(new CastingCategory(registry.getJeiHelpers().getGuiHelper(), CASTING_UID));
        registry.addRecipeCategories(new DryingRecipeCategory(registry.getJeiHelpers().getGuiHelper(), DRY_UID));
        registry.addRecipeCategories(new StickBundleRecipeCategory(registry.getJeiHelpers().getGuiHelper(), STICK_BUNDLE_UID));
        registry.addRecipeCategories(new UnmoldEarthenwareCategory(registry.getJeiHelpers().getGuiHelper(), EARTHENWARE_UNMOLD_UID));
        registry.addRecipeCategories(new UnmoldKaoliniteCategory(registry.getJeiHelpers().getGuiHelper(), KAOLINITE_UNMOLD_UID));
        registry.addRecipeCategories(new UnmoldStonewareCategory(registry.getJeiHelpers().getGuiHelper(), STONEWARE_UNMOLD_UID));
    }

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
    public void register(IModRegistry registry)
    {
        REGISTRY = registry;

        List<SimpleRecipeWrapper> dryList = TFCFRegistries.DRYING.getValuesCollection().stream().map(DryingRecipeWrapper::new).collect(Collectors.toList());
        registry.addRecipes(dryList, DRY_UID);
        registry.addRecipeCatalyst(new ItemStack(BlocksTFCF.DRYER), DRY_UID);

        List<SimpleRecipeWrapper> stickbundleList = TFCFRegistries.STICK_BUNDLE.getValuesCollection().stream().map(StickBundleRecipeWrapper::new).collect(Collectors.toList());
        registry.addRecipes(stickbundleList, STICK_BUNDLE_UID);
        registry.addRecipeCatalyst(new ItemStack(BlocksTFCF.STICK_BUNDLE), STICK_BUNDLE_UID);

        // Knapping Pineapple Leather
        List<KnappingRecipeWrapperTFCF> leatherPineappleRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                .filter(recipe -> recipe.getType() == KnappingTypes.PINEAPPLE_LEATHER)
                .map(recipe -> new KnappingRecipeWrapperTFCF(recipe, registry.getJeiHelpers().getGuiHelper()))
                .collect(Collectors.toList());
        registry.addRecipes(leatherPineappleRecipes, KNAP_PINEAPPLE_LEATHER_UID);
        NonNullList<ItemStack> leatherPineapple = OreDictionary.getOres("leatherPineapple");
        for(ItemStack itemStack : leatherPineapple)
        {
            registry.addRecipeCatalyst(itemStack, KNAP_PINEAPPLE_LEATHER_UID);
        }

        // Knapping Burlap Cloth
        List<KnappingRecipeWrapperTFCF> clothBurlapRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                .filter(recipe -> recipe.getType() == KnappingTypes.BURLAP_CLOTH)
                .map(recipe -> new KnappingRecipeWrapperTFCF(recipe, registry.getJeiHelpers().getGuiHelper()))
                .collect(Collectors.toList());
        registry.addRecipes(clothBurlapRecipes, KNAP_BURLAP_CLOTH_UID);
        NonNullList<ItemStack> clothBurlap = OreDictionary.getOres("clothBurlap");
        for(ItemStack itemStack : clothBurlap)
        {
            registry.addRecipeCatalyst(itemStack, KNAP_BURLAP_CLOTH_UID);
        }

        // Knapping Wool Cloth
        List<KnappingRecipeWrapperTFCF> clothWoolRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                .filter(recipe -> recipe.getType() == KnappingTypes.WOOL_CLOTH)
                .map(recipe -> new KnappingRecipeWrapperTFCF(recipe, registry.getJeiHelpers().getGuiHelper()))
                .collect(Collectors.toList());
        registry.addRecipes(clothWoolRecipes, KNAP_WOOL_CLOTH_UID);
        NonNullList<ItemStack> clothWool = OreDictionary.getOres("clothWool");
        for(ItemStack itemStack : clothWool)
        {
            registry.addRecipeCatalyst(itemStack, KNAP_WOOL_CLOTH_UID);
        }

        // Knapping Silk Cloth
        List<KnappingRecipeWrapperTFCF> clothSilkRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                .filter(recipe -> recipe.getType() == KnappingTypes.SILK_CLOTH)
                .map(recipe -> new KnappingRecipeWrapperTFCF(recipe, registry.getJeiHelpers().getGuiHelper()))
                .collect(Collectors.toList());
        registry.addRecipes(clothSilkRecipes, KNAP_SILK_CLOTH_UID);
        NonNullList<ItemStack> clothSilk = OreDictionary.getOres("clothSilk");
        for(ItemStack itemStack : clothSilk)
        {
            registry.addRecipeCatalyst(itemStack, KNAP_SILK_CLOTH_UID);
        }

        // Knapping Sisal Cloth
        List<KnappingRecipeWrapperTFCF> clothSisalRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                .filter(recipe -> recipe.getType() == KnappingTypes.SISAL_CLOTH)
                .map(recipe -> new KnappingRecipeWrapperTFCF(recipe, registry.getJeiHelpers().getGuiHelper()))
                .collect(Collectors.toList());
        registry.addRecipes(clothSisalRecipes, KNAP_SISAL_CLOTH_UID);
        NonNullList<ItemStack> clothSisal = OreDictionary.getOres("clothSisal");
        for(ItemStack itemStack : clothSisal)
        {
            registry.addRecipeCatalyst(itemStack, KNAP_SISAL_CLOTH_UID);
        }

        // Knapping Cotton Cloth
        List<KnappingRecipeWrapperTFCF> clothCottonRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                .filter(recipe -> recipe.getType() == KnappingTypes.COTTON_CLOTH)
                .map(recipe -> new KnappingRecipeWrapperTFCF(recipe, registry.getJeiHelpers().getGuiHelper()))
                .collect(Collectors.toList());
        registry.addRecipes(clothCottonRecipes, KNAP_COTTON_CLOTH_UID);
        NonNullList<ItemStack> clothCotton = OreDictionary.getOres("clothCotton");
        for(ItemStack itemStack : clothCotton)
        {
            registry.addRecipeCatalyst(itemStack, KNAP_COTTON_CLOTH_UID);
        }

        // Knapping Linen Cloth
        List<KnappingRecipeWrapperTFCF> clothLinenRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                .filter(recipe -> recipe.getType() == KnappingTypes.LINEN_CLOTH)
                .map(recipe -> new KnappingRecipeWrapperTFCF(recipe, registry.getJeiHelpers().getGuiHelper()))
                .collect(Collectors.toList());
        registry.addRecipes(clothLinenRecipes, KNAP_LINEN_CLOTH_UID);
        NonNullList<ItemStack> oresLinen = OreDictionary.getOres("clothLinen");
        for(ItemStack itemStack : oresLinen)
        {
            registry.addRecipeCatalyst(itemStack, KNAP_LINEN_CLOTH_UID);
        }

        // Knapping Hemp Cloth
        List<KnappingRecipeWrapperTFCF> clothHempRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                .filter(recipe -> recipe.getType() == KnappingTypes.HEMP_CLOTH)
                .map(recipe -> new KnappingRecipeWrapperTFCF(recipe, registry.getJeiHelpers().getGuiHelper()))
                .collect(Collectors.toList());
        registry.addRecipes(clothHempRecipes, KNAP_HEMP_CLOTH_UID);
        NonNullList<ItemStack> oresHemp = OreDictionary.getOres("clothHemp");
        for(ItemStack itemStack : oresHemp)
        {
            registry.addRecipeCatalyst(itemStack, KNAP_HEMP_CLOTH_UID);
        }

        // Knapping Yucca Canvas
        List<KnappingRecipeWrapperTFCF> canvasYuccaRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                .filter(recipe -> recipe.getType() == KnappingTypes.YUCCA_CANVAS)
                .map(recipe -> new KnappingRecipeWrapperTFCF(recipe, registry.getJeiHelpers().getGuiHelper()))
                .collect(Collectors.toList());
        registry.addRecipes(canvasYuccaRecipes, KNAP_YUCCA_CANVAS_UID);
        NonNullList<ItemStack> oresYucca = OreDictionary.getOres("canvasYucca");
        for(ItemStack itemStack : oresYucca)
        {
            registry.addRecipeCatalyst(itemStack, KNAP_YUCCA_CANVAS_UID);
        }

        // Knapping Mud
        List<KnappingRecipeWrapperTFCF> mudKnapRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                .filter(recipe -> recipe.getType() == KnappingTypes.MUD)
                .flatMap(recipe -> TFCRegistries.ROCKS.getValuesCollection().stream().map(rock -> new KnappingRecipeWrapperTFCF.Mud(recipe, registry.getJeiHelpers().getGuiHelper(), rock)))
                //.map(recipe -> new KnappingRecipeWrapperTFCF(recipe, registry.getJeiHelpers().getGuiHelper()))
                .collect(Collectors.toList());
        registry.addRecipes(mudKnapRecipes, KNAP_MUD_UID);
        NonNullList<ItemStack> oresMud = OreDictionary.getOres("mud");
        for(Rock rock : TFCRegistries.ROCKS.getValuesCollection())
        {
            registry.addRecipeCatalyst(new ItemStack(ItemMud.get(rock)), KNAP_MUD_UID);
        }

        // Knapping Earthenware Clay
        List<KnappingRecipeWrapperTFCF> clayEarthenwareKnapRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                .filter(recipe -> recipe.getType() == KnappingTypes.EARTHENWARE_CLAY)
                .map(recipe -> new KnappingRecipeWrapperTFCF(recipe, registry.getJeiHelpers().getGuiHelper()))
                .collect(Collectors.toList());
        registry.addRecipes(clayEarthenwareKnapRecipes, KNAP_EARTHENWARE_CLAY_UID);
        NonNullList<ItemStack> oresEarthenware = OreDictionary.getOres("clayEarthenware");
        for(ItemStack itemStack : oresEarthenware)
        {
            registry.addRecipeCatalyst(itemStack, KNAP_EARTHENWARE_CLAY_UID);
        }

        // Knapping Kaolinite Clay
        List<KnappingRecipeWrapperTFCF> clayKaoliniteKnapRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                .filter(recipe -> recipe.getType() == KnappingTypes.KAOLINITE_CLAY)
                .map(recipe -> new KnappingRecipeWrapperTFCF(recipe, registry.getJeiHelpers().getGuiHelper()))
                .collect(Collectors.toList());
        registry.addRecipes(clayKaoliniteKnapRecipes, KNAP_KAOLINITE_CLAY_UID);
        NonNullList<ItemStack> oresKaolinite = OreDictionary.getOres("clayKaolinite");
        for(ItemStack itemStack : oresKaolinite)
        {
            registry.addRecipeCatalyst(itemStack, KNAP_KAOLINITE_CLAY_UID);
        }

        // Knapping Stoneware Clay
        List<KnappingRecipeWrapperTFCF> clayStonewareKnapRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                .filter(recipe -> recipe.getType() == KnappingTypes.STONEWARE_CLAY)
                .map(recipe -> new KnappingRecipeWrapperTFCF(recipe, registry.getJeiHelpers().getGuiHelper()))
                .collect(Collectors.toList());
        registry.addRecipes(clayStonewareKnapRecipes, KNAP_STONEWARE_CLAY_UID);
        NonNullList<ItemStack> oresStoneware = OreDictionary.getOres("clayStoneware");
        for(ItemStack itemStack : oresStoneware)
        {
            registry.addRecipeCatalyst(itemStack, KNAP_STONEWARE_CLAY_UID);
        }

        // Knapping Flint
        List<KnappingRecipeWrapperTFCF> flintKnapRecipes = TFCRegistries.KNAPPING.getValuesCollection().stream()
                .filter(recipe -> recipe.getType() == KnappingTypes.FLINT)
                .map(recipe -> new KnappingRecipeWrapperTFCF(recipe, registry.getJeiHelpers().getGuiHelper()))
                .collect(Collectors.toList());
        registry.addRecipes(flintKnapRecipes, KNAP_FLINT_UID);
        NonNullList<ItemStack> oresFlint = OreDictionary.getOres("flint");
        for(ItemStack itemStack : oresFlint)
        {
            registry.addRecipeCatalyst(itemStack, KNAP_FLINT_UID);
        }
        registry.addRecipeClickArea(GuiKnappingTFCF.class, 97, 44, 22, 15, KNAP_MUD_UID, KNAP_EARTHENWARE_CLAY_UID, KNAP_KAOLINITE_CLAY_UID, KNAP_STONEWARE_CLAY_UID, KNAP_FLINT_UID);

        // Register metal related stuff (put everything here for performance + sorted registration)
        List<UnmoldRecipeWrapperEarthenwareTFCF> unmoldListEarthenware = new ArrayList<>();
        List<CastingRecipeWrapperEarthenwareTFCF> castingListEarthenware = new ArrayList<>();
        List<UnmoldRecipeWrapperKaoliniteTFCF> unmoldListKaolinite = new ArrayList<>();
        List<CastingRecipeWrapperKaoliniteTFCF> castingListKaolinite = new ArrayList<>();
        List<UnmoldRecipeWrapperStonewareTFCF> unmoldListStoneware = new ArrayList<>();
        List<CastingRecipeWrapperStonewareTFCF> castingListStoneware = new ArrayList<>();

        for (Material material : GregTechAPI.MATERIAL_REGISTRY) {
            for (TFCOrePrefixExtended extendedOrePrefix : TFGUtils.TFC_OREPREFIX_REGISTRY) {
                if (material.hasFlag(TFCMaterialFlags.TFC_MATERIAL) && extendedOrePrefix.isHasMold() && material != TFCMaterials.Unknown) {
                    if (material.hasProperty(PropertyKey.TOOL)) {
                        unmoldListEarthenware.add(new UnmoldRecipeWrapperEarthenwareTFCF(material, extendedOrePrefix.getOrePrefix()));
                        unmoldListKaolinite.add(new UnmoldRecipeWrapperKaoliniteTFCF(material, extendedOrePrefix.getOrePrefix()));
                        unmoldListStoneware.add(new UnmoldRecipeWrapperStonewareTFCF(material, extendedOrePrefix.getOrePrefix()));
                    }
                    else if (extendedOrePrefix.getOrePrefix() == OrePrefix.ingot) {
                        unmoldListEarthenware.add(new UnmoldRecipeWrapperEarthenwareTFCF(material, extendedOrePrefix.getOrePrefix()));
                        unmoldListKaolinite.add(new UnmoldRecipeWrapperKaoliniteTFCF(material, extendedOrePrefix.getOrePrefix()));
                        unmoldListStoneware.add(new UnmoldRecipeWrapperStonewareTFCF(material, extendedOrePrefix.getOrePrefix()));
                    }
                    castingListEarthenware.add(new CastingRecipeWrapperEarthenwareTFCF(material, extendedOrePrefix.getOrePrefix()));
                    castingListKaolinite.add(new CastingRecipeWrapperKaoliniteTFCF(material, extendedOrePrefix.getOrePrefix()));
                    castingListStoneware.add(new CastingRecipeWrapperStonewareTFCF(material, extendedOrePrefix.getOrePrefix()));
                }
            }
        }

        for (ItemStack stack : OreDictionary.getOres("workbench"))
        {
            registry.addRecipeCatalyst(stack, EARTHENWARE_UNMOLD_UID);
            registry.addRecipeCatalyst(stack, KAOLINITE_UNMOLD_UID);
            registry.addRecipeCatalyst(stack, STONEWARE_UNMOLD_UID);
        }

        registry.addRecipes(unmoldListEarthenware, EARTHENWARE_UNMOLD_UID);
        registry.addRecipes(castingListEarthenware, CASTING_UID);
        registry.addRecipes(unmoldListKaolinite, KAOLINITE_UNMOLD_UID);
        registry.addRecipes(castingListKaolinite, CASTING_UID);
        registry.addRecipes(unmoldListStoneware, STONEWARE_UNMOLD_UID);
        registry.addRecipes(castingListStoneware, CASTING_UID);

        //ContainerInventoryCrafting - Add ability to transfer recipe items
        IRecipeTransferRegistry transferRegistry = registry.getRecipeTransferRegistry();
        transferRegistry.addRecipeTransferHandler(ContainerInventoryCrafting.class, VanillaRecipeCategoryUid.CRAFTING, 1, 9, 10, 36);
    }
}
