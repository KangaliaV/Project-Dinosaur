package com.kangalia.projectdinosaur.core.util;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.client.gui.FossilExcavatorScreen;
import com.kangalia.projectdinosaur.common.entity.render.AphanerammaRenderer;
import com.kangalia.projectdinosaur.common.entity.render.PetrifiedBoatRenderer;
import com.kangalia.projectdinosaur.core.init.*;
import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import software.bernie.example.client.renderer.entity.ExampleGeoRenderer;
import software.bernie.example.registry.EntityRegistry;

@Mod.EventBusSubscriber(modid = ProjectDinosaur.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ScreenManager.register(ContainerInit.FOSSIL_EXCAVATOR_CONTAINER.get(), FossilExcavatorScreen::new);
        RenderTypeLookup.setRenderLayer(BlockInit.AMBER_BLOCK.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(BlockInit.PETRIFIED_DOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockInit.PETRIFIED_TRAPDOOR.get(), RenderType.cutout());
        Atlases.addWoodType(WoodTypesInit.PETRIFIED);
        ClientRegistry.bindTileEntityRenderer(TileEntitiesInit.PETRIFIED_SIGN_ENTITY.get(), SignTileEntityRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.PETRIFIED_BOAT.get(), PetrifiedBoatRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.APHANERAMMA.get(), AphanerammaRenderer::new);
    }
}
