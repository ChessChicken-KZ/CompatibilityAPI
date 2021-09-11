package kz.chesschicken.compatibility.cla.mixin;

import kz.chesschicken.compatibility.CompatibilityAPI;
import kz.chesschicken.compatibility.cla.api.CursedLegacyAPI;
import net.fabricmc.loader.api.FabricLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CompatibilityAPI.class)
public class MixinCompatibilityAPI {
    @Inject(method = "onPreLaunch", at = @At("HEAD"), remap = false)
    private void setCustomAPI(CallbackInfo ci) {
        if(FabricLoader.getInstance().isModLoaded("api")) {
            CompatibilityAPI.LOGGER.info("Using CursedFabricAPI as an API.");
            CompatibilityAPI.SET_API(new CursedLegacyAPI());
        }
    }
}
