package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.block.FossilExcavator;
import com.kangalia.projectdinosaur.core.itemgroup.DinoBlocks;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class BlockInit {
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ProjectDinosaur.MODID);

    //Blocks

    //Petrified Wood
    public static final RegistryObject<Block> PETRIFIED_LOG = BLOCKS.register("petrified_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)
            .harvestTool(ToolType.AXE)
            .strength(2.0f, 2.0f)
            .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRIPPED_PETRIFIED_LOG = BLOCKS.register("stripped_petrified_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)
            .harvestTool(ToolType.AXE)
            .strength(2.0f, 2.0f)
            .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> PETRIFIED_PLANKS = BLOCKS.register("petrified_planks", () -> new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)
            .harvestTool(ToolType.AXE)
            .strength(2.0f, 2.0f)
            .sound(SoundType.WOOD)));

    //Rock Fossil Ores
    public static final RegistryObject<Block> TROPICAL_ROCK_FOSSIL = BLOCKS.register("tropical_rock_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ARID_ROCK_FOSSIL = BLOCKS.register("arid_rock_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AQUATIC_ROCK_FOSSIL = BLOCKS.register("aquatic_rock_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TEMPERATE_ROCK_FOSSIL = BLOCKS.register("temperate_rock_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FROZEN_ROCK_FOSSIL = BLOCKS.register("frozen_rock_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ALPINE_ROCK_FOSSIL = BLOCKS.register("alpine_rock_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WETLAND_ROCK_FOSSIL = BLOCKS.register("wetland_rock_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GRASSLAND_ROCK_FOSSIL = BLOCKS.register("grassland_rock_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));

    //Machine Blocks
    public static final RegistryObject<Block> FOSSIL_EXCAVATOR = BLOCKS.register("fossil_excavator", () -> new FossilExcavator());


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
