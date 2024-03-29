package com.kangalia.projectdinosaur.common.entity;

import com.kangalia.projectdinosaur.core.init.EntityInit;
import com.kangalia.projectdinosaur.core.init.ItemInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class PetrifiedBoatEntity extends Boat {
    private static final EntityDataAccessor<String> WOOD_TYPE = SynchedEntityData.defineId(PetrifiedBoatEntity.class, EntityDataSerializers.STRING);

    public PetrifiedBoatEntity(EntityType<? extends Boat> entityType, Level world) {
        super(entityType, world);
        this.blocksBuilding = true;
    }

    public PetrifiedBoatEntity(Level world, double x, double y, double z, String woodType) {
        this(EntityInit.PETRIFIED_BOAT.get(), world);
        this.setPos(x, y, z);
        this.setDeltaMovement(Vec3.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.entityData.set(WOOD_TYPE, "petrified");
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(WOOD_TYPE, "petrified");
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        nbt.putString("Type", this.getWoodType());
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putString("Type", this.getWoodType());
    }

    public String getWoodType() {
        return this.entityData.get(WOOD_TYPE);
    }

    public void setWoodType(String wood) {
        this.entityData.set(WOOD_TYPE, wood);
    }

    @Override
    public @NotNull Item getDropItem() {
        return ItemInit.PETRIFIED_BOAT.get();
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return this.getDropItem().getDefaultInstance();
    }
}
