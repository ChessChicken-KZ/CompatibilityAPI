package kz.chesschicken.compatibility.stapi.api;

import kz.chesschicken.compatibility.api.AbstractAPIInterface;
import kz.chesschicken.compatibility.api.InstanceIdentifier;
import kz.chesschicken.compatibility.stapi.utils.StAPICompatibility;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.recipe.SmeltingRegistry;
import net.modificationstation.stationapi.api.registry.BlockRegistry;
import net.modificationstation.stationapi.api.registry.ItemRegistry;

import java.util.function.IntFunction;

public class StAPI implements AbstractAPIInterface {

    @Override
    public BlockBase onBlockInit(InstanceIdentifier identifier, IntFunction<BlockBase> blockBase) {
        BlockBase q = blockBase.apply(BlockRegistry.INSTANCE.getNextSerialID());
        BlockRegistry.INSTANCE.register(StAPICompatibility.from(identifier), q);
        return q;
    }

    @Override
    public ItemBase onItemInit(InstanceIdentifier identifier, IntFunction<ItemBase> itemBase) {
        ItemBase q = itemBase.apply(ItemRegistry.INSTANCE.getNextSerialID());
        ItemRegistry.INSTANCE.register(StAPICompatibility.from(identifier), q);
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
}
