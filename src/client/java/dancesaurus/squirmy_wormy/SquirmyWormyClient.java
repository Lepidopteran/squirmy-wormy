package dancesaurus.squirmy_wormy;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import static dancesaurus.squirmy_wormy.ModInfo.MOD_ID;

public class SquirmyWormyClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(SquirmyWormy.EARTHWORM, EarthwormRenderer::new);
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}
}