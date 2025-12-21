package dancesaurus.squirmy_wormy.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.dedicated.Settings;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

//@SuppressWarnings("deprecation")
//public class GlowWormWeb extends Block {
//    public static final EnumProperty<SegmentType> SEGMENT = EnumProperty.create("segment", SegmentType.class);
//    public static final int MINIMUM_AIR_GAP = 15;
//    public static final float GROWTH_RATE = 0.01f;
//
//    public GlowWormWeb(Properties settings) {
//        super(settings);
//
//        this.registerDefaultState(this.defaultBlockState().setValue(SEGMENT, SegmentType.TIP));
//    }
//
//    @Override
//    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
//        builder.add(SEGMENT);
//    }
//
//    @Override
//    public boolean canSurvive(@NotNull BlockState state, LevelReader world, BlockPos pos) {
//        return !world.getBlockState(pos.above()).isAir();
//    }
//    @Override
//    public void neighborUpdate(
//            BlockState state,
//            Level world,
//            BlockPos pos,
//            Block sourceBlock,
//            BlockPos sourcePos,
//            boolean notify
//    ) {
//        if (!state.isOf(this)) return;
//
//        BlockState below = world.getBlockState(pos.down());
//        SegmentType segment = below.isOf(this) ? SegmentType.MIDDLE : SegmentType.TIP;
//
//        if (state.get(SEGMENT) != segment) {
//            world.setBlockState(pos, state.with(SEGMENT, segment), Block.NOTIFY_ALL);
//        }
//    }
//
//    @Override
//    public BlockState getStateForNeighborUpdate(
//            BlockState state,
//            Direction direction,
//            BlockState neighborState,
//            LevelACAccess world,
//            BlockPos pos,
//            BlockPos neighborPos
//    ) {
//        if (direction == Direction.UP && !state.canPlaceAt(world, pos) && !world.isClient()) {
//            world.scheduleBlockTick(pos, this, 1);
//        }
//
//        return state;
//    }
//
//    @Override
//    public boolean hasRandomTicks(BlockState state) {
//        return true;
//    }
//
//    @Override
//    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
//        if (state.get(SEGMENT) != SegmentType.TIP || random.nextFloat() >= GROWTH_RATE) {
//            return;
//        }
//
//        boolean canGrow = true;
//        for (int index = 1; index <= MINIMUM_AIR_GAP; index++) {
//            BlockPos blockPos = pos.down(index);
//            if (!world.isAir(blockPos)) {
//                canGrow = false;
//                break;
//            }
//        }
//
//        if (canGrow) {
//            world.setBlockState(pos.down(), this.getDefaultState());
//        }
//    }
//
//    @Override
//    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
//        world.breakBlock(pos, true);
//
//        BlockPos below = pos.down();
//        if (world.getBlockState(below).isOf(this)) {
//            world.scheduleBlockTick(below, this, 1);
//        }
//    }
//
//    @Override
//    public StateManager<Block, BlockState> getStateManager() {
//        return super.getStateManager();
//    }
//
//    public enum SegmentType implements StringIdentifiable, StringRepresentable {
//        TIP, MIDDLE;
//
//        @Override
//        public String asString() {
//            return name().toLowerCase(Locale.ROOT);
//        }
//    }
//}
