package com.kangalia.projectdinosaur.common.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class PrehistoricEntity extends TamableAnimal {

    private static final EntityDataAccessor<Integer> AGE_IN_TICKS = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> AGE_SCALE = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);

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
        return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, compoundTag);
    }

    @Override
    public void tick() {
        super.tick();
        this.setAgeInTicks(this.getAgeInTicks() + 1);
        setAgeScale(getAgeScale());

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
    }


    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("AgeInTicks", this.getAgeInTicks());
        pCompound.putFloat("AgeScale", this.getAgeScaleData());
        pCompound.putInt("Gender", this.getGender());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setAgeInTicks(pCompound.getInt("AgeInTicks"));
        this.setAgeScale(pCompound.getFloat("AgeScale"));
        this.setGender(pCompound.getInt("Gender"));
    }
}