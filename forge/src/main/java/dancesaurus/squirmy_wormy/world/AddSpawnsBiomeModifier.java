package dancesaurus.squirmy_wormy.world;

import com.mojang.serialization.Codec;
import dancesaurus.squirmy_wormy.ModEntities;
import dancesaurus.squirmy_wormy.SquirmyWormy;
import dancesaurus.squirmy_wormy.SquirmyWormyForge;
import dancesaurus.squirmy_wormy.platform.ForgePlatformHelper;
import dancesaurus.squirmy_wormy.registries.biome.SpawnModifiers;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import org.jetbrains.annotations.NotNull;

import static dancesaurus.squirmy_wormy.platform.Services.PLATFORM;

public class AddSpawnsBiomeModifier implements BiomeModifier {
	@Override
	public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
		if (phase
			== Phase.ADD) {
			SpawnModifiers.spawnAdditions().forEach((lazyEntity, props) -> {
				if (props.selection().tags().stream().noneMatch(biome::containsTag)
					&& props.selection().biomes().stream().noneMatch(biome::is)
					&& !props.selection().selectsAllBiomes()) {
					return;
				}

				EntityType<?> entityType = lazyEntity.get();

				if (PLATFORM.isDevelopmentEnvironment()) {
					SquirmyWormy.LOGGER.info(
							"Adding {} spawn to biome {}",
							entityType.getDescriptionId(),
							biome.unwrapKey().map(ResourceKey::location).orElse(null)
					);
				}

				builder.getMobSpawnSettings().addSpawn(
						props.category(),
						new MobSpawnSettings.SpawnerData(
								entityType,
								props.weight(),
								props.minGroupSize(),
								props.maxGroupSize()
						)
				);

			});
		}
	}

	@Override
	public @NotNull Codec<? extends BiomeModifier> codec() {
		return SquirmyWormyForge.ADD_SPAWNS_CODEC.get();
	}
}




