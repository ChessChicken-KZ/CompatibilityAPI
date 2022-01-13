package kz.chesschicken.compatibility.common.network;

import net.minecraft.entity.player.PlayerBase;
import net.minecraft.packet.AbstractPacket;

public interface INetworkProxy {
    void queuePacket(PlayerBase playerBase, AbstractPacket packet);
}
