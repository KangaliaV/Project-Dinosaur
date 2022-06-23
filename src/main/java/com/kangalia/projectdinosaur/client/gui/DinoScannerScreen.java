package com.kangalia.projectdinosaur.client.gui;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.container.DinoScannerContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import javax.annotation.Nonnull;

public class DinoScannerScreen extends AbstractContainerScreen<DinoScannerContainer> {

    private final ResourceLocation SCANNER = new ResourceLocation(ProjectDinosaur.MODID, "textures/gui/dino_scanner.png");

    public DinoScannerScreen(DinoScannerContainer dinoScannerContainer, Inventory inventory, Component title) {
        super(dinoScannerContainer, inventory, title);
        this.titleLabelX = 80;
        this.titleLabelY = 116;
    }

    @Override
    public void render(@Nonnull PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pPoseStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShaderTexture(0, SCANNER);
        int i = this.getGuiLeft();
        int j = this.getGuiTop();
        this.blit(pPoseStack, i-40, j-20, 0, 0, 247, 169);
    }

    @Override
    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        this.font.draw(pPoseStack, this.title, (float)this.titleLabelX, (float)this.titleLabelY, 9544081);
        this.font.draw(pPoseStack, new TranslatableComponent("screen.projectdinosaur.dino_age"), -24, 12, 9544081);
        this.font.draw(pPoseStack, new TranslatableComponent("screen.projectdinosaur.dino_sex"), -24, 28, 9544081);
        this.font.draw(pPoseStack, new TranslatableComponent("screen.projectdinosaur.dino_health"), -24, 44, 9544081);
        this.font.draw(pPoseStack, new TranslatableComponent("screen.projectdinosaur.dino_food"), -24, 60, 9544081);
        this.font.draw(pPoseStack, new TranslatableComponent("screen.projectdinosaur.dino_nocturnal"), -24, 76, 9544081);
    }
}
