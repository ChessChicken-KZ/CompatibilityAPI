package kz.chesschicken.compatibility.event.test;

import kz.chesschicken.compatibility.CompatibilityAPI;
import kz.chesschicken.compatibility.event.EventBlock;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.mine_diver.unsafeevents.listener.ListenerPriority;

public class EventFund {

    @SuppressWarnings("all")
    @EventListener(priority = ListenerPriority.HIGHEST)
    public void checkInit(EventBlock event) {
        CompatibilityAPI.LOGGER.info("Touched the EventFund code.");
    }
}
