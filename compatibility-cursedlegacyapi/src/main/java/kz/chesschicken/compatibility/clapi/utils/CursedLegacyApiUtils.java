package kz.chesschicken.compatibility.clapi.utils;

import io.github.minecraftcursedlegacy.accessor.translations.AccessorTranslationStorage;
import io.github.minecraftcursedlegacy.api.registry.Id;
import kz.chesschicken.compatibility.CompatibilityAPI;
import kz.chesschicken.compatibility.common.InstanceIdentifier;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.resource.language.TranslationStorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class CursedLegacyApiUtils {
    public static Id from(InstanceIdentifier identifier) {
        return new Id(identifier.toString());
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public static void loadLangFile(String modid, String lang) {
        Path q = FabricLoader.getInstance().getModContainer(modid).get().getPath("/assets/" + modid + "/lang/" + lang + ".lang");
        if(Files.exists(q)) {
            try {
                Stream<String> stringStream = Files.lines(q);
                stringStream.forEach(s -> {
                    if (s.trim().length() > 0 && !s.startsWith("#")) {
                        String[] args = s.split("=");
                        ((AccessorTranslationStorage) TranslationStorage.getInstance()).getTranslations().put(modid + ":" + args[0], args[1]);
                    }
                });
            } catch (IOException e) {
                CompatibilityAPI.LOGGER.error(e);
            }
        }
    }
}
