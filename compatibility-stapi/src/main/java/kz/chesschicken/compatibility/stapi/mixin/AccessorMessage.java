package kz.chesschicken.compatibility.stapi.mixin;

import net.modificationstation.stationapi.api.packet.Message;
import net.modificationstation.stationapi.api.registry.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Message.class)
public interface AccessorMessage {
    @Accessor("identifier")
    Identifier getID();
}
