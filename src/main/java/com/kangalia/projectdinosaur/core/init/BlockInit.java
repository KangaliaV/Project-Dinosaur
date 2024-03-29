package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.block.StripableRotatedPillarBlock;
import com.kangalia.projectdinosaur.common.block.dinocare.GroundFeederBlock;
import com.kangalia.projectdinosaur.common.block.eggs.*;
import com.kangalia.projectdinosaur.common.block.enrichment.BubbleBlowerBlock;
import com.kangalia.projectdinosaur.common.block.enrichment.GnawingRockBlock;
import com.kangalia.projectdinosaur.common.block.enrichment.ScentDiffuserBlock;
import com.kangalia.projectdinosaur.common.block.enrichment.ScratchingLogBlock;
import com.kangalia.projectdinosaur.common.block.machines.*;
import com.kangalia.projectdinosaur.common.block.signs.PetrifiedSignStandingBlock;
import com.kangalia.projectdinosaur.common.block.signs.PetrifiedSignWallBlock;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockInit {
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ProjectDinosaur.MODID);

    //Petrified Wood
    public static final RegistryObject<Block> PETRIFIED_LOG = registerBlock("petrified_log", () -> new StripableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> STRIPPED_PETRIFIED_LOG = registerBlock("stripped_petrified_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<Block> PETRIFIED_WOOD = registerBlock("petrified_wood", () -> new StripableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<Block> STRIPPED_PETRIFIED_WOOD = registerBlock("stripped_petrified_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> PETRIFIED_PLANKS = registerBlock("petrified_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> PETRIFIED_SLAB = registerBlock("petrified_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));
    public static final RegistryObject<Block> PETRIFIED_STAIRS = registerBlock("petrified_stairs", () -> new StairBlock(() -> PETRIFIED_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)));
    public static final RegistryObject<Block> PETRIFIED_FENCE = registerBlock("petrified_fence", () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));
    public static final RegistryObject<Block> PETRIFIED_FENCE_GATE = registerBlock("petrified_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE), SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN));
    public static final RegistryObject<Block> PETRIFIED_BUTTON = registerBlock("petrified_button", () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.BIRCH_BUTTON).noCollission().strength(0.5F).sound(SoundType.WOOD), BlockSetTypesInit.PETRIFIED_TYPE, 30, true));
    public static final RegistryObject<Block> PETRIFIED_PRESSURE_PLATE = registerBlock("petrified_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE), BlockSetTypesInit.PETRIFIED_TYPE));
    public static final RegistryObject<Block> PETRIFIED_DOOR = registerBlock("petrified_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR), BlockSetTypesInit.PETRIFIED_TYPE));
    public static final RegistryObject<Block> PETRIFIED_TRAPDOOR = registerBlock("petrified_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR), BlockSetTypesInit.PETRIFIED_TYPE));
    public static final RegistryObject<Block> PETRIFIED_SIGN = BLOCKS.register("petrified_sign", () -> new PetrifiedSignStandingBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), BlockSetTypesInit.PETRIFIED_WOOD_TYPE));
    public static final RegistryObject<Block> PETRIFIED_SIGN_WALL = BLOCKS.register("petrified_sign_wall", () -> new PetrifiedSignWallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), BlockSetTypesInit.PETRIFIED_WOOD_TYPE));

    //Raw Fossil Ores
    public static final RegistryObject<Block> QUATERNARY_FOSSIL = registerBlock("quaternary_fossil", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(3.0f, 3.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> NEOGENE_FOSSIL = registerBlock("neogene_fossil", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(3.0f, 3.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PALEOGENE_FOSSIL = registerBlock("paleogene_fossil", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(3.0f, 3.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CRETACEOUS_FOSSIL = registerBlock("cretaceous_fossil", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(3.0f, 3.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> JURASSIC_FOSSIL = registerBlock("jurassic_fossil", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(3.0f, 3.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TRIASSIC_FOSSIL = registerBlock("triassic_fossil", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(3.0f, 3.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PERMIAN_FOSSIL = registerBlock("permian_fossil", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(3.0f, 3.0f).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CARBONIFEROUS_FOSSIL = registerBlock("carboniferous_fossil", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(3.0f, 3.0f).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DEVONIAN_FOSSIL = registerBlock("devonian_fossil", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(3.0f, 3.0f).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SILURIAN_FOSSIL = registerBlock("silurian_fossil", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(3.0f, 3.0f).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ORDOVICIAN_FOSSIL = registerBlock("ordovician_fossil", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(3.0f, 3.0f).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CAMBRIAN_FOSSIL = registerBlock("cambrian_fossil", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(3.0f, 3.0f).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));

    //Encased Fossil Ores
    public static final RegistryObject<Block> QUATERNARY_FOSSIL_ENCASED = registerBlock("quaternary_fossil_encased", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(3.0f, 3.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> NEOGENE_FOSSIL_ENCASED = registerBlock("neogene_fossil_encased", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(3.0f, 3.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PALEOGENE_FOSSIL_ENCASED = registerBlock("paleogene_fossil_encased", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(3.0f, 3.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CRETACEOUS_FOSSIL_ENCASED = registerBlock("cretaceous_fossil_encased", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(3.0f, 3.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> JURASSIC_FOSSIL_ENCASED = registerBlock("jurassic_fossil_encased", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(3.0f, 3.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TRIASSIC_FOSSIL_ENCASED = registerBlock("triassic_fossil_encased", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(3.0f, 3.0f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PERMIAN_FOSSIL_ENCASED = registerBlock("permian_fossil_encased", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(3.0f, 3.0f).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CARBONIFEROUS_FOSSIL_ENCASED = registerBlock("carboniferous_fossil_encased", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(3.0f, 3.0f).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DEVONIAN_FOSSIL_ENCASED = registerBlock("devonian_fossil_encased", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(3.0f, 3.0f).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SILURIAN_FOSSIL_ENCASED = registerBlock("silurian_fossil_encased", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(3.0f, 3.0f).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ORDOVICIAN_FOSSIL_ENCASED = registerBlock("ordovician_fossil_encased", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(3.0f, 3.0f).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CAMBRIAN_FOSSIL_ENCASED = registerBlock("cambrian_fossil_encased", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(3.0f, 3.0f).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops()));

    //Machine Blocks
    public static final RegistryObject<Block> FOSSIL_EXCAVATOR = registerBlock("fossil_excavator", () -> new FossilExcavatorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0f, 6.0f).sound(SoundType.METAL).lightLevel(state -> state.getValue(BlockStateProperties.POWERED) ? 14 : 0).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CORE_STATION = registerBlock("core_station", () -> new CoreStationBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0f, 6.0f).sound(SoundType.METAL).lightLevel(state -> state.getValue(BlockStateProperties.POWERED) ? 14 : 0).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DNA_RECOMBINATOR = registerBlock("dna_recombinator", () -> new DNARecombinatorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0f, 6.0f).sound(SoundType.METAL).lightLevel(state -> state.getValue(BlockStateProperties.POWERED) ? 14 : 0).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> INCUBATOR = registerBlock("incubator", () -> new IncubatorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0f, 6.0f).sound(SoundType.METAL).lightLevel(state -> state.getValue(BlockStateProperties.POWERED) ? 14 : 0).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> EMBRYONIC_WOMB = registerBlockWithoutItem("embryonic_womb", () -> new EmbryonicWombBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0f, 6.0f).sound(SoundType.METAL).lightLevel(state -> state.getValue(BlockStateProperties.POWERED) ? 14 : 0).requiresCorrectToolForDrops().noOcclusion()));

    //Dino Husbandry Blocks
    public static final RegistryObject<Block> GROUND_FEEDER = registerBlock("ground_feeder", () -> new GroundFeederBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0f, 6.0f).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<Block> NEST = registerBlock("nest", () -> new Block(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK).strength(0.5F).sound(SoundType.GRASS)));

    //Enrichment Blocks
    public static final RegistryObject<Block> BUBBLE_BLOWER = registerBlock("bubble_blower", () -> new BubbleBlowerBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(2.0F).sound(SoundType.METAL).noOcclusion()));
    public static final RegistryObject<Block> GNAWING_ROCK_STONE = registerBlock("gnawing_rock_stone", () -> new GnawingRockBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2.0F).sound(SoundType.STONE).noOcclusion()));
    public static final RegistryObject<Block> GNAWING_ROCK_GRANITE = registerBlock("gnawing_rock_granite", () -> new GnawingRockBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2.0F).sound(SoundType.STONE).noOcclusion()));
    public static final RegistryObject<Block> GNAWING_ROCK_ANDESITE = registerBlock("gnawing_rock_andesite", () -> new GnawingRockBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2.0F).sound(SoundType.STONE).noOcclusion()));
    public static final RegistryObject<Block> GNAWING_ROCK_DIORITE = registerBlock("gnawing_rock_diorite", () -> new GnawingRockBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2.0F).sound(SoundType.STONE).noOcclusion()));
    public static final RegistryObject<Block> GNAWING_ROCK_BLACKSTONE = registerBlock("gnawing_rock_blackstone", () -> new GnawingRockBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2.0F).sound(SoundType.STONE).noOcclusion()));
    public static final RegistryObject<Block> GNAWING_ROCK_CALCITE = registerBlock("gnawing_rock_calcite", () -> new GnawingRockBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2.0F).sound(SoundType.STONE).noOcclusion()));
    public static final RegistryObject<Block> GNAWING_ROCK_COBBLED_DEEPSLATE = registerBlock("gnawing_rock_cobbled_deepslate", () -> new GnawingRockBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2.0F).sound(SoundType.STONE).noOcclusion()));
    public static final RegistryObject<Block> GNAWING_ROCK_COBBLESTONE = registerBlock("gnawing_rock_cobblestone", () -> new GnawingRockBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2.0F).sound(SoundType.STONE).noOcclusion()));
    public static final RegistryObject<Block> GNAWING_ROCK_SANDSTONE = registerBlock("gnawing_rock_sandstone", () -> new GnawingRockBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2.0F).sound(SoundType.STONE).noOcclusion()));
    public static final RegistryObject<Block> GNAWING_ROCK_RED_SANDSTONE = registerBlock("gnawing_rock_red_sandstone", () -> new GnawingRockBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2.0F).sound(SoundType.STONE).noOcclusion()));
    public static final RegistryObject<Block> GNAWING_ROCK_TUFF = registerBlock("gnawing_rock_tuff", () -> new GnawingRockBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2.0F).sound(SoundType.STONE).noOcclusion()));
    public static final RegistryObject<Block> SCENT_DIFFUSER_CARNIVORE = registerBlock("scent_diffuser_carnivore", () -> new ScentDiffuserBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2.0F).sound(SoundType.METAL).noOcclusion()));
    public static final RegistryObject<Block> SCRATCHING_LOG_OAK = registerBlock("scratching_log_oak", () -> new ScratchingLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE).strength(2.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> SCRATCHING_LOG_SPRUCE = registerBlock("scratching_log_spruce", () -> new ScratchingLogBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_FENCE).strength(2.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> SCRATCHING_LOG_BIRCH = registerBlock("scratching_log_birch", () -> new ScratchingLogBlock(BlockBehaviour.Properties.copy(Blocks.BIRCH_FENCE).strength(2.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> SCRATCHING_LOG_JUNGLE = registerBlock("scratching_log_jungle", () -> new ScratchingLogBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_FENCE).strength(2.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> SCRATCHING_LOG_ACACIA = registerBlock("scratching_log_acacia", () -> new ScratchingLogBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_FENCE).strength(2.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> SCRATCHING_LOG_DARKOAK = registerBlock("scratching_log_darkoak", () -> new ScratchingLogBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_FENCE).strength(2.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> SCRATCHING_LOG_CRIMSON = registerBlock("scratching_log_crimson", () -> new ScratchingLogBlock(BlockBehaviour.Properties.copy(Blocks.CRIMSON_FENCE).strength(2.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> SCRATCHING_LOG_WARPED = registerBlock("scratching_log_warped", () -> new ScratchingLogBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_FENCE).strength(2.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> SCRATCHING_LOG_MANGROVE = registerBlock("scratching_log_mangrove", () -> new ScratchingLogBlock(BlockBehaviour.Properties.copy(Blocks.MANGROVE_FENCE).strength(2.0F).sound(SoundType.WOOD).noOcclusion()));

    //Incubated Eggs
    public static final RegistryObject<Block> AUSTRALOVENATOR_EGG_INCUBATED = registerEggBlock("australovenator_egg_incubated", () -> new AustralovenatorEggBlock(BlockBehaviour.Properties.copy(Blocks.TURTLE_EGG).strength(0.5F).sound(SoundType.METAL).randomTicks().noOcclusion(), EntityInit.AUSTRALOVENATOR, 3));
    public static final RegistryObject<Block> GASTORNIS_EGG_INCUBATED = registerEggBlock("gastornis_egg_incubated", () -> new GastornisEggBlock(BlockBehaviour.Properties.copy(Blocks.TURTLE_EGG).strength(0.5F).sound(SoundType.METAL).randomTicks().noOcclusion(), EntityInit.GASTORNIS, 4));
    public static final RegistryObject<Block> GORGONOPS_EGG_INCUBATED = registerEggBlock("gorgonops_egg_incubated", () -> new Block(BlockBehaviour.Properties.copy(Blocks.TURTLE_EGG).strength(0.5F).sound(SoundType.METAL).randomTicks().noOcclusion()));
    public static final RegistryObject<Block> SCELIDOSAURUS_EGG_INCUBATED = registerEggBlock("scelidosaurus_egg_incubated", () -> new ScelidosaurusEggBlock(BlockBehaviour.Properties.copy(Blocks.TURTLE_EGG).strength(0.5F).sound(SoundType.METAL).randomTicks().noOcclusion(), EntityInit.SCELIDOSAURUS, 4));

    //Spawn
    public static final RegistryObject<Block> APHANERAMMA_SPAWN = registerWaterSpawnBlock("aphaneramma_spawn", () -> new AphanerammaSpawnBlock(BlockBehaviour.Properties.copy(Blocks.FROGSPAWN).instabreak().noOcclusion().noCollission().sound(SoundType.FROGSPAWN), EntityInit.APHANERAMMA));
    public static final RegistryObject<Block> ARTHROPLEURA_SPAWN = registerEggBlock("arthropleura_spawn", () -> new Block(BlockBehaviour.Properties.copy(Blocks.FROGSPAWN).strength(0.5F).sound(SoundType.METAL).randomTicks().noOcclusion()));
    public static final RegistryObject<Block> GUIYU_SPAWN = registerEggBlock("guiyu_spawn", () -> new Block(BlockBehaviour.Properties.copy(Blocks.FROGSPAWN).strength(0.5F).sound(SoundType.METAL).randomTicks().noOcclusion()));
    public static final RegistryObject<Block> MEGALOGRAPTUS_SPAWN = registerEggBlock("megalograptus_spawn", () -> new Block(BlockBehaviour.Properties.copy(Blocks.FROGSPAWN).strength(0.5F).sound(SoundType.METAL).randomTicks().noOcclusion()));
    public static final RegistryObject<Block> TIKTAALIK_SPAWN = registerEggBlock("tiktaalik_spawn", () -> new Block(BlockBehaviour.Properties.copy(Blocks.FROGSPAWN).strength(0.5F).sound(SoundType.METAL).randomTicks().noOcclusion()));
    public static final RegistryObject<Block> TRILOBITE_SPAWN = registerEggBlock("trilobite_spawn", () -> new TrilobiteSpawnBlock(BlockBehaviour.Properties.copy(Blocks.FROGSPAWN).instabreak().noOcclusion().noCollission().sound(SoundType.FROGSPAWN), EntityInit.TRILOBITE));

    //Helper Methods
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block>RegistryObject<T> registerEggBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerEggBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerEggBlockItem(String name, RegistryObject<T> block) {
        ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().stacksTo(4)));
    }

    private static <T extends Block>RegistryObject<T> registerWaterSpawnBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<T> registerBlockWithoutItem(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        return toReturn;
    }
}
