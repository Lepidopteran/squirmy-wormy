package dancesaurus.squirmy_wormy.mixin;

import dancesaurus.squirmy_wormy.ModBlocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.level.block.state.BlockState;

@Mixin(ShearsItem.class)
abstract class ShearsMixin {


  @Inject(method = "getDestroySpeed", at = @At("RETURN"), cancellable = true)
  private void onGetMiningSpeedMultiplier(ItemStack stack, BlockState state, CallbackInfoReturnable<Float> info) {
    // Checks weather minecraft already speeds up the value
    if (info.getReturnValueF() == 1.0f)
      // TODO: Make glow worm web mine faster. Possibly adding another if statement or adding an else if statement.
      if (state.is(ModBlocks.GLOW_WORM_WOOL.get())) {
        info.setReturnValue(5.0f);
//      } else if (state.is(ModBlocks.GLOW_WORM_WEB)) {
//        info.setReturnValue(15.0f);
      }
  }
}


