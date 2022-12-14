package net.dries007.tfc;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

@Mod.EventBusSubscriber
@Mod(
        modid = MOD_ID,
        name = TerraFirmaCraft.MOD_NAME,
        version = TerraFirmaCraft.VERSION,
        useMetadata = true,
<<<<<<< Updated upstream
        //guiFactory = Constants.GUI_FACTORY,
        dependencies = "required:forge@[14.23.5.2816,);after:jei@[4.14.2,);after:crafttweaker@[4.1.11,);after:waila@(1.8.25,)")

public final class TerraFirmaCraft {
=======
        guiFactory = Constants.GUI_FACTORY,
        dependencies = "required:forge@[14.23.5.2816,);after:gregtech;after:jei@[4.14.2,);after:crafttweaker@[4.1.11,);after:waila@(1.8.25,)")
public final class TerraFirmaCraft
{
>>>>>>> Stashed changes
    public static final String MOD_ID = "tfc";
    public static final String MOD_NAME = "TerraFirmaCraft GTCEuVersion";
    public static final String VERSION = "@VERSION@";

    @Mod.Instance
    private static TerraFirmaCraft INSTANCE = null;
<<<<<<< Updated upstream
=======

    @SidedProxy(modId = MOD_ID, clientSide = "net.dries007.tfc.proxy.ClientProxy", serverSide = "net.dries007.tfc.proxy.ServerProxy")
    private static IProxy PROXY = null;

    static
    {
        FluidRegistry.enableUniversalBucket();
    }

    public static Logger getLog()
    {
        return INSTANCE.log;
    }

    public static IProxy getProxy()
    {
        return PROXY;
    }

    public static WorldTypeTFC getWorldType()
    {
        return INSTANCE.worldTypeTFC;
    }

    public static SimpleNetworkWrapper getNetwork()
    {
        return INSTANCE.network;
    }

    public static TerraFirmaCraft getInstance()
    {
        return INSTANCE;
    }

    private final Logger log = LogManager.getLogger(MOD_ID);
    private final boolean isSignedBuild = true;
    private WorldTypeTFC worldTypeTFC;
    private SimpleNetworkWrapper network;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        log.debug("If you can see this, debug logging is working :)");

        // No need to sync config here, forge magic
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new TFCGuiHandler());
        network = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);
        int id = 0;
        // Received on server
        network.registerMessage(new PacketGuiButton.Handler(), PacketGuiButton.class, ++id, Side.SERVER);
        network.registerMessage(new PacketPlaceBlockSpecial.Handler(), PacketPlaceBlockSpecial.class, ++id, Side.SERVER);
        network.registerMessage(new PacketSwitchPlayerInventoryTab.Handler(), PacketSwitchPlayerInventoryTab.class, ++id, Side.SERVER);
        network.registerMessage(new PacketOpenCraftingGui.Handler(), PacketOpenCraftingGui.class, ++id, Side.SERVER);
        network.registerMessage(new PacketCycleItemMode.Handler(), PacketCycleItemMode.class, ++id, Side.SERVER);
        network.registerMessage(new PacketStackFood.Handler(), PacketStackFood.class, ++id, Side.SERVER);

        // Received on client
        network.registerMessage(new PacketChunkData.Handler(), PacketChunkData.class, ++id, Side.CLIENT);
        network.registerMessage(new PacketCapabilityContainerUpdate.Handler(), PacketCapabilityContainerUpdate.class, ++id, Side.CLIENT);
        network.registerMessage(new PacketCalendarUpdate.Handler(), PacketCalendarUpdate.class, ++id, Side.CLIENT);
        network.registerMessage(new PacketFoodStatsUpdate.Handler(), PacketFoodStatsUpdate.class, ++id, Side.CLIENT);
        network.registerMessage(new PacketFoodStatsReplace.Handler(), PacketFoodStatsReplace.class, ++id, Side.CLIENT);
        network.registerMessage(new PacketPlayerDataUpdate.Handler(), PacketPlayerDataUpdate.class, ++id, Side.CLIENT);
        network.registerMessage(new PacketSpawnTFCParticle.Handler(), PacketSpawnTFCParticle.class, ++id, Side.CLIENT);
        network.registerMessage(new PacketSimpleMessage.Handler(), PacketSimpleMessage.class, ++id, Side.CLIENT);
        //network.registerMessage(new PacketProspectResult.Handler(), PacketProspectResult.class, ++id, Side.CLIENT);

        EntitiesTFC.preInit();
        JsonConfigRegistry.INSTANCE.preInit(event.getModConfigurationDirectory());

        CapabilityChunkData.preInit();
        CapabilityItemSize.preInit();
        CapabilityItemHeat.preInit();
        CapabilityForgeable.preInit();
        CapabilityFood.preInit();
        CapabilityEgg.preInit();
        CapabilityPlayerData.preInit();
        CapabilityDamageResistance.preInit();
        CapabilityMetalItem.preInit();
        CapabilityWorldTracker.preInit();

        if (event.getSide().isClient())
        {
            ClientEvents.preInit();
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        ItemsTFC.init();
        LootTablesTFC.init();
        CapabilityFood.init();
        TFCTriggers.init();

        if (event.getSide().isClient())
        {
            TFCKeybindings.init();
            // Enable overlay to render health, thirst and hunger bars, TFC style.
            // Also renders animal familiarity
            MinecraftForge.EVENT_BUS.register(PlayerDataOverlay.getInstance());
        }
        else
        {
            MinecraftServer server = FMLServerHandler.instance().getServer();
            if (server instanceof DedicatedServer)
            {
                PropertyManager settings = ((DedicatedServer) server).settings;
                if (ConfigTFC.General.OVERRIDES.forceTFCWorldType)
                {
                    // This is called before vanilla defaults it, meaning we intercept it's default with ours
                    // However, we can't actually set this due to fears of overriding the existing world
                    TerraFirmaCraft.getLog().info("Setting default level-type to `tfc_classic`");
                    settings.getStringProperty("level-type", "tfc_classic");
                }
            }
        }

        worldTypeTFC = new WorldTypeTFC();

        CapabilityItemSize.init();
        CapabilityItemHeat.init();
        CapabilityMetalItem.init();

        FMLInterModComms.sendFunctionMessage("theoneprobe", "getTheOneProbe", "net.dries007.tfc.compat.waila.TOPPlugin");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        if (!isSignedBuild)
        {
            log.warn("You are not running an official build. Please do not use this and then report bugs or issues.");
        }
        FuelManager.postInit();
        JsonConfigRegistry.INSTANCE.postInit();
    }

    @Mod.EventHandler
    public void onLoadComplete(FMLLoadCompleteEvent event)
    {
        // This is the latest point that we can possibly stop creating non-decaying stacks on both server + client
        // It should be safe to use as we're only using it internally
        FoodHandler.setNonDecaying(false);
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event)
    {
        if (!isSignedBuild)
        {
            log.warn("You are not running an official build. Please do not use this and then report bugs or issues.");
        }

        event.registerServerCommand(new CommandStripWorld());
        event.registerServerCommand(new CommandHeat());
        event.registerServerCommand(new CommandPlayerTFC());
        event.registerServerCommand(new CommandTimeTFC());
        event.registerServerCommand(new CommandFindVeins());
        event.registerServerCommand(new CommandDebugInfo());
        event.registerServerCommand(new CommandWorkChunk());

        // Initialize calendar for the current server
        CalendarTFC.INSTANCE.init(event.getServer());
    }
>>>>>>> Stashed changes
}
