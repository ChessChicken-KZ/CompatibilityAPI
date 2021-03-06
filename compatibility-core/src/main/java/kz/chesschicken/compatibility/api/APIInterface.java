package kz.chesschicken.compatibility.api;

import kz.chesschicken.compatibility.common.InstanceIdentifier;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;

import java.util.function.IntFunction;

public interface APIInterface {
    String getID();

    BlockBase initBlock(InstanceIdentifier identifier, IntFunction<BlockBase> blockBase);

    ItemBase initItem(InstanceIdentifier identifier, IntFunction<ItemBase> itemBase);

    void initShapedRecipe(ItemInstance result, Object[] ingredients);

    void initShapelessRecipe(ItemInstance result, Object[] ingredients);

    void initSmeltingRecipe(ItemInstance result, ItemInstance ingredients);

    int initBlockTexture(BlockBase blockBase, int meta, String s);

    int initItemTexture(ItemBase itemBase, int meta, String s);
}
