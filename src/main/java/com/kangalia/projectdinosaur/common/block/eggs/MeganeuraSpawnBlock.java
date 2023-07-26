package com.kangalia.projectdinosaur.common.block.eggs;

import com.kangalia.projectdinosaur.common.blockentity.eggs.MeganeuraSpawnBlockEntity;
import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.kangalia.projectdinosaur.common.entity.creature.MeganeuraEntity;
import com.kangalia.projectdinosaur.core.init.BlockEntitiesInit;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class MeganeuraSpawnBlock extends WaterSpawnBlock {

    public MeganeuraSpawnBlock(Properties pProperties, Supplier<? extends EntityType<? extends PrehistoricEntity>> entity) {
        super(pProperties, entity);
    }

    @Override
    public void spawnYoung(ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        int i = pRandom.nextInt(getMinYoung(), getMaxYoung());
        BlockEntity eggEntity = pLevel.getBlockEntity(pPos);

        for(int j = 1; j <= i; ++j) {
            PrehistoricEntity young = dino.get().create(pLevel);
            if (young instanceof MeganeuraEntity) {
                String parent1Genes = getParent1GenesFromSpawnBlockEntity(eggEntity);
                String parent2Genes = getParent2GenesFromSpawnBlockEntity(eggEntity);
                if (Objects.equals(parent1Genes, "") || Objects.equals(parent2Genes, "") || parent1Genes == null || parent2Genes == null) {
                    ((MeganeuraEntity) young).setGenes(((MeganeuraEntity) young).generateGenes(false));
                } else {
                    ((MeganeuraEntity) young).setGenes(((MeganeuraEntity) young).inheritGenes(parent1Genes, parent2Genes));
                }
                young.setGender(pRandom.nextInt(2));
                young.setAgeInTicks(0);
                young.setMatingTicks(12000);
                young.setHunger(young.getMaxFood() / 2);
                young.setHungerTicks(1600);
                young.setEnrichment(young.getMaxEnrichment() / 2);
                young.setAttributes(3);
                young.moveTo((double)pPos.getX() + 0.3D + (double)j * 0.2D, (double)pPos.getY(), (double)pPos.getZ() + 0.3D, 0.0F, 0.0F);
                young.setPersistenceRequired();
                pLevel.addFreshEntity(young);
            }
        }

    }

    public String getParent1GenesFromSpawnBlockEntity(BlockEntity entity) {
        if (entity instanceof MeganeuraSpawnBlockEntity) {
            return ((MeganeuraSpawnBlockEntity) entity).getParent1();
        } else {
            return "";
        }
    }

    public String getParent2GenesFromSpawnBlockEntity(BlockEntity entity) {
        if (entity instanceof MeganeuraSpawnBlockEntity) {
            return ((MeganeuraSpawnBlockEntity) entity).getParent2();
        } else {
            return "";
        }
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @org.jetbrains.annotations.Nullable LivingEntity pPlacer, ItemStack pStack) {
        BlockEntity entity = pLevel.getBlockEntity(pPos);
        if (!pLevel.isClientSide) {
            if (entity instanceof MeganeuraSpawnBlockEntity eggEntity) {
                CompoundTag tag = BlockItem.getBlockEntityData(pStack);
                if (tag != null) {
                    String parent1 = tag.getCompound("parentGenome").getString("parent1");
                    String parent2 = tag.getCompound("parentGenome").getString("parent2");
                    eggEntity.readParents(parent1, parent2);
                } else {
                    eggEntity.readParents("", "");
                }
            }
        }
    }

    @Override
    public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (!pLevel.isClientSide && !pPlayer.isCreative()) {
            if (blockentity instanceof MeganeuraSpawnBlockEntity eggBlockEntity) {
                ItemStack itemstack = new ItemStack(this);
                CompoundTag compoundtag = new CompoundTag();
                compoundtag.put("parentGenome", eggBlockEntity.writeParents());
                BlockItem.setBlockEntityData(itemstack, BlockEntitiesInit.MEGANEURA_SPAWN_ENTITY.get(), compoundtag);
                ItemEntity itementity = new ItemEntity(pLevel, (double) pPos.getX() + 0.5D, (double) pPos.getY() + 0.5D, (double) pPos.getZ() + 0.5D, itemstack);
                itementity.setDefaultPickUpDelay();
                pLevel.addFreshEntity(itementity);

            }
        }
        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        CompoundTag compoundtag = BlockItem.getBlockEntityData(pStack);
        if (compoundtag != null) {
            if (compoundtag.contains("parentGenome")) {
                String parent1 = compoundtag.getCompound("parentGenome").getString("parent1");
                String parent2 = compoundtag.getCompound("parentGenome").getString("parent2");
                if (parent1.equals("")) {
                    pTooltip.add(Component.translatable("genome.projectdinosaur.parent1", "???").withStyle(ChatFormatting.GRAY));
                } else {
                    pTooltip.add(Component.translatable("genome.projectdinosaur.parent1", parent1).withStyle(ChatFormatting.GRAY));
                }
                if (parent2.equals("")) {
                    pTooltip.add(Component.translatable("genome.projectdinosaur.parent2", "???").withStyle(ChatFormatting.GRAY));
                } else {
                    pTooltip.add(Component.translatable("genome.projectdinosaur.parent2", parent2).withStyle(ChatFormatting.GRAY));
                }
            }
        }
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return BlockEntitiesInit.MEGANEURA_SPAWN_ENTITY.get().create(pPos, pState);
    }
}
