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
        /*String origin = "textures/entity/mob/dino/gastornis/gastornis";
        String png = ".png";
        String sleeping = "_sleeping";
        String albino = "_albino";
        String melanistic = "_melanistic";
        String path = origin;
        String genome = object.getGenes();

        if (object.getColourMorph() == 1) { //Albino
            if (object.isBaby()) {
                path = path + "_baby";
            } else {
                if (object.getGender() == 0) {
                    path = path + "_male";
                } else {
                    path = path + "_female";
                }
            }
            if (object.isSleeping()) {
                path = path + sleeping;
            }
            path = path + albino;
        } else if (object.getColourMorph() == 2) { //Melanistic
            if (object.isBaby()) {
                path = path + "_baby";
            } else {
                if (object.getGender() == 0) {
                    path = path + "_male";
                } else {
                    path = path + "_female";
                }
            }
            if (object.isSleeping()) {
                path = path + sleeping;
            }
            path = path + melanistic;
        } else { //Normal
            if (object.isBaby()) {
                path = path + "_baby";
            } else {
                path = path + switch (object.getGeneDominance(1)) { //Feather Colour
                    case 1 -> "_black";
                    case 2 -> "_cream";
                    case 3 -> "_red";
                    case 4 -> "_grey";
                    case 5 -> "_white";
                    default -> "_brown";
                };
            }
            if (object.isSleeping()) {
                path = path + sleeping;
            }
        }*/
        return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/gastornis/gastornis_invis.png");
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
