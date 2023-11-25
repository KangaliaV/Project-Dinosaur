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
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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
    private static final EntityDataAccessor<BlockPos> TRAVEL_POS = SynchedEntityData.defineId(MeganeuraEntity.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Boolean> TRAVELLING = SynchedEntityData.defineId(MeganeuraEntity.class, EntityDataSerializers.BOOLEAN);

    private final AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    private final MeganeuraGenome genome = new MeganeuraGenome();
    public boolean shouldRest = false;
    private int savedAge = 3;

    public MeganeuraEntity(EntityType<? extends TamableAnimal> entityType, Level world) {
        super(entityType, world);
        this.moveControl = new MeganeuraMoveControl(this, 20, true, 85, 10, 3.0F, 2.1F, false);
        this.lookControl = new MeganeuraLookControl(this, 10);
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
        breedingType = 1;
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
            this.getAttribute(Attributes.FLYING_SPEED).setBaseValue(100.0F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 5)));
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.35F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 5)));
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(8.0F*genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 6)));
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(100.0F*genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 7)));
        } else if (age == 1) {
            this.getAttribute(Attributes.FLYING_SPEED).setBaseValue(100.0F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 5)) / 1.5);
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.35F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 5)) / 1.5);
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(8.0F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 6)) / 2);
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(100.0F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 7)) / 2);
        } else {
            this.getAttribute(Attributes.FLYING_SPEED).setBaseValue(100.0F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 5)) / 2);
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
        } else if (this.isResting() && !this.level().isDay()) {
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
        this.goalSelector.addGoal(2, new MeganeuraBabyTravelGoal(this, 0.1D));
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
        int currentAge;
        if (this.isJuvenile() || this.isAdult()) {
            currentAge = 1;
        } else {
            currentAge = 0;
        }
        if (currentAge != savedAge) {
            this.navigation = this.createNavigation(this.level());
            savedAge = currentAge;
        }
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

    public void travel(Vec3 pTravelVector) {
        if (this.isBaby() || this.isChild()) {
            if (this.isEffectiveAi() && this.isInWater()) {
                this.moveRelative(this.getSpeed(), pTravelVector);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            }
        } else {
            if (this.isControlledByLocalInstance()) {
                if (this.isInWater()) {
                    this.moveRelative(0.02F, pTravelVector);
                    this.move(MoverType.SELF, this.getDeltaMovement());
                    this.setDeltaMovement(this.getDeltaMovement().scale(0.8F));
                } else if (this.isInLava()) {
                    this.moveRelative(0.02F, pTravelVector);
                    this.move(MoverType.SELF, this.getDeltaMovement());
                    this.setDeltaMovement(this.getDeltaMovement().scale(0.5D));
                } else {
                    BlockPos ground = getBlockPosBelowThatAffectsMyMovement();
                    float f = 0.91F;
                    if (this.onGround()) {
                        f = this.level().getBlockState(ground).getFriction(this.level(), ground, this) * 0.91F;
                    }

                    float f1 = 0.16277137F / (f * f * f);
                    f = 0.91F;
                    if (this.onGround()) {
                        f = this.level().getBlockState(ground).getFriction(this.level(), ground, this) * 0.91F;
                    }

                    this.moveRelative(this.onGround() ? 0.1F * f1 : 0.4F, pTravelVector);
                    this.move(MoverType.SELF, this.getDeltaMovement());
                    this.setDeltaMovement(this.getDeltaMovement().scale((double)f));
                }
            }

            this.calculateEntityAnimation(false);
        }
    }

    @Override
    protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {
    }

    @Override
    protected PathNavigation createNavigation(Level pLevel) {
        if (this.isAdult() || this.isJuvenile()) {
            FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, pLevel);
            flyingpathnavigation.setCanOpenDoors(false);
            flyingpathnavigation.setCanFloat(true);
            flyingpathnavigation.setCanPassDoors(false);
            return flyingpathnavigation;
        } else {
            return new MeganeuraSwimmingPathNavigation(this, this.level());
        }
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
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
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
        if (this.isAdult() || this.isJuvenile()) {
            //return SoundInit.MEGANEURA_BUZZ.get();
            return SoundEvents.BEE_LOOP;
        } else {
            return SoundEvents.SQUID_AMBIENT;
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        if (this.isAdult() || this.isJuvenile()) {
            //return SoundInit.MEGANEURA_HURT.get();
            return SoundEvents.BEE_HURT;
        } else {
            return SoundEvents.SQUID_HURT;
        }
    }

    @Override
    protected SoundEvent getDeathSound() {
        if (this.isAdult() || this.isJuvenile()) {
            //return SoundInit.MEGANEURA_DEATH.get();
            return SoundEvents.BEE_DEATH;
        } else {
            return SoundEvents.SQUID_DEATH;
        }
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean canBeLeashed(Player pPlayer) {
        return false;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return this.isBaby() || this.isChild();
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
        this.entityData.define(TRAVEL_POS, BlockPos.ZERO);
        this.entityData.define(TRAVELLING, false);

    }

    @Override
    public void addAdditionalSaveData(@Nonnull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putString("Genome", this.getGenes());
        pCompound.putInt("RestTimer", this.getRestTimer());
        pCompound.putInt("RestingTime", this.getRestingTime());
        pCompound.putBoolean("Resting", this.isResting());
        pCompound.putInt("TravelPosX", this.getTravelPos().getX());
        pCompound.putInt("TravelPosY", this.getTravelPos().getY());
        pCompound.putInt("TravelPosZ", this.getTravelPos().getZ());

    }

    @Override
    public void readAdditionalSaveData(@Nonnull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setGenes(pCompound.getString("Genome"));
        this.setRestTimer(pCompound.getInt("RestTimer"));
        this.setRestingTime(pCompound.getInt("RestingTime"));
        this.setResting(pCompound.getBoolean("Resting"));
        int l = pCompound.getInt("TravelPosX");
        int i1 = pCompound.getInt("TravelPosY");
        int j1 = pCompound.getInt("TravelPosZ");
        this.setTravelPos(new BlockPos(l, i1, j1));

    }

    @Override
    public boolean isFlying() {
        return !this.onGround() && !this.isInWater();
    }

    public static class MeganeuraLookControl extends LookControl {

        private final int maxYRotFromCenter;
        private final MeganeuraEntity meganeura;

        public MeganeuraLookControl(MeganeuraEntity entity, int pMaxYRotFromCenter) {
            super(entity);
            this.maxYRotFromCenter = pMaxYRotFromCenter;
            this.meganeura = entity;
        }

        public void tick() {
            if (!this.meganeura.isResting()) {
                if (this.meganeura.isAdult() || this.meganeura.isJuvenile()) {
                    super.tick();
                } else {
                    //From SmoothSwimmingLookControl
                    if (this.lookAtCooldown > 0) {
                        --this.lookAtCooldown;
                        this.getYRotD().ifPresent((p_287449_) -> {
                            this.mob.yHeadRot = this.rotateTowards(this.mob.yHeadRot, p_287449_ + 20.0F, this.yMaxRotSpeed);
                        });
                        this.getXRotD().ifPresent((p_289401_) -> {
                            this.mob.setXRot(this.rotateTowards(this.mob.getXRot(), p_289401_ + 10.0F, this.xMaxRotAngle));
                        });
                    } else {
                        if (this.mob.getNavigation().isDone()) {
                            this.mob.setXRot(this.rotateTowards(this.mob.getXRot(), 0.0F, 5.0F));
                        }

                        this.mob.yHeadRot = this.rotateTowards(this.mob.yHeadRot, this.mob.yBodyRot, this.yMaxRotSpeed);
                    }

                    float f = Mth.wrapDegrees(this.mob.yHeadRot - this.mob.yBodyRot);
                    if (f < (float)(-this.maxYRotFromCenter)) {
                        this.mob.yBodyRot -= 4.0F;
                    } else if (f > (float)this.maxYRotFromCenter) {
                        this.mob.yBodyRot += 4.0F;
                    }

                }
            }
        }
    }

    static class MeganeuraMoveControl extends MoveControl {
        private final int maxTurn;
        private final boolean hoversInPlace;
        private final int maxTurnX;
        private final int maxTurnY;
        private final float inWaterSpeedModifier;
        private final float outsideWaterSpeedModifier;
        private boolean applyGravity;
        private final MeganeuraEntity meganeuraEntity;

        public MeganeuraMoveControl(MeganeuraEntity pMob, int pMaxTurn, boolean pHoversInPlace, int pMaxTurnX, int pMaxTurnY, float pInWaterSpeedModifier, float pOutsideWaterSpeedModifier, boolean pApplyGravity) {
            super(pMob);
            this.meganeuraEntity = pMob;
            this.maxTurn = pMaxTurn;
            this.hoversInPlace = pHoversInPlace;
            this.maxTurnX = pMaxTurnX;
            this.maxTurnY = pMaxTurnY;
            this.inWaterSpeedModifier = pInWaterSpeedModifier;
            this.outsideWaterSpeedModifier = pOutsideWaterSpeedModifier;
            this.applyGravity = pApplyGravity;
        }

        public void tick() {
            if (this.meganeuraEntity.isAdult() || this.meganeuraEntity.isJuvenile()) {
                this.flying();
            } else {
                this.swimming();
            }
        }

        public void swimming() {
            if (this.meganeuraEntity.isFlying() && !this.mob.isInWater()) {
                this.applyGravity = true;
            }
            if (this.applyGravity && this.mob.isInWater()) {
                this.mob.setDeltaMovement(this.mob.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            }

            if (this.operation == MoveControl.Operation.MOVE_TO && !this.mob.getNavigation().isDone()) {
                double d0 = this.wantedX - this.mob.getX();
                double d1 = this.wantedY - this.mob.getY();
                double d2 = this.wantedZ - this.mob.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if (d3 < (double)2.5000003E-7F) {
                    this.mob.setZza(0.0F);
                } else {
                    float f = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                    this.mob.setYRot(this.rotlerp(this.mob.getYRot(), f, (float)this.maxTurnY));
                    this.mob.yBodyRot = this.mob.getYRot();
                    this.mob.yHeadRot = this.mob.getYRot();
                    float f1 = (float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    if (this.mob.isInWater()) {
                        this.mob.setSpeed(f1 * this.inWaterSpeedModifier);
                        double d4 = Math.sqrt(d0 * d0 + d2 * d2);
                        if (Math.abs(d1) > (double)1.0E-5F || Math.abs(d4) > (double)1.0E-5F) {
                            float f3 = -((float)(Mth.atan2(d1, d4) * (double)(180F / (float)Math.PI)));
                            f3 = Mth.clamp(Mth.wrapDegrees(f3), (float)(-this.maxTurnX), (float)this.maxTurnX);
                            this.mob.setXRot(this.rotlerp(this.mob.getXRot(), f3, 5.0F));
                        }

                        float f6 = Mth.cos(this.mob.getXRot() * ((float)Math.PI / 180F));
                        float f4 = Mth.sin(this.mob.getXRot() * ((float)Math.PI / 180F));
                        this.mob.zza = f6 * f1;
                        this.mob.yya = -f4 * f1;
                    } else {
                        float f5 = Math.abs(Mth.wrapDegrees(this.mob.getYRot() - f));
                        float f2 = getTurningSpeedFactor(f5);
                        this.mob.setSpeed(f1 * this.outsideWaterSpeedModifier * f2);
                    }

                }
            } else {
                this.mob.setSpeed(0.0F);
                this.mob.setXxa(0.0F);
                this.mob.setYya(0.0F);
                this.mob.setZza(0.0F);
            }
        }

        private static float getTurningSpeedFactor(float p_249853_) {
            return 1.0F - Mth.clamp((p_249853_ - 10.0F) / 50.0F, 0.0F, 1.0F);
        }

        public void flying() {
            if (this.operation == MoveControl.Operation.MOVE_TO) {
                this.operation = MoveControl.Operation.WAIT;
                this.mob.setNoGravity(true);
                double d0 = this.wantedX - this.mob.getX();
                double d1 = this.wantedY - this.mob.getY();
                double d2 = this.wantedZ - this.mob.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if (d3 < (double)2.5000003E-7F) {
                    this.mob.setYya(0.0F);
                    this.mob.setZza(0.0F);
                    return;
                }

                float f = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                this.mob.setYRot(this.rotlerp(this.mob.getYRot(), f, 90.0F));
                float f1;
                this.speedModifier = 100;
                if (this.mob.onGround()) {
                    f1 = (float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
                } else {
                    f1 = (float)(this.speedModifier * this.mob.getAttributeValue(Attributes.FLYING_SPEED));
                }

                this.mob.setSpeed(f1);
                double d4 = Math.sqrt(d0 * d0 + d2 * d2);
                if (Math.abs(d1) > (double)1.0E-5F || Math.abs(d4) > (double)1.0E-5F) {
                    float f2 = (float)(-(Mth.atan2(d1, d4) * (double)(180F / (float)Math.PI)));
                    this.mob.setXRot(this.rotlerp(this.mob.getXRot(), f2, (float)this.maxTurn));
                    this.mob.setYya(d1 > 0.0D ? f1 : -f1);
                }
            } else {
                if (!this.hoversInPlace) {
                    this.mob.setNoGravity(false);
                }

                this.mob.setYya(0.0F);
                this.mob.setZza(0.0F);
            }
        }
    }

    static class MeganeuraWanderGoal extends Goal {

        MeganeuraEntity meganeura;
        MeganeuraWanderGoal(MeganeuraEntity entity) {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
            meganeura = entity;
        }

        public boolean canUse() {
            if (this.meganeura.isBaby() || this.meganeura.isChild()) {
                return false;
            } else {
                return this.meganeura.navigation.isDone() && this.meganeura.random.nextInt(10) == 0 && this.meganeura.getRestingTime() < 400;
            }
        }
        
        public boolean canContinueToUse() {
            return this.meganeura.navigation.isInProgress() && !this.meganeura.isResting();
        }
        
        public void start() {
            Vec3 vec3 = this.findPos();
            if (!this.meganeura.isResting()) {
                if (vec3 != null) {
                    this.meganeura.navigation.moveTo(this.meganeura.navigation.createPath(BlockPos.containing(vec3), 1), 10.0D);
                }
            }
        }

        public void tick() {
            if (!this.meganeura.isFlying() && this.meganeura.shouldRest) {
                this.meganeura.setResting(true);
                this.meganeura.shouldRest = false;
                this.meganeura.navigation.stop();
                this.meganeura.setDeltaMovement(Vec3.ZERO);
            }
        }

        @javax.annotation.Nullable
        private Vec3 findPos() {
            Vec3 vec3 = this.meganeura.getViewVector(0.0F);
            Vec3 vec32;
            if (this.meganeura.shouldRest) {
                vec32 = LandRandomPos.getPos(this.meganeura, 16, 4);
            } else {
                vec32 = HoverRandomPos.getPos(this.meganeura, 20, 4, vec3.x, vec3.z, 0, 3, 1);
            }
            return vec32 != null ? vec32 : AirAndWaterRandomPos.getPos(this.meganeura, 20, 4, -2, vec3.x, vec3.z, 0);
        }
    }

    static class MeganeuraBabyTravelGoal extends Goal {
        private final MeganeuraEntity meganeura;
        private final double speedModifier;
        private boolean stuck;

        MeganeuraBabyTravelGoal(MeganeuraEntity entity, double pSpeedModifier) {
            this.meganeura = entity;
            this.speedModifier = pSpeedModifier;
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return this.meganeura.isBaby() || this.meganeura.isChild();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            RandomSource randomsource = this.meganeura.random;
            int k = randomsource.nextInt(1025) - 512;
            int l = randomsource.nextInt(9) - 4;
            int i1 = randomsource.nextInt(1025) - 512;
            if ((double)l + this.meganeura.getY() > (double)(this.meganeura.level().getSeaLevel() - 1)) {
                l = 0;
            }

            BlockPos blockpos = new BlockPos(k + (int)this.meganeura.getX(), l + (int)this.meganeura.getY(), i1 + (int)this.meganeura.getZ());
            this.meganeura.setTravelPos(blockpos);
            this.meganeura.setTravelling(true);
            this.stuck = false;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (this.meganeura.getNavigation().isDone()) {
                Vec3 vec3 = Vec3.atBottomCenterOf(this.meganeura.getTravelPos());
                Vec3 vec31 = DefaultRandomPos.getPosTowards(this.meganeura, 32, 3, vec3, (double)((float)Math.PI / 10F));
                if (vec31 == null) {
                    vec31 = DefaultRandomPos.getPosTowards(this.meganeura, 16, 7, vec3, (double)((float)Math.PI / 2F));
                }
                if (vec31 != null) {
                    int i = Mth.floor(vec31.x);
                    int j = Mth.floor(vec31.z);
                    if (!this.meganeura.level().hasChunksAt(i - 34, j - 34, i + 34, j + 34)) {
                        vec31 = null;
                    }
                }
                if (vec31 == null) {
                    this.stuck = true;
                    return;
                }
                this.meganeura.getNavigation().moveTo(vec31.x, vec31.y, vec31.z, this.speedModifier);
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            return !this.meganeura.getNavigation().isDone() && !this.stuck;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void stop() {
            this.meganeura.setTravelling(false);
            super.stop();
        }
    }

    static class MeganeuraSwimmingPathNavigation extends AmphibiousPathNavigation {
        MeganeuraSwimmingPathNavigation(MeganeuraEntity entity, Level pLevel) {
            super(entity, pLevel);
        }

        public boolean isStableDestination(BlockPos pPos) {
            Mob mob = this.mob;
            if (mob instanceof MeganeuraEntity meganeuraEntity) {
                if (meganeuraEntity.isTravelling()) {
                    return this.level.getBlockState(pPos).is(Blocks.WATER);
                }
            }

            return !this.level.getBlockState(pPos.below()).isAir();
        }
    }
}
