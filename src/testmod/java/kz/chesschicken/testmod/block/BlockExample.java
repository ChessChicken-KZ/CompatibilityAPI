package kz.chesschicken.testmod.block;

import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;

public class BlockExample extends BlockBase {
    public BlockExample(int id) {
        super(id, Material.WOOD);
    }

    @Override
    public int getTextureForSide(int side, int meta) {
        return 6;
    }
}
