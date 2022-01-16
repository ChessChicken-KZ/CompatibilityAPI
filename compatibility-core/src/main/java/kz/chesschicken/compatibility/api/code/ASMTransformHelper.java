package kz.chesschicken.compatibility.api.code;

import com.chocohead.mm.api.ClassTinkerers;
import kz.chesschicken.compatibility.utils.cursedjava.TDBArray;
import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;

public class ASMTransformHelper {
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

    public static void init() {
        for(Class<? extends ClassVisitor> c : EventASMTransformer.classVisitorList) {
            applyClassVisitor(c);
        }
    }

    static String getTargetClass(ClassVisitorAcceptor acceptor) {
        ASMTransformHelper.className(
                acceptor.targetClassDeobf().getBytes(StandardCharsets.US_ASCII),
                acceptor.targetClassMapped().getBytes(StandardCharsets.US_ASCII)
        );
        return FabricLoader.getInstance().isDevelopmentEnvironment() ? acceptor.targetClassDeobf() : acceptor.targetClassMapped();
    }

    static void applyClassVisitor(Class<? extends ClassVisitor> instanceVisitor) {
        if(!instanceVisitor.isAnnotationPresent(ClassVisitorAcceptor.class))
            throw new RuntimeException("Found registered ClassVisitor without " + ClassVisitorAcceptor.class.getCanonicalName());
        ClassVisitorAcceptor annotation = instanceVisitor.getAnnotation(ClassVisitorAcceptor.class);
        ClassTinkerers.addTransformation(getTargetClass(annotation), classNode -> {
            try {
                ClassReader cr = new ClassReader(getTargetClass(annotation));
                cr.accept(instanceVisitor.getDeclaredConstructor(int.class, ClassVisitor.class).newInstance(annotation.opcodeASM_VERSION(), classNode), 0);
            } catch (IOException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });

    }
}
