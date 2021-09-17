package com.kangalia.projectdinosaur;

import com.kangalia.projectdinosaur.core.init.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ProjectDinosaur.MODID)
@Mod.EventBusSubscriber(modid = ProjectDinosaur.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ProjectDinosaur {

    public static final String MODID = "projectdinosaur";

    public static final Logger LOGGER = LogManager.getLogger();

    public ProjectDinosaur() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        TileEntitiesInit.TILE_ENTITIES.register(bus);
        ContainersInit.CONTAINERS.register(bus);

        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, FeatureInit::addOres);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
