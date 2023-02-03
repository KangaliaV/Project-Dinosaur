package com.kangalia.projectdinosaur.common.blockentity.eggs;

import com.kangalia.projectdinosaur.core.init.BlockEntitiesInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.checkerframework.checker.units.qual.C;

public class GastornisEggBlockEntity extends BlockEntity {

    private String parent1 = "";
    private String parent2 = "";

    public GastornisEggBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntitiesInit.GASTORNIS_EGG_ENTITY.get(), pPos, pBlockState);
    }

    public String getParent1() {
        return parent1;
    }

    public String getParent2() {
        return parent2;
    }

    public void setParent1(String p1) {
        parent1 = p1;
    }

    public void setParent2(String p2) {
        parent2 = p2;
    }

    public CompoundTag writeParents() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putString("parent1", parent1);
        compoundTag.putString("parent2", parent2);
        return compoundTag;
    }

    public void readParents(String parent1, String parent2) {
        this.setParent1(parent1);
        this.setParent2(parent2);
    }

    @Override
    public void load(CompoundTag nbt) {
        CompoundTag tag = nbt.getCompound("parentGenome");
        if (tag.contains("parent1")) {
            this.setParent1(tag.getCompound("parentGenome").getString("parent1"));
        } else {
            this.setParent1("");
        }
        if (tag.contains("parent2")) {
            this.setParent1(tag.getCompound("parentGenome").getString("parent2"));
        } else {
            setParent2("");
        }
        super.load(nbt);
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        nbt.put("parentGenome", this.writeParents());
        super.saveAdditional(nbt);
    }
}
