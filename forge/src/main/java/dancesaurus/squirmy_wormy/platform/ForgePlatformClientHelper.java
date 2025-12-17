package dancesaurus.squirmy_wormy.platform;

import dancesaurus.squirmy_wormy.platform.services.IPlatformClientHelper;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Supplier;

public class ForgePlatformClientHelper implements IPlatformClientHelper {
    @Override
    public <T extends Entity> void registerEntityRenderer(
            Supplier<EntityType<T>> type,
            EntityRendererProvider<T> renderProvider
    ) {
        EntityRenderers.register(type.get(), renderProvider);
    }
}
