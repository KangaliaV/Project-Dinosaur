package com.kangalia.projectdinosaur.common.entity.model;

import com.google.common.collect.Maps;
import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.ScelidosaurusEntity;
import com.kangalia.projectdinosaur.common.entity.render.textures.CustomTexture;
import com.kangalia.projectdinosaur.common.entity.render.textures.ScelidosaurusTextures;
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

public class ScelidosaurusModel extends GeoModel<ScelidosaurusEntity> {

    private static final Map<String, ResourceLocation> LOCATION_CACHE = Maps.newHashMap();
    ScelidosaurusTextures textures = new ScelidosaurusTextures();

    @Override
    public ResourceLocation getModelResource(ScelidosaurusEntity object) {
        return new ResourceLocation(ProjectDinosaur.MODID, "geo/scelidosaurus_model.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ScelidosaurusEntity object) {
        String genes = object.getGenes().toLowerCase()+"_"+object.isAdult()+"_"+object.isSleeping();
        if (LOCATION_CACHE.containsKey(genes)) {
            return LOCATION_CACHE.get(genes);
        } else {
            try {
                NativeImage texture = textures.colourScelidosaurus(object);
                ResourceLocation location = new ResourceLocation(ProjectDinosaur.MODID, "scelidosaurus_" +genes);
                Minecraft.getInstance().getTextureManager().register(location, new CustomTexture(texture));
                LOCATION_CACHE.put(genes, location);
                return location;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/scelidosaurus/scelidosaurus_invis.png");
        }
    }

    @Override
    public ResourceLocation getAnimationResource(ScelidosaurusEntity animatable) {
        return new ResourceLocation(ProjectDinosaur.MODID, "animations/scelidosaurus.animation.json");
    }

    // This breaks the rendering in the Dino Scanner for some reason - need to find a better solution.
    /*@Override
    public void setCustomAnimations(ScelidosaurusEntity animatable, long instanceId, AnimationState<ScelidosaurusEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        if (animationState == null) return;

        EntityModelData extraDataOfType = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        CoreGeoBone head = this.getAnimationProcessor().getBone("skull");
        CoreGeoBone shoulders = this.getAnimationProcessor().getBone("shoulders");
        CoreGeoBone neck = this.getAnimationProcessor().getBone("neck");
        if (!animatable.isSleeping()) {
            head.setRotY(extraDataOfType.netHeadYaw() * Mth.DEG_TO_RAD / 3);
            shoulders.setRotY(extraDataOfType.netHeadYaw() * Mth.DEG_TO_RAD / 3);
            neck.setRotY(extraDataOfType.netHeadYaw() * Mth.DEG_TO_RAD / 3);
        }
    }*/
}
