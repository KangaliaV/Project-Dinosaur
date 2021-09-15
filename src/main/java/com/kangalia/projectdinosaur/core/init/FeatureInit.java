package com.kangalia.projectdinosaur.core.init;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class FeatureInit {

    public static void addOres(final BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder generation = event.getGeneration();
        addOre(event, OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.PETRIFIED_LOG.get().defaultBlockState(), 16, 4, 60, 10);

        if (event.getCategory().equals(Biome.Category.EXTREME_HILLS)) {
            addOre(event, OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.ALPINE_ROCK_FOSSIL.get().defaultBlockState(), 5, 17, 60, 20);
        }
        if (event.getCategory().equals(Biome.Category.OCEAN) || event.getCategory().equals(Biome.Category.BEACH)) {
            addOre(event, OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.AQUATIC_ROCK_FOSSIL.get().defaultBlockState(), 5, 17, 60, 20);
        }
        if (event.getCategory().equals(Biome.Category.DESERT) || event.getCategory().equals(Biome.Category.MESA)) {
            addOre(event, OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.ARID_ROCK_FOSSIL.get().defaultBlockState(), 5, 17, 60, 20);
        }
        if (event.getCategory().equals(Biome.Category.ICY) || event.getCategory().equals(Biome.Category.TAIGA)) {
            addOre(event, OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.FROZEN_ROCK_FOSSIL.get().defaultBlockState(), 5, 17, 60, 20);
        }
        if (event.getCategory().equals(Biome.Category.PLAINS) || event.getCategory().equals(Biome.Category.SAVANNA)) {
            addOre(event, OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.GRASSLAND_ROCK_FOSSIL.get().defaultBlockState(), 5, 17, 60, 20);
        }
        if (event.getCategory().equals(Biome.Category.FOREST)) {
            addOre(event, OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.TEMPERATE_ROCK_FOSSIL.get().defaultBlockState(), 5, 17, 60, 20);
        }
        if (event.getCategory().equals(Biome.Category.JUNGLE)) {
            addOre(event, OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.TROPICAL_ROCK_FOSSIL.get().defaultBlockState(), 5, 17, 60, 20);
        }
        if (event.getCategory().equals(Biome.Category.SWAMP)) {
            addOre(event, OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.WETLAND_ROCK_FOSSIL.get().defaultBlockState(), 5, 17, 60, 20);
        }
    }

    public static void addOre(final BiomeLoadingEvent event, RuleTest ruleTest, BlockState blockState, int veinSize, int minHeight, int maxHeight, int amount) {
        event.getGeneration()
                .addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configured(new OreFeatureConfig(ruleTest, blockState, veinSize))
                        .decorated(Placement.RANGE.configured(new TopSolidRangeConfig(minHeight, 0, maxHeight)))
                        .squared()
                        .count(amount));
    }
}
