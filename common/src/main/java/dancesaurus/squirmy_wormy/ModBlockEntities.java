package dancesaurus.squirmy_wormy;

import dancesaurus.squirmy_wormy.blocks.entity.GlowingBedEntity;
import dancesaurus.squirmy_wormy.platform.LazyResource;
import net.minecraft.world.level.block.entity.BlockEntityType;

import static dancesaurus.squirmy_wormy.platform.Services.PLATFORM;

public class ModBlockEntities {
	public static final LazyResource<BlockEntityType<GlowingBedEntity>> GLOWING_BED_ENTITY = PLATFORM.registerBlockEntity("glowing_bed_entity",
			GlowingBedEntity::new,
			ModBlocks.GLOWING_BED
	);

	public static void initialize() {
	}
}
