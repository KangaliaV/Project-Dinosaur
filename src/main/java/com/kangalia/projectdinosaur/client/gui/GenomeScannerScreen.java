package com.kangalia.projectdinosaur.client.gui;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.GameNarrator;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class GenomeScannerScreen extends Screen {

    private static final ResourceLocation SCANNER = new ResourceLocation(ProjectDinosaur.MODID, "textures/gui/genome_scanner.png");
    private final ItemStack scanner;
    private final PrehistoricEntity entity;

    private static final int scannerWidth = 168;
    private static final int xCorrection = 154;
    private static final int yCorrection = 18;

    public GenomeScannerScreen(PrehistoricEntity prehistoric, ItemStack usedItem) {
        super(GameNarrator.NO_TITLE);
        this.scanner = usedItem;
        this.entity = prehistoric;
    }

    @Override
    public void render(@Nonnull PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        RenderSystem.setShaderTexture(0, SCANNER);
        int x = (this.width - scannerWidth) / 2;
        int y = 20;
        this.blit(pPoseStack, x, y, 0, 0, 255, 255);
        this.renderLabels(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    protected void renderLabels(@Nonnull PoseStack pPoseStack) {
        pPoseStack.scale(0.9f, 0.9f, 0.9f);
        if (scanner.hasTag()) {
            if (scanner.getTag() != null) {
                this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.dino_species_scientific", entity.getSpeciesScientific()).withStyle(ChatFormatting.ITALIC), 163, 55, 10728867);

                if (scanner.getTag().getBoolean("dino.gastornis")) {
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.dino_genome", scanner.getTag().getString("dino.genome")), 163, 75, 10728867);
                    pPoseStack.scale(0.8f, 0.8f, 0.8f);
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.dino_morph", scanner.getTag().getString("dino.morph")), 50+xCorrection, 100+yCorrection, 9544081);
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.gastornis.feather", scanner.getTag().getString("dino.gastornis.feather")), 50+xCorrection, 112+yCorrection, 9544081);
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.gastornis.underside", scanner.getTag().getString("dino.gastornis.underside")), 50+xCorrection, 124+yCorrection, 9544081);
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.gastornis.pattern", scanner.getTag().getString("dino.gastornis.pattern")), 50+xCorrection, 136+yCorrection, 9544081);
                    if (scanner.getTag().getInt("dino.sex") == 0) {
                        this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.gastornis.highlight", scanner.getTag().getString("dino.gastornis.highlight"), "Y"), 50+xCorrection, 148+yCorrection, 9544081);
                    } else {
                        this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.gastornis.highlight", scanner.getTag().getString("dino.gastornis.highlight"), "N"), 50+xCorrection, 148+yCorrection, 9544081);
                    }
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.gastornis.skin", scanner.getTag().getString("dino.gastornis.skin")), 50+xCorrection, 160+yCorrection, 9544081);
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.gastornis.beak", scanner.getTag().getString("dino.gastornis.beak")), 50+xCorrection, 172+yCorrection, 9544081);

                } else if (scanner.getTag().getBoolean("dino.australovenator")) {
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.dino_genome", scanner.getTag().getString("dino.genome")), 163, 75, 10728867);
                    pPoseStack.scale(0.8f, 0.8f, 0.8f);
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.dino_morph", scanner.getTag().getString("dino.morph")), 50+xCorrection, 100+yCorrection, 9544081);
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.australovenator.base", scanner.getTag().getString("dino.australovenator.base")), 50+xCorrection, 112+yCorrection, 9544081);
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.australovenator.underside", scanner.getTag().getString("dino.australovenator.underside")), 50+xCorrection, 124+yCorrection, 9544081);
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.australovenator.pattern", scanner.getTag().getString("dino.australovenator.pattern_type"), scanner.getTag().getString("dino.australovenator.pattern_colour")), 50+xCorrection, 136+yCorrection, 9544081);
                    if (scanner.getTag().getInt("dino.sex") == 0) {
                        this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.australovenator.highlight", scanner.getTag().getString("dino.australovenator.highlight"), "Y"), 50+xCorrection, 148+yCorrection, 9544081);
                    } else {
                        this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.australovenator.highlight", scanner.getTag().getString("dino.australovenator.highlight"), "N"), 50+xCorrection, 148+yCorrection, 9544081);
                    }

                } else if (scanner.getTag().getBoolean("dino.aphaneramma")) {
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.dino_genome", scanner.getTag().getString("dino.genome")), 163, 75, 10728867);
                    pPoseStack.scale(0.8f, 0.8f, 0.8f);
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.dino_morph", scanner.getTag().getString("dino.morph")), 50+xCorrection, 100+yCorrection, 9544081);
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.aphaneramma.base", scanner.getTag().getString("dino.aphaneramma.base")), 50+xCorrection, 112+yCorrection, 9544081);
                    this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.aphaneramma.underside", scanner.getTag().getString("dino.aphaneramma.underside")), 50+xCorrection, 124+yCorrection, 9544081);
                    if (scanner.getTag().getInt("dino.sex") == 0) {
                        this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.aphaneramma.pattern", scanner.getTag().getString("dino.aphaneramma.pattern"), "M"), 50 + xCorrection, 136 + yCorrection, 9544081);
                    } else {
                        this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.aphaneramma.pattern", scanner.getTag().getString("dino.aphaneramma.pattern"), "F"), 50 + xCorrection, 136 + yCorrection, 9544081);
                    }
                }
                this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.speed", scanner.getTag().getString("dino.speed")), 50+xCorrection, 204+yCorrection, 9544081);
                this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.size", scanner.getTag().getString("dino.size")), 50+xCorrection, 216+yCorrection, 9544081);
                this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.attack_damage", scanner.getTag().getString("dino.attack_damage")), 150+xCorrection, 204+yCorrection, 9544081);
                this.font.draw(pPoseStack, Component.translatable("data.projectdinosaur.max_health", scanner.getTag().getString("dino.max_health")), 150+xCorrection, 216+yCorrection, 9544081);
            }
        }
    }
}
