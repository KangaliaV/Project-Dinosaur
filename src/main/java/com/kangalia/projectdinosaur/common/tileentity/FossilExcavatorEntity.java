package com.kangalia.projectdinosaur.common.tileentity;

import com.kangalia.projectdinosaur.core.init.BlockInit;
import com.kangalia.projectdinosaur.core.init.ItemInit;
import com.kangalia.projectdinosaur.core.init.TileEntitiesInit;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FossilExcavatorEntity extends TileEntity implements ITickable {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public FossilExcavatorEntity(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public FossilExcavatorEntity() {
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
                if (slot>= 0 && slot < 6) {
                    return stack.getItem() == BlockInit.ALPINE_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.AQUATIC_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.ARID_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.FROZEN_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.GRASSLAND_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.TEMPERATE_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.TROPICAL_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.WETLAND_ROCK_FOSSIL.get().asItem();
                }
                if (slot ==12) {
                    return stack.getItem() == ItemInit.IRON_CHISEL.get() ||
                            stack.getItem() == ItemInit.DIAMOND_CHISEL.get() ||
                            stack.getItem() == ItemInit.NETHERITE_CHISEL.get();
                }
                return false;
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack)) {
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

    @Override
    public void tick() {
    }
}
