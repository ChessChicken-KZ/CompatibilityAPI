package kz.chesschicken.compatibility.stapi.mixin.api;

import kz.chesschicken.compatibility.common.item.BlockMetaNamed;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlas;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.client.texture.atlas.CustomAtlasProvider;
import org.spongepowered.asm.mixin.Mixin;


/*
 * Because StationAPI has custom atlas implementation, {@link CustomAtlasProvider} should be implemented in class.
 * Personally I am not sure if StationAPI already implements it in vanilla classes.
 * Will be deleted if so.
 */
@Mixin(BlockMetaNamed.class)
public class MixinBlockMetaNamed implements CustomAtlasProvider {
    @Override
    public Atlas getAtlas() {
        return Atlases.getStationTerrain();
    }
}
