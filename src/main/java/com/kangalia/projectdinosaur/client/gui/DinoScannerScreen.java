package com.kangalia.projectdinosaur.client.gui;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.container.DinoScannerContainer;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class DinoScannerScreen extends AbstractContainerScreen<DinoScannerContainer> {

    private final ResourceLocation SCANNER = new ResourceLocation(ProjectDinosaur.MODID, "textures/gui/dino_scanner.png");
    ItemStack scanner;
    DinoScannerContainer container;

    public DinoScannerScreen(DinoScannerContainer dinoScannerContainer, Inventory inventory, Component title) {
        super(dinoScannerContainer, inventory, title);
        this.titleLabelX = 55;
        this.titleLabelY = 110;
        scanner = dinoScannerContainer.getItem();
        container = dinoScannerContainer;
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
        this.blit(pPoseStack, i-40, j-20, 0, 0, 247, 169);
        if (scanner.getTag() != null) {
            renderEntity(i + 112, j + 75, scanner.getTag().getInt("dino.scale"), container.getPrehistoricEntity());
        } else {
            renderEntity(i + 112, j + 75, 50, container.getPrehistoricEntity());
        }
    }

    @Override
    protected void renderLabels(@Nonnull PoseStack pPoseStack, int pMouseX, int pMouseY) {
        this.font.draw(pPoseStack, this.title, (float)this.titleLabelX, (float)this.titleLabelY, 9544081);
        if (scanner.hasTag()) {
            if (scanner.getTag() != null) {
                if (scanner.getTag().getBoolean("dino.hasnickname")) {
                    this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_nickname", scanner.getTag().getString("dino.nickname")), 55, 126, 9544081);
                } else {
                    this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_nickname", "-----"), 55, 126, 9544081);
                }
                pPoseStack.scale(0.8f, 0.8f, 0.8f);
                this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_age", scanner.getTag().getInt("dino.age")), -30, 12, 9544081);
                if (scanner.getTag().getInt("dino.sex") == 0) {
                    this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_sex", new TranslatableComponent("attribute.projectdinosaur.male")), -30, 28, 9544081);
                } else {
                    this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_sex", new TranslatableComponent("attribute.projectdinosaur.female")), -30, 28, 9544081);
                }
                this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_health", scanner.getTag().getInt("dino.health"), scanner.getTag().getInt("dino.maxhealth")), -30, 44, 9544081);
                this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_food", scanner.getTag().getInt("dino.food"), scanner.getTag().getInt("dino.maxfood")), -30, 60, 9544081);
                this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_enrichment", scanner.getTag().getInt("dino.enrichment"), scanner.getTag().getInt("dino.maxenrichment")), -30, 76, 9544081);
                if (scanner.getTag().getInt("dino.mood") == 0) {
                    this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_mood", new TranslatableComponent("attribute.projectdinosaur.happy")), -30, 92, 9544081);
                } else if (scanner.getTag().getInt("dino.mood") == 1) {
                    this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_mood", new TranslatableComponent("attribute.projectdinosaur.moody")), -30, 92, 9544081);
                } else {
                    this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_mood", new TranslatableComponent("attribute.projectdinosaur.angry")), -30, 92, 9544081);
                }
                if (scanner.getTag().getInt("dino.diet") == 0) {
                    this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_diet", new TranslatableComponent("attribute.projectdinosaur.herbivore")), -30, 108, 9544081);
                } else if (scanner.getTag().getInt("dino.diet") == 1) {
                    this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_diet", new TranslatableComponent("attribute.projectdinosaur.carnivore")), -30, 108, 9544081);
                } else {
                    this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_diet", new TranslatableComponent("attribute.projectdinosaur.piscivore")), -30, 108, 9544081);
                }
                if (scanner.getTag().getInt("dino.schedule") == 0) {
                    this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_schedule", new TranslatableComponent("attribute.projectdinosaur.diurnal")), -30, 124, 9544081);
                } else if (scanner.getTag().getInt("dino.schedule") == 1) {
                    this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_schedule", new TranslatableComponent("attribute.projectdinosaur.nocturnal")), -30, 124, 9544081);
                } else {
                    this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_schedule", new TranslatableComponent("attribute.projectdinosaur.cathemeral")), -30, 124, 9544081);
                }
                if (scanner.getTag().getBoolean("dino.iscryosick")) {
                    this.font.draw(pPoseStack, new TranslatableComponent("data.projectdinosaur.dino_cryosick"), -30, 140, 9544081);
                }
            }
        }
    }

    @SuppressWarnings( "deprecation" )
    public static void renderEntity(int pPosX, int pPosY, int pScale, LivingEntity pLivingEntity) {
        PoseStack posestack = RenderSystem.getModelViewStack();
        posestack.pushPose();
        posestack.translate(pPosX, pPosY, 1050.0D);
        posestack.scale(1.0F, 1.0F, -1.0F);
        RenderSystem.applyModelViewMatrix();
        PoseStack posestack1 = new PoseStack();
        posestack1.translate(0.0D, 0.0D, 1000.0D);
        posestack1.scale((float)pScale, (float)pScale, (float)pScale);
        Quaternion quaternion = Vector3f.ZP.rotationDegrees(180.0F);
        Quaternion quaternion1 = Vector3f.XP.rotationDegrees(-10.0F);
        quaternion.mul(quaternion1);
        posestack1.mulPose(quaternion);
        float f2 = pLivingEntity.yBodyRot;
        float f3 = pLivingEntity.getYRot();
        float f4 = pLivingEntity.getXRot();
        float f5 = pLivingEntity.yHeadRotO;
        float f6 = pLivingEntity.yHeadRot;
        pLivingEntity.yBodyRot = 180F + 35.0F;
        pLivingEntity.setYRot(180F);
        pLivingEntity.setXRot(-10.0F);
        pLivingEntity.yHeadRot = 180F + 20F;
        pLivingEntity.yHeadRotO = 180F - 20F;
        Lighting.setupForEntityInInventory();
        EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        quaternion1.conj();
        entityrenderdispatcher.overrideCameraOrientation(quaternion1);
        entityrenderdispatcher.setRenderShadow(false);
        MultiBufferSource.BufferSource multibuffersource$buffersource = Minecraft.getInstance().renderBuffers().bufferSource();
        RenderSystem.runAsFancy(() -> entityrenderdispatcher.render(pLivingEntity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, posestack1, multibuffersource$buffersource, 15728880));
        multibuffersource$buffersource.endBatch();
        entityrenderdispatcher.setRenderShadow(true);
        pLivingEntity.yBodyRot = f2;
        pLivingEntity.setYRot(f3);
        pLivingEntity.setXRot(f4);
        pLivingEntity.yHeadRotO = f5;
        pLivingEntity.yHeadRot = f6;
        posestack.popPose();
        RenderSystem.applyModelViewMatrix();
        Lighting.setupFor3DItems();
    }
}
