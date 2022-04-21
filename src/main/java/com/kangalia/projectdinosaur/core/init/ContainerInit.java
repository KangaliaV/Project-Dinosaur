package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.container.CoreStationContainer;
import com.kangalia.projectdinosaur.common.container.DNARecombinatorContainer;
import com.kangalia.projectdinosaur.common.container.FossilExcavatorContainer;
import com.kangalia.projectdinosaur.common.container.IncubatorContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ContainerInit {

    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, ProjectDinosaur.MODID);

    public static final RegistryObject<MenuType<FossilExcavatorContainer>> FOSSIL_EXCAVATOR_CONTAINER = CONTAINERS.register("fossil_excavator", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();
        return new FossilExcavatorContainer(windowId, world, pos, inv, inv.player);
    }));
    public static final RegistryObject<MenuType<CoreStationContainer>> CORE_STATION_CONTAINER = CONTAINERS.register("core_station_container", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();
        return new CoreStationContainer(windowId, world, pos, inv, inv.player);
    }));
    public static final RegistryObject<MenuType<DNARecombinatorContainer>> DNA_RECOMBINATOR_CONTAINER = CONTAINERS.register("dna_recombinator_container", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();
        return new DNARecombinatorContainer(windowId, world, pos, inv, inv.player);
    }));
    public static final RegistryObject<MenuType<IncubatorContainer>> INCUBATOR_CONTAINER = CONTAINERS.register("incubator_container", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();
        return new IncubatorContainer(windowId, world, pos, inv, inv.player);
    }));

}
