package com.kangalia.projectdinosaur.core.itemgroup;

import com.kangalia.projectdinosaur.core.init.ItemInit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class DinoCreatures extends CreativeModeTab {

    public static final DinoCreatures DINO_CREATURES = new DinoCreatures(CreativeModeTab.TABS.length, "dino_creatures");

    public DinoCreatures(int index, String label) {
        super(index, label);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemInit.AUSTRALOVENATOR_EGG_FERTILISED.get());
    }
}
