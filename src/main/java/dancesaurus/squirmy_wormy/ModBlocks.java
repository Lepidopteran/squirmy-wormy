package dancesaurus.squirmy_wormy;

import dancesaurus.squirmy_wormy.blocks.GlowWormWeb;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class ModBlocks {

  public static final Block GLOW_WORM_WEB = register(
          new GlowWormWeb(AbstractBlock.Settings
                  .create()
                  .noCollision()
                  .strength(1.0f)
                  .emissiveLighting(ModBlocks::always)
                  .luminance(value -> 1)
                  .nonOpaque()
                  .burnable()), "glow_worm_web", true
  );

  public static final Block GLOW_WORM_WOOL = register(
          new Block(AbstractBlock.Settings
                  .create()
                  .mapColor(MapColor.LIGHT_BLUE)
                  .strength(1.0f)
                  .emissiveLighting(ModBlocks::always)
                  .luminance(value -> 1)
                  .burnable()), "glow_worm_wool", true
  );

  public static Block register(Block block, String name, boolean shouldRegisterItem) {
    // Register the block and its item.
    Identifier id = new Identifier(SquirmyWormy.MOD_ID, name);


    // Sometimes, you may not want to register an item for the block.
    // Eg: if it's a technical block like `minecraft:air` or `minecraft:end_gateway`
    if (shouldRegisterItem) {
      BlockItem blockItem = new BlockItem(block, new Item.Settings());
      Registry.register(Registries.ITEM, id, blockItem);
    }

    return Registry.register(Registries.BLOCK, id, block);
  }

  public static void initialize() {

    CompostingChanceRegistry.INSTANCE.add(GLOW_WORM_WEB, 1.0f);

    ItemGroupEvents
            .modifyEntriesEvent(ItemGroups.NATURAL)
            .register((FabricItemGroupEntries entries) -> entries.add(GLOW_WORM_WEB));
    ItemGroupEvents
            .modifyEntriesEvent(ItemGroups.NATURAL)
            .register((FabricItemGroupEntries entries) -> entries.add(GLOW_WORM_WOOL));
  }

  private static boolean always(BlockState state, BlockView world, BlockPos pos) {
    return true;
  }
}
