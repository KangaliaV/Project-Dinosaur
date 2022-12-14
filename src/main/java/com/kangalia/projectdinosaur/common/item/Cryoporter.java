package com.kangalia.projectdinosaur.common.item;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
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

import javax.annotation.Nonnull;
import java.util.List;

public class Cryoporter extends Item {

    public Cryoporter(Properties pProperties) {
        super(pProperties);
    }

    @Nonnull
    @Override
    public InteractionResult interactLivingEntity(@Nonnull ItemStack pStack, Player pPlayer, @Nonnull LivingEntity pInteractionTarget, @Nonnull InteractionHand pUsedHand) {
        Level level = pPlayer.getLevel();
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (pInteractionTarget instanceof PrehistoricEntity) {
            if (!level.isClientSide) {
                saveEntity(stack, pInteractionTarget);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Nonnull
    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        InteractionHand hand = pContext.getHand();
        Player player = pContext.getPlayer();
        assert player != null;
        ItemStack stack = player.getItemInHand(hand);
        Level level = player.getLevel();
        BlockPos clickedPos = pContext.getClickedPos().above();
        if (!level.isClientSide) {
            releaseEntity(stack, clickedPos, level, player, hand);
        }
        return super.useOn(pContext);
    }

    public static void saveEntity(ItemStack stack, Entity entity) {
        if (stack == null || entity == null)
            return;
        if (!(entity instanceof PrehistoricEntity prehistoric)) {
            return;
        }
        if (prehistoric.isBaby()) {
            return;
        }
        CompoundTag compoundtag = stack.getOrCreateTag();
        if (compoundtag.contains("id")) {
            return;
        }
        entity.save(compoundtag);
        stack.setTag(compoundtag);
        entity.remove(Entity.RemovalReason.DISCARDED);
    }


    public static void releaseEntity(ItemStack stack, BlockPos pos, Level level, Player player, InteractionHand hand) {
        if (stack == null) {
            return;
        }
        CompoundTag compoundtag = stack.getOrCreateTag();
        if (!compoundtag.contains("id")) {
            return;
        }
        Entity entity = EntityType.loadEntityRecursive(compoundtag, level, (e) -> {
            e.teleportTo(pos.getX(), pos.getY(), pos.getZ());
            return e;
        });
        if (entity != null) {
            level.addFreshEntity(entity);
            if (entity instanceof PrehistoricEntity) {
                ((PrehistoricEntity) entity).setRemainingCryosicknessTime(1200);
            }

        }

        compoundtag.remove("id");
        stack.setTag(compoundtag);
        stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, @Nonnull List<Component> pTooltipComponents, @Nonnull TooltipFlag pIsAdvanced) {
        CompoundTag tag = pStack.getOrCreateTag();
        if (tag.contains("id")) {
            String entityName = tag.getString("id");
            pTooltipComponents.add((Component.translatable("cryoporter."+entityName)).setStyle(Style.EMPTY.withItalic(true).withColor(ChatFormatting.AQUA)));
        } else {
            pTooltipComponents.add((Component.translatable("cryoporter.projectdinosaur.empty")).setStyle(Style.EMPTY.withItalic(true).withColor(ChatFormatting.GRAY)));
        }


    }
}
