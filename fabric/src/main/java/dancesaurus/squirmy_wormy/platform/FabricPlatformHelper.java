package dancesaurus.squirmy_wormy.platform;

import dancesaurus.squirmy_wormy.SquirmyWormy;
import dancesaurus.squirmy_wormy.platform.services.IPlatformHelper;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public <T extends Item> Supplier<T> registerCustomItem(Supplier<T> item, String name) {
        T registry = Registry.register(
                BuiltInRegistries.ITEM,
                new ResourceLocation(SquirmyWormy.MOD_ID, name),
                item.get()
        );
        return () -> registry;
    }

    @Override
    public void registerItemCompostingChance(ItemLike item, float chance) {
        CompostingChanceRegistry.INSTANCE.add(item, chance);
    }

    @Override
    public <T extends Mob> Supplier<SpawnEggItem> registerSpawnEgg(
            Supplier<EntityType<T>> entity,
            String backgroundColor,
            String foregroundColor,
            String name
    ) {
        return registerCustomItem(
                () -> new SpawnEggItem(
                        entity.get(),
                        Integer.parseInt(backgroundColor.substring(1), 16),
                        Integer.parseInt(foregroundColor.substring(1), 16),
                        new Item.Properties()
                ), name
        );
    }

    @Override
    public <T extends Block> Supplier<T> registerBlock(Supplier<T> block, String name) {
        T registry = Registry.register(
                BuiltInRegistries.BLOCK,
                new ResourceLocation(SquirmyWormy.MOD_ID, name),
                block.get()
        );

        return () -> registry;
    }

    @Override
    public <T extends Entity> Supplier<EntityType<T>> registerEntity(
            String name,
            EntityType.EntityFactory<T> factory,
            MobCategory category,
            float width,
            float height
    ) {
        EntityType<T> registry = Registry.register(
                BuiltInRegistries.ENTITY_TYPE,
                new ResourceLocation(SquirmyWormy.MOD_ID, name),
                FabricEntityTypeBuilder
                        .create(category, factory)
                        .dimensions(EntityDimensions.scalable(width, height))
                        .build()
        );

        return () -> registry;
    }
}
