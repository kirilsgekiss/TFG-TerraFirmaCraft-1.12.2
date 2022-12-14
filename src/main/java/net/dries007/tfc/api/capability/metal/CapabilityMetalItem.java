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
import net.dries007.tfc.api.types.Ore;
import net.dries007.tfc.compat.gregtech.TFGOrePrefix;
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
import net.dries007.tfc.util.OreDictionaryHelper;

public final class CapabilityMetalItem
{
    public static final ResourceLocation KEY = new ResourceLocation(TerraFirmaCraft.MOD_ID, "metal_object");
    public static final Map<IIngredient<ItemStack>, Supplier<ICapabilityProvider>> CUSTOM_METAL_ITEMS = new HashMap<>(); //Used inside CT, set custom IMetalItem for items outside TFC
    public static final Map<String, OrePrefix> ORE_DICT_MATERIAL_ITEMS = new LinkedHashMap<>();
    @CapabilityInject(IMetalItem.class)
    public static Capability<IMetalItem> METAL_OBJECT_CAPABILITY;

    public static void preInit()
    {
        CapabilityManager.INSTANCE.register(IMetalItem.class, new DumbStorage<>(), MetalItemHandler::new);

        // Register ore dict prefix values
        // ORE_DICT_METAL_ITEMS.put("ingotDouble", Metal.ItemType.DOUBLE_INGOT);
        ORE_DICT_MATERIAL_ITEMS.put("ingot", OrePrefix.ingot);
        ORE_DICT_MATERIAL_ITEMS.put("plateDouble", OrePrefix.plateDouble);
        ORE_DICT_MATERIAL_ITEMS.put("plate", OrePrefix.plate);
        ORE_DICT_MATERIAL_ITEMS.put("dust", OrePrefix.dust);
        ORE_DICT_MATERIAL_ITEMS.put("nugget", OrePrefix.nugget);

        ORE_DICT_MATERIAL_ITEMS.put("toolHeadSword", OrePrefix.toolHeadSword);
        ORE_DICT_MATERIAL_ITEMS.put("toolHeadAxe", OrePrefix.toolHeadAxe);
        ORE_DICT_MATERIAL_ITEMS.put("toolHeadPickaxe", OrePrefix.toolHeadPickaxe);
        ORE_DICT_MATERIAL_ITEMS.put("toolHeadShovel", OrePrefix.toolHeadShovel);
        ORE_DICT_MATERIAL_ITEMS.put("toolHeadHoe", OrePrefix.toolHeadHoe);
        ORE_DICT_MATERIAL_ITEMS.put("toolHeadSaw", OrePrefix.toolHeadSaw);
        ORE_DICT_MATERIAL_ITEMS.put("toolHeadHammer", OrePrefix.toolHeadHammer);
        ORE_DICT_MATERIAL_ITEMS.put("toolHeadSense", OrePrefix.toolHeadSense);
        ORE_DICT_MATERIAL_ITEMS.put("toolHeadKnife", TFGOrePrefix.toolHeadKnife);
    }

    public static void init()
    {
        // Register metalItemHandler here for custom items
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
        for (String oreName : ORE_DICT_MATERIAL_ITEMS.keySet())
        {
            if (oreDict.startsWith(oreName))
            {
                return TFGUtils.MATERIALS_TO_TIER.keySet().stream()
                        .filter(material -> oreDict.equals(OreDictionaryHelper.toString(oreName, material.toCamelCaseString())) && material.hasFluid())
                        .findFirst()
                        .map(metal -> new MetalItemHandler(metal, TFGUtils.getMetalAmountForOrePrefix(OrePrefix.getPrefix(oreName)), true)).orElse(null);
            }
        }
        return null;
    }
}
