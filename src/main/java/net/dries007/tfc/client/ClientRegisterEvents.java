/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.client;

import com.google.common.collect.ImmutableMap;
import gregtech.api.unification.material.Material;
import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.api.capability.IMaterialHandler;
import net.dries007.tfc.api.capability.food.CapabilityFood;
import net.dries007.tfc.api.capability.food.IFood;
import net.dries007.tfc.api.stateproperty.StatePropertiesTFC;
import net.dries007.tfc.api.types.Rock.Type;
import net.dries007.tfc.api.util.IWoodHandler;
import net.dries007.tfc.client.render.*;
import net.dries007.tfc.compat.tfc.TFCOrePrefixExtended;
import net.dries007.tfc.compat.tfc.TFGUtils;
import net.dries007.tfc.objects.blocks.BlockThatchBed;
import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.objects.blocks.agriculture.TFCBlockFruitTreeLeaves;
import net.dries007.tfc.objects.blocks.agriculture.TFCBlockCropDead;
import net.dries007.tfc.objects.blocks.groundcover.BlockCoral;
import net.dries007.tfc.objects.blocks.plants.TFCBlockPlant;
import net.dries007.tfc.objects.blocks.plants.TFCBlockWaterGlowPlant;
import net.dries007.tfc.objects.blocks.stone.TFCBlockRockVariant;
import net.dries007.tfc.objects.blocks.stone.TFCBlockRockRaw;
import net.dries007.tfc.objects.blocks.stone.TFCBlockRockSlab;
import net.dries007.tfc.objects.blocks.stone.TFCBlockRockSmooth;
import net.dries007.tfc.objects.blocks.stone.farmland.*;
import net.dries007.tfc.objects.blocks.wood.*;
import net.dries007.tfc.objects.blocks.wood.tree.TFCBlockLeaves;
import net.dries007.tfc.objects.blocks.wood.tree.TFCBlockLog;
import net.dries007.tfc.objects.blocks.wood.tree.TFCBlockSapling;
import net.dries007.tfc.objects.items.ItemAnimalHide;
import net.dries007.tfc.objects.items.TFCItemArmor;
import net.dries007.tfc.objects.items.TFCItems;
import net.dries007.tfc.objects.items.ceramics.fired.molds.ItemClayMold;
import net.dries007.tfc.objects.items.ceramics.fired.molds.ItemEarthenwareMold;
import net.dries007.tfc.objects.items.ceramics.fired.molds.ItemKaoliniteMold;
import net.dries007.tfc.objects.items.ceramics.fired.molds.ItemStonewareMold;
import net.dries007.tfc.objects.te.*;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;
import static net.dries007.tfc.objects.blocks.BlockPlacedHide.SIZE;
import static net.dries007.tfc.objects.blocks.agriculture.TFCBlockCrop.WILD;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = MOD_ID)
public final class ClientRegisterEvents {
    public static final IBlockColor woodBlockColors = (IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) ->
            tintIndex == 0 ? ((IWoodHandler) state.getBlock()).getWood().getColor() : 0xFFFFFF;

    public static final IItemColor woodItemBlockColors = (stack, tintIndex) ->
            tintIndex == 0 ? ((IWoodHandler) ((ItemBlock) stack.getItem()).getBlock()).getWood().getColor() : 0xFFFFFF;

    public static final IItemColor woodItemColors = (stack, tintIndex) ->
            tintIndex == 0 ? ((IWoodHandler) stack.getItem()).getWood().getColor() : 0xFFFFFF;

    public static final IItemColor moldItemColors = (stack, tintIndex) -> {
        if (tintIndex != 1) return 0xFFFFFF;

        IFluidHandler cap = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
        if (cap != null) {
            if (cap instanceof IMaterialHandler) {
                Material material = ((IMaterialHandler) cap).getMaterial();
                if (material != null) {
                    return material.getMaterialRGB();
                }
            }
        }
        return 0xFFFFFF;
    };

    @SubscribeEvent
    @SuppressWarnings("ConstantConditions")
    public static void registerModels(ModelRegistryEvent event) {
        // Register models for Items //

        // Registering fluid containers
        ModelLoader.setCustomModelResourceLocation(TFCItems.WOODEN_BUCKET, 0, new ModelResourceLocation(TFCItems.WOODEN_BUCKET.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(TFCItems.FIRED_JUG, 0, new ModelResourceLocation(TFCItems.FIRED_JUG.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(TFCItems.FIRED_EARTHENWARE_JUG, 0, new ModelResourceLocation(TFCItems.FIRED_EARTHENWARE_JUG.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(TFCItems.FIRED_KAOLINITE_JUG, 0, new ModelResourceLocation(TFCItems.FIRED_KAOLINITE_JUG.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(TFCItems.FIRED_STONEWARE_JUG, 0, new ModelResourceLocation(TFCItems.FIRED_STONEWARE_JUG.getRegistryName(), "inventory"));
        // ModelLoader.setCustomModelResourceLocation(ItemsTFC.BLUE_STEEL_BUCKET, 0, new ModelResourceLocation(ItemsTFC.BLUE_STEEL_BUCKET.getRegistryName(), "inventory"));
        // ModelLoader.setCustomModelResourceLocation(ItemsTFC.RED_STEEL_BUCKET, 0, new ModelResourceLocation(ItemsTFC.RED_STEEL_BUCKET.getRegistryName(), "inventory"));

        // Simple Items
        TFCItems.getAllSimpleItems().forEach(s -> ModelLoader.setCustomModelResourceLocation(s, 0, new ModelResourceLocation(s.getRegistryName().toString())));

        // Lumber Items
        TFCItems.getAllLumberItems().forEach(s -> ModelLoader.setCustomModelResourceLocation(s, 0, new ModelResourceLocation(new ResourceLocation(MOD_ID, "wood/lumber"), "normal")));

        // Boat Items
        TFCItems.getAllBoatItems().forEach(s -> ModelLoader.setCustomModelResourceLocation(s, 0, new ModelResourceLocation(new ResourceLocation(MOD_ID, "wood/boat"), "normal")));

        // Armor Items
        TFCItems.getAllArmorItems().forEach(s -> ModelLoader.setCustomModelResourceLocation(s, 0, new ModelResourceLocation(s.getRegistryName().toString())));

        // Dye color Items
        for (EnumDyeColor color : EnumDyeColor.values()) {
            ModelLoader.setCustomModelResourceLocation(TFCItems.UNFIRED_VESSEL_GLAZED, color.getDyeDamage(), new ModelResourceLocation(TFCItems.UNFIRED_VESSEL_GLAZED.getRegistryName().toString()));
            ModelLoader.setCustomModelResourceLocation(TFCItems.FIRED_VESSEL_GLAZED, color.getDyeDamage(), new ModelResourceLocation(TFCItems.FIRED_VESSEL_GLAZED.getRegistryName().toString()));

            ModelLoader.setCustomModelResourceLocation(TFCItems.UNFIRED_EARTHENWARE_VESSEL_GLAZED, color.getDyeDamage(), new ModelResourceLocation(TFCItems.UNFIRED_EARTHENWARE_VESSEL_GLAZED.getRegistryName().toString()));
            ModelLoader.setCustomModelResourceLocation(TFCItems.FIRED_EARTHENWARE_VESSEL_GLAZED, color.getDyeDamage(), new ModelResourceLocation(TFCItems.FIRED_EARTHENWARE_VESSEL_GLAZED.getRegistryName().toString()));

            ModelLoader.setCustomModelResourceLocation(TFCItems.UNFIRED_KAOLINITE_VESSEL_GLAZED, color.getDyeDamage(), new ModelResourceLocation(TFCItems.UNFIRED_KAOLINITE_VESSEL_GLAZED.getRegistryName().toString()));
            ModelLoader.setCustomModelResourceLocation(TFCItems.FIRED_KAOLINITE_VESSEL_GLAZED, color.getDyeDamage(), new ModelResourceLocation(TFCItems.FIRED_KAOLINITE_VESSEL_GLAZED.getRegistryName().toString()));

            ModelLoader.setCustomModelResourceLocation(TFCItems.UNFIRED_STONEWARE_VESSEL_GLAZED, color.getDyeDamage(), new ModelResourceLocation(TFCItems.UNFIRED_STONEWARE_VESSEL_GLAZED.getRegistryName().toString()));
            ModelLoader.setCustomModelResourceLocation(TFCItems.FIRED_STONEWARE_VESSEL_GLAZED, color.getDyeDamage(), new ModelResourceLocation(TFCItems.FIRED_STONEWARE_VESSEL_GLAZED.getRegistryName().toString()));
        }

        // Gold Pan
        /*ModelLoader.registerItemVariants(ItemsTFC.GOLDPAN, Arrays.stream(ItemGoldPan.TYPES).map(e -> new ResourceLocation(MOD_ID, "goldpan/" + e)).toArray(ResourceLocation[]::new));
        for (int meta = 0; meta < ItemGoldPan.TYPES.length; meta++)
            ModelLoader.setCustomModelResourceLocation(ItemsTFC.GOLDPAN, meta, new ModelResourceLocation(MOD_ID + ":goldpan/" + ItemGoldPan.TYPES[meta]));
        ModelLoader.registerItemVariants(ItemsTFC.GOLDPAN, Arrays.stream(ItemGoldPan.TYPES).map(e -> new ResourceLocation(MOD_ID, "goldpan/" + e)).toArray(ResourceLocation[]::new));*/

        // Ceramic Molds
        for (TFCOrePrefixExtended extendedOrePrefix : TFGUtils.TFC_OREPREFIX_REGISTRY) {
            if (extendedOrePrefix.isHasMold()) {
                ItemClayMold clayMold = ItemClayMold.get(extendedOrePrefix.getOrePrefix());
                ItemEarthenwareMold earthenwareMold = ItemEarthenwareMold.get(extendedOrePrefix.getOrePrefix());
                ItemKaoliniteMold kaoliniteMold = ItemKaoliniteMold.get(extendedOrePrefix.getOrePrefix());
                ItemStonewareMold stonewareMold = ItemStonewareMold.get(extendedOrePrefix.getOrePrefix());

                ModelBakery.registerItemVariants(clayMold, new ModelResourceLocation(clayMold.getRegistryName().toString() + "_empty"));
                ModelBakery.registerItemVariants(earthenwareMold, new ModelResourceLocation(earthenwareMold.getRegistryName().toString() + "_empty"));
                ModelBakery.registerItemVariants(kaoliniteMold, new ModelResourceLocation(kaoliniteMold.getRegistryName().toString() + "_empty"));
                ModelBakery.registerItemVariants(stonewareMold, new ModelResourceLocation(stonewareMold.getRegistryName().toString() + "_empty"));

                ModelBakery.registerItemVariants(clayMold, new ModelResourceLocation(clayMold.getRegistryName().toString() + "_filled"));
                ModelBakery.registerItemVariants(earthenwareMold, new ModelResourceLocation(earthenwareMold.getRegistryName().toString() + "_filled"));
                ModelBakery.registerItemVariants(kaoliniteMold, new ModelResourceLocation(kaoliniteMold.getRegistryName().toString() + "_filled"));
                ModelBakery.registerItemVariants(stonewareMold, new ModelResourceLocation(stonewareMold.getRegistryName().toString() + "_filled"));

                ModelLoader.setCustomMeshDefinition(clayMold, new ItemMeshDefinition() {
                    @Override
                    @Nonnull
                    public ModelResourceLocation getModelLocation(@Nonnull ItemStack stack) {
                        IFluidHandler cap = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
                        if (cap instanceof IMaterialHandler) {
                            Material material = ((IMaterialHandler) cap).getMaterial();
                            if (material != null) {
                                return new ModelResourceLocation(clayMold.getRegistryName().toString() + "_filled");
                            }
                        }
                        return new ModelResourceLocation(clayMold.getRegistryName().toString() + "_empty");
                    }
                });

                ModelLoader.setCustomMeshDefinition(earthenwareMold, new ItemMeshDefinition() {
                    @Override
                    @Nonnull
                    public ModelResourceLocation getModelLocation(@Nonnull ItemStack stack) {
                        IFluidHandler cap = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
                        if (cap instanceof IMaterialHandler) {
                            Material material = ((IMaterialHandler) cap).getMaterial();
                            if (material != null) {
                                return new ModelResourceLocation(earthenwareMold.getRegistryName().toString() + "_filled");
                            }
                        }
                        return new ModelResourceLocation(earthenwareMold.getRegistryName().toString() + "_empty");
                    }
                });

                ModelLoader.setCustomMeshDefinition(kaoliniteMold, new ItemMeshDefinition() {
                    @Override
                    @Nonnull
                    public ModelResourceLocation getModelLocation(@Nonnull ItemStack stack) {
                        IFluidHandler cap = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
                        if (cap instanceof IMaterialHandler) {
                            Material material = ((IMaterialHandler) cap).getMaterial();
                            if (material != null) {
                                return new ModelResourceLocation(kaoliniteMold.getRegistryName().toString() + "_filled");
                            }
                        }
                        return new ModelResourceLocation(kaoliniteMold.getRegistryName().toString() + "_empty");
                    }
                });

                ModelLoader.setCustomMeshDefinition(stonewareMold, new ItemMeshDefinition() {
                    @Override
                    @Nonnull
                    public ModelResourceLocation getModelLocation(@Nonnull ItemStack stack) {
                        IFluidHandler cap = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
                        if (cap instanceof IMaterialHandler) {
                            Material material = ((IMaterialHandler) cap).getMaterial();
                            if (material != null) {
                                return new ModelResourceLocation(stonewareMold.getRegistryName().toString() + "_filled");
                            }
                        }
                        return new ModelResourceLocation(stonewareMold.getRegistryName().toString() + "_empty");
                    }
                });
            }

        }

        // Register blockstates for Blocks

        // Planks
        BlocksTFC.getAllPlankBlocks().forEach(TFCBlockPlanks::onModelRegister);
        // Workbenches
        BlocksTFC.getAllWorkbenchBlocks().forEach(TFCBlockWorkbench::onModelRegister);
        // Bookshelfs
        BlocksTFC.getAllBookshelfBlocks().forEach(TFCBlockBookshelf::onModelRegister);
        // Looms
        BlocksTFC.getAllLoomBlocks().forEach(TFCBlockLoom::onModelRegister);
        // Barrel Item Blocks
        BlocksTFC.getAllBarrelBlocks().forEach(TFCBlockBarrel::onModelRegister);
        // Stairs
        BlocksTFC.getAllWoodStairsBlocks().forEach(TFCBlockWoodStairs::onModelRegister);
        // Slabs
        BlocksTFC.getAllWoodSlabBlocks().forEach(TFCBlockWoodSlab.Half::onModelRegister);
        // Fence
        BlocksTFC.getAllFenceBlocks().forEach(TFCBlockFence::onModelRegister);
        // Fence gates
        BlocksTFC.getAllFenceGateBlocks().forEach(TFCBlockFenceGate::onModelRegister);
        // Fence log
        // todo ?
        // Fence gates log
        // todo ?
        // ToolRack
        BlocksTFC.getAllToolRackBlocks().forEach(TFCBlockToolRack::onModelRegister);
        // Pressure plate
        BlocksTFC.getAllWoodPressurePlateBlocks().forEach(TFCBlockWoodPressurePlate::onModelRegister);
        // Button
        BlocksTFC.getAllWoodButtonBlocks().forEach(TFCBlockWoodButton::onModelRegister);

        // Item Blocks
        BlocksTFC.getAllNormalItemBlocks().forEach(s -> ModelLoader.setCustomModelResourceLocation(s, 0, new ModelResourceLocation(s.getRegistryName(), "normal")));

        // Inventory Item Blocks
        BlocksTFC.getAllInventoryItemBlocks().forEach(s -> ModelLoader.setCustomModelResourceLocation(s, 0, new ModelResourceLocation(s.getRegistryName(), "inventory")));

        // BLOCKS - STATE MAPPERS //
        // Blocks with Ignored Properties
        BlocksTFC.getAllFluidBlocks().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().ignore(BlockFluidBase.LEVEL).build()));
        BlocksTFC.getAllLeafBlocks().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().ignore(BlockLeaves.DECAYABLE).build()));
        BlocksTFC.getAllRockWallBlocks().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().ignore(BlockWall.VARIANT).build()));
        BlocksTFC.getAllLogBlocks().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().ignore(TFCBlockLog.PLACED).build()));
        BlocksTFC.getAllSaplingBlocks().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().ignore(TFCBlockSapling.STAGE).build()));
        BlocksTFC.getAllDoorBlocks().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().ignore(BlockDoor.POWERED).build()));
        BlocksTFC.getAllChestBlocks().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().ignore(BlockChest.FACING).build()));
        BlocksTFC.getAllRockSlabBlocks().forEach(s -> {
            ModelLoader.setCustomStateMapper(s, new StateMap.Builder().ignore(TFCBlockRockSlab.VARIANT).build());
            ModelLoader.setCustomStateMapper(s.doubleSlab, new StateMap.Builder().ignore(TFCBlockRockSlab.VARIANT).build());
        });
        BlocksTFC.getAllCropBlocks().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().ignore(WILD).build()));
        BlocksTFC.getAllFruitTreeLeavesBlocks().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().ignore(TFCBlockFruitTreeLeaves.DECAYABLE).ignore(TFCBlockFruitTreeLeaves.HARVESTABLE).build()));
        BlocksTFC.getAllRockVariantsBlock().forEach(e -> {
            switch (e.getType()) {
                case RAW:

                case MOSSY_RAW:
                    ModelLoader.setCustomStateMapper(e, new StateMap.Builder().ignore(TFCBlockRockRaw.CAN_FALL).build());
                    break;

                case FARMLAND:
                    ModelLoader.setCustomStateMapper(e, new StateMap.Builder().ignore(TFCBlockFarmland.MOISTURE).build());
                    break;

                case SMOOTH:
                    ModelLoader.setCustomStateMapper(e, new StateMap.Builder().ignore(TFCBlockRockSmooth.CAN_FALL).build());
                    break;

                case LOAMY_SAND_FARMLAND:
                    ModelLoader.setCustomStateMapper(e, new StateMap.Builder().ignore(BlockLoamySandFarmland.MOISTURE).build());
                    break;

                case SANDY_LOAM_FARMLAND:
                    ModelLoader.setCustomStateMapper(e, new StateMap.Builder().ignore(BlockSandyLoamFarmland.MOISTURE).build());
                    break;

                case LOAM_FARMLAND:
                    ModelLoader.setCustomStateMapper(e, new StateMap.Builder().ignore(BlockLoamFarmland.MOISTURE).build());
                    break;

                case SILT_LOAM_FARMLAND:
                    ModelLoader.setCustomStateMapper(e, new StateMap.Builder().ignore(BlockSiltLoamFarmland.MOISTURE).build());
                    break;

                case SILT_FARMLAND:
                    ModelLoader.setCustomStateMapper(e, new StateMap.Builder().ignore(BlockSiltFarmland.MOISTURE).build());
                    break;

                case HUMUS_FARMLAND:
                    ModelLoader.setCustomStateMapper(e, new StateMap.Builder().ignore(BlockHumusFarmland.MOISTURE).build());
                    break;
            }
        });

        ModelLoader.setCustomStateMapper(BlocksTFC.THATCH_BED, new StateMap.Builder().ignore(BlockThatchBed.OCCUPIED).build());

        // Empty Models
        final ModelResourceLocation empty = new ModelResourceLocation(MOD_ID + ":empty");
        // todo: switch to hide rack (involves changing mechanics, etc)
        final ModelResourceLocation hideRack = new ModelResourceLocation(MOD_ID + ":hide_rack");

        ModelLoader.setCustomStateMapper(BlocksTFC.PIT_KILN, blockIn -> ImmutableMap.of(BlocksTFC.PIT_KILN.getDefaultState(), empty));
        ModelLoader.setCustomStateMapper(BlocksTFC.PLACED_ITEM_FLAT, blockIn -> ImmutableMap.of(BlocksTFC.PLACED_ITEM_FLAT.getDefaultState(), empty));
        ModelLoader.setCustomStateMapper(BlocksTFC.PLACED_ITEM, blockIn -> ImmutableMap.of(BlocksTFC.PLACED_ITEM.getDefaultState(), empty));
        ModelLoader.setCustomStateMapper(BlocksTFC.PLACED_HIDE, blockIn -> ImmutableMap.of(BlocksTFC.PLACED_HIDE.getDefaultState().withProperty(SIZE, ItemAnimalHide.HideSize.SMALL), empty, BlocksTFC.PLACED_HIDE.getDefaultState().withProperty(SIZE, ItemAnimalHide.HideSize.MEDIUM), empty, BlocksTFC.PLACED_HIDE.getDefaultState().withProperty(SIZE, ItemAnimalHide.HideSize.LARGE), empty));

        // Register TESRs
        ClientRegistry.bindTileEntitySpecialRenderer(TEChest.class, new TESRChestTFC());
        ClientRegistry.bindTileEntitySpecialRenderer(TEToolRack.class, new TESRToolRack());
        ClientRegistry.bindTileEntitySpecialRenderer(TEPitKiln.class, new TESRPitKiln());
        ClientRegistry.bindTileEntitySpecialRenderer(TEPlacedItemFlat.class, new TESRPlacedItemFlat());
        ClientRegistry.bindTileEntitySpecialRenderer(TEPlacedItem.class, new TESRPlacedItem());
        ClientRegistry.bindTileEntitySpecialRenderer(TEPlacedHide.class, new TESRPlacedHide());
        ClientRegistry.bindTileEntitySpecialRenderer(TEQuern.class, new TESRQuern());
        ClientRegistry.bindTileEntitySpecialRenderer(TEBellows.class, new TESRBellows());
        ClientRegistry.bindTileEntitySpecialRenderer(TEBarrel.class, new TESRBarrel());
        ClientRegistry.bindTileEntitySpecialRenderer(TEAnvilTFC.class, new TESRAnvil());
        ClientRegistry.bindTileEntitySpecialRenderer(TELoom.class, new TESRLoom());
        ClientRegistry.bindTileEntitySpecialRenderer(TECrucible.class, new TESRCrucible());
        ClientRegistry.bindTileEntitySpecialRenderer(TEFirePit.class, new TESRFirePit());
        ClientRegistry.bindTileEntitySpecialRenderer(TESluice.class, new TESRSluice());
        ClientRegistry.bindTileEntitySpecialRenderer(TEDryer.class, new TESRDryer());
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerColorHandlerBlocks(ColorHandlerEvent.Block event) {
        BlockColors blockColors = event.getBlockColors();

        // Planks
        BlocksTFC.getAllPlankBlocks().forEach(s -> blockColors.registerBlockColorHandler(woodBlockColors, s));
        // Workbenches
        BlocksTFC.getAllWorkbenchBlocks().forEach(s -> blockColors.registerBlockColorHandler(woodBlockColors, s));
        // Bookshelfs
        BlocksTFC.getAllBookshelfBlocks().forEach(s -> blockColors.registerBlockColorHandler(woodBlockColors, s));
        // Looms
        BlocksTFC.getAllLoomBlocks().forEach(s -> blockColors.registerBlockColorHandler(woodBlockColors, s));
        // Barrels
        BlocksTFC.getAllBarrelBlocks().forEach(s -> blockColors.registerBlockColorHandler(woodBlockColors, s));
        // Stairs
        BlocksTFC.getAllWoodStairsBlocks().forEach(s -> blockColors.registerBlockColorHandler(woodBlockColors, s));
        // Slabs
        BlocksTFC.getAllWoodSlabBlocks().forEach(s -> blockColors.registerBlockColorHandler(woodBlockColors, s, s.doubleSlab));
        // Fence
        BlocksTFC.getAllFenceBlocks().forEach(s -> blockColors.registerBlockColorHandler(woodBlockColors, s));
        // Fence gates
        BlocksTFC.getAllFenceGateBlocks().forEach(s -> blockColors.registerBlockColorHandler(woodBlockColors, s));
        // Fence log
        // todo ?
        // Fence gates log
        // todo ?
        // ToolRack
        BlocksTFC.getAllToolRackBlocks().forEach(s -> blockColors.registerBlockColorHandler(woodBlockColors, s));
        // Pressure plate
        BlocksTFC.getAllWoodPressurePlateBlocks().forEach(s -> blockColors.registerBlockColorHandler(woodBlockColors, s));
        // Button
        BlocksTFC.getAllWoodButtonBlocks().forEach(s -> blockColors.registerBlockColorHandler(woodBlockColors, s));

        // Grass Colors
        IBlockColor grassColor = GrassColorHandler::computeGrassColor;

        // Foliage Color
        // todo: do something different for conifers - they should have a different color mapping through the seasons
        IBlockColor foliageColor = GrassColorHandler::computeGrassColor;

        for (TFCBlockCropDead block : BlocksTFC.getAllDeadCropBlocks())
            blockColors.registerBlockColorHandler((state, world, os, tintIndex) -> 0xCC7400, block);

        blockColors.registerBlockColorHandler(grassColor, BlocksTFC.PEAT_GRASS);
        blockColors.registerBlockColorHandler(grassColor, BlocksTFC.getAllRockVariantsBlock().stream()
                .filter(x -> x.getType().isGrass).toArray(TFCBlockRockVariant[]::new));
        // This is talking about tall grass vs actual grass blocks
        //blockColors.registerBlockColorHandler(grassColor, TFCBlocks.getAllGrassBlocks().toArray(new TFCBlockPlant[0]));

        blockColors.registerBlockColorHandler(foliageColor, BlocksTFC.getAllLeafBlocks().toArray(new Block[0]));
        blockColors.registerBlockColorHandler(foliageColor, BlocksTFC.getAllPlantBlocks().toArray(new TFCBlockPlant[0]));

        blockColors.registerBlockColorHandler(foliageColor, BlocksTFC.getAllFruitTreeLeavesBlocks().toArray(new Block[0]));

        //blockColors.registerBlockColorHandler(foliageColor, TFCBlocks.getAllFlowerPots().toArray(new Block[0]));

        blockColors.registerBlockColorHandler((state, worldIn, pos, tintIndex) -> TFCBlockFarmland.TINT[state.getValue(TFCBlockFarmland.MOISTURE)],
                BlocksTFC.getAllRockVariantsBlock().stream().filter(x -> x.getType() == Type.FARMLAND).toArray(TFCBlockRockVariant[]::new));

        blockColors.registerBlockColorHandler((state, worldIn, pos, tintIndex) -> BlockLoamySandFarmland.TINT[state.getValue(BlockLoamySandFarmland.MOISTURE)],
                BlocksTFC.getAllRockVariantsBlock().stream()
                        .filter(x -> x.getType() == Type.LOAMY_SAND_FARMLAND).toArray(TFCBlockRockVariant[]::new));

        blockColors.registerBlockColorHandler((state, worldIn, pos, tintIndex) -> BlockSandyLoamFarmland.TINT[state.getValue(BlockSandyLoamFarmland.MOISTURE)],
                BlocksTFC.getAllRockVariantsBlock().stream()
                        .filter(x -> x.getType() == Type.SANDY_LOAM_FARMLAND).toArray(TFCBlockRockVariant[]::new));

        blockColors.registerBlockColorHandler((state, worldIn, pos, tintIndex) -> BlockLoamFarmland.TINT[state.getValue(BlockLoamFarmland.MOISTURE)],
                BlocksTFC.getAllRockVariantsBlock().stream().filter(x -> x.getType() == Type.LOAM_FARMLAND)
                        .toArray(TFCBlockRockVariant[]::new));

        blockColors.registerBlockColorHandler((state, worldIn, pos, tintIndex) -> BlockSiltLoamFarmland.TINT[state.getValue(BlockSiltLoamFarmland.MOISTURE)],
                BlocksTFC.getAllRockVariantsBlock().stream()
                        .filter(x -> x.getType() == Type.SILT_LOAM_FARMLAND).toArray(TFCBlockRockVariant[]::new));

        blockColors.registerBlockColorHandler((state, worldIn, pos, tintIndex) -> BlockSiltFarmland.TINT[state.getValue(BlockSiltFarmland.MOISTURE)],
                BlocksTFC.getAllRockVariantsBlock().stream().filter(x -> x.getType() == Type.SILT_FARMLAND)
                        .toArray(TFCBlockRockVariant[]::new));

        blockColors.registerBlockColorHandler((state, worldIn, pos, tintIndex) -> BlockHumusFarmland.TINT[state.getValue(BlockHumusFarmland.MOISTURE)],
                BlocksTFC.getAllRockVariantsBlock().stream().filter(x -> x.getType() == Type.HUMUS_FARMLAND)
                        .toArray(TFCBlockRockVariant[]::new));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("deprecation")
    public static void registerColorHandlerItems(ColorHandlerEvent.Item event) {
        ItemColors itemColors = event.getItemColors();

        // Planks
        BlocksTFC.getAllPlankBlocks().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        // Workbenches
        BlocksTFC.getAllWorkbenchBlocks().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        // Bookshelfs
        BlocksTFC.getAllBookshelfBlocks().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        // Looms
        BlocksTFC.getAllLoomBlocks().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        // Barrels
        BlocksTFC.getAllBarrelBlocks().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        // Stairs
        BlocksTFC.getAllWoodStairsBlocks().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        // Slabs
        BlocksTFC.getAllWoodSlabBlocks().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        // Fence
        BlocksTFC.getAllFenceBlocks().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        // Fence gate
        BlocksTFC.getAllFenceGateBlocks().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        // Fence log
        // todo ?
        // Fence gates log
        // todo ?
        // ToolRack
        BlocksTFC.getAllToolRackBlocks().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        // Pressure plate
        BlocksTFC.getAllWoodPressurePlateBlocks().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        // Button
        BlocksTFC.getAllWoodButtonBlocks().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));

        // Lumber
        TFCItems.getAllLumberItems().forEach(s -> itemColors.registerItemColorHandler(woodItemColors, s));
        // Boat
        TFCItems.getAllBoatItems().forEach(s -> itemColors.registerItemColorHandler(woodItemColors, s));

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                        tintIndex > 0 ? -1 : ((TFCItemArmor) stack.getItem()).getColor(stack),
                TFCItems.getAllArmorItems().toArray(new TFCItemArmor[0]));

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                        event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
                BlocksTFC.getAllRockVariantsBlock().stream().filter(x -> x.getType().isGrass).toArray(TFCBlockRockVariant[]::new));

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                        event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
                BlocksTFC.PEAT_GRASS);

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                        event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
                BlocksTFC.getAllLeafBlocks().toArray(new TFCBlockLeaves[0]));

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                        event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
                BlocksTFC.getAllFruitTreeLeavesBlocks().toArray(new TFCBlockFruitTreeLeaves[0]));

        itemColors.registerItemColorHandler((stack, tintIndex) -> tintIndex == 1 ? EnumDyeColor.byDyeDamage(stack.getItemDamage()).getColorValue() : 0xFFFFFF,
                TFCItems.UNFIRED_VESSEL_GLAZED, TFCItems.FIRED_VESSEL_GLAZED);

        itemColors.registerItemColorHandler((stack, tintIndex) -> tintIndex == 1 ? EnumDyeColor.byDyeDamage(stack.getItemDamage()).getColorValue() : 0xFFFFFF,
                TFCItems.UNFIRED_EARTHENWARE_VESSEL_GLAZED, TFCItems.FIRED_EARTHENWARE_VESSEL_GLAZED);

        itemColors.registerItemColorHandler((stack, tintIndex) -> tintIndex == 1 ? EnumDyeColor.byDyeDamage(stack.getItemDamage()).getColorValue() : 0xFFFFFF,
                TFCItems.UNFIRED_KAOLINITE_VESSEL_GLAZED, TFCItems.FIRED_KAOLINITE_VESSEL_GLAZED);

        itemColors.registerItemColorHandler((stack, tintIndex) -> tintIndex == 1 ? EnumDyeColor.byDyeDamage(stack.getItemDamage()).getColorValue() : 0xFFFFFF,
                TFCItems.UNFIRED_STONEWARE_VESSEL_GLAZED, TFCItems.FIRED_STONEWARE_VESSEL_GLAZED);

       /* itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            TFCBlocks.getAllGrassBlocks().toArray(new TFCBlockPlant[0]));*/

        // Food
        itemColors.registerItemColorHandler((stack, tintIndex) -> {
            IFood food = stack.getCapability(CapabilityFood.CAPABILITY, null);
            if (food != null) {
                return food.isRotten() ? ConfigTFC.Client.DISPLAY.rottenFoodOverlayColor : 0xFFFFFF;
            }
            return 0xFFFFFF;
        }, ForgeRegistries.ITEMS.getValuesCollection().stream().filter(x -> x instanceof ItemFood).toArray(Item[]::new));

        // Colorize clay molds
        itemColors.registerItemColorHandler(moldItemColors, TFCItems.getAllClayMolds().toArray(new Item[0]));

        // Colorize earthenware molds
        itemColors.registerItemColorHandler(moldItemColors, TFCItems.getAllEarthenwareMolds().toArray(new Item[0]));

        // Colorize kaolinite molds
        itemColors.registerItemColorHandler(moldItemColors, TFCItems.getAllKaoliniteMolds().toArray(new Item[0]));

        // Colorize stoneware molds
        itemColors.registerItemColorHandler(moldItemColors, TFCItems.getAllStonewareMolds().toArray(new Item[0]));
    }

    @SubscribeEvent
    @SuppressWarnings("ConstantConditions")
    public static void registerModelsTFCF(ModelRegistryEvent event) {
        // ITEMS
          

        /*for (Item item : ItemsTFC.getAllItemBows())
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().toString()));*/


        // BLOCKS

        for (Block block : BlocksTFC.getAllCoralPlants())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockCoral.LEVEL).build());

        for (Block block : BlocksTFC.getAllGlowWaterPlants())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(TFCBlockWaterGlowPlant.LEVEL).build());

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverBones) {
            for (Block block : BlocksTFC.getAllSurfaceBones())
                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverDriftwood) {
            for (Block block : BlocksTFC.getAllSurfaceDriftwood())
                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverFlint) {
            for (Block block : BlocksTFC.getAllSurfaceFlint())
                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverPinecone) {
            for (Block block : BlocksTFC.getAllSurfacePinecone())
                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverSeashell) {
            for (Block block : BlocksTFC.getAllSurfaceSeashells())
                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverTwig) {
            for (Block block : BlocksTFC.getAllSurfaceTwig())
                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverRock) {
            for (Block block : BlocksTFC.getAllSurfaceRocks())
                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());
        }

        //for (Block block : TFCBlocks.getAllJoshuaTreeSaplingBlocks())
        //ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockJoshuaTreeSapling.STAGE).build());

        for (TFCBlockFruitTreeLeaves leaves : BlocksTFC.getAllFruitTreeLeavesBlocks())
            ModelLoader.setCustomStateMapper(leaves, new StateMap.Builder().ignore(TFCBlockFruitTreeLeaves.DECAYABLE).ignore(TFCBlockFruitTreeLeaves.HARVESTABLE).build());

        for (TFCBlockFenceGateLog gate : BlocksTFC.getAllFenceGateLogBlocks())
            ModelLoader.setCustomStateMapper(gate, new StateMap.Builder().ignore(TFCBlockFenceGateLog.POWERED).build());

        for (TFCBlockRockSlab.Half block : BlocksTFC.getAllRockSlabBlocks()) {
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(TFCBlockRockSlab.VARIANT).build());
            ModelLoader.setCustomStateMapper(block.doubleSlab, new StateMap.Builder().ignore(TFCBlockRockSlab.VARIANT).build());
        }
        for (Block block : BlocksTFC.getAllBambooLog())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(StatePropertiesTFC.CAN_GROW).build());

        for (Block block : BlocksTFC.getAllBambooLeaves())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockLeaves.DECAYABLE).build());

        for (Block block : BlocksTFC.getAllBambooSapling())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(TFCBlockSapling.STAGE).build());

        /*
        ModelLoader.setCustomStateMapper(BlocksTFCF.CASSIA_CINNAMON_LOG, new StateMap.Builder().ignore(StatePropertiesTFC.CAN_GROW).build());
        ModelLoader.setCustomStateMapper(BlocksTFCF.CASSIA_CINNAMON_LEAVES, new StateMap.Builder().ignore(BlockLeaves.DECAYABLE).build());
        ModelLoader.setCustomStateMapper(BlocksTFCF.CASSIA_CINNAMON_SAPLING, new StateMap.Builder().ignore(BlockSaplingTFC.STAGE).build());

        ModelLoader.setCustomStateMapper(BlocksTFCF.CEYLON_CINNAMON_LOG, new StateMap.Builder().ignore(StatePropertiesTFC.CAN_GROW).build());
        ModelLoader.setCustomStateMapper(BlocksTFCF.CEYLON_CINNAMON_LEAVES, new StateMap.Builder().ignore(BlockLeaves.DECAYABLE).build());
        ModelLoader.setCustomStateMapper(BlocksTFCF.CEYLON_CINNAMON_SAPLING, new StateMap.Builder().ignore(BlockSaplingTFC.STAGE).build());*/

    }

    @SuppressWarnings("deprecation")
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerColorHandlerItemsTFC(ColorHandlerEvent.Item event) {
        ItemColors itemColors = event.getItemColors();

        /*itemColors.registerItemColorHandler(new IItemColor()
        {
            public int colorMultiplier(ItemStack stack, int tintIndex)
            {
                return tintIndex > 0 ? -1 : ((ItemArmorTFCF)stack.getItem()).getColor(stack);
            }
        }, ItemsTFC.getAllArmorItems().toArray(new ItemArmorTFCF[0]));*/


        itemColors.registerItemColorHandler((stack, tintIndex) ->
                        event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
                BlocksTFC.getAllFruitTreeLeavesBlocks().toArray(new TFCBlockFruitTreeLeaves[0])
        );


        /*
        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.CASSIA_CINNAMON_LEAVES);

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.CEYLON_CINNAMON_LEAVES);*/


        /*itemColors.registerItemColorHandler((stack, tintIndex) ->
                        event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
                TFCBlocks.getAllTallGrassWaterBlocks().toArray(new BlockTallGrassWaterTFC[0]));*/

        /*itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.getAllShortGrassBlocks().toArray(new BlockShortGrassTFCF[0]));

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.getAllTallGrassBlocks().toArray(new BlockTallGrassTFCF[0]));*/

        /*itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.getAllStandardBlocks().toArray(new BlockPlantTFCF[0]));

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.getAllStandardBlocks().toArray(new BlockPlantDummy1[0]));

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.getAllStandardBlocks().toArray(new BlockPlantDummy2[0]));*/

        /*itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.getAllHangingCreepingPlantBlocks().toArray(new BlockHangingCreepingPlantTFCF[0]));

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.getAllCreepingPlantBlocks().toArray(new BlockCreepingPlantTFCF[0]));*/


    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerColorHandlerBlocksTFCF(ColorHandlerEvent.Block event) {
        BlockColors blockColors = event.getBlockColors();
        IBlockColor grassColor = GrassColorHandler::computeGrassColor;
        IBlockColor foliageColor = GrassColorHandler::computeGrassColor;

        if (ConfigTFC.FloraeGeneral.WORLD.enableAllBlockTypes) {

        }

        //blockColors.registerBlockColorHandler(grassColor, TFCBlocks.getAllShortGrassBlocks().toArray(new BlockShortGrassTFC[0]));
        //blockColors.registerBlockColorHandler(grassColor, BlocksTFCF.getAllTallGrassBlocks().toArray(new BlockTallGrassTFCF[0]));

        //blockColors.registerBlockColorHandler(foliageColor, TFCBlocks.getAllFruitLeaves().toArray(new Block[0]));
        // blockColors.registerBlockColorHandler(foliageColor, TFCBlocks.getAllNormalTreeLeaves().toArray(new Block[0]));


        for (Block block : BlocksTFC.getAllBambooLeaves())
            blockColors.registerBlockColorHandler(foliageColor, block);

        //blockColors.registerBlockColorHandler(foliageColor, BlocksTFCF.CASSIA_CINNAMON_LEAVES);
        //blockColors.registerBlockColorHandler(foliageColor, BlocksTFCF.CEYLON_CINNAMON_LEAVES);

        //blockColors.registerBlockColorHandler(foliageColor, TFCBlocks.getAllWaterPlantBlocks().toArray(new BlockWaterPlantTFC[0]));
        //blockColors.registerBlockColorHandler(foliageColor, TFCBlocks.getAllHangingPlantBlocks().toArray(new BlockHangingPlantTFC[0]));
        //blockColors.registerBlockColorHandler(foliageColor, TFCBlocks.getAllHangingGlowingPlantBlocks().toArray(new BlockHangingGlowingPlantTFC[0]));
        //blockColors.registerBlockColorHandler(foliageColor, TFCBlocks.getAllHangingCreepingPlantBlocks().toArray(new BlockHangingCreepingPlantTFC[0]));
        //blockColors.registerBlockColorHandler(foliageColor, TFCBlocks.getAllHangingGlowingCreepingPlantBlocks().toArray(new BlockHangingGlowingCreepingPlantTFC[0]));
        //blockColors.registerBlockColorHandler(foliageColor, TFCBlocks.getAllCreepingPlantBlocks().toArray(new BlockCreepingPlantTFC[0]));
        //blockColors.registerBlockColorHandler(foliageColor, TFCBlocks.getAllTallGrassWaterBlocks().toArray(new BlockTallGrassWaterTFC[0]));
        //blockColors.registerBlockColorHandler(foliageColor, BlocksTFCF.getAllStandardBlocks().toArray(new BlockPlantTFCF[0]));
        //blockColors.registerBlockColorHandler(foliageColor, TFCBlocks.getAllStandardBlocks().toArray(new BlockPlantDummy1[0]));
        //blockColors.registerBlockColorHandler(foliageColor, BlocksTFCF.getAllStandardBlocks().toArray(new BlockPlantDummy2[0]));
    }

}
