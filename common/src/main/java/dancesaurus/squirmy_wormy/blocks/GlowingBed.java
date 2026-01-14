package dancesaurus.squirmy_wormy.blocks;

import dancesaurus.squirmy_wormy.blocks.entity.GlowingBedEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class GlowingBed extends BedBlock {
	public GlowingBed(Properties properties) {
		super(DyeColor.CYAN, properties);
	}

	@Override
	public @NotNull BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		return new GlowingBedEntity(pos, state);
	}
}
