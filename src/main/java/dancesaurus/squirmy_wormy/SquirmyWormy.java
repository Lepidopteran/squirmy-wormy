package dancesaurus.squirmy_wormy;

import dancesaurus.squirmy_wormy.Blocks.ModBlocks;
import dancesaurus.squirmy_wormy.entities.Earthworm;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static dancesaurus.squirmy_wormy.ModInfo.MOD_ID;

public class SquirmyWormy implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final EntityType<Earthworm> EARTHWORM = Registry.register(
			Registries.ENTITY_TYPE,
			Identifier.of(MOD_ID, "earthworm"),
			EntityType.Builder.create(Earthworm::new, SpawnGroup.CREATURE).setDimensions(0.5f, 0.4f).build("earthworm")
	);

	@Override
	public void onInitialize() {
		ModItems.initialize();
		ModBlocks.initialize();

		FabricDefaultAttributeRegistry.register(EARTHWORM, Earthworm.createAttributes());
		LOGGER.info("The worms have been released...");
	}
}