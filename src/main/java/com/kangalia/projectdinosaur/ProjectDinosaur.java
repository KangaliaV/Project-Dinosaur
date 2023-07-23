package com.kangalia.projectdinosaur;

import com.kangalia.projectdinosaur.core.init.*;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib.GeckoLib;

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
        SoundInit.register(bus);
        CreativeTabInit.register(bus);

        MinecraftForge.EVENT_BUS.register(this);

        bus.addListener(this::init);
        GeckoLib.initialize();
        bus.addListener(this::addCreative);
    }

    private void init(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            BlockSetType.register(BlockSetTypesInit.PETRIFIED_TYPE);
            WoodType.register(BlockSetTypesInit.PETRIFIED_WOOD_TYPE);
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == CreativeTabInit.DINO_BLOCKS.get()) {

            // PETRIFIED WOOD
            event.accept(BlockInit.PETRIFIED_LOG);
            event.accept(BlockInit.STRIPPED_PETRIFIED_LOG);
            event.accept(BlockInit.PETRIFIED_WOOD);
            event.accept(BlockInit.STRIPPED_PETRIFIED_WOOD);
            event.accept(BlockInit.PETRIFIED_PLANKS);
            event.accept(BlockInit.PETRIFIED_SLAB);
            event.accept(BlockInit.PETRIFIED_STAIRS);
            event.accept(BlockInit.PETRIFIED_FENCE);
            event.accept(BlockInit.PETRIFIED_FENCE_GATE);
            event.accept(BlockInit.PETRIFIED_BUTTON);
            event.accept(BlockInit.PETRIFIED_PRESSURE_PLATE);
            event.accept(BlockInit.PETRIFIED_DOOR);
            event.accept(BlockInit.PETRIFIED_TRAPDOOR);
            event.accept(ItemInit.PETRIFIED_SIGN);

            // FOSSILS
            event.accept(BlockInit.QUATERNARY_FOSSIL);
            event.accept(BlockInit.NEOGENE_FOSSIL);
            event.accept(BlockInit.PALEOGENE_FOSSIL);
            event.accept(BlockInit.CRETACEOUS_FOSSIL);
            event.accept(BlockInit.JURASSIC_FOSSIL);
            event.accept(BlockInit.TRIASSIC_FOSSIL);
            event.accept(BlockInit.PERMIAN_FOSSIL);
            event.accept(BlockInit.CARBONIFEROUS_FOSSIL);
            event.accept(BlockInit.DEVONIAN_FOSSIL);
            event.accept(BlockInit.SILURIAN_FOSSIL);
            event.accept(BlockInit.ORDOVICIAN_FOSSIL);
            event.accept(BlockInit.CAMBRIAN_FOSSIL);

            // ENCASED FOSSILS
            event.accept(BlockInit.QUATERNARY_FOSSIL_ENCASED);
            event.accept(BlockInit.NEOGENE_FOSSIL_ENCASED);
            event.accept(BlockInit.PALEOGENE_FOSSIL_ENCASED);
            event.accept(BlockInit.CRETACEOUS_FOSSIL_ENCASED);
            event.accept(BlockInit.JURASSIC_FOSSIL_ENCASED);
            event.accept(BlockInit.TRIASSIC_FOSSIL_ENCASED);
            event.accept(BlockInit.PERMIAN_FOSSIL_ENCASED);
            event.accept(BlockInit.CARBONIFEROUS_FOSSIL_ENCASED);
            event.accept(BlockInit.DEVONIAN_FOSSIL_ENCASED);
            event.accept(BlockInit.SILURIAN_FOSSIL_ENCASED);
            event.accept(BlockInit.ORDOVICIAN_FOSSIL_ENCASED);
            event.accept(BlockInit.CAMBRIAN_FOSSIL_ENCASED);

            // MACHINES
            event.accept(BlockInit.FOSSIL_EXCAVATOR);
            event.accept(BlockInit.CORE_STATION);
            event.accept(BlockInit.DNA_RECOMBINATOR);
            event.accept(BlockInit.INCUBATOR);
            event.accept(ItemInit.EMBRYONIC_WOMB_ITEM);

            // HUSBANDRY
            event.accept(BlockInit.GROUND_FEEDER);
            event.accept(BlockInit.NEST);

            // ENCRICHMENT
            event.accept(BlockInit.BUBBLE_BLOWER);
            event.accept(BlockInit.GNAWING_ROCK_STONE);
            event.accept(BlockInit.GNAWING_ROCK_GRANITE);
            event.accept(BlockInit.GNAWING_ROCK_ANDESITE);
            event.accept(BlockInit.GNAWING_ROCK_DIORITE);
            event.accept(BlockInit.GNAWING_ROCK_BLACKSTONE);
            event.accept(BlockInit.GNAWING_ROCK_CALCITE);
            event.accept(BlockInit.GNAWING_ROCK_COBBLED_DEEPSLATE);
            event.accept(BlockInit.GNAWING_ROCK_COBBLESTONE);
            event.accept(BlockInit.GNAWING_ROCK_SANDSTONE);
            event.accept(BlockInit.GNAWING_ROCK_RED_SANDSTONE);
            event.accept(BlockInit.GNAWING_ROCK_TUFF);
            event.accept(BlockInit.SCENT_DIFFUSER_CARNIVORE);
            event.accept(BlockInit.SCRATCHING_LOG_OAK);
            event.accept(BlockInit.SCRATCHING_LOG_SPRUCE);
            event.accept(BlockInit.SCRATCHING_LOG_BIRCH);
            event.accept(BlockInit.SCRATCHING_LOG_JUNGLE);
            event.accept(BlockInit.SCRATCHING_LOG_ACACIA);
            event.accept(BlockInit.SCRATCHING_LOG_DARKOAK);
            event.accept(BlockInit.SCRATCHING_LOG_CRIMSON);
            event.accept(BlockInit.SCRATCHING_LOG_WARPED);
            event.accept(BlockInit.SCRATCHING_LOG_MANGROVE);



        } else if (event.getTab() == CreativeTabInit.DINO_ITEMS.get()) {

            // TOOLS
            event.accept(ItemInit.IRON_CHISEL);
            event.accept(ItemInit.DIAMOND_CHISEL);
            event.accept(ItemInit.NETHERITE_CHISEL);

            // FRAGMENTS
            event.accept(ItemInit.QUATERNARY_FOSSIL_FRAGMENT);
            event.accept(ItemInit.NEOGENE_FOSSIL_FRAGMENT);
            event.accept(ItemInit.PALEOGENE_FOSSIL_FRAGMENT);
            event.accept(ItemInit.CRETACEOUS_FOSSIL_FRAGMENT);
            event.accept(ItemInit.JURASSIC_FOSSIL_FRAGMENT);
            event.accept(ItemInit.TRIASSIC_FOSSIL_FRAGMENT);
            event.accept(ItemInit.PERMIAN_FOSSIL_FRAGMENT);
            event.accept(ItemInit.CARBONIFEROUS_FOSSIL_FRAGMENT);
            event.accept(ItemInit.DEVONIAN_FOSSIL_FRAGMENT);
            event.accept(ItemInit.SILURIAN_FOSSIL_FRAGMENT);
            event.accept(ItemInit.ORDOVICIAN_FOSSIL_FRAGMENT);
            event.accept(ItemInit.CAMBRIAN_FOSSIL_FRAGMENT);

            // SPECIMENS
            event.accept(ItemInit.QUATERNARY_FOSSIL_SPECIMEN);
            event.accept(ItemInit.NEOGENE_FOSSIL_SPECIMEN);
            event.accept(ItemInit.PALEOGENE_FOSSIL_SPECIMEN);
            event.accept(ItemInit.CRETACEOUS_FOSSIL_SPECIMEN);
            event.accept(ItemInit.JURASSIC_FOSSIL_SPECIMEN);
            event.accept(ItemInit.TRIASSIC_FOSSIL_SPECIMEN);
            event.accept(ItemInit.PERMIAN_FOSSIL_SPECIMEN);
            event.accept(ItemInit.CARBONIFEROUS_FOSSIL_SPECIMEN);
            event.accept(ItemInit.DEVONIAN_FOSSIL_SPECIMEN);
            event.accept(ItemInit.SILURIAN_FOSSIL_SPECIMEN);
            event.accept(ItemInit.ORDOVICIAN_FOSSIL_SPECIMEN);
            event.accept(ItemInit.CAMBRIAN_FOSSIL_SPECIMEN);

            // MISCELLANEOUS
            event.accept(ItemInit.ROTTEN_EGG);
            event.accept(ItemInit.SYRINGE);
            event.accept(ItemInit.NUTRIENT_GOO);

            // INTERACTIVE
            event.accept(ItemInit.PLASTER);
            event.accept(ItemInit.GROWTH_ACCELERATING_HORMONE);
            event.accept(ItemInit.GROWTH_STUNTING_HORMONE);
            event.accept(ItemInit.DINO_SCANNER);
            event.accept(ItemInit.CRYOPORTER);
            event.accept(ItemInit.GENOME_SCANNER);

        } else if (event.getTab() == CreativeTabInit.DINO_CREATURES.get()) {

            // DNA
            event.accept(ItemInit.APHANERAMMA_DNA);
            event.accept(ItemInit.ARTHROPLEURA_DNA);
            event.accept(ItemInit.AUSTRALOVENATOR_DNA);
            event.accept(ItemInit.DIRE_WOLF_DNA);
            event.accept(ItemInit.GUIYU_DNA);
            event.accept(ItemInit.GASTORNIS_DNA);
            event.accept(ItemInit.GORGONOPS_DNA);
            event.accept(ItemInit.MEGALODON_DNA);
            event.accept(ItemInit.MEGALOGRAPTUS_DNA);
            event.accept(ItemInit.SCELIDOSAURUS_DNA);
            event.accept(ItemInit.TIKTAALIK_DNA);
            event.accept(ItemInit.TRILOBITE_DNA);

            // FERTILISED EGGS
            event.accept(ItemInit.AUSTRALOVENATOR_EGG_FERTILISED);
            event.accept(ItemInit.GASTORNIS_EGG_FERTILISED);
            event.accept(ItemInit.GORGONOPS_EGG_FERTILISED);
            event.accept(ItemInit.SCELIDOSAURUS_EGG_FERTILISED);

            // SPAWN
            event.accept(ItemInit.APHANERAMMA_SPAWN_ITEM);
            event.accept(ItemInit.ARTHROPLEURA_SPAWN_ITEM);
            event.accept(ItemInit.GUIYU_SPAWN_ITEM);
            event.accept(ItemInit.MEGALOGRAPTUS_SPAWN_ITEM);
            event.accept(ItemInit.TIKTAALIK_SPAWN_ITEM);
            event.accept(ItemInit.TRILOBITE_SPAWN_ITEM);

            // EMBRYOS
            event.accept(ItemInit.DIRE_WOLF_EMBYRO);
            event.accept(ItemInit.MEGALODON_EMBRYO);

            // FOETUSES
            event.accept(ItemInit.DIRE_WOLF_FOETUS);
            event.accept(ItemInit.MEGALODON_FOETUS);

            // EGGS
            event.accept(BlockInit.AUSTRALOVENATOR_EGG_INCUBATED);
            event.accept(BlockInit.GASTORNIS_EGG_INCUBATED);
            event.accept(BlockInit.GORGONOPS_EGG_INCUBATED);
            event.accept(BlockInit.SCELIDOSAURUS_EGG_INCUBATED);

        } else if (event.getTab() == CreativeTabInit.DINO_SPAWN_EGGS.get()) {
            event.accept(ItemInit.APHANERAMMA_SPAWN_EGG);
            event.accept(ItemInit.ATHROPLEURA_SPAWN_EGG);
            event.accept(ItemInit.AUSTRALOVENATOR_SPAWN_EGG);
            event.accept(ItemInit.GUIYU_SPAWN_EGG);
            event.accept(ItemInit.DIRE_WOLF_SPAWN_EGG);
            event.accept(ItemInit.SCELIDOSAURUS_SPAWN_EGG);
            event.accept(ItemInit.GASTORNIS_SPAWN_EGG);
            event.accept(ItemInit.GORGONOPS_SPAWN_EGG);
            event.accept(ItemInit.MEGALODON_SPAWN_EGG);
            event.accept(ItemInit.MEGALOGRAPTUS_SPAWN_EGG);
            event.accept(ItemInit.TIKTAALIK_SPAWN_EGG);
            event.accept(ItemInit.TRILOBITE_SPAWN_EGG);
        }
    }
}
