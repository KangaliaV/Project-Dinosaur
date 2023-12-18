package com.kangalia.projectdinosaur.core.init;

import com.google.common.collect.ImmutableSet;
import com.kangalia.projectdinosaur.ProjectDinosaur;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class VillagerInit {

    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, ProjectDinosaur.MODID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, ProjectDinosaur.MODID);

    // POI TYPES
    public static final RegistryObject<PoiType> ARCHEOLOGIST_POI = POI_TYPES.register("archeologist_poi", () ->
            new PoiType(ImmutableSet.copyOf(BlockInit.FOSSIL_EXCAVATOR.get().getStateDefinition().getPossibleStates()),
                    1, 1));

    public static final RegistryObject<PoiType> PALEONTOLOGIST_POI = POI_TYPES.register("paleontologist_poi", () ->
            new PoiType(ImmutableSet.copyOf(BlockInit.DNA_RECOMBINATOR.get().getStateDefinition().getPossibleStates()),
                    1, 1));

    // PROFESSIONS

    public static final RegistryObject<VillagerProfession> ARCHEOLOGIST = VILLAGER_PROFESSIONS.register("archeologist", () ->
            new VillagerProfession("archeologist", holder -> holder.get() == ARCHEOLOGIST_POI.get(), holder -> holder.get() == ARCHEOLOGIST_POI.get(),
                    ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_MASON));

    public static final RegistryObject<VillagerProfession> PALEONTOLOGIST = VILLAGER_PROFESSIONS.register("paleontologist", () ->
            new VillagerProfession("paleontologist", holder -> holder.get() == PALEONTOLOGIST_POI.get(), holder -> holder.get() == PALEONTOLOGIST_POI.get(),
                    ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_MASON));
}
