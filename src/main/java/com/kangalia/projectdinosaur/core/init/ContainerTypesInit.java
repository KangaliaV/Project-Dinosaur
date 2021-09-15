package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.container.FossilExcavatorContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypesInit {

    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPE = DeferredRegister.create(ForgeRegistries.CONTAINERS, ProjectDinosaur.MODID);

    public static final RegistryObject<ContainerType<FossilExcavatorContainer>> FOSSIL_EXCAVATOR_CONTAINER_TYPE = CONTAINER_TYPE.register("fossil_excavator", () -> IForgeContainerType.create(FossilExcavatorContainer::new));

}
