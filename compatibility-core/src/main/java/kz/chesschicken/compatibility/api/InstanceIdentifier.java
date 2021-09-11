package kz.chesschicken.compatibility.api;

import net.fabricmc.loader.api.ModContainer;

public class InstanceIdentifier {
    private final String mod;
    private final String object;
    private InstanceIdentifier(String s, String s1)
    {
        this.mod = s;
        this.object = s1;
    }

    public String toString() {
        return this.mod + ":" + this.object;
    }

    public String getObjectName() {
        return this.object;
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
