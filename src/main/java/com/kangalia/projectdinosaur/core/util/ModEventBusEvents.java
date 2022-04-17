package com.kangalia.projectdinosaur.core.util;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.AphanerammaEntity;
import com.kangalia.projectdinosaur.common.item.ModSpawnEggItem;
import com.kangalia.projectdinosaur.core.init.EntityInit;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProjectDinosaur.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void AddEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityInit.APHANERAMMA.get(), AphanerammaEntity.setCustomAttributes().build());
    }

    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
        //ModSpawnEggItem.initSpawnEggs();
    }
}
