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
            if (australovenator.isBaby()) {
                path = path + "_baby";
                if (australovenator.isSleeping()) {
                    path = path + "_sleeping";
                }
                path = path + switch (australovenator.getColourMorph()) { //Colour Morph
                    case 1 -> "_albino";
                    case 2 -> "_melanistic";
                    default -> "";
                };
                return path;
            }
            if (australovenator.getColourMorph() != 0) {
                if (australovenator.getGender() == 0) {
                    path = path + "_male";
                } else {
                    path = path + "_female";
                }
                if (australovenator.isSleeping()) {
                    path = path + "_sleeping";
                }
            }
            path = path + switch (australovenator.getColourMorph()) { //Colour Morph
                case 1 -> "_albino";
                case 2 -> "_melanistic";
                default -> "_invis";
            };
            return path;
        }
        if (australovenator.getColourMorph() == 2 && !australovenator.isBaby()) {
            if (gene == 4) {
                if (australovenator.getGender() == 0) {
                    path = path + "_highlight" + switch (australovenator.getGeneDominance(4)) { //Highlight Colour *only on males*
                        case 1 -> "_red";
                        case 2 -> "_green";
                        default -> "_blue";
                    };
                    path = path + "_melanistic";
                    if (australovenator.isSleeping()) {
                        path = path + "_sleeping";
                    }
                } else {
                    path = path + "_invis";
                }
                return path;
            }
        }
        if (australovenator.getColourMorph() == 0 && !australovenator.isBaby()) {
            if (gene == 1) {
                path = path + "_base" + switch (australovenator.getGeneDominance(1)) { //Feather Colour
                    case 1 -> "_grey";
                    case 2 -> "_green";
                    case 3 -> "_cream";
                    default -> "_orange";
                };
                if (australovenator.getGender() == 1) {
                    if (australovenator.isSleeping()) {
                        path = path + "_sleeping";
                    }
                }
                return path;
            } else if (gene == 2) {
                path = path + "_underside" + switch (australovenator.getGeneDominance(2)) { //Underside Colour
                    case 1 -> "_grey";
                    case 2 -> "_brown";
                    case 3 -> "_black";
                    default -> "_cream";
                };
                return path;
            } else if (gene == 3) {
                path = path + "_pattern_type" + switch (australovenator.getGeneDominance(3)) { //Pattern Type
                    case 1 -> "_stripes";
                    case 2 -> "_rings";
                    case 3 -> "_spots";
                    case 4 -> "_clownfish";
                    default -> "_circles";
                };
                return path;
            } else if (gene == 4) {
                path = path + "_pattern_colour" + switch (australovenator.getGeneDominance(4)) { //Pattern Colour
                    case 1 -> "_black";
                    case 2 -> "_yellow";
                    case 3 -> "_white";
                    case 4 -> "_red";
                    case 5 -> "_cyan";
                    default -> "_grey";
                };
                return path;
            } else {
                if (australovenator.getGender() == 0) {
                    path = path + "_highlight" + switch (australovenator.getGeneDominance(5)) { //Highlight Colour *only on males*
                        case 1 -> "_green";
                        case 2 -> "_yellow";
                        case 3 -> "_red";
                        default -> "_blue";
                    };
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
