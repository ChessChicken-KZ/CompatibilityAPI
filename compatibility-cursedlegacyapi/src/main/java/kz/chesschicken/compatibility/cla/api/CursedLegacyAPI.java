package kz.chesschicken.compatibility.cla.api;

import io.github.minecraftcursedlegacy.api.client.AtlasMap;
import io.github.minecraftcursedlegacy.api.recipe.Recipes;
import io.github.minecraftcursedlegacy.api.registry.Registries;
import io.github.minecraftcursedlegacy.api.registry.TileItems;
import kz.chesschicken.compatibility.api.APIInterface;
import kz.chesschicken.compatibility.api.InstanceIdentifier;
import kz.chesschicken.compatibility.api.tools.UseCustomTileItem;
import kz.chesschicken.compatibility.cla.utils.CursedLegacyApiUtils;
import lombok.SneakyThrows;
import net.minecraft.block.BlockBase;
import net.minecraft.item.Block;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.recipe.SmeltingRecipeRegistry;
import paulevs.corelib.model.Model;
import paulevs.corelib.model.prefab.FullCubeModel;
import paulevs.corelib.registry.ModelRegistry;

import java.lang.reflect.InvocationTargetException;
import java.util.function.IntFunction;

public class CursedLegacyAPI implements APIInterface {

    @Override
    public String getID() {
        return "api"; /* cool */
    }

    @Override
    public BlockBase onBlockInit(InstanceIdentifier identifier, IntFunction<BlockBase> blockBase) {
        BlockBase q = Registries.TILE.register(CursedLegacyApiUtils.from(identifier), blockBase);
        if(q.getClass().isAnnotationPresent(UseCustomTileItem.class))
            TileItems.registerTileItem(CursedLegacyApiUtils.from(identifier), q, value -> {
                try {
                    return q.getClass().getDeclaredAnnotation(UseCustomTileItem.class).value().getConstructor(int.class).newInstance(value);
                } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                    return new Block(value);
                }
            });
        else
            TileItems.registerTileItem(CursedLegacyApiUtils.from(identifier), q);
        return q;
    }

    @Override
    public ItemBase onItemInit(InstanceIdentifier identifier, IntFunction<ItemBase> itemBase) {
        return Registries.ITEM_TYPE.register(CursedLegacyApiUtils.from(identifier), itemBase);
    }

    @Override
    public void onShapedRecipeInit(ItemInstance result, Object[] ingredients) {
        Recipes.addShapedRecipe(result, ingredients);
    }

    @Override
    public void onShapelessRecipeInit(ItemInstance result, Object[] ingredients) {
        Recipes.addShapelessRecipe(result, ingredients);
    }

    @Override
    public void onSmeltingRecipeInit(ItemInstance result, ItemInstance ingredients) {
        SmeltingRecipeRegistry.getInstance().addSmeltingRecipe(result.itemId, ingredients);
    }

    /**
     * Highly not recommended, still W.I.P.
     * Try using other methods, like automatic texturing from CLA.
     * @param blockBase BlockBase instance.
     * @param s Texture path.
     * @return -1
     */
    @Deprecated
    @Override
    public int onBlockTextureInit(BlockBase blockBase, String s) {
        Model model = new FullCubeModel(s);
        ModelRegistry.addTileModel(blockBase, model);
        ModelRegistry.addItemModel(ItemBase.byId[blockBase.id], model);
        return -1;
    }

    @Override
    public int onItemTextureInit(ItemBase itemBase, String s) {
        return AtlasMap.registerSprite(itemBase.id, 0, s);
    }
}
