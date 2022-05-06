package com.kangalia.projectdinosaur.common.entity.ai;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.kangalia.projectdinosaur.core.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;

public class MoveToGroundFeederGoal extends MoveToBlockGoal {
    PrehistoricEntity entity;

    public MoveToGroundFeederGoal(PathfinderMob pMob, double pSpeedModifier, int pSearchRange, int pVerticalSearchRange) {
        super(pMob, pSpeedModifier, pSearchRange, pVerticalSearchRange);
        this.entity = (PrehistoricEntity) pMob;
    }

    @Override
    protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
        if (entity.isHungry()) {
            return pLevel.getBlockState(pPos).is(BlockInit.GROUND_FEEDER.get());
        }
        return false;
    }

}
