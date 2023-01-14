/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.items.food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import net.dries007.tfc.Constants;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import net.dries007.tfc.api.capability.food.*;
import net.dries007.tfc.api.capability.size.IItemSize;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.dries007.tfc.util.agriculture.Food;
import tfcflorae.objects.items.ItemTFCF;

@ParametersAreNonnullByDefault
public class ItemFoodTFC extends ItemFood implements IItemSize, IItemFoodTFC
{
    private static final Map<Food, ItemFoodTFC> MAP = new HashMap<>();
    ArrayList<PotionEffectToHave> PotionEffects = new ArrayList<PotionEffectToHave>();

    public static ItemFoodTFC get(Food food)
    {
        return MAP.get(food);
    }

    public static ItemStack get(Food food, int amount)
    {
        return new ItemStack(MAP.get(food), amount);
    }

    protected final Food food;

    public ItemFoodTFC(@Nonnull Food food) //, Object... objs
    {
        super(0, 0, food.getCategory() == Food.Category.MEAT || food.getCategory() == Food.Category.COOKED_MEAT);
        this.food = food;
//        this.setMaxDamage(0);
        if (MAP.put(food, this) != null)
        {
            throw new IllegalStateException("There can only be one.");
        }

//        for (Object obj : objs)
//        {
//            if(obj instanceof PotionEffectToHave)
//            {
//                PotionEffectToHave Effect = (PotionEffectToHave)obj;
//                PotionEffects.add(Effect);
//            }
//            else if (obj instanceof Object[])
//                tfcflorae.util.OreDictionaryHelper.register(this, (Object[]) obj);
//            else
//                tfcflorae.util.OreDictionaryHelper.register(this, obj);
//        }

        // Use "category" here as to not conflict with actual items, i.e. grain
        OreDictionaryHelper.register(this, "category", food.getCategory());
        if (food.getOreDictNames() != null)
        {
            for (Object name : food.getOreDictNames())
            {
                OreDictionaryHelper.register(this, name);
            }
        }
    }



    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (this.isInCreativeTab(tab))
        {
            // Makes creative items not decay (like JEI)
            ItemStack stack = new ItemStack(this);
            IFood cap = stack.getCapability(CapabilityFood.CAPABILITY, null);
            if (cap != null)
            {
                cap.setNonDecaying();
            }
            items.add(stack);
        }
    }

    @Override
    public int getItemStackLimit(ItemStack stack)
    {
        return getStackSize(stack);
    }

    @Nonnull
    @Override
    public Size getSize(@Nonnull ItemStack stack)
    {
        return Size.SMALL;
    }

    @Nonnull
    @Override
    public Weight getWeight(@Nonnull ItemStack stack)
    {
        return Weight.VERY_LIGHT;
    }

    @Override
    public ICapabilityProvider getCustomFoodHandler()
    {
        return food.isHeatable() ? new FoodHeatHandler(null, food) : new FoodHandler(null, food);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if(!PotionEffects.isEmpty())
            for(PotionEffectToHave Effect : PotionEffects)
            {
                if (Constants.RNG.nextInt(Effect.chance) == 0)
                    player.addPotionEffect(new PotionEffect(Effect.PotionEffect, Effect.Duration, Effect.Power));
            }
    }
}
