package com.kangalia.projectdinosaur.common.entity.render.textures;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.AustralovenatorEntity;
import com.kangalia.projectdinosaur.common.entity.creature.GastornisEntity;
import com.kangalia.projectdinosaur.core.util.TextureUtils;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.io.IOException;

public class AustralovenatorTextures extends TextureUtils {

    ResourceLocation BASE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/base.png");
    ResourceLocation UNDERSIDE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/underside.png");
    ResourceLocation PATTERN_CIRCLES = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_circles.png");
    ResourceLocation PATTERN_STRIPES = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_stripes.png");
    ResourceLocation PATTERN_SPOTS = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_spots.png");
    ResourceLocation PATTERN_CLOWNFISH = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_clownfish.png");
    ResourceLocation PATTERN_RINGS = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_rings.png");
    ResourceLocation HIGHLIGHT_ADULT = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/highlight_adult.png");
    ResourceLocation HIGHLIGHT_BABY = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/highlight_baby.png");

    ResourceLocation EYE_ADULT = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/eye_adult.png");
    ResourceLocation EYE_BABY = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/eye_baby.png");

    ResourceLocation UNDERSIDE_BLEND = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/underside_blend.png");
    ResourceLocation PATTERN_CIRCLES_BLEND = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_circles_blend.png");
    ResourceLocation PATTERN_STRIPES_BLEND = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_stripes_blend.png");
    ResourceLocation PATTERN_SPOTS_BLEND = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_spots_blend.png");
    ResourceLocation PATTERN_CLOWNFISH_BLEND = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_clownfish_blend.png");
    ResourceLocation PATTERN_RINGS_BLEND = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_rings_blend.png");
    ResourceLocation HIGHLIGHT_BLEND = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/highlight_blend.png");

    ResourceLocation PATTERN;
    ResourceLocation PATTERN_BLEND;

    public NativeImage colourAustralovenator(AustralovenatorEntity australovenator) throws IOException {
        String morph = australovenator.getColourMorph();
        String base = australovenator.getGeneDominance(1);
        String underside = australovenator.getGeneDominance(2);
        String pattern = australovenator.getGeneDominance(3);
        String highlight = australovenator.getGeneDominance(4);

        NativeImage base_image = getNativeImageFromResourceLocation(BASE);
        NativeImage underside_image = getNativeImageFromResourceLocation(UNDERSIDE);
        switch (pattern) {
            case "Stripes" -> {
                PATTERN = PATTERN_STRIPES;
                PATTERN_BLEND = PATTERN_STRIPES_BLEND;
            }
            case "Rings" -> {
                PATTERN = PATTERN_RINGS;
                PATTERN_BLEND = PATTERN_RINGS_BLEND;
            }
            case "Spots" -> {
                PATTERN = PATTERN_SPOTS;
                PATTERN_BLEND = PATTERN_SPOTS_BLEND;
            }
            case "Clownfish" -> {
                PATTERN = PATTERN_CLOWNFISH;
                PATTERN_BLEND = PATTERN_CLOWNFISH_BLEND;
            }
            default -> {
                PATTERN = PATTERN_CIRCLES;
                PATTERN_BLEND = PATTERN_CIRCLES_BLEND;
            }
        }
        NativeImage pattern_image = getNativeImageFromResourceLocation(PATTERN);
        NativeImage highlight_image = getNativeImageFromResourceLocation(HIGHLIGHT_ADULT);
        if (australovenator.isBaby()) {
            highlight_image = getNativeImageFromResourceLocation(HIGHLIGHT_BABY);
        }
        NativeImage eye_image = getNativeImageFromResourceLocation(EYE_ADULT);
        boolean isBaby = australovenator.isBaby();
        if (isBaby) {
            eye_image = getNativeImageFromResourceLocation(EYE_BABY);
        }
        NativeImage underside_blend_image = getNativeImageFromResourceLocation(UNDERSIDE_BLEND);
        NativeImage pattern_blend_image = getNativeImageFromResourceLocation(PATTERN_BLEND);
        NativeImage highlight_blend_image = getNativeImageFromResourceLocation(HIGHLIGHT_BLEND);

        if (morph.equals("Albino")) {
            base_image = colourAlbino(base_image, "base", isBaby);
            underside_image = colourAlbino(underside_image, "underside", isBaby);
            underside_blend_image = colourAlbino(underside_blend_image, "underside", isBaby);
            pattern_image = colourAlbino(pattern_image, "pattern", isBaby);
            pattern_blend_image = colourAlbino(pattern_blend_image, "pattern", isBaby);
            if (australovenator.getGender() == 0 && !australovenator.isBaby()) {
                highlight_image = colourHighlight(highlight_image, highlight.toLowerCase()+"_albino");
                highlight_blend_image = colourHighlight(highlight_blend_image, highlight.toLowerCase()+"_albino");
            } else {
                highlight_image = colourAlbino(highlight_image, "highlight_female", isBaby);
                highlight_blend_image = colourAlbino(highlight_blend_image, "highlight_female", isBaby);
            }
            eye_image = colourAlbino(eye_image, "eye", isBaby);
        } else if (morph.equals("Melanistic")) {
            base_image = colourMelanistic(base_image, "base", isBaby);
            underside_image = colourMelanistic(underside_image, "underside", isBaby);
            underside_blend_image = colourMelanistic(underside_blend_image, "underside", isBaby);
            pattern_image = colourMelanistic(pattern_image, "pattern", isBaby);
            pattern_blend_image = colourMelanistic(pattern_blend_image, "pattern", isBaby);
            if (australovenator.getGender() == 0 && !australovenator.isBaby()) {
                highlight_image = colourHighlight(highlight_image, highlight.toLowerCase()+"_melanistic");
                highlight_blend_image = colourHighlight(highlight_blend_image, highlight.toLowerCase()+"_melanistic");

            } else {
                highlight_image = colourMelanistic(highlight_image, "highlight_female", isBaby);
                highlight_blend_image = colourMelanistic(highlight_blend_image, "highlight_female", isBaby);
            }
        } else {
            base_image = colourBase(base_image, base.toLowerCase(), isBaby);
            underside_image = colourUnderside(underside_image, underside.toLowerCase(), isBaby);
            underside_blend_image = colourUnderside(underside_blend_image, underside.toLowerCase(), isBaby);
            pattern_image = colourPattern(pattern_image, pattern.toLowerCase(), isBaby);
            pattern_blend_image = colourPattern(pattern_blend_image, pattern.toLowerCase(), isBaby);
            if (australovenator.getGender() == 0 && !australovenator.isBaby()) {
                highlight_image = colourHighlight(highlight_image, highlight.toLowerCase());
                highlight_blend_image = colourHighlight(highlight_blend_image, highlight.toLowerCase());
            } else {
                highlight_image = colourUnderside(highlight_image, underside.toLowerCase(), isBaby);
                highlight_blend_image = colourUnderside(highlight_blend_image, underside.toLowerCase(), isBaby);
            }
        }

        combineLayers(base_image, underside_image, false);
        combineLayers(base_image, pattern_image, false);
        combineLayers(base_image, highlight_image, false);
        if (!australovenator.isSleeping()) {
            combineLayers(base_image, eye_image, false);
        }

        combineLayers(base_image, underside_blend_image, true);
        combineLayers(base_image, pattern_blend_image, true);
        combineLayers(base_image, highlight_blend_image, true);

        return base_image;
    }

    public NativeImage colourAlbino(NativeImage image, String layer, boolean isBaby) {
        Color colour = new Color(255, 255, 255);
        boolean a = layer.equals("base") || layer.equals("underside");
        boolean b = layer.equals("pattern") || layer.equals("highlight_female") || layer.equals("eye");
        if (isBaby) {
            if (a) {
                colour = new Color(245, 199, 175);
            } else if (b) {
                colour = new Color(255, 189, 185);
            }
        } else {
            if (a) {
                colour = new Color(245, 219, 195);
            } else if (b) {
                colour = new Color(255, 209, 205);
            }
        }
        stainLayer(image, colour);
        return image;
    }

    public NativeImage colourMelanistic(NativeImage image, String layer, boolean isBaby) {
        Color colour = new Color(0, 0, 0);
        boolean a = layer.equals("feathers") || layer.equals("underside");
        boolean b = layer.equals("pattern") || layer.equals("highlight_female");
        if (isBaby) {
            if (a) {
                colour = new Color(57, 58, 64);
            } else if (b) {
                colour = new Color(77, 78, 84);
            }
        } else {
            if (a) {
                colour = new Color(27, 28, 34);

            } else if (b) {
                colour = new Color(47, 48, 54);
            }
        }
        stainLayer(image, colour);
        return image;
    }

    public NativeImage colourBase(NativeImage image, String c, boolean isBaby) {
        Color colour;
        if (isBaby) {
            colour = switch (c) {
                case "cream", "grey" -> new Color(205, 199, 195);
                case "orange" -> new Color(170, 127, 84);
                case "green" -> new Color(140, 153, 106);
                default -> new Color(0, 0, 0);
            };
        } else {
            colour = switch (c) {
                case "cream" -> new Color(255, 226, 192);
                case "grey" -> new Color(164, 157, 152);
                case "orange" -> new Color(130, 97, 44);
                case "green" -> new Color(100, 113, 66);
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
                case "brown" -> new Color(100, 63, 46);
                case "grey" -> new Color(144, 137, 132);
                case "cream" -> new Color(255, 226, 192);
                case "black" -> new Color(41, 40, 45);
                default -> new Color(0, 0, 0);
            };
        }
        stainLayer(image, colour);
        return image;
    }

    public NativeImage colourPattern(NativeImage image, String c, boolean isBaby) {
        Color colour;
        if (isBaby) {
            colour = new Color(255, 226, 192);
        } else {
            colour = switch (c) {
                case "white" -> new Color(225, 219, 215);
                case "grey" -> new Color(144, 137, 132);
                case "black" -> new Color(41, 40, 45);
                case "red" -> new Color(120, 30, 40);
                case "cyan" -> new Color(2, 97, 138);
                case "yellow" -> new Color(151, 132, 8);
                default -> new Color(0, 0, 0);
            };
        }
        stainLayer(image, colour);
        return image;
    }

    public NativeImage colourHighlight(NativeImage image, String c) {
        Color colour = switch (c) {
            case "red" -> new Color(120, 20, 30);
            case "green" -> new Color(20, 120, 35);
            case "blue" -> new Color(20, 40, 120);
            case "yellow" -> new Color(181, 162, 38);

            case "red_melanistic" -> new Color(50, 0, 0);
            case "green_melanistic" -> new Color(0, 50, 0);
            case "blue_melanistic" -> new Color(0, 0, 50);
            case "yellow_melanistic" -> new Color(101, 82, 0);

            case "red_albino" -> new Color(255, 190, 190);
            case "green_albino" -> new Color(200, 250, 200);
            case "blue_albino" -> new Color(200, 200, 250);
            case "yellow_albino" -> new Color(221, 202, 98);
            default -> new Color(0, 0, 0);
        };

        stainLayer(image, colour);
        return image;
    }

}
