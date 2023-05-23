package com.kangalia.projectdinosaur.common.entity.parts;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
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
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.entity.PartEntity;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class PrehistoricPart<T extends PrehistoricEntity> extends PartEntity<T> {
    public final PrehistoricEntity prehistoric;

    private EntityDimensions size;
    private final EntityDimensions defaultSize;
    private final String age;
    private boolean active;

    public PrehistoricPart(T parent, float width, float height, String age) {
        super(parent);
        this.prehistoric = parent;
        this.size = EntityDimensions.scalable(width, height);
        this.defaultSize = EntityDimensions.scalable(width, height);
        this.age = age;
        this.refreshDimensions();
    }

    @Override
    public boolean isPickable() {
        return true;
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
        boolean flag = false;
        if (this.age != null) {
            boolean a = this.prehistoric.isBaby() && this.age.equals("baby");
            boolean b = this.prehistoric.isJuvenile() && this.age.equals("juvi");
            boolean c = this.prehistoric.isAdult() && this.age.equals("adult");
            System.out.println("Baby: " + a);
            System.out.println("Juvi: " + b);
            System.out.println("Adult: " + c);
            if (a || b || c) {
                flag = true;
            }
            System.out.println("Flag: "+flag);
            if (flag) {
                this.size = defaultSize;
                this.active = true;
            } else {
                this.size = EntityDimensions.scalable(0.0f, 0.0f);
                this.active = false;
            }
        }
        this.setInvisible(false);
        System.out.println("Size: "+size);
        return this.size;
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
    public boolean canBeCollidedWith() {
        return true;
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
