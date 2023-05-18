package com.kangalia.projectdinosaur.common.entity.render.textures;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.AphanerammaEntity;
import com.kangalia.projectdinosaur.core.util.TextureUtils;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.io.IOException;

public class AphanerammaTextures extends TextureUtils {

    ResourceLocation BASE_ADULT = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/aphaneramma/base_adult.png");
    ResourceLocation BASE_BABY = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/aphaneramma/base_baby.png");

    ResourceLocation UNDERSIDE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/aphaneramma/underside.png");
    ResourceLocation UNDERSIDE_BLEND1 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/aphaneramma/underside_blend1.png");
    ResourceLocation UNDERSIDE_BLEND2 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/aphaneramma/underside_blend2.png");
    
    ResourceLocation PATTERN_MALE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/aphaneramma/pattern_male.png");
    ResourceLocation PATTERN_BLEND1_MALE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/aphaneramma/pattern_male_blend1.png");
    ResourceLocation PATTERN_BLEND2_MALE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/aphaneramma/pattern_male_blend2.png");

    ResourceLocation PATTERN_FEMALE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/aphaneramma/pattern_female.png");
    ResourceLocation PATTERN_BLEND1_FEMALE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/aphaneramma/pattern_female_blend1.png");
    ResourceLocation PATTERN_BLEND2_FEMALE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/aphaneramma/pattern_female_blend2.png");

    ResourceLocation EYE_ADULT = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/aphaneramma/eyes_adult.png");
    ResourceLocation EYE_BABY = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/aphaneramma/eyes_baby.png");

    ResourceLocation PATTERN;
    ResourceLocation PATTERN_BLEND1;
    ResourceLocation PATTERN_BLEND2;

    public NativeImage colourAphaneramma(AphanerammaEntity aphaneramma) throws IOException {
        String morph = aphaneramma.getColourMorph();
        String base = aphaneramma.getGeneDominance(1);
        String underside = aphaneramma.getGeneDominance(2);
        String pattern = aphaneramma.getGeneDominance(3);

        boolean isBaby = aphaneramma.isBaby();
        NativeImage original = getNativeImageFromResourceLocation(BASE_ADULT);
        NativeImage base_image = getNativeImageFromResourceLocation(BASE_ADULT);
        if (isBaby) {
            original = getNativeImageFromResourceLocation(BASE_BABY);
            base_image = getNativeImageFromResourceLocation(BASE_BABY);
        }
        NativeImage underside_image = getNativeImageFromResourceLocation(UNDERSIDE);

        if (aphaneramma.getGender() == 0) {
            PATTERN = PATTERN_MALE;
            PATTERN_BLEND1 = PATTERN_BLEND1_MALE;
            PATTERN_BLEND2 = PATTERN_BLEND2_MALE;
        } else {
            PATTERN = PATTERN_FEMALE;
            PATTERN_BLEND1 = PATTERN_BLEND1_FEMALE;
            PATTERN_BLEND2 = PATTERN_BLEND2_FEMALE;
        }
        NativeImage pattern_image = getNativeImageFromResourceLocation(PATTERN);

        NativeImage eye_image = getNativeImageFromResourceLocation(EYE_ADULT);
        if (isBaby) {
            eye_image = getNativeImageFromResourceLocation(EYE_BABY);
        }

        NativeImage underside_blend_image1 = getNativeImageFromResourceLocation(UNDERSIDE_BLEND1);
        NativeImage underside_blend_image2 = getNativeImageFromResourceLocation(UNDERSIDE_BLEND2);
        NativeImage pattern_blend_image1 = getNativeImageFromResourceLocation(PATTERN_BLEND1);
        NativeImage pattern_blend_image2 = getNativeImageFromResourceLocation(PATTERN_BLEND2);

        if (morph.equals("Albino")) {
            base_image = colourAlbino(base_image, "base", isBaby);
            underside_image = colourAlbino(underside_image, "underside", isBaby);
            underside_blend_image1 = colourUndersideBlend(underside_blend_image1, original, "albino", isBaby);
            underside_blend_image2 = colourUndersideBlend(underside_blend_image2, original, "albino", isBaby);
            if (!isBaby) {
                pattern_image = colourAlbino(pattern_image, "pattern", false);
                pattern_blend_image1 = colourPatternBlend(pattern_blend_image1, original, "albino");
                pattern_blend_image2 = colourPatternBlend(pattern_blend_image2, original, "albino");
            }
            eye_image = colourAlbino(eye_image, "eye", isBaby);
        } else if (morph.equals("Melanistic")) {
            base_image = colourMelanistic(base_image, "base", isBaby);
            underside_image = colourMelanistic(underside_image, "underside", isBaby);
            underside_blend_image1 = colourUndersideBlend(underside_blend_image1, original, "melanistic", isBaby);
            underside_blend_image2 = colourUndersideBlend(underside_blend_image2, original, "melanistic", isBaby);
            if (!isBaby) {
                pattern_image = colourMelanistic(pattern_image, "pattern", false);
                pattern_blend_image1 = colourPatternBlend(pattern_blend_image1, original, "melanistic");
                pattern_blend_image2 = colourPatternBlend(pattern_blend_image2, original, "melanistic");
            }
        } else {
            base_image = colourBase(base_image, base.toLowerCase(), isBaby);
            underside_image = colourUnderside(underside_image, underside.toLowerCase(), isBaby);
            underside_blend_image1 = colourUndersideBlend(underside_blend_image1, original, underside.toLowerCase(), isBaby);
            underside_blend_image2 = colourUndersideBlend(underside_blend_image2, original, underside.toLowerCase(), isBaby);
            if (!isBaby) {
                pattern_image = colourPattern(pattern_image, pattern.toLowerCase());
                pattern_blend_image1 = colourPatternBlend(pattern_blend_image1, original, pattern.toLowerCase());
                pattern_blend_image2 = colourPatternBlend(pattern_blend_image2, original, pattern.toLowerCase());
            }
        }

        combineLayers(base_image, underside_image);
        if (!isBaby) {
            combineLayers(base_image, pattern_image);
        }
        combineLayersBlend(base_image, underside_blend_image1, 0.5);
        combineLayersBlend(base_image, underside_blend_image2, 0.75);
        if (!isBaby) {
            combineLayersBlend(base_image, pattern_blend_image1, 0.5);
            combineLayersBlend(base_image, pattern_blend_image2, 0.75);
        }

        if (!aphaneramma.isSleeping()) {
            combineLayers(base_image, eye_image);
        }

        return base_image;
    }

    public NativeImage colourAlbino(NativeImage image, String layer, boolean isBaby) {
        Color colour = new Color(255, 255, 255);
        boolean a = layer.equals("base") || layer.equals("underside");
        boolean b = layer.equals("pattern") || layer.equals("highlight_female") || layer.equals("eye");
        if (isBaby) {
            if (a) {
                colour = new Color(255, 189, 165);
            } else if (b) {
                colour = new Color(255, 169, 155);
            }
        } else {
            if (a) {
                colour = new Color(255, 209, 185);
            } else if (b) {
                colour = new Color(255, 189, 175);
            }
        }
        stainLayer(image, colour);
        return image;
    }

    public NativeImage colourMelanistic(NativeImage image, String layer, boolean isBaby) {
        Color colour = new Color(0, 0, 0);
        boolean a = layer.equals("base") || layer.equals("underside");
        boolean b = layer.equals("pattern") || layer.equals("highlight_female");
        if (isBaby) {
            if (a) {
                colour = new Color(67, 58, 64);
            } else if (b) {
                colour = new Color(87, 78, 84);
            }
        } else {
            if (a) {
                colour = new Color(37, 28, 34);
            } else if (b) {
                colour = new Color(57, 48, 54);
            }
        }
        stainLayer(image, colour);
        return image;
    }

    public NativeImage colourBase(NativeImage image, String c, boolean isBaby) {
        Color colour;
        if (isBaby) {
            colour = switch (c) {
                case "black", "grey" -> new Color(205, 199, 195);
                case "green", "lime" -> new Color(140, 153, 106);
                default -> new Color(0, 0, 0);
            };
        } else {
            colour = switch (c) {
                case "black" -> new Color(55, 56, 40);
                case "grey" -> new Color(149, 157, 142);
                case "lime" -> new Color(110, 117, 54);
                case "green" -> new Color(60, 73, 26);
                default -> new Color(0, 0, 0);
            };
        }
        stainLayer(image, colour);
        return image;
    }

    public NativeImage colourUnderside(NativeImage image, String c, boolean isBaby) {
        Color colour;
        if (isBaby) {
            colour = new Color(255, 226, 192);
        } else {
            colour = switch (c) {
                case "cream" -> new Color(255, 226, 192);
                case "lime" -> new Color(110, 117, 54);
                case "green" -> new Color(60, 73, 26);
                default -> new Color(0, 0, 0);
            };
        }
        stainLayer(image, colour);
        return image;
    }

    public NativeImage colourUndersideBlend(NativeImage image, NativeImage luminance, String c, boolean isBaby) {
        Color colour;
        if (isBaby) {
            colour = switch (c) {
                case "albino" -> new Color(255, 189, 165);
                case "melanistic" -> new Color(67, 58, 64);
                default -> new Color(255, 226, 192);
            };
        } else {
            colour = switch (c) {
                case "albino" -> new Color(255, 209, 185);
                case "melanistic" -> new Color(37, 28, 34);
                case "cream" -> new Color(255, 226, 192);
                case "lime" -> new Color(110, 117, 54);
                case "green" -> new Color(60, 73, 26);
                default -> new Color(0, 0, 0);
            };
        }
        stainLayerDiffBase(image, luminance, colour);
        return image;
    }

    public NativeImage colourPattern(NativeImage image, String c) {
        Color colour = switch (c) {
            case "green" -> new Color(30, 150, 40);
            case "red" -> new Color(140, 30, 40);
            case "blue" -> new Color(2, 97, 158);
            case "yellow" -> new Color(171, 152, 8);
            default -> new Color(0, 0, 0);
        };
        stainLayer(image, colour);
        return image;
    }

    public NativeImage colourPatternBlend(NativeImage image, NativeImage luminance, String c) {
        Color colour = switch (c) {
            case "albino" -> new Color(255, 189, 175);
            case "melanistic" -> new Color(57, 48, 54);
            case "green" -> new Color(30, 150, 40);
            case "red" -> new Color(140, 30, 40);
            case "blue" -> new Color(2, 97, 158);
            case "yellow" -> new Color(171, 152, 8);
            default -> new Color(0, 0, 0);
        };
        stainLayerDiffBase(image, luminance, colour);
        return image;
    }

}
