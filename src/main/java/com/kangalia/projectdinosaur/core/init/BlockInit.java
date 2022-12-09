package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.block.*;
import com.kangalia.projectdinosaur.common.block.dinocare.GroundFeederBlock;
import com.kangalia.projectdinosaur.common.block.eggs.*;
import com.kangalia.projectdinosaur.common.block.enrichment.*;
import com.kangalia.projectdinosaur.common.block.machines.*;
import com.kangalia.projectdinosaur.common.block.signs.PetrifiedSignStandingBlock;
import com.kangalia.projectdinosaur.common.block.signs.PetrifiedSignWallBlock;
import com.kangalia.projectdinosaur.core.itemgroup.DinoBlocks;
import com.kangalia.projectdinosaur.core.itemgroup.DinoCreatures;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.RegistryObject;

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
    public static final RegistryObject<Block> PETRIFIED_FENCE_GATE = registerBlock("petrified_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE)));
    public static final RegistryObject<Block> PETRIFIED_BUTTON = registerBlock("petrified_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON)));
    public static final RegistryObject<Block> PETRIFIED_PRESSURE_PLATE = registerBlock("petrified_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE)));
    public static final RegistryObject<Block> PETRIFIED_DOOR = registerBlock("petrified_door", () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR)));
    public static final RegistryObject<Block> PETRIFIED_TRAPDOOR = registerBlock("petrified_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR)));
    public static final RegistryObject<Block> PETRIFIED_SIGN = BLOCKS.register("petrified_sign", () -> new PetrifiedSignStandingBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), WoodTypesInit.PETRIFIED));
    public static final RegistryObject<Block> PETRIFIED_SIGN_WALL = BLOCKS.register("petrified_sign_wall", () -> new PetrifiedSignWallBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), WoodTypesInit.PETRIFIED));


    //Rock Fossil Ores
    public static final RegistryObject<Block> ALPINE_ROCK_FOSSIL = registerBlock("alpine_rock_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> AQUATIC_ROCK_FOSSIL = registerBlock("aquatic_rock_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> ARID_ROCK_FOSSIL = registerBlock("arid_rock_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> FROZEN_ROCK_FOSSIL = registerBlock("frozen_rock_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> GRASSLAND_ROCK_FOSSIL = registerBlock("grassland_rock_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> TEMPERATE_ROCK_FOSSIL = registerBlock("temperate_rock_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> TROPICAL_ROCK_FOSSIL = registerBlock("tropical_rock_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> WETLAND_ROCK_FOSSIL = registerBlock("wetland_rock_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));

    //Encased Rock Fossil Ores
    public static final RegistryObject<Block> ENCASED_ALPINE_ROCK_FOSSIL = registerBlock("encased_alpine_rock_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> ENCASED_AQUATIC_ROCK_FOSSIL = registerBlock("encased_aquatic_rock_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> ENCASED_ARID_ROCK_FOSSIL = registerBlock("encased_arid_rock_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> ENCASED_FROZEN_ROCK_FOSSIL = registerBlock("encased_frozen_rock_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> ENCASED_GRASSLAND_ROCK_FOSSIL = registerBlock("encased_grassland_rock_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> ENCASED_TEMPERATE_ROCK_FOSSIL = registerBlock("encased_temperate_rock_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> ENCASED_TROPICAL_ROCK_FOSSIL = registerBlock("encased_tropical_rock_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> ENCASED_WETLAND_ROCK_FOSSIL = registerBlock("encased_wetland_rock_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));

    //Crystallised Fossil Blocks
    public static final RegistryObject<Block> ALPINE_CRYSTALLISED_FOSSIL = registerBlock("alpine_crystallised_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> AQUATIC_CRYSTALLISED_FOSSIL = registerBlock("aquatic_crystallised_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> ARID_CRYSTALLISED_FOSSIL = registerBlock("arid_crystallised_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> FROZEN_CRYSTALLISED_FOSSIL = registerBlock("frozen_crystallised_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> GRASSLAND_CRYSTALLISED_FOSSIL = registerBlock("grassland_crystallised_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> TEMPERATE_CRYSTALLISED_FOSSIL = registerBlock("temperate_crystallised_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> TROPICAL_CRYSTALLISED_FOSSIL = registerBlock("tropical_crystallised_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> WETLAND_CRYSTALLISED_FOSSIL = registerBlock("wetland_crystallised_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));

    //Encased Crystallised Fossil Blocks
    public static final RegistryObject<Block> ENCASED_ALPINE_CRYSTALLISED_FOSSIL = registerBlock("encased_alpine_crystallised_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> ENCASED_AQUATIC_CRYSTALLISED_FOSSIL = registerBlock("encased_aquatic_crystallised_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> ENCASED_ARID_CRYSTALLISED_FOSSIL = registerBlock("encased_arid_crystallised_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> ENCASED_FROZEN_CRYSTALLISED_FOSSIL = registerBlock("encased_frozen_crystallised_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> ENCASED_GRASSLAND_CRYSTALLISED_FOSSIL = registerBlock("encased_grassland_crystallised_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> ENCASED_TEMPERATE_CRYSTALLISED_FOSSIL = registerBlock("encased_temperate_crystallised_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> ENCASED_TROPICAL_CRYSTALLISED_FOSSIL = registerBlock("encased_tropical_crystallised_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> ENCASED_WETLAND_CRYSTALLISED_FOSSIL = registerBlock("encased_wetland_crystallised_fossil", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));

    //Machine Blocks
    public static final RegistryObject<Block> FOSSIL_EXCAVATOR = registerBlock("fossil_excavator", () -> new FossilExcavatorBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
            .strength(5.0f, 6.0f)
            .sound(SoundType.METAL)
            .lightLevel(state -> state.getValue(BlockStateProperties.POWERED) ? 14 : 0)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CORE_STATION = registerBlock("core_station", () -> new CoreStationBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
            .strength(5.0f, 6.0f)
            .sound(SoundType.METAL)
            .lightLevel(state -> state.getValue(BlockStateProperties.POWERED) ? 14 : 0)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DNA_RECOMBINATOR = registerBlock("dna_recombinator", () -> new DNARecombinatorBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
            .strength(5.0f, 6.0f)
            .sound(SoundType.METAL)
            .lightLevel(state -> state.getValue(BlockStateProperties.POWERED) ? 14 : 0)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> INCUBATOR = registerBlock("incubator", () -> new IncubatorBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
            .strength(5.0f, 6.0f)
            .sound(SoundType.METAL)
            .lightLevel(state -> state.getValue(BlockStateProperties.POWERED) ? 14 : 0)
            .requiresCorrectToolForDrops()
            .noOcclusion()));

    //Dino Husbandry Blocks
    public static final RegistryObject<Block> GROUND_FEEDER = registerBlock("ground_feeder", () -> new GroundFeederBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
            .strength(5.0f, 6.0f)
            .sound(SoundType.METAL)
            .requiresCorrectToolForDrops()
            .noOcclusion()));
    public static final RegistryObject<Block> NEST = registerBlock("nest", () -> new Block(BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.COLOR_YELLOW)
            .strength(0.5F)
            .sound(SoundType.GRASS)));

    //Enrichment Blocks
    public static final RegistryObject<Block> BUBBLE_BLOWER = registerBlock("bubble_blower", () -> new BubbleBlowerBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_GRAY)
            .strength(2.0F)
            .sound(SoundType.METAL)
            .noOcclusion()));
    public static final RegistryObject<Block> GNAWING_ROCK_STONE = registerBlock("gnawing_rock_stone", () -> new GnawingRockBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(2.0F)
            .sound(SoundType.STONE)
            .noOcclusion()));
    public static final RegistryObject<Block> GNAWING_ROCK_GRANITE = registerBlock("gnawing_rock_granite", () -> new GnawingRockBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(2.0F)
            .sound(SoundType.STONE)
            .noOcclusion()));
    public static final RegistryObject<Block> GNAWING_ROCK_ANDESITE = registerBlock("gnawing_rock_andesite", () -> new GnawingRockBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(2.0F)
            .sound(SoundType.STONE)
            .noOcclusion()));
    public static final RegistryObject<Block> GNAWING_ROCK_DIORITE = registerBlock("gnawing_rock_diorite", () -> new GnawingRockBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(2.0F)
            .sound(SoundType.STONE)
            .noOcclusion()));
    public static final RegistryObject<Block> SCENT_DIFFUSER_CARNIVORE = registerBlock("scent_diffuser_carnivore", () -> new ScentDiffuserBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_RED)
            .strength(2.0F)
            .sound(SoundType.METAL)
            .noOcclusion()));
    public static final RegistryObject<Block> SCRATCHING_LOG_OAK = registerBlock("scratching_log_oak", () -> new ScratchingLogBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)
            .strength(2.0F)
            .sound(SoundType.WOOD)
            .noOcclusion()));
    public static final RegistryObject<Block> SCRATCHING_LOG_SPRUCE = registerBlock("scratching_log_spruce", () -> new ScratchingLogBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)
            .strength(2.0F)
            .sound(SoundType.WOOD)
            .noOcclusion()));
    public static final RegistryObject<Block> SCRATCHING_LOG_BIRCH = registerBlock("scratching_log_birch", () -> new ScratchingLogBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_YELLOW)
            .strength(2.0F)
            .sound(SoundType.WOOD)
            .noOcclusion()));
    public static final RegistryObject<Block> SCRATCHING_LOG_JUNGLE = registerBlock("scratching_log_jungle", () -> new ScratchingLogBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)
            .strength(2.0F)
            .sound(SoundType.WOOD)
            .noOcclusion()));
    public static final RegistryObject<Block> SCRATCHING_LOG_ACACIA = registerBlock("scratching_log_acacia", () -> new ScratchingLogBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_ORANGE)
            .strength(2.0F)
            .sound(SoundType.WOOD)
            .noOcclusion()));
    public static final RegistryObject<Block> SCRATCHING_LOG_DARKOAK = registerBlock("scratching_log_darkoak", () -> new ScratchingLogBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)
            .strength(2.0F)
            .sound(SoundType.WOOD)
            .noOcclusion()));
    public static final RegistryObject<Block> SCRATCHING_LOG_CRIMSON = registerBlock("scratching_log_crimson", () -> new ScratchingLogBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED)
            .strength(2.0F)
            .sound(SoundType.WOOD)
            .noOcclusion()));
    public static final RegistryObject<Block> SCRATCHING_LOG_WARPED = registerBlock("scratching_log_warped", () -> new ScratchingLogBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BLUE)
            .strength(2.0F)
            .sound(SoundType.WOOD)
            .noOcclusion()));

    //Eggs
    public static final RegistryObject<Block> INCUBATED_APHANERAMMA_EGG = registerEggBlock("incubated_aphaneramma_egg", () -> new AphanerammaEggBlock(BlockBehaviour.Properties.of(Material.EGG, MaterialColor.COLOR_GREEN)
            .strength(0.5F)
            .sound(SoundType.METAL)
            .randomTicks()
            .noOcclusion()));
    public static final RegistryObject<Block> INCUBATED_AUSTRALOVENATOR_EGG = registerEggBlock("incubated_australovenator_egg", () -> new AustralovenatorEggBlock(BlockBehaviour.Properties.of(Material.EGG, MaterialColor.COLOR_ORANGE)
            .strength(0.5F)
            .sound(SoundType.METAL)
            .randomTicks()
            .noOcclusion()));
    public static final RegistryObject<Block> INCUBATED_COMPSOGNATHUS_EGG = registerEggBlock("incubated_compsognathus_egg", () -> new CompsognathusEggBlock(BlockBehaviour.Properties.of(Material.EGG, MaterialColor.COLOR_GREEN)
            .strength(0.5F)
            .sound(SoundType.METAL)
            .randomTicks()
            .noOcclusion()));
    public static final RegistryObject<Block> INCUBATED_SCELIDOSAURUS_EGG = registerEggBlock("incubated_scelidosaurus_egg", () -> new ScelidosaurusEggBlock(BlockBehaviour.Properties.of(Material.EGG, MaterialColor.COLOR_RED)
            .strength(0.5F)
            .sound(SoundType.METAL)
            .randomTicks()
            .noOcclusion()));
    public static final RegistryObject<Block> INCUBATED_TARBOSAURUS_EGG = registerEggBlock("incubated_tarbosaurus_egg", () -> new TarbosaurusEggBlock(BlockBehaviour.Properties.of(Material.EGG, MaterialColor.COLOR_RED)
            .strength(0.5F)
            .sound(SoundType.METAL)
            .randomTicks()
            .noOcclusion()));


    //Gem Blocks
    public static final RegistryObject<Block> HEMATINE_BLOCK = registerBlock("hematine_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE)
            .strength(4.0f, 5.0f)
            .sound(SoundType.METAL)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AZURITE_BLOCK = registerBlock("azurite_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE)
            .strength(4.0f, 5.0f)
            .sound(SoundType.METAL)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AMBER_BLOCK = registerBlock("amber_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE)
            .strength(4.0f, 5.0f)
            .sound(SoundType.METAL)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AQUAMARINE_BLOCK = registerBlock("aquamarine_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE)
            .strength(4.0f, 5.0f)
            .sound(SoundType.METAL)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> URAVORITE_BLOCK = registerBlock("uravorite_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE)
            .strength(4.0f, 5.0f)
            .sound(SoundType.METAL)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MALACHITE_BLOCK = registerBlock("malachite_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE)
            .strength(4.0f, 5.0f)
            .sound(SoundType.METAL)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SPHENE_BLOCK = registerBlock("sphene_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE)
            .strength(4.0f, 5.0f)
            .sound(SoundType.METAL)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ALMANDINE_BLOCK = registerBlock("almandine_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE)
            .strength(4.0f, 5.0f)
            .sound(SoundType.METAL)
            .requiresCorrectToolForDrops()));

    //Helper Methods
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(DinoBlocks.DINO_BLOCKS)));
    }

    private static <T extends Block>RegistryObject<T> registerEggBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerEggBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerEggBlockItem(String name, RegistryObject<T> block) {
        ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(DinoCreatures.DINO_CREATURES).stacksTo(4)));
    }
}
