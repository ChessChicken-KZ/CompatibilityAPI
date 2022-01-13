package kz.chesschicken.compatibility.common.event;

import kz.chesschicken.compatibility.CompatibilityAPI;
import net.mine_diver.unsafeevents.Event;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;

public class EventTexture extends Event {

    public int addBlockTexture(BlockBase instance, int meta, String s) {
        return CompatibilityAPI.getAPI().initBlockTexture(instance, meta, s);
    }

    public int addItemTexture(ItemBase instance, int meta, String s) {
        return CompatibilityAPI.getAPI().initItemTexture(instance, meta, s);
    }

    @Override
    protected int getEventID() {
        return ID;
    }

    public static final int ID = NEXT_ID.incrementAndGet();
}
