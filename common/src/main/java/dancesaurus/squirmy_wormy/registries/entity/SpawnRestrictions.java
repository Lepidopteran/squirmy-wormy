package dancesaurus.squirmy_wormy.registries.entity;

import dancesaurus.squirmy_wormy.platform.LazyResource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public final class SpawnRestrictions {

	public record SpawnPlacementProperties<T extends Mob>(
			SpawnPlacements.Type decoratorType,
			Heightmap.Types heightMapType,
			@NotNull
			SpawnPlacements.SpawnPredicate<T> decoratorPredicate
	) {}

	private static final Map<LazyResource<? extends EntityType<? extends LivingEntity>>, SpawnPlacementProperties<? extends Mob>> SPAWN_PLACEMENTS = new HashMap<>();

	public static <T extends Mob> void register(
			LazyResource<EntityType<T>> type,
			SpawnPlacements.Type decoratorType,
			Heightmap.Types heightMapType,
			@NotNull SpawnPlacements.SpawnPredicate<T> decoratorPredicate
	) {
		SPAWN_PLACEMENTS.put(type, new SpawnPlacementProperties<>(decoratorType, heightMapType, decoratorPredicate));
	}

	public static Map<LazyResource<? extends EntityType<? extends LivingEntity>>, SpawnPlacementProperties<? extends Mob>> getAll() {
		return SPAWN_PLACEMENTS;
	}

	private SpawnRestrictions() {
	}
}
