package dancesaurus.squirmy_wormy.blocks.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import dancesaurus.squirmy_wormy.ModBlockEntities;
import dancesaurus.squirmy_wormy.SquirmyWormy;
import dancesaurus.squirmy_wormy.blocks.GlowingBed;
import dancesaurus.squirmy_wormy.blocks.entity.GlowingBedEntity;
import dancesaurus.squirmy_wormy.platform.Services;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import org.jetbrains.annotations.NotNull;

import static net.minecraft.client.renderer.Sheets.BED_SHEET;

public class GlowingBedRenderer implements BlockEntityRenderer<GlowingBedEntity> {
	private final ModelPart headRoot;
	private final ModelPart footRoot;

	public GlowingBedRenderer(BlockEntityRendererProvider.Context context) {
		this.headRoot = context.bakeLayer(ModelLayers.BED_HEAD);
		this.footRoot = context.bakeLayer(ModelLayers.BED_FOOT);
	}

	@Override
	public void render(
			GlowingBedEntity blockEntity,
			float partialTick,
			@NotNull PoseStack poseStack,
			@NotNull MultiBufferSource buffer,
			int packedLight,
			int packedOverlay
	) {


		Level level = blockEntity.getLevel();
		if (level != null) {
			BlockState blockstate = blockEntity.getBlockState();
			DoubleBlockCombiner.NeighborCombineResult<? extends GlowingBedEntity> neighborcombineresult = DoubleBlockCombiner.combineWithNeigbour(
					ModBlockEntities.GLOWING_BED_ENTITY.get(),
					GlowingBed::getBlockType,
					GlowingBed::getConnectedDirection,
					ChestBlock.FACING,
					blockstate,
					level,
					blockEntity.getBlockPos(),
					(ignored, ignoredAsWell) -> false
			);
			int i = neighborcombineresult.apply(new BrightnessCombiner<>()).get(packedLight);

			this.renderPiece(
					poseStack,
					buffer,
					blockstate.getValue(GlowingBed.PART) == BedPart.HEAD ? this.headRoot : this.footRoot,
					blockstate.getValue(GlowingBed.FACING),
					i,
					packedOverlay,
					false
			);

		} else {
			this.renderPiece(
					poseStack,
					buffer,
					this.headRoot,
					Direction.SOUTH,
					packedLight,
					packedOverlay,
					false
			);

			this.renderPiece(
					poseStack,
					buffer,
					this.footRoot,
					Direction.SOUTH,
					packedLight,
					packedOverlay,
					true
			);
		}

	}

	/**
	 * @param foot {@code true} if piece to render is the foot of the bed, {@code
	 *             false} otherwise or if being rendered by a {@link
	 *             net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer}
	 */
	private void renderPiece(
			PoseStack poseStack,
			MultiBufferSource bufferSource,
			ModelPart modelPart,
			Direction direction,
			int packedLight,
			int packedOverlay,
			boolean foot
	) {

		Material mainMaterial = new Material(BED_SHEET, new ResourceLocation(SquirmyWormy.MOD_ID, "entity/bed/glowing"));

		poseStack.pushPose();
		poseStack.translate(0.0F, 0.5625F, foot ? -1.0F : 0.0F);
		poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
		poseStack.translate(0.5F, 0.5F, 0.5F);
		poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F + direction.toYRot()));
		poseStack.translate(-0.5F, -0.5F, -0.5F);
		VertexConsumer vertexconsumer = mainMaterial.buffer(bufferSource, RenderType::entitySolid);
		modelPart.render(poseStack, vertexconsumer, packedLight, packedOverlay);
		poseStack.popPose();
	}
}
