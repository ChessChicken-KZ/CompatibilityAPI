package kz.chesschicken.testmod;

import kz.chesschicken.compatibility.CompatibilityAPI;
import kz.chesschicken.compatibility.api.InstanceIdentifier;
import kz.chesschicken.compatibility.event.CompatibilityEvent;
import kz.chesschicken.testmod.block.BlockExample;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemInstance;

public class TestModInit {
    public static BlockBase testBlock;

    @SuppressWarnings("unused")
    @EventListener
    public void registerBlock(CompatibilityEvent.Block event)
    {
        CompatibilityAPI.LOGGER.info("Testmod registering!");
        testBlock = event.register(InstanceIdentifier.create("testmod:test_block"), value -> new BlockExample(value).setTranslationKey("test_block"));
    }

    @SuppressWarnings("unused")
    @EventListener
    public void registerRecipes(CompatibilityEvent.ShapelessRecipe event)
    {
        event.register(new ItemInstance(testBlock), new Object[] {
                new ItemInstance(BlockBase.DIRT)
        });
    }
}
