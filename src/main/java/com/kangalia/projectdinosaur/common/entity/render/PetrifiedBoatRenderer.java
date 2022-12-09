package com.kangalia.projectdinosaur.common.entity.render;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.resources.ResourceLocation;

public class PetrifiedBoatRenderer extends BoatRenderer {
    public PetrifiedBoatRenderer(EntityRendererProvider.Context p_234563_, boolean p_234564_) {
        super(p_234563_, p_234564_);
    }
    /*private static final ResourceLocation BOAT_TEXTURE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/boat/petrified_boat.png");

    public PetrifiedBoatRenderer(EntityRendererProvider.Context entityRendererManager, boolean ) {
        super(entityRendererManager);
    }

    @Override
    public ResourceLocation getTextureLocation(Boat entity) {
        return BOAT_TEXTURE;
    }*/
}

