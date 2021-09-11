package kz.chesschicken.compatibility.api.tools;

import net.minecraft.item.Block;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface UseCustomTileItem {
    Class<? extends Block> value();
}
