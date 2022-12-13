package com.kangalia.projectdinosaur.common.blockentity;

import com.kangalia.projectdinosaur.common.entity.creature.AphanerammaEntity;
import com.kangalia.projectdinosaur.core.data.recipes.GrowingRecipe;
import com.kangalia.projectdinosaur.core.init.BlockEntitiesInit;
import com.kangalia.projectdinosaur.core.init.BlockInit;
import com.kangalia.projectdinosaur.core.init.ItemInit;
import com.kangalia.projectdinosaur.core.util.OutputStackHandler;
import com.kangalia.projectdinosaur.core.util.RandomNumGen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class EmbryonicWombBlockEntity extends BlockEntity implements IAnimatable {

    static final int WORK_TIME = 300 * 20;
    private int progress = 0;
    SimpleContainer inventory;
    private final NonNullList<ItemStack> items;
    private final RandomNumGen rng = new RandomNumGen();
    private AnimationFactory factory = GeckoLibUtil.createFactory(this);

    protected ItemStackHandler inputs = createInputHandler();
    protected ItemStackHandler outputs;
    protected ItemStackHandler outputWrapper;

    private final LazyOptional<IItemHandler> inputHandler = LazyOptional.of(() -> inputs);
    private final LazyOptional<IItemHandler> outputWrapperHandler = LazyOptional.of(() -> outputWrapper);
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> new CombinedInvWrapper(inputs, outputWrapper));

    public EmbryonicWombBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockEntitiesInit.EMBRYONIC_WOMB_ENTITY.get(), blockPos, blockState);
        this.items = NonNullList.withSize(3, ItemStack.EMPTY);
        outputs = new ItemStackHandler(1);
        outputWrapper = new OutputStackHandler(outputs);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        handler.invalidate();
    }

    @Override
    public void load(CompoundTag nbt) {
        inputs.deserializeNBT(nbt.getCompound("inputs"));
        outputs.deserializeNBT(nbt.getCompound("outputs"));
        this.progress = nbt.getInt("progress");
        super.load(nbt);
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        nbt.put("inputs", inputs.serializeNBT());
        nbt.put("outputs", outputs.serializeNBT());
        nbt.putInt("progress", this.progress);
        super.saveAdditional(nbt);
    }

    private ItemStackHandler createInputHandler() {
        return new ItemStackHandler(2) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if (slot == 0) {
                    return stack.getItem() == ItemInit.DIRE_WOLF_EMBYRO.get() ||
                            stack.getItem() == ItemInit.MEGALODON_EMBRYO.get();
                }
                if (slot == 1) {
                    return stack.getItem() == ItemInit.NUTRIENT_GOO.get();
                }
                return false;

            }
            @Override
            public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
                return super.insertItem(slot, stack, simulate);
            }

            @Override
            public int getSlotLimit(int slot) {
                if (slot == 1) {
                    return 64;
                }
                return 1;
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            this.setChanged();
            if(level != null && level.getBlockState(getBlockPos()).getBlock() != this.getBlockState().getBlock()) {
                return handler.cast();
            }
            if (side == null) {
                return handler.cast();
            }
            if (level == null) {
                if (side == Direction.UP) {
                    return inputHandler.cast();
                }
                if (side == Direction.DOWN) {
                    return outputWrapperHandler.cast();
                }
            }
            if (side == Direction.UP) {
                return inputHandler.cast();
            }
            if (side == Direction.DOWN) {
                return outputWrapperHandler.cast();
            }
        }
        return super.getCapability(cap, side);
    }

    public void tick() {
        if (this.level == null) {
            return;
        }
        BlockState blockState = level.getBlockState(worldPosition);
        if (this.canGrow()) {
            level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.POWERED, true), Block.UPDATE_ALL);
            if (progress < WORK_TIME) {
                ++progress;
                level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
                setChanged();
            }
            if (progress == WORK_TIME) {
                progress = 0;
                this.doGrow();
            }
        } else {
            level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.POWERED, false), Block.UPDATE_ALL);
            progress = 0;
        }
        setChanged();
    }

    private boolean canGrow() {
        ItemStack inputSlot = ItemStack.EMPTY;
        inputSlot = inputs.getStackInSlot(0);
        boolean flag;
        flag = !inputSlot.isEmpty() && inputs.isItemValid(0, inputSlot);
        if (flag) {
            ItemStack haySlot = inputs.getStackInSlot(1);
            return !haySlot.isEmpty();
        }
        return false;
    }

    @Nullable
    public GrowingRecipe craft() {
        inventory = new SimpleContainer(inputs.getSlots());
        inventory.addItem(inputs.getStackInSlot(0));
        inventory.addItem(inputs.getStackInSlot(1));
        List<GrowingRecipe> recipes = level.getRecipeManager().getRecipesFor(GrowingRecipe.GrowingRecipeType.INSTANCE, inventory, level);
        if (!recipes.isEmpty()) {
            GrowingRecipe selectedRecipe;
            if (recipes.size() == 1) {
                selectedRecipe = recipes.get(0);
            } else {
                int totalWeight = recipes.stream().map(r -> r.getWeight()).mapToInt(Integer::intValue).sum();
                int[] weightArray = new int[totalWeight];
                int pos = 0;
                for (int j = 0; j < recipes.size(); j++) {
                    GrowingRecipe er = recipes.get(j);
                    int weight = er.getWeight();
                    for (int k = 0; k < weight; k++) {
                        weightArray[pos] = j;
                        pos++;
                    }
                }
                int randomNum = rng.nextInt(weightArray.length);
                int recipeIndex = weightArray[randomNum];
                inventory.removeAllItems();
                return recipes.get(recipeIndex);
            }
            return selectedRecipe;
        }
        return null;
    }

    private ItemStack getOutput(@Nullable GrowingRecipe selectedRecipe) {
        if (selectedRecipe != null) {
            return selectedRecipe.getResultItem();
        }
        return ItemStack.EMPTY;
    }

    public void doGrow() {
        assert this.level != null;
        ItemStack hay = inputs.getStackInSlot(1);
        ItemStack outputSlot = outputs.getStackInSlot(0);
        if (this.canGrow()) {
            GrowingRecipe selectedRecipe = craft();
            ItemStack output = getOutput(selectedRecipe);
            if (!output.isEmpty() && outputSlot.isEmpty()) {
                ItemStack stack = inputs.getStackInSlot(0);
                stack.shrink(1);
                outputs.insertItem(0, output, false);
            }
            hay.shrink(1);
        }
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag tags = this.getUpdateTag();
        ContainerHelper.saveAllItems(tags, this.items);
        tags.putInt("progress", this.progress);
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return this.saveWithFullMetadata();
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();
        if (tag.contains("progress")) {
            progress = tag.getInt("progress");
            this.getPersistentData().putInt("progress", this.progress);
        }
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.embryonic_womb.embryo", ILoopType.EDefaultLoopTypes.LOOP));
        event.getController().setAnimationSpeed(0.5);
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<EmbryonicWombBlockEntity>(this, "controller", 4, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
