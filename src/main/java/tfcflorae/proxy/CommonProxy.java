package tfcflorae.proxy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.GameRegistry;

import tfcflorae.ConfigTFCF;
import tfcflorae.TFCFlorae;
import tfcflorae.world.worldgen.*;
import tfcflorae.world.worldgen.cave.WorldGenLightstones;
import tfcflorae.world.worldgen.cave.WorldGeneratorUnderground;
import tfcflorae.world.worldgen.groundcover.WorldGenSurfaceBones;
import tfcflorae.world.worldgen.groundcover.WorldGenSurfaceDriftwood;
import tfcflorae.world.worldgen.groundcover.WorldGenSurfaceFlint;
import tfcflorae.world.worldgen.groundcover.WorldGenSurfacePinecone;
import tfcflorae.world.worldgen.groundcover.WorldGenSurfaceRocks;
import tfcflorae.world.worldgen.groundcover.WorldGenSurfaceSeashells;
import tfcflorae.world.worldgen.groundcover.WorldGenSurfaceTwig;
import tfcflorae.world.worldgen.soil.*;
import tfcflorae.world.worldgen.structures.WorldGenStructures;
import tfcflorae.world.worldgen.structures.WorldGenStructuresCorals;

import static tfcflorae.TFCFlorae.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class CommonProxy implements IProxy
{
    public void preInit(FMLPreInitializationEvent event) 
    {
        if (ConfigTFCF.General.STRUCTURES.activateStructureGeneration)
        {
    	    GameRegistry.registerWorldGenerator(new WorldGenStructures(), 0);
            if (ConfigTFCF.General.WORLD.enableCoralWorldGen)
            {
    	        GameRegistry.registerWorldGenerator(new WorldGenStructuresCorals(), 0);
            }
        }
        if (ConfigTFCF.General.WORLD.enableAllWorldGen)
        {
            if (ConfigTFCF.General.WORLD.enableMesaStrata)
            {
                GameRegistry.registerWorldGenerator(new WorldGenMesaStrata(), 0);
            }
            if (ConfigTFCF.General.WORLD.enableTrees)
            {
                GameRegistry.registerWorldGenerator(new WorldGeneratorTrees(), 0);
            }
            //GameRegistry.registerWorldGenerator(new WorldGenWildCropsTFCF(), 0);
            if (ConfigTFCF.General.WORLD.enableCoralWorldGen)
            {
                GameRegistry.registerWorldGenerator(new WorldGenCorals(), 0);
            }
            if (ConfigTFCF.General.WORLD.enableMossyRawWorldGen)
            {
                GameRegistry.registerWorldGenerator(new WorldGenMossyRaw(), 0);
            }
            if (ConfigTFCF.General.WORLD.enablePlantWorldGen)
            {
                GameRegistry.registerWorldGenerator(new WorldGeneratorPlants(), 0);
            }
            if (ConfigTFCF.General.WORLD.enableUndergroundPlantWorldGen)
            {
                GameRegistry.registerWorldGenerator(new WorldGeneratorUnderground(), 0);
            }
            if (ConfigTFCF.General.WORLD.enableLightstoneWorldGen)
            {
                GameRegistry.registerWorldGenerator(new WorldGenLightstones(), 0);
            }
            if (ConfigTFCF.General.WORLD.enableOceanGlowPlantWorldGen)
            {
                GameRegistry.registerWorldGenerator(new WorldGenGlowPlant(), 0);
            }
            if (ConfigTFCF.General.WORLD.enableSoilPits)
            {
                //GameRegistry.registerWorldGenerator(new WorldGenSoil(), 0);
                GameRegistry.registerWorldGenerator(new WorldGenSoilTypes(), 0);
                GameRegistry.registerWorldGenerator(new WorldGenSoilDecorative(), 0);
                GameRegistry.registerWorldGenerator(new WorldGenClays(), 0);
            }
            if (ConfigTFCF.General.WORLD.enableGroundcoverRock)
            {
                GameRegistry.registerWorldGenerator(new WorldGenSurfaceRocks(), 0);
            }
            if (ConfigTFCF.General.WORLD.enableGroundcoverSeashell)
            {
                GameRegistry.registerWorldGenerator(new WorldGenSurfaceSeashells(), 0);
            }
            if (ConfigTFCF.General.WORLD.enableGroundcoverFlint)
            {
                GameRegistry.registerWorldGenerator(new WorldGenSurfaceFlint(), 0);
            }
            if (ConfigTFCF.General.WORLD.enableGroundcoverBones)
            {
                GameRegistry.registerWorldGenerator(new WorldGenSurfaceBones(), 0);
            }
            if (ConfigTFCF.General.WORLD.enableGroundcoverPinecone)
            {
                GameRegistry.registerWorldGenerator(new WorldGenSurfacePinecone(), 0);
            }
            if (ConfigTFCF.General.WORLD.enableGroundcoverDriftwood)
            {
                GameRegistry.registerWorldGenerator(new WorldGenSurfaceDriftwood(), 0);
            }
            if (ConfigTFCF.General.WORLD.enableGroundcoverTwig)
            {
                GameRegistry.registerWorldGenerator(new WorldGenSurfaceTwig(), 0);
            }
            if (ConfigTFCF.General.WORLD.enableGourdWorldGen && TFCFlorae.FirmaLifeAdded)
            {
                GameRegistry.registerWorldGenerator(new WorldGenGourds(), 0);
            }
        }
    }

    public void init(FMLInitializationEvent event) 
    {
    }

    public void postInit(FMLPostInitializationEvent event) 
    {
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) 
    {
    }

    @Override
    @Nonnull
    public IThreadListener getThreadListener(MessageContext context)
    {
        if (context.side.isServer())
        {
            return context.getServerHandler().player.server;
        }
        else
        {
            throw new WrongSideException("Tried to get the IThreadListener from a client-side MessageContext on the dedicated server");
        }
    }

    @Override
    @Nullable
    public EntityPlayer getPlayer(MessageContext context)
    {
        if (context.side.isServer())
        {
            return context.getServerHandler().player;
        }
        else
        {
            throw new WrongSideException("Tried to get the player from a client-side MessageContext on the dedicated server");
        }
    }

    @Override
    @Nullable
    public World getWorld(MessageContext context)
    {
        if (context.side.isServer())
        {
            return context.getServerHandler().player.getServerWorld();
        }
        else
        {
            throw new WrongSideException("Tried to get the player from a client-side MessageContext on the dedicated server");
        }
    }
}
