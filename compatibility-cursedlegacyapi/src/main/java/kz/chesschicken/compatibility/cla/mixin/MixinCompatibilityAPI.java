package kz.chesschicken.compatibility.cla.mixin;

import kz.chesschicken.compatibility.CompatibilityAPI;
import kz.chesschicken.compatibility.cla.api.CursedLegacyAPI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CompatibilityAPI.class)
public class MixinCompatibilityAPI {
    @Inject(method = "onPreLaunch", at = @At("TAIL"), remap = false)
    private void setCustomAPI(CallbackInfo ci) {
        CompatibilityAPI.CURRENT_API = new CursedLegacyAPI();
    }
}
