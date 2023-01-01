package tfcflorae.client;

import javax.annotation.Nonnull;

import com.google.common.base.Strings;

import gregtech.api.unification.material.Material;
import net.dries007.tfc.api.capability.IMaterialHandler;
import net.dries007.tfc.compat.tfc.TFCOrePrefixExtended;
import net.dries007.tfc.compat.tfc.TFGUtils;
import net.minecraft.block.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.dries007.tfc.objects.blocks.BlockSlabTFC;
import net.dries007.tfc.objects.blocks.agriculture.BlockCropDead;
import net.dries007.tfc.client.GrassColorHandler;
import net.dries007.tfc.objects.blocks.agriculture.BlockFruitTreeLeaves;
import net.dries007.tfc.objects.blocks.wood.BlockSaplingTFC;

import tfcflorae.objects.blocks.BlocksTFCF;
import tfcflorae.objects.blocks.groundcover.*;
import tfcflorae.objects.blocks.plants.*;
import tfcflorae.objects.blocks.plants.BlockPlant.*;
import tfcflorae.objects.blocks.wood.fruitwood.*;
import tfcflorae.objects.blocks.wood.BlockFenceGateLog;
import tfcflorae.objects.blocks.wood.BlockJoshuaTreeSapling;
import tfcflorae.objects.blocks.wood.BlockLeavesTFCF;
import tfcflorae.objects.blocks.wood.BlockLogTFCF;
import tfcflorae.objects.items.ItemArmorTFCF;
import tfcflorae.objects.items.ItemFruitDoor;
import tfcflorae.objects.items.ItemsTFCF;
import tfcflorae.objects.items.ceramics.*;
import tfcflorae.ConfigTFCF;
import tfcflorae.TFCFlorae;
import tfcflorae.api.stateproperty.StatePropertiesTFCF;

import static net.dries007.tfc.objects.blocks.agriculture.BlockCropTFC.WILD;
import static tfcflorae.TFCFlorae.TFCFLORAE_MODID;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(value = {Side.CLIENT}, modid = TFCFlorae.TFCFLORAE_MODID)
public class ClientRegisterEventsTFCF 
{
    private final java.util.Map<net.minecraftforge.registries.IRegistryDelegate<Item>, IItemColor> itemColorMap = com.google.common.collect.Maps.newHashMap();

    public ClientRegisterEventsTFCF() {}

	@SubscribeEvent
    @SuppressWarnings("ConstantConditions")
    public static void registerModels(ModelRegistryEvent event)
    {
        // ITEMS

        if (ConfigTFCF.General.WORLD.enableAllEarthenwareClay)
        {
            ModelLoader.setCustomModelResourceLocation(ItemsTFCF.FIRED_EARTHENWARE_JUG, 0, new ModelResourceLocation(ItemsTFCF.FIRED_EARTHENWARE_JUG.getRegistryName(), "inventory"));
        }
        if (ConfigTFCF.General.WORLD.enableAllKaoliniteClay)
        {
            ModelLoader.setCustomModelResourceLocation(ItemsTFCF.FIRED_KAOLINITE_JUG, 0, new ModelResourceLocation(ItemsTFCF.FIRED_KAOLINITE_JUG.getRegistryName(), "inventory"));
        }
        if (ConfigTFCF.General.WORLD.enableAllStonewareClay)
        {
            ModelLoader.setCustomModelResourceLocation(ItemsTFCF.FIRED_STONEWARE_JUG, 0, new ModelResourceLocation(ItemsTFCF.FIRED_STONEWARE_JUG.getRegistryName(), "inventory"));
        }

        for (Item item : ItemsTFCF.getAllSimpleItems())
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().toString()));

        /*for (Item item : ItemsTFCF.getAllItemBows())
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().toString()));*/

        for (ItemFruitDoor item : ItemsTFCF.getAllFruitDoors())
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().toString()));

        for (EnumDyeColor color : EnumDyeColor.values())
        {
            if (ConfigTFCF.General.WORLD.enableAllEarthenwareClay)
            {
                ModelLoader.setCustomModelResourceLocation(ItemsTFCF.UNFIRED_EARTHENWARE_VESSEL_GLAZED, color.getDyeDamage(), new ModelResourceLocation(ItemsTFCF.UNFIRED_EARTHENWARE_VESSEL_GLAZED.getRegistryName().toString()));
                ModelLoader.setCustomModelResourceLocation(ItemsTFCF.FIRED_EARTHENWARE_VESSEL_GLAZED, color.getDyeDamage(), new ModelResourceLocation(ItemsTFCF.FIRED_EARTHENWARE_VESSEL_GLAZED.getRegistryName().toString()));
            }
            if (ConfigTFCF.General.WORLD.enableAllKaoliniteClay)
            {
                ModelLoader.setCustomModelResourceLocation(ItemsTFCF.UNFIRED_KAOLINITE_VESSEL_GLAZED, color.getDyeDamage(), new ModelResourceLocation(ItemsTFCF.UNFIRED_KAOLINITE_VESSEL_GLAZED.getRegistryName().toString()));
                ModelLoader.setCustomModelResourceLocation(ItemsTFCF.FIRED_KAOLINITE_VESSEL_GLAZED, color.getDyeDamage(), new ModelResourceLocation(ItemsTFCF.FIRED_KAOLINITE_VESSEL_GLAZED.getRegistryName().toString()));
            }
            if (ConfigTFCF.General.WORLD.enableAllStonewareClay)
            {
                ModelLoader.setCustomModelResourceLocation(ItemsTFCF.UNFIRED_STONEWARE_VESSEL_GLAZED, color.getDyeDamage(), new ModelResourceLocation(ItemsTFCF.UNFIRED_STONEWARE_VESSEL_GLAZED.getRegistryName().toString()));
                ModelLoader.setCustomModelResourceLocation(ItemsTFCF.FIRED_STONEWARE_VESSEL_GLAZED, color.getDyeDamage(), new ModelResourceLocation(ItemsTFCF.FIRED_STONEWARE_VESSEL_GLAZED.getRegistryName().toString()));
            }
        }

        for (ItemArmorTFCF item : ItemsTFCF.getAllArmorItems())
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().toString()));

        // BLOCKS

        for (ItemBlock itemBlock : BlocksTFCF.getAllNormalItemBlocks())
            ModelLoader.setCustomModelResourceLocation(itemBlock, 0, new ModelResourceLocation(itemBlock.getRegistryName().toString()));

        for (Block block : BlocksTFCF.getAllCoralPlants())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockCoral.LEVEL).build());

        for (Block block : BlocksTFCF.getAllGlowWaterPlants())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockWaterGlowPlant.LEVEL).build());

        if (ConfigTFCF.General.WORLD.enableGroundcoverBones)
        {
            for (Block block : BlocksTFCF.getAllSurfaceBones())
                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());
        }

        if (ConfigTFCF.General.WORLD.enableGroundcoverDriftwood)
        {
            for (Block block : BlocksTFCF.getAllSurfaceDriftwood())
                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());
        }

        if (ConfigTFCF.General.WORLD.enableGroundcoverFlint)
        {
            for (Block block : BlocksTFCF.getAllSurfaceFlint())
                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());
        }

        if (ConfigTFCF.General.WORLD.enableGroundcoverPinecone)
        {
            for (Block block : BlocksTFCF.getAllSurfacePinecone())
                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());
        }

        if (ConfigTFCF.General.WORLD.enableGroundcoverSeashell)
        {
            for (Block block : BlocksTFCF.getAllSurfaceSeashells())
                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());
        }

        if (ConfigTFCF.General.WORLD.enableGroundcoverTwig)
        {
            for (Block block : BlocksTFCF.getAllSurfaceTwig())
                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());
        }

        if (ConfigTFCF.General.WORLD.enableGroundcoverRock)
        {
            for (Block block : BlocksTFCF.getAllSurfaceRocks())
                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());
        }

        for (Block block : BlocksTFCF.getAllJoshuaTreeSaplingBlocks())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockJoshuaTreeSapling.STAGE).build());

        for (BlockFruitTreeLeaves leaves : BlocksTFCF.getAllFruitLeaves())
            ModelLoader.setCustomStateMapper(leaves, new StateMap.Builder().ignore(BlockFruitTreeLeaves.DECAYABLE).ignore(BlockFruitTreeLeaves.HARVESTABLE).build());

        for (BlockLeavesTFCF leaves : BlocksTFCF.getAllNormalTreeLeaves())
            ModelLoader.setCustomStateMapper(leaves, new StateMap.Builder().ignore(BlockLeavesTFCF.DECAYABLE).ignore(BlockLeavesTFCF.HARVESTABLE).build());

        for (BlockLogTFCF Logs : BlocksTFCF.getAllNormalTreeLog())
            ModelLoader.setCustomStateMapper(Logs, new StateMap.Builder().ignore(BlockLogTFCF.PLACED).build());

        for (Block block : BlocksTFCF.getAllCropBlocks())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(WILD).build());

        for (BlockFruitDoor door : BlocksTFCF.getAllFruitDoors())
            ModelLoader.setCustomStateMapper(door, new StateMap.Builder().ignore(BlockDoor.POWERED).build());

        for (BlockFruitFenceGate gate : BlocksTFCF.getAllFruitFenceGates())
            ModelLoader.setCustomStateMapper(gate, new StateMap.Builder().ignore(BlockFruitFenceGate.POWERED).build());

        for (BlockFruitLogFenceGate gate : BlocksTFCF.getAllFruitLogFenceGates())
            ModelLoader.setCustomStateMapper(gate, new StateMap.Builder().ignore(BlockFruitLogFenceGate.POWERED).build());

        for (BlockFenceGateLog gate : BlocksTFCF.getAllFenceGateLogBlocks())
            ModelLoader.setCustomStateMapper(gate, new StateMap.Builder().ignore(BlockFenceGateLog.POWERED).build());

        for (Block block : BlocksTFCF.getAllWallBlocks())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockWall.VARIANT).build());

        for (BlockFruitSlab.Half block : BlocksTFCF.getAllFruitSlabBlocks())
        {
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockFruitSlab.VARIANT).build());
            ModelLoader.setCustomStateMapper(block.doubleSlab, new StateMap.Builder().ignore(BlockFruitSlab.VARIANT).build());
        }

        for (Block block : BlocksTFCF.getAllFruitChestBlocks())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockChest.FACING).build());

        for (BlockSlabTFC.Half block : BlocksTFCF.getAllSlabBlocksTFC())
        {
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockSlabTFC.VARIANT).build());
            ModelLoader.setCustomStateMapper(block.doubleSlab, new StateMap.Builder().ignore(BlockSlabTFC.VARIANT).build());
        }

        for (Block block : BlocksTFCF.getAllFluidBlocks())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockFluidBase.LEVEL).build());

        for (Block block : BlocksTFCF.getAllBambooLog())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(StatePropertiesTFCF.CAN_GROW).build());

        for (Block block : BlocksTFCF.getAllBambooLeaves())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockLeaves.DECAYABLE).build());

        for (Block block : BlocksTFCF.getAllBambooSapling())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockSaplingTFC.STAGE).build());

        ModelLoader.setCustomStateMapper(BlocksTFCF.CASSIA_CINNAMON_LOG, new StateMap.Builder().ignore(StatePropertiesTFCF.CAN_GROW).build());
        ModelLoader.setCustomStateMapper(BlocksTFCF.CASSIA_CINNAMON_LEAVES, new StateMap.Builder().ignore(BlockLeaves.DECAYABLE).build());
        ModelLoader.setCustomStateMapper(BlocksTFCF.CASSIA_CINNAMON_SAPLING, new StateMap.Builder().ignore(BlockSaplingTFC.STAGE).build());

        ModelLoader.setCustomStateMapper(BlocksTFCF.CEYLON_CINNAMON_LOG, new StateMap.Builder().ignore(StatePropertiesTFCF.CAN_GROW).build());
        ModelLoader.setCustomStateMapper(BlocksTFCF.CEYLON_CINNAMON_LEAVES, new StateMap.Builder().ignore(BlockLeaves.DECAYABLE).build());
        ModelLoader.setCustomStateMapper(BlocksTFCF.CEYLON_CINNAMON_SAPLING, new StateMap.Builder().ignore(BlockSaplingTFC.STAGE).build());

        // Ceramic Molds
        for (TFCOrePrefixExtended extendedOrePrefix : TFGUtils.TFC_OREPREFIX_REGISTRY)
        {
            if (extendedOrePrefix.isHasMold())
            {
                ItemEarthenwareMold earthenwareMold = ItemEarthenwareMold.get(extendedOrePrefix.getOrePrefix());
                ItemKaoliniteMold kaoliniteMold = ItemKaoliniteMold.get(extendedOrePrefix.getOrePrefix());
                ItemStonewareMold stonewareMold = ItemStonewareMold.get(extendedOrePrefix.getOrePrefix());

                ModelBakery.registerItemVariants(earthenwareMold, new ModelResourceLocation(earthenwareMold.getRegistryName().toString() +  "_eathenware_empty"));
                ModelBakery.registerItemVariants(kaoliniteMold, new ModelResourceLocation(kaoliniteMold.getRegistryName().toString() +  "_kaolinite_empty"));
                ModelBakery.registerItemVariants(stonewareMold, new ModelResourceLocation(stonewareMold.getRegistryName().toString() +  "_stoneware_empty"));

                ModelBakery.registerItemVariants(earthenwareMold, new ModelResourceLocation(earthenwareMold.getRegistryName().toString() +  "_eathenware_filled"));
                ModelBakery.registerItemVariants(kaoliniteMold, new ModelResourceLocation(kaoliniteMold.getRegistryName().toString() +  "_kaolinite_filled"));
                ModelBakery.registerItemVariants(stonewareMold, new ModelResourceLocation(stonewareMold.getRegistryName().toString() +  "_stoneware_filled"));

                ModelLoader.setCustomMeshDefinition(earthenwareMold, new ItemMeshDefinition()
                {
                    @Override
                    @Nonnull
                    public ModelResourceLocation getModelLocation(@Nonnull ItemStack stack)
                    {
                        IFluidHandler cap = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
                        if (cap instanceof IMaterialHandler)
                        {
                            Material material = ((IMaterialHandler) cap).getMaterial();
                            if (material != null)
                            {
                                return new ModelResourceLocation(earthenwareMold.getRegistryName().toString() + "_earthenware_filled");
                            }
                        }
                        return new ModelResourceLocation(earthenwareMold.getRegistryName().toString() + "_earthenware_empty");
                    }
                });

                ModelLoader.setCustomMeshDefinition(kaoliniteMold, new ItemMeshDefinition()
                {
                    @Override
                    @Nonnull
                    public ModelResourceLocation getModelLocation(@Nonnull ItemStack stack)
                    {
                        IFluidHandler cap = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
                        if (cap instanceof IMaterialHandler)
                        {
                            Material material = ((IMaterialHandler) cap).getMaterial();
                            if (material != null)
                            {
                                return new ModelResourceLocation(kaoliniteMold.getRegistryName().toString() + "_kaolinite_filled");
                            }
                        }
                        return new ModelResourceLocation(kaoliniteMold.getRegistryName().toString() + "_kaolinite_empty");
                    }
                });

                ModelLoader.setCustomMeshDefinition(stonewareMold, new ItemMeshDefinition()
                {
                    @Override
                    @Nonnull
                    public ModelResourceLocation getModelLocation(@Nonnull ItemStack stack)
                    {
                        IFluidHandler cap = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
                        if (cap instanceof IMaterialHandler)
                        {
                            Material material = ((IMaterialHandler) cap).getMaterial();
                            if (material != null)
                            {
                                return new ModelResourceLocation(stonewareMold.getRegistryName().toString() + "_stoneware_filled");
                            }
                        }
                        return new ModelResourceLocation(stonewareMold.getRegistryName().toString() + "_stoneware_empty");
                    }
                });
            }
        }
    }

    @SuppressWarnings("deprecation")
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerColorHandlerItems(ColorHandlerEvent.Item event)
    {
        ItemColors itemColors = event.getItemColors();

        /*itemColors.registerItemColorHandler(new IItemColor()
        {
            public int colorMultiplier(ItemStack stack, int tintIndex)
            {
                return tintIndex > 0 ? -1 : ((ItemArmorTFCF)stack.getItem()).getColor(stack);
            }
        }, ItemsTFCF.getAllArmorItems().toArray(new ItemArmorTFCF[0]));*/

        itemColors.registerItemColorHandler((stack, tintIndex) ->
            tintIndex > 0 ? -1 : ((ItemArmorTFCF)stack.getItem()).getColor(stack),
            ItemsTFCF.getAllArmorItems().toArray(new ItemArmorTFCF[0]));

        if (ConfigTFCF.General.WORLD.enableAllEarthenwareClay)
        {
            itemColors.registerItemColorHandler((stack, tintIndex) -> tintIndex == 1 ? EnumDyeColor.byDyeDamage(stack.getItemDamage()).getColorValue() : 0xFFFFFF,
                ItemsTFCF.UNFIRED_EARTHENWARE_VESSEL_GLAZED, ItemsTFCF.FIRED_EARTHENWARE_VESSEL_GLAZED);
        }
        if (ConfigTFCF.General.WORLD.enableAllKaoliniteClay)
        {
            itemColors.registerItemColorHandler((stack, tintIndex) -> tintIndex == 1 ? EnumDyeColor.byDyeDamage(stack.getItemDamage()).getColorValue() : 0xFFFFFF,
                ItemsTFCF.UNFIRED_KAOLINITE_VESSEL_GLAZED, ItemsTFCF.FIRED_KAOLINITE_VESSEL_GLAZED);
        }
        if (ConfigTFCF.General.WORLD.enableAllStonewareClay)
        {
            itemColors.registerItemColorHandler((stack, tintIndex) -> tintIndex == 1 ? EnumDyeColor.byDyeDamage(stack.getItemDamage()).getColorValue() : 0xFFFFFF,
                ItemsTFCF.UNFIRED_STONEWARE_VESSEL_GLAZED, ItemsTFCF.FIRED_STONEWARE_VESSEL_GLAZED);
        }

        if (ConfigTFCF.General.WORLD.enableAllBlockTypes)
        {
        }

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.getAllFruitLeaves().toArray(new BlockFruitTreeLeaves[0])
        );

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.getAllNormalTreeLeaves().toArray(new BlockLeavesTFCF[0])
        );

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.CASSIA_CINNAMON_LEAVES);

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.CEYLON_CINNAMON_LEAVES);

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.getAllTallGrassWaterBlocks().toArray(new BlockTallGrassWater[0]));

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

        for (Item item : ItemsTFCF.getAllCeramicMoldItems())
        {
            // Colorize item molds
            itemColors.registerItemColorHandler((stack, tintIndex) -> {
                if (tintIndex != 1) return 0xFFFFFF;

                IFluidHandler cap = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
                if (cap != null)
                {
                    if (cap instanceof IMaterialHandler)
                    {
                        Material material = ((IMaterialHandler) cap).getMaterial();
                        if (material != null) {
                            return material.getMaterialRGB();
                        }
                    }
                }
                return 0xFFFFFF;
            }, item);
        }
    }

    @SideOnly(Side.CLIENT)
    private static void registerEnumBasedMetaItems(String prefix, Enum e, Item item)
    {
        //noinspection ConstantConditions
        String registryName = item.getRegistryName().getPath();
        StringBuilder path = new StringBuilder(TFCFLORAE_MODID).append(':');
        if (!Strings.isNullOrEmpty(prefix)) path.append(prefix).append('/');
        path.append(e.name());
        if (!Strings.isNullOrEmpty(prefix))
            path.append(registryName.replace(prefix, "")); // There well be a '/' at the start of registryName due to the prefix, so don't add an extra one.
        else path.append('/').append(registryName);
        ModelLoader.setCustomModelResourceLocation(item, e.ordinal(), new ModelResourceLocation(path.toString().toLowerCase()));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerColorHandlerBlocks(ColorHandlerEvent.Block event)
    {
        BlockColors blockColors = event.getBlockColors();
        IBlockColor grassColor = GrassColorHandler::computeGrassColor;
        IBlockColor foliageColor = GrassColorHandler::computeGrassColor;

        if (ConfigTFCF.General.WORLD.enableAllBlockTypes)
        {

        }

        blockColors.registerBlockColorHandler(grassColor, BlocksTFCF.getAllShortGrassBlocks().toArray(new BlockShortGrassTFCF[0]));
        //blockColors.registerBlockColorHandler(grassColor, BlocksTFCF.getAllTallGrassBlocks().toArray(new BlockTallGrassTFCF[0]));

        blockColors.registerBlockColorHandler(foliageColor, BlocksTFCF.getAllFruitLeaves().toArray(new Block[0]));
        blockColors.registerBlockColorHandler(foliageColor, BlocksTFCF.getAllNormalTreeLeaves().toArray(new Block[0]));

        for (BlockCropDead block : BlocksTFCF.getAllDeadCrops())
            blockColors.registerBlockColorHandler((state, world, os, tintIndex) -> 0xCC7400, block);
            
        for (Block block : BlocksTFCF.getAllBambooLeaves())
            blockColors.registerBlockColorHandler(foliageColor, block);

        blockColors.registerBlockColorHandler(foliageColor, BlocksTFCF.CASSIA_CINNAMON_LEAVES);
        blockColors.registerBlockColorHandler(foliageColor, BlocksTFCF.CEYLON_CINNAMON_LEAVES);
        blockColors.registerBlockColorHandler(foliageColor, BlocksTFCF.getAllWaterPlantBlocks().toArray(new BlockWaterPlantTFCF[0]));
        blockColors.registerBlockColorHandler(foliageColor, BlocksTFCF.getAllHangingPlantBlocks().toArray(new BlockHangingPlantTFCF[0]));
        blockColors.registerBlockColorHandler(foliageColor, BlocksTFCF.getAllHangingGlowingPlantBlocks().toArray(new BlockHangingGlowingPlant[0]));
        blockColors.registerBlockColorHandler(foliageColor, BlocksTFCF.getAllHangingCreepingPlantBlocks().toArray(new BlockHangingCreepingPlantTFCF[0]));
        blockColors.registerBlockColorHandler(foliageColor, BlocksTFCF.getAllHangingGlowingCreepingPlantBlocks().toArray(new BlockHangingGlowingCreepingPlant[0]));
        blockColors.registerBlockColorHandler(foliageColor, BlocksTFCF.getAllCreepingPlantBlocks().toArray(new BlockCreepingPlantTFCF[0]));
        blockColors.registerBlockColorHandler(foliageColor, BlocksTFCF.getAllTallGrassWaterBlocks().toArray(new BlockTallGrassWater[0]));
        //blockColors.registerBlockColorHandler(foliageColor, BlocksTFCF.getAllStandardBlocks().toArray(new BlockPlantTFCF[0]));
        blockColors.registerBlockColorHandler(foliageColor, BlocksTFCF.getAllStandardBlocks().toArray(new BlockPlantDummy1[0]));
        //blockColors.registerBlockColorHandler(foliageColor, BlocksTFCF.getAllStandardBlocks().toArray(new BlockPlantDummy2[0]));

        if (ConfigTFCF.General.WORLD.enableAllBlockTypes && ConfigTFCF.General.WORLD.enableAllFarmland)
        {
        }
    }
}
