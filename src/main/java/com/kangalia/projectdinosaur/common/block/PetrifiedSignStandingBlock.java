package com.kangalia.projectdinosaur.common.block;

import com.kangalia.projectdinosaur.common.blockentity.PetrifiedSignTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.block.entity.BlockEntity;

import javax.annotation.Nullable;

public class PetrifiedSignStandingBlock extends StandingSignBlock {
    public PetrifiedSignStandingBlock(Properties properties, WoodType woodType) {
        super(properties, woodType);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity (BlockPos pos, BlockState state) {
        return new PetrifiedSignTileEntity(pos, state);
    }
}
