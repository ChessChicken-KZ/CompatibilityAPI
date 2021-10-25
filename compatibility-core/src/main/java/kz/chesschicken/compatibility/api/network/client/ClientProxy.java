package kz.chesschicken.compatibility.api.network.client;

import kz.chesschicken.compatibility.api.network.INetworkProxy;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.packet.AbstractPacket;

public class ClientProxy implements INetworkProxy {
    @Override
    public void queuePacket(PlayerBase playerBase, AbstractPacket packet) {
        if(playerBase.level != null) {
            if(playerBase.level.isClient)
                ((Minecraft)FabricLoader.getInstance().getGameInstance()).getNetworkHandler().sendPacket(packet);
            else
                packet.apply(((Minecraft)FabricLoader.getInstance().getGameInstance()).getNetworkHandler());
        }
    }
}
