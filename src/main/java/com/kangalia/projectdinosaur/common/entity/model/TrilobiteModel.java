package com.kangalia.projectdinosaur.common.entity.model;

import com.google.common.collect.Maps;
import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.TrilobiteEntity;
import com.kangalia.projectdinosaur.common.entity.render.textures.CustomTexture;
import com.kangalia.projectdinosaur.common.entity.render.textures.TrilobiteTextures;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import java.io.IOException;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class TrilobiteModel extends AnimatedGeoModel<TrilobiteEntity> {

    private static final Map<String, ResourceLocation> LOCATION_CACHE = Maps.newHashMap();
    TrilobiteTextures textures = new TrilobiteTextures();

    @Override
    public ResourceLocation getModelResource(TrilobiteEntity object) {
        return new ResourceLocation(ProjectDinosaur.MODID, "geo/trilobite_model.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TrilobiteEntity object) {
        String genes = object.getGenes().toLowerCase()+"_"+object.isAdult()+"_"+object.isSleeping();
        if (LOCATION_CACHE.containsKey(genes)) {
            return LOCATION_CACHE.get(genes);
        } else {
            try {
                NativeImage texture = textures.colourTrilobite(object);
                ResourceLocation location = new ResourceLocation(ProjectDinosaur.MODID, "trilobite_" + genes);
                Minecraft.getInstance().getTextureManager().register(location, new CustomTexture(texture));
                LOCATION_CACHE.put(genes, location);
                return location;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/trilobite/trilobite_invis.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TrilobiteEntity animatable) {
        return new ResourceLocation(ProjectDinosaur.MODID, "animations/trilobite.animation.json");
    }
}
