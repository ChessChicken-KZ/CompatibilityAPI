package kz.chesschicken.compatibility.stapi.utils;

import kz.chesschicken.compatibility.api.InstanceIdentifier;
import net.modificationstation.stationapi.api.registry.Identifier;

public class IdentifierStapi {
    public static Identifier from(InstanceIdentifier identifier) {
        return Identifier.of(identifier.toString());
    }
}
