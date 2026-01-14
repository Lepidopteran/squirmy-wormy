package dancesaurus.squirmy_wormy.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import dancesaurus.squirmy_wormy.ModBlocks;
import dancesaurus.squirmy_wormy.blocks.client.GlowingBedItemRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockEntityWithoutLevelRenderer.class)
abstract class BuiltinModelItemRendererMixin {
	@Inject(method = "renderByItem", at = @At("HEAD"), cancellable = true)
	private void fabric_onRender(ItemStack stack, ItemDisplayContext mode, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay, CallbackInfo info) {

		if (stack.is(ModBlocks.GLOWING_BED.get().asItem())) {
			GlowingBedItemRenderer.render(matrices, vertexConsumers, light, overlay);
			info.cancel();
		}
	}
}