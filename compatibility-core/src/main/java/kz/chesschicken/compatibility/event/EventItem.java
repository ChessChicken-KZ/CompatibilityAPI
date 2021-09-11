package kz.chesschicken.compatibility.event;

import kz.chesschicken.compatibility.CompatibilityAPI;
import kz.chesschicken.compatibility.api.InstanceIdentifier;
import net.mine_diver.unsafeevents.Event;
import net.minecraft.item.ItemBase;

import java.util.function.IntFunction;

public class EventItem extends Event {

    public ItemBase register(InstanceIdentifier identifier, IntFunction<ItemBase> instance) {
        return CompatibilityAPI.GET_API().onItemInit(identifier, instance);
    }

    @Override
    protected int getEventID() {
        return ID;
    }

    public static final int ID = NEXT_ID.incrementAndGet();
}
