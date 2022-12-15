package net.dries007.tfc.mixins.gregtech;

import gregtech.api.unification.ore.StoneType;
import gregtech.api.util.GTControlledRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = StoneType.class, remap = false)
public interface IStoneTypeAccessor {

    @Accessor("STONE_TYPE_REGISTRY")
    static void setStoneTypeRegistry(GTControlledRegistry<String, StoneType> stoneTypeRegistry) {
        throw new AssertionError();
    }
}
