package kz.chesschicken.testmod;

import kz.chesschicken.compatibility.CompatibilityAPI;
import kz.chesschicken.compatibility.api.InstanceIdentifier;
import kz.chesschicken.compatibility.event.EventBlock;
import kz.chesschicken.compatibility.event.EventShapelessRecipe;
import kz.chesschicken.testmod.block.BlockExample;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemInstance;

public class TestModInit {
    public static BlockBase testBlock;

    @SuppressWarnings("unused")
    @EventListener
    public void registerBlock(EventBlock event)
    {
        CompatibilityAPI.LOGGER.info("Testmod registering!");
        testBlock = event.register(InstanceIdentifier.create("testmod:test_block"), value -> new BlockExample(value).setTranslationKey("test_block"));
    }

    @SuppressWarnings("unused")
    @EventListener
    public void registerRecipes(EventShapelessRecipe event)
    {
        event.register(new ItemInstance(testBlock), new Object[] {
                new ItemInstance(BlockBase.DIRT)
        });
    }
}
