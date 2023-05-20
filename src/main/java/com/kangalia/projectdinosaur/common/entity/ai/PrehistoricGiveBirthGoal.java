package com.kangalia.projectdinosaur.common.entity.ai;

import com.kangalia.projectdinosaur.common.block.eggs.PrehistoricEggBlock;
import com.kangalia.projectdinosaur.common.blockentity.eggs.AustralovenatorEggBlockEntity;
import com.kangalia.projectdinosaur.common.blockentity.eggs.GastornisEggBlockEntity;
import com.kangalia.projectdinosaur.common.blockentity.eggs.ScelidosaurusEggBlockEntity;
import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.kangalia.projectdinosaur.common.entity.creature.*;
import com.kangalia.projectdinosaur.core.init.BlockEntitiesInit;
import com.kangalia.projectdinosaur.core.init.BlockInit;
import com.kangalia.projectdinosaur.core.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.Random;

public class PrehistoricGiveBirthGoal extends MoveToBlockGoal {

    PrehistoricEntity prehistoricEntity;
    String mate;
    Block nest;
    Random random = new Random();

    public PrehistoricGiveBirthGoal(PrehistoricEntity prehistoric, String prehistoricMate, double pSpeedModifier, int pSearchRange) {
        super(prehistoric, pSpeedModifier, pSearchRange);
        this.prehistoricEntity = prehistoric;
        this.mate = prehistoricMate;
    }

    @Override
    public boolean canUse() {
        if (!prehistoricEntity.isPregnant() || prehistoricEntity.getPregnancyTicks() > 0) {
            return false;
        } else {
            return super.canUse();
        }
    }

    @Override
    public boolean canContinueToUse() {
        if (!prehistoricEntity.isPregnant() || prehistoricEntity.getPregnancyTicks() > 0) {
            return false;
        } else {
            return super.canContinueToUse();
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (isReachedTarget()) {
            this.mob.getNavigation().stop();
            if (prehistoricEntity.getBreedingType() == 0) {
                layEgg(prehistoricEntity, mate);
            } else if (prehistoricEntity.getBreedingType() == 1) {
                laySpawn(prehistoricEntity, mate);
            }
        }
    }

    private void layEgg(PrehistoricEntity female, String mate) {
        if (!female.isInWater()) {
            Level level = female.level;
            BlockPos eggPos = this.blockPos.above();
            level.playSound(null, eggPos, SoundEvents.TURTLE_LAY_EGG, SoundSource.BLOCKS, 0.3F, 0.9F + level.random.nextFloat() * 0.2F);
            Block eggType = female.getEggType();
            if (eggPos.closerToCenterThan(female.position(), 2.0D)) {
                level.setBlock(eggPos, eggType.defaultBlockState().setValue(PrehistoricEggBlock.EGGS, random.nextInt(female.getClutchSize()) + 1), 3);
            }
            BlockEntity eggEntity = level.getBlockEntity(eggPos);
            if (female instanceof GastornisEntity && eggEntity != null && eggEntity.getType() == BlockEntitiesInit.GASTORNIS_EGG_ENTITY.get()) {
                GastornisEggBlockEntity gastornisEggEntity = (GastornisEggBlockEntity) eggEntity.getType().getBlockEntity(level, eggPos);
                if (gastornisEggEntity != null) {
                    gastornisEggEntity.setParent1(mate);
                    gastornisEggEntity.setParent2(female.getGenes());
                }
            } else if (female instanceof AustralovenatorEntity && eggEntity != null && eggEntity.getType() == BlockEntitiesInit.AUSTRALOVENATOR_EGG_ENTITY.get()) {
                AustralovenatorEggBlockEntity australovenatorEggEntity = (AustralovenatorEggBlockEntity) eggEntity.getType().getBlockEntity(level, eggPos);
                if (australovenatorEggEntity != null) {
                    australovenatorEggEntity.setParent1(mate);
                    australovenatorEggEntity.setParent2(female.getGenes());
                }
            } else if (female instanceof ScelidosaurusEntity && eggEntity != null && eggEntity.getType() == BlockEntitiesInit.SCELIDOSAURUS_EGG_ENTITY.get()) {
                ScelidosaurusEggBlockEntity scelidosaurusEggEntity = (ScelidosaurusEggBlockEntity) eggEntity.getType().getBlockEntity(level, eggPos);
                if (scelidosaurusEggEntity != null) {
                    scelidosaurusEggEntity.setParent1(mate);
                    scelidosaurusEggEntity.setParent2(female.getGenes());
                }
            }
        }
        female.setPregnant(false);
    }

    private void laySpawn(PrehistoricEntity female, String mate) {
        if (!female.level.isClientSide) {
            ItemStack itemstack = female.getPrehistoricSpawnType();
            if (female instanceof AphanerammaEntity) {
                itemstack = new ItemStack(ItemInit.APHANERAMMA_SPAWN_ITEM.get());
                CompoundTag compoundtag = new CompoundTag();
                compoundtag.put("parentGenome", female.writeSpawnParents(mate));
                BlockItem.setBlockEntityData(itemstack, BlockEntitiesInit.APHANERAMMA_SPAWN_ENTITY.get(), compoundtag);
            } else if (female instanceof TrilobiteEntity) {
                itemstack = new ItemStack(ItemInit.TRILOBITE_SPAWN_ITEM.get());
                CompoundTag compoundtag = new CompoundTag();
                compoundtag.put("parentGenome", female.writeSpawnParents(mate));
                BlockItem.setBlockEntityData(itemstack, BlockEntitiesInit.TRILOBITE_SPAWN_ENTITY.get(), compoundtag);
            }
            ItemEntity item = new ItemEntity(female.level, female.getX(), female.getY(), female.getZ(), itemstack);
            female.level.addFreshEntity(item);
        }
        female.setPregnant(false);
    }

    @Override
    protected boolean findNearestBlock() {
        if (prehistoricEntity.getNestPos() != BlockPos.ZERO && random.nextInt(4) == 0) {
            this.blockPos = prehistoricEntity.getNestPos();
        } else {
            this.blockPos = prehistoricEntity.getOnPos();
        }
        return true;
    }

    @Override
    protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
        if (pLevel.getBlockState(pPos).getBlock().equals(BlockInit.NEST.get())) {
            nest = pLevel.getBlockState(pPos).getBlock();
            return true;
        }
        return false;
    }

    @Override
    public double acceptedDistance() {
        return 2.0D;
    }
}
