package com.kangalia.projectdinosaur.common.item;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeSpawnEggItem;

import java.util.Optional;
import java.util.function.Supplier;

public class PrehistoricSpawnEgg extends ForgeSpawnEggItem {

    public PrehistoricSpawnEgg(Supplier<? extends EntityType<? extends Mob>> type, int backgroundColor, int highlightColor, Properties props) {
        super(type, backgroundColor, highlightColor, props);
    }

    @Override
    public Optional<Mob> spawnOffspringFromSpawnEgg(Player pPlayer, Mob pMob, EntityType<? extends Mob> pEntityType, ServerLevel pServerLevel, Vec3 pPos, ItemStack pStack) {
        if (!this.spawnsEntity(pStack.getTag(), pEntityType)) {
            return Optional.empty();
        } else {
            PrehistoricEntity mob;
            if (pMob instanceof AgeableMob) {
                mob = (PrehistoricEntity) ((PrehistoricEntity)pMob).getBreedOffspring(pServerLevel, (PrehistoricEntity)pMob);
            } else {
                mob = (PrehistoricEntity) pEntityType.create(pServerLevel);
            }

            if (mob == null) {
                return Optional.empty();
            } else {
                mob.setAgeInDays(0);
                if (!mob.isBaby()) {
                    return Optional.empty();
                } else {
                    mob.moveTo(pPos.x(), pPos.y(), pPos.z(), 0.0F, 0.0F);
                    pServerLevel.addFreshEntityWithPassengers(mob);
                    if (pStack.hasCustomHoverName()) {
                        mob.setCustomName(pStack.getHoverName());
                    }

                    if (!pPlayer.getAbilities().instabuild) {
                        pStack.shrink(1);
                    }

                    return Optional.of(mob);
                }
            }
        }
    }
}
