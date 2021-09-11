package kz.chesschicken.compatibility.stapi.mixin;

import kz.chesschicken.compatibility.CompatibilityAPI;
import kz.chesschicken.compatibility.stapi.api.StationAPI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CompatibilityAPI.class)
public class MixinCompatibilityAPI {
    @Inject(method = "onPreLaunch", at = @At("HEAD"), remap = false)
    private void setCustomAPI(CallbackInfo ci) {
        CompatibilityAPI.LOGGER.info("Using StationAPI as an API.");
        CompatibilityAPI.CURRENT_API = new StationAPI();
    }
}
