package com.kangalia.projectdinosaur.core.util;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.client.gui.*;
import com.kangalia.projectdinosaur.common.block.render.EmbryonicWombRenderer;
import com.kangalia.projectdinosaur.common.entity.render.*;
import com.kangalia.projectdinosaur.core.init.BlockEntitiesInit;
import com.kangalia.projectdinosaur.core.init.ContainerInit;
import com.kangalia.projectdinosaur.core.init.EntityInit;
import com.kangalia.projectdinosaur.core.init.WoodTypesInit;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = ProjectDinosaur.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(ContainerInit.FOSSIL_EXCAVATOR_CONTAINER.get(), FossilExcavatorScreen::new);
        MenuScreens.register(ContainerInit.CORE_STATION_CONTAINER.get(), CoreStationScreen::new);
        MenuScreens.register(ContainerInit.DNA_RECOMBINATOR_CONTAINER.get(), DNARecombinatorScreen::new);
        MenuScreens.register(ContainerInit.INCUBATOR_CONTAINER.get(), IncubatorScreen::new);
        MenuScreens.register(ContainerInit.EMBRYONIC_WOMB_CONTAINER.get(), EmbryonicWombScreen::new);
        MenuScreens.register(ContainerInit.GROUND_FEEDER_CONTAINER.get(), GroundFeederScreen::new);
        MenuScreens.register(ContainerInit.DINO_SCANNER_CONTAINER.get(), DinoScannerScreen::new);

        Sheets.addWoodType(WoodTypesInit.PETRIFIED);

        BlockEntityRenderers.register(BlockEntitiesInit.PETRIFIED_SIGN_ENTITY.get(), SignRenderer::new);
        //EntityRenderers.register(EntityInit.PETRIFIED_BOAT.get(), PetrifiedBoatRenderer::new);

        EntityRenderers.register(EntityInit.APHANERAMMA.get(), AphanerammaRenderer::new);
        EntityRenderers.register(EntityInit.AUSTRALOVENATOR.get(), AustralovenatorRenderer::new);
        EntityRenderers.register(EntityInit.GASTORNIS.get(), GastornisRenderer::new);
        EntityRenderers.register(EntityInit.SCELIDOSAURUS.get(), ScelidosaurusRenderer::new);
        EntityRenderers.register(EntityInit.TRILOBITE.get(), TrilobiteRenderer::new);

        BlockEntityRenderers.register(BlockEntitiesInit.EMBRYONIC_WOMB_ENTITY.get(), EmbryonicWombRenderer::new);


    }
}
