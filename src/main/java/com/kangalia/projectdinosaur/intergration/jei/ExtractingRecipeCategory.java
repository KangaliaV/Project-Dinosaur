package com.kangalia.projectdinosaur.intergration.jei;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.core.data.recipes.ExtractingRecipe;
import com.kangalia.projectdinosaur.core.init.BlockInit;
import com.kangalia.projectdinosaur.core.init.ItemInit;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class ExtractingRecipeCategory implements IRecipeCategory<ExtractingRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(ProjectDinosaur.MODID, "core_extracting");
    public final static ResourceLocation TEXTURE = new ResourceLocation(ProjectDinosaur.MODID, "textures/gui/core_station.png");

    private final IDrawable background;
    private final IDrawable icon;

    public ExtractingRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockInit.CORE_STATION.get()));
    }

    @Override
    public RecipeType<ExtractingRecipe> getRecipeType() {
        return RecipeTypes.EXTRACTING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("screen.projectdinosaur.core_station");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ExtractingRecipe recipe, IFocusGroup focuses) {
        //Syringe
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 16).addIngredients(Ingredient.of(ItemInit.SYRINGE.get()));
        //Other Inputs
        builder.addSlot(RecipeIngredientRole.INPUT, 29, 16).addIngredients(recipe.getIngredients().get(1));
        //Ouputs
        builder.addSlot(RecipeIngredientRole.OUTPUT, 113, 16).addItemStack(recipe.getResultItem());

    }
}
