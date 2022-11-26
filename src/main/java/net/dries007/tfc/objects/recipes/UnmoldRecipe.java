package net.dries007.tfc.objects.recipes;

import com.google.gson.JsonObject;
import gregtech.api.GregTechAPI;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.worldgen.config.OreConfigUtils;
import net.dries007.tfc.Constants;
import net.dries007.tfc.api.capability.IMaterialHandler;
import net.dries007.tfc.api.capability.heat.IItemHeat;
import net.dries007.tfc.client.TFCSounds;
import net.dries007.tfc.objects.items.ceramics.ItemMold;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import java.util.Objects;

import static net.dries007.tfc.api.capability.heat.CapabilityItemHeat.ITEM_HEAT_CAPABILITY;
import static net.minecraftforge.fluids.capability.CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;

@SuppressWarnings("unused")
@ParametersAreNonnullByDefault
public class UnmoldRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {

    private final ResourceLocation resourceLocation;
    private final NonNullList<Ingredient> ingredient;
    private final Material ingredientMaterial;
    private final int ingredientMaterialAmount;
    private final OrePrefix ingredientOrePrefix;
    private final float moldBreakChance;

    private UnmoldRecipe
            (
                    @Nullable ResourceLocation resourceLocation,
                    NonNullList<Ingredient> ingredient,
                    @Nonnull Material ingredientMaterial,
                    int ingredientMaterialAmount,
                    @Nonnull OrePrefix ingredientOrePrefix,
                    float moldBreakChance
            )
    {
        this.resourceLocation = resourceLocation;
        this.ingredient = ingredient;
        this.ingredientMaterial = ingredientMaterial;
        this.ingredientMaterialAmount = ingredientMaterialAmount;
        this.ingredientOrePrefix = ingredientOrePrefix;
        this.moldBreakChance = moldBreakChance;
    }

    public ItemStack getMoldResult(ItemStack moldIn)
    {
        if (Constants.RNG.nextFloat() <= moldBreakChance)
        {
            return new ItemStack(moldIn.getItem());
        }
        else
        {
            return ItemStack.EMPTY;
        }
    }

    public ItemStack getOutputItem(final IMaterialHandler moldHandler)
    {
        ItemStack output = OreDictUnifier.get(ingredientOrePrefix, moldHandler.getMaterial());
        System.out.println(output);

        IItemHeat heat = output.getCapability(ITEM_HEAT_CAPABILITY, null);
        if (heat != null)
        {
            heat.setTemperature(moldHandler.getTemperature());
        }
        return output;
    }

    @Override
    public boolean matches(@Nonnull InventoryCrafting inv, @Nonnull World world)
    {
        boolean foundMold = false;
        for (int slot = 0; slot < inv.getSizeInventory(); slot++)
        {
            ItemStack stack = inv.getStackInSlot(slot);
            if (!stack.isEmpty())
            {
                if (stack.getItem() instanceof ItemMold)
                {
                    ItemMold moldItem = ((ItemMold) stack.getItem());
                    IFluidHandler cap = stack.getCapability(FLUID_HANDLER_CAPABILITY, null);
                    if (cap instanceof IMaterialHandler)
                    {
                        IMaterialHandler moldHandler = (IMaterialHandler) cap;
                        if (!moldHandler.isMolten())
                        {
                            if (moldItem.getOrePrefix().equals(this.ingredientOrePrefix) && !foundMold)
                            {
                                foundMold = true;
                            }
                            else
                            {
                                return false;
                            }
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
        }
        return foundMold;
    }

    @Override
    @Nonnull
    public ItemStack getCraftingResult(InventoryCrafting inv)
    {
        ItemStack moldStack = null;
        for (int slot = 0; slot < inv.getSizeInventory(); slot++)
        {
            ItemStack stack = inv.getStackInSlot(slot);
            if (!stack.isEmpty())
            {
                if (stack.getItem() instanceof ItemMold)
                {
                    ItemMold tmp = ((ItemMold) stack.getItem());
                    if (tmp.getOrePrefix().equals(this.ingredientOrePrefix) && moldStack == null)
                    {
                        moldStack = stack;
                    }
                    else
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else
                {
                    return ItemStack.EMPTY;
                }
            }
        }
        if (moldStack != null)
        {
            IFluidHandler moldCap = moldStack.getCapability(FLUID_HANDLER_CAPABILITY, null);
            if (moldCap instanceof IMaterialHandler)
            {
                IMaterialHandler moldHandler = (IMaterialHandler) moldCap;
                if (!moldHandler.isMolten() && moldHandler.getAmount() == ingredientMaterialAmount)
                {
                    return getOutputItem(moldHandler);
                }
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    @Nonnull
    public NonNullList<ItemStack> getRemainingItems(final InventoryCrafting inv)
    {
        // Return empty molds
        for (int slot = 0; slot < inv.getSizeInventory(); slot++)
        {
            ItemStack stack = inv.getStackInSlot(slot);
            if (!stack.isEmpty())
            {
                if (stack.getItem() instanceof ItemMold)
                {
                    // No need to check for the mold, as it has already been checked earlier
                    EntityPlayer player = ForgeHooks.getCraftingPlayer();
                    if (player != null && !player.world.isRemote)
                    {
                        stack = getMoldResult(stack);
                        if (!stack.isEmpty())
                        {
                            // This can't use the remaining items, because vanilla doesn't sync them on crafting, thus it gives a desync error
                            // To fix: ContainerWorkbench#onCraftMatrixChanged needs to call Container#detectAndSendChanges
                            ItemHandlerHelper.giveItemToPlayer(player, stack);
                        }
                        else
                        {
                            player.world.playSound(null, player.getPosition(), TFCSounds.CERAMIC_BREAK, SoundCategory.PLAYERS, 1.0f, 1.0f);
                        }
                    }
                }
            }
        }
        return ForgeHooks.defaultRecipeGetRemainingItems(inv);
    }

    @Override
    public boolean canFit(int width, int height)
    {
        return true;
    }

    @Override
    @Nonnull
    public ItemStack getRecipeOutput()
    {
        return ItemStack.EMPTY;
    }

    @Override
    @Nonnull
    public NonNullList<Ingredient> getIngredients()
    {
        return ingredient;
    }

    @Override
    public boolean isDynamic()
    {
        return true;
    }

    @Override
    @Nonnull
    public String getGroup()
    {
        return resourceLocation == null ? "" : resourceLocation.toString();
    }

    @SuppressWarnings("unused")
    public static class Factory implements IRecipeFactory
    {
        @Override
        public IRecipe parse(final JsonContext context, final JsonObject json)
        {
            final String resourceLocation = JsonUtils.getString(json, "resourceLocation", "");
            final NonNullList<Ingredient> ingredient = RecipeUtils.parseShapeless(context, json);
            final OrePrefix ingredientOrePrefix = OrePrefix.getPrefix(JsonUtils.getString(json, "input_oreprefix"));
            final Material ingredientMaterial = GregTechAPI.MATERIAL_REGISTRY.getObject(JsonUtils.getString(json, "input_material"));
            final int materialAmount = JsonUtils.getInt(json, "input_material_amount");
            final float moldBreakChance = JsonUtils.getFloat(json, "chance");

            assert ingredientMaterial != null;
            return new UnmoldRecipe
                    (
                        resourceLocation.isEmpty() ? new ResourceLocation(ingredientOrePrefix.name) : new ResourceLocation(resourceLocation),
                        ingredient,
                        ingredientMaterial,
                        materialAmount,
                        ingredientOrePrefix,
                        moldBreakChance
                    );
        }
    }

}
