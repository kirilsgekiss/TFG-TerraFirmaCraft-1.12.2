package net.dries007.tfc.mixins.gregtech;

import gregtech.api.unification.ore.StoneType;
import gregtech.api.util.GTControlledRegistry;
import net.dries007.tfc.compat.gregtech.TFCStoneTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = StoneType.class, remap = false)
public class StoneTypeMixin {

    @Inject(method = "init", at = @At(value = "INVOKE", target = "Ljava/lang/String;getBytes()[B"), remap = false, cancellable = true)
    private static void onInit(CallbackInfo ci) {
        replaceGTStoneTypes();
    }

    private static void replaceGTStoneTypes()
    {
        IStoneTypeAccessor.setStoneTypeRegistry(new GTControlledRegistry<>(128));

        TFCStoneTypes.registerStoneTypes();
    }
}
