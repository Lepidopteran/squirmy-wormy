package dancesaurus.squirmy_wormy.mixin;

import dancesaurus.squirmy_wormy.registries.FlammableBlocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// NOTE: Had to look up the fabric source code, so I can add burnables on forge too. >:(
// Good news is that I don't have to make another service! ^_^

@Mixin(FireBlock.class)
public abstract class FireBlockMixin {
	@Inject(at = @At("HEAD"), method = "getBurnOdds", cancellable = true)
	private void addBurnOdds(BlockState block, CallbackInfoReturnable<Integer> info) {
		FlammableBlocks.FlammableBlockProperties properties = FlammableBlocks.getBlockFlammability(block.getBlock());
		if (properties != null) {
			if (block.hasProperty(BlockStateProperties.WATERLOGGED) && block.getValue(BlockStateProperties.WATERLOGGED)) {
				info.setReturnValue(0);
			} else {
				info.setReturnValue(properties.flammability());
			}
		}
	}

	@Inject(at = @At("HEAD"), method = "getIgniteOdds(Lnet/minecraft/world/level/block/state/BlockState;)I", cancellable = true)
	private void addIgniteOdds(BlockState block, CallbackInfoReturnable<Integer> info) {
		FlammableBlocks.FlammableBlockProperties properties = FlammableBlocks.getBlockFlammability(block.getBlock());

		if (properties != null) {
			if (block.hasProperty(BlockStateProperties.WATERLOGGED) && block.getValue(BlockStateProperties.WATERLOGGED)) {
				info.setReturnValue(0);
			} else {
				info.setReturnValue(properties.spreadChance());
			}
		}
	}
}
