package tfcflorae.compat.jei;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import net.dries007.tfc.api.recipes.knapping.KnappingType;
import net.dries007.tfc.client.gui.GuiKnapping;
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
import tfcflorae.api.registries.TFCFRegistries;
import tfcflorae.compat.jei.category.*;
import tfcflorae.compat.jei.wrappers.*;
import tfcflorae.objects.blocks.BlocksTFCF;
import tfcflorae.objects.items.rock.ItemMud;


@JEIPlugin
public final class TFCFJEIPlugin implements IModPlugin
{
    private static IModRegistry REGISTRY;

    public static final String DRY_UID = TFCFlorae.TFCFLORAE_MODID + ".drying";
    public static final String STICK_BUNDLE_UID = TFCFlorae.TFCFLORAE_MODID + ".stick_bundle";

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {

        registry.addRecipeCategories(new DryingRecipeCategory(registry.getJeiHelpers().getGuiHelper(), DRY_UID));
        registry.addRecipeCategories(new StickBundleRecipeCategory(registry.getJeiHelpers().getGuiHelper(), STICK_BUNDLE_UID));
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



        // Register metal related stuff (put everything here for performance + sorted registration)

        //ContainerInventoryCrafting - Add ability to transfer recipe items
        IRecipeTransferRegistry transferRegistry = registry.getRecipeTransferRegistry();
        transferRegistry.addRecipeTransferHandler(ContainerInventoryCrafting.class, VanillaRecipeCategoryUid.CRAFTING, 1, 9, 10, 36);
    }
}
