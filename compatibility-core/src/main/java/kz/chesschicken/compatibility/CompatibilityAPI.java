package kz.chesschicken.compatibility;

import kz.chesschicken.compatibility.api.APIInterface;
import kz.chesschicken.compatibility.event.EventPreInit;
import lombok.Getter;
import net.fabricmc.loader.api.FabricLoader;
import net.mine_diver.unsafeevents.Event;
import net.mine_diver.unsafeevents.EventBus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.function.Consumer;


public class CompatibilityAPI {
    @Getter private static APIInterface API = null;
    public static final Logger LOGGER = LogManager.getLogger("CompatibilityAPI");
    public static final EventBus EVENT_BUS = new EventBus();

    //TODO: Should set API in a different way.
    public static void setAPI$(APIInterface apiInterface) {
        if(API == null)
            API = apiInterface;
    }

    public static void runAPI() {
        LOGGER.info("Searching for possible mods.");
        FabricLoader.getInstance().getEntrypointContainers("compatibility_mod", Object.class).forEach(oec -> {
            LOGGER.info("Found entrypoint: " + oec.getEntrypoint().getClass().getCanonicalName() + ".");
            registerModEvents(oec.getEntrypoint());
        });

        if(API == null)
            throw new RuntimeException("No API found! Please install StationAPI nor CursedLegacy-API...");

        EVENT_BUS.post(new EventPreInit());
    }

    static void registerModEvents(@NotNull Object entry) {
        if (entry.getClass() == Class.class)
            EVENT_BUS.register((Class<?>) entry);
        else if (entry instanceof Consumer)
            //noinspection unchecked
            EVENT_BUS.register((Consumer<? extends Event>) entry);
        else if (entry.getClass() == Method.class)
            EVENT_BUS.register((Method) entry);
        else
            EVENT_BUS.register(entry);
    }
}
