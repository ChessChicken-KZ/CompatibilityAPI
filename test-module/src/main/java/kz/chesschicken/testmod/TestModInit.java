package kz.chesschicken.testmod;

import kz.chesschicken.compatibility.common.InstanceIdentifier;
import kz.chesschicken.compatibility.common.event.EventBlock;
import kz.chesschicken.compatibility.common.event.EventShapelessRecipe;
import kz.chesschicken.compatibility.common.event.EventTexture;
import kz.chesschicken.testmod.block.BlockExample;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemInstance;

public class TestModInit {
    public static BlockBase testBlock;

    @SuppressWarnings("unused")
    @EventListener
    public void registerBlock(EventBlock event) {
        testBlock = event.register(InstanceIdentifier.create("testmod:folder_block"), value -> new BlockExample(value).setTranslationKey("folder_block"));
    }

    @SuppressWarnings("unused")
    @EventListener
    public void registerTextures(EventTexture event) {
        event.addBlockTexture(testBlock, 0, "/assets/testmod/textures/block/folder_block.png");
    }

    @SuppressWarnings("unused")
    @EventListener
    public void registerRecipes(EventShapelessRecipe event) {
        event.register(new ItemInstance(testBlock), new Object[] {
                new ItemInstance(BlockBase.DIRT)
        });
    }
}
