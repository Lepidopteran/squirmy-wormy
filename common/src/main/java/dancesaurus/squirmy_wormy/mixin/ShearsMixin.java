package dancesaurus.squirmy_wormy.mixin;

import dancesaurus.squirmy_wormy.ModBlocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShearsItem.class)
abstract class ShearsMixin {


	@Inject(method = "getDestroySpeed", at = @At("RETURN"), cancellable = true)
	private void onGetMiningSpeedMultiplier(ItemStack stack, BlockState state, CallbackInfoReturnable<Float> info) {
		// Checks weather minecraft already speeds up the value
		if (info.getReturnValueF() == 1.0f)
			if (state.is(ModBlocks.GLOW_WORM_WOOL.get())) {
				info.setReturnValue(5.0f);
			} else if (state.is(ModBlocks.GLOW_WORM_WEB.get())) {
				info.setReturnValue(15.0f);
			}
	}
}


