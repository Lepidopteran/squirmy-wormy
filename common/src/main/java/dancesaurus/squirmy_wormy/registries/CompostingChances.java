package dancesaurus.squirmy_wormy.registries;

import net.minecraft.world.level.ItemLike;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class CompostingChances {
    private static final Map<Supplier<? extends ItemLike>, Float> ITEM_CHANCES = new HashMap<>();

    public static void register(Supplier<? extends ItemLike> item, float chance) {
        ITEM_CHANCES.put(item, chance);
    }

    public static Map<Supplier<? extends ItemLike>, Float> getAll() {
        return ITEM_CHANCES;
    }

    private CompostingChances() {}
}
