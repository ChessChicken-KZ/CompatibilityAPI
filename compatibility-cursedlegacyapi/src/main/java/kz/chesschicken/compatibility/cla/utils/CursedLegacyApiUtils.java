package kz.chesschicken.compatibility.cla.utils;

import io.github.minecraftcursedlegacy.accessor.translations.AccessorTranslationStorage;
import io.github.minecraftcursedlegacy.api.registry.Id;
import kz.chesschicken.compatibility.api.InstanceIdentifier;
import kz.chesschicken.compatibility.event.EventNetwork;
import kz.chesschicken.compatibility.utils.network.PacketInstance;
import lombok.SneakyThrows;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.resource.language.TranslationStorage;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CursedLegacyApiUtils {
    public static Id from(InstanceIdentifier identifier) {
        return new Id(identifier.toString());
    }

    @SneakyThrows
    public static void loadLangFile(String modid, String lang) {
        File q = new File(FabricLoader.getInstance().getModContainer(modid).get().getRootPath().toFile(), "/assets/" + modid + "/lang/" + lang + ".lang");
        if(q.exists()) {
            Stream<String> stringStream = Files.lines(q.toPath());
            stringStream.forEach(s -> {
                if(s.trim().length() > 0 && !s.startsWith("#")) {
                    String[] args = s.split("=");
                    ((AccessorTranslationStorage) TranslationStorage.getInstance()).getTranslations().put(modid+":"+args[0], args[1]);
                }
            });
        }
    }

    private static byte[] add(byte[] object, byte add) {
        byte[] toReturn = new byte[object.length + 1];
        System.arraycopy(object, 0, toReturn, 0, object.length);
        System.arraycopy(new byte[] { add }, 0, toReturn, object.length, 1);
        return toReturn;
    }

    //FIX: fuck CL-API.
    @SneakyThrows
    public static PacketInstance byteToSomeMagic(byte[] data) {
        byte[] id = new byte[0];
        byte[] other = new byte[0];

        boolean isIdentifierGot = false;
        for(byte b: data) {
            if(b == -127 && !isIdentifierGot)
                id = add(id, b);
            else
                other = add(other, b);

            isIdentifierGot = !isIdentifierGot && b == -126;
        }

        PacketInstance q = EventNetwork.LIST_TO_REGISTER.get(InstanceIdentifier.create(new String(id))).getConstructor(String.class).newInstance(new String(id));
            q.bytes = other;
        return q;
    }
}
