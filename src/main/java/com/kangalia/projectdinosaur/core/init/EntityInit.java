package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.*;
import com.kangalia.projectdinosaur.common.entity.PetrifiedBoatEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ProjectDinosaur.MODID);

    public static final RegistryObject<EntityType<PetrifiedBoatEntity>> PETRIFIED_BOAT = ENTITY_TYPES.register("petrified_boat",
            () -> EntityType.Builder.<PetrifiedBoatEntity>of(PetrifiedBoatEntity::new, MobCategory.MISC).sized(0.5f, 0.5f)
                    .build(new ResourceLocation(ProjectDinosaur.MODID, "petrified_boat").toString()));

    //Dinos
    public static final RegistryObject<EntityType<AphanerammaEntity>> APHANERAMMA = ENTITY_TYPES.register("aphaneramma",
            () -> EntityType.Builder.of(AphanerammaEntity::new, MobCategory.CREATURE)
                    .sized(0.7f, 0.3f)
                    .setTrackingRange(8)
                    .build(new ResourceLocation(ProjectDinosaur.MODID, "aphaneramma").toString()));

    public static final RegistryObject<EntityType<AustralovenatorEntity>> AUSTRALOVENATOR = ENTITY_TYPES.register("australovenator",
            () -> EntityType.Builder.of(AustralovenatorEntity::new, MobCategory.CREATURE)
                    .sized(1.1f, 1.6f)
                    .setTrackingRange(8)
                    .build(new ResourceLocation(ProjectDinosaur.MODID, "australovenator").toString()));

    public static final RegistryObject<EntityType<ScelidosaurusEntity>> SCELIDOSAURUS = ENTITY_TYPES.register("scelidosaurus",
            () -> EntityType.Builder.of(ScelidosaurusEntity::new, MobCategory.CREATURE)
                    .sized(0.9f, 1.1f)
                    .setTrackingRange(8)
                    .build(new ResourceLocation(ProjectDinosaur.MODID, "scelidosaurus").toString()));

    public static final RegistryObject<EntityType<TrilobiteEntity>> TRILOBITE = ENTITY_TYPES.register("trilobite",
            () -> EntityType.Builder.of(TrilobiteEntity::new, MobCategory.CREATURE)
                    .sized(0.6f, 0.3f)
                    .setTrackingRange(8)
                    .build(new ResourceLocation(ProjectDinosaur.MODID, "compsognathus").toString()));
}
