package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.core.data.recipes.ExcavatingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.core.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeInit {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ProjectDinosaur.MODID);

    //Fossil Excavator Recipe
    public static final RegistryObject<RecipeSerializer<ExcavatingRecipe>> EXCAVATING_SERIALIZER = RECIPE_SERIALIZER.register("excavating", ExcavatingRecipe.Serializer::new);

    public static void register(IEventBus bus) {
        RECIPE_SERIALIZER.register(bus);
    }
}
