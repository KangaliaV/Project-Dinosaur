package com.kangalia.projectdinosaur.common.entity.model;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.GastornisEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;
import java.util.List;

public class GastornisModel extends AnimatedGeoModel<GastornisEntity> {

    @Override
    public ResourceLocation getModelResource(GastornisEntity object) {
        return new ResourceLocation(ProjectDinosaur.MODID, "geo/gastornis_model.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GastornisEntity object) {
        if (object.isAdult()) {
            if (object.getGender() == 0 && !object.isSleeping()) {
                return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/gastornis_male.png");
            } else if (object.getGender() == 1 && !object.isSleeping()) {
                return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/gastornis_female.png");
            } else if (object.getGender() == 0 && object.isSleeping()) {
                return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/gastornis_male_sleeping.png");
            } else {
                return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/gastornis_female_sleeping.png");
            }
        } else if (object.isSleeping()) {
            return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/gastornis_baby_sleeping.png");
        } else {
            return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/gastornis_baby.png");
        }
    }

    @Override
    public ResourceLocation getAnimationResource(GastornisEntity animatable) {
        return new ResourceLocation(ProjectDinosaur.MODID, "animations/gastornis.animation.json");
    }

    @Override
    public void setCustomAnimations(GastornisEntity entity, int uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);

        IBone shoulders = this.getAnimationProcessor().getBone("shoulders");
        IBone neck1 = this.getAnimationProcessor().getBone("neck1");
        IBone neck2 = this.getAnimationProcessor().getBone("neck2");

        if (!entity.isSleeping()) {
            shoulders.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD / 4);
            neck1.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD / 3);
            neck2.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD / 3);
        }
    }
}
