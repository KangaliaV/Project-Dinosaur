package com.kangalia.projectdinosaur.common.item;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.*;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class Cryoporter extends Item {

    public Cryoporter(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        Level level = pPlayer.getLevel();
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (pInteractionTarget instanceof PrehistoricEntity) {
            if (!level.isClientSide) {
                saveEntity(stack, pInteractionTarget, pPlayer, pUsedHand);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        InteractionHand hand = pContext.getHand();
        Player player = pContext.getPlayer();
        ItemStack stack = player.getItemInHand(hand);
        Level level = player.getLevel();
        BlockPos clickedPos = pContext.getClickedPos().above();
        System.out.println("useOn");
        if (!level.isClientSide) {
            System.out.println("Is not clientside");
            releaseEntity(stack, clickedPos, level, player, hand);
        }
        return super.useOn(pContext);
    }

    public static void saveEntity(ItemStack stack, Entity entity, Player player, InteractionHand hand) {
        if (stack == null || entity == null)
            return;
        if (!(entity instanceof PrehistoricEntity prehistoric)) {
            return;
        }
        if (prehistoric.isBaby()) {
            return;
        }
        System.out.println("Completed saveEntity checks");
        CompoundTag compoundtag = stack.getOrCreateTag();
        if (compoundtag.contains("id")) {
            System.out.println("Already contains a dino");
            return;
        }
        System.out.println("getOrCreateTag: "+compoundtag);
        entity.save(compoundtag);
        System.out.println("save: "+compoundtag);
        stack.setTag(compoundtag);
        //player.setItemInHand(hand, stack);
        entity.remove(Entity.RemovalReason.DISCARDED);

    }


    public static void releaseEntity(ItemStack stack, BlockPos pos, Level level, Player player, InteractionHand hand) {
        System.out.println("releaseEntity");
        if (stack == null) {
            System.out.println("stack is null");
            return;
        }
        CompoundTag compoundtag = stack.getOrCreateTag();
        if (!compoundtag.contains("id")) {
            System.out.println("is is null");
            return;
        }
        Entity entity = EntityType.loadEntityRecursive(compoundtag, level, (e) -> {
            e.teleportTo(pos.getX(), pos.getY(), pos.getZ());
            return e;
        });
        if (entity != null) {
            System.out.println("Entity is not null");
            level.addFreshEntity(entity);
        }

        compoundtag.remove("id");
        stack.setTag(compoundtag);
        stack.hurt(1, level.random, null);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        CompoundTag tag = pStack.getOrCreateTag();
        if (tag.contains("id")) {
            String entityName = tag.getString("id");
            MutableComponent translatableFull = new TranslatableComponent("cryoporter."+entityName);
            System.out.println("EntityName: "+entityName);
            System.out.println(translatableFull);
            pTooltipComponents.add((new TranslatableComponent("cryoporter."+entityName)).setStyle(Style.EMPTY.withItalic(true).withColor(ChatFormatting.AQUA)));
        } else {
            pTooltipComponents.add((new TranslatableComponent("cryoporter.projectdinosaur.empty")).setStyle(Style.EMPTY.withItalic(true).withColor(ChatFormatting.GRAY)));
        }


    }
}
