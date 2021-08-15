package com.kangalia.projectdinosaur.core.itemgroup;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.core.init.BlockInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class DinoBlocks extends ItemGroup {

    public static final DinoBlocks DINO_BLOCKS = new DinoBlocks(ItemGroup.TABS.length, "dino_blocks");

    public DinoBlocks(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(BlockInit.PETRIFIED_PLANKS.get());
    }
}
