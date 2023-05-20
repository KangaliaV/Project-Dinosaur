package com.kangalia.projectdinosaur.common.block.render;

import com.kangalia.projectdinosaur.common.block.model.EmbryonicWombModel;
import com.kangalia.projectdinosaur.common.blockentity.EmbryonicWombBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class EmbryonicWombRenderer extends GeoBlockRenderer<EmbryonicWombBlockEntity> {


    public EmbryonicWombRenderer(BlockEntityRendererProvider.Context context) {
        super(new EmbryonicWombModel());
    }

    @Override
    public RenderType getRenderType(EmbryonicWombBlockEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
