package kz.chesschicken.compatibility.clapi;

import kz.chesschicken.compatibility.CompatibilityAPI;
import kz.chesschicken.compatibility.clapi.api.CursedLegacyAPI;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

//Because being bald, CL-API.
public class APICall implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        if(FabricLoader.getInstance().isModLoaded("api")) {
            CompatibilityAPI.init(new CursedLegacyAPI());
        }
    }
}
