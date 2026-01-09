package dancesaurus.squirmy_wormy.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

@SuppressWarnings("deprecation")
public class GlowWormWeb extends Block {
	public static final EnumProperty<SegmentType> SEGMENT = EnumProperty.create("segment", SegmentType.class);
	public static final int MINIMUM_AIR_GAP = 15;
	public static final float GROWTH_RATE = 0.01f;

	public GlowWormWeb(Properties settings) {
		super(settings);

		this.registerDefaultState(this.defaultBlockState().setValue(SEGMENT, SegmentType.TIP));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(SEGMENT);
	}

	@Override
	public boolean canSurvive(@NotNull BlockState state, LevelReader world, BlockPos pos) {
		return !world.getBlockState(pos.above()).isAir();
	}

	@Override
	public void neighborChanged(
			BlockState state,
			@NotNull Level level,
			@NotNull BlockPos pos,
			@NotNull Block sourceBlock,
			@NotNull BlockPos sourcePos,
			boolean notify
	) {
		if (!state.is(this)) return;

		BlockState below = level.getBlockState(pos.below());
		SegmentType segment = below.is(this) ? SegmentType.MIDDLE : SegmentType.TIP;

		if (state.getValue(SEGMENT) != segment) {
			level.setBlock(pos, state.setValue(SEGMENT, segment), Block.UPDATE_ALL);
		}
	}

	@Override
	public @NotNull BlockState updateShape(
			@NotNull BlockState state,
			@NotNull Direction direction,
			@NotNull BlockState neighborState,
			@NotNull LevelAccessor level,
			@NotNull BlockPos pos,
			@NotNull BlockPos neighborPos
	) {
		if (direction == Direction.UP &&
				!state.canSurvive(level, pos) &&
				level instanceof Level realLevel &&
				!realLevel.isClientSide()) {

			realLevel.scheduleTick(pos, this, 1);
		}

		return state;
	}

	@Override
	public boolean isRandomlyTicking(@NotNull BlockState state) {
		return true;
	}

	@Override
	public void randomTick(
			BlockState state,
			@NotNull ServerLevel level,
			@NotNull BlockPos pos,
			@NotNull RandomSource random
	) {
		if (state.getValue(SEGMENT) != SegmentType.TIP || random.nextFloat() >= GROWTH_RATE) {
			return;
		}

		boolean canGrow = true;

		for (int i = 1; i <= MINIMUM_AIR_GAP; i++) {
			BlockPos checkPos = pos.below(i);
			if (!level.isEmptyBlock(checkPos)) {
				canGrow = false;
				break;
			}
		}

		if (canGrow) {
			level.setBlock(pos.below(), this.defaultBlockState(), Block.UPDATE_ALL);
		}
	}

	@Override
	public void tick(
			@NotNull BlockState state,
			ServerLevel level,
			@NotNull BlockPos pos,
			@NotNull RandomSource random
	) {
		level.destroyBlock(pos, true);

		BlockPos below = pos.below();
		if (level.getBlockState(below).is(this)) {
			level.scheduleTick(below, this, 1);
		}
	}

	public enum SegmentType implements StringRepresentable {
		TIP, MIDDLE;

		@Override
		public @NotNull String getSerializedName() {
			return name().toLowerCase(Locale.ROOT);
		}
	}
}
