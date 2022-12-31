package com.kangalia.projectdinosaur.intergration.jei;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.core.data.recipes.IncubatingRecipe;
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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

public class IncubatingRecipeCategory implements IRecipeCategory<IncubatingRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(ProjectDinosaur.MODID, "egg_incubating");
    public final static ResourceLocation TEXTURE = new ResourceLocation(ProjectDinosaur.MODID, "textures/gui/incubator.png");

    private final IDrawable background;
    private final IDrawable icon;

    public IncubatingRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockInit.FOSSIL_EXCAVATOR.get()));
    }

    @Override
    public RecipeType<IncubatingRecipe> getRecipeType() {
        return RecipeTypes.INCUBATING_TYPE;
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
    public void setRecipe(IRecipeLayoutBuilder builder, IncubatingRecipe recipe, IFocusGroup focuses) {
        //Eggs
        builder.addSlot(RecipeIngredientRole.INPUT, 96, 22).addIngredients(recipe.getIngredients().get(0));
        //Hay
        builder.addSlot(RecipeIngredientRole.INPUT, 96, 49).addIngredients(Ingredient.of(Blocks.HAY_BLOCK));
        //Ouput
        builder.addSlot(RecipeIngredientRole.OUTPUT, 132, 36).addItemStack(recipe.getResultItem());

    }
}
