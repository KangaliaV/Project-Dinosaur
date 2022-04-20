package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.core.data.recipes.ExcavatingRecipe;
import com.kangalia.projectdinosaur.core.data.recipes.ExtractingRecipe;
import com.kangalia.projectdinosaur.core.data.recipes.RecombinatingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeInit {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ProjectDinosaur.MODID);

    public static final RegistryObject<RecipeSerializer<ExcavatingRecipe>> EXCAVATING_SERIALIZER = RECIPE_SERIALIZER.register("excavating", ExcavatingRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<ExtractingRecipe>> EXTRACTING_SERIALIZER = RECIPE_SERIALIZER.register("extracting", ExtractingRecipe.Serializer::new);
    public static final RegistryObject<RecipeSerializer<RecombinatingRecipe>> RECOMBINATING_SERIALIZER = RECIPE_SERIALIZER.register("recombinating", RecombinatingRecipe.Serializer::new);


    public static void register(IEventBus bus) {
        RECIPE_SERIALIZER.register(bus);
    }
}
