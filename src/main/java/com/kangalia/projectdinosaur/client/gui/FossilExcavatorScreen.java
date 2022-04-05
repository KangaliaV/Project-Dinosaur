package com.kangalia.projectdinosaur.client.gui;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.container.FossilExcavatorContainer;
import com.kangalia.projectdinosaur.common.tileentity.FossilExcavatorTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FossilExcavatorScreen extends ContainerScreen<FossilExcavatorContainer> {

    FossilExcavatorTileEntity tileEntity;
    private final ResourceLocation GUI = new ResourceLocation(ProjectDinosaur.MODID, "textures/gui/fossil_excavator.png");

    public FossilExcavatorScreen(FossilExcavatorContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super (screenContainer, inv, titleIn);
        tileEntity = (FossilExcavatorTileEntity) screenContainer.tileEntity;
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        this.renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(MatrixStack stack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bind(GUI);
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
