package dancesaurus.squirmy_wormy.registries;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class ItemCompostingChances {
    private static final Map<Supplier<Item>, Float>
            COMPOSTING_CHANCES = new HashMap<>();

    public static void register(
            Supplier<Item> item,
            float chance
    ) {
        COMPOSTING_CHANCES.put(item, chance);
    }

    public static Map<Supplier<Item>, Float>

    getAll() {
        return COMPOSTING_CHANCES;
    }

    private ItemCompostingChances() {}
}
