package dancesaurus.squirmy_wormy;

import dancesaurus.squirmy_wormy.entities.Earthworm;
import dancesaurus.squirmy_wormy.entities.GlowWorm;
import dancesaurus.squirmy_wormy.platform.BiomeSelection;
import dancesaurus.squirmy_wormy.platform.LazyResource;
import dancesaurus.squirmy_wormy.registries.entity.EntityAttributes;
import dancesaurus.squirmy_wormy.registries.entity.SpawnRestrictions;
import dancesaurus.squirmy_wormy.registries.biome.SpawnModifiers;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

import static dancesaurus.squirmy_wormy.platform.Services.PLATFORM;

public class ModEntities {
	public static final LazyResource<EntityType<Earthworm>> EARTHWORM = PLATFORM.registerEntity(
			"earthworm",
			Earthworm::new,
			MobCategory.CREATURE,
			0.5f,
			0.4f
	);

	public static final LazyResource<EntityType<GlowWorm>> GLOW_WORM = PLATFORM.registerEntity(
			"glow_worm",
			GlowWorm::new,
			MobCategory.CREATURE,
			0.5f,
			0.4f
	);

	public static void initialize() {
		EntityAttributes.register(EARTHWORM, Earthworm::createAttributes);
		SpawnRestrictions.register(
				EARTHWORM,
				SpawnPlacements.Type.ON_GROUND,
				Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Earthworm::canWormSpawn
		);
		SpawnModifiers.addSpawn(EARTHWORM, BiomeSelection.allBiomes(), MobCategory.CREATURE, 10, 1, 10);

		EntityAttributes.register(GLOW_WORM, GlowWorm::createAttributes);

		SpawnRestrictions.register(
				GLOW_WORM,
				SpawnPlacements.Type.ON_GROUND,
				Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				GlowWorm::canGlowWormSpawn
		);

		SpawnModifiers.addSpawn(GLOW_WORM, BiomeSelection.allBiomes(), MobCategory.CREATURE, 10, 1, 10);
	}
}
