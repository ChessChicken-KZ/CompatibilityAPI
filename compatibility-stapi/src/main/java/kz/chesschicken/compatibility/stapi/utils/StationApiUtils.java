package kz.chesschicken.compatibility.stapi.utils;

import kz.chesschicken.compatibility.common.InstanceIdentifier;
import kz.chesschicken.compatibility.common.block.UseCustomTileItem;
import kz.chesschicken.compatibility.common.block.UseMetaNamedTileItem;
import kz.chesschicken.compatibility.utils.cursedjava.AnnotationReflects;
import kz.chesschicken.compatibility.utils.cursedjava.InitAndApply;
import net.modificationstation.stationapi.api.block.HasCustomBlockItemFactory;
import net.modificationstation.stationapi.api.block.HasMetaNamedBlockItem;
import net.modificationstation.stationapi.api.registry.Identifier;

import java.util.HashMap;


public class StationApiUtils {
    public static Identifier from(InstanceIdentifier identifier) {
        return Identifier.of(identifier.toString());
    }

    public static void rebuildAnnotations(Class<?> classToRebuild) {
        if(classToRebuild.isAnnotationPresent(UseMetaNamedTileItem.class))
            AnnotationReflects.putAnnotation(
                    classToRebuild,
                    HasMetaNamedBlockItem.class,
                    new HashMap<>()
            );

        if(classToRebuild.isAnnotationPresent(UseCustomTileItem.class))
            AnnotationReflects.putAnnotation(
                    classToRebuild,
                    HasCustomBlockItemFactory.class,
                    InitAndApply.apply(
                            new HashMap<>(),
                            values -> values.put("value", classToRebuild.getDeclaredAnnotation(UseCustomTileItem.class).value())
                    )
            );
    }
}
