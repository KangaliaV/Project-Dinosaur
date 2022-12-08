package com.kangalia.projectdinosaur.common.block.enrichment;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;

public class ScratchingLogBlock extends EnrichmentBlock {
    private static final VoxelShape SHAPE = Block.box(5, 0, 5, 11, 16, 11);

    public ScratchingLogBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @SuppressWarnings( "deprecation" )
    @Nonnull
    @Override
    public VoxelShape getShape(@Nonnull BlockState pState, @Nonnull BlockGetter pLevel, @Nonnull BlockPos pPos, @Nonnull CollisionContext pContext) {
        return SHAPE;
    }
}
