package kz.chesschicken.compatibility.cla.utils;

import io.github.minecraftcursedlegacy.api.registry.Id;
import io.github.minecraftcursedlegacy.api.registry.Translations;
import kz.chesschicken.compatibility.api.InstanceIdentifier;
import lombok.SneakyThrows;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;

public class CursedLegacyApiUtils {
    public static Id from(InstanceIdentifier identifier) {
        return new Id(identifier.toString());
    }

    @SneakyThrows
    public static void loadLangFile(String modid, String lang) {
        if(new File(FabricLoader.getInstance().getModContainer(modid).get().getRootPath().toFile(), "/assets/" + modid + "/lang/" + lang + ".lang").exists())
            Translations.loadLangFile("/assets/" + modid + "/lang/" + lang + ".lang");
    }
}
