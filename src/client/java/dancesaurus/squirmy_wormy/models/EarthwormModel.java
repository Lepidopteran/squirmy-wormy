package dancesaurus.squirmy_wormy.models;

import dancesaurus.squirmy_wormy.EarthwormAnimations;
import dancesaurus.squirmy_wormy.entities.Earthworm;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class EarthwormModel<T extends Earthworm> extends SinglePartEntityModel<T> {
	private final ModelPart bone;
	public EarthwormModel(ModelPart root) {
		this.bone = root.getChild("bone");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(0, 4).cuboid(0.5F, -1.0F, -1.5F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 8).cuboid(0.0F, -1.0F, 1.5F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(0.0F, -1.0F, -4.5F, 1.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 16, 16);
	}
	@Override
	public void setAngles(Earthworm entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.animateMovement(EarthwormAnimations.EARTHWORM_WIGGLE, limbSwing, limbSwingAmount, 2.0f, 2.5f);
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		bone.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return bone;
	}
}