package com.kangalia.projectdinosaur.intergration.jei;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.core.data.recipes.RecombinatingRecipe;
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
import net.minecraft.ChatFormatting;
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
        this.background = helper.createDrawable(TEXTURE, 10, 10, 150, 65);
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
        if (recipe.getResultItem().is(ItemInit.ROTTEN_EGG.get())) {
            return;
        }
        //DNA
        builder.addSlot(RecipeIngredientRole.INPUT, 70, 6).addIngredients(recipe.getIngredients().get(0));
        //Eggs
        builder.addSlot(RecipeIngredientRole.INPUT, 39, 6).addIngredients(Ingredient.of(Items.EGG));
        builder.addSlot(RecipeIngredientRole.INPUT, 19, 24).addIngredients(Ingredient.of(Items.EGG));
        builder.addSlot(RecipeIngredientRole.INPUT, 39, 42).addIngredients(Ingredient.of(Items.EGG));
        //Ouputs
        builder.addSlot(RecipeIngredientRole.OUTPUT, 101, 6).addItemStack(ItemInit.ROTTEN_EGG.get().getDefaultInstance()).addTooltipCallback((recipeSlotView, tooltip) -> {
            int weight = recipe.getWeight();
            if (weight != 10) {
                tooltip.add(1, Component.translatable("data.projectdinosaur.weight", 60)
                        .withStyle(ChatFormatting.GRAY));
            }
        });
        builder.addSlot(RecipeIngredientRole.OUTPUT, 121, 24).addItemStack(recipe.getResultItem()).addTooltipCallback((recipeSlotView, tooltip) -> {
            int weight = recipe.getWeight();
            if (weight != 10) {
                tooltip.add(1, Component.translatable("data.projectdinosaur.weight", (weight * 10))
                        .withStyle(ChatFormatting.GRAY));
            }
        });
        builder.addSlot(RecipeIngredientRole.OUTPUT, 101, 42).addItemStack(ItemInit.ROTTEN_EGG.get().getDefaultInstance()).addTooltipCallback((recipeSlotView, tooltip) -> {
            int weight = recipe.getWeight();
            if (weight != 10) {
                tooltip.add(1, Component.translatable("data.projectdinosaur.weight", 60)
                        .withStyle(ChatFormatting.GRAY));
            }
        });



    }
}
