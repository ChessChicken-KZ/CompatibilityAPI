package kz.chesschicken.compatibility.api.network.server;

import kz.chesschicken.compatibility.api.network.INetworkProxy;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.player.ServerPlayer;
import net.minecraft.packet.AbstractPacket;

public class ServerProxy implements INetworkProxy {
    @Override
    public void queuePacket(PlayerBase playerBase, AbstractPacket packet) {
        ((ServerPlayer) playerBase).packetHandler.send(packet);
    }
}
