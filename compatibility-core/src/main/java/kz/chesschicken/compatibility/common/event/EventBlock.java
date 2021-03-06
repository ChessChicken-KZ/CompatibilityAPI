package kz.chesschicken.compatibility.common.event;

import kz.chesschicken.compatibility.CompatibilityAPI;
import kz.chesschicken.compatibility.common.InstanceIdentifier;
import net.mine_diver.unsafeevents.Event;
import net.minecraft.block.BlockBase;

import java.util.function.IntFunction;

public class EventBlock extends Event {
    public BlockBase register(InstanceIdentifier identifier, IntFunction<BlockBase> instance) {
        return CompatibilityAPI.getAPI().initBlock(identifier, instance);
    }

    @Override
    protected int getEventID() {
        return ID;
    }

    public static final int ID = NEXT_ID.incrementAndGet();
}
