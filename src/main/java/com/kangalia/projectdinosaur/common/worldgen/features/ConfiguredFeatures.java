package com.kangalia.projectdinosaur.common.worldgen.features;

import com.google.common.base.Suppliers;
import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.core.init.BlockInit;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class ConfiguredFeatures {

    // CONFIGURED FEATURE KEYS
    public static final ResourceKey<ConfiguredFeature<?, ?>> QUATERNARY_FOSSIL_KEY = registerKey("quaternary_fossil");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NEOGENE_FOSSIL_KEY = registerKey("neogene_fossil");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PALEOGENE_FOSSIL_KEY = registerKey("paleogene_fossil");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CRETACEOUS_FOSSIL_KEY = registerKey("cretaceous_fossil");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JURASSIC_FOSSIL_KEY = registerKey("jurassic_fossil");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TRIASSIC_FOSSIL_KEY = registerKey("triassic_fossil");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PERMIAN_FOSSIL_KEY = registerKey("permian_fossil");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CARBONIFEROUS_FOSSIL_KEY = registerKey("carboniferous_fossil");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEVONIAN_FOSSIL_KEY = registerKey("devonian_fossil");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILURIAN_FOSSIL_KEY = registerKey("silurian_fossil");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORDOVICIAN_FOSSIL_KEY = registerKey("ordovician_fossil");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CAMBRIAN_FOSSIL_KEY = registerKey("cambrian_fossil");

    public static final ResourceKey<ConfiguredFeature<?, ?>> PETRIFIED_LOG_KEY = registerKey("petrified_log");

    // FEATURE CONFIGURATIONS
    public static final Supplier<List<OreConfiguration.TargetBlockState>> QUATERNARY_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), BlockInit.QUATERNARY_FOSSIL.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> NEOGENE_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), BlockInit.NEOGENE_FOSSIL.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> PALEOGENE_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), BlockInit.PALEOGENE_FOSSIL.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> CRETACEOUS_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), BlockInit.CRETACEOUS_FOSSIL.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> JURASSIC_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), BlockInit.JURASSIC_FOSSIL.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> TRIASSIC_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), BlockInit.TRIASSIC_FOSSIL.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> PERMIAN_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), BlockInit.PERMIAN_FOSSIL.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> CARBONIFEROUS_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), BlockInit.CARBONIFEROUS_FOSSIL.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> DEVONIAN_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), BlockInit.DEVONIAN_FOSSIL.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> SILURIAN_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), BlockInit.SILURIAN_FOSSIL.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> ORDOVICIAN_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), BlockInit.ORDOVICIAN_FOSSIL.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> CAMBRIAN_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), BlockInit.CAMBRIAN_FOSSIL.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> PETRIFIED_CONFIGURATION = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), BlockInit.PETRIFIED_LOG.get().defaultBlockState())));


    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        register(context, QUATERNARY_FOSSIL_KEY, Feature.ORE, new OreConfiguration(Objects.requireNonNull(QUATERNARY_CONFIGURATION.get()),8));
        register(context, NEOGENE_FOSSIL_KEY, Feature.ORE, new OreConfiguration(Objects.requireNonNull(NEOGENE_CONFIGURATION.get()),8));
        register(context, PALEOGENE_FOSSIL_KEY, Feature.ORE, new OreConfiguration(Objects.requireNonNull(PALEOGENE_CONFIGURATION.get()),8));
        register(context, CRETACEOUS_FOSSIL_KEY, Feature.ORE, new OreConfiguration(Objects.requireNonNull(CRETACEOUS_CONFIGURATION.get()),8));
        register(context, JURASSIC_FOSSIL_KEY, Feature.ORE, new OreConfiguration(Objects.requireNonNull(JURASSIC_CONFIGURATION.get()),8));
        register(context, TRIASSIC_FOSSIL_KEY, Feature.ORE, new OreConfiguration(Objects.requireNonNull(TRIASSIC_CONFIGURATION.get()),8));
        register(context, PERMIAN_FOSSIL_KEY, Feature.ORE, new OreConfiguration(Objects.requireNonNull(PERMIAN_CONFIGURATION.get()),8));
        register(context, CARBONIFEROUS_FOSSIL_KEY, Feature.ORE, new OreConfiguration(Objects.requireNonNull(CARBONIFEROUS_CONFIGURATION.get()),8));
        register(context, DEVONIAN_FOSSIL_KEY, Feature.ORE, new OreConfiguration(Objects.requireNonNull(DEVONIAN_CONFIGURATION.get()),8));
        register(context, SILURIAN_FOSSIL_KEY, Feature.ORE, new OreConfiguration(Objects.requireNonNull(SILURIAN_CONFIGURATION.get()),8));
        register(context, ORDOVICIAN_FOSSIL_KEY, Feature.ORE, new OreConfiguration(Objects.requireNonNull(ORDOVICIAN_CONFIGURATION.get()),8));
        register(context, CAMBRIAN_FOSSIL_KEY, Feature.ORE, new OreConfiguration(Objects.requireNonNull(CAMBRIAN_CONFIGURATION.get()),8));

        register(context, PETRIFIED_LOG_KEY, Feature.ORE, new OreConfiguration(Objects.requireNonNull(PETRIFIED_CONFIGURATION.get()),16));
    }

    // HELPER METHODS
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(ProjectDinosaur.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
