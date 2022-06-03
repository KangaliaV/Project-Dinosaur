package com.kangalia.projectdinosaur.common.entity.ai;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.PanicGoal;

public class PrehistoricBabyPanicGoal extends PanicGoal {
    PrehistoricEntity prehistoric;

    public PrehistoricBabyPanicGoal(PathfinderMob pMob, double pSpeedModifier) {
        super(pMob, pSpeedModifier);
        this.prehistoric = (PrehistoricEntity) pMob;
    }

    protected boolean shouldPanic() {
        if (this.mob.isBaby()) {
            return this.mob.getLastHurtByMob() != null;
        } else {
            return this.mob.isFreezing() || this.mob.isOnFire();
        }
    }
}
