package com.kangalia.projectdinosaur.common.entity.creature;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.kangalia.projectdinosaur.common.entity.ai.*;
import com.kangalia.projectdinosaur.common.entity.genetics.genomes.GastornisGenome;
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
import java.util.Objects;

public class GastornisEntity extends PrehistoricEntity implements GeoEntity {

    private static final EntityDataAccessor<String> GENOME = SynchedEntityData.defineId(GastornisEntity.class, EntityDataSerializers.STRING);

    private AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    private GastornisGenome genome = new GastornisGenome();

    private final PrehistoricPart[] partsAll;

    public final PrehistoricPart head;
    public final PrehistoricPart neck;
    public final PrehistoricPart body;
    public final PrehistoricPart tail;

    public final double[][] positions = new double[64][3];
    public int posPointer = -1;
    public float yRotA;
    int counter = 0;

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
        childRoamDistance = 10;
        babyRoamDistance = 4;
        isLand = true;
        maxPack = 8;
        minPack = 2;
        maxTotalPack = 14;

        head = new PrehistoricPart(this, "head");
        neck = new PrehistoricPart(this, "neck");
        body = new PrehistoricPart(this, "body");
        tail = new PrehistoricPart(this, "tail");

        this.partsAll = new PrehistoricPart[]{body, head, tail, neck};
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

    public boolean isCustomMultiPart() {
        return isMultipartEntity();
    }

    @Override
    public boolean isMultipartEntity() {
        return true;
    }

    @Override
    public @Nullable PartEntity<?>[] getParts() {
        return partsAll;
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
        if (counter < 20) {
            counter++;
        } else {
            refreshDimensions();
            Arrays.stream(getParts()).filter(Objects::nonNull).forEach(Entity::refreshDimensions);
            counter = 0;
        }
        if (this.isBaby()) {
            babyPartControl();
        } else if (this.isChild()) {
            childPartControl();
        } else if (this.isJuvenile()) {
            juviPartControl();
        } else {
            adultPartControl();
        }
    }

    private void babyPartControl() {
        Vec3[] avec3 = new Vec3[this.partsAll.length];
        for(int j = 0; j < this.partsAll.length; ++j) {
            avec3[j] = new Vec3(this.partsAll[j].getX(), this.partsAll[j].getY(), this.partsAll[j].getZ());
        }
        float f12 = (float)(this.getLatencyPos(5, 1.0F)[1] - this.getLatencyPos(10, 1.0F)[1]) * 10.0F * ((float)Math.PI / 180F);
        float f13 = Mth.cos(f12);
        float f1 = Mth.sin(f12);
        float f14 = this.getViewYRot(1) * ((float)Math.PI / 180F);
        float f2 = Mth.sin(f14);
        float f15 = Mth.cos(f14);
        this.tickPart(this.body, (f2 * 0.05F), 0.0D, (-f15 * 0.05F));
        float f3 = Mth.sin(this.getViewYRot(1) * ((float)Math.PI / 180F) - this.yRotA * 0.01F);
        float f16 = Mth.cos(this.getViewYRot(1) * ((float)Math.PI / 180F) - this.yRotA * 0.01F);
        float f4 = this.getHeadYOffset();
        if (this.isSleeping()) {
            this.tickPart(this.head, (-f3 * 0.25F * f13), (f4 + f1), (f16 * 0.25F * f13));
            this.tickPart(this.neck, (-f3 * 0.13F * f13), (f4 + f1), (f16 * 0.13F * f13));
        }
        this.tickPart(this.head, (-f3 * 0.25F * f13), (f4 + f1) + 0.38F, (f16 * 0.25F * f13));
        this.tickPart(this.neck, (-f3 * 0.13F * f13), (f4 + f1) + 0.28F, (f16 * 0.13F * f13));
        double[] adouble = this.getLatencyPos(5, 1.0F);
        double[] adouble1 = this.getLatencyPos(12, 1.0F);
        float f17 = this.getViewYRot(1) * ((float)Math.PI / 180F) + this.rotWrap(adouble1[0] - adouble[0]) * ((float)Math.PI / 180F);
        float f18 = Mth.sin(f17);
        float f20 = Mth.cos(f17);
        float f22 = 0.1F;
        if (this.isSleeping()) {
            this.tickPart(this.tail, ((f2 * 0.1F + f18 * f22) * f13), adouble1[1] - adouble[1] - (double)((f22 + 1.5F) * f1) - 0.15D, (-(f15 * 0.1F + f20 * f22) * f13));
        } else {
            this.tickPart(this.tail, ((f2 * 0.1F + f18 * f22) * f13), adouble1[1] - adouble[1] - (double) ((f22 + 1.5F) * f1) + 0.15D, (-(f15 * 0.1F + f20 * f22) * f13));
        }

        for(int l = 0; l < this.partsAll.length; ++l) {
            this.partsAll[l].xo = avec3[l].x;
            this.partsAll[l].yo = avec3[l].y;
            this.partsAll[l].zo = avec3[l].z;
            this.partsAll[l].xOld = avec3[l].x;
            this.partsAll[l].yOld = avec3[l].y;
            this.partsAll[l].zOld = avec3[l].z;
        }
    }

    private void childPartControl() {
        Vec3[] avec3 = new Vec3[this.partsAll.length];
        for(int j = 0; j < this.partsAll.length; ++j) {
            avec3[j] = new Vec3(this.partsAll[j].getX(), this.partsAll[j].getY(), this.partsAll[j].getZ());
        }
        float f12 = (float)(this.getLatencyPos(5, 1.0F)[1] - this.getLatencyPos(10, 1.0F)[1]) * 10.0F * ((float)Math.PI / 180F);
        float f13 = Mth.cos(f12);
        float f1 = Mth.sin(f12);
        float f14 = this.getViewYRot(1) * ((float)Math.PI / 180F);
        float f2 = Mth.sin(f14);
        float f15 = Mth.cos(f14);
        this.tickPart(this.body, (f2 * 0.05F), 0.0D, (-f15 * 0.05F));
        float f3 = Mth.sin(this.getViewYRot(1) * ((float)Math.PI / 180F) - this.yRotA * 0.01F);
        float f16 = Mth.cos(this.getViewYRot(1) * ((float)Math.PI / 180F) - this.yRotA * 0.01F);
        float f4 = this.getHeadYOffset();
        if (this.isSleeping()) {
            this.tickPart(this.head, (-f3 * 0.5F * f13), (f4 + f1), (f16 * 0.5F * f13));
            this.tickPart(this.neck, (-f3 * 0.33F * f13), (f4 + f1), (f16 * 0.33F * f13));
        }
        this.tickPart(this.head, (-f3 * 0.5F * f13), (f4 + f1) + 0.6F, (f16 * 0.5F * f13));
        this.tickPart(this.neck, (-f3 * 0.33F * f13), (f4 + f1) + 0.4F, (f16 * 0.33F * f13));
        double[] adouble = this.getLatencyPos(5, 1.0F);
        double[] adouble1 = this.getLatencyPos(12, 1.0F);
        float f17 = this.getViewYRot(1) * ((float)Math.PI / 180F) + this.rotWrap(adouble1[0] - adouble[0]) * ((float)Math.PI / 180F);
        float f18 = Mth.sin(f17);
        float f20 = Mth.cos(f17);
        float f22 = 0.2F;
        if (this.isSleeping()) {
            this.tickPart(this.tail, ((f2 * 0.1F + f18 * f22) * f13), adouble1[1] - adouble[1] - (double)((f22 + 1.5F) * f1) - 0.2D, (-(f15 * 0.1F + f20 * f22) * f13));
        } else {
            this.tickPart(this.tail, ((f2 * 0.1F + f18 * f22) * f13), adouble1[1] - adouble[1] - (double) ((f22 + 1.5F) * f1) + 0.2D, (-(f15 * 0.1F + f20 * f22) * f13));
        }

        for(int l = 0; l < this.partsAll.length; ++l) {
            this.partsAll[l].xo = avec3[l].x;
            this.partsAll[l].yo = avec3[l].y;
            this.partsAll[l].zo = avec3[l].z;
            this.partsAll[l].xOld = avec3[l].x;
            this.partsAll[l].yOld = avec3[l].y;
            this.partsAll[l].zOld = avec3[l].z;
        }
    }

    private void juviPartControl() {
        Vec3[] avec3 = new Vec3[this.partsAll.length];
        for(int j = 0; j < this.partsAll.length; ++j) {
            avec3[j] = new Vec3(this.partsAll[j].getX(), this.partsAll[j].getY(), this.partsAll[j].getZ());
        }
        float f12 = (float)(this.getLatencyPos(5, 1.0F)[1] - this.getLatencyPos(10, 1.0F)[1]) * 10.0F * ((float)Math.PI / 180F);
        float f13 = Mth.cos(f12);
        float f1 = Mth.sin(f12);
        float f14 = this.getViewYRot(1) * ((float)Math.PI / 180F);
        float f2 = Mth.sin(f14);
        float f15 = Mth.cos(f14);
        this.tickPart(this.body, (f2 * 0.05F), 0.0D, (-f15 * 0.05F));
        float f3 = Mth.sin(this.getViewYRot(1) * ((float)Math.PI / 180F) - this.yRotA * 0.01F);
        float f16 = Mth.cos(this.getViewYRot(1) * ((float)Math.PI / 180F) - this.yRotA * 0.01F);
        float f4 = this.getHeadYOffset();
        if (this.isSleeping()) {
            this.tickPart(this.head, (-f3 * 0.9F * f13), (f4 + f1), (f16 * 0.9F * f13));
            this.tickPart(this.neck, (-f3 * 0.53F * f13), (f4 + f1), (f16 * 0.53F * f13));
        }
        this.tickPart(this.head, (-f3 * 0.9F * f13), (f4 + f1) + 1.1F, (f16 * 0.9F * f13));
        this.tickPart(this.neck, (-f3 * 0.53F * f13), (f4 + f1) + 0.6F, (f16 * 0.53F * f13));
        double[] adouble = this.getLatencyPos(5, 1.0F);
        double[] adouble1 = this.getLatencyPos(12, 1.0F);
        float f17 = this.getViewYRot(1) * ((float)Math.PI / 180F) + this.rotWrap(adouble1[0] - adouble[0]) * ((float)Math.PI / 180F);
        float f18 = Mth.sin(f17);
        float f20 = Mth.cos(f17);
        float f22 = 0.45F;
        if (this.isSleeping()) {
            this.tickPart(this.tail, ((f2 * 0.1F + f18 * f22) * f13), adouble1[1] - adouble[1] - (double)((f22 + 1.5F) * f1) - 0.35D, (-(f15 * 0.1F + f20 * f22) * f13));
        } else {
            this.tickPart(this.tail, ((f2 * 0.1F + f18 * f22) * f13), adouble1[1] - adouble[1] - (double) ((f22 + 1.5F) * f1) + 0.35D, (-(f15 * 0.1F + f20 * f22) * f13));
        }

        for(int l = 0; l < this.partsAll.length; ++l) {
            this.partsAll[l].xo = avec3[l].x;
            this.partsAll[l].yo = avec3[l].y;
            this.partsAll[l].zo = avec3[l].z;
            this.partsAll[l].xOld = avec3[l].x;
            this.partsAll[l].yOld = avec3[l].y;
            this.partsAll[l].zOld = avec3[l].z;
        }
    }

    private void adultPartControl() {
        Vec3[] avec3 = new Vec3[this.partsAll.length];
        for(int j = 0; j < this.partsAll.length; ++j) {
            avec3[j] = new Vec3(this.partsAll[j].getX(), this.partsAll[j].getY(), this.partsAll[j].getZ());
        }
        float f12 = (float)(this.getLatencyPos(5, 1.0F)[1] - this.getLatencyPos(10, 1.0F)[1]) * 10.0F * ((float)Math.PI / 180F);
        float f13 = Mth.cos(f12);
        float f1 = Mth.sin(f12);
        float f14 = this.getViewYRot(1) * ((float)Math.PI / 180F);
        float f2 = Mth.sin(f14);
        float f15 = Mth.cos(f14);
        this.tickPart(this.body, (f2 * 0.05F), 0.0D, (-f15 * 0.05F));
        float f3 = Mth.sin(this.getViewYRot(1) * ((float)Math.PI / 180F) - this.yRotA * 0.01F);
        float f16 = Mth.cos(this.getViewYRot(1) * ((float)Math.PI / 180F) - this.yRotA * 0.01F);
        float f4 = this.getHeadYOffset();
        if (this.isSleeping()) {
            this.tickPart(this.head, (-f3 * 1.1F * f13), (f4 + f1), (f16 * 1.1F * f13));
            this.tickPart(this.neck, (-f3 * 0.73F * f13), (f4 + f1), (f16 * 0.73F * f13));
        }
        this.tickPart(this.head, (-f3 * 1.1F * f13), (f4 + f1) + (1.6F * this.getDimensionScaleHeight()), (f16 * 1.1F * f13));
        this.tickPart(this.neck, (-f3 * 0.73F * f13), (f4 + f1) + (0.85F * this.getDimensionScaleHeight()), (f16 * 0.73F * f13));
        double[] adouble = this.getLatencyPos(5, 1.0F);
        double[] adouble1 = this.getLatencyPos(12, 1.0F);
        float f17 = this.getViewYRot(1) * ((float)Math.PI / 180F) + this.rotWrap(adouble1[0] - adouble[0]) * ((float)Math.PI / 180F);
        float f18 = Mth.sin(f17);
        float f20 = Mth.cos(f17);
        float f22 = 0.65F;
        if (this.isSleeping()) {
            this.tickPart(this.tail, ((f2 * 0.1F + f18 * f22) * f13), adouble1[1] - adouble[1] - (double)((f22 + 1.5F) * f1) - (0.6D * this.getDimensionScaleHeight()), (-(f15 * 0.1F + f20 * f22) * f13));
        } else {
            this.tickPart(this.tail, ((f2 * 0.1F + f18 * f22) * f13), adouble1[1] - adouble[1] - (double) ((f22 + 1.5F) * f1) + (0.6D * this.getDimensionScaleHeight()), (-(f15 * 0.1F + f20 * f22) * f13));
        }

        for(int l = 0; l < this.partsAll.length; ++l) {
            this.partsAll[l].xo = avec3[l].x;
            this.partsAll[l].yo = avec3[l].y;
            this.partsAll[l].zo = avec3[l].z;
            this.partsAll[l].xOld = avec3[l].x;
            this.partsAll[l].yOld = avec3[l].y;
            this.partsAll[l].zOld = avec3[l].z;
        }
    }

    private void tickPart(PrehistoricPart pPart, double pOffsetX, double pOffsetY, double pOffsetZ) {
        pPart.setPos(this.getX() + pOffsetX, this.getY() + pOffsetY, this.getZ() + pOffsetZ);
    }

    private double[] getLatencyPos(int pBufferIndexOffset, float pPartialTicks) {
        if (this.isDeadOrDying()) {
            pPartialTicks = 0.0F;
        }

        pPartialTicks = 1.0F - pPartialTicks;
        int i = this.posPointer - pBufferIndexOffset & 63;
        int j = this.posPointer - pBufferIndexOffset - 1 & 63;
        double[] adouble = new double[3];
        double d0 = this.positions[i][0];
        double d1 = Mth.wrapDegrees(this.positions[j][0] - d0);
        adouble[0] = d0 + d1 * (double)pPartialTicks;
        d0 = this.positions[i][1];
        d1 = this.positions[j][1] - d0;
        adouble[1] = d0 + d1 * (double)pPartialTicks;
        adouble[2] = Mth.lerp(pPartialTicks, this.positions[i][2], this.positions[j][2]);
        return adouble;
    }

    private float getHeadYOffset() {
        double[] adouble = this.getLatencyPos(5, 1.0F);
        double[] adouble1 = this.getLatencyPos(0, 1.0F);
        return (float)(adouble[1] - adouble1[1]);
    }

    private float rotWrap(double pAngle) {
        return (float)Mth.wrapDegrees(pAngle);
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
    public void setId(int p_145769_1_) { // Forge: Fix MC-158205: Set part ids to successors of parent mob id
        super.setId(p_145769_1_);
        for (int i = 0; i < partsAll.length; ++i) {
            partsAll[i].setId(p_145769_1_ + i + 1);
        }
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
