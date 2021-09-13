package kz.chesschicken.compatibility.utils.network;

import kz.chesschicken.compatibility.api.InstanceIdentifier;
import net.minecraft.entity.player.PlayerBase;

public abstract class PacketInstance {
    public byte[] bytes;
    public short[] shorts;
    public int[] ints;
    public char[] chars;

    public float[] floats;
    public double[] doubles;
    public long[] longs;

    private final InstanceIdentifier identifier;

    public PacketInstance(InstanceIdentifier i) {
        this.identifier = i;
    }

    public PacketInstance(String s) {
        this.identifier = InstanceIdentifier.create(s);
    }

    public InstanceIdentifier getIdentifier() {
        return this.identifier;
    }

    public abstract void handlePacket(PlayerBase playerBase, PacketInstance packetInstance);
}
