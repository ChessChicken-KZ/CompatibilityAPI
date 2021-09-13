package kz.chesschicken.compatibility.event;

import net.mine_diver.unsafeevents.Event;

public class EventPostInit extends Event {

    private final byte fabricCall;

    public EventPostInit(boolean b) {
        this.fabricCall = (byte) (b ? 0x1 : 0x0);
    }

    public EventPostInit(byte b) {
        this.fabricCall = b;
    }

    public EventPostInit(int i) {
        this.fabricCall = (byte) i;
    }

    public boolean isStraightCall() {
        return this.fabricCall == 0x1;
    }

    @Override
    protected int getEventID() {
        return ID;
    }

    public static final int ID = NEXT_ID.incrementAndGet();
}
