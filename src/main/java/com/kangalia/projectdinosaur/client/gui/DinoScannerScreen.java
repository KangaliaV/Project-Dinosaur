package com.kangalia.projectdinosaur.client.gui;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.container.DinoScannerContainer;
import com.kangalia.projectdinosaur.common.item.DinoScanner;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class DinoScannerScreen extends AbstractContainerScreen<DinoScannerContainer> {

    private final ResourceLocation SCANNER = new ResourceLocation(ProjectDinosaur.MODID, "textures/gui/dino_scanner.png");
    ItemStack scanner;

    public DinoScannerScreen(DinoScannerContainer dinoScannerContainer, Inventory inventory, Component title) {
        super(dinoScannerContainer, inventory, title);
        this.titleLabelX = 55;
        this.titleLabelY = 110;
        scanner = dinoScannerContainer.getItem();
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
        if (scanner.hasTag()) {
            if (scanner.getTag() != null) {
                if (scanner.getTag().getBoolean("dino.hasnickname")) {
                    this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_nickname", scanner.getTag().getString("dino.nickname")), 55, 126, 9544081);
                } else {
                    this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_nickname", "-----"), 55, 126, 9544081);
                }
            }
            this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_age", scanner.getTag().getInt("dino.age")), -26, 12, 9544081);
            if (scanner.getTag().getInt("dino.sex") == 0) {
                this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_sex", new TranslatableComponent("attribute.projectdinosaur.male")), -26, 28, 9544081);
            } else {
                this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_sex", new TranslatableComponent("attribute.projectdinosaur.female")), -26, 28, 9544081);
            }
            this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_health", scanner.getTag().getInt("dino.health"), scanner.getTag().getInt("dino.maxhealth")), -26, 44, 9544081);
            this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_food", scanner.getTag().getInt("dino.food"), scanner.getTag().getInt("dino.maxfood")), -26, 60, 9544081);
            if (scanner.getTag().getInt("dino.diet") == 0) {
                this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_diet", new TranslatableComponent("attribute.projectdinosaur.herbivore")), -26, 76, 9544081);
            } else if (scanner.getTag().getInt("dino.diet") == 1) {
                this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_diet", new TranslatableComponent("attribute.projectdinosaur.carnivore")), -26, 76, 9544081);
            } else {
                this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_diet", new TranslatableComponent("attribute.projectdinosaur.piscivore")), -26, 76, 9544081);
            }
            if (scanner.getTag().getInt("dino.schedule") == 0) {
                this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_schedule", new TranslatableComponent("attribute.projectdinosaur.diurnal")), -26, 92, 9544081);
            } else if (scanner.getTag().getInt("dino.schedule") == 1) {
                this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_schedule", new TranslatableComponent("attribute.projectdinosaur.nocturnal")), -26, 92, 9544081);
            } else {
                this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_schedule", new TranslatableComponent("attribute.projectdinosaur.cathemeral")), -26, 92, 9544081);
            }
        }
    }
}
