package com.kangalia.projectdinosaur.common.blockentity;

import com.kangalia.projectdinosaur.core.data.recipes.ExcavatingRecipe;
import com.kangalia.projectdinosaur.core.init.ItemInit;
import com.kangalia.projectdinosaur.core.init.BlockEntitiesInit;
import com.kangalia.projectdinosaur.core.util.RandomNumGen;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class FossilExcavatorBlockEntity extends BlockEntity {

    static final int WORK_TIME = 5 * 20;
    private int progress = 0;
    private int inputIndex;
    SimpleContainer inventory;
    private final NonNullList<ItemStack> items;
    private final RandomNumGen rng = new RandomNumGen();
    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public FossilExcavatorBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockEntitiesInit.FOSSIL_EXCAVATOR_ENTITY.get(), blockPos, blockState);
        this.items = NonNullList.withSize(13, ItemStack.EMPTY);
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
        return new ItemStackHandler(13) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if (slot >= 1 && slot < 7) {
                    return stack.getItem() == ItemInit.ALPINE_ROCK_FRAGMENT.get().asItem() ||
                            stack.getItem() == ItemInit.AQUATIC_ROCK_FRAGMENT.get().asItem() ||
                            stack.getItem() == ItemInit.ARID_ROCK_FRAGMENT.get().asItem() ||
                            stack.getItem() == ItemInit.FROZEN_ROCK_FRAGMENT.get().asItem() ||
                            stack.getItem() == ItemInit.GRASSLAND_ROCK_FRAGMENT.get().asItem() ||
                            stack.getItem() == ItemInit.TEMPERATE_ROCK_FRAGMENT.get().asItem() ||
                            stack.getItem() == ItemInit.TROPICAL_ROCK_FRAGMENT.get().asItem() ||
                            stack.getItem() == ItemInit.WETLAND_ROCK_FRAGMENT.get().asItem() ||
                            stack.getItem() == ItemInit.ALPINE_CRYSTALLISED_FRAGMENT.get().asItem() ||
                            stack.getItem() == ItemInit.AQUATIC_CRYSTALLISED_FRAGMENT.get().asItem() ||
                            stack.getItem() == ItemInit.ARID_CRYSTALLISED_FRAGMENT.get().asItem() ||
                            stack.getItem() == ItemInit.FROZEN_CRYSTALLISED_FRAGMENT.get().asItem() ||
                            stack.getItem() == ItemInit.GRASSLAND_CRYSTALLISED_FRAGMENT.get().asItem() ||
                            stack.getItem() == ItemInit.TEMPERATE_CRYSTALLISED_FRAGMENT.get().asItem() ||
                            stack.getItem() == ItemInit.TROPICAL_CRYSTALLISED_FRAGMENT.get().asItem() ||
                            stack.getItem() == ItemInit.WETLAND_CRYSTALLISED_FRAGMENT.get().asItem();
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
                            stack.getItem() == ItemInit.ALPINE_CRYSTALLISED_SPECIMEN.get() ||
                            stack.getItem() == ItemInit.AQUATIC_CRYSTALLISED_SPECIMEN.get() ||
                            stack.getItem() == ItemInit.ARID_CRYSTALLISED_SPECIMEN.get() ||
                            stack.getItem() == ItemInit.FROZEN_CRYSTALLISED_SPECIMEN.get() ||
                            stack.getItem() == ItemInit.GRASSLAND_CRYSTALLISED_SPECIMEN.get() ||
                            stack.getItem() == ItemInit.TEMPERATE_CRYSTALLISED_SPECIMEN.get() ||
                            stack.getItem() == ItemInit.TROPICAL_CRYSTALLISED_SPECIMEN.get() ||
                            stack.getItem() == ItemInit.WETLAND_CRYSTALLISED_SPECIMEN.get() ||
                            stack.getItem() == ItemInit.HEMATINE.get() ||
                            stack.getItem() == ItemInit.AZURITE.get() ||
                            stack.getItem() == ItemInit.AMBER.get() ||
                            stack.getItem() == ItemInit.AQUAMARINE.get() ||
                            stack.getItem() == ItemInit.URAVORITE.get() ||
                            stack.getItem() == ItemInit.MALACHITE.get() ||
                            stack.getItem() == ItemInit.SPHENE.get() ||
                            stack.getItem() == ItemInit.ALMANDINE.get() ||
                            stack.getItem() == Items.BONE ||
                            stack.getItem() == Items.CLAY_BALL||
                            stack.getItem() == Items.FLINT||
                            stack.getItem() == Items.SNOWBALL||
                            stack.getItem() == Items.COAL ||
                            stack.getItem() == Blocks.CLAY.asItem() ||
                            stack.getItem() == Blocks.COBBLESTONE.asItem() ||
                            stack.getItem() == Blocks.GRAVEL.asItem() ||
                            stack.getItem() == Blocks.ICE.asItem() ||
                            stack.getItem() == Blocks.SAND.asItem();
                }
                if (slot == 0) {
                    return stack.getItem() == ItemInit.IRON_CHISEL.get() ||
                            stack.getItem() == ItemInit.DIAMOND_CHISEL.get() ||
                            stack.getItem() == ItemInit.NETHERITE_CHISEL.get();
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
                if (slot < 7) {
                    return super.extractItem(slot, amount, simulate);
                } else {
                    return (slot == 7 || slot == 8 || slot == 9 || slot == 10 || slot == 11 || slot == 12) ? super.extractItem(slot, amount, simulate) : ItemStack.EMPTY;
                }
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

    public void tick() {
        if (this.level == null) {
            return;
        }
        BlockState blockState = level.getBlockState(worldPosition);
        if (this.canExcavate()) {
            level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.POWERED, true), Block.UPDATE_ALL);
            if (progress < WORK_TIME) {
                ++progress;
                level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
                setChanged();
            }
            if (progress == WORK_TIME) {
                progress = 0;
                this.doExcavate();
            }
        } else {
            level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.POWERED, false), Block.UPDATE_ALL);
            progress = 0;
        }
        setChanged();
    }

    private boolean canExcavate() {
        int outputIndex = -1;
        this.inputIndex = -1;
        boolean flag = false;
        ItemStack inputSlot = ItemStack.EMPTY;
        ItemStack outputSlot = ItemStack.EMPTY;
        for (int slot = 1; slot < 7; slot++) {
            inputSlot = itemHandler.getStackInSlot(slot);
            if(!inputSlot.isEmpty()) {
                this.inputIndex = slot;
                ItemStack chiselSlot = itemHandler.getStackInSlot(0);
                if(!chiselSlot.isEmpty()) {
                    flag = true;
                    break;
                }
            }
        }
        if (inputIndex == -1 || !flag) {
            return false;
        } else {
            for (int slot = 7; slot < 13; slot++) {
                outputSlot = itemHandler.getStackInSlot(slot);
                if(outputSlot.isEmpty()) {
                    outputIndex = slot;
                    break;
                }
            }
            return outputIndex != -1 && this.inputIndex != -1;
        }
    }

    @Nullable
    public ExcavatingRecipe craft() {
        inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < 7; i++) {
            inventory.addItem(itemHandler.getStackInSlot(i));
            List<ExcavatingRecipe> recipes = level.getRecipeManager().getRecipesFor(ExcavatingRecipe.ExcavatingRecipeType.INSTANCE, inventory, level);
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

    private ItemStack getOutput(@Nullable ExcavatingRecipe selectedRecipe) {
        if (selectedRecipe != null) {
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
            if (!output.isEmpty()) {
                for (int slot = 7; slot < 13; slot++) {
                    ItemStack stack = itemHandler.getStackInSlot(slot);
                    if (stack.isEmpty()) {
                        itemHandler.insertItem(slot, output, false);
                        input.shrink(1);
                        break;
                    } else {
                        if (ItemStack.isSame(stack, output) && stack.getCount() + 1 < 64) {
                            stack.grow(1);
                            input.shrink(1);
                            break;
                        }
                    }
                }
            }
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
            this.getTileData().putInt("progress", this.progress);
        }
    }
}