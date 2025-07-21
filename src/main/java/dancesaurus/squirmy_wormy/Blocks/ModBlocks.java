package dancesaurus.squirmy_wormy.Blocks;

import dancesaurus.squirmy_wormy.ModInfo;
import dancesaurus.squirmy_wormy.SquirmyWormy;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {

    public static final Block GLOW_WORM_WEB = register(
            new Block(AbstractBlock.Settings.create().nonOpaque().burnable()), "glow_worm_web", true);

    public static Block register(Block block, String name, boolean shouldRegisterItem) {
        // Register the block and its item.
        Identifier id = new Identifier(ModInfo.MOD_ID, name);



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

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL)
                .register((FabricItemGroupEntries entries) -> entries.add(GLOW_WORM_WEB));
    }
}
