package com.kangalia.projectdinosaur.common.block.enrichment;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;
import java.util.Random;

public class BubbleBlowerBlock extends EnrichmentBlock {
    private static final VoxelShape SHAPE = Block.box(3, 0, 3, 13, 2, 13);

    public BubbleBlowerBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @SuppressWarnings( "deprecation" )
    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull BlockState pState, @Nonnull BlockGetter pLevel, @Nonnull BlockPos pPos, @Nonnull CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public void animateTick(@Nonnull BlockState pState, @Nonnull Level pLevel, BlockPos pPos, @Nonnull Random pRandom) {
        double d0 = pPos.getX();
        double d1 = pPos.getY();
        double d2 = pPos.getZ();
        super.animateTick(pState, pLevel, pPos, pRandom);
        pLevel.addAlwaysVisibleParticle(ParticleTypes.BUBBLE_COLUMN_UP, d0 + 0.5D, d1, d2 + 0.5D, 0.0D, 0.04D, 0.0D);
        pLevel.addAlwaysVisibleParticle(ParticleTypes.BUBBLE_COLUMN_UP, d0 + (double)pRandom.nextFloat(), d1 + (double)pRandom.nextFloat(), d2 + (double)pRandom.nextFloat(), 0.0D, 0.04D, 0.0D);
        if (pRandom.nextInt(200) == 0) {
            pLevel.playLocalSound(d0, d1, d2, SoundEvents.BUBBLE_COLUMN_UPWARDS_AMBIENT, SoundSource.BLOCKS, 0.2F + pRandom.nextFloat() * 0.2F, 0.9F + pRandom.nextFloat() * 0.15F, false);
        }
    }
}
