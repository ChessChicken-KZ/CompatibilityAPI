package kz.chesschicken.compatibility.api;

import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;

public interface AbstractAPIInterface {
    public int getBlockID();

    public int getItemID();

    public void onBlockInit(InstanceIdentifier identifier, BlockBase blockBase);

    public void onItemInit(InstanceIdentifier identifier, ItemBase itemBase);

    public void onShapedRecipeInit(ItemInstance result, Object[] ingredients);

    public void onShapelessRecipeInit(ItemInstance result, Object[] ingredients);

    public void onSmeltingRecipeInit(ItemInstance result, ItemInstance ingredients);
}
