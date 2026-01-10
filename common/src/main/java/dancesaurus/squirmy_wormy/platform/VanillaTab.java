package dancesaurus.squirmy_wormy.platform;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

/**
 * Defines all the vanilla tabs you can use in vanilla minecraft.
 * Had to create it because minecraft privatizes all their resource keys.
 */
public enum VanillaTab {

	BUILDING_BLOCKS("building_blocks"),
	COLORED_BLOCKS("colored_blocks"),
	NATURAL_BLOCKS("natural_blocks"),
	FUNCTIONAL_BLOCKS("functional_blocks"),
	REDSTONE_BLOCKS("redstone_blocks"),
	HOTBAR("hotbar"),
	SEARCH("search"),
	TOOLS_AND_UTILITIES("tools_and_utilities"),
	COMBAT("combat"),
	FOOD_AND_DRINKS("food_and_drinks"),
	INGREDIENTS("ingredients"),
	SPAWN_EGGS("spawn_eggs"),
	OP_BLOCKS("op_blocks"),
	INVENTORY("inventory");

	private final ResourceKey<CreativeModeTab> key;

	VanillaTab(String id) {
		this.key = ResourceKey.create(
				Registries.CREATIVE_MODE_TAB,
				new ResourceLocation("minecraft", id)
		);
	}

	public ResourceKey<CreativeModeTab> key() {
		return key;
	}
}
