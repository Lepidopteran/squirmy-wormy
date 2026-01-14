package dancesaurus.squirmy_wormy;

import dancesaurus.squirmy_wormy.blocks.client.GlowingBedRenderer;
import dancesaurus.squirmy_wormy.entities.client.EarthwormRenderer;
import dancesaurus.squirmy_wormy.entities.client.GlowWormRenderer;
import net.minecraft.client.renderer.RenderType;

import static dancesaurus.squirmy_wormy.platform.Services.CLIENT;

public class SquirmyWormyClient {
	public static void initialize() {
		CLIENT.registerEntityRenderer(ModEntities.EARTHWORM, EarthwormRenderer::new);
		CLIENT.registerEntityRenderer(ModEntities.GLOW_WORM, GlowWormRenderer::new);
		CLIENT.setBlockRenderType(ModBlocks.GLOW_WORM_WEB, RenderType.translucent());
		CLIENT.registerBlockEntityRenderer(ModBlockEntities.GLOWING_BED_ENTITY, GlowingBedRenderer::new);

	}
}
