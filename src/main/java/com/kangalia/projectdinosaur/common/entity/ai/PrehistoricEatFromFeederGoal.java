package com.kangalia.projectdinosaur.common.entity.ai;

import com.kangalia.projectdinosaur.common.blockentity.GroundFeederBlockEntity;
import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;

public class PrehistoricEatFromFeederGoal extends MoveToBlockGoal {

    protected GroundFeederBlockEntity groundFeeder;
    PrehistoricEntity prehistoricEntity;

    public PrehistoricEatFromFeederGoal(PrehistoricEntity prehistoric, double pSpeedModifier, int pSearchRange) {
        super(prehistoric, pSpeedModifier, pSearchRange);
        this.prehistoricEntity = prehistoric;
    }

    @Override
    public boolean canUse() {
        if (!prehistoricEntity.isHungry()) {
            return false;
        } else {
            return super.canUse();
        }
    }

    @Override
    public boolean canContinueToUse() {
        if (prehistoricEntity.getHunger() >= prehistoricEntity.getMaxFood()) {
            return false;
        } else {
            return super.canContinueToUse();
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (isReachedTarget() && !groundFeeder.isEmpty(prehistoricEntity)) {
            groundFeeder.feedEntity(prehistoricEntity);
            prehistoricEntity.setHungerTicks(1600);
        }
    }

    @Override
    protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
        if (pLevel.getBlockEntity(pPos) instanceof GroundFeederBlockEntity) {
            groundFeeder = (GroundFeederBlockEntity) pLevel.getBlockEntity(pPos);
            return groundFeeder != null;
        }
        return false;
    }

    @Override
    public double acceptedDistance() {
        return 2.0D;
    }
}
