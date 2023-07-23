package com.kangalia.projectdinosaur.client.gui;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.container.DinoScannerContainer;
import com.kangalia.projectdinosaur.common.container.GenomeScannerContainer;
import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.GameNarrator;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class GenomeScannerScreen extends AbstractContainerScreen<GenomeScannerContainer> {

    private static final ResourceLocation SCANNER = new ResourceLocation(ProjectDinosaur.MODID, "textures/gui/genome_scanner.png");
    private final ItemStack scanner;
    private final PrehistoricEntity entity;

    public GenomeScannerScreen(GenomeScannerContainer genomeScannerContainer, Inventory inventory, Component title) {
        super(genomeScannerContainer, inventory, title);
        this.scanner = genomeScannerContainer.getItem();
        this.entity = genomeScannerContainer.getPrehistoricEntity();
    }

    @Override
    public void render(@NotNull GuiGraphics pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pPoseStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShaderTexture(0, SCANNER);
        int x = this.getGuiLeft();
        int y = this.getGuiTop();
        pPoseStack.blit(SCANNER, x, y, 0, 0, 168, 169);
    }

    @Override
    protected void renderLabels(@Nonnull GuiGraphics pPoseStack, int pMouseX, int pMouseY) {
        pPoseStack.pose().scale(0.9f, 0.9f, 0.9f);
        if (scanner.hasTag()) {
            if (scanner.getTag() != null) {
                pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.dino_species_scientific", entity.getSpeciesScientific()).withStyle(ChatFormatting.ITALIC), 25, 26, 10728867);

                // GASTORNIS
                if (scanner.getTag().getBoolean("dino.gastornis")) {
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.dino_genome", scanner.getTag().getString("dino.genome")), 25, 45, 10728867);
                    pPoseStack.pose().scale(0.8f, 0.8f, 0.8f);
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.dino_morph", scanner.getTag().getString("dino.morph")), 30, 95, 9544081);
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.gastornis.feather", scanner.getTag().getString("dino.gastornis.feather")), 30, 105, 9544081);
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.gastornis.underside", scanner.getTag().getString("dino.gastornis.underside")), 30, 115, 9544081);
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.gastornis.pattern", scanner.getTag().getString("dino.gastornis.pattern")), 30, 125, 9544081);
                    if (scanner.getTag().getInt("dino.sex") == 0) {
                        pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.gastornis.highlight", scanner.getTag().getString("dino.gastornis.highlight"), "Y"), 30, 135, 9544081);
                    } else {
                        pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.gastornis.highlight", scanner.getTag().getString("dino.gastornis.highlight"), "N"), 30, 135, 9544081);
                    }
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.gastornis.skin", scanner.getTag().getString("dino.gastornis.skin")), 30, 145, 9544081);
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.gastornis.beak", scanner.getTag().getString("dino.gastornis.beak")), 30, 155, 9544081);

                // AUSTRALOVENATOR
                } else if (scanner.getTag().getBoolean("dino.australovenator")) {
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.dino_genome", scanner.getTag().getString("dino.genome")), 25, 45, 10728867);
                    pPoseStack.pose().scale(0.8f, 0.8f, 0.8f);
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.dino_morph", scanner.getTag().getString("dino.morph")), 30, 95, 9544081);
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.australovenator.base", scanner.getTag().getString("dino.australovenator.base")), 30, 105, 9544081);
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.australovenator.underside", scanner.getTag().getString("dino.australovenator.underside")), 30, 115, 9544081);
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.australovenator.pattern", scanner.getTag().getString("dino.australovenator.pattern_type"), scanner.getTag().getString("dino.australovenator.pattern_colour")), 30, 125, 9544081);
                    if (scanner.getTag().getInt("dino.sex") == 0) {
                        pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.australovenator.highlight", scanner.getTag().getString("dino.australovenator.highlight"), "Y"), 30, 135, 9544081);
                    } else {
                        pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.australovenator.highlight", scanner.getTag().getString("dino.australovenator.highlight"), "N"), 30, 135, 9544081);
                    }

                // APHANERAMMA
                } else if (scanner.getTag().getBoolean("dino.aphaneramma")) {
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.dino_genome", scanner.getTag().getString("dino.genome")), 25, 45, 10728867);
                    pPoseStack.pose().scale(0.8f, 0.8f, 0.8f);
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.dino_morph", scanner.getTag().getString("dino.morph")), 30, 95, 9544081);
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.aphaneramma.base", scanner.getTag().getString("dino.aphaneramma.base")), 30, 105, 9544081);
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.aphaneramma.underside", scanner.getTag().getString("dino.aphaneramma.underside")), 30, 115, 9544081);
                    if (scanner.getTag().getInt("dino.sex") == 0) {
                        pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.aphaneramma.pattern", scanner.getTag().getString("dino.aphaneramma.pattern"), "M"), 30, 125, 9544081);
                    } else {
                        pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.aphaneramma.pattern", scanner.getTag().getString("dino.aphaneramma.pattern"), "F"), 30, 125, 9544081);
                    }

                // TRILOBITE
                } else if (scanner.getTag().getBoolean("dino.trilobite")) {
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.dino_genome", scanner.getTag().getString("dino.genome")), 25, 45, 10728867);
                    pPoseStack.pose().scale(0.8f, 0.8f, 0.8f);
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.dino_morph", scanner.getTag().getString("dino.morph")), 30, 95, 9544081);
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.trilobite.base", scanner.getTag().getString("dino.trilobite.base")), 30, 105, 9544081);
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.trilobite.underside", scanner.getTag().getString("dino.trilobite.underside")), 30, 115, 9544081);
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.trilobite.pattern", scanner.getTag().getString("dino.trilobite.pattern"), scanner.getTag().getString("dino.trilobite.pattern_colour")), 30, 125, 9544081);
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.trilobite.horn", scanner.getTag().getString("dino.trilobite.horn")), 30, 135, 9544081);

                // SCELIDOSAURUS
                } else if (scanner.getTag().getBoolean("dino.scelidosaurus")) {
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.dino_genome", scanner.getTag().getString("dino.genome")), 25, 45, 10728867);
                    pPoseStack.pose().scale(0.8f, 0.8f, 0.8f);
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.dino_morph", scanner.getTag().getString("dino.morph")), 30, 95, 9544081);
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.scelidosaurus.base", scanner.getTag().getString("dino.scelidosaurus.base")), 30, 105, 9544081);
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.scelidosaurus.underside", scanner.getTag().getString("dino.scelidosaurus.underside")), 30, 115, 9544081);
                    pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.scelidosaurus.pattern", scanner.getTag().getString("dino.scelidosaurus.pattern")), 30, 125, 9544081);
                    if (scanner.getTag().getInt("dino.sex") == 0) {
                        pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.scelidosaurus.highlight", scanner.getTag().getString("dino.scelidosaurus.highlight"), "Y"), 30, 135, 9544081);
                    } else {
                        pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.scelidosaurus.highlight", scanner.getTag().getString("dino.scelidosaurus.highlight"), "N"), 30, 135, 9544081);
                    }
                }

                    // UNIVERSALS
                pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.speed", scanner.getTag().getString("dino.speed")), 30, 190, 9544081);
                pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.size", scanner.getTag().getString("dino.size")), 30, 200, 9544081);
                pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.attack_damage", scanner.getTag().getString("dino.attack_damage")), 120, 190, 9544081);
                pPoseStack.drawString(this.font, Component.translatable("data.projectdinosaur.max_health", scanner.getTag().getString("dino.max_health")), 120, 200, 9544081);
            }
        }
    }
}
