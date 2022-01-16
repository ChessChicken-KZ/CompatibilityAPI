package kz.chesschicken.compatibility.utils.api;

import kz.chesschicken.compatibility.api.code.EventASMTransformer;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.mine_diver.unsafeevents.listener.ListenerPriority;

public class EventASMImpl {

    @SuppressWarnings("unused")
    @EventListener(priority = ListenerPriority.HIGHEST)
    public void initASMByteCodeTransformation(EventASMTransformer event) {
        event.register(MathHelperClassVisitor.class);
    }

}
