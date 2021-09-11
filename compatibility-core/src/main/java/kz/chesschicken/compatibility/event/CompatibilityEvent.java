package kz.chesschicken.compatibility.event;

import kz.chesschicken.compatibility.CompatibilityAPI;
import kz.chesschicken.compatibility.api.InstanceIdentifier;
import net.mine_diver.unsafeevents.Event;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;

import java.util.function.IntFunction;

public class CompatibilityEvent {
    public static class Block extends Event {

        public BlockBase register(InstanceIdentifier identifier, IntFunction<BlockBase> instance) {
            return CompatibilityAPI.CURRENT_API.onBlockInit(identifier, instance);
        }

        @Override
        protected int getEventID() {
            return ID;
        }

        public static final int ID = NEXT_ID.incrementAndGet();
    }

    public static class Item extends Event {

        public ItemBase register(InstanceIdentifier identifier, IntFunction<ItemBase> instance) {
            return CompatibilityAPI.CURRENT_API.onItemInit(identifier, instance);
        }

        @Override
        protected int getEventID() {
            return ID;
        }

        public static final int ID = NEXT_ID.incrementAndGet();
    }

    public static class ShapedRecipe extends Event {

        public void register(ItemInstance result, Object[] ingredients) {
            CompatibilityAPI.CURRENT_API.onShapedRecipeInit(result, ingredients);
        }

        @Override
        protected int getEventID() {
            return ID;
        }

        public static final int ID = NEXT_ID.incrementAndGet();
    }

    public static class ShapelessRecipe extends Event {

        public void register(ItemInstance result, Object[] ingredients) {
            CompatibilityAPI.CURRENT_API.onShapelessRecipeInit(result, ingredients);
        }

        @Override
        protected int getEventID() {
            return ID;
        }

        public static final int ID = NEXT_ID.incrementAndGet();
    }

    public static class SmeltingRecipe extends Event {

        public void register(ItemInstance result, ItemInstance ingredients) {
            CompatibilityAPI.CURRENT_API.onSmeltingRecipeInit(result, ingredients);
        }

        @Override
        protected int getEventID() {
            return ID;
        }

        public static final int ID = NEXT_ID.incrementAndGet();
    }
}
