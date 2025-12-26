package dancesaurus.squirmy_wormy.platform;

import dancesaurus.squirmy_wormy.platform.services.IPlatformClientHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.*;

public class FabricPlatformClientHelper implements IPlatformClientHelper {

    @Override
    public <T extends Entity> void registerEntityRenderer(
            LazyResource<EntityType<T>> type,
            EntityRendererProvider<T> renderProvider
    ) {
        EntityRendererRegistry.register(type.get(), renderProvider);
    }
}
