package dancesaurus.squirmy_wormy.blocks.client;

import com.mojang.blaze3d.vertex.PoseStack;
import dancesaurus.squirmy_wormy.ModBlocks;
import dancesaurus.squirmy_wormy.blocks.entity.GlowingBedEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;

public class GlowingBedItemRenderer {
	public static void render(
			PoseStack poseStack,
			MultiBufferSource bufferIn,
			int combinedLightIn,
			int combinedOverlayIn
	) {
		GlowingBedEntity bed = new GlowingBedEntity(BlockPos.ZERO, ModBlocks.GLOWING_BED.get().defaultBlockState());
		Minecraft
				.getInstance()
				.getBlockEntityRenderDispatcher()
				.renderItem(bed, poseStack, bufferIn, combinedLightIn, combinedOverlayIn);
	}
}
