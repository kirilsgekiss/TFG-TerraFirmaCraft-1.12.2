/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.api.capability.metal;

import java.util.*;
import java.util.function.Supplier;
import javax.annotation.Nullable;

import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import net.dries007.tfc.TFGUtils;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.oredict.OreDictionary;

import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.capability.DumbStorage;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;

public final class CapabilityMetalItem
{
    public static final ResourceLocation KEY = new ResourceLocation(TerraFirmaCraft.MOD_ID, "metal_object");
    // Used inside CT, set custom IMetalItem for items outside TFC
    public static final Map<IIngredient<ItemStack>, Supplier<ICapabilityProvider>> CUSTOM_METAL_ITEMS = new HashMap<>();
    public static final Set<OrePrefix> ORE_DICT_MATERIAL_ITEMS = new LinkedHashSet<>();
    @CapabilityInject(IMetalItem.class)
    public static Capability<IMetalItem> METAL_OBJECT_CAPABILITY;

    // Register ore dict prefix values (any ingot has 144mb, etc)
    public static void preInit()
    {
        CapabilityManager.INSTANCE.register(IMetalItem.class, new DumbStorage<>(), MetalItemHandler::new);

        // ORE_DICT_METAL_ITEMS.put("ingotDouble", Metal.ItemType.DOUBLE_INGOT);
        ORE_DICT_MATERIAL_ITEMS.addAll(TFGUtils.ORE_PREFIX_TO_METAL_UNITS.keySet());
    }

    // Register metalItemHandler here for custom items
    public static void init()
    {
        CUSTOM_METAL_ITEMS.put(IIngredient.of(Blocks.IRON_BARS), () -> new MetalItemHandler(Materials.WroughtIron, 36, true));
    }

    /**
     * Gets the IMetalItem instance from an itemstack, either via capability or via interface
     *
     * @param stack The stack
     * @return The IMetalItem if it exists, or null if it doesn't
     */
    @Nullable
    public static IMetalItem getMetalItem(ItemStack stack)
    {
        if (!stack.isEmpty())
        {
            IMetalItem metal = stack.getCapability(METAL_OBJECT_CAPABILITY, null);
            if (metal != null)
            {
                return metal;
            }
            else if (stack.getItem() instanceof IMetalItem)
            {
                return (IMetalItem) stack.getItem();
            }
            else if (stack.getItem() instanceof ItemBlock && ((ItemBlock) stack.getItem()).getBlock() instanceof IMetalItem)
            {
                return (IMetalItem) ((ItemBlock) stack.getItem()).getBlock();
            }
        }
        return null;
    }

    @Nullable
    public static ICapabilityProvider getCustomMetalItem(ItemStack stack)
    {
        if (!stack.isEmpty())
        {
            Set<IIngredient<ItemStack>> itemItemSet = CUSTOM_METAL_ITEMS.keySet();
            for (IIngredient<ItemStack> ingredient : itemItemSet)
            {
                if (ingredient.testIgnoreCount(stack))
                {
                    return CUSTOM_METAL_ITEMS.get(ingredient).get();
                }
            }
            // Try using ore dict prefix-suffix common values (ie: ingotCopper)
            int[] ids = OreDictionary.getOreIDs(stack);
            for (int id : ids)
            {
                ICapabilityProvider handler = getMetalItemFromOreDict(OreDictionary.getOreName(id));
                if (handler != null)
                {
                    return handler;
                }
            }
        }
        return null;
    }

    @Nullable
    private static ICapabilityProvider getMetalItemFromOreDict(String oreDict)
    {
        for (OrePrefix orePrefix : ORE_DICT_MATERIAL_ITEMS)
        {
            if (oreDict.startsWith(orePrefix.name()))
            {
                return TFGUtils.MATERIALS_TO_TIER.keySet().stream()
                        .filter(material -> oreDict.equals(orePrefix.name() + material.toCamelCaseString()) && material.hasFluid())
                        .findFirst()
                        .map(material -> new MetalItemHandler(material, TFGUtils.getMetalAmountFromOrePrefix(orePrefix), true)).orElse(null);
            }
        }
        return null;
    }
}
