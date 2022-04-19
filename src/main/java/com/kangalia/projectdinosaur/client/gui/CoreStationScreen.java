package com.kangalia.projectdinosaur.client.gui;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.blockentity.CoreStationBlockEntity;
import com.kangalia.projectdinosaur.common.blockentity.FossilExcavatorBlockEntity;
import com.kangalia.projectdinosaur.common.container.CoreStationContainer;
import com.kangalia.projectdinosaur.common.container.FossilExcavatorContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CoreStationScreen extends AbstractContainerScreen<CoreStationContainer> {

    CoreStationBlockEntity tileEntity;
    private final ResourceLocation GUI = new ResourceLocation(ProjectDinosaur.MODID, "textures/gui/core_station.png");

    public CoreStationScreen(CoreStationContainer screenContainer, Inventory inv, Component titleIn) {
        super (screenContainer, inv, titleIn);
        tileEntity = (CoreStationBlockEntity) screenContainer.tileEntity;
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(PoseStack stack, float partialTicks, int x, int y) {
        RenderSystem.setShaderTexture(0, GUI);
        int i = this.getGuiLeft();
        int j = this.getGuiTop();
        this.blit(stack, i, j, 0, 0, this.imageWidth, this.imageHeight);

        //Progress Bar
        int maxUnitFill = 100;
        int pbLength = 24;
        int pbHeight = 17;

        int progress = menu.getProgressFromTile();
        int onePixelAmount = Math.round(maxUnitFill / pbLength);
        int fillLength = Math.round(progress / onePixelAmount);

        this.blit(stack,i+76, j+52, 176, 0, fillLength, pbHeight);

    }
}
