package com.kangalia.projectdinosaur.common.blocktypes;

import com.kangalia.projectdinosaur.core.init.BlockInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class StripableRotatedPillarBlock extends RotatedPillarBlock {

    public StripableRotatedPillarBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType) {
        if (stack.getItem() instanceof AxeItem) {
            if (state.is(BlockInit.PETRIFIED_LOG.get())) {
                return BlockInit.STRIPPED_PETRIFIED_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(BlockInit.PETRIFIED_WOOD.get())) {
                return BlockInit.STRIPPED_PETRIFIED_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

            }
        }
        return super.getToolModifiedState(state, world, pos, player, stack, toolType);
    }
}
