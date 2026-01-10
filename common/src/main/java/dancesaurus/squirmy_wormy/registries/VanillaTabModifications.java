package dancesaurus.squirmy_wormy.registries;

import dancesaurus.squirmy_wormy.platform.LazyResource;
import dancesaurus.squirmy_wormy.platform.VanillaTab;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.ItemLike;

import java.util.*;
import java.util.stream.Collectors;

public final class VanillaTabModifications {

	private static final Map<VanillaTab, Set<LazyResource<? extends ItemLike>>> VANILLA_TAB_MODIFICATIONS = new HashMap<>();
	private static final Map<ResourceKey<CreativeModeTab>, VanillaTab> KEY_TO_TAB = Arrays
			.stream(VanillaTab.values())
			.collect(Collectors.toMap(VanillaTab::key, t -> t));

	public static void addItemTo(LazyResource<? extends ItemLike> item, VanillaTab tab) {
		VANILLA_TAB_MODIFICATIONS.computeIfAbsent(tab, k -> new LinkedHashSet<>()).add(item);
	}

	public static Set<LazyResource<? extends ItemLike>> get(ResourceKey<CreativeModeTab> key) {
		VanillaTab tab = KEY_TO_TAB.get(key);
		if (tab == null) {
			return Collections.emptySet();
		}
		return VANILLA_TAB_MODIFICATIONS.getOrDefault(tab, Collections.emptySet());
	}

	public static Set<LazyResource<? extends ItemLike>> get(VanillaTab key) {
		return VANILLA_TAB_MODIFICATIONS.get(key);
	}

	public static Map<VanillaTab, Set<LazyResource<? extends ItemLike>>> getAll() {
		return VANILLA_TAB_MODIFICATIONS;
	}

	private VanillaTabModifications() {
	}
}
