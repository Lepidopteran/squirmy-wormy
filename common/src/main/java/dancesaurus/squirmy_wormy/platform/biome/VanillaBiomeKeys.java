package dancesaurus.squirmy_wormy.platform.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class VanillaBiomeKeys {
    private static final HolderLookup.Provider vanillaRegistries = VanillaRegistries.createLookup();

    private VanillaBiomeKeys() {
    }

    public static boolean isBuiltinBiome(ResourceKey<Biome> key) {
        return biomeRegistryWrapper().get(key).isPresent();
    }

    public static HolderGetter<Biome> biomeRegistryWrapper() {
        return vanillaRegistries.lookupOrThrow(Registries.BIOME);
    }
}
