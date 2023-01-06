package cellars;

import net.dries007.tfc.api.capability.food.FoodTrait;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import cellars.proxy.CommonProxy;
import cellars.util.CellarsTab;
import cellars.util.Reference;
import cellars.util.handlers.PacketHandler;
import cellars.util.handlers.RegistryHandler;

import java.util.Map;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, dependencies = "required-after:tfc")
@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class Main {

    public static final CellarsTab creativeTab = new CellarsTab();

    @Mod.Instance
    public static Main INSTANCE;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void PreInit(FMLPreInitializationEvent event)
    {
        ModConfig.loadConfig(event);
    }
    @Mod.EventHandler
    public void Init(FMLInitializationEvent event)
    {
        System.out.println("Mod Config for Cellars has Cool Mod as " + ModConfig.coolMod);
        System.out.println("Mod Config for Cellars has Icy Mod as " + ModConfig.icyMod);
        System.out.println("Mod Config for Cellars has Frozen Mod as " + ModConfig.icleMod);
        System.out.println("Mod Config for Cellars has Freeze Dryed Mod as " + ModConfig.dryMod);
        System.out.println("Mod Config for Cellars has Preserving Mod as " + ModConfig.preservingMod);

        RegistryHandler.initRegistries();

        Reference.COOL = new FoodTrait("sharkCool", ModConfig.coolMod);
        Reference.ICY = new FoodTrait("sharkIcy", ModConfig.icyMod);
        Reference.FREEZING = new FoodTrait("sharkIcle", ModConfig.icleMod);
        Reference.DRY = new FoodTrait("sharkDry", ModConfig.dryMod);
        Reference.PRESERVING = new FoodTrait("sharkPreserving", ModConfig.preservingMod);

        Map<String, FoodTrait> tmp = FoodTrait.getTraits();
        for (Map.Entry<String,FoodTrait> entry : tmp.entrySet())
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue() +
                    ", Decay = " + entry.getValue().getDecayModifier());
    }
    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event)
    {
        Reference.initialized = true;
        PacketHandler.init();
    }
}
