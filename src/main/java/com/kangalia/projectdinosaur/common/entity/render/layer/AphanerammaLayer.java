package com.kangalia.projectdinosaur.common.entity.render.layer;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.AphanerammaEntity;
import com.kangalia.projectdinosaur.common.entity.creature.AustralovenatorEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class AphanerammaLayer extends GeoLayerRenderer {

    // A resource location for the texture of the layer. This will be applied onto pre-existing cubes on the model
    private static final ResourceLocation DEFAULT_LAYER = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/aphaneramma/");
    // A resource location for the model of the entity. This model is put on top of the normal one, which is then given the texture
    private static final ResourceLocation MODEL = new ResourceLocation(ProjectDinosaur.MODID, "geo/aphaneramma_model.geo.json");

    private final int gene;
    private ResourceLocation LAYER = DEFAULT_LAYER;

    public AphanerammaLayer(IGeoRenderer<?> entityRendererIn, int index) {
        super(entityRendererIn);
        this.gene = index;
    }

    public String getTypeAndColourPath(AphanerammaEntity aphaneramma, int gene) {
        String path = "aphaneramma";
        if (gene == 0) {
            if (!aphaneramma.getColourMorph().equals("Normal")) {
                path = path + "_" + aphaneramma.getColourMorph().toLowerCase(); // Non-Normal Morph
                if (aphaneramma.isBaby()) {
                    path = path + "_baby";
                }
                if (!aphaneramma.isBaby()) {
                    if (aphaneramma.getGender() == 0) {
                        path = path + "_male";
                    } else {
                        path = path + "_female";
                    }
                }
                if (aphaneramma.isSleeping()) {
                    path = path + "_sleeping";
                }
                return path;
            } else {
                if (aphaneramma.isBaby()) {
                    path = path + "_baby";
                    if (aphaneramma.isSleeping()) {
                        path = path + "_sleeping";
                    }
                    return path;
                } else {
                    return path + "_invis";
                }
            }
        }
        if (aphaneramma.getColourMorph().equals("Normal") && !aphaneramma.isBaby()) {
            if (gene == 1) {
                path = path + "_base_" + aphaneramma.getGeneDominance(1).toLowerCase(); //Base Colour
                if (aphaneramma.getGender() == 1) {
                    if (aphaneramma.isSleeping()) {
                        path = path + "_sleeping";
                    }
                }
                return path;
            } else if (gene == 2) {
                return path + "_underside_" + aphaneramma.getGeneDominance(2).toLowerCase(); //Underside Colour
            } else {
                if (aphaneramma.getGender() == 0) {
                    path = path + "_pattern_" + aphaneramma.getGeneDominance(3).toLowerCase(); //Pattern Colour
                    if (aphaneramma.isSleeping()) {
                        path = path + "_sleeping";
                    }
                } else {
                    path = path + "_pattern_female_" + aphaneramma.getGeneDominance(3).toLowerCase(); //Pattern Colour
                }
                return path;
            }
        }
        return path + "_invis";
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, Entity entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entityLivingBaseIn instanceof AphanerammaEntity aphanerammaEntity && LAYER == DEFAULT_LAYER) {
            String texture = this.getTypeAndColourPath(aphanerammaEntity, gene);
            LAYER = new ResourceLocation(LAYER.toString()+texture+".png");
        }
        RenderType cameo =  RenderType.entityTranslucent(LAYER);
        matrixStackIn.pushPose();
        //Move or scale the model as you see fit
        if (entityLivingBaseIn instanceof AphanerammaEntity aphanerammaEntity) {
            float scale = aphanerammaEntity.getAgeScaleData();
            matrixStackIn.scale(scale, scale, scale);
        }
        this.getRenderer().render(this.getEntityModel().getModel(MODEL), entityLivingBaseIn, partialTicks, cameo, matrixStackIn, bufferIn,
                bufferIn.getBuffer(cameo), packedLightIn, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
        matrixStackIn.popPose();
        LAYER = DEFAULT_LAYER;
    }
}
