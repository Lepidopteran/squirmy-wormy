package dancesaurus.squirmy_wormy;

import dancesaurus.squirmy_wormy.entities.Earthworm;
import dancesaurus.squirmy_wormy.entities.GlowWorm;
import dancesaurus.squirmy_wormy.platform.Services;
import dancesaurus.squirmy_wormy.registries.EntityAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Supplier;

import static dancesaurus.squirmy_wormy.platform.Services.PLATFORM;

public class ModEntities {
    public static final Supplier<EntityType<Earthworm>> EARTHWORM = PLATFORM.registerEntity(
            "earthworm",
            Earthworm::new,
            MobCategory.CREATURE,
            0.5f,
            0.4f
    );

    public static final Supplier<EntityType<GlowWorm>> GLOW_WORM = PLATFORM.registerEntity(
            "glow_worm",
            GlowWorm::new,
            MobCategory.CREATURE,
            0.5f,
            0.4f
    );

    public static void initialize() {
        EntityAttributes.register(EARTHWORM, Earthworm::createAttributes);
        EntityAttributes.register(GLOW_WORM, GlowWorm::createAttributes);
    }
}
