package com.kangalia.projectdinosaur.core.util;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.client.gui.*;
import com.kangalia.projectdinosaur.common.block.render.EmbryonicWombRenderer;
import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.kangalia.projectdinosaur.common.entity.ThrownRottenEgg;
import com.kangalia.projectdinosaur.common.entity.parts.PrehistoricPart;
import com.kangalia.projectdinosaur.common.entity.render.*;
import com.kangalia.projectdinosaur.core.init.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.awt.image.renderable.RenderedImageFactory;

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
        MenuScreens.register(ContainerInit.GENOME_SCANNER_CONTAINER.get(), GenomeScannerScreen::new);

        Sheets.addWoodType(BlockSetTypesInit.PETRIFIED_WOOD_TYPE);

        BlockEntityRenderers.register(BlockEntitiesInit.PETRIFIED_SIGN_ENTITY.get(), SignRenderer::new);
        EntityRenderers.register(EntityInit.PETRIFIED_BOAT.get(), context -> new PetrifiedBoatRenderer(context, false));
        EntityRenderers.register(EntityInit.PETRIFIED_CHEST_BOAT.get(), context -> new PetrifiedBoatRenderer(context, true));
        EntityRenderers.register(EntityInit.THROWN_ROTTEN_EGG.get(), ThrownItemRenderer::new);

        EntityRenderers.register(EntityInit.APHANERAMMA.get(), AphanerammaRenderer::new);
        EntityRenderers.register(EntityInit.AUSTRALOVENATOR.get(), AustralovenatorRenderer::new);
        EntityRenderers.register(EntityInit.GASTORNIS.get(), GastornisRenderer::new);
        EntityRenderers.register(EntityInit.SCELIDOSAURUS.get(), ScelidosaurusRenderer::new);
        EntityRenderers.register(EntityInit.TRILOBITE.get(), TrilobiteRenderer::new);

        BlockEntityRenderers.register(BlockEntitiesInit.EMBRYONIC_WOMB_ENTITY.get(), EmbryonicWombRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(ProjectDinosaur.MODID, "boat/petrified"), "main"), BoatModel::createBodyModel);
        event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(ProjectDinosaur.MODID, "chest_boat/petrified"), "main"), BoatModel::createBodyModel);
    }
}
