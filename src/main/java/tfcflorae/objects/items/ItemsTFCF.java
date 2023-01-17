package tfcflorae.objects.items;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.objects.items.*;
import net.dries007.tfc.objects.items.food.ItemFoodTFC;
import net.dries007.tfc.objects.items.food.ItemFoodTFCF;
import net.dries007.tfc.objects.items.wood.ItemBoatTFC;
import net.dries007.tfc.objects.items.wood.ItemFruitBoat;
import net.dries007.tfc.objects.items.wood.ItemFruitDoor;
import net.dries007.tfc.types.DefaultTrees;
import net.dries007.tfc.util.agriculture.Food;
import net.dries007.tfc.util.agriculture.Fruits;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item.ToolMaterial;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.*;
import net.dries007.tfc.objects.blocks.BlockRockSlabTFC;
import net.dries007.tfc.objects.items.ItemSlabTFC;
import net.dries007.tfc.objects.items.ceramics.*;
import net.dries007.tfc.objects.items.itemblock.ItemBlockTFC;
import net.dries007.tfc.util.agriculture.FruitTree;
import net.dries007.tfc.util.Helpers;

import tfcflorae.objects.blocks.BlocksTFCF;
import tfcflorae.objects.blocks.wood.fruitwood.*;
import tfcflorae.objects.blocks.wood.BlockLogTFCF;
import tfcflorae.objects.blocks.wood.bamboo.BlockBambooLog;
import net.dries007.tfc.objects.items.itemblock.ItemBlockStickBundle;
import net.dries007.tfc.objects.items.tools.ItemWalkingStick;
import tfcflorae.util.OreDictionaryHelper;
import tfcflorae.ConfigTFCF;
import tfcflorae.TFCFlorae;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;
import static net.dries007.tfc.objects.CreativeTabsTFC.*;
import static tfcflorae.TFCFlorae.TFCFLORAE_MODID;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = TFCFLORAE_MODID)
@GameRegistry.ObjectHolder(TFCFLORAE_MODID)
public final class ItemsTFCF
{
    // Normal Trees Fruits
    @GameRegistry.ObjectHolder("food/baobab_fruit")
    public static final ItemFoodTFCF BAOBAB_FRUIT = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/barrel_cactus_fruit")
    public static final ItemFoodTFCF BARREL_CACTUS_FRUIT = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/hawthorn")
    public static final ItemFoodTFCF HAWTHORN = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/juniper")
    public static final ItemFoodTFCF JUNIPER = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/osage_orange")
    public static final ItemFoodTFCF OSAGE_ORANGE = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/pink_ivory_drupe")
    public static final ItemFoodTFCF PINK_IVORY_DRUPE = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/riberry")
    public static final ItemFoodTFCF RIBERRY = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/rowan_berry")
    public static final ItemFoodTFCF ROWAN_BERRY = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/sky_fruit")
    public static final ItemFoodTFCF SKY_FRUIT = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/yew_berry")
    public static final ItemFoodTFCF YEW_BERRY = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/glowberry")
    public static final ItemFoodTFCF GLOWBERRY = Helpers.getNull();

    // Foods
    @GameRegistry.ObjectHolder("food/cassia_cinnamon_bark")
    public static final ItemFoodTFCF CASSIA_CINNAMON_BARK = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/ground_cassia_cinnamon_bark")
    public static final ItemFoodTFCF GROUND_CASSIA_CINNAMON = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/ceylon_cinnamon_bark")
    public static final ItemFoodTFCF CEYLON_CINNAMON_BARK = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/ground_ceylon_cinnamon_bark")
    public static final ItemFoodTFCF GROUND_CEYLON_CINNAMON = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/black_tea")
    public static final ItemFoodTFCF BLACK_TEA = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/dried/black_tea")
    public static final ItemFoodTFCF DRIED_BLACK_TEA = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/green_tea")
    public static final ItemFoodTFCF GREEN_TEA = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/dried/green_tea")
    public static final ItemFoodTFCF DRIED_GREEN_TEA = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/white_tea")
    public static final ItemFoodTFCF WHITE_TEA = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/dried/white_tea")
    public static final ItemFoodTFCF DRIED_WHITE_TEA = Helpers.getNull();

    // Miscellaneous Food Stuff
    @GameRegistry.ObjectHolder("pomace")
    public static final ItemMiscTFCF POMACE = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/dried/coffea_cherries")
    public static final ItemFoodTFCF DRIED_COFFEA_CHERRIES = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/roasted/coffee_beans")
    public static final ItemFoodTFCF ROASTED_COFFEE_BEANS = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/coffee_powder")
    public static final ItemFoodTFCF COFFEE_POWDER = Helpers.getNull();

    @GameRegistry.ObjectHolder("crop/product/malt_barley")
    public static final ItemMiscTFCF MALT_BARLEY = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/malt_corn")
    public static final ItemMiscTFCF MALT_CORN = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/malt_rice")
    public static final ItemMiscTFCF MALT_RICE = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/malt_rye")
    public static final ItemMiscTFCF MALT_RYE = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/malt_wheat")
    public static final ItemMiscTFCF MALT_WHEAT = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/malt_amaranth")
    public static final ItemMiscTFCF MALT_AMARANTH = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/malt_buckwheat")
    public static final ItemMiscTFCF MALT_BUCKWHEAT = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/malt_fonio")
    public static final ItemMiscTFCF MALT_FONIO = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/malt_millet")
    public static final ItemMiscTFCF MALT_MILLET = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/malt_quinoa")
    public static final ItemMiscTFCF MALT_QUINOA = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/malt_spelt")
    public static final ItemMiscTFCF MALT_SPELT = Helpers.getNull();

    @GameRegistry.ObjectHolder("food/ginger")
    public static final ItemFoodTFCF GINGER = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/linseed_paste")
    public static final ItemFoodTFCF LINSEED_PASTE = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/rape_seed_paste")
    public static final ItemFoodTFCF RAPE_SEED_PASTE = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/sunflower_seed_paste")
    public static final ItemFoodTFCF SUNFLOWER_SEED_PASTE = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/pumpkin_chunks")
    public static final ItemFoodTFCF OPIUM_POPPY_SEED_PASTE = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/mashed_sugar_beet")
    public static final ItemFoodTFCF MASHED_SUGAR_BEET = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/mashed_sugar_cane")
    public static final ItemFoodTFCF MASHED_SUGAR_CANE = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/soybean_paste")
    public static final ItemFoodTFCF SOYBEAN_PASTE = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/chamomile_head")
    public static final ItemMiscTFCF CHAMOMILE_HEAD = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/dried/chamomile_head")
    public static final ItemMiscTFCF DRIED_CHAMOMILE_HEAD = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/dandelion_head")
    public static final ItemMiscTFCF DANDELION_HEAD = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/dried/dandelion_head")
    public static final ItemMiscTFCF DRIED_DANDELION_HEAD = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/labrador_tea_head")
    public static final ItemMiscTFCF LABRADOR_TEA_HEAD = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/dried/labrador_tea_head")
    public static final ItemMiscTFCF DRIED_LABRADOR_TEA_HEAD = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sunflower_head")
    public static final ItemMiscTFCF SUNFLOWER_HEAD = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/dried/sunflower_head")
    public static final ItemMiscTFCF DRIED_SUNFLOWER_HEAD = Helpers.getNull();


    // Normal Trees Nuts
    @GameRegistry.ObjectHolder("food/pinecone")
    public static final ItemFoodTFCF PINECONE = Helpers.getNull();


    @GameRegistry.ObjectHolder("food/amaranth_flour")
    public static final ItemFoodTFCF AMARANTH_FLOUR = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/buckwheat_flour")
    public static final ItemFoodTFCF BUCKWHEAT_FLOUR = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/fonio_flour")
    public static final ItemFoodTFCF FONIO_FLOUR = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/millet_flour")
    public static final ItemFoodTFCF MILLET_FLOUR = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/quinoa_flour")
    public static final ItemFoodTFCF QUINOA_FLOUR = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/spelt_flour")
    public static final ItemFoodTFCF SPELT_FLOUR = Helpers.getNull();

    // Normal Items
    @GameRegistry.ObjectHolder("tools/walking_Stick")
    public static final ItemWalkingStick WALKING_STICK = Helpers.getNull();

    /*@GameRegistry.ObjectHolder("tools/bows/shortbow/shortbow")
    public static final ItemBowTFCF SHORTBOW = Helpers.getNull();
    @GameRegistry.ObjectHolder("tools/bows/longbow/longbow")
    public static final ItemBowTFCF LONGBOW = Helpers.getNull();
    @GameRegistry.ObjectHolder("tools/bows/bonebow/bonebow")
    public static final ItemBowTFCF BONEBOW = Helpers.getNull();
    @GameRegistry.ObjectHolder("tools/bows/bow_of_lost_souls/bow_of_lost_souls")
    public static final ItemBowTFCF BOW_OF_LOST_SOULS = Helpers.getNull();
    @GameRegistry.ObjectHolder("tools/bows/elite_power_bow/elite_power_bow")
    public static final ItemBowTFCF ELITE_POWER_BOW = Helpers.getNull();
    @GameRegistry.ObjectHolder("tools/bows/green_menace/green_menace")
    public static final ItemBowTFCF GREEN_MENACE = Helpers.getNull();
    @GameRegistry.ObjectHolder("tools/bows/hunting_bow/hunting_bow")
    public static final ItemBowTFCF HUNTING_BOW = Helpers.getNull();
    @GameRegistry.ObjectHolder("tools/bows/nocturnal_bow/nocturnal_bow")
    public static final ItemBowTFCF NOCTURNAL_BOW = Helpers.getNull();
    @GameRegistry.ObjectHolder("tools/bows/red_snake/red_snake")
    public static final ItemBowTFCF RED_SNAKE = Helpers.getNull();
    @GameRegistry.ObjectHolder("tools/bows/rosebow/rosebow")
    public static final ItemBowTFCF ROSEBOW = Helpers.getNull();
    @GameRegistry.ObjectHolder("tools/bows/sabrewing/sabrewing")
    public static final ItemBowTFCF SABREWING = Helpers.getNull();*/

    @GameRegistry.ObjectHolder("container/leather_bag_piece")
    public static final ItemMiscTFCF LEATHER_BAG_PIECE = Helpers.getNull();
    @GameRegistry.ObjectHolder("container/leather_bag")
    public static final ItemBag LEATHER_BAG = Helpers.getNull();
    @GameRegistry.ObjectHolder("container/pineapple_leather_bag_piece")
    public static final ItemMiscTFCF PINEAPPLE_LEATHER_BAG_PIECE = Helpers.getNull();
    @GameRegistry.ObjectHolder("container/pineapple_leather_bag")
    public static final ItemBag PINEAPPLE_LEATHER_BAG = Helpers.getNull();
    @GameRegistry.ObjectHolder("container/burlap_sack_piece")
    public static final ItemMiscTFCF BURLAP_SACK_PIECE = Helpers.getNull();
    @GameRegistry.ObjectHolder("container/burlap_sack")
    public static final ItemSack BURLAP_SACK = Helpers.getNull();
    @GameRegistry.ObjectHolder("container/wool_sack_piece")
    public static final ItemMiscTFCF WOOL_SACK_PIECE = Helpers.getNull();
    @GameRegistry.ObjectHolder("container/wool_sack")
    public static final ItemSack WOOL_SACK = Helpers.getNull();
    @GameRegistry.ObjectHolder("container/silk_sack_piece")
    public static final ItemMiscTFCF SILK_SACK_PIECE = Helpers.getNull();
    @GameRegistry.ObjectHolder("container/silk_sack")
    public static final ItemSack SILK_SACK = Helpers.getNull();
    @GameRegistry.ObjectHolder("container/cotton_sack_piece")
    public static final ItemMiscTFCF COTTON_SACK_PIECE = Helpers.getNull();
    @GameRegistry.ObjectHolder("container/cotton_sack")
    public static final ItemSack COTTON_SACK = Helpers.getNull();
    @GameRegistry.ObjectHolder("container/hemp_sack_piece")
    public static final ItemMiscTFCF HEMP_SACK_PIECE = Helpers.getNull();
    @GameRegistry.ObjectHolder("container/hemp_sack")
    public static final ItemSack HEMP_SACK = Helpers.getNull();
    @GameRegistry.ObjectHolder("container/linen_sack_piece")
    public static final ItemMiscTFCF LINEN_SACK_PIECE = Helpers.getNull();
    @GameRegistry.ObjectHolder("container/linen_sack")
    public static final ItemSack LINEN_SACK = Helpers.getNull();
    @GameRegistry.ObjectHolder("container/sisal_sack_piece")
    public static final ItemMiscTFCF SISAL_SACK_PIECE = Helpers.getNull();
    @GameRegistry.ObjectHolder("container/sisal_sack")
    public static final ItemSack SISAL_SACK = Helpers.getNull();
    @GameRegistry.ObjectHolder("container/yucca_sack_piece")
    public static final ItemMiscTFCF YUCCA_SACK_PIECE = Helpers.getNull();
    @GameRegistry.ObjectHolder("container/yucca_sack")
    public static final ItemSack YUCCA_SACK = Helpers.getNull();

    @GameRegistry.ObjectHolder("logwood_chips")
    public static final ItemMiscTFCF LOGWOOD_CHIPS = Helpers.getNull();
    //@GameRegistry.ObjectHolder("resin")
    //public static final ItemMiscTFCF RESIN = Helpers.getNull();
    @GameRegistry.ObjectHolder("charred_bones")
    public static final ItemMiscTFCF CHARRED_BONES = Helpers.getNull();
    @GameRegistry.ObjectHolder("conch")
    public static final ItemMiscTFCF CONCH = Helpers.getNull();
    @GameRegistry.ObjectHolder("clam")
    public static final ItemMiscTFCF CLAM = Helpers.getNull();
    @GameRegistry.ObjectHolder("live_clam")
    public static final ItemMiscTFCF LIVE_CLAM = Helpers.getNull();
    @GameRegistry.ObjectHolder("scallop")
    public static final ItemMiscTFCF SCALLOP = Helpers.getNull();
    @GameRegistry.ObjectHolder("live_scallop")
    public static final ItemMiscTFCF LIVE_SCALLOP = Helpers.getNull();
    @GameRegistry.ObjectHolder("pearl")
    public static final ItemMiscTFCF PEARL = Helpers.getNull();
    @GameRegistry.ObjectHolder("black_pearl")
    public static final ItemMiscTFCF BLACK_PEARL = Helpers.getNull();
    @GameRegistry.ObjectHolder("live_starfish")
    public static final ItemMiscTFCF LIVE_STARFISH = Helpers.getNull();
    @GameRegistry.ObjectHolder("animal/product/silk_moth_egg")
    public static final ItemMiscTFCF SILK_MOTH_EGG = Helpers.getNull();
    @GameRegistry.ObjectHolder("animal/product/silk_worm_hatchery")
    public static final ItemMiscTFCF SILK_WORM_HATCHERY = Helpers.getNull();
    @GameRegistry.ObjectHolder("animal/product/silk_worm")
    public static final ItemMiscTFCF SILK_WORM = Helpers.getNull();
    @GameRegistry.ObjectHolder("animal/product/silk_worm_cocoon")
    public static final ItemMiscTFCF SILK_WORM_COCOON = Helpers.getNull();
    @GameRegistry.ObjectHolder("animal/product/silk_worm_cocoon_boiled")
    public static final ItemMiscTFCF SILK_WORM_COCOON_BOILED = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/mulberry_leaf")
    public static final ItemMiscTFCF MULBERRY_LEAF = Helpers.getNull();

    @GameRegistry.ObjectHolder("food/cannabis_bud")
    public static final ItemFoodTFCF CANNABIS_BUD = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/dried/cannabis_bud")
    public static final ItemFoodTFCF DRIED_CANNABIS_BUD = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/cannabis_leaf")
    public static final ItemFoodTFCF CANNABIS_LEAF = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/dried/cannabis_leaf")
    public static final ItemFoodTFCF DRIED_CANNABIS_LEAF = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/coca_leaf")
    public static final ItemFoodTFCF COCA_LEAF = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/dried/coca_leaf")
    public static final ItemFoodTFCF DRIED_COCA_LEAF = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/opium_poppy_bulb")
    public static final ItemFoodTFCF OPIUM_POPPY_BULB = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/dried/opium_poppy_bulb")
    public static final ItemFoodTFCF DRIED_OPIUM_POPPY_BULB = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/peyote")
    public static final ItemFoodTFCF PEYOTE = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/dried/peyote")
    public static final ItemFoodTFCF DRIED_PEYOTE = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/tobacco_leaf")
    public static final ItemFoodTFCF TOBACCO_LEAF = Helpers.getNull();
    @GameRegistry.ObjectHolder("food/dried/tobacco_leaf")
    public static final ItemFoodTFCF DRIED_TOBACCO_LEAF = Helpers.getNull();

    @GameRegistry.ObjectHolder("crop/product/papyrus_pulp")
    public static final ItemMiscTFCF PAPYRUS_PULP = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/papyrus_fiber")
    public static final ItemMiscTFCF PAPYRUS_FIBER = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/papyrus_paper")
    public static final ItemMiscTFCF PAPYRUS_PAPER = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/agave")
    public static final ItemMiscTFCF AGAVE = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sisal_fiber")
    public static final ItemMiscTFCF SISAL_FIBER = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sisal_string")
    public static final ItemMiscTFCF SISAL_STRING = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sisal_cloth")
    public static final ItemMiscTFCF SISAL_CLOTH = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/cotton_boll")
    public static final ItemMiscTFCF COTTON_BOLL = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/cotton_yarn")
    public static final ItemMiscTFCF COTTON_YARN = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/cotton_cloth")
    public static final ItemMiscTFCF COTTON_CLOTH = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/flax")
    public static final ItemMiscTFCF FLAX = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/flax_fiber")
    public static final ItemMiscTFCF FLAX_FIBER = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/linen_string")
    public static final ItemMiscTFCF LINEN_STRING = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/linen_cloth")
    public static final ItemMiscTFCF LINEN_CLOTH = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/hemp")
    public static final ItemMiscTFCF HEMP = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/hemp_fiber")
    public static final ItemMiscTFCF HEMP_FIBER = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/hemp_string")
    public static final ItemMiscTFCF HEMP_STRING = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/hemp_cloth")
    public static final ItemMiscTFCF HEMP_CLOTH = Helpers.getNull();

    @GameRegistry.ObjectHolder("crop/product/silk_disc")
    public static final Item SILK_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sisal_disc")
    public static final Item SISAL_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/cotton_disc")
    public static final Item COTTON_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/linen_disc")
    public static final Item LINEN_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/papyrus_disc")
    public static final Item PAPYRUS_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/hemp_disc")
    public static final Item HEMP_DISC = Helpers.getNull();

    @GameRegistry.ObjectHolder("crop/product/soybean_jute_disc")
    public static final Item SOYBEAN_JUTE_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/soybean_silk_disc")
    public static final Item SOYBEAN_SILK_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/soybean_sisal_disc")
    public static final Item SOYBEAN_SISAL_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/soybean_cotton_disc")
    public static final Item SOYBEAN_COTTON_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/soybean_linen_disc")
    public static final Item SOYBEAN_LINEN_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/soybean_papyrus_disc")
    public static final Item SOYBEAN_PAPYRUS_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/soybean_hemp_disc")
    public static final Item SOYBEAN_HEMP_DISC = Helpers.getNull();

    @GameRegistry.ObjectHolder("crop/product/linseed_jute_disc")
    public static final Item LINSEED_JUTE_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/linseed_silk_disc")
    public static final Item LINSEED_SILK_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/linseed_sisal_disc")
    public static final Item LINSEED_SISAL_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/linseed_cotton_disc")
    public static final Item LINSEED_COTTON_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/linseed_linen_disc")
    public static final Item LINSEED_LINEN_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/linseed_papyrus_disc")
    public static final Item LINSEED_PAPYRUS_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/linseed_hemp_disc")
    public static final Item LINSEED_HEMP_DISC = Helpers.getNull();

    @GameRegistry.ObjectHolder("crop/product/rape_seed_jute_disc")
    public static final Item RAPE_SEED_JUTE_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/rape_seed_silk_disc")
    public static final Item RAPE_SEED_SILK_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/rape_seed_sisal_disc")
    public static final Item RAPE_SEED_SISAL_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/rape_seed_cotton_disc")
    public static final Item RAPE_SEED_COTTON_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/rape_seed_linen_disc")
    public static final Item RAPE_SEED_LINEN_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/rape_seed_papyrus_disc")
    public static final Item RAPE_SEED_PAPYRUS_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/rape_seed_hemp_disc")
    public static final Item RAPE_SEED_HEMP_DISC = Helpers.getNull();

    @GameRegistry.ObjectHolder("crop/product/sunflower_seed_jute_disc")
    public static final Item SUNFLOWER_SEED_JUTE_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sunflower_seed_silk_disc")
    public static final Item SUNFLOWER_SEED_SILK_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sunflower_seed_sisal_disc")
    public static final Item SUNFLOWER_SEED_SISAL_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sunflower_seed_cotton_disc")
    public static final Item SUNFLOWER_SEED_COTTON_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sunflower_seed_linen_disc")
    public static final Item SUNFLOWER_SEED_LINEN_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sunflower_seed_papyrus_disc")
    public static final Item SUNFLOWER_SEED_PAPYRUS_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sunflower_seed_hemp_disc")
    public static final Item SUNFLOWER_SEED_HEMP_DISC = Helpers.getNull();

    @GameRegistry.ObjectHolder("crop/product/opium_poppy_seed_jute_disc")
    public static final Item OPIUM_POPPY_SEED_JUTE_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/opium_poppy_seed_silk_disc")
    public static final Item OPIUM_POPPY_SEED_SILK_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/opium_poppy_seed_sisal_disc")
    public static final Item OPIUM_POPPY_SEED_SISAL_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/opium_poppy_seed_cotton_disc")
    public static final Item OPIUM_POPPY_SEED_COTTON_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/opium_poppy_seed_linen_disc")
    public static final Item OPIUM_POPPY_SEED_LINEN_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/opium_poppy_seed_papyrus_disc")
    public static final Item OPIUM_POPPY_SEED_PAPYRUS_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/opium_poppy_seed_hemp_disc")
    public static final Item OPIUM_POPPY_SEED_HEMP_DISC = Helpers.getNull();

    @GameRegistry.ObjectHolder("crop/product/sugar_beet_jute_disc")
    public static final Item SUGAR_BEET_JUTE_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_beet_silk_disc")
    public static final Item SUGAR_BEET_SILK_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_beet_sisal_disc")
    public static final Item SUGAR_BEET_SISAL_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_beet_cotton_disc")
    public static final Item SUGAR_BEET_COTTON_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_beet_linen_disc")
    public static final Item SUGAR_BEET_LINEN_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_beet_papyrus_disc")
    public static final Item SUGAR_BEET_PAPYRUS_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_beet_hemp_disc")
    public static final Item SUGAR_BEET_HEMP_DISC = Helpers.getNull();

    @GameRegistry.ObjectHolder("crop/product/sugar_cane_jute_disc")
    public static final Item SUGAR_CANE_JUTE_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_cane_silk_disc")
    public static final Item SUGAR_CANE_SILK_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_cane_sisal_disc")
    public static final Item SUGAR_CANE_SISAL_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_cane_cotton_disc")
    public static final Item SUGAR_CANE_COTTON_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_cane_linen_disc")
    public static final Item SUGAR_CANE_LINEN_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_cane_papyrus_disc")
    public static final Item SUGAR_CANE_PAPYRUS_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_cane_hemp_disc")
    public static final Item SUGAR_CANE_HEMP_DISC = Helpers.getNull();

    @GameRegistry.ObjectHolder("crop/product/olive_silk_disc")
    public static final Item OLIVE_SILK_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/olive_sisal_disc")
    public static final Item OLIVE_SISAL_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/olive_cotton_disc")
    public static final Item OLIVE_COTTON_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/olive_linen_disc")
    public static final Item OLIVE_LINEN_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/olive_papyrus_disc")
    public static final Item OLIVE_PAPYRUS_DISC = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/olive_hemp_disc")
    public static final Item OLIVE_HEMP_DISC = Helpers.getNull();

    @GameRegistry.ObjectHolder("crop/product/silk_net")
    public static final Item SILK_NET = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sisal_net")
    public static final Item SISAL_NET = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/cotton_net")
    public static final Item COTTON_NET = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/linen_net")
    public static final Item LINEN_NET = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/papyrus_net")
    public static final Item PAPYRUS_NET = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/hemp_net")
    public static final Item HEMP_NET = Helpers.getNull();

    @GameRegistry.ObjectHolder("crop/product/dirty_silk_net")
    public static final Item DIRTY_SILK_NET = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/dirty_sisal_net")
    public static final Item DIRTY_SISAL_NET = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/dirty_cotton_net")
    public static final Item DIRTY_COTTON_NET = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/dirty_linen_net")
    public static final Item DIRTY_LINEN_NET = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/dirty_papyrus_net")
    public static final Item DIRTY_PAPYRUS_NET = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/dirty_hemp_net")
    public static final Item DIRTY_HEMP_NET = Helpers.getNull();
    
    @GameRegistry.ObjectHolder("crop/product/indigo")
    public static final ItemMiscTFCF INDIGO = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/madder")
    public static final ItemMiscTFCF MADDER = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/weld")
    public static final ItemMiscTFCF WELD = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/woad")
    public static final ItemMiscTFCF WOAD = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/hops")
    public static final ItemMiscTFCF HOPS = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/rape")
    public static final ItemMiscTFCF RAPE = Helpers.getNull();

    @GameRegistry.ObjectHolder("cellulose_fibers")
    public static final ItemMiscTFCF CELLULOSE_FIBERS = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/yucca_fiber")
    public static final ItemMiscTFCF YUCCA_FIBER = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/yucca_string")
    public static final ItemMiscTFCF YUCCA_STRING = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/yucca_canvas")
    public static final ItemMiscTFCF YUCCA_CANVAS = Helpers.getNull();

    @GameRegistry.ObjectHolder("wood/fruit_tree/pole/cassia_cinnamon")
    public static final ItemMisc CASSIA_CINNAMON_POLE = Helpers.getNull();
    @GameRegistry.ObjectHolder("wood/fruit_tree/lumber/cassia_cinnamon")
    public static final ItemMisc CASSIA_CINNAMON_LUMBER = Helpers.getNull();
    @GameRegistry.ObjectHolder("wood/fruit_tree/pole/ceylon_cinnamon")
    public static final ItemMisc CEYLON_CINNAMON_POLE = Helpers.getNull();
    @GameRegistry.ObjectHolder("wood/fruit_tree/lumber/ceylon_cinnamon")
    public static final ItemMisc CEYLON_CINNAMON_LUMBER = Helpers.getNull();



    @GameRegistry.ObjectHolder("storage/unfired/urn")
    public static final ItemPottery UNFIRED_URN = Helpers.getNull();
    @GameRegistry.ObjectHolder("wooden_bucket_salt")
    public static final ItemMiscTFCF WOODEN_BUCKET_SALT = Helpers.getNull();
    @GameRegistry.ObjectHolder("wooden_bucket_sugar")
    public static final ItemMiscTFCF WOODEN_BUCKET_SUGAR = Helpers.getNull();

    private static ImmutableList<Item> allSimpleItems;
    //private static ImmutableList<ItemBowTFCF> allItemBows;
    private static ImmutableList<Item> allFoodItems;
    private static ImmutableList<ItemFruitDoor> allFruitDoors;
    private static ImmutableList<Item> allCeramicMoldItems;
    public static ImmutableList<Item> getAllSimpleItems()
    {
        return allSimpleItems;
    }

    /*public static ImmutableList<ItemBowTFCF> getAllItemBows()
    {
        return allItemBows;
    }*/

    public static ImmutableList<Item> getAllFoodItems()
    {
        return allFoodItems;
    }

    public static ImmutableList<ItemFruitDoor> getAllFruitDoors() 
    { 
        return allFruitDoors; 
    }

    private static Map<Fruits, Item> driedFruits = new HashMap<>();

    public static Item getDriedFruit(Fruits fruit)
    {
        return driedFruits.get(fruit);
    }

    public static ImmutableList<Item> getAllCeramicMoldItems()
    {
        return allCeramicMoldItems;
    }


    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> r = event.getRegistry();

        ImmutableList.Builder<Item> simpleItems = ImmutableList.builder();
        //ImmutableList.Builder<ItemBowTFCF> itemBows = ImmutableList.builder();
        ImmutableList.Builder<ItemFruitDoor> fruitDoors = ImmutableList.builder();
        ImmutableList.Builder<Item> ceramicItems = ImmutableList.builder();


        // Dried Berries & Fruits
        for (Fruits fruit : Fruits.values())
        {
            if (TFCFlorae.FirmaLifeAdded)
            {
                if (!fruit.isVanillaFood && fruit.canDry())
                {
                    ItemFoodTFCF dried = new ItemFoodTFCF(fruit.getDriedData());
                    simpleItems.add(register(r, "food/dried/" + fruit.name().toLowerCase(), dried, CT_FOOD));
                    OreDictionary.registerOre(OreDictionaryHelper.toString("dried_" + fruit.name().toLowerCase()), dried);
                    OreDictionary.registerOre("fruitDry", dried);
                    driedFruits.put(fruit, dried);
                }
            }
            else
            {
                if (fruit.canDry())
                {
                    ItemFoodTFCF dried = new ItemFoodTFCF(fruit.getDriedData());
                    simpleItems.add(register(r, "food/dried/" + fruit.name().toLowerCase(), dried, CT_FOOD));
                    OreDictionary.registerOre(OreDictionaryHelper.toString("dried_" + fruit.name().toLowerCase()), dried);
                    OreDictionary.registerOre("fruitDry", dried);
                    driedFruits.put(fruit, dried);
                }
            }
        }


//        // Normal Tree Nuts
//
//        if (!TFCFlorae.FirmaLifeAdded)
//        {
//            simpleItems.add(register(r, "food/acorn", new ItemFoodTFC(Food.UNCRACKED_NUT, "acorn", "category_fruit"), CT_FOOD));
//            simpleItems.add(register(r, "food/acorn_nut", new ItemFoodTFC(Food.NUT, "acorn_nut", "category_fruit"), CT_FOOD));
//            simpleItems.add(register(r, "food/roasted/acorn_nut", new ItemFoodTFC(Food.ROASTED_NUT, "roasted_acorn_nut", "category_fruit"), CT_FOOD));
//        }
//
//
//        if (!TFCFlorae.FirmaLifeAdded)
//        {
//            simpleItems.add(register(r, "food/chestnut", new ItemFoodTFC(Food.UNCRACKED_NUT, "chestnut", "category_fruit"), CT_FOOD));
//            simpleItems.add(register(r, "food/chestnut_nut", new ItemFoodTFC(Food.NUT, "chestnut_nut", "category_fruit"), CT_FOOD));
//            simpleItems.add(register(r, "food/roasted/chestnut_nut", new ItemFoodTFC(Food.ROASTED_NUT, "roasted_chestnut_nut", "category_fruit"), CT_FOOD));
//        }
//
//
//        if (!TFCFlorae.FirmaLifeAdded)
//        {
//            simpleItems.add(register(r, "food/hickory_nut", new ItemFoodTFC(Food.UNCRACKED_NUT, "hickory_nut", "category_fruit"), CT_FOOD));
//            simpleItems.add(register(r, "food/hickory_nut_nut", new ItemFoodTFC(Food.NUT, "hickory_nut_nut", "category_fruit"), CT_FOOD));
//            simpleItems.add(register(r, "food/roasted/hickory_nut_nut", new ItemFoodTFC(Food.ROASTED_NUT, "roasted_hickory_nut_nut", "category_fruit"), CT_FOOD));
//
//            simpleItems.add(register(r, "food/pecan", new ItemFoodTFC(Food.UNCRACKED_NUT, "pecan", "category_fruit"), CT_FOOD));
//            simpleItems.add(register(r, "food/pecan_nut", new ItemFoodTFC(Food.NUT, "pecan_nut", "category_fruit"), CT_FOOD));
//            simpleItems.add(register(r, "food/roasted/pecan_nut", new ItemFoodTFC(Food.ROASTED_NUT, "roasted_pecan_nut", "category_fruit"), CT_FOOD));
//
//            simpleItems.add(register(r, "food/pinecone", new ItemFoodTFC(Food.UNCRACKED_NUT, "pinecone", "category_fruit"), CT_FOOD));
//            simpleItems.add(register(r, "food/pine_nut", new ItemFoodTFC(Food.NUT, "pine_nut", "category_fruit"), CT_FOOD));
//            simpleItems.add(register(r, "food/roasted/pine_nut", new ItemFoodTFC(Food.ROASTED_NUT, "roasted_pine_nut", "category_fruit"), CT_FOOD));
//        }



        if (TFCFlorae.FirmaLifeAdded)
        {
            for (String grain : new String[] {"amaranth", "buckwheat", "fonio", "millet", "quinoa", "spelt"})
            {
                ItemFoodTFC flatbread_dough = new ItemFoodTFC(Food.DOUGH);
                simpleItems.add(register(r, "food/" + grain + "_flatbread_dough", flatbread_dough, CT_FOOD));
                OreDictionary.registerOre(OreDictionaryHelper.toString(grain + "_flatbread_dough"), flatbread_dough);
                OreDictionary.registerOre("doughFlat", flatbread_dough);

                ItemFoodTFC flatbread = new ItemFoodTFC(Food.FLATBREAD);
                simpleItems.add(register(r, "food/" + grain + "_flatbread", flatbread, CT_FOOD));
                OreDictionary.registerOre("flatbread", flatbread);
                OreDictionary.registerOre("categoryBread", flatbread);

                ItemFoodTFC slice = new ItemFoodTFC(Food.SLICE);
                simpleItems.add(register(r, "food/" + grain + "_slice", slice, CT_FOOD));
                OreDictionary.registerOre("slice", slice);
                OreDictionary.registerOre("categoryBread", slice);
            }
        }

        simpleItems.add(register(r, "pomace", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "pomace", "category_fruit"), CT_MISC));
        simpleItems.add(register(r, "yeast", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "yeast"), CT_MISC));

        simpleItems.add(register(r, "firma_cola_mix", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "mix_firma_cola"), CT_MISC));
        simpleItems.add(register(r, "firma_cola_oils", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "oils_firma_cola"), CT_MISC));
        simpleItems.add(register(r, "firma_cola_blend", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "blend_firma_cola"), CT_MISC));

        simpleItems.add(register(r, "crop/product/chamomile_head", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "chamomile_head", "chamomile"), CT_MISC));
        simpleItems.add(register(r, "crop/product/dried/chamomile_head", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "dried_chamomile"), CT_MISC));
        simpleItems.add(register(r, "crop/product/dandelion_head", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "dandelion_head", "dandelion"), CT_MISC));
        simpleItems.add(register(r, "crop/product/dried/dandelion_head", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "dried_dandelion"), CT_MISC));
        simpleItems.add(register(r, "crop/product/labrador_tea_head", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "labrador_tea_head", "labrador_tea"), CT_MISC));
        simpleItems.add(register(r, "crop/product/dried/labrador_tea_head", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "dried_labrador_tea"), CT_MISC));
        simpleItems.add(register(r, "crop/product/sunflower_head", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "sunflower_head", "sunflower"), CT_MISC));
        simpleItems.add(register(r, "crop/product/dried/sunflower_head", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "dried_sunflower_head"), CT_MISC));

        simpleItems.add(register(r, "crop/product/malt_barley", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "malt_barley", "malt", "category_grain"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/malt_corn", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "malt_corn", "malt", "category_grain"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/malt_rice", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "malt_rice", "malt", "category_grain"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/malt_rye", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "malt_rye", "malt", "category_grain"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/malt_wheat", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "malt_wheat", "malt", "category_grain"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/malt_amaranth", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "malt_amaranth", "malt", "category_grain"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/malt_buckwheat", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "malt_buckwheat", "malt", "category_grain"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/malt_fonio", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "malt_fonio", "malt", "category_grain"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/malt_millet", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "malt_millet", "malt", "category_grain"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/malt_quinoa", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "malt_quinoa", "malt", "category_grain"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/malt_spelt", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "malt_spelt", "malt", "category_grain"), CT_FOOD));


        for (BlockLogTFCF log : BlocksTFCF.getAllNormalTreeLog())
            simpleItems.add(register(r, log.getRegistryName().getPath(), new ItemBlockTFC(log), CT_WOOD));

        // Tools
        simpleItems.add(register(r, "tools/walking_stick", new ItemWalkingStick(ToolMaterial.WOOD, 1f, 1.5f, 0.02f, 96, "stick_wood", "walking_stick"), CT_MISC));

        // Bows
        /*itemBows.add(register(r, "tools/bows/shortbow/shortbow", new ItemBowTFCF(Size.VERY_SMALL, Weight.LIGHT, 250, 0, "bow", "bow_shortbow", "bow_wooden_shortbow"), CT_MISC));
        itemBows.add(register(r, "tools/bows/longbow/longbow", new ItemBowTFCF(Size.SMALL, Weight.MEDIUM, 450, 3, "bow", "bow_longbow", "bow_wooden_longbow"), CT_MISC));
        itemBows.add(register(r, "tools/bows/bonebow/bonebow", new ItemBowTFCF(Size.VERY_SMALL, Weight.LIGHT, 320, 2, "bow", "bow_bonebow"), CT_MISC));
        itemBows.add(register(r, "tools/bows/bow_of_lost_souls/bow_of_lost_souls", new ItemBowTFCF(Size.VERY_SMALL, Weight.LIGHT, 320, 2, "bow", "bow_bow_of_lost_souls"), CT_MISC));
        itemBows.add(register(r, "tools/bows/elite_power_bow/elite_power_bow", new ItemBowTFCF(Size.VERY_SMALL, Weight.LIGHT, 360, 3, "bow", "bow_elite_power_bow"), CT_MISC));
        itemBows.add(register(r, "tools/bows/green_menace/green_menace", new ItemBowTFCF(Size.VERY_SMALL, Weight.LIGHT, 384, 1, "bow", "bow_green_menace"), CT_MISC));
        itemBows.add(register(r, "tools/bows/hunting_bow/hunting_bow", new ItemBowTFCF(Size.VERY_SMALL, Weight.LIGHT, 384, 1, "bow", "bow_hunting_bow"), CT_MISC));
        itemBows.add(register(r, "tools/bows/nocturnal_bow/nocturnal_bow", new ItemBowTFCF(Size.VERY_SMALL, Weight.LIGHT, 384, 1, "bow", "bow_nocturnal_bow"), CT_MISC));
        itemBows.add(register(r, "tools/bows/red_snake/red_snake", new ItemBowTFCF(Size.VERY_SMALL, Weight.LIGHT, 384, 1, "bow", "bow_red_snake"), CT_MISC));
        itemBows.add(register(r, "tools/bows/rosebow/rosebow", new ItemBowTFCF(Size.VERY_SMALL, Weight.LIGHT, 384, 1, "bow", "bow_rosebow"), CT_MISC));
        itemBows.add(register(r, "tools/bows/sabrewing/sabrewing", new ItemBowTFCF(Size.VERY_SMALL, Weight.LIGHT, 384, 1, "bow", "bow_sabrewing"), CT_MISC));*/

        // Containers
        simpleItems.add(register(r, "container/leather_bag_piece", new ItemMiscTFCF(Size.SMALL, Weight.LIGHT, "bag_piece", "bag_piece_leather"), CT_MISC));
        simpleItems.add(register(r, "container/leather_bag", new ItemBag("bag", "bag_leather"), CT_MISC));
        simpleItems.add(register(r, "container/pineapple_leather_bag_piece", new ItemMiscTFCF(Size.SMALL, Weight.LIGHT, "bag_piece", "bag_piece_pineapple_leather"), CT_MISC));
        simpleItems.add(register(r, "container/pineapple_leather_bag", new ItemBag("bag", "bag_pineapple_leather"), CT_MISC));
        simpleItems.add(register(r, "container/burlap_sack_piece", new ItemMiscTFCF(Size.SMALL, Weight.LIGHT, "sack_piece", "sack_piece_burlap"), CT_MISC));
        simpleItems.add(register(r, "container/burlap_sack", new ItemSack("sack", "sack_burlap"), CT_MISC));
        simpleItems.add(register(r, "container/wool_sack_piece", new ItemMiscTFCF(Size.SMALL, Weight.LIGHT, "sack_piece", "sack_piece_wool"), CT_MISC));
        simpleItems.add(register(r, "container/wool_sack", new ItemSack("sack", "sack_wool"), CT_MISC));
        simpleItems.add(register(r, "container/silk_sack_piece", new ItemMiscTFCF(Size.SMALL, Weight.LIGHT, "sack_piece", "sack_piece_silk"), CT_MISC));
        simpleItems.add(register(r, "container/silk_sack", new ItemSack("sack", "sack_silk"), CT_MISC));
        simpleItems.add(register(r, "container/cotton_sack_piece", new ItemMiscTFCF(Size.SMALL, Weight.LIGHT, "sack_piece", "sack_piece_cotton"), CT_MISC));
        simpleItems.add(register(r, "container/cotton_sack", new ItemSack("sack", "sack_cotton"), CT_MISC));
        simpleItems.add(register(r, "container/hemp_sack_piece", new ItemMiscTFCF(Size.SMALL, Weight.LIGHT, "sack_piece", "sack_piece_hemp"), CT_MISC));
        simpleItems.add(register(r, "container/hemp_sack", new ItemSack("sack", "sack_hemp"), CT_MISC));
        simpleItems.add(register(r, "container/linen_sack_piece", new ItemMiscTFCF(Size.SMALL, Weight.LIGHT, "sack_piece", "sack_piece_linen"), CT_MISC));
        simpleItems.add(register(r, "container/linen_sack", new ItemSack("sack", "sack_linen"), CT_MISC));
        simpleItems.add(register(r, "container/sisal_sack_piece", new ItemMiscTFCF(Size.SMALL, Weight.LIGHT, "sack_piece", "sack_piece_sisal"), CT_MISC));
        simpleItems.add(register(r, "container/sisal_sack", new ItemSack("sack", "sack_sisal"), CT_MISC));
        simpleItems.add(register(r, "container/yucca_sack_piece", new ItemMiscTFCF(Size.SMALL, Weight.LIGHT, "sack_piece", "sack_piece_yucca"), CT_MISC));
        simpleItems.add(register(r, "container/yucca_sack", new ItemSack("sack", "sack_yucca"), CT_MISC));

        // Items
        simpleItems.add(register(r, "wooden_bucket_salt", new ItemMiscTFCF(Size.LARGE, Weight.MEDIUM, "bucket_salt", "bucket_wooden_salt"), CT_MISC));
        simpleItems.add(register(r, "wooden_bucket_sugar", new ItemMiscTFCF(Size.LARGE, Weight.MEDIUM, "bucket_sugar", "bucket_wooden_sugar"), CT_MISC));
        simpleItems.add(register(r, "animal/product/silk_moth_egg", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "egg_silk_moth", "egg"), CT_MISC));
        simpleItems.add(register(r, "animal/product/silk_worm_hatchery", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "hatchery", "hatchery_silk_worm"), CT_MISC));
        simpleItems.add(register(r, "animal/product/silk_worm", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "silk_worm"), CT_MISC));
        simpleItems.add(register(r, "animal/product/silk_worm_cocoon", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "cocoon", "cocoon_silk_worm"), CT_MISC));
        simpleItems.add(register(r, "animal/product/silk_worm_cocoon_boiled", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "cocoon_boiled", "cocoon_silk_worm_boiled"), CT_MISC));
        simpleItems.add(register(r, "crop/product/mulberry_leaf", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "leaf", "leaf_mulberry", "leaves", "leaves_mulberry"), CT_MISC));
        simpleItems.add(register(r, "logwood_chips", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "chips_logwood", "dust_logwood", "powder_logwood"), CT_MISC));
        //simpleItems.add(register(r, "resin", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "resin", "glue"), CT_MISC));
        simpleItems.add(register(r, "charred_bones", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "bone_charred"), CT_MISC));
        simpleItems.add(register(r, "conch", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "conch", "seashell"), CT_MISC));
        simpleItems.add(register(r, "clam", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "clam", "seashell"), CT_MISC));
        simpleItems.add(register(r, "live_clam", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "clam", "clam_live"), CT_MISC));
        simpleItems.add(register(r, "scallop", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "scallop", "seashell"), CT_MISC));
        simpleItems.add(register(r, "live_scallop", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "scallop", "scallop_live"), CT_MISC));
        simpleItems.add(register(r, "live_starfish", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "starfish", "starfish_live"), CT_MISC));
        simpleItems.add(register(r, "pearl", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "pearl"), CT_MISC));
        simpleItems.add(register(r, "black_pearl", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "pearl_black"), CT_MISC));

        simpleItems.add(register(r, "cellulose_fibers", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "fiber", "fiber_cellulose"), CT_MISC));

        simpleItems.add(register(r, "crop/product/yucca_fiber", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "fiber", "fiber_yucca"), CT_MISC));
        simpleItems.add(register(r, "crop/product/yucca_string", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "string", "string_yucca"), CT_MISC));
        simpleItems.add(register(r, "crop/product/yucca_canvas", new ItemMiscTFCF(Size.VERY_SMALL, Weight.LIGHT, "cloth", "cloth_yucca", "fabric", "fabric_yucca", "canvas", "canvas_yucca"), CT_MISC));

        simpleItems.add(register(r, "crop/product/papyrus_pulp", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "pulp", "pulp_papyrus"), CT_MISC));
        simpleItems.add(register(r, "crop/product/papyrus_fiber", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "fiber", "fiber_papyrus"), CT_MISC));
        simpleItems.add(register(r, "crop/product/papyrus_paper", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "paper", "paper_papyrus"), CT_MISC));
        
        simpleItems.add(register(r, "crop/product/agave", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "crop_agave", "agave"), CT_MISC));
        simpleItems.add(register(r, "crop/product/sisal_fiber", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "fiber", "fiber_sisal"), CT_MISC));
        simpleItems.add(register(r, "crop/product/sisal_string", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "string", "string_sisal"), CT_MISC));
        simpleItems.add(register(r, "crop/product/sisal_cloth", new ItemMiscTFCF(Size.SMALL, Weight.LIGHT, "cloth", "cloth_sisal", "fabric", "fabric_sisal"), CT_MISC));
        
        simpleItems.add(register(r, "crop/product/cotton_boll", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "crop_cotton", "cotton"), CT_MISC));
        simpleItems.add(register(r, "crop/product/cotton_yarn", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "string", "string_cotton", "yarn", "yarn_cotton"), CT_MISC));
        simpleItems.add(register(r, "crop/product/cotton_cloth", new ItemMiscTFCF(Size.SMALL, Weight.LIGHT, "cloth", "cloth_high_quality", "cloth_cotton"), CT_MISC));
        
        simpleItems.add(register(r, "crop/product/flax", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "crop_flax", "flax"), CT_MISC));
        simpleItems.add(register(r, "crop/product/flax_fiber", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "fiber", "fiber_flax"), CT_MISC));
        simpleItems.add(register(r, "crop/product/linen_string", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "string", "string_linen"), CT_MISC));
        simpleItems.add(register(r, "crop/product/linen_cloth", new ItemMiscTFCF(Size.SMALL, Weight.LIGHT, "cloth", "cloth_linen", "fabric", "fabric_linen"), CT_MISC));
        
        simpleItems.add(register(r, "crop/product/hemp", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "crop_hemp", "hemp"), CT_MISC));
        simpleItems.add(register(r, "crop/product/hemp_fiber", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "fiber", "fiber_hemp"), CT_MISC));
        simpleItems.add(register(r, "crop/product/hemp_string", new ItemMiscTFCF(Size.SMALL, Weight.VERY_LIGHT, "string", "string_hemp"), CT_MISC));
        simpleItems.add(register(r, "crop/product/hemp_cloth", new ItemMiscTFCF(Size.SMALL, Weight.LIGHT, "cloth", "cloth_hemp", "fabric", "fabric_hemp"), CT_MISC));

        simpleItems.add(register(r, "crop/product/madder", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "crop_madder", "madder"), CT_MISC));
        simpleItems.add(register(r, "crop/product/weld", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "crop_weld", "weld"), CT_MISC));
        simpleItems.add(register(r, "crop/product/woad", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "crop_woad", "woad"), CT_MISC));
        simpleItems.add(register(r, "crop/product/indigo", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "crop_indigo", "indigo"), CT_MISC));
        simpleItems.add(register(r, "crop/product/rape", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "crop_rape", "rape"), CT_MISC));
        simpleItems.add(register(r, "crop/product/hops", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "crop_hops", "hops"), CT_MISC));

        simpleItems.add(register(r, "crop/product/silk_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk"), CT_MISC));
        simpleItems.add(register(r, "crop/product/sisal_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_sisal"), CT_MISC));
        simpleItems.add(register(r, "crop/product/cotton_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_cotton"), CT_MISC));
        simpleItems.add(register(r, "crop/product/linen_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_linen"), CT_MISC));
        simpleItems.add(register(r, "crop/product/papyrus_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus"), CT_MISC));
        simpleItems.add(register(r, "crop/product/hemp_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_hemp"), CT_MISC));

        simpleItems.add(register(r, "crop/product/olive_silk_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk_olive"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/olive_sisal_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_sisal_olive"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/olive_cotton_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_cotton_olive"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/olive_linen_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_linen_olive"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/olive_papyrus_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus_olive"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/olive_hemp_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_hemp_olive"), CT_FOOD));

        simpleItems.add(register(r, "crop/product/soybean_jute_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_jute_soybean"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/soybean_silk_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk_soybean"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/soybean_sisal_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_sisal_soybean"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/soybean_cotton_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_cotton_soybean"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/soybean_linen_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_linen_soybean"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/soybean_papyrus_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus_soybean"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/soybean_hemp_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_hemp_soybean"), CT_FOOD));

        simpleItems.add(register(r, "crop/product/linseed_jute_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_jute_linseed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/linseed_silk_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk_linseed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/linseed_sisal_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_sisal_linseed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/linseed_cotton_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_cotton_linseed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/linseed_linen_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_linen_linseed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/linseed_papyrus_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus_linseed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/linseed_hemp_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_hemp_linseed"), CT_FOOD));

        simpleItems.add(register(r, "crop/product/rape_seed_jute_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_jute_rape_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/rape_seed_silk_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk_rape_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/rape_seed_sisal_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_sisal_rape_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/rape_seed_cotton_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_cotton_rape_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/rape_seed_linen_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_linen_rape_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/rape_seed_papyrus_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus_rape_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/rape_seed_hemp_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_hemp_rape_seed"), CT_FOOD));

        simpleItems.add(register(r, "crop/product/sunflower_seed_jute_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_jute_sunflower_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sunflower_seed_silk_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk_sunflower_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sunflower_seed_sisal_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_sisal_sunflower_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sunflower_seed_cotton_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_cotton_sunflower_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sunflower_seed_linen_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_linen_sunflower_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sunflower_seed_papyrus_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus_sunflower_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sunflower_seed_hemp_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_hemp_sunflower_seed"), CT_FOOD));

        simpleItems.add(register(r, "crop/product/opium_poppy_seed_jute_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_jute_opium_poppy_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/opium_poppy_seed_silk_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk_opium_poppy_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/opium_poppy_seed_sisal_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_sisal_opium_poppy_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/opium_poppy_seed_cotton_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_cotton_opium_poppy_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/opium_poppy_seed_linen_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_linen_opium_poppy_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/opium_poppy_seed_papyrus_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus_opium_poppy_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/opium_poppy_seed_hemp_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_hemp_opium_poppy_seed"), CT_FOOD));

        simpleItems.add(register(r, "crop/product/sugar_beet_jute_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_jute_sugar_beet"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sugar_beet_silk_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk_sugar_beet"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sugar_beet_sisal_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_sisal_sugar_beet"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sugar_beet_cotton_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_cotton_sugar_beet"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sugar_beet_linen_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_linen_sugar_beet"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sugar_beet_papyrus_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus_sugar_beet"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sugar_beet_hemp_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_hemp_sugar_beet"), CT_FOOD));

        simpleItems.add(register(r, "crop/product/sugar_cane_jute_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_sisal_sugar_cane"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sugar_cane_silk_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk_sugar_cane"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sugar_cane_sisal_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_sisal_sugar_cane"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sugar_cane_cotton_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_cotton_sugar_cane"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sugar_cane_linen_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_linen_sugar_cane"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sugar_cane_papyrus_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus_sugar_cane"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sugar_cane_hemp_disc", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_hemp_sugar_cane"), CT_FOOD));

        simpleItems.add(register(r, "crop/product/silk_net", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_silk"), CT_MISC));
        simpleItems.add(register(r, "crop/product/sisal_net", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_sisal"), CT_MISC));
        simpleItems.add(register(r, "crop/product/cotton_net", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_cotton"), CT_MISC));
        simpleItems.add(register(r, "crop/product/linen_net", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_linen"), CT_MISC));
        simpleItems.add(register(r, "crop/product/papyrus_net", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_papyrus"), CT_MISC));
        simpleItems.add(register(r, "crop/product/hemp_net", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_hemp"), CT_MISC));

        simpleItems.add(register(r, "crop/product/dirty_silk_net", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_silk_dirty"), CT_MISC));
        simpleItems.add(register(r, "crop/product/dirty_sisal_net", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_sisal_dirty"), CT_MISC));
        simpleItems.add(register(r, "crop/product/dirty_cotton_net", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_cotton_dirty"), CT_MISC));
        simpleItems.add(register(r, "crop/product/dirty_linen_net", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_linen_dirty"), CT_MISC));
        simpleItems.add(register(r, "crop/product/dirty_papyrus_net", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_papyrus_dirty"), CT_MISC));
        simpleItems.add(register(r, "crop/product/dirty_hemp_net", new ItemMiscTFCF(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_hemp_dirty"), CT_MISC));

        // Cassia cinnamon
        ItemMisc cassiaPole = new ItemMisc(Size.SMALL, Weight.MEDIUM);
        simpleItems.add(register(r, "wood/fruit_tree/pole/cassia_cinnamon", cassiaPole, CT_WOOD));
        OreDictionary.registerOre("poleCassiaCinnamon", cassiaPole);
        OreDictionary.registerOre("poleWooden", cassiaPole);

        ItemMisc cassiaLumber = new ItemMisc(Size.SMALL, Weight.VERY_LIGHT);
        simpleItems.add(register(r, "wood/fruit_tree/lumber/cassia_cinnamon", cassiaLumber, CT_WOOD));
        OreDictionary.registerOre("lumberCassiaCinnamon", cassiaLumber);
        
        simpleItems.add(register(r, "wood/fruit_tree/boat/cassia_cinnamon", new ItemBoatTFC(DefaultTrees.CASSIA_CINNAMON_TREE), CT_WOOD));

        // Ceylon cinnamon
        ItemMisc ceylonPole = new ItemMisc(Size.SMALL, Weight.MEDIUM);
        simpleItems.add(register(r, "wood/fruit_tree/pole/ceylon_cinnamon", ceylonPole, CT_WOOD));
        OreDictionary.registerOre("poleCeylonCinnamon", ceylonPole);
        OreDictionary.registerOre("poleWooden", ceylonPole);

        ItemMisc ceylonLumber = new ItemMisc(Size.SMALL, Weight.VERY_LIGHT);
        simpleItems.add(register(r, "wood/fruit_tree/lumber/ceylon_cinnamon", ceylonLumber, CT_WOOD));
        OreDictionary.registerOre("lumberCeylonCinnamon", ceylonLumber);
        
        simpleItems.add(register(r, "wood/fruit_tree/boat/ceylon_cinnamon", new ItemBoatTFC(DefaultTrees.CEYLON_CINNAMON_TREE), CT_WOOD));

        for (int i = 0; i < BlocksTFCF.bamboo.length; i++)
        {
            ItemMisc bambooPole = new ItemMisc(Size.SMALL, Weight.MEDIUM);
            simpleItems.add(register(r, "wood/pole/" + BlocksTFCF.bamboo[i], bambooPole, CT_WOOD));
            OreDictionary.registerOre(OreDictionaryHelper.toString("pole_" + BlocksTFCF.bamboo[i]), bambooPole);
            ((BlockBambooLog) BlocksTFCF.getAllBambooLog().get(i)).setDrop(bambooPole);

            ItemMisc bambooLumber = new ItemMisc(Size.SMALL, Weight.VERY_LIGHT);
            simpleItems.add(register(r, "wood/lumber/" + BlocksTFCF.bamboo[i], bambooLumber, CT_WOOD));
            OreDictionary.registerOre(OreDictionaryHelper.toString("lumber_" + BlocksTFCF.bamboo[i]), bambooLumber);
            
            simpleItems.add(register(r, "wood/boat/" + BlocksTFCF.bamboo[i], new ItemFruitBoat(BlocksTFCF.bambooTrees[i]), CT_WOOD));
        }

        /*for (SeasonalTrees fruitTree : SeasonalTrees.values())
        {
            if (!fruitTree.isNormalTree)
            {
                // Poles
                String name = fruitTree.getName().toLowerCase();
                ItemMisc pole = new ItemMisc(Size.SMALL, Weight.MEDIUM);
                simpleItems.add(register(r, "wood/fruit_tree/pole/" + name, pole, CT_WOOD));
                OreDictionary.registerOre(OreDictionaryHelper.toString("pole_" + name.substring(0,1).toLowerCase() + name.substring(1).toLowerCase()), pole);

                // Lumber
                ItemMisc lumber = new ItemMisc(Size.SMALL, Weight.VERY_LIGHT);
                simpleItems.add(register(r, "wood/fruit_tree/lumber/" + name, lumber, CT_WOOD));
                OreDictionary.registerOre(OreDictionaryHelper.toString("lumber_" + name.substring(0,1).toLowerCase() + name.substring(1).toLowerCase()), lumber);

                simpleItems.add(register(r, "wood/fruit_tree/boat/" + name, new ItemBoatTFCF(fruitTree), CT_WOOD));
            }
        }*/

        for (IFruitTree fruitTree : FruitTree.values())
        {
            String name = fruitTree.getName().toLowerCase();

            // Poles
            if (!TFCFlorae.FirmaLifeAdded)
            {
                ItemMisc pole = new ItemMisc(Size.SMALL, Weight.MEDIUM);
                simpleItems.add(register(r, "wood/fruit_tree/pole/" + name, pole, CT_WOOD));
                OreDictionary.registerOre(OreDictionaryHelper.toString("pole_" + name.substring(0,1).toLowerCase() + name.substring(1).toLowerCase()), pole);
            }

            // Lumber
            ItemMisc lumber = new ItemMisc(Size.SMALL, Weight.VERY_LIGHT);
            simpleItems.add(register(r, "wood/fruit_tree/lumber/" + name, lumber, CT_WOOD));
            OreDictionary.registerOre(OreDictionaryHelper.toString("lumber_" + name.substring(0,1).toLowerCase() + name.substring(1).toLowerCase()), lumber);

            simpleItems.add(register(r, "wood/fruit_tree/boat/" + name, new ItemFruitBoat(fruitTree), CT_WOOD));
        }

        for (BlockFruitDoor blockDoor : BlocksTFCF.getAllFruitDoors())
        {
            ItemFruitDoor itemDoor = new ItemFruitDoor(blockDoor);
            fruitDoors.add(register(r, blockDoor.getRegistryName().getPath(), itemDoor, CT_DECORATIONS));
            OreDictionary.registerOre(OreDictionaryHelper.toString("door_wood"), itemDoor);
            OreDictionary.registerOre(OreDictionaryHelper.toString("door_wood_" + blockDoor.Name), itemDoor);
        }

        for (BlockFruitSlab.Half slab : BlocksTFCF.getAllFruitSlabBlocks())
            simpleItems.add(register(r, slab.getRegistryName().getPath(), new ItemSlabTFC(slab, slab, slab.doubleSlab), CT_DECORATIONS));

        for (BlockRockSlabTFC.Half slab : BlocksTFCF.getAllSlabBlocksTFC())
                simpleItems.add(register(r, slab.getRegistryName().getPath(), new ItemSlabTFC(slab, slab, slab.doubleSlab), CT_DECORATIONS));


        allFruitDoors = fruitDoors.build();

        BlocksTFCF.getAllNormalItemBlocks().forEach((x) -> {
            registerItemBlock(r, x);
        });

        allSimpleItems = simpleItems.build();
        //allItemBows = itemBows.build();

        if (ConfigTFCF.General.WORLD.enableAllEarthenwareClay || ConfigTFCF.General.WORLD.enableAllKaoliniteClay || ConfigTFCF.General.WORLD.enableAllStonewareClay)
        {
            allCeramicMoldItems = ceramicItems.build();
        }

        OreDictionaryHelper.init();
    }

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerVanillaOverrides(RegistryEvent.Register<Item> event)
    {
        // Vanilla Overrides. Used for small tweaks on vanilla items, rather than replacing them outright
        TFCFlorae.getLog().info("The below warnings about unintended overrides are normal. The overrides are intended - deal with it. ;)");
        event.getRegistry().registerAll(
            new ItemFlint(Size.VERY_SMALL, Weight.VERY_LIGHT).setRegistryName(Items.FLINT.getRegistryName()).setTranslationKey("flint"),
            new ItemBlockStickBundle(BlocksTFC.STICK_BUNDLE).setRegistryName(MOD_ID, "stick_bundle")
        );
    }

    private static void registerPottery(Builder<Item> items, IForgeRegistry<Item> r, String nameUnfired, String nameFired)
    {
        registerPottery(items, r, nameUnfired, nameFired, new ItemPottery(), new ItemPottery());
    }

    private static void registerPottery(Builder<Item> items, IForgeRegistry<Item> r, String nameUnfired, String nameFired, ItemPottery unfiredItem, ItemPottery firedItem)
    {
        register(r, nameFired, firedItem, CT_POTTERY);
        register(r, nameUnfired, unfiredItem, CT_POTTERY);

        if (items != null)
        {
            items.add(firedItem, unfiredItem);
        }
    }

    private static <T extends Item> T register(IForgeRegistry<Item> r, String name, T item, CreativeTabs ct)
    {
        item.setRegistryName(TFCFLORAE_MODID, name);
        item.setTranslationKey(TFCFLORAE_MODID + "." + name.replace('/', '.'));
        item.setCreativeTab(ct);
        r.register(item);
        return item;
    }
    
    @SuppressWarnings("ConstantConditions")
    private static void registerItemBlock(IForgeRegistry<Item> r, ItemBlock item)
    {
        item.setRegistryName(item.getBlock().getRegistryName());
        item.setCreativeTab(item.getBlock().getCreativeTab());
        r.register(item);
    }
}
