package com.kangalia.projectdinosaur.common.entity.model;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.TrilobiteEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.model.AnimatedGeoModel;

@OnlyIn(Dist.CLIENT)
public class TrilobiteModel extends AnimatedGeoModel<TrilobiteEntity> {

    @Override
    public ResourceLocation getModelResource(TrilobiteEntity object) {
        return new ResourceLocation(ProjectDinosaur.MODID, "geo/trilobite_model.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TrilobiteEntity object) {
        if (object.getVariant() == 0) {
            return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/trilobite1.png");
        } else {
            return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/trilobite2.png");
        }
    }

    @Override
    public ResourceLocation getAnimationResource(TrilobiteEntity animatable) {
        return new ResourceLocation(ProjectDinosaur.MODID, "animations/trilobite.animation.json");
    }
}
