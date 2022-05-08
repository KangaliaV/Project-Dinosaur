package com.kangalia.projectdinosaur.common.entity.model;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.CompsognathusEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;
import java.util.List;

public class CompsognathusModel extends AnimatedGeoModel<CompsognathusEntity> {

    @Override
    public ResourceLocation getModelLocation(CompsognathusEntity object) {
        return new ResourceLocation(ProjectDinosaur.MODID, "geo/compy_model.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(CompsognathusEntity object) {
        if (object.isAdult()) {
            if (object.getGender() == 0) {
                return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/compy_male.png");
            } else {
                return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/compy_female.png");
            }
        } else {
            return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/compy_baby.png");
        }
    }

    @Override
    public ResourceLocation getAnimationFileLocation(CompsognathusEntity animatable) {
        return new ResourceLocation(ProjectDinosaur.MODID, "animations/compy.animation.json");
    }

    @Override
    public void setLivingAnimations(CompsognathusEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);

        IBone head = this.getAnimationProcessor().getBone("skull");
        head.setRotationX(extraDataOfType.get(0).headPitch * Mth.DEG_TO_RAD / 2);
        head.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD / 2);
    }
}
