package kz.chesschicken.compatibility.api.code;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import net.mine_diver.unsafeevents.Event;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;

/*
 * The first to be called event in the game(?).
 */
public class EventASMHandler extends Event {

    @Override
    protected int getEventID() {
        return ID;
    }

    public void register(Class<? extends ClassVisitor> clazz) {
        ASMHandlerImpl.classVisitorList.add(clazz);
    }

    public static void init() {
        for(Class<? extends ClassVisitor> c : ASMHandlerImpl.classVisitorList) {
            apply$REQUIRE_CLASSVISITORACCEPTOR(c);
        }
    }

    static String getTargetClass(ClassVisitorAcceptor acceptor) {
        ASMHandlerImpl.className(
                acceptor.targetClassDeobf().getBytes(StandardCharsets.US_ASCII),
                acceptor.targetClassMapped().getBytes(StandardCharsets.US_ASCII)
        );
        return FabricLoader.getInstance().isDevelopmentEnvironment() ? acceptor.targetClassDeobf() : acceptor.targetClassMapped();
    }

    static void apply$REQUIRE_CLASSVISITORACCEPTOR(Class<? extends ClassVisitor> instanceVisitor) {
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

    public static final int ID = NEXT_ID.incrementAndGet();
}
