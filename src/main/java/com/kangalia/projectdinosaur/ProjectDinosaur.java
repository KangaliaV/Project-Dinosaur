package com.kangalia.projectdinosaur;

import com.kangalia.projectdinosaur.common.worldgen.features.ConfiguredFeatures;
import com.kangalia.projectdinosaur.common.worldgen.features.PlacedFeatures;
import com.kangalia.projectdinosaur.core.init.*;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;

@Mod(ProjectDinosaur.MODID)
@Mod.EventBusSubscriber(modid = ProjectDinosaur.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ProjectDinosaur {

    public static final String MODID = "projectdinosaur";

    public static final Logger LOGGER = LogManager.getLogger();

    public ProjectDinosaur() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        BlockEntitiesInit.TILE_ENTITIES.register(bus);
        ContainerInit.CONTAINERS.register(bus);
        EntityInit.ENTITY_TYPES.register(bus);
        RecipeInit.register(bus);

        ConfiguredFeatures.CONFIGURED_FEATURES.register(bus);
        PlacedFeatures.PLACED_FEATURES.register(bus);

        MinecraftForge.EVENT_BUS.register(this);

        bus.addListener(this::init);
        GeckoLib.initialize();
    }

    private void init(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            WoodType.register(WoodTypesInit.PETRIFIED);
            //OreGen.registerConfiguredFeatures();
        });
    }
}
