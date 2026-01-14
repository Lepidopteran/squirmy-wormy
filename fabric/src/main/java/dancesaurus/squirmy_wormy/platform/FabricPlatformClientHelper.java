package dancesaurus.squirmy_wormy.platform;

import dancesaurus.squirmy_wormy.SquirmyWormy;
import dancesaurus.squirmy_wormy.platform.services.IPlatformClientHelper;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class FabricPlatformClientHelper implements IPlatformClientHelper {

	@Override
	public <T extends Entity> void registerEntityRenderer(
			LazyResource<EntityType<T>> type,
			EntityRendererProvider<T> renderProvider
	) {
		EntityRendererRegistry.register(type.get(), renderProvider);
	}

	@Override
	public <T extends BlockEntity> void registerBlockEntityRenderer(
			LazyResource<BlockEntityType<T>> type,
			BlockEntityRendererProvider<T> renderProvider
	) {
		BlockEntityRenderers.register(type.get(), renderProvider);

		if (Services.PLATFORM.isDevelopmentEnvironment()) {
			SquirmyWormy.LOGGER.info(
					"Registered client block entity renderer for {}",
					type.identifier()
			);
		}
	}

	@Override
	public <T extends Block> void setBlockRenderType(LazyResource<T> block, RenderType renderType) {
		BlockRenderLayerMap.INSTANCE.putBlock(block.get(), RenderType.translucent());
	}
}