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
        //guiFactory = Constants.GUI_FACTORY,
        dependencies = "required:forge@[14.23.5.2816,);after:jei@[4.14.2,);after:crafttweaker@[4.1.11,);after:waila@(1.8.25,)")

public final class TerraFirmaCraft {
    public static final String MOD_ID = "tfc";
    public static final String MOD_NAME = "TerraFirmaCraft GTCEuVersion";
    public static final String VERSION = "@VERSION@";

    @Mod.Instance
    private static TerraFirmaCraft INSTANCE = null;
}
