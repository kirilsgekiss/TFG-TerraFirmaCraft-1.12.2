package net.dries007.tfc.mixins;

import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import java.util.Map;

@Mixin(value = OrePrefix.class, remap = false)
public interface IOrePrefixListAccessor {

    @Accessor("PREFIXES")
    static Map<String, OrePrefix> getOrePrefixes()
    {
        throw new AssertionError();
    }
}
