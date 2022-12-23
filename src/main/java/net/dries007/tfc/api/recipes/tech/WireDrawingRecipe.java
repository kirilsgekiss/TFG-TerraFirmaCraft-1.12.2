package net.dries007.tfc.api.recipes.tech;

import net.dries007.tfc.compat.jei.IJEISimpleRecipe;
import net.dries007.tfc.compat.tfc.TFGUtils;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class WireDrawingRecipe extends IForgeRegistryEntry.Impl<WireDrawingRecipe> implements IJEISimpleRecipe {

    private final IIngredient<ItemStack> input;
    private final ItemStack output;
    private final int wireColor;
    private final int minTier;

    public WireDrawingRecipe(ResourceLocation name, IIngredient<ItemStack> input, int minTier, ItemStack output, int wireColor)
    {
        this.input = input;
        this.minTier = minTier;
        this.output = output;
        this.wireColor = wireColor;
        setRegistryName(name);
    }

    public int getTier()
    {
        return minTier;
    }

    public int getWireColor()
    {
        return wireColor;
    }

    public boolean matches(ItemStack input)
    {
        return this.input.test(input);
    }

    public ItemStack getOutput()
    {
        return output.copy();
    }


    @Override
    public NonNullList<IIngredient<ItemStack>> getIngredients()
    {
        NonNullList<IIngredient<ItemStack>> list = NonNullList.create();
        list.add(input);
        if (TFGUtils.isAtMost(minTier, 3))
        {
            list.add(IIngredient.of(new ItemStack(TechItems.IRON_DRAW_PLATE))); // todo
        }
        else if (TFGUtils.isAtMost(minTier, 4))
        {
            list.add(IIngredient.of(new ItemStack(TechItems.STEEL_DRAW_PLATE))); // todo
        }
        else if (TFGUtils.isAtMost(minTier, 5))
        {
            list.add(IIngredient.of(new ItemStack(TechItems.BLACK_STEEL_DRAW_PLATE))); // todo
        }
        return list;
    }

    @Override
    public NonNullList<ItemStack> getOutputs()
    {
        return NonNullList.withSize(1, output);
    }
}
