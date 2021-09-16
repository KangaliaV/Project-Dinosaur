package com.kangalia.projectdinosaur.common.container;

import com.kangalia.projectdinosaur.common.tileentity.FossilExcavatorEntity;
import com.kangalia.projectdinosaur.core.init.BlockInit;
import com.kangalia.projectdinosaur.core.init.ContainerTypesInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

import javax.annotation.Nullable;
import java.util.Objects;

public class FossilExcavatorContainer extends Container {

    public final FossilExcavatorEntity tileEntityType;
    private final IWorldPosCallable canInteractWithCallable;

    public FossilExcavatorContainer(final int windowID, final PlayerInventory playerInventory, final FossilExcavatorEntity tileEntityType) {
        super(ContainerTypesInit.FOSSIL_EXCAVATOR_CONTAINER_TYPE.get(), windowID);
        this.tileEntityType = tileEntityType;
        this.canInteractWithCallable = IWorldPosCallable.create(tileEntityType.getLevel(), tileEntityType.getBlockPos());

        //Tile Entity
        this.addSlot(new Slot(tileEntityType, 0, 80, 35));

        //Main Player Inventory
        for (int row = 0; row < 3; row++) {
            for(int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
            }
        }

        //Player Hotbar
        for(int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }

    public FossilExcavatorContainer(final int windowID, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowID, playerInventory, getTileEntity(playerInventory, data));
    }

    private static FossilExcavatorEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "Player Inventory cannot be Null");
        Objects.requireNonNull(data, "Packet Buffer cannot be Null");
        final TileEntity te = playerInventory.player.level.getBlockEntity(data.readBlockPos());
        if (te instanceof FossilExcavatorEntity) {
            return (FossilExcavatorEntity) te;
        }
        throw new IllegalStateException("Tile Entity is not correct");
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return stillValid(IWorldPosCallable.create(tileEntityType.getLevel(), tileEntityType.getBlockPos()), player, BlockInit.FOSSIL_EXCAVATOR.get());
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack1 = slot.getItem();
            itemStack = stack1.copy();
            if (index < FossilExcavatorEntity.slots && !this.moveItemStackTo(stack1, FossilExcavatorEntity.slots, this.slots.size(), false)) {
                return ItemStack.EMPTY;
            }
            if (!this.moveItemStackTo(stack1, 0, FossilExcavatorEntity.slots, false)) {
                return ItemStack.EMPTY;
            }
            if (stack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return itemStack;
    }
}
