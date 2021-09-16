package com.kangalia.projectdinosaur.client.gui;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.container.FossilExcavatorContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FossilExcavatorScreen extends ContainerScreen<FossilExcavatorContainer> {

    private static final ResourceLocation FOSSIL_EXCAVATOR_GUI = new ResourceLocation(ProjectDinosaur.MODID, "textures/gui/fossil_excavator");
    PlayerInventory playerInv;
    ITextComponent title;

    public FossilExcavatorScreen(FossilExcavatorContainer screenContainer, PlayerInventory inv, ITextComponent title) {
        super(screenContainer, inv, title);
        playerInv = inv;
        this.title = title;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        this.font.draw(matrixStack, this.inventory.getDisplayName(), this.inventoryLabelX, this.inventoryLabelY, 4210752);
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.textureManager.bind(FOSSIL_EXCAVATOR_GUI);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    protected void renderLabels(MatrixStack matrixStack, int mouseX, int mouseY) {
        this.minecraft.font.draw(matrixStack, this.inventory.getDisplayName(), 7, this.getYSize() -93, 4210752);
        this.minecraft.font.draw(matrixStack, title, 7 + this.getXSize() / 2 - this.minecraft.font.width(title.getString()) / 2, 6, 4210752 );

        int actualMouseX = mouseX - ((this.width - this.getXSize()) / 2);
        int actualMouseY = mouseY - ((this.height - this.getYSize()) / 2);

        this.addTooltips(matrix, actualMouseX, actualMouseY);


    }
}
