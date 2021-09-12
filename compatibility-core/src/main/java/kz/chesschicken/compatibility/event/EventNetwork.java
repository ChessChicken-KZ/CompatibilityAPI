package kz.chesschicken.compatibility.event;

import kz.chesschicken.compatibility.api.InstanceIdentifier;
import kz.chesschicken.compatibility.utils.network.PacketInstance;
import net.mine_diver.unsafeevents.Event;

import java.util.HashMap;
import java.util.Map;

public class EventNetwork extends Event {
    public static Map<InstanceIdentifier, PacketInstance> LIST_TO_REGISTER = new HashMap<>();

    public void register(PacketInstance i) {
        LIST_TO_REGISTER.put(i.getIdentifier(), i);
    }

    @Override
    protected int getEventID() {
        return ID;
    }

    public static final int ID = NEXT_ID.incrementAndGet();
}