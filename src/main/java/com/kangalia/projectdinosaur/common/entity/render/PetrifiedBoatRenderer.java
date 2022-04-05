package com.kangalia.projectdinosaur.common.entity.render;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.util.ResourceLocation;

public class PetrifiedBoatRenderer extends BoatRenderer {
    private static final ResourceLocation BOAT_TEXTURE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/boat/petrified_boat.png");

    public PetrifiedBoatRenderer(EntityRendererManager entityRendererManager) {
        super(entityRendererManager);
    }

    @Override
    public ResourceLocation getTextureLocation(BoatEntity entity) {
        return BOAT_TEXTURE;
    }
}

