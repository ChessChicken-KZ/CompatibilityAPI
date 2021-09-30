package kz.chesschicken.compatibility.cla.utils.network;

import io.github.minecraftcursedlegacy.api.networking.PluginChannel;
import io.github.minecraftcursedlegacy.api.registry.Id;
import kz.chesschicken.compatibility.cla.utils.CursedLegacyApiUtils;
import net.minecraft.network.PacketHandler;

public class CLAPINetworkHelperChannel extends PluginChannel {

    @Override
    public Id getChannelIdentifier() {
        return new Id("compatibility_cla:main_channel");
    }

    @Override
    public void onReceive(PacketHandler arg, byte[] data) {
        CursedLegacyApiUtils.byteToSomeMagic(data);
    }
}
