package com.kangalia.projectdinosaur.common.item;

import com.kangalia.projectdinosaur.common.container.GenomeScannerContainer;
import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.kangalia.projectdinosaur.common.entity.creature.AustralovenatorEntity;
import com.kangalia.projectdinosaur.common.entity.creature.GastornisEntity;
import net.minecraft.ChatFormatting;
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

public class GenomeScanner extends Item {

    PrehistoricEntity clickedEntity;

    public GenomeScanner(Properties pProperties) {
        super(pProperties);
    }

    @Nonnull
    @Override
    public InteractionResult interactLivingEntity(@Nonnull ItemStack pStack, Player pPlayer, @Nonnull LivingEntity pInteractionTarget, @Nonnull InteractionHand pUsedHand) {
        Level level = pPlayer.getLevel();
        System.out.println(pInteractionTarget);
        if (pInteractionTarget instanceof PrehistoricEntity prehistoric) {
            setClickedEntity(prehistoric);
            if (!level.isClientSide) {
                MenuProvider containerProvider = createContainerProvider(prehistoric, pPlayer.getItemInHand(pUsedHand));
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
                return Component.translatable("data.projectdinosaur.dino_species_scientific", entity.getSpeciesScientific()).withStyle(ChatFormatting.ITALIC);
            }

            @Nonnull
            @Override
            public AbstractContainerMenu createMenu(int i, @Nonnull Inventory playerInventory, @Nonnull Player playerEntity) {
                return new GenomeScannerContainer(i, stack, playerInventory, playerEntity);
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
        nbt.putString("dino.genome", prehistoric.getGenes());
        nbt.putString("dino.morph", prehistoric.getColourMorph());
        nbt.putInt("dino.sex", prehistoric.getGender());
        if (prehistoric instanceof GastornisEntity gastornis) {
            nbt.putBoolean("dino.gastornis", true);
            nbt.putString("dino.gastornis.feather", gastornis.getGeneDominance(1));
            nbt.putString("dino.gastornis.underside", gastornis.getGeneDominance(2));
            nbt.putString("dino.gastornis.pattern", gastornis.getGeneDominance(3));
            nbt.putString("dino.gastornis.highlight", gastornis.getGeneDominance(4));
            nbt.putString("dino.gastornis.skin", gastornis.getGeneDominance(5));
            nbt.putString("dino.gastornis.beak", gastornis.getGeneDominance(6));


        } else if (prehistoric instanceof AustralovenatorEntity australovenator) {
            nbt.putBoolean("dino.australovenator", true);
            nbt.putString("dino.australovenator.base", australovenator.getGeneDominance(2));
            nbt.putString("dino.australovenator.underside", australovenator.getGeneDominance(3));
            nbt.putString("dino.australovenator.pattern_type", australovenator.getGeneDominance(4));
            nbt.putString("dino.australovenator.pattern_colour", australovenator.getGeneDominance(5));
            nbt.putString("dino.australovenator.highlight", australovenator.getGeneDominance(6));
        }
        nbt.putString("dino.speed", prehistoric.getCoefficientRating(7));
        nbt.putString("dino.size", prehistoric.getCoefficientRating(8));
        nbt.putString("dino.attack_damage", prehistoric.getCoefficientRating(9));
        nbt.putString("dino.max_health", prehistoric.getCoefficientRating(10));
        stack.setTag(nbt);
    }
}
