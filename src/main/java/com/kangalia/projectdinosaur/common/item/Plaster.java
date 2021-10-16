package com.kangalia.projectdinosaur.common.item;

import com.kangalia.projectdinosaur.core.init.BlockInit;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Plaster extends Item {

    public Plaster (Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = context.getLevel();

        if (!world.isClientSide) {
            BlockState clickedBlock = world.getBlockState(context.getClickedPos());
            BlockPos pos = context.getClickedPos();

            rightClickBlock(clickedBlock, context, pos);
            world.playSound(null, pos, SoundEvents.BOOK_PAGE_TURN, SoundCategory.NEUTRAL, 2.0F, random.nextFloat() + 0.4F + 0.8F);
            stack.shrink(1 );
        }
        return ActionResultType.CONSUME;
    }

    private void rightClickBlock(BlockState clickedBlock, ItemUseContext context, BlockPos pos) {
        World world = context.getLevel();

        if (clickedBlock == BlockInit.ALPINE_ROCK_FOSSIL.get().defaultBlockState()) {
            world.setBlock(pos, BlockInit.ENCASED_ALPINE_ROCK_FOSSIL.get().defaultBlockState(), 1 );
        }
        if (clickedBlock == BlockInit.ARID_ROCK_FOSSIL.get().defaultBlockState()) {
            world.setBlock(pos, BlockInit.ENCASED_ARID_ROCK_FOSSIL.get().defaultBlockState(), 1);
        }
        if (clickedBlock == BlockInit.AQUATIC_ROCK_FOSSIL.get().defaultBlockState()) {
            world.setBlock(pos, BlockInit.ENCASED_AQUATIC_ROCK_FOSSIL.get().defaultBlockState(), 1);
        }
        if (clickedBlock == BlockInit.FROZEN_ROCK_FOSSIL.get().defaultBlockState()) {
            world.setBlock(pos, BlockInit.ENCASED_FROZEN_ROCK_FOSSIL.get().defaultBlockState(), 1);
        }
        if (clickedBlock == BlockInit.GRASSLAND_ROCK_FOSSIL.get().defaultBlockState()) {
            world.setBlock(pos, BlockInit.ENCASED_GRASSLAND_ROCK_FOSSIL.get().defaultBlockState(), 1);
        }
        if (clickedBlock == BlockInit.TEMPERATE_ROCK_FOSSIL.get().defaultBlockState()) {
            world.setBlock(pos, BlockInit.ENCASED_TEMPERATE_ROCK_FOSSIL.get().defaultBlockState(), 1);
        }
        if (clickedBlock == BlockInit.TROPICAL_ROCK_FOSSIL.get().defaultBlockState()) {
            world.setBlock(pos, BlockInit.ENCASED_TROPICAL_ROCK_FOSSIL.get().defaultBlockState(), 1);
        }
        if (clickedBlock == BlockInit.WETLAND_ROCK_FOSSIL.get().defaultBlockState()) {
            world.setBlock(pos, BlockInit.ENCASED_WETLAND_ROCK_FOSSIL.get().defaultBlockState(), 1);
        }

    }




}
