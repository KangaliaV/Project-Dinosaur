package com.kangalia.projectdinosaur.common.entity.model;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.AustralovenatorEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;
import java.util.List;

public class AustralovenatorModel extends AnimatedGeoModel<AustralovenatorEntity> {

    @Override
    public ResourceLocation getModelLocation(AustralovenatorEntity object) {
        return new ResourceLocation(ProjectDinosaur.MODID, "geo/australovenator_model.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(AustralovenatorEntity object) {
        if (object.isAdult()) {
            if (object.getGender() == 0 && !object.isSleeping()) {
                return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator_male.png");
            } else if (object.getGender() == 1 && !object.isSleeping()) {
                return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator_female.png");
            } else if (object.getGender() == 0 && object.isSleeping()) {
                return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator_male_sleeping.png");
            } else {
                return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator_female_sleeping.png");
            }
        } else if (object.isSleeping()) {
            return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator_baby_sleeping.png");
        } else {
            return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator_baby.png");
        }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(AustralovenatorEntity animatable) {
        return new ResourceLocation(ProjectDinosaur.MODID, "animations/australovenator.animation.json");
    }

    @Override
    public void setLivingAnimations(AustralovenatorEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);

        IBone head = this.getAnimationProcessor().getBone("H1");
        IBone neck = this.getAnimationProcessor().getBone("N1");
        if (!entity.isSleeping()) {
            head.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD / 4);
            neck.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD / 2);
            if (!customPredicate.isMoving()) {
                head.setRotationX(extraDataOfType.get(0).headPitch * Mth.DEG_TO_RAD / 4);
                neck.setRotationX(extraDataOfType.get(0).headPitch * Mth.DEG_TO_RAD / 2);
            }
        }
    }
}
