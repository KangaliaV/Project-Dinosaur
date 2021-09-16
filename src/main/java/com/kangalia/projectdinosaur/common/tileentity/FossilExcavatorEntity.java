package com.kangalia.projectdinosaur.common.tileentity;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.block.FossilExcavator;
import com.kangalia.projectdinosaur.common.container.FossilExcavatorContainer;
import com.kangalia.projectdinosaur.core.init.BlockInit;
import com.kangalia.projectdinosaur.core.init.ItemInit;
import com.kangalia.projectdinosaur.core.init.TileEntityTypesInit;
import com.sun.istack.internal.NotNull;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class FossilExcavatorEntity extends TileEntity  {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public FossilExcavatorEntity(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    private ItemStackHandler createHandler(); {
        return new ItemStackHandler(2) {
            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                switch (slot) {
                    case 0:
                        return stack.getItem() == BlockInit.ALPINE_ROCK_FOSSIL.get().asItem() ||
                                stack.getItem() == BlockInit.AQUATIC_ROCK_FOSSIL.get().asItem() ||
                                stack.getItem() == BlockInit.ARID_ROCK_FOSSIL.get().asItem() ||
                                stack.getItem() == BlockInit.FROZEN_ROCK_FOSSIL.get().asItem() ||
                                stack.getItem() == BlockInit.GRASSLAND_ROCK_FOSSIL.get().asItem() ||
                                stack.getItem() == BlockInit.TEMPERATE_ROCK_FOSSIL.get().asItem() ||
                                stack.getItem() == BlockInit.TROPICAL_ROCK_FOSSIL.get().asItem() ||
                                stack.getItem() == BlockInit.WETLAND_ROCK_FOSSIL.get().asItem();

                    case 1:
                        return stack.getItem() == ItemInit.IRON_CHISEL.get() ||
                                stack.getItem() == ItemInit.DIAMOND_CHISEL.get() ||
                                stack.getItem() == ItemInit.NETHERITE_CHISEL.get();

                    default:
                        return false;
                }
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








    //public static int slots = 3;

    //protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);

    //public FossilExcavatorEntity(TileEntityType<?> tileEntityType) {

    //}

    //@Override
    //protected ITextComponent getDefaultName() {
        //return new TranslationTextComponent("container." + ProjectDinosaur.MODID + ".fossil_excavator");
    //}

   // @Override
    //protected Container createMenu(int id, PlayerInventory player) {
        //return new FossilExcavatorContainer(id, player, this);
    //}

    //@Override
    //protected NonNullList<ItemStack> getItems() {
       // return this.items;
    //}

    //@Override
    //protected void setItems(NonNullList<ItemStack> itemsIn) {
        //this.items = itemsIn;
    //}

    //public FossilExcavatorEntity() {
        //this(TileEntityTypesInit.FOSSIL_EXCAVATOR_ENTITY_TYPE.get());
   // }

    //@Override
    //public int getContainerSize() {
        //return slots;
    //}

    //@Override
    //public void tick() {

    //}

    //@Override
    //public CompoundNBT save(CompoundNBT compound) {
        //super.save(compound);
        //ItemStackHelper.saveAllItems(compound, this.items);
        //return compound;
   // }


   // public void load(BlockState state, CompoundNBT compound) {
       // super.load(state, compound);
        //this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        //ItemStackHelper.loadAllItems(compound, this.items);
   // }

}
