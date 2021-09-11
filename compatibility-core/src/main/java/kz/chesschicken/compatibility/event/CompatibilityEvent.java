package kz.chesschicken.compatibility.event;

import net.mine_diver.unsafeevents.Event;

public class CompatibilityEvent {
    public static class Block extends Event {

        public void register() {

        }

        @Override
        protected int getEventID() {
            return ID;
        }

        public static final int ID = NEXT_ID.incrementAndGet();
    }

    public static class Item extends Event {

        public void register() {

        }

        @Override
        protected int getEventID() {
            return ID;
        }

        public static final int ID = NEXT_ID.incrementAndGet();
    }
}
