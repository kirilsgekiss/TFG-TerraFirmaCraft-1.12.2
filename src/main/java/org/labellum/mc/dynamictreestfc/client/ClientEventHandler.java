package org.labellum.mc.dynamictreestfc.client;

import net.dries007.tfc.objects.blocks.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.ferreusveritas.dynamictrees.blocks.BlockRooty;
import com.ferreusveritas.dynamictrees.blocks.LeavesPaging;
import com.ferreusveritas.dynamictrees.items.Seed;
import com.ferreusveritas.dynamictrees.models.bakedmodels.BakedModelBlockRooty;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;
import net.dries007.tfc.client.GrassColorHandler;
import net.dries007.tfc.compat.dynamictrees.TFCTrees;

import static org.labellum.mc.dynamictreestfc.DynamicTreesTFC.MOD_ID;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = MOD_ID, value = {Side.CLIENT})
public class ClientEventHandler
{
    public ClientEventHandler() { }

    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent event)
    {
        Block block = TFCBlocks.blockRootyDirt;
        if (block.getRegistryName() != null)
        {
            BakedModelBlockRooty rootyModel = new TFCBakedModelBlockRooty();
            event.getModelRegistry().putObject(new ModelResourceLocation(block.getRegistryName(), "normal"), rootyModel);
        }
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event)
    {
        //Register Meshers for Branches
        for (TreeFamily tree : TFCTrees.tfcTrees)
        {
            TFCModelHelper.regModel(tree.getDynamicBranch());//Register Branch itemBlock
            TFCModelHelper.regModel(tree);//Register custom state mapper for branch
        }

        ModelLoader.setCustomStateMapper(TFCBlocks.blockRootyDirt, new StateMap.Builder().ignore(BlockRooty.LIFE).build());

        TFCTrees.tfcSpecies.values().stream().filter(s -> s.getSeed() != Seed.NULLSEED).forEach(s -> TFCModelHelper.regModel(s.getSeed()));//Register Seed Item Models
    }

    @SubscribeEvent
    public static void registerColorHandlerBlocks(ColorHandlerEvent.Block event)
    {
        final BlockColors blockColors = event.getBlockColors();
        blockColors.registerBlockColorHandler(GrassColorHandler::computeGrassColor, LeavesPaging.getLeavesMapForModId(MOD_ID).values().toArray(new Block[0]));
        blockColors.registerBlockColorHandler(GrassColorHandler::computeGrassColor, TFCBlocks.blockRootyDirt);
    }
}
