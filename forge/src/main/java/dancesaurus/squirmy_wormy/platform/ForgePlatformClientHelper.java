package dancesaurus.squirmy_wormy.platform;

import dancesaurus.squirmy_wormy.platform.services.IPlatformClientHelper;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ForgePlatformClientHelper implements IPlatformClientHelper {
	@Override
	public <T extends Entity> void registerEntityRenderer(
			LazyResource<EntityType<T>> type,
			EntityRendererProvider<T> renderProvider
	) {
		EntityRenderers.register(type.get(), renderProvider);
	}

	@Override
	public <T extends BlockEntity> void registerBlockEntityRenderer(
			LazyResource<BlockEntityType<T>> type,
			BlockEntityRendererProvider<T> renderProvider
	) {
		BlockEntityRenderers.register(type.get(), renderProvider);
	}

	@Override
	@SuppressWarnings("deprecation")
	public <T extends Block> void setBlockRenderType(LazyResource<T> block, RenderType renderType) {
		ItemBlockRenderTypes.setRenderLayer(block.get(), renderType);
	}
}
