package dancesaurus.squirmy_wormy.platform.services;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;

import java.util.Optional;
import java.util.function.Supplier;

public interface IPlatformHelper {

    /**
     * Gets the name of the current platform
     *
     * @return The name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the game is currently in a development environment.
     *
     * @return True if in a development environment, false otherwise.
     */
    boolean isDevelopmentEnvironment();

    /**
     * Gets the name of the environment type as a string.
     *
     * @return The name of the environment type.
     */
    default String getEnvironmentName() {
        return isDevelopmentEnvironment() ? "development" : "production";
    }

    /**
     * Registers an item with the platform, associating it with the provided name.
     *
     * @param item The supplier for the item to be registered.
     * @param name The identifier or name used for the item registration.
     * @return A supplier for the registered item, which can be used to retrieve or reference it later.
     */
    <T extends Item> Supplier<T> registerItem(Supplier<T> item, String name);

    /**
     * Registers a spawn egg item for the specified entity type with custom color settings and name.
     *
     * @param entity          Supplier for the entity type associated with this spawn egg.
     * @param backgroundColor Color identifier for the background of the spawn egg.
     * @param foregroundColor Color identifier for the foreground of the spawn egg.
     * @param name            Identifier used for registry purposes.
     * @return A supplier for the registered spawn egg item, allowing access to the item instance.
     */
    <T extends Mob> Supplier<SpawnEggItem> registerSpawnEgg(
            Supplier<EntityType<T>> entity,
            String backgroundColor,
            String foregroundColor,
            String name
    );

    /**
     * Registers a spawn egg item for the specified entity type with integer-based color values.
     *
     * @param entity          The supplier for the entity type to associate with the spawn egg.
     * @param backgroundColor The background color in integer format.
     * @param foregroundColor The foreground color in integer format.
     * @param name            The identifier or name used for the spawn egg registration.
     * @return A supplier for the registered spawn egg item, allowing later reference or retrieval.
     */
    <T extends Mob> Supplier<SpawnEggItem> registerSpawnEggWithIntColors(
            Supplier<EntityType<T>> entity,
            int backgroundColor,
            int foregroundColor,
            String name
    );

    /**
     * Registers a block with the specified name.
     *
     * @param block The supplier for the block to be registered.
     * @param name  The name or identifier used to register the block.
     * @return Returns a supplier for the registered block, allowing access to the block instance.
     */
    <T extends Block> Supplier<T> registerBlock(Supplier<T> block, String name);

    /**
     * Registers a block along with its corresponding item, using the provided properties for the item.
     *
     * @param block Supplier for the block to be registered.
     * @param name Identifier or name used for registration of both the block and item.
     * @param itemProps Properties defining the behavior and attributes of the associated item.
     * @return Supplier for the registered block, allowing access to the block instance.
     */
    default <T extends Block> Supplier<T> registerBlockWithItem(
            Supplier<T> block,
            String name,
            Item.Properties itemProps
    ) {
        Supplier<T> registeredBlock = registerBlock(block, name);

        registerItem(
                () -> new BlockItem(registeredBlock.get(), itemProps),
                name
        );

        return registeredBlock;
    }

    /**
     * Registers a block along with its corresponding item using default item properties.
     *
     * @param block Supplier for the block to be registered.
     * @param name Identifier or name used for registration of both the block and item.
     * @return Supplier for the registered block, allowing access to the block instance.
     */
    default <T extends Block> Supplier<T> registerBlockWithItem(
            Supplier<T> block,
            String name
    ) {
        return registerBlockWithItem(block, name, new Item.Properties());
    }

    /**
     * Registers a new entity type with the platform, allowing it to be used in the game.
     *
     * @param name     The registry name of the entity, used for identification.
     * @param factory  The factory used to create instances of the entity type.
     * @param category The category of the mob, determining where it spawns (e.g., CREATURE, MONSTER).
     * @param width    The width of the entity's collision box.
     * @param height   The height of the entity's collision box.
     * @return A supplier for the registered entity type, providing access to its registry entry.
     */
    <T extends Entity> Supplier<EntityType<T>> registerEntity(
            String name,
            EntityType.EntityFactory<T> factory,
            MobCategory category,
            float width,
            float height
    );
}