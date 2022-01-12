package kz.chesschicken.compatibility.utils.cursedjava;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Great alternative to String object, optimizing a lot of memory.
 * Well, at least it saves some memory thought, a bit.
 */
public class ASCIIString {
    byte[] values;

    public ASCIIString(byte... b) {
        this.values = b;
        if(this.values.length > 256)
            throw new RuntimeException("Too big array! Maximum size: 256.");
    }

    public ASCIIString(int... i) {
        this(convertIntegersToBytes(i));
    }


    static byte[] convertIntegersToBytes(int[] i) {
        byte[] o = new byte[i.length];
        for(int q = 0; q < o.length; q++)
            o[q] = (byte) i[q];
        return o;
    }

    //Empty ASCIIString.
    public ASCIIString() {
        this(new byte[0]);
    }

    public ASCIIString(@Nullable String s) {
        this((s == null ? "null" : s).getBytes(StandardCharsets.US_ASCII));
    }

    public ASCIIString(char[] c) {
        this(new String(c).getBytes(StandardCharsets.US_ASCII));
    }

    /**
     * Allows to combine this ASCIIString with another byte array.
     * @param b Byte array.
     */
    public void append(byte[] b) {
        short lenA = (short) values.length;
        this.values = Arrays.copyOf(values, lenA + b.length);
        System.arraycopy(b, 0, this.values, lenA, b.length);
    }

    /**
     * Allows to combine this ASCIIString with another ASCIIString.
     * @param s ASCIIString instance.
     */
    public void append(@NotNull ASCIIString s) {
        append(s.values);
    }

    /**
     * Get string version of the array through {@link Arrays#toString(byte[])}.
     * @return String instance.
     */
    public String arraysToString() {
        return Arrays.toString(values);
    }

    /**
     * Get string version of the array.
     * @return String instance.
     */
    @Override
    public String toString() {
        return new String(values);
    }

    /**
     * Destroy array. Not necessary, just for case.
     */
    @Override
    protected void finalize() {
        this.values = null;
    }

}
