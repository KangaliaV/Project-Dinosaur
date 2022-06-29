package com.kangalia.projectdinosaur.common.entity.model;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.ScelidosaurusEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;
import java.util.List;

public class ScelidosaurusModel extends AnimatedGeoModel<ScelidosaurusEntity> {

    @Override
    public ResourceLocation getModelLocation(ScelidosaurusEntity object) {
        return new ResourceLocation(ProjectDinosaur.MODID, "geo/scelidosaurus_model.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ScelidosaurusEntity object) {
        if (object.isAdult()) {
            if (object.getGender() == 0 && !object.isSleeping()) {
                return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/scelidosaurus_male.png");
            } else if (object.getGender() == 1 && !object.isSleeping()) {
                return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/scelidosaurus_female.png");
            } else if (object.getGender() == 0 && object.isSleeping()) {
                return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/scelidosaurus_male_sleeping.png");
            } else {
                return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/scelidosaurus_female_sleeping.png");
            }
        } else if (object.isSleeping()) {
            return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/scelidosaurus_baby_sleeping.png");
        } else {
            return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/scelidosaurus_baby.png");
        }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ScelidosaurusEntity animatable) {
        return new ResourceLocation(ProjectDinosaur.MODID, "animations/scelidosaurus.animation.json");
    }

    @Override
    public void setLivingAnimations(ScelidosaurusEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);

        IBone head = this.getAnimationProcessor().getBone("skull");
        IBone neckbase = this.getAnimationProcessor().getBone("neckbase");
        IBone neck = this.getAnimationProcessor().getBone("neck1");
        if (!entity.isSleeping()) {
            head.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD / 3);
            neckbase.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD / 5);
            neck.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD / 3);
        }
    }
}
