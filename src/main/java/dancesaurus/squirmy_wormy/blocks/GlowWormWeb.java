package dancesaurus.squirmy_wormy.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.Locale;

@SuppressWarnings("deprecation")

public class GlowWormWeb extends Block {
	public static final EnumProperty<SegmentType> SEGMENT = EnumProperty.of("segment", SegmentType.class);
	public static final int MINIMUM_AIR_GAP = 5;

	public GlowWormWeb(Settings settings) {
		super(settings);
		setDefaultState(this.getDefaultState().with(SEGMENT, SegmentType.TIP));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(SEGMENT);
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		return !world.getBlockState(pos.up()).isAir();
	}

	@Override
	public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
		if (!state.isOf(this)) return;

		BlockState below = world.getBlockState(pos.down());
		SegmentType segment = below.isOf(this) ? SegmentType.MIDDLE : SegmentType.TIP;

		if (state.get(SEGMENT) != segment) {
			world.setBlockState(pos, state.with(SEGMENT, segment), Block.NOTIFY_ALL);
		}
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (direction == Direction.UP && !state.canPlaceAt(world, pos) && !world.isClient()) {
			world.scheduleBlockTick(pos, this, 1);
		}

		return state;
	}

	@Override
	public boolean hasRandomTicks(BlockState state) {
		return true;
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (state.get(SEGMENT) != SegmentType.TIP) {
			return;
		}

		boolean canGrow = true;
		for (int index = 1; index <= MINIMUM_AIR_GAP; index++) {
			BlockPos blockPos = pos.down(index);
			if (!world.isAir(blockPos)) {
				canGrow = false;
				break;
			}
		}

		if (canGrow) {
			world.setBlockState(pos.down(), this.getDefaultState());
		}
	}

	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		world.breakBlock(pos, true);

		BlockPos below = pos.down();
		if (world.getBlockState(below).isOf(this)) {
			world.scheduleBlockTick(below, this, 1);
		}
	}

	@Override
	public StateManager<Block, BlockState> getStateManager() {
		return super.getStateManager();
	}

	public enum SegmentType implements StringIdentifiable {
		TIP, MIDDLE;

		@Override
		public String asString() {
			return name().toLowerCase(Locale.ROOT);
		}
	}
}  
