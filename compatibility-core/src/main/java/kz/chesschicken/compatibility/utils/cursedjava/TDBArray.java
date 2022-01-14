package kz.chesschicken.compatibility.utils.cursedjava;

/**
 * <p>Two Dimensional Byte Arrays.</p>
 * <p>Replacement for Map with byte arrays.</p>
 * @author ChessChicken-KZ
 */
public class TDBArray {
    byte[][] aaKey;
    byte[][] aaValue;
    int aa = 0;

    /**
     * Init with initial standard capacity - 32 items.
     */
    public TDBArray() {
        this(32);
    }

    public TDBArray(int capacity) {
        aaKey = new byte[capacity][];
        aaValue = new byte[capacity][];
    }

    /**
     * <p>Allows to store specific value (byte array) by key value (byte array).</p>
     * <p></p>
     * @param a Key byte array.
     * @param b Value byte array.
     */
    public void put(byte[] a, byte[] b) {
        if(aaKey.length < aaKey.length + 1)
            expand();
        int n1 = aa++;
        aaKey[n1] = a;
        aaValue[n1] = b;
    }

    /**
     * Special method for getting value byte array by key byte array.
     * @param a Key byte array.
     * @return Value byte array. <p>If not found, then: <pre>{@code new byte[0];}</pre>.</p>
     */
    public byte[] getByKeyArray(byte[] a) {
        return this.get(a, aaKey, aaValue);
    }

    /**
     * Special method for getting key byte array by value byte array.
     * @param a Value byte array.
     * @return Key byte array. <p>If not found, then: <pre>{@code new byte[0];}</pre>.</p>
     */
    public byte[] getByValueArray(byte[] a) {
        return this.get(a, aaValue, aaKey);
    }

    /**
     * Special method for getting key byte array by its order in {@link TDBArray#aaKey} array.
     * @param i Order of byte array.
     * @throws ArrayIndexOutOfBoundsException Order is lower or higher than array's capacity.
     * @return Key byte array.
     */
    public byte[] getKeyArrayByOrder(int i) {
        return this.aaKey[i];
    }

    /**
     * Special method for getting value byte array by its order in {@link TDBArray#aaValue} array.
     * @param i Order of byte array.
     * @throws ArrayIndexOutOfBoundsException Order is lower or higher than array's capacity.
     * @return Value byte array.
     */
    public byte[] getValueArrayByOrder(int i) {
        return this.aaValue[i];
    }

    /**
     * <p>The actual size of two arrays.</p>
     * <p>In most situations, {@link TDBArray#aaKey} array's length and {@link TDBArray#aaValue} array's length are identical.</p>
     * @return Size of two arrays.
     */
    public int size() {
        return this.aaKey.length;
    }


    void expand() {
        byte[][] newFirst = new byte[aaKey.length + 1][];
        System.arraycopy(aaKey, 0, newFirst, 0, aaKey.length);
        aaKey = newFirst;

        byte[][] newSecond = new byte[aaValue.length + 1][];
        System.arraycopy(aaValue, 0, newSecond, 0, aaValue.length);
        aaValue = newSecond;
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
}
