package com.kangalia.projectdinosaur.common.item;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.kangalia.projectdinosaur.common.entity.creature.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeSpawnEggItem;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

public class PrehistoricSpawnEgg extends ForgeSpawnEggItem {

    public PrehistoricSpawnEgg(Supplier<? extends EntityType<? extends Mob>> type, int backgroundColor, int highlightColor, Properties props) {
        super(type, backgroundColor, highlightColor, props);
    }

    @Override
    public Optional<Mob> spawnOffspringFromSpawnEgg(Player pPlayer, Mob pMob, EntityType<? extends Mob> pEntityType, ServerLevel pServerLevel, Vec3 pPos, ItemStack pStack) {
        Random random = new Random();
        System.out.println("pStack.getTag(): "+pStack.getTag()+" | pEntityType: "+pEntityType);
        System.out.println("spawnsEntity: "+this.spawnsEntity(pStack.getTag(), pEntityType));
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
                if (mob instanceof AustralovenatorEntity australovenator) {
                    australovenator.setGenes(australovenator.generateGenes(true));
                } else if (mob instanceof GastornisEntity gastornis) {
                    gastornis.setGenes(gastornis.generateGenes(true));
                } else if (mob instanceof AphanerammaEntity aphaneramma) {
                    aphaneramma.setGenes(aphaneramma.generateGenes(true));
                } else if (mob instanceof TrilobiteEntity trilobite) {
                    trilobite.setGenes(trilobite.generateGenes(true));
                } else if (mob instanceof ScelidosaurusEntity scelidosaurus) {
                    scelidosaurus.setGenes(scelidosaurus.generateGenes(true));
                } else if (mob instanceof MeganeuraEntity meganeura) {
                    meganeura.setGenes(meganeura.generateGenes(true));
                }
                mob.setAgeInTicks(0);
                mob.setGender(random.nextInt(2));
                mob.setHunger(mob.getMaxFood());
                mob.setHungerTicks(1600);
                mob.setMatingTicks(12000);
                mob.setEnrichment(mob.getMaxEnrichment());
                mob.setAttributes(0);
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
