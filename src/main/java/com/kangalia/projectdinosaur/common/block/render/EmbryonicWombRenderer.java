package com.kangalia.projectdinosaur.common.block.render;

import com.kangalia.projectdinosaur.common.blockentity.EmbryonicWombBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class EmbryonicWombRenderer extends GeoBlockRenderer<EmbryonicWombBlockEntity> {


    public EmbryonicWombRenderer(GeoModel<EmbryonicWombBlockEntity> model) {
        super(model);
    }

    @Override
    public RenderType getRenderType(EmbryonicWombBlockEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
