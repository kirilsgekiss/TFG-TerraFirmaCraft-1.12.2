package tfcflorae;

import net.minecraftforge.fml.common.Mod;

@Mod(modid = TFCFlorae.TFCFLORAE_MODID, name = TFCFlorae.NAME, version = TFCFlorae.VERSION, dependencies = TFCFlorae.DEPENDENCIES)
public class TFCFlorae
{
    public static final String TFCFLORAE_MODID = "tfcflorae";
    public static final String NAME = "TFC Florae";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDENCIES = "required-after:tfc;"
            + "after:firmalife;"
            + "after:tfcelementia;"
            + "after:tfc_ph_compat;";

    @Mod.Instance
    public static TFCFlorae instance;

    public static boolean FirmaLifeAdded = false;
}
