package com.kangalia.projectdinosaur.common.entity.creature;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.kangalia.projectdinosaur.common.entity.ai.*;
import com.kangalia.projectdinosaur.common.entity.genetics.genomes.GastornisGenome;
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
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
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

public class GastornisEntity extends PrehistoricEntity implements GeoEntity {

    private static final EntityDataAccessor<String> GENOME = SynchedEntityData.defineId(GastornisEntity.class, EntityDataSerializers.STRING);

    private AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    private GastornisGenome genome = new GastornisGenome();

    public GastornisEntity(EntityType<? extends TamableAnimal> entityType, Level world) {
        super(entityType, world);
        this.moveControl = new GastornisEntity.GastornisMoveControl();
        this.lookControl = new GastornisEntity.GastornisLookControl();
        this.maxUpStep = 0.5F;
        minSize = 0.25F;
        maxMaleSize = 1.25F;
        maxFemaleSize = 1.15F;
        minHeight = 0.45f;
        maxHeight = 1.0f;
        minWidth = 0.4f;
        maxWidth = 1.05f;
        maxFood = 80;
        diet = 0;
        soundVolume = 0.3F;
        sleepSchedule = 0;
        name = Component.translatable("dino.projectdinosaur.gastornis");
        nameScientific = Component.translatable("dino.projectdinosaur.gastornis.scientific");
        renderScale = 35;
        maleRoamDistance = 64;
        femaleRoamDistance = 48;
        juvinileRoamDistance = 24;
        babyRoamDistance = 4;
        isLand = true;
        maxPack = 8;
        minPack = 2;
        maxTotalPack = 14;
    }

    public static AttributeSupplier.Builder setCustomAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 80.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.4F)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .add(Attributes.ATTACK_DAMAGE, 8.0F)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5F)
                .add(Attributes.ATTACK_SPEED, 1.0F);
    }

    @Override
    public void randomizeAttributes(int age) {
        if (age == 0) {
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.4F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 7)));
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(8.0F*genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 9)));
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(80.0F*genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 10)));
        } else if (age == 1) {
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.4F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 7)) / 1.5);
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(8.0F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 9)) / 2);
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(80.0F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 10)) / 2);
        } else {
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.4F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 7))/2);
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(8.0F*genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 9))/4);
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(80.0F*genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 10))/4);
        }
    }

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        if (!(event.getLimbSwingAmount() > -0.05F && event.getLimbSwingAmount() < 0.05F) && !this.isInWater()) {
            event.getController().setAnimation(RawAnimation.begin().then("animation.gastornis.run", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(3);
        } else if (this.isSleeping()) {
            event.getController().setAnimation(RawAnimation.begin().then("animation.gastornis.sleep", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(0.4);
        } else {
            event.getController().setAnimation(RawAnimation.begin().then("animation.gastornis.idle", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(0.5);
        }
        return PlayState.CONTINUE;
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<GastornisEntity>(this, "controller", 20, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.factory;
    }
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new PrehistoricBabyAvoidEntityGoal<>(this, Player.class, 4.0F, 2.0D, 1.5D));
        this.goalSelector.addGoal(0, new PrehistoricBabyPanicGoal(this, 2.0D));
        this.goalSelector.addGoal(0, new PrehistoricSleepInNestGoal(this, 2.0D, 32, 8));
        this.goalSelector.addGoal(0, new PrehistoricGiveBirthGoal(this, this.getMate(), 2.0D, 32));
        this.goalSelector.addGoal(0, new PrehistoricCheckPackGoal(this, 2, 8));
        this.goalSelector.addGoal(1, new PrehistoricBreedGoal(this, 2.0D));
        this.goalSelector.addGoal(1, new PrehistoricEatFromFeederGoal(this, 2.0D, 32));
        this.goalSelector.addGoal(1, new PrehistoricPlayWithEnrichmentGoal(this, 2.0D, 32));
        this.goalSelector.addGoal(1, new PrehistoricMeleeAttackGoal(this, 2.0D, true));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.25D, 200));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(0, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, 25, true, false, this::isMoodyAt));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, AustralovenatorEntity.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, GastornisEntity.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, ScelidosaurusEntity.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(2, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    @Override
    public float getMaxHeight() {
        float sizeCoefficient = genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 8));
        if (this.getGender() == 0) {
            return sizeCoefficient * maxHeight;
        } else {
            return sizeCoefficient * (maxHeight - 0.1f);
        }
    }

    @Override
    public float getMaxWidth() {
        float sizeCoefficient = genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 8));
        if (this.getGender() == 0) {
            return sizeCoefficient * maxWidth;
        } else {
            return sizeCoefficient * (maxWidth - 0.1f);
        }
    }

    @Override
    public int getAmbientSoundInterval() {
        return 120;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (this.isMoody()) {
            return SoundInit.GASTORNIS_HISS.get();
        } else {
            if (this.random.nextBoolean()) {
                return SoundInit.GASTORNIS_FLAPPING.get();
            } else {
                return SoundInit.GASTORNIS_CALL.get();

            }
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundInit.GASTORNIS_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundInit.GASTORNIS_DEATH.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.SHEEP_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean canBeLeashed(Player pPlayer) {
        return false;
    }


    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@Nonnull ServerLevel serverWorld, @Nonnull AgeableMob ageableMob) {
        return EntityInit.GASTORNIS.get().create(serverWorld);
    }

    @Override
    public int getBreedingType() {
        return 0;
    }

    @Override
    public Block getEggType() {
        return BlockInit.GASTORNIS_EGG_INCUBATED.get();
    }

    @Override
    public int getClutchSize() {
        return 4;
    }

    @Override
    public int getAdultAge() {
        return 8;
    }

    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor serverLevelAccessor, @NotNull DifficultyInstance difficultyInstance, @NotNull MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag compoundTag) {
        this.setGenes(this.generateGenes(true));
        System.out.println(this.getGenes());
        this.setAttributes(0);
        return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, compoundTag);
    }

    @Override
    public float getGenderMaxSize() {
        float sizeCoefficient = genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 8));
        if (this.getGender() == 0) {
            return sizeCoefficient * maxMaleSize;
        } else {
            return sizeCoefficient * maxFemaleSize;
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
            return genome.calculateDominanceFC(alleles);
        } else if (gene == 2) {
            return genome.calculateDominanceUC(alleles);
        } else if (gene == 3) {
            return genome.calculateDominancePC(alleles);
        } else if (gene == 4) {
            return genome.calculateDominanceHC(alleles);
        } else if (gene == 5) {
            return genome.calculateDominanceSC(alleles);
        } else {
            return genome.calculateDominanceBC(alleles);
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
    }

    @Override
    public void addAdditionalSaveData(@Nonnull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putString("Genome", this.getGenes());
    }

    @Override
    public void readAdditionalSaveData(@Nonnull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setGenes(pCompound.getString("Genome"));
    }

    class GastornisMoveControl extends MoveControl {
        public GastornisMoveControl() {
            super(GastornisEntity.this);
        }

        public void tick() {
            if (!GastornisEntity.this.isSleeping()) {
                super.tick();
            }
        }
    }

    public class GastornisLookControl extends LookControl {
        public GastornisLookControl() {
            super(GastornisEntity.this);
        }

        public void tick() {
            if (!GastornisEntity.this.isSleeping()) {
                super.tick();
            }
        }
    }
}
