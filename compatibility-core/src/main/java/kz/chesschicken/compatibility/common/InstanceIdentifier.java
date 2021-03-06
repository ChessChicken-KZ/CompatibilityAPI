package kz.chesschicken.compatibility.common;

import net.fabricmc.loader.api.ModContainer;

import java.util.Objects;

public class InstanceIdentifier {
    private final String mod;
    private final String object;

    InstanceIdentifier(String s, String s1) {
        this.mod = s;
        this.object = s1;
    }

    public String toString() {
        return this.mod + ":" + this.object;
    }

    public String getObjectName() {
        return this.object;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        InstanceIdentifier that = (InstanceIdentifier) o;
        return mod.equals(that.mod) && object.equals(that.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mod, object);
    }

    public static InstanceIdentifier create(String s, String s1) {
        return new InstanceIdentifier(s, s1);
    }

    public static InstanceIdentifier create(String s) {
        String s1, s2;

        String[] args = s.split(":");
        if(args.length > 1) {
            s1 = args[0];
            s2 = args[1];
        }
        else {
            s1 = "minecraft";
            s2 = s;
        }

        return new InstanceIdentifier(s1, s2);
    }

    public static InstanceIdentifier create(ModContainer modContainer, String s) {
        return new InstanceIdentifier(modContainer.getMetadata().getId(), s);
    }
}
