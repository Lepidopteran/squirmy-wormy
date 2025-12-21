package dancesaurus.squirmy_wormy.registries;

import dancesaurus.squirmy_wormy.platform.FlammableBlockProperties;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class FlammableBlocks {
    private static final Map<Block, FlammableBlockProperties> FLAMMABLE_BLOCKS = new HashMap<>();
    public static final FlammableBlockProperties CARPET_FLAMMABILITY = new FlammableBlockProperties(60, 20);
    public static final FlammableBlockProperties WOOL_FLAMMABILITY = new FlammableBlockProperties(30, 60);

    public static void register(Block block, FlammableBlockProperties properties) {
        FLAMMABLE_BLOCKS.put(block, properties);
    }

    public static Map<Block, FlammableBlockProperties> getAll() {
        return FLAMMABLE_BLOCKS;
    }

    public static FlammableBlockProperties getBlockFlammability(Block block) {
        return FLAMMABLE_BLOCKS.get(block);
    }

    private FlammableBlocks() {
    }
}
