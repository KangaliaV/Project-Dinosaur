package com.kangalia.projectdinosaur.common.entity.render;

import com.kangalia.projectdinosaur.common.entity.creature.TrilobiteEntity;
import com.kangalia.projectdinosaur.common.entity.model.TrilobiteModel;
import com.kangalia.projectdinosaur.common.entity.render.layer.TrilobiteLayer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class TrilobiteRenderer extends GeoEntityRenderer<TrilobiteEntity> {

    public TrilobiteRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new TrilobiteModel());
        this.addLayer(new TrilobiteLayer(this, 0));
        this.addLayer(new TrilobiteLayer(this, 1));
        this.addLayer(new TrilobiteLayer(this, 2));
        this.addLayer(new TrilobiteLayer(this, 3));
    }

    @Override
    public void render(GeoModel model, TrilobiteEntity animatable, float partialTicks, RenderType type, PoseStack matrixStackIn, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        float scale = animatable.getAgeScaleData();
        this.shadowRadius = scale * 0.3F;
        super.render(model, animatable, partialTicks, type, matrixStackIn, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}
