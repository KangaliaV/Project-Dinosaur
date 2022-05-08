package com.kangalia.projectdinosaur.common.entity.render;

import com.kangalia.projectdinosaur.common.entity.creature.CompsognathusEntity;
import com.kangalia.projectdinosaur.common.entity.model.CompsognathusModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CompsognathusRenderer extends GeoEntityRenderer<CompsognathusEntity> {

    public CompsognathusRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CompsognathusModel());
    }

    @Override
    public void render(GeoModel model, CompsognathusEntity animatable, float partialTicks, RenderType type, PoseStack matrixStackIn, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        float scale = animatable.getAgeScaleData();
        matrixStackIn.scale(scale, scale, scale);
        this.shadowRadius = scale * 0.2F;
        super.render(model, animatable, partialTicks, type, matrixStackIn, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}
