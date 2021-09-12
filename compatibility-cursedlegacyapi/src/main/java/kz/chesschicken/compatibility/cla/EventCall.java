package kz.chesschicken.compatibility.cla;

import kz.chesschicken.compatibility.CompatibilityAPI;
import kz.chesschicken.compatibility.cla.utils.CursedLegacyApiUtils;
import kz.chesschicken.compatibility.event.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

public class EventCall implements ModInitializer {
    @Override
    public void onInitialize() {
        CompatibilityAPI.EVENT_BUS.post(new EventInit());

        CompatibilityAPI.EVENT_BUS.post(new EventBlock());
        CompatibilityAPI.EVENT_BUS.post(new EventItem());

        CompatibilityAPI.EVENT_BUS.post(new EventTexture());

        CompatibilityAPI.EVENT_BUS.post(new EventShapedRecipe());
        CompatibilityAPI.EVENT_BUS.post(new EventShapelessRecipe());
        CompatibilityAPI.EVENT_BUS.post(new EventSmeltingRecipe());

        for(ModContainer modContainer : FabricLoader.getInstance().getAllMods())
            if(modContainer.getMetadata().containsCustomValue("compatibility:lang_file"))
                CursedLegacyApiUtils.loadLangFile(modContainer.getMetadata().getId(), modContainer.getMetadata().getCustomValue("compatibility:lang_file").getAsString());

        CompatibilityAPI.EVENT_BUS.post(new EventPostInit());
    }
}
