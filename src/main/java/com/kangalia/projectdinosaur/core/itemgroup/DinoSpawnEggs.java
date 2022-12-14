package com.kangalia.projectdinosaur.core.itemgroup;

import com.kangalia.projectdinosaur.core.init.BlockInit;
import com.kangalia.projectdinosaur.core.init.ItemInit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class DinoSpawnEggs extends CreativeModeTab {

    public static final DinoSpawnEggs DINO_SPAWN_EGGS = new DinoSpawnEggs(CreativeModeTab.TABS.length, "dino_spawn_eggs");

    public DinoSpawnEggs(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemInit.APHANERAMMA_SPAWN_EGG.get());
    }
}
