package kz.chesschicken.compatibility.stapi;

import kz.chesschicken.compatibility.CompatibilityAPI;
import kz.chesschicken.compatibility.event.CompatibilityEvent;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;

public class EventCall {

    @SuppressWarnings("unused")
    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        CompatibilityAPI.EVENT_BUS.post(new CompatibilityEvent.Block());
    }

    @SuppressWarnings("unused")
    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        CompatibilityAPI.EVENT_BUS.post(new CompatibilityEvent.Item());
    }
}
