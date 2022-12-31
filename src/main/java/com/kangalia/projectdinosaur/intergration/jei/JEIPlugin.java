package com.kangalia.projectdinosaur.intergration.jei;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.core.data.recipes.*;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(ProjectDinosaur.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new ExcavatingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new ExtractingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new GrowingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new IncubatingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new RecombinatingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<ExcavatingRecipe> excavatingRecipes = recipeManager.getAllRecipesFor(ExcavatingRecipe.ExcavatingRecipeType.INSTANCE).stream().toList();
        List<ExtractingRecipe> extractingRecipes = recipeManager.getAllRecipesFor(ExtractingRecipe.ExtractingRecipeType.INSTANCE).stream().toList();
        List<GrowingRecipe> growingRecipes = recipeManager.getAllRecipesFor(GrowingRecipe.GrowingRecipeType.INSTANCE).stream().toList();
        List<IncubatingRecipe> incubatingRecipes = recipeManager.getAllRecipesFor(IncubatingRecipe.IncubatingRecipeType.INSTANCE).stream().toList();
        List<RecombinatingRecipe> recombinatingRecipes = recipeManager.getAllRecipesFor(RecombinatingRecipe.RecombinatingRecipeType.INSTANCE).stream().toList();

        registration.addRecipes(RecipeTypes.EXCAVATING_TYPE, excavatingRecipes);
        registration.addRecipes(RecipeTypes.EXTRACTING_TYPE, extractingRecipes);
        registration.addRecipes(RecipeTypes.GROWING_TYPE, growingRecipes);
        registration.addRecipes(RecipeTypes.INCUBATING_TYPE, incubatingRecipes);
        registration.addRecipes(RecipeTypes.RECOMBINATING_TYPE, recombinatingRecipes);
    }
}
