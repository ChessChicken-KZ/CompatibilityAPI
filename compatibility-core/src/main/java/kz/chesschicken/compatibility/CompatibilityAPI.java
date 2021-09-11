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
    private static APIInterface CURRENT_API = null;
    public static final Logger LOGGER = LogManager.getLogger("CompatibilityAPI");
    public static final EventBus EVENT_BUS = new EventBus();

    public static APIInterface GET_API() {
        return CURRENT_API;
    }

    public static void SET_API(APIInterface apiInterface) {
        /* StationAPI is based. */
        if(CURRENT_API == null || apiInterface.getID().equals("stationapi"))
            CURRENT_API = apiInterface;
    }


    public void onPreLaunch() {
        LOGGER.info("Searching for possible mods.");
        FabricLoader.getInstance().getEntrypointContainers("compatibility_mod", Object.class).forEach(oec -> {
            LOGGER.info("Found entrypoint(s) inside " + oec.getProvider().getMetadata().getId() + " mod.");
            registerModEvents(oec.getEntrypoint());
        });

        if(CURRENT_API == null) {
            throw new RuntimeException("No API found! Please install StAPI nor CursedLegacyApi nor Beta-Essentials...");
        }
    }

    private void registerModEvents(Object entry) {
        if (entry.getClass() == Class.class)
            EVENT_BUS.register((Class<?>) entry);
        else if (entry instanceof Consumer)
            EVENT_BUS.register((Consumer<? extends Event>) entry);
        else if (entry.getClass() == Method.class)
            EVENT_BUS.register((Method) entry);
        else
            EVENT_BUS.register(entry);
    }



}
