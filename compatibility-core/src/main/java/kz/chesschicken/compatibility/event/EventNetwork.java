package kz.chesschicken.compatibility.event;

import kz.chesschicken.compatibility.api.InstanceIdentifier;
import kz.chesschicken.compatibility.api.network.INetworkProxy;
import kz.chesschicken.compatibility.api.network.PacketDeclaration;
import kz.chesschicken.compatibility.api.network.client.ClientProxy;
import kz.chesschicken.compatibility.api.network.server.ServerProxy;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.mine_diver.unsafeevents.Event;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.network.PacketHandler;
import net.minecraft.packet.AbstractPacket;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.HashMap;
import java.util.Map;

public class EventNetwork extends Event {
    public static Map<InstanceIdentifier, TriConsumer<InstanceIdentifier, PacketHandler, PacketDeclaration>> LIST_TO_REGISTER = new HashMap<>();
    public static INetworkProxy CURRENT_PROXY = FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT ? new ClientProxy() : new ServerProxy();

    public static void queuePacket(PlayerBase playerBase, AbstractPacket instance) {
        CURRENT_PROXY.queuePacket(playerBase, instance);
    }

    public void register(InstanceIdentifier identifier, TriConsumer<InstanceIdentifier, PacketHandler, PacketDeclaration> i) {
        LIST_TO_REGISTER.put(identifier, i);
    }

    @Override
    protected int getEventID() {
        return ID;
    }

    public static final int ID = NEXT_ID.incrementAndGet();
}