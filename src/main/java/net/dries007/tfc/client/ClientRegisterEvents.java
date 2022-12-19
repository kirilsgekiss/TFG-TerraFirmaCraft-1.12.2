/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.client;

import javax.annotation.Nonnull;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import gregtech.api.unification.material.Material;
import net.dries007.tfc.compat.tfc.TFCOrePrefixExtended;
import net.dries007.tfc.compat.tfc.TFGUtils;
import net.dries007.tfc.api.capability.IMaterialHandler;
import net.dries007.tfc.objects.items.ceramics.ItemMold;
import net.dries007.tfc.objects.items.metal.ItemAnvil;
import net.minecraft.block.*;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.*;
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

import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.api.capability.food.CapabilityFood;
import net.dries007.tfc.api.capability.food.IFood;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.client.render.*;
import net.dries007.tfc.objects.blocks.BlockSlabTFC;
import net.dries007.tfc.objects.blocks.BlockThatchBed;
import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.objects.blocks.agriculture.BlockFruitTreeLeaves;
import net.dries007.tfc.objects.blocks.plants.BlockPlantTFC;
import net.dries007.tfc.objects.blocks.stone.*;
import net.dries007.tfc.objects.blocks.wood.BlockLeavesTFC;
import net.dries007.tfc.objects.blocks.wood.BlockLogTFC;
import net.dries007.tfc.objects.blocks.wood.BlockSaplingTFC;
import net.dries007.tfc.objects.items.ItemAnimalHide;
import net.dries007.tfc.objects.items.ItemsTFC;
import net.dries007.tfc.objects.te.*;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;
import static net.dries007.tfc.objects.blocks.BlockPlacedHide.SIZE;
import static net.dries007.tfc.objects.blocks.agriculture.BlockCropTFC.WILD;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = MOD_ID)
public final class ClientRegisterEvents
{
    @SubscribeEvent
    @SuppressWarnings("ConstantConditions")
    public static void registerModels(ModelRegistryEvent event)
    {
        // ITEMS //

        // Registering fluid containers
        ModelLoader.setCustomModelResourceLocation(ItemsTFC.WOODEN_BUCKET, 0, new ModelResourceLocation(ItemsTFC.WOODEN_BUCKET.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemsTFC.FIRED_JUG, 0, new ModelResourceLocation(ItemsTFC.FIRED_JUG.getRegistryName(), "inventory"));
        // ModelLoader.setCustomModelResourceLocation(ItemsTFC.BLUE_STEEL_BUCKET, 0, new ModelResourceLocation(ItemsTFC.BLUE_STEEL_BUCKET.getRegistryName(), "inventory"));
        // ModelLoader.setCustomModelResourceLocation(ItemsTFC.RED_STEEL_BUCKET, 0, new ModelResourceLocation(ItemsTFC.RED_STEEL_BUCKET.getRegistryName(), "inventory"));

        // Simple Items
        for (Item item : ItemsTFC.getAllSimpleItems())
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName().toString()));

        // Dye color Items
        for (EnumDyeColor color : EnumDyeColor.values())
        {
            ModelLoader.setCustomModelResourceLocation(ItemsTFC.UNFIRED_VESSEL_GLAZED, color.getDyeDamage(), new ModelResourceLocation(ItemsTFC.UNFIRED_VESSEL_GLAZED.getRegistryName().toString()));
            ModelLoader.setCustomModelResourceLocation(ItemsTFC.FIRED_VESSEL_GLAZED, color.getDyeDamage(), new ModelResourceLocation(ItemsTFC.FIRED_VESSEL_GLAZED.getRegistryName().toString()));
        }

        // Gold Pan
        /*ModelLoader.registerItemVariants(ItemsTFC.GOLDPAN, Arrays.stream(ItemGoldPan.TYPES).map(e -> new ResourceLocation(MOD_ID, "goldpan/" + e)).toArray(ResourceLocation[]::new));
        for (int meta = 0; meta < ItemGoldPan.TYPES.length; meta++)
            ModelLoader.setCustomModelResourceLocation(ItemsTFC.GOLDPAN, meta, new ModelResourceLocation(MOD_ID + ":goldpan/" + ItemGoldPan.TYPES[meta]));
        ModelLoader.registerItemVariants(ItemsTFC.GOLDPAN, Arrays.stream(ItemGoldPan.TYPES).map(e -> new ResourceLocation(MOD_ID, "goldpan/" + e)).toArray(ResourceLocation[]::new));*/

        // Ceramic Molds
        for (TFCOrePrefixExtended extendedOrePrefix : TFGUtils.EXTENDED_OREPREFIXES)
        {
            if (extendedOrePrefix.isHasMold())
            {
                ItemMold item = ItemMold.get(extendedOrePrefix.getOrePrefix());

                ModelBakery.registerItemVariants(item, new ModelResourceLocation(item.getRegistryName().toString() +  "_empty"));
                ModelBakery.registerItemVariants(item, new ModelResourceLocation(item.getRegistryName().toString() +  "_filled"));

                ModelLoader.setCustomMeshDefinition(item, new ItemMeshDefinition()
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
                                return new ModelResourceLocation(item.getRegistryName().toString() + "_filled");
                            }
                        }
                        return new ModelResourceLocation(item.getRegistryName().toString() + "_empty");
                    }
                });
            }

        }

        // Item Blocks
        for (ItemBlock item : BlocksTFC.getAllNormalItemBlocks())
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "normal"));

        for (ItemBlock item : BlocksTFC.getAllInventoryItemBlocks())
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));

        for (ItemBlock item : BlocksTFC.getAllBarrelItemBlocks())
        {
            final ModelResourceLocation sealed = new ModelResourceLocation(item.getRegistryName(), "sealed=true");
            final ModelResourceLocation unsealed = new ModelResourceLocation(item.getRegistryName(), "sealed=false");
            ModelLoader.setCustomMeshDefinition(item, stack -> stack.getTagCompound() != null ? sealed : unsealed);
        }

        // BLOCKS - STATE MAPPERS //

        // Blocks with Ignored Properties
        for (Block block : BlocksTFC.getAllFluidBlocks())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockFluidBase.LEVEL).build());

        for (Block block : BlocksTFC.getAllFenceGateBlocks())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockFenceGate.POWERED).build());

        for (Block block : BlocksTFC.getAllLeafBlocks())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockLeaves.DECAYABLE).build());

        for (Block block : BlocksTFC.getAllWallBlocks())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockWall.VARIANT).build());

        for (Block block : BlocksTFC.getAllLogBlocks())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockLogTFC.PLACED).build());

        for (Block block : BlocksTFC.getAllSaplingBlocks())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockSaplingTFC.STAGE).build());

        for (Block block : BlocksTFC.getAllDoorBlocks())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockDoor.POWERED).build());

        for (Block block : BlocksTFC.getAllChestBlocks())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockChest.FACING).build());

        for (BlockSlabTFC.Half block : BlocksTFC.getAllSlabBlocks())
        {
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockSlabTFC.VARIANT).build());
            ModelLoader.setCustomStateMapper(block.doubleSlab, new StateMap.Builder().ignore(BlockSlabTFC.VARIANT).build());
        }

        for (Block block : BlocksTFC.getAllCropBlocks())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(WILD).build());

        for (Block block : BlocksTFC.getAllFruitTreeLeavesBlocks())
            ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(BlockFruitTreeLeaves.DECAYABLE).ignore(BlockFruitTreeLeaves.HARVESTABLE).build());

        BlocksTFC.getAllBlockRockVariants().forEach(e -> {
            if (e.getType() == Rock.Type.FARMLAND)
            {
                ModelLoader.setCustomStateMapper(e, new StateMap.Builder().ignore(BlockFarmlandTFC.MOISTURE).build());
            }
            else if (e.getType() == Rock.Type.RAW)
            {
                ModelLoader.setCustomStateMapper(e, new StateMap.Builder().ignore(BlockRockRaw.CAN_FALL).build());
            }
            else if (e.getType() == Rock.Type.SMOOTH)
            {
                ModelLoader.setCustomStateMapper(e, new StateMap.Builder().ignore(BlockRockSmooth.CAN_FALL).build());
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

        // TESRs //

        ClientRegistry.bindTileEntitySpecialRenderer(TEChestTFC.class, new TESRChestTFC());
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
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerColorHandlerBlocks(ColorHandlerEvent.Block event)
    {
        BlockColors blockColors = event.getBlockColors();

        // Grass Colors
        IBlockColor grassColor = GrassColorHandler::computeGrassColor;

        // Foliage Color
        // todo: do something different for conifers - they should have a different color mapping through the seasons
        IBlockColor foliageColor = GrassColorHandler::computeGrassColor;

        blockColors.registerBlockColorHandler(grassColor, BlocksTFC.PEAT_GRASS);
        blockColors.registerBlockColorHandler(grassColor, BlocksTFC.getAllBlockRockVariants().stream().filter(x -> x.getType().isGrass).toArray(BlockRockVariant[]::new));
        // This is talking about tall grass vs actual grass blocks
        blockColors.registerBlockColorHandler(grassColor, BlocksTFC.getAllGrassBlocks().toArray(new BlockPlantTFC[0]));

        blockColors.registerBlockColorHandler(foliageColor, BlocksTFC.getAllLeafBlocks().toArray(new Block[0]));
        blockColors.registerBlockColorHandler(foliageColor, BlocksTFC.getAllPlantBlocks().toArray(new BlockPlantTFC[0]));

        blockColors.registerBlockColorHandler(foliageColor, BlocksTFC.getAllFruitTreeLeavesBlocks().toArray(new Block[0]));

        blockColors.registerBlockColorHandler(foliageColor, BlocksTFC.getAllFlowerPots().toArray(new Block[0]));

        blockColors.registerBlockColorHandler((state, worldIn, pos, tintIndex) -> BlockFarmlandTFC.TINT[state.getValue(BlockFarmlandTFC.MOISTURE)],
            BlocksTFC.getAllBlockRockVariants().stream().filter(x -> x.getType() == Rock.Type.FARMLAND).toArray(BlockRockVariant[]::new));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("deprecation")
    public static void registerColorHandlerItems(ColorHandlerEvent.Item event)
    {
        ItemColors itemColors = event.getItemColors();

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFC.getAllBlockRockVariants().stream().filter(x -> x.getType().isGrass).toArray(BlockRockVariant[]::new));

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFC.PEAT_GRASS);

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFC.getAllLeafBlocks().toArray(new BlockLeavesTFC[0]));

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFC.getAllFruitTreeLeavesBlocks().toArray(new BlockFruitTreeLeaves[0]));

        itemColors.registerItemColorHandler((stack, tintIndex) -> tintIndex == 1 ? EnumDyeColor.byDyeDamage(stack.getItemDamage()).getColorValue() : 0xFFFFFF,
            ItemsTFC.UNFIRED_VESSEL_GLAZED, ItemsTFC.FIRED_VESSEL_GLAZED);

        itemColors.registerItemColorHandler((stack, tintIndex) ->
                event.getBlockColors().colorMultiplier(((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata()), null, null, tintIndex),
            BlocksTFC.getAllGrassBlocks().toArray(new BlockPlantTFC[0]));

        itemColors.registerItemColorHandler((stack, tintIndex) -> {
            IFood food = stack.getCapability(CapabilityFood.CAPABILITY, null);
            if (food != null)
            {
                return food.isRotten() ? ConfigTFC.Client.DISPLAY.rottenFoodOverlayColor : 0xFFFFFF;
            }
            return 0xFFFFFF;
        }, ForgeRegistries.ITEMS.getValuesCollection().stream().filter(x -> x instanceof ItemFood).toArray(Item[]::new));

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
        }, ForgeRegistries.ITEMS.getValuesCollection().stream().filter(x -> x instanceof ItemMold).toArray(Item[]::new));
    }
}
