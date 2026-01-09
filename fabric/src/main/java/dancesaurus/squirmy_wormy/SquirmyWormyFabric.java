package dancesaurus.squirmy_wormy;

import dancesaurus.squirmy_wormy.platform.BiomeSelection;
import dancesaurus.squirmy_wormy.platform.Services;
import dancesaurus.squirmy_wormy.registries.EntityAttributes;
import dancesaurus.squirmy_wormy.registries.EntitySpawnPlacements;
import dancesaurus.squirmy_wormy.registries.VanillaTabModifications;
import dancesaurus.squirmy_wormy.registries.biome.SpawnModifiers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.block.Blocks;

public class SquirmyWormyFabric implements ModInitializer {

	@Override
	@SuppressWarnings("unchecked")
	public void onInitialize() {
		SquirmyWormy.initialize();
		SquirmyWormy.registerCompostingChances();
		ModBlocks.registerFlammableBlocks();

		EntityAttributes.getAll().forEach((type, attributes) -> {
			EntityType<? extends LivingEntity> entityType = type.get();
			if (Services.PLATFORM.isDevelopmentEnvironment()) {
				SquirmyWormy.LOGGER.info("Registering attributes for {}", entityType);
			}
			FabricDefaultAttributeRegistry.register(entityType, attributes.get());
		});

		EntitySpawnPlacements.getAll().forEach((type, props) -> {
			EntityType<Mob> entityType = (EntityType<Mob>) type.get();
			SpawnPlacements.SpawnPredicate<Mob> predicate = (SpawnPlacements.SpawnPredicate<Mob>) props.decoratorPredicate();
			if (Services.PLATFORM.isDevelopmentEnvironment()) {
				SquirmyWormy.LOGGER.info("Registering Spawn Placement for {}", entityType);
			}
			SpawnPlacements.register(entityType, props.decoratorType(), props.heightMapType(), predicate);
		});

		VanillaTabModifications
				.getAll()
				.forEach((tab, items) -> ItemGroupEvents
						.modifyEntriesEvent(tab.key())
						.register(entries -> items.forEach(item -> entries.accept(item.get()))));

		SpawnModifiers.spawnAdditions().forEach((lazyEntity, props) -> {
			EntityType<?> entityType = lazyEntity.get();

			BiomeModifications.addSpawn(
					context -> {
						BiomeSelection selection = props.selection();
						boolean canSpawn = selection.selectsAllBiomes() ||
								selection.tags().stream().anyMatch(context::hasTag) ||
								selection.biomes().stream().anyMatch(context.getBiomeKey()::equals);

						if (Services.PLATFORM.isDevelopmentEnvironment() && canSpawn) {
							SquirmyWormy.LOGGER.info(
									"Adding {} spawn to biome {}",
									entityType.getDescriptionId(),
									context.getBiomeKey().location()
							);
						}

						return canSpawn;
					}, props.category(), lazyEntity.get(), props.weight(), props.minGroupSize(), props.maxGroupSize()
			);
		});

		PlayerBlockBreakEvents.AFTER.register((
				(level, player, blockPos, blockState, blockEntity) -> {
					if (!level.isClientSide) {
						RandomSource random = level.random;
						if (blockState.getBlock() == Blocks.DIRT && random.nextFloat() <= 0.05) {
							ModEntities.EARTHWORM.get().spawn((ServerLevel) level, blockPos, MobSpawnType.TRIGGERED);
						}
					}
				}
		));

		SquirmyWormy.LOGGER.info("Y O U ' V E  G O T  W O R M S !");
	}
}
