package kz.chesschicken.compatibility.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.transformer.Config;

@Mixin(Mixins.class)
public interface AccessorMixins {

    @Invoker(value = "registerConfiguration", remap = false)
    static void invokeRegisterConfiguration(Config config) {
        throw new AssertionError();
    }
}
