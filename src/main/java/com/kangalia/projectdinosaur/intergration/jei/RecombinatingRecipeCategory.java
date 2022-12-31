package com.kangalia.projectdinosaur.intergration.jei;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.core.data.recipes.RecombinatingRecipe;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class RecombinatingRecipeCategory implements IRecipeCategory<RecombinatingRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(ProjectDinosaur.MODID, "dna_recombinating");
    public final static ResourceLocation TEXTURE = new ResourceLocation(ProjectDinosaur.MODID, "textures/gui/dna_recombinator.png");

    private final IDrawable background;
    private final IDrawable icon;

    public RecombinatingRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockInit.DNA_RECOMBINATOR.get()));
    }

    @Override
    public RecipeType<RecombinatingRecipe> getRecipeType() {
        return RecipeTypes.RECOMBINATING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("screen.projectdinosaur.dna_recombinator");
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
    public void setRecipe(IRecipeLayoutBuilder builder, RecombinatingRecipe recipe, IFocusGroup focuses) {
        //DNA
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 16).addIngredients(recipe.getIngredients().get(0));
        //Eggs
        builder.addSlot(RecipeIngredientRole.INPUT, 49, 16).addIngredients(Ingredient.of(Items.EGG));
        builder.addSlot(RecipeIngredientRole.INPUT, 29, 34).addIngredients(Ingredient.of(Items.EGG));
        builder.addSlot(RecipeIngredientRole.INPUT, 49, 52).addIngredients(Ingredient.of(Items.EGG));
        //Ouputs
        builder.addSlot(RecipeIngredientRole.OUTPUT, 111, 16).addItemStack(recipe.getResultItem());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 131, 34).addItemStack(recipe.getResultItem());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 111, 52).addItemStack(recipe.getResultItem());



    }
}
