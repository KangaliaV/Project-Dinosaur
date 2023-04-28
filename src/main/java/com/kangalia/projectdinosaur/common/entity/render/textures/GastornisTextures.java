package com.kangalia.projectdinosaur.common.entity.render.textures;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.GastornisEntity;
import com.kangalia.projectdinosaur.core.util.TextureUtils;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.io.IOException;

public class GastornisTextures extends TextureUtils {

    ResourceLocation BASE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/gastornis/base.png");
    ResourceLocation FEATHERS = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/gastornis/feathers.png");
    ResourceLocation UNDERSIDE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/gastornis/underside.png");
    ResourceLocation PATTERN = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/gastornis/pattern.png");
    ResourceLocation HIGHLIGHT_ADULT = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/gastornis/highlight_adult.png");
    ResourceLocation HIGHLIGHT_BABY = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/gastornis/highlight_baby.png");
    ResourceLocation SKIN = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/gastornis/skin.png");
    ResourceLocation BEAK = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/gastornis/beak.png");

    ResourceLocation EYE_ADULT = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/gastornis/eye_adult.png");
    ResourceLocation EYE_BABY = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/gastornis/eye_baby.png");


    public NativeImage colourGastornis(GastornisEntity gastornis) throws IOException {
        String morph = gastornis.getColourMorph();
        String feathers = gastornis.getGeneDominance(1);
        String underside = gastornis.getGeneDominance(2);
        String pattern = gastornis.getGeneDominance(3);
        String highlight = gastornis.getGeneDominance(4);
        String skin = gastornis.getGeneDominance(5);
        String beak = gastornis.getGeneDominance(6);

        NativeImage base_image = getNativeImageFromResourceLocation(BASE);
        NativeImage feathers_image = getNativeImageFromResourceLocation(FEATHERS);
        NativeImage underside_image = getNativeImageFromResourceLocation(UNDERSIDE);
        NativeImage pattern_image = getNativeImageFromResourceLocation(PATTERN);
        NativeImage highlight_image = getNativeImageFromResourceLocation(HIGHLIGHT_ADULT);
        if (gastornis.isBaby()) {
            highlight_image = getNativeImageFromResourceLocation(HIGHLIGHT_BABY);
        }
        NativeImage skin_image = getNativeImageFromResourceLocation(SKIN);
        NativeImage beak_image = getNativeImageFromResourceLocation(BEAK);

        NativeImage eye_image = getNativeImageFromResourceLocation(EYE_ADULT);

        boolean isBaby = gastornis.isBaby();

        if (isBaby) {
            eye_image = getNativeImageFromResourceLocation(EYE_BABY);
        }

        if (morph.equals("Albino")) {
            feathers_image = colourAlbino(feathers_image, "feathers", isBaby);
            underside_image = colourAlbino(underside_image, "underside", isBaby);
            pattern_image = colourAlbino(pattern_image, "pattern", isBaby);
            if (gastornis.getGender() == 0 && !gastornis.isBaby()) {
                highlight_image = colourHighlight(highlight_image, highlight.toLowerCase()+"_albino");
            } else {
                highlight_image = colourAlbino(highlight_image, "highlight_female", isBaby);
            }
            skin_image = colourAlbino(skin_image, "skin", isBaby);
            beak_image = colourAlbino(beak_image, "beak", isBaby);
            eye_image = colourAlbino(eye_image, "eye", isBaby);
        } else if (morph.equals("Melanistic")) {
            feathers_image = colourMelanistic(feathers_image, "feathers", isBaby);
            underside_image = colourMelanistic(underside_image, "underside", isBaby);
            pattern_image = colourMelanistic(pattern_image, "pattern", isBaby);
            if (gastornis.getGender() == 0 && !gastornis.isBaby()) {
                highlight_image = colourHighlight(highlight_image, highlight.toLowerCase()+"_melanistic");
            } else {
                highlight_image = colourMelanistic(highlight_image, "highlight_female", isBaby);
            }
            skin_image = colourMelanistic(skin_image, "skin", isBaby);
            beak_image = colourMelanistic(beak_image, "beak", isBaby);
        } else {
            feathers_image = colourFeathersOrPattern(feathers_image, feathers.toLowerCase(), isBaby);
            underside_image = colourUnderside(underside_image, underside.toLowerCase(), isBaby);
            pattern_image = colourFeathersOrPattern(pattern_image, pattern.toLowerCase(), isBaby);
            if (gastornis.getGender() == 0 && !gastornis.isBaby()) {
                highlight_image = colourHighlight(highlight_image, highlight.toLowerCase());
            } else {
                highlight_image = colourUnderside(highlight_image, underside.toLowerCase(), isBaby);
            }
            skin_image = colourSkin(skin_image, skin.toLowerCase(), isBaby);
            beak_image = colourBeak(beak_image, beak.toLowerCase(), isBaby);
        }

        combineLayers(base_image, skin_image);
        combineLayers(base_image, underside_image);
        combineLayers(base_image, beak_image);
        combineLayers(base_image, feathers_image);
        combineLayers(base_image, pattern_image);
        combineLayers(base_image, highlight_image);
        if (!gastornis.isSleeping()) {
            combineLayers(base_image, eye_image);
        }

        return base_image;
    }

    public void combineLayers(NativeImage base, NativeImage image) {
        for(int y = 0; y < base.getHeight(); ++y) {
            for(int x = 0; x < base.getWidth(); ++x) {
                int image_colour = image.getPixelRGBA(x, y);
                if (NativeImage.getA(image_colour) > 0) {
                    base.setPixelRGBA(x, y, image_colour);
                }
            }
        }
    }

    public void stainLayer(NativeImage base, Color colour) {
        for(int y = 0; y < base.getHeight(); ++y) {
            for(int x = 0; x < base.getWidth(); ++x) {
                int colourRGB = colour.getRGB();
                int alpha = NativeImage.getA(base.getPixelRGBA(x, y));
                Color base_colour = new Color(base.getPixelRGBA(x, y));
                Color average = new Color(this.multiply(base_colour.getRGB(), colourRGB));
                base.setPixelRGBA(x, y, this.stainViaLuminance(alpha, base_colour, average));
            }
        }
    }

    public int stainViaLuminance(int alpha, Color colour1, Color colour2) {
        int R = colour1.getRed();
        int G = colour1.getGreen();
        int B = colour1.getBlue();

        // Standard Luminance Calculation:
        float L = (float) ((0.2126*R) + (0.7152*G) + (0.0722*B));

        int finalRed = (int) ((colour2.getRed() * L) / 255);
        int finalGreen = (int) ((colour2.getGreen() * L) / 255);
        int finalBlue = (int) ((colour2.getBlue() * L) / 255);

        return NativeImage.combine(alpha, finalRed, finalGreen, finalBlue);
    }

    public int multiply(int color, int baseColour) {
        int a = NativeImage.getA(color);
        int r = NativeImage.getR(color);
        r = (int)((float)r * NativeImage.getR(baseColour)) / 255;
        int g = NativeImage.getG(color);
        g = (int)((float)g * NativeImage.getG(baseColour)) / 255;
        int b = NativeImage.getB(color);
        b = (int)((float)b * NativeImage.getB(baseColour)) / 255;
        return NativeImage.combine(a, r, g, b);
    }

    public NativeImage colourAlbino(NativeImage image, String layer, boolean isBaby) {
        Color colour = new Color(255, 255, 255);
        boolean a = layer.equals("feathers") || layer.equals("underside") || layer.equals("pattern");
        boolean b = layer.equals("skin") || layer.equals("beak") || layer.equals("highlight_female") || layer.equals("eye");
        if (isBaby) {
            if (a) {
                colour = new Color(245, 199, 195);
            } else if (b) {
                colour = new Color(255, 189, 185);
            }
        } else {
            if (a) {
                colour = new Color(245, 219, 215);
            } else if (b) {
                colour = new Color(255, 209, 205);
            }
        }
        stainLayer(image, colour);
        return image;
    }

    public NativeImage colourMelanistic(NativeImage image, String layer, boolean isBaby) {
        Color colour = new Color(0, 0, 0);
        boolean a = layer.equals("feathers") || layer.equals("underside") || layer.equals("pattern");
        boolean b = layer.equals("skin") || layer.equals("beak") || layer.equals("highlight_female");
        if (isBaby) {
            if (a) {
                colour = new Color(67, 68, 74);
            } else if (b) {
                colour = new Color(87, 88, 94);
            }
        } else {
            if (a) {
                colour = new Color(37, 38, 44);

            } else if (b) {
                colour = new Color(57, 58, 64);
            }
        }
        stainLayer(image, colour);
        return image;
    }

    public NativeImage colourFeathersOrPattern(NativeImage image, String c, boolean isBaby) {
        Color colour;
        if (isBaby) {
            colour = switch (c) {
                case "white", "grey", "cream" -> new Color(225, 219, 215);
                case "red" -> new Color(160, 107, 84);
                case "brown" -> new Color(170, 133, 106);
                case "black" -> new Color(111, 100, 90);
                default -> new Color(0, 0, 0);
            };
        } else {
            colour = switch (c) {
                case "white" -> new Color(225, 219, 215);
                case "grey" -> new Color(164, 157, 152);
                case "cream" -> new Color(255, 226, 192);
                case "red" -> new Color(110, 57, 34);
                case "brown" -> new Color(120, 83, 56);
                case "black" -> new Color(61, 50, 40);
                default -> new Color(0, 0, 0);
            };
        }
        stainLayer(image, colour);
        return image;
    }

    public NativeImage colourUnderside(NativeImage image, String c, boolean isBaby) {
        Color colour;
        if (isBaby) {
            colour = new Color(225, 219, 215);
        } else {
            colour = switch (c) {
                case "white" -> new Color(225, 219, 215);
                case "grey" -> new Color(164, 157, 152);
                case "cream" -> new Color(255, 226, 192);
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

            case "red_melanistic" -> new Color(50, 0, 0);
            case "green_melanistic" -> new Color(0, 50, 0);
            case "blue_melanistic" -> new Color(0, 0, 50);

            case "red_albino" -> new Color(255, 190, 190);
            case "green_albino" -> new Color(200, 250, 200);
            case "blue_albino" -> new Color(200, 200, 250);
            default -> new Color(0, 0, 0);
        };

        stainLayer(image, colour);
        return image;
    }

    public NativeImage colourSkin(NativeImage image, String c, boolean isBaby) {
        Color colour;
        if (isBaby) {
            colour = switch (c) {
                case "cream" -> new Color(255, 196, 162);
                case "canvas" -> new Color(218, 155, 114);
                case "brown" -> new Color(130, 73, 46);
                case "black" -> new Color(81, 50, 40);
                default -> new Color(0, 0, 0);
            };
        } else {
            colour = switch (c) {
                case "cream" -> new Color(235, 206, 172);
                case "canvas" -> new Color(188, 145, 104);
                case "brown" -> new Color(100, 63, 36);
                case "black" -> new Color(51, 40, 30);
                default -> new Color(0, 0, 0);
            };
        }
        stainLayer(image, colour);
        return image;
    }

    public NativeImage colourBeak(NativeImage image, String c, boolean isBaby) {
        Color colour;
        if (isBaby) {
            colour = switch (c) {
                case "cream" -> new Color(255, 186, 177);
                case "yellow" -> new Color(255, 200, 100);
                case "orange" -> new Color(255, 120, 100);
                default -> new Color(0, 0, 0);
            };
        } else {
            colour = switch (c) {
                case "cream" -> new Color(255, 216, 177);
                case "yellow" -> new Color(255, 200, 0);
                case "orange" -> new Color(200, 100, 0);
                default -> new Color(0, 0, 0);
            };
        }
        stainLayer(image, colour);
        return image;
    }

}
