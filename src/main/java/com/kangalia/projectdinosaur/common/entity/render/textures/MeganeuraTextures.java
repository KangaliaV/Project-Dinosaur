package com.kangalia.projectdinosaur.common.entity.render.textures;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.MeganeuraEntity;
import com.kangalia.projectdinosaur.core.util.TextureUtils;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.io.IOException;

public class MeganeuraTextures extends TextureUtils {

    ResourceLocation BASE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/meganeura/base.png");
    ResourceLocation BODY = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/meganeura/base.png");
    ResourceLocation TAIL = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/meganeura/tail.png");
    ResourceLocation PATTERN = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/meganeura/pattern.png");
    ResourceLocation WING = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/meganeura/wing.png");

    ResourceLocation EYE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/meganeura/eye.png");

    ResourceLocation TAIL_BLEND = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/meganeura/tail_blend.png");
    ResourceLocation PATTERN_BLEND = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/meganeura/pattern_blend.png");
    ResourceLocation WING_BLEND = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/meganeura/wing_blend.png");
    
    public NativeImage colourMeganeura(MeganeuraEntity meganeura) throws IOException {
        String morph = meganeura.getColourMorph();
        String base = meganeura.getGeneDominance(1);
        String tail = meganeura.getGeneDominance(2);
        String pattern = meganeura.getGeneDominance(3);
        String wing = meganeura.getGeneDominance(4);

        NativeImage original = getNativeImageFromResourceLocation(BASE);
        NativeImage base_image = getNativeImageFromResourceLocation(BODY);
        NativeImage tail_image = getNativeImageFromResourceLocation(TAIL);
        NativeImage pattern_image = getNativeImageFromResourceLocation(PATTERN);
        NativeImage wing_image = getNativeImageFromResourceLocation(WING);
        NativeImage eye_image = getNativeImageFromResourceLocation(EYE);
        
        NativeImage tail_blend_image = getNativeImageFromResourceLocation(TAIL_BLEND);
        NativeImage pattern_blend_image = getNativeImageFromResourceLocation(PATTERN_BLEND);
        NativeImage wing_blend_image = getNativeImageFromResourceLocation(WING_BLEND);

        boolean isBaby = meganeura.isBaby();

        if (morph.equals("Albino")) {
            base_image = colourAlbino(base_image, "base", isBaby);
            tail_image = colourAlbino(tail_image, "tail", isBaby);
            tail_blend_image = colourTailBlend(tail_blend_image, original, "albino", isBaby);
            pattern_image = colourAlbino(pattern_image, "pattern", isBaby);
            pattern_blend_image = colourPatternBlend(pattern_blend_image, original,"albino", isBaby);
            wing_image = colourAlbino(pattern_image, "wing", isBaby);
            wing_blend_image = colourWingBlend(pattern_blend_image, original,"albino");
            eye_image = colourAlbino(eye_image, "eye", isBaby);

        } else if (morph.equals("Melanistic")) {
            base_image = colourMelanistic(base_image, "base", isBaby);
            tail_image = colourMelanistic(tail_image, "tail", isBaby);
            tail_blend_image = colourTailBlend(tail_blend_image, original, "melanistic", isBaby);
            pattern_image = colourMelanistic(pattern_image, "pattern", isBaby);
            pattern_blend_image = colourPatternBlend(pattern_blend_image, original, "melanistic", isBaby);
            wing_image = colourMelanistic(pattern_image, "wing", isBaby);
            wing_blend_image = colourWingBlend(pattern_blend_image, original, "melanistic");
            eye_image = colourMelanistic(eye_image, "eye", isBaby);

        } else {
            base_image = colourBase(base_image, base.toLowerCase(), isBaby);
            tail_image = colourTail(tail_image, tail.toLowerCase(), isBaby);
            tail_blend_image = colourTailBlend(tail_blend_image, original, tail.toLowerCase(), isBaby);
            pattern_image = colourPattern(pattern_image, pattern.toLowerCase(), isBaby);
            pattern_blend_image = colourPatternBlend(pattern_blend_image, original, pattern.toLowerCase(), isBaby);
            wing_image = colourWing(pattern_image, wing.toLowerCase());
            wing_blend_image = colourWingBlend(pattern_blend_image, original, wing.toLowerCase());
        }

        combineLayers(base_image, tail_image);
        combineLayers(base_image, wing_image);
        combineLayers(base_image, pattern_image);

        combineLayersBlend(base_image, tail_blend_image, 0.7);
        combineLayersBlend(base_image, wing_blend_image, 0.7);
        combineLayersBlend(base_image, pattern_blend_image, 0.7);

        if (!meganeura.isSleeping()) {
            combineLayers(base_image, eye_image);
        }

        return base_image;
    }

    public NativeImage colourAlbino(NativeImage image, String layer, boolean isBaby) {
        Color colour = new Color(255, 255, 255);
        boolean a = layer.equals("base") || layer.equals("tail");
        boolean b = layer.equals("pattern") || layer.equals("wing") || layer.equals("eye");
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
        boolean a = layer.equals("base") || layer.equals("tail");
        boolean b = layer.equals("pattern") || layer.equals("wing") || layer.equals("eye");
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

    public NativeImage colourBase(NativeImage image, String c, boolean isBaby) {
        Color colour;
        if (isBaby) {
            colour = switch (c) {
                case "cream" -> new Color(205, 199, 195);
                case "blue" -> new Color(82, 147, 158);
                case "green" -> new Color(140, 153, 106);
                case "black" -> new Color(165, 159, 145);
                default -> new Color(0, 0, 0);
            };
        } else {
            colour = switch (c) {
                case "cream" -> new Color(245, 216, 182);
                case "blue" -> new Color(42, 127, 138);
                case "green" -> new Color(100, 113, 66);
                case "black" -> new Color(65, 59, 45);
                default -> new Color(0, 0, 0);
            };
        }
        stainLayer(image, colour);
        return image;
    }

    public NativeImage colourPattern(NativeImage image, String c, boolean isBaby) {
        Color colour = switch (c) {
            case "albino" -> new Color(255, 229, 175);
            case "melanistic" -> new Color(47, 48, 64);
            case "green" -> new Color(30, 150, 40);
            case "red" -> new Color(140, 30, 40);
            case "blue" -> new Color(2, 97, 158);
            case "yellow" -> new Color(171, 152, 8);
            default -> new Color(0, 0, 0);
        };
        stainLayer(image, colour);
        return image;
    }

    public NativeImage colourPatternBlend(NativeImage image, NativeImage luminance, String c, boolean isBaby) {
        Color colour = switch (c) {
            case "albino" -> new Color(255, 229, 175);
            case "melanistic" -> new Color(47, 48, 64);
            case "green" -> new Color(30, 150, 40);
            case "red" -> new Color(140, 30, 40);
            case "blue" -> new Color(2, 97, 158);
            case "yellow" -> new Color(171, 152, 8);
            default -> new Color(0, 0, 0);
        };
        stainLayerDiffBase(image, luminance, colour);
        return image;
    }

    public NativeImage colourTail(NativeImage image, String c, boolean isBaby) {
        Color colour;
        if (isBaby) {
            colour = switch (c) {
                case "cream" -> new Color(205, 199, 195);
                case "brown" -> new Color(150, 127, 104);
                case "green" -> new Color(140, 153, 106);
                default -> new Color(0, 0, 0);
            };
        } else {
            colour = switch (c) {
                case "cream" -> new Color(245, 216, 182);
                case "brown" -> new Color(100, 77, 54);
                case "green" -> new Color(100, 113, 66);
                default -> new Color(0, 0, 0);
            };
        }
        stainLayer(image, colour);
        return image;
    }

    public NativeImage colourTailBlend(NativeImage image, NativeImage luminance, String c, boolean isBaby) {
        Color colour;
        if (isBaby) {
            colour = switch (c) {
                case "cream" -> new Color(205, 199, 195);
                case "brown" -> new Color(150, 127, 104);
                case "green" -> new Color(140, 153, 106);
                default -> new Color(0, 0, 0);
            };
        } else {
            colour = switch (c) {
                case "cream" -> new Color(245, 216, 182);
                case "brown" -> new Color(100, 77, 54);
                case "green" -> new Color(100, 113, 66);
                default -> new Color(0, 0, 0);
            };
        }
        stainLayerDiffBase(image, luminance, colour);
        return image;

    }

    public NativeImage colourWing(NativeImage image, String c) {
        Color colour;
        colour = switch (c) {
            case "albino" -> new Color(235, 199, 195);
            case "melanistic" -> new Color(105, 99, 95);
            case "white" -> new Color(205, 199, 195);
            case "blue" -> new Color(110, 127, 154);
            case "yellow" -> new Color(191, 172, 98);
            case "red" -> new Color(220, 100, 50);
            default -> new Color(0, 0, 0);
        };
        stainLayer(image, colour);
        return image;
    }

    public NativeImage colourWingBlend(NativeImage image, NativeImage luminance, String c) {
        Color colour;
        colour = switch (c) {
            case "albino" -> new Color(235, 199, 195);
            case "melanistic" -> new Color(105, 99, 95);
            case "white" -> new Color(205, 199, 195);
            case "blue" -> new Color(110, 127, 154);
            case "yellow" -> new Color(191, 172, 98);
            case "red" -> new Color(220, 100, 50);
            default -> new Color(0, 0, 0);
        };
        stainLayerDiffBase(image, luminance, colour);
        return image;

    }

}
