package com.kangalia.projectdinosaur.common.block.fossilexcavator;

import com.kangalia.projectdinosaur.common.data.recipes.ExcavatingRecipe;
import com.kangalia.projectdinosaur.core.init.BlockInit;
import com.kangalia.projectdinosaur.core.init.ItemInit;
import com.kangalia.projectdinosaur.core.init.RecipeInit;
import com.kangalia.projectdinosaur.core.init.TileEntitiesInit;
import com.kangalia.projectdinosaur.core.util.RandomNumGen;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FossilExcavatorTileEntity extends TileEntity implements ITickableTileEntity {

    public static final int CHISEL_SLOTS = 1;
    public static final int INPUT_SLOTS = 6;
    public static final int OUTPUT_SLOTS = 6;
    public static final int TOTAL_SLOTS = CHISEL_SLOTS + INPUT_SLOTS + OUTPUT_SLOTS;

    private final RandomNumGen rng = new RandomNumGen();

    public List<Integer> range(int start, int end) {
        return IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public FossilExcavatorTileEntity(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public FossilExcavatorTileEntity() {
        this(TileEntitiesInit.FOSSIL_EXCAVATOR_ENTITY.get());
    }


    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        super.load(state, nbt);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());
        return super.save(compound);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(13) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if (slot >= 0 && slot < 6) {
                    return stack.getItem() == BlockInit.ENCASED_ALPINE_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.ENCASED_AQUATIC_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.ENCASED_ARID_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.ENCASED_FROZEN_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.ENCASED_GRASSLAND_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.ENCASED_TEMPERATE_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.ENCASED_TROPICAL_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.ENCASED_WETLAND_ROCK_FOSSIL.get().asItem();
                }
                if (slot >= 6 && slot < 12) {
                    return stack.getItem() == ItemInit.ALPINE_ROCK_SPECIMEN.get() ||
                            stack.getItem() == ItemInit.AQUATIC_ROCK_SPECIMEN.get() ||
                            stack.getItem() == ItemInit.ARID_ROCK_SPECIMEN.get() ||
                            stack.getItem() == ItemInit.FROZEN_ROCK_SPECIMEN.get() ||
                            stack.getItem() == ItemInit.GRASSLAND_ROCK_SPECIMEN.get() ||
                            stack.getItem() == ItemInit.TEMPERATE_ROCK_SPECIMEN.get() ||
                            stack.getItem() == ItemInit.TROPICAL_ROCK_SPECIMEN.get() ||
                            stack.getItem() == ItemInit.WETLAND_ROCK_SPECIMEN.get() ||
                            stack.getItem() == Items.BONE.getItem() ||
                            stack.getItem() == Items.CLAY_BALL.getItem() ||
                            stack.getItem() == Items.FLINT.getItem() ||
                            stack.getItem() == Items.SNOWBALL.getItem() ||
                            stack.getItem() == Items.COAL.getItem() ||
                            stack.getItem() == Blocks.CLAY.asItem() ||
                            stack.getItem() == Blocks.COBBLESTONE.asItem() ||
                            stack.getItem() == Blocks.GRAVEL.asItem() ||
                            stack.getItem() == Blocks.PACKED_ICE.asItem() ||
                            stack.getItem() == Blocks.SAND.asItem();
                }
                if (slot == 12) {
                    return stack.getItem() == ItemInit.IRON_CHISEL.get() ||
                            stack.getItem() == ItemInit.DIAMOND_CHISEL.get() ||
                            stack.getItem() == ItemInit.NETHERITE_CHISEL.get();
                }
                return false;
            }

            @Override
            public int getSlotLimit(int slot) {
                return 64;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!isItemValid(slot, stack)) {
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }
        };
    }



    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }

        return super.getCapability(cap, side);
    }

    public void craft() {
        Inventory inventory = new Inventory(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.addItem(itemHandler.getStackInSlot(i));

            List<ExcavatingRecipe> recipes = level.getRecipeManager().getRecipesFor(RecipeInit.EXCAVATING_RECIPE, inventory, level);

            if (!recipes.isEmpty()) {
                ExcavatingRecipe selectedRecipe;

                if (recipes.size() == 1) {
                    selectedRecipe = recipes.get(0);
                } else {
                    int totalWeight = recipes.stream().map(r -> r.getWeight()).mapToInt(Integer::intValue).sum();
                    int[] weightArray = new int[totalWeight];

                    int pos = 0;
                    for (int j = 0; j < recipes.size(); j++) {
                        ExcavatingRecipe er = recipes.get(j);
                        int weight = er.getWeight();
                        for (int k = 0; k < weight; k++) {
                            weightArray[pos] = j;
                            pos++;
                        }
                    }
                    //Generate random number.
                    int randomNum = rng.nextInt(weightArray.length);
                    int recipeIndex = weightArray[randomNum];
                    //Select random recipe from (filtered) list.
                    selectedRecipe = recipes.get(recipeIndex);
                    ItemStack output = selectedRecipe.getResultItem();

                    itemHandler.extractItem(0, 1, false);
                    itemHandler.insertItem(6, output, false);

                    setChanged();
                }
            }
        }
    }

    @Override
    public void tick() {
        if (level.isClientSide) {
            return;
        }
        craft();
    }
}