package com.kangalia.projectdinosaur.common.entity.render.layer;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.GastornisEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class GastornisLayer extends GeoLayerRenderer {

    // A resource location for the texture of the layer. This will be applied onto pre-existing cubes on the model
    private static final ResourceLocation DEFAULT_LAYER = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/gastornis/");
    // A resource location for the model of the entity. This model is put on top of the normal one, which is then given the texture
    private static final ResourceLocation MODEL = new ResourceLocation(ProjectDinosaur.MODID, "geo/gastornis_model.geo.json");

    private final int gene;
    private ResourceLocation LAYER = DEFAULT_LAYER;

    public GastornisLayer(IGeoRenderer<?> entityRendererIn, int index) {
        super(entityRendererIn);
        this.gene = index;
    }

    public String getTypeAndColourPath(GastornisEntity gastornis, int gene) {
        String path = "gastornis";
        if (gene == 0) {
            if (!gastornis.getColourMorph().equals("Normal")) {
                if (gastornis.isBaby()) {
                    path = path + "_baby";
                } else {
                    if (gastornis.getGender() == 0) {
                        path = path + "_male";
                    } else {
                        path = path + "_female";
                    }
                }
                path = path + "_" + gastornis.getColourMorph().toLowerCase(); //Colour Morph
                if (gastornis.isSleeping()) {
                    path = path + "_sleeping";
                }
                return path;
            } else {
                if (gastornis.isBaby()) {
                    path = path + "_baby";
                    if (gastornis.isSleeping()) {
                        path = path + "_sleeping";
                    }
                    return path;
                } else {
                    return path + "_invis";
                }
            }
        }
        if (gastornis.getColourMorph().equals("Melanistic") && !gastornis.isBaby()) {
            if (gene == 4) {
                if (gastornis.getGender() == 0) {
                    path = path + "_highlight_" + gastornis.getGeneDominance(4).toLowerCase() + "_melanistic";  //Highlight Colour *only on males*
                    if (gastornis.isSleeping()) {
                        path = path + "_sleeping";
                    }
                } else {
                    path = path + "_invis";
                }
                return path;
            }
        }
        if (gastornis.getColourMorph().equals("Normal") && !gastornis.isBaby()) {
            if (gene == 1) {
                return path + "_feather_" + gastornis.getGeneDominance(1).toLowerCase(); //Feather Colour
            } else if (gene == 2) {
                path = path + "_underside_" + gastornis.getGeneDominance(2).toLowerCase(); //Underside Colour
                if (gastornis.getGender() == 1) {
                    if (gastornis.isSleeping()) {
                        path = path + "_sleeping";
                    }
                }
                return path;
            } else if (gene == 3) {
                return path + "_pattern_" + gastornis.getGeneDominance(3).toLowerCase();  //Pattern Colour
            } else if (gene == 4) {
                if (gastornis.getGender() == 0) {
                    path = path + "_highlight_" + gastornis.getGeneDominance(4).toLowerCase(); //Highlight Colour *only on males*
                    if (gastornis.isSleeping()) {
                        path = path + "_sleeping";
                    }
                } else {
                    path = path + "_invis";
                }
                return path;
            } else if (gene == 5) {
                return path + "_skin_" + gastornis.getGeneDominance(5).toLowerCase();
            } else {
                return path + "_beak_" + gastornis.getGeneDominance(6).toLowerCase();  //Beak Colour
            }
        }
        return path + "_invis";
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, Entity entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entityLivingBaseIn instanceof GastornisEntity gastornisEntity && LAYER == DEFAULT_LAYER) {
            String texture = this.getTypeAndColourPath(gastornisEntity, gene);
            LAYER = new ResourceLocation(LAYER.toString()+texture+".png");
        }
        RenderType cameo =  RenderType.entityTranslucent(LAYER);
        matrixStackIn.pushPose();
        //Move or scale the model as you see fit
        if (entityLivingBaseIn instanceof GastornisEntity gastornisEntity) {
            float scale = gastornisEntity.getAgeScaleData();
            matrixStackIn.scale(scale, scale, scale);
        }
        this.getRenderer().render(this.getEntityModel().getModel(MODEL), entityLivingBaseIn, partialTicks, cameo, matrixStackIn, bufferIn,
                bufferIn.getBuffer(cameo), packedLightIn, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
        matrixStackIn.popPose();
        LAYER = DEFAULT_LAYER;
    }
}
