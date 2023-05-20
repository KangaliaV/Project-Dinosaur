package com.kangalia.projectdinosaur.common.block.model;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.blockentity.EmbryonicWombBlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import software.bernie.geckolib.model.GeoModel;

public class EmbryonicWombModel extends GeoModel<EmbryonicWombBlockEntity> {

    @Override
    public ResourceLocation getModelResource(EmbryonicWombBlockEntity object) {
        return new ResourceLocation(ProjectDinosaur.MODID, "geo/embryonic_womb.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EmbryonicWombBlockEntity object) {
        if (object.getBlockState().getValue(BlockStateProperties.POWERED)) {
            return new ResourceLocation(ProjectDinosaur.MODID, "textures/block/embryonic_womb_powered.png");
        } else {
            return new ResourceLocation(ProjectDinosaur.MODID, "textures/block/embryonic_womb.png");
        }
    }

    @Override
    public ResourceLocation getAnimationResource(EmbryonicWombBlockEntity animatable) {
        return new ResourceLocation(ProjectDinosaur.MODID, "animations/embryonic_womb.animation.json");
    }
}
