package kz.chesschicken.compatibility.utils.cursedjava;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Two Dimensional Byte Arrays.
 * Replacement for Map with byte arrays.
 */
public class TDBArray {
    byte[][] first;
    byte[][] second;
    int n = 0;

    public TDBArray() {
        this(32);
    }

    public TDBArray(int capacity) {
        first = new byte[capacity][];
        second = new byte[capacity][];
    }

    public void put(byte[] a, byte[] b) {
        if(first.length < first.length + 1)
            expand();
        int n1 = n++;
        first[n1] = a;
        second[n1] = b;
    }

    void expand() {
        byte[][] newFirst = new byte[first.length + 1][];
        System.arraycopy(first, 0, newFirst, 0, first.length);
        first = newFirst;

        byte[][] newSecond = new byte[second.length + 1][];
        System.arraycopy(second, 0, newSecond, 0, second.length);
        second = newSecond;
    }

    byte[] get(byte[] a, byte[][] fir1, byte[][] sec1) {
        for(int q = 0; q < fir1.length; q++) {
            if(fir1[q][0] != a[0])
                continue;

            if(fir1[q] == a)
                return sec1[q];
        }
        return new byte[0];
    }

    public byte[] getByKeyArray(byte[] a) {
        return this.get(a, first, second);
    }

    public byte[] getByValueArray(byte[] a) {
        return this.get(a, second, first);
    }

    public byte[] getKeyArrayByOrder(int i) {
        return this.first[i];
    }

    public byte[] getValueArrayByOrder(int i) {
        return this.second[i];
    }

    public int size() {
        return this.first.length;
    }


    static Unsafe unsafe;
    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
