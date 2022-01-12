package kz.chesschicken.compatibility.event;

import kz.chesschicken.compatibility.CompatibilityAPI;
import net.mine_diver.unsafeevents.Event;
import net.minecraft.item.ItemInstance;

public class EventSmeltingRecipe extends Event {

    public void register(ItemInstance result, ItemInstance ingredients) {
        CompatibilityAPI.getAPI().initSmeltingRecipe(result, ingredients);
    }

    @Override
    protected int getEventID() {
        return ID;
    }

    public static final int ID = NEXT_ID.incrementAndGet();
}
