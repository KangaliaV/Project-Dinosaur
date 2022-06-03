package com.kangalia.projectdinosaur.common.entity.ai;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.player.Player;

public class PrehistoricBabyAvoidEntityGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {
    PrehistoricEntity prehistoric;

    public PrehistoricBabyAvoidEntityGoal(PrehistoricEntity pMob, Class<T> pEntityClassToAvoid, float pMaxDistance, double pWalkSpeedModifier, double pSprintSpeedModifier) {
        super(pMob, pEntityClassToAvoid, pMaxDistance, pWalkSpeedModifier, pSprintSpeedModifier);
        this.prehistoric = pMob;
    }

    public boolean canUse() {
        if (super.canUse() && !this.prehistoric.isSleeping() && this.toAvoid instanceof Player) {
            return prehistoric.isBaby();
        } else {
            return false;
        }
    }
}
