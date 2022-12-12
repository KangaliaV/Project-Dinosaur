package com.kangalia.projectdinosaur.common.block.render;

import com.kangalia.projectdinosaur.common.block.model.EmbryonicWombModel;
import com.kangalia.projectdinosaur.common.blockentity.EmbryonicWombBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class EmbryonicWombRenderer extends GeoBlockRenderer<EmbryonicWombBlockEntity> {

    public EmbryonicWombRenderer(BlockEntityRendererProvider.Context rendererProvider) {
        super(rendererProvider, new EmbryonicWombModel());
    }

    @Override
    public RenderType getRenderType(EmbryonicWombBlockEntity animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
