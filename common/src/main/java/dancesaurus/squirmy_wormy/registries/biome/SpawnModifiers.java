package dancesaurus.squirmy_wormy.registries.biome;

import dancesaurus.squirmy_wormy.platform.BiomeSelection;
import dancesaurus.squirmy_wormy.platform.LazyResource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;

import java.util.HashMap;
import java.util.Map;

public class SpawnModifiers {
	private static final Map<LazyResource<? extends EntityType<? extends LivingEntity>>, AddSpawnProperties> SPAWN_ADDITIONS = new HashMap<>();

	public record AddSpawnProperties(
			BiomeSelection selection,
			MobCategory category,
			Integer weight,
			Integer minGroupSize,
			Integer maxGroupSize
	) {
	}

	public static <T extends LivingEntity> void addSpawn(
			LazyResource<EntityType<T>> type,
			BiomeSelection selection,
			MobCategory category,
			Integer weight,
			Integer minGroupSize,
			Integer maxGroupSize
	) {
		SPAWN_ADDITIONS.put(type, new AddSpawnProperties(selection, category, weight, minGroupSize, maxGroupSize));
	}

	public static Map<LazyResource<? extends EntityType<? extends LivingEntity>>, AddSpawnProperties> spawnAdditions() {
		return SPAWN_ADDITIONS;
	}
}
