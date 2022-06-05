package com.kangalia.projectdinosaur.client.gui;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class DinoScannerScreen extends Screen {

    private final ResourceLocation SCANNER = new ResourceLocation(ProjectDinosaur.MODID, "textures/gui/dino_scanner.png");

    protected DinoScannerScreen(Component pTitle) {
        super(pTitle);
    }

    @Override
    public void render(@Nonnull PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        RenderSystem.setShaderTexture(0, SCANNER);
    }
}
