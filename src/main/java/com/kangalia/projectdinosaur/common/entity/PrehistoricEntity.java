package com.kangalia.projectdinosaur.common.entity;

import com.kangalia.projectdinosaur.common.block.eggs.AphanerammaEggBlock;
import com.kangalia.projectdinosaur.core.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class PrehistoricEntity extends TamableAnimal {

    private static final EntityDataAccessor<Integer> AGE_IN_TICKS = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> AGE_SCALE = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MATING_TICKS = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);

    private static final Predicate<Entity> PREHISTORIC_PREDICATE = entity -> entity instanceof PrehistoricEntity;

    public float minSize;
    public float maxMaleSize;
    public float maxFemaleSize;

    protected PrehistoricEntity(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_) {
        super(p_21803_, p_21804_);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag compoundTag) {
        Random random = new Random();
        this.setAgeInDays(this.getAdultAge());
        this.setGender(random.nextInt(2));
        this.setMatingTicks(12000);
        return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, compoundTag);
    }

    @Override
    public void tick() {
        super.tick();
        this.setAgeInTicks(this.getAgeInTicks() + 1);
        setAgeScale(getAgeScale());
        if (!level.isClientSide && this.isAdult()) {
            if (this.getMatingTicks() > 0) {
                this.setMatingTicks(this.getMatingTicks()-1);
            } else {
                this.breed();
            }
        }
    }

    public void breed() {
        if (this.getGender() == 0) {
            System.out.println("Male starts breeding.");
            double d0 = 64;
            List<Entity> list = level.getEntities(this, this.getBoundingBox().inflate(d0, 4.0D, d0), PREHISTORIC_PREDICATE);
            List<PrehistoricEntity> listOfFemales = new ArrayList<>();
            if (!list.isEmpty()) {
                System.out.println("List is not empty in breed.");
                for (Entity e : list) {
                    PrehistoricEntity mob = (PrehistoricEntity) e;
                    if (!mob.equals(this)) {
                        System.out.println("Mob does not equal exact entity.");
                        if (mob.getType() == this.getType()) {
                            System.out.println("Mob type matches.");
                            if (mob.isAdult()) {
                                System.out.println("Mob is adult.");
                                if (mob.getGender() == 1) {
                                    System.out.println("Mob is female.");
                                    if (mob.getMatingTicks() == 0) {
                                        System.out.println("Add female to list in mate.");
                                        listOfFemales.add(mob);
                                    }
                                }
                            }
                        }

                    }
                }
            }
            if (!listOfFemales.isEmpty() && this.getMatingTicks() == 0) {
                PrehistoricEntity prehistoric = listOfFemales.get(0);
                if (prehistoric.getMatingTicks() == 0) {
                    this.getNavigation().moveTo(prehistoric, 1);
                    double distance = this.getBbWidth() * 8.0F * this.getBbWidth() * 8.0F + prehistoric.getBbWidth();
                    if (this.distanceToSqr(prehistoric.getX(), prehistoric.getBoundingBox().minY, prehistoric.getZ()) <= distance && prehistoric.onGround && this.onGround && this.isAdult() && prehistoric.isAdult()) {
                        this.setMatingTicks(this.random.nextInt(6000) + 6000);
                        prehistoric.setMatingTicks(this.random.nextInt(12000) + 12000);
                        this.layEgg(prehistoric);
                    }
                }
            }
        }
    }

    public void layEgg(PrehistoricEntity prehistoric) {
        BlockPos blockpos = prehistoric.blockPosition();
        if (!prehistoric.isInWater()) {
            Level level = prehistoric.level;
            level.playSound((Player) null, blockpos, SoundEvents.TURTLE_LAY_EGG, SoundSource.BLOCKS, 0.3F, 0.9F + level.random.nextFloat() * 0.2F);
            if (prehistoric instanceof AphanerammaEntity) {
                level.setBlock(prehistoric.getOnPos().above(), BlockInit.INCUBATED_APHANERAMMA_EGG.get().defaultBlockState().setValue(AphanerammaEggBlock.EGGS, prehistoric.random.nextInt(4) + 1), 3);
            } else {
                level.setBlock(prehistoric.getOnPos().above(), BlockInit.INCUBATED_APHANERAMMA_EGG.get().defaultBlockState().setValue(AphanerammaEggBlock.EGGS, prehistoric.random.nextInt(4) + 1), 3);
            }
        }
    }

    public int getMatingTicks() {
        return this.entityData.get(MATING_TICKS);
    }

    public void setMatingTicks(int matingTicks) {
        this.entityData.set(MATING_TICKS, matingTicks);
    }

    public boolean isAdult() {
        return this.getAgeInDays() >= getAdultAge();
    }

    @Override
    public boolean isBaby() {
        return this.getAgeInDays() < this.getAdultAge();
    }

    public int getAgeInDays() {
        return this.entityData.get(AGE_IN_TICKS) / 24000;
    }

    public void setAgeInDays(int days) {
        this.entityData.set(AGE_IN_TICKS, days * 24000);
    }

    public int getAdultAge() {
        return 1;
    }

    public int getAgeInTicks() {
        return this.entityData.get(AGE_IN_TICKS);
    }

    public void setAgeInTicks(int ticks) {
        this.entityData.set(AGE_IN_TICKS, ticks);
    }

    public float getAgeScaleData() {
        return this.entityData.get(AGE_SCALE);
    }

    public void setAgeScale(float ageScale) {
        this.entityData.set(AGE_SCALE, ageScale);
    }

    public int getGender() {
        return this.entityData.get(GENDER);
    }

    public void setGender(int gender) {
        this.entityData.set(GENDER, gender);
    }

    public float getGenderMaxSize() {
        if (this.getGender() == 0) {
            return maxMaleSize;
        } else {
            return maxFemaleSize;
        }
    }

    public float getAgeScale() {
        float maxSize = getGenderMaxSize();
        float step = (maxSize - this.minSize) / ((this.getAdultAge() * 24000) + 1);
        if (this.getAgeInTicks() >= this.getAdultAge() * 24000) {
            return minSize + ((step) * this.getAdultAge() * 24000);
        }
        return minSize + ((step * this.getAgeInTicks()));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(AGE_IN_TICKS, 0);
        this.entityData.define(AGE_SCALE, 0.0F);
        this.entityData.define(GENDER, 0);
        this.entityData.define(MATING_TICKS, 240);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("AgeInTicks", this.getAgeInTicks());
        pCompound.putFloat("AgeScale", this.getAgeScaleData());
        pCompound.putInt("Gender", this.getGender());
        pCompound.putInt("MatingTicks", this.getMatingTicks());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setAgeInTicks(pCompound.getInt("AgeInTicks"));
        this.setAgeScale(pCompound.getFloat("AgeScale"));
        this.setGender(pCompound.getInt("Gender"));
        this.setMatingTicks(pCompound.getInt("MatingTicks"));
    }
}
