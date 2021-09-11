package kz.chesschicken.compatibility;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.mine_diver.unsafeevents.Event;
import net.mine_diver.unsafeevents.EventBus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.function.Consumer;


public class CompatibilityAPI implements PreLaunchEntrypoint {
    public static final Logger LOGGER = LogManager.getLogger("Compatibility");
    public static final EventBus EVENT_BUS = new EventBus();

    @Override
    public void onPreLaunch() {
        LOGGER.info("Searching for possible mods.");

        FabricLoader fabricLoader = FabricLoader.getInstance();

        fabricLoader.getEntrypointContainers("compatibility:init", Object.class).forEach(objectEntrypointContainer -> {
            if (objectEntrypointContainer.getEntrypoint().getClass() == Class.class)
                EVENT_BUS.register((Class<?>) objectEntrypointContainer.getEntrypoint());
            else if (objectEntrypointContainer.getEntrypoint() instanceof Consumer)
                EVENT_BUS.register((Consumer<? extends Event>) objectEntrypointContainer.getEntrypoint());
            else if (objectEntrypointContainer.getEntrypoint().getClass() == Method.class)
                EVENT_BUS.register((Method) objectEntrypointContainer.getEntrypoint());
        });
    }
}
