package kz.chesschicken.compatibility.stapi;

import kz.chesschicken.compatibility.CompatibilityAPI;
import kz.chesschicken.compatibility.event.*;
import kz.chesschicken.compatibility.stapi.api.StationAPI;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.event.mod.InitEvent;
import net.modificationstation.stationapi.api.event.mod.PostInitEvent;
import net.modificationstation.stationapi.api.event.mod.PreInitEvent;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.MessageListenerRegistryEvent;
import net.modificationstation.stationapi.api.registry.Identifier;

public class EventCall {

    //Fuck me till I can't walk.
    @SuppressWarnings("unused")
    @EventListener
    public void initAPI(PreInitEvent event) {
        CompatibilityAPI.init(new StationAPI());
    }

    @SuppressWarnings("unused")
    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        CompatibilityAPI.getEventBus().post(new EventBlock());
    }

    @SuppressWarnings("unused")
    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        CompatibilityAPI.getEventBus().post(new EventItem());
    }

    @SuppressWarnings("unused")
    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {
        Identifier type = event.recipeId;
        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type())
            CompatibilityAPI.getEventBus().post(new EventShapedRecipe());

        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type())
            CompatibilityAPI.getEventBus().post(new EventShapelessRecipe());

        if (type == RecipeRegisterEvent.Vanilla.SMELTING.type())
            CompatibilityAPI.getEventBus().post(new EventSmeltingRecipe());
    }

    @SuppressWarnings("unused")
    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        CompatibilityAPI.getEventBus().post(new EventTexture());
    }

    @SuppressWarnings("unused")
    @EventListener
    public void requestInit(InitEvent event) {
        CompatibilityAPI.getEventBus().post(new EventInit());
    }

    @SuppressWarnings("unused")
    @EventListener
    public void requestPostInit(PostInitEvent event) {
        CompatibilityAPI.getEventBus().post(new EventPostInit(0x0));
    }

    @SuppressWarnings("unused")
    @EventListener
    public void registerMessageListeners(MessageListenerRegistryEvent messageListenerRegistry) {
        CompatibilityAPI.getEventBus().post(new EventNetwork());
    }
}
