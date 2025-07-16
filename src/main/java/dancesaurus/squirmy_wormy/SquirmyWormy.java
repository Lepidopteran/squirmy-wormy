package dancesaurus.squirmy_wormy;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dancesaurus.squirmy_wormy.ModInfo.MOD_ID;

public class SquirmyWormy implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.initialize();
		
		LOGGER.info("The worms have been released...");
	}
}