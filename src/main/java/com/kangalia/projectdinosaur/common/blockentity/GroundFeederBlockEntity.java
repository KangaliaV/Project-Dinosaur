package com.kangalia.projectdinosaur.common.blockentity;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.kangalia.projectdinosaur.core.init.BlockEntitiesInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GroundFeederBlockEntity extends BlockEntity {
    public int herbi = 0;
    public int carni = 0;
    public int pisci = 0;
    private final NonNullList<ItemStack> items;
    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public GroundFeederBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockEntitiesInit.GROUND_FEEDER_ENTITY.get(), blockPos, blockState);
        this.items = NonNullList.withSize(1, ItemStack.EMPTY);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        handler.invalidate();
    }

    @Override
    public void load(CompoundTag nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        this.herbi = nbt.getInt("herbi");
        this.carni = nbt.getInt("carni");
        this.pisci = nbt.getInt("pisci");
        super.load(nbt);
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        nbt.put("inv", itemHandler.serializeNBT());
        nbt.putInt("herbi", this.herbi);
        nbt.putInt("carni", this.carni);
        nbt.putInt("pisci", this.pisci);
        super.saveAdditional(nbt);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if (slot == 0) {
                    return stack.getItem() == Items.BEEF ||
                            stack.getItem() == Items.PORKCHOP ||
                            stack.getItem() == Items.CHICKEN ||
                            stack.getItem() == Items.MUTTON ||
                            stack.getItem() == Items.RABBIT ||
                            stack.getItem() == Items.EGG ||
                            stack.getItem() == Items.WHEAT ||
                            stack.getItem() == Items.CARROT ||
                            stack.getItem() == Items.POTATO ||
                            stack.getItem() == Items.BEETROOT ||
                            stack.getItem() == Items.WHEAT_SEEDS ||
                            stack.getItem() == Items.BEETROOT_SEEDS ||
                            stack.getItem() == Items.APPLE ||
                            stack.getItem() == Items.MELON_SLICE ||
                            stack.getItem() == Items.MELON ||
                            stack.getItem() == Items.PUMPKIN ||
                            stack.getItem() == Items.MELON_SEEDS ||
                            stack.getItem() == Items.PUMPKIN_SEEDS ||
                            stack.getItem() == Items.GLOW_BERRIES ||
                            stack.getItem() == Items.SALMON ||
                            stack.getItem() == Items.COD ||
                            stack.getItem() == Items.TROPICAL_FISH;
                }
                return false;

            }
            @Nonnull
            @Override
            public ItemStack insertItem(int slot, ItemStack stack , boolean simulate) {
                return(isItemValid(slot, stack)) ? super.insertItem(slot, stack, simulate) : stack;
            }

            @Nonnull
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                return super.extractItem(slot, amount, simulate);
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

    public int getFoodType() {
        if (herbi > carni && herbi > pisci) {
            return 1;
        } else if (carni > herbi && carni > pisci) {
            return 2;
        } else if (pisci > herbi && pisci > carni) {
            return 3;
        } else if (herbi == 0 && carni == 0 && pisci == 0) {
            return 0;
        } else if (herbi == carni && carni == pisci) {
            return 1;
        } else {
            return 0;
        }
    }

    public boolean isEmpty(PrehistoricEntity entity) {
        if (entity.getDiet() == 0) {
            return herbi == 0;
        } else if (entity.getDiet() == 1) {
            return carni == 0;
        } else if (entity.getDiet() == 2) {
            return pisci == 0;
        }
        return false;
    }

    public void feedEntity(PrehistoricEntity entity) {
        boolean flag = false;
        boolean flag2 = true;
        while (flag2) {
            if (!this.isEmpty(entity)) {
                if (entity.getHunger() < entity.maxFood) {
                    if (entity.getDiet() == 0) {
                        this.herbi = this.herbi - 1;
                        entity.setHunger(entity.getHunger() + 1);
                        flag = true;
                    } else if (entity.getDiet() == 1) {
                        this.carni = this.carni - 1;
                        entity.setHunger(entity.getHunger() + 1);
                        flag = true;
                    } else if (entity.getDiet() == 2) {
                        this.pisci = this.pisci - 1;
                        entity.setHunger(entity.getHunger() + 1);
                        flag = true;
                    }
                    if (flag) {
                        entity.level.playSound(null, entity.blockPosition(), SoundEvents.GENERIC_EAT, SoundSource.NEUTRAL, 1.0F, entity.getVoicePitch());
                        assert level != null;
                        level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
                        setChanged();
                    } else {
                        flag2 = false;
                    }
                } else {
                    flag2 = false;
                }
            } else {
                flag2 = false;
            }
        }
    }

    public void tick() {
        if (this.level == null) {
            return;
        }
        ItemStack item = itemHandler.getStackInSlot(0);
        if (!item.isEmpty()) {
            int feederMax = 640;
            if (item.getItem().equals(Items.WHEAT) || item.getItem().equals(Items.CARROT) || item.getItem().equals(Items.POTATO) || item.getItem().equals(Items.BEETROOT) || item.getItem().equals(Items.WHEAT_SEEDS) || item.getItem().equals(Items.BEETROOT_SEEDS) || item.getItem().equals(Items.APPLE) || item.getItem().equals(Items.MELON_SLICE) || item.getItem().equals(Items.MELON) || item.getItem().equals(Items.PUMPKIN) || item.getItem().equals(Items.MELON_SEEDS) || item.getItem().equals(Items.PUMPKIN_SEEDS) || item.getItem().equals(Items.GLOW_BERRIES)) {
                if (this.herbi < feederMax) {
                    this.herbi = this.herbi + 10;
                    item.shrink(1);
                    if (this.herbi > feederMax) {
                        this.herbi = feederMax;
                    }
                }
            }
            if (item.getItem().equals(Items.BEEF.asItem()) || item.getItem().equals(Items.PORKCHOP) || item.getItem().equals(Items.CHICKEN) || item.getItem().equals(Items.MUTTON) || item.getItem().equals(Items.RABBIT) || item.getItem().equals(Items.EGG)) {
                if (this.carni < feederMax) {
                    this.carni = this.carni + 10;
                    item.shrink(1);
                    if (this.carni > feederMax) {
                        this.carni = feederMax;
                    }
                }
            }
            if (item.getItem().equals(Items.SALMON) || item.getItem().equals(Items.COD) || item.getItem().equals(Items.TROPICAL_FISH)) {
                if (this.pisci < feederMax) {
                    this.pisci = this.pisci + 10;
                    item.shrink(1);
                    if (this.pisci > feederMax) {
                        this.pisci = feederMax;
                    }
                }
            }
        }
        level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        setChanged();
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag tags = this.getUpdateTag();
        ContainerHelper.saveAllItems(tags, this.items);
        tags.putInt("herbi", this.herbi);
        tags.putInt("carni", this.carni);
        tags.putInt("pisci", this.pisci);
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithFullMetadata();
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();
        if (tag.contains("herbi")) {
            herbi = tag.getInt("herbi");
            this.getTileData().putInt("herbi", this.herbi);
        }
        if (tag.contains("carni")) {
            carni = (tag.getInt("carni"));
            this.getTileData().putInt("carni", this.carni);
        }
        if (tag.contains("pisci")) {
            pisci = (tag.getInt("pisci"));
            this.getTileData().putInt("pisci", this.pisci);
        }
    }
}
