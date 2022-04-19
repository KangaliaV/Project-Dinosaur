package com.kangalia.projectdinosaur.common.item;

import com.kangalia.projectdinosaur.core.init.BlockInit;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResult;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class Plaster extends Item {

    public Plaster (Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        Level world = context.getLevel();

        if (!world.isClientSide) {
            BlockState clickedBlock = world.getBlockState(context.getClickedPos());
            BlockPos pos = context.getClickedPos();
            boolean success = rightClickBlock(clickedBlock, context, pos);

            if (success) {
                world.playSound(null, pos, SoundEvents.BOOK_PAGE_TURN, SoundSource.NEUTRAL, 2.0F, world.random.nextFloat() + 0.4F + 0.8F);
                stack.shrink(1 );
            }
        }
        return InteractionResult.CONSUME;
    }

    private boolean rightClickBlock(BlockState clickedBlock, UseOnContext context, BlockPos pos) {
        Level world = context.getLevel();

        if (clickedBlock == BlockInit.ALPINE_ROCK_FOSSIL.get().defaultBlockState()) {
            world.setBlock(pos, BlockInit.ENCASED_ALPINE_ROCK_FOSSIL.get().defaultBlockState(), 1 );
            return true;
        }
        if (clickedBlock == BlockInit.ARID_ROCK_FOSSIL.get().defaultBlockState()) {
            world.setBlock(pos, BlockInit.ENCASED_ARID_ROCK_FOSSIL.get().defaultBlockState(), 1);
            return true;
        }
        if (clickedBlock == BlockInit.AQUATIC_ROCK_FOSSIL.get().defaultBlockState()) {
            world.setBlock(pos, BlockInit.ENCASED_AQUATIC_ROCK_FOSSIL.get().defaultBlockState(), 1);
            return true;
        }
        if (clickedBlock == BlockInit.FROZEN_ROCK_FOSSIL.get().defaultBlockState()) {
            world.setBlock(pos, BlockInit.ENCASED_FROZEN_ROCK_FOSSIL.get().defaultBlockState(), 1);
            return true;
        }
        if (clickedBlock == BlockInit.GRASSLAND_ROCK_FOSSIL.get().defaultBlockState()) {
            world.setBlock(pos, BlockInit.ENCASED_GRASSLAND_ROCK_FOSSIL.get().defaultBlockState(), 1);
            return true;
        }
        if (clickedBlock == BlockInit.TEMPERATE_ROCK_FOSSIL.get().defaultBlockState()) {
            world.setBlock(pos, BlockInit.ENCASED_TEMPERATE_ROCK_FOSSIL.get().defaultBlockState(), 1);
            return true;
        }
        if (clickedBlock == BlockInit.TROPICAL_ROCK_FOSSIL.get().defaultBlockState()) {
            world.setBlock(pos, BlockInit.ENCASED_TROPICAL_ROCK_FOSSIL.get().defaultBlockState(), 1);
            return true;
        }
        if (clickedBlock == BlockInit.WETLAND_ROCK_FOSSIL.get().defaultBlockState()) {
            world.setBlock(pos, BlockInit.ENCASED_WETLAND_ROCK_FOSSIL.get().defaultBlockState(), 1);
            return true;
        }
        if (clickedBlock == BlockInit.ALPINE_CRYSTALLISED_FOSSIL.get().defaultBlockState()) {
            world.setBlock(pos, BlockInit.ENCASED_ALPINE_CRYSTALLISED_FOSSIL.get().defaultBlockState(), 1 );
            return true;
        }
        if (clickedBlock == BlockInit.ARID_CRYSTALLISED_FOSSIL.get().defaultBlockState()) {
            world.setBlock(pos, BlockInit.ENCASED_ARID_CRYSTALLISED_FOSSIL.get().defaultBlockState(), 1);
            return true;
        }
        if (clickedBlock == BlockInit.AQUATIC_CRYSTALLISED_FOSSIL.get().defaultBlockState()) {
            world.setBlock(pos, BlockInit.ENCASED_AQUATIC_CRYSTALLISED_FOSSIL.get().defaultBlockState(), 1);
            return true;
        }
        if (clickedBlock == BlockInit.FROZEN_CRYSTALLISED_FOSSIL.get().defaultBlockState()) {
            world.setBlock(pos, BlockInit.ENCASED_FROZEN_CRYSTALLISED_FOSSIL.get().defaultBlockState(), 1);
            return true;
        }
        if (clickedBlock == BlockInit.GRASSLAND_CRYSTALLISED_FOSSIL.get().defaultBlockState()) {
            world.setBlock(pos, BlockInit.ENCASED_GRASSLAND_CRYSTALLISED_FOSSIL.get().defaultBlockState(), 1);
            return true;
        }
        if (clickedBlock == BlockInit.TEMPERATE_CRYSTALLISED_FOSSIL.get().defaultBlockState()) {
            world.setBlock(pos, BlockInit.ENCASED_TEMPERATE_CRYSTALLISED_FOSSIL.get().defaultBlockState(), 1);
            return true;
        }
        if (clickedBlock == BlockInit.TROPICAL_CRYSTALLISED_FOSSIL.get().defaultBlockState()) {
            world.setBlock(pos, BlockInit.ENCASED_TROPICAL_CRYSTALLISED_FOSSIL.get().defaultBlockState(), 1);
            return true;
        }
        if (clickedBlock == BlockInit.WETLAND_CRYSTALLISED_FOSSIL.get().defaultBlockState()) {
            world.setBlock(pos, BlockInit.ENCASED_WETLAND_CRYSTALLISED_FOSSIL.get().defaultBlockState(), 1);
            return true;
        }
        return false;

    }




}
