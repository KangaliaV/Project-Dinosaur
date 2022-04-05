package com.kangalia.projectdinosaur.common.block;

import com.kangalia.projectdinosaur.common.tileentity.PetrifiedSignTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.StandingSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class PetrifiedSignStandingBlock extends StandingSignBlock {
    public PetrifiedSignStandingBlock(Properties properties, WoodType woodType) {
        super(properties, woodType);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new PetrifiedSignTileEntity();
    }
}
