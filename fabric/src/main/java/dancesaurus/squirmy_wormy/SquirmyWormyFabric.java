package dancesaurus.squirmy_wormy;

import dancesaurus.squirmy_wormy.registries.EntityAttributes;
import dancesaurus.squirmy_wormy.registries.EntitySpawnPlacements;
import dancesaurus.squirmy_wormy.registries.FlammableBlocks;
import dancesaurus.squirmy_wormy.registries.VanillaTabModifications;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.world.entity.*;

public class SquirmyWormyFabric implements ModInitializer {

	@Override
	@SuppressWarnings("unchecked")
	public void onInitialize() {
		SquirmyWormy.initialize();
		SquirmyWormy.registerCompostingChances();
		ModBlocks.registerFlammableBlocks();

		EntityAttributes.getAll().forEach((type, attributes) -> {
			EntityType<? extends LivingEntity> entityType = type.get();
			SquirmyWormy.LOGGER.info("Registering attributes for {}", entityType);

			FabricDefaultAttributeRegistry.register(entityType, attributes.get());
		});

		EntitySpawnPlacements.getAll().forEach((type, props) -> {
			EntityType<Mob> entityType = (EntityType<Mob>) type.get();
			SpawnPlacements.SpawnPredicate<Mob> predicate = (SpawnPlacements.SpawnPredicate<Mob>) props.decoratorPredicate();

			SquirmyWormy.LOGGER.info("Registering Spawn Placement for {}", entityType);
			SpawnPlacements.register(entityType, props.decoratorType(), props.heightMapType(), predicate);
		});

		VanillaTabModifications.getAll().forEach((tab, items) -> {
			ItemGroupEvents.modifyEntriesEvent(tab.key()).register(entries -> {
				items.forEach(item -> {
					entries.accept(item.get());
				});
			});
		});


		SquirmyWormy.LOGGER.info("Y O U ' V E  G O T  W O R M S !");
	}

}
