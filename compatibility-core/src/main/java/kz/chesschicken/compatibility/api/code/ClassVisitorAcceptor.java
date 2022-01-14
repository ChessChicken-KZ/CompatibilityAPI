package kz.chesschicken.compatibility.api.code;

import org.objectweb.asm.Opcodes;

import java.lang.annotation.*;

/**
 * Special annotation for a class type, that is going to be accepted by {@link EventASMHandler#register(Class)}.<p>
 * Must be implemented, or else expect {@link RuntimeException}.
 *
 * @author ChessChicken-KZ
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ClassVisitorAcceptor {
    String targetClassDeobf();

    String targetClassMapped();

    int opcodeASM_VERSION() default Opcodes.ASM8;
}
