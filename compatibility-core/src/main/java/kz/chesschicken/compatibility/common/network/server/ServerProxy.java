package kz.chesschicken.compatibility.common.network.server;

import kz.chesschicken.compatibility.common.network.INetworkProxy;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.player.ServerPlayer;
import net.minecraft.packet.AbstractPacket;

@Environment(EnvType.SERVER)
public class ServerProxy implements INetworkProxy {
    @Override
    public void queuePacket(PlayerBase playerBase, AbstractPacket packet) {
        ((ServerPlayer) playerBase).packetHandler.send(packet);
    }
}
