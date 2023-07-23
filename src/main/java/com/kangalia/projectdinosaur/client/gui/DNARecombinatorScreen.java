package com.kangalia.projectdinosaur.client.gui;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.blockentity.DNARecombinatorBlockEntity;
import com.kangalia.projectdinosaur.common.container.DNARecombinatorContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class DNARecombinatorScreen extends AbstractContainerScreen<DNARecombinatorContainer> {

    DNARecombinatorBlockEntity tileEntity;
    private final ResourceLocation GUI = new ResourceLocation(ProjectDinosaur.MODID, "textures/gui/dna_recombinator.png");

    public DNARecombinatorScreen(DNARecombinatorContainer screenContainer, Inventory inv, Component titleIn) {
        super (screenContainer, inv, titleIn);
        tileEntity = (DNARecombinatorBlockEntity) screenContainer.tileEntity;
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
        int maxUnitFill = 600;
        int pbLength = 24;
        int pbHeight = 17;

        int progress = menu.getProgressFromTile();
        int onePixelAmount = Math.round(maxUnitFill / pbLength);
        int fillLength = Math.round(progress / onePixelAmount);

        stack.blit(GUI,i+76, j+36, 176, 0, fillLength, pbHeight);

    }
}
