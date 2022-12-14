package com.kangalia.projectdinosaur.common.block.eggs;

import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class LandSpawnBlock extends Block {

    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 6.0D, 14.0D);
    private static int minHatchTickDelay = 3600;
    private static int maxHatchTickDelay = 12000;
    private static int minYoung = 1;
    private static int maxYoung = 4;
    private final Supplier<? extends EntityType<? extends PrehistoricEntity>> dino;

    public LandSpawnBlock(Properties pProperties, Supplier<? extends EntityType<? extends PrehistoricEntity>> entity) {
        super(pProperties);
        dino = entity;
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return mayPlaceOn(pLevel, pPos.below());
    }

    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        pLevel.scheduleTick(pPos, this, getHatchDelay(pLevel.getRandom()));
    }

    private static int getHatchDelay(RandomSource pRandom) {
        return pRandom.nextInt(minHatchTickDelay, maxHatchTickDelay);
    }

    /**
     * Update the provided state given the provided neighbor direction and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific direction passed in.
     */
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        return !this.canSurvive(pState, pLevel, pCurrentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pDirection, pNeighborState, pLevel, pCurrentPos, pNeighborPos);
    }

    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!this.canSurvive(pState, pLevel, pPos)) {
            this.destroyBlock(pLevel, pPos);
        } else {
            this.hatch(pLevel, pPos, pRandom);
        }
    }

    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pEntity.getType().equals(EntityType.FALLING_BLOCK)) {
            this.destroyBlock(pLevel, pPos);
        }
    }

    private static boolean mayPlaceOn(BlockGetter pLevel, BlockPos pPos) {
        Block block = pLevel.getBlockState(pPos).getBlock();
        if (block == Blocks.DIRT) {
            return true;
        } else if (block == Blocks.COARSE_DIRT) {
            return true;
        } else if (block == Blocks.GRASS_BLOCK) {
            return true;
        } else if (block == Blocks.ROOTED_DIRT) {
            return true;
        } else if (block == Blocks.PODZOL) {
            return true;
        } else {
            return false;
        }
    }

    private void hatch(ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        this.destroyBlock(pLevel, pPos);
        pLevel.playSound((Player)null, pPos, SoundEvents.FROGSPAWN_HATCH, SoundSource.BLOCKS, 1.0F, 1.0F);
        this.spawnYoung(pLevel, pPos, pRandom);
    }

    private void destroyBlock(Level pLevel, BlockPos pPos) {
        pLevel.destroyBlock(pPos, false);
    }

    private void spawnYoung(ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        int i = pRandom.nextInt(minYoung, maxYoung);

        for(int j = 1; j <= i; ++j) {
            PrehistoricEntity young = dino.get().create(pLevel);
            if (young != null) {
                young.setGender(pRandom.nextInt(2));
                young.setAgeInTicks(0);
                young.setMatingTicks(12000);
                young.setHunger(young.getMaxFood() / 2);
                young.setHungerTicks(1600);
                young.setEnrichment(young.getMaxEnrichment() / 2);
                young.moveTo((double)pPos.getX() + 0.3D + (double)j * 0.2D, (double)pPos.getY(), (double)pPos.getZ() + 0.3D, 0.0F, 0.0F);
                young.setPersistenceRequired();
                pLevel.addFreshEntity(young);
            }
        }

    }
}