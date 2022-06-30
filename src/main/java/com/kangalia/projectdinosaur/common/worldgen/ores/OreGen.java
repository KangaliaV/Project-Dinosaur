package com.kangalia.projectdinosaur.common.worldgen.ores;

import com.kangalia.projectdinosaur.core.init.BlockInit;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class OreGen {
    public static final int ROCK_VEINSIZE = 8;
    public static final int ROCK_AMOUNT = 12;
    public static final int ROCK_AMOUNT_UNDERGROUND = 4;

    public static final int CRYSTALLISED_VEINSIZE = 5;
    public static final int CRYSTALLISED_AMOUNT = 3;
    public static final int CRYSTALLISED_AMOUNT_UNDERGROUND = 1;

    public static final int PETRIFIED_VEINSIZE = 16;
    public static final int PETRIFIED_AMOUNT = 7;

    public static Holder<PlacedFeature> ALPINE_ROCK_OREGEN;
    public static Holder<PlacedFeature> ALPINE_CRYSTALLISED_OREGEN;
    public static Holder<PlacedFeature> AQUATIC_ROCK_OREGEN;
    public static Holder<PlacedFeature> AQUATIC_CRYSTALLISED_OREGEN;
    public static Holder<PlacedFeature> ARID_ROCK_OREGEN;
    public static Holder<PlacedFeature> ARID_CRYSTALLISED_OREGEN;
    public static Holder<PlacedFeature> FROZEN_ROCK_OREGEN;
    public static Holder<PlacedFeature> FROZEN_CRYSTALLISED_OREGEN;
    public static Holder<PlacedFeature> GRASSLAND_ROCK_OREGEN;
    public static Holder<PlacedFeature> GRASSLAND_CRYSTALLISED_OREGEN;
    public static Holder<PlacedFeature> TEMPERATE_ROCK_OREGEN;
    public static Holder<PlacedFeature> TEMPERATE_CRYSTALLISED_OREGEN;
    public static Holder<PlacedFeature> TROPICAL_ROCK_OREGEN;
    public static Holder<PlacedFeature> TROPICAL_CRYSTALLISED_OREGEN;
    public static Holder<PlacedFeature> WETLAND_ROCK_OREGEN;
    public static Holder<PlacedFeature> WETLAND_CRYSTALLISED_OREGEN;
    public static Holder<PlacedFeature> PETRIFIED_OREGEN;

    public static Holder<PlacedFeature> ALPINE_ROCK_OREGEN_UNDERGROUND;
    public static Holder<PlacedFeature> ALPINE_CRYSTALLISED_OREGEN_UNDERGROUND;
    public static Holder<PlacedFeature> AQUATIC_ROCK_OREGEN_UNDERGROUND;
    public static Holder<PlacedFeature> AQUATIC_CRYSTALLISED_OREGEN_UNDERGROUND;
    public static Holder<PlacedFeature> ARID_ROCK_OREGEN_UNDERGROUND;
    public static Holder<PlacedFeature> ARID_CRYSTALLISED_OREGEN_UNDERGROUND;
    public static Holder<PlacedFeature> FROZEN_ROCK_OREGEN_UNDERGROUND;
    public static Holder<PlacedFeature> FROZEN_CRYSTALLISED_OREGEN_UNDERGROUND;
    public static Holder<PlacedFeature> GRASSLAND_ROCK_OREGEN_UNDERGROUND;
    public static Holder<PlacedFeature> GRASSLAND_CRYSTALLISED_OREGEN_UNDERGROUND;
    public static Holder<PlacedFeature> TEMPERATE_ROCK_OREGEN_UNDERGROUND;
    public static Holder<PlacedFeature> TEMPERATE_CRYSTALLISED_OREGEN_UNDERGROUND;
    public static Holder<PlacedFeature> TROPICAL_ROCK_OREGEN_UNDERGROUND;
    public static Holder<PlacedFeature> TROPICAL_CRYSTALLISED_OREGEN_UNDERGROUND;
    public static Holder<PlacedFeature> WETLAND_ROCK_OREGEN_UNDERGROUND;
    public static Holder<PlacedFeature> WETLAND_CRYSTALLISED_OREGEN_UNDERGROUND;

    public static void registerConfiguredFeatures() {
        // Alpine
        OreConfiguration alpineRockConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.ALPINE_ROCK_FOSSIL.get().defaultBlockState(), ROCK_VEINSIZE);
        ALPINE_ROCK_OREGEN = registerPlacedFeature("alpine_rock_fossil", new ConfiguredFeature<>(Feature.ORE, (alpineRockConfig)),
                CountPlacement.of(ROCK_AMOUNT),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));

        OreConfiguration alpineCrystallisedConfig = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.ALPINE_CRYSTALLISED_FOSSIL.get().defaultBlockState(), CRYSTALLISED_VEINSIZE);
        ALPINE_CRYSTALLISED_OREGEN = registerPlacedFeature("alpine_crystallised_fossil", new ConfiguredFeature<>(Feature.ORE, (alpineCrystallisedConfig)),
                CountPlacement.of(CRYSTALLISED_AMOUNT),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64)));

        // Aquatic
        OreConfiguration aquaticRockConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.AQUATIC_ROCK_FOSSIL.get().defaultBlockState(), ROCK_VEINSIZE);
        AQUATIC_ROCK_OREGEN = registerPlacedFeature("aquatic_rock_fossil", new ConfiguredFeature<>(Feature.ORE, (aquaticRockConfig)),
                CountPlacement.of(ROCK_AMOUNT),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));

        OreConfiguration aquaticCrystallisedConfig = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.AQUATIC_CRYSTALLISED_FOSSIL.get().defaultBlockState(), CRYSTALLISED_VEINSIZE);
        AQUATIC_CRYSTALLISED_OREGEN = registerPlacedFeature("aquatic_crystallised_fossil", new ConfiguredFeature<>(Feature.ORE, (aquaticCrystallisedConfig)),
                CountPlacement.of(CRYSTALLISED_AMOUNT),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64)));

        // Arid
        OreConfiguration aridRockConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.ARID_ROCK_FOSSIL.get().defaultBlockState(), ROCK_VEINSIZE);
        ARID_ROCK_OREGEN = registerPlacedFeature("arid_rock_fossil", new ConfiguredFeature<>(Feature.ORE, (aridRockConfig)),
                CountPlacement.of(ROCK_AMOUNT),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));

        OreConfiguration aridCrystallisedConfig = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.ARID_CRYSTALLISED_FOSSIL.get().defaultBlockState(), CRYSTALLISED_VEINSIZE);
        ARID_CRYSTALLISED_OREGEN = registerPlacedFeature("arid_crystallised_fossil", new ConfiguredFeature<>(Feature.ORE, (aridCrystallisedConfig)),
                CountPlacement.of(CRYSTALLISED_AMOUNT),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64)));

        // Frozen
        OreConfiguration frozenRockConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.FROZEN_ROCK_FOSSIL.get().defaultBlockState(), ROCK_VEINSIZE);
        FROZEN_ROCK_OREGEN = registerPlacedFeature("frozen_rock_fossil", new ConfiguredFeature<>(Feature.ORE, (frozenRockConfig)),
                CountPlacement.of(ROCK_AMOUNT),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));

        OreConfiguration frozenCrystallisedConfig = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.FROZEN_CRYSTALLISED_FOSSIL.get().defaultBlockState(), CRYSTALLISED_VEINSIZE);
        FROZEN_CRYSTALLISED_OREGEN = registerPlacedFeature("frozen_crystallised_fossil", new ConfiguredFeature<>(Feature.ORE, (frozenCrystallisedConfig)),
                CountPlacement.of(CRYSTALLISED_AMOUNT),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64)));

        // Grassland
        OreConfiguration grasslandRockConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.GRASSLAND_ROCK_FOSSIL.get().defaultBlockState(), ROCK_VEINSIZE);
        GRASSLAND_ROCK_OREGEN = registerPlacedFeature("grassland_rock_fossil", new ConfiguredFeature<>(Feature.ORE, (grasslandRockConfig)),
                CountPlacement.of(ROCK_AMOUNT),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));

        OreConfiguration grasslandCrystallisedConfig = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.GRASSLAND_CRYSTALLISED_FOSSIL.get().defaultBlockState(), CRYSTALLISED_VEINSIZE);
        GRASSLAND_CRYSTALLISED_OREGEN = registerPlacedFeature("grassland_crystallised_fossil", new ConfiguredFeature<>(Feature.ORE, (grasslandCrystallisedConfig)),
                CountPlacement.of(CRYSTALLISED_AMOUNT),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64)));

        // Temperate
        OreConfiguration temperateRockConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.TEMPERATE_ROCK_FOSSIL.get().defaultBlockState(), ROCK_VEINSIZE);
        TEMPERATE_ROCK_OREGEN = registerPlacedFeature("temperate_rock_fossil", new ConfiguredFeature<>(Feature.ORE, (temperateRockConfig)),
                CountPlacement.of(ROCK_AMOUNT),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));

        OreConfiguration temperateCrystallisedConfig = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.TEMPERATE_CRYSTALLISED_FOSSIL.get().defaultBlockState(), CRYSTALLISED_VEINSIZE);
        TEMPERATE_CRYSTALLISED_OREGEN = registerPlacedFeature("temperate_crystallised_fossil", new ConfiguredFeature<>(Feature.ORE, (temperateCrystallisedConfig)),
                CountPlacement.of(CRYSTALLISED_AMOUNT),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64)));

        // Tropical
        OreConfiguration tropicalRockConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.TROPICAL_ROCK_FOSSIL.get().defaultBlockState(), ROCK_VEINSIZE);
        TROPICAL_ROCK_OREGEN = registerPlacedFeature("tropical_rock_fossil", new ConfiguredFeature<>(Feature.ORE, (tropicalRockConfig)),
                CountPlacement.of(ROCK_AMOUNT),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));

        OreConfiguration tropicalCrystallisedConfig = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.TROPICAL_CRYSTALLISED_FOSSIL.get().defaultBlockState(), CRYSTALLISED_VEINSIZE);
        TROPICAL_CRYSTALLISED_OREGEN = registerPlacedFeature("tropical_crystallised_fossil", new ConfiguredFeature<>(Feature.ORE, (tropicalCrystallisedConfig)),
                CountPlacement.of(CRYSTALLISED_AMOUNT),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64)));

        // Wetland
        OreConfiguration wetlandRockConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.WETLAND_ROCK_FOSSIL.get().defaultBlockState(), ROCK_VEINSIZE);
        WETLAND_ROCK_OREGEN = registerPlacedFeature("wetland_rock_fossil", new ConfiguredFeature<>(Feature.ORE, (wetlandRockConfig)),
                CountPlacement.of(ROCK_AMOUNT),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));

        OreConfiguration wetlandCrystallisedConfig = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.WETLAND_CRYSTALLISED_FOSSIL.get().defaultBlockState(), CRYSTALLISED_VEINSIZE);
        WETLAND_CRYSTALLISED_OREGEN = registerPlacedFeature("wetland_crystallised_fossil", new ConfiguredFeature<>(Feature.ORE, (wetlandCrystallisedConfig)),
                CountPlacement.of(CRYSTALLISED_AMOUNT),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64)));

        // Petrified Wood
        OreConfiguration petrifiedConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.PETRIFIED_LOG.get().defaultBlockState(), PETRIFIED_VEINSIZE);
        PETRIFIED_OREGEN = registerPlacedFeature("petrified_log", new ConfiguredFeature<>(Feature.ORE, (petrifiedConfig)),
                CountPlacement.of(PETRIFIED_AMOUNT),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));

        //Underground Biomes
        // Alpine
        OreConfiguration alpineRockConfigUnderground = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.ALPINE_ROCK_FOSSIL.get().defaultBlockState(), ROCK_VEINSIZE);
        ALPINE_ROCK_OREGEN_UNDERGROUND = registerPlacedFeature("alpine_rock_fossil_underground", new ConfiguredFeature<>(Feature.ORE, (alpineRockConfigUnderground)),
                CountPlacement.of(ROCK_AMOUNT_UNDERGROUND),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));

        OreConfiguration alpineCrystallisedConfigUnderground = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.ALPINE_CRYSTALLISED_FOSSIL.get().defaultBlockState(), CRYSTALLISED_VEINSIZE);
        ALPINE_CRYSTALLISED_OREGEN_UNDERGROUND = registerPlacedFeature("alpine_crystallised_fossil_underground", new ConfiguredFeature<>(Feature.ORE, (alpineCrystallisedConfigUnderground)),
                CountPlacement.of(CRYSTALLISED_AMOUNT_UNDERGROUND),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64)));

        // Aquatic
        OreConfiguration aquaticRockConfigUnderground = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.AQUATIC_ROCK_FOSSIL.get().defaultBlockState(), ROCK_VEINSIZE);
        AQUATIC_ROCK_OREGEN_UNDERGROUND = registerPlacedFeature("aquatic_rock_fossil_underground", new ConfiguredFeature<>(Feature.ORE, (aquaticRockConfigUnderground)),
                CountPlacement.of(ROCK_AMOUNT_UNDERGROUND),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));

        OreConfiguration aquaticCrystallisedConfigUnderground = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.AQUATIC_CRYSTALLISED_FOSSIL.get().defaultBlockState(), CRYSTALLISED_VEINSIZE);
        AQUATIC_CRYSTALLISED_OREGEN_UNDERGROUND = registerPlacedFeature("aquatic_crystallised_fossil_underground", new ConfiguredFeature<>(Feature.ORE, (aquaticCrystallisedConfigUnderground)),
                CountPlacement.of(CRYSTALLISED_AMOUNT_UNDERGROUND),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64)));

        // Arid
        OreConfiguration aridRockConfigUnderground = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.ARID_ROCK_FOSSIL.get().defaultBlockState(), ROCK_VEINSIZE);
        ARID_ROCK_OREGEN_UNDERGROUND = registerPlacedFeature("arid_rock_fossil_underground", new ConfiguredFeature<>(Feature.ORE, (aridRockConfigUnderground)),
                CountPlacement.of(ROCK_AMOUNT_UNDERGROUND),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));

        OreConfiguration aridCrystallisedConfigUnderground = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.ARID_CRYSTALLISED_FOSSIL.get().defaultBlockState(), CRYSTALLISED_VEINSIZE);
        ARID_CRYSTALLISED_OREGEN_UNDERGROUND = registerPlacedFeature("arid_crystallised_fossil_underground", new ConfiguredFeature<>(Feature.ORE, (aridCrystallisedConfigUnderground)),
                CountPlacement.of(CRYSTALLISED_AMOUNT_UNDERGROUND),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64)));

        // Frozen
        OreConfiguration frozenRockConfigUnderground = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.FROZEN_ROCK_FOSSIL.get().defaultBlockState(), ROCK_VEINSIZE);
        FROZEN_ROCK_OREGEN_UNDERGROUND = registerPlacedFeature("frozen_rock_fossil_underground", new ConfiguredFeature<>(Feature.ORE, (frozenRockConfigUnderground)),
                CountPlacement.of(ROCK_AMOUNT_UNDERGROUND),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));

        OreConfiguration frozenCrystallisedConfigUnderground = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.FROZEN_CRYSTALLISED_FOSSIL.get().defaultBlockState(), CRYSTALLISED_VEINSIZE);
        FROZEN_CRYSTALLISED_OREGEN_UNDERGROUND = registerPlacedFeature("frozen_crystallised_fossil_underground", new ConfiguredFeature<>(Feature.ORE, (frozenCrystallisedConfigUnderground)),
                CountPlacement.of(CRYSTALLISED_AMOUNT_UNDERGROUND),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64)));

        // Grassland
        OreConfiguration grasslandRockConfigUnderground = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.GRASSLAND_ROCK_FOSSIL.get().defaultBlockState(), ROCK_VEINSIZE);
        GRASSLAND_ROCK_OREGEN_UNDERGROUND = registerPlacedFeature("grassland_rock_fossil_underground", new ConfiguredFeature<>(Feature.ORE, (grasslandRockConfigUnderground)),
                CountPlacement.of(ROCK_AMOUNT_UNDERGROUND),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));

        OreConfiguration grasslandCrystallisedConfigUnderground = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.GRASSLAND_CRYSTALLISED_FOSSIL.get().defaultBlockState(), CRYSTALLISED_VEINSIZE);
        GRASSLAND_CRYSTALLISED_OREGEN_UNDERGROUND = registerPlacedFeature("grassland_crystallised_fossil_underground", new ConfiguredFeature<>(Feature.ORE, (grasslandCrystallisedConfigUnderground)),
                CountPlacement.of(CRYSTALLISED_AMOUNT_UNDERGROUND),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64)));

        // Temperate
        OreConfiguration temperateRockConfigUnderground = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.TEMPERATE_ROCK_FOSSIL.get().defaultBlockState(), ROCK_VEINSIZE);
        TEMPERATE_ROCK_OREGEN_UNDERGROUND = registerPlacedFeature("temperate_rock_fossil_underground", new ConfiguredFeature<>(Feature.ORE, (temperateRockConfigUnderground)),
                CountPlacement.of(ROCK_AMOUNT_UNDERGROUND),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));

        OreConfiguration temperateCrystallisedConfigUnderground = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.TEMPERATE_CRYSTALLISED_FOSSIL.get().defaultBlockState(), CRYSTALLISED_VEINSIZE);
        TEMPERATE_CRYSTALLISED_OREGEN_UNDERGROUND = registerPlacedFeature("temperate_crystallised_fossil_underground", new ConfiguredFeature<>(Feature.ORE, (temperateCrystallisedConfigUnderground)),
                CountPlacement.of(CRYSTALLISED_AMOUNT_UNDERGROUND),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64)));

        // Tropical
        OreConfiguration tropicalRockConfigUnderground = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.TROPICAL_ROCK_FOSSIL.get().defaultBlockState(), ROCK_VEINSIZE);
        TROPICAL_ROCK_OREGEN_UNDERGROUND = registerPlacedFeature("tropical_rock_fossil_underground", new ConfiguredFeature<>(Feature.ORE, (tropicalRockConfigUnderground)),
                CountPlacement.of(ROCK_AMOUNT_UNDERGROUND),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));

        OreConfiguration tropicalCrystallisedConfigUnderground = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.TROPICAL_CRYSTALLISED_FOSSIL.get().defaultBlockState(), CRYSTALLISED_VEINSIZE);
        TROPICAL_CRYSTALLISED_OREGEN_UNDERGROUND = registerPlacedFeature("tropical_crystallised_fossil_underground", new ConfiguredFeature<>(Feature.ORE, (tropicalCrystallisedConfigUnderground)),
                CountPlacement.of(CRYSTALLISED_AMOUNT_UNDERGROUND),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64)));

        // Wetland
        OreConfiguration wetlandRockConfigUnderground = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.WETLAND_ROCK_FOSSIL.get().defaultBlockState(), ROCK_VEINSIZE);
        WETLAND_ROCK_OREGEN_UNDERGROUND = registerPlacedFeature("wetland_rock_fossil_underground", new ConfiguredFeature<>(Feature.ORE, (wetlandRockConfigUnderground)),
                CountPlacement.of(ROCK_AMOUNT_UNDERGROUND),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));

        OreConfiguration wetlandCrystallisedConfigUnderground = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.WETLAND_CRYSTALLISED_FOSSIL.get().defaultBlockState(), CRYSTALLISED_VEINSIZE);
        WETLAND_CRYSTALLISED_OREGEN_UNDERGROUND = registerPlacedFeature("wetland_crystallised_fossil_underground", new ConfiguredFeature<>(Feature.ORE, (wetlandCrystallisedConfigUnderground)),
                CountPlacement.of(CRYSTALLISED_AMOUNT_UNDERGROUND),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64)));
    }

    private static <C extends FeatureConfiguration, F extends Feature<C>> Holder<PlacedFeature> registerPlacedFeature(String registryName, ConfiguredFeature<C, F> feature, PlacementModifier... placementModifiers) {
        return PlacementUtils.register(registryName, Holder.direct(feature), placementModifiers);
    }

    public static void onBiomeLoadingEvent(BiomeLoadingEvent event) {
        //Petrified Wood
        event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PETRIFIED_OREGEN);
        //Alpine Check
        if (event.getCategory() == Biome.BiomeCategory.EXTREME_HILLS || event.getCategory() == Biome.BiomeCategory.TAIGA || event.getCategory() == Biome.BiomeCategory.MOUNTAIN) {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ALPINE_ROCK_OREGEN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ALPINE_CRYSTALLISED_OREGEN);
        }
        //Aquatic Check
        if (event.getCategory() == Biome.BiomeCategory.OCEAN || event.getCategory() == Biome.BiomeCategory.BEACH || event.getCategory() == Biome.BiomeCategory.RIVER) {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, AQUATIC_ROCK_OREGEN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, AQUATIC_CRYSTALLISED_OREGEN);
        }
        //Arid Check
        if (event.getCategory() == Biome.BiomeCategory.DESERT || event.getCategory() == Biome.BiomeCategory.MESA || event.getCategory() == Biome.BiomeCategory.SAVANNA) {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ARID_ROCK_OREGEN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ARID_CRYSTALLISED_OREGEN);
        }
        //Frozen Check
        if (event.getCategory() == Biome.BiomeCategory.ICY) {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, FROZEN_ROCK_OREGEN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, FROZEN_CRYSTALLISED_OREGEN);
        }
        //Grassland Check
        if (event.getCategory() == Biome.BiomeCategory.PLAINS) {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GRASSLAND_ROCK_OREGEN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GRASSLAND_CRYSTALLISED_OREGEN);
        }
        //Temperate Check
        if (event.getCategory() == Biome.BiomeCategory.FOREST) {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TEMPERATE_ROCK_OREGEN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TEMPERATE_CRYSTALLISED_OREGEN);
        }
        //Tropical Check
        if (event.getCategory() == Biome.BiomeCategory.JUNGLE) {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TROPICAL_ROCK_OREGEN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TROPICAL_CRYSTALLISED_OREGEN);
        }
        //Wetland Check
        if (event.getCategory() == Biome.BiomeCategory.SWAMP) {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WETLAND_ROCK_OREGEN);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WETLAND_CRYSTALLISED_OREGEN);
        }
        //Underground Biome Fossil Check
        if (event.getCategory() == Biome.BiomeCategory.UNDERGROUND) {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ALPINE_ROCK_OREGEN_UNDERGROUND);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ALPINE_CRYSTALLISED_OREGEN_UNDERGROUND);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, AQUATIC_ROCK_OREGEN_UNDERGROUND);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, AQUATIC_CRYSTALLISED_OREGEN_UNDERGROUND);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ARID_ROCK_OREGEN_UNDERGROUND);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ARID_CRYSTALLISED_OREGEN_UNDERGROUND);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, FROZEN_ROCK_OREGEN_UNDERGROUND);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, FROZEN_CRYSTALLISED_OREGEN_UNDERGROUND);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GRASSLAND_ROCK_OREGEN_UNDERGROUND);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GRASSLAND_CRYSTALLISED_OREGEN_UNDERGROUND);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TEMPERATE_ROCK_OREGEN_UNDERGROUND);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TEMPERATE_CRYSTALLISED_OREGEN_UNDERGROUND);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TROPICAL_ROCK_OREGEN_UNDERGROUND);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, TROPICAL_CRYSTALLISED_OREGEN_UNDERGROUND);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WETLAND_ROCK_OREGEN_UNDERGROUND);
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WETLAND_CRYSTALLISED_OREGEN_UNDERGROUND);
        }
    }
}
