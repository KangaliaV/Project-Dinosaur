package com.kangalia.projectdinosaur.common.entity.render.layer;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.AustralovenatorEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

import java.util.Locale;

public class AustralovenatorLayer extends GeoLayerRenderer {

    // A resource location for the texture of the layer. This will be applied onto pre-existing cubes on the model
    private static final ResourceLocation DEFAULT_LAYER = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/");
    // A resource location for the model of the entity. This model is put on top of the normal one, which is then given the texture
    private static final ResourceLocation MODEL = new ResourceLocation(ProjectDinosaur.MODID, "geo/australovenator_model.geo.json");

    private final int gene;
    private ResourceLocation LAYER = DEFAULT_LAYER;

    public AustralovenatorLayer(IGeoRenderer<?> entityRendererIn, int index) {
        super(entityRendererIn);
        this.gene = index;
    }

    public String getTypeAndColourPath(AustralovenatorEntity australovenator, int gene) {
        String path = "australovenator";
        if (gene == 0) {
            if (!australovenator.getColourMorph().equals("Normal")) {
                if (australovenator.isBaby()) {
                    path = path + "_baby";
                }
                path = path + "_" + australovenator.getColourMorph().toLowerCase(); //Colour Morph (Baby)
                if (australovenator.isSleeping()) {
                    path = path + "_sleeping";
                }
                return path;
            } else {
                if (australovenator.isBaby()) {
                    path = path + "_baby";
                    if (australovenator.isSleeping()) {
                        path = path + "_sleeping";
                    }
                    return path;
                } else {
                    return path + "_invis";
                }
            }
        }
        if (australovenator.getColourMorph().equals("Melanistic") && !australovenator.isBaby()) {
            if (gene == 4) {
                if (australovenator.getGender() == 0) {
                    path = path + "_highlight_" + australovenator.getGeneDominance(4).toLowerCase() + "_melanistic";  //Highlight Colour *only on males*
                    if (australovenator.isSleeping()) {
                        path = path + "_sleeping";
                    }
                } else {
                    path = path + "_invis";
                }
                return path;
            }
        }
        if (australovenator.getColourMorph().equals("Normal") && !australovenator.isBaby()) {
            if (gene == 1) {
                path = path + "_base_" + australovenator.getGeneDominance(2).toLowerCase(); //Base Colour
                if (australovenator.getGender() == 1) {
                    if (australovenator.isSleeping()) {
                        path = path + "_sleeping";
                    }
                }
                return path;
            } else if (gene == 2) {
                return path + "_underside_" + australovenator.getGeneDominance(3).toLowerCase(); //Underside Colour
            } else if (gene == 3) {
                if (australovenator.getGender() == 0) {
                    return path + "_male_pattern_" + australovenator.getGeneDominance(4).toLowerCase() + "_" + australovenator.getGeneDominance(5).toLowerCase(); //Pattern Type & Colour (Male)
                } else {
                    return path + "_female_pattern_" + australovenator.getGeneDominance(4).toLowerCase() + "_" + australovenator.getGeneDominance(5).toLowerCase(); //Pattern Type & Colour (Female)
                }
            } else {
                if (australovenator.getGender() == 0) {
                    path = path + "_highlight_" + australovenator.getGeneDominance(6).toLowerCase(); //Highlight Colour *only on males*
                    if (australovenator.isSleeping()) {
                        path = path + "_sleeping";
                    }
                } else {
                    path = path + "_invis";
                }
                return path;
            }
        }
        return path + "_invis";
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, Entity entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entityLivingBaseIn instanceof AustralovenatorEntity australovenatorEntity && LAYER == DEFAULT_LAYER) {
            String texture = this.getTypeAndColourPath(australovenatorEntity, gene);
            LAYER = new ResourceLocation(LAYER.toString()+texture+".png");
        }
        RenderType cameo =  RenderType.entityTranslucent(LAYER);
        matrixStackIn.pushPose();
        //Move or scale the model as you see fit
        if (entityLivingBaseIn instanceof AustralovenatorEntity australovenatorEntity) {
            float scale = australovenatorEntity.getAgeScaleData();
            matrixStackIn.scale(scale, scale, scale);
        }
        this.getRenderer().render(this.getEntityModel().getModel(MODEL), entityLivingBaseIn, partialTicks, cameo, matrixStackIn, bufferIn,
                bufferIn.getBuffer(cameo), packedLightIn, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
        matrixStackIn.popPose();
        LAYER = DEFAULT_LAYER;
    }
}
