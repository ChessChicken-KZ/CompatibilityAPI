package kz.chesschicken.compatibility.stapi.api;

import kz.chesschicken.compatibility.api.APIInterface;
import kz.chesschicken.compatibility.api.InstanceIdentifier;
import kz.chesschicken.compatibility.stapi.utils.StationApiUtils;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.recipe.SmeltingRegistry;
import net.modificationstation.stationapi.api.registry.BlockRegistry;
import net.modificationstation.stationapi.api.registry.ItemRegistry;

import java.util.function.IntFunction;

public class StationAPI implements APIInterface {

    @Override
    public String getID() {
        return "stationapi";
    }

    @Override
    public BlockBase onBlockInit(InstanceIdentifier identifier, IntFunction<BlockBase> blockBase) {
        BlockBase q = blockBase.apply(BlockRegistry.INSTANCE.getNextSerialID());

        //Injecting annotations.
        StationApiUtils.rebuildAnnotations(q.getClass());

        BlockRegistry.INSTANCE.register(StationApiUtils.from(identifier), q);
        return q;
    }

    @Override
    public ItemBase onItemInit(InstanceIdentifier identifier, IntFunction<ItemBase> itemBase) {
        ItemBase q = itemBase.apply(ItemRegistry.INSTANCE.getNextSerialID());
        ItemRegistry.INSTANCE.register(StationApiUtils.from(identifier), q);
        return q;
    }

    @Override
    public void onShapedRecipeInit(ItemInstance result, Object[] ingredients) {
        CraftingRegistry.addShapedRecipe(result, ingredients);
    }

    @Override
    public void onShapelessRecipeInit(ItemInstance result, Object[] ingredients) {
        CraftingRegistry.addShapelessRecipe(result, ingredients);
    }

    @Override
    public void onSmeltingRecipeInit(ItemInstance result, ItemInstance ingredients) {
        SmeltingRegistry.addSmeltingRecipe(result, ingredients);
    }

    @Override
    public int onBlockTextureInit(BlockBase blockBase, String s) {
        int i = Atlases.getStationTerrain().addTexture(s).index;
        blockBase.texture = i;
        return i;
    }

    @Override
    public int onItemTextureInit(ItemBase itemBase, String s) {
        int i = Atlases.getStationGuiItems().addTexture(s).index;
        itemBase.setTexturePosition(i);
        return i;
    }
}
