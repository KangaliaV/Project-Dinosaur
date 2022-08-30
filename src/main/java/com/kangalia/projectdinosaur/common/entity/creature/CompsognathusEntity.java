package com.kangalia.projectdinosaur.common.entity.creature;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.kangalia.projectdinosaur.common.entity.ai.PrehistoricBabyAvoidEntityGoal;
import com.kangalia.projectdinosaur.common.entity.ai.PrehistoricBabyPanicGoal;
import com.kangalia.projectdinosaur.common.entity.ai.PrehistoricMeleeAttackGoal;
import com.kangalia.projectdinosaur.core.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
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
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.UUID;

public class CompsognathusEntity extends PrehistoricEntity implements IAnimatable {

    private AnimationFactory factory = new AnimationFactory(this);

    public CompsognathusEntity(EntityType<? extends TamableAnimal> entityType, Level world) {
        super(entityType, world);
        this.moveControl = new CompsognathusEntity.CompsognathusMoveControl();
        this.lookControl = new CompsognathusEntity.CompsognathusLookControl();
        this.maxUpStep = 0.5F;
        minSize = 0.50F;
        maxMaleSize = 2.0F;
        maxFemaleSize = 1.8F;
        maxFood = 40;
        diet = 1;
        soundVolume = 0.2F;
        sleepSchedule = 1;
        adultHealth = 12.0F;
        name = new TranslatableComponent("dino.projectdinosaur.compsognathus");
        renderScale = 75;
    }

    public static AttributeSupplier.Builder setCustomAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.3F)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .add(Attributes.ATTACK_DAMAGE, 2.0F)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5F)
                .add(Attributes.ATTACK_SPEED, 1.5F);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (!(event.getLimbSwingAmount() > -0.05F && event.getLimbSwingAmount() < 0.05F)) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.compy.run", true));
            event.getController().setAnimationSpeed(8.0);
        } else if (this.isSleeping()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.compy.sleep", true));
            event.getController().setAnimationSpeed(0.35);
        } else if (this.isScrem()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.compy.screm", true));
            event.getController().setAnimationSpeed(1.0);
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.compy.idle", true));
            event.getController().setAnimationSpeed(1.0);
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.setResetSpeedInTicks(20);
        data.addAnimationController(new AnimationController<CompsognathusEntity>(this, "controller", 10, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new PrehistoricBabyAvoidEntityGoal<>(this, Player.class, 4.0F, 1.75D, 1.5D));
        this.goalSelector.addGoal(0, new PrehistoricBabyPanicGoal(this, 1.75D));
        this.goalSelector.addGoal(1, new PrehistoricMeleeAttackGoal(this, 1.75D, true));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.25D, 200));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(0, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Player.class, 25, true, false, this::isMoodyAt));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Rabbit.class, 20, false, false, (p_28600_) -> p_28600_ instanceof Rabbit));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Chicken.class, 20, false, false, (p_28600_) -> p_28600_ instanceof Chicken));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Pig.class, 20, false, false, (p_28600_) -> p_28600_ instanceof Pig));
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    @Override
    protected int getExperienceReward(Player player) {
        return 1 + this.level.random.nextInt(4);
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
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean canBeLeashed(Player pPlayer) {
        return false;
    }


    @org.jetbrains.annotations.Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageableMob) {
        return EntityInit.COMPSOGNATHUS.get().create(serverWorld);
    }

    @Override
    public int getAdultAge() {
        return 4;
    }

    class CompsognathusMoveControl extends MoveControl {
        public CompsognathusMoveControl() {
            super(CompsognathusEntity.this);
        }

        public void tick() {
            if (!CompsognathusEntity.this.isSleeping()) {
                super.tick();
            }

        }
    }

    public class CompsognathusLookControl extends LookControl {
        public CompsognathusLookControl() {
            super(CompsognathusEntity.this);
        }

        public void tick() {
            if (!CompsognathusEntity.this.isSleeping()) {
                super.tick();
            }

        }
    }
}
