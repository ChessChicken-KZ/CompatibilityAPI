package kz.chesschicken.compatibility.common.event;

import kz.chesschicken.compatibility.CompatibilityAPI;
import net.mine_diver.unsafeevents.Event;
import net.minecraft.item.ItemInstance;

public class EventShapedRecipe extends Event {

    public void register(ItemInstance result, Object[] ingredients) {
        CompatibilityAPI.getAPI().initShapedRecipe(result, ingredients);
    }

    @Override
    protected int getEventID() {
        return ID;
    }

    public static final int ID = NEXT_ID.incrementAndGet();
}
