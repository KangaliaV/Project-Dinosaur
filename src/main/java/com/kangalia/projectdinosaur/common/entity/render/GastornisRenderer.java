package com.kangalia.projectdinosaur.common.entity.render;

import com.kangalia.projectdinosaur.common.entity.creature.GastornisEntity;
import com.kangalia.projectdinosaur.common.entity.model.GastornisModel;
import com.kangalia.projectdinosaur.common.entity.render.layer.GastornisLayer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GastornisRenderer extends GeoEntityRenderer<GastornisEntity> {

    public GastornisRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GastornisModel());
        addLayer(new GastornisLayer(this, 0));
        addLayer(new GastornisLayer(this, 1));
        addLayer(new GastornisLayer(this, 2));
        addLayer(new GastornisLayer(this, 3));
        addLayer(new GastornisLayer(this, 4));
        addLayer(new GastornisLayer(this, 5));
        addLayer(new GastornisLayer(this, 6));
    }

    @Override
    public void render(GeoModel model, GastornisEntity animatable, float partialTicks, RenderType type, PoseStack matrixStackIn, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        float scale = animatable.getAgeScaleData();
        this.shadowRadius = scale * 0.4F;
        super.render(model, animatable, partialTicks, type, matrixStackIn, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}
