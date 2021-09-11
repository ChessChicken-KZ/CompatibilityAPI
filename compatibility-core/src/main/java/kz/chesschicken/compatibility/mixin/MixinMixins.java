package kz.chesschicken.compatibility.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.FabricLoader;
import net.fabricmc.loader.launch.common.FabricMixinBootstrap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.Set;

@Mixin(FabricMixinBootstrap.class)
public class MixinMixins {
    @Inject(method = "getMixinConfigs", at = @At("RETURN"), cancellable = true, remap = false)
    private static void dothis(FabricLoader loader, EnvType type, CallbackInfoReturnable<Set<String>> cir)
    {
         for(String s : cir.getReturnValue())
         {
             System.out.println("LOL: " + s);
         }
    }
}
