package com.kangalia.projectdinosaur.common.entity;

import com.kangalia.projectdinosaur.common.block.enrichment.EnrichmentBlock;
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
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public abstract class PrehistoricEntity extends TamableAnimal implements NeutralMob {

    private static final EntityDataAccessor<Integer> AGE_IN_TICKS = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> AGE_SCALE = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MATING_TICKS = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HUNGER = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HUNGER_TICKS = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HEALING_TICKS = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> MISSED_SLEEP = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> SCREM = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> STUNTED = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> REMAINING_ANGER_TIME = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> REMAINING_CRYOSICKNESS_TIME = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ENRICHMENT = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ENRICHMENT_TICKS = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ENRICHMENT_COOLDOWN = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<BlockPos> NEST_POS = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Boolean> PREGNANT = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> PREGNANCY_TICKS = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<String> MATE = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.STRING);

    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);

    public float minSize;
    public float maxMaleSize;
    public float maxFemaleSize;
    public float minHeight;
    public float maxHeight;
    public float minWidth;
    public float maxWidth;
    public int maxFood;
    public int diet;
    protected EnrichmentBlock enrichment;
    protected Block nest;
    protected BlockPos blockPos = BlockPos.ZERO;
    public float soundVolume;
    public int sleepSchedule;
    private UUID persistentAngerTarget;
    public Component name;
    public Component nameScientific;
    public int renderScale;
    public int maxEnrichment = 100;
    boolean adultFlag = false;
    boolean juviFlag = false;
    public int breedingType;
    public boolean canSleep;
    public int maleRoamDistance;
    public int femaleRoamDistance;
    public int juvinileRoamDistance;
    public int babyRoamDistance;
    public boolean isLand;

    protected PrehistoricEntity(EntityType<? extends TamableAnimal> p_21803_, Level p_21804_) {
        super(p_21803_, p_21804_);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@Nonnull ServerLevel serverLevel, @Nonnull AgeableMob ageableMob) {
        return null;
    }

    @Override
    public SpawnGroupData finalizeSpawn(@Nonnull ServerLevelAccessor serverLevelAccessor, @Nonnull DifficultyInstance difficultyInstance, @Nonnull MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag compoundTag) {
        Random random = new Random();
        this.setAgeInDays(this.getAdultAge());
        this.setGender(random.nextInt(2));
        this.setMatingTicks(7500);
        this.setHunger(maxFood);
        this.setHungerTicks(1600);
        this.setRemainingCryosicknessTime(0);
        this.setEnrichment(maxEnrichment / 2);
        this.setEnrichmentTicks(2000);
        this.setAttributes(0);
        return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, compoundTag);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level.isClientSide) {
            if (this.isAngry()) {
                this.setLastHurtByMob(this.getLastHurtByMob());
            }
            this.updatePersistentAnger((ServerLevel)this.level, true);
        }
    }

    public int getRoamDistance() {
        if (this.isBaby()) {
            return babyRoamDistance;
        } else if (this.isJuvenile()) {
            return juvinileRoamDistance;
        } else {
            if (this.getGender() == 0) {
                return maleRoamDistance;
            } else {
                return femaleRoamDistance;
            }
        }
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        if (!Objects.equals(this.getGenes(), "")) {
            return super.getDimensions(pPose).scale(this.getDimensionScaleWidth(), this.getDimensionScaleHeight());
        } else {
            return super.getDimensions(pPose);
        }
    }

    public float getDimensionScaleHeight() {
        float step = (getMaxHeight() - this.minHeight) / ((this.getAdultAge() * 24000) + 1);
        if (this.getAgeInTicks() >= this.getAdultAge() * 24000) {
            return this.minHeight + ((step) * this.getAdultAge() * 24000);
        }
        return minHeight + (step * this.getAgeInTicks());
    }

    public float getDimensionScaleWidth() {
        float step = (getMaxWidth() - this.minWidth) / ((this.getAdultAge() * 24000) + 1);
        if (this.getAgeInTicks() >= this.getAdultAge() * 24000) {
            return this.minWidth + ((step) * this.getAdultAge() * 24000);
        }
        return minWidth + (step * this.getAgeInTicks());
    }

    @Override
    public int getExperienceReward() {
        float multiplier = 2.0f * this.getBbWidth();
        if (this.getDiet() == 1) {
            multiplier = multiplier * 1.5f;
        }

        return Math.min(this.getAdultAge(), this.getAdultAge() * (int)multiplier) + this.level.random.nextInt(3);
    }

    @Override
    public boolean canMate(Animal pOtherAnimal) {
        if (pOtherAnimal == this) {
            return false;
        } else if (pOtherAnimal.getClass() != this.getClass()) {
            return false;
        } else {
            PrehistoricEntity prehistoric = (PrehistoricEntity) pOtherAnimal;
            if (!prehistoric.isAdult() || !this.isAdult()) {
                return false;
            } else if (prehistoric.getMatingTicks() > 0 || this.getMatingTicks() > 0) {
                return false;
            } else if (prehistoric.getGender() == this.getGender()) {
                return false;
            } else return !prehistoric.isGrumpy() || !this.isGrumpy();
        }
    }

    @Override
    public void tick() {
        super.tick();
        refreshDimensions();
        if (!level.isClientSide) {

            // AGE
            if (this.getAgeInTicks() == this.getAdultAge() * 24000 && !adultFlag) {
                this.setAttributes(0);
                adultFlag = true;
            }
            if (this.isJuvenile() && !juviFlag) {
                this.setAttributes(1);
                juviFlag = true;
            }
            if (!this.isStunted()) {
                this.setAgeInTicks(this.getAgeInTicks() + 1);
                setAgeScale(getAgeScale());
            }


            // HUNGER
            if (this.getHungerTicks() > 0) {
                this.setHungerTicks(this.getHungerTicks() - 1);
            } else if (this.getHungerTicks() <= 0) {
                if (this.getHunger() > 0) {
                    this.setHunger(this.getHunger() - 1);
                } else if (this.getHunger() <= 0) {
                    this.hurt(this.damageSources().starve(), 1);
                    this.playHurtSound(this.damageSources().starve());
                }
                this.setHungerTicks(this.random.nextInt(600) + 1000);
            }


            // ENRICHMENT
            if (this.getEnrichmentTicks() > 0) {
                this.setEnrichmentTicks(this.getEnrichmentTicks() - 1);
            } else if (this.getEnrichmentTicks() <= 0) {
                if (this.getEnrichment() > 0) {
                    this.setEnrichment(this.getEnrichment() - 1);
                }
                this.setEnrichmentTicks(this.random.nextInt(500) + 1500);
            }
            if (this.getEnrichmentCooldown() > 0) {
                this.setEnrichmentCooldown(this.getEnrichmentCooldown() - 1);
            }


            // MATING
            if (this.isAdult()) {
                if (this.getMatingTicks() > 0) {
                    this.setMatingTicks(this.getMatingTicks() - 1);
                }
            }


            // PREGNANCY
            if (this.getPregnancyTicks() > 0) {
                this.setPregnancyTicks(this.getPregnancyTicks() - 1);
            }


            // CRYOSICKNESS
            if (this.getRemainingCryosicknessTime() > 0) {
                this.setRemainingCryosicknessTime(this.getRemainingCryosicknessTime() - 1);
                this.setSleeping(true);
                if (this.getRemainingCryosicknessTime() == 1199) {
                    this.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, this.getRemainingCryosicknessTime(), 0));
                }
            }


            // SLEEPING
            if (this.shouldSleep() && !this.isSleeping()) {
                if (this.canSleep) {
                    this.setSleeping(true);
                    this.setMissedSleep(0);
                } else {
                    this.addMissedSleep();
                }
            }
            if (!this.shouldSleep() && this.isSleeping() && this.getRemainingCryosicknessTime() == 0) {
                this.setSleeping(false);
                this.setCanSleep(false);
            }


            // HEALTH REGEN
            if (this.getHealth() < this.getMaxHealth() && !this.isStarving()) {
                this.setHealingTicks(this.getHealingTicks() + 1);
                if (this.getHealingTicks() == 100) {
                    this.setHealth(this.getHealth() + 1);
                    this.setHunger(this.getHunger() - 1);
                    if (this.getHealth() > this.getMaxHealth()) {
                        this.setHealth(this.getMaxHealth());
                    }
                    this.setHealingTicks(0);
                }
            }
        }
    }

    public CompoundTag writeSpawnParents(String prehistoric) {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putString("parent1", this.getGenes());
        compoundTag.putString("parent2", prehistoric);
        return compoundTag;
    }

    public ItemStack getPrehistoricSpawnType() {
        return ItemStack.EMPTY;
    }

    public String getMate() {
        return this.entityData.get(MATE);
    }

    public void setMate(String mateGenes) {
        this.entityData.set(MATE, mateGenes);
    }

    public boolean isPregnant() {
        return this.entityData.get(PREGNANT);
    }

    public void setPregnant(boolean pregnant) {
        this.entityData.set(PREGNANT, pregnant);
    }

    public int getPregnancyTicks() {
        return this.entityData.get(PREGNANCY_TICKS);
    }

    public void setPregnancyTicks(int ticks) {
        this.entityData.set(PREGNANCY_TICKS, ticks);
    }

    public int getBreedingType() {
        return breedingType;
    }

    public Block getEggType() {
        return null;
    }

    public int getClutchSize() {
        return 1;
    }


    @Nonnull
    @Override
    public InteractionResult mobInteract(Player pPlayer, @Nonnull InteractionHand pHand) {
        ItemStack item = pPlayer.getItemInHand(pHand);
        if (this.isHungry() || this.getHealth() < this.getMaxHealth()) {
            if (diet == 0) {
                if (item.getItem().equals(Items.WHEAT) || item.getItem().equals(Items.CARROT) || item.getItem().equals(Items.POTATO) || item.getItem().equals(Items.BEETROOT) || item.getItem().equals(Items.WHEAT_SEEDS) || item.getItem().equals(Items.BEETROOT_SEEDS) || item.getItem().equals(Items.APPLE) || item.getItem().equals(Items.MELON_SLICE) || item.getItem().equals(Items.MELON) || item.getItem().equals(Items.PUMPKIN) || item.getItem().equals(Items.MELON_SEEDS) || item.getItem().equals(Items.PUMPKIN_SEEDS) || item.getItem().equals(Items.GLOW_BERRIES)) {
                    eatFromHand(item);
                    return InteractionResult.SUCCESS;
                }
            } else if (diet == 1) {
                if (item.getItem().equals(Items.BEEF) || item.getItem().equals(Items.PORKCHOP) || item.getItem().equals(Items.CHICKEN) || item.getItem().equals(Items.MUTTON) || item.getItem().equals(Items.RABBIT) || item.getItem().equals(Items.EGG)) {
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
        if (item.is(ItemInit.GROWTH_ACCELERATING_HORMONE.get()) && !this.isAdult()) {
            if (this.isStunted() && item.getCount() >= 2) {
                this.setStunted(false);
                item.shrink(2);
                playHormoneSound(pPlayer);
            } else if (!this.isStunted()) {
                this.setHunger(this.getHunger() - 5);
                this.setAgeInDays(this.getAgeInDays() + 1);
                item.shrink(1);
                playHormoneSound(pPlayer);
            }
            refreshDimensions();
            return InteractionResult.SUCCESS;
        } else if (item.is(ItemInit.GROWTH_STUNTING_HORMONE.get()) && !this.isAdult() && !this.isStunted()) {
            this.setStunted(true);
            item.shrink(1);
            playHormoneSound(pPlayer);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    public void eatFromHand(ItemStack item) {
        if (item != null) {
            if (this.isHungry()) {
                this.setHunger(this.getHunger() + (this.random.nextInt(6) + 8));
                if (this.getHunger() > maxFood) {
                    this.setHunger(maxFood);
                }
            } else {
                this.heal(this.random.nextInt(3) + 1);
            }
            item.shrink(1);
            this.level.playSound(null, this.blockPosition(), SoundEvents.GENERIC_EAT, SoundSource.NEUTRAL, this.getSoundVolume(), this.getVoicePitch());
        }
    }

    public boolean isTerrestrial() {
        return this.isLand;
    }

    public void setSleeping(boolean sleeping) {
        this.entityData.set(SLEEPING, sleeping);
    }

    public int getSleepSchedule() {
        return sleepSchedule;
    }

    public boolean shouldSleep() {
        if (this.isAngry() || this.isStarving()) {
            return false;
        } else if (this.isTerrestrial() && this.isInWater()) {
            return false;
        } else if (this.level.isDay() && getSleepSchedule() == 1) {
            return true;
        } else if (!this.level.isDay() && getSleepSchedule() == 0) {
            return true;
        } else return false;
    }

    public void setCanSleep(boolean sleep) {
        canSleep = sleep;
    }

    public boolean isSleeping() {
        return this.entityData.get(SLEEPING);
    }

    public int getMissedSleep() {
        return this.entityData.get(MISSED_SLEEP);
    }

    public void setMissedSleep(int sleepMissed) {
        this.entityData.set(MISSED_SLEEP, sleepMissed);
    }

    public void addMissedSleep() {
        this.setMissedSleep(this.getMissedSleep() + 1);
    }

    public boolean isCryosick() {
        return this.getRemainingCryosicknessTime() > 0;
    }

    public int getRemainingCryosicknessTime() {
        return this.entityData.get(REMAINING_CRYOSICKNESS_TIME);
    }

    public void setRemainingCryosicknessTime(int time) {
        this.entityData.set(REMAINING_CRYOSICKNESS_TIME, time);
    }

    public void playHormoneSound(Player player) {
        player.playSound(SoundEvents.ZOMBIE_VILLAGER_CURE, 1.0F, 1.0F);
    }

    public void randomizeAttributes(int age) {}

    public void setAttributes(int age) {
        this.randomizeAttributes(age);
        this.setHealth((float)this.getAttribute(Attributes.MAX_HEALTH).getValue());
        refreshDimensions();
    }

    public String getGenes() {
        return "";
    }

    public String getColourMorph() {
        return "Normal";
    }

    public String getCoefficientRating(int gene) {
        return "Error!";
    }

    public Block getEgg() {
        return null;
    }

    public int getDiet() {
        return this.diet;
    }

    public boolean isHungry() {
        return this.getHunger() < maxFood * 0.8F;
    }

    public boolean isStarving() {
        return this.getHunger() < maxFood * 0.2F;
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

    public void setHealingTicks(int ticks) {
        this.entityData.set(HEALING_TICKS, ticks);
    }

    public int getHealingTicks() {
        return this.entityData.get(HEALING_TICKS);
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

    public boolean isJuvenile() {
        int adultTicks = this.getAdultAge() * 24000;
        boolean isJuvi = this.getAgeInTicks() >= adultTicks * 0.6;
        boolean isNotAdult = this.getAgeInDays() < this.getAdultAge();
        return !isJuvi || !isNotAdult;
    }

    @Override
    public boolean isBaby() {
        boolean isNotAdult = this.getAgeInDays() < this.getAdultAge();
        return isNotAdult && this.isJuvenile();
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

    public float getMaxHeight() {
        if (this.getGender() == 0) {
            return maxHeight;
        } else {
            return maxHeight - 0.5f;
        }
    }

    public float getMaxWidth() {
        if (this.getGender() == 0) {
            return maxWidth;
        } else {
            return maxWidth - 0.2f;
        }
    }

    public void setScrem(boolean screm) {
        this.entityData.set(SCREM, screm);
    }

    public boolean isScrem() {
        return this.entityData.get(SCREM);
    }

    public void setStunted(boolean stunted) {
        this.entityData.set(STUNTED, stunted);
    }

    public boolean isStunted() {
        return this.entityData.get(STUNTED);
    }

    public float getAgeScale() {
        float maxSize = getGenderMaxSize();
        float step = (maxSize - this.minSize) / ((this.getAdultAge() * 24000) + 1);
        if (this.getAgeInTicks() >= this.getAdultAge() * 24000) {
            return minSize + ((step) * this.getAdultAge() * 24000);
        }
        return minSize + ((step * this.getAgeInTicks()));
    }

    public int getEnrichment() {
        return this.entityData.get(ENRICHMENT);
    }

    public void setEnrichment(int enrichment) {
        this.entityData.set(ENRICHMENT, enrichment);
        if (this.entityData.get(ENRICHMENT) > maxEnrichment) {
            this.entityData.set(ENRICHMENT, maxEnrichment);
        }
    }

    public int getEnrichmentTicks() {
        return this.entityData.get(ENRICHMENT_TICKS);
    }

    public void setEnrichmentTicks(int ticks) {
        this.entityData.set(ENRICHMENT_TICKS, ticks);
    }

    public int getEnrichmentCooldown() {
        return this.entityData.get(ENRICHMENT_COOLDOWN);
    }

    public void setEnrichmentCooldown(int cooldown) {
        this.entityData.set(ENRICHMENT_COOLDOWN, cooldown);
    }

    public int getMaxEnrichment() {
        return maxEnrichment;
    }

    public int getMood() {
        if (this.isMoody()) {
            return 1;
        } else if (this.isAngry()) {
            return 2;
        } else {
            return 0;
        }
    }

    public boolean isMoody() {
        return this.getEnrichment() < (maxEnrichment / 2) || this.getMissedSleep() == 4;
    }

    public boolean isMoodyAt(LivingEntity livingEntity) {
        if (this.isAngry()) {
            return false;
        }
        return livingEntity.getType() == EntityType.PLAYER;
    }

    public boolean isGrumpy() {
        return this.getEnrichment() < (maxEnrichment * 0.8f) || this.getMissedSleep() == 2;
    }

    public Component getSpecies() {
        return name;
    }

    public Component getSpeciesScientific() {
        return nameScientific;
    }

    public int getRenderScale() {
        return renderScale;
    }

    @Override
    public float getSoundVolume() {
        return this.soundVolume;
    }

    @Override
    public void playAmbientSound() {
        SoundEvent soundevent = this.getAmbientSound();
        if (soundevent != null && !this.isSleeping()) {
            this.setScrem(true);
            this.playSound(soundevent, this.getSoundVolume(), this.getVoicePitch());
            this.setScrem(false);
        }
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(REMAINING_ANGER_TIME);
    }

    @Override
    public void setRemainingPersistentAngerTime(int pTime) {
        this.entityData.set(REMAINING_ANGER_TIME, pTime);
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    @Override
    @javax.annotation.Nullable
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    @Override
    public void setPersistentAngerTarget(@javax.annotation.Nullable UUID pTarget) {
        this.persistentAngerTarget = pTarget;
    }

    @Override
    public boolean hurt(@Nonnull DamageSource pSource, float pAmount) {
        this.setHealingTicks(0);
        return super.hurt(pSource, pAmount);
    }

    @Override
    public boolean isAngry() {
        if (this.getPersistentAngerTarget() != null && this.getLastHurtByMob() != null) {
            if (this.getPersistentAngerTarget().equals(this.getLastHurtByMob().getUUID())) {
                return this.getRemainingPersistentAngerTime() > 0;
            } else {
                return false;
            }
        }
        return false;
    }

    public BlockPos getNestPos() {
        return this.entityData.get(NEST_POS);
    }

    public void setNestPos(BlockPos pHomePos) {
        this.entityData.set(NEST_POS, pHomePos);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(AGE_IN_TICKS, 0);
        this.entityData.define(AGE_SCALE, 0.0F);
        this.entityData.define(GENDER, 0);
        this.entityData.define(MATING_TICKS, 0);
        this.entityData.define(HUNGER, 0);
        this.entityData.define(HUNGER_TICKS, 0);
        this.entityData.define(HEALING_TICKS, 0);
        this.entityData.define(SLEEPING, false);
        this.entityData.define(MISSED_SLEEP, 0);
        this.entityData.define(SCREM, false);
        this.entityData.define(STUNTED, false);
        this.entityData.define(REMAINING_CRYOSICKNESS_TIME, 0);
        this.entityData.define(REMAINING_ANGER_TIME, 0);
        this.entityData.define(ENRICHMENT, 0);
        this.entityData.define(ENRICHMENT_TICKS, 0);
        this.entityData.define(ENRICHMENT_COOLDOWN, 0);
        this.entityData.define(NEST_POS, BlockPos.ZERO);
        this.entityData.define(PREGNANT, false);
        this.entityData.define(PREGNANCY_TICKS, 0);
        this.entityData.define(MATE, "");
    }

    @Override
    public void addAdditionalSaveData(@Nonnull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("AgeInTicks", this.getAgeInTicks());
        pCompound.putFloat("AgeScale", this.getAgeScaleData());
        pCompound.putInt("Gender", this.getGender());
        pCompound.putInt("MatingTicks", this.getMatingTicks());
        pCompound.putInt("Hunger", this.getHunger());
        pCompound.putInt("HungerTicks", this.getHungerTicks());
        pCompound.putInt("HealingTicks", this.getHealingTicks());
        pCompound.putBoolean("Sleeping", this.isSleeping());
        pCompound.putInt("MissedSleep", this.getMissedSleep());
        pCompound.putBoolean("Screm", this.isScrem());
        pCompound.putBoolean("Stunted", this.isStunted());
        pCompound.putInt("RemainingCryosicknessTime", this.getRemainingCryosicknessTime());
        this.addPersistentAngerSaveData(pCompound);
        pCompound.putInt("Enrichment", this.getEnrichment());
        pCompound.putInt("EnrichmentTicks", this.getEnrichmentTicks());
        pCompound.putInt("EnrichmentCooldown", this.getEnrichmentCooldown());
        pCompound.putInt("EnrichmentCooldown", this.getEnrichmentCooldown());
        pCompound.putInt("NestPosX", this.getNestPos().getX());
        pCompound.putInt("NestPosY", this.getNestPos().getY());
        pCompound.putInt("NestPosZ", this.getNestPos().getZ());
        pCompound.putBoolean("Pregnant", this.isPregnant());
        pCompound.putInt("PregnancyTicks", this.getPregnancyTicks());
        pCompound.putString("Mate", this.getMate());
    }

    @Override
    public void readAdditionalSaveData(@Nonnull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setAgeInTicks(pCompound.getInt("AgeInTicks"));
        this.setAgeScale(pCompound.getFloat("AgeScale"));
        this.setGender(pCompound.getInt("Gender"));
        this.setMatingTicks(pCompound.getInt("MatingTicks"));
        this.setHunger(pCompound.getInt("Hunger"));
        this.setHungerTicks(pCompound.getInt("HungerTicks"));
        this.setHealingTicks(pCompound.getInt("HealingTicks"));
        this.setSleeping(pCompound.getBoolean("Sleeping"));
        this.setMissedSleep(pCompound.getInt("MissedSleep"));
        this.setScrem(pCompound.getBoolean("Screm"));
        this.setStunted(pCompound.getBoolean("Stunted"));
        this.setRemainingCryosicknessTime(pCompound.getInt("RemainingCryosicknessTime"));
        this.readPersistentAngerSaveData(this.level, pCompound);
        this.setEnrichment(pCompound.getInt("Enrichment"));
        this.setEnrichmentTicks(pCompound.getInt("EnrichmentTicks"));
        this.setEnrichmentCooldown(pCompound.getInt("EnrichmentCooldown"));
        int i = pCompound.getInt("NestPosX");
        int j = pCompound.getInt("NestPosY");
        int k = pCompound.getInt("NestPosZ");
        this.setNestPos(new BlockPos(i, j, k));
        this.setPregnant(pCompound.getBoolean("Pregnant"));
        this.setPregnancyTicks(pCompound.getInt("PregnancyTicks"));
        this.setMate(pCompound.getString("Mate"));
    }
}
