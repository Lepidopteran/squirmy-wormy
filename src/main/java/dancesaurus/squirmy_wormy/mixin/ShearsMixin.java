package dancesaurus.squirmy_wormy.mixin;

import dancesaurus.squirmy_wormy.ModBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.fabricmc.fabric.api.mininglevel.v1.FabricMineableTags;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;


@Mixin(ShearsItem.class)
abstract class ShearsMixin {


  @Inject(method = "getMiningSpeedMultiplier", at = @At("RETURN"), cancellable = true)
  private void onGetMiningSpeedMultiplier(ItemStack stack, BlockState state, CallbackInfoReturnable<Float> info) {
    // Checks weather minecraft already speeds up the value
    if (info.getReturnValueF() == 1.0f) {
      // TODO: Make glow worm web mine faster. Possibly adding another if statement or adding an else if statement.
      if (state.isOf(ModBlocks.GLOW_WORM_WEB) || state.isOf(ModBlocks.GLOW_WORM_WOOL)) {
        info.setReturnValue(5.0f);
      }
    }
  }
}

