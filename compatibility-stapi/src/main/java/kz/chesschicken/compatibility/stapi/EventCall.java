package kz.chesschicken.compatibility.stapi;

import kz.chesschicken.compatibility.CompatibilityAPI;
import kz.chesschicken.compatibility.api.InstanceIdentifier;
import kz.chesschicken.compatibility.event.*;
import kz.chesschicken.compatibility.stapi.utils.StationApiUtils;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.event.mod.InitEvent;
import net.modificationstation.stationapi.api.event.mod.PostInitEvent;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.MessageListenerRegistryEvent;
import net.modificationstation.stationapi.api.registry.Identifier;

public class EventCall {

    @SuppressWarnings("unused")
    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        CompatibilityAPI.EVENT_BUS.post(new EventBlock());
    }

    @SuppressWarnings("unused")
    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        CompatibilityAPI.EVENT_BUS.post(new EventItem());
    }

    @SuppressWarnings("unused")
    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {
        Identifier type = event.recipeId;
        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type())
            CompatibilityAPI.EVENT_BUS.post(new EventShapedRecipe());

        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type())
            CompatibilityAPI.EVENT_BUS.post(new EventShapelessRecipe());

        if (type == RecipeRegisterEvent.Vanilla.SMELTING.type())
            CompatibilityAPI.EVENT_BUS.post(new EventSmeltingRecipe());
    }

    @SuppressWarnings("unused")
    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        CompatibilityAPI.EVENT_BUS.post(new EventTexture());
    }

    @SuppressWarnings("unused")
    @EventListener
    public void requestInit(InitEvent event) {
        CompatibilityAPI.EVENT_BUS.post(new EventInit());
    }

    @SuppressWarnings("unused")
    @EventListener
    public void requestPostInit(PostInitEvent event) {
        CompatibilityAPI.EVENT_BUS.post(new EventPostInit());
    }

    @SuppressWarnings("unused")
    @EventListener
    public void registerMessageListeners(MessageListenerRegistryEvent messageListenerRegistry) {
        CompatibilityAPI.EVENT_BUS.post(new EventNetwork());

        for(InstanceIdentifier i : EventNetwork.LIST_TO_REGISTER.keySet())
            messageListenerRegistry.registry.register(StationApiUtils.from(i), (playerBase, message) -> EventNetwork.LIST_TO_REGISTER.get(i).handlePacket(playerBase, StationApiUtils.degrade(i, message)));
    }
}
