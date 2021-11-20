package com.kangalia.projectdinosaur.common.block.fossilexcavator;

import com.kangalia.projectdinosaur.common.data.recipes.ExcavatingRecipe;
import com.kangalia.projectdinosaur.core.init.BlockInit;
import com.kangalia.projectdinosaur.core.init.ItemInit;
import com.kangalia.projectdinosaur.core.init.RecipeInit;
import com.kangalia.projectdinosaur.core.init.TileEntitiesInit;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

@SuppressWarnings("NullableProblems")
public class FossilExcavatorTileEntity extends TileEntity implements ITickableTileEntity {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public FossilExcavatorTileEntity(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public FossilExcavatorTileEntity() {
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
                /*if (slot>= 0 && slot < 6) {
                    return stack.getItem() == BlockInit.ENCASED_ALPINE_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.ENCASED_AQUATIC_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.ENCASED_ARID_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.ENCASED_FROZEN_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.ENCASED_GRASSLAND_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.ENCASED_TEMPERATE_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.ENCASED_TROPICAL_ROCK_FOSSIL.get().asItem() ||
                            stack.getItem() == BlockInit.ENCASED_WETLAND_ROCK_FOSSIL.get().asItem();
                }
                if (slot ==12) {
                    return stack.getItem() == ItemInit.IRON_CHISEL.get() ||
                            stack.getItem() == ItemInit.DIAMOND_CHISEL.get() ||
                            stack.getItem() == ItemInit.NETHERITE_CHISEL.get();
                }
                return false;*/
                return true;
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!isItemValid(slot, stack)) {
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

    public void craft() {
        Inventory inventory = new Inventory(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.addItem(itemHandler.getStackInSlot(i));

            Optional<ExcavatingRecipe> recipe = level.getRecipeManager().getRecipeFor(RecipeInit.EXCAVATING_RECIPE, inventory, level);

            recipe.ifPresent(IRecipe -> {
                ItemStack output = IRecipe.getResultItem();

                itemHandler.extractItem(0, 1, false);

                /*This bit hopefully damages the chisel when used in the second crafting slot (slot 12).
                It should get the item from the stack, extract the old item, damage the item, and then insert the new one into slot 12.
                Currently doesn't work
                ItemStack chisel = inventory.getItem(12);
                itemHandler.extractItem(12, 1, false);
                int damage = chisel.getDamageValue();
                if (damage > 1) {
                    chisel.setDamageValue(damage+1);
                    itemHandler.insertItem(12, chisel, false);
                }*/

                itemHandler.insertItem(6, output, false);

                /*Figure out how to play the stonecutter sound here - use Kaupenjoe Ep 34 to finish the recipe.
                TileEntity blockEntityPos = level.getBlockEntity(getBlockPos());
                level.playSound(blockEntityPos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, 1.0F, 1.0F); */

                setChanged();
            });
        }
    }

    @Override
    public void tick() {
        if (level.isClientSide) {
            return;
        }
        craft();
    }

}