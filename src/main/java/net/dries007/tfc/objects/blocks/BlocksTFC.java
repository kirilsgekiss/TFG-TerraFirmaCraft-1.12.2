/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import gregtech.api.GregTechAPI;
import net.dries007.tfc.compat.gregtech.materials.TFCMaterialFlags;
import net.dries007.tfc.objects.fluids.FluidsTFC;
import net.dries007.tfc.util.Helpers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGravel;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
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
import net.dries007.tfc.types.DefaultPlants;
import net.dries007.tfc.api.types.Rock.Type;
import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.*;
import net.dries007.tfc.api.util.FallingBlockManager;
import net.dries007.tfc.objects.blocks.agriculture.*;
import net.dries007.tfc.objects.blocks.devices.*;
import net.dries007.tfc.objects.blocks.metal.*;
import net.dries007.tfc.objects.blocks.plants.BlockFloatingWaterTFC;
import net.dries007.tfc.objects.blocks.plants.BlockPlantTFC;
import net.dries007.tfc.objects.blocks.stone.*;
import net.dries007.tfc.objects.blocks.wood.*;
import net.dries007.tfc.objects.fluids.properties.FluidWrapper;
import net.dries007.tfc.objects.items.itemblock.*;
import net.dries007.tfc.objects.te.*;
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
    public static final BlockStickBundle STICK_BUNDLE = Helpers.getNull();
    @GameRegistry.ObjectHolder("devices/dryer")
    public static final BlockDryer DRYER = Helpers.getNull();

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
    public static final BlockLogPileTFC LOG_PILE = getNull();
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


    // All these are for use in model registration. Do not use for block lookups.
    // Use the static get methods in the classes instead.
    private static ImmutableList<ItemBlock> allNormalItemBlocks;
    private static ImmutableList<ItemBlock> allInventoryItemBlocks;
    private static ImmutableList<ItemBlock> allColorizedItemBlocks;
    private static ImmutableList<BlockBarrelTFC> allBarrelBlocksBlocks;

    private static ImmutableList<BlockFluidBase> allFluidBlocks;
    private static ImmutableList<BlockRockVariant> allBlockRockVariants;
    private static ImmutableList<BlockWallTFC> allWallBlocks;
    private static ImmutableList<BlockLogTFC> allLogBlocks;
    private static ImmutableList<BlockPlanksTFC> allPlankBlocks;
    private static ImmutableList<BlockWorkbenchTFC> allWorkbenchBlocks;
    private static ImmutableList<BlockBookshelfTFC> allBookshelfBlocks;
    private static ImmutableList<BlockLeavesTFC> allLeafBlocks;
    private static ImmutableList<BlockFenceGateTFC> allFenceGateBlocks;
    private static ImmutableList<BlockFenceTFC> allFenceBlocks;
    private static ImmutableList<BlockWoodPressurePlateTFC> allWoodPressurePlateBlocks;
    private static ImmutableList<BlockWoodButtonTFC> allWoodButtonBlocks;
    private static ImmutableList<BlockSaplingTFC> allSaplingBlocks;
    private static ImmutableList<BlockDoorTFC> allDoorBlocks;
    private static ImmutableList<BlockTrapDoorWoodTFC> allTrapDoorWoodBlocks;
    private static ImmutableList<BlockTrapDoorMetalTFC> allTrapDoorMetalBlocks;
    private static ImmutableList<BlockRockStairsTFC> allRockStairsBlocks;
    private static ImmutableList<BlockWoodStairsTFC> allWoodStairsBlocks;
    private static ImmutableList<BlockRockSlabTFC.Half> allRockSlabBlocks;
    private static ImmutableList<BlockWoodSlabTFC.Half> allWoodSlabBlocks;
    private static ImmutableList<BlockChestTFC> allChestBlocks;
    private static ImmutableList<BlockAnvilTFC> allAnvils;
    private static ImmutableList<BlockCladdingTFC> allSheets;
    private static ImmutableList<BlockLampTFC> allLamps;
    private static ImmutableList<BlockToolRackTFC> allToolRackBlocks;
    private static ImmutableList<BlockCropTFC> allCropBlocks;
    private static ImmutableList<BlockCropDead> allDeadCropBlocks;
    private static ImmutableList<BlockPlantTFC> allPlantBlocks;
    private static ImmutableList<BlockPlantTFC> allGrassBlocks;
    private static ImmutableList<BlockLoomTFC> allLoomBlocks;
    private static ImmutableList<BlockSupportTFC> allSupportBlocks;
    private static ImmutableList<BlockFlowerPotTFC> allFlowerPots;

    private static ImmutableList<BlockFruitTreeSapling> allFruitTreeSaplingBlocks = Helpers.getNull();
    private static ImmutableList<BlockFruitTreeTrunk> allFruitTreeTrunkBlocks = Helpers.getNull();
    private static ImmutableList<BlockFruitTreeBranch> allFruitTreeBranchBlocks = Helpers.getNull();
    private static ImmutableList<BlockFruitTreeLeaves> allFruitTreeLeavesBlocks = Helpers.getNull();

    private static ImmutableList<BlockBerryBush> allBerryBushBlocks = Helpers.getNull();

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

    public static ImmutableList<BlockBarrelTFC> getAllBarrelBlocksBlocks()
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

    public static ImmutableList<BlockPlanksTFC> getAllPlankBlocks() {
        return allPlankBlocks;
    }

    public static ImmutableList<BlockWorkbenchTFC> getAllWorkbenchBlocks() {
        return allWorkbenchBlocks;
    }

    public static ImmutableList<BlockBookshelfTFC> getAllBookshelfBlocks() {
        return allBookshelfBlocks;
    }

    public static ImmutableList<BlockLeavesTFC> getAllLeafBlocks()
    {
        return allLeafBlocks;
    }

    public static ImmutableList<BlockFenceGateTFC> getAllFenceGateBlocks()
    {
        return allFenceGateBlocks;
    }

    public static ImmutableList<BlockFenceTFC> getAllFenceBlocks()
    {
        return allFenceBlocks;
    }

    public static ImmutableList<BlockWoodPressurePlateTFC> getAllWoodPressurePlateBlocks()
    {
        return allWoodPressurePlateBlocks;
    }

    public static ImmutableList<BlockWoodButtonTFC> getAllWoodButtonBlocks()
    {
        return allWoodButtonBlocks;
    }

    public static ImmutableList<BlockWallTFC> getAllWallBlocks()
    {
        return allWallBlocks;
    }

    public static ImmutableList<BlockSaplingTFC> getAllSaplingBlocks()
    {
        return allSaplingBlocks;
    }

    public static ImmutableList<BlockDoorTFC> getAllDoorBlocks()
    {
        return allDoorBlocks;
    }

    public static ImmutableList<BlockTrapDoorWoodTFC> getAllTrapDoorWoodBlocks()
    {
        return allTrapDoorWoodBlocks;
    }

    public static ImmutableList<BlockTrapDoorMetalTFC> getAllTrapDoorMetalBlocks()
    {
        return allTrapDoorMetalBlocks;
    }

    public static ImmutableList<BlockRockStairsTFC> getAllRockStairsBlocks()
    {
        return allRockStairsBlocks;
    }

    public static ImmutableList<BlockWoodStairsTFC> getAllWoodStairsBlocks()
    {
        return allWoodStairsBlocks;
    }

    public static ImmutableList<BlockRockSlabTFC.Half> getAllRockSlabBlocks()
    {
        return allRockSlabBlocks;
    }

    public static ImmutableList<BlockWoodSlabTFC.Half> getAllWoodSlabBlocks()
    {
        return allWoodSlabBlocks;
    }

    public static ImmutableList<BlockChestTFC> getAllChestBlocks()
    {
        return allChestBlocks;
    }

    public static ImmutableList<BlockAnvilTFC> getAllAnvils()
    {
        return allAnvils;
    }

    public static ImmutableList<BlockCladdingTFC> getAllSheets()
    {
        return allSheets;
    }

    public static ImmutableList<BlockLampTFC> getAllLamps()
    {
        return allLamps;
    }

    public static ImmutableList<BlockToolRackTFC> getAllToolRackBlocks()
    {
        return allToolRackBlocks;
    }

    public static ImmutableList<BlockCropTFC> getAllCropBlocks()
    {
        return allCropBlocks;
    }

    public static ImmutableList<BlockCropDead> getAllDeadCropBlocks()
    {
        return allDeadCropBlocks;
    }

    public static ImmutableList<BlockPlantTFC> getAllPlantBlocks()
    {
        return allPlantBlocks;
    }

    public static ImmutableList<BlockPlantTFC> getAllGrassBlocks()
    {
        return allGrassBlocks;
    }

    public static ImmutableList<BlockLoomTFC> getAllLoomBlocks()
    {
        return allLoomBlocks;
    }

    public static ImmutableList<BlockSupportTFC> getAllSupportBlocks()
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

        normalItemBlocks.add(new ItemBlockTFC(register(r, "debug", new BlockDebug(), CT_MISC)));

        normalItemBlocks.add(new ItemBlockTFC(register(r, "aggregate", new BlockAggregate(), CT_ROCK_BLOCKS)));
        normalItemBlocks.add(new ItemBlockTFC(register(r, "fire_clay_block", new BlockFireClay(), CT_ROCK_BLOCKS)));

        normalItemBlocks.add(new ItemBlockTFC(register(r, "peat", new BlockPeat(Material.GROUND), CT_ROCK_BLOCKS)));
        normalItemBlocks.add(new ItemBlockTFC(register(r, "peat_grass", new BlockPeatGrass(Material.GRASS), CT_ROCK_BLOCKS)));

        normalItemBlocks.add(new ItemBlockTFC(register(r, "thatch", new BlockThatch(), CT_DECORATIONS)));
        normalItemBlocks.add(new ItemBlockTFC(register(r, "fire_bricks", new BlockFireBrick(), CT_DECORATIONS)));

        normalItemBlocks.add(new ItemBlockTFC(register(r, "quern", new BlockQuern(), CT_MISC)));
        normalItemBlocks.add(new ItemBlockCrucible(register(r, "crucible", new BlockCrucible(), CT_MISC)));
        normalItemBlocks.add(new ItemBlockTFC(register(r, "blast_furnace", new BlockBlastFurnace(), CT_MISC)));
        normalItemBlocks.add(new ItemBlockDryer(register(r, "devices/dryer", new BlockDryer(), CT_MISC)));
        normalItemBlocks.add(new ItemBlockStickBundle(register(r, "devices/stick_bundle", new BlockStickBundle(), CT_MISC)));

        inventoryItemBlocks.add(new ItemBlockTFC(register(r, "bellows", new BlockBellows(), CT_MISC)));
        inventoryItemBlocks.add(new ItemBlockTFC(register(r, "bloomery", new BlockBloomery(), CT_MISC)));
        inventoryItemBlocks.add(new ItemBlockTFC(register(r, "nest_box", new BlockNestBox(), CT_MISC)));
        inventoryItemBlocks.add(new ItemBlockSluice(register(r, "sluice", new BlockSluice(), CT_MISC)));

        normalItemBlocks.add(new ItemBlockTFC(register(r, "sea_ice", new BlockIceTFC(FluidsTFC.SEA_WATER.get()), CT_MISC)));

        normalItemBlocks.add(new ItemBlockLargeVessel(register(r, "ceramics/fired/large_vessel", new BlockLargeVessel(), CT_POTTERY)));
        normalItemBlocks.add(new ItemBlockPowderKeg(register(r, "powderkeg", new BlockPowderKeg(), CT_WOOD)));

        normalItemBlocks.add(new ItemBlockTFC(register(r, "alabaster/raw/plain", new BlockDecorativeStone(MapColor.SNOW), CT_DECORATIONS)));
        normalItemBlocks.add(new ItemBlockTFC(register(r, "alabaster/polished/plain", new BlockDecorativeStone(MapColor.SNOW), CT_DECORATIONS)));
        normalItemBlocks.add(new ItemBlockTFC(register(r, "alabaster/bricks/plain", new BlockDecorativeStone(MapColor.SNOW), CT_DECORATIONS)));

        normalItemBlocks.add(new ItemBlockTFC(register(r, "ceramics/earthenware/earthenware_clay_block", new BlockEarthenwareClay(), CT_ROCK_BLOCKS)));
        normalItemBlocks.add(new ItemBlockTFC(register(r, "ceramics/earthenware/earthenware_bricks", new BlockFireBrick(), CT_DECORATIONS)));
        normalItemBlocks.add(new ItemBlockLargeVessel(register(r, "ceramics/earthenware/fired/large_vessel", new BlockLargeVessel(), CT_POTTERY)));

        normalItemBlocks.add(new ItemBlockTFC(register(r, "ceramics/kaolinite/kaolinite_clay_block", new BlockKaoliniteClay(), CT_ROCK_BLOCKS)));
        normalItemBlocks.add(new ItemBlockTFC(register(r, "ceramics/kaolinite/kaolinite_bricks", new BlockFireBrick(), CT_DECORATIONS)));
        normalItemBlocks.add(new ItemBlockLargeVessel(register(r, "ceramics/kaolinite/fired/large_vessel", new BlockLargeVessel(), CT_POTTERY)));

        normalItemBlocks.add(new ItemBlockTFC(register(r, "ceramics/stoneware/stoneware_clay_block", new BlockStonewareClay(), CT_ROCK_BLOCKS)));
        normalItemBlocks.add(new ItemBlockTFC(register(r, "ceramics/stoneware/stoneware_bricks", new BlockFireBrick(), CT_DECORATIONS)));
        normalItemBlocks.add(new ItemBlockLargeVessel(register(r, "ceramics/stoneware/fired/large_vessel", new BlockLargeVessel(), CT_POTTERY)));

        normalItemBlocks.add(new ItemBlockUrn(register(r, "storage/urn", new BlockUrn(), CT_POTTERY)));
        normalItemBlocks.add(new ItemBlockUrnLoot(register(r, "storage/urn_loot", new BlockUrnLoot(), CT_POTTERY)));
        normalItemBlocks.add(new ItemBlockCrate(register(r, "storage/crate", new BlockCrate(), CT_DECORATIONS)));

        for (EnumDyeColor dyeColor : EnumDyeColor.values())
        {
            BlockDecorativeStone polished = new BlockDecorativeStone(MapColor.getBlockColor(dyeColor));
            BlockDecorativeStone bricks = new BlockDecorativeStone(MapColor.getBlockColor(dyeColor));
            BlockDecorativeStone raw = new BlockDecorativeStone(MapColor.getBlockColor(dyeColor));

            normalItemBlocks.add(new ItemBlockTFC(register(r, "alabaster/polished/" + dyeColor.getName(), polished, CT_DECORATIONS)));
            normalItemBlocks.add(new ItemBlockTFC(register(r, "alabaster/bricks/" + dyeColor.getName(), bricks, CT_DECORATIONS)));
            normalItemBlocks.add(new ItemBlockTFC(register(r, "alabaster/raw/" + dyeColor.getName(), raw, CT_DECORATIONS)));

            BlockDecorativeStone.ALABASTER_POLISHED.put(dyeColor, polished);
            BlockDecorativeStone.ALABASTER_BRICKS.put(dyeColor, bricks);
            BlockDecorativeStone.ALABASTER_RAW.put(dyeColor, raw);
        }

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
            for (Rock.Type type : values())
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
                    normalItemBlocks.add(new ItemBlockTFC(x));
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
                            FallingBlockManager.registerFallable(BlockRockVariant.get(rock, SMOOTH).getDefaultState().withProperty(BlockRockSmooth.CAN_FALL, true), spec);
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
            Builder<BlockLogTFC> logs = ImmutableList.builder();
            Builder<BlockPlanksTFC> planks = ImmutableList.builder();
            Builder<BlockWorkbenchTFC> workbenches = ImmutableList.builder();
            Builder<BlockBookshelfTFC> bookshelfs = ImmutableList.builder();
            Builder<BlockLeavesTFC> leaves = ImmutableList.builder();
            Builder<BlockFenceGateTFC> fenceGates = ImmutableList.builder();
            Builder<BlockFenceTFC> fence = ImmutableList.builder();
            Builder<BlockWoodPressurePlateTFC> woodPressurePlate = ImmutableList.builder();
            Builder<BlockWoodButtonTFC> woodButton = ImmutableList.builder();
            Builder<BlockSaplingTFC> saplings = ImmutableList.builder();
            Builder<BlockDoorTFC> doors = ImmutableList.builder();
            Builder<BlockTrapDoorWoodTFC> trapDoors = ImmutableList.builder();
            Builder<BlockChestTFC> chests = ImmutableList.builder();
            Builder<BlockToolRackTFC> toolRacks = ImmutableList.builder();
            Builder<BlockBarrelTFC> barrelBlocks = ImmutableList.builder();
            Builder<BlockPlantTFC> plants = ImmutableList.builder();
            Builder<BlockLoomTFC> looms = ImmutableList.builder();
            Builder<BlockSupportTFC> supports = ImmutableList.builder();

            for (Tree wood : TFCRegistries.TREES.getValuesCollection())
            {
                logs.add(register(r, "wood/log/" + wood.getRegistryName().getPath(), new BlockLogTFC(wood), CT_WOOD));
                planks.add(register(r, "wood/planks/" + wood.getRegistryName().getPath(), new BlockPlanksTFC(wood), CT_WOOD));
                workbenches.add(register(r, "wood/workbench/" + wood.getRegistryName().getPath(), new BlockWorkbenchTFC(wood), CT_DECORATIONS));
                bookshelfs.add(register(r, "wood/bookshelf/" + wood.getRegistryName().getPath(), new BlockBookshelfTFC(wood), CT_DECORATIONS));
                leaves.add(register(r, "wood/leaves/" + wood.getRegistryName().getPath(), new BlockLeavesTFC(wood), CT_WOOD));
                fenceGates.add(register(r, "wood/fence_gate/" + wood.getRegistryName().getPath(), new BlockFenceGateTFC(wood), CT_DECORATIONS));
                fence.add(register(r, "wood/fence/" + wood.getRegistryName().getPath(), new BlockFenceTFC(wood), CT_DECORATIONS));
                woodButton.add(register(r, "wood/button/" + wood.getRegistryName().getPath(), new BlockWoodButtonTFC(wood), CT_DECORATIONS));
                woodPressurePlate.add(register(r, "wood/pressure_plate/" + wood.getRegistryName().getPath().toLowerCase(), new BlockWoodPressurePlateTFC(wood), CT_DECORATIONS));
                saplings.add(register(r, "wood/sapling/" + wood.getRegistryName().getPath(), new BlockSaplingTFC(wood), CT_WOOD));
                doors.add(register(r, "wood/door/" + wood.getRegistryName().getPath(), new BlockDoorTFC(wood), CT_DECORATIONS));
                trapDoors.add(register(r, "wood/trapdoor/" + wood.getRegistryName().getPath(), new BlockTrapDoorWoodTFC(wood), CT_DECORATIONS));
                chests.add(register(r, "wood/chest/" + wood.getRegistryName().getPath(), new BlockChestTFC(BlockChestTFC.TFCBASIC, wood), CT_DECORATIONS));
                chests.add(register(r, "wood/chest_trap/" + wood.getRegistryName().getPath(), new BlockChestTFC(BlockChestTFC.TFCTRAP, wood), CT_DECORATIONS));

                toolRacks.add(register(r, "wood/tool_rack/" + wood.getRegistryName().getPath(), new BlockToolRackTFC(wood), CT_DECORATIONS));
                barrelBlocks.add(register(r, "wood/barrel/" + wood.getRegistryName().getPath(), new BlockBarrelTFC(wood), CT_DECORATIONS));

                looms.add(register(r, "wood/loom/" + wood.getRegistryName().getPath(), new BlockLoomTFC(wood), CT_WOOD));
                supports.add(register(r, "wood/support/" + wood.getRegistryName().getPath(), new BlockSupportTFC(wood), CT_WOOD));
            }

            allPlankBlocks = planks.build();
            allWorkbenchBlocks = workbenches.build();
            allBookshelfBlocks = bookshelfs.build();
            allLoomBlocks = looms.build();
            allFenceGateBlocks = fenceGates.build();
            allFenceBlocks = fence.build();
            allWoodButtonBlocks = woodButton.build();
            allWoodPressurePlateBlocks = woodPressurePlate.build();
            allBarrelBlocksBlocks = barrelBlocks.build();

            allLogBlocks = logs.build();
            allLeafBlocks = leaves.build();
            allSaplingBlocks = saplings.build();
            allDoorBlocks = doors.build();
            allTrapDoorWoodBlocks = trapDoors.build();
            allChestBlocks = chests.build();
            allToolRackBlocks = toolRacks.build();
            allSupportBlocks = supports.build();

            // logs are special
            allPlankBlocks.forEach(x -> colorizedItemBlocks.add(new ItemBlockTFC(x)));
            allWorkbenchBlocks.forEach(x -> colorizedItemBlocks.add(new ItemBlockTFC(x)));
            allBookshelfBlocks.forEach(x -> colorizedItemBlocks.add(new ItemBlockTFC(x)));
            allLoomBlocks.forEach(x -> colorizedItemBlocks.add(new ItemBlockTFC(x)));
            allFenceBlocks.forEach(x -> colorizedItemBlocks.add(new ItemBlockTFC(x)));
            allWoodButtonBlocks.forEach(x -> colorizedItemBlocks.add(new ItemBlockTFC(x)));
            allWoodPressurePlateBlocks.forEach(x -> colorizedItemBlocks.add(new ItemBlockTFC(x)));
            allBarrelBlocksBlocks.forEach(x -> colorizedItemBlocks.add(new ItemBlockBarrel(x)));

            allLeafBlocks.forEach(x -> normalItemBlocks.add(new ItemBlockTFC(x)));
            allFenceGateBlocks.forEach(x -> inventoryItemBlocks.add(new ItemBlockTFC(x)));
            allSaplingBlocks.forEach(x -> inventoryItemBlocks.add(new ItemBlockSaplingTFC(x)));
            allTrapDoorWoodBlocks.forEach(x -> inventoryItemBlocks.add(new ItemBlockTFC(x)));
            allChestBlocks.forEach(x -> normalItemBlocks.add(new ItemBlockTFC(x)));
            allToolRackBlocks.forEach(x -> normalItemBlocks.add(new ItemBlockTFC(x)));
            allSupportBlocks.forEach(x -> normalItemBlocks.add(new ItemBlockTFC(x)));
        }

        {
            Builder<BlockWallTFC> blockWallTFC = ImmutableList.builder();
            Builder<BlockRockStairsTFC> blockRockStairsTFC = new Builder<>();
            Builder<BlockWoodStairsTFC> blockWoodStairsTFC = new Builder<>();
            Builder<BlockRockSlabTFC.Half> blockRockSlabTFC = new Builder<>();
            Builder<BlockWoodSlabTFC.Half> blockWoodSlabTFC = new Builder<>();

            // Walls
            for (Type type : new Type[] {SMOOTH, COBBLE, BRICKS, MUD_BRICKS})
                for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
                    blockWallTFC.add(register(r, ("wall/" + type.name().toLowerCase() + "/" + rock.getRegistryName().getPath()).toLowerCase(), new BlockWallTFC(BlockRockVariant.get(rock, type)), CT_DECORATIONS));

            // Stairs
            for (Type type : new Type[] {SMOOTH, COBBLE, BRICKS, MUD_BRICKS, RAW})
                for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
                    blockRockStairsTFC.add(register(r, "stairs/" + (type.name().toLowerCase() + "/" + rock.getRegistryName().getPath()).toLowerCase(), new BlockRockStairsTFC(rock, type), CT_DECORATIONS));
            for (Tree wood : TFCRegistries.TREES.getValuesCollection())
                blockWoodStairsTFC.add(register(r, "stairs/wood/" + wood.getRegistryName().getPath(), new BlockWoodStairsTFC(wood), CT_DECORATIONS));

            // Double Slabs
            // Full slabs are the same as full blocks, they are not saved to a list, they are kept track of by the halfslab version.
            for (Type type : new Type[] {SMOOTH, COBBLE, BRICKS, MUD_BRICKS, RAW})
                for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
                    register(r, "double_slab/" + (type.name().toLowerCase() + "/" + rock.getRegistryName().getPath()).toLowerCase(), new BlockRockSlabTFC.Double(rock, type));
            for (Tree wood : TFCRegistries.TREES.getValuesCollection())
                register(r, "double_slab/wood/" + wood.getRegistryName().getPath(), new BlockWoodSlabTFC.Double(wood));

            // Slabs
            for (Type type : new Type[] {SMOOTH, COBBLE, BRICKS, MUD_BRICKS, RAW})
                for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
                    blockRockSlabTFC.add(register(r, "slab/" + (type.name().toLowerCase() + "/" + rock.getRegistryName().getPath()).toLowerCase(), new BlockRockSlabTFC.Half(rock, type), CT_DECORATIONS));
            for (Tree wood : TFCRegistries.TREES.getValuesCollection())
                blockWoodSlabTFC.add(register(r, "slab/wood/" + wood.getRegistryName().getPath(), new BlockWoodSlabTFC.Half(wood), CT_DECORATIONS));

            for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
            {
                // Redstone things
                inventoryItemBlocks.add(new ItemBlockTFC(register(r, "stone/button/" + rock.getRegistryName().getPath().toLowerCase(), new BlockButtonStoneTFC(rock), CT_DECORATIONS)));
                inventoryItemBlocks.add(new ItemBlockTFC(register(r, "stone/pressure_plate/" + rock.getRegistryName().getPath().toLowerCase(), new BlockPressurePlateTFC(rock), CT_DECORATIONS)));
            }

            allWallBlocks = blockWallTFC.build();
            allRockStairsBlocks = blockRockStairsTFC.build();
            allWoodStairsBlocks = blockWoodStairsTFC.build();
            allRockSlabBlocks = blockRockSlabTFC.build();
            allWoodSlabBlocks = blockWoodSlabTFC.build();
            allWallBlocks.forEach(x -> normalItemBlocks.add(new ItemBlockTFC(x)));
            allRockStairsBlocks.forEach(x -> normalItemBlocks.add(new ItemBlockTFC(x)));
            allWoodStairsBlocks.forEach(x -> colorizedItemBlocks.add(new ItemBlockTFC(x)));
        }

        {
            Builder<BlockAnvilTFC> anvils = ImmutableList.builder();
            Builder<BlockCladdingTFC> sheets = ImmutableList.builder();
            Builder<BlockLampTFC> lamps = ImmutableList.builder();
            Builder<BlockTrapDoorMetalTFC> metalTrapdoors = ImmutableList.builder();

            for (gregtech.api.unification.material.Material material : GregTechAPI.MATERIAL_REGISTRY)
            {
                if (material.hasFlag(TFCMaterialFlags.GENERATE_ANVIL)) {
                    anvils.add(register(r, "anvil/" + material.getUnlocalizedName(), new BlockAnvilTFC(material), CT_METAL));
                }

                if (material.hasFlag(TFCMaterialFlags.GENERATE_CLADDING)) {
                    sheets.add(register(r, "cladding/" + material.getUnlocalizedName(), new BlockCladdingTFC(material), CT_METAL));
                }

                if (material.hasFlag(TFCMaterialFlags.GENERATE_TRAPDOOR)) {
                    metalTrapdoors.add(register(r, "trapdoor/" + material.getUnlocalizedName(), new BlockTrapDoorMetalTFC(material), CT_METAL));
                }

                if (material.hasFlag(TFCMaterialFlags.GENERATE_LAMP)) {
                    lamps.add(register(r, "lamp/" + material.getUnlocalizedName(), new BlockLampTFC(material), CT_METAL));
                }
            }

            allAnvils = anvils.build();
            allSheets = sheets.build();
            allLamps = lamps.build();
            allTrapDoorMetalBlocks = metalTrapdoors.build();
        }

        Builder<BlockCropTFC> cropBlocks = ImmutableList.builder();
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

        Builder<BlockCropDead> deadCrops = ImmutableList.builder();
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
            allFruitTreeSaplingBlocks.forEach(x -> inventoryItemBlocks.add(new ItemBlockTFC(x)));
            allFruitTreeLeavesBlocks.forEach(x -> inventoryItemBlocks.add(new ItemBlockTFC(x)));
            allBerryBushBlocks.forEach(x -> inventoryItemBlocks.add(new ItemBlockTFC(x)));
        }

        {

            Builder<BlockPlantTFC> b = ImmutableList.builder();
            Builder<BlockFlowerPotTFC> pots = ImmutableList.builder();
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
            }
            allPlantBlocks = b.build();
            allFlowerPots = pots.build();

            for (BlockPlantTFC blockPlant : allPlantBlocks)
            {
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
            }
        }

        {
            Builder<BlockPlantTFC> b = ImmutableList.builder();
            for (Plant plant : TFCRegistries.PLANTS.getValuesCollection())
            {
                if (plant.getPlantType() == Plant.PlantType.SHORT_GRASS || plant.getPlantType() == Plant.PlantType.TALL_GRASS)
                    b.add(register(r, "plants/" + plant.getRegistryName().getPath(), plant.getPlantType().create(plant), CT_FLORA));
            }
            allGrassBlocks = b.build();
            for (BlockPlantTFC blockPlant : allGrassBlocks)
            {
                normalItemBlocks.add(new ItemBlockTFC(blockPlant));
            }
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
        register(r, "log_pile", new BlockLogPileTFC());
        register(r, "molten", new BlockMolten());
        register(r, "bloom", new BlockBloom());
        register(r, "thatch_bed", new BlockThatchBed());

        // Note: if you add blocks you don't need to put them in this list of todos. Feel free to add them where they make sense :)

        // todo: smoke rack (placed with any string, so event based?) + smoke blocks or will we use particles?

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
        register(TEChestTFC.class, "chest");
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
                    new BlockSnowTFC().setRegistryName("minecraft", "snow_layer").setTranslationKey("snow")
            );

        }

        if (ConfigTFC.General.OVERRIDES.enableTorchOverride)
        {
            event.getRegistry().register(new BlockTorchTFC().setRegistryName("minecraft", "torch").setTranslationKey("torch"));
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
                type == ROOTED_LOAMY_SAND ||
                type == ROOTED_SANDY_LOAM ||
                type == ROOTED_LOAM ||
                type == ROOTED_SILT_LOAM ||
                type == ROOTED_SILT ||
                type == ROOTED_HUMUS ||
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
                type == ROOTED_LOAMY_SAND ||
                type == ROOTED_SANDY_LOAM ||
                type == ROOTED_LOAM ||
                type == ROOTED_SILT_LOAM ||
                type == ROOTED_SILT ||
                type == ROOTED_HUMUS ||
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
                type == ROOTED_LOAMY_SAND ||
                type == ROOTED_SANDY_LOAM ||
                type == ROOTED_LOAM ||
                type == ROOTED_SILT_LOAM ||
                type == ROOTED_SILT ||
                type == ROOTED_HUMUS ||
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
                type == ROOTED_LOAMY_SAND ||
                type == ROOTED_SANDY_LOAM ||
                type == ROOTED_LOAM ||
                type == ROOTED_SILT_LOAM ||
                type == ROOTED_SILT ||
                type == ROOTED_HUMUS ||
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
                type == ROOTED_LOAMY_SAND ||
                type == ROOTED_SANDY_LOAM ||
                type == ROOTED_LOAM ||
                type == ROOTED_SILT_LOAM ||
                type == ROOTED_SILT ||
                type == ROOTED_HUMUS ||
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
