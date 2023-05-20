package com.kangalia.projectdinosaur.common.entity.model;

import com.google.common.collect.Maps;
import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.AphanerammaEntity;
import com.kangalia.projectdinosaur.common.entity.render.textures.AphanerammaTextures;
import com.kangalia.projectdinosaur.common.entity.render.textures.CustomTexture;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import java.io.IOException;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class AphanerammaModel extends GeoModel<AphanerammaEntity> {

    private static final Map<String, ResourceLocation> LOCATION_CACHE = Maps.newHashMap();
    AphanerammaTextures textures = new AphanerammaTextures();

    @Override
    public ResourceLocation getModelResource(AphanerammaEntity object) {
        return new ResourceLocation(ProjectDinosaur.MODID, "geo/aphaneramma_model.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AphanerammaEntity object) {
        String genes = object.getGenes().toLowerCase()+"_"+object.isAdult()+"_"+object.isSleeping();
        if (LOCATION_CACHE.containsKey(genes)) {
            return LOCATION_CACHE.get(genes);
        } else {
            try {
                NativeImage texture = textures.colourAphaneramma(object);
                ResourceLocation location = new ResourceLocation(ProjectDinosaur.MODID, "aphaneramma_" + genes);
                Minecraft.getInstance().getTextureManager().register(location, new CustomTexture(texture));
                LOCATION_CACHE.put(genes, location);
                return location;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/aphaneramma/aphaneramma_invis.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AphanerammaEntity animatable) {
        return new ResourceLocation(ProjectDinosaur.MODID, "animations/aphaneramma.animation.json");
    }

    // This breaks the rendering in the Dino Scanner for some reason - need to find a better solution.
    /*@Override
    public void setCustomAnimations(AphanerammaEntity animatable, long instanceId, AnimationState<AphanerammaEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        if (animationState == null) return;

        EntityModelData extraDataOfType = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        CoreGeoBone head = this.getAnimationProcessor().getBone("bone10");
        if (!animatable.isSleeping()) {
            head.setRotX(extraDataOfType.headPitch() * Mth.DEG_TO_RAD / 2);
            head.setRotY(extraDataOfType.netHeadYaw() * Mth.DEG_TO_RAD / 2);
        }
    }*/
}
