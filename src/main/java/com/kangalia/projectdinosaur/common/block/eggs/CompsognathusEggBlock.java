package com.kangalia.projectdinosaur.common.block.eggs;

import com.kangalia.projectdinosaur.common.entity.creature.AphanerammaEntity;
import com.kangalia.projectdinosaur.common.entity.creature.CompsognathusEntity;
import com.kangalia.projectdinosaur.core.init.BlockInit;
import com.kangalia.projectdinosaur.core.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Random;

public class CompsognathusEggBlock extends Block {

    private static final VoxelShape ONE_EGG_AABB = Block.box(3.0D, 0.0D, 3.0D, 12.0D, 7.0D, 12.0D);
    private static final VoxelShape MULTIPLE_EGGS_AABB = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 7.0D, 15.0D);
    public static final IntegerProperty HATCH = BlockStateProperties.HATCH;
    public static final IntegerProperty EGGS = BlockStateProperties.EGGS;

    public CompsognathusEggBlock(BlockBehaviour.Properties p_57759_) {
        super(p_57759_);
        this.registerDefaultState(this.stateDefinition.any().setValue(HATCH, Integer.valueOf(0)).setValue(EGGS, Integer.valueOf(1)));
    }

    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        this.destroyEgg(pLevel, pState, pPos, pEntity, 100);
        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    public void fallOn(Level p_154845_, BlockState p_154846_, BlockPos p_154847_, Entity p_154848_, float p_154849_) {
        if (!(p_154848_ instanceof Zombie)) {
            this.destroyEgg(p_154845_, p_154846_, p_154847_, p_154848_, 3);
        }

        super.fallOn(p_154845_, p_154846_, p_154847_, p_154848_, p_154849_);
    }

    private void destroyEgg(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, int pChance) {
        if (this.canDestroyEgg(pLevel, pEntity)) {
            if (!pLevel.isClientSide && pLevel.random.nextInt(pChance) == 0 && pState.is(BlockInit.INCUBATED_COMPSOGNATHUS_EGG.get())) {
                this.decreaseEggs(pLevel, pPos, pState);
            }

        }
    }

    private void decreaseEggs(Level pLevel, BlockPos pPos, BlockState pState) {
        pLevel.playSound((Player)null, pPos, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + pLevel.random.nextFloat() * 0.2F);
        int i = pState.getValue(EGGS);
        if (i <= 1) {
            pLevel.destroyBlock(pPos, false);
        } else {
            pLevel.setBlock(pPos, pState.setValue(EGGS, Integer.valueOf(i - 1)), 2);
            pLevel.levelEvent(2001, pPos, Block.getId(pState));
        }

    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        Random random = new Random();
        if (this.shouldUpdateHatchLevel(pLevel) && onNest(pLevel, pPos)) {
            int i = pState.getValue(HATCH);
            if (i < 2) {
                pLevel.playSound((Player)null, pPos, SoundEvents.TURTLE_EGG_CRACK, SoundSource.BLOCKS, 0.7F, 0.9F + pRandom.nextFloat() * 0.2F);
                pLevel.setBlock(pPos, pState.setValue(HATCH, Integer.valueOf(i + 1)), 2);
            } else {
                pLevel.playSound((Player)null, pPos, SoundEvents.TURTLE_EGG_HATCH, SoundSource.BLOCKS, 0.7F, 0.9F + pRandom.nextFloat() * 0.2F);
                pLevel.removeBlock(pPos, false);

                for(int j = 0; j < pState.getValue(EGGS); ++j) {
                    pLevel.levelEvent(2001, pPos, Block.getId(pState));
                    CompsognathusEntity compsognathus = EntityInit.COMPSOGNATHUS.get().create(pLevel);
                    compsognathus.setGender(random.nextInt(2));
                    compsognathus.setAgeInTicks(0);
                    compsognathus.setMatingTicks(12000);
                    compsognathus.setHunger(compsognathus.getMaxFood() / 2);
                    compsognathus.setHungerTicks(1600);
                    compsognathus.moveTo((double)pPos.getX() + 0.3D + (double)j * 0.2D, (double)pPos.getY(), (double)pPos.getZ() + 0.3D, 0.0F, 0.0F);
                    pLevel.addFreshEntity(compsognathus);
                }
            }
        }

    }

    public static boolean onNest(BlockGetter pLevel, BlockPos pPos) {
        return isNest(pLevel, pPos.below());
    }

    public static boolean isNest(BlockGetter pReader, BlockPos pPos) {
        return pReader.getBlockState(pPos).is(BlockInit.NEST.get());
    }

    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (onNest(pLevel, pPos) && !pLevel.isClientSide) {
            pLevel.levelEvent(2005, pPos, 0);
        }

    }

    private boolean shouldUpdateHatchLevel(Level pLevel) {
        float f = pLevel.getTimeOfDay(1.0F);
        if ((double)f < 0.39D && (double)f > 0.35D) {
            return true;
        } else {

            return pLevel.random.nextInt(500) <= 100;
        }
    }

    /**
     * Called after a player has successfully harvested this block. This method will only be called if the player has
     * used the correct tool and drops should be spawned.
     */
    public void playerDestroy(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState, @Nullable BlockEntity pTe, ItemStack pStack) {
        super.playerDestroy(pLevel, pPlayer, pPos, pState, pTe, pStack);
        this.decreaseEggs(pLevel, pPos, pState);
    }

    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        return !pUseContext.isSecondaryUseActive() && pUseContext.getItemInHand().is(this.asItem()) && pState.getValue(EGGS) < 4 ? true : super.canBeReplaced(pState, pUseContext);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockState blockstate = pContext.getLevel().getBlockState(pContext.getClickedPos());
        return blockstate.is(this) ? blockstate.setValue(EGGS, Integer.valueOf(Math.min(4, blockstate.getValue(EGGS) + 1))) : super.getStateForPlacement(pContext);
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return pState.getValue(EGGS) > 1 ? MULTIPLE_EGGS_AABB : ONE_EGG_AABB;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(HATCH, EGGS);
    }

    private boolean canDestroyEgg(Level pLevel, Entity pEntity) {
        if (!(pEntity instanceof AphanerammaEntity) && !(pEntity instanceof Bat)) {
            if (!(pEntity instanceof LivingEntity)) {
                return false;
            } else {
                return pEntity instanceof Player || net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(pLevel, pEntity);
            }
        } else {
            return false;
        }
    }
}
