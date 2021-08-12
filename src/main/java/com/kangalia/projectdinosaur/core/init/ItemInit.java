package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ProjectDinosaur.MODID);

    public static final RegistryObject<Item> IRON_CHISEL = ITEMS.register("iron_chisel", () -> new Item(new Item.Properties().durability(100).tab(ItemGroup.TAB_TOOLS)));
}
