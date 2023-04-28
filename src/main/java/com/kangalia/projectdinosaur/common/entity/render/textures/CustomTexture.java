package com.kangalia.projectdinosaur.common.entity.render.textures;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.platform.TextureUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.server.packs.resources.ResourceManager;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class CustomTexture extends AbstractTexture {

    public final NativeImage image;

    public CustomTexture(NativeImage nativeImage) {
        this.image = nativeImage;
        if (this.image == null) {
            throw new IllegalStateException("Texture is null.");
        }
    }

    @Override
    public void load(@NotNull ResourceManager manager) throws IOException {
        if (!RenderSystem.isOnRenderThreadOrInit()) {
            RenderSystem.recordRenderCall(() -> {
                this.loadImage(image);
            });
        } else {
            this.loadImage(image);
        }
    }

    private void loadImage(NativeImage imageIn) {
        TextureUtil.prepareImage(this.getId(), imageIn.getWidth(), imageIn.getHeight());
        imageIn.upload(0, 0, 0, true);
    }

}
