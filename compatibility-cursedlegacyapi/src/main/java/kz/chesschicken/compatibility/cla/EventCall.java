package kz.chesschicken.compatibility.cla;

import kz.chesschicken.compatibility.CompatibilityAPI;
import kz.chesschicken.compatibility.event.CompatibilityEvent;
import net.fabricmc.api.ModInitializer;

public class EventCall implements ModInitializer {
    @Override
    public void onInitialize() {
        //wait, what
        CompatibilityAPI.EVENT_BUS.post(new CompatibilityEvent.Block());
        CompatibilityAPI.EVENT_BUS.post(new CompatibilityEvent.Item());

        CompatibilityAPI.EVENT_BUS.post(new CompatibilityEvent.ShapedRecipe());
        CompatibilityAPI.EVENT_BUS.post(new CompatibilityEvent.ShapelessRecipe());
        CompatibilityAPI.EVENT_BUS.post(new CompatibilityEvent.SmeltingRecipe());
    }
}
