package com.kangalia.projectdinosaur.common.entity;

import com.kangalia.projectdinosaur.common.block.eggs.AphanerammaEggBlock;
import com.kangalia.projectdinosaur.common.blockentity.GroundFeederBlockEntity;
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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public abstract class PrehistoricEntity extends TamableAnimal {

    private static final EntityDataAccessor<Integer> AGE_IN_TICKS = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> AGE_SCALE = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MATING_TICKS = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HUNGER = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HUNGER_TICKS = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);

    private static final Predicate<Entity> PREHISTORIC_PREDICATE = entity -> entity instanceof PrehistoricEntity;

    public float minSize;
    public float maxMaleSize;
    public float maxFemaleSize;
    public int maxFood;
    public int diet;
    protected GroundFeederBlockEntity groundFeeder;
    protected BlockPos blockPos = BlockPos.ZERO;
    public boolean canHunt;

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
        this.setHunger(maxFood / 2);
        this.setHungerTicks(1600);
        return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, compoundTag);
    }

    @Override
    public void tick() {
        super.tick();
        this.setAgeInTicks(this.getAgeInTicks() + 1);
        setAgeScale(getAgeScale());
        if (this.getHungerTicks() > 0) {
            this.setHungerTicks(this.getHungerTicks()-1);
        } else if (this.getHungerTicks() <= 0) {
            if (this.getHunger() > 0) {
                this.setHunger(this.getHunger()-1);
            } else if (this.getHunger() <= 0) {
                this.hurt(DamageSource.STARVE, 1);
                this.playHurtSound(DamageSource.STARVE);
            }
            this.setHungerTicks(this.random.nextInt(600) + 1000);
        }
        if (this.isHungry()) {
            eatFromNearestFeeder();
        }
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
            double d0 = 64;
            List<Entity> list = level.getEntities(this, this.getBoundingBox().inflate(d0, 4.0D, d0), PREHISTORIC_PREDICATE);
            List<PrehistoricEntity> listOfFemales = new ArrayList<>();
            if (!list.isEmpty()) {
                for (Entity e : list) {
                    PrehistoricEntity mob = (PrehistoricEntity) e;
                    if (!mob.equals(this)) {
                        if (mob.getType() == this.getType()) {
                            if (mob.isAdult()) {
                                if (mob.getGender() == 1) {
                                    if (mob.getMatingTicks() == 0) {
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

    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack item = pPlayer.getItemInHand(pHand);
        if (this.isHungry()) {
            if (diet == 0) {
                if (item.getItem().equals(Items.BEEF.asItem()) || item.getItem().equals(Items.PORKCHOP) || item.getItem().equals(Items.CHICKEN) || item.getItem().equals(Items.MUTTON) || item.getItem().equals(Items.RABBIT) || item.getItem().equals(Items.EGG)) {
                    eatFromHand(item);
                    return InteractionResult.SUCCESS;
                }
            } else if (diet == 1) {
                if (item.getItem().equals(Items.WHEAT) || item.getItem().equals(Items.CARROT) || item.getItem().equals(Items.POTATO) || item.getItem().equals(Items.BEETROOT) || item.getItem().equals(Items.WHEAT_SEEDS) || item.getItem().equals(Items.BEETROOT_SEEDS) || item.getItem().equals(Items.APPLE) || item.getItem().equals(Items.MELON_SLICE) || item.getItem().equals(Items.MELON) || item.getItem().equals(Items.PUMPKIN) || item.getItem().equals(Items.MELON_SEEDS) || item.getItem().equals(Items.PUMPKIN_SEEDS) || item.getItem().equals(Items.GLOW_BERRIES)) {
                    eatFromHand(item);
                    return InteractionResult.SUCCESS;
                }
            } else if (diet == 2) {
                if (item.getItem().equals(Items.SALMON) || item.getItem().equals(Items.COD) || item.getItem().equals(Items.TROPICAL_FISH)) {
                    eatFromHand(item);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.FAIL;
    }

    public void eatFromHand(ItemStack item) {
        if (item != null) {
            this.setHunger(this.getHunger() + this.random.nextInt(8) + 3);
            if (this.getHunger() > maxFood) {
                this.setHunger(maxFood);
            }
            item.shrink(1);
            this.level.playSound(null, this.blockPosition(), SoundEvents.GENERIC_EAT, SoundSource.NEUTRAL, this.getSoundVolume(), this.getVoicePitch());
        }
    }

    protected void eatFromNearestFeeder() {
        int i = 16;
        int j = 8;
        BlockPos blockpos = this.blockPosition();
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(int k = 0; k <= j; k = k > 0 ? -k : 1 - k) {
            for(int l = 0; l < i; ++l) {
                for(int i1 = 0; i1 <= l; i1 = i1 > 0 ? -i1 : 1 - i1) {
                    for(int j1 = i1 < l && i1 > -l ? l : 0; j1 <= l; j1 = j1 > 0 ? -j1 : 1 - j1) {
                        blockpos$mutableblockpos.setWithOffset(blockpos, i1, k - 1, j1);
                        if (this.isWithinRestriction(blockpos$mutableblockpos) && this.isValidTarget(this.level, blockpos$mutableblockpos)) {
                            this.blockPos = blockpos$mutableblockpos;
                            if (!groundFeeder.isEmpty(this)) {
                                this.getNavigation().moveTo((double) ((float) this.blockPos.getX()) + 0.5D, (double) (this.blockPos.getY() + 1), (double) ((float) this.blockPos.getZ()) + 0.5D, 1.0D);
                                BlockPos blockPosAbove = this.blockPos.above();
                                if (blockPosAbove.closerToCenterThan(this.position(), 2.0D)) {
                                    this.getNavigation().stop();
                                    groundFeeder.feedEntity(this);
                                    this.setHungerTicks(1600);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
        groundFeeder = (GroundFeederBlockEntity) pLevel.getBlockEntity(pPos);
        return groundFeeder != null;
    }

    public int getDiet() {
        return this.diet;
    }

    public boolean isHungry() {
        return this.getHunger() < maxFood * 0.75F;
    }

    public int getHungerTicks() {
        return this.entityData.get(HUNGER_TICKS);
    }

    public void setHungerTicks(int hungerTicks) {
        this.entityData.set(HUNGER_TICKS, hungerTicks);
    }

    public int getMaxFood() {
        return maxFood;
    }

    public int getHunger() {
        return this.entityData.get(HUNGER);
    }

    public void setHunger(int hunger) {
        this.entityData.set(HUNGER, hunger);
        if (this.entityData.get(HUNGER) > maxFood) {
            this.entityData.set(HUNGER, maxFood);
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
        this.entityData.define(HUNGER, 0);
        this.entityData.define(HUNGER_TICKS, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("AgeInTicks", this.getAgeInTicks());
        pCompound.putFloat("AgeScale", this.getAgeScaleData());
        pCompound.putInt("Gender", this.getGender());
        pCompound.putInt("MatingTicks", this.getMatingTicks());
        pCompound.putInt("Hunger", this.getHunger());
        pCompound.putInt("HungerTicks", this.getHungerTicks());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setAgeInTicks(pCompound.getInt("AgeInTicks"));
        this.setAgeScale(pCompound.getFloat("AgeScale"));
        this.setGender(pCompound.getInt("Gender"));
        this.setMatingTicks(pCompound.getInt("MatingTicks"));
        this.setHunger(pCompound.getInt("Hunger"));
        this.setHungerTicks(pCompound.getInt("HungerTicks"));
    }
}
