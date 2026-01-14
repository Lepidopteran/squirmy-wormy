package dancesaurus.squirmy_wormy;

import dancesaurus.squirmy_wormy.platform.Services;
import dancesaurus.squirmy_wormy.registries.CompostingChances;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SquirmyWormy {
	public static final String MOD_ID = "squirmy_wormy";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static void initialize() {
		ModBlocks.initialize();
		ModItems.initialize();
		ModEntities.initialize();
		ModBlockEntities.initialize();
	}

	public static void registerCompostingChances() {
		CompostingChances
				.getAll()
				.forEach((item, chance) -> Services.PLATFORM.registerItemCompostingChance(item.get().asItem(), chance));
	}
}
