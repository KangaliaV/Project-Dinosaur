package com.kangalia.projectdinosaur.common.entity.render;

import com.kangalia.projectdinosaur.common.entity.creature.AustralovenatorEntity;
import com.kangalia.projectdinosaur.common.entity.model.AustralovenatorModel;
import com.kangalia.projectdinosaur.common.entity.render.layer.AustralovenatorLayer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class AustralovenatorRenderer extends GeoEntityRenderer<AustralovenatorEntity> {

    public AustralovenatorRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new AustralovenatorModel());
        addLayer(new AustralovenatorLayer(this, 0));
        addLayer(new AustralovenatorLayer(this, 1));
        addLayer(new AustralovenatorLayer(this, 2));
        addLayer(new AustralovenatorLayer(this, 3));
        addLayer(new AustralovenatorLayer(this, 4));
    }

    @Override
    public void render(GeoModel model, AustralovenatorEntity animatable, float partialTicks, RenderType type, PoseStack matrixStackIn, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        float scale = animatable.getAgeScaleData();
        this.shadowRadius = scale * 0.2F;
        super.render(model, animatable, partialTicks, type, matrixStackIn, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}
