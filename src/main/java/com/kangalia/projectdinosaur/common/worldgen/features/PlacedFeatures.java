package com.kangalia.projectdinosaur.common.worldgen.features;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class PlacedFeatures {

    // CONFIGURED FEATURE KEYS
    public static final ResourceKey<PlacedFeature> QUATERNARY_FOSSIL_PLACED_KEY = createKey("quaternary_fossil_placed");
    public static final ResourceKey<PlacedFeature> NEOGENE_FOSSIL_PLACED_KEY = createKey("neogene_fossil_placed");
    public static final ResourceKey<PlacedFeature> PALEOGENE_FOSSIL_PLACED_KEY = createKey("paleogene_fossil_placed");
    public static final ResourceKey<PlacedFeature> CRETACEOUS_FOSSIL_PLACED_KEY = createKey("cretaceous_fossil_placed");
    public static final ResourceKey<PlacedFeature> JURASSIC_FOSSI_PLACEDL_KEY = createKey("jurassic_fossil_placed");
    public static final ResourceKey<PlacedFeature> TRIASSIC_FOSSIL_PLACED_KEY = createKey("triassic_fossil_placed");
    public static final ResourceKey<PlacedFeature> PERMIAN_FOSSIL_PLACED_KEY = createKey("permian_fossil_placed");
    public static final ResourceKey<PlacedFeature> CARBONIFEROUS_FOSSIL_PLACED_KEY = createKey("carboniferous_fossil_placed");
    public static final ResourceKey<PlacedFeature> DEVONIAN_FOSSIL_PLACED_KEY = createKey("devonian_fossil_placed");
    public static final ResourceKey<PlacedFeature> SILURIAN_FOSSIL_PLACED_KEY = createKey("silurian_fossil_placed");
    public static final ResourceKey<PlacedFeature> ORDOVICIAN_FOSSIL_PLACED_KEY = createKey("ordovician_fossil_placed");
    public static final ResourceKey<PlacedFeature> CAMBRIAN_FOSSIL_PLACED_KEY = createKey("cambrian_fossil_placed");

    public static final ResourceKey<PlacedFeature> PETRIFIED_LOG_KEY = createKey("petrified_log_placed");

    public static void bootstrap (BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, QUATERNARY_FOSSIL_PLACED_KEY, configuredFeatures.getOrThrow(ConfiguredFeatures.QUATERNARY_FOSSIL_KEY),
                commonOrePlacement(10, // VeinsPerChunk
                HeightRangePlacement.uniform(VerticalAnchor.absolute(52), VerticalAnchor.absolute(64))));
        register(context, NEOGENE_FOSSIL_PLACED_KEY, configuredFeatures.getOrThrow(ConfiguredFeatures.QUATERNARY_FOSSIL_KEY),
                commonOrePlacement(10, // VeinsPerChunk
                HeightRangePlacement.uniform(VerticalAnchor.absolute(42), VerticalAnchor.absolute(54))));
        register(context, PALEOGENE_FOSSIL_PLACED_KEY, configuredFeatures.getOrThrow(ConfiguredFeatures.NEOGENE_FOSSIL_KEY),
                commonOrePlacement(10, // VeinsPerChunk
                HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.absolute(44))));
        register(context, CRETACEOUS_FOSSIL_PLACED_KEY, configuredFeatures.getOrThrow(ConfiguredFeatures.CRETACEOUS_FOSSIL_KEY),
                commonOrePlacement(10, // VeinsPerChunk
                HeightRangePlacement.uniform(VerticalAnchor.absolute(22), VerticalAnchor.absolute(34))));
        register(context, JURASSIC_FOSSI_PLACEDL_KEY, configuredFeatures.getOrThrow(ConfiguredFeatures.JURASSIC_FOSSIL_KEY),
                commonOrePlacement(10, // VeinsPerChunk
                HeightRangePlacement.uniform(VerticalAnchor.absolute(12), VerticalAnchor.absolute(24))));
        register(context, TRIASSIC_FOSSIL_PLACED_KEY, configuredFeatures.getOrThrow(ConfiguredFeatures.TRIASSIC_FOSSIL_KEY),
                commonOrePlacement(10, // VeinsPerChunk
                HeightRangePlacement.uniform(VerticalAnchor.absolute(2), VerticalAnchor.absolute(14))));
        register(context, PERMIAN_FOSSIL_PLACED_KEY, configuredFeatures.getOrThrow(ConfiguredFeatures.PERMIAN_FOSSIL_KEY),
                commonOrePlacement(10, // VeinsPerChunk
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-8), VerticalAnchor.absolute(4))));
        register(context, CARBONIFEROUS_FOSSIL_PLACED_KEY, configuredFeatures.getOrThrow(ConfiguredFeatures.CARBONIFEROUS_FOSSIL_KEY),
                commonOrePlacement(10, // VeinsPerChunk
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-18), VerticalAnchor.absolute(-6))));
        register(context, DEVONIAN_FOSSIL_PLACED_KEY, configuredFeatures.getOrThrow(ConfiguredFeatures.DEVONIAN_FOSSIL_KEY),
                commonOrePlacement(10, // VeinsPerChunk
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-28), VerticalAnchor.absolute(-16))));
        register(context, SILURIAN_FOSSIL_PLACED_KEY, configuredFeatures.getOrThrow(ConfiguredFeatures.SILURIAN_FOSSIL_KEY),
                commonOrePlacement(10, // VeinsPerChunk
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-38), VerticalAnchor.absolute(-26))));
        register(context, ORDOVICIAN_FOSSIL_PLACED_KEY, configuredFeatures.getOrThrow(ConfiguredFeatures.ORDOVICIAN_FOSSIL_KEY),
                commonOrePlacement(10, // VeinsPerChunk
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-48), VerticalAnchor.absolute(-36))));
        register(context, CAMBRIAN_FOSSIL_PLACED_KEY, configuredFeatures.getOrThrow(ConfiguredFeatures.CAMBRIAN_FOSSIL_KEY),
                commonOrePlacement(10, // VeinsPerChunk
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-58), VerticalAnchor.absolute(-46))));

        register(context, PETRIFIED_LOG_KEY, configuredFeatures.getOrThrow(ConfiguredFeatures.PETRIFIED_LOG_KEY), commonOrePlacement(12, // VeinsPerChunk
                HeightRangePlacement.uniform(VerticalAnchor.absolute(8), VerticalAnchor.absolute(64))));
    }


    // HELPER METHODS
    public static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(ProjectDinosaur.MODID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }
}
