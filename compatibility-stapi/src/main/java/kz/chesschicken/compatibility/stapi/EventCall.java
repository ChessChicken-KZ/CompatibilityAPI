package kz.chesschicken.compatibility.stapi;

import kz.chesschicken.compatibility.CompatibilityAPI;
import kz.chesschicken.compatibility.event.CompatibilityEvent;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.registry.Identifier;

public class EventCall {

    @SuppressWarnings("unused")
    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        CompatibilityAPI.LOGGER.info("Called blocks register event.");
        CompatibilityAPI.EVENT_BUS.post(new CompatibilityEvent.Block());
    }

    @SuppressWarnings("unused")
    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        CompatibilityAPI.LOGGER.info("Called items register event.");
        CompatibilityAPI.EVENT_BUS.post(new CompatibilityEvent.Item());
    }

    @SuppressWarnings("unused")
    @EventListener
    public void registerRecipes(RecipeRegisterEvent event)
    {
        CompatibilityAPI.LOGGER.info("Called recipes register event." + (event.recipeId));
        Identifier type = event.recipeId;
        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type())
            CompatibilityAPI.EVENT_BUS.post(new CompatibilityEvent.ShapedRecipe());

        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type())
            CompatibilityAPI.EVENT_BUS.post(new CompatibilityEvent.ShapelessRecipe());

        if (type == RecipeRegisterEvent.Vanilla.SMELTING.type())
            CompatibilityAPI.EVENT_BUS.post(new CompatibilityEvent.SmeltingRecipe());
    }

    @SuppressWarnings("unused")
    @EventListener
    public void registerTextures(TextureRegisterEvent event)
    {
        CompatibilityAPI.EVENT_BUS.post(new CompatibilityEvent.Texture());
    }

}
