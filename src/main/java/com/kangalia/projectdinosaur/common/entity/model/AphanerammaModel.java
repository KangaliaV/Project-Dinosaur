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

    @Override
    public void setLivingAnimations(AphanerammaEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);

        IBone head = this.getAnimationProcessor().getBone("bone10");
        head.setRotationX(extraDataOfType.get(0).headPitch * Mth.DEG_TO_RAD / 2);
        head.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD / 2);
    }
}
