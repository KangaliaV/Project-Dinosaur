package com.kangalia.projectdinosaur.common.entity.render;

import com.kangalia.projectdinosaur.common.entity.AphanerammaEntity;
import com.kangalia.projectdinosaur.common.entity.model.AphanerammaModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class AphanerammaRenderer extends GeoEntityRenderer<AphanerammaEntity> {

    public AphanerammaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new AphanerammaModel());
        this.shadowRadius = 0.6F;
    }
}
