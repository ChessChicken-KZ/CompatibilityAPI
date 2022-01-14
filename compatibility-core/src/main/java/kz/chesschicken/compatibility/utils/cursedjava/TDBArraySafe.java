package kz.chesschicken.compatibility.utils.cursedjava;

public class TDBArraySafe extends TDBArray {
    @Override
    public void put(byte[] a, byte[] b) {
        assert a.length > 0 && b.length > 0;
        super.put(a, b);
    }
}
