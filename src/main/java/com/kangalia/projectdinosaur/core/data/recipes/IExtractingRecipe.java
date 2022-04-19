package com.kangalia.projectdinosaur.core.data.recipes;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public interface IExtractingRecipe extends Recipe<Container> {

    ResourceLocation TYPE_ID = new ResourceLocation(ProjectDinosaur.MODID, "extracting");

    @Override
    default RecipeType<?> getType() {
        return Registry.RECIPE_TYPE.getOptional(TYPE_ID).get();
    }

    @Override
    default boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    default boolean isSpecial() {
        return true;
    }
}
