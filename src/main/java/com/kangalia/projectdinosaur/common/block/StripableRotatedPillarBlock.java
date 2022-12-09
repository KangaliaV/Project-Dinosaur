package com.kangalia.projectdinosaur.common.block;

import com.kangalia.projectdinosaur.core.init.BlockInit;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolAction;

import javax.annotation.Nullable;

public class StripableRotatedPillarBlock extends RotatedPillarBlock {

    public StripableRotatedPillarBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if (context.getItemInHand().getItem() instanceof AxeItem) {
            if (state.is(BlockInit.PETRIFIED_LOG.get())) {
                return BlockInit.STRIPPED_PETRIFIED_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(BlockInit.PETRIFIED_WOOD.get())) {
                return BlockInit.STRIPPED_PETRIFIED_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

            }
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
