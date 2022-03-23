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
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FossilExcavatorTileEntity extends TileEntity implements ITickableTileEntity {

    static final int WORK_TIME = 3 * 20;
    private int progress = 0;
    private int inputIndex;
    Inventory inventory;

    private final NonNullList<ItemStack> items;
    private NonNullList<ItemStack> stacks = NonNullList.withSize(13, ItemStack.EMPTY);

    private final RandomNumGen rng = new RandomNumGen();

    public List<Integer> range(int start, int end) {
        return IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public FossilExcavatorTileEntity() {
        super(TileEntitiesInit.FOSSIL_EXCAVATOR_ENTITY.get());
        this.items = NonNullList.withSize(12, ItemStack.EMPTY);
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        this.progress = nbt.getInt("Progress");
        super.load(state, nbt);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());
        compound.putInt("Progress", this.progress);
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
                if (slot >= 1 && slot < 7) {
                    return stack.getItem() == BlockInit.ENCASED_ALPINE_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.ENCASED_AQUATIC_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.ENCASED_ARID_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.ENCASED_FROZEN_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.ENCASED_GRASSLAND_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.ENCASED_TEMPERATE_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.ENCASED_TROPICAL_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.ENCASED_WETLAND_ROCK_FOSSIL.get().asItem();
                }
                if (slot >= 7 && slot < 13) {
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
                if (slot == 0) {
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

    @Override
    public void tick() {
        if (this.level == null || level.isClientSide) {
            return;
        }
        //Potentially put the entirety of 'doExcavate' in here, like it is for AbstractFurnaceTileEntity.
        //for (int o = 7; o < 13; o++) {
            //doExcavate(selectedRecipe);
        //}
        if (this.canExcavate()) {
            if (progress < WORK_TIME) {
                ++progress;
            }
            if (progress == WORK_TIME) {
                progress = 0;
                this.doExcavate();
            }
        } else {
            progress = 0;
        }
        setChanged();
    }

    private boolean canExcavate() {
        System.out.println("Start canExcavate");
        int outputIndex = -1;
        this.inputIndex = -1;
        boolean flag = false;
        ItemStack inputSlot = ItemStack.EMPTY;
        ItemStack outputSlot = ItemStack.EMPTY;
        for (int slot = 1; slot < 7; slot++) {
            inputSlot = itemHandler.getStackInSlot(slot);
            if(!inputSlot.isEmpty()) {
                this.inputIndex = slot;
                System.out.println("Input Slot Not Empty");
                flag = true;
                break;
            }
        }
        System.out.println("inputIndex Check = "+inputIndex);
        if (inputIndex == -1 || !flag) {
            return false;
        } else {
            for (int slot = 7; slot < 13; slot++) {
                outputSlot = itemHandler.getStackInSlot(slot);
                System.out.println("inputSlot = "+inputSlot+". outputSlot = "+outputSlot);
                if(outputSlot.isEmpty()) {
                    outputIndex = slot;
                    System.out.println("outputSlot is Empty");
                    break;
                } /*else if (ItemStack.isSame(inputSlot, outputSlot) && outputSlot.getCount() <64) {
                    outputIndex = slot;
                    System.out.println("outputSlot isSame and < 64");
                    break;
                }*/
            }
            return outputIndex != -1 && this.inputIndex != -1;
        }
    }

    @Nullable
    public ExcavatingRecipe craft() {
        inventory = new Inventory(itemHandler.getSlots());
        //System.out.println("Before "+inventory);
        for (int i = 0; i < 7; i++) {
            //Adds everything at once, which when there's more than a stack of fossils between all six slots, this breaks the recipe check. Either needs to be fixed, or make only one input slot.
            inventory.addItem(itemHandler.getStackInSlot(i));
            System.out.println("After Inputs = " + inventory);

            List<ExcavatingRecipe> recipes = level.getRecipeManager().getRecipesFor(RecipeInit.EXCAVATING_RECIPE, inventory, level);
            System.out.println("Get Recipes = " + recipes);

            if (!recipes.isEmpty()) {
                System.out.println("Recipe Start");
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
                    System.out.println("Craft = "+recipes.get(recipeIndex));
                    inventory.removeAllItems();
                    System.out.println("After Craft = " + inventory);
                    return recipes.get(recipeIndex);
                }

            }
        }
        return null;
    }

    private ItemStack getOutput(@Nullable ExcavatingRecipe selectedRecipe) {
        if (selectedRecipe != null) {
            craft();
            System.out.println("Get Output = "+selectedRecipe.getResultItem());
            return selectedRecipe.getResultItem();
        }
        return ItemStack.EMPTY;
    }

    public void doExcavate() {
        assert this.level != null;

        if (this.canExcavate()) {
            ExcavatingRecipe selectedRecipe = craft();
            ItemStack input = itemHandler.getStackInSlot(inputIndex);
            ItemStack output = getOutput(selectedRecipe);
            System.out.println("Input = "+input+". Slot = " +inputIndex+". Output = "+output);

            if (!output.isEmpty()) {
                for (int slot = 7; slot < 13; slot++) {
                    ItemStack stack = itemHandler.getStackInSlot(slot);
                    System.out.println("Stack = "+stack+". Output = "+output);
                    if (stack.isEmpty()) {
                        itemHandler.insertItem(slot, output, false);

                        System.out.println("Insert");
                        break;
                    } else {
                        if (ItemStack.isSame(stack, output) && stack.getCount() + 1 < 64) {
                            stack.grow(1);
                            System.out.println("Grow");
                            break;
                        }
                    }
                }
            }
            itemHandler.extractItem(inputIndex, 1, false);
        }
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT tags = this.getUpdateTag();
        ItemStackHelper.saveAllItems(tags, this.items);
        return new SUpdateTileEntityPacket(this.worldPosition, 1, tags);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT tags = super.getUpdateTag();
        tags.putInt("Progress", this.progress);
        return tags;
    }
}