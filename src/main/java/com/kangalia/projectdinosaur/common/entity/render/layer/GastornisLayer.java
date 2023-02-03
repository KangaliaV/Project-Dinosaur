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
            if (gastornis.isBaby()) {
                path = path + "_baby";
                if (gastornis.isSleeping()) {
                    path = path + "_sleeping";
                }
                path = path + switch (gastornis.getColourMorph()) { //Colour Morph
                    case 1 -> "_albino";
                    case 2 -> "_melanistic";
                    default -> "";
                };
                return path;
            }
            if (gastornis.getColourMorph() != 0) {
                if (gastornis.getGender() == 0) {
                    path = path + "_male";
                } else {
                    path = path + "_female";
                }
                if (gastornis.isSleeping()) {
                    path = path + "_sleeping";
                }
            }
            path = path + switch (gastornis.getColourMorph()) { //Colour Morph
                case 1 -> "_albino";
                case 2 -> "_melanistic";
                default -> "_invis";
            };
            return path;
        }
        if (gastornis.getColourMorph() == 2 && !gastornis.isBaby()) {
            if (gene == 4) {
                if (gastornis.getGender() == 0) {
                    path = path + "_highlight" + switch (gastornis.getGeneDominance(4)) { //Highlight Colour *only on males*
                        case 1 -> "_red";
                        case 2 -> "_green";
                        default -> "_blue";
                    };
                    path = path + "_melanistic";
                    if (gastornis.isSleeping()) {
                        path = path + "_sleeping";
                    }
                } else {
                    path = path + "_invis";
                }
                return path;
            }
        }
        if (gastornis.getColourMorph() == 0 && !gastornis.isBaby()) {
            if (gene == 1) {
                path = path + "_feather" + switch (gastornis.getGeneDominance(1)) { //Feather Colour
                    case 1 -> "_black";
                    case 2 -> "_cream";
                    case 3 -> "_red";
                    case 4 -> "_grey";
                    case 5 -> "_white";
                    default -> "_brown";
                };
                return path;
            } else if (gene == 2) {
                path = path + "_underside" + switch (gastornis.getGeneDominance(2)) { //Underside Colour
                    case 1 -> "_grey";
                    case 2 -> "_white";
                    default -> "_cream";
                };
                if (gastornis.getGender() == 1) {
                    if (gastornis.isSleeping()) {
                        path = path + "_sleeping";
                    }
                }
                return path;
            } else if (gene == 3) {
                path = path + "_pattern" + switch (gastornis.getGeneDominance(3)) { //Pattern Colour
                    case 1 -> "_brown";
                    case 2 -> "_cream";
                    case 3 -> "_red";
                    case 4 -> "_grey";
                    case 5 -> "_white";
                    default -> "_black";
                };
                return path;
            } else if (gene == 4) {
                if (gastornis.getGender() == 0) {
                    path = path + "_highlight" + switch (gastornis.getGeneDominance(4)) { //Highlight Colour *only on males*
                        case 1 -> "_red";
                        case 2 -> "_green";
                        default -> "_blue";
                    };
                    if (gastornis.isSleeping()) {
                        path = path + "_sleeping";
                    }
                } else {
                    path = path + "_invis";
                }
                return path;
            } else if (gene == 5) {
                path = path + "_skin" + switch (gastornis.getGeneDominance(5)) { //Skin Colour
                    case 1 -> "_brown";
                    case 2 -> "_canvas";
                    case 3 -> "_black";
                    default -> "_cream";
                };
                return path;
            } else {
                path = path + "_beak" + switch (gastornis.getGeneDominance(6)) { //Beak Colour
                    case 1 -> "_orange";
                    case 2 -> "_cream";
                    default -> "_yellow";
                };
                return path;
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
