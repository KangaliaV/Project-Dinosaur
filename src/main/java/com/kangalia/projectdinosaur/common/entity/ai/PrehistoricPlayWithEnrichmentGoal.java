package com.kangalia.projectdinosaur.common.entity.ai;

import com.kangalia.projectdinosaur.common.block.enrichment.BubbleBlowerBlock;
import com.kangalia.projectdinosaur.common.block.enrichment.EnrichmentBlock;
import com.kangalia.projectdinosaur.common.block.enrichment.ScentDiffuserBlock;
import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.kangalia.projectdinosaur.common.entity.creature.AphanerammaEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;

import java.util.Random;

public class PrehistoricPlayWithEnrichmentGoal extends MoveToBlockGoal {

    PrehistoricEntity prehistoricEntity;
    EnrichmentBlock enrichment;
    Random random = new Random();

    public PrehistoricPlayWithEnrichmentGoal(PrehistoricEntity prehistoric, double pSpeedModifier, int pSearchRange) {
        super(prehistoric, pSpeedModifier, pSearchRange);
        this.prehistoricEntity = prehistoric;
    }

    @Override
    public boolean canUse() {
        if (!prehistoricEntity.isGrumpy() || !prehistoricEntity.isMoody()) {
            return false;
        } else {
            return super.canUse();
        }
    }

    @Override
    public boolean canContinueToUse() {
        if (prehistoricEntity.getEnrichment() >= prehistoricEntity.getMaxEnrichment()) {
            return false;
        } else {
            return super.canContinueToUse();
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (isReachedTarget()) {
            prehistoricEntity.setEnrichment(prehistoricEntity.getEnrichment() + 20);
            prehistoricEntity.setEnrichmentTicks(random.nextInt(400) + 2000);
            prehistoricEntity.setEnrichmentCooldown(2400);
        }
    }

    @Override
    protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
        if (pLevel.getBlockState(pPos).getBlock() instanceof EnrichmentBlock) {
            if (pLevel.getBlockState(pPos).getBlock() instanceof BubbleBlowerBlock && !(prehistoricEntity instanceof AphanerammaEntity)) {
                return false;
            }
            if (pLevel.getBlockState(pPos).getBlock() instanceof ScentDiffuserBlock && !(prehistoricEntity.getDiet() == 1)) {
                return false;
            } else {
                enrichment = (EnrichmentBlock) pLevel.getBlockState(pPos).getBlock();
                return true;
            }
        }
        return false;
    }

    @Override
    public double acceptedDistance() {
        return 2.0D;
    }
}
