package com.kangalia.projectdinosaur.common.entity.creature;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.kangalia.projectdinosaur.common.entity.ai.PrehistoricBabyAvoidEntityGoal;
import com.kangalia.projectdinosaur.common.entity.ai.PrehistoricBabyPanicGoal;
import com.kangalia.projectdinosaur.common.entity.ai.PrehistoricMeleeAttackGoal;
import com.kangalia.projectdinosaur.core.init.BlockInit;
import com.kangalia.projectdinosaur.core.init.EntityInit;
import com.kangalia.projectdinosaur.core.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.AmphibiousNodeEvaluator;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimatableModel;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nonnull;

public class AphanerammaEntity extends PrehistoricEntity implements IAnimatable {

    private static final EntityDataAccessor<BlockPos> TRAVEL_POS = SynchedEntityData.defineId(AphanerammaEntity.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Boolean> TRAVELLING = SynchedEntityData.defineId(AphanerammaEntity.class, EntityDataSerializers.BOOLEAN);

    private AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public AphanerammaEntity(EntityType<? extends TamableAnimal> entityType, Level world) {
        super(entityType, world);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.moveControl = new AphanerammaEntity.AphanerammaMoveControl(this);
        this.lookControl = new AphanerammaEntity.AphanerammaLookControl(this, 20);
        this.maxUpStep = 1.0F;
        minSize = 0.25F;
        maxMaleSize = 0.8F;
        maxFemaleSize = 1.0F;
        maxFood = 50;
        diet = 2;
        soundVolume = 0.2F;
        sleepSchedule = 0;
        name = Component.translatable("dino.projectdinosaur.aphaneramma");
        renderScale = 60;
    }

    public static AttributeSupplier.Builder setCustomAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 4.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.1F)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .add(Attributes.ATTACK_DAMAGE, 1.0F)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5F)
                .add(Attributes.ATTACK_SPEED, 1.0F);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (!(event.getLimbSwingAmount() > -0.05F && event.getLimbSwingAmount() < 0.05F) && !this.isInWater()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.Aphaneramma.walk", ILoopType.EDefaultLoopTypes.LOOP));
            event.getController().setAnimationSpeed(1.0);
        } else if (this.isInWater()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.Aphaneramma.swim", ILoopType.EDefaultLoopTypes.LOOP));
            event.getController().setAnimationSpeed(1.0);
        } else if (this.isSleeping()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.Aphaneramma.sleep", ILoopType.EDefaultLoopTypes.LOOP));
            event.getController().setAnimationSpeed(0.5);
        } else if (this.isScrem()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.Aphaneramma.screm", ILoopType.EDefaultLoopTypes.LOOP));
            event.getController().setAnimationSpeed(1.0);
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.Aphaneramma.idle", ILoopType.EDefaultLoopTypes.LOOP));
            event.getController().setAnimationSpeed(1.0);
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.setResetSpeedInTicks(10);
        data.addAnimationController(new AnimationController<AphanerammaEntity>(this, "controller", 4, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PrehistoricBabyAvoidEntityGoal<>(this, Player.class, 4.0F, 1.5D, 1.5D));
        this.goalSelector.addGoal(0, new PrehistoricBabyPanicGoal(this, 1.5D));
        this.goalSelector.addGoal(1, new AphanerammaTravelGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new AphanerammaRandomStrollGoal(this, 1.0D, 100));
        this.goalSelector.addGoal(3, new PrehistoricMeleeAttackGoal(this, 2.0D, true));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, 25, true, false, this::isMoodyAt));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractFish.class, 20, false, false, (p_28600_) -> p_28600_ instanceof AbstractSchoolingFish));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Squid.class, 20, false, false, (p_28600_) -> p_28600_ instanceof Squid));
    }

    /*@Override
    protected int getExperienceReward(Player player) {
        return 1 + this.level.random.nextInt(4);
    }*/

    @Override
    public int getAmbientSoundInterval() {
        return 100;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (!this.isInWater()) {
            return SoundEvents.AXOLOTL_IDLE_AIR;
        } else {
            return SoundEvents.AXOLOTL_IDLE_WATER;
        }
    }

    void setTravelPos(BlockPos pTravelPos) {
        this.entityData.set(TRAVEL_POS, pTravelPos);
    }

    BlockPos getTravelPos() {
        return this.entityData.get(TRAVEL_POS);
    }

    boolean isTravelling() {
        return this.entityData.get(TRAVELLING);
    }

    void setTravelling(boolean pIsTravelling) {
        this.entityData.set(TRAVELLING, pIsTravelling);
    }

    @Override
    public int getBreedingType() {
        return 1;
    }

    @Override
    public ItemStack getSpawnType() {
        return ItemInit.APHANERAMMA_SPAWN_ITEM.get().getDefaultInstance();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.AXOLOTL_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.AXOLOTL_DEATH;
    }


    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.FROG_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean canBeLeashed(Player pPlayer) {
        return false;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public MobType getMobType() {
        return MobType.WATER;
    }

    protected PathNavigation createNavigation(Level level) {
        return new AphanerammaPathNavigation(this, level);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public AgeableMob getBreedOffspring(@Nonnull ServerLevel serverWorld, @Nonnull AgeableMob ageableMob) {
        return EntityInit.APHANERAMMA.get().create(serverWorld);
    }

    @Override
    public int getAdultAge() {
        return 5;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TRAVEL_POS, BlockPos.ZERO);
        this.entityData.define(TRAVELLING, false);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("TravelPosX", this.getTravelPos().getX());
        pCompound.putInt("TravelPosY", this.getTravelPos().getY());
        pCompound.putInt("TravelPosZ", this.getTravelPos().getZ());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        int l = pCompound.getInt("TravelPosX");
        int i1 = pCompound.getInt("TravelPosY");
        int j1 = pCompound.getInt("TravelPosZ");
        this.setTravelPos(new BlockPos(l, i1, j1));
    }

    public void travel(Vec3 p_149181_) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), p_149181_);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
        } else {
            super.travel(p_149181_);
        }
    }

    class AphanerammaLookControl extends SmoothSwimmingLookControl {
        public AphanerammaLookControl(AphanerammaEntity p_149210_, int p_149211_) {
            super(p_149210_, p_149211_);
        }

        public void tick() {
            if (!AphanerammaEntity.this.isSleeping()) {
                super.tick();
            }
        }
    }

    static class AphanerammaMoveControl extends MoveControl {
        private final AphanerammaEntity aphaneramma;

        AphanerammaMoveControl(AphanerammaEntity aphanerammaEntity) {
            super(aphanerammaEntity);
            this.aphaneramma = aphanerammaEntity;
        }

        private void updateSpeed() {
            if (this.aphaneramma.isInWater()) {
                this.aphaneramma.setDeltaMovement(this.aphaneramma.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
                if (this.aphaneramma.isBaby()) {
                    this.aphaneramma.setSpeed(Math.max(this.aphaneramma.getSpeed() / 2.0F, 0.06F));

                }
            } else if (this.aphaneramma.onGround) {
                this.aphaneramma.setSpeed(Math.max(this.aphaneramma.getSpeed(), 0.06F));
            }
        }

        public void tick() {
            this.updateSpeed();
            if (this.operation == MoveControl.Operation.MOVE_TO && !this.aphaneramma.getNavigation().isDone()) {
                double d0 = this.wantedX - this.aphaneramma.getX();
                double d1 = this.wantedY - this.aphaneramma.getY();
                double d2 = this.wantedZ - this.aphaneramma.getZ();
                double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 /= d3;
                float f = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                this.aphaneramma.setYRot(this.rotlerp(this.aphaneramma.getYRot(), f, 90.0F));
                this.aphaneramma.yBodyRot = this.aphaneramma.getYRot();
                float f1 = (float)(this.speedModifier * this.aphaneramma.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.aphaneramma.setSpeed(Mth.lerp(0.125F, this.aphaneramma.getSpeed(), f1));
                this.aphaneramma.setDeltaMovement(this.aphaneramma.getDeltaMovement().add(0.0D, (double)this.aphaneramma.getSpeed() * d1 * 0.1D, 0.0D));
            } else {
                this.aphaneramma.setSpeed(0.0F);
            }
        }
    }

    static class AphanerammaPathNavigation extends AmphibiousPathNavigation {
        AphanerammaPathNavigation(AphanerammaEntity entity, Level pLevel) {
            super(entity, pLevel);
        }

        public boolean isStableDestination(BlockPos pPos) {
            Mob mob = this.mob;
            if (mob instanceof AphanerammaEntity aphaneramma) {
                if (aphaneramma.isTravelling()) {
                    return this.level.getBlockState(pPos).is(Blocks.WATER);
                }
            }

            return !this.level.getBlockState(pPos.below()).isAir();
        }
    }

    static class AphanerammaRandomStrollGoal extends RandomStrollGoal {
        private final AphanerammaEntity aphaneramma;

        AphanerammaRandomStrollGoal(AphanerammaEntity entity, double pSpeedModifier, int pInterval) {
            super(entity, pSpeedModifier, pInterval);
            this.aphaneramma = entity;
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return !this.mob.isInWater() && !this.aphaneramma.isSleeping() && super.canUse();
        }
    }

    static class AphanerammaTravelGoal extends Goal {
        private final AphanerammaEntity aphaneramma;
        private final double speedModifier;
        private boolean stuck;

        AphanerammaTravelGoal(AphanerammaEntity entity, double pSpeedModifier) {
            this.aphaneramma = entity;
            this.speedModifier = pSpeedModifier;
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return this.aphaneramma.isInWater() && !this.aphaneramma.isSleeping();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            int i = 512;
            int j = 4;
            RandomSource randomsource = this.aphaneramma.random;
            int k = randomsource.nextInt(1025) - 512;
            int l = randomsource.nextInt(9) - 4;
            int i1 = randomsource.nextInt(1025) - 512;
            if ((double)l + this.aphaneramma.getY() > (double)(this.aphaneramma.level.getSeaLevel() - 1)) {
                l = 0;
            }

            BlockPos blockpos = new BlockPos((double)k + this.aphaneramma.getX(), (double)l + this.aphaneramma.getY(), (double)i1 + this.aphaneramma.getZ());
            this.aphaneramma.setTravelPos(blockpos);
            this.aphaneramma.setTravelling(true);
            this.stuck = false;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (this.aphaneramma.getNavigation().isDone()) {
                Vec3 vec3 = Vec3.atBottomCenterOf(this.aphaneramma.getTravelPos());
                Vec3 vec31 = DefaultRandomPos.getPosTowards(this.aphaneramma, 16, 3, vec3, (double)((float)Math.PI / 10F));
                if (vec31 == null) {
                    vec31 = DefaultRandomPos.getPosTowards(this.aphaneramma, 8, 7, vec3, (double)((float)Math.PI / 2F));
                }
                if (vec31 != null) {
                    int i = Mth.floor(vec31.x);
                    int j = Mth.floor(vec31.z);
                    int k = 34;
                    if (!this.aphaneramma.level.hasChunksAt(i - 34, j - 34, i + 34, j + 34)) {
                        vec31 = null;
                    }
                }
                if (vec31 == null) {
                    this.stuck = true;
                    return;
                }
                this.aphaneramma.getNavigation().moveTo(vec31.x, vec31.y, vec31.z, this.speedModifier);
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            return !this.aphaneramma.getNavigation().isDone() && !this.stuck;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void stop() {
            this.aphaneramma.setTravelling(false);
            super.stop();
        }
    }
}
