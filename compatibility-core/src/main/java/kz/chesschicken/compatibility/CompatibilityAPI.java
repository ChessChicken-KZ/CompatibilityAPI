package kz.chesschicken.compatibility;

import kz.chesschicken.compatibility.api.APIInterface;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.mine_diver.unsafeevents.Event;
import net.mine_diver.unsafeevents.EventBus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.function.Consumer;


public class CompatibilityAPI implements PreLaunchEntrypoint {
    public static APIInterface CURRENT_API = null;
    public static final Logger LOGGER = LogManager.getLogger("CompatibilityAPI");
    public static final EventBus EVENT_BUS = new EventBus();

    public void onPreLaunch() {
        LOGGER.info("Searching for possible mods.");

        FabricLoader.getInstance().getEntrypointContainers("compatibility_mod", Object.class).forEach(oec -> {

            CompatibilityAPI.LOGGER.info("comp!init " + oec.getProvider().getMetadata().getName());

            if (oec.getEntrypoint().getClass() == Class.class)
                EVENT_BUS.register((Class<?>) oec.getEntrypoint());
            else if (oec.getEntrypoint() instanceof Consumer)
                EVENT_BUS.register((Consumer<? extends Event>) oec.getEntrypoint());
            else if (oec.getEntrypoint().getClass() == Method.class)
                EVENT_BUS.register((Method) oec.getEntrypoint());
        });

        if(CompatibilityAPI.CURRENT_API == null) {
            throw new RuntimeException("No API found! Please install StAPI nor CursedLegacyApi nor Beta-Essentials...");
        }
    }

}
