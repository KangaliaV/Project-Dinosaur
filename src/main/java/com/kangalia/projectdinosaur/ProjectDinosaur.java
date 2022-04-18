package com.kangalia.projectdinosaur;

import com.kangalia.projectdinosaur.common.worldgen.ores.OreGen;
import com.kangalia.projectdinosaur.core.data.recipes.ExcavatingRecipe;
import com.kangalia.projectdinosaur.core.init.*;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
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
        BlockEntitiesInit.TILE_ENTITIES.register(bus);
        ContainerInit.CONTAINERS.register(bus);
        EntityInit.ENTITY_TYPES.register(bus);
        RecipeInit.register(bus);

        MinecraftForge.EVENT_BUS.addListener(OreGen::onBiomeLoadingEvent);
        MinecraftForge.EVENT_BUS.register(this);

        bus.addListener(this::init);
    }

    private void init(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            WoodType.register(WoodTypesInit.PETRIFIED);
            OreGen.registerConfiguredFeatures();
        });
    }

    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, ExcavatingRecipe.ExcavatingRecipeType.ID, ExcavatingRecipe.ExcavatingRecipeType.INSTANCE);
    }
}
