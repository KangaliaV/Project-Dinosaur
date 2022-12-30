package com.kangalia.projectdinosaur.common.worldgen.features;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class PlacedFeatures {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, ProjectDinosaur.MODID);

    //Ore Placed Features
    public static final RegistryObject<PlacedFeature> QUATERNARY_FOSSIL_PLACED = PLACED_FEATURES.register("quaternary_fossil_placed",
            () -> new PlacedFeature(ConfiguredFeatures.QUATERNARY_FOSSIL_ORE.getHolder().get(),
                    commonOrePlacement(6, // VeinsPerChunk
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(52), VerticalAnchor.absolute(64)))));
    public static final RegistryObject<PlacedFeature> NEOGENE_FOSSIL_PLACED = PLACED_FEATURES.register("neogene_fossil_placed",
            () -> new PlacedFeature(ConfiguredFeatures.NEOGENE_FOSSIL_ORE.getHolder().get(),
                    commonOrePlacement(6, // VeinsPerChunk
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(42), VerticalAnchor.absolute(54)))));
    public static final RegistryObject<PlacedFeature> PALEOGENE_FOSSIL_PLACED = PLACED_FEATURES.register("paleogene_fossil_placed",
            () -> new PlacedFeature(ConfiguredFeatures.PALEOGENE_FOSSIL_ORE.getHolder().get(),
                    commonOrePlacement(6, // VeinsPerChunk
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.absolute(44)))));
    public static final RegistryObject<PlacedFeature> CRETACEOUS_FOSSIL_PLACED = PLACED_FEATURES.register("cretaceous_fossil_placed",
            () -> new PlacedFeature(ConfiguredFeatures.CRETACEOUS_FOSSIL_ORE.getHolder().get(),
                    commonOrePlacement(6, // VeinsPerChunk
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(22), VerticalAnchor.absolute(34)))));
    public static final RegistryObject<PlacedFeature> JURASSIC_FOSSIL_PLACED = PLACED_FEATURES.register("jurassic_fossil_placed",
            () -> new PlacedFeature(ConfiguredFeatures.JURASSIC_FOSSIL_ORE.getHolder().get(),
                    commonOrePlacement(6, // VeinsPerChunk
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(12), VerticalAnchor.absolute(24)))));
    public static final RegistryObject<PlacedFeature> TRIASSIC_FOSSIL_PLACED = PLACED_FEATURES.register("triassic_fossil_placed",
            () -> new PlacedFeature(ConfiguredFeatures.TRIASSIC_FOSSIL_ORE.getHolder().get(),
                    commonOrePlacement(6, // VeinsPerChunk
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(2), VerticalAnchor.absolute(14)))));
    public static final RegistryObject<PlacedFeature> PERMIAN_FOSSIL_PLACED = PLACED_FEATURES.register("permian_fossil_placed",
            () -> new PlacedFeature(ConfiguredFeatures.PERMIAN_FOSSIL_ORE.getHolder().get(),
                    commonOrePlacement(6, // VeinsPerChunk
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(-8), VerticalAnchor.absolute(4)))));
    public static final RegistryObject<PlacedFeature> CARBONIFEROUS_FOSSIL_PLACED = PLACED_FEATURES.register("carboniferous_fossil_placed",
            () -> new PlacedFeature(ConfiguredFeatures.CARBONIFEROUS_FOSSIL_ORE.getHolder().get(),
                    commonOrePlacement(6, // VeinsPerChunk
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(-18), VerticalAnchor.absolute(-6)))));
    public static final RegistryObject<PlacedFeature> DEVONIAN_FOSSIL_PLACED = PLACED_FEATURES.register("devonian_fossil_placed",
            () -> new PlacedFeature(ConfiguredFeatures.DEVONIAN_FOSSIL_ORE.getHolder().get(),
                    commonOrePlacement(6, // VeinsPerChunk
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(-28), VerticalAnchor.absolute(-16)))));
    public static final RegistryObject<PlacedFeature> SILURIAN_FOSSIL_PLACED = PLACED_FEATURES.register("silurian_fossil_placed",
            () -> new PlacedFeature(ConfiguredFeatures.SILURIAN_FOSSIL_ORE.getHolder().get(),
                    commonOrePlacement(6, // VeinsPerChunk
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(-38), VerticalAnchor.absolute(-26)))));
    public static final RegistryObject<PlacedFeature> ORDOVICIAN_FOSSIL_PLACED = PLACED_FEATURES.register("ordovician_fossil_placed",
            () -> new PlacedFeature(ConfiguredFeatures.ORDOVICIAN_FOSSIL_ORE.getHolder().get(),
                    commonOrePlacement(6, // VeinsPerChunk
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(-48), VerticalAnchor.absolute(-36)))));
    public static final RegistryObject<PlacedFeature> CAMBRIAN_FOSSIL_PLACED = PLACED_FEATURES.register("cambrian_fossil_placed",
            () -> new PlacedFeature(ConfiguredFeatures.CAMBRIAN_FOSSIL_ORE.getHolder().get(),
                    commonOrePlacement(6, // VeinsPerChunk
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(-58), VerticalAnchor.absolute(-46)))));

    public static final RegistryObject<PlacedFeature> PETRIFIED_LOG_PLACED = PLACED_FEATURES.register("petrified_log_placed",
            () -> new PlacedFeature(ConfiguredFeatures.PETRIFIED_LOG_ORE.getHolder().get(),
                    commonOrePlacement(8, // VeinsPerChunk
                            HeightRangePlacement.uniform(VerticalAnchor.absolute(8), VerticalAnchor.absolute(64)))));

    //Helper Methods
    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }
}
