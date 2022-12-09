package com.kangalia.projectdinosaur.common.block.machines;

import com.kangalia.projectdinosaur.common.container.FossilExcavatorContainer;
import com.kangalia.projectdinosaur.common.blockentity.FossilExcavatorBlockEntity;
import com.kangalia.projectdinosaur.core.init.BlockEntitiesInit;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

public class FossilExcavatorBlock extends Block implements EntityBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public FossilExcavatorBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BlockEntitiesInit.FOSSIL_EXCAVATOR_ENTITY.get().create(pos, state);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (pLevel.isClientSide()) {
            return null;
        }
        return (lvl, pos, blockState, t) -> {
            if (t instanceof FossilExcavatorBlockEntity blockEntity) {
                blockEntity.tick();
            }
        };
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if(world.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        if(!world.isClientSide) {
            BlockEntity tileEntity = world.getBlockEntity(pos);
            if(tileEntity instanceof FossilExcavatorBlockEntity && player instanceof ServerPlayer) {

                MenuProvider containerProvider = createContainerProvider(world, pos);
                NetworkHooks.openScreen(((ServerPlayer) player), containerProvider, tileEntity.getBlockPos());
            }
            else {
                throw new IllegalStateException("Container Provider is Missing!");
            }
        }
        return InteractionResult.CONSUME;
    }

    private MenuProvider createContainerProvider(Level world, BlockPos pos) {
        return new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.translatable("screen.projectdinosaur.fossil_excavator");
            }

            @Nullable
            @Override
            public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
                return new FossilExcavatorContainer(i, world, pos, playerInventory, playerEntity);
            }
        };
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        if(state.hasBlockEntity() && state.getBlock() != newState.getBlock()) {
            world.getBlockEntity(pos).getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(h -> {
                for (int i = 0; i < h.getSlots(); i++) {
                    popResource(world, pos, h.getStackInSlot(i));
                }
            });
        }
        super.onRemove(state, world, pos, newState, isMoving);
    }



    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BlockStateProperties.FACING, BlockStateProperties.POWERED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(BlockStateProperties.FACING, context.getHorizontalDirection().getOpposite()).setValue(BlockStateProperties.POWERED, false);
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor world, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }
}
