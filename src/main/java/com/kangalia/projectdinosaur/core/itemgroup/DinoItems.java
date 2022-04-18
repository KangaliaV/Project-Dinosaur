package com.kangalia.projectdinosaur.core.itemgroup;

import com.kangalia.projectdinosaur.core.init.ItemInit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class DinoItems extends CreativeModeTab {

    public static final DinoItems DINO_ITEMS = new DinoItems(CreativeModeTab.TABS.length, "dino_items");

    public DinoItems(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemInit.IRON_CHISEL.get());
    }
}

