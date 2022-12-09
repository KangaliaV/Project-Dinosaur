package com.kangalia.projectdinosaur.common.entity.model;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.AphanerammaEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class AphanerammaModel extends AnimatedGeoModel<AphanerammaEntity> {

    @Override
    public ResourceLocation getModelResource(AphanerammaEntity object) {
        return new ResourceLocation(ProjectDinosaur.MODID, "geo/aphaneramma_model.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AphanerammaEntity object) {
        if (object.isAdult()) {
            if (object.getGender() == 0 && !object.isSleeping()) {
                return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/aphaneramma_male.png");
            } else if (object.getGender() == 1 && !object.isSleeping()) {
                return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/aphaneramma_female.png");
            } else if (object.getGender() == 0 && object.isSleeping()) {
                return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/aphaneramma_male_sleeping.png");
            } else {
                return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/aphaneramma_female_sleeping.png");
            }
        } else if (object.isSleeping()) {
            return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/aphaneramma_baby_sleeping.png");
        } else {
            return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/aphaneramma_baby.png");
        }
    }

    @Override
    public ResourceLocation getAnimationResource(AphanerammaEntity animatable) {
        return new ResourceLocation(ProjectDinosaur.MODID, "animations/aphaneramma.animation.json");
    }

    @Override
    public void setCustomAnimations(AphanerammaEntity entity, int uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);

        IBone head = this.getAnimationProcessor().getBone("bone10");
        if (!entity.isSleeping()) {
            head.setRotationX(extraDataOfType.get(0).headPitch * Mth.DEG_TO_RAD / 2);
            head.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD / 2);
        }
    }
}
