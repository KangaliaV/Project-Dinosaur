package com.kangalia.projectdinosaur.client.gui;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.blockentity.GroundFeederBlockEntity;
import com.kangalia.projectdinosaur.common.container.GroundFeederContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class GroundFeederScreen extends AbstractContainerScreen<GroundFeederContainer> {
    GroundFeederBlockEntity tileEntity;
    private final ResourceLocation GUI = new ResourceLocation(ProjectDinosaur.MODID, "textures/gui/ground_feeder.png");

    public GroundFeederScreen(GroundFeederContainer screenContainer, Inventory inv, Component titleIn) {
        super (screenContainer, inv, titleIn);
        tileEntity = (GroundFeederBlockEntity) screenContainer.tileEntity;
    }

    @Override
    public void render(@NotNull GuiGraphics stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics stack, float partialTicks, int x, int y) {
        RenderSystem.setShaderTexture(0, GUI);
        int i = this.getGuiLeft();
        int j = this.getGuiTop();
        stack.blit(GUI, i, j, 0, 0, this.imageWidth, this.imageHeight);

        //Progress Bars
        int maxUnitFill = 640;
        int pbHeight = 28;

        int herbi = menu.getHerbiFromTile();
        int carni = menu.getCarniFromTile();
        int pisci = menu.getPisciFromTile();

        int herbiFillHeight = Math.round(herbi * pbHeight / maxUnitFill);
        int carniFillHeight = Math.round(carni * pbHeight / maxUnitFill);
        int pisciFillHeight = Math.round(pisci * pbHeight / maxUnitFill);

        stack.blit(GUI,i+72, j+66-herbiFillHeight, 176, 32-herbiFillHeight, 6, herbiFillHeight);
        stack.blit(GUI,i+85, j+66-carniFillHeight, 176, 32-carniFillHeight, 6, carniFillHeight);
        stack.blit(GUI,i+98, j+66-pisciFillHeight, 176, 32-pisciFillHeight, 6, pisciFillHeight);

    }
}
