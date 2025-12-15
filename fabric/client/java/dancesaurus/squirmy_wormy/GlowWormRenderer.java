package dancesaurus.squirmy_wormy;

import dancesaurus.squirmy_wormy.entities.Earthworm;
import dancesaurus.squirmy_wormy.entities.GlowWorm;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class GlowWormRenderer extends GeoEntityRenderer<GlowWorm> {

	public GlowWormRenderer(EntityRendererFactory.Context context) {
		super(context, new DefaultedEntityGeoModel<>(new Identifier(SquirmyWormy.MOD_ID, "glow_worm")));
		addRenderLayer(new AutoGlowingGeoLayer<>(this));
	}

	@Override
	public void render(GlowWorm entity, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
		if(entity.isBaby()) {
			poseStack.scale(0.5f, 0.5f, 0.5f);
		} else {
			poseStack.scale(1f, 1f, 1f);
		}

		if(entity.isClimbing()) {
			poseStack.multiply((RotationAxis.NEGATIVE_Y.rotationDegrees(90f)));
		}

		super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
	}
}
