package tfcflorae.client;

import com.google.common.base.Strings;

import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.objects.blocks.plants.*;
import net.dries007.tfc.objects.blocks.plants.BlockPlant.BlockPlantDummy1;
import net.minecraft.block.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.dries007.tfc.objects.blocks.stone.BlockRockSlab;
import net.dries007.tfc.client.GrassColorHandler;
import net.dries007.tfc.objects.blocks.agriculture.BlockFruitTreeLeaves;
import net.dries007.tfc.objects.blocks.wood.TFCBlockSapling;

import tfcflorae.objects.blocks.BlocksTFCF;
import net.dries007.tfc.objects.blocks.groundcover.*;
import net.dries007.tfc.objects.blocks.wood.fruitwood.*;
import net.dries007.tfc.objects.blocks.wood.TFCBlockFenceGateLog;
import net.dries007.tfc.objects.blocks.wood.joshua.BlockJoshuaTreeSapling;
import net.dries007.tfc.objects.blocks.wood.fruitwood.BlockFruitLeaves;
import net.dries007.tfc.objects.blocks.wood.fruitwood.BlockFruitLog;
import net.dries007.tfc.objects.items.wood.ItemFruitDoor;
import tfcflorae.objects.items.ItemsTFCF;
import tfcflorae.TFCFlorae;
import net.dries007.tfc.api.stateproperty.StatePropertiesTFC;

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
        for (Item item : ItemsTFCF.getAllSimpleItems())
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().toString()));

        /*for (Item item : ItemsTFCF.getAllItemBows())
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().toString()));*/

        for (ItemFruitDoor item : ItemsTFCF.getAllFruitDoors())
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().toString()));




        // BLOCKS

        for (ItemBlock itemBlock : BlocksTFCF.getAllNormalItemBlocks())
            ModelLoader.setCustomModelResourceLocation(itemBlock, 0, new ModelResourceLocation(itemBlock.getRegistryName().toString()));

        for (Block block : BlocksTFCF.getAllCoralPlants())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockCoral.LEVEL).build());

        for (Block block : BlocksTFCF.getAllGlowWaterPlants())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockWaterGlowPlant.LEVEL).build());

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverBones)
        {
            for (Block block : BlocksTFCF.getAllSurfaceBones())
                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverDriftwood)
        {
            for (Block block : BlocksTFCF.getAllSurfaceDriftwood())
                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverFlint)
        {
            for (Block block : BlocksTFCF.getAllSurfaceFlint())
                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverPinecone)
        {
            for (Block block : BlocksTFCF.getAllSurfacePinecone())
                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverSeashell)
        {
            for (Block block : BlocksTFCF.getAllSurfaceSeashells())
                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverTwig)
        {
            for (Block block : BlocksTFCF.getAllSurfaceTwig())
                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());
        }

        if (ConfigTFC.FloraeGeneral.WORLD.enableGroundcoverRock)
        {
            for (Block block : BlocksTFCF.getAllSurfaceRocks())
                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().build());
        }

        for (Block block : BlocksTFCF.getAllJoshuaTreeSaplingBlocks())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockJoshuaTreeSapling.STAGE).build());

        for (BlockFruitTreeLeaves leaves : BlocksTFCF.getAllFruitLeaves())
            ModelLoader.setCustomStateMapper(leaves, new StateMap.Builder().ignore(BlockFruitTreeLeaves.DECAYABLE).ignore(BlockFruitTreeLeaves.HARVESTABLE).build());

        for (BlockFruitLeaves leaves : BlocksTFCF.getAllNormalTreeLeaves())
            ModelLoader.setCustomStateMapper(leaves, new StateMap.Builder().ignore(BlockFruitLeaves.DECAYABLE).ignore(BlockFruitLeaves.HARVESTABLE).build());

        for (BlockFruitLog Logs : BlocksTFCF.getAllNormalTreeLog())
            ModelLoader.setCustomStateMapper(Logs, new StateMap.Builder().ignore(BlockFruitLog.PLACED).build());

        for (BlockFruitDoor door : BlocksTFCF.getAllFruitDoors())
            ModelLoader.setCustomStateMapper(door, new StateMap.Builder().ignore(BlockDoor.POWERED).build());

        for (BlockFruitFenceGate gate : BlocksTFCF.getAllFruitFenceGates())
            ModelLoader.setCustomStateMapper(gate, new StateMap.Builder().ignore(BlockFruitFenceGate.POWERED).build());

        for (BlockFruitLogFenceGate gate : BlocksTFCF.getAllFruitLogFenceGates())
            ModelLoader.setCustomStateMapper(gate, new StateMap.Builder().ignore(BlockFruitLogFenceGate.POWERED).build());

        for (TFCBlockFenceGateLog gate : BlocksTFCF.getAllFenceGateLogBlocks())
            ModelLoader.setCustomStateMapper(gate, new StateMap.Builder().ignore(TFCBlockFenceGateLog.POWERED).build());

        for (BlockFruitSlab.Half block : BlocksTFCF.getAllFruitSlabBlocks())
        {
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockFruitSlab.VARIANT).build());
            ModelLoader.setCustomStateMapper(block.doubleSlab, new StateMap.Builder().ignore(BlockFruitSlab.VARIANT).build());
        }

        for (Block block : BlocksTFCF.getAllFruitChestBlocks())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockChest.FACING).build());

        for (BlockRockSlab.Half block : BlocksTFCF.getAllSlabBlocksTFC())
        {
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockRockSlab.VARIANT).build());
            ModelLoader.setCustomStateMapper(block.doubleSlab, new StateMap.Builder().ignore(BlockRockSlab.VARIANT).build());
        }
        for (Block block : BlocksTFCF.getAllBambooLog())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(StatePropertiesTFC.CAN_GROW).build());

        for (Block block : BlocksTFCF.getAllBambooLeaves())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockLeaves.DECAYABLE).build());

        for (Block block : BlocksTFCF.getAllBambooSapling())
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
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.getAllFruitLeaves().toArray(new BlockFruitTreeLeaves[0])
        );

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.getAllNormalTreeLeaves().toArray(new BlockFruitLeaves[0])
        );

        /*
        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.CASSIA_CINNAMON_LEAVES);

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFCF.CEYLON_CINNAMON_LEAVES);*/


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

        if (ConfigTFC.FloraeGeneral.WORLD.enableAllBlockTypes)
        {

        }

        blockColors.registerBlockColorHandler(grassColor, BlocksTFCF.getAllShortGrassBlocks().toArray(new BlockShortGrassTFCF[0]));
        //blockColors.registerBlockColorHandler(grassColor, BlocksTFCF.getAllTallGrassBlocks().toArray(new BlockTallGrassTFCF[0]));

        blockColors.registerBlockColorHandler(foliageColor, BlocksTFCF.getAllFruitLeaves().toArray(new Block[0]));
        blockColors.registerBlockColorHandler(foliageColor, BlocksTFCF.getAllNormalTreeLeaves().toArray(new Block[0]));

            
        for (Block block : BlocksTFCF.getAllBambooLeaves())
            blockColors.registerBlockColorHandler(foliageColor, block);

        //blockColors.registerBlockColorHandler(foliageColor, BlocksTFCF.CASSIA_CINNAMON_LEAVES);
        //blockColors.registerBlockColorHandler(foliageColor, BlocksTFCF.CEYLON_CINNAMON_LEAVES);

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

        if (ConfigTFC.FloraeGeneral.WORLD.enableAllBlockTypes && ConfigTFC.FloraeGeneral.WORLD.enableAllFarmland)
        {
        }
    }
}
