package com.kangalia.projectdinosaur.common.data.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kangalia.projectdinosaur.core.init.BlockInit;
import com.kangalia.projectdinosaur.core.init.RecipeInit;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class ExcavatingRecipe implements IExcavatingRecipe {

    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> inputs;

    public ExcavatingRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> inputs) {
        this.id = id;
        this.output = output;
        this.inputs = inputs;
    }

    @Override
    public boolean matches(IInventory inventory, World world) {
        if(inputs.get(0).test(inventory.getItem(0))) {
            return inputs.get(1).test(inventory.getItem(1));
        }
        return false;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return inputs;
    }

    @Override
    public ItemStack assemble(IInventory inv) {
        return output;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return RecipeInit.EXCAVATING_SERIALIZER.get();
    }

    public ItemStack getIcon() {
        return new ItemStack(BlockInit.FOSSIL_EXCAVATOR.get());
    }

    public static class ExcavatingRecipeType implements IRecipeType<ExcavatingRecipe> {
        @Override
        public String toString() {
            return ExcavatingRecipe.TYPE_ID.toString();
        }
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ExcavatingRecipe> {

        @Override
        public ExcavatingRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack output = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "result"));
            JsonArray ingredients = JSONUtils.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new ExcavatingRecipe(id, output, inputs);
        }

        @Nullable
        @Override
        public ExcavatingRecipe fromNetwork(ResourceLocation id, PacketBuffer buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }

            ItemStack output = buffer.readItem();
            return new ExcavatingRecipe(id, output, inputs);
        }

        @Override
        public void toNetwork(PacketBuffer buffer, ExcavatingRecipe recipe) {
            buffer.writeInt(recipe.getIngredients().size());
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.toNetwork(buffer);
            }
            buffer.writeItemStack(recipe.getResultItem(), false);
        }
    }
}
