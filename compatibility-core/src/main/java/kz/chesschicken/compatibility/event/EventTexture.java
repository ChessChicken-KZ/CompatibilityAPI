package kz.chesschicken.compatibility.event;

import kz.chesschicken.compatibility.CompatibilityAPI;
import net.mine_diver.unsafeevents.Event;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;

public class EventTexture extends Event {

    public int addBlockTexture(BlockBase instance, String s) {
        return CompatibilityAPI.getAPI().initBlockTexture(instance, s);
    }

    public int addItemTexture(ItemBase instance, String s) {
        return CompatibilityAPI.getAPI().initItemTexture(instance, s);
    }

    @Override
    protected int getEventID() {
        return ID;
    }

    public static final int ID = NEXT_ID.incrementAndGet();
}
