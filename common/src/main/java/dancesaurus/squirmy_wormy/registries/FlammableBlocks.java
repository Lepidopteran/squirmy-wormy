package dancesaurus.squirmy_wormy.registries;

import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public final class FlammableBlocks {
    private static final Map<Block, FlammableBlockProperties> FLAMMABLE_BLOCKS = new HashMap<>();
    public static final FlammableBlockProperties CARPET_FLAMMABILITY = new FlammableBlockProperties(60, 20);
    public static final FlammableBlockProperties WOOL_FLAMMABILITY = new FlammableBlockProperties(30, 60);

    /**
     * A record representing properties of flammable blocks, used to define how easily a block can catch fire and the chance it spreads fire to adjacent blocks.
     *
     * @param spreadChance The probability (as a percentage) that this block will spread fire to neighboring blocks when ignited.
     * @param flammability The ease/speed with which this block can catch fire from an external source, such as a flame or lava.
     */
    public record FlammableBlockProperties(int spreadChance, int flammability) {
    }

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
