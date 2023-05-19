package com.kangalia.projectdinosaur.common.entity.ai;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.BreedGoal;

import java.util.Random;

public class PrehistoricBreedGoal extends BreedGoal {

    PrehistoricEntity prehistoricEntity;
    Random random = new Random();
    protected BlockPos blockPos = BlockPos.ZERO;

    public PrehistoricBreedGoal(PrehistoricEntity prehistoric, double pSpeedModifier) {
        super(prehistoric, pSpeedModifier, PrehistoricEntity.class);
        this.prehistoricEntity = prehistoric;
    }

    @Override
    public boolean canUse() {
        if (!prehistoricEntity.isAdult()) {
            return false;
        } else if (prehistoricEntity.getMatingTicks() > 0) {
            return false;
        } else if (prehistoricEntity.isGrumpy() || prehistoricEntity.isMoody()) {
            return false;
        } else {
            prehistoricEntity.setInLoveTime(600);
            if (super.canUse()) {
                if (this.partner != null) {
                    this.partner.setInLoveTime(600);
                    return super.canUse();
                }
            }
            return false;
        }
    }

    @Override
    protected void breed() {
        PrehistoricEntity mate = (PrehistoricEntity) this.partner;
        if (mate != null && prehistoricEntity != null) {
            prehistoricEntity.getNavigation().stop();
            mate.getNavigation().stop();
            if (prehistoricEntity.getGender() == 1) {
                prehistoricEntity.setPregnant(true);
                prehistoricEntity.setPregnancyTicks(300);
                prehistoricEntity.setMate(mate.getGenes());
                prehistoricEntity.setMatingTicks(this.random.nextInt(12000) + 12000);
                mate.setMatingTicks(this.random.nextInt(6000) + 6000);
            } else if (mate.getGender() == 1) {
                mate.setPregnant(true);
                mate.setPregnancyTicks(300);
                mate.setMate(prehistoricEntity.getGenes());
                mate.setMatingTicks(this.random.nextInt(12000) + 12000);
                prehistoricEntity.setMatingTicks(this.random.nextInt(6000) + 6000);
            }
            prehistoricEntity.resetLove();
            mate.resetLove();
        }
    }
}
