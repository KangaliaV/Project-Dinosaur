package com.kangalia.projectdinosaur.common.entity.model;

import com.google.common.collect.Maps;
import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.ScelidosaurusEntity;
import com.kangalia.projectdinosaur.common.entity.render.textures.AustralovenatorTextures;
import com.kangalia.projectdinosaur.common.entity.render.textures.CustomTexture;
import com.kangalia.projectdinosaur.common.entity.render.textures.ScelidosaurusTextures;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ScelidosaurusModel extends AnimatedGeoModel<ScelidosaurusEntity> {

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

    @Override
    public void setCustomAnimations(ScelidosaurusEntity entity, int uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        if (customPredicate == null) return;

        List<EntityModelData> extraDataOfType = customPredicate.getExtraDataOfType(EntityModelData.class);

        IBone head = this.getAnimationProcessor().getBone("skull");
        IBone shoulders = this.getAnimationProcessor().getBone("shoulders");
        IBone neck = this.getAnimationProcessor().getBone("neck");
        if (!entity.isSleeping()) {
            head.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD / 3);
            shoulders.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD / 3);
            neck.setRotationY(extraDataOfType.get(0).netHeadYaw * Mth.DEG_TO_RAD / 3);
        }
    }
}
