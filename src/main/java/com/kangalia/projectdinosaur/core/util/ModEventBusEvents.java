package com.kangalia.projectdinosaur.core.util;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.kangalia.projectdinosaur.common.entity.creature.*;
import com.kangalia.projectdinosaur.common.entity.parts.PrehistoricPart;
import com.kangalia.projectdinosaur.core.data.recipes.ExcavatingRecipe;
import com.kangalia.projectdinosaur.core.data.recipes.ExtractingRecipe;
import com.kangalia.projectdinosaur.core.data.recipes.IncubatingRecipe;
import com.kangalia.projectdinosaur.core.data.recipes.RecombinatingRecipe;
import com.kangalia.projectdinosaur.core.init.EntityInit;
import com.kangalia.projectdinosaur.core.init.ItemInit;
import net.minecraft.core.Registry;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.entity.PartEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ProjectDinosaur.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void AddEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityInit.APHANERAMMA.get(), AphanerammaEntity.setCustomAttributes().build());
        event.put(EntityInit.AUSTRALOVENATOR.get(), AustralovenatorEntity.setCustomAttributes().build());
        event.put(EntityInit.GASTORNIS.get(), GastornisEntity.setCustomAttributes().build());
        event.put(EntityInit.MEGANEURA.get(), MeganeuraEntity.setCustomAttributes().build());
        event.put(EntityInit.SCELIDOSAURUS.get(), ScelidosaurusEntity.setCustomAttributes().build());
        event.put(EntityInit.TRILOBITE.get(), TrilobiteEntity.setCustomAttributes().build());
    }
}
