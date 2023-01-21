package net.dries007.tfc.util.agriculture;

import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.dries007.tfc.objects.items.TFCItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.objects.items.food.ItemFoodTFC;
import net.dries007.tfc.types.DefaultTrees;
import net.dries007.tfc.util.calendar.CalendarTFC;
import net.dries007.tfc.util.calendar.ICalendar;
import net.dries007.tfc.util.calendar.Month;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public enum SeasonalTrees
{
    // Fruiting and/or flowering trees
    BAOBAB(() -> ItemFoodTFC.get(Food.BAOBAB_FRUIT), new int[] {0, 0, 1, 2, 2, 1, 1, 3, 4, 4, 0, 0}, 23f, 40f, 10f, 150f, 0.33f, false, false, false, DefaultTrees.BAOBAB_TREE),
    MACLURA(() -> ItemFoodTFC.get(Food.OSAGE_ORANGE), new int[] {0, 0, 1, 2, 2, 1, 1, 3, 4, 4, 0, 0}, -1f, 17f, 140f, 400f, 0.33f, false, false, false, DefaultTrees.MACLURA_TREE),
    MAHOGANY(() -> ItemFoodTFC.get(Food.SKY_FRUIT), new int[] {0, 0, 1, 2, 2, 1, 1, 3, 4, 4, 0, 0}, 23f, 42f, 220f, 500f, 0.33f, false, false, false, DefaultTrees.MAHOGANY_TREE),
    PINK_IVORY(() -> ItemFoodTFC.get(Food.PINK_IVORY_DRUPE), new int[] {0, 0, 1, 2, 2, 1, 1, 3, 4, 4, 0, 0}, 18f, 31f, 210f, 500f, 0.33f, false, false, false, DefaultTrees.PINK_IVORY_TREE),
    SYZYGIUM(() -> ItemFoodTFC.get(Food.RIBERRY), new int[] {0, 0, 1, 2, 2, 1, 1, 3, 4, 4, 0, 0}, 13f, 35f, 140f, 360f, 0.33f, false, false, false, DefaultTrees.SYZYGIUM_TREE),
    YEW(() -> ItemFoodTFC.get(Food.YEW_BERRY), new int[] {0, 0, 1, 2, 2, 1, 1, 3, 4, 4, 0, 0}, -15f, 11f, 180f, 350f, 0.33f, true, false, false, DefaultTrees.YEW_TREE),
    JOSHUA_TREE(() -> ItemFoodTFC.get(Food.BARREL_CACTUS_FRUIT), new int[] {1, 2, 2, 2, 3, 3, 3, 1, 1, 1, 1, 1}, 13f, 35f, 140f, 360f, 0.33f, false, false, true, DefaultTrees.JOSHUA_TREE_TREE),
    PURPLE_JACARANDA(null, new int[] {0, 0, 1, 1, 2, 1, 1, 1, 2, 4, 4, 0}, 10f, 34f, 180f, 300f, 0.33f, true, false, false, DefaultTrees.JACARANDA_TREE),
    YELLOW_JACARANDA(null, new int[] {0, 0, 1, 1, 2, 1, 1, 1, 2, 4, 4, 0}, 10f, 34f, 180f, 300f, 0.33f, true, false, false, DefaultTrees.JACARANDA_TREE),
    JUNIPER(() -> ItemFoodTFC.get(Food.JUNIPER), new int[] {0, 0, 1, 2, 2, 1, 1, 3, 4, 4, 0, 0}, -8f, 20f, 80f, 350f, 0.33f, false, false, false, DefaultTrees.JUNIPER_TREE),
    RED_CEDAR(() -> ItemFoodTFC.get(Food.JUNIPER), new int[] {0, 0, 1, 2, 2, 1, 1, 3, 4, 4, 0, 0}, -8f, 17f, 10f, 240f, 0.33f, false, false, false, DefaultTrees.RED_CEDAR_TREE),
    WHITE_CEDAR(() -> ItemFoodTFC.get(Food.JUNIPER), new int[] {0, 0, 1, 2, 2, 1, 1, 3, 4, 4, 0, 0}, -8f, 17f, 10f, 240f, 0.33f, false, false, false, DefaultTrees.WHITE_CEDAR_TREE),
    PINK_IPE(null, new int[] {0, 0, 0, 1, 1, 2, 2, 2, 1, 1, 4, 4}, 15f, 32f, 150f, 350f, 0.33f, true, false, false, DefaultTrees.IPE_TREE),
    WHITE_IPE(null, new int[] {0, 0, 0, 1, 1, 2, 2, 2, 1, 1, 4, 4}, 15f, 32f, 150f, 350f, 0.33f, true, false, false, DefaultTrees.IPE_TREE),
    YELLOW_IPE(null, new int[] {0, 0, 0, 1, 1, 2, 2, 2, 1, 1, 4, 4}, 15f, 32f, 150f, 350f, 0.33f, true, false, false, DefaultTrees.IPE_TREE),
    ARGYLE_EUCALYPTUS(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, 18f, 39f, 120f, 300f, 0.33f, true, true, false, DefaultTrees.EUCALYPTUS_TREE, true),
    RAINBOW_EUCALYPTUS(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, 18f, 39f, 120f, 300f, 0.33f, true, true, false, DefaultTrees.EUCALYPTUS_TREE, true),
    SNOW_GUM_EUCALYPTUS(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, 18f, 39f, 120f, 300f, 0.33f, true, true, false, DefaultTrees.EUCALYPTUS_TREE, true),

    // Seasonal trees
    GINKGO(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, 6f, 20f, 240f, 550f, 0.33f, true, false, false, DefaultTrees.GINKGO_TREE),
    LARCH(() -> TFCItems.PINECONE, new int[] {0, 0, 1, 2, 2, 1, 1, 3, 4, 4, 0, 0}, -12f, 15f, 60f, 400f, 0.33f, true, false, false, DefaultTrees.LARCH_TREE),
    LOCUST(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -6f, 15f, 120f, 290f, 0.33f, true, false, false, DefaultTrees.LOCUST_TREE),
    YELLOW_HAWTHORN(() -> ItemFoodTFC.get(Food.HAWTHORN), new int[] {0, 0, 1, 2, 2, 1, 1, 3, 4, 4, 0, 0}, -8f, 14f, 180f, 400f, 0.33f, true, false, false, DefaultTrees.HAWTHORN_TREE),
    ORANGE_HAWTHORN(() -> ItemFoodTFC.get(Food.HAWTHORN), new int[] {0, 0, 1, 2, 2, 1, 1, 3, 4, 4, 0, 0}, -8f, 14f, 180f, 400f, 0.33f, true, false, false, DefaultTrees.HAWTHORN_TREE),
    RED_HAWTHORN(() -> ItemFoodTFC.get(Food.HAWTHORN), new int[] {0, 0, 1, 2, 2, 1, 1, 3, 4, 4, 0, 0}, -8f, 14f, 180f, 400f, 0.33f, true, false, false, DefaultTrees.HAWTHORN_TREE),
    YELLOW_MULBERRY(() -> ItemFoodTFC.get(Food.HAWTHORN), new int[] {0, 0, 1, 2, 2, 1, 1, 3, 4, 4, 0, 0}, -30f, 28f, 140f, 420f, 0.33f, true, false, false, DefaultTrees.MULBERRY_TREE),
    ORANGE_MULBERRY(() -> ItemFoodTFC.get(Food.HAWTHORN), new int[] {0, 0, 1, 2, 2, 1, 1, 3, 4, 4, 0, 0}, -30f, 28f, 140f, 420f, 0.33f, true, false, false, DefaultTrees.MULBERRY_TREE),
    RED_MULBERRY(() -> ItemFoodTFC.get(Food.HAWTHORN), new int[] {0, 0, 1, 2, 2, 1, 1, 3, 4, 4, 0, 0}, -30f, 28f, 140f, 420f, 0.33f, true, false, false, DefaultTrees.MULBERRY_TREE),
    YELLOW_ROWAN(() -> ItemFoodTFC.get(Food.ROWAN_BERRY), new int[] {0, 0, 1, 2, 2, 1, 1, 3, 4, 4, 0, 0}, -15f, 8f, 180f, 400f, 0.33f, true, false, false, DefaultTrees.ROWAN_TREE),
    ORANGE_ROWAN(() -> ItemFoodTFC.get(Food.ROWAN_BERRY), new int[] {0, 0, 1, 2, 2, 1, 1, 3, 4, 4, 0, 0}, -15f, 8f, 180f, 400f, 0.33f, true, false, false, DefaultTrees.ROWAN_TREE),
    RED_ROWAN(() -> ItemFoodTFC.get(Food.ROWAN_BERRY), new int[] {0, 0, 1, 2, 2, 1, 1, 3, 4, 4, 0, 0}, -15f, 8f, 180f, 400f, 0.33f, true, false, false, DefaultTrees.ROWAN_TREE),
    YELLOW_PINK_CHERRY(() -> ItemFoodTFC.get(Food.CHERRY), new int[] {0, 0, 0, 2, 2, 1, 1, 3, 4, 4, 0, 0}, 0f, 20f, 180f, 300f, 0.33f, true, false, false, DefaultTrees.PINK_CHERRY_TREE),
    ORANGE_PINK_CHERRY(() -> ItemFoodTFC.get(Food.CHERRY), new int[] {0, 0, 0, 2, 2, 1, 1, 3, 4, 4, 0, 0}, 0f, 20f, 180f, 300f, 0.33f, true, false, false, DefaultTrees.PINK_CHERRY_TREE),
    RED_PINK_CHERRY(() -> ItemFoodTFC.get(Food.CHERRY), new int[] {0, 0, 0, 0, 2, 1, 1, 3, 4, 4, 0, 0}, 0f, 20f, 180f, 300f, 0.33f, true, false, false, DefaultTrees.PINK_CHERRY_TREE),
    YELLOW_WHITE_CHERRY(() -> ItemFoodTFC.get(Food.CHERRY), new int[] {0, 0, 0, 2, 2, 1, 1, 3, 4, 4, 0, 0}, 0f, 20f, 180f, 300f, 0.33f, true, false, false, DefaultTrees.WHITE_CHERRY_TREE),
    ORANGE_WHITE_CHERRY(() -> ItemFoodTFC.get(Food.CHERRY), new int[] {0, 0, 0, 2, 2, 1, 1, 3, 4, 4, 0, 0}, 0f, 20f, 180f, 300f, 0.33f, true, false, false, DefaultTrees.WHITE_CHERRY_TREE),
    RED_WHITE_CHERRY(() -> ItemFoodTFC.get(Food.CHERRY), new int[] {0, 0, 0, 0, 2, 1, 1, 3, 4, 4, 0, 0}, 0f, 20f, 180f, 300f, 0.33f, true, false, false, DefaultTrees.WHITE_CHERRY_TREE),
    YELLOW_SWEETGUM(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -2f, 18f, 140f, 360f, 0.33f, true, false, false, DefaultTrees.SWEETGUM_TREE),
    ORANGE_SWEETGUM(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -2f, 18f, 140f, 360f, 0.33f, true, false, false, DefaultTrees.SWEETGUM_TREE),
    RED_SWEETGUM(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -2f, 18f, 140f, 360f, 0.33f, true, false, false, DefaultTrees.SWEETGUM_TREE),
    YELLOW_ALDER(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -4f, 13f, 60f, 400f, 0.33f, true, false, false, DefaultTrees.ALDER_TREE),
    ORANGE_ALDER(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -4f, 13f, 60f, 400f, 0.33f, true, false, false, DefaultTrees.ALDER_TREE),
    RED_ALDER(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -4f, 13f, 60f, 400f, 0.33f, true, false, false, DefaultTrees.ALDER_TREE),
    YELLOW_BEECH(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -15f, 9f, 220f, 300f, 0.33f, true, false, false, DefaultTrees.BEECH_TREE),
    ORANGE_BEECH(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -15f, 9f, 220f, 300f, 0.33f, true, false, false, DefaultTrees.BEECH_TREE),
    RED_BEECH(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -15f, 9f, 220f, 300f, 0.33f, true, false, false, DefaultTrees.BEECH_TREE),
    YELLOW_BLACK_WALNUT(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -10f, 16f, 180f, 300f, 0.33f, true, false, false, DefaultTrees.BLACK_WALNUT_TREE),
    ORANGE_BLACK_WALNUT(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -10f, 16f, 180f, 300f, 0.33f, true, false, false, DefaultTrees.BLACK_WALNUT_TREE),
    RED_BLACK_WALNUT(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -10f, 16f, 180f, 300f, 0.33f, true, false, false, DefaultTrees.BLACK_WALNUT_TREE),
    YELLOW_BUTTERNUT(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -8f, 17f, 180f, 320f, 0.33f, true, false, false, DefaultTrees.BUTTERNUT_TREE),
    ORANGE_BUTTERNUT(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -8f, 17f, 180f, 320f, 0.33f, true, false, false, DefaultTrees.BUTTERNUT_TREE),
    RED_BUTTERNUT(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -8f, 17f, 180f, 320f, 0.33f, true, false, false, DefaultTrees.BUTTERNUT_TREE),
    /*
    *   Cypress trees are coniferous.
    */
    //YELLOW_CYPRESS(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, 4f, 33f, 140f, 350f, 0.33f, true, false, false, TreesTFCF.CYPRESS_TREE),
    //ORANGE_CYPRESS(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, 4f, 33f, 140f, 350f, 0.33f, true, false, false, TreesTFCF.CYPRESS_TREE),
    //RED_CYPRESS(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, 4f, 33f, 140f, 350f, 0.33f, true, false, false, TreesTFCF.CYPRESS_TREE),
    YELLOW_EUROPEAN_OAK(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -8f, 15f, 140f, 430f, 0.33f, true, false, false, DefaultTrees.EUROPEAN_OAK_TREE),
    ORANGE_EUROPEAN_OAK(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -8f, 15f, 140f, 430f, 0.33f, true, false, false, DefaultTrees.EUROPEAN_OAK_TREE),
    RED_EUROPEAN_OAK(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -8f, 15f, 140f, 430f, 0.33f, true, false, false, DefaultTrees.EUROPEAN_OAK_TREE),
    YELLOW_HAZEL(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -10f, 14f, 60f, 400f, 0.33f, true, false, false, DefaultTrees.HAZEL_TREE),
    ORANGE_HAZEL(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -10f, 14f, 60f, 400f, 0.33f, true, false, false, DefaultTrees.HAZEL_TREE),
    RED_HAZEL(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -10f, 14f, 60f, 400f, 0.33f, true, false, false, DefaultTrees.HAZEL_TREE),
    YELLOW_HORNBEAM(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -10f, 12f, 140f, 430f, 0.33f, true, false, false, DefaultTrees.HORNBEAM_TREE),
    ORANGE_HORNBEAM(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -10f, 12f, 140f, 430f, 0.33f, true, false, false, DefaultTrees.HORNBEAM_TREE),
    RED_HORNBEAM(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -10f, 12f, 140f, 430f, 0.33f, true, false, false, DefaultTrees.HORNBEAM_TREE),
    YELLOW_POPLAR(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -7f, 14f, 140f, 400f, 0.33f, true, false, false, DefaultTrees.POPLAR_TREE),
    ORANGE_POPLAR(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -7f, 14f, 140f, 400f, 0.33f, true, false, false, DefaultTrees.POPLAR_TREE),
    RED_POPLAR(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -7f, 14f, 140f, 400f, 0.33f, true, false, false, DefaultTrees.POPLAR_TREE),
    YELLOW_RED_ELM(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, 2f, 20f, 60f, 290f, 0.33f, true, false, false, DefaultTrees.RED_ELM_TREE),
    ORANGE_RED_ELM(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, 2f, 20f, 60f, 290f, 0.33f, true, false, false, DefaultTrees.RED_ELM_TREE),
    RED_RED_ELM(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, 2f, 20f, 60f, 290f, 0.33f, true, false, false, DefaultTrees.RED_ELM_TREE),
    YELLOW_WALNUT(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -10f, 16f, 180f, 300f, 0.33f, true, false, false, DefaultTrees.WALNUT_TREE),
    ORANGE_WALNUT(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -10f, 16f, 180f, 300f, 0.33f, true, false, false, DefaultTrees.WALNUT_TREE),
    RED_WALNUT(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -10f, 16f, 180f, 300f, 0.33f, true, false, false, DefaultTrees.WALNUT_TREE),
    YELLOW_WHITE_ELM(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, 2f, 20f, 60f, 290f, 0.33f, true, false, false, DefaultTrees.WHITE_ELM_TREE),
    ORANGE_WHITE_ELM(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, 2f, 20f, 60f, 290f, 0.33f, true, false, false, DefaultTrees.WHITE_ELM_TREE),
    RED_WHITE_ELM(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, 2f, 20f, 60f, 290f, 0.33f, true, false, false, DefaultTrees.WHITE_ELM_TREE),
    YELLOW_WHITEBEAM(() -> ItemFoodTFC.get(Food.ROWAN_BERRY), new int[] {0, 0, 1, 2, 2, 1, 1, 3, 4, 4, 0, 0}, -10f, 12f, 140f, 430f, 0.33f, true, false, false, DefaultTrees.WHITEBEAM_TREE),
    ORANGE_WHITEBEAM(() -> ItemFoodTFC.get(Food.ROWAN_BERRY), new int[] {0, 0, 1, 2, 2, 1, 1, 3, 4, 4, 0, 0}, -10f, 12f, 140f, 430f, 0.33f, true, false, false, DefaultTrees.WHITEBEAM_TREE),
    RED_WHITEBEAM(() -> ItemFoodTFC.get(Food.ROWAN_BERRY), new int[] {0, 0, 1, 2, 2, 1, 1, 3, 4, 4, 0, 0}, -10f, 12f, 140f, 430f, 0.33f, true, false, false, DefaultTrees.WHITEBEAM_TREE),
    YELLOW_ASH(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -6f, 12f, 60f, 140f, 0.33f, true, false, false, DefaultTrees.ASH_TREE),
    ORANGE_ASH(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -6f, 12f, 60f, 140f, 0.33f, true, false, false, DefaultTrees.ASH_TREE),
    RED_ASH(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -6f, 12f, 60f, 140f, 0.33f, true, false, false, DefaultTrees.ASH_TREE),
    YELLOW_ASPEN(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -10f, 16f, 10f, 80f, 0.33f, true, false, false, DefaultTrees.ASPEN_TREE),
    ORANGE_ASPEN(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -10f, 16f, 10f, 80f, 0.33f, true, false, false, DefaultTrees.ASPEN_TREE),
    RED_ASPEN(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -10f, 16f, 10f, 80f, 0.33f, true, false, false, DefaultTrees.ASPEN_TREE),
    YELLOW_BIRCH(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -15f, 7f, 20f, 180f, 0.33f, true, false, false, DefaultTrees.BIRCH_TREE),
    ORANGE_BIRCH(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -15f, 7f, 20f, 180f, 0.33f, true, false, false, DefaultTrees.BIRCH_TREE),
    RED_BIRCH(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -15f, 7f, 20f, 180f, 0.33f, true, false, false, DefaultTrees.BIRCH_TREE),
    YELLOW_CHESTNUT(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, 11f, 35f, 160f, 320f, 0.33f, true, false, false, DefaultTrees.CHESTNUT_TREE),
    ORANGE_CHESTNUT(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, 11f, 35f, 160f, 320f, 0.33f, true, false, false, DefaultTrees.CHESTNUT_TREE),
    RED_CHESTNUT(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, 11f, 35f, 160f, 320f, 0.33f, true, false, false, DefaultTrees.CHESTNUT_TREE),
    YELLOW_HICKORY(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, 7f, 29f, 80f, 250f, 0.33f, true, false, false, DefaultTrees.HICKORY_TREE),
    ORANGE_HICKORY(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, 7f, 29f, 80f, 250f, 0.33f, true, false, false, DefaultTrees.HICKORY_TREE),
    RED_HICKORY(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, 7f, 29f, 80f, 250f, 0.33f, true, false, false, DefaultTrees.HICKORY_TREE),
    YELLOW_MAPLE(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, 3f, 20f, 140f, 360f, 0.33f, true, false, false, DefaultTrees.MAPLE_TREE),
    ORANGE_MAPLE(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, 3f, 20f, 140f, 360f, 0.33f, true, false, false, DefaultTrees.MAPLE_TREE),
    RED_MAPLE(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, 3f, 20f, 140f, 360f, 0.33f, true, false, false, DefaultTrees.MAPLE_TREE),
    YELLOW_OAK(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -8f, 12f, 180f, 430f, 0.33f, true, false, false, DefaultTrees.OAK_TREE),
    ORANGE_OAK(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -8f, 12f, 180f, 430f, 0.33f, true, false, false, DefaultTrees.OAK_TREE),
    RED_OAK(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, -8f, 12f, 180f, 430f, 0.33f, true, false, false, DefaultTrees.OAK_TREE),
    YELLOW_SYCAMORE(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, 17f, 33f, 120f, 290f, 0.33f, true, false, false, DefaultTrees.SYCAMORE_TREE),
    ORANGE_SYCAMORE(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, 17f, 33f, 120f, 290f, 0.33f, true, false, false, DefaultTrees.SYCAMORE_TREE),
    RED_SYCAMORE(null, new int[] {0, 0, 1, 2, 2, 1, 1, 1, 4, 4, 0, 0}, 17f, 33f, 120f, 290f, 0.33f, true, false, false, DefaultTrees.SYCAMORE_TREE);

    /*static
    {
        for (FruitTreeTFCF tree : values())
        {
            WorldGenFruitTrees.register(tree);
        }
    }*/

    private final Supplier<Item> fruit;
    private final int[] stages;
    private final int numStages;
    /*private final Month flowerMonthStart;
    private final int floweringMonths;
    private final Month harvestMonthStart;
    private final int harvestingMonths;
    private final Month autumnMonthStart;
    private final int autumnMonths;
    private final Month winterMonthStart;
    private final int winterMonths;*/
    private final float growthTime;
    private final float minTemp;
    private final float maxTemp;
    private final float minRain;
    private final float maxRain;
    public final boolean isNormalTree;
    public final boolean isCustomLog;
    public final boolean hasDeadLeaves;
    public final boolean isLogTree;
    public final boolean isSpecialBlock;
    public final Tree normalTree;

    //FruitTreeTFCF(Supplier<Item> fruit, int[] stages, Month flowerMonthStart, int floweringMonths, Month harvestMonthStart, int harvestingMonths, Month autumnMonthStart, int autumnMonths, Month winterMonthStart, int winterMonths, float minTemp, float maxTemp, float minRain, float maxRain, float growthTime, boolean hasDeadLeaves, boolean isLogTree, Tree normalTree)
    SeasonalTrees(Supplier<Item> fruit, int[] stages, float minTemp, float maxTemp, float minRain, float maxRain, float growthTime, boolean hasDeadLeaves, boolean isLogTree, boolean isSpecialBlock, Tree normalTree)
    {
        this.fruit = fruit;
        this.stages = stages;
        /*this.flowerMonthStart = flowerMonthStart;
        this.floweringMonths = floweringMonths;
        this.harvestMonthStart = harvestMonthStart;
        this.harvestingMonths = harvestingMonths;
        this.autumnMonthStart = autumnMonthStart;
        this.autumnMonths = autumnMonths;
        this.winterMonthStart = winterMonthStart;
        this.winterMonths = winterMonths;*/
        this.growthTime = growthTime * CalendarTFC.CALENDAR_TIME.getDaysInMonth() * ICalendar.HOURS_IN_DAY;

        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.minRain = minRain;
        this.maxRain = maxRain;

        this.isCustomLog = false;
        this.isNormalTree = true;
        this.hasDeadLeaves = hasDeadLeaves;
        this.isLogTree = isLogTree;
        this.isSpecialBlock = isSpecialBlock;
        this.normalTree = normalTree;

        HashSet<Integer> hashSet = new HashSet<>();
        for (int stage : stages)
        {
            hashSet.add(stage);
        }
        this.numStages = hashSet.size() <= 1 ? 1 : hashSet.size() - 1;
    }

    //FruitTreeTFCF(Supplier<Item> fruit, Month flowerMonthStart, int floweringMonths, Month harvestMonthStart, int harvestingMonths, Month autumnMonthStart, int autumnMonths, Month winterMonthStart, int winterMonths, float minTemp, float maxTemp, float minRain, float maxRain, float growthTime, boolean hasDeadLeaves, boolean isLogTree, Tree normalTree, boolean customLog)
    SeasonalTrees(Supplier<Item> fruit, int[] stages, float minTemp, float maxTemp, float minRain, float maxRain, float growthTime, boolean hasDeadLeaves, boolean isLogTree, boolean isSpecialBlock, Tree normalTree, boolean customLog)
    {
        this.fruit = fruit;
        this.stages = stages;
        /*this.flowerMonthStart = flowerMonthStart;
        this.floweringMonths = floweringMonths;
        this.harvestMonthStart = harvestMonthStart;
        this.harvestingMonths = harvestingMonths;
        this.autumnMonthStart = autumnMonthStart;
        this.autumnMonths = autumnMonths;
        this.winterMonthStart = winterMonthStart;
        this.winterMonths = winterMonths;*/
        this.growthTime = growthTime * CalendarTFC.CALENDAR_TIME.getDaysInMonth() * ICalendar.HOURS_IN_DAY;

        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.minRain = minRain;
        this.maxRain = maxRain;

        this.isCustomLog = customLog;
        this.isNormalTree = true;
        this.hasDeadLeaves = hasDeadLeaves;
        this.isLogTree = isLogTree;
        this.isSpecialBlock = isSpecialBlock;
        this.normalTree = normalTree;

        HashSet<Integer> hashSet = new HashSet<>();
        for (int stage : stages)
        {
            hashSet.add(stage);
        }
        this.numStages = hashSet.size() <= 1 ? 1 : hashSet.size() - 1;
    }

    public int getStageForMonth(Month month)
    {
        return stages[month.ordinal()];
    }

    public int getStageForMonth()
    {
        return getStageForMonth(CalendarTFC.CALENDAR_TIME.getMonthOfYear());
    }

    public int getNumStages()
    {
        return numStages;
    }

    public float getGrowthTime()
    {
        return this.growthTime;
    }

    public boolean isValidConditions(float temperature, float rainfall)
    {
        return minTemp - 5 < temperature && temperature < maxTemp + 5 && minRain - 50 < rainfall && rainfall < maxRain + 50;
    }

    public boolean isValidForGrowth(float temperature, float rainfall)
    {
        return minTemp < temperature && temperature < maxTemp && minRain < rainfall && rainfall < maxRain;
    }

    public Item getFoodDrop()
    {
        return this.fruit.get();
    }

    public Supplier<Item> getDrop()
    {
        return fruit;
    }

    public String getName()
    {
        return this.name();
    }

    @SideOnly(Side.CLIENT)
    public void addInfo(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {}
}
