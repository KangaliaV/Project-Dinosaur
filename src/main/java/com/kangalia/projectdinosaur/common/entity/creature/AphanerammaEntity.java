package com.kangalia.projectdinosaur.common.entity.creature;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.kangalia.projectdinosaur.common.entity.ai.*;
import com.kangalia.projectdinosaur.common.entity.genetics.genomes.AphanerammaGenome;
import com.kangalia.projectdinosaur.common.entity.parts.PrehistoricPart;
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
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
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
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
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

public class AphanerammaEntity extends PrehistoricEntity implements GeoEntity {

    private static final EntityDataAccessor<BlockPos> TRAVEL_POS = SynchedEntityData.defineId(AphanerammaEntity.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Boolean> TRAVELLING = SynchedEntityData.defineId(AphanerammaEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<String> GENOME = SynchedEntityData.defineId(AphanerammaEntity.class, EntityDataSerializers.STRING);

    private AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);
    private AphanerammaGenome genome = new AphanerammaGenome();

    private final PrehistoricPart[] partsAll;

    public final PrehistoricPart body;
    public final PrehistoricPart head;
    public final PrehistoricPart tail;

    public final double[][] positions = new double[64][3];
    public int posPointer = -1;
    public float yRotA;
    int counter = 0;


    public AphanerammaEntity(EntityType<? extends TamableAnimal> entityType, Level world) {
        super(entityType, world);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.moveControl = new AphanerammaEntity.AphanerammaMoveControl(this);
        this.lookControl = new AphanerammaEntity.AphanerammaLookControl(this, 20);
        this.maxUpStep = 1.0F;
        minSize = 0.25F;
        maxMaleSize = 0.8F;
        maxFemaleSize = 1.0F;
        minHeight = 0.25f;
        maxHeight = 1.0f;
        minWidth = 0.4f;
        maxWidth = 1.2f;
        maxFood = 50;
        diet = 2;
        soundVolume = 0.2F;
        sleepSchedule = 0;
        name = Component.translatable("dino.projectdinosaur.aphaneramma");
        nameScientific = Component.translatable("dino.projectdinosaur.aphaneramma.scientific");
        renderScale = 60;
        breedingType = 1;
        maleRoamDistance = 32;
        femaleRoamDistance = 24;
        juvinileRoamDistance = 12;
        babyRoamDistance = 2;
        isLand = false;

        body = new PrehistoricPart(this, "body");
        head = new PrehistoricPart(this, "head");
        tail = new PrehistoricPart(this, "tail");

        this.partsAll = new PrehistoricPart[]{body, head, tail};
    }

    public static AttributeSupplier.Builder setCustomAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 4.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.09F)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .add(Attributes.ATTACK_DAMAGE, 1.0F)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5F)
                .add(Attributes.ATTACK_SPEED, 1.0F);
    }

    @Override
    public void randomizeAttributes(int age) {
        if (age == 0) {
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.09F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 4)));
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(8.0F*genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 6)));
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(12.0F*genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 7)));
        } else if (age == 1) {
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.09F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 4)) / 1.5);
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(8.0F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 6)) / 2);
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(12.0F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 7)) / 2);
        } else {
            this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.09F * genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 4))/2);
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(8.0F*genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 6))/4);
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(12.0F*genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 7))/4);
        }
    }

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        if (!(event.getLimbSwingAmount() > -0.05F && event.getLimbSwingAmount() < 0.05F) && !this.isInWater()) {
            event.getController().setAnimation(RawAnimation.begin().then("animation.Aphaneramma.walk", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(1.0);
        } else if (this.isInWater()) {
            event.getController().setAnimation(RawAnimation.begin().then("animation.Aphaneramma.swim", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(1.0);
        } else if (this.isSleeping()) {
            event.getController().setAnimation(RawAnimation.begin().then("animation.Aphaneramma.sleep", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(0.5);
        } else if (this.isScrem()) {
            event.getController().setAnimation(RawAnimation.begin().then("animation.Aphaneramma.screm", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(1.0);
        } else {
            event.getController().setAnimation(RawAnimation.begin().then("animation.Aphaneramma.idle", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(1.0);
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<AphanerammaEntity>(this, "controller", 20, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.factory;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PrehistoricBabyAvoidEntityGoal<>(this, Player.class, 4.0F, 1.5D, 1.5D));
        this.goalSelector.addGoal(0, new PrehistoricBabyPanicGoal(this, 1.5D));
        this.goalSelector.addGoal(0, new PrehistoricSleepInNestGoal(this, 2.0D, 32));
        this.goalSelector.addGoal(0, new PrehistoricGiveBirthGoal(this, this.getMate(), 2.0D, 32));
        this.goalSelector.addGoal(1, new PrehistoricBreedGoal(this, 2.0D));
        this.goalSelector.addGoal(1, new PrehistoricEatFromFeederGoal(this, 2.0D, 32));
        this.goalSelector.addGoal(1, new PrehistoricPlayWithEnrichmentGoal(this, 2.0D, 32));
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
        this.tickPart(this.head, (-f3 * 0.2f * f13), (f4 + f1), (f16 * 0.2f * f13));
        double[] adouble = this.getLatencyPos(5, 1.0F);
        double[] adouble1 = this.getLatencyPos(12, 1.0F);
        float f17 = this.getViewYRot(1) * ((float)Math.PI / 180F) + this.rotWrap(adouble1[0] - adouble[0]) * ((float)Math.PI / 180F);
        float f18 = Mth.sin(f17);
        float f20 = Mth.cos(f17);
        float f22 = 0.1F;
        this.tickPart(this.tail, ((f2 * 0.1F + f18 * f22) * f13), adouble1[1] - adouble[1] - (double)((f22 + 1.5F) * f1), (-(f15 * 0.1F + f20 * f22) * f13));
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
        this.tickPart(this.head, (-f3 * 0.35f * f13), (f4 + f1), (f16 * 0.35f * f13));
        double[] adouble = this.getLatencyPos(5, 1.0F);
        double[] adouble1 = this.getLatencyPos(12, 1.0F);
        float f17 = this.getViewYRot(1) * ((float)Math.PI / 180F) + this.rotWrap(adouble1[0] - adouble[0]) * ((float)Math.PI / 180F);
        float f18 = Mth.sin(f17);
        float f20 = Mth.cos(f17);
        float f22 = 0.2F;
        this.tickPart(this.tail, ((f2 * 0.15F + f18 * f22) * f13), adouble1[1] - adouble[1] - (double)((f22 + 1.5F) * f1), (-(f15 * 0.15F + f20 * f22) * f13));
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
        this.tickPart(this.head, (-f3 * 0.4f * f13), (f4 + f1), (f16 * 0.4f * f13));
        double[] adouble = this.getLatencyPos(5, 1.0F);
        double[] adouble1 = this.getLatencyPos(12, 1.0F);
        float f17 = this.getViewYRot(1) * ((float)Math.PI / 180F) + this.rotWrap(adouble1[0] - adouble[0]) * ((float)Math.PI / 180F);
        float f18 = Mth.sin(f17);
        float f20 = Mth.cos(f17);
        float f22 = 0.3F;
        this.tickPart(this.tail, ((f2 * 0.22F + f18 * f22) * f13), adouble1[1] - adouble[1] - (double)((f22 + 1.5F) * f1), (-(f15 * 0.22F + f20 * f22) * f13));
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
        this.tickPart(this.head, (-f3 * 0.55f * f13), (f4 + f1), (f16 * 0.55f * f13));
        double[] adouble = this.getLatencyPos(5, 1.0F);
        double[] adouble1 = this.getLatencyPos(12, 1.0F);
        float f17 = this.getViewYRot(1) * ((float)Math.PI / 180F) + this.rotWrap(adouble1[0] - adouble[0]) * ((float)Math.PI / 180F);
        float f18 = Mth.sin(f17);
        float f20 = Mth.cos(f17);
        float f22 = 0.4F;
        this.tickPart(this.tail, ((f2 * 0.3F + f18 * f22) * f13), adouble1[1] - adouble[1] - (double)((f22 + 1.5F) * f1), (-(f15 * 0.3F + f20 * f22) * f13));
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

    public double[] getLatencyPos(int pBufferIndexOffset, float pPartialTicks) {
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
    public boolean isInWall() {
        if (this.isInWater()) {
            return false;
        } else {
            return super.isInWall();
        }
    }

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

    @Override
    public ItemStack getPrehistoricSpawnType() {
        return new ItemStack(ItemInit.APHANERAMMA_SPAWN_ITEM.get());
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
            return sizeCoefficient * (maxHeight + 0.3f);
        }
    }

    @Override
    public float getMaxWidth() {
        float sizeCoefficient = genome.calculateCoefficient(genome.getAlleles(this.getGenes(), 7));
        if (this.getGender() == 0) {
            return sizeCoefficient * maxWidth;
        } else {
            return sizeCoefficient * (maxWidth + 0.15f);
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
        } else {
            return genome.calculateDominancePC(alleles);
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
        this.entityData.define(TRAVEL_POS, BlockPos.ZERO);
        this.entityData.define(TRAVELLING, false);
        this.entityData.define(GENOME, "");

    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("TravelPosX", this.getTravelPos().getX());
        pCompound.putInt("TravelPosY", this.getTravelPos().getY());
        pCompound.putInt("TravelPosZ", this.getTravelPos().getZ());
        pCompound.putString("Genome", this.getGenes());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        int l = pCompound.getInt("TravelPosX");
        int i1 = pCompound.getInt("TravelPosY");
        int j1 = pCompound.getInt("TravelPosZ");
        this.setTravelPos(new BlockPos(l, i1, j1));
        this.setGenes(pCompound.getString("Genome"));
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

    static class AphanerammaMoveControl extends SmoothSwimmingMoveControl {
        private final AphanerammaEntity aphaneramma;

        public AphanerammaMoveControl(AphanerammaEntity Aphaneramma) {
            super(Aphaneramma, 85, 10, 3.0F, 2.1F, false);
            this.aphaneramma = Aphaneramma;
        }

        public void tick() {
            if (!this.aphaneramma.isSleeping()) {
                super.tick();
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

            BlockPos blockpos = new BlockPos(k + (int)this.aphaneramma.getX(), l + (int)this.aphaneramma.getY(), i1 + (int)this.aphaneramma.getZ());
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
