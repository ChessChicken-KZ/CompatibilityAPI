package kz.chesschicken.compatibility.event;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.mine_diver.unsafeevents.listener.ListenerPriority;

public class EventFund {

    @SuppressWarnings("all")
    @EventListener(priority = ListenerPriority.HIGHEST)
    public void checkInit(CompatibilityEvent.Block event) {
        event.getEventID();
    }
}
