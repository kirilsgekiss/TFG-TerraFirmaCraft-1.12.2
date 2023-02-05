/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.items;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.Material;
import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.IFruitTree;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.types.Wood;
import net.dries007.tfc.compat.gregtech.materials.TFCMaterialFlags;
import net.dries007.tfc.compat.tfc.TFCOrePrefixExtended;
import net.dries007.tfc.compat.tfc.TFGUtils;
import net.dries007.tfc.objects.ArmorMaterialTFC;
import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.objects.blocks.rock.TFCBlockRockSlab;
import net.dries007.tfc.objects.blocks.wood.TFCBlockWoodDoor;
import net.dries007.tfc.objects.blocks.wood.TFCBlockWoodSlab;
import net.dries007.tfc.objects.blocks.wood.tree.TFCBlockLog;
import net.dries007.tfc.objects.items.ceramics.*;
import net.dries007.tfc.objects.items.ceramics.fired.ItemJug;
import net.dries007.tfc.objects.items.ceramics.fired.ItemSmallVessel;
import net.dries007.tfc.objects.items.ceramics.fired.molds.ItemClayMold;
import net.dries007.tfc.objects.items.ceramics.fired.molds.ItemEarthenwareMold;
import net.dries007.tfc.objects.items.ceramics.fired.molds.ItemKaoliniteMold;
import net.dries007.tfc.objects.items.ceramics.fired.molds.ItemStonewareMold;
import net.dries007.tfc.objects.items.ceramics.unfired.ItemUnfiredLargeVessel;
import net.dries007.tfc.objects.items.ceramics.unfired.ItemUnfiredSmallVessel;
import net.dries007.tfc.objects.items.ceramics.unfired.molds.ItemUnfiredClayMold;
import net.dries007.tfc.objects.items.ceramics.unfired.molds.ItemUnfiredEarthenwareMold;
import net.dries007.tfc.objects.items.ceramics.unfired.molds.ItemUnfiredKaoliniteMold;
import net.dries007.tfc.objects.items.ceramics.unfired.molds.ItemUnfiredStonewareMold;
import net.dries007.tfc.objects.items.devices.ItemCalendarClock;
import net.dries007.tfc.objects.items.devices.ItemFloraDensity;
import net.dries007.tfc.objects.items.food.ItemDynamicBowlFood;
import net.dries007.tfc.objects.items.food.ItemSandwich;
import net.dries007.tfc.objects.items.food.TFCItemFood;
import net.dries007.tfc.objects.items.itemblock.ItemBlockTorch;
import net.dries007.tfc.objects.items.itemblock.TFCItemBlock;
import net.dries007.tfc.objects.items.metal.ItemAnvil;
import net.dries007.tfc.objects.items.metal.ItemCladding;
import net.dries007.tfc.objects.items.metal.ItemLamp;
import net.dries007.tfc.objects.items.metal.ItemMetalTrapdoor;
import net.dries007.tfc.objects.items.rock.*;
import net.dries007.tfc.objects.items.tools.ItemWalkingStick;
import net.dries007.tfc.objects.items.wood.TFCItemBoat;
import net.dries007.tfc.objects.items.wood.TFCItemDoor;
import net.dries007.tfc.objects.items.wood.TFCItemLumber;
import net.dries007.tfc.objects.items.wood.TFCItemWoodenBucket;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.dries007.tfc.util.agriculture.Crop;
import net.dries007.tfc.util.agriculture.Food;
import net.dries007.tfc.util.agriculture.FruitTree;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSnow;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;
import static net.dries007.tfc.objects.CreativeTabsTFC.*;
import static net.dries007.tfc.util.Helpers.getNull;

@Mod.EventBusSubscriber(modid = MOD_ID)
@GameRegistry.ObjectHolder(MOD_ID)
public final class TFCItems {

    //=== Miscellaneous ==============================================================================================//

    public static final ItemGoldPan GOLDPAN = getNull();


    public static final Item WROUGHT_IRON_GRILL = getNull();
    public static final Item GLUE = getNull();
// !   public static final Item BRASS_MECHANISMS = getNull();
// !   public static final Item SALT = getNull();
// !   public static final Item MINERAL_LICK = getNull();


    //=== Grinding/Crushing Tools ====================================================================================//

    public static final Item HANDSTONE = getNull();


    //=== Crop Product ===============================================================================================//

    @GameRegistry.ObjectHolder("crop/product/jute")
    public static final ItemMisc JUTE = getNull();
    @GameRegistry.ObjectHolder("crop/product/sisal")
    public static final ItemMisc SISAL = getNull();
    @GameRegistry.ObjectHolder("crop/product/linen")
    public static final ItemMisc LINEN = getNull();
    @GameRegistry.ObjectHolder("crop/product/hemp")
    public static final ItemMisc HEMP = getNull();
    @GameRegistry.ObjectHolder("crop/product/cotton")
    public static final ItemMisc COTTON = getNull();
    @GameRegistry.ObjectHolder("crop/product/yucca")
    public static final ItemMisc YUCCA = getNull();
    @GameRegistry.ObjectHolder("crop/product/papyrus")
    public static final ItemMisc PAPYRUS = getNull();
    @GameRegistry.ObjectHolder("crop/product/silk")
    public static final ItemMisc SILK = getNull();

    //=== Crop fiber =================================================================================================//

    @GameRegistry.ObjectHolder("crop/product/jute_fiber")
    public static final ItemMisc JUTE_FIBER = getNull();
    @GameRegistry.ObjectHolder("crop/product/sisal_fiber")
    public static final ItemMisc SISAL_FIBER = getNull();
    @GameRegistry.ObjectHolder("crop/product/linen_fiber")
    public static final ItemMisc LINEN_FIBER = getNull();
    @GameRegistry.ObjectHolder("crop/product/hemp_fiber")
    public static final ItemMisc HEMP_FIBER = getNull();
    @GameRegistry.ObjectHolder("crop/product/cotton_fiber")
    public static final ItemMisc COTTON_FIBER = getNull();
    @GameRegistry.ObjectHolder("crop/product/yucca_fiber")
    public static final ItemMisc YUCCA_FIBER = getNull();
    @GameRegistry.ObjectHolder("crop/product/papyrus_fiber")
    public static final ItemMisc PAPYRUS_FIBER = getNull();
    @GameRegistry.ObjectHolder("crop/product/silk_fiber")
    public static final ItemMisc SILK_FIBER = getNull();

    //=== Crop string ================================================================================================//

    @GameRegistry.ObjectHolder("crop/product/jute_string")
    public static final ItemMisc JUTE_STRING = getNull();
    @GameRegistry.ObjectHolder("crop/product/sisal_string")
    public static final ItemMisc SISAL_STRING = getNull();
    @GameRegistry.ObjectHolder("crop/product/linen_string")
    public static final ItemMisc LINEN_STRING = getNull();
    @GameRegistry.ObjectHolder("crop/product/hemp_string")
    public static final ItemMisc HEMP_STRING = getNull();
    @GameRegistry.ObjectHolder("crop/product/cotton_string")
    public static final ItemMisc COTTON_STRING = getNull();
    @GameRegistry.ObjectHolder("crop/product/yucca_string")
    public static final ItemMisc YUCCA_STRING = getNull();

    //=== Crop cloth =================================================================================================//

    @GameRegistry.ObjectHolder("crop/product/jute_cloth")
    public static final ItemMisc JUTE_CLOTH = getNull();
    @GameRegistry.ObjectHolder("crop/product/sisal_cloth")
    public static final ItemMisc SISAL_CLOTH = getNull();
    @GameRegistry.ObjectHolder("crop/product/linen_cloth")
    public static final ItemMisc LINEN_CLOTH = getNull();
    @GameRegistry.ObjectHolder("crop/product/hemp_cloth")
    public static final ItemMisc HEMP_CLOTH = getNull();
    @GameRegistry.ObjectHolder("crop/product/cotton_cloth")
    public static final ItemMisc COTTON_CLOTH = getNull();
    @GameRegistry.ObjectHolder("crop/product/yucca_canvas")
    public static final ItemMisc YUCCA_CANVAS = getNull();
    @GameRegistry.ObjectHolder("animal/product/wool_cloth")
    public static final ItemMisc WOOL_CLOTH = getNull();
    @GameRegistry.ObjectHolder("crop/product/papyrus_paper")
    public static final ItemMisc PAPYRUS_PAPER = getNull();
    @GameRegistry.ObjectHolder("animal/product/silk_cloth")
    public static final ItemMisc SILK_CLOTH = getNull();

    @GameRegistry.ObjectHolder("crop/product/papyrus_pulp")
    public static final ItemMisc PAPYRUS_PULP = getNull();

    //=== Crop disc ==================================================================================================//

    @GameRegistry.ObjectHolder("crop/product/jute_disc")
    public static final ItemMisc JUTE_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/sisal_disc")
    public static final ItemMisc SISAL_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/linen_disc")
    public static final ItemMisc LINEN_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/hemp_disc")
    public static final ItemMisc HEMP_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/cotton_disc")
    public static final ItemMisc COTTON_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/yucca_disc")
    public static final ItemMisc YUCCA_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/papyrus_disc")
    public static final ItemMisc PAPYRUS_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/silk_disc")
    public static final ItemMisc SILK_DISC = getNull();

    //=== Crop olive disc ============================================================================================//

    @GameRegistry.ObjectHolder("crop/product/olive_jute_disc")
    public static final ItemMisc OLIVE_JUTE_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/olive_sisal_disc")
    public static final ItemMisc OLIVE_SISAL_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/olive_linen_disc")
    public static final ItemMisc OLIVE_LINEN_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/olive_hemp_disc")
    public static final ItemMisc OLIVE_HEMP_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/olive_cotton_disc")
    public static final ItemMisc OLIVE_COTTON_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/olive_yucca_disc")
    public static final ItemMisc OLIVE_YUCCA_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/olive_papyrus_disc")
    public static final ItemMisc OLIVE_PAPYRUS_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/olive_silk_disc")
    public static final ItemMisc OLIVE_SILK_DISC = getNull();

    //=== Crop soybean disc ==========================================================================================//

    @GameRegistry.ObjectHolder("crop/product/soybean_jute_disc")
    public static final ItemMisc SOYBEAN_JUTE_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/soybean_sisal_disc")
    public static final ItemMisc SOYBEAN_SISAL_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/soybean_linen_disc")
    public static final ItemMisc SOYBEAN_LINEN_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/soybean_hemp_disc")
    public static final ItemMisc SOYBEAN_HEMP_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/soybean_cotton_disc")
    public static final ItemMisc SOYBEAN_COTTON_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/soybean_yucca_disc")
    public static final ItemMisc SOYBEAN_YUCCA_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/soybean_papyrus_disc")
    public static final ItemMisc SOYBEAN_PAPYRUS_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/soybean_silk_disc")
    public static final ItemMisc SOYBEAN_SILK_DISC = getNull();

    //=== Crop linseed disc ==========================================================================================//

    @GameRegistry.ObjectHolder("crop/product/linseed_jute_disc")
    public static final ItemMisc LINSEED_JUTE_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/linseed_sisal_disc")
    public static final ItemMisc LINSEED_SISAL_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/linseed_linen_disc")
    public static final ItemMisc LINSEED_LINEN_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/linseed_hemp_disc")
    public static final ItemMisc LINSEED_HEMP_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/linseed_cotton_disc")
    public static final ItemMisc LINSEED_COTTON_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/linseed_yucca_disc")
    public static final ItemMisc LINSEED_YUCCA_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/linseed_papyrus_disc")
    public static final ItemMisc LINSEED_PAPYRUS_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/linseed_silk_disc")
    public static final ItemMisc LINSEED_SILK_DISC = getNull();

    //=== Crop rape seed disc ========================================================================================//

    @GameRegistry.ObjectHolder("crop/product/rape_seed_jute_disc")
    public static final ItemMisc RAPE_SEED_JUTE_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/rape_seed_sisal_disc")
    public static final ItemMisc RAPE_SEED_SISAL_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/rape_seed_linen_disc")
    public static final ItemMisc RAPE_SEED_LINEN_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/rape_seed_hemp_disc")
    public static final ItemMisc RAPE_SEED_HEMP_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/rape_seed_cotton_disc")
    public static final ItemMisc RAPE_SEED_COTTON_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/rape_seed_yucca_disc")
    public static final ItemMisc RAPE_SEED_YUCCA_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/rape_seed_papyrus_disc")
    public static final ItemMisc RAPE_SEED_PAPYRUS_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/rape_seed_silk_disc")
    public static final ItemMisc RAPE_SEED_SILK_DISC = getNull();

    //=== Crop sunflower seed disc ===================================================================================//

    @GameRegistry.ObjectHolder("crop/product/sunflower_seed_jute_disc")
    public static final ItemMisc SUNFLOWER_SEED_JUTE_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/sunflower_seed_sisal_disc")
    public static final ItemMisc SUNFLOWER_SEED_SISAL_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/sunflower_seed_linen_disc")
    public static final ItemMisc SUNFLOWER_SEED_LINEN_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/sunflower_seed_hemp_disc")
    public static final ItemMisc SUNFLOWER_SEED_HEMP_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/sunflower_seed_cotton_disc")
    public static final ItemMisc SUNFLOWER_SEED_COTTON_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/sunflower_seed_yucca_disc")
    public static final ItemMisc SUNFLOWER_SEED_YUCCA_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/sunflower_seed_papyrus_disc")
    public static final ItemMisc SUNFLOWER_SEED_PAPYRUS_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/sunflower_seed_silk_disc")
    public static final ItemMisc SUNFLOWER_SEED_SILK_DISC = getNull();

    //=== Crop opium poppy seed disc =================================================================================//

    @GameRegistry.ObjectHolder("crop/product/opium_poppy_seed_jute_disc")
    public static final ItemMisc OPIUM_POPPY_SEED_JUTE_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/opium_poppy_seed_sisal_disc")
    public static final ItemMisc OPIUM_POPPY_SEED_SISAL_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/opium_poppy_seed_linen_disc")
    public static final ItemMisc OPIUM_POPPY_SEED_LINEN_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/opium_poppy_seed_hemp_disc")
    public static final ItemMisc OPIUM_POPPY_SEED_HEMP_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/opium_poppy_seed_cotton_disc")
    public static final ItemMisc OPIUM_POPPY_SEED_COTTON_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/opium_poppy_seed_yucca_disc")
    public static final ItemMisc OPIUM_POPPY_SEED_YUCCA_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/opium_poppy_seed_papyrus_disc")
    public static final ItemMisc OPIUM_POPPY_SEED_PAPYRUS_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/opium_poppy_seed_silk_disc")
    public static final ItemMisc OPIUM_POPPY_SEED_SILK_DISC = getNull();

    //=== Crop sugar beet disc =======================================================================================//

    @GameRegistry.ObjectHolder("crop/product/sugar_beet_jute_disc")
    public static final ItemMisc SUGAR_BEET_JUTE_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_beet_sisal_disc")
    public static final ItemMisc SUGAR_BEET_SISAL_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_beet_linen_disc")
    public static final ItemMisc SUGAR_BEET_LINEN_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_beet_hemp_disc")
    public static final ItemMisc SUGAR_BEET_HEMP_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_beet_cotton_disc")
    public static final ItemMisc SUGAR_BEET_COTTON_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_beet_yucca_disc")
    public static final ItemMisc SUGAR_BEET_YUCCA_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_beet_papyrus_disc")
    public static final ItemMisc SUGAR_BEET_PAPYRUS_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_beet_silk_disc")
    public static final ItemMisc SUGAR_BEET_SILK_DISC = getNull();

    //=== Crop sugar cane disc =======================================================================================//

    @GameRegistry.ObjectHolder("crop/product/sugar_cane_jute_disc")
    public static final ItemMisc SUGAR_CANE_JUTE_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_cane_sisal_disc")
    public static final ItemMisc SUGAR_CANE_SISAL_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_cane_linen_disc")
    public static final ItemMisc SUGAR_CANE_LINEN_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_cane_hemp_disc")
    public static final ItemMisc SUGAR_CANE_HEMP_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_cane_cotton_disc")
    public static final ItemMisc SUGAR_CANE_COTTON_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_cane_yucca_disc")
    public static final ItemMisc SUGAR_CANE_YUCCA_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_cane_papyrus_disc")
    public static final ItemMisc SUGAR_CANE_PAPYRUS_DISC = getNull();
    @GameRegistry.ObjectHolder("crop/product/sugar_cane_silk_disc")
    public static final ItemMisc SUGAR_CANE_SILK_DISC = getNull();

    //=== Crop net ===================================================================================================//

    @GameRegistry.ObjectHolder("crop/product/jute_net")
    public static final ItemMisc JUTE_NET = getNull();
    @GameRegistry.ObjectHolder("crop/product/sisal_net")
    public static final ItemMisc SISAL_NET = getNull();
    @GameRegistry.ObjectHolder("crop/product/linen_net")
    public static final ItemMisc LINEN_NET = getNull();
    @GameRegistry.ObjectHolder("crop/product/hemp_net")
    public static final ItemMisc HEMP_NET = getNull();
    @GameRegistry.ObjectHolder("crop/product/cotton_net")
    public static final ItemMisc COTTON_NET = getNull();
    @GameRegistry.ObjectHolder("crop/product/yucca_net")
    public static final ItemMisc YUCCA_NET = getNull();
    @GameRegistry.ObjectHolder("crop/product/papyrus_net")
    public static final ItemMisc PAPYRUS_NET = getNull();
    @GameRegistry.ObjectHolder("crop/product/silk_net")
    public static final ItemMisc SILK_NET = getNull();

    //=== Crop dirty net =============================================================================================//

    @GameRegistry.ObjectHolder("crop/product/dirty_jute_net")
    public static final ItemMisc DIRTY_JUTE_NET = getNull();
    @GameRegistry.ObjectHolder("crop/product/dirty_sisal_net")
    public static final ItemMisc DIRTY_SISAL_NET = getNull();
    @GameRegistry.ObjectHolder("crop/product/dirty_linen_net")
    public static final ItemMisc DIRTY_LINEN_NET = getNull();
    @GameRegistry.ObjectHolder("crop/product/dirty_hemp_net")
    public static final ItemMisc DIRTY_HEMP_NET = getNull();
    @GameRegistry.ObjectHolder("crop/product/dirty_cotton_net")
    public static final ItemMisc DIRTY_COTTON_NET = getNull();
    @GameRegistry.ObjectHolder("crop/product/dirty_yucca_net")
    public static final ItemMisc DIRTY_YUCCA_NET = getNull();
    @GameRegistry.ObjectHolder("crop/product/dirty_papyrus_net")
    public static final ItemMisc DIRTY_PAPYRUS_NET = getNull();
    @GameRegistry.ObjectHolder("crop/product/dirty_silk_net")
    public static final ItemMisc DIRTY_SILK_NET = getNull();

    //=== Crop head ==================================================================================================//

    @GameRegistry.ObjectHolder("crop/product/chamomile_head")
    public static final ItemMisc CHAMOMILE_HEAD = getNull();
    @GameRegistry.ObjectHolder("crop/product/dandelion_head")
    public static final ItemMisc DANDELION_HEAD = getNull();
    @GameRegistry.ObjectHolder("crop/product/labrador_tea_head")
    public static final ItemMisc LABRADOR_TEA_HEAD = getNull();
    @GameRegistry.ObjectHolder("crop/product/sunflower_head")
    public static final ItemMisc SUNFLOWER_HEAD = getNull();

    //=== Crop dried head ============================================================================================//

    @GameRegistry.ObjectHolder("crop/product/dried/chamomile_head")
    public static final ItemMisc DRIED_CHAMOMILE_HEAD = getNull();
    @GameRegistry.ObjectHolder("crop/product/dried/dandelion_head")
    public static final ItemMisc DRIED_DANDELION_HEAD = getNull();
    @GameRegistry.ObjectHolder("crop/product/dried/labrador_tea_head")
    public static final ItemMisc DRIED_LABRADOR_TEA_HEAD = getNull();
    @GameRegistry.ObjectHolder("crop/product/dried/sunflower_head")
    public static final ItemMisc DRIED_SUNFLOWER_HEAD = getNull();

    //=== Malt =======================================================================================================//

    @GameRegistry.ObjectHolder("crop/product/malt_barley")
    public static final ItemMisc MALT_BARLEY = getNull();
    @GameRegistry.ObjectHolder("crop/product/malt_corn")
    public static final ItemMisc MALT_CORN = getNull();
    @GameRegistry.ObjectHolder("crop/product/malt_rice")
    public static final ItemMisc MALT_RICE = getNull();
    @GameRegistry.ObjectHolder("crop/product/malt_rye")
    public static final ItemMisc MALT_RYE = getNull();
    @GameRegistry.ObjectHolder("crop/product/malt_wheat")
    public static final ItemMisc MALT_WHEAT = getNull();
    @GameRegistry.ObjectHolder("crop/product/malt_amaranth")
    public static final ItemMisc MALT_AMARANTH = getNull();
    @GameRegistry.ObjectHolder("crop/product/malt_buckwheat")
    public static final ItemMisc MALT_BUCKWHEAT = getNull();
    @GameRegistry.ObjectHolder("crop/product/malt_fonio")
    public static final ItemMisc MALT_FONIO = getNull();
    @GameRegistry.ObjectHolder("crop/product/malt_millet")
    public static final ItemMisc MALT_MILLET = getNull();
    @GameRegistry.ObjectHolder("crop/product/malt_quinoa")
    public static final ItemMisc MALT_QUINOA = getNull();
    @GameRegistry.ObjectHolder("crop/product/malt_spelt")
    public static final ItemMisc MALT_SPELT = getNull();

    //=== Container ==================================================================================================//

    @GameRegistry.ObjectHolder("container/leather_bag_piece")
    public static final ItemMisc LEATHER_BAG_PIECE = getNull();
    @GameRegistry.ObjectHolder("container/leather_bag")
    public static final ItemBag LEATHER_BAG = getNull();
    @GameRegistry.ObjectHolder("container/pineapple_leather_bag_piece")
    public static final ItemMisc PINEAPPLE_LEATHER_BAG_PIECE = getNull();
    @GameRegistry.ObjectHolder("container/pineapple_leather_bag")
    public static final ItemBag PINEAPPLE_LEATHER_BAG = getNull();
    @GameRegistry.ObjectHolder("container/burlap_sack_piece")
    public static final ItemMisc BURLAP_SACK_PIECE = getNull();
    @GameRegistry.ObjectHolder("container/burlap_sack")
    public static final ItemSack BURLAP_SACK = getNull();
    @GameRegistry.ObjectHolder("container/wool_sack_piece")
    public static final ItemMisc WOOL_SACK_PIECE = getNull();
    @GameRegistry.ObjectHolder("container/wool_sack")
    public static final ItemSack WOOL_SACK = getNull();
    @GameRegistry.ObjectHolder("container/silk_sack_piece")
    public static final ItemMisc SILK_SACK_PIECE = getNull();
    @GameRegistry.ObjectHolder("container/silk_sack")
    public static final ItemSack SILK_SACK = getNull();
    @GameRegistry.ObjectHolder("container/cotton_sack_piece")
    public static final ItemMisc COTTON_SACK_PIECE = getNull();
    @GameRegistry.ObjectHolder("container/cotton_sack")
    public static final ItemSack COTTON_SACK = getNull();
    @GameRegistry.ObjectHolder("container/hemp_sack_piece")
    public static final ItemMisc HEMP_SACK_PIECE = getNull();
    @GameRegistry.ObjectHolder("container/hemp_sack")
    public static final ItemSack HEMP_SACK = getNull();
    @GameRegistry.ObjectHolder("container/linen_sack_piece")
    public static final ItemMisc LINEN_SACK_PIECE = getNull();
    @GameRegistry.ObjectHolder("container/linen_sack")
    public static final ItemSack LINEN_SACK = getNull();
    @GameRegistry.ObjectHolder("container/sisal_sack_piece")
    public static final ItemMisc SISAL_SACK_PIECE = getNull();
    @GameRegistry.ObjectHolder("container/sisal_sack")
    public static final ItemSack SISAL_SACK = getNull();
    @GameRegistry.ObjectHolder("container/yucca_sack_piece")
    public static final ItemMisc YUCCA_SACK_PIECE = getNull();
    @GameRegistry.ObjectHolder("container/yucca_sack")
    public static final ItemSack YUCCA_SACK = getNull();

    //=== Armor ======================================================================================================//

    @GameRegistry.ObjectHolder("armor/helmet/pineapple_leather")
    public static final TFCItemArmor PINEAPPLE_LEATHER_HELMET = getNull();
    @GameRegistry.ObjectHolder("armor/chestplate/pineapple_leather")
    public static final TFCItemArmor PINEAPPLE_LEATHER_CHESTPLATE = getNull();
    @GameRegistry.ObjectHolder("armor/leggings/pineapple_leather")
    public static final TFCItemArmor PINEAPPLE_LEATHER_LEGGINGS = getNull();
    @GameRegistry.ObjectHolder("armor/boots/pineapple_leather")
    public static final TFCItemArmor PINEAPPLE_LEATHER_BOOTS = getNull();
    @GameRegistry.ObjectHolder("armor/helmet/burlap_cloth")
    public static final TFCItemArmor BURLAP_CLOTH_HELMET = getNull();
    @GameRegistry.ObjectHolder("armor/chestplate/burlap_cloth")
    public static final TFCItemArmor BURLAP_CLOTH_CHESTPLATE = getNull();
    @GameRegistry.ObjectHolder("armor/leggings/burlap_cloth")
    public static final TFCItemArmor BURLAP_CLOTH_LEGGINGS = getNull();
    @GameRegistry.ObjectHolder("armor/boots/burlap_cloth")
    public static final TFCItemArmor BURLAP_CLOTH_BOOTS = getNull();
    @GameRegistry.ObjectHolder("armor/helmet/wool_cloth")
    public static final TFCItemArmor WOOL_CLOTH_HELMET = getNull();
    @GameRegistry.ObjectHolder("armor/chestplate/wool_cloth")
    public static final TFCItemArmor WOOL_CLOTH_CHESTPLATE = getNull();
    @GameRegistry.ObjectHolder("armor/leggings/wool_cloth")
    public static final TFCItemArmor WOOL_CLOTH_LEGGINGS = getNull();
    @GameRegistry.ObjectHolder("armor/boots/wool_cloth")
    public static final TFCItemArmor WOOL_CLOTH_BOOTS = getNull();
    @GameRegistry.ObjectHolder("armor/helmet/silk_cloth")
    public static final TFCItemArmor SILK_CLOTH_HELMET = getNull();
    @GameRegistry.ObjectHolder("armor/chestplate/silk_cloth")
    public static final TFCItemArmor SILK_CLOTH_CHESTPLATE = getNull();
    @GameRegistry.ObjectHolder("armor/leggings/silk_cloth")
    public static final TFCItemArmor SILK_CLOTH_LEGGINGS = getNull();
    @GameRegistry.ObjectHolder("armor/boots/silk_cloth")
    public static final TFCItemArmor SILK_CLOTH_BOOTS = getNull();
    @GameRegistry.ObjectHolder("armor/helmet/sisal_cloth")
    public static final TFCItemArmor SISAL_CLOTH_HELMET = getNull();
    @GameRegistry.ObjectHolder("armor/chestplate/sisal_cloth")
    public static final TFCItemArmor SISAL_CLOTH_CHESTPLATE = getNull();
    @GameRegistry.ObjectHolder("armor/leggings/sisal_cloth")
    public static final TFCItemArmor SISAL_CLOTH_LEGGINGS = getNull();
    @GameRegistry.ObjectHolder("armor/boots/sisal_cloth")
    public static final TFCItemArmor SISAL_CLOTH_BOOTS = getNull();
    @GameRegistry.ObjectHolder("armor/helmet/cotton_cloth")
    public static final TFCItemArmor COTTON_CLOTH_HELMET = getNull();
    @GameRegistry.ObjectHolder("armor/chestplate/cotton_cloth")
    public static final TFCItemArmor COTTON_CLOTH_CHESTPLATE = getNull();
    @GameRegistry.ObjectHolder("armor/leggings/cotton_cloth")
    public static final TFCItemArmor COTTON_CLOTH_LEGGINGS = getNull();
    @GameRegistry.ObjectHolder("armor/boots/cotton_cloth")
    public static final TFCItemArmor COTTON_CLOTH_BOOTS = getNull();
    @GameRegistry.ObjectHolder("armor/helmet/linen_cloth")
    public static final TFCItemArmor LINEN_CLOTH_HELMET = getNull();
    @GameRegistry.ObjectHolder("armor/chestplate/linen_cloth")
    public static final TFCItemArmor LINEN_CLOTH_CHESTPLATE = getNull();
    @GameRegistry.ObjectHolder("armor/leggings/linen_cloth")
    public static final TFCItemArmor LINEN_CLOTH_LEGGINGS = getNull();
    @GameRegistry.ObjectHolder("armor/boots/linen_cloth")
    public static final TFCItemArmor LINEN_CLOTH_BOOTS = getNull();
    @GameRegistry.ObjectHolder("armor/helmet/hemp_cloth")
    public static final TFCItemArmor HEMP_CLOTH_HELMET = getNull();
    @GameRegistry.ObjectHolder("armor/chestplate/hemp_cloth")
    public static final TFCItemArmor HEMP_CLOTH_CHESTPLATE = getNull();
    @GameRegistry.ObjectHolder("armor/leggings/hemp_cloth")
    public static final TFCItemArmor HEMP_CLOTH_LEGGINGS = getNull();
    @GameRegistry.ObjectHolder("armor/boots/hemp_cloth")
    public static final TFCItemArmor HEMP_CLOTH_BOOTS = getNull();
    @GameRegistry.ObjectHolder("armor/helmet/yucca_canvas")
    public static final TFCItemArmor YUCCA_CANVAS_HELMET = getNull();
    @GameRegistry.ObjectHolder("armor/chestplate/yucca_canvas")
    public static final TFCItemArmor YUCCA_CANVAS_CHESTPLATE = getNull();
    @GameRegistry.ObjectHolder("armor/leggings/yucca_canvas")
    public static final TFCItemArmor YUCCA_CANVAS_LEGGINGS = getNull();
    @GameRegistry.ObjectHolder("armor/boots/yucca_canvas")
    public static final TFCItemArmor YUCCA_CANVAS_BOOTS = getNull();

    //=== Devices ====================================================================================================//

    @GameRegistry.ObjectHolder("devices/flora_density_meter")
    public static final ItemFloraDensity FLORA_DENSITY_METER = getNull();
    @GameRegistry.ObjectHolder("devices/season_clock")
    public static final ItemCalendarClock CALENDAR_CLOCK = getNull();


    @GameRegistry.ObjectHolder("pomace")
    public static final ItemMisc POMACE = getNull();
    @GameRegistry.ObjectHolder("charred_bones")
    public static final ItemMisc CHARRED_BONES = getNull();

    // Normal Trees Nuts
    @GameRegistry.ObjectHolder("food/pinecone")
    public static final TFCItemFood PINECONE = getNull();


    public static final ItemMisc STRAW = getNull();

    @GameRegistry.ObjectHolder("animal/product/wool")
    public static final ItemMisc WOOL = getNull();
    @GameRegistry.ObjectHolder("animal/product/wool_yarn")
    public static final ItemMisc WOOL_YARN = getNull();


    @GameRegistry.ObjectHolder("ceramics/clay/unfired/fire_brick")
    public static final ItemPottery UNFIRED_FIRE_BRICK = getNull();
    @GameRegistry.ObjectHolder("ceramics/clay/fired/fire_brick")
    public static final ItemPottery FIRED_FIRE_BRICK = getNull();
    @GameRegistry.ObjectHolder("ceramics/clay/unfired/vessel")
    public static final ItemPottery UNFIRED_VESSEL = getNull();
    @GameRegistry.ObjectHolder("ceramics/clay/fired/vessel")
    public static final ItemPottery FIRED_VESSEL = getNull();
    @GameRegistry.ObjectHolder("ceramics/clay/unfired/vessel_glazed")
    public static final ItemPottery UNFIRED_VESSEL_GLAZED = getNull();
    @GameRegistry.ObjectHolder("ceramics/clay/fired/vessel_glazed")
    public static final ItemPottery FIRED_VESSEL_GLAZED = getNull();
    @GameRegistry.ObjectHolder("ceramics/clay/unfired/jug")
    public static final ItemPottery UNFIRED_JUG = getNull();
    @GameRegistry.ObjectHolder("ceramics/clay/fired/jug")
    public static final ItemPottery FIRED_JUG = getNull();
    @GameRegistry.ObjectHolder("ceramics/clay/unfired/pot")
    public static final ItemPottery UNFIRED_POT = getNull();
    @GameRegistry.ObjectHolder("ceramics/clay/fired/pot")
    public static final ItemPottery FIRED_POT = getNull();
    @GameRegistry.ObjectHolder("ceramics/clay/unfired/bowl")
    public static final ItemPottery UNFIRED_BOWL = getNull();
    @GameRegistry.ObjectHolder("ceramics/clay/fired/bowl")
    public static final ItemPottery FIRED_BOWL = getNull();
    @GameRegistry.ObjectHolder("ceramics/clay/unfired/spindle")
    public static final ItemPottery UNFIRED_SPINDLE = getNull();
    @GameRegistry.ObjectHolder("ceramics/clay/fired/spindle")
    public static final ItemPottery FIRED_SPINDLE = getNull();
    @GameRegistry.ObjectHolder("ceramics/clay/unfired/large_vessel")
    public static final ItemPottery UNFIRED_LARGE_VESSEL = getNull();
    @GameRegistry.ObjectHolder("ceramics/unfired/crucible")
    public static final ItemPottery UNFIRED_CRUCIBLE = getNull();
    @GameRegistry.ObjectHolder("ceramics/fire_clay")
    public static final Item FIRE_CLAY = getNull();

    // Earthenware Clay Ceramics
    @GameRegistry.ObjectHolder("ceramics/earthenware/earthenware_clay")
    public static final ItemClayEarthenware EARTHENWARE_CLAY = getNull();
    @GameRegistry.ObjectHolder("ceramics/earthenware/unfired/earthenware_brick")
    public static final ItemPottery UNFIRED_EARTHENWARE_BRICK = getNull();
    @GameRegistry.ObjectHolder("ceramics/earthenware/fired/earthenware_brick")
    public static final ItemPottery FIRED_EARTHENWARE_BRICK = getNull();
    @GameRegistry.ObjectHolder("ceramics/earthenware/unfired/vessel")
    public static final ItemPottery UNFIRED_EARTHENWARE_VESSEL = getNull();
    @GameRegistry.ObjectHolder("ceramics/earthenware/fired/vessel")
    public static final ItemPottery FIRED_EARTHENWARE_VESSEL = getNull();
    @GameRegistry.ObjectHolder("ceramics/earthenware/unfired/vessel_glazed")
    public static final ItemPottery UNFIRED_EARTHENWARE_VESSEL_GLAZED = getNull();
    @GameRegistry.ObjectHolder("ceramics/earthenware/fired/vessel_glazed")
    public static final ItemPottery FIRED_EARTHENWARE_VESSEL_GLAZED = getNull();
    @GameRegistry.ObjectHolder("ceramics/earthenware/unfired/jug")
    public static final ItemPottery UNFIRED_EARTHENWARE_JUG = getNull();
    @GameRegistry.ObjectHolder("ceramics/earthenware/fired/jug")
    public static final ItemPottery FIRED_EARTHENWARE_JUG = getNull();
    @GameRegistry.ObjectHolder("ceramics/earthenware/unfired/pot")
    public static final ItemPottery UNFIRED_EARTHENWARE_POT = getNull();
    @GameRegistry.ObjectHolder("ceramics/earthenware/fired/pot")
    public static final ItemPottery FIRED_EARTHENWARE_POT = getNull();
    @GameRegistry.ObjectHolder("ceramics/earthenware/unfired/bowl")
    public static final ItemPottery UNFIRED_EARTHENWARE_BOWL = getNull();
    @GameRegistry.ObjectHolder("ceramics/earthenware/fired/bowl")
    public static final ItemPottery FIRED_EARTHENWARE_BOWL = getNull();
    @GameRegistry.ObjectHolder("ceramics/earthenware/unfired/spindle")
    public static final ItemPottery UNFIRED_EARTHENWARE_SPINDLE = getNull();
    @GameRegistry.ObjectHolder("ceramics/earthenware/fired/spindle")
    public static final ItemPottery FIRED_EARTHENWARE_SPINDLE = getNull();
    @GameRegistry.ObjectHolder("ceramics/earthenware/unfired/large_vessel")
    public static final ItemPottery UNFIRED_EARTHENWARE_LARGE_VESSEL = getNull();

    // Kaolinite Clay Ceramics
    @GameRegistry.ObjectHolder("ceramics/kaolinite/kaolinite_clay")
    public static final ItemClayKaolinite KAOLINITE_CLAY = getNull();
    @GameRegistry.ObjectHolder("ceramics/kaolinite/unfired/kaolinite_brick")
    public static final ItemPottery UNFIRED_KAOLINITE_BRICK = getNull();
    @GameRegistry.ObjectHolder("ceramics/kaolinite/fired/kaolinite_brick")
    public static final ItemPottery FIRED_KAOLINITE_BRICK = getNull();
    @GameRegistry.ObjectHolder("ceramics/kaolinite/unfired/vessel")
    public static final ItemPottery UNFIRED_KAOLINITE_VESSEL = getNull();
    @GameRegistry.ObjectHolder("ceramics/kaolinite/fired/vessel")
    public static final ItemPottery FIRED_KAOLINITE_VESSEL = getNull();
    @GameRegistry.ObjectHolder("ceramics/kaolinite/unfired/vessel_glazed")
    public static final ItemPottery UNFIRED_KAOLINITE_VESSEL_GLAZED = getNull();
    @GameRegistry.ObjectHolder("ceramics/kaolinite/fired/vessel_glazed")
    public static final ItemPottery FIRED_KAOLINITE_VESSEL_GLAZED = getNull();
    @GameRegistry.ObjectHolder("ceramics/kaolinite/unfired/jug")
    public static final ItemPottery UNFIRED_KAOLINITE_JUG = getNull();
    @GameRegistry.ObjectHolder("ceramics/kaolinite/fired/jug")
    public static final ItemPottery FIRED_KAOLINITE_JUG = getNull();
    @GameRegistry.ObjectHolder("ceramics/kaolinite/unfired/pot")
    public static final ItemPottery UNFIRED_KAOLINITE_POT = getNull();
    @GameRegistry.ObjectHolder("ceramics/kaolinite/fired/pot")
    public static final ItemPottery FIRED_KAOLINITE_POT = getNull();
    @GameRegistry.ObjectHolder("ceramics/kaolinite/unfired/bowl")
    public static final ItemPottery UNFIRED_KAOLINITE_BOWL = getNull();
    @GameRegistry.ObjectHolder("ceramics/kaolinite/fired/bowl")
    public static final ItemPottery FIRED_KAOLINITE_BOWL = getNull();
    @GameRegistry.ObjectHolder("ceramics/kaolinite/unfired/spindle")
    public static final ItemPottery UNFIRED_KAOLINITE_SPINDLE = getNull();
    @GameRegistry.ObjectHolder("ceramics/kaolinite/fired/spindle")
    public static final ItemPottery FIRED_KAOLINITE_SPINDLE = getNull();
    @GameRegistry.ObjectHolder("ceramics/kaolinite/unfired/large_vessel")
    public static final ItemPottery UNFIRED_KAOLINITE_LARGE_VESSEL = getNull();

    // Stoneware Clay Ceramics
    @GameRegistry.ObjectHolder("ceramics/stoneware/stoneware_clay")
    public static final ItemClayStoneware STONEWARE_CLAY = getNull();
    @GameRegistry.ObjectHolder("ceramics/stoneware/unfired/stoneware_brick")
    public static final ItemPottery UNFIRED_STONEWARE_BRICK = getNull();
    @GameRegistry.ObjectHolder("ceramics/stoneware/fired/stoneware_brick")
    public static final ItemPottery FIRED_STONEWARE_BRICK = getNull();
    @GameRegistry.ObjectHolder("ceramics/stoneware/unfired/vessel")
    public static final ItemPottery UNFIRED_STONEWARE_VESSEL = getNull();
    @GameRegistry.ObjectHolder("ceramics/stoneware/fired/vessel")
    public static final ItemPottery FIRED_STONEWARE_VESSEL = getNull();
    @GameRegistry.ObjectHolder("ceramics/stoneware/unfired/vessel_glazed")
    public static final ItemPottery UNFIRED_STONEWARE_VESSEL_GLAZED = getNull();
    @GameRegistry.ObjectHolder("ceramics/stoneware/fired/vessel_glazed")
    public static final ItemPottery FIRED_STONEWARE_VESSEL_GLAZED = getNull();
    @GameRegistry.ObjectHolder("ceramics/stoneware/unfired/jug")
    public static final ItemPottery UNFIRED_STONEWARE_JUG = getNull();
    @GameRegistry.ObjectHolder("ceramics/stoneware/fired/jug")
    public static final ItemPottery FIRED_STONEWARE_JUG = getNull();
    @GameRegistry.ObjectHolder("ceramics/stoneware/unfired/pot")
    public static final ItemPottery UNFIRED_STONEWARE_POT = getNull();
    @GameRegistry.ObjectHolder("ceramics/stoneware/fired/pot")
    public static final ItemPottery FIRED_STONEWARE_POT = getNull();
    @GameRegistry.ObjectHolder("ceramics/stoneware/unfired/bowl")
    public static final ItemPottery UNFIRED_STONEWARE_BOWL = getNull();
    @GameRegistry.ObjectHolder("ceramics/stoneware/fired/bowl")
    public static final ItemPottery FIRED_STONEWARE_BOWL = getNull();
    @GameRegistry.ObjectHolder("ceramics/stoneware/unfired/spindle")
    public static final ItemPottery UNFIRED_STONEWARE_SPINDLE = getNull();
    @GameRegistry.ObjectHolder("ceramics/stoneware/fired/spindle")
    public static final ItemPottery FIRED_STONEWARE_SPINDLE = getNull();
    @GameRegistry.ObjectHolder("ceramics/stoneware/unfired/large_vessel")
    public static final ItemPottery UNFIRED_STONEWARE_LARGE_VESSEL = getNull();

    @GameRegistry.ObjectHolder("bloom/unrefined")
    public static final ItemBloom UNREFINED_BLOOM = getNull();
    @GameRegistry.ObjectHolder("bloom/refined")
    public static final ItemBloom REFINED_BLOOM = getNull();

    public static final TFCItem MORTAR = getNull();

    public static final TFCItem HALTER = getNull();

    @GameRegistry.ObjectHolder("quiver")
    public static final ItemQuiver QUIVER = getNull();

    public static final TFCItemWoodenBucket WOODEN_BUCKET = getNull();

    //@GameRegistry.ObjectHolder("metal/bucket/blue_steel")
    //public static final ItemMetalBucket BLUE_STEEL_BUCKET = getNull();
    //@GameRegistry.ObjectHolder("metal/bucket/red_steel")
    //public static final ItemMetalBucket RED_STEEL_BUCKET = getNull();

    @GameRegistry.ObjectHolder("dye/black")
    public static final ItemMisc DYE_BLACK = getNull();
    @GameRegistry.ObjectHolder("dye/blue")
    public static final ItemMisc DYE_BLUE = getNull();
    @GameRegistry.ObjectHolder("dye/brown")
    public static final ItemMisc DYE_BROWN = getNull();
    @GameRegistry.ObjectHolder("dye/white")
    public static final ItemMisc DYE_WHITE = getNull();

    @GameRegistry.ObjectHolder("ceramics/unfired/clay_brick")
    public static final ItemPottery UNFIRED_BRICK = getNull();

    @GameRegistry.ObjectHolder("ceramics/unfired/clay_flower_pot")
    public static final ItemPottery UNFIRED_FLOWER_POT = getNull();


    @GameRegistry.ObjectHolder("food/olive_paste")
    public static final ItemMisc OLIVE_PASTE = getNull();
    @GameRegistry.ObjectHolder("glass_shard")
    public static final ItemMisc GLASS_SHARD = getNull();
    @GameRegistry.ObjectHolder("stick_bunch")
    public static final ItemMisc STICK_BUNCH = getNull();
    @GameRegistry.ObjectHolder("wood_ash")
    public static final ItemMisc WOOD_ASH = getNull();

    // Floraer shit
    // Miscellaneous Food Stuff


    // Normal Items
    @GameRegistry.ObjectHolder("tools/walking_Stick")
    public static final ItemWalkingStick WALKING_STICK = getNull();

    /*@GameRegistry.ObjectHolder("tools/bows/shortbow/shortbow")
    public static final ItemBowTFCF SHORTBOW = getNull();
    @GameRegistry.ObjectHolder("tools/bows/longbow/longbow")
    public static final ItemBowTFCF LONGBOW = getNull();
    @GameRegistry.ObjectHolder("tools/bows/bonebow/bonebow")
    public static final ItemBowTFCF BONEBOW = getNull();
    @GameRegistry.ObjectHolder("tools/bows/bow_of_lost_souls/bow_of_lost_souls")
    public static final ItemBowTFCF BOW_OF_LOST_SOULS = getNull();
    @GameRegistry.ObjectHolder("tools/bows/elite_power_bow/elite_power_bow")
    public static final ItemBowTFCF ELITE_POWER_BOW = getNull();
    @GameRegistry.ObjectHolder("tools/bows/green_menace/green_menace")
    public static final ItemBowTFCF GREEN_MENACE = getNull();
    @GameRegistry.ObjectHolder("tools/bows/hunting_bow/hunting_bow")
    public static final ItemBowTFCF HUNTING_BOW = getNull();
    @GameRegistry.ObjectHolder("tools/bows/nocturnal_bow/nocturnal_bow")
    public static final ItemBowTFCF NOCTURNAL_BOW = getNull();
    @GameRegistry.ObjectHolder("tools/bows/red_snake/red_snake")
    public static final ItemBowTFCF RED_SNAKE = getNull();
    @GameRegistry.ObjectHolder("tools/bows/rosebow/rosebow")
    public static final ItemBowTFCF ROSEBOW = getNull();
    @GameRegistry.ObjectHolder("tools/bows/sabrewing/sabrewing")
    public static final ItemBowTFCF SABREWING = getNull();*/


    @GameRegistry.ObjectHolder("logwood_chips")
    public static final ItemMisc LOGWOOD_CHIPS = getNull();
    //@GameRegistry.ObjectHolder("resin")
    //public static final ItemMisc RESIN = getNull();
    @GameRegistry.ObjectHolder("conch")
    public static final ItemMisc CONCH = getNull();
    @GameRegistry.ObjectHolder("clam")
    public static final ItemMisc CLAM = getNull();
    @GameRegistry.ObjectHolder("live_clam")
    public static final ItemMisc LIVE_CLAM = getNull();
    @GameRegistry.ObjectHolder("scallop")
    public static final ItemMisc SCALLOP = getNull();
    @GameRegistry.ObjectHolder("live_scallop")
    public static final ItemMisc LIVE_SCALLOP = getNull();
    @GameRegistry.ObjectHolder("pearl")
    public static final ItemMisc PEARL = getNull();
    @GameRegistry.ObjectHolder("black_pearl")
    public static final ItemMisc BLACK_PEARL = getNull();
    @GameRegistry.ObjectHolder("live_starfish")
    public static final ItemMisc LIVE_STARFISH = getNull();
    @GameRegistry.ObjectHolder("animal/product/silk_moth_egg")
    public static final ItemMisc SILK_MOTH_EGG = getNull();
    @GameRegistry.ObjectHolder("animal/product/silk_worm_hatchery")
    public static final ItemMisc SILK_WORM_HATCHERY = getNull();
    @GameRegistry.ObjectHolder("animal/product/silk_worm")
    public static final ItemMisc SILK_WORM = getNull();
    @GameRegistry.ObjectHolder("animal/product/silk_worm_cocoon")
    public static final ItemMisc SILK_WORM_COCOON = getNull();
    @GameRegistry.ObjectHolder("animal/product/silk_worm_cocoon_boiled")
    public static final ItemMisc SILK_WORM_COCOON_BOILED = getNull();
    @GameRegistry.ObjectHolder("crop/product/mulberry_leaf")
    public static final ItemMisc MULBERRY_LEAF = getNull();


    @GameRegistry.ObjectHolder("crop/product/indigo")
    public static final ItemMisc INDIGO = getNull();
    @GameRegistry.ObjectHolder("crop/product/madder")
    public static final ItemMisc MADDER = getNull();
    @GameRegistry.ObjectHolder("crop/product/weld")
    public static final ItemMisc WELD = getNull();
    @GameRegistry.ObjectHolder("crop/product/woad")
    public static final ItemMisc WOAD = getNull();
    @GameRegistry.ObjectHolder("crop/product/hops")
    public static final ItemMisc HOPS = getNull();
    @GameRegistry.ObjectHolder("crop/product/rape")
    public static final ItemMisc RAPE = getNull();

    @GameRegistry.ObjectHolder("cellulose_fibers")
    public static final ItemMisc CELLULOSE_FIBERS = getNull();

    @GameRegistry.ObjectHolder("wood/fruit_tree/pole/cassia_cinnamon")
    public static final ItemMisc CASSIA_CINNAMON_POLE = getNull();
    @GameRegistry.ObjectHolder("wood/fruit_tree/lumber/cassia_cinnamon")
    public static final ItemMisc CASSIA_CINNAMON_LUMBER = getNull();
    @GameRegistry.ObjectHolder("wood/fruit_tree/pole/ceylon_cinnamon")
    public static final ItemMisc CEYLON_CINNAMON_POLE = getNull();
    @GameRegistry.ObjectHolder("wood/fruit_tree/lumber/ceylon_cinnamon")
    public static final ItemMisc CEYLON_CINNAMON_LUMBER = getNull();


    @GameRegistry.ObjectHolder("storage/unfired/urn")
    public static final ItemPottery UNFIRED_URN = getNull();
    @GameRegistry.ObjectHolder("wooden_bucket_salt")
    public static final ItemMisc WOODEN_BUCKET_SALT = getNull();
    @GameRegistry.ObjectHolder("wooden_bucket_sugar")
    public static final ItemMisc WOODEN_BUCKET_SUGAR = getNull();

    private static ImmutableList<Item> allSimpleItems = getNull();
    private static ImmutableList<TFCItemLumber> allLumberItems = getNull();
    private static ImmutableList<TFCItemBoat> allBoatItems = getNull();
    private static ImmutableList<TFCItemArmor> allArmorItems = getNull();
    private static ImmutableList<ItemClayMold> allClayMolds = getNull();
    private static ImmutableList<ItemEarthenwareMold> allEarthenwareMolds = getNull();
    private static ImmutableList<ItemKaoliniteMold> allKaoliniteMolds = getNull();
    private static ImmutableList<ItemStonewareMold> allStonewareMolds = getNull();

    public static ImmutableList<Item> getAllSimpleItems() {
        return allSimpleItems;
    }

    public static ImmutableList<TFCItemLumber> getAllLumberItems() {
        return allLumberItems;
    }

    public static ImmutableList<TFCItemBoat> getAllBoatItems() {
        return allBoatItems;
    }

    public static ImmutableList<TFCItemArmor> getAllArmorItems() {
        return allArmorItems;
    }

    public static ImmutableList<ItemClayMold> getAllClayMolds() {
        return allClayMolds;
    }

    public static ImmutableList<ItemEarthenwareMold> getAllEarthenwareMolds() {
        return allEarthenwareMolds;
    }

    public static ImmutableList<ItemKaoliniteMold> getAllKaoliniteMolds() {
        return allKaoliniteMolds;
    }

    public static ImmutableList<ItemStonewareMold> getAllStonewareMolds() {
        return allStonewareMolds;
    }


    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> r = event.getRegistry();
        Builder<Item> simpleItems = ImmutableList.builder();

        Builder<TFCItemLumber> lumberItems = ImmutableList.builder();
        Builder<TFCItemBoat> boatItems = ImmutableList.builder();

        simpleItems.add(register(r, "wand", new ItemDebug(), CT_MISC));
        simpleItems.add(register(r, "mortar", new ItemMisc(Size.TINY, Weight.VERY_LIGHT, "mortar"), CT_MISC));
        simpleItems.add(register(r, "halter", new ItemMisc(Size.SMALL, Weight.LIGHT, "halter"), CT_MISC));
        register(r, "wooden_bucket", new TFCItemWoodenBucket(), CT_WOOD); //not a simple item, use a custom model
        //register(r, "metal/bucket/blue_steel", new ItemMetalBucket(Metal.BLUE_STEEL, Metal.ItemType.BUCKET), CT_METAL); //not a simple item, use a custom model
        //register(r, "metal/bucket/red_steel", new ItemMetalBucket(Metal.RED_STEEL, Metal.ItemType.BUCKET), CT_METAL); //not a simple item, use a custom model

        // Rock Type Items
        {
            for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
                simpleItems.add(register(r, "rock/" + rock.getRegistryName().getPath().toLowerCase(), new ItemRock(rock), CT_ROCK_ITEMS));
            for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
                simpleItems.add(register(r, "brick/" + rock.getRegistryName().getPath().toLowerCase(), new TFCItemBrick(rock), CT_ROCK_ITEMS));

            for (Rock rock : TFCRegistries.ROCKS.getValuesCollection()) {
                ItemMud mud = new ItemMud(rock);
                ItemUnfiredMudBrick unfiredMudBrick = new ItemUnfiredMudBrick(mud, rock);
                simpleItems.add(register(r, "mud/mud_ball/" + rock.getRegistryName().getPath().toLowerCase(), mud, CT_ROCK_ITEMS));
                simpleItems.add(register(r, "mud/unfired/mud_brick/" + rock.getRegistryName().getPath().toLowerCase(), unfiredMudBrick, CT_ROCK_ITEMS));
                simpleItems.add(register(r, "mud/fired/mud_brick/" + rock.getRegistryName().getPath().toLowerCase(), new ItemFiredMudBrick(unfiredMudBrick), CT_ROCK_ITEMS));
            }
        }

        BlocksTFC.getAllNormalItemBlocks().forEach(x -> registerItemBlock(r, x));
        BlocksTFC.getAllColorizedItemBlocks().forEach(x -> registerItemBlock(r, x));
        BlocksTFC.getAllInventoryItemBlocks().forEach(x -> registerItemBlock(r, x));

        for (TFCBlockLog log : BlocksTFC.getAllLogBlocks())
            simpleItems.add(register(r, log.getRegistryName().getPath(), new TFCItemBlock(log), CT_WOOD));

        for (TFCBlockWoodDoor door : BlocksTFC.getAllDoorBlocks())
            simpleItems.add(register(r, door.getRegistryName().getPath(), new TFCItemDoor(door), CT_DECORATIONS));

        for (TFCBlockRockSlab.Half slab : BlocksTFC.getAllRockSlabBlocks())
            simpleItems.add(register(r, slab.getRegistryName().getPath(), new TFCItemSlab(slab, slab, slab.doubleSlab), CT_DECORATIONS));

        for (TFCBlockWoodSlab.Half slab : BlocksTFC.getAllWoodSlabBlocks())
            simpleItems.add(register(r, slab.getRegistryName().getPath(), new TFCItemSlab(slab, slab, slab.doubleSlab), CT_DECORATIONS));




        for (Wood wood : TFCRegistries.WOODS.getValuesCollection()) {
            lumberItems.add(register(r, "wood/lumber/" + wood.getRegistryName().getPath(), new TFCItemLumber(wood), CT_WOOD));
            boatItems.add(register(r, "wood/boat/" + wood.getRegistryName().getPath(), new TFCItemBoat(wood), CT_WOOD));
        }

        simpleItems.add(register(r, "stick_bunch", new ItemMisc(Size.NORMAL, Weight.LIGHT), CT_WOOD));
        simpleItems.add(register(r, "stick_bundle", new ItemMisc(Size.VERY_LARGE, Weight.MEDIUM, "log_wood", "stick_bundle"), CT_WOOD));
        simpleItems.add(register(r, "wood_ash", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT), CT_WOOD));

        // METAL
        {
            for (Material material : GregTechAPI.MATERIAL_REGISTRY) {
                if (material.hasFlag(TFCMaterialFlags.GENERATE_ANVIL)) {
                    simpleItems.add(register(r, "metal/anvil/" + material.getUnlocalizedName(), new ItemAnvil(material), CT_METAL));
                }

                if (material.hasFlag(TFCMaterialFlags.GENERATE_TRAPDOOR)) {
                    simpleItems.add(register(r, "metal/trapdoor/" + material.getUnlocalizedName(), new ItemMetalTrapdoor(material), CT_METAL));
                }

                if (material.hasFlag(TFCMaterialFlags.GENERATE_CLADDING)) {
                    simpleItems.add(register(r, "metal/cladding/" + material.getUnlocalizedName(), new ItemCladding(material), CT_METAL));
                }

                if (material.hasFlag(TFCMaterialFlags.GENERATE_LAMP)) {
                    simpleItems.add(register(r, "metal/lamp/" + material.getUnlocalizedName(), new ItemLamp(material), CT_METAL));
                }
            }
        }


        // Pottery
        Builder<ItemClayMold> clayMolds = ImmutableList.builder();
        Builder<ItemEarthenwareMold> earthenwareMolds = ImmutableList.builder();
        Builder<ItemKaoliniteMold> kaoliniteMolds = ImmutableList.builder();
        Builder<ItemStonewareMold> stonewareMolds = ImmutableList.builder();
        {
            // Ordinary
            {
                for (TFCOrePrefixExtended extendedOrePrefix : TFGUtils.TFC_OREPREFIX_REGISTRY) {
                    if (extendedOrePrefix.isHasMold()) {
                        clayMolds.add(register(r, "ceramics/clay/fired/mold/" + extendedOrePrefix.getOrePrefix().name, new ItemClayMold(extendedOrePrefix.getOrePrefix(), extendedOrePrefix.getMetalUnits()), CT_POTTERY));

                        simpleItems.add(register(r, "ceramics/clay/unfired/mold/" + extendedOrePrefix.getOrePrefix().name, new ItemUnfiredClayMold(extendedOrePrefix.getOrePrefix()), CT_POTTERY));
                    }
                }

                simpleItems.add(register(r, "ceramics/clay/unfired/large_vessel", new ItemUnfiredLargeVessel(), CT_POTTERY));
                simpleItems.add(register(r, "ceramics/clay/unfired/crucible", new ItemPottery(Size.LARGE, Weight.VERY_HEAVY), CT_POTTERY));

                registerPottery(simpleItems, r, "ceramics/clay/unfired/vessel", "ceramics/clay/fired/vessel", new ItemUnfiredSmallVessel(false), new ItemSmallVessel(false));
                registerPottery(null, r, "ceramics/clay/unfired/vessel_glazed", "ceramics/clay/fired/vessel_glazed", new ItemUnfiredSmallVessel(true), new ItemSmallVessel(true));

                ItemPottery firedPot = new ItemPottery(Size.LARGE, Weight.LIGHT);
                registerPottery(simpleItems, r, "ceramics/clay/unfired/pot", "ceramics/clay/fired/pot", new ItemPottery(Size.LARGE, Weight.LIGHT), firedPot);
                OreDictionaryHelper.register(firedPot, "cooking_pot");

                ItemPottery firedBowl = new ItemPottery(Size.VERY_SMALL, Weight.VERY_LIGHT);
                registerPottery(simpleItems, r, "ceramics/unfired/bowl", "ceramics/clay/fired/bowl", new ItemPottery(Size.VERY_SMALL, Weight.VERY_LIGHT), firedBowl);
                OreDictionaryHelper.register(firedBowl, "bowl");

                registerPottery(simpleItems, r, "ceramics/clay/unfired/spindle", "ceramics/clay/fired/spindle");
                registerPottery(simpleItems, r, "ceramics/clay/unfired/fire_brick", "ceramics/clay/fired/fire_brick");

                simpleItems.add(register(r, "ceramics/fire_clay", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "fire_clay"), CT_MISC));

                simpleItems.add(register(r, "ceramics/clay/unfired/jug", new ItemPottery(), CT_POTTERY));
                register(r, "ceramics/clay/fired/jug", new ItemJug(), CT_POTTERY);
                simpleItems.add(register(r, "ceramics/clay/unfired/clay_brick", new ItemPottery(), CT_POTTERY));
                simpleItems.add(register(r, "ceramics/clay/unfired/clay_flower_pot", new ItemPottery(), CT_POTTERY));

            }

            // Earthenware
            {
                for (TFCOrePrefixExtended extendedOrePrefix : TFGUtils.TFC_OREPREFIX_REGISTRY) {
                    if (extendedOrePrefix.isHasMold()) {
                        earthenwareMolds.add(register(r, "ceramics/earthenware/fired/mold/" + extendedOrePrefix.getOrePrefix().name, new ItemEarthenwareMold(extendedOrePrefix.getOrePrefix(), extendedOrePrefix.getMetalUnits()), CT_POTTERY));

                        simpleItems.add(register(r, "ceramics/earthenware/unfired/mold/" + extendedOrePrefix.getOrePrefix().name, new ItemUnfiredEarthenwareMold(extendedOrePrefix.getOrePrefix()), CT_POTTERY));
                    }
                }

                simpleItems.add(register(r, "ceramics/earthenware/earthenware_clay", new ItemClayEarthenware(Size.VERY_SMALL, Weight.VERY_LIGHT, "clay_earthenware"), CT_MISC));
                registerPottery(simpleItems, r, "ceramics/earthenware/unfired/earthenware_brick", "ceramics/earthenware/fired/earthenware_brick");

                simpleItems.add(register(r, "ceramics/earthenware/unfired/large_vessel", new ItemUnfiredLargeVessel(), CT_POTTERY));

                registerPottery(simpleItems, r, "ceramics/earthenware/unfired/vessel", "ceramics/earthenware/fired/vessel", new ItemUnfiredSmallVessel(false), new ItemSmallVessel(false));
                registerPottery(null, r, "ceramics/earthenware/unfired/vessel_glazed", "ceramics/earthenware/fired/vessel_glazed", new ItemUnfiredSmallVessel(true), new ItemSmallVessel(true));

                ItemPottery firedPotEarthenware = new ItemPottery(Size.LARGE, Weight.LIGHT);
                registerPottery(simpleItems, r, "ceramics/earthenware/unfired/pot", "ceramics/earthenware/fired/pot", new ItemPottery(Size.LARGE, Weight.LIGHT), firedPotEarthenware);
                OreDictionaryHelper.register(firedPotEarthenware, "cooking_pot");

                ItemPottery firedBowlEarthenware = new ItemPottery(Size.VERY_SMALL, Weight.VERY_LIGHT);
                registerPottery(simpleItems, r, "ceramics/earthenware/unfired/bowl", "ceramics/earthenware/fired/bowl", new ItemPottery(Size.VERY_SMALL, Weight.VERY_LIGHT), firedBowlEarthenware);
                OreDictionaryHelper.register(firedBowlEarthenware, "bowl");

                simpleItems.add(register(r, "ceramics/earthenware/unfired/jug", new ItemPottery(), CT_POTTERY));
                register(r, "ceramics/earthenware/fired/jug", new ItemJug(), CT_POTTERY);
            }

            // Kaolinite
            {
                for (TFCOrePrefixExtended extendedOrePrefix : TFGUtils.TFC_OREPREFIX_REGISTRY) {
                    if (extendedOrePrefix.isHasMold()) {
                        kaoliniteMolds.add(register(r, "ceramics/kaolinite/fired/mold/" + extendedOrePrefix.getOrePrefix().name, new ItemKaoliniteMold(extendedOrePrefix.getOrePrefix(), extendedOrePrefix.getMetalUnits()), CT_POTTERY));

                        simpleItems.add(register(r, "ceramics/kaolinite/unfired/mold/" + extendedOrePrefix.getOrePrefix().name, new ItemUnfiredKaoliniteMold(extendedOrePrefix.getOrePrefix()), CT_POTTERY));
                    }
                }

                simpleItems.add(register(r, "ceramics/kaolinite/kaolinite_clay", new ItemClayKaolinite(Size.VERY_SMALL, Weight.VERY_LIGHT, "clay_kaolinite"), CT_MISC));
                registerPottery(simpleItems, r, "ceramics/kaolinite/unfired/kaolinite_brick", "ceramics/kaolinite/fired/kaolinite_brick");

                simpleItems.add(register(r, "ceramics/kaolinite/unfired/large_vessel", new ItemUnfiredLargeVessel(), CT_POTTERY));

                registerPottery(simpleItems, r, "ceramics/kaolinite/unfired/vessel", "ceramics/kaolinite/fired/vessel", new ItemUnfiredSmallVessel(false), new ItemSmallVessel(false));
                registerPottery(null, r, "ceramics/kaolinite/unfired/vessel_glazed", "ceramics/kaolinite/fired/vessel_glazed", new ItemUnfiredSmallVessel(true), new ItemSmallVessel(true));

                ItemPottery firedPotKaolinite = new ItemPottery(Size.LARGE, Weight.LIGHT);
                registerPottery(simpleItems, r, "ceramics/kaolinite/unfired/pot", "ceramics/kaolinite/fired/pot", new ItemPottery(Size.LARGE, Weight.LIGHT), firedPotKaolinite);
                OreDictionaryHelper.register(firedPotKaolinite, "cooking_pot");

                ItemPottery firedBowlKaolinite = new ItemPottery(Size.VERY_SMALL, Weight.VERY_LIGHT);
                registerPottery(simpleItems, r, "ceramics/kaolinite/unfired/bowl", "ceramics/kaolinite/fired/bowl", new ItemPottery(Size.VERY_SMALL, Weight.VERY_LIGHT), firedBowlKaolinite);
                OreDictionaryHelper.register(firedBowlKaolinite, "bowl");

                simpleItems.add(register(r, "ceramics/kaolinite/unfired/jug", new ItemPottery(), CT_POTTERY));
                register(r, "ceramics/kaolinite/fired/jug", new ItemJug(), CT_POTTERY);
            }

            // Stoneware
            {
                for (TFCOrePrefixExtended extendedOrePrefix : TFGUtils.TFC_OREPREFIX_REGISTRY) {
                    if (extendedOrePrefix.isHasMold()) {
                        stonewareMolds.add(register(r, "ceramics/stoneware/fired/mold/" + extendedOrePrefix.getOrePrefix().name, new ItemStonewareMold(extendedOrePrefix.getOrePrefix(), extendedOrePrefix.getMetalUnits()), CT_POTTERY));

                        simpleItems.add(register(r, "ceramics/stoneware/unfired/mold/" + extendedOrePrefix.getOrePrefix().name, new ItemUnfiredStonewareMold(extendedOrePrefix.getOrePrefix()), CT_POTTERY));
                    }
                }

                simpleItems.add(register(r, "ceramics/stoneware/stoneware_clay", new ItemClayStoneware(Size.VERY_SMALL, Weight.VERY_LIGHT, "clay_stoneware"), CT_MISC));
                registerPottery(simpleItems, r, "ceramics/stoneware/unfired/stoneware_brick", "ceramics/stoneware/fired/stoneware_brick");

                simpleItems.add(register(r, "ceramics/stoneware/unfired/large_vessel", new ItemUnfiredLargeVessel(), CT_POTTERY));

                registerPottery(simpleItems, r, "ceramics/stoneware/unfired/vessel", "ceramics/stoneware/fired/vessel", new ItemUnfiredSmallVessel(false), new ItemSmallVessel(false));
                registerPottery(null, r, "ceramics/stoneware/unfired/vessel_glazed", "ceramics/stoneware/fired/vessel_glazed", new ItemUnfiredSmallVessel(true), new ItemSmallVessel(true));

                ItemPottery firedPotStoneware = new ItemPottery(Size.LARGE, Weight.LIGHT);
                registerPottery(simpleItems, r, "ceramics/stoneware/unfired/pot", "ceramics/stoneware/fired/pot", new ItemPottery(Size.LARGE, Weight.LIGHT), firedPotStoneware);
                OreDictionaryHelper.register(firedPotStoneware, "cooking_pot");

                ItemPottery firedBowlStoneware = new ItemPottery(Size.VERY_SMALL, Weight.VERY_LIGHT);
                registerPottery(simpleItems, r, "ceramics/stoneware/unfired/bowl", "ceramics/stoneware/fired/bowl", new ItemPottery(Size.VERY_SMALL, Weight.VERY_LIGHT), firedBowlStoneware);
                OreDictionaryHelper.register(firedBowlStoneware, "bowl");

                simpleItems.add(register(r, "ceramics/stoneware/unfired/jug", new ItemPottery(), CT_POTTERY));
                register(r, "ceramics/stoneware/fired/jug", new ItemJug(), CT_POTTERY);
            }


            simpleItems.add(register(r, "storage/unfired/urn", new ItemUnfiredUrn(), CT_POTTERY));
        }

        // Crop
        {

            simpleItems.add(register(r, "crop/product/chamomile_head", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "chamomile_head", "chamomile"), CT_MISC));
            simpleItems.add(register(r, "crop/product/dried/chamomile_head", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "dried_chamomile"), CT_MISC));
            simpleItems.add(register(r, "crop/product/dandelion_head", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "dandelion_head", "dandelion"), CT_MISC));
            simpleItems.add(register(r, "crop/product/dried/dandelion_head", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "dried_dandelion"), CT_MISC));
            simpleItems.add(register(r, "crop/product/labrador_tea_head", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "labrador_tea_head", "labrador_tea"), CT_MISC));
            simpleItems.add(register(r, "crop/product/dried/labrador_tea_head", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "dried_labrador_tea"), CT_MISC));
            simpleItems.add(register(r, "crop/product/sunflower_head", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "sunflower_head", "sunflower"), CT_MISC));
            simpleItems.add(register(r, "crop/product/dried/sunflower_head", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "dried_sunflower_head"), CT_MISC));

            simpleItems.add(register(r, "crop/product/malt_barley", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "malt_barley", "malt", "category_grain"), CT_FOOD));
            simpleItems.add(register(r, "crop/product/malt_corn", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "malt_corn", "malt", "category_grain"), CT_FOOD));
            simpleItems.add(register(r, "crop/product/malt_rice", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "malt_rice", "malt", "category_grain"), CT_FOOD));
            simpleItems.add(register(r, "crop/product/malt_rye", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "malt_rye", "malt", "category_grain"), CT_FOOD));
            simpleItems.add(register(r, "crop/product/malt_wheat", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "malt_wheat", "malt", "category_grain"), CT_FOOD));
            simpleItems.add(register(r, "crop/product/malt_amaranth", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "malt_amaranth", "malt", "category_grain"), CT_FOOD));
            simpleItems.add(register(r, "crop/product/malt_buckwheat", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "malt_buckwheat", "malt", "category_grain"), CT_FOOD));
            simpleItems.add(register(r, "crop/product/malt_fonio", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "malt_fonio", "malt", "category_grain"), CT_FOOD));
            simpleItems.add(register(r, "crop/product/malt_millet", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "malt_millet", "malt", "category_grain"), CT_FOOD));
            simpleItems.add(register(r, "crop/product/malt_quinoa", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "malt_quinoa", "malt", "category_grain"), CT_FOOD));
            simpleItems.add(register(r, "crop/product/malt_spelt", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "malt_spelt", "malt", "category_grain"), CT_FOOD));


            for (Crop crop : Crop.values()) {
                simpleItems.add(register(r, "crop/seeds/" + crop.name().toLowerCase(), new TFCItemSeeds(crop), CT_FOOD));
            }

            for (Crop crop : new Crop[]{Crop.JUTE, Crop.SISAL, Crop.LINEN, Crop.HEMP, Crop.COTTON}) {

                simpleItems.add(register(r, "crop/product/" + crop.name().toLowerCase(), new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, crop.name().toLowerCase()), CT_MISC));
                simpleItems.add(register(r, "crop/product/" + crop.name().toLowerCase() + "_fiber", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "fiber", crop.name().toLowerCase() + ".fiber"), CT_MISC));
                simpleItems.add(register(r, "crop/product/" + crop.name().toLowerCase() + "_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", crop.name().toLowerCase() + ".disc"), CT_MISC));
                simpleItems.add(register(r, "crop/product/" + crop.name().toLowerCase() + "_string", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "string", crop.name().toLowerCase() + ".string"), CT_MISC));
                simpleItems.add(register(r, "crop/product/" + crop.name().toLowerCase() + "_cloth", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "cloth", crop.name().toLowerCase() + ".cloth"), CT_MISC));
                simpleItems.add(register(r, "crop/product/olive_" + crop.name().toLowerCase() + "_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "olive." + crop.name().toLowerCase() + ".disc"), CT_MISC));
                simpleItems.add(register(r, "crop/product/soybean_" + crop.name().toLowerCase() + "_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "soybean." + crop.name().toLowerCase() + ".disc"), CT_MISC));
                simpleItems.add(register(r, "crop/product/linseed_" + crop.name().toLowerCase() + "_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "linseed." + crop.name().toLowerCase() + ".disc"), CT_MISC));
                simpleItems.add(register(r, "crop/product/rape_seed_" + crop.name().toLowerCase() + "_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "rape.seed." + crop.name().toLowerCase() + ".disc"), CT_MISC));
                simpleItems.add(register(r, "crop/product/sunflower_seed_" + crop.name().toLowerCase() + "_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "sunflower.seed." + crop.name().toLowerCase() + ".disc"), CT_MISC));
                simpleItems.add(register(r, "crop/product/opium_poppy_seed_" + crop.name().toLowerCase() + "_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "opium.poppy.seed." + crop.name().toLowerCase() + ".disc"), CT_MISC));
                simpleItems.add(register(r, "crop/product/sugar_beet_" + crop.name().toLowerCase() + "_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "sugar.beet." + crop.name().toLowerCase() + ".disc"), CT_MISC));
                simpleItems.add(register(r, "crop/product/sugar_cane_" + crop.name().toLowerCase() + "_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "sugar.cane." + crop.name().toLowerCase() + ".disc"), CT_MISC));
                simpleItems.add(register(r, "crop/product/" + crop.name().toLowerCase() + "_net", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", crop.name().toLowerCase() + ".net"), CT_MISC));
                simpleItems.add(register(r, "crop/product/dirty_" + crop.name().toLowerCase() + "_net", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "dirty." + crop.name().toLowerCase() + ".net"), CT_MISC));
            }


            simpleItems.add(register(r, "crop/product/yucca_fiber", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "fiber", "fiber_yucca"), CT_MISC));
            simpleItems.add(register(r, "crop/product/yucca_canvas", new ItemMisc(Size.VERY_SMALL, Weight.LIGHT, "cloth", "cloth_yucca", "fabric", "fabric_yucca", "canvas", "canvas_yucca"), CT_MISC));

            simpleItems.add(register(r, "crop/product/papyrus_pulp", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "pulp", "pulp_papyrus"), CT_MISC));
            simpleItems.add(register(r, "crop/product/papyrus_fiber", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "fiber", "fiber_papyrus"), CT_MISC));
            simpleItems.add(register(r, "crop/product/papyrus_paper", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "paper", "paper_papyrus"), CT_MISC));

            simpleItems.add(register(r, "crop/product/madder", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "crop_madder", "madder"), CT_MISC));
            simpleItems.add(register(r, "crop/product/weld", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "crop_weld", "weld"), CT_MISC));
            simpleItems.add(register(r, "crop/product/woad", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "crop_woad", "woad"), CT_MISC));
            simpleItems.add(register(r, "crop/product/indigo", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "crop_indigo", "indigo"), CT_MISC));
            simpleItems.add(register(r, "crop/product/rape", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "crop_rape", "rape"), CT_MISC));
            simpleItems.add(register(r, "crop/product/hops", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "crop_hops", "hops"), CT_MISC));

            simpleItems.add(register(r, "crop/product/olive_silk_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk_olive"), CT_FOOD));
            simpleItems.add(register(r, "crop/product/olive_papyrus_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus_olive"), CT_FOOD));

            simpleItems.add(register(r, "crop/product/soybean_silk_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk_soybean"), CT_FOOD));
            simpleItems.add(register(r, "crop/product/soybean_papyrus_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus_soybean"), CT_FOOD));

            simpleItems.add(register(r, "crop/product/linseed_silk_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk_linseed"), CT_FOOD));
            simpleItems.add(register(r, "crop/product/linseed_papyrus_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus_linseed"), CT_FOOD));

            simpleItems.add(register(r, "crop/product/rape_seed_silk_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk_rape_seed"), CT_FOOD));
            simpleItems.add(register(r, "crop/product/rape_seed_papyrus_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus_rape_seed"), CT_FOOD));

            simpleItems.add(register(r, "crop/product/sunflower_seed_silk_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk_sunflower_seed"), CT_FOOD));
            simpleItems.add(register(r, "crop/product/sunflower_seed_papyrus_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus_sunflower_seed"), CT_FOOD));

            simpleItems.add(register(r, "crop/product/opium_poppy_seed_silk_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk_opium_poppy_seed"), CT_FOOD));
            simpleItems.add(register(r, "crop/product/opium_poppy_seed_papyrus_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus_opium_poppy_seed"), CT_FOOD));

            simpleItems.add(register(r, "crop/product/sugar_beet_silk_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk_sugar_beet"), CT_FOOD));
            simpleItems.add(register(r, "crop/product/sugar_beet_papyrus_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus_sugar_beet"), CT_FOOD));

            simpleItems.add(register(r, "crop/product/sugar_cane_silk_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk_sugar_cane"), CT_FOOD));
            simpleItems.add(register(r, "crop/product/sugar_cane_papyrus_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus_sugar_cane"), CT_FOOD));

            simpleItems.add(register(r, "crop/product/silk_net", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_silk"), CT_MISC));
            simpleItems.add(register(r, "crop/product/papyrus_net", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_papyrus"), CT_MISC));

            simpleItems.add(register(r, "crop/product/dirty_silk_net", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_silk_dirty"), CT_MISC));
            simpleItems.add(register(r, "crop/product/dirty_papyrus_net", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "net", "net_papyrus_dirty"), CT_MISC));


            simpleItems.add(register(r, "crop/product/silk_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_silk"), CT_MISC));
            simpleItems.add(register(r, "crop/product/papyrus_disc", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "disc", "disc_papyrus"), CT_MISC));


            simpleItems.add(register(r, "devices/flora_density_meter", new ItemFloraDensity(Size.VERY_SMALL, Weight.VERY_LIGHT), CT_MISC));
            simpleItems.add(register(r, "devices/season_clock", new ItemCalendarClock(Size.VERY_SMALL, Weight.VERY_LIGHT), CT_MISC));

        }


        // Containers
        {

            simpleItems.add(register(r, "container/leather_bag_piece", new ItemMisc(Size.SMALL, Weight.LIGHT, "bag_piece", "bag_piece_leather"), CT_MISC));
            simpleItems.add(register(r, "container/leather_bag", new ItemBag("bag", "bag_leather"), CT_MISC));
            simpleItems.add(register(r, "container/pineapple_leather_bag_piece", new ItemMisc(Size.SMALL, Weight.LIGHT, "bag_piece", "bag_piece_pineapple_leather"), CT_MISC));
            simpleItems.add(register(r, "container/pineapple_leather_bag", new ItemBag("bag", "bag_pineapple_leather"), CT_MISC));
            simpleItems.add(register(r, "container/burlap_sack_piece", new ItemMisc(Size.SMALL, Weight.LIGHT, "sack_piece", "sack_piece_burlap"), CT_MISC));
            simpleItems.add(register(r, "container/burlap_sack", new ItemSack("sack", "sack_burlap"), CT_MISC));
            simpleItems.add(register(r, "container/wool_sack_piece", new ItemMisc(Size.SMALL, Weight.LIGHT, "sack_piece", "sack_piece_wool"), CT_MISC));
            simpleItems.add(register(r, "container/wool_sack", new ItemSack("sack", "sack_wool"), CT_MISC));
            simpleItems.add(register(r, "container/silk_sack_piece", new ItemMisc(Size.SMALL, Weight.LIGHT, "sack_piece", "sack_piece_silk"), CT_MISC));
            simpleItems.add(register(r, "container/silk_sack", new ItemSack("sack", "sack_silk"), CT_MISC));
            simpleItems.add(register(r, "container/cotton_sack_piece", new ItemMisc(Size.SMALL, Weight.LIGHT, "sack_piece", "sack_piece_cotton"), CT_MISC));
            simpleItems.add(register(r, "container/cotton_sack", new ItemSack("sack", "sack_cotton"), CT_MISC));
            simpleItems.add(register(r, "container/hemp_sack_piece", new ItemMisc(Size.SMALL, Weight.LIGHT, "sack_piece", "sack_piece_hemp"), CT_MISC));
            simpleItems.add(register(r, "container/hemp_sack", new ItemSack("sack", "sack_hemp"), CT_MISC));
            simpleItems.add(register(r, "container/linen_sack_piece", new ItemMisc(Size.SMALL, Weight.LIGHT, "sack_piece", "sack_piece_linen"), CT_MISC));
            simpleItems.add(register(r, "container/linen_sack", new ItemSack("sack", "sack_linen"), CT_MISC));
            simpleItems.add(register(r, "container/sisal_sack_piece", new ItemMisc(Size.SMALL, Weight.LIGHT, "sack_piece", "sack_piece_sisal"), CT_MISC));
            simpleItems.add(register(r, "container/sisal_sack", new ItemSack("sack", "sack_sisal"), CT_MISC));
            simpleItems.add(register(r, "container/yucca_sack_piece", new ItemMisc(Size.SMALL, Weight.LIGHT, "sack_piece", "sack_piece_yucca"), CT_MISC));
            simpleItems.add(register(r, "container/yucca_sack", new ItemSack("sack", "sack_yucca"), CT_MISC));

        }


        // Armors
        ImmutableList.Builder<TFCItemArmor> armorItems = ImmutableList.builder();
        {
            armorItems.add(register(r, "armor/helmet/pineapple_leather", new TFCItemArmor(ArmorMaterialTFC.PINEAPPLE_LEATHER, 0, EntityEquipmentSlot.HEAD), CT_MISC));
            armorItems.add(register(r, "armor/chestplate/pineapple_leather", new TFCItemArmor(ArmorMaterialTFC.PINEAPPLE_LEATHER, 1, EntityEquipmentSlot.CHEST), CT_MISC));
            armorItems.add(register(r, "armor/leggings/pineapple_leather", new TFCItemArmor(ArmorMaterialTFC.PINEAPPLE_LEATHER, 2, EntityEquipmentSlot.LEGS), CT_MISC));
            armorItems.add(register(r, "armor/boots/pineapple_leather", new TFCItemArmor(ArmorMaterialTFC.PINEAPPLE_LEATHER, 3, EntityEquipmentSlot.FEET), CT_MISC));

            armorItems.add(register(r, "armor/helmet/burlap_cloth", new TFCItemArmor(ArmorMaterialTFC.BURLAP_CLOTH, 0, EntityEquipmentSlot.HEAD), CT_MISC));
            armorItems.add(register(r, "armor/chestplate/burlap_cloth", new TFCItemArmor(ArmorMaterialTFC.BURLAP_CLOTH, 1, EntityEquipmentSlot.CHEST), CT_MISC));
            armorItems.add(register(r, "armor/leggings/burlap_cloth", new TFCItemArmor(ArmorMaterialTFC.BURLAP_CLOTH, 2, EntityEquipmentSlot.LEGS), CT_MISC));
            armorItems.add(register(r, "armor/boots/burlap_cloth", new TFCItemArmor(ArmorMaterialTFC.BURLAP_CLOTH, 3, EntityEquipmentSlot.FEET), CT_MISC));

            armorItems.add(register(r, "armor/helmet/wool_cloth", new TFCItemArmor(ArmorMaterialTFC.WOOL_CLOTH, 0, EntityEquipmentSlot.HEAD), CT_MISC));
            armorItems.add(register(r, "armor/chestplate/wool_cloth", new TFCItemArmor(ArmorMaterialTFC.WOOL_CLOTH, 1, EntityEquipmentSlot.CHEST), CT_MISC));
            armorItems.add(register(r, "armor/leggings/wool_cloth", new TFCItemArmor(ArmorMaterialTFC.WOOL_CLOTH, 2, EntityEquipmentSlot.LEGS), CT_MISC));
            armorItems.add(register(r, "armor/boots/wool_cloth", new TFCItemArmor(ArmorMaterialTFC.WOOL_CLOTH, 3, EntityEquipmentSlot.FEET), CT_MISC));

            armorItems.add(register(r, "armor/helmet/silk_cloth", new TFCItemArmor(ArmorMaterialTFC.SILK_CLOTH, 0, EntityEquipmentSlot.HEAD), CT_MISC));
            armorItems.add(register(r, "armor/chestplate/silk_cloth", new TFCItemArmor(ArmorMaterialTFC.SILK_CLOTH, 1, EntityEquipmentSlot.CHEST), CT_MISC));
            armorItems.add(register(r, "armor/leggings/silk_cloth", new TFCItemArmor(ArmorMaterialTFC.SILK_CLOTH, 2, EntityEquipmentSlot.LEGS), CT_MISC));
            armorItems.add(register(r, "armor/boots/silk_cloth", new TFCItemArmor(ArmorMaterialTFC.SILK_CLOTH, 3, EntityEquipmentSlot.FEET), CT_MISC));

            armorItems.add(register(r, "armor/helmet/sisal_cloth", new TFCItemArmor(ArmorMaterialTFC.SISAL_CLOTH, 0, EntityEquipmentSlot.HEAD), CT_MISC));
            armorItems.add(register(r, "armor/chestplate/sisal_cloth", new TFCItemArmor(ArmorMaterialTFC.SISAL_CLOTH, 1, EntityEquipmentSlot.CHEST), CT_MISC));
            armorItems.add(register(r, "armor/leggings/sisal_cloth", new TFCItemArmor(ArmorMaterialTFC.SISAL_CLOTH, 2, EntityEquipmentSlot.LEGS), CT_MISC));
            armorItems.add(register(r, "armor/boots/sisal_cloth", new TFCItemArmor(ArmorMaterialTFC.SISAL_CLOTH, 3, EntityEquipmentSlot.FEET), CT_MISC));

            armorItems.add(register(r, "armor/helmet/cotton_cloth", new TFCItemArmor(ArmorMaterialTFC.COTTON_CLOTH, 0, EntityEquipmentSlot.HEAD), CT_MISC));
            armorItems.add(register(r, "armor/chestplate/cotton_cloth", new TFCItemArmor(ArmorMaterialTFC.COTTON_CLOTH, 1, EntityEquipmentSlot.CHEST), CT_MISC));
            armorItems.add(register(r, "armor/leggings/cotton_cloth", new TFCItemArmor(ArmorMaterialTFC.COTTON_CLOTH, 2, EntityEquipmentSlot.LEGS), CT_MISC));
            armorItems.add(register(r, "armor/boots/cotton_cloth", new TFCItemArmor(ArmorMaterialTFC.COTTON_CLOTH, 3, EntityEquipmentSlot.FEET), CT_MISC));

            armorItems.add(register(r, "armor/helmet/linen_cloth", new TFCItemArmor(ArmorMaterialTFC.LINEN_CLOTH, 0, EntityEquipmentSlot.HEAD), CT_MISC));
            armorItems.add(register(r, "armor/chestplate/linen_cloth", new TFCItemArmor(ArmorMaterialTFC.LINEN_CLOTH, 1, EntityEquipmentSlot.CHEST), CT_MISC));
            armorItems.add(register(r, "armor/leggings/linen_cloth", new TFCItemArmor(ArmorMaterialTFC.LINEN_CLOTH, 2, EntityEquipmentSlot.LEGS), CT_MISC));
            armorItems.add(register(r, "armor/boots/linen_cloth", new TFCItemArmor(ArmorMaterialTFC.LINEN_CLOTH, 3, EntityEquipmentSlot.FEET), CT_MISC));

            armorItems.add(register(r, "armor/helmet/hemp_cloth", new TFCItemArmor(ArmorMaterialTFC.HEMP_CLOTH, 0, EntityEquipmentSlot.HEAD), CT_MISC));
            armorItems.add(register(r, "armor/chestplate/hemp_cloth", new TFCItemArmor(ArmorMaterialTFC.HEMP_CLOTH, 1, EntityEquipmentSlot.CHEST), CT_MISC));
            armorItems.add(register(r, "armor/leggings/hemp_cloth", new TFCItemArmor(ArmorMaterialTFC.HEMP_CLOTH, 2, EntityEquipmentSlot.LEGS), CT_MISC));
            armorItems.add(register(r, "armor/boots/hemp_cloth", new TFCItemArmor(ArmorMaterialTFC.HEMP_CLOTH, 3, EntityEquipmentSlot.FEET), CT_MISC));

            armorItems.add(register(r, "armor/helmet/yucca_canvas", new TFCItemArmor(ArmorMaterialTFC.YUCCA_CANVAS, 0, EntityEquipmentSlot.HEAD), CT_MISC));
            armorItems.add(register(r, "armor/chestplate/yucca_canvas", new TFCItemArmor(ArmorMaterialTFC.YUCCA_CANVAS, 1, EntityEquipmentSlot.CHEST), CT_MISC));
            armorItems.add(register(r, "armor/leggings/yucca_canvas", new TFCItemArmor(ArmorMaterialTFC.YUCCA_CANVAS, 2, EntityEquipmentSlot.LEGS), CT_MISC));
            armorItems.add(register(r, "armor/boots/yucca_canvas", new TFCItemArmor(ArmorMaterialTFC.YUCCA_CANVAS, 3, EntityEquipmentSlot.FEET), CT_MISC));


            allArmorItems = armorItems.build();
        }


        // All simple foods (not meals) just use ItemFood and are registered here
        for (Food food : Food.values()) {
            if (food.getCategory() != Food.Category.MEAL) {
                simpleItems.add(register(r, "food/" + food.name().toLowerCase(), new TFCItemFood(food), CT_FOOD));
            }
        }
        // Complex foods that require special classes go here
        for (Food food : new Food[]{Food.BARLEY_BREAD_SANDWICH, Food.CORNBREAD_SANDWICH, Food.OAT_BREAD_SANDWICH, Food.RICE_BREAD_SANDWICH, Food.RYE_BREAD_SANDWICH, Food.WHEAT_BREAD_SANDWICH}) {
            simpleItems.add(register(r, "food/" + food.name().toLowerCase(), new ItemSandwich(food), CT_FOOD));
        }
        for (Food food : new Food[]{Food.SOUP_GRAIN, Food.SOUP_FRUIT, Food.SOUP_VEGETABLE, Food.SOUP_MEAT, Food.SOUP_DAIRY}) {
            simpleItems.add(register(r, "food/" + food.name().toLowerCase(), new ItemDynamicBowlFood(food), CT_FOOD));
        }
        for (Food food : new Food[]{Food.SALAD_GRAIN, Food.SALAD_FRUIT, Food.SALAD_VEGETABLE, Food.SALAD_MEAT, Food.SALAD_DAIRY}) {
            simpleItems.add(register(r, "food/" + food.name().toLowerCase(), new ItemDynamicBowlFood(food), CT_FOOD));
        }

        //olive oil production
        simpleItems.add(register(r, "food/olive_paste", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT), CT_FOOD)); //not edible

        simpleItems.add(register(r, "firestarter", new ItemFireStarter(), CT_MISC));
        simpleItems.add(register(r, "straw", new ItemMisc(Size.SMALL, Weight.VERY_LIGHT, "kindling", "straw"), CT_MISC));
        simpleItems.add(register(r, "glass_shard", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT), CT_MISC));
        simpleItems.add(register(r, "handstone", new ItemCraftingTool(250, Size.NORMAL, Weight.VERY_HEAVY, "handstone"), CT_MISC));

        simpleItems.add(register(r, "spindle", new ItemCraftingTool(40, Size.NORMAL, Weight.MEDIUM, "spindle"), CT_MISC));

        simpleItems.add(register(r, "bloom/unrefined", new ItemBloom(false), CT_MISC));
        simpleItems.add(register(r, "bloom/refined", new ItemBloom(true), CT_MISC));

        // Animal Hides
        for (ItemAnimalHide.HideSize size : ItemAnimalHide.HideSize.values()) {
            for (ItemAnimalHide.HideType type : ItemAnimalHide.HideType.values()) {
                simpleItems.add(register(r, ("hide/" + type.name() + "/" + size.name()).toLowerCase(), new ItemAnimalHide(type, size), CT_MISC));
            }
        }

        simpleItems.add(register(r, "quiver", new ItemQuiver(), CT_MISC));

        simpleItems.add(register(r, "animal/product/wool", new ItemMisc(Size.SMALL, Weight.LIGHT), CT_MISC));
        simpleItems.add(register(r, "animal/product/wool_yarn", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "string"), CT_MISC));
        simpleItems.add(register(r, "animal/product/wool_cloth", new ItemMisc(Size.SMALL, Weight.LIGHT, "cloth_high_quality"), CT_MISC));
        simpleItems.add(register(r, "animal/product/silk_cloth", new ItemMisc(Size.SMALL, Weight.LIGHT, "cloth_high_quality"), CT_MISC));

        simpleItems.add(register(r, "dye/black", new ItemMisc(Size.TINY, Weight.LIGHT, "dye_black"), CT_MISC));
        simpleItems.add(register(r, "dye/blue", new ItemMisc(Size.TINY, Weight.LIGHT, "dye_blue"), CT_MISC));
        simpleItems.add(register(r, "dye/white", new ItemMisc(Size.TINY, Weight.LIGHT, "dye_white"), CT_MISC));
        simpleItems.add(register(r, "dye/brown", new ItemMisc(Size.TINY, Weight.LIGHT, "dye_brown"), CT_MISC));
        simpleItems.add(register(r, "alabaster_brick", new ItemMisc(Size.VERY_SMALL, Weight.LIGHT), CT_MISC));
        simpleItems.add(register(r, "glue", new ItemMisc(Size.TINY, Weight.LIGHT, "slimeball", "glue"), CT_MISC));

        simpleItems.add(register(r, "wrought_iron_grill", new ItemMisc(Size.LARGE, Weight.HEAVY, "grill"), CT_MISC));

        // Florae Start
        simpleItems.add(register(r, "pomace", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "pomace", "category_fruit"), CT_MISC));
        simpleItems.add(register(r, "yeast", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "yeast"), CT_MISC));

        simpleItems.add(register(r, "firma_cola_mix", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "mix_firma_cola"), CT_MISC));
        simpleItems.add(register(r, "firma_cola_oils", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "oils_firma_cola"), CT_MISC));
        simpleItems.add(register(r, "firma_cola_blend", new ItemMisc(Size.VERY_SMALL, Weight.VERY_LIGHT, "blend_firma_cola"), CT_MISC));

        // Tools
        simpleItems.add(register(r, "tools/walking_stick", new ItemWalkingStick(Item.ToolMaterial.WOOD, 1f, 1.5f, 0.02f, 96, "stick_wood", "walking_stick"), CT_MISC));

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


        // Dried Berries & Fruits
        /*for (Fruits fruit : Fruits.values())
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



        // Normal Tree Nuts

        if (!TFCFlorae.FirmaLifeAdded)
        {
            simpleItems.add(register(r, "food/acorn", new ItemFoodTFC(Food.UNCRACKED_NUT, "acorn", "category_fruit"), CT_FOOD));
            simpleItems.add(register(r, "food/acorn_nut", new ItemFoodTFC(Food.NUT, "acorn_nut", "category_fruit"), CT_FOOD));
            simpleItems.add(register(r, "food/roasted/acorn_nut", new ItemFoodTFC(Food.ROASTED_NUT, "roasted_acorn_nut", "category_fruit"), CT_FOOD));
        }


        if (!TFCFlorae.FirmaLifeAdded)
        {
            simpleItems.add(register(r, "food/chestnut", new ItemFoodTFC(Food.UNCRACKED_NUT, "chestnut", "category_fruit"), CT_FOOD));
            simpleItems.add(register(r, "food/chestnut_nut", new ItemFoodTFC(Food.NUT, "chestnut_nut", "category_fruit"), CT_FOOD));
            simpleItems.add(register(r, "food/roasted/chestnut_nut", new ItemFoodTFC(Food.ROASTED_NUT, "roasted_chestnut_nut", "category_fruit"), CT_FOOD));
        }


        if (!TFCFlorae.FirmaLifeAdded)
        {
            simpleItems.add(register(r, "food/hickory_nut", new ItemFoodTFC(Food.UNCRACKED_NUT, "hickory_nut", "category_fruit"), CT_FOOD));
            simpleItems.add(register(r, "food/hickory_nut_nut", new ItemFoodTFC(Food.NUT, "hickory_nut_nut", "category_fruit"), CT_FOOD));
            simpleItems.add(register(r, "food/roasted/hickory_nut_nut", new ItemFoodTFC(Food.ROASTED_NUT, "roasted_hickory_nut_nut", "category_fruit"), CT_FOOD));

            simpleItems.add(register(r, "food/pecan", new ItemFoodTFC(Food.UNCRACKED_NUT, "pecan", "category_fruit"), CT_FOOD));
            simpleItems.add(register(r, "food/pecan_nut", new ItemFoodTFC(Food.NUT, "pecan_nut", "category_fruit"), CT_FOOD));
            simpleItems.add(register(r, "food/roasted/pecan_nut", new ItemFoodTFC(Food.ROASTED_NUT, "roasted_pecan_nut", "category_fruit"), CT_FOOD));

            simpleItems.add(register(r, "food/pinecone", new ItemFoodTFC(Food.UNCRACKED_NUT, "pinecone", "category_fruit"), CT_FOOD));
            simpleItems.add(register(r, "food/pine_nut", new ItemFoodTFC(Food.NUT, "pine_nut", "category_fruit"), CT_FOOD));
            simpleItems.add(register(r, "food/roasted/pine_nut", new ItemFoodTFC(Food.ROASTED_NUT, "roasted_pine_nut", "category_fruit"), CT_FOOD));
        }

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
        }*/

        for (IFruitTree fruitTree : FruitTree.values()) {
            String name = fruitTree.getName().toLowerCase();

            // Poles
            if (true) // if firmalife !added
            {
                ItemMisc pole = new ItemMisc(Size.SMALL, Weight.MEDIUM);
                simpleItems.add(register(r, "wood/fruit_tree/pole/" + name, pole, CT_WOOD));
                OreDictionary.registerOre(OreDictionaryHelper.toString("pole_" + name.substring(0, 1).toLowerCase() + name.substring(1).toLowerCase()), pole);
            }

            // Lumber
            ItemMisc lumber = new ItemMisc(Size.SMALL, Weight.VERY_LIGHT);
            simpleItems.add(register(r, "wood/fruit_tree/lumber/" + name, lumber, CT_WOOD));
            OreDictionary.registerOre(OreDictionaryHelper.toString("lumber_" + name.substring(0, 1).toLowerCase() + name.substring(1).toLowerCase()), lumber);

        }


        //allItemBows = itemBows.build();

        // Florae End

        allSimpleItems = simpleItems.build();

        allLumberItems = lumberItems.build();
        allBoatItems = boatItems.build();

        allClayMolds = clayMolds.build();
        allEarthenwareMolds = earthenwareMolds.build();
        allKaoliniteMolds = kaoliniteMolds.build();
        allStonewareMolds = stonewareMolds.build();

        OreDictionaryHelper.init();
    }

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerVanillaOverrides(RegistryEvent.Register<Item> event) {
        // Vanilla Overrides. Used for small tweaks on vanilla items, rather than replacing them outright
        TerraFirmaCraft.getLog().info("The below warnings about unintended overrides are normal. The override is intended. ;)");
        event.getRegistry().registerAll(
                new ItemSnow(Blocks.SNOW_LAYER).setRegistryName("minecraft", "snow_layer"),
                new TFCItemGlassBottle().setRegistryName(Items.GLASS_BOTTLE.getRegistryName()).setTranslationKey("glassBottle"),
                new ItemFlint(Size.VERY_SMALL, Weight.VERY_LIGHT).setRegistryName(Items.FLINT.getRegistryName()).setTranslationKey("flint")
//            new ItemBlockStickBundle(BlocksTFC.STICK_BUNDLE).setRegistryName(MOD_ID, "stick_bundle")
        );

        if (ConfigTFC.General.OVERRIDES.enableTorchOverride) {
            event.getRegistry().register(new ItemBlockTorch(Blocks.TORCH).setRegistryName("minecraft", "torch"));
        }
    }

    private static void registerPottery(Builder<Item> items, IForgeRegistry<Item> r, String nameUnfired, String nameFired) {
        registerPottery(items, r, nameUnfired, nameFired, new ItemPottery(), new ItemPottery());
    }

    private static void registerPottery(Builder<Item> items, IForgeRegistry<Item> r, String nameUnfired, String nameFired, ItemPottery unfiredItem, ItemPottery firedItem) {
        register(r, nameFired, firedItem, CT_POTTERY);
        register(r, nameUnfired, unfiredItem, CT_POTTERY);

        if (items != null) {
            items.add(firedItem, unfiredItem);
        }
    }

    @SuppressWarnings("ConstantConditions")
    private static void registerItemBlock(IForgeRegistry<Item> r, ItemBlock item) {
        item.setRegistryName(item.getBlock().getRegistryName());
        item.setCreativeTab(item.getBlock().getCreativeTab());
        r.register(item);
    }

    private static <T extends Item> T register(IForgeRegistry<Item> r, String name, T item, CreativeTabs ct) {
        item.setRegistryName(MOD_ID, name);
        item.setTranslationKey(MOD_ID + "." + name.replace('/', '.'));
        item.setCreativeTab(ct);
        r.register(item);
        return item;
    }
}
