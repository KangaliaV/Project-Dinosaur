package com.kangalia.projectdinosaur.common.entity.model;

import com.google.common.collect.Maps;
import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.GastornisEntity;
import com.kangalia.projectdinosaur.common.entity.render.textures.CustomTexture;
import com.kangalia.projectdinosaur.common.entity.render.textures.GastornisTextures;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GastornisModel extends AnimatedGeoModel<GastornisEntity> {

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
