package dancesaurus.squirmy_wormy.platform;

import dancesaurus.squirmy_wormy.platform.services.IPlatformHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

import java.util.function.Supplier;

import static dancesaurus.squirmy_wormy.SquirmyWormyForge.*;

public class ForgePlatformHelper implements IPlatformHelper {


    @Override
    public String getPlatformName() {

        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(Supplier<T> item, String name) {
        return ITEMS.register(name, item);
    }

    @Override
    public <T extends Block> Supplier<T> registerBlock(Supplier<T> block, String name) {
        return BLOCKS.register(name, block);
    }

    @Override
    public <T extends Mob> Supplier<SpawnEggItem> registerSpawnEgg(
            Supplier<EntityType<T>> entity,
            String backgroundColor,
            String foregroundColor,
            String name
    ) {
        return ITEMS.register(
                name,
                () -> new ForgeSpawnEggItem(
                        entity,
                        Integer.parseInt(backgroundColor.substring(1), 16),
                        Integer.parseInt(foregroundColor.substring(1), 16),
                        new Item.Properties()
                )
        );
    }

    @Override
    public <T extends Mob> Supplier<SpawnEggItem> registerSpawnEggWithIntColors(
            Supplier<EntityType<T>> entity,
            int backgroundColor,
            int foregroundColor,
            String name
    ) {
        return ITEMS.register(
                name,
                () -> new ForgeSpawnEggItem(
                        entity,
                        backgroundColor,
                        foregroundColor,
                        new Item.Properties()
                )
        );
    }

    @Override
    public <T extends Entity> Supplier<EntityType<T>> registerEntity(
            String name,
            EntityType.EntityFactory<T> factory,
            MobCategory category,
            float width,
            float height
    ) {
        return ENTITY_TYPES.register(
                name,
                () -> EntityType.Builder.of(factory, category).sized(width, height).build(name)
        );
    }
}