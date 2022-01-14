package kz.chesschicken.compatibility.utils.cursedjava;

//TODO: Add more safe checking code.
/**
 * Same as {@link TDBArray}, but with some asserts and safe checkings.
 */
public class TDBArraySafe extends TDBArray {
    @Override
    public void put(byte[] a, byte[] b) {
        assert a.length > 0 && b.length > 0;
        super.put(a, b);
    }
}
