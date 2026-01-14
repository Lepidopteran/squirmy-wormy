package dancesaurus.squirmy_wormy;

import dancesaurus.squirmy_wormy.blocks.GlowWormWeb;
import dancesaurus.squirmy_wormy.blocks.GlowingBed;
import dancesaurus.squirmy_wormy.platform.LazyResource;
import dancesaurus.squirmy_wormy.platform.VanillaTab;
import dancesaurus.squirmy_wormy.registries.CompostingChances;
import dancesaurus.squirmy_wormy.registries.FlammableBlocks;
import dancesaurus.squirmy_wormy.registries.VanillaTabModifications;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BedItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import static dancesaurus.squirmy_wormy.platform.Services.PLATFORM;

public class ModBlocks {

	public static final LazyResource<GlowWormWeb> GLOW_WORM_WEB = PLATFORM.registerBlockWithItem(
			() -> new GlowWormWeb(BlockBehaviour.Properties
					.of()
					.sound(SoundType.BIG_DRIPLEAF)
					.noCollission()
					.strength(1.0f)
					.emissiveRendering(ModBlocks::always)
					.lightLevel(value -> 1)
					.noOcclusion()), "glow_worm_web"
	);

	public static final LazyResource<Block> GLOWING_WOOL = PLATFORM.registerBlockWithItem(
			() -> new Block(BlockBehaviour.Properties
					.copy(Blocks.WHITE_WOOL)
					.mapColor(MapColor.COLOR_LIGHT_BLUE)
					.strength(1.0f)
					.emissiveRendering(ModBlocks::always)
					.lightLevel(value -> 1)), "glowing_wool"
	);

	public static final LazyResource<CarpetBlock> GLOWING_CARPET = PLATFORM.registerBlockWithItem(
			() -> new CarpetBlock(BlockBehaviour.Properties
					.copy(Blocks.WHITE_CARPET)
					.mapColor(MapColor.COLOR_LIGHT_BLUE)
					.strength(1.0f)
					.emissiveRendering(ModBlocks::always)
					.lightLevel(value -> 1)), "glowing_carpet"
	);

	// NOTE: Add helper function if used multiple times.
	public static final LazyResource<GlowingBed> GLOWING_BED;

	static {
		LazyResource<GlowingBed> bed = PLATFORM.registerBlock(
				() -> new GlowingBed(BlockBehaviour.Properties
						.of()
						.mapColor(state -> state.getValue(BedBlock.PART) == BedPart.FOOT ?
								DyeColor.WHITE.getMapColor() :
								MapColor.WOOL)
						.sound(SoundType.WOOD)
						.strength(0.2F)
						.noOcclusion()
						.ignitedByLava()
						.emissiveRendering(ModBlocks::always)
						.pushReaction(PushReaction.DESTROY)), "glowing_bed"
		);

		PLATFORM.registerCustomItem(() -> new BlockItem(bed.get(), new Item.Properties().stacksTo(1)), "glowing_bed");

		GLOWING_BED = bed;
	}

	public static void initialize() {

		CompostingChances.register(GLOW_WORM_WEB, 1.0f);
		VanillaTabModifications.addItemTo(GLOW_WORM_WEB, VanillaTab.NATURAL_BLOCKS);
		VanillaTabModifications.addItemTo(GLOWING_WOOL, VanillaTab.COLORED_BLOCKS);
		VanillaTabModifications.addItemTo(GLOWING_CARPET, VanillaTab.COLORED_BLOCKS);
		VanillaTabModifications.addItemTo(GLOWING_BED, VanillaTab.COLORED_BLOCKS);
	}

	// HACK: Need to make it load after initialization, so I can keep proper track of it.
	public static void registerFlammableBlocks() {
		FlammableBlocks.register(GLOWING_WOOL.get(), FlammableBlocks.WOOL_FLAMMABILITY);
		FlammableBlocks.register(GLOWING_CARPET.get(), FlammableBlocks.CARPET_FLAMMABILITY);
	}

	private static boolean always(BlockState state, BlockGetter level, BlockPos pos) {
		return true;
	}
}
