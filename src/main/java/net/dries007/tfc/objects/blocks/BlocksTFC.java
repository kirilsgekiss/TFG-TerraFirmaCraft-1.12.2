/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import gregtech.api.GregTechAPI;
import net.dries007.tfc.api.capability.food.FoodData;
import net.dries007.tfc.api.types.*;
import net.dries007.tfc.compat.gregtech.materials.TFCMaterialFlags;
import net.dries007.tfc.objects.blocks.groundcover.*;
import net.dries007.tfc.objects.blocks.metal.TFCBlockCladding;
import net.dries007.tfc.objects.blocks.metal.TFCBlockLamp;
import net.dries007.tfc.objects.blocks.metal.TFCBlockMetalAnvil;
import net.dries007.tfc.objects.blocks.metal.TFCBlockMetalTrapDoor;
import net.dries007.tfc.objects.blocks.plants.*;
import net.dries007.tfc.objects.blocks.plants.BlockPlant.BlockPlantDummy1;
import net.dries007.tfc.objects.blocks.wood.*;
import net.dries007.tfc.objects.blocks.wood.tree.BlockLeavesTFC;
import net.dries007.tfc.objects.blocks.wood.tree.BlockLogTFC;
import net.dries007.tfc.objects.blocks.wood.tree.BlockSaplingTFC;
import net.dries007.tfc.objects.blocks.wood.tree.bamboo.BlockBambooLeaves;
import net.dries007.tfc.objects.blocks.wood.tree.bamboo.BlockBambooLog;
import net.dries007.tfc.objects.blocks.wood.tree.bamboo.BlockBambooSapling;
import net.dries007.tfc.objects.blocks.wood.tree.cinnamon.*;
import net.dries007.tfc.objects.blocks.wood.tree.fruitwood.*;
import net.dries007.tfc.objects.blocks.wood.tree.joshua.BlockJoshuaTreeFlower;
import net.dries007.tfc.objects.blocks.wood.tree.joshua.BlockJoshuaTreeLog;
import net.dries007.tfc.objects.blocks.wood.tree.joshua.BlockJoshuaTreeSapling;
import net.dries007.tfc.objects.fluids.FluidsTFC;
import net.dries007.tfc.objects.items.food.ItemBlockRot;
import net.dries007.tfc.objects.items.food.PotionEffectToHave;
import net.dries007.tfc.objects.te.*;
import net.dries007.tfc.types.DefaultPlants;
import net.dries007.tfc.types.DefaultTrees;
import net.dries007.tfc.util.agriculture.SeasonalTrees;
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
import net.dries007.tfc.objects.blocks.stone.BlockRockVariant;
import net.dries007.tfc.objects.items.itemblock.ItemBlockDryer;
import net.dries007.tfc.objects.items.itemblock.ItemBlockStickBundle;
import net.dries007.tfc.objects.items.itemblock.ItemBlockCrate;
import net.dries007.tfc.objects.items.itemblock.ItemBlockUrn;
import net.dries007.tfc.objects.items.itemblock.ItemBlockUrnLoot;
import net.dries007.tfc.api.types.Rock.Type;
import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.util.FallingBlockManager;
import net.dries007.tfc.objects.blocks.agriculture.*;
import net.dries007.tfc.objects.blocks.devices.*;
import net.dries007.tfc.objects.blocks.stone.*;
import net.dries007.tfc.objects.fluids.properties.FluidWrapper;
import net.dries007.tfc.objects.items.itemblock.*;
import net.dries007.tfc.util.agriculture.BerryBush;
import net.dries007.tfc.util.agriculture.Crop;
import net.dries007.tfc.util.agriculture.FruitTree;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;
import static net.dries007.tfc.api.types.Rock.Type.*;
import static net.dries007.tfc.objects.CreativeTabsTFC.*;
import static net.dries007.tfc.util.Helpers.getNull;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = MOD_ID)
@GameRegistry.ObjectHolder(MOD_ID)
public final class BlocksTFC
{
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
    public static final BlockSurfaceBones BONES = getNull();
    @GameRegistry.ObjectHolder("groundcover/driftwood")
    public static final BlockDriftwood DRIFTWOOD = getNull();
    @GameRegistry.ObjectHolder("groundcover/flint")
    public static final BlockSurfaceFlint FLINT = getNull();
    @GameRegistry.ObjectHolder("groundcover/pinecone")
    public static final BlockPinecone PINECONE = getNull();
    @GameRegistry.ObjectHolder("groundcover/seashell")
    public static final BlockSurfaceSeashells SEASHELLS = getNull();
    @GameRegistry.ObjectHolder("groundcover/twig")
    public static final BlockTwig TWIG = getNull();

    @GameRegistry.ObjectHolder("wood/fruit_tree/log/cassia_cinnamon")
    public static final BlockCassiaCinnamonLog CASSIA_CINNAMON_LOG = getNull();
    @GameRegistry.ObjectHolder("wood/fruit_tree/leaves/cassia_cinnamon")
    public static final BlockCassiaCinnamonLeaves CASSIA_CINNAMON_LEAVES = getNull();
    @GameRegistry.ObjectHolder("wood/fruit_tree/sapling/cassia_cinnamon")
    public static final BlockCassiaCinnamonSapling CASSIA_CINNAMON_SAPLING = getNull();
    @GameRegistry.ObjectHolder("wood/fruit_tree/log/ceylon_cinnamon")
    public static final BlockCeylonCinnamonLog CEYLON_CINNAMON_LOG = getNull();
    @GameRegistry.ObjectHolder("wood/fruit_tree/leaves/ceylon_cinnamon")
    public static final BlockCeylonCinnamonLeaves CEYLON_CINNAMON_LEAVES = getNull();
    @GameRegistry.ObjectHolder("wood/fruit_tree/sapling/ceylon_cinnamon")
    public static final BlockCeylonCinnamonSapling CEYLON_CINNAMON_SAPLING = getNull();

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
    @GameRegistry.ObjectHolder("wood/log/arrow_bamboo")
    public static final BlockBambooLog ARROW_BAMBOO_LOG = getNull();
    @GameRegistry.ObjectHolder("wood/leaves/arrow_bamboo")
    public static final BlockBambooLeaves ARROW_BAMBOO_LEAVES = getNull();
    @GameRegistry.ObjectHolder("wood/log/black_bamboo")
    public static final BlockBambooLog BLACK_BAMBOO_LOG = getNull();
    @GameRegistry.ObjectHolder("wood/leaves/black_bamboo")
    public static final BlockBambooLeaves BLACK_BAMBOO_LEAVES = getNull();
    @GameRegistry.ObjectHolder("wood/log/blue_bamboo")
    public static final BlockBambooLog BLUE_BAMBOO_LOG = getNull();
    @GameRegistry.ObjectHolder("wood/leaves/blue_bamboo")
    public static final BlockBambooLeaves BLUE_BAMBOO_LEAVES = getNull();
    @GameRegistry.ObjectHolder("wood/log/dragon_bamboo")
    public static final BlockBambooLog DRAGON_BAMBOO_LOG = getNull();
    @GameRegistry.ObjectHolder("wood/leaves/dragon_bamboo")
    public static final BlockBambooLeaves DRAGON_BAMBOO_LEAVES = getNull();
    @GameRegistry.ObjectHolder("wood/log/golden_bamboo")
    public static final BlockBambooLog GOLDEN_BAMBOO_LOG = getNull();
    @GameRegistry.ObjectHolder("wood/leaves/golden_bamboo")
    public static final BlockBambooLeaves GOLDEN_BAMBOO_LEAVES = getNull();
    @GameRegistry.ObjectHolder("wood/log/narrow_leaf_bamboo")
    public static final BlockBambooLog NARROW_LEAF_BAMBOO_LOG = getNull();
    @GameRegistry.ObjectHolder("wood/leaves/narrow_leaf_bamboo")
    public static final BlockBambooLeaves NARROW_LEAF_BAMBOO_LEAVES = getNull();
    @GameRegistry.ObjectHolder("wood/log/red_bamboo")
    public static final BlockBambooLog RED_BAMBOO_LOG = getNull();
    @GameRegistry.ObjectHolder("wood/leaves/red_bamboo")
    public static final BlockBambooLeaves RED_BAMBOO_LEAVES = getNull();
    @GameRegistry.ObjectHolder("wood/log/temple_bamboo")
    public static final BlockBambooLog TEMPLE_BAMBOO_LOG = getNull();
    @GameRegistry.ObjectHolder("wood/leaves/temple_bamboo")
    public static final BlockBambooLeaves TEMPLE_BAMBOO_LEAVES = getNull();
    @GameRegistry.ObjectHolder("wood/log/thorny_bamboo")
    public static final BlockBambooLog THORNY_BAMBOO_LOG = getNull();
    @GameRegistry.ObjectHolder("wood/leaves/thorny_bamboo")
    public static final BlockBambooLeaves THORNY_BAMBOO_LEAVES = getNull();
    @GameRegistry.ObjectHolder("wood/log/timber_bamboo")
    public static final BlockBambooLog TIMBER_BAMBOO_LOG = getNull();
    @GameRegistry.ObjectHolder("wood/leaves/timber_bamboo")
    public static final BlockBambooLeaves TIMBER_BAMBOO_LEAVES = getNull();
    @GameRegistry.ObjectHolder("wood/log/tinwa_bamboo")
    public static final BlockBambooLog TINWA_BAMBOO_LOG = getNull();
    @GameRegistry.ObjectHolder("wood/leaves/tinwa_bamboo")
    public static final BlockBambooLeaves TINWA_BAMBOO_LEAVES = getNull();
    @GameRegistry.ObjectHolder("wood/log/weavers_bamboo")
    public static final BlockBambooLog WEAVERS_BAMBOO_LOG = getNull();
    @GameRegistry.ObjectHolder("wood/leaves/weavers_bamboo")
    public static final BlockBambooLeaves WEAVERS_BAMBOO_LEAVES = getNull();

    @GameRegistry.ObjectHolder("coral/brain/dead")
    public static final BlockCoral BRAIN_CORAL_DEAD = getNull();
    @GameRegistry.ObjectHolder("coral/bubble/dead")
    public static final BlockCoral BUBBLE_CORAL_DEAD = getNull();
    @GameRegistry.ObjectHolder("coral/fire/dead")
    public static final BlockCoral FIRE_CORAL_DEAD = getNull();
    @GameRegistry.ObjectHolder("coral/horn/dead")
    public static final BlockCoral HORN_CORAL_DEAD = getNull();
    @GameRegistry.ObjectHolder("coral/tube/dead")
    public static final BlockCoral TUBE_CORAL_DEAD = getNull();

    @GameRegistry.ObjectHolder("coral/fan/brain/dead")
    public static final BlockCoral BRAIN_CORAL_FAN_DEAD = getNull();
    @GameRegistry.ObjectHolder("coral/fan/bubble/dead")
    public static final BlockCoral BUBBLE_CORAL_FAN_DEAD = getNull();
    @GameRegistry.ObjectHolder("coral/fan/fire/dead")
    public static final BlockCoral FIRE_CORAL_FAN_DEAD = getNull();
    @GameRegistry.ObjectHolder("coral/fan/horn/dead")
    public static final BlockCoral HORN_CORAL_FAN_DEAD = getNull();
    @GameRegistry.ObjectHolder("coral/fan/tube/dead")
    public static final BlockCoral TUBE_CORAL_FAN_DEAD = getNull();

    @GameRegistry.ObjectHolder("coral/block/brain/dead")
    public static final BlockCoralBlock BRAIN_CORAL_BLOCK_DEAD = getNull();
    @GameRegistry.ObjectHolder("coral/block/bubble/dead")
    public static final BlockCoralBlock BUBBLE_CORAL_BLOCK_DEAD = getNull();
    @GameRegistry.ObjectHolder("coral/block/fire/dead")
    public static final BlockCoralBlock FIRE_CORAL_BLOCK_DEAD = getNull();
    @GameRegistry.ObjectHolder("coral/block/horn/dead")
    public static final BlockCoralBlock HORN_CORAL_BLOCK_DEAD = getNull();
    @GameRegistry.ObjectHolder("coral/block/tube/dead")
    public static final BlockCoralBlock TUBE_CORAL_BLOCK_DEAD = getNull();

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
    public static final BlockLightstone LIGHTSTONE = getNull();

    @GameRegistry.ObjectHolder("plants/saguaro_cactus")
    public static final TFCBlockSaguaroCactus SAGUARO_CACTUS = getNull();


    // All these are for use in model registration. Do not use for block lookups.
    // Use the static get methods in the classes instead.
    private static ImmutableList<ItemBlock> allNormalItemBlocks = getNull();
    private static ImmutableList<ItemBlock> allInventoryItemBlocks = getNull();
    private static ImmutableList<ItemBlock> allColorizedItemBlocks = getNull();
    private static ImmutableList<TFCBlockBarrel> allBarrelBlocksBlocks = getNull();

    private static ImmutableList<BlockFluidBase> allFluidBlocks = getNull();
    private static ImmutableList<BlockRockVariant> allBlockRockVariants = getNull();
    private static ImmutableList<TFCBlockRockWall> allWallBlocks = getNull();
    private static ImmutableList<BlockLogTFC> allLogBlocks = getNull();
    private static ImmutableList<TFCBlockPlanks> allPlankBlocks = getNull();
    private static ImmutableList<TFCBlockWorkbench> allWorkbenchBlocks = getNull();
    private static ImmutableList<TFCBlockBookshelf> allBookshelfBlocks = getNull();
    private static ImmutableList<BlockLeavesTFC> allLeafBlocks = getNull();
    private static ImmutableList<TFCBlockFenceGate> allFenceGateBlocks = getNull();
    private static ImmutableList<TFCBlockFence> allFenceBlocks = getNull();
    private static ImmutableList<TFCBlockWoodPressurePlate> allWoodPressurePlateBlocks = getNull();
    private static ImmutableList<TFCBlockWoodButton> allWoodButtonBlocks = getNull();
    private static ImmutableList<BlockSaplingTFC> allSaplingBlocks = getNull();
    private static ImmutableList<TFCBlockWoodDoor> allDoorBlocks = getNull();
    private static ImmutableList<TFCBlockWoodTrapDoor> allTrapDoorWoodBlocks = getNull();
    private static ImmutableList<TFCBlockMetalTrapDoor> allBlockMetalTrapDoor = getNull();
    private static ImmutableList<TFCBlockRockStairs> allRockStairsBlocks = getNull();
    private static ImmutableList<TFCBlockWoodStairs> allWoodStairsBlocks = getNull();
    private static ImmutableList<TFCBlockRockSlab.Half> allRockSlabBlocks = getNull();
    private static ImmutableList<TFCBlockWoodSlab.Half> allWoodSlabBlocks = getNull();
    private static ImmutableList<TFCBlockChest> allChestBlocks = getNull();
    private static ImmutableList<TFCBlockMetalAnvil> allBlcokAnvils = getNull();
    private static ImmutableList<TFCBlockCladding> allBlockCladding = getNull();
    private static ImmutableList<TFCBlockLamp> allBlockLamps = getNull();
    private static ImmutableList<TFCBlockToolRack> allToolRackBlocks = getNull();
    private static ImmutableList<TFCBlockCrop> allCropBlocks = getNull();
    private static ImmutableList<BlockCropDead> allDeadCropBlocks = getNull();
    private static ImmutableList<TFCBlockPlant> allPlantBlocks = getNull();
    private static ImmutableList<TFCBlockPlant> allGrassBlocks = getNull();
    private static ImmutableList<TFCBlockLoom> allLoomBlocks = getNull();
    private static ImmutableList<TFCBlockWoodSupport> allSupportBlocks = getNull();
    private static ImmutableList<BlockFlowerPotTFC> allFlowerPots = getNull();


    private static ImmutableList<Block> allFoodItemBlocks = getNull();
    private static ImmutableList<TFCBlockFenceGateLog> allFenceGateLogBlocks = getNull();
    private static ImmutableList<BlockFruitTreeLeaves> allFruitLeaves = getNull();
    private static ImmutableList<BlockFruitTreeSapling> allFruitSapling = getNull();
    //private static ImmutableList<BlockFruitBarrelTest> allFruitBarrel = getNull();
    private static ImmutableList<BlockFruitBookshelf> allFruitBookshelves = getNull();
    private static ImmutableList<BlockFruitButton> allFruitButton = getNull();
    private static ImmutableList<BlockFruitDoor> allFruitDoors = getNull();
    private static ImmutableList<BlockFruitPressurePlate> allFruitPressurePlate = getNull();
    private static ImmutableList<BlockFruitFence> allFruitFences = getNull();
    private static ImmutableList<BlockFruitFenceGate> allFruitFenceGates = getNull();
    private static ImmutableList<BlockFruitLogFence> allFruitLogFences = getNull();
    private static ImmutableList<BlockFruitLogFenceGate> allFruitLogFenceGates = getNull();
    private static ImmutableList<BlockFruitPlanks> allFruitPlanks = getNull();
    private static ImmutableList<BlockFruitSlab.Half> allFruitSlabBlocks = getNull();
    private static ImmutableList<BlockFruitStairs> allFruitStairBlocks = getNull();
    private static ImmutableList<BlockFruitSupport> allFruitSupport = getNull();
    private static ImmutableList<BlockFruitToolRack> allFruitToolRack = getNull();
    private static ImmutableList<BlockFruitTrapDoor> allFruitTrapDoors = getNull();
    private static ImmutableList<BlockFruitWorkbench> allFruitWorkbench = getNull();
    private static ImmutableList<BlockFruitChest> allFruitChestBlocks = getNull();
    private static ImmutableList<BlockFruitLoom> allFruitLoomBlocks = getNull();
    private static ImmutableList<BlockCropDead> allDeadCrops = getNull();
    private static ImmutableList<TFCBlockRockSlab.Half> allSlabBlocksTFC = getNull();
    private static ImmutableList<TFCBlockWoodStairs> allStairBlocksTFC = getNull();
    private static ImmutableList<TFCBlockPlanks> allPlanksTFC = getNull();
    private static ImmutableList<BlockSurfaceRock> allSurfaceRocks = getNull();
    private static ImmutableList<BlockSurfaceSeashells> allSurfaceSeashells = getNull();
    private static ImmutableList<BlockSurfaceFlint> allSurfaceFlint = getNull();
    private static ImmutableList<BlockSurfaceBones> allSurfaceBones = getNull();
    private static ImmutableList<BlockDriftwood> allSurfaceDriftwood = getNull();
    private static ImmutableList<BlockTwig> allSurfaceTwig = getNull();
    private static ImmutableList<BlockPinecone> allSurfacePinecone = getNull();
    private static ImmutableList<Block> allBambooLog = getNull();
    private static ImmutableList<Block> allBambooLeaves = getNull();
    private static ImmutableList<Block> allBambooSapling = getNull();
    private static ImmutableList<BlockFruitLeaves> allNormalTreeLeaves = getNull();
    private static ImmutableList<BlockFruitLog> allNormalTreeLog = getNull();
    private static ImmutableList<BlockCoral> allCoralPlants = getNull();
    private static ImmutableList<TFCBlockWaterGlowPlant> allGlowWaterPlants = getNull();
    private static ImmutableList<TFCBlockWaterPlant> allWaterPlantBlocks = getNull();
    private static ImmutableList<TFCBlockHangingPlant> allHangingPlantBlocks = getNull();
    private static ImmutableList<TFCBlockHangingGlowingPlant> allHangingGlowingPlantBlocks = getNull();
    private static ImmutableList<TFCBlockHangingCreepingPlant> allHangingCreepingPlantBlocks = getNull();
    private static ImmutableList<TFCBlockHangingGlowingCreepingPlant> allHangingGlowingCreepingPlantBlocks = getNull();
    private static ImmutableList<TFCBlockCreepingPlant> allCreepingPlantBlocks = getNull();
    private static ImmutableList<TFCBlockTallGrassWater> allTallGrassWaterBlocks = getNull();
    private static ImmutableList<TFCBlockShortGrass> allShortGrassBlocks = getNull();
    private static ImmutableList<TFCBlockSaguaroCactus> allSaguaroCactusBlocks = getNull();
    private static ImmutableList<BlockPlantDummy1> allStandardBlocks = getNull();
    private static ImmutableList<BlockLightstone> allLightstoneBlocks = getNull();
    private static ImmutableList<BlockJoshuaTreeFlower> allJoshuaTreeFlowerBlocks = getNull();
    private static ImmutableList<BlockJoshuaTreeLog> allJoshuaTreeLogBlocks = getNull();
    private static ImmutableList<BlockJoshuaTreeSapling> allJoshuaTreeSaplingBlocks = getNull();

    private static ImmutableList<BlockFruitTreeSapling> allFruitTreeSaplingBlocks = getNull();
    private static ImmutableList<BlockFruitTreeTrunk> allFruitTreeTrunkBlocks = getNull();
    private static ImmutableList<BlockFruitTreeBranch> allFruitTreeBranchBlocks = getNull();
    private static ImmutableList<BlockFruitTreeLeaves> allFruitTreeLeavesBlocks = getNull();

    private static ImmutableList<BlockBerryBush> allBerryBushBlocks = getNull();

    public static ImmutableList<ItemBlock> getAllNormalItemBlocks()
    {
        return allNormalItemBlocks;
    }

    public static ImmutableList<ItemBlock> getAllInventoryItemBlocks() {
        return allInventoryItemBlocks;
    }

    public static ImmutableList<ItemBlock> getAllColorizedItemBlocks() {
        return allColorizedItemBlocks;
    }

    public static ImmutableList<TFCBlockBarrel> getAllBarrelBlocks()
    {
        return allBarrelBlocksBlocks;
    }

    public static ImmutableList<BlockFluidBase> getAllFluidBlocks()
    {
        return allFluidBlocks;
    }

    public static ImmutableList<BlockRockVariant> getAllBlockRockVariants()
    {
        return allBlockRockVariants;
    }

    public static ImmutableList<BlockLogTFC> getAllLogBlocks()
    {
        return allLogBlocks;
    }

    public static ImmutableList<TFCBlockPlanks> getAllPlankBlocks() {
        return allPlankBlocks;
    }

    public static ImmutableList<TFCBlockWorkbench> getAllWorkbenchBlocks() {
        return allWorkbenchBlocks;
    }

    public static ImmutableList<TFCBlockBookshelf> getAllBookshelfBlocks() {
        return allBookshelfBlocks;
    }

    public static ImmutableList<BlockLeavesTFC> getAllLeafBlocks()
    {
        return allLeafBlocks;
    }

    public static ImmutableList<TFCBlockFenceGate> getAllFenceGateBlocks()
    {
        return allFenceGateBlocks;
    }

    public static ImmutableList<TFCBlockFence> getAllFenceBlocks()
    {
        return allFenceBlocks;
    }

    public static ImmutableList<TFCBlockWoodPressurePlate> getAllWoodPressurePlateBlocks()
    {
        return allWoodPressurePlateBlocks;
    }

    public static ImmutableList<TFCBlockWoodButton> getAllWoodButtonBlocks()
    {
        return allWoodButtonBlocks;
    }

    public static ImmutableList<TFCBlockRockWall> getAllWallBlocks()
    {
        return allWallBlocks;
    }

    public static ImmutableList<BlockSaplingTFC> getAllSaplingBlocks()
    {
        return allSaplingBlocks;
    }

    public static ImmutableList<TFCBlockWoodDoor> getAllDoorBlocks()
    {
        return allDoorBlocks;
    }

    public static ImmutableList<TFCBlockWoodTrapDoor> getAllTrapDoorWoodBlocks()
    {
        return allTrapDoorWoodBlocks;
    }

    public static ImmutableList<TFCBlockMetalTrapDoor> getAllBlockMetalTrapDoor()
    {
        return allBlockMetalTrapDoor;
    }

    public static ImmutableList<TFCBlockRockStairs> getAllRockStairsBlocks()
    {
        return allRockStairsBlocks;
    }

    public static ImmutableList<TFCBlockWoodStairs> getAllWoodStairsBlocks()
    {
        return allWoodStairsBlocks;
    }

    public static ImmutableList<TFCBlockRockSlab.Half> getAllRockSlabBlocks()
    {
        return allRockSlabBlocks;
    }

    public static ImmutableList<TFCBlockWoodSlab.Half> getAllWoodSlabBlocks()
    {
        return allWoodSlabBlocks;
    }

    public static ImmutableList<TFCBlockChest> getAllChestBlocks()
    {
        return allChestBlocks;
    }

    public static ImmutableList<TFCBlockMetalAnvil> getAllBlcokAnvils()
    {
        return allBlcokAnvils;
    }

    public static ImmutableList<TFCBlockCladding> getAllBlockCladding()
    {
        return allBlockCladding;
    }

    public static ImmutableList<TFCBlockLamp> getAllBlockLamps()
    {
        return allBlockLamps;
    }

    public static ImmutableList<TFCBlockToolRack> getAllToolRackBlocks()
    {
        return allToolRackBlocks;
    }

    public static ImmutableList<TFCBlockCrop> getAllCropBlocks()
    {
        return allCropBlocks;
    }

    public static ImmutableList<BlockCropDead> getAllDeadCropBlocks()
    {
        return allDeadCropBlocks;
    }

    public static ImmutableList<TFCBlockPlant> getAllPlantBlocks()
    {
        return allPlantBlocks;
    }

    public static ImmutableList<TFCBlockPlant> getAllGrassBlocks()
    {
        return allGrassBlocks;
    }

    public static ImmutableList<TFCBlockLoom> getAllLoomBlocks()
    {
        return allLoomBlocks;
    }

    public static ImmutableList<TFCBlockWoodSupport> getAllSupportBlocks()
    {
        return allSupportBlocks;
    }

    public static ImmutableList<BlockFlowerPotTFC> getAllFlowerPots()
    {
        return allFlowerPots;
    }

    public static ImmutableList<BlockFruitTreeSapling> getAllFruitTreeSaplingBlocks() {return allFruitTreeSaplingBlocks;}

    public static ImmutableList<BlockFruitTreeTrunk> getAllFruitTreeTrunkBlocks()
    {
        return allFruitTreeTrunkBlocks;
    }

    public static ImmutableList<BlockFruitTreeBranch> getAllFruitTreeBranchBlocks()
    {
        return allFruitTreeBranchBlocks;
    }

    public static ImmutableList<BlockFruitTreeLeaves> getAllFruitTreeLeavesBlocks()
    {
        return allFruitTreeLeavesBlocks;
    }

    public static ImmutableList<BlockBerryBush> getAllBerryBushBlocks()
    {
        return allBerryBushBlocks;
    }

    public static ImmutableList<Block> getAllFoodIBs()
    {
        return allFoodItemBlocks;
    }

    public static ImmutableList<TFCBlockFenceGateLog> getAllFenceGateLogBlocks()
    {
        return allFenceGateLogBlocks;
    }

    public static ImmutableList<BlockFruitTreeLeaves> getAllFruitLeaves()
    {
        return allFruitLeaves;
    }

    public static ImmutableList<BlockFruitTreeSapling> getAllFruitSapling()
    {
        return allFruitSapling;
    }

    /*public static ImmutableList<BlockFruitBarrelTest> getAllFruitBarrel()
    {
        return allFruitBarrel;
    }*/

    public static ImmutableList<BlockFruitBookshelf> getAllFruitBookshelves()
    {
        return allFruitBookshelves;
    }

    public static ImmutableList<BlockFruitButton> getAllFruitButton()
    {
        return allFruitButton;
    }

    public static ImmutableList<BlockFruitDoor> getAllFruitDoors()
    {
        return allFruitDoors;
    }

    public static ImmutableList<BlockFruitPlanks> getAllFruitPlanks()
    {
        return allFruitPlanks;
    }

    public static ImmutableList<TFCBlockPlanks> getAllPlanksTFC()
    {
        return allPlanksTFC;
    }

    public static ImmutableList<BlockFruitSlab.Half> getAllFruitSlabBlocks()
    {
        return allFruitSlabBlocks;
    }

    public static ImmutableList<BlockFruitStairs> getAllFruitStairBlocks()
    {
        return allFruitStairBlocks;
    }

    public static ImmutableList<BlockFruitPressurePlate> getAllFruitPressurePlate()
    {
        return allFruitPressurePlate;
    }

    public static ImmutableList<BlockFruitFence> getAllFruitFences()
    {
        return allFruitFences;
    }

    public static ImmutableList<BlockFruitFenceGate> getAllFruitFenceGates()
    {
        return allFruitFenceGates;
    }

    public static ImmutableList<BlockFruitLogFence> getAllFruitLogFences()
    {
        return allFruitLogFences;
    }

    public static ImmutableList<BlockFruitLogFenceGate> getAllFruitLogFenceGates()
    {
        return allFruitLogFenceGates;
    }

    public static ImmutableList<BlockFruitSupport> getAllFruitSupport()
    {
        return allFruitSupport;
    }

    public static ImmutableList<BlockFruitToolRack> getAllFruitToolRack()
    {
        return allFruitToolRack;
    }

    public static ImmutableList<BlockFruitTrapDoor> getAllFruitTrapdoors()
    {
        return allFruitTrapDoors;
    }

    public static ImmutableList<BlockFruitWorkbench> getAllFruitWorkbench()
    {
        return allFruitWorkbench;
    }

    public static ImmutableList<BlockFruitChest> getAllFruitChestBlocks()
    {
        return allFruitChestBlocks;
    }

    public static ImmutableList<BlockFruitLoom> getAllFruitLoomBlocks()
    {
        return allFruitLoomBlocks;
    }
    public static ImmutableList<BlockCropDead> getAllDeadCrops()
    {
        return allDeadCrops;
    }

    public static ImmutableList<TFCBlockRockSlab.Half> getAllSlabBlocksTFC()
    {
        return allSlabBlocksTFC;
    }

    public static ImmutableList<TFCBlockWoodStairs> getAllStairBlocksTFC()
    {
        return allStairBlocksTFC;
    }

    public static ImmutableList<BlockSurfaceRock> getAllSurfaceRocks()
    {
        return allSurfaceRocks;
    }

    public static ImmutableList<BlockSurfaceSeashells> getAllSurfaceSeashells()
    {
        return allSurfaceSeashells;
    }

    public static ImmutableList<BlockSurfaceFlint> getAllSurfaceFlint()
    {
        return allSurfaceFlint;
    }

    public static ImmutableList<BlockSurfaceBones> getAllSurfaceBones()
    {
        return allSurfaceBones;
    }

    public static ImmutableList<BlockDriftwood> getAllSurfaceDriftwood()
    {
        return allSurfaceDriftwood;
    }

    public static ImmutableList<BlockTwig> getAllSurfaceTwig()
    {
        return allSurfaceTwig;
    }

    public static ImmutableList<BlockPinecone> getAllSurfacePinecone()
    {
        return allSurfacePinecone;
    }

    public static ImmutableList<Block> getAllBambooLog()
    {
        return allBambooLog;
    }

    public static ImmutableList<Block> getAllBambooLeaves()
    {
        return allBambooLeaves;
    }

    public static ImmutableList<Block> getAllBambooSapling()
    {
        return allBambooSapling;
    }

    public static ImmutableList<BlockFruitLeaves> getAllNormalTreeLeaves()
    {
        return allNormalTreeLeaves;
    }

    public static ImmutableList<BlockFruitLog> getAllNormalTreeLog()
    {
        return allNormalTreeLog;
    }

    public static ImmutableList<BlockCoral> getAllCoralPlants()
    {
        return allCoralPlants;
    }

    public static ImmutableList<TFCBlockWaterGlowPlant> getAllGlowWaterPlants()
    {
        return allGlowWaterPlants;
    }

    public static ImmutableList<TFCBlockWaterPlant> getAllWaterPlantBlocks()
    {
        return allWaterPlantBlocks;
    }

    public static ImmutableList<TFCBlockHangingPlant> getAllHangingPlantBlocks()
    {
        return allHangingPlantBlocks;
    }

    public static ImmutableList<TFCBlockHangingGlowingPlant> getAllHangingGlowingPlantBlocks()
    {
        return allHangingGlowingPlantBlocks;
    }

    public static ImmutableList<TFCBlockHangingCreepingPlant> getAllHangingCreepingPlantBlocks()
    {
        return allHangingCreepingPlantBlocks;
    }

    public static ImmutableList<TFCBlockHangingGlowingCreepingPlant> getAllHangingGlowingCreepingPlantBlocks()
    {
        return allHangingGlowingCreepingPlantBlocks;
    }

    public static ImmutableList<TFCBlockCreepingPlant> getAllCreepingPlantBlocks()
    {
        return allCreepingPlantBlocks;
    }

    public static ImmutableList<TFCBlockTallGrassWater> getAllTallGrassWaterBlocks()
    {
        return allTallGrassWaterBlocks;
    }

    public static ImmutableList<TFCBlockShortGrass> getAllShortGrassBlocks()
    {
        return allShortGrassBlocks;
    }

    public static ImmutableList<TFCBlockSaguaroCactus> getAllSaguaroCactusBlocks()
    {
        return allSaguaroCactusBlocks;
    }

    public static ImmutableList<BlockPlantDummy1> getAllStandardBlocks()
    {
        return allStandardBlocks;
    }

    public static ImmutableList<BlockLightstone> getAllLightstoneBlocks()
    {
        return allLightstoneBlocks;
    }

    public static ImmutableList<BlockJoshuaTreeFlower> getAllJoshuaTreeFlowerBlocks()
    {
        return allJoshuaTreeFlowerBlocks;
    }

    public static ImmutableList<BlockJoshuaTreeLog> getAllJoshuaTreeLogBlocks()
    {
        return allJoshuaTreeLogBlocks;
    }

    public static ImmutableList<BlockJoshuaTreeSapling> getAllJoshuaTreeSaplingBlocks()
    {
        return allJoshuaTreeSaplingBlocks;
    }

    public static String[] bamboo = {"arrow_bamboo", "black_bamboo", "blue_bamboo", "dragon_bamboo", "golden_bamboo", "narrow_leaf_bamboo", "red_bamboo", "temple_bamboo", "thorny_bamboo", "timber_bamboo", "tinwa_bamboo", "weavers_bamboo"};
    public static Tree[] bambooTrees = {DefaultTrees.ARROW_BAMBOO, DefaultTrees.BLACK_BAMBOO, DefaultTrees.BLUE_BAMBOO, DefaultTrees.DRAGON_BAMBOO, DefaultTrees.GOLDEN_BAMBOO, DefaultTrees.NARROW_LEAF_BAMBOO, DefaultTrees.RED_BAMBOO, DefaultTrees.TEMPLE_BAMBOO, DefaultTrees.THORNY_BAMBOO, DefaultTrees.TIMBER_BAMBOO, DefaultTrees.TINWA_BAMBOO, DefaultTrees.WEAVERS_BAMBOO};

    @SubscribeEvent
    @SuppressWarnings("ConstantConditions")
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        // This is called here because it needs to wait until Metal registry has fired
        FluidsTFC.registerFluids();
        IForgeRegistry<Block> r = event.getRegistry();

        Builder<ItemBlock> normalItemBlocks = ImmutableList.builder();
        Builder<ItemBlock> colorizedItemBlocks = ImmutableList.builder();
        Builder<ItemBlock> inventoryItemBlocks = ImmutableList.builder();

        ImmutableList.Builder<Block> itemBambooLog = ImmutableList.builder();
        ImmutableList.Builder<Block> itemBambooLeaves = ImmutableList.builder();
        ImmutableList.Builder<Block> itemBambooSapling = ImmutableList.builder();
        ImmutableList.Builder<BlockFruitLeaves> itemNormalTreeLeaves = ImmutableList.builder();
        ImmutableList.Builder<BlockFruitLog> normalTreeLog = ImmutableList.builder();
        ImmutableList.Builder<Block> foodItemBlocks = ImmutableList.builder();
        ImmutableList.Builder<TFCBlockFenceGateLog> fenceGatesLog = ImmutableList.builder();
        ImmutableList.Builder<BlockFruitTreeLeaves> fruitLeaves = ImmutableList.builder();
        ImmutableList.Builder<BlockFruitTreeSapling> fruitSapling = ImmutableList.builder();
        //ImmutableList.Builder<BlockFruitBarrelTest> fruitBarrel = ImmutableList.builder();
        ImmutableList.Builder<BlockFruitBookshelf> fruitBookshelves = ImmutableList.builder();
        ImmutableList.Builder<BlockFruitButton> fruitButton = ImmutableList.builder();
        ImmutableList.Builder<BlockFruitDoor> fruitDoors = ImmutableList.builder();
        ImmutableList.Builder<BlockFruitPlanks> fruitPlanks = ImmutableList.builder();
        ImmutableList.Builder<BlockFruitSlab.Half> fruitSlab = new Builder<>();
        ImmutableList.Builder<BlockFruitStairs> fruitStairs = new Builder<>();
        ImmutableList.Builder<BlockFruitPressurePlate> fruitPressurePlate = ImmutableList.builder();
        ImmutableList.Builder<BlockFruitFence> fruitFences = ImmutableList.builder();
        ImmutableList.Builder<BlockFruitFenceGate> fruitFenceGates = ImmutableList.builder();
        ImmutableList.Builder<BlockFruitLogFence> fruitLogFences = ImmutableList.builder();
        ImmutableList.Builder<BlockFruitLogFenceGate> fruitLogFenceGates = ImmutableList.builder();
        ImmutableList.Builder<BlockFruitSupport> fruitSupport = ImmutableList.builder();
        ImmutableList.Builder<BlockFruitToolRack> fruitToolRack = ImmutableList.builder();
        ImmutableList.Builder<BlockFruitTrapDoor> fruitTrapdoors = ImmutableList.builder();
        ImmutableList.Builder<BlockFruitWorkbench> fruitWorkbench = ImmutableList.builder();
        ImmutableList.Builder<BlockFruitChest> fruitChests = ImmutableList.builder();
        ImmutableList.Builder<BlockFruitLoom> fruitLoom = ImmutableList.builder();
        ImmutableList.Builder<TFCBlockCrop> cropBlocks = ImmutableList.builder();
        ImmutableList.Builder<BlockCropDead> deadCrops = ImmutableList.builder();
        ImmutableList.Builder<BlockRockVariant> blockRockVariantsTFCF = ImmutableList.builder();
        ImmutableList.Builder<BlockSurfaceRock> surfaceRock = ImmutableList.builder();
        ImmutableList.Builder<BlockSurfaceSeashells> surfaceSeashell = ImmutableList.builder();
        ImmutableList.Builder<BlockSurfaceFlint> surfaceFlint = ImmutableList.builder();
        ImmutableList.Builder<BlockSurfaceBones> surfaceBone = ImmutableList.builder();
        ImmutableList.Builder<BlockDriftwood> surfaceDriftwood = ImmutableList.builder();
        ImmutableList.Builder<BlockTwig> surfaceTwig = ImmutableList.builder();
        ImmutableList.Builder<BlockPinecone> surfacePinecone = ImmutableList.builder();
        ImmutableList.Builder<TFCBlockRockSlab.Half> blockSlabTFC = new Builder<>();
        ImmutableList.Builder<TFCBlockWoodStairs> blockStairTFC = new Builder<>();
        ImmutableList.Builder<TFCBlockPlanks> planksTFC = ImmutableList.builder();
        ImmutableList.Builder<BlockPebbleWater> pebbleWater = ImmutableList.builder();
        ImmutableList.Builder<BlockCoral> plantCoral = ImmutableList.builder();
        ImmutableList.Builder<TFCBlockWaterGlowPlant> plantGlowWater = ImmutableList.builder();
        ImmutableList.Builder<BlockLightstone> blockLightstone = ImmutableList.builder();
        ImmutableList.Builder<ItemBlockCondenser> itemBlockCondenser = ImmutableList.builder();
        //ImmutableList.Builder<MultiBlockBase> multiBlock = ImmutableList.builder();


        normalItemBlocks.add(new TFCItemBlock(register(r, "debug", new BlockDebug(), CT_MISC)));

        normalItemBlocks.add(new TFCItemBlock(register(r, "aggregate", new BlockAggregate(), CT_ROCK_BLOCKS)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "fire_clay_block", new BlockFireClay(), CT_ROCK_BLOCKS)));

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

        normalItemBlocks.add(new TFCItemBlock(register(r, "ceramics/earthenware/earthenware_clay_block", new BlockEarthenwareClay(), CT_ROCK_BLOCKS)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "ceramics/earthenware/earthenware_bricks", new BlockFireBrick(), CT_DECORATIONS)));
        normalItemBlocks.add(new ItemBlockLargeVessel(register(r, "ceramics/earthenware/fired/large_vessel", new BlockLargeVessel(), CT_POTTERY)));

        normalItemBlocks.add(new TFCItemBlock(register(r, "ceramics/kaolinite/kaolinite_clay_block", new BlockKaoliniteClay(), CT_ROCK_BLOCKS)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "ceramics/kaolinite/kaolinite_bricks", new BlockFireBrick(), CT_DECORATIONS)));
        normalItemBlocks.add(new ItemBlockLargeVessel(register(r, "ceramics/kaolinite/fired/large_vessel", new BlockLargeVessel(), CT_POTTERY)));

        normalItemBlocks.add(new TFCItemBlock(register(r, "ceramics/stoneware/stoneware_clay_block", new BlockStonewareClay(), CT_ROCK_BLOCKS)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "ceramics/stoneware/stoneware_bricks", new BlockFireBrick(), CT_DECORATIONS)));
        normalItemBlocks.add(new ItemBlockLargeVessel(register(r, "ceramics/stoneware/fired/large_vessel", new BlockLargeVessel(), CT_POTTERY)));

        normalItemBlocks.add(new ItemBlockUrn(register(r, "storage/urn", new BlockUrn(), CT_POTTERY)));
        normalItemBlocks.add(new ItemBlockUrnLoot(register(r, "storage/urn_loot", new BlockUrnLoot(), CT_POTTERY)));
        normalItemBlocks.add(new ItemBlockCrate(register(r, "storage/crate", new BlockCrate(), CT_DECORATIONS)));

        for (EnumDyeColor dyeColor : EnumDyeColor.values())
        {
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
                    register(r, "fluid/hot_water", new BlockFluidHotWater()),
                    register(r, "fluid/fresh_water", new BlockFluidWater(FluidsTFC.FRESH_WATER.get(), Material.WATER, false)),
                    register(r, "fluid/sea_water", new BlockFluidWater(FluidsTFC.SEA_WATER.get(), Material.WATER, true)),
                    register(r, "fluid/distilled_water", new BlockFluidTFC(FluidsTFC.DISTILLED_WATER.get(), Material.WATER, false)),
                    register(r, "fluid/waste", new BlockFluidTFC(FluidsTFC.WASTE.get(), Material.WATER, false)),
                    register(r, "fluid/base_potash_liquor", new BlockFluidTFC(FluidsTFC.BASE_POTASH_LIQUOR.get(), Material.WATER, false)),
                    register(r, "fluid/sweet_sap", new BlockFluidTFC(FluidsTFC.SWEET_SAP.get(), Material.WATER, false)),
                    register(r, "fluid/sweet_syrup", new BlockFluidTFC(FluidsTFC.SWEET_SYRUP.get(), Material.WATER, false)),
                    register(r, "fluid/resin", new BlockFluidTFC(FluidsTFC.RESIN.get(), Material.WATER, false)),
                    register(r, "fluid/kino", new BlockFluidTFC(FluidsTFC.KINO.get(), Material.WATER, false)),
                    register(r, "fluid/salammoniac", new BlockFluidTFC(FluidsTFC.SALAMMONIAC.get(), Material.WATER, false))
            );
            for (FluidWrapper wrapper : FluidsTFC.getAllOtherFiniteFluids())
            {
                fluids.add(register(r, "fluid/" + wrapper.get().getName(), new BlockFluidTFC(wrapper.get(), Material.WATER, false)));
            }
            for (FluidWrapper wrapper : FluidsTFC.getAllFermentedAlcoholsFluids())
            {
                fluids.add(register(r, "fluid/" + wrapper.get().getName(), new BlockFluidTFC(wrapper.get(), Material.WATER, false)));
            }
            for (FluidWrapper wrapper : FluidsTFC.getAllAlcoholsFluids())
            {
                fluids.add(register(r, "fluid/" + wrapper.get().getName(), new BlockFluidTFC(wrapper.get(), Material.WATER, false)));
            }
            for (FluidWrapper wrapper : FluidsTFC.getAllBeerFluids())
            {
                fluids.add(register(r, "fluid/" + wrapper.get().getName(), new BlockFluidTFC(wrapper.get(), Material.WATER, false)));
            }
            for (FluidWrapper wrapper : FluidsTFC.getAllTeaFluids())
            {
                fluids.add(register(r, "fluid/" + wrapper.get().getName(), new BlockFluidTFC(wrapper.get(), Material.WATER, false)));
            }
            for (FluidWrapper wrapper : FluidsTFC.getAllCoffeeFluids())
            {
                fluids.add(register(r, "fluid/" + wrapper.get().getName(), new BlockFluidTFC(wrapper.get(), Material.WATER, false)));
            }
            for (FluidWrapper wrapper : FluidsTFC.getAllJuiceBerryFluids())
            {
                fluids.add(register(r, "fluid/" + wrapper.get().getName(), new BlockFluidTFC(wrapper.get(), Material.WATER, false)));
            }
            for (FluidWrapper wrapper : FluidsTFC.getAllJuiceFruitFluids())
            {
                fluids.add(register(r, "fluid/" + wrapper.get().getName(), new BlockFluidTFC(wrapper.get(), Material.WATER, false)));
            }
            for (FluidWrapper wrapper : FluidsTFC.getAllMiscFluids())
            {
                fluids.add(register(r, "fluid/" + wrapper.get().getName(), new BlockFluidTFC(wrapper.get(), Material.WATER, false)));
            }
            for (EnumDyeColor color : EnumDyeColor.values())
            {
                FluidWrapper wrapper = FluidsTFC.getFluidFromDye(color);
                fluids.add(register(r, "fluid/" + wrapper.get().getName(), new BlockFluidTFC(wrapper.get(), Material.WATER, false)));
            }
            allFluidBlocks = fluids.build();
        }

        {
            Builder<BlockRockVariant> b = ImmutableList.builder();
            for (Type type : values())
            {
                for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
                {
                    if (type != ANVIL)
                    {
                        b.add(register(r, type.name().toLowerCase() + "/" + rock.getRegistryName().getPath(), BlockRockVariant.create(rock, type), CT_ROCK_BLOCKS));
                    }
                    else if (rock.getRockCategory().hasAnvil())
                    {
                        // Anvil registration is special, is has it's own folder
                        register(r, "anvil/" + rock.getRegistryName().getPath(), BlockRockVariant.create(rock, type));
                    }
                }
            }
            allBlockRockVariants = b.build();
            allBlockRockVariants.forEach(x ->
            {
                if (x.getType() == SAND)
                {
                    normalItemBlocks.add(new ItemBlockHeat(x, 1, 600));
                }
                else if (x.getType() != SPIKE && x.getType() != ANVIL)
                {
                    normalItemBlocks.add(new TFCItemBlock(x));
                }
            });
        }

        {
            // Add resultingState to the registered collapsable blocks.
            for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
            {
                for (Rock.Type type : values())
                {
                    FallingBlockManager.Specification spec = type.getFallingSpecification();
                    switch (type)
                    {
                        case ANVIL:
                            if (!rock.getRockCategory().hasAnvil())
                            {
                                break;
                            }
                        case RAW:
                            spec = new FallingBlockManager.Specification(spec);
                            spec.setResultingState(BlockRockVariant.get(rock, COBBLE).getDefaultState());
                            FallingBlockManager.registerFallable(BlockRockVariant.get(rock, RAW), spec);
                            break;
                        case SMOOTH:
                            spec = new FallingBlockManager.Specification(spec);
                            spec.setResultingState(BlockRockVariant.get(rock, COBBLE).getDefaultState());
                            FallingBlockManager.registerFallable(BlockRockVariant.get(rock, SMOOTH).getDefaultState().withProperty(TFCBlockRockSmooth.CAN_FALL, true), spec);
                            break;
                        default:
                            Rock.Type nonGrassType = type.getNonGrassVersion();
                            if (nonGrassType != type)
                            {
                                spec = new FallingBlockManager.Specification(spec);
                                spec.setResultingState(BlockRockVariant.get(rock, nonGrassType).getDefaultState());
                            }
                            FallingBlockManager.registerFallable(BlockRockVariant.get(rock, type), spec);
                    }
                }
            }
        }



        {
            Builder<BlockLogTFC> blockLogs = ImmutableList.builder();
            Builder<TFCBlockPlanks> blockPlanks = ImmutableList.builder();
            Builder<TFCBlockWorkbench> blockWorkbenches = ImmutableList.builder();
            Builder<TFCBlockBookshelf> blockBookshelfs = ImmutableList.builder();
            Builder<BlockLeavesTFC> blockLeaves = ImmutableList.builder();
            Builder<TFCBlockFenceGate> blockFenceGates = ImmutableList.builder();
            Builder<TFCBlockFence> blockFence = ImmutableList.builder();
            Builder<TFCBlockWoodPressurePlate> blockWoodPressurePlate = ImmutableList.builder();
            Builder<TFCBlockWoodButton> blockWoodButton = ImmutableList.builder();
            Builder<BlockSaplingTFC> blockSaplings = ImmutableList.builder();
            Builder<TFCBlockWoodDoor> blockDoors = ImmutableList.builder();
            Builder<TFCBlockWoodTrapDoor> blockTrapDoors = ImmutableList.builder();
            Builder<TFCBlockChest> blockChests = ImmutableList.builder();
            Builder<TFCBlockToolRack> blockToolRacks = ImmutableList.builder();
            Builder<TFCBlockBarrel> blockBarrel = ImmutableList.builder();
            Builder<TFCBlockPlant> blockPlants = ImmutableList.builder();
            Builder<TFCBlockLoom> blockLooms = ImmutableList.builder();
            Builder<TFCBlockWoodSupport> blockSupports = ImmutableList.builder();

            // Other blocks that don't have specific order requirements
            for (Wood wood : TFCRegistries.WOODS.getValuesCollection())
            {

                blockChests.add(register(r, "wood/chest/" + wood.getRegistryName().getPath(), new TFCBlockChest(TFCBlockChest.TFCBASIC, wood), CT_DECORATIONS));
                blockChests.add(register(r, "wood/chest_trap/" + wood.getRegistryName().getPath(), new TFCBlockChest(TFCBlockChest.TFCTRAP, wood), CT_DECORATIONS));

            }

            for (Tree wood : TFCRegistries.TREES.getValuesCollection())
            {
                blockLogs.add(register(r, "wood/log/" + wood.getRegistryName().getPath(), new BlockLogTFC(wood), CT_WOOD));
                blockPlanks.add(register(r, "wood/planks/" + wood.getRegistryName().getPath(), new TFCBlockPlanks(wood), CT_WOOD));
                blockWorkbenches.add(register(r, "wood/workbench/" + wood.getRegistryName().getPath(), new TFCBlockWorkbench(wood), CT_DECORATIONS));
                blockBookshelfs.add(register(r, "wood/bookshelf/" + wood.getRegistryName().getPath(), new TFCBlockBookshelf(wood), CT_DECORATIONS));
                blockLeaves.add(register(r, "wood/leaves/" + wood.getRegistryName().getPath(), new BlockLeavesTFC(wood), CT_WOOD));
                blockFenceGates.add(register(r, "wood/fence_gate/" + wood.getRegistryName().getPath(), new TFCBlockFenceGate(wood), CT_DECORATIONS));
                blockFence.add(register(r, "wood/fence/" + wood.getRegistryName().getPath(), new TFCBlockFence(wood), CT_DECORATIONS));
                blockWoodButton.add(register(r, "wood/button/" + wood.getRegistryName().getPath(), new TFCBlockWoodButton(wood), CT_DECORATIONS));
                blockWoodPressurePlate.add(register(r, "wood/pressure_plate/" + wood.getRegistryName().getPath().toLowerCase(), new TFCBlockWoodPressurePlate(wood), CT_DECORATIONS));
                blockSaplings.add(register(r, "wood/sapling/" + wood.getRegistryName().getPath(), new BlockSaplingTFC(wood), CT_WOOD));
                blockDoors.add(register(r, "wood/door/" + wood.getRegistryName().getPath(), new TFCBlockWoodDoor(wood), CT_DECORATIONS));
                blockTrapDoors.add(register(r, "wood/trapdoor/" + wood.getRegistryName().getPath(), new TFCBlockWoodTrapDoor(wood), CT_DECORATIONS));


                blockToolRacks.add(register(r, "wood/tool_rack/" + wood.getRegistryName().getPath(), new TFCBlockToolRack(wood), CT_DECORATIONS));
                blockBarrel.add(register(r, "wood/barrel/" + wood.getRegistryName().getPath(), new TFCBlockBarrel(wood), CT_DECORATIONS));

                blockLooms.add(register(r, "wood/loom/" + wood.getRegistryName().getPath(), new TFCBlockLoom(wood), CT_WOOD));
                blockSupports.add(register(r, "wood/support/" + wood.getRegistryName().getPath(), new TFCBlockWoodSupport(wood), CT_WOOD));
            }

            allPlankBlocks = blockPlanks.build();
            allWorkbenchBlocks = blockWorkbenches.build();
            allBookshelfBlocks = blockBookshelfs.build();
            allLoomBlocks = blockLooms.build();
            allFenceGateBlocks = blockFenceGates.build();
            allFenceBlocks = blockFence.build();
            allWoodButtonBlocks = blockWoodButton.build();
            allWoodPressurePlateBlocks = blockWoodPressurePlate.build();
            allBarrelBlocksBlocks = blockBarrel.build();

            allLogBlocks = blockLogs.build();
            allLeafBlocks = blockLeaves.build();
            allSaplingBlocks = blockSaplings.build();
            allDoorBlocks = blockDoors.build();
            allTrapDoorWoodBlocks = blockTrapDoors.build();
            allChestBlocks = blockChests.build();
            allToolRackBlocks = blockToolRacks.build();
            allSupportBlocks = blockSupports.build();

            // logs are special
            allPlankBlocks.forEach(x -> colorizedItemBlocks.add(new TFCItemBlock(x)));
            allWorkbenchBlocks.forEach(x -> colorizedItemBlocks.add(new TFCItemBlock(x)));
            allBookshelfBlocks.forEach(x -> colorizedItemBlocks.add(new TFCItemBlock(x)));
            allLoomBlocks.forEach(x -> colorizedItemBlocks.add(new TFCItemBlock(x)));
            allFenceBlocks.forEach(x -> colorizedItemBlocks.add(new TFCItemBlock(x)));
            allFenceGateBlocks.forEach(x -> colorizedItemBlocks.add(new TFCItemBlock(x)));
            allWoodButtonBlocks.forEach(x -> colorizedItemBlocks.add(new TFCItemBlock(x)));
            allWoodPressurePlateBlocks.forEach(x -> colorizedItemBlocks.add(new TFCItemBlock(x)));
            allBarrelBlocksBlocks.forEach(x -> colorizedItemBlocks.add(new ItemBlockBarrel(x)));
            allToolRackBlocks.forEach(x -> colorizedItemBlocks.add(new TFCItemBlock(x)));

            allLeafBlocks.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));
            allSaplingBlocks.forEach(x -> inventoryItemBlocks.add(new ItemBlockSaplingTFC(x)));
            allTrapDoorWoodBlocks.forEach(x -> inventoryItemBlocks.add(new TFCItemBlock(x)));
            allChestBlocks.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));
            allSupportBlocks.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));
        }

        {
            Builder<TFCBlockRockWall> blockWallTFC = ImmutableList.builder();
            Builder<TFCBlockRockStairs> blockRockStairsTFC = new Builder<>();
            Builder<TFCBlockWoodStairs> blockWoodStairsTFC = new Builder<>();
            Builder<TFCBlockRockSlab.Half> blockRockSlabTFC = new Builder<>();
            Builder<TFCBlockWoodSlab.Half> blockWoodSlabTFC = new Builder<>();

            // Walls
            for (Type type : new Type[] {SMOOTH, COBBLE, BRICKS, MUD_BRICKS})
                for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
                    blockWallTFC.add(register(r, ("wall/" + type.name().toLowerCase() + "/" + rock.getRegistryName().getPath()).toLowerCase(), new TFCBlockRockWall(BlockRockVariant.get(rock, type)), CT_DECORATIONS));

            // Stairs
            for (Type type : new Type[] {SMOOTH, COBBLE, BRICKS, MUD_BRICKS, RAW})
                for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
                    blockRockStairsTFC.add(register(r, "stairs/" + (type.name().toLowerCase() + "/" + rock.getRegistryName().getPath()).toLowerCase(), new TFCBlockRockStairs(rock, type), CT_DECORATIONS));
            for (Tree wood : TFCRegistries.TREES.getValuesCollection())
                blockWoodStairsTFC.add(register(r, "stairs/wood/" + wood.getRegistryName().getPath(), new TFCBlockWoodStairs(wood), CT_DECORATIONS));

            // Double Slabs
            // Full slabs are the same as full blocks, they are not saved to a list, they are kept track of by the halfslab version.
            for (Type type : new Type[] {SMOOTH, COBBLE, BRICKS, MUD_BRICKS, RAW})
                for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
                    register(r, "double_slab/" + (type.name().toLowerCase() + "/" + rock.getRegistryName().getPath()).toLowerCase(), new TFCBlockRockSlab.Double(rock, type));
            for (Tree wood : TFCRegistries.TREES.getValuesCollection())
                register(r, "double_slab/wood/" + wood.getRegistryName().getPath(), new TFCBlockWoodSlab.Double(wood));

            // Slabs
            for (Type type : new Type[] {SMOOTH, COBBLE, BRICKS, MUD_BRICKS, RAW})
                for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
                    blockRockSlabTFC.add(register(r, "slab/" + (type.name().toLowerCase() + "/" + rock.getRegistryName().getPath()).toLowerCase(), new TFCBlockRockSlab.Half(rock, type), CT_DECORATIONS));
            for (Tree wood : TFCRegistries.TREES.getValuesCollection())
                blockWoodSlabTFC.add(register(r, "slab/wood/" + wood.getRegistryName().getPath(), new TFCBlockWoodSlab.Half(wood), CT_DECORATIONS));

            for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
            {
                // Redstone things
                inventoryItemBlocks.add(new TFCItemBlock(register(r, "stone/button/" + rock.getRegistryName().getPath().toLowerCase(), new TFCBlockRockButton(rock), CT_DECORATIONS)));
                inventoryItemBlocks.add(new TFCItemBlock(register(r, "stone/pressure_plate/" + rock.getRegistryName().getPath().toLowerCase(), new TFCBlockRockPressurePlate(rock), CT_DECORATIONS)));
            }

            allWallBlocks = blockWallTFC.build();
            allRockStairsBlocks = blockRockStairsTFC.build();
            allWoodStairsBlocks = blockWoodStairsTFC.build();
            allRockSlabBlocks = blockRockSlabTFC.build();
            allWoodSlabBlocks = blockWoodSlabTFC.build();

            allWallBlocks.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));
            allRockStairsBlocks.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));
            allWoodStairsBlocks.forEach(x -> colorizedItemBlocks.add(new TFCItemBlock(x)));
        }

        {
            Builder<TFCBlockMetalAnvil> blockAnvils = ImmutableList.builder();
            Builder<TFCBlockCladding> blockCladding = ImmutableList.builder();
            Builder<TFCBlockLamp> blockLamps = ImmutableList.builder();
            Builder<TFCBlockMetalTrapDoor> blockMetalTrapdoors = ImmutableList.builder();

            for (gregtech.api.unification.material.Material material : GregTechAPI.MATERIAL_REGISTRY)
            {
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

            allBlcokAnvils = blockAnvils.build();
            allBlockCladding = blockCladding.build();
            allBlockLamps = blockLamps.build();
            allBlockMetalTrapDoor = blockMetalTrapdoors.build();
        }

        //Builder<TFCBlockCrop> cropBlocks = ImmutableList.builder();
        {

            for (Crop crop : Crop.values())
            {
                cropBlocks.add(register(r, "crop/" + crop.name().toLowerCase(), crop.createGrowingBlock()));
            }

            allCropBlocks = cropBlocks.build();
            /*for (BlockCropTFC blockCropWater : allCropBlocks)
            {
                normalItemBlocks.add(new ItemBlockCropWaterTFC((BlockCropTFC) blockCropWater));
            }*/
        }

        //Builder<BlockCropDead> deadCrops = ImmutableList.builder();
        {

            for (Crop crop : Crop.values())
            {
                deadCrops.add(register(r, "dead_crop/" + crop.name().toLowerCase(), crop.createDeadBlock()));
            }

            allDeadCropBlocks = deadCrops.build();
            /*for (BlockCropDead blockCropWaterDead : allDeadCropBlocks)
            {
                normalItemBlocks.add(new ItemBlockCropDeadWaterTFC((BlockCropDead) blockCropWaterDead));
            }*/
        }

        {
            Builder<BlockFruitTreeSapling> fSaplings = ImmutableList.builder();
            Builder<BlockFruitTreeTrunk> fTrunks = ImmutableList.builder();
            Builder<BlockFruitTreeBranch> fBranches = ImmutableList.builder();
            Builder<BlockFruitTreeLeaves> fLeaves = ImmutableList.builder();

            for (FruitTree tree : FruitTree.values())
            {
                fSaplings.add(register(r, "fruit_trees/sapling/" + tree.name().toLowerCase(), new BlockFruitTreeSapling(tree), CT_WOOD));
                fTrunks.add(register(r, "fruit_trees/trunk/" + tree.name().toLowerCase(), new BlockFruitTreeTrunk(tree)));
                fBranches.add(register(r, "fruit_trees/branch/" + tree.name().toLowerCase(), new BlockFruitTreeBranch(tree)));
                fLeaves.add(register(r, "fruit_trees/leaves/" + tree.name().toLowerCase(), new BlockFruitTreeLeaves(tree), CT_WOOD));
            }

            allFruitTreeSaplingBlocks = fSaplings.build();
            allFruitTreeTrunkBlocks = fTrunks.build();
            allFruitTreeBranchBlocks = fBranches.build();
            allFruitTreeLeavesBlocks = fLeaves.build();

            Builder<BlockBerryBush> fBerry = ImmutableList.builder();
            {
                for (BerryBush bush : BerryBush.values()) {
                    fBerry.add(register(r, "berry_bush/" + bush.name().toLowerCase(), new BlockBerryBush(bush), CT_FOOD));
                }

                allBerryBushBlocks = fBerry.build();
            }

            //Add ItemBlocks
            allFruitTreeSaplingBlocks.forEach(x -> inventoryItemBlocks.add(new TFCItemBlock(x)));
            allFruitTreeLeavesBlocks.forEach(x -> inventoryItemBlocks.add(new TFCItemBlock(x)));
            allBerryBushBlocks.forEach(x -> inventoryItemBlocks.add(new TFCItemBlock(x)));
        }

        {
            Builder<TFCBlockPlant> plantBlocks = ImmutableList.builder();
            Builder<BlockFlowerPotTFC> plantInPotBlock = ImmutableList.builder();

            Builder<TFCBlockHangingPlant> plantHangingBlock = ImmutableList.builder();
            Builder<TFCBlockHangingGlowingPlant> plantHangingGlowingBlock = ImmutableList.builder();
            Builder<TFCBlockHangingCreepingPlant> plantHangingCreepingBlock = ImmutableList.builder();
            Builder<TFCBlockHangingGlowingCreepingPlant> plantHangingGlowingCreepingBlock = ImmutableList.builder();

            for (Plant plant : TFCRegistries.PLANTS.getValuesCollection())
            {
                if (plant.getPlantType() == Plant.PlantType.HANGING && (
                        plant == TFCRegistries.PLANTS.getValue(DefaultPlants.BEARDED_MOSS) ||
                        plant == TFCRegistries.PLANTS.getValue(DefaultPlants.GLOW_VINE) ||
                        plant == TFCRegistries.PLANTS.getValue(DefaultPlants.LIANA) ||
                        plant == TFCRegistries.PLANTS.getValue(DefaultPlants.HANGING_VINE) ||
                        plant == TFCRegistries.PLANTS.getValue(DefaultPlants.JUNGLE_VINE)))
                {
                    if (plant == TFCRegistries.PLANTS.getValue(DefaultPlants.GLOW_VINE))
                    {
                        plantHangingGlowingBlock.add(register(r, "plants/" + plant.getRegistryName().getPath(), new TFCBlockHangingGlowingPlant(plant), CT_FLORA));
                        //plantHangingGlowingCreepingBlock.add(register(r, "plants/" + plant.getRegistryName().getPath() + "_creeping", new BlockHangingGlowingCreepingPlantTFC(plant), CT_FLORA));
                    }
                    else
                    {
                        plantHangingBlock.add(register(r, "plants/" + plant.getRegistryName().getPath(), new TFCBlockHangingPlant(plant), CT_FLORA));
                        //plantHangingCreepingBlock.add(register(r, "plants/" + plant.getRegistryName().getPath() + "_creeping", new BlockHangingCreepingPlantTFC(plant), CT_FLORA));
                    }
                }
                else
                {
                    plantBlocks.add(register(r, "plants/" + plant.getRegistryName().getPath(), plant.getPlantType().create(plant), CT_FLORA));
                }
            }

            allPlantBlocks = plantBlocks.build();
            allPlantBlocks.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));

            allHangingPlantBlocks = plantHangingBlock.build();
            allHangingPlantBlocks.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));

            allHangingGlowingPlantBlocks = plantHangingGlowingBlock.build();
            allHangingGlowingPlantBlocks.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));

            allHangingCreepingPlantBlocks = plantHangingCreepingBlock.build();
            allHangingCreepingPlantBlocks.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));

            allHangingGlowingCreepingPlantBlocks = plantHangingGlowingCreepingBlock.build();
            allHangingGlowingCreepingPlantBlocks.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));


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
            allGrassBlocks = b.build();
            for (TFCBlockPlant blockPlant : allGrassBlocks)
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
        plantCoral.add(register(r, "coral/tube/dead", new BlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.SNOW), CT_FLORA));
        plantCoral.add(register(r, "coral/brain/dead", new BlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.SNOW), CT_FLORA));
        plantCoral.add(register(r, "coral/bubble/dead", new BlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.SNOW), CT_FLORA));
        plantCoral.add(register(r, "coral/fire/dead", new BlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.SNOW), CT_FLORA));
        plantCoral.add(register(r, "coral/horn/dead", new BlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.SNOW), CT_FLORA));

        for (EnumDyeColor dyeColor : EnumDyeColor.values())
        {

            BlockCoral brainNormal = new BlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.getBlockColor(dyeColor));
            BlockCoral bubbleNormal = new BlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.getBlockColor(dyeColor));
            BlockCoral fireNormal = new BlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.getBlockColor(dyeColor));
            BlockCoral hornNormal = new BlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.getBlockColor(dyeColor));
            BlockCoral tubeNormal = new BlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.getBlockColor(dyeColor));

            plantCoral.add(register(r, "coral/brain/" + dyeColor.getName(), brainNormal, CT_FLORA));
            plantCoral.add(register(r, "coral/bubble/" + dyeColor.getName(), bubbleNormal, CT_FLORA));
            plantCoral.add(register(r, "coral/fire/" + dyeColor.getName(), fireNormal, CT_FLORA));
            plantCoral.add(register(r, "coral/horn/" + dyeColor.getName(), hornNormal, CT_FLORA));
            plantCoral.add(register(r, "coral/tube/" + dyeColor.getName(), tubeNormal, CT_FLORA));

            BlockCoral.BRAIN_CORAL.put(dyeColor, brainNormal);
            BlockCoral.BUBBLE_CORAL.put(dyeColor, bubbleNormal);
            BlockCoral.FIRE_CORAL.put(dyeColor, fireNormal);
            BlockCoral.HORN_CORAL.put(dyeColor, hornNormal);
            BlockCoral.TUBE_CORAL.put(dyeColor, tubeNormal);
        }

        // Fan Corals
        plantCoral.add(register(r, "coral/fan/tube/dead", new BlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.SNOW), CT_FLORA));
        plantCoral.add(register(r, "coral/fan/brain/dead", new BlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.SNOW), CT_FLORA));
        plantCoral.add(register(r, "coral/fan/bubble/dead", new BlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.SNOW), CT_FLORA));
        plantCoral.add(register(r, "coral/fan/fire/dead", new BlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.SNOW), CT_FLORA));
        plantCoral.add(register(r, "coral/fan/horn/dead", new BlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.SNOW), CT_FLORA));

        for (EnumDyeColor dyeColor : EnumDyeColor.values())
        {
            BlockCoral brainFan = new BlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.getBlockColor(dyeColor));
            BlockCoral bubbleFan = new BlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.getBlockColor(dyeColor));
            BlockCoral fireFan = new BlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.getBlockColor(dyeColor));
            BlockCoral hornFan = new BlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.getBlockColor(dyeColor));
            BlockCoral tubeFan = new BlockCoral(FluidsTFC.SEA_WATER.get(), MapColor.getBlockColor(dyeColor));

            plantCoral.add(register(r, "coral/fan/brain/" + dyeColor.getName(), brainFan, CT_FLORA));
            plantCoral.add(register(r, "coral/fan/bubble/" + dyeColor.getName(), bubbleFan, CT_FLORA));
            plantCoral.add(register(r, "coral/fan/fire/" + dyeColor.getName(), fireFan, CT_FLORA));
            plantCoral.add(register(r, "coral/fan/horn/" + dyeColor.getName(), hornFan, CT_FLORA));
            plantCoral.add(register(r, "coral/fan/tube/" + dyeColor.getName(), tubeFan, CT_FLORA));

            BlockCoral.BRAIN_CORAL_FAN.put(dyeColor, brainFan);
            BlockCoral.BUBBLE_CORAL_FAN.put(dyeColor, bubbleFan);
            BlockCoral.FIRE_CORAL_FAN.put(dyeColor, fireFan);
            BlockCoral.HORN_CORAL_FAN.put(dyeColor, hornFan);
            BlockCoral.TUBE_CORAL_FAN.put(dyeColor, tubeFan);
        }
        allCoralPlants = plantCoral.build();
        for (BlockCoral plantCoralBlock : allCoralPlants)
        {
            normalItemBlocks.add(new TFCItemBlock(plantCoralBlock));
        }

        normalItemBlocks.add(new TFCItemBlock(register(r, "coral/block/brain/dead", new BlockCoralBlock(MapColor.SNOW), CT_FLORA)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "coral/block/bubble/dead", new BlockCoralBlock(MapColor.SNOW), CT_FLORA)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "coral/block/fire/dead", new BlockCoralBlock(MapColor.SNOW), CT_FLORA)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "coral/block/horn/dead", new BlockCoralBlock(MapColor.SNOW), CT_FLORA)));
        normalItemBlocks.add(new TFCItemBlock(register(r, "coral/block/tube/dead", new BlockCoralBlock(MapColor.SNOW), CT_FLORA)));

        for (EnumDyeColor dyeColor : EnumDyeColor.values())
        {
            BlockCoralBlock brain = new BlockCoralBlock(MapColor.getBlockColor(dyeColor));
            BlockCoralBlock bubble = new BlockCoralBlock(MapColor.getBlockColor(dyeColor));
            BlockCoralBlock fire = new BlockCoralBlock(MapColor.getBlockColor(dyeColor));
            BlockCoralBlock horn = new BlockCoralBlock(MapColor.getBlockColor(dyeColor));
            BlockCoralBlock tube = new BlockCoralBlock(MapColor.getBlockColor(dyeColor));

            normalItemBlocks.add(new TFCItemBlock(register(r, "coral/block/brain/" + dyeColor.getName(), brain, CT_FLORA)));
            normalItemBlocks.add(new TFCItemBlock(register(r, "coral/block/bubble/" + dyeColor.getName(), bubble, CT_FLORA)));
            normalItemBlocks.add(new TFCItemBlock(register(r, "coral/block/fire/" + dyeColor.getName(), fire, CT_FLORA)));
            normalItemBlocks.add(new TFCItemBlock(register(r, "coral/block/horn/" + dyeColor.getName(), horn, CT_FLORA)));
            normalItemBlocks.add(new TFCItemBlock(register(r, "coral/block/tube/" + dyeColor.getName(), tube, CT_FLORA)));

            BlockCoralBlock.BRAIN_CORAL_BLOCK.put(dyeColor, brain);
            BlockCoralBlock.BUBBLE_CORAL_BLOCK.put(dyeColor, bubble);
            BlockCoralBlock.FIRE_CORAL_BLOCK.put(dyeColor, fire);
            BlockCoralBlock.HORN_CORAL_BLOCK.put(dyeColor, horn);
            BlockCoralBlock.TUBE_CORAL_BLOCK.put(dyeColor, tube);
        }

        {
            plantGlowWater.add(register(r, "plants/glowing_sea_banana", new TFCBlockWaterGlowPlant(FluidsTFC.SEA_WATER.get()), CT_FLORA));
        }
        allGlowWaterPlants = plantGlowWater.build();
        for (TFCBlockWaterGlowPlant plantGlowWaterBlock : allGlowWaterPlants)
        {
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
        allLightstoneBlocks = blockLightstone.build();
        for (BlockLightstone lightstone : allLightstoneBlocks)
        {
            normalItemBlocks.add(new TFCItemBlock(lightstone));
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverRock)
        {
            for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
            {
                surfaceRock.add(register(r, "groundcover/rock/" + rock.getRegistryName().getPath().toLowerCase(), new BlockSurfaceRock(rock), CT_ROCK_BLOCKS));
            }
            allSurfaceRocks = surfaceRock.build();
            allSurfaceRocks.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverBones)
        {
            surfaceBone.add(register(r, "groundcover/bone", new BlockSurfaceBones(), CT_FLORA));
            allSurfaceBones = surfaceBone.build();
            allSurfaceBones.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverDriftwood)
        {
            surfaceDriftwood.add(register(r, "groundcover/driftwood", new BlockDriftwood(), CT_FLORA));
            allSurfaceDriftwood = surfaceDriftwood.build();
            allSurfaceDriftwood.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverFlint)
        {
            surfaceFlint.add(register(r, "groundcover/flint", new BlockSurfaceFlint(), CT_FLORA));
            allSurfaceFlint = surfaceFlint.build();
            allSurfaceFlint.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverPinecone)
        {
            surfacePinecone.add(register(r, "groundcover/pinecone", new BlockPinecone(), CT_FLORA));
            allSurfacePinecone = surfacePinecone.build();
            allSurfacePinecone.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverSeashell)
        {
            surfaceSeashell.add(register(r, "groundcover/seashell", new BlockSurfaceSeashells(), CT_FLORA));
            allSurfaceSeashells = surfaceSeashell.build();
            allSurfaceSeashells.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverTwig)
        {
            surfaceTwig.add(register(r, "groundcover/twig", new BlockTwig(), CT_FLORA));
            allSurfaceTwig = surfaceTwig.build();
            allSurfaceTwig.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));
        }


        for (SeasonalTrees fruitTree : SeasonalTrees.values())
        {
            /*
            if (fruitTree.isNormalTree)
            {
                String name = fruitTree.getName().toLowerCase();
                if (!fruitTree.isSpecialBlock)
                {
                    itemNormalTreeLeaves.add(register(r, "wood/leaves/" + name, new BlockFruitLeaves(fruitTree.normalTree, fruitTree), CT_WOOD));
                }
                if (fruitTree.isCustomLog)
                {
                    normalTreeLog.add(register(r, "wood/log/" + name, new BlockFruitLog(fruitTree.normalTree, fruitTree), CT_WOOD));
                }
                if (fruitTree.normalTree == DefaultTrees.JOSHUA_TREE_TREE)
                {
                    Builder<BlockJoshuaTreeFlower> flowerJoshuaTree = ImmutableList.builder();
                    String name1 = fruitTree.getName().toLowerCase();

                    flowerJoshuaTree.add(register(r, "wood/leaves/" + name1, new BlockJoshuaTreeFlower(fruitTree.normalTree, fruitTree), CT_WOOD));

                    allJoshuaTreeFlowerBlocks = flowerJoshuaTree.build();
                    for (BlockJoshuaTreeFlower blockJoshuaTreeFlower : allJoshuaTreeFlowerBlocks)
                    {
                        normalItemBlocks.add(new ItemBlockTFC(blockJoshuaTreeFlower));
                    }
                }
            }*/
            /*else
            {
                String name = fruitTree.getName().toLowerCase();
                register(r, "wood/fruit_tree/branch/" + name, new BlockFruitTreeBranch(fruitTree));
                fruitLeaves.add(register(r, "wood/fruit_tree/leaves/" + name, new BlockFruitTreeLeaves(fruitTree), CT_WOOD));
                fruitSapling.add(register(r, "wood/fruit_tree/sapling/" + name, new BlockFruitTreeSapling(fruitTree), CT_WOOD));
                register(r, "wood/fruit_tree/trunk/" + name, new BlockFruitTreeTrunk(fruitTree));
                //fruitBarrel.add(register(r, "wood/fruit_tree/barrel/" + name, new BlockFruitBarrelTest(), CT_DECORATIONS));
                fruitBookshelves.add(register(r, "wood/fruit_tree/bookshelf/" + name, new BlockFruitBookshelves(), CT_DECORATIONS));
                fruitButton.add(register(r, "wood/fruit_tree/button/" + name, new BlockFruitButton(), CT_DECORATIONS));
                fruitDoors.add(register(r, "wood/fruit_tree/door/" + name, new BlockFruitDoor(name), CT_DECORATIONS));
                fruitPlanks.add(register(r, "wood/fruit_tree/planks/" + name, new BlockFruitPlanks(fruitTree), CT_WOOD));
                register(r, "wood/fruit_tree/double_slab/" + name, new BlockFruitSlab.Double(fruitTree));
                fruitSlab.add(register(r, "wood/fruit_tree/slab/" + name, new BlockFruitSlab.Half(fruitTree), CT_DECORATIONS));
                fruitStairs.add(register(r, "wood/fruit_tree/stairs/" + name, new BlockFruitStairs(fruitTree), CT_DECORATIONS));
                fruitPressurePlate.add(register(r, "wood/fruit_tree/pressure_plate/" + name, new BlockFruitPressurePlate(), CT_DECORATIONS));
                fruitFences.add(register(r, "wood/fruit_tree/fence/" +  name, new BlockFruitFence(), CT_DECORATIONS));
                fruitFenceGates.add(register(r, "wood/fruit_tree/fence_gate/" + name, new BlockFruitFenceGate(), CT_DECORATIONS));
                fruitLogFences.add(register(r, "wood/fruit_tree/fence_log/" + name, new BlockFruitLogFence(), CT_DECORATIONS));
                fruitLogFenceGates.add(register(r, "wood/fruit_tree/fence_gate_log/" + name, new BlockFruitLogFenceGate(), CT_DECORATIONS));
                fruitSupport.add(register(r, "wood/fruit_tree/support/" +  name, new BlockFruitSupport(), CT_DECORATIONS));
                fruitToolRack.add(register(r, "wood/fruit_tree/tool_rack/" + name, new BlockFruitToolRack(), CT_DECORATIONS));
                fruitTrapdoors.add(register(r, "wood/fruit_tree/trapdoor/" + name, new BlockFruitTrapDoor(), CT_DECORATIONS));
                fruitWorkbench.add(register(r, "wood/fruit_tree/workbench/" + name, new BlockFruitWorkbench(), CT_DECORATIONS));
                fruitChests.add(register(r, "wood/fruit_tree/chest/" + name, new BlockFruitChestTFCF(BlockFruitChestTFCF.TFCBASIC, fruitTree), CT_DECORATIONS));
                fruitChests.add(register(r, "wood/fruit_tree/chest_trap/" + name, new BlockFruitChestTFCF(BlockFruitChestTFCF.TFCTRAP, fruitTree), CT_DECORATIONS));
                fruitLoom.add(register(r, "wood/fruit_tree/loom/" + name, new BlockFruitLoom(fruitTree), CT_WOOD));
            }*/
        }

        for (IFruitTree fruitTree : FruitTree.values())
        {
            String name = fruitTree.getName().toLowerCase();
            //fruitBarrel.add(register(r, "wood/fruit_tree/barrel/" + name, new BlockFruitBarrelTest(), CT_DECORATIONS));
            fruitBookshelves.add(register(r, "wood/fruit_tree/bookshelf/" + name, new BlockFruitBookshelf(), CT_DECORATIONS));
            if (true) // if firmalife !added
            {
                fruitDoors.add(register(r, "wood/fruit_tree/door/" + name, new BlockFruitDoor(name), CT_DECORATIONS));
            }
            fruitButton.add(register(r, "wood/fruit_tree/button/" + name, new BlockFruitButton(), CT_DECORATIONS));
            fruitPlanks.add(register(r, "wood/fruit_tree/planks/" + name, new BlockFruitPlanks(fruitTree), CT_WOOD));
            register(r, "wood/fruit_tree/double_slab/" + name, new BlockFruitSlab.Double(fruitTree));
            fruitSlab.add(register(r, "wood/fruit_tree/slab/" + name, new BlockFruitSlab.Half(fruitTree), CT_DECORATIONS));
            fruitStairs.add(register(r, "wood/fruit_tree/stairs/" + name, new BlockFruitStairs(fruitTree), CT_DECORATIONS));
            fruitPressurePlate.add(register(r, "wood/fruit_tree/pressure_plate/" + name, new BlockFruitPressurePlate(), CT_DECORATIONS));
            if (true) // if firmalife !added
            {
                fruitFences.add(register(r, "wood/fruit_tree/fence/" + name, new BlockFruitFence(), CT_DECORATIONS));
                fruitFenceGates.add(register(r, "wood/fruit_tree/fence_gate/" + name, new BlockFruitFenceGate(), CT_DECORATIONS));
            }
            fruitLogFences.add(register(r, "wood/fruit_tree/fence_log/" + name, new BlockFruitLogFence(), CT_DECORATIONS));
            fruitLogFenceGates.add(register(r, "wood/fruit_tree/fence_gate_log/" + name, new BlockFruitLogFenceGate(), CT_DECORATIONS));
            fruitSupport.add(register(r, "wood/fruit_tree/support/" + name, new BlockFruitSupport(), CT_DECORATIONS));
            fruitToolRack.add(register(r, "wood/fruit_tree/tool_rack/" + name, new BlockFruitToolRack(), CT_DECORATIONS));
            if (true) // if firmalife !added
            {
                fruitTrapdoors.add(register(r, "wood/fruit_tree/trapdoor/" + name, new BlockFruitTrapDoor(), CT_DECORATIONS));
            }
            fruitWorkbench.add(register(r, "wood/fruit_tree/workbench/" + name, new BlockFruitWorkbench(), CT_DECORATIONS));
            fruitChests.add(register(r, "wood/fruit_tree/chest/" + name, new BlockFruitChest(BlockFruitChest.TFCBASIC, fruitTree), CT_DECORATIONS));
            fruitChests.add(register(r, "wood/fruit_tree/chest_trap/" + name, new BlockFruitChest(BlockFruitChest.TFCTRAP, fruitTree), CT_DECORATIONS));
            fruitLoom.add(register(r, "wood/fruit_tree/loom/" + name, new BlockFruitLoom(fruitTree), CT_WOOD));
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

        // Bamboo
        for (int i = 0; i < bamboo.length; i++)
        {
            //fruitBarrel.add(register(r, "wood/barrel/" + bamboo[i], new BlockFruitBarrelTest(), CT_DECORATIONS));
            fruitBookshelves.add(register(r, "wood/bookshelf/" + bamboo[i], new BlockFruitBookshelf(), CT_DECORATIONS));
            fruitButton.add(register(r, "wood/button/" + bamboo[i], new BlockFruitButton(), CT_DECORATIONS));
            fruitDoors.add(register(r, "wood/door/" + bamboo[i], new BlockFruitDoor(bamboo[i]), CT_DECORATIONS));
            planksTFC.add(register(r, "wood/planks/" + bamboo[i], new TFCBlockPlanks(bambooTrees[i]), CT_WOOD));
            fruitPressurePlate.add(register(r, "wood/pressure_plate/" + bamboo[i], new BlockFruitPressurePlate(), CT_DECORATIONS));
            fruitFences.add(register(r, "wood/fence/" + bamboo[i], new BlockFruitFence(), CT_DECORATIONS));
            fruitFenceGates.add(register(r, "wood/fence_gate/" + bamboo[i], new BlockFruitFenceGate(), CT_DECORATIONS));
            fruitLogFences.add(register(r, "wood/fence_log/" + bamboo[i], new BlockFruitLogFence(), CT_DECORATIONS));
            fruitLogFenceGates.add(register(r, "wood/fence_gate_log/" + bamboo[i], new BlockFruitLogFenceGate(), CT_DECORATIONS));
            fruitSupport.add(register(r, "wood/support/" + bamboo[i], new BlockFruitSupport(), CT_DECORATIONS));
            fruitToolRack.add(register(r, "wood/tool_rack/" + bamboo[i], new BlockFruitToolRack(), CT_DECORATIONS));
            fruitTrapdoors.add(register(r, "wood/trapdoor/" + bamboo[i], new BlockFruitTrapDoor(), CT_DECORATIONS));
            fruitWorkbench.add(register(r, "wood/workbench/" + bamboo[i], new BlockFruitWorkbench(), CT_DECORATIONS));
//            register(r, "wood/double_slab/" + bamboo[i], new BlockRockSlab.Double(bambooTrees[i]));
//            blockSlabTFC.add(register(r, "wood/slab/" + bamboo[i], new BlockRockSlab.Half(bambooTrees[i]), CT_DECORATIONS));
//            blockStairTFC.add(register(r, "wood/stairs/" + bamboo[i], new BlockWoodStairsTFC(bambooTrees[i]), CT_DECORATIONS));

            fruitChests.add(register(r, "wood/chest/" + bamboo[i], new BlockFruitChest(BlockFruitChest.TFCBASIC, bambooTrees[i]), CT_DECORATIONS));
            fruitChests.add(register(r, "wood/chest_trap/" + bamboo[i], new BlockFruitChest(BlockFruitChest.TFCTRAP, bambooTrees[i]), CT_DECORATIONS));
            fruitLoom.add(register(r, "wood/loom/" + bamboo[i], new BlockFruitLoom(bambooTrees[i]), CT_WOOD));

            Block bambooBlock = register(r, "wood/log/" + bamboo[i], new BlockBambooLog(), CT_WOOD);
            BlockBambooLeaves leaves = new BlockBambooLeaves(bambooTrees[i]);
            Block bambooLeaves = register(r, "wood/leaves/" + bamboo[i], leaves, CT_WOOD);

            BlockBambooSapling sapling = new BlockBambooSapling(bambooTrees[i], bambooLeaves, bambooBlock);
            Block bambooSapling = register(r, "wood/sapling/" + bamboo[i], sapling, CT_WOOD);
            leaves.setBambooSapling(sapling);

            itemBambooLog.add(bambooBlock);
            itemBambooLeaves.add(bambooLeaves);
            itemBambooSapling.add(bambooSapling);
        }

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

        allBambooLog = itemBambooLog.build();
        allBambooLog.forEach((x) -> {
            normalItemBlocks.add(new TFCItemBlock(x));
        });

        allBambooLeaves = itemBambooLeaves.build();
        allBambooLeaves.forEach((x) -> {
            normalItemBlocks.add(new TFCItemBlock(x));
        });

        allNormalTreeLeaves = itemNormalTreeLeaves.build();
        allNormalTreeLeaves.forEach((x) -> {
            normalItemBlocks.add(new TFCItemBlock(x));
        });

        allNormalTreeLog = normalTreeLog.build();

        allBambooSapling = itemBambooSapling.build();
        allBambooSapling.forEach((x) -> {
            normalItemBlocks.add(new TFCItemBlock(x));
        });

        allFoodItemBlocks = foodItemBlocks.build();
        allFoodItemBlocks.forEach((x) -> {
            normalItemBlocks.add(new ItemBlockRot(x));
        });

        allFenceGateLogBlocks = fenceGatesLog.build();
        allFenceGateLogBlocks.forEach((x) -> {
            normalItemBlocks.add(new TFCItemBlock(x));
        });

        allFruitLeaves = fruitLeaves.build();
        allFruitLeaves.forEach((x) -> {
            normalItemBlocks.add(new TFCItemBlock(x));
        });

        allFruitSapling = fruitSapling.build();
        allFruitSapling.forEach((x) -> {
            normalItemBlocks.add(new TFCItemBlock(x));
        });

        /*allFruitBarrel = fruitBarrel.build();
        allFruitBarrel.forEach((x) -> {
            normalItemBlocks.add(new ItemBlockTFC(x));
        });*/

        allFruitBookshelves = fruitBookshelves.build();
        allFruitBookshelves.forEach((x) -> {
            normalItemBlocks.add(new TFCItemBlock(x));
        });

        allFruitButton = fruitButton.build();
        allFruitButton.forEach((x) -> {
            normalItemBlocks.add(new TFCItemBlock(x));
        });

        allFruitDoors = fruitDoors.build();

        allFruitPlanks = fruitPlanks.build();
        allFruitPlanks.forEach((x) -> {
            normalItemBlocks.add(new TFCItemBlock(x));
        });

        allPlanksTFC = planksTFC.build();
        allPlanksTFC.forEach((x) -> {
            normalItemBlocks.add(new TFCItemBlock(x));
        });

        allSlabBlocksTFC = blockSlabTFC.build();
        allStairBlocksTFC = blockStairTFC.build();
        allStairBlocksTFC.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));

        allFruitSlabBlocks = fruitSlab.build();
        allFruitStairBlocks = fruitStairs.build();
        allFruitStairBlocks.forEach(x -> normalItemBlocks.add(new TFCItemBlock(x)));

        allFruitPressurePlate = fruitPressurePlate.build();
        allFruitPressurePlate.forEach((x) -> {
            normalItemBlocks.add(new TFCItemBlock(x));
        });

        allFruitFences = fruitFences.build();
        allFruitFences.forEach((x) -> {
            normalItemBlocks.add(new TFCItemBlock(x));
        });

        allFruitFenceGates = fruitFenceGates.build();
        allFruitFenceGates.forEach((x) -> {
            normalItemBlocks.add(new TFCItemBlock(x));
        });

        allFruitLogFences = fruitLogFences.build();
        allFruitLogFences.forEach((x) -> {
            normalItemBlocks.add(new TFCItemBlock(x));
        });

        allFruitLogFenceGates = fruitLogFenceGates.build();
        allFruitLogFenceGates.forEach((x) -> {
            normalItemBlocks.add(new TFCItemBlock(x));
        });

        allFruitSupport = fruitSupport.build();
        allFruitSupport.forEach((x) -> {
            normalItemBlocks.add(new TFCItemBlock(x));
        });

        allFruitToolRack = fruitToolRack.build();
        allFruitToolRack.forEach((x) -> {
            normalItemBlocks.add(new TFCItemBlock(x));
        });

        allFruitTrapDoors = fruitTrapdoors.build();
        allFruitTrapDoors.forEach((x) -> {
            normalItemBlocks.add(new TFCItemBlock(x));
        });

        allFruitWorkbench = fruitWorkbench.build();
        allFruitWorkbench.forEach((x) -> {
            normalItemBlocks.add(new TFCItemBlock(x));
        });

        allFruitChestBlocks = fruitChests.build();
        allFruitChestBlocks.forEach((x) -> {
            normalItemBlocks.add(new TFCItemBlock(x));
        });

        allFruitLoomBlocks = fruitLoom.build();
        allFruitLoomBlocks.forEach((x) -> {
            normalItemBlocks.add(new TFCItemBlock(x));
        });
        // Florae End

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
        register(TEFruitChest.class, "fruit_chest");
        register(TEFruitLoom.class, "fruit_loom");
        register(TEUrn.class, "urn");
        register(TECrate.class, "crate");
        register(TEDryer.class, "dryer");
        register(TEStickBundle.class, "stick_bundle");
        register(TECondenser.class, "condenser");
        register(TEAlembic.class, "alembic");
        register(TESaguaroCactus.class, "saguaro_cactus");
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerVanillaOverrides(RegistryEvent.Register<Block> event)
    {
        // Vanilla Overrides. Used for small tweaks on vanilla items, rather than replacing them outright
        if (ConfigTFC.General.OVERRIDES.enableFrozenOverrides)
        {
            TerraFirmaCraft.getLog().info("The below warnings about unintended overrides are normal. The override is intended. ;)");
            event.getRegistry().registerAll(
                    new BlockIceTFC(FluidRegistry.WATER).setRegistryName("minecraft", "ice").setTranslationKey("ice"),
                    new BlockSnowTFC().setRegistryName("minecraft", "snow_layer").setTranslationKey("snow"),
                    new BlockTorchTFC().setRegistryName("minecraft", "torch").setTranslationKey("torch")
            );

        }
    }

    public static boolean isWater(IBlockState current)
    {
        return current.getMaterial() == Material.WATER;
    }

    public static boolean isVanillaWater(IBlockState current)
    {
        return current == FluidRegistry.WATER.getBlock().getDefaultState();
    }

    public static boolean isFreshWater(IBlockState current)
    {
        return current == FluidsTFC.FRESH_WATER.get().getBlock().getDefaultState();
    }

    public static boolean isSeaWater(IBlockState current)
    {
        return current == FluidsTFC.SEA_WATER.get().getBlock().getDefaultState();
    }

    public static boolean isFreshWaterOrIce(IBlockState current)
    {
        return current.getBlock() == Blocks.ICE || isVanillaWater(current);
    }

    public static boolean isRawStone(IBlockState current)
    {
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;

        Rock.Type type = ((BlockRockVariant) current.getBlock()).getType();
        return type == RAW ||
                type == COBBLE ||
                type == SMOOTH ||
                type == MOSSY_RAW;
    }

    public static boolean isClay(IBlockState current)
    {
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;

        Type type = ((BlockRockVariant) current.getBlock()).getType();
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

    public static boolean isDirt(IBlockState current)
    {
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;

        Type type = ((BlockRockVariant) current.getBlock()).getType();
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

    public static boolean isSand(IBlockState current)
    {
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;
        Rock.Type type = ((BlockRockVariant) current.getBlock()).getType();
        return type == SAND;
    }

    // todo: change to property of type? (soil & stone maybe?)
    public static boolean isSoil(IBlockState current)
    {
        if (current.getBlock() instanceof BlockPeat) return true;
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;

        Type type = ((BlockRockVariant) current.getBlock()).getType();
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

    public static boolean isGrowableSoil(IBlockState current)
    {
        if (current.getBlock() instanceof BlockPeat) return false;
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;

        Type type = ((BlockRockVariant) current.getBlock()).getType();
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

    public static boolean isSoilOrGravel(IBlockState current)
    {
        if (current.getBlock() instanceof BlockPeat) return true;
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;

        Type type = ((BlockRockVariant) current.getBlock()).getType();
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

    public static boolean isGrass(IBlockState current)
    {
        if (current.getBlock() instanceof BlockPeatGrass) return true;
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;

        Rock.Type type = ((BlockRockVariant) current.getBlock()).getType();
        return type.isGrass;
    }

    public static boolean isDryGrass(IBlockState current)
    {
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;

        Type type = ((BlockRockVariant) current.getBlock()).getType();
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

    public static boolean isGround(IBlockState current)
    {
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;

        Type type = ((BlockRockVariant) current.getBlock()).getType();
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

    public static boolean isClayGrass(IBlockState current)
    {
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;
        Type type = ((BlockRockVariant) current.getBlock()).getType();

        return
                // Type.CLAY_GRASS ?
                type == SANDY_CLAY_LOAM_GRASS ||
                type == SANDY_CLAY_GRASS ||
                type == CLAY_LOAM_GRASS ||
                type == SILTY_CLAY_GRASS ||
                type == SILTY_CLAY_LOAM_GRASS ||
                type == CLAY_HUMUS_GRASS;
    }

    public static boolean isClayDryGrass(IBlockState current)
    {
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;
        Type type = ((BlockRockVariant) current.getBlock()).getType();

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

    public static boolean isKaoliniteClayGrass(IBlockState current)
    {
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;
        Type type = ((BlockRockVariant) current.getBlock()).getType();

        return type == KAOLINITE_CLAY_GRASS ||
                type == SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == SANDY_KAOLINITE_CLAY_GRASS ||
                type == KAOLINITE_CLAY_LOAM_GRASS ||
                type == SILTY_KAOLINITE_CLAY_GRASS ||
                type == SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                type == KAOLINITE_CLAY_HUMUS_GRASS;
    }

    public static boolean isKaoliniteClayDryGrass(IBlockState current)
    {
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;
        Type type = ((BlockRockVariant) current.getBlock()).getType();

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

    public static boolean isKaoliniteClayPodzol(IBlockState current)
    {
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;
        Type type = ((BlockRockVariant) current.getBlock()).getType();

        return type == KAOLINITE_CLAY_PODZOL ||
                type == SANDY_KAOLINITE_CLAY_LOAM_PODZOL ||
                type == SANDY_KAOLINITE_CLAY_PODZOL ||
                type == KAOLINITE_CLAY_LOAM_PODZOL ||
                type == SILTY_KAOLINITE_CLAY_PODZOL ||
                type == SILTY_KAOLINITE_CLAY_LOAM_PODZOL;
    }

    public static boolean isKaoliniteClayDirt(IBlockState current)
    {
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;
        Type type = ((BlockRockVariant) current.getBlock()).getType();

        return type == KAOLINITE_CLAY ||
                type == SANDY_KAOLINITE_CLAY_LOAM ||
                type == SANDY_KAOLINITE_CLAY ||
                type == KAOLINITE_CLAY_LOAM ||
                type == SILTY_KAOLINITE_CLAY ||
                type == SILTY_KAOLINITE_CLAY_LOAM ||
                type == KAOLINITE_CLAY_HUMUS;
    }

    public static boolean isStonewareClayGrass(IBlockState current)
    {
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;
        Type type = ((BlockRockVariant) current.getBlock()).getType();

        return type == STONEWARE_CLAY_GRASS ||
                type == SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                type == SANDY_STONEWARE_CLAY_GRASS ||
                type == STONEWARE_CLAY_LOAM_GRASS ||
                type == SILTY_STONEWARE_CLAY_GRASS ||
                type == SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                type == STONEWARE_CLAY_HUMUS_GRASS;
    }

    public static boolean isStonewareClayDryGrass(IBlockState current)
    {
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;
        Type type = ((BlockRockVariant) current.getBlock()).getType();

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

    public static boolean isStonewareClayPodzol(IBlockState current)
    {
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;
        Type type = ((BlockRockVariant) current.getBlock()).getType();

        return type == STONEWARE_CLAY_PODZOL ||
                type == SANDY_STONEWARE_CLAY_LOAM_PODZOL ||
                type == SANDY_STONEWARE_CLAY_PODZOL ||
                type == STONEWARE_CLAY_LOAM_PODZOL ||
                type == SILTY_STONEWARE_CLAY_PODZOL ||
                type == SILTY_STONEWARE_CLAY_LOAM_PODZOL;
    }

    public static boolean isStonewareClayDirt(IBlockState current)
    {
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;
        Type type = ((BlockRockVariant) current.getBlock()).getType();

        return type == STONEWARE_CLAY ||
                type == SANDY_STONEWARE_CLAY_LOAM ||
                type == SANDY_STONEWARE_CLAY ||
                type == STONEWARE_CLAY_LOAM ||
                type == SILTY_STONEWARE_CLAY ||
                type == SILTY_STONEWARE_CLAY_LOAM ||
                type == STONEWARE_CLAY_HUMUS;
    }

    public static boolean isEarthenwareClayGrass(IBlockState current)
    {
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;
        Type type = ((BlockRockVariant) current.getBlock()).getType();

        return type == EARTHENWARE_CLAY_GRASS ||
                type == SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SANDY_EARTHENWARE_CLAY_GRASS ||
                type == EARTHENWARE_CLAY_LOAM_GRASS ||
                type == SILTY_EARTHENWARE_CLAY_GRASS ||
                type == SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                type == EARTHENWARE_CLAY_HUMUS_GRASS;
    }

    public static boolean isEarthenwareClayDryGrass(IBlockState current)
    {
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;
        Type type = ((BlockRockVariant) current.getBlock()).getType();

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

    public static boolean isEarthenwareClayPodzol(IBlockState current)
    {
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;
        Type type = ((BlockRockVariant) current.getBlock()).getType();

        return type == EARTHENWARE_CLAY_PODZOL ||
                type == SANDY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                type == SANDY_EARTHENWARE_CLAY_PODZOL ||
                type == EARTHENWARE_CLAY_LOAM_PODZOL ||
                type == SILTY_EARTHENWARE_CLAY_PODZOL ||
                type == SILTY_EARTHENWARE_CLAY_LOAM_PODZOL;
    }

    public static boolean isEarthenwareClayDirt(IBlockState current)
    {
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;
        Type type = ((BlockRockVariant) current.getBlock()).getType();

        return type == EARTHENWARE_CLAY ||
                type == SANDY_EARTHENWARE_CLAY_LOAM ||
                type == SANDY_EARTHENWARE_CLAY ||
                type == EARTHENWARE_CLAY_LOAM ||
                type == SILTY_EARTHENWARE_CLAY ||
                type == SILTY_EARTHENWARE_CLAY_LOAM ||
                type == EARTHENWARE_CLAY_HUMUS;
    }

    public static boolean isClayPodzol(IBlockState current)
    {
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;
        Type type = ((BlockRockVariant) current.getBlock()).getType();

        return type == CLAY_PODZOL ||
                type == SANDY_CLAY_LOAM_PODZOL ||
                type == SANDY_CLAY_PODZOL ||
                type == CLAY_LOAM_PODZOL ||
                type == SILTY_CLAY_PODZOL ||
                type == SILTY_CLAY_LOAM_PODZOL;
    }

    public static boolean isClayDirt(IBlockState current)
    {
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;
        Type type = ((BlockRockVariant) current.getBlock()).getType();

        return
                // CLAY ?
                type == SANDY_CLAY_LOAM ||
                type == SANDY_CLAY ||
                type == CLAY_LOAM ||
                type == SILTY_CLAY_LOAM ||
                type == SILTY_CLAY ||
                type == CLAY_HUMUS;
    }

    public static boolean isPodzol(IBlockState current)
    {
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;
        Type type = ((BlockRockVariant) current.getBlock()).getType();

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

    public static boolean isSparseGrass(IBlockState current)
    {
        if (!(current.getBlock() instanceof BlockRockVariant)) return false;
        Type type = ((BlockRockVariant) current.getBlock()).getType();

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

    private static <T extends Block> T register(IForgeRegistry<Block> r, String name, T block, CreativeTabs ct)
    {
        block.setCreativeTab(ct);
        return register(r, name, block);
    }

    private static <T extends Block> T register(IForgeRegistry<Block> r, String name, T block)
    {
        block.setRegistryName(MOD_ID, name);
        block.setTranslationKey(MOD_ID + "." + name.replace('/', '.'));
        r.register(block);
        return block;
    }

    private static <T extends TileEntity> void register(Class<T> te, String name)
    {
        TileEntity.register(MOD_ID + ":" + name, te);
    }
}
