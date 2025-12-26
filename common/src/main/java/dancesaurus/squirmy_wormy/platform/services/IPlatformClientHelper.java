package dancesaurus.squirmy_wormy.platform.services;

import dancesaurus.squirmy_wormy.platform.LazyResource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

public interface IPlatformClientHelper {

    /**
     * Registers an entity renderer provider for the specified entity type, allowing the platform to render the entity in the game world.
     *
     * @param type          A supplier for the entity type, which provides the entity class and associated data for rendering.
     * @param renderProvider The entity renderer provider responsible for supplying the renderer instance for the entity type.
     */
    <T extends Entity> void registerEntityRenderer(LazyResource<EntityType<T>> type, EntityRendererProvider<T> renderProvider);
}