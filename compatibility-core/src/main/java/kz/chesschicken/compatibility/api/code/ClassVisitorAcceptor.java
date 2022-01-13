package kz.chesschicken.compatibility.api.code;

import org.objectweb.asm.Opcodes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassVisitorAcceptor {
    String targetClassDeobf();

    String targetClassMapped();

    int opcodeASM_VERSION() default Opcodes.ASM8;
}
