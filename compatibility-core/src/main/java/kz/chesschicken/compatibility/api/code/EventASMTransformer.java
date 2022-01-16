package kz.chesschicken.compatibility.api.code;

import net.mine_diver.unsafeevents.Event;
import org.objectweb.asm.ClassVisitor;

import java.util.ArrayList;
import java.util.List;

/*
 * The first to be called event in the game(?).
 */
public class EventASMTransformer extends Event {

    public static List<Class<? extends ClassVisitor>> classVisitorList = new ArrayList<>();

    @Override
    protected int getEventID() {
        return ID;
    }

    public void register(Class<? extends ClassVisitor> clazz) {
        classVisitorList.add(clazz);
    }

    public static final int ID = NEXT_ID.incrementAndGet();
}
