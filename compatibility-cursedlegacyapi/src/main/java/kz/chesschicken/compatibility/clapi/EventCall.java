package kz.chesschicken.compatibility.clapi;

import kz.chesschicken.compatibility.CompatibilityAPI;
import kz.chesschicken.compatibility.clapi.utils.CursedLegacyApiUtils;
import kz.chesschicken.compatibility.common.event.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

public class EventCall implements ModInitializer {
    @Override
    public void onInitialize() {
        CompatibilityAPI.getEventBus().post(new EventInit());

        CompatibilityAPI.getEventBus().post(new EventBlock());
        CompatibilityAPI.getEventBus().post(new EventItem());

        CompatibilityAPI.getEventBus().post(new EventTexture());

        CompatibilityAPI.getEventBus().post(new EventShapedRecipe());
        CompatibilityAPI.getEventBus().post(new EventShapelessRecipe());
        CompatibilityAPI.getEventBus().post(new EventSmeltingRecipe());

        CompatibilityAPI.getEventBus().post(new EventNetwork());

        for(ModContainer mod : FabricLoader.getInstance().getAllMods())
            if(mod.getMetadata().containsCustomValue("compatibility:lang_file"))
                CursedLegacyApiUtils.loadLangFile(mod.getMetadata().getId(), mod.getMetadata().getCustomValue("compatibility:lang_file").getAsString());

        CompatibilityAPI.getEventBus().post(new EventPostInit(0x0));
    }
}
