package com.kangalia.projectdinosaur.core.itemgroup;

import com.kangalia.projectdinosaur.core.init.BlockInit;
import com.kangalia.projectdinosaur.core.init.ItemInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class DinoItems extends ItemGroup {

    public static final DinoItems DINO_ITEMS = new DinoItems(ItemGroup.TABS.length, "dino_items");

    public DinoItems(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemInit.IRON_CHISEL.get());
    }
}

