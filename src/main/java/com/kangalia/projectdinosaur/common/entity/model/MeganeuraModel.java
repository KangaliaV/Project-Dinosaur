package com.kangalia.projectdinosaur.common.entity.model;

import com.google.common.collect.Maps;
import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.MeganeuraEntity;
import com.kangalia.projectdinosaur.common.entity.render.textures.CustomTexture;
import com.kangalia.projectdinosaur.common.entity.render.textures.MeganeuraTextures;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.model.GeoModel;

import java.io.IOException;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class MeganeuraModel extends GeoModel<MeganeuraEntity> {

    private static final Map<String, ResourceLocation> LOCATION_CACHE = Maps.newHashMap();
    MeganeuraTextures textures = new MeganeuraTextures();

    @Override
    public ResourceLocation getModelResource(MeganeuraEntity object) {
        return new ResourceLocation(ProjectDinosaur.MODID, "geo/meganeura_model.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MeganeuraEntity object) {
        String genes = object.getGenes().toLowerCase()+"_"+object.isAdult()+"_"+object.isSleeping();
        if (LOCATION_CACHE.containsKey(genes)) {
            return LOCATION_CACHE.get(genes);
        } else {
            try {
                NativeImage texture = textures.colourMeganeura(object);
                ResourceLocation location = new ResourceLocation(ProjectDinosaur.MODID, "meganeura_" + genes);
                Minecraft.getInstance().getTextureManager().register(location, new CustomTexture(texture));
                LOCATION_CACHE.put(genes, location);
                return location;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/meganeura/meganeura-invisible.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MeganeuraEntity animatable) {
        return new ResourceLocation(ProjectDinosaur.MODID, "animations/meganeura.animation.json");
    }
}
