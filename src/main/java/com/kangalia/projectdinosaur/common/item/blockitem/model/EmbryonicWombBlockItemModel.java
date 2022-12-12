package com.kangalia.projectdinosaur.common.item.blockitem.model;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.item.blockitem.EmbryonicWombBlockItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EmbryonicWombBlockItemModel extends AnimatedGeoModel<EmbryonicWombBlockItem> {

    @Override
    public ResourceLocation getModelResource(EmbryonicWombBlockItem object) {
        return new ResourceLocation(ProjectDinosaur.MODID, "geo/embryonic_womb.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EmbryonicWombBlockItem object) {
        return new ResourceLocation(ProjectDinosaur.MODID, "textures/blocks/embryonic_womb.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EmbryonicWombBlockItem animatable) {
        return new ResourceLocation(ProjectDinosaur.MODID, "animations/embryonic_womb.animation.json");
    }
}
