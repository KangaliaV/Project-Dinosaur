package com.kangalia.projectdinosaur.intergration.jei;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.core.data.recipes.*;
import mezz.jei.api.recipe.RecipeType;

public class RecipeTypes {

    public static final RecipeType<ExcavatingRecipe> EXCAVATING_TYPE = RecipeType.create(ProjectDinosaur.MODID, "excavating", ExcavatingRecipe.class);
    public static final RecipeType<ExtractingRecipe> EXTRACTING_TYPE = RecipeType.create(ProjectDinosaur.MODID, "extracting", ExtractingRecipe.class);
    public static final RecipeType<GrowingRecipe> GROWING_TYPE = RecipeType.create(ProjectDinosaur.MODID, "growing", GrowingRecipe.class);
    public static final RecipeType<IncubatingRecipe> INCUBATING_TYPE = RecipeType.create(ProjectDinosaur.MODID, "incubating", IncubatingRecipe.class);
    public static final RecipeType<RecombinatingRecipe> RECOMBINATING_TYPE = RecipeType.create(ProjectDinosaur.MODID, "recombinating", RecombinatingRecipe.class);

}
