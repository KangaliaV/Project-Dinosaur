package com.kangalia.projectdinosaur.common.entity.parts;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.kangalia.projectdinosaur.core.init.ItemInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.entity.PartEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class PrehistoricPart<T extends PrehistoricEntity> extends PartEntity<T> {
    public final PrehistoricEntity prehistoric;

    private final EntityDimensions activeSize;
    private final EntityDimensions unactiveSize = EntityDimensions.scalable(0.0f, 0.0f);
    private final String age;
    private boolean active;

    public PrehistoricPart(T parent, float width, float height, String age) {
        super(parent);
        this.prehistoric = parent;
        this.activeSize = EntityDimensions.scalable(width, height);
        this.age = age;
        this.refreshDimensions();
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        return !this.isInvulnerableTo(source) && this.prehistoric.hurt(this, source, amount);
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        return getParent().interact(player, hand);
    }

    @Override
    public boolean is(Entity entityIn) {
        return this == entityIn || this.prehistoric == entityIn;
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        if (this.age != null) {
            boolean a = this.prehistoric.isBaby() && this.age.equals("baby");
            boolean b = this.prehistoric.isJuvenile() && this.age.equals("juvi");
            boolean c = this.prehistoric.isAdult() && this.age.equals("adult");
            if (a || b || c) {
                this.active = true;
                return this.activeSize;
            } else {
                this.active = false;
                return this.unactiveSize;
            }
        } else {
            return this.unactiveSize;
        }
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Nullable
    public ItemStack getPickResult() {
        return this.prehistoric.getPickResult();
    }

    @Override
    public boolean shouldBeSaved() {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public void tick() {
        xOld = getX();
        yOld = getY();
        zOld = getZ();
        yRotO = getYRot();
        xRotO = getXRot();
        if (this.active) {
            this.collideWithOthers();
        }
        super.tick();
    }

    private void collideWithOthers() {
        List<Entity> list = this.getLevel().getEntities(this, this.getBoundingBox().inflate(0.2D, 0.0D, 0.2D));

        for (Entity entity : list) {
            if (entity.isPushable()) {
                this.collideWithEntity(entity);
            }
        }
    }

    private void collideWithEntity(Entity entity) {
        entity.push(this);
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {

    }
}
