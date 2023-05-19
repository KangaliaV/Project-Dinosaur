package com.kangalia.projectdinosaur.core.util;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.IOException;

public class TextureUtils {

    protected static final Logger LOGGER = LogManager.getLogger();

    public NativeImage getNativeImageFromResourceLocation(ResourceLocation location) {
        try {
            Resource resource = Minecraft.getInstance().getResourceManager().getResourceOrThrow(location);
            return NativeImage.read(resource.open());
        } catch (IOException e) {
            LOGGER.error("Couldn't load image", e);
        }
        LOGGER.error("Skipping this image due to error: "+this);
        return null;
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

    public void combineLayersBlend(NativeImage base, NativeImage image, double weight) {
        for(int y = 0; y < base.getHeight(); ++y) {
            for(int x = 0; x < base.getWidth(); ++x) {
                int base_colour = base.getPixelRGBA(x, y);
                int image_colour = image.getPixelRGBA(x, y);
                if (NativeImage.getA(image_colour) > 0) {
                    int final_colour = blendColour(new Color(base_colour), new Color(image_colour), weight).getRGB();
                    base.setPixelRGBA(x, y, final_colour);
                }
            }
        }
    }

    public Color blendColour(Color colour1, Color colour2, double weight) {
        float r = (float) weight;
        float ir = (float) 1.0 - r;

        float[] rgb1 = new float[3];
        float[] rgb2 = new float[3];

        colour1.getColorComponents(rgb1);
        colour2.getColorComponents(rgb2);

        return new Color(rgb1[0] * r + rgb2[0] * ir, rgb1[1] * r + rgb2[1] * ir, rgb1[2] * r + rgb2[2] * ir);

    }

    public void stainLayerDiffBase(NativeImage base, NativeImage luminanceLayer, Color colour) {
        for(int y = 0; y < base.getHeight(); ++y) {
            for(int x = 0; x < base.getWidth(); ++x) {
                int colourRGB = colour.getRGB();
                int alpha = NativeImage.getA(base.getPixelRGBA(x, y));
                Color base_colour = new Color(base.getPixelRGBA(x, y));
                Color new_colour = new Color(this.multiply(base_colour.getRGB(), colourRGB));
                Color luminance_colour = new Color(luminanceLayer.getPixelRGBA(x, y));

                base.setPixelRGBA(x, y, this.stainViaLuminance(alpha, luminance_colour, new_colour));
            }
        }
    }

    public void stainLayer(NativeImage base, Color colour) {
        for(int y = 0; y < base.getHeight(); ++y) {
            for(int x = 0; x < base.getWidth(); ++x) {
                int colourRGB = colour.getRGB();
                int alpha = NativeImage.getA(base.getPixelRGBA(x, y));
                Color base_colour = new Color(base.getPixelRGBA(x, y));
                Color new_colour = new Color(this.multiply(base_colour.getRGB(), colourRGB));

                base.setPixelRGBA(x, y, this.stainViaLuminance(alpha, base_colour, new_colour));
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

}
