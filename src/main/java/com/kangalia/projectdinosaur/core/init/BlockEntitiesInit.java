package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.blockentity.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockEntitiesInit {

    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ProjectDinosaur.MODID);

    //Machines
    public static final RegistryObject<BlockEntityType<FossilExcavatorBlockEntity>> FOSSIL_EXCAVATOR_ENTITY = TILE_ENTITIES.register("fossil_excavator",
            () -> BlockEntityType.Builder.of(FossilExcavatorBlockEntity::new, BlockInit.FOSSIL_EXCAVATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<CoreStationBlockEntity>> CORE_STATION_ENTITY = TILE_ENTITIES.register("core_station",
            () -> BlockEntityType.Builder.of(CoreStationBlockEntity::new, BlockInit.CORE_STATION.get()).build(null));
    public static final RegistryObject<BlockEntityType<DNARecombinatorBlockEntity>> DNA_RECOMBINATOR_ENTITY = TILE_ENTITIES.register("dna_recombinator",
            () -> BlockEntityType.Builder.of(DNARecombinatorBlockEntity::new, BlockInit.DNA_RECOMBINATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<IncubatorBlockEntity>> INCUBATOR_ENTITY = TILE_ENTITIES.register("incubator",
            () -> BlockEntityType.Builder.of(IncubatorBlockEntity::new, BlockInit.INCUBATOR.get()).build(null));

    //Dino Care
    public static final RegistryObject<BlockEntityType<GroundFeederBlockEntity>> GROUND_FEEDER_ENTITY = TILE_ENTITIES.register("ground_feeder",
            () -> BlockEntityType.Builder.of(GroundFeederBlockEntity::new, BlockInit.GROUND_FEEDER.get()).build(null));

    //Other
    public static final RegistryObject<BlockEntityType<PetrifiedSignTileEntity>> PETRIFIED_SIGN_ENTITY = TILE_ENTITIES.register("petrified_sign",
            () -> BlockEntityType.Builder.of(PetrifiedSignTileEntity::new, BlockInit.PETRIFIED_SIGN.get(), BlockInit.PETRIFIED_SIGN_WALL.get()).build(null));
}
