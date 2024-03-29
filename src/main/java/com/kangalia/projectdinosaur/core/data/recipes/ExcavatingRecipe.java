package com.kangalia.projectdinosaur.core.data.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.core.init.BlockInit;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class ExcavatingRecipe implements Recipe<Container> {

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> inputs;
    private final Integer weight;

    public ExcavatingRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> inputs, Integer weight) {
        this.id = id;
        this.output = output;
        this.inputs = inputs;
        this.weight = weight;
    }

    @Override
    public boolean matches(Container inventory, Level world) {
        if (world.isClientSide) {
            return false;
        }
        if(inputs.get(0).test(inventory.getItem(0))) {
            return inputs.get(1).test(inventory.getItem(1));
        }
        return false;
    }

    @Override
    public ItemStack assemble(Container p_44001_, RegistryAccess p_267165_) {
        return output;
    }

    public Integer getWeight() { return this.weight;}

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return inputs;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess p_267052_) {
        return getResultItem();
    }

    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ExcavatingRecipe.Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return ExcavatingRecipeType.INSTANCE;
    }

    public ItemStack getIcon() {
        return new ItemStack(BlockInit.FOSSIL_EXCAVATOR.get());
    }

    public static class ExcavatingRecipeType implements RecipeType<ExcavatingRecipe> {
        private ExcavatingRecipeType() {}
        public static final ExcavatingRecipeType INSTANCE = new ExcavatingRecipeType();
        public static final String ID = "excavating";
    }

    public static class Serializer implements RecipeSerializer<ExcavatingRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(ProjectDinosaur.MODID, "excavating");

        @Override
        public ExcavatingRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            int weight = Integer.parseInt(GsonHelper.getAsString(json, "weight"));
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new ExcavatingRecipe(id, output, inputs, weight);
        }

        @Nullable
        @Override
        public ExcavatingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack output = buffer.readItem();
            return new ExcavatingRecipe(id, output, inputs, null);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, ExcavatingRecipe recipe) {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buffer);
            }
            buffer.writeItemStack(recipe.getResultItem(), false);
        }

    }
}
