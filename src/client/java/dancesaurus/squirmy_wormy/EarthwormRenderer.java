package dancesaurus.squirmy_wormy;

import dancesaurus.squirmy_wormy.SquirmyWormyClient;
import dancesaurus.squirmy_wormy.entities.Earthworm;
import dancesaurus.squirmy_wormy.models.EarthwormModel;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import static dancesaurus.squirmy_wormy.ModInfo.MOD_ID;

public class EarthwormRenderer extends MobEntityRenderer<Earthworm, EarthwormModel<Earthworm>> {

    public EarthwormRenderer(EntityRendererFactory.Context context) {
        super(context, new EarthwormModel<>(context.getPart(SquirmyWormyClient.EARTHWORM_LAYER)), 0.125f);
    }

    @Override
    public void render(Earthworm mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if (mobEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(Earthworm entity) {
        return Identifier.of(MOD_ID, "textures/entity/earthworm/earthworm_texture.png");
    }
}