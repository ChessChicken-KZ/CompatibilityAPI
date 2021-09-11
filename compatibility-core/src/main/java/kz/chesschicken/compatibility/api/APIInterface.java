package kz.chesschicken.compatibility.api;

import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;

import java.util.function.IntFunction;

public interface APIInterface {
    String getID();

    BlockBase onBlockInit(InstanceIdentifier identifier, IntFunction<BlockBase> blockBase);

    ItemBase onItemInit(InstanceIdentifier identifier, IntFunction<ItemBase> itemBase);

    void onShapedRecipeInit(ItemInstance result, Object[] ingredients);

    void onShapelessRecipeInit(ItemInstance result, Object[] ingredients);

    void onSmeltingRecipeInit(ItemInstance result, ItemInstance ingredients);

    int onBlockTextureInit(BlockBase blockBase, String s);

    int onItemTextureInit(ItemBase itemBase, String s);
}
