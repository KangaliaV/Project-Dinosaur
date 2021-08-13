package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ProjectDinosaur.MODID);

    //Petrified Wood
    public static final RegistryObject<Block> PETRIFIED_LOG = BLOCKS.register("petrified_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)
            .harvestTool(ToolType.AXE)
            .strength(2.0f, 2.0f)
            .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> PETRIFIED_PLANKS = BLOCKS.register("petrified_planks", () -> new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)
            .harvestTool(ToolType.AXE)
            .strength(2.0f, 2.0f)
            .sound(SoundType.WOOD)));

    //Rock Fossil Ores
    //Need JSON files for all of these yet. Nothing has been done with them except this registration.
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
}
