package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
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
    public static final RegistryObject<Item> IRON_CHISEL = ITEMS.register("iron_chisel", () -> new Item(new Item.Properties().stacksTo(1).durability(250).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> DIAMOND_CHISEL = ITEMS.register("diamond_chisel", () -> new Item(new Item.Properties().stacksTo(1).durability(1561).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> NETHERITE_CHISEL = ITEMS.register("netherite_chisel", () -> new Item(new Item.Properties().stacksTo(1).durability(2031).tab(DinoItems.DINO_ITEMS)));

    //Rock Specimens
    public static final RegistryObject<Item> ALPINE_ROCK_SPECIMEN = ITEMS.register("alpine_rock_specimen", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> AQUATIC_ROCK_SPECIMEN = ITEMS.register("aquatic_rock_specimen", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> ARID_ROCK_SPECIMEN = ITEMS.register("arid_rock_specimen", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> FROZEN_ROCK_SPECIMEN = ITEMS.register("frozen_rock_specimen", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> GRASSLAND_ROCK_SPECIMEN = ITEMS.register("grassland_rock_specimen", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> TEMPERATE_ROCK_SPECIMEN = ITEMS.register("temperate_rock_specimen", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> TROPICAL_ROCK_SPECIMEN = ITEMS.register("tropical_rock_specimen", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> WETLAND_ROCK_SPECIMEN = ITEMS.register("wetland_rock_specimen", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));

}
