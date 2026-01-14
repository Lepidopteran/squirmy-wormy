package dancesaurus.squirmy_wormy.platform;

import dancesaurus.squirmy_wormy.platform.services.IPlatformHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

import java.util.Set;
import java.util.function.Supplier;

import static dancesaurus.squirmy_wormy.SquirmyWormyForge.*;

public class ForgePlatformHelper implements IPlatformHelper {


	@Override
	public String getPlatformName() {

		return "Forge";
	}

	@Override
	public boolean isModLoaded(String modId) {

		return ModList.get().isLoaded(modId);
	}

	@Override
	public boolean isDevelopmentEnvironment() {
		return !FMLLoader.isProduction();
	}

	@Override
	public <T extends Item> LazyResource<T> registerCustomItem(Supplier<T> item, String name) {
		return new LazyResource<>(name, ITEMS.register(name, item));
	}

	@Override
	public void registerItemCompostingChance(ItemLike item, float chance) {
		ComposterBlock.COMPOSTABLES.put(item.asItem(), chance);
	}

	@Override
	public <T extends Block> LazyResource<T> registerBlock(Supplier<T> block, String name) {
		return new LazyResource<>(name, BLOCKS.register(name, block));
	}

	@Override
	public <T extends Mob> LazyResource<SpawnEggItem> registerSpawnEgg(
			LazyResource<EntityType<T>> entity,
			String backgroundColor,
			String foregroundColor,
			String name
	) {
		return new LazyResource<>(
				name, ITEMS.register(
				name, () -> new ForgeSpawnEggItem(
						entity.supplier(),
						Integer.parseInt(backgroundColor.substring(1), 16),
						Integer.parseInt(foregroundColor.substring(1), 16),
						new Item.Properties()
				)
		)
		);
	}

	@Override
	public <T extends Entity> LazyResource<EntityType<T>> registerEntity(
			String name,
			EntityType.EntityFactory<T> factory,
			MobCategory category,
			float width,
			float height
	) {
		return new LazyResource<>(
				name,
				ENTITY_TYPES.register(
						name,
						() -> EntityType.Builder.of(factory, category).sized(width, height).build(name)
				)
		);
	}

	@Override
	@SuppressWarnings("DataFlowIssue")
	public <T extends BlockEntity> LazyResource<BlockEntityType<T>> registerBlockEntityWithSet(
			String name,
			BlockEntityFactory<T> factory,
			Set<LazyResource<? extends Block>> blocks
	) {
		return new LazyResource<>(
				name, BLOCK_ENTITY_TYPES.register(
				name,
				() -> BlockEntityType.Builder
						.of(factory::create, blocks.stream().map(LazyResource::get).toArray(Block[]::new))
						.build(null)
		)
		);
	}
}