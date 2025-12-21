package dancesaurus.squirmy_wormy.platform;

import dancesaurus.squirmy_wormy.SquirmyWormy;
import dancesaurus.squirmy_wormy.platform.services.IPlatformClientHelper;
import dancesaurus.squirmy_wormy.platform.services.IPlatformHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class FabricPlatformClientHelper implements IPlatformClientHelper {

    @Override
    public <T extends Entity> void registerEntityRenderer(
            Supplier<EntityType<T>> type,
            EntityRendererProvider<T> renderProvider
    ) {
        EntityRendererRegistry.register(type.get(), renderProvider);
    }
}
