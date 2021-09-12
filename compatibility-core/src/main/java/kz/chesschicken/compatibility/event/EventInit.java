package kz.chesschicken.compatibility.event;

import net.mine_diver.unsafeevents.Event;

public class EventInit extends Event {

    @Override
    protected int getEventID() {
        return ID;
    }

    public static final int ID = NEXT_ID.incrementAndGet();
}
