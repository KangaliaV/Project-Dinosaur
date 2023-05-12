package com.kangalia.projectdinosaur.common.entity.render.textures;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.TrilobiteEntity;
import com.kangalia.projectdinosaur.core.util.TextureUtils;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.io.IOException;

public class TrilobiteTextures extends TextureUtils {

    ResourceLocation BASE_ADULT_LARGE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/trilobite/base_adult_large.png");
    ResourceLocation BASE_ADULT_SMALL = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/trilobite/base_adult_small.png");
    ResourceLocation BASE_BABY = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/trilobite/base_baby.png");

    ResourceLocation UNDERSIDE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/trilobite/underside.png");
    ResourceLocation UNDERSIDE_BLEND1 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/trilobite/underside_blend1.png");
    ResourceLocation UNDERSIDE_BLEND2 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/trilobite/underside_blend2.png");

    // STRIPES
    ResourceLocation PATTERN_STRIPES_BLEND1 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/trilobite/pattern_stripes_blend1.png");
    ResourceLocation PATTERN_STRIPES_BLEND2 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/trilobite/pattern_stripes_blend2.png");

    // SPINE
    ResourceLocation PATTERN_SPINE_BLEND1 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/trilobite/pattern_spine_blend1.png");
    ResourceLocation PATTERN_SPINE_BLEND2 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/trilobite/pattern_spine_blend2.png");

    ResourceLocation PATTERN_BLEND1;
    ResourceLocation PATTERN_BLEND2;

    public NativeImage colourTrilobite(TrilobiteEntity trilobite) throws IOException {
        String morph = trilobite.getColourMorph();
        String base = trilobite.getGeneDominance(1);
        String underside = trilobite.getGeneDominance(2);
        String pattern = trilobite.getGeneDominance(3);
        String pattern_colour = trilobite.getGeneDominance(4);
        String horn = trilobite.getGeneDominance(5);

        boolean isBaby = trilobite.isBaby();

        NativeImage base_image = getNativeImageFromResourceLocation(BASE_ADULT_LARGE);
        if (isBaby) {
            base_image = getNativeImageFromResourceLocation(BASE_BABY);
        } else if (horn.equals("Small")) {
            base_image = getNativeImageFromResourceLocation(BASE_ADULT_SMALL);
        }

        NativeImage underside_image = getNativeImageFromResourceLocation(UNDERSIDE);

        if (pattern.equals("Stripes")) {
            PATTERN_BLEND1 = PATTERN_STRIPES_BLEND1;
            PATTERN_BLEND2 = PATTERN_STRIPES_BLEND2;
        } else {
            PATTERN_BLEND1 = PATTERN_SPINE_BLEND1;
            PATTERN_BLEND2 = PATTERN_SPINE_BLEND2;
        }

        NativeImage underside_blend_image1 = getNativeImageFromResourceLocation(UNDERSIDE_BLEND1);
        NativeImage underside_blend_image2 = getNativeImageFromResourceLocation(UNDERSIDE_BLEND2);
        NativeImage pattern_blend_image1 = getNativeImageFromResourceLocation(PATTERN_BLEND1);
        NativeImage pattern_blend_image2 = getNativeImageFromResourceLocation(PATTERN_BLEND2);

        if (morph.equals("Albino")) {
            base_image = colourAlbino(base_image, "base", isBaby);
            underside_image = colourAlbino(underside_image, "underside", isBaby);
            underside_blend_image1 = colourUndersideBlend(underside_blend_image1, base_image, "albino", isBaby);
            underside_blend_image2 = colourUndersideBlend(underside_blend_image2, base_image, "albino", isBaby);
            if (!pattern.equals("None") || !trilobite.isBaby()) {
                pattern_blend_image1 = colourPatternBlend(pattern_blend_image1, base_image, "albino");
                pattern_blend_image2 = colourPatternBlend(pattern_blend_image2, base_image, "albino");
            }
        } else if (morph.equals("Melanistic")) {
            base_image = colourMelanistic(base_image, "base", isBaby);
            underside_image = colourMelanistic(underside_image, "underside", isBaby);
            underside_blend_image1 = colourUndersideBlend(underside_blend_image1, base_image, "melanistic", isBaby);
            underside_blend_image2 = colourUndersideBlend(underside_blend_image2, base_image, "melanistic", isBaby);
            if (!pattern.equals("None") || !trilobite.isBaby()) {
                pattern_blend_image1 = colourPatternBlend(pattern_blend_image1, base_image, "melanistic");
                pattern_blend_image2 = colourPatternBlend(pattern_blend_image2, base_image, "melanistic");
            }
        } else {
            base_image = colourBase(base_image, base.toLowerCase(), isBaby);
            underside_image = colourUnderside(underside_image, underside.toLowerCase(), isBaby);
            underside_blend_image1 = colourUndersideBlend(underside_blend_image1, base_image, underside.toLowerCase(), isBaby);
            underside_blend_image2 = colourUndersideBlend(underside_blend_image2, base_image, underside.toLowerCase(), isBaby);
            if (!pattern.equals("None") || !trilobite.isBaby()) {
                pattern_blend_image1 = colourPatternBlend(pattern_blend_image1, base_image, pattern_colour.toLowerCase());
                pattern_blend_image2 = colourPatternBlend(pattern_blend_image2, base_image, pattern_colour.toLowerCase());
            }
        }

        combineLayers(base_image, underside_image);

        combineLayersBlend(base_image, underside_blend_image1, 0.5);
        combineLayersBlend(base_image, underside_blend_image2, 0.75);
        if (pattern.equals("Stripes") && !trilobite.isBaby() || pattern.equals("Spine") && !trilobite.isBaby()) {
            combineLayersBlend(base_image, pattern_blend_image1, 0.5);
            combineLayersBlend(base_image, pattern_blend_image2, 0.75);
        }

        return base_image;
    }

    public NativeImage colourAlbino(NativeImage image, String layer, boolean isBaby) {
        Color colour = new Color(255, 255, 255);
        boolean a = layer.equals("base") || layer.equals("underside");
        boolean b = layer.equals("pattern");
        if (isBaby) {
            if (a) {
                colour = new Color(245, 219, 195);
            }
        } else {
            if (a) {
                colour = new Color(245, 239, 225);
            } else if (b) {
                colour = new Color(255, 229, 175);
            }
        }
        stainLayer(image, colour);
        return image;
    }

    public NativeImage colourMelanistic(NativeImage image, String layer, boolean isBaby) {
        Color colour = new Color(0, 0, 0);
        boolean a = layer.equals("base") || layer.equals("underside");
        boolean b = layer.equals("pattern");
        if (isBaby) {
            if (a) {
                colour = new Color(57, 58, 74);
            }
        } else {
            if (a) {
                colour = new Color(27, 28, 44);
            } else if (b) {
                colour = new Color(47, 48, 64);
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
                case "brown" -> new Color(150, 127, 104);
                case "green" -> new Color(140, 153, 106);
                case "black" -> new Color(165, 159, 145);
                default -> new Color(0, 0, 0);
            };
        } else {
            colour = switch (c) {
                case "cream" -> new Color(245, 216, 182);
                case "brown" -> new Color(100, 77, 54);
                case "green" -> new Color(100, 113, 66);
                case "black" -> new Color(65, 59, 45);
                default -> new Color(0, 0, 0);
            };
        }
        stainLayer(image, colour);
        return image;
    }

    public NativeImage colourUnderside(NativeImage image, String c, boolean isBaby) {
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

    public NativeImage colourUndersideBlend(NativeImage image, NativeImage luminance, String c, boolean isBaby) {
        Color colour;
        if (isBaby) {
            colour = switch (c) {
                case "albino" -> new Color(245, 219, 195);
                case "melanistic" -> new Color(57, 58, 74);
                case "cream" -> new Color(205, 199, 195);
                case "brown" -> new Color(150, 127, 104);
                case "green" -> new Color(140, 153, 106);
                default -> new Color(0, 0, 0);
            };
        } else {
            colour = switch (c) {
                case "albino" -> new Color(245, 239, 225);
                case "melanistic" -> new Color(27, 28, 44);
                case "cream" -> new Color(245, 216, 182);
                case "brown" -> new Color(100, 77, 54);
                case "green" -> new Color(100, 113, 66);
                default -> new Color(0, 0, 0);
            };
        }
        stainLayerDiffBase(image, luminance, colour);
        return image;
    }

    public NativeImage colourPatternBlend(NativeImage image, NativeImage luminance, String c) {
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

}
