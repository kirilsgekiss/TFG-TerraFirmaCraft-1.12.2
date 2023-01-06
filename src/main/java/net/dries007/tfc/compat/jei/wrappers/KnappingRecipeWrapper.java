/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.compat.jei.wrappers;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.dries007.tfc.api.recipes.knapping.KnappingRecipe;
import net.dries007.tfc.api.recipes.knapping.KnappingType;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.objects.items.rock.ItemRock;
import net.dries007.tfc.util.Helpers;
import tfcflorae.TFCFlorae;
import tfcflorae.objects.items.rock.ItemMud;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

@ParametersAreNonnullByDefault
public class KnappingRecipeWrapper implements IRecipeWrapper
{
    private static final ResourceLocation CLAY_DISABLED_TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/knapping/clay_button_disabled.png");
    private static final ResourceLocation FIRE_CLAY_DISABLED_TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/knapping/clay_button_fire_disabled.png");
    private static final ResourceLocation CLAY_TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/knapping/clay_button.png");
    private static final ResourceLocation FIRE_CLAY_TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/knapping/clay_button_fire.png");
    private static final ResourceLocation LEATHER_TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/knapping/leather_button.png");
    private static final ResourceLocation PINEAPPLE_LEATHER_TEXTURE = new ResourceLocation(TFCFlorae.TFCFLORAE_MODID, "textures/gui/knapping/pineapple_leather_button.png");
    private static final ResourceLocation BURLAP_CLOTH_TEXTURE = new ResourceLocation(TFCFlorae.TFCFLORAE_MODID, "textures/blocks/devices/loom/product/burlap.png");
    private static final ResourceLocation WOOL_CLOTH_TEXTURE = new ResourceLocation(TFCFlorae.TFCFLORAE_MODID, "textures/blocks/devices/loom/product/wool.png");
    private static final ResourceLocation SILK_CLOTH_TEXTURE = new ResourceLocation(TFCFlorae.TFCFLORAE_MODID, "textures/blocks/devices/loom/product/silk.png");
    private static final ResourceLocation SISAL_CLOTH_TEXTURE = new ResourceLocation(TFCFlorae.TFCFLORAE_MODID, "textures/blocks/devices/loom/product/sisal.png");
    private static final ResourceLocation COTTON_CLOTH_TEXTURE = new ResourceLocation(TFCFlorae.TFCFLORAE_MODID, "textures/blocks/devices/loom/product/cotton.png");
    private static final ResourceLocation LINEN_CLOTH_TEXTURE = new ResourceLocation(TFCFlorae.TFCFLORAE_MODID, "textures/blocks/devices/loom/product/linen.png");
    private static final ResourceLocation HEMP_CLOTH_TEXTURE = new ResourceLocation(TFCFlorae.TFCFLORAE_MODID, "textures/blocks/devices/loom/product/hemp.png");
    private static final ResourceLocation YUCCA_CANVAS_TEXTURE = new ResourceLocation(TFCFlorae.TFCFLORAE_MODID, "textures/blocks/devices/loom/product/yucca.png");
    private static final ResourceLocation MUD_TEXTURE = new ResourceLocation(TFCFlorae.TFCFLORAE_MODID,"textures/gui/knapping/mud_button.png");
    private static final ResourceLocation MUD_DISABLED_TEXTURE = new ResourceLocation(TFCFlorae.TFCFLORAE_MODID, "textures/gui/knapping/mud_button_disabled.png");
    private static final ResourceLocation EARTHENWARE_CLAY_TEXTURE = new ResourceLocation(TFCFlorae.TFCFLORAE_MODID, "textures/gui/knapping/earthenware_clay_button.png");
    private static final ResourceLocation EARTHENWARE_CLAY_DISABLED_TEXTURE = new ResourceLocation(TFCFlorae.TFCFLORAE_MODID, "textures/gui/knapping/earthenware_clay_button_disabled.png");
    private static final ResourceLocation KAOLINITE_CLAY_TEXTURE = new ResourceLocation(TFCFlorae.TFCFLORAE_MODID, "textures/gui/knapping/kaolinite_clay_button.png");
    private static final ResourceLocation KAOLINITE_CLAY_DISABLED_TEXTURE = new ResourceLocation(TFCFlorae.TFCFLORAE_MODID, "textures/gui/knapping/kaolinite_clay_button_disabled.png");
    private static final ResourceLocation STONEWARE_CLAY_TEXTURE = new ResourceLocation(TFCFlorae.TFCFLORAE_MODID, "textures/gui/knapping/stoneware_clay_button.png");
    private static final ResourceLocation STONEWARE_CLAY_DISABLED_TEXTURE = new ResourceLocation(TFCFlorae.TFCFLORAE_MODID, "textures/gui/knapping/stoneware_clay_button_disabled.png");
    private static final ResourceLocation FLINT_TEXTURE = new ResourceLocation(TFCFlorae.TFCFLORAE_MODID, "textures/gui/knapping/flint_button.png");

    @Nullable
    private static ResourceLocation getHighTexture(KnappingType type)
    {
        if (type == KnappingType.CLAY)
        {
            return CLAY_TEXTURE;
        }
        else if (type == KnappingType.FIRE_CLAY)
        {
            return FIRE_CLAY_TEXTURE;
        }
        else if (type == KnappingType.LEATHER)
        {
            return LEATHER_TEXTURE;
        }
        else if (type == KnappingType.PINEAPPLE_LEATHER)
        {
            return PINEAPPLE_LEATHER_TEXTURE;
        }
        else if (type == KnappingType.BURLAP_CLOTH)
        {
            return BURLAP_CLOTH_TEXTURE;
        }
        else if (type == KnappingType.WOOL_CLOTH)
        {
            return WOOL_CLOTH_TEXTURE;
        }
        else if (type == KnappingType.SILK_CLOTH)
        {
            return SILK_CLOTH_TEXTURE;
        }
        else if (type == KnappingType.SISAL_CLOTH)
        {
            return SISAL_CLOTH_TEXTURE;
        }
        else if (type == KnappingType.COTTON_CLOTH)
        {
            return COTTON_CLOTH_TEXTURE;
        }
        else if (type == KnappingType.LINEN_CLOTH)
        {
            return LINEN_CLOTH_TEXTURE;
        }
        else if (type == KnappingType.HEMP_CLOTH)
        {
            return HEMP_CLOTH_TEXTURE;
        }
        else if (type == KnappingType.YUCCA_CANVAS)
        {
            return YUCCA_CANVAS_TEXTURE;
        }
        else if(type == KnappingType.EARTHENWARE_CLAY)
        {
            return EARTHENWARE_CLAY_TEXTURE;
        }
        else if(type == KnappingType.KAOLINITE_CLAY)
        {
            return KAOLINITE_CLAY_TEXTURE;
        }
        else if(type == KnappingType.STONEWARE_CLAY)
        {
            return STONEWARE_CLAY_TEXTURE;
        }
        else if(type == KnappingType.FLINT)
        {
            return FLINT_TEXTURE;
        }
        return null;
    }

    @Nullable
    private static ResourceLocation getLowTexture(KnappingType type)
    {
        if (type == KnappingType.CLAY)
        {
            return CLAY_DISABLED_TEXTURE;
        }
        else if (type == KnappingType.FIRE_CLAY)
        {
            return FIRE_CLAY_DISABLED_TEXTURE;
        }
        else if(type == KnappingType.EARTHENWARE_CLAY)
        {
            return EARTHENWARE_CLAY_DISABLED_TEXTURE;
        }
        else if(type == KnappingType.KAOLINITE_CLAY)
        {
            return KAOLINITE_CLAY_DISABLED_TEXTURE;
        }
        else if(type == KnappingType.STONEWARE_CLAY)
        {
            return STONEWARE_CLAY_DISABLED_TEXTURE;
        }
        return null;
    }

    protected final KnappingRecipe recipe;
    private final IDrawable squareHigh, squareLow;

    public KnappingRecipeWrapper(KnappingRecipe recipe, IGuiHelper guiHelper)
    {
        this(recipe, guiHelper, getHighTexture(recipe.getType()), getLowTexture(recipe.getType()));
    }

    protected KnappingRecipeWrapper(KnappingRecipe recipe, IGuiHelper helper, @Nullable ResourceLocation highTexture, @Nullable ResourceLocation lowTexture)
    {
        this.recipe = recipe;

        this.squareHigh = highTexture == null ? null : helper.drawableBuilder(highTexture, 0, 0, 16, 16).setTextureSize(16, 16).build();
        this.squareLow = lowTexture == null ? null : helper.drawableBuilder(lowTexture, 0, 0, 16, 16).setTextureSize(16, 16).build();
    }

    @Override
    public void getIngredients(IIngredients ingredients)
    {
        ItemStack output = recipe.getOutput(ItemStack.EMPTY);
        ingredients.setOutput(VanillaTypes.ITEM, output);
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY)
    {
        for (int y = 0; y < recipe.getMatrix().getHeight(); y++)
        {
            for (int x = 0; x < recipe.getMatrix().getWidth(); x++)
            {
                if (recipe.getMatrix().get(x, y) && squareHigh != null)
                {
                    squareHigh.draw(minecraft, 1 + x * 16, 1 + y * 16);
                }
                else if (squareLow != null)
                {
                    squareLow.draw(minecraft, 1 + x * 16, 1 + y * 16);
                }
            }
        }
    }

    /**
     * Extra wrapper for stone, since it needs to check the rock for the input and texture
     */
    public static class Stone extends KnappingRecipeWrapper
    {
        private final Rock rock;

        public Stone(KnappingRecipe recipe, IGuiHelper helper, Rock rock)
        {
            super(recipe, helper, rock.getTexture(), null);

            this.rock = rock;
        }

        @Override
        public void getIngredients(IIngredients ingredients)
        {
            ingredients.setOutputLists(VanillaTypes.ITEM, Helpers.listOf(Helpers.listOf(recipe.getOutput(new ItemStack(ItemRock.get(rock))))));
        }
    }

    public static class Mud extends KnappingRecipeWrapper
    {
        private final Rock rock;

        public Mud(KnappingRecipe recipe, IGuiHelper helper, Rock rock)
        {
            super(recipe, helper, ItemMud.get(rock).getForegroundTexture(), ItemMud.get(rock).getBackgroundTexture());

            this.rock = rock;
        }

        @Override
        public void getIngredients(IIngredients ingredients)
        {
            ingredients.setOutputLists(VanillaTypes.ITEM, Helpers.listOf(Helpers.listOf(recipe.getOutput(new ItemStack(ItemMud.get(rock))))));
        }
    }
}
