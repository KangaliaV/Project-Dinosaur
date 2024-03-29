package com.kangalia.projectdinosaur.common.entity.render;

import com.kangalia.projectdinosaur.common.entity.creature.ScelidosaurusEntity;
import com.kangalia.projectdinosaur.common.entity.model.ScelidosaurusModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ScelidosaurusRenderer extends GeoEntityRenderer<ScelidosaurusEntity> {

    public ScelidosaurusRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ScelidosaurusModel());
    }

    @Override
    public void actuallyRender(PoseStack poseStack, ScelidosaurusEntity animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        float scale = animatable.getAgeScaleData();
        poseStack.scale(scale, scale, scale);
        this.shadowRadius = scale * 0.45F;
        super.actuallyRender(poseStack, animatable, model, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
