package kz.chesschicken.compatibility.event;

import kz.chesschicken.compatibility.api.InstanceIdentifier;
import kz.chesschicken.compatibility.utils.network.PacketInstance;
import net.mine_diver.unsafeevents.Event;

import java.util.HashMap;
import java.util.Map;

public class EventNetwork extends Event {
    public static Map<InstanceIdentifier, Class<? extends PacketInstance>> LIST_TO_REGISTER = new HashMap<>();

    public static void queuePacket(PacketInstance instance) {
        if(LIST_TO_REGISTER.containsKey(instance.getIdentifier())) {
            //FIXME: Add
        }
    }

    public void register(InstanceIdentifier identifier, Class<? extends PacketInstance> i) {
        LIST_TO_REGISTER.put(identifier, i);
    }

    @Override
    protected int getEventID() {
        return ID;
    }

    public static final int ID = NEXT_ID.incrementAndGet();
}