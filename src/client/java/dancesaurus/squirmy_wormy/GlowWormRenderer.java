package dancesaurus.squirmy_wormy;

import dancesaurus.squirmy_wormy.entities.Earthworm;
import dancesaurus.squirmy_wormy.entities.GlowWorm;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public class GlowWormRenderer extends GeoEntityRenderer<GlowWorm> {

    public GlowWormRenderer(EntityRendererFactory.Context context) {
        super(context, new DefaultedEntityGeoModel<>(new Identifier(ModInfo.MOD_ID, "glow_worm")));
        addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }
}