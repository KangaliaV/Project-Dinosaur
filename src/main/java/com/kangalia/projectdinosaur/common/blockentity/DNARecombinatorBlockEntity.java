package com.kangalia.projectdinosaur.common.blockentity;

import com.kangalia.projectdinosaur.core.data.recipes.RecombinatingRecipe;
import com.kangalia.projectdinosaur.core.init.BlockEntitiesInit;
import com.kangalia.projectdinosaur.core.init.ItemInit;
import com.kangalia.projectdinosaur.core.util.RandomNumGen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class DNARecombinatorBlockEntity extends BlockEntity {

    static final int WORK_TIME = 30 * 20;
    private int progress = 0;
    SimpleContainer inventory;
    private final NonNullList<ItemStack> items;
    private final RandomNumGen rng = new RandomNumGen();
    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public DNARecombinatorBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockEntitiesInit.DNA_RECOMBINATOR_ENTITY.get(), blockPos, blockState);
        this.items = NonNullList.withSize(7, ItemStack.EMPTY);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        handler.invalidate();
    }

    @Override
    public void load(CompoundTag nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        this.progress = nbt.getInt("progress");
        super.load(nbt);
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        nbt.put("inv", itemHandler.serializeNBT());
        nbt.putInt("progress", this.progress);
        super.saveAdditional(nbt);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(7) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if (slot > 0 & slot < 7) {
                    return stack.getItem() == Items.EGG ||
                            stack.getItem() == ItemInit.ROTTEN_EGG.get() ||
                            stack.getItem() == ItemInit.AUSTRALOVENATOR_EGG_FERTILISED.get() ||
                            stack.getItem() == ItemInit.SCELIDOSAURUS_EGG_FERTILISED.get();
                }
                if (slot == 0) {
                    return stack.getItem() == ItemInit.APHANERAMMA_DNA.get() ||
                            stack.getItem() == ItemInit.AUSTRALOVENATOR_DNA.get() ||
                            stack.getItem() == ItemInit.SCELIDOSAURUS_DNA.get();
                }
                return false;

            }
            @Nonnull
            @Override
            public ItemStack insertItem(int slot, ItemStack stack , boolean simulate) {
                return(isItemValid(slot, stack)) ? super.insertItem(slot, stack, simulate) : stack;
            }

            //Hopper extraction code doesn't work. Needs to be worked on.
            @Nonnull
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                return super.extractItem(slot, amount, simulate);
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER && side != Direction.DOWN) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

    public void tick() {
        if (this.level == null) {
            return;
        }
        BlockState blockState = level.getBlockState(worldPosition);
        if (this.canRecombine()) {
            level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.POWERED, true), Block.UPDATE_ALL);
            if (progress < WORK_TIME) {
                ++progress;
                level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
                setChanged();
            }
            if (progress == WORK_TIME) {
                progress = 0;
                this.doRecombine();
            }
        } else {
            level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.POWERED, false), Block.UPDATE_ALL);
            progress = 0;
        }
        setChanged();
    }

    private boolean canRecombine() {
        ItemStack inputSlot = ItemStack.EMPTY;
        int counter = 0;
        for (int slot = 1; slot < 7; slot++) {
            inputSlot = itemHandler.getStackInSlot(slot);
            if (!inputSlot.isEmpty() && inputSlot.getItem() == Items.EGG) {
                ++counter;
            }
        }
        if (counter == 6) {
            ItemStack syringeSlot = itemHandler.getStackInSlot(0);
            if (!syringeSlot.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public RecombinatingRecipe craft() {
        inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < 7; i++) {
            inventory.addItem(itemHandler.getStackInSlot(i));
            List<RecombinatingRecipe> recipes = level.getRecipeManager().getRecipesFor(RecombinatingRecipe.RecombinatingRecipeType.INSTANCE, inventory, level);
            if (!recipes.isEmpty()) {
                RecombinatingRecipe selectedRecipe;
                if (recipes.size() == 1) {
                    selectedRecipe = recipes.get(0);
                } else {
                    int totalWeight = recipes.stream().map(r -> r.getWeight()).mapToInt(Integer::intValue).sum();
                    int[] weightArray = new int[totalWeight];
                    int pos = 0;
                    for (int j = 0; j < recipes.size(); j++) {
                        RecombinatingRecipe er = recipes.get(j);
                        int weight = er.getWeight();
                        for (int k = 0; k < weight; k++) {
                            weightArray[pos] = j;
                            pos++;
                        }
                    }
                    int randomNum = rng.nextInt(weightArray.length);
                    int recipeIndex = weightArray[randomNum];
                    inventory.removeAllItems();
                    return recipes.get(recipeIndex);
                }
                return selectedRecipe;
            }
        }
        return null;
    }

    private ItemStack getOutput(@Nullable RecombinatingRecipe selectedRecipe) {
        if (selectedRecipe != null) {
            return selectedRecipe.getResultItem();
        }
        return ItemStack.EMPTY;
    }

    public void doRecombine() {
        assert this.level != null;
        ItemStack dna = itemHandler.getStackInSlot(0);
        if (this.canRecombine()) {
            RecombinatingRecipe selectedRecipe1 = craft();
            ItemStack output1 = getOutput(selectedRecipe1);
            RecombinatingRecipe selectedRecipe2 = craft();
            ItemStack output2 = getOutput(selectedRecipe2);
            RecombinatingRecipe selectedRecipe3 = craft();
            ItemStack output3 = getOutput(selectedRecipe3);
            RecombinatingRecipe selectedRecipe4 = craft();
            ItemStack output4 = getOutput(selectedRecipe4);
            RecombinatingRecipe selectedRecipe5 = craft();
            ItemStack output5 = getOutput(selectedRecipe5);
            RecombinatingRecipe selectedRecipe6 = craft();
            ItemStack output6 = getOutput(selectedRecipe6);
            if (!output1.isEmpty()) {
                ItemStack stack = itemHandler.getStackInSlot(1);
                stack.shrink(1);
                itemHandler.insertItem(1, output1, false);
            }
            if (!output2.isEmpty()) {
                ItemStack stack = itemHandler.getStackInSlot(2);
                stack.shrink(1);
                itemHandler.insertItem(2, output2, false);
            }
            if (!output3.isEmpty()) {
                ItemStack stack = itemHandler.getStackInSlot(3);
                stack.shrink(1);
                itemHandler.insertItem(3, output3, false);
            }
            if (!output4.isEmpty()) {
                ItemStack stack = itemHandler.getStackInSlot(4);
                stack.shrink(1);
                itemHandler.insertItem(4, output4, false);
            }
            if (!output5.isEmpty()) {
                ItemStack stack = itemHandler.getStackInSlot(5);
                stack.shrink(1);
                itemHandler.insertItem(5, output5, false);
            }
            if (!output6.isEmpty()) {
                ItemStack stack = itemHandler.getStackInSlot(6);
                stack.shrink(1);
                itemHandler.insertItem(6, output6, false);
            }
            dna.shrink(1);
        }
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag tags = this.getUpdateTag();
        ContainerHelper.saveAllItems(tags, this.items);
        tags.putInt("progress", this.progress);
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithFullMetadata();
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();
        if (tag.contains("progress")) {
            progress = tag.getInt("progress");
            this.getPersistentData().putInt("progress", this.progress);
        }
    }
}
