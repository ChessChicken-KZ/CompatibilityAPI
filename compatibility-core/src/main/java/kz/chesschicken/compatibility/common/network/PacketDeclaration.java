package kz.chesschicken.compatibility.common.network;

import kz.chesschicken.compatibility.common.InstanceIdentifier;
import kz.chesschicken.compatibility.common.event.EventNetwork;
import lombok.SneakyThrows;
import net.minecraft.network.PacketHandler;
import net.minecraft.packet.AbstractPacket;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class PacketDeclaration extends AbstractPacket {
    public InstanceIdentifier identifier;

    public byte[] bytes;
    public short[] shorts;
    public int[] integers;
    public char[] chars;
    public float[] floats;
    public double[] doubles;
    public long[] longs;

    public PacketDeclaration(InstanceIdentifier i) {
        this.identifier = i;
    }

    @SneakyThrows
    @Override
    public void read(DataInputStream in) {
        this.identifier = InstanceIdentifier.create(readString(in, 32767));

        short s = in.readShort();

        byte[] resulting = new byte[] {
                (byte) (((s & 64) != 0) ? 1 : 0),
                (byte) (((s & 32) != 0) ? 1 : 0),
                (byte) (((s & 16) != 0) ? 1 : 0),
                (byte) (((s & 8) != 0) ? 1 : 0),
                (byte) (((s & 4) != 0) ? 1 : 0),
                (byte) (((s & 2) != 0) ? 1 : 0),
                (byte) (((s & 1) != 0) ? 1 : 0),
        };

        //BYTES
        if(resulting[0] == 1) {
            int l = in.readInt();
            bytes = new byte[l];
            for(int i = 0; i < l; i++)
                bytes[i] = in.readByte();
        }

        //SHORTS
        if(resulting[1] == 1) {
            int l = in.readInt();
            shorts = new short[l];
            for(int i = 0; i < l; i++)
                shorts[i] = in.readShort();
        }

        //INTEGERS
        if(resulting[2] == 1) {
            int l = in.readInt();
            integers = new int[l];
            for(int i = 0; i < l; i++)
                integers[i] = in.readInt();
        }

        //CHARS
        if(resulting[3] == 1) {
            int l = in.readInt();
            chars = new char[l];
            for(int i = 0; i < l; i++)
                chars[i] = in.readChar();
        }

        //FLOATS
        if(resulting[4] == 1) {
            int l = in.readInt();
            floats = new float[l];
            for(int i = 0; i < l; i++)
                floats[i] = in.readFloat();
        }

        //DOUBLES
        if(resulting[5] == 1) {
            int l = in.readInt();
            doubles = new double[l];
            for(int i = 0; i < l; i++)
                doubles[i] = in.readDouble();
        }

        //LONGS
        if(resulting[6] == 1) {
            int l = in.readInt();
            longs = new long[l];
            for(int i = 0; i < l; i++)
                longs[i] = in.readLong();
        }

    }

    @SneakyThrows
    @Override
    public void write(DataOutputStream out) {
        writeString(identifier.toString(), out);
        byte[] resulting = new byte[] {
                (byte) (bytes != null ? 1 : 0), //64
                (byte) (shorts != null ? 1 : 0), //32
                (byte) (integers != null ? 1 : 0), //16
                (byte) (chars != null ? 1 : 0), //8
                (byte) (floats != null ? 1 : 0), //4
                (byte) (doubles != null ? 1 : 0), //2
                (byte) (longs != null ? 1 : 0), //1
        };
        out.writeShort(
                (resulting[0] == 1 ? 64 : 0) +
                        (resulting[1] == 1 ? 32 : 0) +
                        (resulting[2] == 1 ? 16 : 0) +
                        (resulting[3] == 1 ? 8 : 0) +
                        (resulting[4] == 1 ? 4 : 0) +
                        (resulting[5] == 1 ? 2 : 0) +
                        (resulting[6] == 1 ? 1 : 0)
        );

        //BYTES
        if(resulting[0] == 1) {
            out.writeInt(bytes.length);
            for(byte b : bytes)
                out.writeByte(b);
        }

        //SHORTS
        if(resulting[1] == 1) {
            out.writeInt(shorts.length);
            for(short s : shorts)
                out.writeShort(s);
        }

        //INTEGERS
        if(resulting[2] == 2) {
            out.writeInt(integers.length);
            for(int i : integers)
                out.writeInt(i);
        }

        //CHARS
        if(resulting[3] == 1) {
            out.writeInt(chars.length);
            for(char c : chars)
                out.writeChar(c);
        }

        //FLOATS
        if(resulting[4] == 1) {
            out.writeInt(floats.length);
            for(float f : floats)
                out.writeFloat(f);
        }

        //DOUBLES
        if(resulting[5] == 1) {
            out.writeInt(doubles.length);
            for(double d : doubles)
                out.writeDouble(d);
        }

        //LONGS
        if(resulting[6] == 1) {
            out.writeInt(longs.length);
            for(long l : longs)
                out.writeLong(l);
        }

    }

    @Override
    public void apply(PacketHandler handler) {
        if(EventNetwork.LIST_TO_REGISTER.containsKey(identifier))
            EventNetwork.LIST_TO_REGISTER.get(identifier).accept(identifier, handler, this);
        else
            System.out.println("Unknown, broken or unregistered packet: " + identifier);
    }

    @Override
    public int length() {
        return (4 + identifier.toString().toCharArray().length * 2) + 2 + //INT + STRING + ONE SHORT
                (bytes != null ? 4 + bytes.length : 0) + //INT + BYTES
                (shorts != null ? 4 + shorts.length * 2 : 0) + //INT + SHORTS
                (integers != null ? 4 + integers.length * 4 : 0) + //INT + INTEGERS
                (chars != null ? 4 + chars.length * 2 : 0) + //INT + CHARS
                (floats != null ? 4 + floats.length * 4 : 0) + //INT + FLOATS
                (doubles != null ? 4 + doubles.length * 8 : 0) + //INT + DOUBLES
                (longs != null ? 4 + longs.length * 8 : 0); //INT + LONGS
    }
}
