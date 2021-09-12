package kz.chesschicken.compatibility.api.item;

import net.minecraft.item.Block;
import net.minecraft.item.ItemInstance;

public class BlockMetaNamed extends Block {
    private String prefix;

    public BlockMetaNamed(int i) {
        super(i);
    }

    public BlockMetaNamed(int i, String s) {
        this(i);
        this.prefix = s;
    }

    public void setPrefix(String s) {
        this.prefix = s;
    }

    @Override
    public String getTranslationKey(ItemInstance item) {
        return super.getTranslationKey() + prefix + item.getDamage();
    }
}
