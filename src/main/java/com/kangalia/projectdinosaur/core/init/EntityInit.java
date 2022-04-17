package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.AphanerammaEntity;
import com.kangalia.projectdinosaur.common.entity.PetrifiedBoatEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityInit {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, ProjectDinosaur.MODID);

    public static final RegistryObject<EntityType<PetrifiedBoatEntity>> PETRIFIED_BOAT = ENTITY_TYPES.register("petrified_boat",
            () -> EntityType.Builder.<PetrifiedBoatEntity>of(PetrifiedBoatEntity::new, EntityClassification.MISC)
                    .sized(0.5f, 0.5f)
                    .build(new ResourceLocation(ProjectDinosaur.MODID, "petrified_boat").toString()));

    public static final RegistryObject<EntityType<AphanerammaEntity>> APHANERAMMA = ENTITY_TYPES.register("aphaneramma",
            () -> EntityType.Builder.of(AphanerammaEntity::new, EntityClassification.CREATURE)
                    .sized(0.9f, 0.3f)
                    .setTrackingRange(8)
                    .build(new ResourceLocation(ProjectDinosaur.MODID, "aphaneramma").toString()));
}
