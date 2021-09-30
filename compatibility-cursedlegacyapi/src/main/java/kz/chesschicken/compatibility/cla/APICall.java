package kz.chesschicken.compatibility.cla;

import kz.chesschicken.compatibility.CompatibilityAPI;
import kz.chesschicken.compatibility.cla.api.CursedLegacyAPI;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

//Because being bald, CL-API.
public class APICall implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        if(FabricLoader.getInstance().isModLoaded("api")) {
            CompatibilityAPI.LOGGER.info("Using CursedFabricAPI as an API.");
            CompatibilityAPI.SET_API(new CursedLegacyAPI());
        }
    }
}
