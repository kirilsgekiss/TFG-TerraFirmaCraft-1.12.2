/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.api.recipes.quern;

import net.dries007.tfc.api.capability.food.CapabilityFood;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.compat.jei.IJEISimpleRecipe;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class QuernRecipe extends IForgeRegistryEntry.Impl<QuernRecipe> implements IJEISimpleRecipe {
    @Nullable
    public static QuernRecipe get(ItemStack item) {
        return TFCRegistries.QUERN.getValuesCollection().stream().filter(x -> x.isValidInput(item)).findFirst().orElse(null);
    }

    protected IIngredient<ItemStack> inputItem;
    protected ItemStack outputItem;

    public QuernRecipe(IIngredient<ItemStack> input, ItemStack output) {
        this.inputItem = input;
        this.outputItem = output;

        if (inputItem == null || outputItem == null) {
            throw new IllegalArgumentException("Input and output are not allowed to be empty");
        }
    }

    @Nonnull
    public ItemStack getOutputItem(ItemStack stack) {
        return CapabilityFood.updateFoodFromPrevious(stack, outputItem.copy());
    }

    @Override
    public NonNullList<IIngredient<ItemStack>> getIngredients() {
        return NonNullList.withSize(1, inputItem);
    }

    @Override
    public NonNullList<ItemStack> getOutputs() {
        return NonNullList.withSize(1, outputItem);
    }

    private boolean isValidInput(ItemStack inputItem) {
        return this.inputItem.test(inputItem);
    }
}
