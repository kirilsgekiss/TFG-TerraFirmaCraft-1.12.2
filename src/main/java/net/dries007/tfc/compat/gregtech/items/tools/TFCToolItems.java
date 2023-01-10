package net.dries007.tfc.compat.gregtech.items.tools;

import gregtech.GregTechMod;
import gregtech.GregTechVersion;
import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.items.toolitem.*;
import gregtech.common.items.ToolItems;
import gregtech.core.sound.GTSoundEvents;
import net.dries007.tfc.TerraFirmaCraft;

public final class TFCToolItems {

    public static IGTTool TONGS;
    public static IGTTool TUYERE;
    public static IGTTool CHISEL;
    public static IGTTool PROPICK;
    public static IGTTool JAVELIN;

    public static void init()
    {
        TONGS = ToolItems.register(ItemGTTool.Builder.of(GTValues.MODID, "tongs")
                .toolStats(b -> b.crafting().cannotAttack().attackSpeed(-2.4F))
                .oreDict("craftingToolTongs")
                .sound(GTSoundEvents.SOFT_MALLET_TOOL) // todo
                .toolClasses("tongs"));

        TUYERE = ToolItems.register(ItemGTTool.Builder.of(GTValues.MODID, "tuyere")
                .toolStats(b -> b.crafting().cannotAttack().attackSpeed(-2.4F))
                .oreDict("craftingToolTuyere")
                .sound(GTSoundEvents.SOFT_MALLET_TOOL) // todo
                .toolClasses("tuyere"));

        CHISEL = ToolItems.register(ItemGTTool.Builder.of(GTValues.MODID, "chisel")
                .toolStats(b -> b.crafting().cannotAttack().attackSpeed(-2.4F))
                .oreDict("craftingToolChisel")
                .sound(GTSoundEvents.SOFT_MALLET_TOOL) // todo
                .toolClasses("chisel"));

        PROPICK = ToolItems.register(ItemGTTool.Builder.of(GTValues.MODID, "propick")
                .toolStats(b -> b.crafting().cannotAttack().attackSpeed(-2.4F))
                .oreDict("craftingToolPropick")
                .sound(GTSoundEvents.SOFT_MALLET_TOOL) // todo
                .toolClasses("propick"));

        JAVELIN = ToolItems.register(ItemGTTool.Builder.of(GTValues.MODID, "javelin")
                .toolStats(b -> b.crafting().cannotAttack().attackSpeed(-2.4F))
                .oreDict("craftingToolJavelin")
                .sound(GTSoundEvents.SOFT_MALLET_TOOL) // todo
                .toolClasses("javelin"));
    }

}
