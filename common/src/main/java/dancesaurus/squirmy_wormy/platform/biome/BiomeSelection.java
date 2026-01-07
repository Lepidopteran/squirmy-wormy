package dancesaurus.squirmy_wormy.platform.biome;

import java.util.function.Predicate;

public final class BiomeSelection {
    private BiomeSelection() {
    }

    public static Predicate<BiomeSelectionContext> vanilla() {
        return context -> {
            // In addition to the namespace, we also check that it exists in the vanilla registries
            return context.getBiomeKey().location().getNamespace().equals("minecraft")
                    && VanillaBiomeKeys.isBuiltinBiome(context.getBiomeKey());
        };
    }


}
