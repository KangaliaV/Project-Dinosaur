package com.kangalia.projectdinosaur.common.block.eggs;

import com.kangalia.projectdinosaur.common.blockentity.eggs.AustralovenatorEggBlockEntity;
import com.kangalia.projectdinosaur.common.entity.PrehistoricEntity;
import com.kangalia.projectdinosaur.common.entity.creature.AustralovenatorEntity;
import com.kangalia.projectdinosaur.core.init.BlockEntitiesInit;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.LootContext;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class AustralovenatorEggBlock extends PrehistoricEggBlock {

    public AustralovenatorEggBlock(Properties pProperties, Supplier<? extends EntityType<? extends PrehistoricEntity>> entity, int eggs) {
        super(pProperties, entity, eggs);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (shouldHatch(pLevel) && onNest(pLevel, pPos)) {
            BlockEntity eggEntity = pLevel.getBlockEntity(pPos);

            for (int j = 0; j < pState.getValue(EGGS); ++j) {
                pLevel.levelEvent(2001, pPos, Block.getId(pState));
                PrehistoricEntity young = dino.get().create(pLevel);
                if (young instanceof AustralovenatorEntity) {
                    String parent1Genes = getParent1GenesFromBlockEntity(eggEntity);
                    String parent2Genes = getParent2GenesFromBlockEntity(eggEntity);
                    if (Objects.equals(parent1Genes, "") || Objects.equals(parent2Genes, "") || parent1Genes == null || parent2Genes == null) {
                        ((AustralovenatorEntity) young).setGenes(((AustralovenatorEntity) young).generateGenes());
                    } else {
                        ((AustralovenatorEntity) young).setGenes(((AustralovenatorEntity) young).inheritGenes(parent1Genes, parent2Genes));
                    }
                    young.setGender(pRandom.nextInt(2));
                    young.setAgeInTicks(0);
                    young.setMatingTicks(12000);
                    young.setHunger(young.getMaxFood() / 2);
                    young.setHungerTicks(1600);
                    young.setEnrichment(young.getMaxEnrichment() / 2);
                    young.setAttributes(false);
                    young.moveTo((double) pPos.getX() + 0.3D + (double) j * 0.2D, (double) pPos.getY(), (double) pPos.getZ() + 0.3D, 0.0F, 0.0F);
                    pLevel.addFreshEntity(young);
                    System.out.println(((AustralovenatorEntity) young).getGenes());
                }
            }
            pLevel.playSound((Player) null, pPos, SoundEvents.TURTLE_EGG_HATCH, SoundSource.BLOCKS, 0.7F, 0.9F + pRandom.nextFloat() * 0.2F);
            pLevel.removeBlock(pPos, false);
        }
    }

    @Override
    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        ItemStack eggItem = pUseContext.getItemInHand();
        CompoundTag tag = BlockItem.getBlockEntityData(eggItem);
        String parent1 = "";
        String parent2 = "";
        if (tag != null) {
            parent1 = tag.getCompound("parentGenome").getString("parent1");
            parent2 = tag.getCompound("parentGenome").getString("parent2");
        }
        BlockEntity entity = pUseContext.getLevel().getBlockEntity(pUseContext.getClickedPos());
        boolean flag1 = false;
        boolean flag2 = false;
        String parent1Entity = "";
        String parent2Entity = "";
        if (entity instanceof AustralovenatorEggBlockEntity eggEntity) {
            parent1Entity = eggEntity.getParent1();
            parent2Entity = eggEntity.getParent2();
            flag1 = parent1.equals(parent1Entity);
            flag2 = parent2.equals(parent2Entity);
        }
        if (!flag1) {
            return false;
        }
        if (!flag2) {
            return false;
        }
        return !pUseContext.isSecondaryUseActive() && eggItem.is(this.asItem()) && flag1 && flag2 && pState.getValue(EGGS) < maxEggs ? true : super.canBeReplaced(pState, pUseContext);
    }

    public String getParent1GenesFromBlockEntity(BlockEntity entity) {
        if (entity instanceof AustralovenatorEggBlockEntity) {
            return ((AustralovenatorEggBlockEntity) entity).getParent1();
        } else {
            return "";
        }
    }

    public String getParent2GenesFromBlockEntity(BlockEntity entity) {
        if (entity instanceof AustralovenatorEggBlockEntity) {
            return ((AustralovenatorEggBlockEntity) entity).getParent2();
        } else {
            return "";
        }
    }

    @Override
    public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (!pLevel.isClientSide && !pPlayer.isCreative()) {
            if (blockentity instanceof AustralovenatorEggBlockEntity eggBlockEntity) {
                ItemStack itemstack = new ItemStack(this);
                CompoundTag compoundtag = new CompoundTag();
                compoundtag.put("parentGenome", eggBlockEntity.writeParents());
                BlockItem.setBlockEntityData(itemstack, BlockEntitiesInit.AUSTRALOVENATOR_EGG_ENTITY.get(), compoundtag);
                ItemEntity itementity = new ItemEntity(pLevel, (double) pPos.getX() + 0.5D, (double) pPos.getY() + 0.5D, (double) pPos.getZ() + 0.5D, itemstack);
                itementity.setDefaultPickUpDelay();
                pLevel.addFreshEntity(itementity);

            }
        }
        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @org.jetbrains.annotations.Nullable LivingEntity pPlacer, ItemStack pStack) {
        BlockEntity entity = pLevel.getBlockEntity(pPos);
        if (!pLevel.isClientSide) {
            if (entity instanceof AustralovenatorEggBlockEntity eggEntity) {
                CompoundTag tag = BlockItem.getBlockEntityData(pStack);
                if (tag != null) {
                    String parent1 = tag.getCompound("parentGenome").getString("parent1");
                    String parent2 = tag.getCompound("parentGenome").getString("parent2");
                    eggEntity.readParents(parent1, parent2);
                } else {
                    eggEntity.readParents("", "");
                }
            }
        }
    }

    /**
     * Called after a player has successfully harvested this block. This method will only be called if the player has
     * used the correct tool and drops should be spawned.
     */
    public void playerDestroy(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState, @Nullable BlockEntity pTe, ItemStack pStack) {
        String parent1Old = this.getParent1DataFromBlockEntity(pLevel, pPos, pTe);
        String parent2Old = this.getParent2DataFromBlockEntity(pLevel, pPos, pTe);
        super.playerDestroy(pLevel, pPlayer, pPos, pState, pTe, pStack);
        this.playerDecreaseEggs(pLevel, pPos, pState, parent1Old, parent2Old);
    }

    public String getParent1DataFromBlockEntity(Level level, BlockPos pos, BlockEntity entity) {
        //BlockEntity entity = level.getBlockEntity(pos);
        String parent1 = "";
        String parent2 = "";
        if (entity instanceof AustralovenatorEggBlockEntity eggEntity) {
            parent1 = eggEntity.getParent1();
        }
        return parent1;
    }

    public String getParent2DataFromBlockEntity(Level level, BlockPos pos, BlockEntity entity) {
        //BlockEntity entity = level.getBlockEntity(pos);
        String parent2 = "";
        if (entity instanceof AustralovenatorEggBlockEntity eggEntity) {
            parent2 = eggEntity.getParent2();
        }
        return parent2;
    }

    public void playerDecreaseEggs(Level pLevel, BlockPos pPos, BlockState pState, String parent1, String parent2) {
        pLevel.playSound((Player)null, pPos, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + pLevel.random.nextFloat() * 0.2F);
        int i = pState.getValue(EGGS);
        if (i <= 1) {
            pLevel.destroyBlock(pPos, false);
        } else {
            pLevel.setBlock(pPos, pState.setValue(EGGS, Integer.valueOf(i - 1)), 2);
            BlockEntity entity1 = pLevel.getBlockEntity(pPos);
            if (entity1 instanceof AustralovenatorEggBlockEntity eggEntity1) {
                eggEntity1.readParents(parent1, parent2);
            }
            pLevel.gameEvent(GameEvent.BLOCK_DESTROY, pPos, GameEvent.Context.of(pState));
            pLevel.levelEvent(2001, pPos, Block.getId(pState));
        }
    }

    @Override
    public List<ItemStack> getDrops(BlockState pState, LootContext.Builder pBuilder) {
        return Collections.emptyList();
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        CompoundTag compoundtag = BlockItem.getBlockEntityData(pStack);
        if (compoundtag != null) {
            if (compoundtag.contains("parentGenome")) {
                String parent1 = compoundtag.getCompound("parentGenome").getString("parent1");
                String parent2 = compoundtag.getCompound("parentGenome").getString("parent2");
                if (parent1.equals("")) {
                    pTooltip.add(Component.translatable("genome.projectdinosaur.parent1", "???").withStyle(ChatFormatting.GRAY));
                } else {
                    pTooltip.add(Component.translatable("genome.projectdinosaur.parent1", parent1).withStyle(ChatFormatting.GRAY));
                }
                if (parent2.equals("")) {
                    pTooltip.add(Component.translatable("genome.projectdinosaur.parent2", "???").withStyle(ChatFormatting.GRAY));
                } else {
                    pTooltip.add(Component.translatable("genome.projectdinosaur.parent2", parent2).withStyle(ChatFormatting.GRAY));
                }
            }
        }
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return BlockEntitiesInit.AUSTRALOVENATOR_EGG_ENTITY.get().create(pPos, pState);
    }
}
