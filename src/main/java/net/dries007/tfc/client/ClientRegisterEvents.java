/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.client;

import com.ferreusveritas.dynamictrees.blocks.BlockRooty;
import com.ferreusveritas.dynamictrees.blocks.LeavesPaging;
import com.ferreusveritas.dynamictrees.items.Seed;
import com.google.common.collect.ImmutableMap;
import gregtech.api.unification.material.Material;
import net.dries007.tfc.TFCConfig;
import net.dries007.tfc.api.capability.IMaterialHandler;
import net.dries007.tfc.api.capability.food.CapabilityFood;
import net.dries007.tfc.api.capability.food.IFood;
import net.dries007.tfc.api.types.Rock.Type;
import net.dries007.tfc.api.util.IWoodHandler;
import net.dries007.tfc.client.render.*;
import net.dries007.tfc.compat.dynamictrees.DTTrees;
import net.dries007.tfc.compat.dynamictrees.client.DTModelHelper;
import net.dries007.tfc.compat.tfc.TFCOrePrefixExtended;
import net.dries007.tfc.compat.tfc.TFGUtils;
import net.dries007.tfc.objects.blocks.TFCBlockThatchBed;
import net.dries007.tfc.objects.blocks.TFCBlocks;
import net.dries007.tfc.objects.blocks.agriculture.TFCBlockFruitTreeLeaves;
import net.dries007.tfc.objects.blocks.plants.*;
import net.dries007.tfc.objects.blocks.rock.TFCBlockRockRaw;
import net.dries007.tfc.objects.blocks.rock.TFCBlockRockSlab;
import net.dries007.tfc.objects.blocks.rock.TFCBlockRockSmooth;
import net.dries007.tfc.objects.blocks.rock.TFCBlockRockVariant;
import net.dries007.tfc.objects.blocks.rock.farmland.*;
import net.dries007.tfc.objects.blocks.wood.*;
import net.dries007.tfc.objects.blocks.wood.tree.TFCBlockLeaves;
import net.dries007.tfc.objects.blocks.wood.tree.TFCBlockLog;
import net.dries007.tfc.objects.blocks.wood.tree.TFCBlockSapling;
import net.dries007.tfc.objects.items.TFCItemAnimalHide;
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
import net.minecraftforge.client.event.ModelBakeEvent;
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
import static net.dries007.tfc.objects.blocks.TFCBlockPlacedHide.SIZE;

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
    public static void bakingModels(ModelBakeEvent event)
    {
        TFCBlocks.blockRootyDirt.onModelBake(event);
    }

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

        //=== Register blockstates for Blocks ========================================================================//

        TFCBlocks.getAllNormalItemBlocks().forEach(s -> ModelLoader.setCustomModelResourceLocation(s, 0, new ModelResourceLocation(s.getRegistryName(), "normal")));
        TFCBlocks.getAllInventoryItemBlocks().forEach(s -> ModelLoader.setCustomModelResourceLocation(s, 0, new ModelResourceLocation(s.getRegistryName(), "inventory")));

        //=== Agriculture ============================================================================================//

//        TFCBlocks.getAllBlockCrop().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().ignore(WILD).build()));
        TFCBlocks.getAllBlockFruitTreeLeaves().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().ignore(TFCBlockFruitTreeLeaves.DECAYABLE).ignore(TFCBlockFruitTreeLeaves.HARVESTABLE).build()));

        //=== Groundcover ============================================================================================//

//        if (TFCConfig.FloraeGeneral.WORLD.enableCoralWorldGen) {TFCBlocks.getAllBlockCoral().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().ignore(TFCBlockCoral.LEVEL).build()));}
        if (TFCConfig.FloraeGeneral.WORLD.enableLightstoneWorldGen) {TFCBlocks.getAllBlockLightstone().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().build()));}
        if (TFCConfig.FloraeGeneral.WORLD.enableGroundcoverBones) { TFCBlocks.getAllBlockSurfaceBones().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().build()));}
        if (TFCConfig.FloraeGeneral.WORLD.enableGroundcoverDriftwood) { TFCBlocks.getAllBlockDriftwood().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().build()));}
        if (TFCConfig.FloraeGeneral.WORLD.enableGroundcoverFlint) { TFCBlocks.getAllBlockSurfaceFlint().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().build()));}
        if (TFCConfig.FloraeGeneral.WORLD.enableGroundcoverPinecone) { TFCBlocks.getAllBlockSurfacePinecone().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().build()));}
        if (TFCConfig.FloraeGeneral.WORLD.enableGroundcoverRock) { TFCBlocks.getAllBlockSurfaceRock().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().build()));}
        if (TFCConfig.FloraeGeneral.WORLD.enableGroundcoverSeashell) { TFCBlocks.getAllBlockSurfaceSeashells().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().build()));}
        if (TFCConfig.FloraeGeneral.WORLD.enableGroundcoverTwig) { TFCBlocks.getAllBlockSurfaceTwig().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().build()));}


        //=== Rock ===================================================================================================//

        TFCBlocks.getAllBlockRockSlab().forEach(s -> {
            ModelLoader.setCustomStateMapper(s, new StateMap.Builder().ignore(TFCBlockRockSlab.VARIANT).build());
            ModelLoader.setCustomStateMapper(s.doubleSlab, new StateMap.Builder().ignore(TFCBlockRockSlab.VARIANT).build());
        });
        TFCBlocks.getAllBlockRockVariant().forEach(e -> {
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
                    ModelLoader.setCustomStateMapper(e, new StateMap.Builder().ignore(TFCBlockFarmlandLoamySand.MOISTURE).build());
                    break;
                case SANDY_LOAM_FARMLAND:
                    ModelLoader.setCustomStateMapper(e, new StateMap.Builder().ignore(TFCBlockFarmlandSandyLoam.MOISTURE).build());
                    break;
                case LOAM_FARMLAND:
                    ModelLoader.setCustomStateMapper(e, new StateMap.Builder().ignore(TFCBlockFarmlandLoam.MOISTURE).build());
                    break;
                case SILT_LOAM_FARMLAND:
                    ModelLoader.setCustomStateMapper(e, new StateMap.Builder().ignore(TFCBlockFarmlandSiltLoam.MOISTURE).build());
                    break;
                case SILT_FARMLAND:
                    ModelLoader.setCustomStateMapper(e, new StateMap.Builder().ignore(TFCBlockFarmlandSilt.MOISTURE).build());
                    break;
                case HUMUS_FARMLAND:
                    ModelLoader.setCustomStateMapper(e, new StateMap.Builder().ignore(TFCBlockFarmlandHumus.MOISTURE).build());
                    break;
            }
        });
        TFCBlocks.getAllBlockRockWall().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().ignore(BlockWall.VARIANT).build()));


        //=== Tree ===================================================================================================//

        TFCBlocks.getAllBlockLeaves().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().ignore(BlockLeaves.DECAYABLE).build()));
        TFCBlocks.getAllBlockLog().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().ignore(TFCBlockLog.PLACED).build()));
        TFCBlocks.getAllBlockSapling().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().ignore(TFCBlockSapling.STAGE).build()));

        // Register meshes for tree branches
        DTTrees.TFCTrees.forEach(s -> {
            // Register Branch itemBlock
            DTModelHelper.regModel(s.getDynamicBranch());
            // Register custom state mapper for branch
            DTModelHelper.regModel(s);
        });

        DTTrees.TFCSpecies.values().stream().filter(s -> s.getSeed() != Seed.NULLSEED).forEach(s -> DTModelHelper.regModel(s.getSeed()));// Register Seed Item Models

        //=== Wood ===================================================================================================//

        TFCBlocks.getAllBlockBarrel().forEach(TFCBlockBarrel::onModelRegister);
        TFCBlocks.getAllBlockBookshelf().forEach(TFCBlockBookshelf::onModelRegister);
//        TFCBlocks.getAllBlockChest().forEach(TFCBlockChest::onModelRegister);
        TFCBlocks.getAllBlockChest().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().ignore(BlockChest.FACING).build()));
        TFCBlocks.getAllBlockFence().forEach(TFCBlockFence::onModelRegister);
        TFCBlocks.getAllBlockFenceGate().forEach(TFCBlockFenceGate::onModelRegister);
//        TFCBlocks.getAllBlockFenceGateLog().forEach(TFCBlockFenceGateLog::onModelRegister);
        TFCBlocks.getAllBlockFenceGateLog().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().ignore(TFCBlockFenceGateLog.POWERED).build()));
        TFCBlocks.getAllBlockLoom().forEach(TFCBlockLoom::onModelRegister);
        TFCBlocks.getAllBlockPlank().forEach(TFCBlockPlank::onModelRegister);
        TFCBlocks.getAllBlockToolRack().forEach(TFCBlockToolRack::onModelRegister);
        TFCBlocks.getAllBlockWoodButton().forEach(TFCBlockWoodButton::onModelRegister);
//        TFCBlocks.getAllBlockWoodDoor().forEach(TFCBlockWoodDoor::onModelRegister);
        TFCBlocks.getAllBlockWoodDoor().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().ignore(BlockDoor.POWERED).build()));
        TFCBlocks.getAllBlockWoodPressurePlate().forEach(TFCBlockWoodPressurePlate::onModelRegister);
        TFCBlocks.getAllBlockWoodSlab().forEach(TFCBlockWoodSlab.Half::onModelRegister);
        TFCBlocks.getAllBlockWoodStairs().forEach(TFCBlockWoodStairs::onModelRegister);
        TFCBlocks.getAllBlockWoodSupport().forEach(TFCBlockWoodSupport::onModelRegister);
//        TFCBlocks.getAllBlockWoodTrapDoor().forEach(TFCBlockWoodTrapDoor::onModelRegister);
        TFCBlocks.getAllBlockWorkbench().forEach(TFCBlockWorkbench::onModelRegister);

        //=== Other ==================================================================================================//

        TFCBlocks.getAllBlockFluidBase().forEach(s -> ModelLoader.setCustomStateMapper(s, new StateMap.Builder().ignore(BlockFluidBase.LEVEL).build()));



        // BLOCKS - STATE MAPPERS //
        // Blocks with Ignored Properties

        ModelLoader.setCustomStateMapper(TFCBlocks.blockRootyDirt, new StateMap.Builder().ignore(BlockRooty.LIFE).build());

        ModelLoader.setCustomStateMapper(TFCBlocks.THATCH_BED, new StateMap.Builder().ignore(TFCBlockThatchBed.OCCUPIED).build());

        // Empty Models
        final ModelResourceLocation empty = new ModelResourceLocation(MOD_ID + ":empty");
        // todo: switch to hide rack (involves changing mechanics, etc)
        final ModelResourceLocation hideRack = new ModelResourceLocation(MOD_ID + ":hide_rack");

        ModelLoader.setCustomStateMapper(TFCBlocks.PIT_KILN, blockIn -> ImmutableMap.of(TFCBlocks.PIT_KILN.getDefaultState(), empty));
        ModelLoader.setCustomStateMapper(TFCBlocks.PLACED_ITEM_FLAT, blockIn -> ImmutableMap.of(TFCBlocks.PLACED_ITEM_FLAT.getDefaultState(), empty));
        ModelLoader.setCustomStateMapper(TFCBlocks.PLACED_ITEM, blockIn -> ImmutableMap.of(TFCBlocks.PLACED_ITEM.getDefaultState(), empty));
        ModelLoader.setCustomStateMapper(TFCBlocks.PLACED_HIDE, blockIn -> ImmutableMap.of(TFCBlocks.PLACED_HIDE.getDefaultState().withProperty(SIZE, TFCItemAnimalHide.HideSize.SMALL), empty, TFCBlocks.PLACED_HIDE.getDefaultState().withProperty(SIZE, TFCItemAnimalHide.HideSize.MEDIUM), empty, TFCBlocks.PLACED_HIDE.getDefaultState().withProperty(SIZE, TFCItemAnimalHide.HideSize.LARGE), empty));

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
        ClientRegistry.bindTileEntitySpecialRenderer(TEAnvil.class, new TESRAnvil());
        ClientRegistry.bindTileEntitySpecialRenderer(TELoom.class, new TESRLoom());
        ClientRegistry.bindTileEntitySpecialRenderer(TECrucible.class, new TESRCrucible());
        ClientRegistry.bindTileEntitySpecialRenderer(TEFirePit.class, new TESRFirePit());
        ClientRegistry.bindTileEntitySpecialRenderer(TESluice.class, new TESRSluice());
        ClientRegistry.bindTileEntitySpecialRenderer(TEDryer.class, new TESRDryer());


        // ITEMS


        /*for (Item item : ItemsTFC.getAllItemBows())
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().toString()));*/


        // BLOCKS

//        for (Block block : TFCBlocks.getAllBlockWaterGlowPlant())
//            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(TFCBlockWaterGlowPlant.LEVEL).build());



        //for (Block block : TFCBlocks.getAllJoshuaTreeSaplingBlocks())
        //ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockJoshuaTreeSapling.STAGE).build());

//        for (Block block : TFCBlocks.getAllBambooLog())
//            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(StatePropertiesTFC.CAN_GROW).build());
//
//        for (Block block : TFCBlocks.getAllBambooLeaves())
//            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockLeaves.DECAYABLE).build());
//
//        for (Block block : TFCBlocks.getAllBambooSapling())
//            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(TFCBlockSapling.STAGE).build());


//        ModelLoader.setCustomStateMapper(BlocksTFCF.CASSIA_CINNAMON_LOG, new StateMap.Builder().ignore(StatePropertiesTFC.CAN_GROW).build());
//        ModelLoader.setCustomStateMapper(BlocksTFCF.CASSIA_CINNAMON_LEAVES, new StateMap.Builder().ignore(BlockLeaves.DECAYABLE).build());
//        ModelLoader.setCustomStateMapper(BlocksTFCF.CASSIA_CINNAMON_SAPLING, new StateMap.Builder().ignore(BlockSaplingTFC.STAGE).build());
//
//        ModelLoader.setCustomStateMapper(BlocksTFCF.CEYLON_CINNAMON_LOG, new StateMap.Builder().ignore(StatePropertiesTFC.CAN_GROW).build());
//        ModelLoader.setCustomStateMapper(BlocksTFCF.CEYLON_CINNAMON_LEAVES, new StateMap.Builder().ignore(BlockLeaves.DECAYABLE).build());
//        ModelLoader.setCustomStateMapper(BlocksTFCF.CEYLON_CINNAMON_SAPLING, new StateMap.Builder().ignore(BlockSaplingTFC.STAGE).build());

    }


    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerColorHandlerBlocks(ColorHandlerEvent.Block event) {
        BlockColors blockColor = event.getBlockColors();

        IBlockColor grassColor = GrassColorHandler::computeGrassColor;
        // todo: do something different for conifers - they should have a different color mapping through the seasons
        IBlockColor foliageColor = GrassColorHandler::computeGrassColor;

        //=== Agriculture and other green things ====================================================================//

        TFCBlocks.getAllBlockCropDead().forEach(s -> blockColor.registerBlockColorHandler((state, world, os, tintIndex) -> 0xCC7400, s));

        // blockColor.registerBlockColorHandler(foliageColor, TFCBlocks.getAllBlockFruitTreeLeaves().toArray(new Block[0]));

        blockColor.registerBlockColorHandler(grassColor, TFCBlocks.getAllBlockRockVariant().stream()
                .filter(x -> x.getType().isGrass)
                .toArray(TFCBlockRockVariant[]::new));

        blockColor.registerBlockColorHandler((state, worldIn, pos, tintIndex) -> TFCBlockFarmland.TINT[state.getValue(TFCBlockFarmland.MOISTURE)],
                TFCBlocks.getAllBlockRockVariant().stream()
                        .filter(x -> x.getType() == Type.FARMLAND)
                        .toArray(TFCBlockRockVariant[]::new));

        blockColor.registerBlockColorHandler((state, worldIn, pos, tintIndex) -> TFCBlockFarmlandLoamySand.TINT[state.getValue(TFCBlockFarmlandLoamySand.MOISTURE)],
                TFCBlocks.getAllBlockRockVariant().stream()
                        .filter(x -> x.getType() == Type.LOAMY_SAND_FARMLAND)
                        .toArray(TFCBlockRockVariant[]::new));

        blockColor.registerBlockColorHandler((state, worldIn, pos, tintIndex) -> TFCBlockFarmlandSandyLoam.TINT[state.getValue(TFCBlockFarmlandSandyLoam.MOISTURE)],
                TFCBlocks.getAllBlockRockVariant().stream()
                        .filter(x -> x.getType() == Type.SANDY_LOAM_FARMLAND)
                        .toArray(TFCBlockRockVariant[]::new));

        blockColor.registerBlockColorHandler((state, worldIn, pos, tintIndex) -> TFCBlockFarmlandLoam.TINT[state.getValue(TFCBlockFarmlandLoam.MOISTURE)],
                TFCBlocks.getAllBlockRockVariant().stream()
                        .filter(x -> x.getType() == Type.LOAM_FARMLAND)
                        .toArray(TFCBlockRockVariant[]::new));

        blockColor.registerBlockColorHandler((state, worldIn, pos, tintIndex) -> TFCBlockFarmlandSiltLoam.TINT[state.getValue(TFCBlockFarmlandSiltLoam.MOISTURE)],
                TFCBlocks.getAllBlockRockVariant().stream()
                        .filter(x -> x.getType() == Type.SILT_LOAM_FARMLAND)
                        .toArray(TFCBlockRockVariant[]::new));

        blockColor.registerBlockColorHandler((state, worldIn, pos, tintIndex) -> TFCBlockFarmlandSilt.TINT[state.getValue(TFCBlockFarmlandSilt.MOISTURE)],
                TFCBlocks.getAllBlockRockVariant().stream()
                        .filter(x -> x.getType() == Type.SILT_FARMLAND)
                        .toArray(TFCBlockRockVariant[]::new));

        blockColor.registerBlockColorHandler((state, worldIn, pos, tintIndex) -> TFCBlockFarmlandHumus.TINT[state.getValue(TFCBlockFarmlandHumus.MOISTURE)],
                TFCBlocks.getAllBlockRockVariant().stream()
                        .filter(x -> x.getType() == Type.HUMUS_FARMLAND)
                        .toArray(TFCBlockRockVariant[]::new));

        blockColor.registerBlockColorHandler(grassColor, TFCBlocks.PEAT_GRASS);

        blockColor.registerBlockColorHandler(grassColor, TFCBlocks.blockRootyDirt);

        //=== Flora =================================================================================================//

        blockColor.registerBlockColorHandler(foliageColor, TFCBlocks.getAllBlockPlant().toArray(new TFCBlockPlant[0]));

        blockColor.registerBlockColorHandler(foliageColor, TFCBlocks.getAllBlockHangingPlant().toArray(new TFCBlockHangingPlant[0]));
        blockColor.registerBlockColorHandler(foliageColor, TFCBlocks.getAllBlockHangingGlowingPlant().toArray(new TFCBlockHangingGlowingPlant[0]));
        blockColor.registerBlockColorHandler(foliageColor, TFCBlocks.getAllBlockHangingCreepingPlant().toArray(new TFCBlockHangingCreepingPlant[0]));
        blockColor.registerBlockColorHandler(foliageColor, TFCBlocks.getAllBlockHangingGlowingCreepingPlant().toArray(new TFCBlockHangingGlowingCreepingPlant[0]));

        // blockColor.registerBlockColorHandler(grassColor, TFCBlocks.getAllBlockShortGrass().toArray(new TFCBlockShortGrass[0]));
        // blockColor.registerBlockColorHandler(grassColor, TFCBlocks.getAllBlockTallGrass().toArray(new TFCBlockTallGrass[0]));
        // blockColors.registerBlockColorHandler(grassColor, TFCBlocks.getAllGrass().toArray(new TFCBlockPlant[0]));
        // blockColors.registerBlockColorHandler(foliageColor, TFCBlocks.getAllFlowerPots().toArray(new Block[0]));
        // blockColors.registerBlockColorHandler(foliageColor, TFCBlocks.getAllWaterPlantBlocks().toArray(new BlockWaterPlantTFC[0]));

        // blockColors.registerBlockColorHandler(foliageColor, TFCBlocks.getAllCreepingPlantBlocks().toArray(new BlockCreepingPlantTFC[0]));
        // blockColors.registerBlockColorHandler(foliageColor, TFCBlocks.getAllTallGrassWaterBlocks().toArray(new BlockTallGrassWaterTFC[0]));

        //=== Tree ===================================================================================================//

        blockColor.registerBlockColorHandler(foliageColor, TFCBlocks.getAllBlockLeaves().toArray(new Block[0]));
        blockColor.registerBlockColorHandler(foliageColor, LeavesPaging.getLeavesMapForModId(MOD_ID).values().toArray(new Block[0]));

        //=== Wood ===================================================================================================//

        TFCBlocks.getAllBlockBarrel().forEach(s -> blockColor.registerBlockColorHandler(woodBlockColors, s));
        TFCBlocks.getAllBlockBookshelf().forEach(s -> blockColor.registerBlockColorHandler(woodBlockColors, s));
        // TFCBlocks.getAllBlockChest().forEach(s -> blockColor.registerBlockColorHandler(woodBlockColors, s));
        TFCBlocks.getAllBlockFence().forEach(s -> blockColor.registerBlockColorHandler(woodBlockColors, s));
        TFCBlocks.getAllBlockFenceGate().forEach(s -> blockColor.registerBlockColorHandler(woodBlockColors, s));
        // TFCBlocks.getAllBlockFenceGateLog().forEach(s -> blockColor.registerBlockColorHandler(woodBlockColors, s));
        TFCBlocks.getAllBlockLoom().forEach(s -> blockColor.registerBlockColorHandler(woodBlockColors, s));
        TFCBlocks.getAllBlockPlank().forEach(s -> blockColor.registerBlockColorHandler(woodBlockColors, s));
        TFCBlocks.getAllBlockToolRack().forEach(s -> blockColor.registerBlockColorHandler(woodBlockColors, s));
        TFCBlocks.getAllBlockWoodButton().forEach(s -> blockColor.registerBlockColorHandler(woodBlockColors, s));
        // TFCBlocks.getAllBlockWoodDoor().forEach(s -> blockColor.registerBlockColorHandler(woodBlockColors, s));
        TFCBlocks.getAllBlockWoodPressurePlate().forEach(s -> blockColor.registerBlockColorHandler(woodBlockColors, s));
        TFCBlocks.getAllBlockWoodSlab().forEach(s -> blockColor.registerBlockColorHandler(woodBlockColors, s, s.doubleSlab));
        TFCBlocks.getAllBlockWoodStairs().forEach(s -> blockColor.registerBlockColorHandler(woodBlockColors, s));
        TFCBlocks.getAllBlockWoodSupport().forEach(s -> blockColor.registerBlockColorHandler(woodBlockColors, s));
        // TFCBlocks.getAllBlockWoodTrapDoor().forEach(s -> blockColor.registerBlockColorHandler(woodBlockColors, s));
        TFCBlocks.getAllBlockWorkbench().forEach(s -> blockColor.registerBlockColorHandler(woodBlockColors, s));


        // blockColors.registerBlockColorHandler(foliageColor, TFCBlocks.getAllFruitLeaves().toArray(new Block[0]));
        // blockColors.registerBlockColorHandler(foliageColor, TFCBlocks.getAllNormalTreeLeaves().toArray(new Block[0]));


        // for (Block block : TFCBlocks.getAllBambooLeaves())
        //    blockColors.registerBlockColorHandler(foliageColor, block);

        //blockColors.registerBlockColorHandler(foliageColor, TFCBlocks.CASSIA_CINNAMON_LEAVES);
        //blockColors.registerBlockColorHandler(foliageColor, TFCBlocks.CEYLON_CINNAMON_LEAVES);


        // blockColors.registerBlockColorHandler(foliageColor, TFCBlocks.getAllStandardBlocks().toArray(new BlockPlantTFCF[0]));
        // blockColors.registerBlockColorHandler(foliageColor, TFCBlocks.getAllStandardBlocks().toArray(new BlockPlantDummy1[0]));
        // blockColors.registerBlockColorHandler(foliageColor, TFCBlocks.getAllStandardBlocks().toArray(new BlockPlantDummy2[0]));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerColorHandlerItems(ColorHandlerEvent.Item event) {
        ItemColors itemColors = event.getItemColors();

        //=== Wood ===================================================================================================//

        TFCBlocks.getAllBlockBarrel().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        TFCBlocks.getAllBlockBookshelf().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        // TFCBlocks.getAllBlockChest().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        TFCBlocks.getAllBlockFence().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        TFCBlocks.getAllBlockFenceGate().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        // TFCBlocks.getAllBlockFenceGateLog().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        TFCBlocks.getAllBlockLoom().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        TFCBlocks.getAllBlockPlank().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        TFCBlocks.getAllBlockToolRack().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        TFCBlocks.getAllBlockWoodButton().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        // TFCBlocks.getAllBlockWoodDoor().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        TFCBlocks.getAllBlockWoodPressurePlate().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        TFCBlocks.getAllBlockWoodSlab().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        TFCBlocks.getAllBlockWoodStairs().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        TFCBlocks.getAllBlockWoodSupport().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        // TFCBlocks.getAllBlockWoodTrapDoor().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));
        TFCBlocks.getAllBlockWorkbench().forEach(s -> itemColors.registerItemColorHandler(woodItemBlockColors, Item.getItemFromBlock(s)));

        TFCItems.getAllLumberItems().forEach(s -> itemColors.registerItemColorHandler(woodItemColors, s));
        TFCItems.getAllBoatItems().forEach(s -> itemColors.registerItemColorHandler(woodItemColors, s));

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                        tintIndex > 0 ? -1 : ((TFCItemArmor) stack.getItem()).getColor(stack),
                TFCItems.getAllArmorItems().toArray(new TFCItemArmor[0]));

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                        event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
                TFCBlocks.getAllBlockRockVariant().stream().filter(x -> x.getType().isGrass).toArray(TFCBlockRockVariant[]::new));

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                        event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
                TFCBlocks.PEAT_GRASS);

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                        event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
                TFCBlocks.getAllBlockLeaves().toArray(new TFCBlockLeaves[0]));

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                        event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
                TFCBlocks.getAllBlockFruitTreeLeaves().toArray(new TFCBlockFruitTreeLeaves[0]));

        itemColors.registerItemColorHandler((stack, tintIndex) -> tintIndex == 1 ? EnumDyeColor.byDyeDamage(stack.getItemDamage()).getColorValue() : 0xFFFFFF,
                TFCItems.UNFIRED_VESSEL_GLAZED, TFCItems.FIRED_VESSEL_GLAZED);

        itemColors.registerItemColorHandler((stack, tintIndex) -> tintIndex == 1 ? EnumDyeColor.byDyeDamage(stack.getItemDamage()).getColorValue() : 0xFFFFFF,
                TFCItems.UNFIRED_EARTHENWARE_VESSEL_GLAZED, TFCItems.FIRED_EARTHENWARE_VESSEL_GLAZED);

        itemColors.registerItemColorHandler((stack, tintIndex) -> tintIndex == 1 ? EnumDyeColor.byDyeDamage(stack.getItemDamage()).getColorValue() : 0xFFFFFF,
                TFCItems.UNFIRED_KAOLINITE_VESSEL_GLAZED, TFCItems.FIRED_KAOLINITE_VESSEL_GLAZED);

        itemColors.registerItemColorHandler((stack, tintIndex) -> tintIndex == 1 ? EnumDyeColor.byDyeDamage(stack.getItemDamage()).getColorValue() : 0xFFFFFF,
                TFCItems.UNFIRED_STONEWARE_VESSEL_GLAZED, TFCItems.FIRED_STONEWARE_VESSEL_GLAZED);

        /*
        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            TFCBlocks.getAllGrassBlocks().toArray(new TFCBlockPlant[0]));
        */

        // Food
        itemColors.registerItemColorHandler((stack, tintIndex) -> {
            IFood food = stack.getCapability(CapabilityFood.CAPABILITY, null);
            if (food != null) {
                return food.isRotten() ? TFCConfig.Client.DISPLAY.rottenFoodOverlayColor : 0xFFFFFF;
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

        /*
        itemColors.registerItemColorHandler(new IItemColor()
        {
            public int colorMultiplier(ItemStack stack, int tintIndex)
            {
                return tintIndex > 0 ? -1 : ((ItemArmorTFCF)stack.getItem()).getColor(stack);
            }
        }, ItemsTFC.getAllArmorItems().toArray(new ItemArmorTFCF[0]));
        */

        // itemColors.registerItemColorHandler((stack, tintIndex) ->
        //                 event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
        //         TFCBlocks.getAllFruitLeaves().toArray(new TFCBlockFruitTreeLeaves[0])
        // );

        /*
        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.CASSIA_CINNAMON_LEAVES);

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.CEYLON_CINNAMON_LEAVES);
        */


        /*
        itemColors.registerItemColorHandler((stack, tintIndex) ->
                        event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
                TFCBlocks.getAllTallGrassWaterBlocks().toArray(new BlockTallGrassWaterTFC[0]));
        */

        /*
        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.getAllShortGrassBlocks().toArray(new BlockShortGrassTFCF[0]));

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.getAllTallGrassBlocks().toArray(new BlockTallGrassTFCF[0]));
        */

        /*
        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.getAllStandardBlocks().toArray(new BlockPlantTFCF[0]));

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.getAllStandardBlocks().toArray(new BlockPlantDummy1[0]));

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.getAllStandardBlocks().toArray(new BlockPlantDummy2[0]));
        */

        /*
        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.getAllHangingCreepingPlantBlocks().toArray(new BlockHangingCreepingPlantTFCF[0]));

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.getAllCreepingPlantBlocks().toArray(new BlockCreepingPlantTFCF[0]));
        */
    }
}
