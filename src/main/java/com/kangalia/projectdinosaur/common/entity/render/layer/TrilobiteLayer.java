package com.kangalia.projectdinosaur.common.entity.render.layer;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.AphanerammaEntity;
import com.kangalia.projectdinosaur.common.entity.creature.TrilobiteEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class TrilobiteLayer extends GeoLayerRenderer {

    // A resource location for the texture of the layer. This will be applied onto pre-existing cubes on the model
    private static final ResourceLocation DEFAULT_LAYER = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/trilobite/");
    // A resource location for the model of the entity. This model is put on top of the normal one, which is then given the texture
    private static final ResourceLocation MODEL = new ResourceLocation(ProjectDinosaur.MODID, "geo/trilobite_model.geo.json");

    private final int gene;
    private ResourceLocation LAYER = DEFAULT_LAYER;

    public TrilobiteLayer(IGeoRenderer<?> entityRendererIn, int index) {
        super(entityRendererIn);
        this.gene = index;
    }

    public String getTypeAndColourPath(TrilobiteEntity trilobite, int gene) {
        String path = "trilobite";
        if (gene == 0) {
            if (!trilobite.getColourMorph().equals("Normal")) {
                path = path + "_" + trilobite.getColourMorph().toLowerCase(); // Non-Normal Morph
                if (trilobite.isBaby()) {
                    path = path + "_baby";
                } else {
                    path = path + "_" + trilobite.getGeneDominance(5).toLowerCase(); // Horn Type
                }
                return path;
            } else {
                if (trilobite.isBaby()) {
                    return path + "_baby";
                } else {
                    return path + "_invis";
                }
            }
        }
        if (trilobite.getColourMorph().equals("Normal") && !trilobite.isBaby()) {
            if (gene == 1) {
                return path + "_base_" + trilobite.getGeneDominance(1).toLowerCase() + "_" + trilobite.getGeneDominance(5).toLowerCase(); //Base Colour & Horn Type
            } else if (gene == 2) {
                return path + "_underside_" + trilobite.getGeneDominance(2).toLowerCase(); //Underside Colour
            } else {
                if (!trilobite.getGeneDominance(3).equals("None")) {
                    return path + "_pattern_" + trilobite.getGeneDominance(3).toLowerCase() + "_" + trilobite.getGeneDominance(4).toLowerCase(); //Pattern Type & Colour
                }
            }
        }
        return path + "_invis";
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, Entity entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entityLivingBaseIn instanceof TrilobiteEntity trilobiteEntity && LAYER == DEFAULT_LAYER) {
            String texture = this.getTypeAndColourPath(trilobiteEntity, gene);
            LAYER = new ResourceLocation(LAYER.toString()+texture+".png");
        }
        RenderType cameo =  RenderType.entityTranslucent(LAYER);
        matrixStackIn.pushPose();
        //Move or scale the model as you see fit
        if (entityLivingBaseIn instanceof TrilobiteEntity trilobiteEntity) {
            float scale = trilobiteEntity.getAgeScaleData();
            matrixStackIn.scale(scale, scale, scale);
        }
        this.getRenderer().render(this.getEntityModel().getModel(MODEL), entityLivingBaseIn, partialTicks, cameo, matrixStackIn, bufferIn,
                bufferIn.getBuffer(cameo), packedLightIn, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
        matrixStackIn.popPose();
        LAYER = DEFAULT_LAYER;
    }
}
