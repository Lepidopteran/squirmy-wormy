package dancesaurus.squirmy_wormy;

import dancesaurus.squirmy_wormy.platform.Services;
import dancesaurus.squirmy_wormy.platform.Services.*;
import dancesaurus.squirmy_wormy.entities.Earthworm;
import dancesaurus.squirmy_wormy.entities.GlowWorm;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public final class SquirmyWormy {
    public static final String MOD_ID = "squirmy_wormy";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Supplier<EntityType<Earthworm>> EARTHWORM = Services.PLATFORM.registerEntity(
            "earthworm",
            Earthworm::new,
            MobCategory.CREATURE,
            0.5f,
            0.4f
    );

    public static final Supplier<EntityType<GlowWorm>> GLOW_WORM = Services.PLATFORM.registerEntity(
            "glow_worm",
            GlowWorm::new,
            MobCategory.CREATURE,
            0.5f,
            0.4f
    );

    public static void init() {
        ModBlocks.initialize();
        ModItems.initialize();
    }


}
