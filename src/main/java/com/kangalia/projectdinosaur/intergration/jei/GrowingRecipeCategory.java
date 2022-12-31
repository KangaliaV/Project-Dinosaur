package com.kangalia.projectdinosaur.intergration.jei;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.core.data.recipes.GrowingRecipe;
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

public class GrowingRecipeCategory implements IRecipeCategory<GrowingRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(ProjectDinosaur.MODID, "embryonic_growing");
    public final static ResourceLocation TEXTURE = new ResourceLocation(ProjectDinosaur.MODID, "textures/gui/embryonic_womb.png");

    private final IDrawable background;
    private final IDrawable icon;

    public GrowingRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BlockInit.EMBRYONIC_WOMB.get()));
    }

    @Override
    public RecipeType<GrowingRecipe> getRecipeType() {
        return RecipeTypes.GROWING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("screen.projectdinosaur.embryonic_womb");
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
    public void setRecipe(IRecipeLayoutBuilder builder, GrowingRecipe recipe, IFocusGroup focuses) {
        //Embryo
        builder.addSlot(RecipeIngredientRole.INPUT, 96, 22).addIngredients(recipe.getIngredients().get(0));
        //Nutrient Goo
        builder.addSlot(RecipeIngredientRole.INPUT, 96, 49).addIngredients(Ingredient.of(ItemInit.NUTRIENT_GOO.get()));
        //Ouput
        builder.addSlot(RecipeIngredientRole.OUTPUT, 132, 36).addItemStack(recipe.getResultItem());

    }
}
