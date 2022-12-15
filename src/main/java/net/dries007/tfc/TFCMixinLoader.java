package net.dries007.tfc;

import org.spongepowered.asm.mixin.Mixins;
import zone.rong.mixinbooter.MixinLoader;

@MixinLoader
public class TFCMixinLoader {
    static {
        Mixins.addConfiguration("mixins.tfc.json");
    }
}
