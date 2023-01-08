package net.dries007.tfc.compat.top;

import gregtech.integration.theoneprobe.provider.*;
import mcjty.theoneprobe.TheOneProbe;
import mcjty.theoneprobe.api.ITheOneProbe;
import net.dries007.tfc.compat.top.providers.CrucibleProvider;

public class TOPCompatibility {
    public static void registerCompatibility() {
        ITheOneProbe oneProbe = TheOneProbe.theOneProbeImp;
        oneProbe.registerProvider(new CrucibleProvider());
    }
}
