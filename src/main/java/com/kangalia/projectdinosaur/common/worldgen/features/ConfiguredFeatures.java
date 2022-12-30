package com.kangalia.projectdinosaur.common.worldgen.features;

import com.google.common.base.Suppliers;
import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.core.init.BlockInit;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class ConfiguredFeatures {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, ProjectDinosaur.MODID);

    //Ore Configurations
    public static final Supplier<List<OreConfiguration.TargetBlockState>> QUATERNARY_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.QUATERNARY_FOSSIL.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> NEOGENE_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.NEOGENE_FOSSIL.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> PALEOGENE_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.PALEOGENE_FOSSIL.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> CRETACEOUS_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.CRETACEOUS_FOSSIL.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> JURASSIC_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.JURASSIC_FOSSIL.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> TRIASSIC_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.TRIASSIC_FOSSIL.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> PERMIAN_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.PERMIAN_FOSSIL.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> CARBONIFEROUS_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.CARBONIFEROUS_FOSSIL.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> DEVONIAN_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.DEVONIAN_FOSSIL.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> SILURIAN_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.SILURIAN_FOSSIL.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> ORDOVICIAN_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.ORDOVICIAN_FOSSIL.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> CAMBRIAN_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.CAMBRIAN_FOSSIL.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> PETRIFIED_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.PETRIFIED_LOG.get().defaultBlockState())));

    //Ore Features
    public static final RegistryObject<ConfiguredFeature<?, ?>> QUATERNARY_FOSSIL_ORE = CONFIGURED_FEATURES.register("quaternary_fossil_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(Objects.requireNonNull(QUATERNARY_CONFIGURATION.get()),8)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> NEOGENE_FOSSIL_ORE = CONFIGURED_FEATURES.register("neogene_fossil_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(Objects.requireNonNull(NEOGENE_CONFIGURATION.get()),8)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> PALEOGENE_FOSSIL_ORE = CONFIGURED_FEATURES.register("paleogene_fossil_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(Objects.requireNonNull(PALEOGENE_CONFIGURATION.get()),8)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CRETACEOUS_FOSSIL_ORE = CONFIGURED_FEATURES.register("cretaceous_fossil_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(Objects.requireNonNull(CRETACEOUS_CONFIGURATION.get()),8)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> JURASSIC_FOSSIL_ORE = CONFIGURED_FEATURES.register("jurassic_fossil_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(Objects.requireNonNull(JURASSIC_CONFIGURATION.get()),8)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> TRIASSIC_FOSSIL_ORE = CONFIGURED_FEATURES.register("triassic_fossil_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(Objects.requireNonNull(TRIASSIC_CONFIGURATION.get()),8)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> PERMIAN_FOSSIL_ORE = CONFIGURED_FEATURES.register("permian_fossil_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(Objects.requireNonNull(PERMIAN_CONFIGURATION.get()),8)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CARBONIFEROUS_FOSSIL_ORE = CONFIGURED_FEATURES.register("carboniferous_fossil_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(Objects.requireNonNull(CARBONIFEROUS_CONFIGURATION.get()),8)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> DEVONIAN_FOSSIL_ORE = CONFIGURED_FEATURES.register("devonian_fossil_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(Objects.requireNonNull(DEVONIAN_CONFIGURATION.get()),8)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> SILURIAN_FOSSIL_ORE = CONFIGURED_FEATURES.register("silurian_fossil_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(Objects.requireNonNull(SILURIAN_CONFIGURATION.get()),8)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ORDOVICIAN_FOSSIL_ORE = CONFIGURED_FEATURES.register("ordovician_fossil_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(Objects.requireNonNull(ORDOVICIAN_CONFIGURATION.get()),8)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> CAMBRIAN_FOSSIL_ORE = CONFIGURED_FEATURES.register("cambrian_fossil_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(Objects.requireNonNull(CAMBRIAN_CONFIGURATION.get()),8)));

    public static final RegistryObject<ConfiguredFeature<?, ?>> PETRIFIED_LOG_ORE = CONFIGURED_FEATURES.register("petrified_log_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(Objects.requireNonNull(PETRIFIED_CONFIGURATION.get()),16)));
}
