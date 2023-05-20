package com.kangalia.projectdinosaur.common.entity.model;

import com.google.common.collect.Maps;
import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.AustralovenatorEntity;
import com.kangalia.projectdinosaur.common.entity.render.textures.AustralovenatorTextures;
import com.kangalia.projectdinosaur.common.entity.render.textures.CustomTexture;
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

public class AustralovenatorModel extends GeoModel<AustralovenatorEntity> {

    private static final Map<String, ResourceLocation> LOCATION_CACHE = Maps.newHashMap();
    AustralovenatorTextures textures = new AustralovenatorTextures();
    
    @Override
    public ResourceLocation getModelResource(AustralovenatorEntity object) {
        return new ResourceLocation(ProjectDinosaur.MODID, "geo/australovenator_model.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AustralovenatorEntity object) {
        String genes = object.getGenes().toLowerCase()+"_"+object.isAdult()+"_"+object.isSleeping();
        if (LOCATION_CACHE.containsKey(genes)) {
            return LOCATION_CACHE.get(genes);
        } else {
            try {
                NativeImage texture = textures.colourAustralovenator(object);
                ResourceLocation location = new ResourceLocation(ProjectDinosaur.MODID, "australovenator_" +genes);
                Minecraft.getInstance().getTextureManager().register(location, new CustomTexture(texture));
                LOCATION_CACHE.put(genes, location);
                return location;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/australovenator_invis.png");
        }
    }

    @Override
    public ResourceLocation getAnimationResource(AustralovenatorEntity animatable) {
        return new ResourceLocation(ProjectDinosaur.MODID, "animations/australovenator.animation.json");
    }

    @Override
    public void setCustomAnimations(AustralovenatorEntity animatable, long instanceId, AnimationState<AustralovenatorEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        if (animationState == null) return;

        EntityModelData extraDataOfType = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        CoreGeoBone head = this.getAnimationProcessor().getBone("N3");
        CoreGeoBone neck = this.getAnimationProcessor().getBone("N1");
        if (!animatable.isSleeping()) {
            head.setRotY(extraDataOfType.netHeadYaw() * Mth.DEG_TO_RAD / 2);
            neck.setRotY(extraDataOfType.netHeadYaw() * Mth.DEG_TO_RAD / 3);
            if (!animationState.isMoving()) {
                head.setRotX(extraDataOfType.headPitch() * Mth.DEG_TO_RAD / 3);
                neck.setRotX(extraDataOfType.headPitch() * Mth.DEG_TO_RAD / 3);
            }
        }
    }
}
