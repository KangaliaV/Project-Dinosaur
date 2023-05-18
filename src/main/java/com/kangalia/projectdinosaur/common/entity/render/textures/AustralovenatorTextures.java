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
    ResourceLocation UNDERSIDE_BLEND1 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/underside_blend1.png");
    ResourceLocation UNDERSIDE_BLEND2 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/underside_blend2.png");

    // CIRCLES
    ResourceLocation PATTERN_CIRCLES_MALE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_circles_male.png");
    ResourceLocation PATTERN_CIRCLES_BLEND1_MALE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_circles_male_blend1.png");
    ResourceLocation PATTERN_CIRCLES_BLEND2_MALE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_circles_male_blend2.png");

    ResourceLocation PATTERN_CIRCLES_FEMALE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_circles_female.png");
    ResourceLocation PATTERN_CIRCLES_BLEND1_FEMALE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_circles_female_blend1.png");
    ResourceLocation PATTERN_CIRCLES_BLEND2_FEMALE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_circles_female_blend2.png");

    // STRIPES
    ResourceLocation PATTERN_STRIPES_MALE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_stripes_male.png");
    ResourceLocation PATTERN_STRIPES_BLEND1_MALE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_stripes_male_blend1.png");
    ResourceLocation PATTERN_STRIPES_BLEND2_MALE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_stripes_male_blend2.png");

    ResourceLocation PATTERN_STRIPES_FEMALE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_stripes_female.png");
    ResourceLocation PATTERN_STRIPES_BLEND1_FEMALE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_stripes_female_blend1.png");
    ResourceLocation PATTERN_STRIPES_BLEND2_FEMALE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_stripes_female_blend2.png");

    // RINGS
    ResourceLocation PATTERN_RINGS_MALE_ALBINO = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_rings_male_albino.png");
    ResourceLocation PATTERN_RINGS_MALE_MELANISTIC = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_rings_male_melanistic.png");
    ResourceLocation PATTERN_RINGS_MALE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_rings_male.png");

    ResourceLocation PATTERN_RINGS_BLEND1_MALE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_rings_male_blend1.png");
    ResourceLocation PATTERN_RINGS_BLEND2_MALE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_rings_male_blend2.png");

    ResourceLocation PATTERN_RINGS_FEMALE_ALBINO = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_rings_female_albino.png");
    ResourceLocation PATTERN_RINGS_FEMALE_MELANISTIC = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_rings_female_melanistic.png");
    ResourceLocation PATTERN_RINGS_FEMALE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_rings_female.png");

    ResourceLocation PATTERN_RINGS_BLEND1_FEMALE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_rings_female_blend1.png");
    ResourceLocation PATTERN_RINGS_BLEND2_FEMALE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_rings_female_blend2.png");

    // SPOTS
    ResourceLocation PATTERN_SPOTS_ALBINO = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_spots_albino.png");
    ResourceLocation PATTERN_SPOTS_MELANISTIC = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_spots_melanistic.png");
    ResourceLocation PATTERN_SPOTS = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_spots.png");

    ResourceLocation PATTERN_SPOTS_BLEND1 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_spots_blend1.png");
    ResourceLocation PATTERN_SPOTS_BLEND2 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_spots_blend2.png");

    // CLOWNFISH
    ResourceLocation PATTERN_CLOWNFISH_ALBINO = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_clownfish_albino.png");
    ResourceLocation PATTERN_CLOWNFISH_MELANISTIC = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_clownfish_melanistic.png");
    ResourceLocation PATTERN_CLOWNFISH = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_clownfish.png");

    ResourceLocation PATTERN_CLOWNFISH_BLEND1 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_clownfish_blend1.png");
    ResourceLocation PATTERN_CLOWNFISH_BLEND2 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_clownfish_blend2.png");
    ResourceLocation PATTERN_CLOWNFISH_BLEND3 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_clownfish_blend3.png");
    ResourceLocation PATTERN_CLOWNFISH_BLEND4 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/pattern_clownfish_blend4.png");


    ResourceLocation HIGHLIGHT_ADULT = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/highlight_adult.png");
    ResourceLocation HIGHLIGHT_BABY = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/highlight_baby.png");
    ResourceLocation HIGHLIGHT_BLEND1 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/highlight_blend1.png");
    ResourceLocation HIGHLIGHT_BLEND2 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/highlight_blend2.png");

    ResourceLocation EYE_ADULT = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/eye_adult.png");
    ResourceLocation EYE_BABY = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/australovenator/eye_baby.png");

    ResourceLocation PATTERN;
    ResourceLocation PATTERN_BLEND1;
    ResourceLocation PATTERN_BLEND2;
    ResourceLocation PATTERN_BLEND3;
    ResourceLocation PATTERN_BLEND4;

    public NativeImage colourAustralovenator(AustralovenatorEntity australovenator) throws IOException {
        String morph = australovenator.getColourMorph();
        String base = australovenator.getGeneDominance(2);
        String underside = australovenator.getGeneDominance(3);
        String pattern = australovenator.getGeneDominance(4);
        String pattern_colour = australovenator.getGeneDominance(5);
        String highlight = australovenator.getGeneDominance(6);

        NativeImage original = getNativeImageFromResourceLocation(BASE);
        NativeImage base_image = getNativeImageFromResourceLocation(BASE);
        NativeImage underside_image = getNativeImageFromResourceLocation(UNDERSIDE);
        switch (pattern) {
            case "Stripes" -> {
                if (australovenator.getGender() == 0) {
                    PATTERN = PATTERN_STRIPES_MALE;
                    PATTERN_BLEND1 = PATTERN_STRIPES_BLEND1_MALE;
                    PATTERN_BLEND2 = PATTERN_STRIPES_BLEND2_MALE;
                } else {
                    PATTERN = PATTERN_STRIPES_FEMALE;
                    PATTERN_BLEND1 = PATTERN_STRIPES_BLEND1_FEMALE;
                    PATTERN_BLEND2 = PATTERN_STRIPES_BLEND2_FEMALE;
                }
            }
            case "Rings" -> {
                if (morph.equals("Albino")) {
                    if (australovenator.getGender() == 0) {
                        PATTERN = PATTERN_RINGS_MALE_ALBINO;
                    } else {
                        PATTERN = PATTERN_RINGS_FEMALE_ALBINO;
                    }
                } else if (morph.equals("Melanistic")) {
                    if (australovenator.getGender() == 0) {
                        PATTERN = PATTERN_RINGS_MALE_MELANISTIC;
                    } else {
                        PATTERN = PATTERN_RINGS_FEMALE_MELANISTIC;
                    }
                } else {
                    if (australovenator.getGender() == 0) {
                        PATTERN = PATTERN_RINGS_MALE;
                    } else {
                        PATTERN = PATTERN_RINGS_FEMALE;
                    }
                }
                if (australovenator.getGender() == 0) {
                    PATTERN_BLEND1 = PATTERN_RINGS_BLEND1_MALE;
                    PATTERN_BLEND2 = PATTERN_RINGS_BLEND2_MALE;
                } else {
                    PATTERN_BLEND1 = PATTERN_RINGS_BLEND1_FEMALE;
                    PATTERN_BLEND2 = PATTERN_RINGS_BLEND2_FEMALE;
                }
            }
            case "Spots" -> {
                if (morph.equals("Albino")) {
                    PATTERN = PATTERN_SPOTS_ALBINO;
                } else if (morph.equals("Melanistic")) {
                    PATTERN = PATTERN_SPOTS_MELANISTIC;
                } else {
                    PATTERN = PATTERN_SPOTS;
                }
                PATTERN_BLEND1 = PATTERN_SPOTS_BLEND1;
                PATTERN_BLEND2 = PATTERN_SPOTS_BLEND2;
            }
            case "Clownfish" -> {
                if (morph.equals("Albino")) {
                    PATTERN = PATTERN_CLOWNFISH_ALBINO;
                } else if (morph.equals("Melanistic")) {
                    PATTERN = PATTERN_CLOWNFISH_MELANISTIC;
                } else {
                    PATTERN = PATTERN_CLOWNFISH;
                }
                PATTERN_BLEND1 = PATTERN_CLOWNFISH_BLEND1;
                PATTERN_BLEND2 = PATTERN_CLOWNFISH_BLEND2;
                PATTERN_BLEND3 = PATTERN_CLOWNFISH_BLEND3;
                PATTERN_BLEND4 = PATTERN_CLOWNFISH_BLEND4;
            }
            default -> {
                if (australovenator.getGender() == 0) {
                    PATTERN = PATTERN_CIRCLES_MALE;
                    PATTERN_BLEND1 = PATTERN_CIRCLES_BLEND1_MALE;
                    PATTERN_BLEND2 = PATTERN_CIRCLES_BLEND2_MALE;
                } else {
                    PATTERN = PATTERN_CIRCLES_FEMALE;
                    PATTERN_BLEND1 = PATTERN_CIRCLES_BLEND1_FEMALE;
                    PATTERN_BLEND2 = PATTERN_CIRCLES_BLEND2_FEMALE;
                }
            }
        }
        boolean c = pattern.equals("Clownfish");

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
        NativeImage underside_blend_image1 = getNativeImageFromResourceLocation(UNDERSIDE_BLEND1);
        NativeImage underside_blend_image2 = getNativeImageFromResourceLocation(UNDERSIDE_BLEND2);
        NativeImage pattern_blend_image1 = getNativeImageFromResourceLocation(PATTERN_BLEND1);
        NativeImage pattern_blend_image2 = getNativeImageFromResourceLocation(PATTERN_BLEND2);
        NativeImage pattern_blend_image3 = null;
        NativeImage pattern_blend_image4 = null;
        if (c) {
            pattern_blend_image3 = getNativeImageFromResourceLocation(PATTERN_BLEND3);
            pattern_blend_image4 = getNativeImageFromResourceLocation(PATTERN_BLEND4);
        }
        NativeImage highlight_blend_image1 = getNativeImageFromResourceLocation(HIGHLIGHT_BLEND1);
        NativeImage highlight_blend_image2 = getNativeImageFromResourceLocation(HIGHLIGHT_BLEND2);

        boolean b = !(pattern.equals("Rings") || pattern.equals("Clownfish") || pattern.equals("Spots"));

        if (morph.equals("Albino")) {
            base_image = colourAlbino(base_image, "base", isBaby);
            underside_image = colourAlbino(underside_image, "underside", isBaby);
            underside_blend_image1 = colourUndersideBlend(underside_blend_image1, original, "albino", isBaby);
            underside_blend_image2 = colourUndersideBlend(underside_blend_image2, original, "albino", isBaby);
            if (b) {
                pattern_image = colourAlbino(pattern_image, "pattern", isBaby);
            }
            if (c && pattern_blend_image3 != null || pattern_blend_image4 != null) {
                pattern_blend_image3 = colourPatternBlend(pattern_blend_image3, original, "albino", isBaby);
                pattern_blend_image4 = colourPatternBlend(pattern_blend_image4, original, "albino", isBaby);
            }
            pattern_blend_image1 = colourPatternBlend(pattern_blend_image1, original, "albino", isBaby);
            pattern_blend_image2 = colourPatternBlend(pattern_blend_image2, original, "albino", isBaby);
            if (australovenator.getGender() == 0 && !australovenator.isBaby()) {
                highlight_image = colourHighlight(highlight_image, highlight.toLowerCase()+"_albino");
                highlight_blend_image1 = colourHighlightBlend(highlight_blend_image1, original, highlight.toLowerCase()+"_albino");
                highlight_blend_image2 = colourHighlightBlend(highlight_blend_image2, original, highlight.toLowerCase()+"_albino");
            } else {
                highlight_image = colourAlbino(highlight_image, "highlight_female", isBaby);
                highlight_blend_image1 = colourUndersideBlend(highlight_blend_image1, original, "albino", isBaby);
                highlight_blend_image2 = colourUndersideBlend(highlight_blend_image2, original, "albino", isBaby);
            }
            eye_image = colourAlbino(eye_image, "eye", isBaby);
        } else if (morph.equals("Melanistic")) {
            base_image = colourMelanistic(base_image, "base", isBaby);
            underside_image = colourMelanistic(underside_image, "underside", isBaby);
            underside_blend_image1 = colourUndersideBlend(underside_blend_image1, original, "melanistic", isBaby);
            underside_blend_image2 = colourUndersideBlend(underside_blend_image2, original, "melanistic", isBaby);
            if (b) {
                pattern_image = colourMelanistic(pattern_image, "pattern", isBaby);
            }
            if (c && pattern_blend_image3 != null || pattern_blend_image4 != null) {
                pattern_blend_image3 = colourPatternBlend(pattern_blend_image3, original, "melanistic", isBaby);
                pattern_blend_image4 = colourPatternBlend(pattern_blend_image4, original, "melanistic", isBaby);
            }
            pattern_blend_image1 = colourPatternBlend(pattern_blend_image1, original, "melanistic", isBaby);
            pattern_blend_image2 = colourPatternBlend(pattern_blend_image2, original, "melanistic", isBaby);
            if (australovenator.getGender() == 0 && !australovenator.isBaby()) {
                highlight_image = colourHighlight(highlight_image, highlight.toLowerCase()+"_melanistic");
                highlight_blend_image1 = colourHighlightBlend(highlight_blend_image1, original, highlight.toLowerCase()+"_melanistic");
                highlight_blend_image2 = colourHighlightBlend(highlight_blend_image2, original, highlight.toLowerCase()+"_melanistic");
            } else {
                highlight_image = colourMelanistic(highlight_image, "highlight_female", isBaby);
                highlight_blend_image1 = colourUndersideBlend(highlight_blend_image1, original, "melanistic", isBaby);
                highlight_blend_image2 = colourUndersideBlend(highlight_blend_image2, original, "melanistic", isBaby);
            }
        } else {
            base_image = colourBase(base_image, base.toLowerCase(), isBaby);
            underside_image = colourUnderside(underside_image, underside.toLowerCase(), isBaby);
            underside_blend_image1 = colourUndersideBlend(underside_blend_image1, original, underside.toLowerCase(), isBaby);
            underside_blend_image2 = colourUndersideBlend(underside_blend_image2, original, underside.toLowerCase(), isBaby);
            if (b) {
                pattern_image = colourPattern(pattern_image, pattern_colour.toLowerCase(), isBaby);
            }
            if (c && pattern_blend_image3 != null || pattern_blend_image4 != null) {
                pattern_blend_image1 = colourPatternBlend(pattern_blend_image1, original, "black", isBaby);
                pattern_blend_image2 = colourPatternBlend(pattern_blend_image2, original, "black", isBaby);
                pattern_blend_image3 = colourPatternBlend(pattern_blend_image3, original, pattern_colour.toLowerCase(), isBaby);
                pattern_blend_image4 = colourPatternBlend(pattern_blend_image4, original, pattern_colour.toLowerCase(), isBaby);
            } else {
                pattern_blend_image1 = colourPatternBlend(pattern_blend_image1, original, pattern_colour.toLowerCase(), isBaby);
                pattern_blend_image2 = colourPatternBlend(pattern_blend_image2, original, pattern_colour.toLowerCase(), isBaby);
            }
            if (australovenator.getGender() == 0 && !australovenator.isBaby()) {
                highlight_image = colourHighlight(highlight_image, highlight.toLowerCase());
                highlight_blend_image1 = colourHighlightBlend(highlight_blend_image1, original, highlight.toLowerCase());
                highlight_blend_image2 = colourHighlightBlend(highlight_blend_image2, original, highlight.toLowerCase());
            } else {
                highlight_image = colourUnderside(highlight_image, underside.toLowerCase(), isBaby);
                highlight_blend_image1 = colourUndersideBlend(highlight_blend_image1, original, underside.toLowerCase(), isBaby);
                highlight_blend_image2 = colourUndersideBlend(highlight_blend_image2, original, underside.toLowerCase(), isBaby);
            }
        }

        combineLayers(base_image, underside_image);
        combineLayers(base_image, pattern_image);
        combineLayers(base_image, highlight_image);

        combineLayersBlend(base_image, underside_blend_image1, 0.5);
        combineLayersBlend(base_image, underside_blend_image2, 0.75);
        if (c) {
            combineLayersBlend(base_image, pattern_blend_image3, 0.5);
            combineLayersBlend(base_image, pattern_blend_image4, 0.75);
        }
        combineLayersBlend(base_image, pattern_blend_image1, 0.5);
        combineLayersBlend(base_image, pattern_blend_image2, 0.75);
        combineLayersBlend(base_image, highlight_blend_image1, 0.5);
        combineLayersBlend(base_image, highlight_blend_image2, 0.75);

        if (!australovenator.isSleeping()) {
            combineLayers(base_image, eye_image);
        }

        return base_image;
    }

    public NativeImage colourAlbino(NativeImage image, String layer, boolean isBaby) {
        Color colour = new Color(255, 255, 255);
        boolean a = layer.equals("base") || layer.equals("underside");
        boolean b = layer.equals("pattern") || layer.equals("highlight_female");
        boolean c = layer.equals("eye");
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
        if (c) {
            colour = new Color(255, 239, 225);
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

    public NativeImage colourUndersideBlend(NativeImage image, NativeImage luminance, String c, boolean isBaby) {
        Color colour;
        if (isBaby) {
            colour = switch (c) {
                case "albino" -> new Color(245, 199, 175);
                case "melanistic" -> new Color(57, 58, 64);
                default -> new Color(255, 226, 192);
            };
        } else {
            colour = switch (c) {
                case "albino" -> new Color(245, 219, 195);
                case "melanistic" -> new Color(27, 28, 34);
                case "brown" -> new Color(100, 63, 46);
                case "grey" -> new Color(144, 137, 132);
                case "cream" -> new Color(255, 226, 192);
                case "black" -> new Color(41, 40, 45);
                default -> new Color(0, 0, 0);
            };
        }
        stainLayerDiffBase(image, luminance, colour);
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

    public NativeImage colourPatternBlend(NativeImage image, NativeImage luminance, String c, boolean isBaby) {
        Color colour;
        if (isBaby) {
            colour = switch (c) {
                case "albino" -> new Color(255, 189, 185);
                case "melanistic" -> new Color(77, 78, 84);
                default -> new Color(255, 226, 192);
            };
        } else {
            colour = switch (c) {
                case "albino" -> new Color(255, 209, 205);
                case "melanistic" -> new Color(47, 48, 54);
                case "white" -> new Color(225, 219, 215);
                case "grey" -> new Color(144, 137, 132);
                case "black" -> new Color(41, 40, 45);
                case "red" -> new Color(120, 30, 40);
                case "cyan" -> new Color(2, 97, 138);
                case "yellow" -> new Color(151, 132, 8);
                default -> new Color(0, 0, 0);
            };
        }
        stainLayerDiffBase(image, luminance, colour);
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

    public NativeImage colourHighlightBlend(NativeImage image, NativeImage luminance, String c) {
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

        stainLayerDiffBase(image, luminance, colour);
        return image;
    }

}
