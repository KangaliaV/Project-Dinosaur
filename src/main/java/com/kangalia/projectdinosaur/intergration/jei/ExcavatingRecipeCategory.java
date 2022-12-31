package com.kangalia.projectdinosaur.intergration.jei;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.core.data.recipes.ExcavatingRecipe;
import com.kangalia.projectdinosaur.core.init.BlockInit;
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

public class ExcavatingRecipeCategory implements IRecipeCategory<ExcavatingRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(ProjectDinosaur.MODID, "fossil_excavating");
    public final static ResourceLocation TEXTURE = new ResourceLocation(ProjectDinosaur.MODID, "textures/gui/fossil_excavator.png");

    private final IDrawable background;
    private final IDrawable icon;

    public ExcavatingRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockInit.FOSSIL_EXCAVATOR.get()));
    }

    @Override
    public RecipeType<ExcavatingRecipe> getRecipeType() {
        return RecipeTypes.EXCAVATING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("screen.projectdinosaur.fossil_excavator");
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
    public void setRecipe(IRecipeLayoutBuilder builder, ExcavatingRecipe recipe, IFocusGroup focuses) {
        //Chisel
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 16).addIngredients(recipe.getIngredients().get(0));
        //Other Inputs
        builder.addSlot(RecipeIngredientRole.INPUT, 29, 16).addIngredients(recipe.getIngredients().get(1));
        //Ouputs
        builder.addSlot(RecipeIngredientRole.OUTPUT, 113, 16).addItemStack(recipe.getResultItem());

    }
}
