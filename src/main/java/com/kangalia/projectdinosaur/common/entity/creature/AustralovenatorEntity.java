package com.kangalia.projectdinosaur.common.entity.creature;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.kangalia.projectdinosaur.common.entity.ai.*;
import com.kangalia.projectdinosaur.common.entity.genetics.genomes.AustralovenatorGenome;
import com.kangalia.projectdinosaur.common.entity.parts.PrehistoricPart;
import com.kangalia.projectdinosaur.core.init.BlockInit;
import com.kangalia.projectdinosaur.core.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
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

public class AustralovenatorEntity extends PrehistoricEntity implements GeoEntity {

    private static final EntityDataAccessor<String> GENOME = SynchedEntityData.defineId(AustralovenatorEntity.class, EntityDataSerializers.STRING);

    private AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    private AustralovenatorGenome genome = new AustralovenatorGenome();

    private final PrehistoricPart[] partsBaby;
    private final PrehistoricPart[] partsJuvi;
    private final PrehistoricPart[] partsAdult;

    public final PrehistoricPart head_baby;
    public final PrehistoricPart neck_baby;
    public final PrehistoricPart body_baby;
    public final PrehistoricPart tail1_baby;
    public final PrehistoricPart tail2_baby;

    public final PrehistoricPart head_juvi;
    public final PrehistoricPart neck_juvi;
    public final PrehistoricPart body_juvi;
    public final PrehistoricPart tail1_juvi;
    public final PrehistoricPart tail2_juvi;

    public final PrehistoricPart head;
    public final PrehistoricPart neck;
    public final PrehistoricPart body;
    public final PrehistoricPart tail1;
    public final PrehistoricPart tail2;


    public AustralovenatorEntity(EntityType<? extends TamableAnimal> entityType, Level world) {
        super(entityType, world);
        this.moveControl = new AustralovenatorEntity.AustralovenatorMoveControl();
        this.lookControl = new AustralovenatorEntity.AustralovenatorLookControl();
        this.maxUpStep = 1.0F;
        minSize = 0.25F;
        maxMaleSize = 1.3F;
        maxFemaleSize = 1.1F;
        minHeight = 0.45f;
        maxHeight = 1.2f;
        minWidth = 0.4f;
        maxWidth = 1.05f;
        maxFood = 80;
        diet = 1;
        soundVolume = 0.3F;
        sleepSchedule = 0;
        name = Component.translatable("dino.projectdinosaur.australovenator");
        nameScientific = Component.translatable("dino.projectdinosaur.australovenator.scientific");
        renderScale = 25;
        breedingType = 0;
        maleRoamDistance = 64;
        femaleRoamDistance = 48;
        juvinileRoamDistance = 24;
        babyRoamDistance = 4;
        isLand = true;

        head_baby = new PrehistoricPart(this, 0.2f, 0.28f, "baby");
        neck_baby = new PrehistoricPart(this, 0.3f, 0.35f, "baby");
        body_baby = new PrehistoricPart(this, 0.43f, 0.48f, "baby");
        tail1_baby = new PrehistoricPart(this, 0.33f, 0.3f, "baby");
        tail2_baby = new PrehistoricPart(this, 0.3f, 0.23f, "baby");

        head_juvi = new PrehistoricPart(this, 0.55f, 0.55f, "juvi");
        neck_juvi = new PrehistoricPart(this, 0.6f, 0.8f, "juvi");
        body_juvi = new PrehistoricPart(this, 0.85f, 0.95f, "juvi");
        tail1_juvi = new PrehistoricPart(this, 0.75f, 0.6f, "juvi");
        tail2_juvi = new PrehistoricPart(this, 0.6f, 0.45f, "juvi");

        head = new PrehistoricPart(this, 1.1f, 1.1f, "adult");
        neck = new PrehistoricPart(this, 1.2f, 1.6f, "adult");
        body = new PrehistoricPart(this, 1.7f, 1.9f, "adult");
        tail1 = new PrehistoricPart(this, 1.5f, 1.2f, "adult");
        tail2 = new PrehistoricPart(this, 1.2f, 0.9f, "adult");

        this.partsBaby = new PrehistoricPart[]{body_baby, head_baby, tail1_baby, tail2_baby, neck_baby};
        this.partsJuvi = new PrehistoricPart[]{body_juvi, head_juvi, tail1_juvi, tail2_juvi, neck_juvi};
        this.partsAdult = new PrehistoricPart[]{body, head, tail1, tail2, neck};
    }

    public static AttributeSupplier.Builder setCustomAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.35F)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .add(Attributes.ATTACK_DAMAGE, 8.0F)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5F)
                .add(Attributes.ATTACK_SPEED, 1.0F);
    }

    @Override
    public void randomizeAttributes(int age) {
        if (age == 0) {
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.35F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 4)));
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(8.0F*genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 6)));
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(50.0F*genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 7)));
        } else if (age == 1) {
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.35F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 4)) / 1.5);
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(8.0F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 6)) / 2);
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(50.0F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 7)) / 2);
        } else {
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.35F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 4)) / 2);
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(8.0F*genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 6)) / 4);
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(50.0F*genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 7)) / 4);
        }
    }

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        if (!(event.getLimbSwingAmount() > -0.05F && event.getLimbSwingAmount() < 0.05F) && !this.isInWater()) {
            event.getController().setAnimation(RawAnimation.begin().then("animation.Australovenator.run", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(3.5);
        } else if (this.isSleeping()) {
            event.getController().setAnimation(RawAnimation.begin().then("animation.Australovenator.sleep", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(0.35);
        } else {
            event.getController().setAnimation(RawAnimation.begin().then("animation.Australovenator.idle", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(1.5);
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<AustralovenatorEntity>(this, "controller", 20, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.factory;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new PrehistoricBabyAvoidEntityGoal<>(this, Player.class, 4.0F, 2.0D, 1.5D));
        this.goalSelector.addGoal(0, new PrehistoricBabyPanicGoal(this, 2.0D));
        this.goalSelector.addGoal(0, new PrehistoricSleepInNestGoal(this, 2.0D, 32));
        this.goalSelector.addGoal(0, new PrehistoricGiveBirthGoal(this, this.getMate(), 2.0D, 32));
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
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Rabbit.class, 20, false, false, (p_28600_) -> p_28600_ instanceof Rabbit));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Chicken.class, 20, false, false, (p_28600_) -> p_28600_ instanceof Chicken));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Pig.class, 20, false, false, (p_28600_) -> p_28600_ instanceof Pig));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Sheep.class, 20, false, false, (p_28600_) -> p_28600_ instanceof Sheep));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Cow.class, 20, false, false, (p_28600_) -> p_28600_ instanceof Cow));
        this.targetSelector.addGoal(5, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    public boolean isCustomMultiPart() {
        return isMultipartEntity();
    }

    @Override
    public boolean isMultipartEntity() {
        return true;
    }

    @Override
    public @Nullable PartEntity<?>[] getParts() {
        if (this.isBaby()) {
            return partsBaby;
        } else if (this.isJuvenile()) {
            return partsJuvi;
        } else {
            return partsAdult;
        }
    }

    @Override
    public boolean isPickable() {
        return !isCustomMultiPart();
    }
    @Override
    public boolean canBeCollidedWith() {
        return !isCustomMultiPart();
    }

    @Override
    protected void doPush(Entity entity) {
        if (!isCustomMultiPart()) {
            super.doPush(entity);
        }
    }

    @Override
    public boolean isColliding(BlockPos pos, BlockState state) {
        if (isCustomMultiPart()) {
            VoxelShape voxelShape = state.getCollisionShape(this.level, pos, CollisionContext.of(this));
            VoxelShape voxelShape2 = voxelShape.move(pos.getX(), pos.getY(), pos.getZ());
            return Shapes.joinIsNotEmpty(voxelShape2, Shapes.create(getParts()[0].getBoundingBox()), BooleanOp.AND);
        }
        return super.isColliding(pos, state);
    }

    @Override
    public float getPickRadius() {
        if (isCustomMultiPart()) {
            return getType().getWidth() * getAgeScale() - getBbWidth();
        }
        return super.getPickRadius();
    }

    @Override
    protected AABB makeBoundingBox() {
        if (isCustomMultiPart() && getParts() != null) {
            return getParts()[0].getDimensions(Pose.STANDING).makeBoundingBox(position());
        }
        return super.makeBoundingBox();
    }

    @Override
    public void aiStep() {
        super.aiStep();
        PrehistoricPart[] parts;
        if (this.isBaby()) {
            parts = partsBaby;
        } else if (this.isJuvenile()) {
            parts = partsJuvi;
        } else {
            parts = partsAdult;
        }
        Vec3[] vec3s = new Vec3[parts.length];
        for (int i = 0; i < parts.length; i++) {
            vec3s[i] = parts[i].getPosition(1.0f);
        }
        Vec3 offset = calculateViewVector(getXRot(), yBodyRot).reverse().scale(0.3);
        parts[0].setPos(getX() + offset.x, getY() + offset.y, getZ() + offset.z);

        Vec3 offsetHor = calculateViewVector(0, yBodyRot).scale(0.8f * getAgeScale());
        Vec3 headOffset = calculateViewVector(0, yBodyRot).with(Direction.Axis.Y, 0).add(0, getAgeScale(), 0);
        Vec3 neckOffset = calculateViewVector(0, yBodyRot).with(Direction.Axis.Y, 0).scale(-0.02f).add(0, getAgeScale(), 0);
        parts[1].setPos(getX() + headOffset.x + offsetHor.x, getY() + headOffset.y, getZ() + headOffset.z + offsetHor.z);
        parts[4].setPos(getX() + neckOffset.x + offsetHor.x, getY() + neckOffset.y, getZ() + neckOffset.z + offsetHor.z);

        offsetHor = offsetHor.yRot((float) Math.toRadians(180));
        Vec3 tailOffset1 = calculateViewVector(0, yBodyRot).scale(-0.8).add(0, getAgeScale(), 0);
        Vec3 tailOffset2 = calculateViewVector(0, yBodyRot).scale(-1.8).add(0, getAgeScale(), 0);
        parts[2].setPos(getX() + tailOffset1.x + offsetHor.x, getY() + tailOffset1.y, getZ() + tailOffset1.z + offsetHor.z);
        parts[3].setPos(getX() + tailOffset2.x + offsetHor.x, getY() + tailOffset2.y, getZ() + tailOffset2.z + offsetHor.z);

        for (int i = 0; i < parts.length; i++) {
            parts[i].xo = vec3s[i].x;
            parts[i].yo = vec3s[i].y;
            parts[i].zo = vec3s[i].z;
            parts[i].xOld = vec3s[i].x;
            parts[i].yOld = vec3s[i].y;
            parts[i].zOld = vec3s[i].z;
        }
    }

    @Override
    public int getAmbientSoundInterval() {
        return 120;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.BAT_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.BAT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BAT_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.SHEEP_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean canBeLeashed(Player pPlayer) {
        return false;
    }


    @org.jetbrains.annotations.Nullable
    @Override
    public AgeableMob getBreedOffspring(@Nonnull ServerLevel serverWorld, @Nonnull AgeableMob ageableMob) {
        return EntityInit.AUSTRALOVENATOR.get().create(serverWorld);
    }

    @Override
    public Block getEggType() {
        return BlockInit.AUSTRALOVENATOR_EGG_INCUBATED.get();
    }

    @Override
    public int getClutchSize() {
        return 3;
    }

    @Override
    public int getAdultAge() {
        return 10;
    }

    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor serverLevelAccessor, @NotNull DifficultyInstance difficultyInstance, @NotNull MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag compoundTag) {
        this.setGenes(this.generateGenes(true));
        System.out.println(this.getGenes());
        this.setAttributes(0);
        return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, compoundTag);
    }

    @Override
    public void setId(int p_145769_1_) {
        super.setId(p_145769_1_);
        PrehistoricPart[] parts;
        if (this.isBaby()) {
            parts = partsBaby;
        } else if (this.isJuvenile()) {
            parts = partsJuvi;
        } else {
            parts = partsAdult;
        }
        for (int i = 0; i < parts.length; ++i) // Forge: Fix MC-158205: Set part ids to successors of parent mob id
            parts[i].setId(p_145769_1_ + i + 1);
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
        if (gene == 2) {
            return genome.calculateDominanceBC(alleles);
        } else if (gene == 3) {
            return genome.calculateDominanceUC(alleles);
        } else if (gene == 4) {
            return genome.calculateDominancePT(alleles);
        } else if (gene == 5) {
            return genome.calculateDominancePC(alleles);
        } else {
            return genome.calculateDominanceHC(alleles);
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

    class AustralovenatorMoveControl extends MoveControl {
        public AustralovenatorMoveControl() {
            super(AustralovenatorEntity.this);
        }

        public void tick() {
            if (!AustralovenatorEntity.this.isSleeping()) {
                super.tick();
            }

        }
    }

    public class AustralovenatorLookControl extends LookControl {
        public AustralovenatorLookControl() {
            super(AustralovenatorEntity.this);
        }

        public void tick() {
            if (!AustralovenatorEntity.this.isSleeping()) {
                super.tick();
            }

        }
    }
}
