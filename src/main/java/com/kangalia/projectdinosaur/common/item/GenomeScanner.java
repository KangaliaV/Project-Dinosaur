package com.kangalia.projectdinosaur.common.item;

import com.kangalia.projectdinosaur.client.gui.GenomeScannerScreen;
import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.kangalia.projectdinosaur.common.entity.creature.AustralovenatorEntity;
import com.kangalia.projectdinosaur.common.entity.creature.GastornisEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

public class GenomeScanner extends Item {

    public GenomeScanner(Properties pProperties) {
        super(pProperties);
    }

    @Nonnull
    @Override
    public InteractionResult interactLivingEntity(@Nonnull ItemStack pStack, Player pPlayer, @Nonnull LivingEntity pInteractionTarget, @Nonnull InteractionHand pUsedHand) {
        Level level = pPlayer.getLevel();
        if (pInteractionTarget instanceof PrehistoricEntity prehistoric) {
            ItemStack scanner = pPlayer.getItemInHand(pUsedHand);
            saveNBT(scanner, prehistoric);
            if (scanner.getTag() != null && level.isClientSide) {
                openScanner(prehistoric, scanner);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    public void saveNBT(ItemStack stack, PrehistoricEntity prehistoric) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putString("dino.name.scientific", prehistoric.getSpeciesScientific().getString());
        nbt.putString("dino.genome", prehistoric.getGenes());
        nbt.putString("dino.morph", prehistoric.getColourMorph());
        nbt.putInt("dino.sex", prehistoric.getGender());
        if (prehistoric instanceof GastornisEntity gastornis) {
            nbt.putBoolean("dino.australovenator", false);
            nbt.putBoolean("dino.gastornis", true);
            nbt.putString("dino.gastornis.feather", gastornis.getGeneDominance(1));
            nbt.putString("dino.gastornis.underside", gastornis.getGeneDominance(2));
            nbt.putString("dino.gastornis.pattern", gastornis.getGeneDominance(3));
            nbt.putString("dino.gastornis.highlight", gastornis.getGeneDominance(4));
            nbt.putString("dino.gastornis.skin", gastornis.getGeneDominance(5));
            nbt.putString("dino.gastornis.beak", gastornis.getGeneDominance(6));
            nbt.putString("dino.speed", prehistoric.getCoefficientRating(7));
            nbt.putString("dino.size", prehistoric.getCoefficientRating(8));
            nbt.putString("dino.attack_damage", prehistoric.getCoefficientRating(9));
            nbt.putString("dino.max_health", prehistoric.getCoefficientRating(10));

        } else if (prehistoric instanceof AustralovenatorEntity australovenator) {
            nbt.putBoolean("dino.gastornis", false);
            nbt.putBoolean("dino.australovenator", true);
            nbt.putString("dino.australovenator.base", australovenator.getGeneDominance(2));
            nbt.putString("dino.australovenator.underside", australovenator.getGeneDominance(3));
            nbt.putString("dino.australovenator.pattern_type", australovenator.getGeneDominance(4));
            nbt.putString("dino.australovenator.pattern_colour", australovenator.getGeneDominance(5));
            nbt.putString("dino.australovenator.highlight", australovenator.getGeneDominance(6));
            nbt.putString("dino.speed", prehistoric.getCoefficientRating(6));
            nbt.putString("dino.size", prehistoric.getCoefficientRating(7));
            nbt.putString("dino.attack_damage", prehistoric.getCoefficientRating(8));
            nbt.putString("dino.max_health", prehistoric.getCoefficientRating(9));
        }

        stack.setTag(nbt);
    }

    @OnlyIn(Dist.CLIENT)
    public void openScanner(PrehistoricEntity entity, ItemStack scanner) {
        Minecraft mc = Minecraft.getInstance();
        mc.setScreen(new GenomeScannerScreen(entity, scanner));
    }
}
