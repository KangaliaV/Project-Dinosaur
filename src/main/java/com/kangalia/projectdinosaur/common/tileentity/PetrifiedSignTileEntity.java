package com.kangalia.projectdinosaur.common.tileentity;

import com.kangalia.projectdinosaur.core.init.BlockEntitiesInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class PetrifiedSignTileEntity extends SignBlockEntity {
    public PetrifiedSignTileEntity(BlockPos blockPos, BlockState blockState) {
        super(blockPos, blockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return BlockEntitiesInit.PETRIFIED_SIGN_ENTITY.get();
    }
}
