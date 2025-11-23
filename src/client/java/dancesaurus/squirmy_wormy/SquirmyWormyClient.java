package dancesaurus.squirmy_wormy;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class SquirmyWormyClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(SquirmyWormy.EARTHWORM, EarthwormRenderer::new);
		EntityRendererRegistry.register(SquirmyWormy.GLOW_WORM, GlowWormRenderer::new);
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GLOW_WORM_WEB, RenderLayer.getTranslucent());
	}
}
