package com.kangalia.projectdinosaur.common.entity.creature;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.kangalia.projectdinosaur.common.entity.ai.*;
import com.kangalia.projectdinosaur.common.entity.genetics.genomes.MeganeuraGenome;
import com.kangalia.projectdinosaur.common.entity.parts.PrehistoricPart;
import com.kangalia.projectdinosaur.core.init.BlockInit;
import com.kangalia.projectdinosaur.core.init.EntityInit;
import com.kangalia.projectdinosaur.core.init.SoundInit;
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
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.entity.PartEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Objects;

public class MeganeuraEntity extends PrehistoricEntity implements GeoEntity, FlyingAnimal {

    private static final EntityDataAccessor<String> GENOME = SynchedEntityData.defineId(MeganeuraEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Integer> REST_TIMER = SynchedEntityData.defineId(MeganeuraEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> RESTING_TIME = SynchedEntityData.defineId(MeganeuraEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> RESTING = SynchedEntityData.defineId(MeganeuraEntity.class, EntityDataSerializers.BOOLEAN);

    private final AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    private final MeganeuraGenome genome = new MeganeuraGenome();
    public boolean shouldRest = false;

    public MeganeuraEntity(EntityType<? extends TamableAnimal> entityType, Level world) {
        super(entityType, world);
        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.lookControl = new MeganeuraEntity.MeganeuraLookControl();
        this.setMaxUpStep(0.5F);
        minSize = 0.25F;
        maxMaleSize = 1.3F;
        maxFemaleSize = 1.1F;
        minHeight = 0.75f;
        maxHeight = 1.5f;
        minWidth = 1.1f;
        maxWidth = 1.75f;
        maxFood = 80;
        diet = 1;
        soundVolume = 0.3F;
        sleepSchedule = 0;
        name = Component.translatable("dino.projectdinosaur.meganeura");
        nameScientific = Component.translatable("dino.projectdinosaur.meganeura.scientific");
        renderScale = 25;
        breedingType = 0;
        maleRoamDistance = 64;
        femaleRoamDistance = 48;
        juvinileRoamDistance = 24;
        childRoamDistance = 10;
        babyRoamDistance = 4;
        isLand = true;
        maxPack = 10;
        minPack = 4;
        maxTotalPack = 10;
    }

    public static AttributeSupplier.Builder setCustomAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 100.0F)
                .add(Attributes.FLYING_SPEED, 1.4F)
                .add(Attributes.MOVEMENT_SPEED, 0.35F)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .add(Attributes.ATTACK_DAMAGE, 8.0F)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5F)
                .add(Attributes.ATTACK_SPEED, 1.0F);
    }

    @Override
    public void randomizeAttributes(int age) {
        if (age == 0) {
            this.getAttribute(Attributes.FLYING_SPEED).setBaseValue(10.0F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 5)));
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.35F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 5)));
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(8.0F*genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 6)));
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(100.0F*genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 7)));
        } else if (age == 1) {
            this.getAttribute(Attributes.FLYING_SPEED).setBaseValue(10.0F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 5)) / 1.5);
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.35F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 5)) / 1.5);
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(8.0F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 6)) / 2);
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(100.0F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 7)) / 2);
        } else {
            this.getAttribute(Attributes.FLYING_SPEED).setBaseValue(10.0F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 5)) / 2);
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.35F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 5)) / 2);
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(8.0F*genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 6)) / 4);
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(100.0F*genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 7)) / 4);
        }
    }

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        if (!this.isInWater() && !this.onGround() && !this.isResting()) {
            event.getController().setAnimation(RawAnimation.begin().then("animation.meganeura.fly", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(1.5);
        } else if (this.isInWater()) {
            event.getController().setAnimation(RawAnimation.begin().then("animation.meganeura.swim", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(0.35);
        } else if (this.isResting()) {
            event.getController().setAnimation(RawAnimation.begin().then("animation.meganeura.sleep", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(0.35);
        } else {
            event.getController().setAnimation(RawAnimation.begin().then("animation.meganeura.idle", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(1);
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<MeganeuraEntity>(this, "controller", 20, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.factory;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PrehistoricBabyAvoidEntityGoal<>(this, Player.class, 4.0F, 2.0D, 1.5D));
        this.goalSelector.addGoal(0, new PrehistoricBabyPanicGoal(this, 2.0D));
        this.goalSelector.addGoal(0, new PrehistoricSleepInNestGoal(this, 2.0D, 32, 20));
        this.goalSelector.addGoal(0, new PrehistoricGiveBirthGoal(this, this.getMate(), 2.0D, 32));
        this.goalSelector.addGoal(0, new PrehistoricCheckPackGoal(this, 4, 10));
        this.goalSelector.addGoal(1, new PrehistoricBreedGoal(this, 2.0D));
        this.goalSelector.addGoal(1, new PrehistoricEatFromFeederGoal(this, 2.0D, 32));
        this.goalSelector.addGoal(1, new PrehistoricPlayWithEnrichmentGoal(this, 2.0D, 32));
        this.goalSelector.addGoal(1, new PrehistoricMeleeAttackGoal(this, 2.0D, true));
        this.goalSelector.addGoal(2, new MeganeuraWanderGoal(this));
        this.goalSelector.addGoal(3, new FloatGoal(this));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(0, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, 25, true, false, this::isMoodyAt));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, MeganeuraEntity.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Pig.class, 20, false, false, (p_28600_) -> p_28600_ instanceof Pig));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Cow.class, 20, false, false, (p_28600_) -> p_28600_ instanceof Cow));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, ScelidosaurusEntity.class, 10, true, false, (p_28600_) -> p_28600_ instanceof ScelidosaurusEntity));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Chicken.class, 20, false, false, (p_28600_) -> p_28600_ instanceof Chicken));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Rabbit.class, 20, false, false, (p_28600_) -> p_28600_ instanceof Rabbit));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Sheep.class, 20, false, false, (p_28600_) -> p_28600_ instanceof Sheep));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, GastornisEntity.class, 10, true, false, (p_28600_) -> p_28600_ instanceof GastornisEntity));
        this.targetSelector.addGoal(5, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    @Override
    public void tick() {
        super.tick();
        int maxRestingTime;
        int maxTimer;
        if (this.level().isDay()) {
            maxRestingTime = 400;
            maxTimer = 2000;
        } else {
            maxRestingTime = 800;
            maxTimer = 800;
        }
        if (!this.level().isClientSide()) {
            if (this.isAngry() || this.isStarving()) {
                this.setResting(false);
                this.setRestingTime(0);
                this.shouldRest = false;
            }
            if (this.getRestTimer() > 0) {
                this.setRestTimer(this.getRestTimer() - 1);
            } else {
                this.shouldRest = true;
            }
            if (this.isResting() && this.getRestingTime() < maxRestingTime) {
                this.setRestingTime(this.getRestingTime() + 1);
            } else if (this.isResting() && this.getRestingTime() >= 400) {
                this.setResting(false);
                this.shouldRest = false;
                this.setRestTimer(this.random.nextInt(1000) + maxTimer);
                this.setRestingTime(0);
            }
        }
    }

    @Override
    protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {
    }

    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, pLevel);
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(true);
        flyingpathnavigation.setCanPassDoors(false);
        return flyingpathnavigation;
    }

    public boolean isCustomMultiPart() {
        return isMultipartEntity();
    }

    @Override
    public boolean isMultipartEntity() {
        return false;
    }
    
    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public boolean canBeCollidedWith() {
        return !isCustomMultiPart();
    }

    @Override
    public float getPickRadius() {
        if (isCustomMultiPart()) {
            return getType().getWidth() * getAgeScale() - getBbWidth();
        }
        return super.getPickRadius();
    }
    
    @Override
    public int getAmbientSoundInterval() {
        return 120;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        //return SoundInit.MEGANEURA_BUZZ.get();
        return SoundEvents.BEE_LOOP;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        //return SoundInit.MEGANEURA_HURT.get();
        return SoundEvents.BEE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        //return SoundInit.MEGANEURA_DEATH.get();
        return SoundEvents.BEE_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean canBeLeashed(Player pPlayer) {
        return false;
    }


    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@Nonnull ServerLevel serverWorld, @Nonnull AgeableMob ageableMob) {
        return EntityInit.MEGANEURA.get().create(serverWorld);
    }

    @Override
    public int getClutchSize() {
        return 1;
    }

    @Override
    public int getAdultAge() {
        return 4;
    }

    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor serverLevelAccessor, @NotNull DifficultyInstance difficultyInstance, @NotNull MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag compoundTag) {
        this.setGenes(this.generateGenes(true));
        System.out.println(this.getGenes());
        this.setAttributes(0);
        this.setRestTimer(2000);
        return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, compoundTag);
    }

    @Override
    public float getGenderMaxSize() {
        float sizeCoefficient = genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 7));
        if (this.getGender() == 0) {
            return sizeCoefficient * maxMaleSize;
        } else {
            return sizeCoefficient * maxFemaleSize;
        }
    }

    @Override
    public float getMaxHeight() {
        float sizeCoefficient = genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 7));
        if (this.getGender() == 0) {
            return sizeCoefficient * maxHeight;
        } else {
            return sizeCoefficient * (maxHeight - 0.2f);
        }
    }

    @Override
    public float getMaxWidth() {
        float sizeCoefficient = genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 7));
        if (this.getGender() == 0) {
            return sizeCoefficient * maxWidth;
        } else {
            return sizeCoefficient * (maxWidth - 0.1f);
        }
    }

    public String generateGenes(boolean allowed) {
        if (allowed) {
            return genome.setRandomGenes();
        } else {
            return genome.setRandomAllowedGenes();
        }
    }

    public String inheritGenes(String parent1, String parent2) {
        return genome.setInheritedGenes(parent1, parent2);
    }

    @Override
    public String getGenes() {
        return this.entityData.get(GENOME);
    }

    public void setGenes(String genes) {
        this.entityData.set(GENOME, genes);
    }

    public String getGeneDominance(int gene) {
        String alleles = genome.getAlleles(this.getGenes(), gene);
        if (gene == 1) {
            return genome.calculateDominanceBC(alleles);
        } else if (gene == 2) {
            return genome.calculateDominanceTC(alleles);
        } else if (gene == 3) {
            return genome.calculateDominancePC(alleles);
        } else {
            return genome.calculateDominanceWC(alleles);
        }
    }

    public String getCoefficientRating(int gene) {
        String alleles = genome.getAlleles(this.getGenes(), gene);
        float coefficient = genome.calculateCoefficient(alleles);
        if (coefficient == 1.2f) {
            return "Highest";
        } else if (coefficient < 1.2f && coefficient >= 1.1f) {
            return "High";
        } else if (coefficient < 1.1f && coefficient >= 1f) {
            return "Mid-High";
        } else if (coefficient < 1f && coefficient >= 0.9f) {
            return "Mid-Low";
        } else if (coefficient < 0.9f && coefficient > 0.8f) {
            return "Low";
        } else if (coefficient == 0.8f) {
            return "Lowest";
        } else {
            return "Error";
        }
    }

    public boolean isResting() {
        return entityData.get(RESTING);
    }

    public void setResting(boolean resting) {
        entityData.set(RESTING, resting);
    }

    public int getRestTimer() {
        return entityData.get(REST_TIMER);
    }

    public void setRestTimer(int timer) {
        entityData.set(REST_TIMER, timer);
    }

    public int getRestingTime() {
        return entityData.get(RESTING_TIME);
    }

    public void setRestingTime(int timer) {
        entityData.set(RESTING_TIME, timer);
    }


    @Override
    public String getColourMorph() {
        if (genome.isAlbino(this.getGenes())) {
            return "Albino";
        } else if (genome.isMelanistic(this.getGenes())) {
            return "Melanistic";
        } else {
            return "Normal";
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(GENOME, "");
        this.entityData.define(REST_TIMER, 0);
        this.entityData.define(RESTING_TIME, 0);
        this.entityData.define(RESTING, false);

    }

    @Override
    public void addAdditionalSaveData(@Nonnull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putString("Genome", this.getGenes());
        pCompound.putInt("RestTimer", this.getRestTimer());
        pCompound.putInt("RestingTime", this.getRestingTime());
        pCompound.putBoolean("Resting", this.isResting());

    }

    @Override
    public void readAdditionalSaveData(@Nonnull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setGenes(pCompound.getString("Genome"));
        this.setRestTimer(pCompound.getInt("RestTimer"));
        this.setRestingTime(pCompound.getInt("RestingTime"));
        this.setResting(pCompound.getBoolean("Resting"));

    }

    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    public class MeganeuraLookControl extends LookControl {
        public MeganeuraLookControl() {
            super(MeganeuraEntity.this);
        }

        public void tick() {
            if (!MeganeuraEntity.this.isResting()) {
                super.tick();
            }

        }
    }

    class MeganeuraWanderGoal extends Goal {

        MeganeuraEntity meganeura;
        MeganeuraWanderGoal(MeganeuraEntity entity) {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
            meganeura = entity;
        }
        
        public boolean canUse() {
            return MeganeuraEntity.this.navigation.isDone() && MeganeuraEntity.this.random.nextInt(10) == 0 && MeganeuraEntity.this.getRestingTime() < 400;
        }
        
        public boolean canContinueToUse() {
            return MeganeuraEntity.this.navigation.isInProgress() && !MeganeuraEntity.this.isResting();
        }
        
        public void start() {
            Vec3 vec3 = this.findPos();
            if (!MeganeuraEntity.this.isResting()) {
                if (vec3 != null) {
                    MeganeuraEntity.this.navigation.moveTo(MeganeuraEntity.this.navigation.createPath(BlockPos.containing(vec3), 1), 1.0D);
                }
            }
        }

        public void tick() {
            if (!MeganeuraEntity.this.isFlying() && MeganeuraEntity.this.shouldRest) {
                MeganeuraEntity.this.setResting(true);
                MeganeuraEntity.this.shouldRest = false;
                MeganeuraEntity.this.navigation.stop();
                MeganeuraEntity.this.setDeltaMovement(Vec3.ZERO);
            }
        }

        @javax.annotation.Nullable
        private Vec3 findPos() {
            Vec3 vec3 = MeganeuraEntity.this.getViewVector(0.0F);
            Vec3 vec32;
            if (MeganeuraEntity.this.shouldRest) {
                vec32 = LandRandomPos.getPos(MeganeuraEntity.this, 16, 4);
            } else {
                vec32 = HoverRandomPos.getPos(MeganeuraEntity.this, 16, 4, vec3.x, vec3.z, 0, 3, 1);
            }
            return vec32 != null ? vec32 : AirAndWaterRandomPos.getPos(MeganeuraEntity.this, 16, 4, -2, vec3.x, vec3.z, 0);
        }
    }
}
