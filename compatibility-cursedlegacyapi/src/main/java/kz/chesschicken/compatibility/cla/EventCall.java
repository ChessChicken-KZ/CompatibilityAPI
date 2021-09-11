package kz.chesschicken.compatibility.cla;

import kz.chesschicken.compatibility.CompatibilityAPI;
import kz.chesschicken.compatibility.event.*;
import net.fabricmc.api.ModInitializer;

public class EventCall implements ModInitializer {
    @Override
    public void onInitialize() {
        //wait, what
        CompatibilityAPI.LOGGER.info("Initializing mods...");
        CompatibilityAPI.EVENT_BUS.post(new EventBlock());
        CompatibilityAPI.EVENT_BUS.post(new EventItem());

        CompatibilityAPI.EVENT_BUS.post(new EventTexture());

        CompatibilityAPI.EVENT_BUS.post(new EventShapedRecipe());
        CompatibilityAPI.EVENT_BUS.post(new EventShapelessRecipe());
        CompatibilityAPI.EVENT_BUS.post(new EventSmeltingRecipe());
    }
}
