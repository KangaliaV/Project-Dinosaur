package com.kangalia.projectdinosaur.common.item;

import com.kangalia.projectdinosaur.common.container.DinoScannerContainer;
import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
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

import javax.annotation.Nonnull;
import java.util.Objects;

public class DinoScanner extends Item {

    PrehistoricEntity clickedEntity;

    public DinoScanner(Properties pProperties) {
        super(pProperties);
    }

    @Nonnull
    @Override
    public InteractionResult interactLivingEntity(@Nonnull ItemStack pStack, Player pPlayer, @Nonnull LivingEntity pInteractionTarget, @Nonnull InteractionHand pUsedHand) {
        Level level = pPlayer.getLevel();
        if (pInteractionTarget instanceof PrehistoricEntity) {
            setClickedEntity((PrehistoricEntity) pInteractionTarget);
            if (!level.isClientSide) {
                MenuProvider containerProvider = createContainerProvider((PrehistoricEntity) pInteractionTarget, pPlayer.getItemInHand(pUsedHand));
                NetworkHooks.openScreen((ServerPlayer) pPlayer, containerProvider);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    private MenuProvider createContainerProvider(PrehistoricEntity entity, ItemStack stack) {
        return new MenuProvider() {
            @Nonnull
            @Override
            public Component getDisplayName() {
                return Component.translatable("data.projectdinosaur.dino_species", entity.getSpecies());
            }

            @Nonnull
            @Override
            public AbstractContainerMenu createMenu(int i, @Nonnull Inventory playerInventory, @Nonnull Player playerEntity) {
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
        nbt.putBoolean("dino.iscryosick", prehistoric.isCryosick());
        nbt.putBoolean("dino.islonely", prehistoric.isLonely());
        nbt.putInt("dino.age", prehistoric.getAgeInDays());
        nbt.putInt("dino.sex", prehistoric.getGender());
        nbt.putInt("dino.health", Math.round((int)prehistoric.getHealth()));
        nbt.putInt("dino.maxhealth", Math.round((int)prehistoric.getMaxHealth()));
        nbt.putInt("dino.food", prehistoric.getHunger());
        nbt.putInt("dino.maxfood", prehistoric.getMaxFood());
        nbt.putInt("dino.diet", prehistoric.getDiet());
        nbt.putInt("dino.schedule", prehistoric.getSleepSchedule());
        nbt.putInt("dino.scale", prehistoric.getRenderScale());
        nbt.putInt("dino.enrichment", prehistoric.getEnrichment());
        nbt.putInt("dino.maxenrichment", prehistoric.getMaxEnrichment());
        nbt.putInt("dino.mood", prehistoric.getMood());
        stack.setTag(nbt);
    }
}
