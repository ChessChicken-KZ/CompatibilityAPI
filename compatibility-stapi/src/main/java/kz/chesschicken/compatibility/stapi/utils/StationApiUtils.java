package kz.chesschicken.compatibility.stapi.utils;

import kz.chesschicken.compatibility.api.InstanceIdentifier;
import kz.chesschicken.compatibility.utils.UseCustomTileItem;
import kz.chesschicken.compatibility.utils.UseMetaNamedTileItem;
import kz.chesschicken.compatibility.utils.reflection.AnnotationReflects;
import net.modificationstation.stationapi.api.block.HasCustomBlockItemFactory;
import net.modificationstation.stationapi.api.block.HasMetaNamedBlockItem;
import net.modificationstation.stationapi.api.registry.Identifier;

import java.util.HashMap;
import java.util.Map;


public class StationApiUtils {
    public static Identifier from(InstanceIdentifier identifier) {
        return Identifier.of(identifier.toString());
    }

    public static void rebuildAnnotations(Class<?> classToRebuild) {
        if(classToRebuild.isAnnotationPresent(UseMetaNamedTileItem.class))
            AnnotationReflects.putAnnotation(classToRebuild, HasMetaNamedBlockItem.class, new HashMap<>());

        if(classToRebuild.isAnnotationPresent(UseCustomTileItem.class)) {
            Map<String, Object> values = new HashMap<>();
            values.put("value", classToRebuild.getDeclaredAnnotation(UseCustomTileItem.class).value());
            AnnotationReflects.putAnnotation(classToRebuild, HasCustomBlockItemFactory.class, values);
        }
    }
}
