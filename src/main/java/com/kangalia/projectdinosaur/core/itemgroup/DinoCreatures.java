package com.kangalia.projectdinosaur.core.itemgroup;

import com.kangalia.projectdinosaur.core.init.ItemInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class DinoCreatures extends ItemGroup{

    public static final DinoCreatures DINO_CREATURES = new DinoCreatures(ItemGroup.TABS.length, "dino_creatures");

    public DinoCreatures(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemInit.APHANERAMMA_SPAWN_EGG.get());
    }
}
