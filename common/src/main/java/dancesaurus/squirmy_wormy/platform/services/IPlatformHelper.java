package dancesaurus.squirmy_wormy.platform.services;

import dancesaurus.squirmy_wormy.platform.LazyResource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

/**
 * Contains a set of methods that help in handling different operations related to Fabric and Forge.
 */
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
     * Registers a custom item with the platform using the provided supplier and name.
     * The item is created via the supplier and registered with the specified identifier.
     *
     * @param item A supplier for the item instance to be registered.
     * @param name The identifier or name used for the item registration.
     * @return A LazyResource containing the registered item, providing access to the item instance.
     */
    <T extends Item> LazyResource<T> registerCustomItem(Supplier<T> item, String name);

    /**
     * Registers an item with a platform using the provided properties and name.
     *
     * @param props The properties defining the behavior and attributes of the item.
     * @param name  The identifier or name used for the item registration.
     * @return A LazyResource for the registered item, which can be used to retrieve or reference it later.
     */
    default LazyResource<Item> registerItem(Item.Properties props, String name) {
        return registerCustomItem(() -> new Item(props), name);
    }

    /**
     * Registers an item with a platform using default properties and the specified name.
     *
     * @param name The identifier or name used for the item registration.
     * @return A LazyResource for the registered item, which can be used to retrieve or reference it later.
     */
    default LazyResource<Item> registerItem(String name) {
        return registerItem(new Item.Properties(), name);
    }

    /**
     * Sets the composting chance for the specified item.
     * This determines the probability that the item will add a level to a composter.
     *
     * @param item The item you want to add a chance to.
     * @param chance The probability of the item adding a level
     */
    void registerItemCompostingChance(ItemLike item, float chance);

    /**
     * Registers a spawn egg item for the specified entity type with custom color settings and name.
     *
     * @param entity          Supplier for the entity type associated with this spawn egg.
     * @param backgroundColor Color identifier for the background of the spawn egg.
     * @param foregroundColor Color identifier for the foreground of the spawn egg.
     * @param name            Identifier used for registry purposes.
     * @return A LazyResource for the registered spawn egg item, allowing access to the item instance.
     */
    <T extends Mob> LazyResource<SpawnEggItem> registerSpawnEgg(
            LazyResource<EntityType<T>> entity,
            String backgroundColor,
            String foregroundColor,
            String name
    );

    /**
     * Registers a block with the specified name.
     *
     * @param block The supplier for the block to be registered.
     * @param name  The name or identifier used to register the block.
     * @return Returns a LazyResource for the registered block, allowing access to the block instance.
     */
    <T extends Block> LazyResource<T> registerBlock(Supplier<T> block, String name);

    /**
     * Registers a block along with its corresponding item, using the provided properties for the item.
     *
     * @param block     Supplier for the block to be registered.
     * @param name      Identifier or name used for registration of both the block and item.
     * @param itemProps Properties defining the behavior and attributes of the associated item.
     * @return LazyResource for the registered block, allowing access to the block instance.
     */
    default <T extends Block> LazyResource<T> registerBlockWithItem(
            Supplier<T> block,
            String name,
            Item.Properties itemProps
    ) {
        LazyResource<T> registeredBlock = registerBlock(block, name);

        registerCustomItem(
                () -> new BlockItem(registeredBlock.get(), itemProps),
                name
        );

        return registeredBlock;
    }

    /**
     * Registers a block along with its corresponding item using default item properties.
     *
     * @param block Supplier for the block to be registered.
     * @param name  Identifier or name used for registration of both the block and item.
     * @return LazyResource for the registered block, allowing access to the block instance.
     */
    default <T extends Block> LazyResource<T> registerBlockWithItem(
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
     * @return A LazyResource for the registered entity type, providing access to its registry entry.
     */
    <T extends Entity> LazyResource<EntityType<T>> registerEntity(
            String name,
            EntityType.EntityFactory<T> factory,
            MobCategory category,
            float width,
            float height
    );
}