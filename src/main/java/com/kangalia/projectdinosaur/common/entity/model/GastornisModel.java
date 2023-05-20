package com.kangalia.projectdinosaur.common.entity.model;

import com.google.common.collect.Maps;
import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.GastornisEntity;
import com.kangalia.projectdinosaur.common.entity.render.textures.CustomTexture;
import com.kangalia.projectdinosaur.common.entity.render.textures.GastornisTextures;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import java.io.IOException;
import java.util.Map;

public class GastornisModel extends GeoModel<GastornisEntity> {

    private static final Map<String, ResourceLocation> LOCATION_CACHE = Maps.newHashMap();
    GastornisTextures textures = new GastornisTextures();

    @Override
    public ResourceLocation getModelResource(GastornisEntity object) {
        return new ResourceLocation(ProjectDinosaur.MODID, "geo/gastornis_model.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GastornisEntity object) {
        String genes = object.getGenes().toLowerCase()+"_"+object.isAdult()+"_"+object.isSleeping();
        if (LOCATION_CACHE.containsKey(genes)) {
            return LOCATION_CACHE.get(genes);
        } else {
            try {
                NativeImage texture = textures.colourGastornis(object);
                ResourceLocation location = new ResourceLocation(ProjectDinosaur.MODID, "gastornis_" +genes);
                Minecraft.getInstance().getTextureManager().register(location, new CustomTexture(texture));
                LOCATION_CACHE.put(genes, location);
                return location;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/gastornis/gastornis_invis.png");
        }
    }

    @Override
    public ResourceLocation getAnimationResource(GastornisEntity animatable) {
        return new ResourceLocation(ProjectDinosaur.MODID, "animations/gastornis.animation.json");
    }

    public void setCustomAnimations(GastornisEntity animatable, long instanceId, AnimationState<GastornisEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        if (animationState == null) return;

        EntityModelData extraDataOfType = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        CoreGeoBone shoulders = this.getAnimationProcessor().getBone("shoulders");
        CoreGeoBone neck1 = this.getAnimationProcessor().getBone("neck1");
        CoreGeoBone neck2 = this.getAnimationProcessor().getBone("neck2");

        if (!animatable.isSleeping()) {
            shoulders.setRotY(extraDataOfType.netHeadYaw() * Mth.DEG_TO_RAD / 4);
            neck1.setRotY(extraDataOfType.netHeadYaw() * Mth.DEG_TO_RAD / 3);
            neck2.setRotY(extraDataOfType.netHeadYaw() * Mth.DEG_TO_RAD / 3);
        }
    }
}
