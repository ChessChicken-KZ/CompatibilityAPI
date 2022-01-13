package kz.chesschicken.compatibility.api.code;

import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.ClassVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ASMHandlerImpl {
    public static List<Class<? extends ClassVisitor>> classVisitorList = new ArrayList<>();
    static Map<byte[], byte[]> classNames = new HashMap<>();
    static Map<byte[], byte[]> methodNames = new HashMap<>();
    static Map<byte[], byte[]> fieldNames = new HashMap<>();

    public static void className(byte[] s, byte[] s1) {
        classNames.putIfAbsent(s, s1);
    }

    public static void methodName(byte[] s, byte[] s1) {
        methodNames.putIfAbsent(s, s1);
    }

    public static void fieldName(byte[] s, byte[] s1) {
        fieldNames.putIfAbsent(s, s1);
    }

    public static byte[] getClassName(byte[] deobf) {
        return FabricLoader.getInstance().isDevelopmentEnvironment() ? deobf : classNames.get(deobf);
    }

    public static byte[] getMethodName(byte[] deobf) {
        return FabricLoader.getInstance().isDevelopmentEnvironment() ? deobf : methodNames.get(deobf);
    }

    public static byte[] getFieldName(byte[] deobf) {
        return FabricLoader.getInstance().isDevelopmentEnvironment() ? deobf : fieldNames.get(deobf);
    }
}
