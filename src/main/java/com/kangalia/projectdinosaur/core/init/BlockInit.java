package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.block.fossilexcavator.FossilExcavatorBlock;
import com.kangalia.projectdinosaur.core.itemgroup.DinoBlocks;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class BlockInit {
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ProjectDinosaur.MODID);

    //Blocks

    //Petrified Wood
    public static final RegistryObject<Block> PETRIFIED_LOG = registerBlock("petrified_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)
            .harvestTool(ToolType.AXE)
            .strength(2.0f, 2.0f)
            .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRIPPED_PETRIFIED_LOG = registerBlock("stripped_petrified_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)
            .harvestTool(ToolType.AXE)
            .strength(2.0f, 2.0f)
            .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> PETRIFIED_PLANKS = registerBlock("petrified_planks", () -> new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)
            .harvestTool(ToolType.AXE)
            .strength(2.0f, 2.0f)
            .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> PETRIFIED_SLAB = registerBlock("petrified_slab", () -> new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)
            .harvestTool(ToolType.AXE)
            .strength(2.0f, 2.0f)
            .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> PETRIFIED_STAIRS = registerBlock("petrified_stairs", () -> new StairsBlock(() -> PETRIFIED_PLANKS.get().defaultBlockState(), AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)
            .harvestTool(ToolType.AXE)
            .strength(2.0f, 2.0f)
            .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> PETRIFIED_FENCE = registerBlock("petrified_fence", () -> new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)
            .harvestTool(ToolType.AXE)
            .strength(2.0f, 2.0f)
            .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> PETRIFIED_FENCE_GATE = registerBlock("petrified_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)
            .harvestTool(ToolType.AXE)
            .strength(2.0f, 2.0f)
            .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> PETRIFIED_BUTTON = registerBlock("petrified_button", () -> new WoodButtonBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)
            .harvestTool(ToolType.AXE)
            .strength(2.0f, 2.0f)
            .sound(SoundType.WOOD)
            .noCollission()));
    public static final RegistryObject<Block> PETRIFIED_PRESSURE_PLATE = registerBlock("petrified_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)
            .harvestTool(ToolType.AXE)
            .strength(2.0f, 2.0f)
            .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> PETRIFIED_DOOR = registerBlock("petrified_door", () -> new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)
            .harvestTool(ToolType.AXE)
            .strength(2.0f, 2.0f)
            .sound(SoundType.WOOD)
            .noOcclusion()));
    public static final RegistryObject<Block> PETRIFIED_TRAPDOOR = registerBlock("petrified_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)
            .harvestTool(ToolType.AXE)
            .strength(2.0f, 2.0f)
            .sound(SoundType.WOOD)
            .noOcclusion()));

    //Rock Fossil Ores
    public static final RegistryObject<Block> ALPINE_ROCK_FOSSIL = registerBlock("alpine_rock_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AQUATIC_ROCK_FOSSIL = registerBlock("aquatic_rock_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ARID_ROCK_FOSSIL = registerBlock("arid_rock_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FROZEN_ROCK_FOSSIL = registerBlock("frozen_rock_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GRASSLAND_ROCK_FOSSIL = registerBlock("grassland_rock_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TEMPERATE_ROCK_FOSSIL = registerBlock("temperate_rock_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TROPICAL_ROCK_FOSSIL = registerBlock("tropical_rock_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WETLAND_ROCK_FOSSIL = registerBlock("wetland_rock_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));

    //Encased Rock Fossil Ores
    public static final RegistryObject<Block> ENCASED_ALPINE_ROCK_FOSSIL = registerBlock("encased_alpine_rock_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_AQUATIC_ROCK_FOSSIL = registerBlock("encased_aquatic_rock_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_ARID_ROCK_FOSSIL = registerBlock("encased_arid_rock_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_FROZEN_ROCK_FOSSIL = registerBlock("encased_frozen_rock_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_GRASSLAND_ROCK_FOSSIL = registerBlock("encased_grassland_rock_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_TEMPERATE_ROCK_FOSSIL = registerBlock("encased_temperate_rock_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_TROPICAL_ROCK_FOSSIL = registerBlock("encased_tropical_rock_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_WETLAND_ROCK_FOSSIL = registerBlock("encased_wetland_rock_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));

    //Crystallised Fossil Blocks
    public static final RegistryObject<Block> ALPINE_CRYSTALLISED_FOSSIL = registerBlock("alpine_crystallised_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> AQUATIC_CRYSTALLISED_FOSSIL = registerBlock("aquatic_crystallised_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ARID_CRYSTALLISED_FOSSIL = registerBlock("arid_crystallised_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FROZEN_CRYSTALLISED_FOSSIL = registerBlock("frozen_crystallised_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> GRASSLAND_CRYSTALLISED_FOSSIL = registerBlock("grassland_crystallised_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TEMPERATE_CRYSTALLISED_FOSSIL = registerBlock("temperate_crystallised_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TROPICAL_CRYSTALLISED_FOSSIL = registerBlock("tropical_crystallised_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WETLAND_CRYSTALLISED_FOSSIL = registerBlock("wetland_crystallised_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));

    //Encased Crystallised Fossil Blocks
    public static final RegistryObject<Block> ENCASED_ALPINE_CRYSTALLISED_FOSSIL = registerBlock("encased_alpine_crystallised_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_AQUATIC_CRYSTALLISED_FOSSIL = registerBlock("encased_aquatic_crystallised_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_ARID_CRYSTALLISED_FOSSIL = registerBlock("encased_arid_crystallised_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_FROZEN_CRYSTALLISED_FOSSIL = registerBlock("encased_frozen_crystallised_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_GRASSLAND_CRYSTALLISED_FOSSIL = registerBlock("encased_grassland_crystallised_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_TEMPERATE_CRYSTALLISED_FOSSIL = registerBlock("encased_temperate_crystallised_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_TROPICAL_CRYSTALLISED_FOSSIL = registerBlock("encased_tropical_crystallised_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ENCASED_WETLAND_CRYSTALLISED_FOSSIL = registerBlock("encased_wetland_crystallised_fossil", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(3.0f, 3.0f)
            .sound(SoundType.STONE)
            .requiresCorrectToolForDrops()));

    //Miscellaneous Blocks
    public static final RegistryObject<Block> AMBER_BLOCK = registerBlock("amber_block", () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
            .strength(4.0f, 5.0f)
            .sound(SoundType.METAL)
            .requiresCorrectToolForDrops()
            .noOcclusion()));

    //Machine Blocks
    public static final RegistryObject<Block> FOSSIL_EXCAVATOR = registerBlock("fossil_excavator", () -> new FossilExcavatorBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(2)
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
