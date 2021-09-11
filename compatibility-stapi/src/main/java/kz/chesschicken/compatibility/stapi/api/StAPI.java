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

public class StAPI implements AbstractAPIInterface
{
    @Override
    public int getBlockID() {
        return BlockRegistry.INSTANCE.getNextSerialID();
    }

    @Override
    public int getItemID() {
        return ItemRegistry.INSTANCE.getNextSerialID();
    }

    @Override
    public void onBlockInit(InstanceIdentifier identifier, BlockBase blockBase) {
        BlockRegistry.INSTANCE.register(StAPICompatibility.from(identifier), blockBase);
    }

    @Override
    public void onItemInit(InstanceIdentifier identifier, ItemBase itemBase) {
        ItemRegistry.INSTANCE.register(StAPICompatibility.from(identifier), itemBase);
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
