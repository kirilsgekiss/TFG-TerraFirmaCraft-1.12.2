package net.dries007.tfc.api.recipes.tech;

import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.util.SimpleCraftMatrix;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class GlassWorkingRecipe extends IForgeRegistryEntry.Impl<GlassWorkingRecipe> {

    public static GlassWorkingRecipe get(SimpleCraftMatrix input)
    {
        return TFCRegistries.GLASS_WORKING.getValuesCollection().stream().filter(x -> x.matches(input)).findFirst().orElse(null);
    }

    private final SimpleCraftMatrix matrix;
    private final ItemStack output;

    public GlassWorkingRecipe(ItemStack output, String... pattern)
    {
        this.matrix = new SimpleCraftMatrix(false, pattern);
        this.output = output;
    }

    public SimpleCraftMatrix getMatrix()
    {
        return matrix;
    }

    public ItemStack getOutput()
    {
        return output.copy();
    }

    private boolean matches(SimpleCraftMatrix other)
    {
        return other.matches(this.matrix);
    }
}
