package com.kangalia.projectdinosaur.client.gui;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.blockentity.IncubatorBlockEntity;
import com.kangalia.projectdinosaur.common.container.IncubatorContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class IncubatorScreen extends AbstractContainerScreen<IncubatorContainer> {

    IncubatorBlockEntity tileEntity;
    private final ResourceLocation GUI = new ResourceLocation(ProjectDinosaur.MODID, "textures/gui/incubator.png");

    public IncubatorScreen(IncubatorContainer screenContainer, Inventory inv, Component titleIn) {
        super (screenContainer, inv, titleIn);
        tileEntity = (IncubatorBlockEntity) screenContainer.tileEntity;
    }

    @Override
    public void render(GuiGraphics stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics stack, float partialTicks, int x, int y) {
        RenderSystem.setShaderTexture(0, GUI);
        int i = this.getGuiLeft();
        int j = this.getGuiTop();
        stack.blit(GUI, i, j, 0, 0, this.imageWidth, this.imageHeight);

        //Progress Bar
        int maxUnitFill = 6000;
        int pbLength = 15;
        int pbHeight = 45;

        int progress = menu.getProgressFromTile();
        int fillHeight = Math.round(progress * pbHeight / maxUnitFill);

        stack.blit(GUI,i+64, j+65-fillHeight, 176, 44-fillHeight, pbLength, fillHeight);

    }
}
