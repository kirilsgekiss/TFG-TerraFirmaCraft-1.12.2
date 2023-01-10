package tfcflorae;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

import tfcflorae.proxy.CommonProxy;
import tfcflorae.util.CapabilityHeatHandler;
import tfcflorae.util.HelpersTFCF;
import tfcflorae.util.OreDictionaryHelper;
import tfcflorae.util.fuel.FuelsTFCF;

@SuppressWarnings({ "WeakerAccess", "unused" })
@Mod(modid = TFCFlorae.TFCFLORAE_MODID, name = TFCFlorae.NAME, version = TFCFlorae.VERSION, dependencies = TFCFlorae.DEPENDENCIES, certificateFingerprint = TFCFlorae.SIGNING_KEY)
public class TFCFlorae
{
    public static final String TFCFLORAE_MODID = "tfcflorae";
    public static final String NAME = "TFC Florae";
    public static final String VERSION = "@VERSION@";
    public static final String SIGNING_KEY = "@FINGERPRINT@";
    public static final String DEPENDENCIES = "required-after:tfc;"
            + "after:firmalife;"
            + "after:tfcelementia;"
            + "after:tfc_ph_compat;";

    @Mod.Instance
    public static TFCFlorae instance;
    public static Logger logger;
    public static boolean signedBuild = true;

    public static boolean FirmaLifeAdded = false;
    public static boolean TFCElementiaAdded = false;
    public static boolean TFCPHCompatAdded = false;

    @SidedProxy(serverSide = "tfcflorae.proxy.CommonProxy", clientSide = "tfcflorae.proxy.ClientProxy")
    public static CommonProxy proxy;

    public static Logger getLog()
    {
        return logger;
    }

    public static CommonProxy getProxy()
    {
        return proxy;
    }

    public static TFCFlorae getInstance()
    {
        return instance;
    }

    public static SimpleNetworkWrapper getNetwork()
    {
        return instance.network;
    }

    private SimpleNetworkWrapper network;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        //ClassAdder.addClasses(event.getModConfigurationDirectory());
        logger = event.getModLog();

        //NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        network = NetworkRegistry.INSTANCE.newSimpleChannel(TFCFLORAE_MODID);
        int id = 0;

        for (ModContainer Mod : Loader.instance().getActiveModList())
        {
            if (Mod.getModId().equals("firmalife"))
                FirmaLifeAdded = true;
            if (Mod.getModId().equals("tfcelementia"))
                TFCElementiaAdded = true;
            if (Mod.getModId().equals("tfc_ph_compat"))
                TFCPHCompatAdded = true;
        }

        proxy.preInit(event);

        HelpersTFCF.insertWhitelistFluids();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        //NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
        OreDictionaryHelper.init();
        CapabilityHeatHandler.init();
		proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        FuelsTFCF.postInit();
        proxy.postInit(event);
    }
}
