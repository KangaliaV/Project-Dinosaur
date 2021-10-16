package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.item.Plaster;
import com.kangalia.projectdinosaur.core.itemgroup.DinoItems;
import net.minecraft.item.BoatItem;
import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ProjectDinosaur.MODID);

    //Chisels
    public static final RegistryObject<Item> IRON_CHISEL = ITEMS.register("iron_chisel", () -> new Item(new Item.Properties().durability(250).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> DIAMOND_CHISEL = ITEMS.register("diamond_chisel", () -> new Item(new Item.Properties().durability(1561).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> NETHERITE_CHISEL = ITEMS.register("netherite_chisel", () -> new Item(new Item.Properties().durability(2031).tab(DinoItems.DINO_ITEMS)));

    //Plaster
    public static final RegistryObject<Item> PLASTER = ITEMS.register("plaster", () -> new Plaster(new Item.Properties().tab(DinoItems.DINO_ITEMS)));


}
