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
import net.minecraft.item.Item;
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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FossilExcavatorTileEntity extends TileEntity implements ITickableTileEntity, ISidedInventory {

    static final int WORK_TIME = 3 * 20;
    private int progress = 0;
    public int calculated = 0;

    ItemStack current;
    ItemStack output;

    private final NonNullList<ItemStack> items;
    private final LazyOptional<? extends IItemHandler>[] handlers;

    private final IIntArray fields = new IIntArray() {
        @Override
        public int get(int index) {
            switch (index) {
                case 0:
                    return progress;
                default:
                    return 0;
            }
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0:
                    progress = value;
                    break;
            }
        }

        @Override
        public int getCount() {
            return 1;
        }
    };

    private final RandomNumGen rng = new RandomNumGen();

    public List<Integer> range(int start, int end) {
        return IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public FossilExcavatorTileEntity() {
        super(TileEntitiesInit.FOSSIL_EXCAVATOR_ENTITY.get());
        this.handlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN);
        this.items = NonNullList.withSize(12, ItemStack.EMPTY);
    }

    void encodeExtraData(PacketBuffer buffer) {
        buffer.writeByte(fields.getCount());
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
                if (slot == 6) {
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
        if (level.isClientSide) {
            return;
        }
        craft();
    }

    public void craft() {
        Inventory inventory = new Inventory(itemHandler.getSlots());
        for (int i = 0; i < 7; i++) {
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
                    output = selectedRecipe.getResultItem();

                    for (int o = 7; o < 13; o++) {
                        current = itemHandler.getStackInSlot(o);

                        if (current.isEmpty()) {
                            while (progress < WORK_TIME) {
                                ++progress;
                            }

                            itemHandler.insertItem(o, output, false);
                            calculated = 1;

                            if (calculated == 1) {
                                progress = 0;
                                for (int r = 0; r < 6; r++) {
                                    if (!itemHandler.getStackInSlot(r).isEmpty()) {
                                        itemHandler.extractItem(r, 1, false);
                                        setChanged();
                                        //o = 7;
                                        return;
                                    }
                                }
                            }
                        }
                        current = itemHandler.getStackInSlot(o);

                        if (!current.isEmpty()) {
                            int newCount = current.getCount() + output.getCount();

                            if (!ItemStack.isSame(current, output) || newCount >= 64) {
                                progress = 0;
                            } else {
                                while (progress < WORK_TIME) {
                                    ++progress;
                                }
                                current.grow(1);
                                calculated = 1;

                                if (calculated == 1) {
                                    progress = 0;
                                    for (int r = 0; r < 6; r++) {
                                        if (!itemHandler.getStackInSlot(r).isEmpty()) {
                                            itemHandler.extractItem(r, 1, false);
                                            setChanged();
                                            //o = 7;
                                            return;
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void getRecipeOutput() {

    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        return new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12};
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return this.canPlaceItem(index, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return true;
    }

    @Override
    public int getContainerSize() {
        return 13;
    }

    @Override
    public boolean isEmpty() {
        return getItem(0).isEmpty() &&
                getItem(1).isEmpty() &&
                getItem(2).isEmpty() &&
                getItem(3).isEmpty() &&
                getItem(4).isEmpty() &&
                getItem(5).isEmpty() &&
                getItem(6).isEmpty() &&
                getItem(7).isEmpty() &&
                getItem(8).isEmpty() &&
                getItem(9).isEmpty() &&
                getItem(10).isEmpty() &&
                getItem(11).isEmpty() &&
                getItem(12).isEmpty();
    }

    @Override
    public ItemStack getItem(int index) {
        return items.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int amount) {
        return ItemStackHelper.removeItem(items, index, amount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ItemStackHelper.takeItem(items, index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        items.set(index, stack);
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return this.level != null && this.level.getBlockEntity(this.worldPosition) == this && player.distanceToSqr(this.worldPosition.getX() + 0.5, this.worldPosition.getY() + 0.5, this.worldPosition.getZ()) <= 64;
    }

    @Override
    public void clearContent() {
        items.clear();
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