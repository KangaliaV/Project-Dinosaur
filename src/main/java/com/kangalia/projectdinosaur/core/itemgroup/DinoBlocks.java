package com.kangalia.projectdinosaur.core.itemgroup;

import com.kangalia.projectdinosaur.core.init.BlockInit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class DinoBlocks extends CreativeModeTab {

    public static final DinoBlocks DINO_BLOCKS = new DinoBlocks(CreativeModeTab.TABS.length, "dino_blocks");

    public DinoBlocks(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(BlockInit.PETRIFIED_PLANKS.get());
    }
}
