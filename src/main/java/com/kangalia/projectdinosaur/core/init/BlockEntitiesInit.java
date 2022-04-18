package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.tileentity.FossilExcavatorBlockEntity;
import com.kangalia.projectdinosaur.common.tileentity.PetrifiedSignTileEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockEntitiesInit {

    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, ProjectDinosaur.MODID);

    public static final RegistryObject<BlockEntityType<FossilExcavatorBlockEntity>> FOSSIL_EXCAVATOR_ENTITY = TILE_ENTITIES.register("fossil_excavator",
            () -> BlockEntityType.Builder.of(FossilExcavatorBlockEntity::new, BlockInit.FOSSIL_EXCAVATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<PetrifiedSignTileEntity>> PETRIFIED_SIGN_ENTITY = TILE_ENTITIES.register("petrified_sign",
            () -> BlockEntityType.Builder.of(PetrifiedSignTileEntity::new, BlockInit.PETRIFIED_SIGN.get(), BlockInit.PETRIFIED_SIGN_WALL.get()).build(null));
}
