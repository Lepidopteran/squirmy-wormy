package dancesaurus.squirmy_wormy.entities.client;

import com.mojang.blaze3d.vertex.PoseStack;
import dancesaurus.squirmy_wormy.SquirmyWormy;
import dancesaurus.squirmy_wormy.entities.Earthworm;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class EarthwormRenderer extends GeoEntityRenderer<Earthworm> {

    public EarthwormRenderer(EntityRendererProvider.Context context) {
        super(context, new DefaultedEntityGeoModel<>(new ResourceLocation(SquirmyWormy.MOD_ID, "earthworm")));
    }

    @Override
    public void render(
            @NotNull Earthworm entity,
            float entityYaw,
            float partialTick,
            @NotNull PoseStack poseStack,
            @NotNull MultiBufferSource bufferSource,
            int packedLight
    ) {
        if(entity.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}