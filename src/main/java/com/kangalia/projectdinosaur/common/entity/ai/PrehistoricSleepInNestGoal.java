package com.kangalia.projectdinosaur.common.entity.ai;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.kangalia.projectdinosaur.core.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;

import java.util.Random;

public class PrehistoricSleepInNestGoal extends MoveToBlockGoal {

    PrehistoricEntity prehistoricEntity;
    Block nest;
    Random random = new Random();
    int chance;

    public PrehistoricSleepInNestGoal(PrehistoricEntity prehistoric, double pSpeedModifier, int pSearchRange, int changeHomeChance) {
        super(prehistoric, pSpeedModifier, pSearchRange);
        this.prehistoricEntity = prehistoric;
        this.chance = changeHomeChance;
    }

    @Override
    public boolean canUse() {
        if (!prehistoricEntity.shouldSleep()) {
            return false;
        } else {
            return super.canUse();
        }
    }

    @Override
    public boolean canContinueToUse() {
        if (!prehistoricEntity.shouldSleep()) {
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
            prehistoricEntity.setCanSleep(true);
            prehistoricEntity.setNestPos(this.blockPos);
            prehistoricEntity.restrictTo(this.blockPos, prehistoricEntity.getRoamDistance());
        }
    }

    @Override
    protected boolean findNearestBlock() {
        if (super.findNearestBlock()) {
            if (prehistoricEntity.getNestPos() != BlockPos.ZERO) {
                if (this.blockPos != prehistoricEntity.getNestPos()) {
                    if (this.random.nextInt(chance) != 0) {
                        this.blockPos = prehistoricEntity.getNestPos();
                    }
                }
            }
            return true;
        } else {
            if (prehistoricEntity.getNestPos() != BlockPos.ZERO) {
                this.blockPos = prehistoricEntity.getNestPos();
                return this.isValidTarget(this.mob.level, this.blockPos);
            }
        }
        return false;
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
