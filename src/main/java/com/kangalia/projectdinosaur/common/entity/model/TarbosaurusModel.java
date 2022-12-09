package com.kangalia.projectdinosaur.common.entity.model;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.TarbosaurusEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;
import java.util.List;

public class TarbosaurusModel extends AnimatedGeoModel<TarbosaurusEntity> {

    @Override
    public ResourceLocation getModelResource(TarbosaurusEntity object) {
        return new ResourceLocation(ProjectDinosaur.MODID, "geo/tarbosaurus_model.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TarbosaurusEntity object) {
        if (object.isAdult()) {
            if (object.getGender() == 0 && !object.isSleeping()) {
                return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/tarbosaurus_male.png");
            } else if (object.getGender() == 1 && !object.isSleeping()) {
                return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/tarbosaurus_female.png");
            } else if (object.getGender() == 0 && object.isSleeping()) {
                return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/tarbosaurus_male_sleeping.png");
            } else {
                return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/tarbosaurus_female_sleeping.png");
            }
        } else if (object.isSleeping()) {
            return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/tarbosaurus_baby_sleeping.png");
        } else {
            return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/tarbosaurus_baby.png");
        }
    }

    @Override
    public ResourceLocation getAnimationResource(TarbosaurusEntity animatable) {
        return new ResourceLocation(ProjectDinosaur.MODID, "animations/tarbosaurus.animation.json");
    }

    @Override
    public void setCustomAnimations(TarbosaurusEntity entity, int uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);

        IBone head = this.getAnimationProcessor().getBone("skull");
        IBone neck = this.getAnimationProcessor().getBone("neckbase");
        if (!entity.isSleeping()) {
            head.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD / 2);
            neck.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD / 3);
            if (!customPredicate.isMoving()) {
                head.setRotationX(extraDataOfType.get(0).headPitch * Mth.DEG_TO_RAD / 3);
                neck.setRotationX(extraDataOfType.get(0).headPitch * Mth.DEG_TO_RAD / 3);
            }
        }
    }
}
