package dancesaurus.squirmy_wormy.registries;

import dancesaurus.squirmy_wormy.platform.LazyResource;
import net.minecraft.world.level.ItemLike;

import java.util.HashMap;
import java.util.Map;

public final class CompostingChances {
    private static final Map<LazyResource<? extends ItemLike>, Float> ITEM_CHANCES = new HashMap<>();

    public static void register(LazyResource<? extends ItemLike> item, float chance) {
        ITEM_CHANCES.put(item, chance);
    }

    public static Map<LazyResource<? extends ItemLike>, Float> getAll() {
        return ITEM_CHANCES;
    }

    private CompostingChances() {}
}
