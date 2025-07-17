package dancesaurus.squirmy_wormy;

import dancesaurus.squirmy_wormy.models.EarthwormModel;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import static dancesaurus.squirmy_wormy.ModInfo.MOD_ID;

public class SquirmyWormyClient implements ClientModInitializer {
	public static final EntityModelLayer EARTHWORM_LAYER = new EntityModelLayer(Identifier.of(MOD_ID, "earthworm"), "main");
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(SquirmyWormy.EARTHWORM, EarthwormRenderer::new);
		EntityRendererRegistry.register(SquirmyWormy.EARTHWORM, EarthwormRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(EARTHWORM_LAYER, EarthwormModel::getTexturedModelData);
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}
}