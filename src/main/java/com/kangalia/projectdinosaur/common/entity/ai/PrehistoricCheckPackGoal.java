package com.kangalia.projectdinosaur.common.entity.ai;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class PrehistoricCheckPackGoal extends Goal {

    private static final TargetingConditions PARTNER_TARGETING = TargetingConditions.forCombat().range(32.0D).ignoreLineOfSight();
    private final Class<? extends Animal> packClass;
    protected final Level level;
    @Nullable
    PrehistoricEntity prehistoricEntity;
    private final int minPack;
    private final int maxPack;
    Random random = new Random();


    public PrehistoricCheckPackGoal(PrehistoricEntity prehistoric, int minPack, int maxPack) {
        this.prehistoricEntity = prehistoric;
        this.minPack = minPack;
        this.maxPack = maxPack;
        this.level = prehistoric.getLevel();
        this.packClass = prehistoric.getClass();
        this.setFlags(EnumSet.of(Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (this.prehistoricEntity == null) {
            return false;
        }
        if (this.prehistoricEntity.isSleeping() || this.prehistoricEntity.isCryosick()) {
            return false;
        }
        if (this.prehistoricEntity.getNestPos() == BlockPos.ZERO) {
            return false;
        }
        if (!this.prehistoricEntity.isAdult()) {
            return false;
        } else {
            return this.prehistoricEntity.getCheckPackTicks() <= 0;
        }
    }

    @Override
    public void stop() {
        this.prehistoricEntity.setCheckPackTicks(this.random.nextInt(3000) + 6000);
    }

    @Override
    public void tick() {
        this.getPackAroundNest();
    }

    private void getPackAroundNest() {
        assert this.prehistoricEntity != null;
        List<? extends Animal> packList = this.level.getNearbyEntities(this.packClass, PARTNER_TARGETING, this.prehistoricEntity, this.prehistoricEntity.getBoundingBox().inflate(32.0D));

        boolean a = packList.size() > 0;
        if (a) {
            for (int i = 0; i < packList.size(); i++) {
                Animal animal = packList.get(i);
                if (!(animal instanceof PrehistoricEntity)) {
                    packList.remove(animal);
                }
            }
        }
        if (a) {
            for (int i = 0; i < packList.size(); i++) {
                Animal animal = packList.get(i);
                PrehistoricEntity prehistoric = (PrehistoricEntity) animal;
                if (!prehistoric.isAdult()) {
                    packList.remove(animal);
                }
            }
        }
        if (a) {
            for (int i = 0; i < packList.size(); i++) {
                Animal animal = packList.get(i);
                PrehistoricEntity prehistoric = (PrehistoricEntity) animal;
                if (!this.prehistoricEntity.isWithinRestriction(prehistoric.getOnPos())) {
                    packList.remove(animal);
                }
            }
        }

        if (packList.size() > (this.maxPack - 1)) {
            PrehistoricEntity weakestPackMate = this.getWeakestPackmate(packList);
            this.prehistoricEntity.setTarget(weakestPackMate);
            this.prehistoricEntity.setPersistentAngerTarget(weakestPackMate.getUUID());
            this.prehistoricEntity.setLastHurtByMob(weakestPackMate);
            weakestPackMate.setTarget(this.prehistoricEntity);
            weakestPackMate.setPersistentAngerTarget(this.prehistoricEntity.getUUID());
            weakestPackMate.setLastHurtByMob(this.prehistoricEntity);
        } else if (packList.size() < (this.minPack - 1)) {
            this.prehistoricEntity.setLonely(true);
        }
        this.prehistoricEntity.setCheckPackTicks(this.random.nextInt(3000) + 6000);
    }

    private PrehistoricEntity getWeakestPackmate(List<? extends Animal> packList) {
        double lowestHealth = Double.MAX_VALUE;
        PrehistoricEntity weakestPackMember = null;
        for (Animal entity : packList) {
            PrehistoricEntity packmate = (PrehistoricEntity) entity;
            if (packmate.getHealth() < lowestHealth) {
                weakestPackMember = packmate;
                lowestHealth = packmate.getHealth();
            }
        }
        return weakestPackMember;
    }
}
