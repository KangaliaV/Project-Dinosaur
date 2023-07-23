package com.kangalia.projectdinosaur.common.entity.parts;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.kangalia.projectdinosaur.common.entity.creature.AphanerammaEntity;
import com.kangalia.projectdinosaur.common.entity.creature.AustralovenatorEntity;
import com.kangalia.projectdinosaur.common.entity.creature.GastornisEntity;
import com.kangalia.projectdinosaur.common.entity.creature.ScelidosaurusEntity;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PrehistoricPart<T extends PrehistoricEntity> extends PartEntity<T> {

    public final PrehistoricEntity prehistoric;

    private final String part;

    public PrehistoricPart(T parent, String part) {
        super(parent);
        this.prehistoric = parent;
        this.part = part;
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
        if (this.prehistoric instanceof AustralovenatorEntity) {
            return australovenatorDimensions();
        } else if (this.prehistoric instanceof AphanerammaEntity) {
            return aphanerammaDimensions();
        } else if (this.prehistoric instanceof GastornisEntity) {
            return gastornisDimensions();
        } else if (this.prehistoric instanceof ScelidosaurusEntity) {
            return scelidosaurusDimensions();
        }
        return EntityDimensions.scalable(1.0f, 1.0f);
    }

    public EntityDimensions aphanerammaDimensions() {
        if (this.prehistoric.isBaby()) {
            switch (this.part) {
                case "body": return EntityDimensions.scalable(0.25f, 0.1f);
                case "head":
                case "tail":
                    return EntityDimensions.scalable(0.15f, 0.1f);
            }
        } else if (this.prehistoric.isChild()) {
            switch (this.part) {
                case "body": return EntityDimensions.scalable(0.4f, 0.2f);
                case "head":
                case "tail":
                    return EntityDimensions.scalable(0.3f, 0.2f);
            }
        } else if (this.prehistoric.isJuvenile()) {
            switch (this.part) {
                case "body": return EntityDimensions.scalable(0.55f, 0.3f);
                case "head":
                case "tail":
                    return EntityDimensions.scalable(0.45f, 0.3f);
            }
        } else {
            switch (this.part) {
                case "body": return EntityDimensions.scalable(0.7f, 0.4f);
                case "head":
                case "tail":
                    return EntityDimensions.scalable(0.6f, 0.4f);
            }
        }
        return EntityDimensions.scalable(1.0f, 1.0f);
    }

    public EntityDimensions australovenatorDimensions() {
        if (this.prehistoric.isBaby()) {
            switch (this.part) {
                case "head": return EntityDimensions.scalable(0.3f, 0.38f);
                case "neck": return EntityDimensions.scalable(0.4f, 0.45f);
                case "body": return EntityDimensions.scalable(0.53f, 0.58f);
                case "tail1": return EntityDimensions.scalable(0.43f, 0.4f);
                case "tail2": return EntityDimensions.scalable(0.4f, 0.33f);
            }
        } else if (this.prehistoric.isChild()) {
            switch (this.part) {
                case "head": return EntityDimensions.scalable(0.65f, 0.65f);
                case "neck": return EntityDimensions.scalable(0.7f, 0.9f);
                case "body": return EntityDimensions.scalable(0.95f, 0.85f);
                case "tail1": return EntityDimensions.scalable(0.85f, 0.7f);
                case "tail2": return EntityDimensions.scalable(0.7f, 0.55f);
            }
        } else if (this.prehistoric.isJuvenile()) {
            switch (this.part) {
                case "head": return EntityDimensions.scalable(0.85f, 0.85f);
                case "neck": return EntityDimensions.scalable(0.9f, 1.1f);
                case "body": return EntityDimensions.scalable(1.15f, 1.15f);
                case "tail1": return EntityDimensions.scalable(1.05f, 0.9f);
                case "tail2": return EntityDimensions.scalable(0.9f, 0.75f);
            }
        } else {
            switch (this.part) {
                case "head": return EntityDimensions.scalable(1.1f, 1.1f);
                case "neck": return EntityDimensions.scalable(1.2f, 1.6f);
                case "body": return EntityDimensions.scalable(1.7f, 1.9f);
                case "tail1": return EntityDimensions.scalable(1.5f, 1.2f);
                case "tail2": return EntityDimensions.scalable(1.2f, 0.9f);
            }
        }
        return EntityDimensions.scalable(1.0f, 1.0f);
    }

    public EntityDimensions gastornisDimensions() {
        if (this.prehistoric.isBaby()) {
            switch (this.part) {
                case "head": return EntityDimensions.scalable(0.2f, 0.2f);
                case "neck": return EntityDimensions.scalable(0.18f, 0.3f);
                case "body": return EntityDimensions.scalable(0.27f, 0.4f);
                case "tail": return EntityDimensions.scalable(0.22f, 0.22f);
            }
        } else if (this.prehistoric.isChild()) {
            switch (this.part) {
                case "head": return EntityDimensions.scalable(0.4f, 0.4f);
                case "neck": return EntityDimensions.scalable(0.38f, 0.5f);
                case "body": return EntityDimensions.scalable(0.47f, 0.65f);
                case "tail": return EntityDimensions.scalable(0.42f, 0.42f);
            }
        } else if (this.prehistoric.isJuvenile()) {
            switch (this.part) {
                case "head": return EntityDimensions.scalable(0.6f, 0.8f);
                case "neck": return EntityDimensions.scalable(0.58f, 0.9f);
                case "body": return EntityDimensions.scalable(0.67f, 1.15f);
                case "tail": return EntityDimensions.scalable(0.62f, 0.62f);
            }
        } else {
            switch (this.part) {
                case "head": return EntityDimensions.scalable(0.7f, 0.7f);
                case "neck": return EntityDimensions.scalable(0.7f, 0.9f);
                case "body": return EntityDimensions.scalable(0.8f, 1.5f);
                case "tail": return EntityDimensions.scalable(0.8f, 0.9f);
            }
        }
        return EntityDimensions.scalable(1.0f, 1.0f);
    }

    public EntityDimensions scelidosaurusDimensions() {
        if (this.prehistoric.isBaby()) {
            switch (this.part) {
                case "head":
                case "tail3":
                    return EntityDimensions.scalable(0.15f, 0.15f);
                case "neck":
                case "tail1":
                    return EntityDimensions.scalable(0.25f, 0.25f);
                case "body": return EntityDimensions.scalable(0.25f, 0.4f);
                case "tail2": return EntityDimensions.scalable(0.2f, 0.2f);
            }
        } else if (this.prehistoric.isChild()) {
            switch (this.part) {
                case "head":
                case "tail3":
                    return EntityDimensions.scalable(0.25f, 0.25f);
                case "neck":
                case "tail1":
                    return EntityDimensions.scalable(0.35f, 0.35f);
                case "body": return EntityDimensions.scalable(0.35f, 0.65f);
                case "tail2": return EntityDimensions.scalable(0.3f, 0.3f);
            }
        } else if (this.prehistoric.isJuvenile()) {
            switch (this.part) {
                case "head":
                case "tail3":
                    return EntityDimensions.scalable(0.3f, 0.3f);
                case "neck":
                case "tail1":
                    return EntityDimensions.scalable(0.55f, 0.55f);
                case "body": return EntityDimensions.scalable(0.75f, 0.95f);
                case "tail2": return EntityDimensions.scalable(0.4f, 0.4f);
            }
        } else {
            switch (this.part) {
                case "head":
                case "tail3":
                    return EntityDimensions.scalable(0.4f, 0.4f);
                case "neck":
                case "tail1":
                    return EntityDimensions.scalable(0.7f, 0.7f);
                case "body": return EntityDimensions.scalable(0.9f, 1.2f);
                case "tail2": return EntityDimensions.scalable(0.55f, 0.55f);
            }
        }
        return EntityDimensions.scalable(1.0f, 1.0f);
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
        this.collideWithOthers();
        this.refreshDimensions();
        super.tick();
    }

    private void collideWithOthers() {
        List<Entity> list = this.level().getEntities(this, this.getBoundingBox().inflate(0.2D, 0.0D, 0.2D));

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
