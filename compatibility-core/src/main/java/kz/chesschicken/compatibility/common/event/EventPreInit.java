package kz.chesschicken.compatibility.common.event;

import net.mine_diver.unsafeevents.Event;

public class EventPreInit extends Event {
    @Override
    protected int getEventID() {
        return ID;
    }

    public static final int ID = NEXT_ID.incrementAndGet();
}

