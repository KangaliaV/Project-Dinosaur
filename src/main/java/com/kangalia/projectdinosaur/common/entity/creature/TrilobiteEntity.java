package com.kangalia.projectdinosaur.common.entity.creature;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.kangalia.projectdinosaur.common.entity.ai.PrehistoricBabyAvoidEntityGoal;
import com.kangalia.projectdinosaur.common.entity.genetics.genomes.TrilobiteGenome;
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
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nonnull;
import java.util.Random;

public class TrilobiteEntity extends PrehistoricEntity implements IAnimatable {

    private static final EntityDataAccessor<String> GENOME = SynchedEntityData.defineId(TrilobiteEntity.class, EntityDataSerializers.STRING);

    private AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private TrilobiteGenome genome = new TrilobiteGenome();

    public TrilobiteEntity(EntityType<? extends TamableAnimal> entityType, Level world) {
        super(entityType, world);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.moveControl = new TrilobiteEntity.TrilobiteMoveControl();
        this.lookControl = new TrilobiteEntity.TrilobiteLookControl();
        this.maxUpStep = 1.0F;
        minSize = 0.25F;
        maxMaleSize = 1.0F;
        maxFemaleSize = 1.2F;
        minHeight = 0.1f;
        maxHeight = 0.2f;
        minWidth = 0.3f;
        maxWidth = 1.0f;
        maxFood = 50;
        diet = 0;
        soundVolume = 0.2F;
        sleepSchedule = 2;
        name = Component.translatable("dino.projectdinosaur.trilobite");
        nameScientific = Component.translatable("dino.projectdinosaur.trilobite.scientific");
        renderScale = 60;
        breedingType = 1;
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

    @Override
    public void randomizeAttributes(int age) {
        if (age == 0) {
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.1F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 6)));
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(16.0F*genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 8)));
        } else if (age == 1) {
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.1F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 6)) / 1.5);
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(16.0F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 8)) / 2);
        } else {
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.1F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 6))/2);
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(16.0F*genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 8))/4);
        }
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (!(event.getLimbSwingAmount() > -0.05F && event.getLimbSwingAmount() < 0.05F)) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.trilobite.idle", ILoopType.EDefaultLoopTypes.LOOP));
            event.getController().setAnimationSpeed(1.0);
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.trilobite.idle", ILoopType.EDefaultLoopTypes.LOOP));
            event.getController().setAnimationSpeed(0.5);
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.setResetSpeedInTicks(10);
        data.addAnimationController(new AnimationController<TrilobiteEntity>(this, "controller", 4, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PrehistoricBabyAvoidEntityGoal<>(this, Player.class, 4.0F, 1.5D, 1.5D));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.5D));
        this.goalSelector.addGoal(1, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.25D, 200));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    /*@Override
    protected int getExperienceReward(Player player) {
        return 1 + this.level.random.nextInt(4);
    }*/

    @Override
    public float getMaxHeight() {
        float sizeCoefficient = genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 7));
        if (this.getGender() == 0) {
            return sizeCoefficient * (maxHeight * 0.9f);
        } else {
            return sizeCoefficient * maxHeight;
        }
    }

    @Override
    public float getMaxWidth() {
        float sizeCoefficient = genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 7));
        if (this.getGender() == 0) {
            return sizeCoefficient * (maxWidth * 0.8f);
        } else {
            return sizeCoefficient * maxWidth;
        }
    }

    @Override
    public int getAmbientSoundInterval() {
        return 100;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SQUID_AMBIENT;
    }

    @Override
    public int getBreedingType() {
        return 1;
    }

    @Override
    public ItemStack getSpawnType() {
        return ItemInit.TRILOBITE_SPAWN_ITEM.get().getDefaultInstance();
    }

    @Override
    protected boolean isAffectedByFluids() {
        return false;
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

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@Nonnull ServerLevel serverWorld, @Nonnull AgeableMob ageableMob) {
        return EntityInit.TRILOBITE.get().create(serverWorld);
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
            return genome.calculateDominanceUC(alleles);
        } else if (gene == 3) {
            return genome.calculateDominancePT(alleles);
        } else if (gene == 4) {
            return genome.calculateDominancePC(alleles);
        } else {
            return genome.calculateDominanceHT(alleles);
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

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(GENOME, "");
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putString("Genome", this.getGenes());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setGenes(pCompound.getString("Genome"));
    }

    class TrilobiteMoveControl extends MoveControl {
        public TrilobiteMoveControl() {
            super(TrilobiteEntity.this);
        }

        public void tick() {
            if (!TrilobiteEntity.this.isSleeping()) {
                super.tick();
            }

        }
    }

    public class TrilobiteLookControl extends LookControl {
        public TrilobiteLookControl() {
            super(TrilobiteEntity.this);
        }

        public void tick() {
            if (!TrilobiteEntity.this.isSleeping()) {
                super.tick();
            }

        }
    }
}
