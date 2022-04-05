package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.tileentity.FossilExcavatorTileEntity;
import com.kangalia.projectdinosaur.common.tileentity.PetrifiedSignTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntitiesInit {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ProjectDinosaur.MODID);

    public static final RegistryObject<TileEntityType<FossilExcavatorTileEntity>> FOSSIL_EXCAVATOR_ENTITY = TILE_ENTITIES.register("fossil_excavator",
            () -> TileEntityType.Builder.of(FossilExcavatorTileEntity::new, BlockInit.FOSSIL_EXCAVATOR.get()).build(null));
    public static final RegistryObject<TileEntityType<PetrifiedSignTileEntity>> PETRIFIED_SIGN_ENTITY = TILE_ENTITIES.register("petrified_sign",
            () -> TileEntityType.Builder.of(PetrifiedSignTileEntity::new, BlockInit.PETRIFIED_SIGN.get(), BlockInit.PETRIFIED_SIGN_WALL.get()).build(null));
}
