/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.util.agriculture;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.dries007.tfc.api.types.ICrop;
import net.dries007.tfc.objects.blocks.agriculture.BlockCropDead;
import net.dries007.tfc.objects.blocks.agriculture.BlockCropSimple;
import net.dries007.tfc.objects.blocks.agriculture.BlockCropSpreading;
import net.dries007.tfc.objects.blocks.agriculture.TFCBlockCrop;
import net.dries007.tfc.objects.items.TFCItems;
import net.dries007.tfc.objects.items.food.TFCItemFood;
import net.dries007.tfc.util.calendar.CalendarTFC;
import net.dries007.tfc.util.calendar.ICalendar;
import net.dries007.tfc.util.skills.Skill;
import net.dries007.tfc.util.skills.SkillTier;
import net.dries007.tfc.world.classic.worldgen.WorldGenWildCrops;

import static net.dries007.tfc.util.agriculture.Crop.CropType.*;

public enum Crop implements ICrop
{
    // these definitions are defined in the spreadsheet at
    // https://docs.google.com/spreadsheets/d/1Ghw3dCmVO5Gv0MMGBydUxox_nwLYmmcZkGSbbf0QSAE/edit#gid=893781093
    // It should be modified first, and then the resulting definitions copied to this space here
    BARLEY(Food.BARLEY, 0f, 1f, 26f, 33f, 50f, 70f, 310f, 330f, 8, 0.4f, SIMPLE),
    MAIZE(Food.MAIZE, 10f, 19f, 40f, 45f, 110f, 140f, 400f, 450f, 6, 0.6f, SIMPLE),
    OAT(Food.OAT, 0f, 3f, 30f, 34f, 50f, 100f, 350f, 400f, 8, 0.5f, SIMPLE),
    RICE(Food.RICE, 20f, 22f, 40f, 45f, 250f, 300f, 450f, 500f, 8, 0.6f, SIMPLE),
    RYE(Food.RYE, 0f, 4f, 35f, 40f, 50f, 100f, 400f, 450f, 8, 0.5f, SIMPLE),
    WHEAT(Food.WHEAT, 0f, 3f, 30f, 34f, 50f, 100f, 350f, 400f, 8, 0.5f, SIMPLE),
    BEET(Food.BEET, -5f, 0f, 20f, 25f, 50f, 70f, 300f, 320f, 7, 0.6f, SIMPLE),
    CABBAGE(Food.CABBAGE, -10f, 0f, 27f, 33f, 50f, 60f, 280f, 300f, 6, 0.6f, SIMPLE),
    CARROT(Food.CARROT, 3f, 10f, 30f, 36f, 50f, 100f, 400f, 450f, 5, 0.6f, SIMPLE),
    GARLIC(Food.GARLIC, -20f, -1f, 18f, 26f, 50f, 60f, 310f, 340f, 5, 0.65f, SIMPLE),
    GREEN_BEAN(Food.GREEN_BEAN, 2f, 9f, 35f, 41f, 70f, 150f, 410f, 450f, 7, 0.45f, PICKABLE),
    ONION(Food.ONION, -1f, 10f, 37f, 40f, 70f, 200f, 410f, 450f, 7, 0.4f, SIMPLE),
    POTATO(Food.POTATO, 0f, 4f, 30f, 35f, 50f, 100f, 390f, 440f, 7, 0.55f, SIMPLE),
    SOYBEAN(Food.SOYBEAN, 8f, 12f, 30f, 36f, 55f, 160f, 410f, 450f, 7, 0.5f, SIMPLE),
    SQUASH(Food.SQUASH, 5f, 14f, 33f, 37f, 45f, 90f, 390f, 440f, 8, 0.5f, SIMPLE),
    SUGARCANE(Food.SUGARCANE, 12f, 20f, 38f, 45f, 50f, 160f, 410f, 450f, 8, 0.5f, SIMPLE),
    TOMATO(Food.TOMATO, 0f, 8f, 36f, 40f, 50f, 120f, 390f, 430f, 8, 0.45f, PICKABLE),
    RED_BELL_PEPPER(() -> new ItemStack(TFCItemFood.get(Food.RED_BELL_PEPPER)), () -> new ItemStack(TFCItemFood.get(Food.GREEN_BELL_PEPPER)), 4f, 12f, 32f, 38f, 50f, 100f, 400f, 450f, 7, 0.55f, PICKABLE),
    YELLOW_BELL_PEPPER(() -> new ItemStack(TFCItemFood.get(Food.YELLOW_BELL_PEPPER)), () -> new ItemStack(TFCItemFood.get(Food.GREEN_BELL_PEPPER)), 4f, 12f, 32f, 38f, 50f, 100f, 400f, 450f, 7, 0.55f, PICKABLE),
    JUTE(() -> new ItemStack(TFCItems.JUTE), () -> ItemStack.EMPTY, 5f, 11f, 37f, 42f, 50f, 100f, 410f, 450f, 6, 0.5f, SIMPLE),

    AMARANTH(Food.AMARANTH, 0f, 8f, 35f, 40f, 50f, 100f, 400f, 450f, 6, 0.5f, SIMPLE),
    BUCKWHEAT(Food.BUCKWHEAT,-5f, 0f, 30f, 35f, 50f, 100f, 400f, 450f, 6, 0.5f, SIMPLE),
    FONIO(Food.FONIO, 7f, 15f, 40f, 50f, 50f, 70f, 200f, 250f, 6, 0.5f, SIMPLE),
    MILLET(Food.MILLET, 0f, 4f, 35f, 40f, 70f, 90f, 400f, 450f, 6, 0.5f, SIMPLE),
    QUINOA(Food.QUINOA, -10f, -5f, 35f, 40f, 50f, 100f, 400f, 450f, 6, 0.5f, SIMPLE),
    SPELT(Food.SPELT, 0f, 4f, 35f, 40f, 70f, 90f, 400f, 450f, 6, 0.5f, SIMPLE),
    BLACK_EYED_PEAS(Food.BLACK_EYED_PEAS, 0f, 8f, 35f, 40f, 50f, 100f, 400f, 450f, 7, 0.5f, PICKABLE),
    CAYENNE_PEPPER(() -> new ItemStack(TFCItemFood.get(Food.RED_CAYENNE_PEPPER)), () -> new ItemStack(TFCItemFood.get(Food.GREEN_CAYENNE_PEPPER)), 4f, 12f, 35f, 40f, 50f, 100f, 400f, 450f, 7, 0.5f, PICKABLE),
    GINGER(Food.GINGER, 0f, 5f, 35f, 40f, 50f, 100f, 400f, 450f, 5, 0.5f, SIMPLE),
    GINSENG(Food.GINSENG, 0f, 5f, 35f, 40f, 50f, 100f, 400f, 450f, 5, 0.5f, SIMPLE),
    RUTABAGA(Food.RUTABAGA, 0f, 8f, 35f, 40f, 50f, 100f, 400f, 450f, 7, 0.5f, SIMPLE),
    TURNIP(Food.TURNIP, 0f, 8f, 35f, 40f, 50f, 100f, 400f, 450f, 7, 0.5f, SIMPLE),
    SUGAR_BEET(Food.SUGAR_BEET, 0f, 5f, 35f, 40f, 50f, 100f, 400f, 450f, 7, 0.5f, SIMPLE),
    PURPLE_GRAPE(Food.PURPLE_GRAPE, 0f, 8f, 35f, 40f, 50f, 100f, 400f, 450f, 8, 0.5f, PICKABLE),
    GREEN_GRAPE(Food.GREEN_GRAPE, 0f, 8f, 35f, 40f, 50f, 100f, 400f, 450f, 8, 0.5f, PICKABLE),
    LIQUORICE_ROOT(Food.LIQUORICE_ROOT, -20f, -1f, 18f, 26f, 50f, 60f, 310f, 340f, 8, 0.5f, SIMPLE),
    COFFEA(Food.COFFEA_CHERRIES, 7f, 15f, 40f, 50f, 50f, 70f, 200f, 250f, 8, 0.5f, PICKABLE),

    SISAL(() -> new ItemStack(TFCItems.SISAL), () -> ItemStack.EMPTY, 12f, 18f, 35f, 40f, 50f, 100f, 400f, 450f, 6, 0.5f, SIMPLE),
    COCA(() -> new ItemStack(TFCItemFood.get(Food.COCA_LEAF)), () -> ItemStack.EMPTY, 0f, 18f, 35f, 40f, 50f, 100f, 400f, 450f, 6, 0.5f, PICKABLE),
    COTTON(() -> new ItemStack(TFCItems.COTTON_FIBER), () -> ItemStack.EMPTY, 0f, 8f, 35f, 40f, 50f, 100f, 400f, 450f, 6, 0.5f, PICKABLE),
    LINEN(() -> new ItemStack(TFCItems.LINEN), () -> ItemStack.EMPTY, 0f, 18f, 35f, 40f, 50f, 100f, 400f, 450f, 6, 0.5f, SIMPLE),
    HEMP(() -> new ItemStack(TFCItems.HEMP), () -> new ItemStack(TFCItemFood.get(Food.CANNABIS_BUD)), 0f, 18f, 35f, 40f, 50f, 100f, 400f, 450f, 5, 0.5f, PICKABLE),
    HOP(() -> new ItemStack(TFCItems.HOPS), () -> ItemStack.EMPTY, 0f, 18f, 35f, 40f, 50f, 100f, 400f, 450f, 6, 0.5f, PICKABLE),
    INDIGO(() -> new ItemStack(TFCItems.INDIGO), () -> ItemStack.EMPTY, 0f, 18f, 35f, 40f, 50f, 100f, 400f, 450f, 5, 0.5f, SIMPLE),
    MADDER(() -> new ItemStack(TFCItems.MADDER), () -> ItemStack.EMPTY, 0f, 18f, 35f, 40f, 50f, 100f, 400f, 450f, 5, 0.5f, SIMPLE),
    OPIUM_POPPY(() -> new ItemStack(TFCItemFood.get(Food.OPIUM_POPPY_BULB)), () -> ItemStack.EMPTY, 0f, 4f, 35f, 40f, 50f, 100f, 400f, 450f, 6, 0.5f, PICKABLE),
    RAPE(() -> new ItemStack(TFCItems.RAPE), () -> ItemStack.EMPTY, 0f, 10f, 35f, 40f, 50f, 100f, 400f, 450f, 6, 0.5f, SIMPLE),
    WELD(() -> new ItemStack(TFCItems.WELD), () -> ItemStack.EMPTY, 0f, 18f, 35f, 40f, 50f, 100f, 400f, 450f, 5, 0.5f, SIMPLE),
    WOAD(() -> new ItemStack(TFCItems.WOAD), () -> ItemStack.EMPTY, 0f, 18f, 35f, 40f, 50f, 100f, 400f, 450f, 6, 0.5f, SIMPLE),
    TOBACCO(() -> new ItemStack(TFCItemFood.get(Food.TOBACCO_LEAF)), () -> ItemStack.EMPTY, 0f, 18f, 35f, 40f, 50f, 100f, 400f, 450f, 7, 0.5f, PICKABLE);

    static
    {
        for (ICrop crop : values())
        {
            WorldGenWildCrops.register(crop);
        }
    }

    /**
     * the count to add to the amount of food dropped when applying the skill bonus
     *
     * @param skill  agriculture skill of the harvester
     * @param random random instance to use, generally Block.RANDOM
     * @return amount to add to item stack count
     */
    public static int getSkillFoodBonus(Skill skill, Random random)
    {
        return random.nextInt(2 + (int) (6 * skill.getTotalLevel()));
    }

    /**
     * the count to add to the amount of seeds dropped when applying the skill bonus
     *
     * @param skill  agriculture skill of the harvester
     * @param random random instance to use, generally Block.RANDOM
     * @return amount to add to item stack count
     */
    public static int getSkillSeedBonus(Skill skill, Random random)
    {
        if (skill.getTier().isAtLeast(SkillTier.ADEPT) && random.nextInt(10 - 2 * skill.getTier().ordinal()) == 0)
            return 1;
        else
            return 0;
    }

    // how this crop generates food items
    private final Supplier<ItemStack> foodDrop;
    private final Supplier<ItemStack> foodDropEarly;
    // temperature compatibility range
    private final float tempMinAlive, tempMinGrow, tempMaxGrow, tempMaxAlive;
    // rainfall compatibility range
    private final float rainMinAlive, rainMinGrow, rainMaxGrow, rainMaxAlive;
    // growth
    private final int growthStages; // the number of blockstates the crop has for growing, ignoring wild state
    private final float growthTime; // Time is measured in % of months, scales with calendar month length
    // which crop block behavior implementation is used
    private final CropType type;

//    private final Product product;

    Crop(Food foodDrop, float tempMinAlive, float tempMinGrow, float tempMaxGrow, float tempMaxAlive, float rainMinAlive, float rainMinGrow, float rainMaxGrow, float rainMaxAlive, int growthStages, float growthTime, CropType type)
    {
        this(() -> new ItemStack(TFCItemFood.get(foodDrop)), () -> ItemStack.EMPTY, tempMinAlive, tempMinGrow, tempMaxGrow, tempMaxAlive, rainMinAlive, rainMinGrow, rainMaxGrow, rainMaxAlive, growthStages, growthTime, type);
    }

    Crop(Supplier<ItemStack> foodDrop, Supplier<ItemStack> foodDropEarly, float tempMinAlive, float tempMinGrow, float tempMaxGrow, float tempMaxAlive, float rainMinAlive, float rainMinGrow, float rainMaxGrow, float rainMaxAlive, int growthStages, float growthTime, CropType type)
    {
        this.foodDrop = foodDrop;
        this.foodDropEarly = foodDropEarly;

        this.tempMinAlive = tempMinAlive;
        this.tempMinGrow = tempMinGrow;
        this.tempMaxGrow = tempMaxGrow;
        this.tempMaxAlive = tempMaxAlive;

        this.rainMinAlive = rainMinAlive;
        this.rainMinGrow = rainMinGrow;
        this.rainMaxGrow = rainMaxGrow;
        this.rainMaxAlive = rainMaxAlive;

        this.growthStages = growthStages;
        this.growthTime = growthTime; // This is measured in % of months

        this.type = type;
    }

    @Override
    public long getGrowthTicks()
    {
        return (long) (growthTime * CalendarTFC.CALENDAR_TIME.getDaysInMonth() * ICalendar.TICKS_IN_DAY);
    }

    @Override
    public int getMaxStage()
    {
        return growthStages - 1;
    }

    @Override
    public boolean isValidConditions(float temperature, float rainfall)
    {
        return tempMinAlive < temperature && temperature < tempMaxAlive && rainMinAlive < rainfall && rainfall < rainMaxAlive;
    }

    @Override
    public boolean isValidForGrowth(float temperature, float rainfall)
    {
        return tempMinGrow < temperature && temperature < tempMaxGrow && rainMinGrow < rainfall && rainfall < rainMaxGrow;
    }

    @Nonnull
    @Override
    public ItemStack getFoodDrop(int currentStage)
    {
        if (currentStage == getMaxStage())
        {
            return foodDrop.get();
        }
        else if (currentStage == getMaxStage() - 1)
        {
            return foodDropEarly.get();
        }
        return ItemStack.EMPTY;
    }

    public TFCBlockCrop createGrowingBlock()
    {
        if (type == SIMPLE || type == PICKABLE)
        {
            return BlockCropSimple.create(this, type == PICKABLE);
        }
        else if (type == SPREADING)
        {
            return BlockCropSpreading.create(this);
        }
        throw new IllegalStateException("Invalid growthstage property " + growthStages + " for crop");
    }

    public BlockCropDead createDeadBlock()
    {
        return new BlockCropDead(this);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInfo(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if (GuiScreen.isShiftKeyDown())
        {
            tooltip.add(TextFormatting.GRAY + I18n.format("tfc.tooltip.climate_info"));
            tooltip.add(TextFormatting.BLUE + I18n.format("tfc.tooltip.climate_info_rainfall", (int) rainMinGrow, (int) rainMaxGrow));
            tooltip.add(TextFormatting.GOLD + I18n.format("tfc.tooltip.climate_info_temperature", String.format("%.1f", tempMinGrow), String.format("%.1f", tempMaxGrow)));
        }
        else
        {
            tooltip.add(TextFormatting.GRAY + I18n.format("tfc.tooltip.hold_shift_for_climate_info"));
        }
    }

    enum CropType
    {
        SIMPLE, PICKABLE, SPREADING
    }
}
