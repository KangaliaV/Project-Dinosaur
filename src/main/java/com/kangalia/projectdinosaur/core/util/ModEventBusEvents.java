package com.kangalia.projectdinosaur.core.util;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.entity.creature.AphanerammaEntity;
import com.kangalia.projectdinosaur.common.entity.creature.AustralovenatorEntity;
import com.kangalia.projectdinosaur.common.entity.creature.CompsognathusEntity;
import com.kangalia.projectdinosaur.core.data.recipes.ExcavatingRecipe;
import com.kangalia.projectdinosaur.core.data.recipes.ExtractingRecipe;
import com.kangalia.projectdinosaur.core.data.recipes.IncubatingRecipe;
import com.kangalia.projectdinosaur.core.data.recipes.RecombinatingRecipe;
import com.kangalia.projectdinosaur.core.init.EntityInit;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
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

    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, ExcavatingRecipe.ExcavatingRecipeType.ID, ExcavatingRecipe.ExcavatingRecipeType.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, ExtractingRecipe.ExtractingRecipeType.ID, ExtractingRecipe.ExtractingRecipeType.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, RecombinatingRecipe.RecombinatingRecipeType.ID, RecombinatingRecipe.RecombinatingRecipeType.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, IncubatingRecipe.IncubatingRecipeType.ID, IncubatingRecipe.IncubatingRecipeType.INSTANCE);
    }
}
