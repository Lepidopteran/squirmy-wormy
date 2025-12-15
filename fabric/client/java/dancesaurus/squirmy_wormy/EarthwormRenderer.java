package dancesaurus.squirmy_wormy;

import dancesaurus.squirmy_wormy.entities.Earthworm;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class EarthwormRenderer extends GeoEntityRenderer<Earthworm> {

    public EarthwormRenderer(EntityRendererFactory.Context context) {
        super(context, new DefaultedEntityGeoModel<>(new Identifier(SquirmyWormy.MOD_ID, "earthworm")));
    }

    @Override
    public void render(Earthworm entity, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}