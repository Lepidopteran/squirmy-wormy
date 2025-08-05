package dancesaurus.squirmy_wormy.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

@SuppressWarnings("deprecation")

public class GlowWormWeb extends Block {
	public GlowWormWeb(Settings settings) {
		super(settings);
	}

	private void breakChain(ServerWorld world, BlockPos startPos, Block block) {
		BlockPos currentPos = startPos.down();
		while (true) {
			BlockState state = world.getBlockState(currentPos);
			if (state.isOf(block)) {
				world.breakBlock(currentPos, true);
				currentPos = currentPos.down();
			} else {
				break;
			}
		}
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		return !world.getBlockState(pos.up()).isAir();
	}

	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (!state.canPlaceAt(world, pos)) {
			breakChain(world, pos, state.getBlock());
		}
	}
	
	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (direction == Direction.UP && !state.canPlaceAt(world, pos)) {
			if (!world.isClient()) {
				world.scheduleBlockTick(pos, state.getBlock(), 100);
			}

			return Blocks.AIR.getDefaultState();
		}

		return state;
	}

	@Override
	public StateManager<Block, BlockState> getStateManager() {
		return super.getStateManager();
	}
}  
