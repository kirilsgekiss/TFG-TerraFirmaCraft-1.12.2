package net.dries007.tfc.compat.jei.wrappers;

import net.dries007.tfc.objects.recipes.StickBundleRecipe;
import net.dries007.tfc.util.calendar.ICalendar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class StickBundleRecipeWrapper extends SimpleRecipeWrapper {
    private final StickBundleRecipe recipe;

    public StickBundleRecipeWrapper(StickBundleRecipe recipeWrapper) {
        super(recipeWrapper);
        this.recipe = recipeWrapper;
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        float x = 60f;
        float y = 4f;
        String text = (StickBundleRecipe.getDuration(recipe) / ICalendar.TICKS_IN_HOUR < 48) ?
                StickBundleRecipe.getDuration(recipe) / ICalendar.TICKS_IN_HOUR + " " + I18n.format("tfc.tooltip.hours") :
                StickBundleRecipe.getDuration(recipe) / ICalendar.TICKS_IN_DAY + " " + I18n.format("tfc.tooltip.days");

        //String text = StickBundleRecipe.getDuration(recipe) / ICalendar.TICKS_IN_HOUR + " " + I18n.format("tfcflorae.tooltip.hours");
        x = x - minecraft.fontRenderer.getStringWidth(text) / 2.0f;
        minecraft.fontRenderer.drawString(text, x, y, 0xFFFFFF, false);
    }
}
