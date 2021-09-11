package kz.chesschicken.compatibility;

import kz.chesschicken.compatibility.api.AbstractAPIInterface;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.mine_diver.unsafeevents.Event;
import net.mine_diver.unsafeevents.EventBus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.function.Consumer;


public class CompatibilityAPI implements PreLaunchEntrypoint, ModInitializer {
    public static AbstractAPIInterface CURRENT_API;
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

    @Override
    public void onInitialize() {
        if(CURRENT_API == null)
            throw new NullPointerException("No API found! Please install StAPI nor CursedLegacyApi nor Beta-Essentials");
    }
}
