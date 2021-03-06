package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.item.DinoScanner;
import com.kangalia.projectdinosaur.common.item.PrehistoricSpawnEgg;
import com.kangalia.projectdinosaur.common.item.PetrifiedBoatItem;
import com.kangalia.projectdinosaur.common.item.Plaster;
import com.kangalia.projectdinosaur.core.itemgroup.DinoCreatures;
import com.kangalia.projectdinosaur.core.itemgroup.DinoItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ProjectDinosaur.MODID);

    //Tools
    public static final RegistryObject<Item> IRON_CHISEL = ITEMS.register("iron_chisel", () -> new Item(new Item.Properties().stacksTo(1).durability(250).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> DIAMOND_CHISEL = ITEMS.register("diamond_chisel", () -> new Item(new Item.Properties().stacksTo(1).durability(1561).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> NETHERITE_CHISEL = ITEMS.register("netherite_chisel", () -> new Item(new Item.Properties().stacksTo(1).durability(2031).tab(DinoItems.DINO_ITEMS)));

    //Rock Fragments
    public static final RegistryObject<Item> ALPINE_ROCK_FRAGMENT = ITEMS.register("alpine_rock_fragment", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> AQUATIC_ROCK_FRAGMENT = ITEMS.register("aquatic_rock_fragment", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> ARID_ROCK_FRAGMENT = ITEMS.register("arid_rock_fragment", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> FROZEN_ROCK_FRAGMENT = ITEMS.register("frozen_rock_fragment", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> GRASSLAND_ROCK_FRAGMENT = ITEMS.register("grassland_rock_fragment", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> TEMPERATE_ROCK_FRAGMENT = ITEMS.register("temperate_rock_fragment", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> TROPICAL_ROCK_FRAGMENT = ITEMS.register("tropical_rock_fragment", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> WETLAND_ROCK_FRAGMENT = ITEMS.register("wetland_rock_fragment", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));

    //Crystallised Fragments
    public static final RegistryObject<Item> ALPINE_CRYSTALLISED_FRAGMENT = ITEMS.register("alpine_crystallised_fragment", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> AQUATIC_CRYSTALLISED_FRAGMENT = ITEMS.register("aquatic_crystallised_fragment", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> ARID_CRYSTALLISED_FRAGMENT = ITEMS.register("arid_crystallised_fragment", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> FROZEN_CRYSTALLISED_FRAGMENT = ITEMS.register("frozen_crystallised_fragment", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> GRASSLAND_CRYSTALLISED_FRAGMENT = ITEMS.register("grassland_crystallised_fragment", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> TEMPERATE_CRYSTALLISED_FRAGMENT = ITEMS.register("temperate_crystallised_fragment", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> TROPICAL_CRYSTALLISED_FRAGMENT = ITEMS.register("tropical_crystallised_fragment", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> WETLAND_CRYSTALLISED_FRAGMENT = ITEMS.register("wetland_crystallised_fragment", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));

    //Rock Specimens
    public static final RegistryObject<Item> ALPINE_ROCK_SPECIMEN = ITEMS.register("alpine_rock_specimen", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> AQUATIC_ROCK_SPECIMEN = ITEMS.register("aquatic_rock_specimen", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> ARID_ROCK_SPECIMEN = ITEMS.register("arid_rock_specimen", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> FROZEN_ROCK_SPECIMEN = ITEMS.register("frozen_rock_specimen", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> GRASSLAND_ROCK_SPECIMEN = ITEMS.register("grassland_rock_specimen", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> TEMPERATE_ROCK_SPECIMEN = ITEMS.register("temperate_rock_specimen", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> TROPICAL_ROCK_SPECIMEN = ITEMS.register("tropical_rock_specimen", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> WETLAND_ROCK_SPECIMEN = ITEMS.register("wetland_rock_specimen", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));

    //Crystallised Specimens
    public static final RegistryObject<Item> ALPINE_CRYSTALLISED_SPECIMEN = ITEMS.register("alpine_crystallised_specimen", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> AQUATIC_CRYSTALLISED_SPECIMEN = ITEMS.register("aquatic_crystallised_specimen", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> ARID_CRYSTALLISED_SPECIMEN = ITEMS.register("arid_crystallised_specimen", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> FROZEN_CRYSTALLISED_SPECIMEN = ITEMS.register("frozen_crystallised_specimen", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> GRASSLAND_CRYSTALLISED_SPECIMEN = ITEMS.register("grassland_crystallised_specimen", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> TEMPERATE_CRYSTALLISED_SPECIMEN = ITEMS.register("temperate_crystallised_specimen", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> TROPICAL_CRYSTALLISED_SPECIMEN = ITEMS.register("tropical_crystallised_specimen", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> WETLAND_CRYSTALLISED_SPECIMEN = ITEMS.register("wetland_crystallised_specimen", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));

    //DNA
    public static final RegistryObject<Item> APHANERAMMA_DNA = ITEMS.register("aphaneramma_dna", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoCreatures.DINO_CREATURES)));
    public static final RegistryObject<Item> AUSTRALOVENATOR_DNA = ITEMS.register("australovenator_dna", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoCreatures.DINO_CREATURES)));
    public static final RegistryObject<Item> COMPSOGNATHUS_DNA = ITEMS.register("compsognathus_dna", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoCreatures.DINO_CREATURES)));
    public static final RegistryObject<Item> SCELIDOSAURUS_DNA = ITEMS.register("scelidosaurus_dna", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoCreatures.DINO_CREATURES)));
    public static final RegistryObject<Item> TARBOSAURUS_DNA = ITEMS.register("tarbosaurus_dna", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoCreatures.DINO_CREATURES)));

    //Fertilised Eggs
    public static final RegistryObject<Item> FERTILISED_APHANERAMMA_EGG = ITEMS.register("fertilised_aphaneramma_egg", () -> new Item(new Item.Properties().stacksTo(16).tab(DinoCreatures.DINO_CREATURES)));
    public static final RegistryObject<Item> FERTILISED_AUSTRALOVENATOR_EGG = ITEMS.register("fertilised_australovenator_egg", () -> new Item(new Item.Properties().stacksTo(16).tab(DinoCreatures.DINO_CREATURES)));
    public static final RegistryObject<Item> FERTILISED_COMPSOGNATHUS_EGG = ITEMS.register("fertilised_compsognathus_egg", () -> new Item(new Item.Properties().stacksTo(16).tab(DinoCreatures.DINO_CREATURES)));
    public static final RegistryObject<Item> FERTILISED_SCELIDOSAURUS_EGG = ITEMS.register("fertilised_scelidosaurus_egg", () -> new Item(new Item.Properties().stacksTo(16).tab(DinoCreatures.DINO_CREATURES)));
    public static final RegistryObject<Item> FERTILISED_TARBOSAURUS_EGG = ITEMS.register("fertilised_tarbosaurus_egg", () -> new Item(new Item.Properties().stacksTo(16).tab(DinoCreatures.DINO_CREATURES)));

    //Spawn Eggs
    public static final RegistryObject<ForgeSpawnEggItem> APHANERAMMA_SPAWN_EGG = ITEMS.register("aphaneramma_spawn_egg", () -> new PrehistoricSpawnEgg(EntityInit.APHANERAMMA, 0x223602, 0x395905, new Item.Properties().stacksTo(64).tab(DinoCreatures.DINO_CREATURES)));
    public static final RegistryObject<ForgeSpawnEggItem> AUSTRALOVENATOR_SPAWN_EGG = ITEMS.register("australovenator_spawn_egg", () -> new PrehistoricSpawnEgg(EntityInit.AUSTRALOVENATOR, 0x805e2d, 0x4d4943, new Item.Properties().stacksTo(64).tab(DinoCreatures.DINO_CREATURES)));
    public static final RegistryObject<ForgeSpawnEggItem> COMPSOGNATHUS_SPAWN_EGG = ITEMS.register("compsognathus_spawn_egg", () -> new PrehistoricSpawnEgg(EntityInit.COMPSOGNATHUS, 0x143226, 0x80886f, new Item.Properties().stacksTo(64).tab(DinoCreatures.DINO_CREATURES)));
    public static final RegistryObject<ForgeSpawnEggItem> SCELIDOSAURUS_SPAWN_EGG = ITEMS.register("scelidosaurus_spawn_egg", () -> new PrehistoricSpawnEgg(EntityInit.SCELIDOSAURUS, 0x764631, 0xcb9f8a, new Item.Properties().stacksTo(64).tab(DinoCreatures.DINO_CREATURES)));
    public static final RegistryObject<ForgeSpawnEggItem> TARBOSAURUS_SPAWN_EGG = ITEMS.register("tarbosaurus_spawn_egg", () -> new PrehistoricSpawnEgg(EntityInit.TARBOSAURUS, 0x767b32, 0x326e56, new Item.Properties().stacksTo(64).tab(DinoCreatures.DINO_CREATURES)));

    //Gems
    public static final RegistryObject<Item> HEMATINE = ITEMS.register("hematine", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> AZURITE = ITEMS.register("azurite", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> AMBER = ITEMS.register("amber", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> AQUAMARINE = ITEMS.register("aquamarine", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> URAVORITE = ITEMS.register("uravorite", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> MALACHITE = ITEMS.register("malachite", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> SPHENE = ITEMS.register("sphene", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> ALMANDINE = ITEMS.register("almandine", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));

    //Miscellaneous Items
    public static final RegistryObject<Item> PETRIFIED_SIGN = ITEMS.register("petrified_sign", () -> new SignItem(new Item.Properties().stacksTo(16).tab(DinoItems.DINO_ITEMS), BlockInit.PETRIFIED_SIGN.get(), BlockInit.PETRIFIED_SIGN_WALL.get()));
    public static final RegistryObject<Item> PETRIFIED_BOAT = ITEMS.register("petrified_boat", () -> new PetrifiedBoatItem(new Item.Properties().tab(DinoItems.DINO_ITEMS), "petrified"));
    public static final RegistryObject<Item> ROTTEN_EGG = ITEMS.register("rotten_egg", () -> new Item(new Item.Properties().stacksTo(16).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> SYRINGE = ITEMS.register("syringe", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));

    //Interactive Items
    public static final RegistryObject<Item> PLASTER = ITEMS.register("plaster", () -> new Plaster(new Item.Properties().tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> GROWTH_ACCELERATING_HORMONE = ITEMS.register("growth_accelerating_hormone", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> GROWTH_STUNTING_HORMONE = ITEMS.register("growth_stunting_hormone", () -> new Item(new Item.Properties().stacksTo(64).tab(DinoItems.DINO_ITEMS)));
    public static final RegistryObject<Item> DINO_SCANNER = ITEMS.register("dino_scanner", () -> new DinoScanner(new Item.Properties().stacksTo(1).tab(DinoItems.DINO_ITEMS)));

}
