package kz.chesschicken.compatibility;

import kz.chesschicken.compatibility.api.APIInterface;
import kz.chesschicken.compatibility.event.EventPreInit;
import kz.chesschicken.compatibility.utils.ASCIIString;
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
    @Getter private static EventBus EventBus;

    /*
     * TODO: LOOK HERE! FIX TILL FEBRUARY PLEASE ALIMA.
     * Should set API in a different way.
     * Still.
     */
    public static void init(APIInterface apiInterface) {
        API = apiInterface;
        EventBus = new EventBus();
        runAPI();
        new ASCIIString((byte) 0xff00, (byte) 4, (byte) 3);
    }

    static void runAPI() {
        LOGGER.info("Searching for possible entrypoints of \"compatibility_mod\".");
        FabricLoader.getInstance().getEntrypointContainers("compatibility_mod", Object.class).forEach(oec -> {
            LOGGER.info("Found entrypoint, registering. [ {} ]", oec.getEntrypoint().getClass().getCanonicalName());
            registerModEvents(oec.getEntrypoint());
        });

        EventBus.post(new EventPreInit());
    }

    static void registerModEvents(@NotNull Object entry) {
        if (entry.getClass() == Class.class)
            EventBus.register((Class<?>) entry);
        else if (entry instanceof Consumer)
            //noinspection unchecked
            EventBus.register((Consumer<? extends Event>) entry);
        else if (entry.getClass() == Method.class)
            EventBus.register((Method) entry);
        else
            EventBus.register(entry);
    }
}
