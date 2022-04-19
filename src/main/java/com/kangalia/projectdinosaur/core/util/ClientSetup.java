package com.kangalia.projectdinosaur.core.util;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.client.gui.FossilExcavatorScreen;
import com.kangalia.projectdinosaur.common.entity.render.AphanerammaRenderer;
import com.kangalia.projectdinosaur.common.entity.render.PetrifiedBoatRenderer;
import com.kangalia.projectdinosaur.core.init.*;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Block;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ProjectDinosaur.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(ContainerInit.FOSSIL_EXCAVATOR_CONTAINER.get(), FossilExcavatorScreen::new);
        ItemBlockRenderTypes.setRenderLayer(BlockInit.AMBER_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BlockInit.PETRIFIED_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInit.PETRIFIED_TRAPDOOR.get(), RenderType.cutout());
        Sheets.addWoodType(WoodTypesInit.PETRIFIED);
        BlockEntityRenderers.register(BlockEntitiesInit.PETRIFIED_SIGN_ENTITY.get(), SignRenderer::new);
        EntityRenderers.register(EntityInit.PETRIFIED_BOAT.get(), PetrifiedBoatRenderer::new);
        EntityRenderers.register(EntityInit.APHANERAMMA.get(), AphanerammaRenderer::new);
    }
}
