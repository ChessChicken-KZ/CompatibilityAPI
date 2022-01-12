package kz.chesschicken.compatibility.clapi.api;

import io.github.minecraftcursedlegacy.api.client.AtlasMap;
import io.github.minecraftcursedlegacy.api.recipe.Recipes;
import io.github.minecraftcursedlegacy.api.registry.Registries;
import io.github.minecraftcursedlegacy.api.registry.TileItems;
import kz.chesschicken.compatibility.api.APIInterface;
import kz.chesschicken.compatibility.api.InstanceIdentifier;
import kz.chesschicken.compatibility.api.item.BlockMetaNamed;
import kz.chesschicken.compatibility.clapi.utils.CursedLegacyApiUtils;
import kz.chesschicken.compatibility.utils.UseCustomTileItem;
import kz.chesschicken.compatibility.utils.UseMetaNamedTileItem;
import net.minecraft.block.BlockBase;
import net.minecraft.item.Block;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.recipe.SmeltingRecipeRegistry;

import java.lang.reflect.InvocationTargetException;
import java.util.function.IntFunction;

public class CursedLegacyAPI implements APIInterface {

    @Override
    public String getID() {
        return "api"; /* cool */
    }

    @Override
    public BlockBase initBlock(InstanceIdentifier identifier, IntFunction<BlockBase> blockBase) {
        BlockBase q = Registries.TILE.register(CursedLegacyApiUtils.from(identifier), blockBase);
        if(q.getClass().isAnnotationPresent(UseCustomTileItem.class))
            TileItems.registerTileItem(CursedLegacyApiUtils.from(identifier), q, value -> {
                try {
                    return q.getClass().getDeclaredAnnotation(UseCustomTileItem.class).value().getConstructor(int.class).newInstance(value);
                } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                    return new Block(value);
                }
            });
        else if(q.getClass().isAnnotationPresent(UseMetaNamedTileItem.class))
            TileItems.registerTileItem(CursedLegacyApiUtils.from(identifier), q, value -> new BlockMetaNamed(value, q.getClass().getDeclaredAnnotation(UseMetaNamedTileItem.class).value()));
        else
            TileItems.registerTileItem(CursedLegacyApiUtils.from(identifier), q);
        return q;
    }

    @Override
    public ItemBase initItem(InstanceIdentifier identifier, IntFunction<ItemBase> itemBase) {
        return Registries.ITEM_TYPE.register(CursedLegacyApiUtils.from(identifier), itemBase);
    }

    @Override
    public void initShapedRecipe(ItemInstance result, Object[] ingredients) {
        Recipes.addShapedRecipe(result, ingredients);
    }

    @Override
    public void initShapelessRecipe(ItemInstance result, Object[] ingredients) {
        Recipes.addShapelessRecipe(result, ingredients);
    }

    @Override
    public void initSmeltingRecipe(ItemInstance result, ItemInstance ingredients) {
        SmeltingRecipeRegistry.getInstance().addSmeltingRecipe(result.itemId, ingredients);
    }

    @Override
    public int initBlockTexture(BlockBase blockBase, int meta, String s) {
        /*
         * TODO: CHECK THIS LATER!
         * Too unsure, because the code refers to item atlas.
         * Is there any way to register block sprite in similar way?
         */
        return AtlasMap.registerSprite(new ItemInstance(blockBase, 1, meta), s);
    }

    @Override
    public int initItemTexture(ItemBase itemBase, int meta, String s) {
        return AtlasMap.registerSprite(itemBase.id, meta, s);
    }

}
