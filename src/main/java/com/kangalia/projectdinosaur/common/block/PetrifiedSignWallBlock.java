package com.kangalia.projectdinosaur.common.block;

import com.kangalia.projectdinosaur.common.tileentity.PetrifiedSignTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.BlockGetter;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class PetrifiedSignWallBlock extends WallSignBlock {
    public PetrifiedSignWallBlock(Properties properties, WoodType woodType) {
        super(properties, woodType);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity (BlockPos pos, BlockState state) {
        return new PetrifiedSignTileEntity(pos, state);
    }
}
