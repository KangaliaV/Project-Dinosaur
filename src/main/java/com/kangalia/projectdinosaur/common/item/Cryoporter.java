package com.kangalia.projectdinosaur.common.item;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

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
                //saveEntity(stack, pInteractionTarget, pPlayer, pUsedHand);
                CompoundTag compoundtag = stack.getOrCreateTag();
                pInteractionTarget.save(compoundtag);
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
        if (!level.isClientSide) {
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
        CompoundTag compoundtag = stack.getOrCreateTag();
        compoundtag.put("projectdinosaur.entity", entity.saveWithoutId(compoundtag));
        stack.setTag(compoundtag);
        player.setItemInHand(hand, stack);
        entity.remove(Entity.RemovalReason.DISCARDED);
    }

    public static void releaseEntity(ItemStack stack, BlockPos pos, Level level, Player player, InteractionHand hand) {
        if (stack == null)
            return;
        CompoundTag compoundtag = stack.getOrCreateTag();
        if (!compoundtag.contains("projectdinosaur.entity"))
            return;
        Entity entity = EntityType.loadEntityRecursive(compoundtag, level, (e) -> {
            e.teleportTo(pos.getX(), pos.getY(), pos.getZ());
            return e;
        });
        if (entity != null) {
            level.addFreshEntity(entity);
        }
        compoundtag.put("projectdinosaur.entities", new CompoundTag());
        stack.setTag(compoundtag);
        player.setItemInHand(hand, stack);
    }
}
