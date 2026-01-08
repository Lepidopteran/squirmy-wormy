package dancesaurus.squirmy_wormy.platform;

import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

import java.util.HashSet;
import java.util.Set;

public class BiomeSelection {
	private final boolean allBiomes;
	private final Set<TagKey<Biome>> tags = new HashSet<>();
	private final Set<ResourceKey<Biome>> biomes = new HashSet<>();

	public static BiomeSelection allBiomes() {
		return new BiomeSelection(Set.of(), Set.of(), true);
	}

	private BiomeSelection(Set<TagKey<Biome>> tags, Set<ResourceKey<Biome>> biomes, Boolean allBiomes) {
		this.tags.addAll(tags);
		this.biomes.addAll(biomes);

		this.allBiomes = allBiomes;
	}

	public static Builder builder() {
		return new Builder();
	}

	public Set<ResourceKey<Biome>> biomes() {
		return biomes;
	}

	public Set<TagKey<Biome>> tags() {
		return tags;
	}

	public boolean selectsAllBiomes() {
		return this.allBiomes;
	}

	public static class Builder {
		private final Set<TagKey<Biome>> tags = new HashSet<>();
		private final Set<ResourceKey<Biome>> biomes = new HashSet<>();

		public Builder addTag(TagKey<Biome> tag) {
			this.tags.add(tag);
			return this;
		}

		public Builder addBiome(ResourceKey<Biome> biome) {
			this.biomes.add(biome);
			return this;
		}

		public BiomeSelection build() {
			return new BiomeSelection(this.tags, this.biomes, false);
		}
	}
}
