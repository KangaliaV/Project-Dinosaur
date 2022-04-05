package com.kangalia.projectdinosaur.core.util;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.client.gui.FossilExcavatorScreen;
import com.kangalia.projectdinosaur.core.init.BlockInit;
import com.kangalia.projectdinosaur.core.init.ContainerInit;
import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ProjectDinosaur.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ScreenManager.register(ContainerInit.FOSSIL_EXCAVATOR_CONTAINER.get(), FossilExcavatorScreen::new);
        RenderTypeLookup.setRenderLayer(BlockInit.AMBER_BLOCK.get(), RenderType.translucent());
        RenderTypeLookup.setRenderLayer(BlockInit.PETRIFIED_DOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockInit.PETRIFIED_TRAPDOOR.get(), RenderType.cutout());
    }

    @SubscribeEvent
    public static void onRegisterBlocks(final RegistryEvent.Register<Block> event) throws NoSuchFieldException, IllegalAccessException {
        //EventHandler.addStripping();
    }
}
