package dancesaurus.squirmy_wormy.platform.services;

import dancesaurus.squirmy_wormy.platform.LazyResource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public interface IPlatformClientHelper {

	/**
	 * Registers an entity renderer provider for the specified entity type, allowing the platform to render the entity in the game world.
	 *
	 * @param type           A supplier for the entity type, which provides the entity class and associated data for rendering.
	 * @param renderProvider The entity renderer provider responsible for supplying the renderer instance for the entity type.
	 */
	<T extends Entity> void registerEntityRenderer(
			LazyResource<EntityType<T>> type,
			EntityRendererProvider<T> renderProvider
	);

	/**
	 * Registers a block entity renderer provider for the specified block entity type, allowing the platform to render block entities in the game world.
	 *
	 * @param type           A LazyResource providing the block entity type, which defines the type of block entity to render.
	 * @param renderProvider The block entity renderer provider responsible for supplying the renderer instance for the block entity type.
	 */
	<T extends BlockEntity> void registerBlockEntityRenderer(
			LazyResource<BlockEntityType<T>> type,
			BlockEntityRendererProvider<T> renderProvider
	);

	/**
	 * Sets the render type for the specified block, determining how it is visually represented in the game world.
	 *
	 * @param block      The block to configure with the specified render type.
	 * @param renderType The render type to apply to the block, defining its visual properties and rendering behavior.
	 */
	<T extends Block> void setBlockRenderType(LazyResource<T> block, RenderType renderType);
}