package kz.chesschicken.compatibility.mixin;

import io.github.minecraftcursedlegacy.api.recipe.Recipes;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Recipes.class)
public class MixinRecipes {

    @Inject(method = "addShapedRecipe", at = @At("HEAD"), cancellable = true, remap = false)
    private static void redirect_addShapedRecipe(ItemInstance result, Object[] recipe, CallbackInfo ci)
    {
        CraftingRegistry.addShapedRecipe(result, recipe);
        ci.cancel();
    }

    @Inject(method = "addShapelessRecipe", at = @At("HEAD"), cancellable = true, remap = false)
    private static void redirect_addShapelessRecipe(ItemInstance result, Object[] ingredients, CallbackInfo ci)
    {
        CraftingRegistry.addShapelessRecipe(result, ingredients);
        ci.cancel();
    }
}
