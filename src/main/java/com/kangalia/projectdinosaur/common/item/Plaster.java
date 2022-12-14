package com.kangalia.projectdinosaur.common.item;

import com.kangalia.projectdinosaur.core.init.BlockInit;
import net.minecraft.world.level.block.Block;
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
                if (context.getPlayer() != null && !context.getPlayer().isCreative()) {
                    stack.shrink(1);
                }
            }
        }
        return InteractionResult.PASS;
    }

    private boolean rightClickBlock(BlockState clickedBlock, UseOnContext context, BlockPos pos) {
        Level world = context.getLevel();
        if (!world.isClientSide) {
            if (clickedBlock == BlockInit.QUATERNARY_FOSSIL.get().defaultBlockState()) {
                world.setBlock(pos, BlockInit.QUATERNARY_FOSSIL_ENCASED.get().defaultBlockState(), Block.UPDATE_ALL);
                return true;
            }
            if (clickedBlock == BlockInit.NEOGENE_FOSSIL.get().defaultBlockState()) {
                world.setBlock(pos, BlockInit.NEOGENE_FOSSIL_ENCASED.get().defaultBlockState(), Block.UPDATE_ALL);
                return true;
            }
            if (clickedBlock == BlockInit.PALEOGENE_FOSSIL.get().defaultBlockState()) {
                world.setBlock(pos, BlockInit.PALEOGENE_FOSSIL_ENCASED.get().defaultBlockState(), Block.UPDATE_ALL);
                return true;
            }
            if (clickedBlock == BlockInit.CRETACEOUS_FOSSIL.get().defaultBlockState()) {
                world.setBlock(pos, BlockInit.CRETACEOUS_FOSSIL_ENCASED.get().defaultBlockState(), Block.UPDATE_ALL);
                return true;
            }
            if (clickedBlock == BlockInit.JURASSIC_FOSSIL.get().defaultBlockState()) {
                world.setBlock(pos, BlockInit.JURASSIC_FOSSIL_ENCASED.get().defaultBlockState(), Block.UPDATE_ALL);
                return true;
            }
            if (clickedBlock == BlockInit.TRIASSIC_FOSSIL.get().defaultBlockState()) {
                world.setBlock(pos, BlockInit.TRIASSIC_FOSSIL_ENCASED.get().defaultBlockState(), Block.UPDATE_ALL);
                return true;
            }
            if (clickedBlock == BlockInit.PERMIAN_FOSSIL.get().defaultBlockState()) {
                world.setBlock(pos, BlockInit.PERMIAN_FOSSIL_ENCASED.get().defaultBlockState(), Block.UPDATE_ALL);
                return true;
            }
            if (clickedBlock == BlockInit.CARBONIFEROUS_FOSSIL.get().defaultBlockState()) {
                world.setBlock(pos, BlockInit.CARBONIFEROUS_FOSSIL_ENCASED.get().defaultBlockState(), Block.UPDATE_ALL);
                return true;
            }
            if (clickedBlock == BlockInit.DEVONIAN_FOSSIL.get().defaultBlockState()) {
                world.setBlock(pos, BlockInit.DEVONIAN_FOSSIL_ENCASED.get().defaultBlockState(), Block.UPDATE_ALL);
                return true;
            }
            if (clickedBlock == BlockInit.SILURIAN_FOSSIL.get().defaultBlockState()) {
                world.setBlock(pos, BlockInit.SILURIAN_FOSSIL_ENCASED.get().defaultBlockState(), Block.UPDATE_ALL);
                return true;
            }
            if (clickedBlock == BlockInit.ORDOVICIAN_FOSSIL.get().defaultBlockState()) {
                world.setBlock(pos, BlockInit.ORDOVICIAN_FOSSIL_ENCASED.get().defaultBlockState(), Block.UPDATE_ALL);
                return true;
            }
            if (clickedBlock == BlockInit.CAMBRIAN_FOSSIL.get().defaultBlockState()) {
                world.setBlock(pos, BlockInit.CAMBRIAN_FOSSIL_ENCASED.get().defaultBlockState(), Block.UPDATE_ALL);
                return true;
            }
        }
        return false;
    }




}
