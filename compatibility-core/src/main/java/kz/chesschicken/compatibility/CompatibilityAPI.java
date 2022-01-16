package kz.chesschicken.compatibility;

import kz.chesschicken.compatibility.api.APIInterface;
import kz.chesschicken.compatibility.api.code.ASMTransformHelper;
import kz.chesschicken.compatibility.api.code.EventASMTransformer;
import kz.chesschicken.compatibility.common.event.EventPreInit;
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
    public static final Logger LOGGER = LogManager.getLogger("CompatibilityAPI");

    @Getter private static APIInterface API = null;
    @Getter private static final EventBus EventBus = new EventBus();

    public static void init(APIInterface apiInterface) {
        if(API != null)
            return;
        LOGGER.info("Using {} as an providing interface API.", apiInterface.getID());
        API = apiInterface;
        searchAndSetupMods();
    }

    static void searchAndSetupMods() {
        LOGGER.info("Searching for possible entrypoints of \"compatibility_mod\".");
        FabricLoader.getInstance().getEntrypointContainers("compatibility_mod", Object.class).forEach(oec -> {
            LOGGER.info("Found entrypoint, registering. [ {} ]", oec.getEntrypoint().getClass().getCanonicalName());
            registerModEvents(oec.getEntrypoint());
        });

        EventBus.post(new EventASMTransformer());
        ASMTransformHelper.init();
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
