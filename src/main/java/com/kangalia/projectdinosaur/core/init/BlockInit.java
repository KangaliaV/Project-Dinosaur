package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ProjectDinosaur.MODID);

    public static final RegistryObject<Block> PETRIFIED_LOG = BLOCKS.register("petrified_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)
            .harvestTool(ToolType.AXE)
            .strength(2.0f, 2.0f)
            .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> PETRIFIED_PLANKS = BLOCKS.register("petrified_planks", () -> new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN)
            .harvestTool(ToolType.AXE)
            .strength(2.0f, 2.0f)
            .sound(SoundType.WOOD)));
}
