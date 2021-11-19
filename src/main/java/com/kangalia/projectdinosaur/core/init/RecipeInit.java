package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.data.recipes.ExcavatingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeInit {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ProjectDinosaur.MODID);

    //Fossil Excavator Recipe
    public static final RegistryObject<ExcavatingRecipe.Serializer> EXCAVATING_SERIALIZER = RECIPE_SERIALIZER.register("excavating", ExcavatingRecipe.Serializer::new);
    public static IRecipeType<ExcavatingRecipe> EXCAVATING_RECIPE = new ExcavatingRecipe.ExcavatingRecipeType();

    public static void register(IEventBus bus) {
        RECIPE_SERIALIZER.register(bus);

        Registry.register(Registry.RECIPE_TYPE, ExcavatingRecipe.TYPE_ID, EXCAVATING_RECIPE);
    }
}
