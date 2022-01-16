package kz.chesschicken.compatibility.utils.api;

import kz.chesschicken.compatibility.api.code.ASMTransformHelper;
import kz.chesschicken.compatibility.api.code.ClassVisitorAcceptor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.nio.charset.StandardCharsets;

@ClassVisitorAcceptor(
        targetClassDeobf = "net/minecraft/util/maths/MathHelper",
        targetClassMapped = "net/minecraft/class_189"
)
public class MathHelperClassVisitor extends ClassVisitor {
    public MathHelperClassVisitor(int a, ClassVisitor classVisitor) {
        super(a, classVisitor);

        ASMTransformHelper.className(
                // sin
                new byte[] { 115, 105, 110 },
                // method_644
                new byte[] { 109, 101, 116, 104, 111, 100, 95, 54, 52, 52 }
        );

        ASMTransformHelper.className(
                // cos
                new byte[] { 99, 111, 115 },
                // method_646
                new byte[] { 109, 101, 116, 104, 111, 100, 95, 54, 52, 54 }
        );
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        byte[] methodName = name.getBytes(StandardCharsets.US_ASCII);
        //sin
        if(methodName == ASMTransformHelper.getClassName(new byte[] { 115, 105, 110 }))
            return new OverrideSin(access, cv.visitMethod(access, name, descriptor, signature, exceptions));

        if(methodName == ASMTransformHelper.getMethodName(new byte[] { 99, 111, 115 }))
            return new OverrideCos(access, cv.visitMethod(access, name, descriptor, signature, exceptions));

        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    public static class OverrideSin extends MethodVisitor {
        public OverrideSin(int api, MethodVisitor methodVisitor) {
            super(api, methodVisitor);
        }

        @Override
        public void visitCode() {
            mv.visitCode();
            Label L0 = new Label();
            mv.visitLabel(L0); //Switch to L0.
            mv.visitLineNumber(25, L0);
            mv.visitVarInsn(Opcodes.FLOAD, 0); //Loading float variable.
            mv.visitLdcInsn(new Float("100.0")); //Loading constant float value.
            mv.visitInsn(Opcodes.FMUL); //Multiplying floats together.
            mv.visitLdcInsn(new Float("0.5")); //Loading constant float value.
            mv.visitInsn(Opcodes.FADD); //Adding floats together.
            mv.visitInsn(Opcodes.F2I); //Converting float to int.
            mv.visitMethodInsn( //Calling a specific method with give int as argument.
                    Opcodes.INVOKESTATIC,
                    "kz/chesschicken/compatibility/utils/api/MathHelperClassVisitor",
                    "sinLookup", "(I)F", false
            );
            mv.visitInsn(Opcodes.FRETURN); //Returning the value.
            Label L1 = new Label();
            mv.visitLabel(L1); //Switch to L1.
            mv.visitLocalVariable("a", "F", null, L0, L1, 0); //Some descriptor.
        }

        @Override
        public void visitMaxs(int maxStack, int maxLocals) {
            mv.visitMaxs(2, 1);
        }
    }

    public static class OverrideCos extends MethodVisitor {

        public OverrideCos(int api, MethodVisitor methodVisitor) {
            super(api, methodVisitor);
        }

        @Override
        public void visitMaxs(int maxStack, int maxLocals) {
            super.visitMaxs(2, 1);
        }

        @Override
        public void visitCode() {
            mv.visitCode();
            Label L0 = new Label();
            mv.visitLabel(L0); //Switch to L0.
            mv.visitLineNumber(29, L0);
            mv.visitVarInsn(Opcodes.FLOAD, 0); //Loading float variable.
            mv.visitLdcInsn(new Float("90.0")); //Loading constant float value.
            mv.visitInsn(Opcodes.FADD); //Adding floats together.
            mv.visitLdcInsn(new Float("100.0")); //Loading constant float value.
            mv.visitInsn(Opcodes.FMUL); //Multiply floats together.
            mv.visitLdcInsn(new Float("0.5")); //Loading constant float value.
            mv.visitInsn(Opcodes.FADD); //Adding floats together.
            mv.visitInsn(Opcodes.F2I); //Converting float to int.
            mv.visitMethodInsn( //Calling a specific method with give int as argument.
                    Opcodes.INVOKESTATIC,
                    "kz/chesschicken/compatibility/utils/api/MathHelperClassVisitor",
                    "sinLookup", "(I)F", false
            );
            mv.visitInsn(Opcodes.FRETURN); //Returning the value.
            Label L1 = new Label();
            mv.visitLabel(L1); //Switch to L1.
            mv.visitLocalVariable("a", "F", null, L0, L1, 0); //Some descriptor.
        }
    }


    static final float[] sin = new float[36000];
    static {
        for (int i = 0; i < sin.length; i++) {
            sin[i] = (float) Math.sin((i * Math.PI) / 18000);
        }
    }

    public static float sinLookup(int a) {
        return a >= 0 ? sin[a % 36000] : -sin[-a % 36000];
    }
}
