package com.kangalia.projectdinosaur.common.item;

import com.kangalia.projectdinosaur.common.container.DinoScannerContainer;
import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

import java.util.Objects;

public class DinoScanner extends Item {

    PrehistoricEntity clickedEntity;

    public DinoScanner(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        setClickedEntity((PrehistoricEntity) pInteractionTarget);
        Level level = pPlayer.getLevel();
        if (pInteractionTarget instanceof PrehistoricEntity && !level.isClientSide) {
            MenuProvider containerProvider = createContainerProvider((PrehistoricEntity) pInteractionTarget, pPlayer.getItemInHand(pUsedHand));
            NetworkHooks.openGui((ServerPlayer) pPlayer, containerProvider);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    private MenuProvider createContainerProvider(PrehistoricEntity entity, ItemStack stack) {
        return new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return new TranslatableComponent("data.projectdinosaur.dino_species", entity.getSpecies());
            }

            @javax.annotation.Nullable
            @Override
            public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
                return new DinoScannerContainer(i, stack, playerInventory, playerEntity);
            }
        };
    }

    public PrehistoricEntity getClickedEntity() {
        return clickedEntity;
    }

    public void setClickedEntity(PrehistoricEntity entity) {
        clickedEntity = entity;
    }

    public void saveNBT(ItemStack stack, PrehistoricEntity prehistoric) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putBoolean("dino.hasnickname", prehistoric.hasCustomName());
        if (prehistoric.hasCustomName()) {
            nbt.putString("dino.nickname", Objects.requireNonNull(prehistoric.getCustomName()).getString());
        } else {
            nbt.putString("dino.nickname", "null");
        }
        nbt.putInt("dino.age", prehistoric.getAgeInDays());
        nbt.putInt("dino.sex", prehistoric.getGender());
        nbt.putInt("dino.health", Math.round((int)prehistoric.getHealth()));
        nbt.putInt("dino.maxhealth", Math.round((int)prehistoric.getMaxHealth()));
        nbt.putInt("dino.food", prehistoric.getHunger());
        nbt.putInt("dino.maxfood", prehistoric.getMaxFood());
        nbt.putInt("dino.diet", prehistoric.getDiet());
        nbt.putInt("dino.schedule", prehistoric.getSleepSchedule());
        stack.setTag(nbt);
    }
}
