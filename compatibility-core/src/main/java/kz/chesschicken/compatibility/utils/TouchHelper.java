package kz.chesschicken.compatibility.utils;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;

public class TouchHelper implements PreLaunchEntrypoint {
    public static Logger LOGGER = LogManager.getLogger("Compatibility");

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private String getInsidePath(String s) {
        return new File(FabricLoader.getInstance().getModContainer("compatibility").get().getRootPath().toFile(), s).toString();
    }

    @Override
    public void onPreLaunch() {
        LOGGER.info("Invoking pre-launch event.");

        //if(FabricLoader.getInstance().isModLoaded("stationapi"))
            //Mixins.addConfiguration(getInsidePath("/assets/compatibility/patches/stapi.compatibility.mixins.json"));
    }
}
