package com.kangalia.projectdinosaur.common.entity.model;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.AphanerammaEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AphanerammaModel extends AnimatedGeoModel<AphanerammaEntity> {

    @Override
    public ResourceLocation getModelLocation(AphanerammaEntity object) {
        return new ResourceLocation(ProjectDinosaur.MODID, "geo/aphaneramma_model.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(AphanerammaEntity object) {
        return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/aphaneramma.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(AphanerammaEntity animatable) {
        return new ResourceLocation(ProjectDinosaur.MODID, "animations/aphaneramma.animation.json");
    }
}
