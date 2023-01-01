package com.kangalia.projectdinosaur.intergration.jei;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.core.data.recipes.ExtractingRecipe;
import com.kangalia.projectdinosaur.core.init.BlockInit;
import com.kangalia.projectdinosaur.core.init.ItemInit;
import com.mojang.blaze3d.platform.TextureUtil;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
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
        this.background = helper.createDrawable(TEXTURE, 10, 10, 150, 65);
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
        builder.addSlot(RecipeIngredientRole.INPUT, 70, 6).addIngredients(Ingredient.of(ItemInit.SYRINGE.get()));
        //Other Inputs
        builder.addSlot(RecipeIngredientRole.INPUT, 19, 6).addIngredients(recipe.getIngredients().get(1));
        //Ouputs
        builder.addSlot(RecipeIngredientRole.OUTPUT, 103, 6).addItemStack(recipe.getResultItem())
                .addTooltipCallback((recipeSlotView, tooltip) -> {
            int weight = recipe.getWeight();
            if (weight != 10) {
                tooltip.add(1, Component.translatable("data.projectdinosaur.weight", (weight * 10))
                        .withStyle(ChatFormatting.GRAY));
            }
        });

    }
}
