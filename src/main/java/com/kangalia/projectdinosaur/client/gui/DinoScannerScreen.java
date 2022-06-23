package com.kangalia.projectdinosaur.client.gui;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.container.DinoScannerContainer;
import com.kangalia.projectdinosaur.common.item.DinoScanner;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class DinoScannerScreen extends AbstractContainerScreen<DinoScannerContainer> {

    private final ResourceLocation SCANNER = new ResourceLocation(ProjectDinosaur.MODID, "textures/gui/dino_scanner.png");

    public DinoScannerScreen(DinoScannerContainer dinoScannerContainer, Inventory inventory, Component title) {
    super(dinoScannerContainer, inventory, title);

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
        int i = (this.width - 192) / 2;
        int j = 2;
        this.blit(pPoseStack, i, 2, 0, 0, 192, 192);
    }
}
