package kz.chesschicken.compatibility.cla.utils;

import io.github.minecraftcursedlegacy.accessor.translations.AccessorTranslationStorage;
import io.github.minecraftcursedlegacy.api.registry.Id;
import kz.chesschicken.compatibility.api.InstanceIdentifier;
import lombok.SneakyThrows;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.resource.language.TranslationStorage;

import java.io.File;
import java.nio.file.Files;
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
}
