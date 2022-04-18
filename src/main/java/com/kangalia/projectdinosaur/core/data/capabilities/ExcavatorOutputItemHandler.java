package com.kangalia.projectdinosaur.core.data.capabilities;

import net.minecraft.world.item.ItemStack;
import net.minecraft.core.NonNullList;
import net.minecraftforge.items.ItemStackHandler;

public class ExcavatorOutputItemHandler extends ItemStackHandler{

    private final ItemStackHandler internalSlot;

    public ExcavatorOutputItemHandler(ItemStackHandler hidden) {
        super();
        internalSlot = hidden;
    }

    @Override
    public void setSize(int size) {
        stacks = NonNullList.<ItemStack>withSize(size, ItemStack.EMPTY);
    }

    @Override
    public void setStackInSlot(int slot, ItemStack stack) {
        internalSlot.setStackInSlot(slot, stack);
    }

    @Override
    public int getSlots() {
        return internalSlot.getSlots();
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return internalSlot.getStackInSlot(slot);
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return stack;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return internalSlot.extractItem(slot, amount, simulate);
    }
}
