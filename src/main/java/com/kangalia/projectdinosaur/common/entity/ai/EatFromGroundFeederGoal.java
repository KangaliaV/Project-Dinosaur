package com.kangalia.projectdinosaur.common.entity.ai;

import com.kangalia.projectdinosaur.common.blockentity.GroundFeederBlockEntity;
import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;

import java.util.EnumSet;

public class EatFromGroundFeederGoal extends MoveToBlockGoal {
    protected final PrehistoricEntity mob;
    public final double speedModifier;
    protected int nextStartTick;
    protected int tryTicks;
    private int maxStayTicks;
    protected BlockPos blockPos = BlockPos.ZERO;
    private boolean reachedTarget;
    private final int searchRange;
    private final int verticalSearchRange;
    protected int verticalSearchStart;
    protected GroundFeederBlockEntity groundFeeder;

    //All of this code needs work - something is broken with it somewhere.
    public EatFromGroundFeederGoal(PathfinderMob pMob, double pSpeedModifier, int pSearchRange, int pVerticalSearchRange) {
        super(pMob, pSpeedModifier, pSearchRange, pVerticalSearchRange);
        this.mob = (PrehistoricEntity) pMob;
        this.speedModifier = pSpeedModifier;
        this.searchRange = pSearchRange;
        this.verticalSearchStart = 0;
        this.verticalSearchRange = pVerticalSearchRange;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP));
    }

    public boolean canUse() {
        if (!this.mob.isHungry()) {
            return false;
        }
        if (this.nextStartTick > 0) {
            --this.nextStartTick;
            return false;
        } else {
            this.nextStartTick = this.nextStartTick(this.mob);
            return this.findNearestBlock();
        }
    }

    protected int nextStartTick(PathfinderMob pCreature) {
        return reducedTickDelay(200 + pCreature.getRandom().nextInt(200));
    }

    public boolean canContinueToUse() {
        return this.tryTicks >= -this.maxStayTicks && this.tryTicks <= 1200 && this.isValidTarget(this.mob.level, this.blockPos);
    }

    public void start() {
        this.moveMobToBlock();
        this.tryTicks = 0;
        this.maxStayTicks = this.mob.getRandom().nextInt(this.mob.getRandom().nextInt(1200) + 1200) + 1200;
    }

    protected void moveMobToBlock() {
        this.mob.getNavigation().moveTo((double)((float)this.blockPos.getX()) + 0.5D, (double)(this.blockPos.getY() + 1), (double)((float)this.blockPos.getZ()) + 0.5D, this.speedModifier);
    }

    public double acceptedDistance() {
        return 1.0D;
    }

    protected BlockPos getMoveToTarget() {
        return this.blockPos.above();
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        BlockPos blockpos = this.getMoveToTarget();
        if (!blockpos.closerToCenterThan(this.mob.position(), this.acceptedDistance())) {
            this.reachedTarget = false;
            ++this.tryTicks;
            if (this.shouldRecalculatePath()) {
                this.mob.getNavigation().moveTo((double)((float)blockpos.getX()) + 0.5D, (double)blockpos.getY(), (double)((float)blockpos.getZ()) + 0.5D, this.speedModifier);
            }
        } else {
            this.reachedTarget = true;
            this.feedEntity(mob, groundFeeder);
            --this.tryTicks;
        }

    }

    public boolean shouldRecalculatePath() {
        return this.tryTicks % 40 == 0;
    }

    protected boolean isReachedTarget() {
        return this.reachedTarget;
    }

    protected boolean findNearestBlock() {
        int i = this.searchRange;
        int j = this.verticalSearchRange;
        BlockPos blockpos = this.mob.blockPosition();
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(int k = this.verticalSearchStart; k <= j; k = k > 0 ? -k : 1 - k) {
            for(int l = 0; l < i; ++l) {
                for(int i1 = 0; i1 <= l; i1 = i1 > 0 ? -i1 : 1 - i1) {
                    for(int j1 = i1 < l && i1 > -l ? l : 0; j1 <= l; j1 = j1 > 0 ? -j1 : 1 - j1) {
                        blockpos$mutableblockpos.setWithOffset(blockpos, i1, k - 1, j1);
                        if (this.mob.isWithinRestriction(blockpos$mutableblockpos) && this.isValidTarget(this.mob.level, blockpos$mutableblockpos)) {
                            this.blockPos = blockpos$mutableblockpos;
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    @Override
    protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
        if (mob.isHungry()) {
            groundFeeder = (GroundFeederBlockEntity) pLevel.getBlockEntity(pPos);
            return groundFeeder != null;
        }
        return false;
    }

    public void feedEntity(PrehistoricEntity entity, GroundFeederBlockEntity groundFeeder) {
        while (entity.isHungry()) {
            if (!groundFeeder.isEmpty(entity)) {
                if (entity.getDiet() == 0) {
                    groundFeeder.herbi = groundFeeder.herbi - 1;
                    entity.setHunger(entity.getHunger() + 1);
                } else if (entity.getDiet() == 1) {
                    groundFeeder.carni = groundFeeder.carni - 1;
                    entity.setHunger(entity.getHunger() + 1);
                } else if (entity.getDiet() == 2) {
                    groundFeeder.pisci = groundFeeder.pisci - 1;
                    entity.setHunger(entity.getHunger() + 1);
                    System.out.println("Entity Hunger: "+entity.getHunger());
                    System.out.println("Feeder Amount: "+groundFeeder.pisci);
                }
                mob.level.playSound(null, mob.blockPosition(), SoundEvents.GENERIC_EAT, SoundSource.NEUTRAL, 1.0F, mob.getVoicePitch());
            }
        }
    }
}
