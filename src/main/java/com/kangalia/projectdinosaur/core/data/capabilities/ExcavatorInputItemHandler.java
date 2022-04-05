package com.kangalia.projectdinosaur.core.data.capabilities;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class ExcavatorInputItemHandler extends ItemStackHandler {

    public ExcavatorInputItemHandler() {
        super();
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return super.insertItem(slot, stack, simulate);
    }
}
