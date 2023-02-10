package com.kangalia.projectdinosaur.client.gui;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.container.GenomeScannerContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class GenomeScannerScreen extends AbstractContainerScreen<GenomeScannerContainer> {

    private final ResourceLocation SCANNER = new ResourceLocation(ProjectDinosaur.MODID, "textures/gui/genome_scanner.png");
    ItemStack scanner;
    GenomeScannerContainer container;

    public GenomeScannerScreen(GenomeScannerContainer genomeScannerContainer, Inventory inventory, Component title) {
        super(genomeScannerContainer, inventory, title);
        this.titleLabelX = 24;
        this.titleLabelY = 12;
        scanner = genomeScannerContainer.getItem();
        container = genomeScannerContainer;
    }

    @Override
    public void render(@Nonnull PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pPoseStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(@Nonnull PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShaderTexture(0, SCANNER);
        int i = this.getGuiLeft();
        int j = this.getGuiTop();
        this.blit(pPoseStack, i+4, j-20, 0, 0, 247, 169);
    }

    @Override
    protected void renderLabels(@Nonnull PoseStack pPoseStack, int pMouseX, int pMouseY) {
        pPoseStack.scale(0.9f, 0.9f, 0.9f);
        this.font.draw(pPoseStack, this.title, (float)this.titleLabelX, (float)this.titleLabelY, 10728867);
        if (scanner.hasTag()) {
            if (scanner.getTag() != null) {
                if (scanner.getTag().getBoolean("dino.gastornis")) {
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.dino_genome", scanner.getTag().getString("dino.genome")), 24, 30, 10728867);
                    pPoseStack.scale(0.8f, 0.8f, 0.8f);
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.dino_morph", scanner.getTag().getString("dino.morph")), 30, 66, 9544081);
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.gastornis.feather", scanner.getTag().getString("dino.gastornis.feather")), 30, 78, 9544081);
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.gastornis.underside", scanner.getTag().getString("dino.gastornis.underside")), 30, 90, 9544081);
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.gastornis.pattern", scanner.getTag().getString("dino.gastornis.pattern")), 30, 102, 9544081);
                    if (scanner.getTag().getInt("dino.sex") == 0) {
                        this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.gastornis.highlight", scanner.getTag().getString("dino.gastornis.highlight"), "Y"), 30, 114, 9544081);
                    } else {
                        this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.gastornis.highlight", scanner.getTag().getString("dino.gastornis.highlight"), "N"), 30, 114, 9544081);
                    }
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.gastornis.skin", scanner.getTag().getString("dino.gastornis.skin")), 30, 126, 9544081);
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.gastornis.beak", scanner.getTag().getString("dino.gastornis.beak")), 30, 138, 9544081);
                } else if (scanner.getTag().getBoolean("dino.australovenator")) {
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.dino_genome", scanner.getTag().getString("dino.genome")), 24, 30, 10728867);
                    pPoseStack.scale(0.8f, 0.8f, 0.8f);
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.dino_morph", scanner.getTag().getString("dino.morph")), 30, 66, 9544081);
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.australovenator.base", scanner.getTag().getString("dino.australovenator.base")), 30, 78, 9544081);
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.australovenator.underside", scanner.getTag().getString("dino.australovenator.underside")), 30, 90, 9544081);
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.australovenator.pattern", scanner.getTag().getString("dino.australovenator.pattern_type"), scanner.getTag().getString("dino.australovenator.pattern_colour")), 30, 102, 9544081);
                    if (scanner.getTag().getInt("dino.sex") == 0) {
                        this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.australovenator.highlight", scanner.getTag().getString("dino.australovenator.highlight"), "Y"), 30, 114, 9544081);
                    } else {
                        this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.australovenator.highlight", scanner.getTag().getString("dino.australovenator.highlight"), "N"), 30, 114, 9544081);
                    }
                }
                this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.speed", scanner.getTag().getString("dino.speed")), 30, 164, 9544081);
                this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.size", scanner.getTag().getString("dino.size")), 30, 176, 9544081);
                this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.attack_damage", scanner.getTag().getString("dino.attack_damage")), 130, 164, 9544081);
                this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.max_health", scanner.getTag().getString("dino.max_health")), 130, 176, 9544081);
            }
        }
    }
}
