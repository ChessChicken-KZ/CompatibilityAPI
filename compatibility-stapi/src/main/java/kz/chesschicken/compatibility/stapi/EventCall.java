package kz.chesschicken.compatibility.stapi;

import kz.chesschicken.compatibility.CompatibilityAPI;
import kz.chesschicken.compatibility.api.InstanceIdentifier;
import kz.chesschicken.compatibility.event.*;
import kz.chesschicken.compatibility.stapi.utils.StationApiUtils;
import lombok.SneakyThrows;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.entity.player.PlayerBase;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.event.mod.InitEvent;
import net.modificationstation.stationapi.api.event.mod.PostInitEvent;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.MessageListenerRegistryEvent;
import net.modificationstation.stationapi.api.packet.Message;
import net.modificationstation.stationapi.api.registry.Identifier;

import java.lang.reflect.InvocationTargetException;
import java.util.function.BiConsumer;

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
        CompatibilityAPI.EVENT_BUS.post(new EventPostInit(0x0));
    }

    @SuppressWarnings("unused")
    @EventListener
    public void registerMessageListeners(MessageListenerRegistryEvent messageListenerRegistry) {
        CompatibilityAPI.EVENT_BUS.post(new EventNetwork());

        for(InstanceIdentifier i : EventNetwork.LIST_TO_REGISTER.keySet())
            messageListenerRegistry.registry.register(StationApiUtils.from(i), new BiConsumer<PlayerBase, Message>() {
                @SneakyThrows
                @Override
                public void accept(PlayerBase playerBase, Message message) {
                    EventNetwork.LIST_TO_REGISTER.get(i).getConstructor(String.class).newInstance(i.toString()).handlePacket(playerBase, StationApiUtils.simplify(i, message));
                }
            });
    }
}
