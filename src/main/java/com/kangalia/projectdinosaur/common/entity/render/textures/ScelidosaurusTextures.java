package com.kangalia.projectdinosaur.common.entity.render.textures;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.ScelidosaurusEntity;
import com.kangalia.projectdinosaur.core.util.TextureUtils;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.io.IOException;

public class ScelidosaurusTextures extends TextureUtils {

    ResourceLocation BASE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/scelidosaurus/base.png");
    ResourceLocation UNDERSIDE = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/scelidosaurus/underside.png");
    ResourceLocation SCUTES = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/scelidosaurus/scutes.png");
    ResourceLocation PATTERN = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/scelidosaurus/pattern.png");
    ResourceLocation HIGHLIGHT_ADULT = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/scelidosaurus/highlight_adult.png");
    ResourceLocation HIGHLIGHT_BABY = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/scelidosaurus/highlight_baby.png");

    ResourceLocation EYE_ADULT = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/scelidosaurus/eye_adult.png");
    ResourceLocation EYE_BABY = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/scelidosaurus/eye_baby.png");

    ResourceLocation UNDERSIDE_BLEND1 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/scelidosaurus/underside_blend1.png");
    ResourceLocation UNDERSIDE_BLEND2 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/scelidosaurus/underside_blend2.png");
    ResourceLocation SCUTES_BLEND1 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/scelidosaurus/scutes_blend1.png");
    ResourceLocation SCUTES_BLEND2 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/scelidosaurus/scutes_blend1.png");
    ResourceLocation PATTERN_BLEND1 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/scelidosaurus/pattern_blend1.png");
    ResourceLocation PATTERN_BLEND2 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/scelidosaurus/pattern_blend2.png");
    ResourceLocation HIGHLIGHT_BLEND1 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/scelidosaurus/highlight_blend1.png");
    ResourceLocation HIGHLIGHT_BLEND2 = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/scelidosaurus/highlight_blend2.png");

    ResourceLocation MOUTH = new ResourceLocation(ProjectDinosaur.MODID, "textures/entity/mob/dino/scelidosaurus/mouth.png");

    public NativeImage colourScelidosaurus(ScelidosaurusEntity scelidosaurus) throws IOException {
        String morph = scelidosaurus.getColourMorph();
        String base = scelidosaurus.getGeneDominance(1);
        String underside = scelidosaurus.getGeneDominance(2);
        String pattern = scelidosaurus.getGeneDominance(3);
        String highlight = scelidosaurus.getGeneDominance(4);

        NativeImage original = getNativeImageFromResourceLocation(BASE);
        NativeImage base_image = getNativeImageFromResourceLocation(BASE);
        NativeImage underside_image = getNativeImageFromResourceLocation(UNDERSIDE);
        NativeImage pattern_image = getNativeImageFromResourceLocation(PATTERN);
        NativeImage scutes_image = getNativeImageFromResourceLocation(SCUTES);
        NativeImage highlight_image = getNativeImageFromResourceLocation(HIGHLIGHT_ADULT);
        if (scelidosaurus.isBaby()) {
            highlight_image = getNativeImageFromResourceLocation(HIGHLIGHT_BABY);
        }

        NativeImage eye_image = getNativeImageFromResourceLocation(EYE_ADULT);
        boolean isBaby = scelidosaurus.isBaby();
        if (isBaby) {
            eye_image = getNativeImageFromResourceLocation(EYE_BABY);
        }

        NativeImage underside_blend_image1 = getNativeImageFromResourceLocation(UNDERSIDE_BLEND1);
        NativeImage underside_blend_image2 = getNativeImageFromResourceLocation(UNDERSIDE_BLEND2);
        NativeImage scutes_blend_image1 = getNativeImageFromResourceLocation(SCUTES_BLEND1);
        NativeImage scutes_blend_image2 = getNativeImageFromResourceLocation(SCUTES_BLEND2);
        NativeImage pattern_blend_image1 = getNativeImageFromResourceLocation(PATTERN_BLEND1);
        NativeImage pattern_blend_image2 = getNativeImageFromResourceLocation(PATTERN_BLEND2);
        NativeImage highlight_blend_image1 = getNativeImageFromResourceLocation(HIGHLIGHT_BLEND1);
        NativeImage highlight_blend_image2 = getNativeImageFromResourceLocation(HIGHLIGHT_BLEND2);

        NativeImage mouth_image = getNativeImageFromResourceLocation(MOUTH);

        if (morph.equals("Albino")) {
            base_image = colourAlbino(base_image, "base", isBaby);
            underside_image = colourAlbino(underside_image, "underside", isBaby);
            underside_blend_image1 = colourUndersideBlend(underside_blend_image1, original, "albino", isBaby);
            underside_blend_image2 = colourUndersideBlend(underside_blend_image2, original, "albino", isBaby);
            scutes_blend_image1 = colourScutesBlend(scutes_blend_image1, original, "albino", isBaby);
            scutes_blend_image2 = colourScutesBlend(scutes_blend_image2, original, "albino", isBaby);
            pattern_image = colourAlbino(pattern_image, "pattern", isBaby);
            pattern_blend_image1 = colourPatternBlend(pattern_blend_image1, original,"albino", isBaby);
            pattern_blend_image2 = colourPatternBlend(pattern_blend_image2, original,"albino", isBaby);
            if (scelidosaurus.getGender() == 0 && !scelidosaurus.isBaby()) {
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
            scutes_blend_image1 = colourScutesBlend(scutes_blend_image1, original, "melanistic", isBaby);
            scutes_blend_image2 = colourScutesBlend(scutes_blend_image2, original, "melanistic", isBaby);
            pattern_image = colourMelanistic(pattern_image, "pattern", isBaby);
            pattern_blend_image1 = colourPatternBlend(pattern_blend_image1, original, "melanistic", isBaby);
            pattern_blend_image2 = colourPatternBlend(pattern_blend_image2, original, "melanistic", isBaby);
            if (scelidosaurus.getGender() == 0 && !scelidosaurus.isBaby()) {
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
            scutes_blend_image1 = colourScutesBlend(scutes_blend_image1, original, "normal", isBaby);
            scutes_blend_image2 = colourScutesBlend(scutes_blend_image2, original, "normal", isBaby);
            pattern_image = colourPattern(pattern_image, pattern.toLowerCase(), isBaby);
            pattern_blend_image1 = colourPatternBlend(pattern_blend_image1, original, pattern.toLowerCase(), isBaby);
            pattern_blend_image2 = colourPatternBlend(pattern_blend_image2, original, pattern.toLowerCase(), isBaby);
            if (scelidosaurus.getGender() == 0 && !scelidosaurus.isBaby()) {
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
        combineLayers(base_image, scutes_image);
        combineLayers(base_image, pattern_image);
        combineLayers(base_image, highlight_image);

        combineLayersBlend(base_image, underside_blend_image1, 0.75);
        combineLayersBlend(base_image, underside_blend_image2, 0.5);
        combineLayersBlend(base_image, scutes_blend_image1, 0.75);
        combineLayersBlend(base_image, scutes_blend_image2, 0.5);
        combineLayersBlend(base_image, pattern_blend_image1, 0.75);
        combineLayersBlend(base_image, pattern_blend_image2, 0.5);
        combineLayersBlend(base_image, highlight_blend_image1, 0.75);
        combineLayersBlend(base_image, highlight_blend_image2, 0.5);

        if (!scelidosaurus.isSleeping()) {
            combineLayers(base_image, eye_image);
        }

        combineLayers(base_image, mouth_image);

        return base_image;
    }

    public NativeImage colourAlbino(NativeImage image, String layer, boolean isBaby) {
        Color colour = new Color(255, 255, 255);
        boolean a = layer.equals("base") || layer.equals("underside");
        boolean b = layer.equals("pattern") || layer.equals("highlight_female") || layer.equals("eye");
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
        boolean a = layer.equals("base") || layer.equals("underside");
        boolean b = layer.equals("pattern") || layer.equals("highlight_female");
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
                case "grey", "cream" -> new Color(225, 219, 215);
                case "brown" -> new Color(170, 133, 106);
                case "black" -> new Color(111, 100, 100);
                default -> new Color(0, 0, 0);
            };
        } else {
            colour = switch (c) {
                case "grey" -> new Color(164, 157, 152);
                case "cream" -> new Color(255, 226, 192);
                case "brown" -> new Color(120, 83, 56);
                case "black" -> new Color(61, 50, 50);
                default -> new Color(0, 0, 0);
            };
        }
        stainLayer(image, colour);
        return image;
    }

    public NativeImage colourPattern(NativeImage image, String c, boolean isBaby) {
        Color colour;
        if (isBaby) {
            colour = switch (c) {
                case "white", "grey" -> new Color(225, 219, 215);
                case "black" -> new Color(111, 100, 90);
                case "red" -> new Color(220, 130, 120);
                case "cyan" -> new Color(102, 197, 218);
                case "yellow" -> new Color(231, 232, 108);
                default -> new Color(0, 0, 0);
            };
        } else {
            colour = switch (c) {
                case "white" -> new Color(225, 219, 215);
                case "grey" -> new Color(164, 157, 152);
                case "black" -> new Color(61, 50, 50);
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
                case "white", "grey" -> new Color(225, 219, 215);
                case "black" -> new Color(111, 100, 90);
                case "red" -> new Color(220, 130, 120);
                case "cyan" -> new Color(102, 197, 218);
                case "yellow" -> new Color(231, 232, 108);
                case "albino" -> new Color(245, 199, 195);
                case "melanistic" -> new Color(67, 68, 74);
                default -> new Color(0, 0, 0);
            };
        } else {
            colour = switch (c) {
                case "white" -> new Color(225, 219, 215);
                case "grey" -> new Color(164, 157, 152);
                case "black" -> new Color(61, 50, 50);
                case "red" -> new Color(120, 30, 40);
                case "cyan" -> new Color(2, 97, 138);
                case "yellow" -> new Color(151, 132, 8);
                case "albino" -> new Color(245, 219, 215);
                case "melanistic" -> new Color(37, 38, 44);
                default -> new Color(0, 0, 0);
            };
        }
        stainLayerDiffBase(image, luminance, colour);
        return image;
    }

    public NativeImage colourUnderside(NativeImage image, String c, boolean isBaby) {
        Color colour;
        if (isBaby) {
            colour = new Color(235, 229, 205);
        } else {
            colour = switch (c) {
                case "brown" -> new Color(170, 133, 106);
                case "grey" -> new Color(164, 157, 152);
                case "cream" -> new Color(255, 226, 192);
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
                case "albino" -> new Color(245, 199, 195);
                case "melanistic" -> new Color(67, 68, 74);
                default -> new Color(235, 229, 205);
            };
        } else {
            colour = switch (c) {
                case "albino" -> new Color(245, 219, 215);
                case "melanistic" -> new Color(37, 38, 44);
                case "brown" -> new Color(170, 133, 106);
                case "grey" -> new Color(164, 157, 152);
                case "cream" -> new Color(255, 226, 192);
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

    public NativeImage colourScutesBlend(NativeImage image, NativeImage luminance, String c, boolean isBaby) {
        Color colour;
        if (isBaby) {
            colour = switch (c) {
                case "albino" -> new Color(255, 189, 185);
                case "melanistic" -> new Color(87, 88, 94);
                default -> new Color(80, 80, 80);
            };
        } else {
            colour = switch (c) {
                case "albino" -> new Color(255, 209, 205);
                case "melanistic" -> new Color(57, 58, 64);
                default -> new Color(60, 60, 60);
            };
        }

        stainLayerDiffBase(image, luminance, colour);
        return image;
    }

}
