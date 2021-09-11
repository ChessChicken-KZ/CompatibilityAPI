package kz.chesschicken.compatibility.cla.utils;

import io.github.minecraftcursedlegacy.api.registry.Id;
import kz.chesschicken.compatibility.api.InstanceIdentifier;

public class CLAUtils {
    public static Id from(InstanceIdentifier identifier) {
        return new Id(identifier.toString());
    }
}
