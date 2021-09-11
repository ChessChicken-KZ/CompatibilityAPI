package kz.chesschicken.compatibility.event;

import net.mine_diver.unsafeevents.listener.EventListener;

public class EventFund {

    @SuppressWarnings("all")
    @EventListener
    public void checkInit(CompatibilityEvent.Block event) {
        event.getEventID();
    }
}
