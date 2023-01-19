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
import net.dries007.tfc.objects.blocks.stone.BlockRockSlab;
import net.dries007.tfc.objects.items.ItemSlabTFC;
import net.dries007.tfc.objects.items.ceramics.*;
import net.dries007.tfc.objects.items.itemblock.ItemBlockTFC;
import net.dries007.tfc.util.agriculture.FruitTree;
import net.dries007.tfc.util.Helpers;

import tfcflorae.objects.blocks.BlocksTFCF;
import net.dries007.tfc.objects.blocks.wood.fruitwood.*;
import net.dries007.tfc.objects.blocks.wood.fruitwood.BlockFruitLog;
import net.dries007.tfc.objects.blocks.wood.bamboo.BlockBambooLog;
import net.dries007.tfc.objects.items.itemblock.ItemBlockStickBundle;
import net.dries007.tfc.objects.items.tools.ItemWalkingStick;
import net.dries007.tfc.util.OreDictionaryHelper;
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
    // Miscellaneous Food Stuff



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



    @GameRegistry.ObjectHolder("logwood_chips")
    public static final ItemMisc LOGWOOD_CHIPS = Helpers.getNull();
    //@GameRegistry.ObjectHolder("resin")
    //public static final ItemMisc RESIN = Helpers.getNull();
    @GameRegistry.ObjectHolder("conch")
    public static final ItemMisc CONCH = Helpers.getNull();
    @GameRegistry.ObjectHolder("clam")
    public static final ItemMisc CLAM = Helpers.getNull();
    @GameRegistry.ObjectHolder("live_clam")
    public static final ItemMisc LIVE_CLAM = Helpers.getNull();
    @GameRegistry.ObjectHolder("scallop")
    public static final ItemMisc SCALLOP = Helpers.getNull();
    @GameRegistry.ObjectHolder("live_scallop")
    public static final ItemMisc LIVE_SCALLOP = Helpers.getNull();
    @GameRegistry.ObjectHolder("pearl")
    public static final ItemMisc PEARL = Helpers.getNull();
    @GameRegistry.ObjectHolder("black_pearl")
    public static final ItemMisc BLACK_PEARL = Helpers.getNull();
    @GameRegistry.ObjectHolder("live_starfish")
    public static final ItemMisc LIVE_STARFISH = Helpers.getNull();
    @GameRegistry.ObjectHolder("animal/product/silk_moth_egg")
    public static final ItemMisc SILK_MOTH_EGG = Helpers.getNull();
    @GameRegistry.ObjectHolder("animal/product/silk_worm_hatchery")
    public static final ItemMisc SILK_WORM_HATCHERY = Helpers.getNull();
    @GameRegistry.ObjectHolder("animal/product/silk_worm")
    public static final ItemMisc SILK_WORM = Helpers.getNull();
    @GameRegistry.ObjectHolder("animal/product/silk_worm_cocoon")
    public static final ItemMisc SILK_WORM_COCOON = Helpers.getNull();
    @GameRegistry.ObjectHolder("animal/product/silk_worm_cocoon_boiled")
    public static final ItemMisc SILK_WORM_COCOON_BOILED = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/mulberry_leaf")
    public static final ItemMisc MULBERRY_LEAF = Helpers.getNull();

    @GameRegistry.ObjectHolder("crop/product/papyrus_pulp")
    public static final ItemMisc PAPYRUS_PULP = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/papyrus_fiber")
    public static final ItemMisc PAPYRUS_FIBER = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/papyrus_paper")
    public static final ItemMisc PAPYRUS_PAPER = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/agave")
    public static final ItemMisc AGAVE = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sisal_fiber")
    public static final ItemMisc SISAL_FIBER = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sisal_string")
    public static final ItemMisc SISAL_STRING = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/sisal_cloth")
    public static final ItemMisc SISAL_CLOTH = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/cotton_boll")
    public static final ItemMisc COTTON_BOLL = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/cotton_yarn")
    public static final ItemMisc COTTON_YARN = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/cotton_cloth")
    public static final ItemMisc COTTON_CLOTH = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/flax")
    public static final ItemMisc FLAX = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/flax_fiber")
    public static final ItemMisc FLAX_FIBER = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/linen_string")
    public static final ItemMisc LINEN_STRING = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/linen_cloth")
    public static final ItemMisc LINEN_CLOTH = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/hemp")
    public static final ItemMisc HEMP = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/hemp_fiber")
    public static final ItemMisc HEMP_FIBER = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/hemp_string")
    public static final ItemMisc HEMP_STRING = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/hemp_cloth")
    public static final ItemMisc HEMP_CLOTH = Helpers.getNull();

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
    public static final ItemMisc INDIGO = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/madder")
    public static final ItemMisc MADDER = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/weld")
    public static final ItemMisc WELD = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/woad")
    public static final ItemMisc WOAD = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/hops")
    public static final ItemMisc HOPS = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/rape")
    public static final ItemMisc RAPE = Helpers.getNull();

    @GameRegistry.ObjectHolder("cellulose_fibers")
    public static final ItemMisc CELLULOSE_FIBERS = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/yucca_fiber")
    public static final ItemMisc YUCCA_FIBER = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/yucca_string")
    public static final ItemMisc YUCCA_STRING = Helpers.getNull();
    @GameRegistry.ObjectHolder("crop/product/yucca_canvas")
    public static final ItemMisc YUCCA_CANVAS = Helpers.getNull();

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
    public static final ItemMisc WOODEN_BUCKET_SALT = Helpers.getNull();
    @GameRegistry.ObjectHolder("wooden_bucket_sugar")
    public static final ItemMisc WOODEN_BUCKET_SUGAR = Helpers.getNull();

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

        simpleItems.add(register(r, "pomace", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "pomace", "category_fruit"), CT_MISC));
        simpleItems.add(register(r, "yeast", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "yeast"), CT_MISC));

        simpleItems.add(register(r, "firma_cola_mix", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "mix_firma_cola"), CT_MISC));
        simpleItems.add(register(r, "firma_cola_oils", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "oils_firma_cola"), CT_MISC));
        simpleItems.add(register(r, "firma_cola_blend", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "blend_firma_cola"), CT_MISC));




        for (BlockFruitLog log : BlocksTFCF.getAllNormalTreeLog())
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


        // Items
        simpleItems.add(register(r, "wooden_bucket_salt", new ItemMisc(Size.LARGE, Weight.MEDIUM, "bucket_salt", "bucket_wooden_salt"), CT_MISC));
        simpleItems.add(register(r, "wooden_bucket_sugar", new ItemMisc(Size.LARGE, Weight.MEDIUM, "bucket_sugar", "bucket_wooden_sugar"), CT_MISC));
        simpleItems.add(register(r, "animal/product/silk_moth_egg", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "egg_silk_moth", "egg"), CT_MISC));
        simpleItems.add(register(r, "animal/product/silk_worm_hatchery", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "hatchery", "hatchery_silk_worm"), CT_MISC));
        simpleItems.add(register(r, "animal/product/silk_worm", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "silk_worm"), CT_MISC));
        simpleItems.add(register(r, "animal/product/silk_worm_cocoon", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "cocoon", "cocoon_silk_worm"), CT_MISC));
        simpleItems.add(register(r, "animal/product/silk_worm_cocoon_boiled", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "cocoon_boiled", "cocoon_silk_worm_boiled"), CT_MISC));
        simpleItems.add(register(r, "crop/product/mulberry_leaf", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "leaf", "leaf_mulberry", "leaves", "leaves_mulberry"), CT_MISC));
        simpleItems.add(register(r, "logwood_chips", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "chips_logwood", "dust_logwood", "powder_logwood"), CT_MISC));
        //simpleItems.add(register(r, "resin", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "resin", "glue"), CT_MISC));
//        simpleItems.add(register(r, "charred_bones", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "bone_charred"), CT_MISC));
        simpleItems.add(register(r, "conch", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "conch", "seashell"), CT_MISC));
        simpleItems.add(register(r, "clam", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "clam", "seashell"), CT_MISC));
        simpleItems.add(register(r, "live_clam", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "clam", "clam_live"), CT_MISC));
        simpleItems.add(register(r, "scallop", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "scallop", "seashell"), CT_MISC));
        simpleItems.add(register(r, "live_scallop", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "scallop", "scallop_live"), CT_MISC));
        simpleItems.add(register(r, "live_starfish", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "starfish", "starfish_live"), CT_MISC));
        simpleItems.add(register(r, "pearl", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "pearl"), CT_MISC));
        simpleItems.add(register(r, "black_pearl", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "pearl_black"), CT_MISC));

        simpleItems.add(register(r, "cellulose_fibers", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "fiber", "fiber_cellulose"), CT_MISC));

        simpleItems.add(register(r, "crop/product/yucca_fiber", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "fiber", "fiber_yucca"), CT_MISC));
        simpleItems.add(register(r, "crop/product/yucca_string", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "string", "string_yucca"), CT_MISC));
        simpleItems.add(register(r, "crop/product/yucca_canvas", new ItemMisc(Size.VERY_SMALL, Weight.LIGHT, "cloth", "cloth_yucca", "fabric", "fabric_yucca", "canvas", "canvas_yucca"), CT_MISC));

        simpleItems.add(register(r, "crop/product/papyrus_pulp", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "pulp", "pulp_papyrus"), CT_MISC));
        simpleItems.add(register(r, "crop/product/papyrus_fiber", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "fiber", "fiber_papyrus"), CT_MISC));
        simpleItems.add(register(r, "crop/product/papyrus_paper", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "paper", "paper_papyrus"), CT_MISC));
        
        simpleItems.add(register(r, "crop/product/agave", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "crop_agave", "agave"), CT_MISC));
        simpleItems.add(register(r, "crop/product/sisal_fiber", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "fiber", "fiber_sisal"), CT_MISC));
        simpleItems.add(register(r, "crop/product/sisal_string", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "string", "string_sisal"), CT_MISC));
        simpleItems.add(register(r, "crop/product/sisal_cloth", new ItemMisc(Size.SMALL, Weight.LIGHT, "cloth", "cloth_sisal", "fabric", "fabric_sisal"), CT_MISC));
        
        simpleItems.add(register(r, "crop/product/cotton_boll", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "crop_cotton", "cotton"), CT_MISC));
        simpleItems.add(register(r, "crop/product/cotton_yarn", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "string", "string_cotton", "yarn", "yarn_cotton"), CT_MISC));
        simpleItems.add(register(r, "crop/product/cotton_cloth", new ItemMisc(Size.SMALL, Weight.LIGHT, "cloth", "cloth_high_quality", "cloth_cotton"), CT_MISC));
        
        simpleItems.add(register(r, "crop/product/flax", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "crop_flax", "flax"), CT_MISC));
        simpleItems.add(register(r, "crop/product/flax_fiber", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "fiber", "fiber_flax"), CT_MISC));
        simpleItems.add(register(r, "crop/product/linen_string", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "string", "string_linen"), CT_MISC));
        simpleItems.add(register(r, "crop/product/linen_cloth", new ItemMisc(Size.SMALL, Weight.LIGHT, "cloth", "cloth_linen", "fabric", "fabric_linen"), CT_MISC));
        
        simpleItems.add(register(r, "crop/product/hemp", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "crop_hemp", "hemp"), CT_MISC));
        simpleItems.add(register(r, "crop/product/hemp_fiber", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "fiber", "fiber_hemp"), CT_MISC));
        simpleItems.add(register(r, "crop/product/hemp_string", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "string", "string_hemp"), CT_MISC));
        simpleItems.add(register(r, "crop/product/hemp_cloth", new ItemMisc(Size.SMALL, Weight.LIGHT, "cloth", "cloth_hemp", "fabric", "fabric_hemp"), CT_MISC));

        simpleItems.add(register(r, "crop/product/madder", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "crop_madder", "madder"), CT_MISC));
        simpleItems.add(register(r, "crop/product/weld", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "crop_weld", "weld"), CT_MISC));
        simpleItems.add(register(r, "crop/product/woad", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "crop_woad", "woad"), CT_MISC));
        simpleItems.add(register(r, "crop/product/indigo", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "crop_indigo", "indigo"), CT_MISC));
        simpleItems.add(register(r, "crop/product/rape", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "crop_rape", "rape"), CT_MISC));
        simpleItems.add(register(r, "crop/product/hops", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "crop_hops", "hops"), CT_MISC));

        simpleItems.add(register(r, "crop/product/silk_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk"), CT_MISC));
        simpleItems.add(register(r, "crop/product/sisal_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_sisal"), CT_MISC));
        simpleItems.add(register(r, "crop/product/cotton_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_cotton"), CT_MISC));
        simpleItems.add(register(r, "crop/product/linen_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_linen"), CT_MISC));
        simpleItems.add(register(r, "crop/product/papyrus_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus"), CT_MISC));
        simpleItems.add(register(r, "crop/product/hemp_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_hemp"), CT_MISC));

        simpleItems.add(register(r, "crop/product/olive_silk_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk_olive"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/olive_sisal_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_sisal_olive"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/olive_cotton_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_cotton_olive"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/olive_linen_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_linen_olive"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/olive_papyrus_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus_olive"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/olive_hemp_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_hemp_olive"), CT_FOOD));

        simpleItems.add(register(r, "crop/product/soybean_jute_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_jute_soybean"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/soybean_silk_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk_soybean"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/soybean_sisal_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_sisal_soybean"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/soybean_cotton_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_cotton_soybean"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/soybean_linen_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_linen_soybean"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/soybean_papyrus_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus_soybean"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/soybean_hemp_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_hemp_soybean"), CT_FOOD));

        simpleItems.add(register(r, "crop/product/linseed_jute_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_jute_linseed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/linseed_silk_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk_linseed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/linseed_sisal_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_sisal_linseed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/linseed_cotton_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_cotton_linseed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/linseed_linen_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_linen_linseed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/linseed_papyrus_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus_linseed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/linseed_hemp_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_hemp_linseed"), CT_FOOD));

        simpleItems.add(register(r, "crop/product/rape_seed_jute_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_jute_rape_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/rape_seed_silk_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk_rape_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/rape_seed_sisal_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_sisal_rape_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/rape_seed_cotton_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_cotton_rape_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/rape_seed_linen_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_linen_rape_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/rape_seed_papyrus_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus_rape_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/rape_seed_hemp_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_hemp_rape_seed"), CT_FOOD));

        simpleItems.add(register(r, "crop/product/sunflower_seed_jute_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_jute_sunflower_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sunflower_seed_silk_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk_sunflower_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sunflower_seed_sisal_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_sisal_sunflower_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sunflower_seed_cotton_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_cotton_sunflower_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sunflower_seed_linen_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_linen_sunflower_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sunflower_seed_papyrus_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus_sunflower_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sunflower_seed_hemp_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_hemp_sunflower_seed"), CT_FOOD));

        simpleItems.add(register(r, "crop/product/opium_poppy_seed_jute_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_jute_opium_poppy_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/opium_poppy_seed_silk_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk_opium_poppy_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/opium_poppy_seed_sisal_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_sisal_opium_poppy_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/opium_poppy_seed_cotton_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_cotton_opium_poppy_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/opium_poppy_seed_linen_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_linen_opium_poppy_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/opium_poppy_seed_papyrus_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus_opium_poppy_seed"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/opium_poppy_seed_hemp_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_hemp_opium_poppy_seed"), CT_FOOD));

        simpleItems.add(register(r, "crop/product/sugar_beet_jute_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_jute_sugar_beet"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sugar_beet_silk_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk_sugar_beet"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sugar_beet_sisal_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_sisal_sugar_beet"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sugar_beet_cotton_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_cotton_sugar_beet"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sugar_beet_linen_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_linen_sugar_beet"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sugar_beet_papyrus_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus_sugar_beet"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sugar_beet_hemp_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_hemp_sugar_beet"), CT_FOOD));

        simpleItems.add(register(r, "crop/product/sugar_cane_jute_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_sisal_sugar_cane"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sugar_cane_silk_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk_sugar_cane"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sugar_cane_sisal_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_sisal_sugar_cane"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sugar_cane_cotton_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_cotton_sugar_cane"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sugar_cane_linen_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_linen_sugar_cane"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sugar_cane_papyrus_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus_sugar_cane"), CT_FOOD));
        simpleItems.add(register(r, "crop/product/sugar_cane_hemp_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_hemp_sugar_cane"), CT_FOOD));

        simpleItems.add(register(r, "crop/product/silk_net", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_silk"), CT_MISC));
        simpleItems.add(register(r, "crop/product/sisal_net", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_sisal"), CT_MISC));
        simpleItems.add(register(r, "crop/product/cotton_net", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_cotton"), CT_MISC));
        simpleItems.add(register(r, "crop/product/linen_net", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_linen"), CT_MISC));
        simpleItems.add(register(r, "crop/product/papyrus_net", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_papyrus"), CT_MISC));
        simpleItems.add(register(r, "crop/product/hemp_net", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_hemp"), CT_MISC));

        simpleItems.add(register(r, "crop/product/dirty_silk_net", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_silk_dirty"), CT_MISC));
        simpleItems.add(register(r, "crop/product/dirty_sisal_net", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_sisal_dirty"), CT_MISC));
        simpleItems.add(register(r, "crop/product/dirty_cotton_net", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_cotton_dirty"), CT_MISC));
        simpleItems.add(register(r, "crop/product/dirty_linen_net", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_linen_dirty"), CT_MISC));
        simpleItems.add(register(r, "crop/product/dirty_papyrus_net", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_papyrus_dirty"), CT_MISC));
        simpleItems.add(register(r, "crop/product/dirty_hemp_net", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_hemp_dirty"), CT_MISC));

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

        for (BlockRockSlab.Half slab : BlocksTFCF.getAllSlabBlocksTFC())
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
