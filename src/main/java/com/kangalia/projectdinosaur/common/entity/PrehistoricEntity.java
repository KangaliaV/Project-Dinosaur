package com.kangalia.projectdinosaur.common.entity;

import com.kangalia.projectdinosaur.common.block.eggs.*;
import com.kangalia.projectdinosaur.common.block.enrichment.BubbleBlowerBlock;
import com.kangalia.projectdinosaur.common.block.enrichment.EnrichmentBlock;
import com.kangalia.projectdinosaur.common.block.enrichment.ScentDiffuserBlock;
import com.kangalia.projectdinosaur.common.blockentity.GroundFeederBlockEntity;
import com.kangalia.projectdinosaur.common.blockentity.eggs.GastornisEggBlockEntity;
import com.kangalia.projectdinosaur.common.entity.creature.*;
import com.kangalia.projectdinosaur.core.init.BlockEntitiesInit;
import com.kangalia.projectdinosaur.core.init.BlockInit;
import com.kangalia.projectdinosaur.core.init.ItemInit;
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
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.event.level.PistonEvent;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.Predicate;

public abstract class PrehistoricEntity extends TamableAnimal implements NeutralMob {

    private static final EntityDataAccessor<Integer> AGE_IN_TICKS = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> AGE_SCALE = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MATING_TICKS = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HUNGER = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HUNGER_TICKS = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HEALING_TICKS = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> SLEEPING = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> SCREM = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> STUNTED = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> REMAINING_ANGER_TIME = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> REMAINING_CRYOSICKNESS_TIME = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ENRICHMENT = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ENRICHMENT_TICKS = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ENRICHMENT_COOLDOWN = SynchedEntityData.defineId(PrehistoricEntity.class, EntityDataSerializers.INT);
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    private static final Predicate<Entity> PREHISTORIC_PREDICATE = entity -> entity instanceof PrehistoricEntity;

    public float minSize;
    public float maxMaleSize;
    public float maxFemaleSize;
    public int maxFood;
    public int diet;
    protected GroundFeederBlockEntity groundFeeder;
    protected EnrichmentBlock enrichment;
    protected Block nest;
    protected BlockPos blockPos = BlockPos.ZERO;
    public float soundVolume;
    public int sleepSchedule;
    private UUID persistentAngerTarget;
    public float adultHealth;
    public Component name;
    public int renderScale;
    public int maxEnrichment = 100;
    private boolean validTarget;

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
        this.setMatingTicks(1200);
        this.setHunger(maxFood);
        this.setHungerTicks(1600);
        this.setRemainingCryosicknessTime(0);
        this.setEnrichment(maxEnrichment / 2);
        this.setEnrichmentTicks(2000);
        this.setAdultAttributes();
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

    @Override
    public void tick() {
        super.tick();
        if (!level.isClientSide) {
            if (this.getAgeInTicks() == this.getAdultAge() * 24000) {
                this.setAdultAttributes();
            }
            if (!this.isStunted()) {
                this.setAgeInTicks(this.getAgeInTicks() + 1);
                setAgeScale(getAgeScale());
            }
            if (this.getHungerTicks() > 0) {
                this.setHungerTicks(this.getHungerTicks() - 1);
            } else if (this.getHungerTicks() <= 0) {
                if (this.getHunger() > 0) {
                    this.setHunger(this.getHunger() - 1);
                } else if (this.getHunger() <= 0) {
                    this.hurt(DamageSource.STARVE, 1);
                    this.playHurtSound(DamageSource.STARVE);
                }
                this.setHungerTicks(this.random.nextInt(600) + 1000);
            }
            if (this.isHungry() && !isSleeping()) {
                eatFromNearestFeeder();
            }
            if (this.getEnrichmentTicks() > 0) {
                this.setEnrichmentTicks(this.getEnrichmentTicks() - 1);
            } else if (this.getEnrichmentTicks() <= 0) {
                if (this.getEnrichment() > 0) {
                    this.setEnrichment(this.getEnrichment() - 1);
                } else if (this.getEnrichment() <= 0) {
                    this.hurt(DamageSource.GENERIC, 1);
                    this.playHurtSound(DamageSource.GENERIC);
                }
                this.setEnrichmentTicks(this.random.nextInt(500) + 1500);
            }
            if (this.getEnrichmentCooldown() > 0) {
                this.setEnrichmentCooldown(this.getEnrichmentCooldown() - 1);
            } else if (this.getEnrichmentCooldown() <= 0) {
                if (isGrumpy() && !isSleeping()) {
                    playWithNearestEnrichment();
                }
            }
            if (this.isAdult()) {
                if (this.getMatingTicks() > 0) {
                    this.setMatingTicks(this.getMatingTicks() - 1);
                } else if (!isSleeping()) {
                    if (!this.isMoody()) {
                        this.breed();
                    }
                }
            }
            if (this.getRemainingCryosicknessTime() > 0) {
                this.setRemainingCryosicknessTime(this.getRemainingCryosicknessTime() - 1);
            }
            if (this.shouldSleep() && !this.isSleeping()) {
                if (this.findNestToSleep() == 1) {
                    this.setSleeping(true);
                }
            }
            if (!this.shouldSleep() && this.isSleeping()) {
                this.setSleeping(false);
            }
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
                    double distance = this.getBbWidth() * 4.0F * this.getBbWidth() * 4.0F + prehistoric.getBbWidth();
                    if (this.distanceToSqr(prehistoric.getX(), prehistoric.getBoundingBox().minY, prehistoric.getZ()) <= distance && prehistoric.onGround && this.onGround && this.isAdult() && prehistoric.isAdult()) {
                        this.setMatingTicks(this.random.nextInt(6000) + 6000);
                        prehistoric.setMatingTicks(this.random.nextInt(12000) + 12000);
                        if (this.getBreedingType() == 1) {
                            this.laySpawn(prehistoric);
                        } else {
                            this.layEgg(prehistoric);
                        }
                    }
                }
            }
        }
    }

    public void layEgg(PrehistoricEntity prehistoric) {
        BlockPos blockpos = prehistoric.blockPosition();
        if (!prehistoric.isInWater()) {
            Level level = prehistoric.level;
            level.playSound(null, blockpos, SoundEvents.TURTLE_LAY_EGG, SoundSource.BLOCKS, 0.3F, 0.9F + level.random.nextFloat() * 0.2F);
            Block eggType = prehistoric.getEggType();
            BlockPos eggPos = prehistoric.getOnPos().above();
            level.setBlock(eggPos, eggType.defaultBlockState().setValue(PrehistoricEggBlock.EGGS, prehistoric.random.nextInt(prehistoric.getClutchSize()) + 1), 3);
            BlockEntity eggEntity = level.getBlockEntity(eggPos);
            if (prehistoric instanceof GastornisEntity && this instanceof GastornisEntity && eggEntity != null && eggEntity.getType() == BlockEntitiesInit.GASTORNIS_EGG_ENTITY.get()) {
                GastornisEggBlockEntity gastornisEggEntity = (GastornisEggBlockEntity) eggEntity.getType().getBlockEntity(level, eggPos);
                System.out.println("Egg Entity: "+gastornisEggEntity);
                if (gastornisEggEntity != null) {
                    System.out.println("Father: "+((GastornisEntity) this).getGenes());
                    System.out.println("Mother: "+((GastornisEntity) prehistoric).getGenes());
                    gastornisEggEntity.setParent1(((GastornisEntity) this).getGenes());
                    gastornisEggEntity.setParent2(((GastornisEntity) prehistoric).getGenes());
                }
            }
        }
    }

    public void laySpawn(PrehistoricEntity prehistoric) {
        if (!prehistoric.level.isClientSide) {
            ItemEntity item = new ItemEntity(prehistoric.level, prehistoric.getX(), prehistoric.getY(), prehistoric.getZ(), prehistoric.getSpawnType());
            prehistoric.level.addFreshEntity(item);
        }
    }

    public int getBreedingType() {
        return 0;
    }

    public Block getEggType() {
        return null;
    }

    public int getClutchSize() {
        return 1;
    }

    public ItemStack getSpawnType() {
        return ItemStack.EMPTY;
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
                                this.getNavigation().moveTo((double) ((float) this.blockPos.getX()) + 0.5D, (this.blockPos.getY() + 1), (double) ((float) this.blockPos.getZ()) + 0.5D, 1.0D);
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
        if (pLevel.getBlockEntity(pPos) instanceof GroundFeederBlockEntity) {
            groundFeeder = (GroundFeederBlockEntity) pLevel.getBlockEntity(pPos);
            return groundFeeder != null;
        }
        return false;
    }

    public int findNestToSleep() {
        int i = 16;
        int j = 8;
        BlockPos blockpos = this.blockPosition();
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(int k = 0; k <= j; k = k > 0 ? -k : 1 - k) {
            for(int l = 0; l < i; ++l) {
                for(int i1 = 0; i1 <= l; i1 = i1 > 0 ? -i1 : 1 - i1) {
                    for(int j1 = i1 < l && i1 > -l ? l : 0; j1 <= l; j1 = j1 > 0 ? -j1 : 1 - j1) {
                        blockpos$mutableblockpos.setWithOffset(blockpos, i1, k - 1, j1);
                        if (this.isWithinRestriction(blockpos$mutableblockpos) && this.isValidTargetSleeping(this.level, blockpos$mutableblockpos)) {
                            this.blockPos = blockpos$mutableblockpos;
                            this.getNavigation().moveTo((double) ((float) this.blockPos.getX()) + 0.5D, (this.blockPos.getY() + 1), (double) ((float) this.blockPos.getZ()) + 0.5D, 1.0D);
                            BlockPos blockPosAbove = this.blockPos.above();
                            if (blockPosAbove.closerToCenterThan(this.position(), 1.0D)) {
                                this.getNavigation().stop();
                                this.setSleeping(true);
                                return 0;
                            }
                        }
                    }
                }
            }
        }
        return 1;
    }

    protected void playWithNearestEnrichment() {
        int i = 16;
        int j = 8;
        BlockPos blockpos = this.blockPosition();
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(int k = 0; k <= j; k = k > 0 ? -k : 1 - k) {
            for(int l = 0; l < i; ++l) {
                for(int i1 = 0; i1 <= l; i1 = i1 > 0 ? -i1 : 1 - i1) {
                    for(int j1 = i1 < l && i1 > -l ? l : 0; j1 <= l; j1 = j1 > 0 ? -j1 : 1 - j1) {
                        blockpos$mutableblockpos.setWithOffset(blockpos, i1, k - 1, j1);
                        validTarget = this.isValidTargetEnrichment(this.level, blockpos$mutableblockpos, this);
                        if (this.isWithinRestriction(blockpos$mutableblockpos) && validTarget) {
                            this.blockPos = blockpos$mutableblockpos;
                            this.getNavigation().moveTo((double) ((float) this.blockPos.getX()) + 0.5D, this.blockPos.getY() + 1, (double) ((float) this.blockPos.getZ()) + 0.5D, 1.0D);
                            BlockPos blockPosAbove = this.blockPos.above();
                            if (blockPosAbove.closerToCenterThan(this.position(), 2.0D)) {
                                this.getNavigation().stop();
                                this.setEnrichment(this.getEnrichment() + 20);
                                this.setEnrichmentTicks(random.nextInt(400) + 2000);
                                this.setEnrichmentCooldown(2400);
                            }
                        }
                    }
                }
            }
        }
    }

    protected boolean isValidTargetEnrichment(LevelReader pLevel, BlockPos pPos, PrehistoricEntity entity) {
        if (pLevel.getBlockState(pPos).getBlock() instanceof EnrichmentBlock) {
            if (pLevel.getBlockState(pPos).getBlock() instanceof BubbleBlowerBlock && !(entity instanceof AphanerammaEntity)) {
                return false;
            }
            if (pLevel.getBlockState(pPos).getBlock() instanceof ScentDiffuserBlock && !(entity.getDiet() == 1)) {
                return false;
            } else {
                enrichment = (EnrichmentBlock) pLevel.getBlockState(pPos).getBlock();
                return true;
            }
        }
        return false;
    }

    protected boolean isValidTargetSleeping(LevelReader pLevel, BlockPos pPos) {
        if (pLevel.getBlockState(pPos).getBlock().equals(BlockInit.NEST.get())) {
            nest = pLevel.getBlockState(pPos).getBlock();
            return true;
        }
        return false;
    }

    public void setSleeping(boolean sleeping) {
        this.entityData.set(SLEEPING, sleeping);
    }

    public int getSleepSchedule() {
        return sleepSchedule;
    }

    public boolean shouldSleep() {
        if (this.isCryosick()) {
            return true;
        } else if (this.isAngry() || this.isStarving() || this.isInWater()) {
            return false;
        } else if (this.level.isDay() && getSleepSchedule() == 1) {
            return true;
        } else if (!this.level.isDay() && getSleepSchedule() == 0) {
            return true;
        } else return false;
    }

    public boolean isSleeping() {
        return this.entityData.get(SLEEPING);
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

    public void setAdultAttributes() {
        Objects.requireNonNull(this.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(this.getAdultHealth());
        this.setHealth(this.getAdultHealth());
    }

    public float getAdultHealth() {
        return adultHealth;
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
        return this.getEnrichment() < (maxEnrichment / 2);
    }

    public boolean isMoodyAt(LivingEntity livingEntity) {
        if (this.isAngry()) {
            return false;
        }
        return livingEntity.getType() == EntityType.PLAYER;
    }

    public boolean isGrumpy() {
        return this.getEnrichment() < (maxEnrichment * 0.8f);
    }

    public Component getSpecies() {
        return name;
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

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(AGE_IN_TICKS, 0);
        this.entityData.define(AGE_SCALE, 0.0F);
        this.entityData.define(GENDER, 0);
        this.entityData.define(MATING_TICKS, 240);
        this.entityData.define(HUNGER, 0);
        this.entityData.define(HUNGER_TICKS, 0);
        this.entityData.define(HEALING_TICKS, 0);
        this.entityData.define(SLEEPING, false);
        this.entityData.define(SCREM, false);
        this.entityData.define(STUNTED, false);
        this.entityData.define(REMAINING_CRYOSICKNESS_TIME, 0);
        this.entityData.define(REMAINING_ANGER_TIME, 0);
        this.entityData.define(ENRICHMENT, 0);
        this.entityData.define(ENRICHMENT_TICKS, 0);
        this.entityData.define(ENRICHMENT_COOLDOWN, 0);
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
        pCompound.putBoolean("Screm", this.isScrem());
        pCompound.putBoolean("Stunted", this.isStunted());
        pCompound.putInt("RemainingCryosicknessTime", this.getRemainingCryosicknessTime());
        this.addPersistentAngerSaveData(pCompound);
        pCompound.putInt("Enrichment", this.getEnrichment());
        pCompound.putInt("EnrichmentTicks", this.getEnrichmentTicks());
        pCompound.putInt("EnrichmentCooldown", this.getEnrichmentCooldown());
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
        this.setScrem(pCompound.getBoolean("Screm"));
        this.setStunted(pCompound.getBoolean("Stunted"));
        this.setRemainingCryosicknessTime(pCompound.getInt("RemainingCryosicknessTime"));
        this.readPersistentAngerSaveData(this.level, pCompound);
        this.setEnrichment(pCompound.getInt("Enrichment"));
        this.setEnrichmentTicks(pCompound.getInt("EnrichmentTicks"));
        this.setEnrichmentCooldown(pCompound.getInt("EnrichmentCooldown"));
    }
}
