package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.block.*;
import com.kangalia.projectdinosaur.core.itemgroup.DinoBlocks;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WoodButtonBlock;
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
    public static final RegistryObject<Block> ALPINE_ROCK_FOSSIL = registerBlock("alpine_rock_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AQUATIC_ROCK_FOSSIL = registerBlock("aquatic_rock_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ARID_ROCK_FOSSIL = registerBlock("arid_rock_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FROZEN_ROCK_FOSSIL = registerBlock("frozen_rock_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GRASSLAND_ROCK_FOSSIL = registerBlock("grassland_rock_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TEMPERATE_ROCK_FOSSIL = registerBlock("temperate_rock_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TROPICAL_ROCK_FOSSIL = registerBlock("tropical_rock_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WETLAND_ROCK_FOSSIL = registerBlock("wetland_rock_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));

    //Encased Rock Fossil Ores
    public static final RegistryObject<Block> ENCASED_ALPINE_ROCK_FOSSIL = registerBlock("encased_alpine_rock_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_AQUATIC_ROCK_FOSSIL = registerBlock("encased_aquatic_rock_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_ARID_ROCK_FOSSIL = registerBlock("encased_arid_rock_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_FROZEN_ROCK_FOSSIL = registerBlock("encased_frozen_rock_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_GRASSLAND_ROCK_FOSSIL = registerBlock("encased_grassland_rock_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_TEMPERATE_ROCK_FOSSIL = registerBlock("encased_temperate_rock_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_TROPICAL_ROCK_FOSSIL = registerBlock("encased_tropical_rock_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_WETLAND_ROCK_FOSSIL = registerBlock("encased_wetland_rock_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));

    //Crystallised Fossil Blocks
    public static final RegistryObject<Block> ALPINE_CRYSTALLISED_FOSSIL = registerBlock("alpine_crystallised_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AQUATIC_CRYSTALLISED_FOSSIL = registerBlock("aquatic_crystallised_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ARID_CRYSTALLISED_FOSSIL = registerBlock("arid_crystallised_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FROZEN_CRYSTALLISED_FOSSIL = registerBlock("frozen_crystallised_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GRASSLAND_CRYSTALLISED_FOSSIL = registerBlock("grassland_crystallised_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TEMPERATE_CRYSTALLISED_FOSSIL = registerBlock("temperate_crystallised_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TROPICAL_CRYSTALLISED_FOSSIL = registerBlock("tropical_crystallised_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WETLAND_CRYSTALLISED_FOSSIL = registerBlock("wetland_crystallised_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));

    //Encased Crystallised Fossil Blocks
    public static final RegistryObject<Block> ENCASED_ALPINE_CRYSTALLISED_FOSSIL = registerBlock("encased_alpine_crystallised_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_AQUATIC_CRYSTALLISED_FOSSIL = registerBlock("encased_aquatic_crystallised_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_ARID_CRYSTALLISED_FOSSIL = registerBlock("encased_arid_crystallised_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_FROZEN_CRYSTALLISED_FOSSIL = registerBlock("encased_frozen_crystallised_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_GRASSLAND_CRYSTALLISED_FOSSIL = registerBlock("encased_grassland_crystallised_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_TEMPERATE_CRYSTALLISED_FOSSIL = registerBlock("encased_temperate_crystallised_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_TROPICAL_CRYSTALLISED_FOSSIL = registerBlock("encased_tropical_crystallised_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_WETLAND_CRYSTALLISED_FOSSIL = registerBlock("encased_wetland_crystallised_fossil", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));

    //Miscellaneous Blocks
    public static final RegistryObject<Block> AMBER_BLOCK = registerBlock("amber_block", () -> new OreBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE)
            .strength(4.0f, 5.0f)
            .sound(SoundType.METAL)
            .requiresCorrectToolForDrops()
            .noOcclusion()));

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


    //Helper Methods
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(DinoBlocks.DINO_BLOCKS)));
    }
}
