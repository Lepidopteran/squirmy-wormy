package dancesaurus.squirmy_wormy;

import dancesaurus.squirmy_wormy.entities.client.EarthwormRenderer;
import dancesaurus.squirmy_wormy.entities.client.GlowWormRenderer;
import dancesaurus.squirmy_wormy.platform.Services;

public class SquirmyWormyClient {
    public static void initialize() {
        Services.CLIENT.registerEntityRenderer(ModEntities.EARTHWORM, EarthwormRenderer::new);
        Services.CLIENT.registerEntityRenderer(ModEntities.GLOW_WORM, GlowWormRenderer::new);
    }
}
