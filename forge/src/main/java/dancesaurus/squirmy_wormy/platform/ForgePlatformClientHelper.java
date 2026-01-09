package dancesaurus.squirmy_wormy.platform;

import dancesaurus.squirmy_wormy.ModBlocks;
import dancesaurus.squirmy_wormy.platform.services.IPlatformClientHelper;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;

public class ForgePlatformClientHelper implements IPlatformClientHelper {
    @Override
    public <T extends Entity> void registerEntityRenderer(
            LazyResource<EntityType<T>> type,
            EntityRendererProvider<T> renderProvider
    ) {
        EntityRenderers.register(type.get(), renderProvider);
    }

	@Override
	@SuppressWarnings("deprecation")
	public <T extends Block> void setBlockRenderType(LazyResource<T> block, RenderType renderType) {
		ItemBlockRenderTypes.setRenderLayer(
				ModBlocks.GLOW_WORM_WEB.get(),
				RenderType.translucent()
		);
	}
}
