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
            return CompatibilityAPI.GET_API().onBlockInit(identifier, instance);
        }

        @Override
        protected int getEventID() {
            return ID;
        }

        public static final int ID = NEXT_ID.incrementAndGet();
    }

    public static class Item extends Event {

        public ItemBase register(InstanceIdentifier identifier, IntFunction<ItemBase> instance) {
            return CompatibilityAPI.GET_API().onItemInit(identifier, instance);
        }

        @Override
        protected int getEventID() {
            return ID;
        }

        public static final int ID = NEXT_ID.incrementAndGet();
    }

    public static class ShapedRecipe extends Event {

        public void register(ItemInstance result, Object[] ingredients) {
            CompatibilityAPI.GET_API().onShapedRecipeInit(result, ingredients);
        }

        @Override
        protected int getEventID() {
            return ID;
        }

        public static final int ID = NEXT_ID.incrementAndGet();
    }

    public static class ShapelessRecipe extends Event {

        public void register(ItemInstance result, Object[] ingredients) {
            CompatibilityAPI.GET_API().onShapelessRecipeInit(result, ingredients);
        }

        @Override
        protected int getEventID() {
            return ID;
        }

        public static final int ID = NEXT_ID.incrementAndGet();
    }

    public static class SmeltingRecipe extends Event {

        public void register(ItemInstance result, ItemInstance ingredients) {
            CompatibilityAPI.GET_API().onSmeltingRecipeInit(result, ingredients);
        }

        @Override
        protected int getEventID() {
            return ID;
        }

        public static final int ID = NEXT_ID.incrementAndGet();
    }

    public static class Texture extends Event {

        public int addBlockTexture(BlockBase instance, String s) {
            return CompatibilityAPI.GET_API().onBlockTextureInit(instance, s);
        }

        public int addItemTexture(ItemBase instance, String s) {
            return CompatibilityAPI.GET_API().onItemTextureInit(instance, s);
        }

        @Override
        protected int getEventID() {
            return ID;
        }

        public static final int ID = NEXT_ID.incrementAndGet();
    }
}
