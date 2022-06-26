package com.kangalia.projectdinosaur.core.util;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.AphanerammaEntity;
import com.kangalia.projectdinosaur.common.entity.creature.AustralovenatorEntity;
import com.kangalia.projectdinosaur.common.entity.creature.CompsognathusEntity;
import com.kangalia.projectdinosaur.core.init.EntityInit;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProjectDinosaur.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void AddEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityInit.APHANERAMMA.get(), AphanerammaEntity.setCustomAttributes().build());
        event.put(EntityInit.AUSTRALOVENATOR.get(), AustralovenatorEntity.setCustomAttributes().build());
        event.put(EntityInit.COMPSOGNATHUS.get(), CompsognathusEntity.setCustomAttributes().build());
    }
}
