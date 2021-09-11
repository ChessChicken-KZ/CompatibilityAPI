package kz.chesschicken.compatibility.event;

import kz.chesschicken.compatibility.CompatibilityAPI;
import kz.chesschicken.compatibility.api.InstanceIdentifier;
import net.mine_diver.unsafeevents.Event;
import net.minecraft.block.BlockBase;

import java.util.function.IntFunction;

public class EventBlock extends Event {

    public BlockBase register(InstanceIdentifier identifier, IntFunction<BlockBase> instance) {
        return CompatibilityAPI.GET_API().onBlockInit(identifier, instance);
    }

    @Override
    protected int getEventID() {
        return ID;
    }

    public static final int ID = NEXT_ID.incrementAndGet();
}
