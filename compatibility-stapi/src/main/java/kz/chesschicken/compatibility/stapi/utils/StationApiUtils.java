package kz.chesschicken.compatibility.stapi.utils;

import kz.chesschicken.compatibility.api.InstanceIdentifier;
import kz.chesschicken.compatibility.event.EventNetwork;
import kz.chesschicken.compatibility.stapi.mixin.AccessorMessage;
import kz.chesschicken.compatibility.utils.UseCustomTileItem;
import kz.chesschicken.compatibility.utils.UseMetaNamedTileItem;
import kz.chesschicken.compatibility.utils.network.PacketInstance;
import kz.chesschicken.compatibility.utils.reflection.AnnotationReflects;
import net.minecraft.entity.player.PlayerBase;
import net.modificationstation.stationapi.api.block.HasCustomBlockItemFactory;
import net.modificationstation.stationapi.api.block.HasMetaNamedBlockItem;
import net.modificationstation.stationapi.api.packet.Message;
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

    public static PacketInstance degrade(InstanceIdentifier i, Message message) {
        PacketInstance sample = new PacketInstance(((AccessorMessage)message).getID().toString()) {
            @Override
            public void handlePacket(PlayerBase playerBase, PacketInstance packetInstance) {
                EventNetwork.LIST_TO_REGISTER.get(i).handlePacket(playerBase, packetInstance);
            }
        };
        sample.bytes = message.bytes;
        sample.shorts = message.shorts;
        sample.ints = message.ints;
        sample.chars = message.chars;
        sample.floats = message.floats;
        sample.doubles = message.doubles;
        sample.longs = message.longs;
        return sample;
    }
}
