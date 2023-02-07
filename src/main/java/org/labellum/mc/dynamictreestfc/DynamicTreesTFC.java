package org.labellum.mc.dynamictreestfc;

import net.dries007.tfc.compat.dynamictrees.TFCLeavesHandler;
import net.dries007.tfc.compat.dynamictrees.TFCRootDecay;
import net.dries007.tfc.compat.dynamictrees.TFCTrees;
import org.apache.logging.log4j.Logger;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.ferreusveritas.dynamictrees.api.TreeHelper;
import com.ferreusveritas.dynamictrees.event.BiomeSuitabilityEvent;
import net.dries007.tfc.TerraFirmaCraft;

import static com.ferreusveritas.dynamictrees.ModConstants.*;

@Mod(
    modid = DynamicTreesTFC.MOD_ID,
    name = DynamicTreesTFC.MOD_NAME,
    version = DynamicTreesTFC.VERSION,
    dependencies = DynamicTreesTFC.DEPENDENCIES
)
public class DynamicTreesTFC
{

    public static final String MOD_ID = "dynamictreestfc";
    public static final String MOD_NAME = "TFC: Dynamic Trees ";
    public static final String VERSION = "1.0.0";
    public static final String DEPENDENCIES
        = REQAFTER + TerraFirmaCraft.MOD_ID +
        AT + "1.7.3.161" + ORGREATER +
        NEXT +
        REQAFTER + DYNAMICTREES_LATEST;

    //TFC version in dev is the string "${version}" so not sure how this works.

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static DynamicTreesTFC INSTANCE;

    public static Logger logger;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event)
    {
        TFCTrees.postInit();
        TreeHelper.setCustomRootBlockDecay(TFCRootDecay.INSTANCE);
    }


    /**
     * This is a special class that listens to registry events, to allow creation of mod blocks and items at the proper time.
     */
    @Mod.EventBusSubscriber
    public static class ObjectRegistryHandler
    {
        /**
         * Listen for the register event for creating custom items
         */
        @SubscribeEvent
        public static void addItems(RegistryEvent.Register<Item> event)
        {
            ModItems.register(event.getRegistry());
            TFCTrees.registerItems(event.getRegistry());
        }

        /**
         * Listen for DynamicTrees event for biome suitability
         * Ignore suitability computations for TFC Trees for now
         * https://github.com/ferreusveritas/DynamicTrees/blob/f7edfc2d423b87bf6b7ebf2ad1b628a694114171/src/main/java/com/ferreusveritas/dynamictrees/trees/Species.java#L963
         * need to catch BiomeSuitabilityEvent
         */
        @SubscribeEvent
        public static void biomeHandler(BiomeSuitabilityEvent event)
        {
            event.setSuitability(1.0f); //doesn't change value, sets isHandled
        }

        /**
         * Listen for the register event for creating custom blocks
         */
        @SubscribeEvent
        public static void addBlocks(RegistryEvent.Register<Block> event)
        {
            TFCLeavesHandler.register();
            TFCTrees.registerBlocks(event.getRegistry());
        }
    }
}
