package kz.chesschicken.compatibility.cla.api;

import io.github.minecraftcursedlegacy.api.recipe.Recipes;
import io.github.minecraftcursedlegacy.api.registry.Registries;
import kz.chesschicken.compatibility.api.AbstractAPIInterface;
import kz.chesschicken.compatibility.api.InstanceIdentifier;
import kz.chesschicken.compatibility.cla.utils.CLACompatibility;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;

import java.util.function.IntFunction;

public class CursedLegacyAPI implements AbstractAPIInterface {

    @Override
    public BlockBase onBlockInit(InstanceIdentifier identifier, IntFunction<BlockBase> blockBase) {
        return Registries.TILE.register(CLACompatibility.from(identifier), blockBase);
    }

    @Override
    public ItemBase onItemInit(InstanceIdentifier identifier, IntFunction<ItemBase> itemBase) {
        return Registries.ITEM_TYPE.register(CLACompatibility.from(identifier), itemBase);
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
}
