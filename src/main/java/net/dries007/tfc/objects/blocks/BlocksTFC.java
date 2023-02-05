/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import gregtech.api.GregTechAPI;
import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.capability.food.FoodData;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Plant;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.types.Rock.Type;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.api.types.Wood;
import net.dries007.tfc.api.util.FallingBlockManager;
import net.dries007.tfc.compat.gregtech.materials.TFCMaterialFlags;
import net.dries007.tfc.objects.blocks.agriculture.*;
import net.dries007.tfc.objects.blocks.devices.*;
import net.dries007.tfc.objects.blocks.groundcover.*;
import net.dries007.tfc.objects.blocks.metal.TFCBlockCladding;
import net.dries007.tfc.objects.blocks.metal.TFCBlockLamp;
import net.dries007.tfc.objects.blocks.metal.TFCBlockMetalAnvil;
import net.dries007.tfc.objects.blocks.metal.TFCBlockMetalTrapDoor;
import net.dries007.tfc.objects.blocks.plants.BlockPlant.TFCBlockPlantDummy1;
import net.dries007.tfc.objects.blocks.plants.*;
import net.dries007.tfc.objects.blocks.rock.*;
import net.dries007.tfc.objects.blocks.rock.clay.TFCBlockFireClay;
import net.dries007.tfc.objects.blocks.rock.clay.TFCBlockEarthenwareClay;
import net.dries007.tfc.objects.blocks.rock.clay.TFCBlockKaoliniteClay;
import net.dries007.tfc.objects.blocks.rock.clay.TFCBlockStonewareClay;
import net.dries007.tfc.objects.blocks.wood.*;
import net.dries007.tfc.objects.blocks.wood.tree.TFCBlockLeaves;
import net.dries007.tfc.objects.blocks.wood.tree.TFCBlockLog;
import net.dries007.tfc.objects.blocks.wood.tree.TFCBlockSapling;
import net.dries007.tfc.objects.fluids.FluidsTFC;
import net.dries007.tfc.objects.fluids.properties.FluidWrapper;
import net.dries007.tfc.objects.items.food.ItemBlockRot;
import net.dries007.tfc.objects.items.food.PotionEffectToHave;
import net.dries007.tfc.objects.items.itemblock.*;
import net.dries007.tfc.objects.te.*;
import net.dries007.tfc.types.DefaultPlants;
import net.dries007.tfc.util.agriculture.BerryBush;
import net.dries007.tfc.util.agriculture.Crop;
import net.dries007.tfc.util.agriculture.FruitTree;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGravel;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;
import static net.dries007.tfc.api.types.Rock.Type.*;
import static net.dries007.tfc.objects.CreativeTabsTFC.*;
import static net.dries007.tfc.util.Helpers.getNull;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = MOD_ID)
@GameRegistry.ObjectHolder(MOD_ID)
public final class BlocksTFC {
    @GameRegistry.ObjectHolder("ceramics/fired/large_vessel")
    public static final BlockLargeVessel FIRED_LARGE_VESSEL = getNull();
    @GameRegistry.ObjectHolder("ceramics/earthenware/fired/large_vessel")
    public static final BlockLargeVessel FIRED_EARTHENWARE_LARGE_VESSEL = getNull();
    @GameRegistry.ObjectHolder("ceramics/kaolinite/fired/large_vessel")
    public static final BlockLargeVessel FIRED_KAOLINITE_LARGE_VESSEL = getNull();
    @GameRegistry.ObjectHolder("ceramics/stoneware/fired/large_vessel")
    public static final BlockLargeVessel FIRED_STONEWARE_LARGE_VESSEL = getNull();

    @GameRegistry.ObjectHolder("alabaster/bricks/plain")
    public static final BlockDecorativeStone ALABASTER_BRICKS_PLAIN = getNull();
    @GameRegistry.ObjectHolder("alabaster/polished/plain")
    public static final BlockDecorativeStone ALABASTER_POLISHED_PLAIN = getNull();
    @GameRegistry.ObjectHolder("alabaster/raw/plain")
    public static final BlockDecorativeStone ALABASTER_RAW_PLAIN = getNull();
    @GameRegistry.ObjectHolder("devices/stick_bundle")
    public static final BlockStickBundle STICK_BUNDLE = getNull();
    @GameRegistry.ObjectHolder("devices/dryer")
    public static final BlockDryer DRYER = getNull();

    @GameRegistry.ObjectHolder("storage/urn")
    public static final BlockUrn FIRED_URN = getNull();
    @GameRegistry.ObjectHolder("storage/urn_loot")
    public static final BlockUrnLoot URN_LOOT = getNull();
    @GameRegistry.ObjectHolder("storage/crate")
    public static final BlockCrate CRATE = getNull();

    public static final BlockDebug DEBUG = getNull();
    public static final BlockPeat PEAT = getNull();
    public static final BlockPeat PEAT_GRASS = getNull();
    public static final BlockFirePit FIREPIT = getNull();
    public static final BlockThatch THATCH = getNull();
    public static final BlockThatchBed THATCH_BED = getNull();
    public static final BlockPitKiln PIT_KILN = getNull();
    public static final BlockPlacedItemFlat PLACED_ITEM_FLAT = getNull();
    public static final BlockPlacedItem PLACED_ITEM = getNull();
    public static final BlockPlacedHide PLACED_HIDE = getNull();
    public static final BlockCharcoalPile CHARCOAL_PILE = getNull();
    public static final BlockNestBox NEST_BOX = getNull();
    public static final TFCBlockLogPile LOG_PILE = getNull();
    public static final BlockCharcoalForge CHARCOAL_FORGE = getNull();
    public static final BlockCrucible CRUCIBLE = getNull();
    public static final BlockMolten MOLTEN = getNull();
    public static final BlockBlastFurnace BLAST_FURNACE = getNull();
    public static final BlockBloom BLOOM = getNull();
    public static final BlockBloomery BLOOMERY = getNull();
    public static final BlockQuern QUERN = getNull();
    public static final BlockIceTFC SEA_ICE = getNull();
    public static final BlockPowderKeg POWDERKEG = getNull();
    public static final BlockGravel AGGREGATE = getNull();
    public static final Block FIRE_BRICKS = getNull();

    @GameRegistry.ObjectHolder("groundcover/bone")
    public static final TFCBlockSurfaceBones BONES = getNull();
    @GameRegistry.ObjectHolder("groundcover/driftwood")
    public static final TFCBlockDriftwood DRIFTWOOD = getNull();
    @GameRegistry.ObjectHolder("groundcover/flint")
    public static final TFCBlockSurfaceFlint FLINT = getNull();
    @GameRegistry.ObjectHolder("groundcover/pinecone")
    public static final TFCBlockSurfacePinecone PINECONE = getNull();
    @GameRegistry.ObjectHolder("groundcover/seashell")
    public static final TFCBlockSurfaceSeashells SEASHELLS = getNull();
    @GameRegistry.ObjectHolder("groundcover/twig")
    public static final TFCBlockSurfaceTwig TWIG = getNull();

//    @GameRegistry.ObjectHolder("wood/fruit_tree/log/cassia_cinnamon")
//    public static final BlockCassiaCinnamonLog CASSIA_CINNAMON_LOG = getNull();
//    @GameRegistry.ObjectHolder("wood/fruit_tree/leaves/cassia_cinnamon")
//    public static final BlockCassiaCinnamonLeaves CASSIA_CINNAMON_LEAVES = getNull();
//    @GameRegistry.ObjectHolder("wood/fruit_tree/sapling/cassia_cinnamon")
//    public static final BlockCassiaCinnamonSapling CASSIA_CINNAMON_SAPLING = getNull();
//    @GameRegistry.ObjectHolder("wood/fruit_tree/log/ceylon_cinnamon")
//    public static final BlockCeylonCinnamonLog CEYLON_CINNAMON_LOG = getNull();
//    @GameRegistry.ObjectHolder("wood/fruit_tree/leaves/ceylon_cinnamon")
//    public static final BlockCeylonCinnamonLeaves CEYLON_CINNAMON_LEAVES = getNull();
//    @GameRegistry.ObjectHolder("wood/fruit_tree/sapling/ceylon_cinnamon")
//    public static final BlockCeylonCinnamonSapling CEYLON_CINNAMON_SAPLING = getNull();

    // Bales
    @GameRegistry.ObjectHolder("crop/bales/yucca/yucca_bale")
    public static final BlockBale YUCCA_BALE = getNull();
    @GameRegistry.ObjectHolder("crop/bales/yucca/yucca_fiber_bale")
    public static final BlockBale YUCCA_FIBER_BALE = getNull();
    @GameRegistry.ObjectHolder("crop/bales/cotton/cotton_bale")
    public static final BlockBale COTTON_BALE = getNull();
    @GameRegistry.ObjectHolder("crop/bales/cotton/cotton_yarn_bale")
    public static final BlockBale COTTON_YARN_BALE = getNull();
    @GameRegistry.ObjectHolder("crop/bales/hemp/hemp_bale")
    public static final BlockBale HEMP_BALE = getNull();
    @GameRegistry.ObjectHolder("crop/bales/hemp/hemp_fiber_bale")
    public static final BlockBale HEMP_FIBER_BALE = getNull();
    @GameRegistry.ObjectHolder("crop/bales/jute/jute_bale")
    public static final BlockBale JUTE_BALE = getNull();
    @GameRegistry.ObjectHolder("crop/bales/jute/jute_fiber_bale")
    public static final BlockBale JUTE_FIBER_BALE = getNull();
    @GameRegistry.ObjectHolder("crop/bales/linen/linen_bale")
    public static final BlockBale LINEN_BALE = getNull();
    @GameRegistry.ObjectHolder("crop/bales/linen/linen_string_bale")
    public static final BlockBale LINEN_FIBER_BALE = getNull();
    @GameRegistry.ObjectHolder("crop/bales/papyrus/papyrus_fiber_bale")
    public static final BlockBale PAPYRUS_FIBER_BALE = getNull();
    @GameRegistry.ObjectHolder("crop/bales/silk/silk_string_bale")
    public static final BlockBale SILK_STRING_BALE = getNull();
    @GameRegistry.ObjectHolder("crop/bales/sisal/sisal_fiber_bale")
    public static final BlockBale SISAL_FIBER_BALE = getNull();

    // Bamboo Blocks
//    @GameRegistry.ObjectHolder("wood/log/arrow_bamboo")
//    public static final BlockBambooLog ARROW_BAMBOO_LOG = getNull();
//    @GameRegistry.ObjectHolder("wood/leaves/arrow_bamboo")
//    public static final BlockBambooLeaves ARROW_BAMBOO_LEAVES = getNull();
//    @GameRegistry.ObjectHolder("wood/log/black_bamboo")
//    public static final BlockBambooLog BLACK_BAMBOO_LOG = getNull();
//    @GameRegistry.ObjectHolder("wood/leaves/black_bamboo")
//    public static final BlockBambooLeaves BLACK_BAMBOO_LEAVES = getNull();
//    @GameRegistry.ObjectHolder("wood/log/blue_bamboo")
//    public static final BlockBambooLog BLUE_BAMBOO_LOG = getNull();
//    @GameRegistry.ObjectHolder("wood/leaves/blue_bamboo")
//    public static final BlockBambooLeaves BLUE_BAMBOO_LEAVES = getNull();
//    @GameRegistry.ObjectHolder("wood/log/dragon_bamboo")
//    public static final BlockBambooLog DRAGON_BAMBOO_LOG = getNull();
//    @GameRegistry.ObjectHolder("wood/leaves/dragon_bamboo")
//    public static final BlockBambooLeaves DRAGON_BAMBOO_LEAVES = getNull();
//    @GameRegistry.ObjectHolder("wood/log/golden_bamboo")
//    public static final BlockBambooLog GOLDEN_BAMBOO_LOG = getNull();
//    @GameRegistry.ObjectHolder("wood/leaves/golden_bamboo")
//    public static final BlockBambooLeaves GOLDEN_BAMBOO_LEAVES = getNull();
//    @GameRegistry.ObjectHolder("wood/log/narrow_leaf_bamboo")
//    public static final BlockBambooLog NARROW_LEAF_BAMBOO_LOG = getNull();
//    @GameRegistry.ObjectHolder("wood/leaves/narrow_leaf_bamboo")
//    public static final BlockBambooLeaves NARROW_LEAF_BAMBOO_LEAVES = getNull();
//    @GameRegistry.ObjectHolder("wood/log/red_bamboo")
//    public static final BlockBambooLog RED_BAMBOO_LOG = getNull();
//    @GameRegistry.ObjectHolder("wood/leaves/red_bamboo")
//    public static final BlockBambooLeaves RED_BAMBOO_LEAVES = getNull();
//    @GameRegistry.ObjectHolder("wood/log/temple_bamboo")
//    public static final BlockBambooLog TEMPLE_BAMBOO_LOG = getNull();
//    @GameRegistry.ObjectHolder("wood/leaves/temple_bamboo")
//    public static final BlockBambooLeaves TEMPLE_BAMBOO_LEAVES = getNull();
//    @GameRegistry.ObjectHolder("wood/log/thorny_bamboo")
//    public static final BlockBambooLog THORNY_BAMBOO_LOG = getNull();
//    @GameRegistry.ObjectHolder("wood/leaves/thorny_bamboo")
//    public static final BlockBambooLeaves THORNY_BAMBOO_LEAVES = getNull();
//    @GameRegistry.ObjectHolder("wood/log/timber_bamboo")
//    public static final BlockBambooLog TIMBER_BAMBOO_LOG = getNull();
//    @GameRegistry.ObjectHolder("wood/leaves/timber_bamboo")
//    public static final BlockBambooLeaves TIMBER_BAMBOO_LEAVES = getNull();
//    @GameRegistry.ObjectHolder("wood/log/tinwa_bamboo")
//    public static final BlockBambooLog TINWA_BAMBOO_LOG = getNull();
//    @GameRegistry.ObjectHolder("wood/leaves/tinwa_bamboo")
//    public static final BlockBambooLeaves TINWA_BAMBOO_LEAVES = getNull();
//    @GameRegistry.ObjectHolder("wood/log/weavers_bamboo")
//    public static final BlockBambooLog WEAVERS_BAMBOO_LOG = getNull();
//    @GameRegistry.ObjectHolder("wood/leaves/weavers_bamboo")
//    public static final BlockBambooLeaves WEAVERS_BAMBOO_LEAVES = getNull();

    @GameRegistry.ObjectHolder("coral/brain/dead")
    public static final TFCBlockCoral BRAIN_CORAL_DEAD = getNull();
    @GameRegistry.ObjectHolder("coral/bubble/dead")
    public static final TFCBlockCoral BUBBLE_CORAL_DEAD = getNull();
    @GameRegistry.ObjectHolder("coral/fire/dead")
    public static final TFCBlockCoral FIRE_CORAL_DEAD = getNull();
    @GameRegistry.ObjectHolder("coral/horn/dead")
    public static final TFCBlockCoral HORN_CORAL_DEAD = getNull();
    @GameRegistry.ObjectHolder("coral/tube/dead")
    public static final TFCBlockCoral TUBE_CORAL_DEAD = getNull();

    @GameRegistry.ObjectHolder("coral/fan/brain/dead")
    public static final TFCBlockCoral BRAIN_CORAL_FAN_DEAD = getNull();
    @GameRegistry.ObjectHolder("coral/fan/bubble/dead")
    public static final TFCBlockCoral BUBBLE_CORAL_FAN_DEAD = getNull();
    @GameRegistry.ObjectHolder("coral/fan/fire/dead")
    public static final TFCBlockCoral FIRE_CORAL_FAN_DEAD = getNull();
    @GameRegistry.ObjectHolder("coral/fan/horn/dead")
    public static final TFCBlockCoral HORN_CORAL_FAN_DEAD = getNull();
    @GameRegistry.ObjectHolder("coral/fan/tube/dead")
    public static final TFCBlockCoral TUBE_CORAL_FAN_DEAD = getNull();

    @GameRegistry.ObjectHolder("coral/block/brain/dead")
    public static final TFCBlockCoralBlock BRAIN_CORAL_BLOCK_DEAD = getNull();
    @GameRegistry.ObjectHolder("coral/block/bubble/dead")
    public static final TFCBlockCoralBlock BUBBLE_CORAL_BLOCK_DEAD = getNull();
    @GameRegistry.ObjectHolder("coral/block/fire/dead")
    public static final TFCBlockCoralBlock FIRE_CORAL_BLOCK_DEAD = getNull();
    @GameRegistry.ObjectHolder("coral/block/horn/dead")
    public static final TFCBlockCoralBlock HORN_CORAL_BLOCK_DEAD = getNull();
    @GameRegistry.ObjectHolder("coral/block/tube/dead")
    public static final TFCBlockCoralBlock TUBE_CORAL_BLOCK_DEAD = getNull();

    @GameRegistry.ObjectHolder("plants/glowing_sea_banana")
    public static final TFCBlockWaterGlowPlant GLOWING_SEA_BANANA = getNull();
    @GameRegistry.ObjectHolder("plants/blueshroom")
    public static final TFCBlockCaveMushroom BLUESHROOM = getNull();
    @GameRegistry.ObjectHolder("plants/glowshroom")
    public static final TFCBlockCaveMushroom GLOWSHROOM = getNull();
    @GameRegistry.ObjectHolder("plants/magma_shroom")
    public static final TFCBlockCaveMushroom MAGMA_SHROOM = getNull();
    @GameRegistry.ObjectHolder("plants/poison_shroom")
    public static final TFCBlockCaveMushroom POISON_SHROOM = getNull();
    @GameRegistry.ObjectHolder("plants/sulphur_shroom")
    public static final TFCBlockCaveMushroom SULPHUR_SHROOM = getNull();

    @GameRegistry.ObjectHolder("groundcover/lightstone")
    public static final TFCBlockLightstone LIGHTSTONE = getNull();

    @GameRegistry.ObjectHolder("plants/saguaro_cactus")
    public static final TFCBlockSaguaroCactus SAGUARO_CACTUS = getNull();



    //=== ImmutableList ==============================================================================================//
    // All these are for use in model registration. Do not use for block lookups.
    // Use the static get methods in the classes instead.
    private static ImmutableList<ItemBlock> allNormalItemBlocks = getNull();
    private static ImmutableList<ItemBlock> allInventoryItemBlocks = getNull();
    private static ImmutableList<ItemBlock> allColorizedItemBlocks = getNull();

    //=== Agriculture ================================================================================================//

    private static ImmutableList<TFCBlockBerryBush> allBlockBerryBush = getNull();
    private static ImmutableList<TFCBlockCrop> allBlockCrop = getNull();
    private static ImmutableList<TFCBlockCropDead> allBlockCropDead = getNull();
    private static ImmutableList<TFCBlockFruitTreeBranch> allBlockFruitTreeBranch = getNull();
    private static ImmutableList<TFCBlockFruitTreeLeaves> allBlockFruitTreeLeaves = getNull();
    private static ImmutableList<TFCBlockFruitTreeSapling> allBlockFruitTreeSapling = getNull();
    private static ImmutableList<TFCBlockFruitTreeTrunk> allBlockFruitTreeTrunk = getNull();

    //=== Groundcover ================================================================================================//

    private static ImmutableList<TFCBlockCoral> allBlockCoral = getNull();
    private static ImmutableList<TFCBlockDriftwood> allBlockDriftwood = getNull();
    private static ImmutableList<TFCBlockLightstone> allBlockLightstone = getNull();
    private static ImmutableList<TFCBlockSurfacePinecone> allBlockSurfacePinecone = getNull();
    private static ImmutableList<TFCBlockSurfaceBones> allBlockSurfaceBones = getNull();
    private static ImmutableList<TFCBlockSurfaceFlint> allBlockSurfaceFlint = getNull();
    private static ImmutableList<TFCBlockSurfaceRock> allBlockSurfaceRock = getNull();
    private static ImmutableList<TFCBlockSurfaceSeashells> allBlockSurfaceSeashells = getNull();
    private static ImmutableList<TFCBlockSurfaceTwig> allBlockSurfaceTwig = getNull();

    //=== Metal ======================================================================================================//

    private static ImmutableList<TFCBlockCladding> allBlockCladding = getNull();
    private static ImmutableList<TFCBlockLamp> allBlockLamps = getNull();
    private static ImmutableList<TFCBlockMetalAnvil> allBlockMetalAnvil = getNull();
    private static ImmutableList<TFCBlockMetalTrapDoor> allBlockMetalTrapDoor = getNull();

    //=== Plants =====================================================================================================//

    private static ImmutableList<TFCBlockPlantDummy1> allStandardBlocks = getNull();
    private static ImmutableList<TFCBlockCreepingPlant> allBlockCreepingPlant = getNull();
    private static ImmutableList<TFCBlockHangingCreepingPlant> allBlockHangingCreepingPlant = getNull();
    private static ImmutableList<TFCBlockHangingGlowingCreepingPlant> allBlockHangingGlowingCreepingPlant = getNull();
    private static ImmutableList<TFCBlockHangingGlowingPlant> allBlockHangingGlowingPlant = getNull();
    private static ImmutableList<TFCBlockHangingPlant> allBlockHangingPlant = getNull();
    private static ImmutableList<TFCBlockPlant> allBlockPlant = getNull();
    private static ImmutableList<TFCBlockSaguaroCactus> allBlockSaguaroCactus = getNull();
    private static ImmutableList<TFCBlockShortGrass> allBlockShortGrass = getNull();
    private static ImmutableList<TFCBlockWaterGlowPlant> allBlockWaterGlowPlant = getNull();
    private static ImmutableList<TFCBlockTallGrassWater> allBlockTallGrassWater = getNull();
    private static ImmutableList<TFCBlockWaterPlant> allBlockWaterPlant = getNull();

    //=== Rock =======================================================================================================//

    private static ImmutableList<TFCBlockRockSlab.Half> allBlockRockSlab = getNull();
    private static ImmutableList<TFCBlockRockStairs> allBlockRockStairs = getNull();
    private static ImmutableList<TFCBlockRockVariant> allBlockRockVariant = getNull();
    private static ImmutableList<TFCBlockRockWall> allBlockRockWall = getNull();

    //=== Bamboo =====================================================================================================//

//    private static ImmutableList<BlockBambooLeaves> allBlockBambooLeaves = getNull();
//    private static ImmutableList<BlockBambooLog> allBlockBambooLog = getNull();
//    private static ImmutableList<BlockBambooSapling> allBlockBambooSapling = getNull();

    //=== Cinnamon ===================================================================================================//

//    private static ImmutableList<BlockCassiaCinnamonLeaves> allBlockCassiaCinnamonLeaves = getNull();
//    private static ImmutableList<BlockCassiaCinnamonLog> allBlockCassiaCinnamonLog = getNull();
//    private static ImmutableList<BlockCassiaCinnamonSapling> allBlockCassiaCinnamonSapling = getNull();

//    private static ImmutableList<BlockCeylonCinnamonLeaves> allBlockCeylonCinnamonLeaves = getNull();
//    private static ImmutableList<BlockCeylonCinnamonLog> allBlockCeylonCinnamonLog = getNull();
//    private static ImmutableList<BlockCeylonCinnamonSapling> allBlockCeylonCinnamonSapling = getNull();


    //=== Joshua Tree ================================================================================================//

//    private static ImmutableList<BlockJoshuaTreeFlower> allBlockJoshuaTreeFlower = getNull();
//    private static ImmutableList<BlockJoshuaTreeLog> allBlockJoshuaTreeLog = getNull();
//    private static ImmutableList<BlockJoshuaTreeSapling> allBlockJoshuaTreeSapling = getNull();

    //=== Tree =======================================================================================================//

    private static ImmutableList<TFCBlockLeaves> allBlockLeaves = getNull();
    private static ImmutableList<TFCBlockLog> allBlockLog = getNull();
    private static ImmutableList<TFCBlockSapling> allBlockSapling = getNull();

    //=== Wood =======================================================================================================//

    private static ImmutableList<TFCBlockBarrel> allBlockBarrel = getNull();
    private static ImmutableList<TFCBlockBookshelf> allBlockBookshelf = getNull();
    private static ImmutableList<TFCBlockChest> allBlockChest = getNull();
    private static ImmutableList<TFCBlockFence> allBlockFence = getNull();
    private static ImmutableList<TFCBlockFenceGateLog> allBlockFenceGateLog = getNull();
    private static ImmutableList<TFCBlockFenceGate> allBlockFenceGate = getNull();
    private static ImmutableList<TFCBlockLoom> allBlockLoom = getNull();
    private static ImmutableList<TFCBlockPlank> allBlockPlank = getNull();
    private static ImmutableList<TFCBlockToolRack> allBlockToolRack = getNull();
    private static ImmutableList<TFCBlockWoodButton> allBlockWoodButton = getNull();
    private static ImmutableList<TFCBlockWoodDoor> allBlockWoodDoor = getNull();
    private static ImmutableList<TFCBlockWoodPressurePlate> allBlockWoodPressurePlate = getNull();
    private static ImmutableList<TFCBlockWoodSlab.Half> allBlockWoodSlab = getNull();
    private static ImmutableList<TFCBlockWoodStairs> allBlockWoodStairs = getNull();
    private static ImmutableList<TFCBlockWoodSupport> allBlockWoodSupport = getNull();
    private static ImmutableList<TFCBlockWoodTrapDoor> allBlockWoodTrapDoor = getNull();
    private static ImmutableList<TFCBlockWorkbench> allBlockWorkbench = getNull();

    //=== Other ======================================================================================================//

    private static ImmutableList<TFCBlockFlowerPot> allFlowerPots = getNull();
    private static ImmutableList<BlockFluidBase> allBlockFluidBase = getNull();



    //=== ImmutableList getAll =======================================================================================//

    public static ImmutableList<ItemBlock> getAllNormalItemBlocks() { return allNormalItemBlocks; }
    public static ImmutableList<ItemBlock> getAllInventoryItemBlocks() { return allInventoryItemBlocks; }
    public static ImmutableList<ItemBlock> getAllColorizedItemBlocks() { return allColorizedItemBlocks; }

    //=== Agriculture ================================================================================================//

    public static ImmutableList<TFCBlockBerryBush> getAllBlockBerryBush() { return allBlockBerryBush; }
    public static ImmutableList<TFCBlockCrop> getAllBlockCrop() { return allBlockCrop; }
    public static ImmutableList<TFCBlockCropDead> getAllBlockCropDead() { return allBlockCropDead; }
    public static ImmutableList<TFCBlockFruitTreeBranch> getAllBlockFruitTreeBranch() { return allBlockFruitTreeBranch; }
    public static ImmutableList<TFCBlockFruitTreeLeaves> getAllBlockFruitTreeLeaves() { return allBlockFruitTreeLeaves; }
    public static ImmutableList<TFCBlockFruitTreeSapling> getAllBlockFruitTreeSapling() { return allBlockFruitTreeSapling; }
    public static ImmutableList<TFCBlockFruitTreeTrunk> getAllBlockFruitTreeTrunk() { return allBlockFruitTreeTrunk; }

    //=== Groundcover ================================================================================================//

    public static ImmutableList<TFCBlockCoral> getAllBlockCoral() { return allBlockCoral; }
    public static ImmutableList<TFCBlockDriftwood> getAllBlockDriftwood() { return allBlockDriftwood; }
    public static ImmutableList<TFCBlockLightstone> getAllBlockLightstone() { return allBlockLightstone; }
    public static ImmutableList<TFCBlockSurfacePinecone> getAllBlockSurfacePinecone() { return allBlockSurfacePinecone; }
    public static ImmutableList<TFCBlockSurfaceBones> getAllBlockSurfaceBones() { return allBlockSurfaceBones; }
    public static ImmutableList<TFCBlockSurfaceFlint> getAllBlockSurfaceFlint() { return allBlockSurfaceFlint; }
    public static ImmutableList<TFCBlockSurfaceRock> getAllBlockSurfaceRock() { return allBlockSurfaceRock; }
    public static ImmutableList<TFCBlockSurfaceSeashells> getAllBlockSurfaceSeashells() { return allBlockSurfaceSeashells; }
    public static ImmutableList<TFCBlockSurfaceTwig> getAllBlockSurfaceTwig() { return allBlockSurfaceTwig; }

    //=== Metal ======================================================================================================//

    public static ImmutableList<TFCBlockCladding> getAllBlockCladding() { return allBlockCladding; }
    public static ImmutableList<TFCBlockLamp> getAllBlockLamps() { return allBlockLamps; }
    public static ImmutableList<TFCBlockMetalAnvil> getAllBlockMetalAnvil() { return allBlockMetalAnvil; }
    public static ImmutableList<TFCBlockMetalTrapDoor> getAllBlockMetalTrapDoor() { return allBlockMetalTrapDoor; }

    //=== Plants =====================================================================================================//

    public static ImmutableList<TFCBlockPlantDummy1> getAllStandardBlocks() { return allStandardBlocks; }
    public static ImmutableList<TFCBlockCreepingPlant> getAllBlockCreepingPlant() { return allBlockCreepingPlant; }
    public static ImmutableList<TFCBlockHangingCreepingPlant> getAllBlockHangingCreepingPlant() { return allBlockHangingCreepingPlant; }
    public static ImmutableList<TFCBlockHangingGlowingCreepingPlant> getAllBlockHangingGlowingCreepingPlant() { return allBlockHangingGlowingCreepingPlant; }
    public static ImmutableList<TFCBlockHangingGlowingPlant> getAllBlockHangingGlowingPlant() { return allBlockHangingGlowingPlant; }
    public static ImmutableList<TFCBlockHangingPlant> getAllBlockHangingPlant() { return allBlockHangingPlant; }
    public static ImmutableList<TFCBlockPlant> getAllBlockPlant() { return allBlockPlant; }
    public static ImmutableList<TFCBlockSaguaroCactus> getAllBlockSaguaroCactus() { return allBlockSaguaroCactus; }
    public static ImmutableList<TFCBlockShortGrass> getAllBlockShortGrass() { return allBlockShortGrass; }
    public static ImmutableList<TFCBlockTallGrassWater> getAllBlockTallGrassWater() { return allBlockTallGrassWater; }
    public static ImmutableList<TFCBlockWaterGlowPlant> getAllBlockWaterGlowPlant() { return allBlockWaterGlowPlant; }
    public static ImmutableList<TFCBlockWaterPlant> getAllBlockWaterPlant() { return allBlockWaterPlant; }

    //=== Rock =======================================================================================================//

    public static ImmutableList<TFCBlockRockSlab.Half> getAllBlockRockSlab() { return allBlockRockSlab; }
    public static ImmutableList<TFCBlockRockStairs> getAllBlockRockStairs() { return allBlockRockStairs; }
    public static ImmutableList<TFCBlockRockVariant> getAllBlockRockVariant() { return allBlockRockVariant; }
    public static ImmutableList<TFCBlockRockWall> getAllBlockRockWall() { return allBlockRockWall; }

    //=== Bamboo =====================================================================================================//

//    public static ImmutableList<BlockBambooLeaves> getAllBlockBambooLeaves() { return allBlockBambooLeaves; }
//    public static ImmutableList<BlockBambooLog> getAllBlockBambooLog() { return allBlockBambooLog; }
//    public static ImmutableList<BlockBambooSapling> getAllBlockBambooSapling() { return allBlockBambooSapling; }

    //=== Cinnamon ===================================================================================================//

//    public static ImmutableList<BlockCassiaCinnamonLeaves> getAllBlockCassiaCinnamonLeaves() { return allBlockCassiaCinnamonLeaves; }
//    public static ImmutableList<BlockCassiaCinnamonLog> getAllBlockCassiaCinnamonLog() { return allBlockCassiaCinnamonLog; }
//    public static ImmutableList<BlockCassiaCinnamonSapling> getAllBlockCassiaCinnamonSapling() { return allBlockCassiaCinnamonSapling; }

//    public static ImmutableList<BlockCeylonCinnamonLeaves> getAllBlockCeylonCinnamonLeaves() { return allBlockCeylonCinnamonLeaves; }
//    public static ImmutableList<BlockCeylonCinnamonLog> getAllBlockCeylonCinnamonLog() { return allBlockCeylonCinnamonLog; }
//    public static ImmutableList<BlockCeylonCinnamonSapling> getAllBlockCeylonCinnamonSapling() { return allBlockCeylonCinnamonSapling; }

    //=== Joshua Tree ================================================================================================//

//    public static ImmutableList<BlockJoshuaTreeFlower> getAllJoshuaTreeFlowerBlocks() { return allBlockJoshuaTreeFlower; }
//    public static ImmutableList<BlockJoshuaTreeLog> getAllJoshuaTreeLogBlocks() { return allBlockJoshuaTreeLog; }
//    public static ImmutableList<BlockJoshuaTreeSapling> getAllJoshuaTreeSaplingBlocks() { return allBlockJoshuaTreeSapling; }

    //=== Tree =======================================================================================================//

    public static ImmutableList<TFCBlockLeaves> getAllBlockLeaves() {return allBlockLeaves;}
    public static ImmutableList<TFCBlockLog> getAllBlockLog() {return allBlockLog;}
    public static ImmutableList<TFCBlockSapling> getAllBlockSapling() {return allBlockSapling;}

    //=== Wood =======================================================================================================//

    public static ImmutableList<TFCBlockBarrel> getAllBlockBarrel() { return allBlockBarrel; }
    public static ImmutableList<TFCBlockBookshelf> getAllBlockBookshelf() { return allBlockBookshelf; }
    public static ImmutableList<TFCBlockChest> getAllBlockChest() { return allBlockChest; }
    public static ImmutableList<TFCBlockFence> getAllBlockFence() { return allBlockFence; }
    public static ImmutableList<TFCBlockFenceGate> getAllBlockFenceGate() { return allBlockFenceGate; }
    public static ImmutableList<TFCBlockFenceGateLog> getAllBlockFenceGateLog() { return allBlockFenceGateLog; }
    public static ImmutableList<TFCBlockLoom> getAllBlockLoom() { return allBlockLoom; }
    public static ImmutableList<TFCBlockPlank> getAllBlockPlank() { return allBlockPlank; }
    public static ImmutableList<TFCBlockToolRack> getAllBlockToolRack() { return allBlockToolRack; }
    public static ImmutableList<TFCBlockWoodButton> getAllBlockWoodButton() { return allBlockWoodButton; }
    public static ImmutableList<TFCBlockWoodDoor> getAllBlockWoodDoor() { return allBlockWoodDoor; }
    public static ImmutableList<TFCBlockWoodPressurePlate> getAllBlockWoodPressurePlate() { return allBlockWoodPressurePlate; }
    public static ImmutableList<TFCBlockWoodSlab.Half> getAllBlockWoodSlab() { return allBlockWoodSlab; }
    public static ImmutableList<TFCBlockWoodStairs> getAllBlockWoodStairs() { return allBlockWoodStairs; }
    public static ImmutableList<TFCBlockWoodSupport> getAllBlockWoodSupport() { return allBlockWoodSupport; }
    public static ImmutableList<TFCBlockWoodTrapDoor> getAllBlockWoodTrapDoor() { return allBlockWoodTrapDoor; }
    public static ImmutableList<TFCBlockWorkbench> getAllBlockWorkbench() { return allBlockWorkbench; }

    //=== Other ======================================================================================================//

    public static ImmutableList<TFCBlockFlowerPot> getAllFlowerPots() { return allFlowerPots; }
    public static ImmutableList<BlockFluidBase> getAllBlockFluidBase() { return allBlockFluidBase; }






//    public static String[] bamboo = {"arrow_bamboo", "black_bamboo", "blue_bamboo", "dragon_bamboo", "golden_bamboo", "narrow_leaf_bamboo", "red_bamboo", "temple_bamboo", "thorny_bamboo", "timber_bamboo", "tinwa_bamboo", "weavers_bamboo"};
//    public static Tree[] bambooTrees = {DefaultTrees.ARROW_BAMBOO, DefaultTrees.BLACK_BAMBOO, DefaultTrees.BLUE_BAMBOO, DefaultTrees.DRAGON_BAMBOO, DefaultTrees.GOLDEN_BAMBOO, DefaultTrees.NARROW_LEAF_BAMBOO, DefaultTrees.RED_BAMBOO, DefaultTrees.TEMPLE_BAMBOO, DefaultTrees.THORNY_BAMBOO, DefaultTrees.TIMBER_BAMBOO, DefaultTrees.TINWA_BAMBOO, DefaultTrees.WEAVERS_BAMBOO};

    @SubscribeEvent
    @SuppressWarnings("ConstantConditions")
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        // This is called here because it needs to wait until Metal registry has fired
        FluidsTFC.registerFluids();
        IForgeRegistry<Block> r = event.getRegistry();

        Builder<ItemBlock> normalItemBlocks = ImmutableList.builder();
        Builder<ItemBlock> colorizedItemBlocks = ImmutableList.builder();
        Builder<ItemBlock> inventoryItemBlocks = ImmutableList.builder();

        ImmutableList.Builder<Block> itemBambooLog = ImmutableList.builder();
        ImmutableList.Builder<Block> itemBambooLeaves = ImmutableList.builder();
        ImmutableList.Builder<Block> itemBambooSapling = ImmutableList.builder();
        ImmutableList.Builder<Block> foodItemBlocks = ImmutableList.builder();
        ImmutableList.Builder<TFCBlockSurfaceRock> surfaceRock = ImmutableList.builder();
        ImmutableList.Builder<TFCBlockSurfaceSeashells> surfaceSeashell = ImmutableList.builder();
        ImmutableList.Builder<TFCBlockSurfaceFlint> surfaceFlint = ImmutableList.builder();
        ImmutableList.Builder<TFCBlockSurfaceBones> surfaceBone = ImmutableList.builder();
        ImmutableList.Builder<TFCBlockDriftwood> surfaceDriftwood = ImmutableList.builder();
        ImmutableList.Builder<TFCBlockSurfaceTwig> surfaceTwig = ImmutableList.builder();
        ImmutableList.Builder<TFCBlockSurfacePinecone> surfacePinecone = ImmutableList.builder();
        ImmutableList.Builder<TFCBlockPebbleWater> pebbleWater = ImmutableList.builder();
        ImmutableList.Builder<TFCBlockCoral> plantCoral = ImmutableList.builder();
        ImmutableList.Builder<TFCBlockWaterGlowPlant> plantGlowWater = ImmutableList.builder();
        ImmutableList.Builder<TFCBlockLightstone> blockLightstone = ImmutableList.builder();
        ImmutableList.Builder<ItemBlockCondenser> itemBlockCondenser = ImmutableList.builder();
        //ImmutableList.Builder<MultiBlockBase> multiBlock = ImmutableList.builder();


        normalItemBlocks.add(new TFCItemBlock(register(r, "debug", new BlockDebug(), CT_MISC)));

        normalItemBlocks.add(new TFCItemBlock(register(r, "aggregate", new BlockAggregate(), CT_ROCK_BLOCKS)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "fire_clay_block", new TFCBlockFireClay(), CT_ROCK_BLOCKS)));

        normalItemBlocks.add(new TFCItemBlock(register(r, "peat", new BlockPeat(Material.GROUND), CT_ROCK_BLOCKS)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "peat_grass", new BlockPeatGrass(Material.GRASS), CT_ROCK_BLOCKS)));

        normalItemBlocks.add(new TFCItemBlock(register(r, "thatch", new BlockThatch(), CT_DECORATIONS)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "fire_bricks", new BlockFireBrick(), CT_DECORATIONS)));

        normalItemBlocks.add(new TFCItemBlock(register(r, "quern", new BlockQuern(), CT_MISC)));
        normalItemBlocks.add(new ItemBlockCrucible(register(r, "crucible", new BlockCrucible(), CT_MISC)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "blast_furnace", new BlockBlastFurnace(), CT_MISC)));
        normalItemBlocks.add(new ItemBlockDryer(register(r, "devices/dryer", new BlockDryer(), CT_MISC)));
        normalItemBlocks.add(new ItemBlockStickBundle(register(r, "devices/stick_bundle", new BlockStickBundle(), CT_MISC)));

        inventoryItemBlocks.add(new TFCItemBlock(register(r, "bellows", new BlockBellows(), CT_MISC)));
        inventoryItemBlocks.add(new TFCItemBlock(register(r, "bloomery", new BlockBloomery(), CT_MISC)));
        inventoryItemBlocks.add(new TFCItemBlock(register(r, "nest_box", new BlockNestBox(), CT_MISC)));
        inventoryItemBlocks.add(new ItemBlockSluice(register(r, "sluice", new BlockSluice(), CT_MISC)));

        normalItemBlocks.add(new TFCItemBlock(register(r, "sea_ice", new BlockIceTFC(FluidsTFC.SEA_WATER.get()), CT_MISC)));

        normalItemBlocks.add(new ItemBlockLargeVessel(register(r, "ceramics/fired/large_vessel", new BlockLargeVessel(), CT_POTTERY)));
        normalItemBlocks.add(new ItemBlockPowderKeg(register(r, "powderkeg", new BlockPowderKeg(), CT_WOOD)));

        normalItemBlocks.add(new TFCItemBlock(register(r, "alabaster/raw/plain", new BlockDecorativeStone(MapColor.SNOW), CT_DECORATIONS)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "alabaster/polished/plain", new BlockDecorativeStone(MapColor.SNOW), CT_DECORATIONS)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "alabaster/bricks/plain", new BlockDecorativeStone(MapColor.SNOW), CT_DECORATIONS)));

        normalItemBlocks.add(new TFCItemBlock(register(r, "ceramics/earthenware/earthenware_clay_block", new TFCBlockEarthenwareClay(), CT_ROCK_BLOCKS)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "ceramics/earthenware/earthenware_bricks", new BlockFireBrick(), CT_DECORATIONS)));
        normalItemBlocks.add(new ItemBlockLargeVessel(register(r, "ceramics/earthenware/fired/large_vessel", new BlockLargeVessel(), CT_POTTERY)));

        normalItemBlocks.add(new TFCItemBlock(register(r, "ceramics/kaolinite/kaolinite_clay_block", new TFCBlockKaoliniteClay(), CT_ROCK_BLOCKS)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "ceramics/kaolinite/kaolinite_bricks", new BlockFireBrick(), CT_DECORATIONS)));
        normalItemBlocks.add(new ItemBlockLargeVessel(register(r, "ceramics/kaolinite/fired/large_vessel", new BlockLargeVessel(), CT_POTTERY)));

        normalItemBlocks.add(new TFCItemBlock(register(r, "ceramics/stoneware/stoneware_clay_block", new TFCBlockStonewareClay(), CT_ROCK_BLOCKS)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "ceramics/stoneware/stoneware_bricks", new BlockFireBrick(), CT_DECORATIONS)));
        normalItemBlocks.add(new ItemBlockLargeVessel(register(r, "ceramics/stoneware/fired/large_vessel", new BlockLargeVessel(), CT_POTTERY)));

        normalItemBlocks.add(new ItemBlockUrn(register(r, "storage/urn", new BlockUrn(), CT_POTTERY)));
        normalItemBlocks.add(new ItemBlockUrnLoot(register(r, "storage/urn_loot", new BlockUrnLoot(), CT_POTTERY)));
        normalItemBlocks.add(new ItemBlockCrate(register(r, "storage/crate", new BlockCrate(), CT_DECORATIONS)));

        for (EnumDyeColor dyeColor : EnumDyeColor.values()) {
            BlockDecorativeStone polished = new BlockDecorativeStone(MapColor.getBlockColor(dyeColor));
            BlockDecorativeStone bricks = new BlockDecorativeStone(MapColor.getBlockColor(dyeColor));
            BlockDecorativeStone raw = new BlockDecorativeStone(MapColor.getBlockColor(dyeColor));

            normalItemBlocks.add(new TFCItemBlock(register(r, "alabaster/polished/" + dyeColor.getName(), polished, CT_DECORATIONS)));
            normalItemBlocks.add(new TFCItemBlock(register(r, "alabaster/bricks/" + dyeColor.getName(), bricks, CT_DECORATIONS)));
            normalItemBlocks.add(new TFCItemBlock(register(r, "alabaster/raw/" + dyeColor.getName(), raw, CT_DECORATIONS)));

            BlockDecorativeStone.ALABASTER_POLISHED.put(dyeColor, polished);
            BlockDecorativeStone.ALABASTER_BRICKS.put(dyeColor, bricks);
            BlockDecorativeStone.ALABASTER_RAW.put(dyeColor, raw);
        }

        // Fluid
        // Apparently this is the way we're supposed to do things even though the fluid registry defaults. So we'll do it this way.
        Builder<BlockFluidBase> fluids = ImmutableList.builder();
        {
            fluids.add(
                    register(r, "fluid/hot_water", new TFCBlockFluidHotWater()),
                    register(r, "fluid/fresh_water", new TFCBlockFluidWater(FluidsTFC.FRESH_WATER.get(), Material.WATER, false)),
                    register(r, "fluid/sea_water", new TFCBlockFluidWater(FluidsTFC.SEA_WATER.get(), Material.WATER, true)),
                    register(r, "fluid/distilled_water", new TFCBlockFluid(FluidsTFC.DISTILLED_WATER.get(), Material.WATER, false)),
                    register(r, "fluid/waste", new TFCBlockFluid(FluidsTFC.WASTE.get(), Material.WATER, false)),
                    register(r, "fluid/base_potash_liquor", new TFCBlockFluid(FluidsTFC.BASE_POTASH_LIQUOR.get(), Material.WATER, false)),
                    register(r, "fluid/sweet_sap", new TFCBlockFluid(FluidsTFC.SWEET_SAP.get(), Material.WATER, false)),
                    register(r, "fluid/sweet_syrup", new TFCBlockFluid(FluidsTFC.SWEET_SYRUP.get(), Material.WATER, false)),
                    register(r, "fluid/resin", new TFCBlockFluid(FluidsTFC.RESIN.get(), Material.WATER, false)),
                    register(r, "fluid/kino", new TFCBlockFluid(FluidsTFC.KINO.get(), Material.WATER, false)),
                    register(r, "fluid/salammoniac", new TFCBlockFluid(FluidsTFC.SALAMMONIAC.get(), Material.WATER, false))
            );
            for (FluidWrapper wrapper : FluidsTFC.getAllOtherFiniteFluids()) {
                fluids.add(register(r, "fluid/" + wrapper.get().getName(), new TFCBlockFluid(wrapper.get(), Material.WATER, false)));
            }
            for (FluidWrapper wrapper : FluidsTFC.getAllFermentedAlcoholsFluids()) {
                fluids.add(register(r, "fluid/" + wrapper.get().getName(), new TFCBlockFluid(wrapper.get(), Material.WATER, false)));
            }
            for (FluidWrapper wrapper : FluidsTFC.getAllAlcoholsFluids()) {
                fluids.add(register(r, "fluid/" + wrapper.get().getName(), new TFCBlockFluid(wrapper.get(), Material.WATER, false)));
            }
            for (FluidWrapper wrapper : FluidsTFC.getAllBeerFluids()) {
                fluids.add(register(r, "fluid/" + wrapper.get().getName(), new TFCBlockFluid(wrapper.get(), Material.WATER, false)));
            }
            for (FluidWrapper wrapper : FluidsTFC.getAllTeaFluids()) {
                fluids.add(register(r, "fluid/" + wrapper.get().getName(), new TFCBlockFluid(wrapper.get(), Material.WATER, false)));
            }
            for (FluidWrapper wrapper : FluidsTFC.getAllCoffeeFluids()) {
                fluids.add(register(r, "fluid/" + wrapper.get().getName(), new TFCBlockFluid(wrapper.get(), Material.WATER, false)));
            }
            for (FluidWrapper wrapper : FluidsTFC.getAllJuiceBerryFluids()) {
                fluids.add(register(r, "fluid/" + wrapper.get().getName(), new TFCBlockFluid(wrapper.get(), Material.WATER, false)));
            }
            for (FluidWrapper wrapper : FluidsTFC.getAllJuiceFruitFluids()) {
                fluids.add(register(r, "fluid/" + wrapper.get().getName(), new TFCBlockFluid(wrapper.get(), Material.WATER, false)));
            }
            for (FluidWrapper wrapper : FluidsTFC.getAllMiscFluids()) {
                fluids.add(register(r, "fluid/" + wrapper.get().getName(), new TFCBlockFluid(wrapper.get(), Material.WATER, false)));
            }
            for (EnumDyeColor color : EnumDyeColor.values()) {
                FluidWrapper wrapper = FluidsTFC.getFluidFromDye(color);
                fluids.add(register(r, "fluid/" + wrapper.get().getName(), new TFCBlockFluid(wrapper.get(), Material.WATER, false)));
            }
            allBlockFluidBase = fluids.build();
        }

        // Rock Variant
        Builder<TFCBlockRockVariant> blockRockVariant = ImmutableList.builder();
        {
            for (Type type : values()) {
                for (Rock rock : TFCRegistries.ROCKS.getValuesCollection()) {
                    if (type != ANVIL) {
                        blockRockVariant.add(register(r, type.name().toLowerCase() + "/" + rock.getRegistryName().getPath(), TFCBlockRockVariant.create(rock, type), CT_ROCK_BLOCKS));
                    } else if (rock.getRockCategory().hasAnvil()) {
                        // Anvil registration is special, is has it's own folder
                        register(r, "anvil/" + rock.getRegistryName().getPath(), TFCBlockRockVariant.create(rock, type));
                    }
                }
            }
            allBlockRockVariant = blockRockVariant.build();
            allBlockRockVariant.forEach(x ->
            {
                if (x.getType() == SAND) {
                    normalItemBlocks.add(new ItemBlockHeat(x, 1, 600));
                } else if (x.getType() != SPIKE && x.getType() != ANVIL) {
                    normalItemBlocks.add(new TFCItemBlock(x));
                }
            });

            {
                // Add resultingState to the registered collapsable blocks.
                for (Rock rock : TFCRegistries.ROCKS.getValuesCollection()) {
                    for (Rock.Type type : values()) {
                        FallingBlockManager.Specification spec = type.getFallingSpecification();
                        switch (type) {
                            case ANVIL:
                                if (!rock.getRockCategory().hasAnvil()) {
                                    break;
                                }
                            case RAW:
                                spec = new FallingBlockManager.Specification(spec);
                                spec.setResultingState(TFCBlockRockVariant.get(rock, COBBLE).getDefaultState());
                                FallingBlockManager.registerFallable(TFCBlockRockVariant.get(rock, RAW), spec);
                                break;
                            case SMOOTH:
                                spec = new FallingBlockManager.Specification(spec);
                                spec.setResultingState(TFCBlockRockVariant.get(rock, COBBLE).getDefaultState());
                                FallingBlockManager.registerFallable(TFCBlockRockVariant.get(rock, SMOOTH).getDefaultState().withProperty(TFCBlockRockSmooth.CAN_FALL, true), spec);
                                break;
                            default:
                                Rock.Type nonGrassType = type.getNonGrassVersion();
                                if (nonGrassType != type) {
                                    spec = new FallingBlockManager.Specification(spec);
                                    spec.setResultingState(TFCBlockRockVariant.get(rock, nonGrassType).getDefaultState());
                                }
                                FallingBlockManager.registerFallable(TFCBlockRockVariant.get(rock, type), spec);
                        }
                    }
                }
            }
        }

        // Wood
        Builder<TFCBlockPlank> blockPlanks = ImmutableList.builder();
        Builder<TFCBlockWorkbench> blockWorkbenches = ImmutableList.builder();
        Builder<TFCBlockBookshelf> blockBookshelfs = ImmutableList.builder();
        Builder<TFCBlockFence> blockFence = ImmutableList.builder();
        Builder<TFCBlockFenceGate> blockFenceGates = ImmutableList.builder();
        Builder<TFCBlockFenceGateLog> blockFenceGatesLog = ImmutableList.builder();
        Builder<TFCBlockWoodPressurePlate> blockWoodPressurePlate = ImmutableList.builder();
        Builder<TFCBlockWoodButton> blockWoodButton = ImmutableList.builder();
        Builder<TFCBlockWoodDoor> blockDoors = ImmutableList.builder();
        Builder<TFCBlockWoodTrapDoor> blockTrapDoors = ImmutableList.builder();
        Builder<TFCBlockChest> blockChests = ImmutableList.builder();
        Builder<TFCBlockToolRack> blockToolRacks = ImmutableList.builder();
        Builder<TFCBlockBarrel> blockBarrel = ImmutableList.builder();
        Builder<TFCBlockLoom> blockLooms = ImmutableList.builder();
        Builder<TFCBlockWoodSupport> blockSupports = ImmutableList.builder();
        Builder<TFCBlockWoodStairs> blockWoodStairs = new Builder<>();
        Builder<TFCBlockWoodSlab.Half> blockWoodSlab = new Builder<>();
        {
            for (Wood wood : TFCRegistries.WOODS.getValuesCollection()) {
                blockPlanks.add(register(r, "wood/planks/" + wood.getRegistryName().getPath(), new TFCBlockPlank(wood), CT_WOOD));
                blockWorkbenches.add(register(r, "wood/workbench/" + wood.getRegistryName().getPath(), new TFCBlockWorkbench(wood), CT_DECORATIONS));
                blockBookshelfs.add(register(r, "wood/bookshelf/" + wood.getRegistryName().getPath(), new TFCBlockBookshelf(wood), CT_DECORATIONS));
                blockFence.add(register(r, "wood/fence/" + wood.getRegistryName().getPath(), new TFCBlockFence(wood), CT_DECORATIONS));
                blockFenceGates.add(register(r, "wood/fence_gate/" + wood.getRegistryName().getPath(), new TFCBlockFenceGate(wood), CT_DECORATIONS));
                blockFenceGatesLog.add(register(r, "wood/fence_gate_log/" + wood.getRegistryName().getPath(), new TFCBlockFenceGateLog(wood), CT_DECORATIONS));
                blockWoodPressurePlate.add(register(r, "wood/pressure_plate/" + wood.getRegistryName().getPath().toLowerCase(), new TFCBlockWoodPressurePlate(wood), CT_DECORATIONS));
                blockWoodButton.add(register(r, "wood/button/" + wood.getRegistryName().getPath(), new TFCBlockWoodButton(wood), CT_DECORATIONS));
                blockDoors.add(register(r, "wood/door/" + wood.getRegistryName().getPath(), new TFCBlockWoodDoor(wood), CT_DECORATIONS));
                blockTrapDoors.add(register(r, "wood/trapdoor/" + wood.getRegistryName().getPath(), new TFCBlockWoodTrapDoor(wood), CT_DECORATIONS));
                blockChests.add(register(r, "wood/chest/" + wood.getRegistryName().getPath(), new TFCBlockChest(TFCBlockChest.TFCBASIC, wood), CT_DECORATIONS));
                blockChests.add(register(r, "wood/chest_trap/" + wood.getRegistryName().getPath(), new TFCBlockChest(TFCBlockChest.TFCTRAP, wood), CT_DECORATIONS));
                blockToolRacks.add(register(r, "wood/tool_rack/" + wood.getRegistryName().getPath(), new TFCBlockToolRack(wood), CT_DECORATIONS));
                blockBarrel.add(register(r, "wood/barrel/" + wood.getRegistryName().getPath(), new TFCBlockBarrel(wood), CT_DECORATIONS));
                blockLooms.add(register(r, "wood/loom/" + wood.getRegistryName().getPath(), new TFCBlockLoom(wood), CT_WOOD));
                blockSupports.add(register(r, "wood/support/" + wood.getRegistryName().getPath(), new TFCBlockWoodSupport(wood), CT_WOOD));
                blockWoodStairs.add(register(r, "wood/stairs/" + wood.getRegistryName().getPath(), new TFCBlockWoodStairs(wood), CT_DECORATIONS));
                blockWoodSlab.add(register(r, "wood/slab/" + wood.getRegistryName().getPath(), new TFCBlockWoodSlab.Half(wood), CT_DECORATIONS));
                register(r, "wood/double_slab/" + wood.getRegistryName().getPath(), new TFCBlockWoodSlab.Double(wood));

            }

            allBlockPlank = blockPlanks.build();
            allBlockWorkbench = blockWorkbenches.build();
            allBlockBookshelf = blockBookshelfs.build();
            allBlockFence = blockFence.build();
            allBlockFenceGate = blockFenceGates.build();
            allBlockFenceGateLog = blockFenceGatesLog.build();
            allBlockWoodPressurePlate = blockWoodPressurePlate.build();
            allBlockWoodButton = blockWoodButton.build();
            allBlockWoodDoor = blockDoors.build();
            allBlockWoodTrapDoor = blockTrapDoors.build();
            allBlockChest = blockChests.build();
            allBlockToolRack = blockToolRacks.build();
            allBlockBarrel = blockBarrel.build();
            allBlockLoom = blockLooms.build();
            allBlockWoodSupport = blockSupports.build();
            allBlockWoodStairs = blockWoodStairs.build();
            allBlockWoodSlab = blockWoodSlab.build();

            allBlockPlank.forEach(x -> colorizedItemBlocks.add(new TFCItemBlock(x)));
            allBlockWorkbench.forEach(x -> colorizedItemBlocks.add(new TFCItemBlock(x)));
            allBlockBookshelf.forEach(x -> colorizedItemBlocks.add(new TFCItemBlock(x)));
            allBlockFence.forEach(x -> colorizedItemBlocks.add(new TFCItemBlock(x)));
            allBlockFenceGate.forEach(x -> colorizedItemBlocks.add(new TFCItemBlock(x)));
            allBlockFenceGateLog.forEach((x) -> colorizedItemBlocks.add(new TFCItemBlock(x)));
            allBlockWoodPressurePlate.forEach(x -> colorizedItemBlocks.add(new TFCItemBlock(x)));
            allBlockWoodButton.forEach(x -> colorizedItemBlocks.add(new TFCItemBlock(x)));
            allBlockWoodTrapDoor.forEach(x -> colorizedItemBlocks.add(new TFCItemBlock(x)));
            allBlockChest.forEach(x -> colorizedItemBlocks.add(new TFCItemBlock(x)));
            allBlockToolRack.forEach(x -> colorizedItemBlocks.add(new TFCItemBlock(x)));
            allBlockBarrel.forEach(x -> colorizedItemBlocks.add(new ItemBlockBarrel(x)));
            allBlockLoom.forEach(x -> colorizedItemBlocks.add(new TFCItemBlock(x)));
            allBlockWoodSupport.forEach(x -> colorizedItemBlocks.add(new TFCItemBlock(x)));
            allBlockWoodStairs.forEach(x -> colorizedItemBlocks.add(new TFCItemBlock(x)));
            allBlockWoodSlab.forEach(x -> colorizedItemBlocks.add(new TFCItemBlock(x)));
        }


        // Tree
        Builder<TFCBlockLog> blockLogs = ImmutableList.builder();
        Builder<TFCBlockLeaves> blockLeaves = ImmutableList.builder();
        Builder<TFCBlockSapling> blockSaplings = ImmutableList.builder();
        {
            for (Tree tree : TFCRegistries.TREES.getValuesCollection()) {
                blockLogs.add(register(r, "wood/log/" + tree.getRegistryName().getPath(), new TFCBlockLog(tree), CT_WOOD));
                blockLeaves.add(register(r, "wood/leaves/" + tree.getRegistryName().getPath(), new TFCBlockLeaves(tree), CT_WOOD));
                blockSaplings.add(register(r, "wood/sapling/" + tree.getRegistryName().getPath(), new TFCBlockSapling(tree), CT_WOOD));
            }


            allBlockLog = blockLogs.build();
            allBlockLeaves = blockLeaves.build();
            allBlockSapling = blockSaplings.build();


            allBlockLeaves.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));
            allBlockSapling.forEach(x -> inventoryItemBlocks.add(new ItemBlockSaplingTFC(x)));
        }


        // Rock
        Builder<TFCBlockRockWall> blockRockWall = ImmutableList.builder();
        Builder<TFCBlockRockStairs> blockRockStairs = new Builder<>();
        Builder<TFCBlockRockSlab.Half> blockRockSlab = new Builder<>();
        {
            for (Type type : new Type[]{SMOOTH, COBBLE, BRICKS, MUD_BRICKS, RAW})
                for (Rock rock : TFCRegistries.ROCKS.getValuesCollection()) {
                    blockRockWall.add(register(r, ("wall/" + type.name().toLowerCase() + "/" + rock.getRegistryName().getPath()).toLowerCase(), new TFCBlockRockWall(TFCBlockRockVariant.get(rock, type)), CT_DECORATIONS));
                    blockRockStairs.add(register(r, "stairs/" + (type.name().toLowerCase() + "/" + rock.getRegistryName().getPath()).toLowerCase(), new TFCBlockRockStairs(rock, type), CT_DECORATIONS));
                    blockRockSlab.add(register(r, "slab/" + (type.name().toLowerCase() + "/" + rock.getRegistryName().getPath()).toLowerCase(), new TFCBlockRockSlab.Half(rock, type), CT_DECORATIONS));
                    register(r, "double_slab/" + (type.name().toLowerCase() + "/" + rock.getRegistryName().getPath()).toLowerCase(), new TFCBlockRockSlab.Double(rock, type));
                }

            for (Rock rock : TFCRegistries.ROCKS.getValuesCollection()) {
                // Redstone things
                inventoryItemBlocks.add(new TFCItemBlock(register(r, "stone/button/" + rock.getRegistryName().getPath().toLowerCase(), new TFCBlockRockButton(rock), CT_DECORATIONS)));
                inventoryItemBlocks.add(new TFCItemBlock(register(r, "stone/pressure_plate/" + rock.getRegistryName().getPath().toLowerCase(), new TFCBlockRockPressurePlate(rock), CT_DECORATIONS)));
            }

            allBlockRockWall = blockRockWall.build();
            allBlockRockStairs = blockRockStairs.build();
            allBlockRockSlab = blockRockSlab.build();


            allBlockRockWall.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));
            allBlockRockStairs.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));
        }


        // Metall
        Builder<TFCBlockMetalAnvil> blockAnvils = ImmutableList.builder();
        Builder<TFCBlockCladding> blockCladding = ImmutableList.builder();
        Builder<TFCBlockLamp> blockLamps = ImmutableList.builder();
        Builder<TFCBlockMetalTrapDoor> blockMetalTrapdoors = ImmutableList.builder();
        {
            for (gregtech.api.unification.material.Material material : GregTechAPI.MATERIAL_REGISTRY) {
                if (material.hasFlag(TFCMaterialFlags.GENERATE_ANVIL)) {
                    blockAnvils.add(register(r, "anvil/" + material.getUnlocalizedName(), new TFCBlockMetalAnvil(material), CT_METAL));
                }

                if (material.hasFlag(TFCMaterialFlags.GENERATE_CLADDING)) {
                    blockCladding.add(register(r, "cladding/" + material.getUnlocalizedName(), new TFCBlockCladding(material), CT_METAL));
                }

                if (material.hasFlag(TFCMaterialFlags.GENERATE_TRAPDOOR)) {
                    blockMetalTrapdoors.add(register(r, "trapdoor/" + material.getUnlocalizedName(), new TFCBlockMetalTrapDoor(material), CT_METAL));
                }

                if (material.hasFlag(TFCMaterialFlags.GENERATE_LAMP)) {
                    blockLamps.add(register(r, "lamp/" + material.getUnlocalizedName(), new TFCBlockLamp(material), CT_METAL));
                }
            }

            allBlockMetalAnvil = blockAnvils.build();
            allBlockCladding = blockCladding.build();
            allBlockLamps = blockLamps.build();
            allBlockMetalTrapDoor = blockMetalTrapdoors.build();
        }


        // Crop
        Builder<TFCBlockCrop> blockCrop = ImmutableList.builder();
        Builder<TFCBlockCropDead> blockCropDead = ImmutableList.builder();
        {
            for (Crop crop : Crop.values()) {
                blockCrop.add(register(r, "crop/" + crop.name().toLowerCase(), crop.createGrowingBlock()));
                blockCropDead.add(register(r, "dead_crop/" + crop.name().toLowerCase(), crop.createDeadBlock()));
            }

            allBlockCrop = blockCrop.build();
            allBlockCropDead = blockCropDead.build();
            /*
            for (TFCBlockCrop blockCropWater : allCropBlocks)
            {
                normalItemBlocks.add(new ItemBlockCropWaterTFC((TFCBlockCrop) blockCropWater));
            }
            for (BlockCropDead blockCropWaterDead : allDeadCropBlocks)
            {
                normalItemBlocks.add(new ItemBlockCropDeadWaterTFC((BlockCropDead) blockCropWaterDead));
            }*/
        }


        // Fruit Tree
        Builder<TFCBlockFruitTreeSapling> fruitSaplings = ImmutableList.builder();
        Builder<TFCBlockFruitTreeTrunk> fruitTrunks = ImmutableList.builder();
        Builder<TFCBlockFruitTreeBranch> fruitBranches = ImmutableList.builder();
        Builder<TFCBlockFruitTreeLeaves> fruitLeaves = ImmutableList.builder();
        {
            for (FruitTree tree : FruitTree.values()) {
                fruitSaplings.add(register(r, "fruit_trees/sapling/" + tree.name().toLowerCase(), new TFCBlockFruitTreeSapling(tree), CT_WOOD));
                fruitTrunks.add(register(r, "fruit_trees/trunk/" + tree.name().toLowerCase(), new TFCBlockFruitTreeTrunk(tree)));
                fruitBranches.add(register(r, "fruit_trees/branch/" + tree.name().toLowerCase(), new TFCBlockFruitTreeBranch(tree)));
                fruitLeaves.add(register(r, "fruit_trees/leaves/" + tree.name().toLowerCase(), new TFCBlockFruitTreeLeaves(tree), CT_WOOD));
            }

            allBlockFruitTreeSapling = fruitSaplings.build();
            allBlockFruitTreeTrunk = fruitTrunks.build();
            allBlockFruitTreeBranch = fruitBranches.build();
            allBlockFruitTreeLeaves = fruitLeaves.build();

            allBlockFruitTreeSapling.forEach(x -> inventoryItemBlocks.add(new TFCItemBlock(x)));
            allBlockFruitTreeLeaves.forEach(x -> inventoryItemBlocks.add(new TFCItemBlock(x)));

        }


        // Berry Bush
        Builder<TFCBlockBerryBush> blockBerryBush = ImmutableList.builder();
        {
            for (BerryBush bush : BerryBush.values()) {
                blockBerryBush.add(register(r, "berry_bush/" + bush.name().toLowerCase(), new TFCBlockBerryBush(bush), CT_FOOD));
            }

            allBlockBerryBush = blockBerryBush.build();

            allBlockBerryBush.forEach(x -> inventoryItemBlocks.add(new TFCItemBlock(x)));
        }


        {
            Builder<TFCBlockPlant> blockPlants = ImmutableList.builder();
            Builder<TFCBlockFlowerPot> blockPlantInPot = ImmutableList.builder();

            Builder<TFCBlockHangingPlant> plantHangingBlock = ImmutableList.builder();
            Builder<TFCBlockHangingGlowingPlant> plantHangingGlowingBlock = ImmutableList.builder();
            Builder<TFCBlockHangingCreepingPlant> plantHangingCreepingBlock = ImmutableList.builder();
            Builder<TFCBlockHangingGlowingCreepingPlant> plantHangingGlowingCreepingBlock = ImmutableList.builder();

            for (Plant plant : TFCRegistries.PLANTS.getValuesCollection()) {
                if (plant.getPlantType() == Plant.PlantType.HANGING && (
                        plant == TFCRegistries.PLANTS.getValue(DefaultPlants.BEARDED_MOSS) ||
                                plant == TFCRegistries.PLANTS.getValue(DefaultPlants.GLOW_VINE) ||
                                plant == TFCRegistries.PLANTS.getValue(DefaultPlants.LIANA) ||
                                plant == TFCRegistries.PLANTS.getValue(DefaultPlants.HANGING_VINE) ||
                                plant == TFCRegistries.PLANTS.getValue(DefaultPlants.JUNGLE_VINE))) {
                    if (plant == TFCRegistries.PLANTS.getValue(DefaultPlants.GLOW_VINE)) {
                        plantHangingGlowingBlock.add(register(r, "plants/" + plant.getRegistryName().getPath(), new TFCBlockHangingGlowingPlant(plant), CT_FLORA));
                        //plantHangingGlowingCreepingBlock.add(register(r, "plants/" + plant.getRegistryName().getPath() + "_creeping", new BlockHangingGlowingCreepingPlantTFC(plant), CT_FLORA));
                    } else {
                        plantHangingBlock.add(register(r, "plants/" + plant.getRegistryName().getPath(), new TFCBlockHangingPlant(plant), CT_FLORA));
                        //plantHangingCreepingBlock.add(register(r, "plants/" + plant.getRegistryName().getPath() + "_creeping", new BlockHangingCreepingPlantTFC(plant), CT_FLORA));
                    }
                } else {
                    blockPlants.add(register(r, "plants/" + plant.getRegistryName().getPath(), plant.getPlantType().create(plant), CT_FLORA));
                }
            }

            allBlockPlant = blockPlants.build();
            allBlockPlant.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));

            allBlockHangingPlant = plantHangingBlock.build();
            allBlockHangingPlant.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));

            allBlockHangingGlowingPlant = plantHangingGlowingBlock.build();
            allBlockHangingGlowingPlant.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));

            allBlockHangingCreepingPlant = plantHangingCreepingBlock.build();
            allBlockHangingCreepingPlant.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));

            allBlockHangingGlowingCreepingPlant = plantHangingGlowingCreepingBlock.build();
            allBlockHangingGlowingCreepingPlant.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));


            /*
            for (Plant plant : TFCRegistries.PLANTS.getValuesCollection())
            {
                if (plant.getPlantType() != Plant.PlantType.WATER ||
                        plant.getPlantType() != Plant.PlantType.WATER_SEA ||
                        plant.getPlantType() != Plant.PlantType.TALL_WATER ||
                        plant.getPlantType() != Plant.PlantType.TALL_WATER_SEA ||
                        plant != TFCRegistries.PLANTS.getValue(DefaultPlants.BEARDED_MOSS) ||
                        plant != TFCRegistries.PLANTS.getValue(DefaultPlants.GLOW_VINE) ||
                        plant != TFCRegistries.PLANTS.getValue(DefaultPlants.LIANA) ||
                        plant != TFCRegistries.PLANTS.getValue(DefaultPlants.HANGING_VINE) ||
                        plant != TFCRegistries.PLANTS.getValue(DefaultPlants.JUNGLE_VINE) ||
                        plant != TFCRegistries.PLANTS.getValue(DefaultPlants.SAGUARO_CACTUS))
                {
                    if (plant.getPlantType() != Plant.PlantType.SHORT_GRASS && plant.getPlantType() != Plant.PlantType.TALL_GRASS)
                        b.add(register(r, "plants/" + plant.getRegistryName().getPath(), plant.getPlantType().create(plant), CT_FLORA));
                    if (plant.canBePotted())
                        pots.add(register(r, "flowerpot/" + plant.getRegistryName().getPath(), new BlockFlowerPotTFC(plant)));
                }
            }*/

/*
            for (TFCBlockPlant blockPlant : allPlantBlocks)
            {
                System.out.println(blockPlant);
                if (blockPlant instanceof BlockFloatingWaterTFC)
                {
                    inventoryItemBlocks.add(new ItemBlockFloatingWaterTFC((BlockFloatingWaterTFC) blockPlant));
                }
                else if (blockPlant.getPlant().canBePotted())
                {
                    normalItemBlocks.add(new ItemBlockPlant(blockPlant, blockPlant.getPlant()));
                }
                else
                {
                    normalItemBlocks.add(new ItemBlockTFC(blockPlant));
                }
            }*/
        }

        {
            /*
            Builder<TFCBlockPlant> b = ImmutableList.builder();
            for (Plant plant : TFCRegistries.PLANTS.getValuesCollection())
            {
                if (plant.getPlantType() == Plant.PlantType.SHORT_GRASS || plant.getPlantType() == Plant.PlantType.TALL_GRASS)
                    b.add(register(r, "plants/" + plant.getRegistryName().getPath(), plant.getPlantType().create(plant), CT_FLORA));
            }
            allPlantBlocks = b.build();
            for (TFCBlockPlant blockPlant : allPlantBlocks)
            {
                normalItemBlocks.add(new ItemBlockTFC(blockPlant));
            }*/
        }

        // Registering JEI only blocks (for info)
        inventoryItemBlocks.add(new ItemBlock(register(r, "firepit", new BlockFirePit())));
        inventoryItemBlocks.add(new ItemBlock(register(r, "charcoal_forge", new BlockCharcoalForge())));
        inventoryItemBlocks.add(new ItemBlock(register(r, "pit_kiln", new BlockPitKiln())));
        inventoryItemBlocks.add(new ItemBlock(register(r, "placed_item", new BlockPlacedItem())));
        // technical blocks
        // These have no ItemBlock or Creative Tab
        register(r, "placed_item_flat", new BlockPlacedItemFlat());
        register(r, "placed_hide", new BlockPlacedHide());
        register(r, "charcoal_pile", new BlockCharcoalPile());
        register(r, "log_pile", new TFCBlockLogPile());
        register(r, "molten", new BlockMolten());
        register(r, "bloom", new BlockBloom());
        register(r, "thatch_bed", new BlockThatchBed());

        // Note: if you add blocks you don't need to put them in this list of todos. Feel free to add them where they make sense :)

        // todo: smoke rack (placed with any string, so event based?) + smoke blocks or will we use particles?

        // Florae Start
        /*
        for (Metal metal : TFCRegistries.METALS.getValuesCollection())
        {
            if (metal == TFCRegistries.METALS.getValue((DefaultMetals.COPPER)))
            {
                metalAlembics.add(register(r, "devices/" + "alembic/" + metal.getRegistryName().getPath(), new BlockMetalAlembic(metal), CT_METAL));
                //itemBlockCondenser.add(register(r, "devices/" + "alembic_condenser/" + metal.getRegistryName().getPath(), new BlockMetalAlembicCondenser(metal), CT_METAL));
                itemBlockCondenser.add(new ItemBlockCondenser(register(r, "devices/alembic_condenser/" + metal.getRegistryName().getPath(), new BlockMetalAlembicCondenser(metal), CT_METAL)));

                allBlockMetalAlembics = metalAlembics.build();
                allBlockMetalAlembics.forEach(x -> normalItemBlocks.add(new ItemBlockTFC(x)));

                allItemBlockCondenser = itemBlockCondenser.build();
            }
        }*/

        normalItemBlocks.add(new TFCItemBlock(register(r, "crop/bales/yucca/yucca_bale", new BlockBale(), CT_DECORATIONS)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "crop/bales/yucca/yucca_fiber_bale", new BlockBale(), CT_DECORATIONS)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "crop/bales/cotton/cotton_bale", new BlockBale(), CT_DECORATIONS)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "crop/bales/cotton/cotton_yarn_bale", new BlockBale(), CT_DECORATIONS)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "crop/bales/hemp/hemp_bale", new BlockBale(), CT_DECORATIONS)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "crop/bales/hemp/hemp_fiber_bale", new BlockBale(), CT_DECORATIONS)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "crop/bales/jute/jute_bale", new BlockBale(), CT_DECORATIONS)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "crop/bales/jute/jute_fiber_bale", new BlockBale(), CT_DECORATIONS)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "crop/bales/linen/linen_bale", new BlockBale(), CT_DECORATIONS)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "crop/bales/linen/linen_fiber_bale", new BlockBale(), CT_DECORATIONS)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "crop/bales/papyrus/papyrus_fiber_bale", new BlockBale(), CT_DECORATIONS)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "crop/bales/silk/silk_string_bale", new BlockBale(), CT_DECORATIONS)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "crop/bales/sisal/sisal_fiber_bale", new BlockBale(), CT_DECORATIONS)));



        /*normalItemBlocks.add(new ItemBlockTFC(register(r, "coral/tube/dead", new BlockCoral(FluidsTFC.SALT_WATER.get(), MapColor.SNOW), CT_FLORA)));
        normalItemBlocks.add(new ItemBlockTFC(register(r, "coral/brain/dead", new BlockCoral(FluidsTFC.SALT_WATER.get(), MapColor.SNOW), CT_FLORA)));
        normalItemBlocks.add(new ItemBlockTFC(register(r, "coral/bubble/dead", new BlockCoral(FluidsTFC.SALT_WATER.get(), MapColor.SNOW), CT_FLORA)));
        normalItemBlocks.add(new ItemBlockTFC(register(r, "coral/fire/dead", new BlockCoral(FluidsTFC.SALT_WATER.get(), MapColor.SNOW), CT_FLORA)));
        normalItemBlocks.add(new ItemBlockTFC(register(r, "coral/horn/dead", new BlockCoral(FluidsTFC.SALT_WATER.get(), MapColor.SNOW), CT_FLORA)));

        for (EnumDyeColor dyeColor : EnumDyeColor.values())
        {
            BlockCoral brain = new BlockCoral(FluidsTFC.SALT_WATER.get(), MapColor.getBlockColor(dyeColor));
            BlockCoral bubble = new BlockCoral(FluidsTFC.SALT_WATER.get(), MapColor.getBlockColor(dyeColor));
            BlockCoral fire = new BlockCoral(FluidsTFC.SALT_WATER.get(), MapColor.getBlockColor(dyeColor));
            BlockCoral horn = new BlockCoral(FluidsTFC.SALT_WATER.get(), MapColor.getBlockColor(dyeColor));
            BlockCoral tube = new BlockCoral(FluidsTFC.SALT_WATER.get(), MapColor.getBlockColor(dyeColor));

            normalItemBlocks.add(new ItemBlockTFC(register(r, "coral/brain/" + dyeColor.getName(), brain, CT_FLORA)));
            normalItemBlocks.add(new ItemBlockTFC(register(r, "coral/bubble/" + dyeColor.getName(), bubble, CT_FLORA)));
            normalItemBlocks.add(new ItemBlockTFC(register(r, "coral/fire/" + dyeColor.getName(), fire, CT_FLORA)));
            normalItemBlocks.add(new ItemBlockTFC(register(r, "coral/horn/" + dyeColor.getName(), horn, CT_FLORA)));
            normalItemBlocks.add(new ItemBlockTFC(register(r, "coral/tube/" + dyeColor.getName(), tube, CT_FLORA)));

            BlockCoral.BRAIN_CORAL.put(dyeColor, brain);
            BlockCoral.BUBBLE_CORAL.put(dyeColor, bubble);
            BlockCoral.FIRE_CORAL.put(dyeColor, fire);
            BlockCoral.HORN_CORAL.put(dyeColor, horn);
            BlockCoral.TUBE_CORAL.put(dyeColor, tube);
        }

        normalItemBlocks.add(new ItemBlockTFC(register(r, "coral/fan/tube/dead", new BlockCoral(FluidsTFC.SALT_WATER.get(), MapColor.SNOW), CT_FLORA)));
        normalItemBlocks.add(new ItemBlockTFC(register(r, "coral/fan/brain/dead", new BlockCoral(FluidsTFC.SALT_WATER.get(), MapColor.SNOW), CT_FLORA)));
        normalItemBlocks.add(new ItemBlockTFC(register(r, "coral/fan/bubble/dead", new BlockCoral(FluidsTFC.SALT_WATER.get(), MapColor.SNOW), CT_FLORA)));
        normalItemBlocks.add(new ItemBlockTFC(register(r, "coral/fan/fire/dead", new BlockCoral(FluidsTFC.SALT_WATER.get(), MapColor.SNOW), CT_FLORA)));
        normalItemBlocks.add(new ItemBlockTFC(register(r, "coral/fan/horn/dead", new BlockCoral(FluidsTFC.SALT_WATER.get(), MapColor.SNOW), CT_FLORA)));

        for (EnumDyeColor dyeColor : EnumDyeColor.values())
        {
            BlockCoral brain = new BlockCoral(FluidsTFC.SALT_WATER.get(), MapColor.getBlockColor(dyeColor));
            BlockCoral bubble = new BlockCoral(FluidsTFC.SALT_WATER.get(), MapColor.getBlockColor(dyeColor));
            BlockCoral fire = new BlockCoral(FluidsTFC.SALT_WATER.get(), MapColor.getBlockColor(dyeColor));
            BlockCoral horn = new BlockCoral(FluidsTFC.SALT_WATER.get(), MapColor.getBlockColor(dyeColor));
            BlockCoral tube = new BlockCoral(FluidsTFC.SALT_WATER.get(), MapColor.getBlockColor(dyeColor));

            normalItemBlocks.add(new ItemBlockTFC(register(r, "coral/fan/brain/" + dyeColor.getName(), brain, CT_FLORA)));
            normalItemBlocks.add(new ItemBlockTFC(register(r, "coral/fan/bubble/" + dyeColor.getName(), bubble, CT_FLORA)));
            normalItemBlocks.add(new ItemBlockTFC(register(r, "coral/fan/fire/" + dyeColor.getName(), fire, CT_FLORA)));
            normalItemBlocks.add(new ItemBlockTFC(register(r, "coral/fan/horn/" + dyeColor.getName(), horn, CT_FLORA)));
            normalItemBlocks.add(new ItemBlockTFC(register(r, "coral/fan/tube/" + dyeColor.getName(), tube, CT_FLORA)));

            BlockCoral.BRAIN_CORAL_FAN.put(dyeColor, brain);
            BlockCoral.BUBBLE_CORAL_FAN.put(dyeColor, bubble);
            BlockCoral.FIRE_CORAL_FAN.put(dyeColor, fire);
            BlockCoral.HORN_CORAL_FAN.put(dyeColor, horn);
            BlockCoral.TUBE_CORAL_FAN.put(dyeColor, tube);
        }*/

        // Normal Corals
        plantCoral.add(register(r, "coral/tube/dead", new TFCBlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.SNOW), CT_FLORA));
        plantCoral.add(register(r, "coral/brain/dead", new TFCBlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.SNOW), CT_FLORA));
        plantCoral.add(register(r, "coral/bubble/dead", new TFCBlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.SNOW), CT_FLORA));
        plantCoral.add(register(r, "coral/fire/dead", new TFCBlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.SNOW), CT_FLORA));
        plantCoral.add(register(r, "coral/horn/dead", new TFCBlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.SNOW), CT_FLORA));

        for (EnumDyeColor dyeColor : EnumDyeColor.values()) {

            TFCBlockCoral brainNormal = new TFCBlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.getBlockColor(dyeColor));
            TFCBlockCoral bubbleNormal = new TFCBlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.getBlockColor(dyeColor));
            TFCBlockCoral fireNormal = new TFCBlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.getBlockColor(dyeColor));
            TFCBlockCoral hornNormal = new TFCBlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.getBlockColor(dyeColor));
            TFCBlockCoral tubeNormal = new TFCBlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.getBlockColor(dyeColor));

            plantCoral.add(register(r, "coral/brain/" + dyeColor.getName(), brainNormal, CT_FLORA));
            plantCoral.add(register(r, "coral/bubble/" + dyeColor.getName(), bubbleNormal, CT_FLORA));
            plantCoral.add(register(r, "coral/fire/" + dyeColor.getName(), fireNormal, CT_FLORA));
            plantCoral.add(register(r, "coral/horn/" + dyeColor.getName(), hornNormal, CT_FLORA));
            plantCoral.add(register(r, "coral/tube/" + dyeColor.getName(), tubeNormal, CT_FLORA));

            TFCBlockCoral.BRAIN_CORAL.put(dyeColor, brainNormal);
            TFCBlockCoral.BUBBLE_CORAL.put(dyeColor, bubbleNormal);
            TFCBlockCoral.FIRE_CORAL.put(dyeColor, fireNormal);
            TFCBlockCoral.HORN_CORAL.put(dyeColor, hornNormal);
            TFCBlockCoral.TUBE_CORAL.put(dyeColor, tubeNormal);
        }

        // Fan Corals
        plantCoral.add(register(r, "coral/fan/tube/dead", new TFCBlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.SNOW), CT_FLORA));
        plantCoral.add(register(r, "coral/fan/brain/dead", new TFCBlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.SNOW), CT_FLORA));
        plantCoral.add(register(r, "coral/fan/bubble/dead", new TFCBlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.SNOW), CT_FLORA));
        plantCoral.add(register(r, "coral/fan/fire/dead", new TFCBlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.SNOW), CT_FLORA));
        plantCoral.add(register(r, "coral/fan/horn/dead", new TFCBlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.SNOW), CT_FLORA));

        for (EnumDyeColor dyeColor : EnumDyeColor.values()) {
            TFCBlockCoral brainFan = new TFCBlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.getBlockColor(dyeColor));
            TFCBlockCoral bubbleFan = new TFCBlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.getBlockColor(dyeColor));
            TFCBlockCoral fireFan = new TFCBlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.getBlockColor(dyeColor));
            TFCBlockCoral hornFan = new TFCBlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.getBlockColor(dyeColor));
            TFCBlockCoral tubeFan = new TFCBlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.getBlockColor(dyeColor));

            plantCoral.add(register(r, "coral/fan/brain/" + dyeColor.getName(), brainFan, CT_FLORA));
            plantCoral.add(register(r, "coral/fan/bubble/" + dyeColor.getName(), bubbleFan, CT_FLORA));
            plantCoral.add(register(r, "coral/fan/fire/" + dyeColor.getName(), fireFan, CT_FLORA));
            plantCoral.add(register(r, "coral/fan/horn/" + dyeColor.getName(), hornFan, CT_FLORA));
            plantCoral.add(register(r, "coral/fan/tube/" + dyeColor.getName(), tubeFan, CT_FLORA));

            TFCBlockCoral.BRAIN_CORAL_FAN.put(dyeColor, brainFan);
            TFCBlockCoral.BUBBLE_CORAL_FAN.put(dyeColor, bubbleFan);
            TFCBlockCoral.FIRE_CORAL_FAN.put(dyeColor, fireFan);
            TFCBlockCoral.HORN_CORAL_FAN.put(dyeColor, hornFan);
            TFCBlockCoral.TUBE_CORAL_FAN.put(dyeColor, tubeFan);
        }
        allBlockCoral = plantCoral.build();
        for (TFCBlockCoral plantCoralBlock : allBlockCoral) {
            normalItemBlocks.add(new TFCItemBlock(plantCoralBlock));
        }

        normalItemBlocks.add(new TFCItemBlock(register(r, "coral/block/brain/dead", new TFCBlockCoralBlock(MapColor.SNOW), CT_FLORA)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "coral/block/bubble/dead", new TFCBlockCoralBlock(MapColor.SNOW), CT_FLORA)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "coral/block/fire/dead", new TFCBlockCoralBlock(MapColor.SNOW), CT_FLORA)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "coral/block/horn/dead", new TFCBlockCoralBlock(MapColor.SNOW), CT_FLORA)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "coral/block/tube/dead", new TFCBlockCoralBlock(MapColor.SNOW), CT_FLORA)));

        for (EnumDyeColor dyeColor : EnumDyeColor.values()) {
            TFCBlockCoralBlock brain = new TFCBlockCoralBlock(MapColor.getBlockColor(dyeColor));
            TFCBlockCoralBlock bubble = new TFCBlockCoralBlock(MapColor.getBlockColor(dyeColor));
            TFCBlockCoralBlock fire = new TFCBlockCoralBlock(MapColor.getBlockColor(dyeColor));
            TFCBlockCoralBlock horn = new TFCBlockCoralBlock(MapColor.getBlockColor(dyeColor));
            TFCBlockCoralBlock tube = new TFCBlockCoralBlock(MapColor.getBlockColor(dyeColor));

            normalItemBlocks.add(new TFCItemBlock(register(r, "coral/block/brain/" + dyeColor.getName(), brain, CT_FLORA)));
            normalItemBlocks.add(new TFCItemBlock(register(r, "coral/block/bubble/" + dyeColor.getName(), bubble, CT_FLORA)));
            normalItemBlocks.add(new TFCItemBlock(register(r, "coral/block/fire/" + dyeColor.getName(), fire, CT_FLORA)));
            normalItemBlocks.add(new TFCItemBlock(register(r, "coral/block/horn/" + dyeColor.getName(), horn, CT_FLORA)));
            normalItemBlocks.add(new TFCItemBlock(register(r, "coral/block/tube/" + dyeColor.getName(), tube, CT_FLORA)));

            TFCBlockCoralBlock.BRAIN_CORAL_BLOCK.put(dyeColor, brain);
            TFCBlockCoralBlock.BUBBLE_CORAL_BLOCK.put(dyeColor, bubble);
            TFCBlockCoralBlock.FIRE_CORAL_BLOCK.put(dyeColor, fire);
            TFCBlockCoralBlock.HORN_CORAL_BLOCK.put(dyeColor, horn);
            TFCBlockCoralBlock.TUBE_CORAL_BLOCK.put(dyeColor, tube);
        }

        {
            plantGlowWater.add(register(r, "plants/glowing_sea_banana", new TFCBlockWaterGlowPlant(FluidsTFC.SEA_WATER.get()), CT_FLORA));
        }
        allBlockWaterGlowPlant = plantGlowWater.build();
        for (TFCBlockWaterGlowPlant plantGlowWaterBlock : allBlockWaterGlowPlant) {
            normalItemBlocks.add(new TFCItemBlock(plantGlowWaterBlock));
        }

        {
            //Builder<BlockCaveMushroom> plantCaveMushroom = ImmutableList.builder();

            TFCBlockCaveMushroom blueshroom = new TFCBlockCaveMushroom(0.3F, FoodData.RAW_BLUESHROOM, new PotionEffectToHave(MobEffects.HUNGER, 610, 1, 4), new PotionEffectToHave(MobEffects.HASTE, 610, 1, 4), "blueshroom", "mushroom", "category_vegetable");
            TFCBlockCaveMushroom glowshroom = new TFCBlockCaveMushroom(0.5F, FoodData.RAW_GLOWSHROOM, new PotionEffectToHave(MobEffects.HUNGER, 610, 1, 4), new PotionEffectToHave(MobEffects.GLOWING, 610, 1, 4), "glowshroom", "mushroom", "category_vegetable");
            TFCBlockCaveMushroom magma_shroom = new TFCBlockCaveMushroom(0.2F, FoodData.RAW_MAGMA_SHROOM, new PotionEffectToHave(MobEffects.HUNGER, 610, 1, 4), new PotionEffectToHave(MobEffects.FIRE_RESISTANCE, 610, 1, 4), "magma_shroom", "mushroom", "category_vegetable");
            TFCBlockCaveMushroom poison_shroom = new TFCBlockCaveMushroom(0.1F, FoodData.RAW_POISON_SHROOM, new PotionEffectToHave(MobEffects.POISON, 610, 1, 4), new PotionEffectToHave(MobEffects.ABSORPTION, 610, 1, 4), "poison_shroom", "mushroom", "category_vegetable");
            TFCBlockCaveMushroom sulphur_shroom = new TFCBlockCaveMushroom(0.1F, FoodData.RAW_SULPHUR_SHROOM, new PotionEffectToHave(MobEffects.MINING_FATIGUE, 610, 1, 4), new PotionEffectToHave(MobEffects.LUCK, 610, 1, 4), "sulphur_shroom", "mushroom", "category_vegetable");

            normalItemBlocks.add(new ItemBlockCaveMushroom(register(r, "plants/blueshroom", blueshroom, CT_FLORA)));
            normalItemBlocks.add(new ItemBlockCaveMushroom(register(r, "plants/glowshroom", glowshroom, CT_FLORA)));
            normalItemBlocks.add(new ItemBlockCaveMushroom(register(r, "plants/magma_shroom", magma_shroom, CT_FLORA)));
            normalItemBlocks.add(new ItemBlockCaveMushroom(register(r, "plants/poison_shroom", poison_shroom, CT_FLORA)));
            normalItemBlocks.add(new ItemBlockCaveMushroom(register(r, "plants/sulphur_shroom", sulphur_shroom, CT_FLORA)));

            /*
            plantCaveMushroom.add(register(r, "plants/blueshroom", new BlockCaveMushroom(0.3F, Food.RAW_BLUESHROOM, new PotionEffectToHave(MobEffects.HUNGER, 610, 1, 4), new PotionEffectToHave(MobEffects.HASTE, 610, 1, 4), "blueshroom", "mushroom", "category_vegetable"), CT_FLORA));
            plantCaveMushroom.add(register(r, "plants/glowshroom", new BlockCaveMushroom(0.5F, Food.RAW_GLOWSHROOM, new PotionEffectToHave(MobEffects.HUNGER, 610, 1, 4), new PotionEffectToHave(MobEffects.GLOWING, 610, 1, 4), "glowshroom", "mushroom", "category_vegetable"), CT_FLORA));
            plantCaveMushroom.add(register(r, "plants/magma_shroom", new BlockCaveMushroom(0.2F, Food.RAW_MAGMA_SHROOM, new PotionEffectToHave(MobEffects.HUNGER, 610, 1, 4), new PotionEffectToHave(MobEffects.FIRE_RESISTANCE, 610, 1, 4), "magma_shroom", "mushroom", "category_vegetable"), CT_FLORA));
            plantCaveMushroom.add(register(r, "plants/poison_shroom", new BlockCaveMushroom(0.1F, Food.RAW_POISON_SHROOM, new PotionEffectToHave(MobEffects.POISON, 610, 1, 4), new PotionEffectToHave(MobEffects.ABSORPTION, 610, 1, 4), "poison_shroom", "mushroom", "category_vegetable"), CT_FLORA));
            plantCaveMushroom.add(register(r, "plants/sulphur_shroom", new BlockCaveMushroom(0.1F, Food.RAW_SULPHUR_SHROOM, new PotionEffectToHave(MobEffects.MINING_FATIGUE, 610, 1, 4), new PotionEffectToHave(MobEffects.LUCK, 610, 1, 4), "sulphur_shroom", "mushroom", "category_vegetable"), CT_FLORA));
            */
        }

        {

        }


        /*for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
        {
            pebbleWater.add(register(r, "pebble/" + rock.getRegistryName().getPath().toLowerCase(), new BlockPebbleWater(FluidsTFC.SALT_WATER.get(), rock), CT_DECORATIONS));
        }
        allPebbleWater = pebbleWater.build();
        allPebbleWater.forEach((x) -> {
            normalItemBlocks.add(new ItemBlockTFC(x));
        });*/

        /*
        {
            blockLightstone.add(register(r, "groundcover/lightstone", new BlockLightstone(0.8f), CT_GEMS));
        }*/
        allBlockLightstone = blockLightstone.build();
        for (TFCBlockLightstone lightstone : allBlockLightstone) {
            normalItemBlocks.add(new TFCItemBlock(lightstone));
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverRock) {
            for (Rock rock : TFCRegistries.ROCKS.getValuesCollection()) {
                surfaceRock.add(register(r, "groundcover/rock/" + rock.getRegistryName().getPath().toLowerCase(), new TFCBlockSurfaceRock(rock), CT_ROCK_BLOCKS));
            }
            allBlockSurfaceRock = surfaceRock.build();
            allBlockSurfaceRock.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverBones) {
            surfaceBone.add(register(r, "groundcover/bone", new TFCBlockSurfaceBones(), CT_FLORA));
            allBlockSurfaceBones = surfaceBone.build();
            allBlockSurfaceBones.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverDriftwood) {
            surfaceDriftwood.add(register(r, "groundcover/driftwood", new TFCBlockDriftwood(), CT_FLORA));
            allBlockDriftwood = surfaceDriftwood.build();
            allBlockDriftwood.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverFlint) {
            surfaceFlint.add(register(r, "groundcover/flint", new TFCBlockSurfaceFlint(), CT_FLORA));
            allBlockSurfaceFlint = surfaceFlint.build();
            allBlockSurfaceFlint.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverPinecone) {
            surfacePinecone.add(register(r, "groundcover/pinecone", new TFCBlockSurfacePinecone(), CT_FLORA));
            allBlockSurfacePinecone = surfacePinecone.build();
            allBlockSurfacePinecone.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverSeashell) {
            surfaceSeashell.add(register(r, "groundcover/seashell", new TFCBlockSurfaceSeashells(), CT_FLORA));
            allBlockSurfaceSeashells = surfaceSeashell.build();
            allBlockSurfaceSeashells.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverTwig) {
            surfaceTwig.add(register(r, "groundcover/twig", new TFCBlockSurfaceTwig(), CT_FLORA));
            allBlockSurfaceTwig = surfaceTwig.build();
            allBlockSurfaceTwig.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));
        }


//        // Cassia Cinnamon
//        //fruitBarrel.add(register(r, "wood/fruit_tree/barrel/cassia_cinnamon", new BlockFruitBarrelTest(), CT_DECORATIONS));
//        fruitBookshelves.add(register(r, "wood/fruit_tree/bookshelf/cassia_cinnamon", new BlockFruitBookshelf(), CT_DECORATIONS));
//        fruitDoors.add(register(r, "wood/fruit_tree/door/cassia_cinnamon", new BlockFruitDoor("cassia_cinnamon"), CT_DECORATIONS));
//        fruitButton.add(register(r, "wood/fruit_tree/button/cassia_cinnamon", new BlockFruitButton(), CT_DECORATIONS));
//        planksTFC.add(register(r, "wood/fruit_tree/planks/cassia_cinnamon", new BlockPlanksTFC(DefaultTrees.CASSIA_CINNAMON_TREE), CT_WOOD));
//        fruitPressurePlate.add(register(r, "wood/fruit_tree/pressure_plate/cassia_cinnamon", new BlockFruitPressurePlate(), CT_DECORATIONS));
//        fruitFences.add(register(r, "wood/fruit_tree/fence/cassia_cinnamon", new BlockFruitFence(), CT_DECORATIONS));
//        fruitFenceGates.add(register(r, "wood/fruit_tree/fence_gate/cassia_cinnamon", new BlockFruitFenceGate(), CT_DECORATIONS));
//        fruitLogFences.add(register(r, "wood/fruit_tree/fence_log/cassia_cinnamon", new BlockFruitLogFence(), CT_DECORATIONS));
//        fruitLogFenceGates.add(register(r, "wood/fruit_tree/fence_gate_log/cassia_cinnamon", new BlockFruitLogFenceGate(), CT_DECORATIONS));
//        fruitSupport.add(register(r, "wood/fruit_tree/support/cassia_cinnamon", new BlockFruitSupport(), CT_DECORATIONS));
//        fruitToolRack.add(register(r, "wood/fruit_tree/tool_rack/cassia_cinnamon", new BlockFruitToolRack(), CT_DECORATIONS));
//        fruitTrapdoors.add(register(r, "wood/fruit_tree/trapdoor/cassia_cinnamon", new BlockFruitTrapDoor(), CT_DECORATIONS));
//        fruitWorkbench.add(register(r, "wood/fruit_tree/workbench/cassia_cinnamon", new BlockFruitWorkbench(), CT_DECORATIONS));
//        register(r, "wood/fruit_tree/double_slab/cassia_cinnamon", new BlockRockSlab.Double(DefaultTrees.CASSIA_CINNAMON_TREE));
//        blockSlabTFC.add(register(r, "wood/fruit_tree/slab/cassia_cinnamon", new BlockRockSlab.Half(DefaultTrees.CASSIA_CINNAMON_TREE), CT_DECORATIONS));
//        blockStairTFC.add(register(r, "wood/fruit_tree/stairs/cassia_cinnamon", new BlockWoodStairsTFC(DefaultTrees.CASSIA_CINNAMON_TREE), CT_DECORATIONS));
//        fruitChests.add(register(r, "wood/fruit_tree/chest/cassia_cinnamon", new BlockFruitChest(BlockFruitChest.TFCBASIC, DefaultTrees.CASSIA_CINNAMON_TREE), CT_DECORATIONS));
//        fruitChests.add(register(r, "wood/fruit_tree/chest_trap/cassia_cinnamon", new BlockFruitChest(BlockFruitChest.TFCTRAP, DefaultTrees.CASSIA_CINNAMON_TREE), CT_DECORATIONS));
//        fruitLoom.add(register(r, "wood/fruit_tree/loom/cassia_cinnamon", new BlockFruitLoom(DefaultTrees.CASSIA_CINNAMON_TREE), CT_WOOD));
//
//        // Ceylon Cinnamon
//        //fruitBarrel.add(register(r, "wood/fruit_tree/barrel/ceylon_cinnamon", new BlockFruitBarrelTest(), CT_DECORATIONS));
//        fruitBookshelves.add(register(r, "wood/fruit_tree/bookshelf/ceylon_cinnamon", new BlockFruitBookshelf(), CT_DECORATIONS));
//        fruitButton.add(register(r, "wood/fruit_tree/button/ceylon_cinnamon", new BlockFruitButton(), CT_DECORATIONS));
//        fruitDoors.add(register(r, "wood/fruit_tree/door/ceylon_cinnamon", new BlockFruitDoor("ceylon_cinnamon"), CT_DECORATIONS));
//        planksTFC.add(register(r, "wood/fruit_tree/planks/ceylon_cinnamon", new BlockPlanksTFC(DefaultTrees.CEYLON_CINNAMON_TREE), CT_WOOD));
//        fruitPressurePlate.add(register(r, "wood/fruit_tree/pressure_plate/ceylon_cinnamon", new BlockFruitPressurePlate(), CT_DECORATIONS));
//        fruitFences.add(register(r, "wood/fruit_tree/fence/ceylon_cinnamon", new BlockFruitFence(), CT_DECORATIONS));
//        fruitFenceGates.add(register(r, "wood/fruit_tree/fence_gate/ceylon_cinnamon", new BlockFruitFenceGate(), CT_DECORATIONS));
//        fruitLogFences.add(register(r, "wood/fruit_tree/fence_log/ceylon_cinnamon", new BlockFruitLogFence(), CT_DECORATIONS));
//        fruitLogFenceGates.add(register(r, "wood/fruit_tree/fence_gate_log/ceylon_cinnamon", new BlockFruitLogFenceGate(), CT_DECORATIONS));
//        fruitSupport.add(register(r, "wood/fruit_tree/support/ceylon_cinnamon", new BlockFruitSupport(), CT_DECORATIONS));
//        fruitToolRack.add(register(r, "wood/fruit_tree/tool_rack/ceylon_cinnamon", new BlockFruitToolRack(), CT_DECORATIONS));
//        fruitTrapdoors.add(register(r, "wood/fruit_tree/trapdoor/ceylon_cinnamon", new BlockFruitTrapDoor(), CT_DECORATIONS));
//        fruitWorkbench.add(register(r, "wood/fruit_tree/workbench/ceylon_cinnamon", new BlockFruitWorkbench(), CT_DECORATIONS));
//        register(r, "wood/fruit_tree/double_slab/ceylon_cinnamon", new BlockRockSlab.Double(DefaultTrees.CEYLON_CINNAMON_TREE));
//        blockSlabTFC.add(register(r, "wood/fruit_tree/slab/ceylon_cinnamon", new BlockRockSlab.Half(DefaultTrees.CEYLON_CINNAMON_TREE), CT_DECORATIONS));
//        blockStairTFC.add(register(r, "wood/fruit_tree/stairs/ceylon_cinnamon", new BlockWoodStairsTFC(DefaultTrees.CEYLON_CINNAMON_TREE), CT_DECORATIONS));
//        fruitChests.add(register(r, "wood/fruit_tree/chest/ceylon_cinnamon", new BlockFruitChest(BlockFruitChest.TFCBASIC, DefaultTrees.CEYLON_CINNAMON_TREE), CT_DECORATIONS));
//        fruitChests.add(register(r, "wood/fruit_tree/chest_trap/ceylon_cinnamon", new BlockFruitChest(BlockFruitChest.TFCTRAP, DefaultTrees.CEYLON_CINNAMON_TREE), CT_DECORATIONS));
//        fruitLoom.add(register(r, "wood/fruit_tree/loom/ceylon_cinnamon", new BlockFruitLoom(DefaultTrees.CEYLON_CINNAMON_TREE), CT_WOOD));

        //inventoryItemBlocks.add(register(r, "wood/fruit_tree/log/cassia_cinnamon", new BlockCassiaCinnamonLog(), CT_WOOD));
        //inventoryItemBlocks.add(register(r, "wood/fruit_tree/leaves/cassia_cinnamon", new BlockCassiaCinnamonLeaves(), CT_WOOD));
        //inventoryItemBlocks.add(register(r, "wood/fruit_tree/sapling/cassia_cinnamon", new BlockCassiaCinnamonSapling(), CT_WOOD));

        //inventoryItemBlocks.add(register(r, "wood/fruit_tree/log/ceylon_cinnamon", new BlockCeylonCinnamonLog(), CT_WOOD));
        //inventoryItemBlocks.add(register(r, "wood/fruit_tree/leaves/ceylon_cinnamon", new BlockCeylonCinnamonLeaves(), CT_WOOD));
        //inventoryItemBlocks.add(register(r, "wood/fruit_tree/sapling/ceylon_cinnamon", new BlockCeylonCinnamonSapling(), CT_WOOD));

//        // Bamboo
//        for (int i = 0; i < bamboo.length; i++)
//        {
//            //fruitBarrel.add(register(r, "wood/barrel/" + bamboo[i], new BlockFruitBarrelTest(), CT_DECORATIONS));
//            fruitBookshelves.add(register(r, "wood/bookshelf/" + bamboo[i], new BlockFruitBookshelf(), CT_DECORATIONS));
//            fruitButton.add(register(r, "wood/button/" + bamboo[i], new BlockFruitButton(), CT_DECORATIONS));
//            fruitDoors.add(register(r, "wood/door/" + bamboo[i], new BlockFruitDoor(bamboo[i]), CT_DECORATIONS));
//            planksTFC.add(register(r, "wood/planks/" + bamboo[i], new TFCBlockPlanks(bambooTrees[i]), CT_WOOD));
//            fruitPressurePlate.add(register(r, "wood/pressure_plate/" + bamboo[i], new BlockFruitPressurePlate(), CT_DECORATIONS));
//            fruitFences.add(register(r, "wood/fence/" + bamboo[i], new BlockFruitFence(), CT_DECORATIONS));
//            fruitFenceGates.add(register(r, "wood/fence_gate/" + bamboo[i], new BlockFruitFenceGate(), CT_DECORATIONS));
//            fruitLogFences.add(register(r, "wood/fence_log/" + bamboo[i], new BlockFruitLogFence(), CT_DECORATIONS));
//            fruitLogFenceGates.add(register(r, "wood/fence_gate_log/" + bamboo[i], new BlockFruitLogFenceGate(), CT_DECORATIONS));
//            fruitSupport.add(register(r, "wood/support/" + bamboo[i], new BlockFruitSupport(), CT_DECORATIONS));
//            fruitToolRack.add(register(r, "wood/tool_rack/" + bamboo[i], new BlockFruitToolRack(), CT_DECORATIONS));
//            fruitTrapdoors.add(register(r, "wood/trapdoor/" + bamboo[i], new BlockFruitTrapDoor(), CT_DECORATIONS));
//            fruitWorkbench.add(register(r, "wood/workbench/" + bamboo[i], new BlockFruitWorkbench(), CT_DECORATIONS));
////            register(r, "wood/double_slab/" + bamboo[i], new BlockRockSlab.Double(bambooTrees[i]));
////            blockSlabTFC.add(register(r, "wood/slab/" + bamboo[i], new BlockRockSlab.Half(bambooTrees[i]), CT_DECORATIONS));
////            blockStairTFC.add(register(r, "wood/stairs/" + bamboo[i], new BlockWoodStairsTFC(bambooTrees[i]), CT_DECORATIONS));
//
//            fruitChests.add(register(r, "wood/chest/" + bamboo[i], new BlockFruitChest(BlockFruitChest.TFCBASIC, bambooTrees[i]), CT_DECORATIONS));
//            fruitChests.add(register(r, "wood/chest_trap/" + bamboo[i], new BlockFruitChest(BlockFruitChest.TFCTRAP, bambooTrees[i]), CT_DECORATIONS));
//            fruitLoom.add(register(r, "wood/loom/" + bamboo[i], new BlockFruitLoom(bambooTrees[i]), CT_WOOD));
//
//            Block bambooBlock = register(r, "wood/log/" + bamboo[i], new BlockBambooLog(), CT_WOOD);
//            BlockBambooLeaves leaves = new BlockBambooLeaves(bambooTrees[i]);
//            Block bambooLeaves = register(r, "wood/leaves/" + bamboo[i], leaves, CT_WOOD);
//
//            BlockBambooSapling sapling = new BlockBambooSapling(bambooTrees[i], bambooLeaves, bambooBlock);
//            Block bambooSapling = register(r, "wood/sapling/" + bamboo[i], sapling, CT_WOOD);
//            leaves.setBambooSapling(sapling);
//
//            itemBambooLog.add(bambooBlock);
//            itemBambooLeaves.add(bambooLeaves);
//            itemBambooSapling.add(bambooSapling);
//        }

        //multiBlock.add(register(r, "multiblock/campfire", new BlockCampfire(Material.ROCK), CT_MISC));
        //multiBlock.add(register(r, "multiblock/dummyHalf", new BlockDummyHalf(), CT_MISC));

        /*
        for(Tree wood : TFCRegistries.TREES.getValuesCollection())
        {
            fenceGatesLog.add(register(r, "wood/fence_gate_log/" + wood.getRegistryName().getPath(), new TFCBlockFenceGateLog(wood), CT_DECORATIONS));

            if (wood == TFCRegistries.TREES.getValue(DefaultTrees.JOSHUA_TREE))
            {
                Builder<BlockJoshuaTreeLog> logJoshuaTree = ImmutableList.builder();
                Builder<BlockJoshuaTreeSapling> saplingJoshuaTree = ImmutableList.builder();

                logJoshuaTree.add(register(r, "wood/log/" + wood.getRegistryName().getPath(), new BlockJoshuaTreeLog(wood), CT_WOOD));
                saplingJoshuaTree.add(register(r, "wood/sapling/" + wood.getRegistryName().getPath(), new BlockJoshuaTreeSapling(wood), CT_WOOD));

                allJoshuaTreeLogBlocks = logJoshuaTree.build();
                for (BlockJoshuaTreeLog blockJoshuaTreeLog : allJoshuaTreeLogBlocks)
                {
                    normalItemBlocks.add(new ItemBlockTFC(blockJoshuaTreeLog));
                }
                allJoshuaTreeSaplingBlocks = saplingJoshuaTree.build();
                for (BlockJoshuaTreeSapling blockJoshuaTreeSapling : allJoshuaTreeSaplingBlocks)
                {
                    normalItemBlocks.add(new ItemBlockTFC(blockJoshuaTreeSapling));
                }
            }
        }*/

//        allBlockBambooLeaves = itemBambooLog.build();
//        allBlockBambooLeaves.forEach((x) -> {
//            normalItemBlocks.add(new TFCItemBlock(x));
//        });
//
//        allBlockBambooLog = itemBambooLeaves.build();
//        allBlockBambooLog.forEach((x) -> {
//            normalItemBlocks.add(new TFCItemBlock(x));
//        });
//
//
//        allBlockBambooSapling = itemBambooSapling.build();
//        allBlockBambooSapling.forEach((x) -> {
//            normalItemBlocks.add(new TFCItemBlock(x));
//        });

        allFoodItemBlocks = foodItemBlocks.build();
        allFoodItemBlocks.forEach((x) -> {
            normalItemBlocks.add(new ItemBlockRot(x));
        });


        allNormalItemBlocks = normalItemBlocks.build();
        allColorizedItemBlocks = colorizedItemBlocks.build();
        allInventoryItemBlocks = inventoryItemBlocks.build();

        // Register Tile Entities
        // Putting tile entity registration in the respective block can call it multiple times. Just put here to avoid duplicates

        register(TETickCounter.class, "tick_counter");
        register(TEPlacedItem.class, "placed_item");
        register(TEPlacedItemFlat.class, "placed_item_flat");
        register(TEPlacedHide.class, "placed_hide");
        register(TEPitKiln.class, "pit_kiln");
        register(TEChest.class, "chest");
        register(TENestBox.class, "nest_box");
        register(TELogPile.class, "log_pile");
        register(TEFirePit.class, "fire_pit");
        register(TEToolRack.class, "tool_rack");
        register(TELoom.class, "loom");
        register(TELamp.class, "lamp");
        register(TEBellows.class, "bellows");
        register(TEBarrel.class, "barrel");
        register(TECharcoalForge.class, "charcoal_forge");
        register(TEAnvilTFC.class, "anvil");
        register(TECrucible.class, "crucible");
        register(TECropBase.class, "crop_base");
        register(TECropSpreading.class, "crop_spreading");
        register(TEBlastFurnace.class, "blast_furnace");
        register(TEBloomery.class, "bloomery");
        register(TEBloom.class, "bloom");
        register(TEMetalSheet.class, "metal_sheet");
        register(TEQuern.class, "quern");
        register(TELargeVessel.class, "large_vessel");
        register(TELargeStonewareVessel.class, "large_stoneware_vessel");
        register(TELargeKaoliniteVessel.class, "large_kaolinite_vessel");
        register(TELargeEarthenwareVessel.class, "large_earthenware_vessel");
        register(TEPowderKeg.class, "powderkeg");
        register(TESluice.class, "sluice");
        register(TEUrn.class, "urn");
        register(TECrate.class, "crate");
        register(TEDryer.class, "dryer");
        register(TEStickBundle.class, "stick_bundle");
        register(TECondenser.class, "condenser");
        register(TEAlembic.class, "alembic");
        register(TESaguaroCactus.class, "saguaro_cactus");
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerVanillaOverrides(RegistryEvent.Register<Block> event) {
        // Vanilla Overrides. Used for small tweaks on vanilla items, rather than replacing them outright
        if (ConfigTFC.General.OVERRIDES.enableFrozenOverrides) {
            TerraFirmaCraft.getLog().info("The below warnings about unintended overrides are normal. The override is intended. ;)");
            event.getRegistry().registerAll(
                    new BlockIceTFC(FluidRegistry.WATER).setRegistryName("minecraft", "ice").setTranslationKey("ice"),
                    new BlockSnowTFC().setRegistryName("minecraft", "snow_layer").setTranslationKey("snow"),
                    new BlockTorchTFC().setRegistryName("minecraft", "torch").setTranslationKey("torch")
            );

        }
    }

    public static boolean isWater(IBlockState current) {
        return current.getMaterial() == Material.WATER;
    }

    public static boolean isVanillaWater(IBlockState current) {
        return current == FluidRegistry.WATER.getBlock().getDefaultState();
    }

    public static boolean isFreshWater(IBlockState current) {
        return current == FluidsTFC.FRESH_WATER.get().getBlock().getDefaultState();
    }

    public static boolean isSeaWater(IBlockState current) {
        return current == FluidsTFC.SEA_WATER.get().getBlock().getDefaultState();
    }

    public static boolean isFreshWaterOrIce(IBlockState current) {
        return current.getBlock() == Blocks.ICE || isVanillaWater(current);
    }

    public static boolean isRawStone(IBlockState current) {
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;

        Rock.Type type = ((TFCBlockRockVariant) current.getBlock()).getType();
        return type == RAW ||
                type == COBBLE ||
                type == SMOOTH ||
                type == MOSSY_RAW;
    }

    public static boolean isClay(IBlockState current) {
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;

        Type type = ((TFCBlockRockVariant) current.getBlock()).getType();
        return type == CLAY ||
                type == CLAY_GRASS ||
                type == MUD ||
                type == SANDY_CLAY_LOAM ||
                type == SANDY_CLAY ||
                type == CLAY_LOAM ||
                type == SILTY_CLAY_LOAM ||
                type == SILTY_CLAY ||
                type == COARSE_SANDY_CLAY_LOAM ||
                type == COARSE_SANDY_CLAY ||
                type == COARSE_CLAY_LOAM ||
                type == COARSE_CLAY ||
                type == COARSE_SILTY_CLAY ||
                type == COARSE_SILTY_CLAY_LOAM ||
                type == SANDY_CLAY_LOAM_GRASS ||
                type == SANDY_CLAY_LOAM_PODZOL ||
                type == SANDY_CLAY_GRASS ||
                type == SANDY_CLAY_PODZOL ||
                type == CLAY_LOAM_GRASS ||
                type == CLAY_LOAM_PODZOL ||
                type == CLAY_PODZOL ||
                type == SILTY_CLAY_GRASS ||
                type == SILTY_CLAY_PODZOL ||
                type == SILTY_CLAY_LOAM_GRASS ||
                type == SILTY_CLAY_LOAM_PODZOL ||
                type == DRY_SANDY_CLAY_LOAM_GRASS ||
                type == DRY_SANDY_CLAY_GRASS ||
                type == DRY_CLAY_LOAM_GRASS ||
                type == DRY_CLAY_GRASS ||
                type == DRY_SILTY_CLAY_GRASS ||
                type == DRY_SILTY_CLAY_LOAM_GRASS ||
                type == CLAY_HUMUS ||
                type == CLAY_HUMUS_GRASS ||
                type == DRY_CLAY_HUMUS_GRASS ||
                type == KAOLINITE_CLAY ||
                type == SANDY_KAOLINITE_CLAY_LOAM ||
                type == COARSE_SANDY_KAOLINITE_CLAY_LOAM ||
                type == SANDY_KAOLINITE_CLAY ||
                type == COARSE_SANDY_KAOLINITE_CLAY ||
                type == KAOLINITE_CLAY_LOAM ||
                type == COARSE_KAOLINITE_CLAY_LOAM ||
                type == COARSE_KAOLINITE_CLAY ||
                type == SILTY_KAOLINITE_CLAY ||
                type == COARSE_SILTY_KAOLINITE_CLAY ||
                type == SILTY_KAOLINITE_CLAY_LOAM ||
                type == COARSE_SILTY_KAOLINITE_CLAY_LOAM ||
                type == KAOLINITE_CLAY_HUMUS ||
                type == KAOLINITE_CLAY_GRASS ||
                type == SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SANDY_KAOLINITE_CLAY_LOAM_PODZOL ||
                type == SANDY_KAOLINITE_CLAY_GRASS ||
                type == SANDY_KAOLINITE_CLAY_PODZOL ||
                type == KAOLINITE_CLAY_LOAM_GRASS ||
                type == KAOLINITE_CLAY_LOAM_PODZOL ||
                type == KAOLINITE_CLAY_PODZOL ||
                type == SILTY_KAOLINITE_CLAY_GRASS ||
                type == SILTY_KAOLINITE_CLAY_PODZOL ||
                type == SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SILTY_KAOLINITE_CLAY_LOAM_PODZOL ||
                type == DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == DRY_SANDY_KAOLINITE_CLAY_GRASS ||
                type == DRY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == DRY_KAOLINITE_CLAY_GRASS ||
                type == DRY_SILTY_KAOLINITE_CLAY_GRASS ||
                type == DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == KAOLINITE_CLAY_HUMUS_GRASS ||
                type == DRY_KAOLINITE_CLAY_HUMUS_GRASS ||
                type == SPARSE_CLAY_GRASS ||
                type == SPARSE_SANDY_CLAY_LOAM_GRASS ||
                type == SPARSE_SANDY_CLAY_GRASS ||
                type == SPARSE_CLAY_LOAM_GRASS ||
                type == SPARSE_SILTY_CLAY_GRASS ||
                type == SPARSE_SILTY_CLAY_LOAM_GRASS ||
                type == SPARSE_CLAY_HUMUS_GRASS ||
                type == SPARSE_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SPARSE_SANDY_KAOLINITE_CLAY_GRASS ||
                type == SPARSE_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SPARSE_KAOLINITE_CLAY_GRASS ||
                type == SPARSE_SILTY_KAOLINITE_CLAY_GRASS ||
                type == SPARSE_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SPARSE_KAOLINITE_CLAY_HUMUS_GRASS ||
                type == STONEWARE_CLAY ||
                type == SANDY_STONEWARE_CLAY_LOAM ||
                type == COARSE_SANDY_STONEWARE_CLAY_LOAM ||
                type == SANDY_STONEWARE_CLAY ||
                type == COARSE_SANDY_STONEWARE_CLAY ||
                type == STONEWARE_CLAY_LOAM ||
                type == COARSE_STONEWARE_CLAY_LOAM ||
                type == COARSE_STONEWARE_CLAY ||
                type == SILTY_STONEWARE_CLAY ||
                type == COARSE_SILTY_STONEWARE_CLAY ||
                type == SILTY_STONEWARE_CLAY_LOAM ||
                type == COARSE_SILTY_STONEWARE_CLAY_LOAM ||
                type == STONEWARE_CLAY_HUMUS ||
                type == STONEWARE_CLAY_GRASS ||
                type == SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                type == SANDY_STONEWARE_CLAY_LOAM_PODZOL ||
                type == SANDY_STONEWARE_CLAY_GRASS ||
                type == SANDY_STONEWARE_CLAY_PODZOL ||
                type == STONEWARE_CLAY_LOAM_GRASS ||
                type == STONEWARE_CLAY_LOAM_PODZOL ||
                type == STONEWARE_CLAY_PODZOL ||
                type == SILTY_STONEWARE_CLAY_GRASS ||
                type == SILTY_STONEWARE_CLAY_PODZOL ||
                type == SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                type == SILTY_STONEWARE_CLAY_LOAM_PODZOL ||
                type == DRY_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                type == DRY_SANDY_STONEWARE_CLAY_GRASS ||
                type == DRY_STONEWARE_CLAY_LOAM_GRASS ||
                type == DRY_STONEWARE_CLAY_GRASS ||
                type == DRY_SILTY_STONEWARE_CLAY_GRASS ||
                type == DRY_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                type == STONEWARE_CLAY_HUMUS_GRASS ||
                type == DRY_STONEWARE_CLAY_HUMUS_GRASS ||
                type == SPARSE_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_SANDY_STONEWARE_CLAY_GRASS ||
                type == SPARSE_STONEWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_STONEWARE_CLAY_GRASS ||
                type == SPARSE_SILTY_STONEWARE_CLAY_GRASS ||
                type == SPARSE_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_STONEWARE_CLAY_HUMUS_GRASS ||
                type == EARTHENWARE_CLAY ||
                type == SANDY_EARTHENWARE_CLAY_LOAM ||
                type == COARSE_SANDY_EARTHENWARE_CLAY_LOAM ||
                type == SANDY_EARTHENWARE_CLAY ||
                type == COARSE_SANDY_EARTHENWARE_CLAY ||
                type == EARTHENWARE_CLAY_LOAM ||
                type == COARSE_EARTHENWARE_CLAY_LOAM ||
                type == COARSE_EARTHENWARE_CLAY ||
                type == SILTY_EARTHENWARE_CLAY ||
                type == COARSE_SILTY_EARTHENWARE_CLAY ||
                type == SILTY_EARTHENWARE_CLAY_LOAM ||
                type == COARSE_SILTY_EARTHENWARE_CLAY_LOAM ||
                type == EARTHENWARE_CLAY_HUMUS ||
                type == EARTHENWARE_CLAY_GRASS ||
                type == SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SANDY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                type == SANDY_EARTHENWARE_CLAY_GRASS ||
                type == SANDY_EARTHENWARE_CLAY_PODZOL ||
                type == EARTHENWARE_CLAY_LOAM_GRASS ||
                type == EARTHENWARE_CLAY_LOAM_PODZOL ||
                type == EARTHENWARE_CLAY_PODZOL ||
                type == SILTY_EARTHENWARE_CLAY_GRASS ||
                type == SILTY_EARTHENWARE_CLAY_PODZOL ||
                type == SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SILTY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                type == DRY_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == DRY_SANDY_EARTHENWARE_CLAY_GRASS ||
                type == DRY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == DRY_EARTHENWARE_CLAY_GRASS ||
                type == DRY_SILTY_EARTHENWARE_CLAY_GRASS ||
                type == DRY_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == EARTHENWARE_CLAY_HUMUS_GRASS ||
                type == DRY_EARTHENWARE_CLAY_HUMUS_GRASS ||
                type == SPARSE_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_SANDY_EARTHENWARE_CLAY_GRASS ||
                type == SPARSE_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_EARTHENWARE_CLAY_GRASS ||
                type == SPARSE_SILTY_EARTHENWARE_CLAY_GRASS ||
                type == SPARSE_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_EARTHENWARE_CLAY_HUMUS_GRASS;
    }

    public static boolean isDirt(IBlockState current) {
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;

        Type type = ((TFCBlockRockVariant) current.getBlock()).getType();
        return type == DIRT ||
                type == MUD ||
                type == ROOTED_DIRT ||
                type == BOG_IRON ||
                type == COARSE_DIRT ||
                type == LOAMY_SAND ||
                type == SANDY_LOAM ||
                type == LOAM ||
                type == SILT_LOAM ||
                type == SILT ||
                type == COARSE_LOAMY_SAND ||
                type == COARSE_SANDY_LOAM ||
                type == COARSE_LOAM ||
                type == COARSE_SILT_LOAM ||
                type == COARSE_SILT ||
                type == COARSE_HUMUS;
    }

    public static boolean isSand(IBlockState current) {
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;
        Rock.Type type = ((TFCBlockRockVariant) current.getBlock()).getType();
        return type == SAND;
    }

    // todo: change to property of type? (soil & stone maybe?)
    public static boolean isSoil(IBlockState current) {
        if (current.getBlock() instanceof BlockPeat) return true;
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;

        Type type = ((TFCBlockRockVariant) current.getBlock()).getType();
        return type == GRASS ||
                type == DRY_GRASS ||
                type == DIRT ||
                type == CLAY ||
                type == CLAY_GRASS ||
                type == ROOTED_DIRT ||
                type == COARSE_DIRT ||
                type == MUD ||
                type == BOG_IRON ||
                type == BOG_IRON_GRASS ||
                type == DRY_BOG_IRON_GRASS ||
                type == SPARSE_BOG_IRON_GRASS ||
                type == BOG_IRON_PODZOL ||
                type == LOAMY_SAND ||
                type == SANDY_LOAM ||
                type == LOAM ||
                type == SILT_LOAM ||
                type == SILT ||
                type == SANDY_CLAY_LOAM ||
                type == SANDY_CLAY ||
                type == CLAY_LOAM ||
                type == SILTY_CLAY_LOAM ||
                type == SILTY_CLAY ||
                type == COARSE_LOAMY_SAND ||
                type == COARSE_SANDY_LOAM ||
                type == COARSE_SANDY_CLAY_LOAM ||
                type == COARSE_SANDY_CLAY ||
                type == COARSE_LOAM ||
                type == COARSE_CLAY_LOAM ||
                type == COARSE_CLAY ||
                type == COARSE_SILTY_CLAY ||
                type == COARSE_SILTY_CLAY_LOAM ||
                type == COARSE_SILT_LOAM ||
                type == COARSE_SILT ||
                type == PODZOL ||
                type == LOAMY_SAND_GRASS ||
                type == LOAMY_SAND_PODZOL ||
                type == SANDY_LOAM_GRASS ||
                type == SANDY_LOAM_PODZOL ||
                type == SANDY_CLAY_LOAM_GRASS ||
                type == SANDY_CLAY_LOAM_PODZOL ||
                type == SANDY_CLAY_GRASS ||
                type == SANDY_CLAY_PODZOL ||
                type == LOAM_GRASS ||
                type == LOAM_PODZOL ||
                type == CLAY_LOAM_GRASS ||
                type == CLAY_LOAM_PODZOL ||
                type == CLAY_PODZOL ||
                type == SILTY_CLAY_GRASS ||
                type == SILTY_CLAY_PODZOL ||
                type == SILTY_CLAY_LOAM_GRASS ||
                type == SILTY_CLAY_LOAM_PODZOL ||
                type == SILT_LOAM_GRASS ||
                type == SILT_LOAM_PODZOL ||
                type == SILT_GRASS ||
                type == SILT_PODZOL ||
                type == DRY_LOAMY_SAND_GRASS ||
                type == DRY_SANDY_LOAM_GRASS ||
                type == DRY_SANDY_CLAY_LOAM_GRASS ||
                type == DRY_SANDY_CLAY_GRASS ||
                type == DRY_LOAM_GRASS ||
                type == DRY_CLAY_LOAM_GRASS ||
                type == DRY_CLAY_GRASS ||
                type == DRY_SILTY_CLAY_GRASS ||
                type == DRY_SILTY_CLAY_LOAM_GRASS ||
                type == DRY_SILT_LOAM_GRASS ||
                type == DRY_SILT_GRASS ||
                type == HUMUS ||
                type == COARSE_HUMUS ||
                type == HUMUS_GRASS ||
                type == DRY_HUMUS_GRASS ||
                type == CLAY_HUMUS ||
                type == CLAY_HUMUS_GRASS ||
                type == DRY_CLAY_HUMUS_GRASS ||
                type == KAOLINITE_CLAY ||
                type == SANDY_KAOLINITE_CLAY_LOAM ||
                type == COARSE_SANDY_KAOLINITE_CLAY_LOAM ||
                type == SANDY_KAOLINITE_CLAY ||
                type == COARSE_SANDY_KAOLINITE_CLAY ||
                type == KAOLINITE_CLAY_LOAM ||
                type == COARSE_KAOLINITE_CLAY_LOAM ||
                type == COARSE_KAOLINITE_CLAY ||
                type == SILTY_KAOLINITE_CLAY ||
                type == COARSE_SILTY_KAOLINITE_CLAY ||
                type == SILTY_KAOLINITE_CLAY_LOAM ||
                type == COARSE_SILTY_KAOLINITE_CLAY_LOAM ||
                type == KAOLINITE_CLAY_HUMUS ||
                type == KAOLINITE_CLAY_GRASS ||
                type == SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SANDY_KAOLINITE_CLAY_LOAM_PODZOL ||
                type == SANDY_KAOLINITE_CLAY_GRASS ||
                type == SANDY_KAOLINITE_CLAY_PODZOL ||
                type == KAOLINITE_CLAY_LOAM_GRASS ||
                type == KAOLINITE_CLAY_LOAM_PODZOL ||
                type == KAOLINITE_CLAY_PODZOL ||
                type == SILTY_KAOLINITE_CLAY_GRASS ||
                type == SILTY_KAOLINITE_CLAY_PODZOL ||
                type == SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SILTY_KAOLINITE_CLAY_LOAM_PODZOL ||
                type == DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == DRY_SANDY_KAOLINITE_CLAY_GRASS ||
                type == DRY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == DRY_KAOLINITE_CLAY_GRASS ||
                type == DRY_SILTY_KAOLINITE_CLAY_GRASS ||
                type == DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == KAOLINITE_CLAY_HUMUS_GRASS ||
                type == DRY_KAOLINITE_CLAY_HUMUS_GRASS ||
                type == SPARSE_GRASS ||
                type == SPARSE_CLAY_GRASS ||
                type == SPARSE_LOAMY_SAND_GRASS ||
                type == SPARSE_SANDY_LOAM_GRASS ||
                type == SPARSE_SANDY_CLAY_LOAM_GRASS ||
                type == SPARSE_SANDY_CLAY_GRASS ||
                type == SPARSE_LOAM_GRASS ||
                type == SPARSE_CLAY_LOAM_GRASS ||
                type == SPARSE_SILTY_CLAY_GRASS ||
                type == SPARSE_SILTY_CLAY_LOAM_GRASS ||
                type == SPARSE_SILT_LOAM_GRASS ||
                type == SPARSE_SILT_GRASS ||
                type == SPARSE_HUMUS_GRASS ||
                type == SPARSE_CLAY_HUMUS_GRASS ||
                type == SPARSE_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SPARSE_SANDY_KAOLINITE_CLAY_GRASS ||
                type == SPARSE_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SPARSE_KAOLINITE_CLAY_GRASS ||
                type == SPARSE_SILTY_KAOLINITE_CLAY_GRASS ||
                type == SPARSE_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SPARSE_KAOLINITE_CLAY_HUMUS_GRASS ||
                type == STONEWARE_CLAY ||
                type == SANDY_STONEWARE_CLAY_LOAM ||
                type == COARSE_SANDY_STONEWARE_CLAY_LOAM ||
                type == SANDY_STONEWARE_CLAY ||
                type == COARSE_SANDY_STONEWARE_CLAY ||
                type == STONEWARE_CLAY_LOAM ||
                type == COARSE_STONEWARE_CLAY_LOAM ||
                type == COARSE_STONEWARE_CLAY ||
                type == SILTY_STONEWARE_CLAY ||
                type == COARSE_SILTY_STONEWARE_CLAY ||
                type == SILTY_STONEWARE_CLAY_LOAM ||
                type == COARSE_SILTY_STONEWARE_CLAY_LOAM ||
                type == STONEWARE_CLAY_HUMUS ||
                type == STONEWARE_CLAY_GRASS ||
                type == SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                type == SANDY_STONEWARE_CLAY_LOAM_PODZOL ||
                type == SANDY_STONEWARE_CLAY_GRASS ||
                type == SANDY_STONEWARE_CLAY_PODZOL ||
                type == STONEWARE_CLAY_LOAM_GRASS ||
                type == STONEWARE_CLAY_LOAM_PODZOL ||
                type == STONEWARE_CLAY_PODZOL ||
                type == SILTY_STONEWARE_CLAY_GRASS ||
                type == SILTY_STONEWARE_CLAY_PODZOL ||
                type == SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                type == SILTY_STONEWARE_CLAY_LOAM_PODZOL ||
                type == DRY_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                type == DRY_SANDY_STONEWARE_CLAY_GRASS ||
                type == DRY_STONEWARE_CLAY_LOAM_GRASS ||
                type == DRY_STONEWARE_CLAY_GRASS ||
                type == DRY_SILTY_STONEWARE_CLAY_GRASS ||
                type == DRY_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                type == STONEWARE_CLAY_HUMUS_GRASS ||
                type == DRY_STONEWARE_CLAY_HUMUS_GRASS ||
                type == SPARSE_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_SANDY_STONEWARE_CLAY_GRASS ||
                type == SPARSE_STONEWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_STONEWARE_CLAY_GRASS ||
                type == SPARSE_SILTY_STONEWARE_CLAY_GRASS ||
                type == SPARSE_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_STONEWARE_CLAY_HUMUS_GRASS ||
                type == EARTHENWARE_CLAY ||
                type == SANDY_EARTHENWARE_CLAY_LOAM ||
                type == COARSE_SANDY_EARTHENWARE_CLAY_LOAM ||
                type == SANDY_EARTHENWARE_CLAY ||
                type == COARSE_SANDY_EARTHENWARE_CLAY ||
                type == EARTHENWARE_CLAY_LOAM ||
                type == COARSE_EARTHENWARE_CLAY_LOAM ||
                type == COARSE_EARTHENWARE_CLAY ||
                type == SILTY_EARTHENWARE_CLAY ||
                type == COARSE_SILTY_EARTHENWARE_CLAY ||
                type == SILTY_EARTHENWARE_CLAY_LOAM ||
                type == COARSE_SILTY_EARTHENWARE_CLAY_LOAM ||
                type == EARTHENWARE_CLAY_HUMUS ||
                type == EARTHENWARE_CLAY_GRASS ||
                type == SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SANDY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                type == SANDY_EARTHENWARE_CLAY_GRASS ||
                type == SANDY_EARTHENWARE_CLAY_PODZOL ||
                type == EARTHENWARE_CLAY_LOAM_GRASS ||
                type == EARTHENWARE_CLAY_LOAM_PODZOL ||
                type == EARTHENWARE_CLAY_PODZOL ||
                type == SILTY_EARTHENWARE_CLAY_GRASS ||
                type == SILTY_EARTHENWARE_CLAY_PODZOL ||
                type == SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SILTY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                type == DRY_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == DRY_SANDY_EARTHENWARE_CLAY_GRASS ||
                type == DRY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == DRY_EARTHENWARE_CLAY_GRASS ||
                type == DRY_SILTY_EARTHENWARE_CLAY_GRASS ||
                type == DRY_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == EARTHENWARE_CLAY_HUMUS_GRASS ||
                type == DRY_EARTHENWARE_CLAY_HUMUS_GRASS ||
                type == SPARSE_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_SANDY_EARTHENWARE_CLAY_GRASS ||
                type == SPARSE_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_EARTHENWARE_CLAY_GRASS ||
                type == SPARSE_SILTY_EARTHENWARE_CLAY_GRASS ||
                type == SPARSE_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_EARTHENWARE_CLAY_HUMUS_GRASS;
    }

    public static boolean isGrowableSoil(IBlockState current) {
        if (current.getBlock() instanceof BlockPeat) return false;
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;

        Type type = ((TFCBlockRockVariant) current.getBlock()).getType();
        return type == GRASS ||
                type == DRY_GRASS ||
                type == DIRT ||
                type == CLAY ||
                type == CLAY_GRASS ||
                type == ROOTED_DIRT ||
                type == COARSE_DIRT ||
                type == MUD ||
                type == BOG_IRON ||
                type == BOG_IRON_GRASS ||
                type == DRY_BOG_IRON_GRASS ||
                type == SPARSE_BOG_IRON_GRASS ||
                type == BOG_IRON_PODZOL ||
                type == LOAMY_SAND ||
                type == SANDY_LOAM ||
                type == LOAM ||
                type == SILT_LOAM ||
                type == SILT ||
                type == SANDY_CLAY_LOAM ||
                type == SANDY_CLAY ||
                type == CLAY_LOAM ||
                type == SILTY_CLAY_LOAM ||
                type == SILTY_CLAY ||
                type == COARSE_LOAMY_SAND ||
                type == COARSE_SANDY_LOAM ||
                type == COARSE_SANDY_CLAY_LOAM ||
                type == COARSE_SANDY_CLAY ||
                type == COARSE_LOAM ||
                type == COARSE_CLAY_LOAM ||
                type == COARSE_CLAY ||
                type == COARSE_SILTY_CLAY ||
                type == COARSE_SILTY_CLAY_LOAM ||
                type == COARSE_SILT_LOAM ||
                type == COARSE_SILT ||
                type == PODZOL ||
                type == LOAMY_SAND_GRASS ||
                type == LOAMY_SAND_PODZOL ||
                type == SANDY_LOAM_GRASS ||
                type == SANDY_LOAM_PODZOL ||
                type == SANDY_CLAY_LOAM_GRASS ||
                type == SANDY_CLAY_LOAM_PODZOL ||
                type == SANDY_CLAY_GRASS ||
                type == SANDY_CLAY_PODZOL ||
                type == LOAM_GRASS ||
                type == LOAM_PODZOL ||
                type == CLAY_LOAM_GRASS ||
                type == CLAY_LOAM_PODZOL ||
                type == CLAY_PODZOL ||
                type == SILTY_CLAY_GRASS ||
                type == SILTY_CLAY_PODZOL ||
                type == SILTY_CLAY_LOAM_GRASS ||
                type == SILTY_CLAY_LOAM_PODZOL ||
                type == SILT_LOAM_GRASS ||
                type == SILT_LOAM_PODZOL ||
                type == SILT_GRASS ||
                type == SILT_PODZOL ||
                type == DRY_LOAMY_SAND_GRASS ||
                type == DRY_SANDY_LOAM_GRASS ||
                type == DRY_SANDY_CLAY_LOAM_GRASS ||
                type == DRY_SANDY_CLAY_GRASS ||
                type == DRY_LOAM_GRASS ||
                type == DRY_CLAY_LOAM_GRASS ||
                type == DRY_CLAY_GRASS ||
                type == DRY_SILTY_CLAY_GRASS ||
                type == DRY_SILTY_CLAY_LOAM_GRASS ||
                type == DRY_SILT_LOAM_GRASS ||
                type == DRY_SILT_GRASS ||
                type == HUMUS ||
                type == COARSE_HUMUS ||
                type == HUMUS_GRASS ||
                type == DRY_HUMUS_GRASS ||
                type == CLAY_HUMUS ||
                type == CLAY_HUMUS_GRASS ||
                type == DRY_CLAY_HUMUS_GRASS ||
                type == KAOLINITE_CLAY ||
                type == SANDY_KAOLINITE_CLAY_LOAM ||
                type == COARSE_SANDY_KAOLINITE_CLAY_LOAM ||
                type == SANDY_KAOLINITE_CLAY ||
                type == COARSE_SANDY_KAOLINITE_CLAY ||
                type == KAOLINITE_CLAY_LOAM ||
                type == COARSE_KAOLINITE_CLAY_LOAM ||
                type == COARSE_KAOLINITE_CLAY ||
                type == SILTY_KAOLINITE_CLAY ||
                type == COARSE_SILTY_KAOLINITE_CLAY ||
                type == SILTY_KAOLINITE_CLAY_LOAM ||
                type == COARSE_SILTY_KAOLINITE_CLAY_LOAM ||
                type == KAOLINITE_CLAY_HUMUS ||
                type == KAOLINITE_CLAY_GRASS ||
                type == SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SANDY_KAOLINITE_CLAY_LOAM_PODZOL ||
                type == SANDY_KAOLINITE_CLAY_GRASS ||
                type == SANDY_KAOLINITE_CLAY_PODZOL ||
                type == KAOLINITE_CLAY_LOAM_GRASS ||
                type == KAOLINITE_CLAY_LOAM_PODZOL ||
                type == KAOLINITE_CLAY_PODZOL ||
                type == SILTY_KAOLINITE_CLAY_GRASS ||
                type == SILTY_KAOLINITE_CLAY_PODZOL ||
                type == SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SILTY_KAOLINITE_CLAY_LOAM_PODZOL ||
                type == DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == DRY_SANDY_KAOLINITE_CLAY_GRASS ||
                type == DRY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == DRY_KAOLINITE_CLAY_GRASS ||
                type == DRY_SILTY_KAOLINITE_CLAY_GRASS ||
                type == DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == KAOLINITE_CLAY_HUMUS_GRASS ||
                type == DRY_KAOLINITE_CLAY_HUMUS_GRASS ||
                type == SPARSE_GRASS ||
                type == SPARSE_CLAY_GRASS ||
                type == SPARSE_LOAMY_SAND_GRASS ||
                type == SPARSE_SANDY_LOAM_GRASS ||
                type == SPARSE_SANDY_CLAY_LOAM_GRASS ||
                type == SPARSE_SANDY_CLAY_GRASS ||
                type == SPARSE_LOAM_GRASS ||
                type == SPARSE_CLAY_LOAM_GRASS ||
                type == SPARSE_SILTY_CLAY_GRASS ||
                type == SPARSE_SILTY_CLAY_LOAM_GRASS ||
                type == SPARSE_SILT_LOAM_GRASS ||
                type == SPARSE_SILT_GRASS ||
                type == SPARSE_HUMUS_GRASS ||
                type == SPARSE_CLAY_HUMUS_GRASS ||
                type == SPARSE_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SPARSE_SANDY_KAOLINITE_CLAY_GRASS ||
                type == SPARSE_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SPARSE_KAOLINITE_CLAY_GRASS ||
                type == SPARSE_SILTY_KAOLINITE_CLAY_GRASS ||
                type == SPARSE_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SPARSE_KAOLINITE_CLAY_HUMUS_GRASS ||
                type == STONEWARE_CLAY ||
                type == SANDY_STONEWARE_CLAY_LOAM ||
                type == COARSE_SANDY_STONEWARE_CLAY_LOAM ||
                type == SANDY_STONEWARE_CLAY ||
                type == COARSE_SANDY_STONEWARE_CLAY ||
                type == STONEWARE_CLAY_LOAM ||
                type == COARSE_STONEWARE_CLAY_LOAM ||
                type == COARSE_STONEWARE_CLAY ||
                type == SILTY_STONEWARE_CLAY ||
                type == COARSE_SILTY_STONEWARE_CLAY ||
                type == SILTY_STONEWARE_CLAY_LOAM ||
                type == COARSE_SILTY_STONEWARE_CLAY_LOAM ||
                type == STONEWARE_CLAY_HUMUS ||
                type == STONEWARE_CLAY_GRASS ||
                type == SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                type == SANDY_STONEWARE_CLAY_LOAM_PODZOL ||
                type == SANDY_STONEWARE_CLAY_GRASS ||
                type == SANDY_STONEWARE_CLAY_PODZOL ||
                type == STONEWARE_CLAY_LOAM_GRASS ||
                type == STONEWARE_CLAY_LOAM_PODZOL ||
                type == STONEWARE_CLAY_PODZOL ||
                type == SILTY_STONEWARE_CLAY_GRASS ||
                type == SILTY_STONEWARE_CLAY_PODZOL ||
                type == SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                type == SILTY_STONEWARE_CLAY_LOAM_PODZOL ||
                type == DRY_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                type == DRY_SANDY_STONEWARE_CLAY_GRASS ||
                type == DRY_STONEWARE_CLAY_LOAM_GRASS ||
                type == DRY_STONEWARE_CLAY_GRASS ||
                type == DRY_SILTY_STONEWARE_CLAY_GRASS ||
                type == DRY_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                type == STONEWARE_CLAY_HUMUS_GRASS ||
                type == DRY_STONEWARE_CLAY_HUMUS_GRASS ||
                type == SPARSE_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_SANDY_STONEWARE_CLAY_GRASS ||
                type == SPARSE_STONEWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_STONEWARE_CLAY_GRASS ||
                type == SPARSE_SILTY_STONEWARE_CLAY_GRASS ||
                type == SPARSE_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_STONEWARE_CLAY_HUMUS_GRASS ||
                type == EARTHENWARE_CLAY ||
                type == SANDY_EARTHENWARE_CLAY_LOAM ||
                type == COARSE_SANDY_EARTHENWARE_CLAY_LOAM ||
                type == SANDY_EARTHENWARE_CLAY ||
                type == COARSE_SANDY_EARTHENWARE_CLAY ||
                type == EARTHENWARE_CLAY_LOAM ||
                type == COARSE_EARTHENWARE_CLAY_LOAM ||
                type == COARSE_EARTHENWARE_CLAY ||
                type == SILTY_EARTHENWARE_CLAY ||
                type == COARSE_SILTY_EARTHENWARE_CLAY ||
                type == SILTY_EARTHENWARE_CLAY_LOAM ||
                type == COARSE_SILTY_EARTHENWARE_CLAY_LOAM ||
                type == EARTHENWARE_CLAY_HUMUS ||
                type == EARTHENWARE_CLAY_GRASS ||
                type == SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SANDY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                type == SANDY_EARTHENWARE_CLAY_GRASS ||
                type == SANDY_EARTHENWARE_CLAY_PODZOL ||
                type == EARTHENWARE_CLAY_LOAM_GRASS ||
                type == EARTHENWARE_CLAY_LOAM_PODZOL ||
                type == EARTHENWARE_CLAY_PODZOL ||
                type == SILTY_EARTHENWARE_CLAY_GRASS ||
                type == SILTY_EARTHENWARE_CLAY_PODZOL ||
                type == SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SILTY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                type == DRY_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == DRY_SANDY_EARTHENWARE_CLAY_GRASS ||
                type == DRY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == DRY_EARTHENWARE_CLAY_GRASS ||
                type == DRY_SILTY_EARTHENWARE_CLAY_GRASS ||
                type == DRY_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == EARTHENWARE_CLAY_HUMUS_GRASS ||
                type == DRY_EARTHENWARE_CLAY_HUMUS_GRASS ||
                type == SPARSE_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_SANDY_EARTHENWARE_CLAY_GRASS ||
                type == SPARSE_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_EARTHENWARE_CLAY_GRASS ||
                type == SPARSE_SILTY_EARTHENWARE_CLAY_GRASS ||
                type == SPARSE_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_EARTHENWARE_CLAY_HUMUS_GRASS;
    }

    public static boolean isSoilOrGravel(IBlockState current) {
        if (current.getBlock() instanceof BlockPeat) return true;
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;

        Type type = ((TFCBlockRockVariant) current.getBlock()).getType();
        return type == GRASS ||
                type == DRY_GRASS ||
                type == DIRT ||
                type == GRAVEL ||
                type == CLAY ||
                type == ROOTED_DIRT ||
                type == COARSE_DIRT ||
                type == MUD ||
                type == BOG_IRON ||
                type == BOG_IRON_GRASS ||
                type == DRY_BOG_IRON_GRASS ||
                type == SPARSE_BOG_IRON_GRASS ||
                type == BOG_IRON_PODZOL ||
                type == LOAMY_SAND ||
                type == SANDY_LOAM ||
                type == LOAM ||
                type == SILT_LOAM ||
                type == SILT ||
                type == COARSE_LOAMY_SAND ||
                type == COARSE_SANDY_LOAM ||
                type == COARSE_LOAM ||
                type == COARSE_SILT_LOAM ||
                type == COARSE_SILT ||
                type == PODZOL ||
                type == LOAMY_SAND_GRASS ||
                type == LOAMY_SAND_PODZOL ||
                type == SANDY_LOAM_GRASS ||
                type == SANDY_LOAM_PODZOL ||
                type == LOAM_GRASS ||
                type == LOAM_PODZOL ||
                type == SILT_LOAM_GRASS ||
                type == SILT_LOAM_PODZOL ||
                type == SILT_GRASS ||
                type == SILT_PODZOL ||
                type == DRY_LOAMY_SAND_GRASS ||
                type == DRY_SANDY_LOAM_GRASS ||
                type == DRY_LOAM_GRASS ||
                type == DRY_SILT_LOAM_GRASS ||
                type == DRY_SILT_GRASS ||
                type == HUMUS ||
                type == COARSE_HUMUS ||
                type == HUMUS_GRASS ||
                type == DRY_HUMUS_GRASS ||
                type == SPARSE_GRASS ||
                type == SPARSE_LOAMY_SAND_GRASS ||
                type == SPARSE_SANDY_LOAM_GRASS ||
                type == SPARSE_LOAM_GRASS ||
                type == SPARSE_SILT_LOAM_GRASS ||
                type == SPARSE_SILT_GRASS ||
                type == SPARSE_HUMUS_GRASS;
    }

    public static boolean isGrass(IBlockState current) {
        if (current.getBlock() instanceof BlockPeatGrass) return true;
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;

        Rock.Type type = ((TFCBlockRockVariant) current.getBlock()).getType();
        return type.isGrass;
    }

    public static boolean isDryGrass(IBlockState current) {
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;

        Type type = ((TFCBlockRockVariant) current.getBlock()).getType();
        return type == DRY_GRASS ||
                type == DRY_BOG_IRON_GRASS ||
                type == DRY_LOAMY_SAND_GRASS ||
                type == DRY_SANDY_LOAM_GRASS ||
                type == DRY_SANDY_CLAY_LOAM_GRASS ||
                type == DRY_SANDY_CLAY_GRASS ||
                type == DRY_LOAM_GRASS ||
                type == DRY_CLAY_LOAM_GRASS ||
                type == DRY_CLAY_GRASS ||
                type == DRY_SILTY_CLAY_GRASS ||
                type == DRY_SILTY_CLAY_LOAM_GRASS ||
                type == DRY_SILT_LOAM_GRASS ||
                type == DRY_SILT_GRASS ||
                type == DRY_HUMUS_GRASS ||
                type == DRY_CLAY_HUMUS_GRASS ||
                type == DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == DRY_SANDY_KAOLINITE_CLAY_GRASS ||
                type == DRY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == DRY_KAOLINITE_CLAY_GRASS ||
                type == DRY_SILTY_KAOLINITE_CLAY_GRASS ||
                type == DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == DRY_KAOLINITE_CLAY_HUMUS_GRASS ||
                type == DRY_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                type == DRY_SANDY_STONEWARE_CLAY_GRASS ||
                type == DRY_STONEWARE_CLAY_LOAM_GRASS ||
                type == DRY_STONEWARE_CLAY_GRASS ||
                type == DRY_SILTY_STONEWARE_CLAY_GRASS ||
                type == DRY_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                type == DRY_STONEWARE_CLAY_HUMUS_GRASS ||
                type == DRY_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == DRY_SANDY_EARTHENWARE_CLAY_GRASS ||
                type == DRY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == DRY_EARTHENWARE_CLAY_GRASS ||
                type == DRY_SILTY_EARTHENWARE_CLAY_GRASS ||
                type == DRY_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == DRY_EARTHENWARE_CLAY_HUMUS_GRASS;
    }

    public static boolean isGround(IBlockState current) {
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;

        Type type = ((TFCBlockRockVariant) current.getBlock()).getType();
        return type == GRASS ||
                type == DRY_GRASS ||
                type == DIRT ||
                type == GRAVEL ||
                type == RAW ||
                type == SAND ||
                type == ROOTED_DIRT ||
                type == COARSE_DIRT ||
                type == MUD ||
                type == BOG_IRON ||
                type == BOG_IRON_GRASS ||
                type == DRY_BOG_IRON_GRASS ||
                type == SPARSE_BOG_IRON_GRASS ||
                type == BOG_IRON_PODZOL ||
                type == LOAMY_SAND ||
                type == SANDY_LOAM ||
                type == LOAM ||
                type == SILT_LOAM ||
                type == SILT ||
                type == SANDY_CLAY_LOAM ||
                type == SANDY_CLAY ||
                type == CLAY_LOAM ||
                type == SILTY_CLAY_LOAM ||
                type == SILTY_CLAY ||
                type == COARSE_LOAMY_SAND ||
                type == COARSE_SANDY_LOAM ||
                type == COARSE_SANDY_CLAY_LOAM ||
                type == COARSE_SANDY_CLAY ||
                type == COARSE_LOAM ||
                type == COARSE_CLAY_LOAM ||
                type == COARSE_CLAY ||
                type == COARSE_SILTY_CLAY ||
                type == COARSE_SILTY_CLAY_LOAM ||
                type == COARSE_SILT_LOAM ||
                type == COARSE_SILT ||
                type == PODZOL ||
                type == LOAMY_SAND_GRASS ||
                type == LOAMY_SAND_PODZOL ||
                type == SANDY_LOAM_GRASS ||
                type == SANDY_LOAM_PODZOL ||
                type == SANDY_CLAY_LOAM_GRASS ||
                type == SANDY_CLAY_LOAM_PODZOL ||
                type == SANDY_CLAY_GRASS ||
                type == SANDY_CLAY_PODZOL ||
                type == LOAM_GRASS ||
                type == LOAM_PODZOL ||
                type == CLAY_LOAM_GRASS ||
                type == CLAY_LOAM_PODZOL ||
                type == CLAY_PODZOL ||
                type == SILTY_CLAY_GRASS ||
                type == SILTY_CLAY_PODZOL ||
                type == SILTY_CLAY_LOAM_GRASS ||
                type == SILTY_CLAY_LOAM_PODZOL ||
                type == SILT_LOAM_GRASS ||
                type == SILT_LOAM_PODZOL ||
                type == SILT_GRASS ||
                type == SILT_PODZOL ||
                type == DRY_LOAMY_SAND_GRASS ||
                type == DRY_SANDY_LOAM_GRASS ||
                type == DRY_SANDY_CLAY_LOAM_GRASS ||
                type == DRY_SANDY_CLAY_GRASS ||
                type == DRY_LOAM_GRASS ||
                type == DRY_CLAY_LOAM_GRASS ||
                type == DRY_CLAY_GRASS ||
                type == DRY_SILTY_CLAY_GRASS ||
                type == DRY_SILTY_CLAY_LOAM_GRASS ||
                type == DRY_SILT_LOAM_GRASS ||
                type == DRY_SILT_GRASS ||
                type == HUMUS ||
                type == COARSE_HUMUS ||
                type == HUMUS_GRASS ||
                type == DRY_HUMUS_GRASS ||
                type == CLAY_HUMUS ||
                type == CLAY_HUMUS_GRASS ||
                type == DRY_CLAY_HUMUS_GRASS ||
                type == KAOLINITE_CLAY ||
                type == SANDY_KAOLINITE_CLAY_LOAM ||
                type == COARSE_SANDY_KAOLINITE_CLAY_LOAM ||
                type == SANDY_KAOLINITE_CLAY ||
                type == COARSE_SANDY_KAOLINITE_CLAY ||
                type == KAOLINITE_CLAY_LOAM ||
                type == COARSE_KAOLINITE_CLAY_LOAM ||
                type == COARSE_KAOLINITE_CLAY ||
                type == SILTY_KAOLINITE_CLAY ||
                type == COARSE_SILTY_KAOLINITE_CLAY ||
                type == SILTY_KAOLINITE_CLAY_LOAM ||
                type == COARSE_SILTY_KAOLINITE_CLAY_LOAM ||
                type == KAOLINITE_CLAY_HUMUS ||
                type == KAOLINITE_CLAY_GRASS ||
                type == SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SANDY_KAOLINITE_CLAY_LOAM_PODZOL ||
                type == SANDY_KAOLINITE_CLAY_GRASS ||
                type == SANDY_KAOLINITE_CLAY_PODZOL ||
                type == KAOLINITE_CLAY_LOAM_GRASS ||
                type == KAOLINITE_CLAY_LOAM_PODZOL ||
                type == KAOLINITE_CLAY_PODZOL ||
                type == SILTY_KAOLINITE_CLAY_GRASS ||
                type == SILTY_KAOLINITE_CLAY_PODZOL ||
                type == SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SILTY_KAOLINITE_CLAY_LOAM_PODZOL ||
                type == DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == DRY_SANDY_KAOLINITE_CLAY_GRASS ||
                type == DRY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == DRY_KAOLINITE_CLAY_GRASS ||
                type == DRY_SILTY_KAOLINITE_CLAY_GRASS ||
                type == DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == KAOLINITE_CLAY_HUMUS_GRASS ||
                type == DRY_KAOLINITE_CLAY_HUMUS_GRASS ||
                type == SPARSE_GRASS ||
                type == SPARSE_CLAY_GRASS ||
                type == SPARSE_LOAMY_SAND_GRASS ||
                type == SPARSE_SANDY_LOAM_GRASS ||
                type == SPARSE_SANDY_CLAY_LOAM_GRASS ||
                type == SPARSE_SANDY_CLAY_GRASS ||
                type == SPARSE_LOAM_GRASS ||
                type == SPARSE_CLAY_LOAM_GRASS ||
                type == SPARSE_SILTY_CLAY_GRASS ||
                type == SPARSE_SILTY_CLAY_LOAM_GRASS ||
                type == SPARSE_SILT_LOAM_GRASS ||
                type == SPARSE_SILT_GRASS ||
                type == SPARSE_HUMUS_GRASS ||
                type == SPARSE_CLAY_HUMUS_GRASS ||
                type == SPARSE_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SPARSE_SANDY_KAOLINITE_CLAY_GRASS ||
                type == SPARSE_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SPARSE_KAOLINITE_CLAY_GRASS ||
                type == SPARSE_SILTY_KAOLINITE_CLAY_GRASS ||
                type == SPARSE_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SPARSE_KAOLINITE_CLAY_HUMUS_GRASS ||
                type == STONEWARE_CLAY ||
                type == SANDY_STONEWARE_CLAY_LOAM ||
                type == COARSE_SANDY_STONEWARE_CLAY_LOAM ||
                type == SANDY_STONEWARE_CLAY ||
                type == COARSE_SANDY_STONEWARE_CLAY ||
                type == STONEWARE_CLAY_LOAM ||
                type == COARSE_STONEWARE_CLAY_LOAM ||
                type == COARSE_STONEWARE_CLAY ||
                type == SILTY_STONEWARE_CLAY ||
                type == COARSE_SILTY_STONEWARE_CLAY ||
                type == SILTY_STONEWARE_CLAY_LOAM ||
                type == COARSE_SILTY_STONEWARE_CLAY_LOAM ||
                type == STONEWARE_CLAY_HUMUS ||
                type == STONEWARE_CLAY_GRASS ||
                type == SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                type == SANDY_STONEWARE_CLAY_LOAM_PODZOL ||
                type == SANDY_STONEWARE_CLAY_GRASS ||
                type == SANDY_STONEWARE_CLAY_PODZOL ||
                type == STONEWARE_CLAY_LOAM_GRASS ||
                type == STONEWARE_CLAY_LOAM_PODZOL ||
                type == STONEWARE_CLAY_PODZOL ||
                type == SILTY_STONEWARE_CLAY_GRASS ||
                type == SILTY_STONEWARE_CLAY_PODZOL ||
                type == SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                type == SILTY_STONEWARE_CLAY_LOAM_PODZOL ||
                type == DRY_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                type == DRY_SANDY_STONEWARE_CLAY_GRASS ||
                type == DRY_STONEWARE_CLAY_LOAM_GRASS ||
                type == DRY_STONEWARE_CLAY_GRASS ||
                type == DRY_SILTY_STONEWARE_CLAY_GRASS ||
                type == DRY_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                type == STONEWARE_CLAY_HUMUS_GRASS ||
                type == DRY_STONEWARE_CLAY_HUMUS_GRASS ||
                type == SPARSE_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_SANDY_STONEWARE_CLAY_GRASS ||
                type == SPARSE_STONEWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_STONEWARE_CLAY_GRASS ||
                type == SPARSE_SILTY_STONEWARE_CLAY_GRASS ||
                type == SPARSE_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_STONEWARE_CLAY_HUMUS_GRASS ||
                type == EARTHENWARE_CLAY ||
                type == SANDY_EARTHENWARE_CLAY_LOAM ||
                type == COARSE_SANDY_EARTHENWARE_CLAY_LOAM ||
                type == SANDY_EARTHENWARE_CLAY ||
                type == COARSE_SANDY_EARTHENWARE_CLAY ||
                type == EARTHENWARE_CLAY_LOAM ||
                type == COARSE_EARTHENWARE_CLAY_LOAM ||
                type == COARSE_EARTHENWARE_CLAY ||
                type == SILTY_EARTHENWARE_CLAY ||
                type == COARSE_SILTY_EARTHENWARE_CLAY ||
                type == SILTY_EARTHENWARE_CLAY_LOAM ||
                type == COARSE_SILTY_EARTHENWARE_CLAY_LOAM ||
                type == EARTHENWARE_CLAY_HUMUS ||
                type == EARTHENWARE_CLAY_GRASS ||
                type == SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SANDY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                type == SANDY_EARTHENWARE_CLAY_GRASS ||
                type == SANDY_EARTHENWARE_CLAY_PODZOL ||
                type == EARTHENWARE_CLAY_LOAM_GRASS ||
                type == EARTHENWARE_CLAY_LOAM_PODZOL ||
                type == EARTHENWARE_CLAY_PODZOL ||
                type == SILTY_EARTHENWARE_CLAY_GRASS ||
                type == SILTY_EARTHENWARE_CLAY_PODZOL ||
                type == SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SILTY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                type == DRY_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == DRY_SANDY_EARTHENWARE_CLAY_GRASS ||
                type == DRY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == DRY_EARTHENWARE_CLAY_GRASS ||
                type == DRY_SILTY_EARTHENWARE_CLAY_GRASS ||
                type == DRY_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == EARTHENWARE_CLAY_HUMUS_GRASS ||
                type == DRY_EARTHENWARE_CLAY_HUMUS_GRASS ||
                type == SPARSE_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_SANDY_EARTHENWARE_CLAY_GRASS ||
                type == SPARSE_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_EARTHENWARE_CLAY_GRASS ||
                type == SPARSE_SILTY_EARTHENWARE_CLAY_GRASS ||
                type == SPARSE_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_EARTHENWARE_CLAY_HUMUS_GRASS;
    }

    public static boolean isClayGrass(IBlockState current) {
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;
        Type type = ((TFCBlockRockVariant) current.getBlock()).getType();

        return
                // Type.CLAY_GRASS ?
                type == SANDY_CLAY_LOAM_GRASS ||
                        type == SANDY_CLAY_GRASS ||
                        type == CLAY_LOAM_GRASS ||
                        type == SILTY_CLAY_GRASS ||
                        type == SILTY_CLAY_LOAM_GRASS ||
                        type == CLAY_HUMUS_GRASS;
    }

    public static boolean isClayDryGrass(IBlockState current) {
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;
        Type type = ((TFCBlockRockVariant) current.getBlock()).getType();

        return type == DRY_SANDY_CLAY_LOAM_GRASS ||
                type == DRY_SANDY_CLAY_GRASS ||
                type == DRY_CLAY_LOAM_GRASS ||
                type == DRY_CLAY_GRASS ||
                type == DRY_SILTY_CLAY_GRASS ||
                type == DRY_SILTY_CLAY_LOAM_GRASS ||
                type == SPARSE_CLAY_GRASS ||
                type == SPARSE_SANDY_CLAY_LOAM_GRASS ||
                type == SPARSE_SANDY_CLAY_GRASS ||
                type == SPARSE_CLAY_LOAM_GRASS ||
                type == SPARSE_SILTY_CLAY_GRASS ||
                type == SPARSE_SILTY_CLAY_LOAM_GRASS ||
                type == SPARSE_CLAY_HUMUS_GRASS;
    }

    public static boolean isKaoliniteClayGrass(IBlockState current) {
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;
        Type type = ((TFCBlockRockVariant) current.getBlock()).getType();

        return type == KAOLINITE_CLAY_GRASS ||
                type == SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SANDY_KAOLINITE_CLAY_GRASS ||
                type == KAOLINITE_CLAY_LOAM_GRASS ||
                type == SILTY_KAOLINITE_CLAY_GRASS ||
                type == SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == KAOLINITE_CLAY_HUMUS_GRASS;
    }

    public static boolean isKaoliniteClayDryGrass(IBlockState current) {
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;
        Type type = ((TFCBlockRockVariant) current.getBlock()).getType();

        return type == DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == DRY_SANDY_KAOLINITE_CLAY_GRASS ||
                type == DRY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == DRY_KAOLINITE_CLAY_GRASS ||
                type == DRY_SILTY_KAOLINITE_CLAY_GRASS ||
                type == DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == DRY_KAOLINITE_CLAY_HUMUS_GRASS ||
                type == SPARSE_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SPARSE_SANDY_KAOLINITE_CLAY_GRASS ||
                type == SPARSE_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SPARSE_KAOLINITE_CLAY_GRASS ||
                type == SPARSE_SILTY_KAOLINITE_CLAY_GRASS ||
                type == SPARSE_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SPARSE_KAOLINITE_CLAY_HUMUS_GRASS;
    }

    public static boolean isKaoliniteClayPodzol(IBlockState current) {
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;
        Type type = ((TFCBlockRockVariant) current.getBlock()).getType();

        return type == KAOLINITE_CLAY_PODZOL ||
                type == SANDY_KAOLINITE_CLAY_LOAM_PODZOL ||
                type == SANDY_KAOLINITE_CLAY_PODZOL ||
                type == KAOLINITE_CLAY_LOAM_PODZOL ||
                type == SILTY_KAOLINITE_CLAY_PODZOL ||
                type == SILTY_KAOLINITE_CLAY_LOAM_PODZOL;
    }

    public static boolean isKaoliniteClayDirt(IBlockState current) {
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;
        Type type = ((TFCBlockRockVariant) current.getBlock()).getType();

        return type == KAOLINITE_CLAY ||
                type == SANDY_KAOLINITE_CLAY_LOAM ||
                type == SANDY_KAOLINITE_CLAY ||
                type == KAOLINITE_CLAY_LOAM ||
                type == SILTY_KAOLINITE_CLAY ||
                type == SILTY_KAOLINITE_CLAY_LOAM ||
                type == KAOLINITE_CLAY_HUMUS;
    }

    public static boolean isStonewareClayGrass(IBlockState current) {
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;
        Type type = ((TFCBlockRockVariant) current.getBlock()).getType();

        return type == STONEWARE_CLAY_GRASS ||
                type == SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                type == SANDY_STONEWARE_CLAY_GRASS ||
                type == STONEWARE_CLAY_LOAM_GRASS ||
                type == SILTY_STONEWARE_CLAY_GRASS ||
                type == SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                type == STONEWARE_CLAY_HUMUS_GRASS;
    }

    public static boolean isStonewareClayDryGrass(IBlockState current) {
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;
        Type type = ((TFCBlockRockVariant) current.getBlock()).getType();

        return type == DRY_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                type == DRY_SANDY_STONEWARE_CLAY_GRASS ||
                type == DRY_STONEWARE_CLAY_LOAM_GRASS ||
                type == DRY_STONEWARE_CLAY_GRASS ||
                type == DRY_SILTY_STONEWARE_CLAY_GRASS ||
                type == DRY_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                type == DRY_STONEWARE_CLAY_HUMUS_GRASS ||
                type == SPARSE_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_SANDY_STONEWARE_CLAY_GRASS ||
                type == SPARSE_STONEWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_STONEWARE_CLAY_GRASS ||
                type == SPARSE_SILTY_STONEWARE_CLAY_GRASS ||
                type == SPARSE_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_STONEWARE_CLAY_HUMUS_GRASS;
    }

    public static boolean isStonewareClayPodzol(IBlockState current) {
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;
        Type type = ((TFCBlockRockVariant) current.getBlock()).getType();

        return type == STONEWARE_CLAY_PODZOL ||
                type == SANDY_STONEWARE_CLAY_LOAM_PODZOL ||
                type == SANDY_STONEWARE_CLAY_PODZOL ||
                type == STONEWARE_CLAY_LOAM_PODZOL ||
                type == SILTY_STONEWARE_CLAY_PODZOL ||
                type == SILTY_STONEWARE_CLAY_LOAM_PODZOL;
    }

    public static boolean isStonewareClayDirt(IBlockState current) {
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;
        Type type = ((TFCBlockRockVariant) current.getBlock()).getType();

        return type == STONEWARE_CLAY ||
                type == SANDY_STONEWARE_CLAY_LOAM ||
                type == SANDY_STONEWARE_CLAY ||
                type == STONEWARE_CLAY_LOAM ||
                type == SILTY_STONEWARE_CLAY ||
                type == SILTY_STONEWARE_CLAY_LOAM ||
                type == STONEWARE_CLAY_HUMUS;
    }

    public static boolean isEarthenwareClayGrass(IBlockState current) {
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;
        Type type = ((TFCBlockRockVariant) current.getBlock()).getType();

        return type == EARTHENWARE_CLAY_GRASS ||
                type == SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SANDY_EARTHENWARE_CLAY_GRASS ||
                type == EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SILTY_EARTHENWARE_CLAY_GRASS ||
                type == SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == EARTHENWARE_CLAY_HUMUS_GRASS;
    }

    public static boolean isEarthenwareClayDryGrass(IBlockState current) {
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;
        Type type = ((TFCBlockRockVariant) current.getBlock()).getType();

        return type == DRY_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == DRY_SANDY_EARTHENWARE_CLAY_GRASS ||
                type == DRY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == DRY_EARTHENWARE_CLAY_GRASS ||
                type == DRY_SILTY_EARTHENWARE_CLAY_GRASS ||
                type == DRY_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == DRY_EARTHENWARE_CLAY_HUMUS_GRASS ||
                type == SPARSE_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_SANDY_EARTHENWARE_CLAY_GRASS ||
                type == SPARSE_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_EARTHENWARE_CLAY_GRASS ||
                type == SPARSE_SILTY_EARTHENWARE_CLAY_GRASS ||
                type == SPARSE_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_EARTHENWARE_CLAY_HUMUS_GRASS;
    }

    public static boolean isEarthenwareClayPodzol(IBlockState current) {
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;
        Type type = ((TFCBlockRockVariant) current.getBlock()).getType();

        return type == EARTHENWARE_CLAY_PODZOL ||
                type == SANDY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                type == SANDY_EARTHENWARE_CLAY_PODZOL ||
                type == EARTHENWARE_CLAY_LOAM_PODZOL ||
                type == SILTY_EARTHENWARE_CLAY_PODZOL ||
                type == SILTY_EARTHENWARE_CLAY_LOAM_PODZOL;
    }

    public static boolean isEarthenwareClayDirt(IBlockState current) {
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;
        Type type = ((TFCBlockRockVariant) current.getBlock()).getType();

        return type == EARTHENWARE_CLAY ||
                type == SANDY_EARTHENWARE_CLAY_LOAM ||
                type == SANDY_EARTHENWARE_CLAY ||
                type == EARTHENWARE_CLAY_LOAM ||
                type == SILTY_EARTHENWARE_CLAY ||
                type == SILTY_EARTHENWARE_CLAY_LOAM ||
                type == EARTHENWARE_CLAY_HUMUS;
    }

    public static boolean isClayPodzol(IBlockState current) {
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;
        Type type = ((TFCBlockRockVariant) current.getBlock()).getType();

        return type == CLAY_PODZOL ||
                type == SANDY_CLAY_LOAM_PODZOL ||
                type == SANDY_CLAY_PODZOL ||
                type == CLAY_LOAM_PODZOL ||
                type == SILTY_CLAY_PODZOL ||
                type == SILTY_CLAY_LOAM_PODZOL;
    }

    public static boolean isClayDirt(IBlockState current) {
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;
        Type type = ((TFCBlockRockVariant) current.getBlock()).getType();

        return
                // CLAY ?
                type == SANDY_CLAY_LOAM ||
                        type == SANDY_CLAY ||
                        type == CLAY_LOAM ||
                        type == SILTY_CLAY_LOAM ||
                        type == SILTY_CLAY ||
                        type == CLAY_HUMUS;
    }

    public static boolean isPodzol(IBlockState current) {
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;
        Type type = ((TFCBlockRockVariant) current.getBlock()).getType();

        return type == PODZOL ||
                type == BOG_IRON_PODZOL ||
                type == LOAMY_SAND_PODZOL ||
                type == SANDY_LOAM_PODZOL ||
                type == SANDY_CLAY_LOAM_PODZOL ||
                type == SANDY_CLAY_PODZOL ||
                type == LOAM_PODZOL ||
                type == CLAY_LOAM_PODZOL ||
                type == CLAY_PODZOL ||
                type == SILTY_CLAY_PODZOL ||
                type == SILTY_CLAY_LOAM_PODZOL ||
                type == SILT_LOAM_PODZOL ||
                type == SILT_PODZOL ||
                type == SANDY_KAOLINITE_CLAY_LOAM_PODZOL ||
                type == SANDY_KAOLINITE_CLAY_PODZOL ||
                type == KAOLINITE_CLAY_LOAM_PODZOL ||
                type == KAOLINITE_CLAY_PODZOL ||
                type == SILTY_KAOLINITE_CLAY_PODZOL ||
                type == SILTY_KAOLINITE_CLAY_LOAM_PODZOL ||
                type == SANDY_STONEWARE_CLAY_LOAM_PODZOL ||
                type == SANDY_STONEWARE_CLAY_PODZOL ||
                type == STONEWARE_CLAY_LOAM_PODZOL ||
                type == STONEWARE_CLAY_PODZOL ||
                type == SILTY_STONEWARE_CLAY_PODZOL ||
                type == SILTY_STONEWARE_CLAY_LOAM_PODZOL ||
                type == SANDY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                type == SANDY_EARTHENWARE_CLAY_PODZOL ||
                type == EARTHENWARE_CLAY_LOAM_PODZOL ||
                type == EARTHENWARE_CLAY_PODZOL ||
                type == SILTY_EARTHENWARE_CLAY_PODZOL ||
                type == SILTY_EARTHENWARE_CLAY_LOAM_PODZOL;
    }

    public static boolean isSparseGrass(IBlockState current) {
        if (!(current.getBlock() instanceof TFCBlockRockVariant)) return false;
        Type type = ((TFCBlockRockVariant) current.getBlock()).getType();

        return type == SPARSE_GRASS ||
                type == SPARSE_BOG_IRON_GRASS ||
                type == SPARSE_CLAY_GRASS ||
                type == SPARSE_LOAMY_SAND_GRASS ||
                type == SPARSE_SANDY_LOAM_GRASS ||
                type == SPARSE_SANDY_CLAY_LOAM_GRASS ||
                type == SPARSE_SANDY_CLAY_GRASS ||
                type == SPARSE_LOAM_GRASS ||
                type == SPARSE_CLAY_LOAM_GRASS ||
                type == SPARSE_SILTY_CLAY_GRASS ||
                type == SPARSE_SILTY_CLAY_LOAM_GRASS ||
                type == SPARSE_SILT_LOAM_GRASS ||
                type == SPARSE_SILT_GRASS ||
                type == SPARSE_HUMUS_GRASS ||
                type == SPARSE_CLAY_HUMUS_GRASS ||
                type == SPARSE_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SPARSE_SANDY_KAOLINITE_CLAY_GRASS ||
                type == SPARSE_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SPARSE_KAOLINITE_CLAY_GRASS ||
                type == SPARSE_SILTY_KAOLINITE_CLAY_GRASS ||
                type == SPARSE_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SPARSE_KAOLINITE_CLAY_HUMUS_GRASS ||
                type == SPARSE_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_SANDY_STONEWARE_CLAY_GRASS ||
                type == SPARSE_STONEWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_STONEWARE_CLAY_GRASS ||
                type == SPARSE_SILTY_STONEWARE_CLAY_GRASS ||
                type == SPARSE_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_STONEWARE_CLAY_HUMUS_GRASS ||
                type == SPARSE_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_SANDY_EARTHENWARE_CLAY_GRASS ||
                type == SPARSE_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_EARTHENWARE_CLAY_GRASS ||
                type == SPARSE_SILTY_EARTHENWARE_CLAY_GRASS ||
                type == SPARSE_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SPARSE_EARTHENWARE_CLAY_HUMUS_GRASS;
    }

    private static <T extends Block> T register(IForgeRegistry<Block> r, String name, T block, CreativeTabs ct) {
        block.setCreativeTab(ct);
        return register(r, name, block);
    }

    private static <T extends Block> T register(IForgeRegistry<Block> r, String name, T block) {
        block.setRegistryName(MOD_ID, name);
        block.setTranslationKey(MOD_ID + "." + name.replace('/', '.'));
        r.register(block);
        return block;
    }

    private static <T extends TileEntity> void register(Class<T> te, String name) {
        TileEntity.register(MOD_ID + ":" + name, te);
    }
}
