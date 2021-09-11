package kz.chesschicken.compatibility.stapi.api;

import kz.chesschicken.compatibility.api.APIInterface;
import kz.chesschicken.compatibility.api.InstanceIdentifier;
import kz.chesschicken.compatibility.stapi.utils.StAPIUtils;
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
    public BlockBase onBlockInit(InstanceIdentifier identifier, IntFunction<BlockBase> blockBase) {
        BlockBase q = blockBase.apply(BlockRegistry.INSTANCE.getNextSerialID());
        BlockRegistry.INSTANCE.register(StAPIUtils.from(identifier), q);
        return q;
    }

    @Override
    public ItemBase onItemInit(InstanceIdentifier identifier, IntFunction<ItemBase> itemBase) {
        ItemBase q = itemBase.apply(ItemRegistry.INSTANCE.getNextSerialID());
        ItemRegistry.INSTANCE.register(StAPIUtils.from(identifier), q);
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
        return Atlases.getStationTerrain().addTexture(s).index;
    }

    @Override
    public int onItemTextureInit(ItemBase itemBase, String s) {
        return Atlases.getStationGuiItems().addTexture(s).index;
    }
}
