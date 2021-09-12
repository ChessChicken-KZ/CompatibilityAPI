package kz.chesschicken.compatibility.cla.utils.network;

import io.github.minecraftcursedlegacy.api.networking.PluginChannel;
import io.github.minecraftcursedlegacy.api.registry.Id;
import kz.chesschicken.compatibility.api.InstanceIdentifier;
import kz.chesschicken.compatibility.cla.utils.CursedLegacyApiUtils;
import net.minecraft.network.PacketHandler;

public class ChannelProxy extends PluginChannel {
    private final InstanceIdentifier identifier;
    public ChannelProxy(InstanceIdentifier i) {
        this.identifier = i;
    }

    @Override
    public Id getChannelIdentifier() {
        return CursedLegacyApiUtils.from(identifier);
    }

    @Override
    public void onReceive(PacketHandler arg, byte[] data) {
        //???
    }
}
