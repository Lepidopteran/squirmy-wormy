package dancesaurus.squirmy_wormy;

//import dancesaurus.squirmy_wormy.blocks.GlowWormWeb;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Supplier;

import static dancesaurus.squirmy_wormy.platform.Services.PLATFORM;

public class ModBlocks {

//    public static final Block GLOW_WORM_WEB = register(
//            new GlowWormWeb(BlockBehaviour.Properties
//                    .of()
//                    .sound(SoundType.WOOL)
//                    .noCollission()
//                    .strength(1.0f)
//                    .emissiveRendering(ModBlocks::always)
//                    .lightLevel(value -> 1)
//                    .noOcclusion()), "glow_worm_web", true
//    );

    public static final Supplier<Block> GLOW_WORM_WOOL = PLATFORM.registerBlockWithItem(
            () ->
                    new Block(BlockBehaviour.Properties
                            .copy(Blocks.WHITE_WOOL)
                            .mapColor(MapColor.COLOR_LIGHT_BLUE)
                            .strength(1.0f)
                            .emissiveRendering(ModBlocks::always)
                            .lightLevel(value -> 1)), "glow_worm_wool"
    );

    public static final Supplier<CarpetBlock> GLOWING_CARPET = PLATFORM.registerBlockWithItem(
            () -> new CarpetBlock(BlockBehaviour.Properties
                    .copy(Blocks.WHITE_CARPET)
                    .mapColor(MapColor.COLOR_LIGHT_BLUE)
                    .strength(1.0f)
                    .emissiveRendering(ModBlocks::always)
                    .lightLevel(value -> 1)), "glowing_carpet"
    );


    public static void initialize() {
//
//    CompostingChanceRegistry.INSTANCE.add(GLOW_WORM_WEB, 1.0f);
//
//    ItemGroupEvents
//            .modifyEntriesEvent(ItemGroups.NATURAL)
//            .register((FabricItemGroupEntries entries) -> entries.add(GLOW_WORM_WEB));
//    ItemGroupEvents
//            .modifyEntriesEvent(ItemGroups.COLORED_BLOCKS)
//            .register((FabricItemGroupEntries entries) -> entries.add(GLOW_WORM_WOOL));
//      ItemGroupEvents
//              .modifyEntriesEvent(ItemGroups.COLORED_BLOCKS)
//              .register((FabricItemGroupEntries entries) -> entries.add(GLOWING_CARPET));
    }

    private static boolean always(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }
}
