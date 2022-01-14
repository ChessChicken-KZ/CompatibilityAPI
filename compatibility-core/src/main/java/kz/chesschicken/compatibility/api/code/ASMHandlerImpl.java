package kz.chesschicken.compatibility.api.code;

import kz.chesschicken.compatibility.utils.cursedjava.TDBArray;
import net.fabricmc.loader.api.FabricLoader;

public class ASMHandlerImpl {
    static TDBArray classNames = new TDBArray();
    static TDBArray methodNames = new TDBArray();
    static TDBArray fieldNames = new TDBArray();

    public static void className(byte[] s, byte[] s1) {
        classNames.put(s, s1);
    }

    public static void methodName(byte[] s, byte[] s1) {
        methodNames.put(s, s1);
    }

    public static void fieldName(byte[] s, byte[] s1) {
        fieldNames.put(s, s1);
    }

    public static byte[] getClassName(byte[] deobf) {
        return FabricLoader.getInstance().isDevelopmentEnvironment() ? deobf : classNames.getByKeyArray(deobf);
    }

    public static byte[] getMethodName(byte[] deobf) {
        return FabricLoader.getInstance().isDevelopmentEnvironment() ? deobf : methodNames.getByKeyArray(deobf);
    }

    public static byte[] getFieldName(byte[] deobf) {
        return FabricLoader.getInstance().isDevelopmentEnvironment() ? deobf : fieldNames.getByKeyArray(deobf);
    }
}
