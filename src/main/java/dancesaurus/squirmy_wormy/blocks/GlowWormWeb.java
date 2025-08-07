package dancesaurus.squirmy_wormy.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
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
	public GlowWormWeb(Settings settings) {
		super(settings);
		setDefaultState(this.getDefaultState().with(SEGMENT, SegmentType.MIDDLE));
	}
	public static final EnumProperty<SegmentType> SEGMENT = EnumProperty.of("segment", SegmentType.class);

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(SEGMENT);
	}

	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
		updateSegment(world, pos);
		updateSegment(world, pos.down());
		updateSegment(world, pos.up());
	}

	private void updateSegment(World world, BlockPos pos) {
		BlockState state = world.getBlockState(pos);
		if (!state.isOf(this)) return;

		BlockState above = world.getBlockState(pos.up());
		SegmentType segment = above.isOf(this) ? SegmentType.TIP : SegmentType.MIDDLE;

		if (state.get(SEGMENT) != segment) {
			world.setBlockState(pos, state.with(SEGMENT, segment), Block.NOTIFY_ALL);
		}
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		return !world.getBlockState(pos.up()).isAir();
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (direction == Direction.UP && !state.canPlaceAt(world, pos)) {
			if (!world.isClient()) {
				world.scheduleBlockTick(pos, state.getBlock(), 1);
			}

			return Blocks.AIR.getDefaultState();
		}

		return state;
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
