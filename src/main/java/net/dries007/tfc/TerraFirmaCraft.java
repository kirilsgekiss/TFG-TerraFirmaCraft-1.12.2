/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc;

import com.ferreusveritas.dynamictrees.api.TreeHelper;
import com.ferreusveritas.dynamictrees.seasons.SeasonHelper;
import net.dries007.tfc.api.capability.damage.CapabilityDamageResistance;
import net.dries007.tfc.api.capability.egg.CapabilityEgg;
import net.dries007.tfc.api.capability.food.CapabilityFood;
import net.dries007.tfc.api.capability.food.FoodHandler;
import net.dries007.tfc.api.capability.forge.CapabilityForgeable;
import net.dries007.tfc.api.capability.heat.CapabilityItemHeat;
import net.dries007.tfc.api.capability.metal.CapabilityMetalItem;
import net.dries007.tfc.api.capability.player.CapabilityPlayerData;
import net.dries007.tfc.api.capability.size.CapabilityItemSize;
import net.dries007.tfc.api.capability.worldtracker.CapabilityWorldTracker;
import net.dries007.tfc.client.ClientEvents;
import net.dries007.tfc.client.TFCGuiHandler;
import net.dries007.tfc.client.TFCKeybindings;
import net.dries007.tfc.client.gui.overlay.PlayerDataOverlay;
import net.dries007.tfc.command.*;
import net.dries007.tfc.compat.dynamictrees.DTLeavesHandler;
import net.dries007.tfc.compat.dynamictrees.DTRootDecay;
import net.dries007.tfc.compat.dynamictrees.DTTrees;
import net.dries007.tfc.compat.gregtech.items.TFCMetaItem;
import net.dries007.tfc.compat.gregtech.items.tools.TFCToolItems;
import net.dries007.tfc.compat.top.TOPCompatibility;
import net.dries007.tfc.network.*;
import net.dries007.tfc.objects.TFCLootTables;
import net.dries007.tfc.objects.entity.TFCEntities;
import net.dries007.tfc.proxy.IProxy;
import net.dries007.tfc.util.CapabilityHeatHandler;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.agriculture.TFCSeasonManager;
import net.dries007.tfc.util.calendar.TFCCalendar;
import net.dries007.tfc.util.fuel.FuelManager;
import net.dries007.tfc.util.json.JsonConfigRegistry;
import net.dries007.tfc.world.classic.WorldTypeTFC;
import net.dries007.tfc.world.classic.chunkdata.CapabilityChunkData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.dedicated.PropertyManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.server.FMLServerHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.dries007.tfc.TerraFirmaCraft.DEPENDENCIES;
import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

@SuppressWarnings("FieldMayBeFinal")
@Mod.EventBusSubscriber
@Mod(
        modid = MOD_ID,
        name = TerraFirmaCraft.MOD_NAME,
        version = TerraFirmaCraft.VERSION,
        useMetadata = true,
        guiFactory = Constants.GUI_FACTORY,
        dependencies = DEPENDENCIES)
public final class TerraFirmaCraft {
    public static final String MOD_ID = "tfc";
    public static final String MOD_NAME = "TerraFirmaGreg: Edition";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDENCIES = "" +
            "required:forge@[14.23.5.2847,);" +
            "after:gregtech[2.5.0-beta,);" +
            "after:jei@[4.14.2,)";

    @Mod.Instance
    private static TerraFirmaCraft INSTANCE = null;

    @SidedProxy(modId = MOD_ID, clientSide = "net.dries007.tfc.proxy.ClientProxy", serverSide = "net.dries007.tfc.proxy.ServerProxy")
    private static IProxy PROXY = null;

    private final Logger log = LogManager.getLogger(MOD_ID);
    private WorldTypeTFC worldTypeTFC;
    private SimpleNetworkWrapper network;

    public static Logger getLog() {
        return INSTANCE.log;
    }

    public static IProxy getProxy() {
        return PROXY;
    }

    public static WorldTypeTFC getWorldType() {
        return INSTANCE.worldTypeTFC;
    }

    public static SimpleNetworkWrapper getNetwork() {
        return INSTANCE.network;
    }

    public static TerraFirmaCraft getInstance() {
        return INSTANCE;
    }

    static {
        FluidRegistry.enableUniversalBucket();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        log.debug("TerraFirmaCraft is Working! :)");

        TFCToolItems.init();
        TFCMetaItem.init();

        DTLeavesHandler.preInit();

        //OBJLoader.INSTANCE.addDomain(TFCFLORAE_MODID); // Client Proxy from Florae?

        SeasonHelper.setSeasonManager(TFCSeasonManager.INSTANCE);

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
        network.registerMessage(new PacketProspectResult.Handler(), PacketProspectResult.class, ++id, Side.CLIENT);

        TFCEntities.preInit();
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

        Helpers.insertWhitelistFluids();

        if (event.getSide().isClient()) {
            ClientEvents.preInit();
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        TFCLootTables.init();
        CapabilityFood.init();

        if (event.getSide().isClient()) {
            TFCKeybindings.init();
            // Enable overlay to render health, thirst and hunger bars, TFC style.
            // Also renders animal familiarity
            MinecraftForge.EVENT_BUS.register(PlayerDataOverlay.getInstance());
        } else {
            MinecraftServer server = FMLServerHandler.instance().getServer();
            if (server instanceof DedicatedServer) {
                PropertyManager settings = ((DedicatedServer) server).settings;
                if (TFCConfig.General.OVERRIDES.forceTFCWorldType) {
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
        CapabilityHeatHandler.init();

        TOPCompatibility.registerCompatibility();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
//        DefaultRecipes.register();

        DTTrees.postInit();
        TreeHelper.setCustomRootBlockDecay(DTRootDecay.INSTANCE);

        FuelManager.postInit();
        JsonConfigRegistry.INSTANCE.postInit();
    }

    @Mod.EventHandler
    public void onLoadComplete(FMLLoadCompleteEvent event) {
        // This is the latest point that we can possibly stop creating non-decaying stacks on both server + client
        // It should be safe to use as we're only using it internally
        FoodHandler.setNonDecaying(false);
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandStripWorld());
        event.registerServerCommand(new CommandHeat());
        event.registerServerCommand(new CommandPlayerTFC());
        event.registerServerCommand(new CommandTimeTFC());
        event.registerServerCommand(new CommandDebugInfo());
        event.registerServerCommand(new CommandWorkChunk());
        event.registerServerCommand(new CommandGenTree());

        // Initialize calendar for the current server
        TFCCalendar.INSTANCE.init(event.getServer());
    }
}
