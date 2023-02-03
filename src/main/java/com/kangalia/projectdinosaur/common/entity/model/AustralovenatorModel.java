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
    public ResourceLocation getModelResource(AustralovenatorEntity object) {
        return new ResourceLocation(ProjectDinosaur.MODID, "geo/australovenator_model.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AustralovenatorEntity object) {
        return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/australovenator_invis.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AustralovenatorEntity animatable) {
        return new ResourceLocation(ProjectDinosaur.MODID, "animations/australovenator.animation.json");
    }

    @Override
    public void setCustomAnimations(AustralovenatorEntity entity, int uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);

        IBone head = this.getAnimationProcessor().getBone("N3");
        IBone neck = this.getAnimationProcessor().getBone("N1");
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
