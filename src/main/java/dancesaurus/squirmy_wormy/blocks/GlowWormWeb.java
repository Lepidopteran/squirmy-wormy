package dancesaurus.squirmy_wormy.blocks;

import dancesaurus.squirmy_wormy.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.slf4j.LoggerFactory;

import java.util.Locale;

@SuppressWarnings("deprecation")

public class GlowWormWeb extends Block {
	public GlowWormWeb(Settings settings) {
		super(settings);
		setDefaultState(this.getDefaultState().with(SEGMENT, SegmentType.TIP));
	}
	public static final EnumProperty<SegmentType> SEGMENT = EnumProperty.of("segment", SegmentType.class);

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
		if (direction == Direction.UP && !state.canPlaceAt(world, pos)) {
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
