package dancesaurus.squirmy_wormy;

import dancesaurus.squirmy_wormy.registries.EntityAttributes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

public class SquirmyWormyFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        SquirmyWormy.init();

        EntityAttributes.getAll().forEach((type, attributes) -> {
            EntityType<? extends LivingEntity> entityType = type.get();
            SquirmyWormy.LOGGER.info("Registering attributes for {}", entityType);

            FabricDefaultAttributeRegistry.register(entityType, attributes.get());
        });

        SquirmyWormy.LOGGER.info("Y O U ' V E  G O T  W O R M S !");
    }
}
