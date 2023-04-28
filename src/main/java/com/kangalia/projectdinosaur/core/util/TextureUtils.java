package com.kangalia.projectdinosaur.core.util;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

}
