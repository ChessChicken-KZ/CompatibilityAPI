package kz.chesschicken.compatibility.stapi;

import kz.chesschicken.compatibility.CompatibilityAPI;
import kz.chesschicken.compatibility.event.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
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
    public void registerRecipes(RecipeRegisterEvent event)
    {
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
    public void registerTextures(TextureRegisterEvent event)
    {
        CompatibilityAPI.EVENT_BUS.post(new EventTexture());
    }

}
