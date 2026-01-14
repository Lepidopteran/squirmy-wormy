package dancesaurus.squirmy_wormy.blocks.entity;

import dancesaurus.squirmy_wormy.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class GlowingBedEntity extends BlockEntity {
	private DyeColor color;

	public GlowingBedEntity(BlockPos pos, BlockState blockState) {
		super(ModBlockEntities.GLOWING_BED_ENTITY.get(), pos, blockState);
		this.color = ((BedBlock)blockState.getBlock()).getColor();
	}

	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	public DyeColor getColor() {
		return this.color;
	}

	public void setColor(DyeColor color) {
		this.color = color;
	}
}
