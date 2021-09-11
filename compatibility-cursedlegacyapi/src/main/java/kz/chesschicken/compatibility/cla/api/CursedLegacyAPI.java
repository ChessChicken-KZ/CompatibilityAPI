package kz.chesschicken.compatibility.cla.api;

import io.github.minecraftcursedlegacy.api.client.AtlasMap;
import io.github.minecraftcursedlegacy.api.recipe.Recipes;
import io.github.minecraftcursedlegacy.api.registry.Registries;
import kz.chesschicken.compatibility.api.APIInterface;
import kz.chesschicken.compatibility.api.InstanceIdentifier;
import kz.chesschicken.compatibility.cla.utils.CLAUtils;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import paulevs.corelib.model.Model;
import paulevs.corelib.model.prefab.FullCubeModel;
import paulevs.corelib.registry.ModelRegistry;

import java.util.function.IntFunction;

public class CursedLegacyAPI implements APIInterface {

    @Override
    public String getID() {
        return "api"; /* cool */
    }

    @Override
    public BlockBase onBlockInit(InstanceIdentifier identifier, IntFunction<BlockBase> blockBase) {
        return Registries.TILE.register(CLAUtils.from(identifier), blockBase);
    }

    @Override
    public ItemBase onItemInit(InstanceIdentifier identifier, IntFunction<ItemBase> itemBase) {
        return Registries.ITEM_TYPE.register(CLAUtils.from(identifier), itemBase);
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

    }

    /**
     * Highly not recommended, still W.I.P.
     * Try using other methods, like automatic texturing from CLA.
     * @param blockBase BlockBase instance.
     * @param s Texture path.
     * @return -1
     */
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
