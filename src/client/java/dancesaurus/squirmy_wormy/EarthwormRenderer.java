package dancesaurus.squirmy_wormy;

import dancesaurus.squirmy_wormy.entities.Earthworm;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class EarthwormRenderer extends GeoEntityRenderer<Earthworm> {

    public EarthwormRenderer(EntityRendererFactory.Context context) {
        super(context, new DefaultedEntityGeoModel<>(new Identifier(ModInfo.MOD_ID, "earthworm")));
    }
}