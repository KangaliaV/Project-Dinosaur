package com.kangalia.projectdinosaur.common.entity.render;

import com.kangalia.projectdinosaur.common.entity.AphanerammaEntity;
import com.kangalia.projectdinosaur.common.entity.model.AphanerammaModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class AphanerammaRenderer extends GeoEntityRenderer<AphanerammaEntity> {
    public AphanerammaRenderer(EntityRendererManager renderManager) {
        super(renderManager, new AphanerammaModel());
    }
}
