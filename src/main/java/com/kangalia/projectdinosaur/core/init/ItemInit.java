package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import com.kangalia.projectdinosaur.common.item.*;
import com.kangalia.projectdinosaur.common.item.blockitem.EmbryonicWombBlockItem;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.item.SignItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ProjectDinosaur.MODID);

    //Tools
    public static final RegistryObject<Item> IRON_CHISEL = ITEMS.register("iron_chisel", () -> new ChiselItem(new Item.Properties().stacksTo(1).durability(250)));
    public static final RegistryObject<Item> DIAMOND_CHISEL = ITEMS.register("diamond_chisel", () -> new ChiselItem(new Item.Properties().stacksTo(1).durability(1561)));
    public static final RegistryObject<Item> NETHERITE_CHISEL = ITEMS.register("netherite_chisel", () -> new ChiselItem(new Item.Properties().stacksTo(1).durability(2031)));

    //Fossil Fragments
    public static final RegistryObject<Item> QUATERNARY_FOSSIL_FRAGMENT = ITEMS.register("quaternary_fossil_fragment", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> NEOGENE_FOSSIL_FRAGMENT = ITEMS.register("neogene_fossil_fragment", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> PALEOGENE_FOSSIL_FRAGMENT = ITEMS.register("paleogene_fossil_fragment", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CRETACEOUS_FOSSIL_FRAGMENT = ITEMS.register("cretaceous_fossil_fragment", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> JURASSIC_FOSSIL_FRAGMENT = ITEMS.register("jurassic_fossil_fragment", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> TRIASSIC_FOSSIL_FRAGMENT = ITEMS.register("triassic_fossil_fragment", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> PERMIAN_FOSSIL_FRAGMENT = ITEMS.register("permian_fossil_fragment", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CARBONIFEROUS_FOSSIL_FRAGMENT = ITEMS.register("carboniferous_fossil_fragment", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> DEVONIAN_FOSSIL_FRAGMENT = ITEMS.register("devonian_fossil_fragment", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> SILURIAN_FOSSIL_FRAGMENT = ITEMS.register("silurian_fossil_fragment", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> ORDOVICIAN_FOSSIL_FRAGMENT = ITEMS.register("ordovician_fossil_fragment", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CAMBRIAN_FOSSIL_FRAGMENT = ITEMS.register("cambrian_fossil_fragment", () -> new Item(new Item.Properties().stacksTo(64)));

    //Fossil Specimens
    public static final RegistryObject<Item> QUATERNARY_FOSSIL_SPECIMEN = ITEMS.register("quaternary_fossil_specimen", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> NEOGENE_FOSSIL_SPECIMEN = ITEMS.register("neogene_fossil_specimen", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> PALEOGENE_FOSSIL_SPECIMEN = ITEMS.register("paleogene_fossil_specimen", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CRETACEOUS_FOSSIL_SPECIMEN = ITEMS.register("cretaceous_fossil_specimen", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> JURASSIC_FOSSIL_SPECIMEN = ITEMS.register("jurassic_fossil_specimen", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> TRIASSIC_FOSSIL_SPECIMEN = ITEMS.register("triassic_fossil_specimen", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> PERMIAN_FOSSIL_SPECIMEN = ITEMS.register("permian_fossil_specimen", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CARBONIFEROUS_FOSSIL_SPECIMEN = ITEMS.register("carboniferous_fossil_specimen", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> DEVONIAN_FOSSIL_SPECIMEN = ITEMS.register("devonian_fossil_specimen", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> SILURIAN_FOSSIL_SPECIMEN = ITEMS.register("silurian_fossil_specimen", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> ORDOVICIAN_FOSSIL_SPECIMEN = ITEMS.register("ordovician_fossil_specimen", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> CAMBRIAN_FOSSIL_SPECIMEN = ITEMS.register("cambrian_fossil_specimen", () -> new Item(new Item.Properties().stacksTo(64)));

    //DNA
    public static final RegistryObject<Item> APHANERAMMA_DNA = ITEMS.register("aphaneramma_dna", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> ARTHROPLEURA_DNA = ITEMS.register("arthropleura_dna", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> AUSTRALOVENATOR_DNA = ITEMS.register("australovenator_dna", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> DIRE_WOLF_DNA = ITEMS.register("dire_wolf_dna", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> GUIYU_DNA = ITEMS.register("guiyu_dna", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> GASTORNIS_DNA = ITEMS.register("gastornis_dna", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> GORGONOPS_DNA = ITEMS.register("gorgonops_dna", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> MEGALODON_DNA = ITEMS.register("megalodon_dna", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> MEGALOGRAPTUS_DNA = ITEMS.register("megalograptus_dna", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> SCELIDOSAURUS_DNA = ITEMS.register("scelidosaurus_dna", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> TIKTAALIK_DNA = ITEMS.register("tiktaalik_dna", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> TRILOBITE_DNA = ITEMS.register("trilobite_dna", () -> new Item(new Item.Properties().stacksTo(64)));

    //Fertilised Eggs
    public static final RegistryObject<Item> AUSTRALOVENATOR_EGG_FERTILISED = ITEMS.register("australovenator_egg_fertilised", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> GASTORNIS_EGG_FERTILISED = ITEMS.register("gastornis_egg_fertilised", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> GORGONOPS_EGG_FERTILISED = ITEMS.register("gorgonops_egg_fertilised", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> SCELIDOSAURUS_EGG_FERTILISED = ITEMS.register("scelidosaurus_egg_fertilised", () -> new Item(new Item.Properties().stacksTo(16)));

    //Spawn
    public static final RegistryObject<Item> APHANERAMMA_SPAWN_ITEM = ITEMS.register("aphaneramma_spawn_item", () -> new PlaceOnWaterBlockItem(BlockInit.APHANERAMMA_SPAWN.get(), new Item.Properties()));
    public static final RegistryObject<Item> ARTHROPLEURA_SPAWN_ITEM = ITEMS.register("arthropleura_spawn_item", () -> new PlaceOnWaterBlockItem(BlockInit.ARTHROPLEURA_SPAWN.get(), new Item.Properties()));
    public static final RegistryObject<Item> GUIYU_SPAWN_ITEM = ITEMS.register("guiyu_spawn_item", () -> new PlaceOnWaterBlockItem(BlockInit.GUIYU_SPAWN.get(), new Item.Properties()));
    public static final RegistryObject<Item> MEGALOGRAPTUS_SPAWN_ITEM = ITEMS.register("megalograptus_spawn_item", () -> new PlaceOnWaterBlockItem(BlockInit.MEGALOGRAPTUS_SPAWN.get(), new Item.Properties()));
    public static final RegistryObject<Item> TIKTAALIK_SPAWN_ITEM = ITEMS.register("tiktaalik_spawn_item", () -> new PlaceOnWaterBlockItem(BlockInit.TIKTAALIK_SPAWN.get(), new Item.Properties()));
    public static final RegistryObject<Item> TRILOBITE_SPAWN_ITEM = ITEMS.register("trilobite_spawn_item", () -> new PlaceOnWaterBlockItem(BlockInit.TRILOBITE_SPAWN.get(), new Item.Properties()));

    //Embryos
    public static final RegistryObject<Item> DIRE_WOLF_EMBYRO = ITEMS.register("dire_wolf_embryo", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> MEGALODON_EMBRYO = ITEMS.register("megalodon_embryo", () -> new Item(new Item.Properties().stacksTo(16)));

    //Foetuses
    public static final RegistryObject<Item> DIRE_WOLF_FOETUS = ITEMS.register("dire_wolf_foetus", () -> new Item(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> MEGALODON_FOETUS = ITEMS.register("megalodon_foetus", () -> new Item(new Item.Properties().stacksTo(16)));

    //Spawn Eggs
    public static final RegistryObject<ForgeSpawnEggItem> APHANERAMMA_SPAWN_EGG = ITEMS.register("aphaneramma_spawn_egg", () -> new PrehistoricSpawnEgg(EntityInit.APHANERAMMA, 0x223602, 0x395905, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<ForgeSpawnEggItem> ATHROPLEURA_SPAWN_EGG = ITEMS.register("arthropleura_spawn_egg", () -> new PrehistoricSpawnEgg(EntityInit.APHANERAMMA, 0x1d1d1d, 0xa6b1b5, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<ForgeSpawnEggItem> AUSTRALOVENATOR_SPAWN_EGG = ITEMS.register("australovenator_spawn_egg", () -> new PrehistoricSpawnEgg(EntityInit.AUSTRALOVENATOR, 0x805e2d, 0x4d4943, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<ForgeSpawnEggItem> DIRE_WOLF_SPAWN_EGG = ITEMS.register("dire_wolf_spawn_egg", () -> new PrehistoricSpawnEgg(EntityInit.APHANERAMMA, 0x544742, 0x403e3d, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<ForgeSpawnEggItem> GUIYU_SPAWN_EGG = ITEMS.register("guiyu_spawn_egg", () -> new PrehistoricSpawnEgg(EntityInit.APHANERAMMA, 0x274f41, 0x773f28, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<ForgeSpawnEggItem> GASTORNIS_SPAWN_EGG = ITEMS.register("gastornis_spawn_egg", () -> new PrehistoricSpawnEgg(EntityInit.GASTORNIS, 0x433727, 0x938878, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<ForgeSpawnEggItem> GORGONOPS_SPAWN_EGG = ITEMS.register("gorgonops_spawn_egg", () -> new PrehistoricSpawnEgg(EntityInit.APHANERAMMA, 0x262422, 0x968670, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<ForgeSpawnEggItem> MEGALODON_SPAWN_EGG = ITEMS.register("megalodon_spawn_egg", () -> new PrehistoricSpawnEgg(EntityInit.APHANERAMMA, 0x5c6163, 0xbbc4c7, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<ForgeSpawnEggItem> MEGALOGRAPTUS_SPAWN_EGG = ITEMS.register("megalograptus_spawn_egg", () -> new PrehistoricSpawnEgg(EntityInit.APHANERAMMA, 0xbbc4c7, 0xb2a3a0, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<ForgeSpawnEggItem> SCELIDOSAURUS_SPAWN_EGG = ITEMS.register("scelidosaurus_spawn_egg", () -> new PrehistoricSpawnEgg(EntityInit.SCELIDOSAURUS, 0x764631, 0xcb9f8a, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<ForgeSpawnEggItem> TIKTAALIK_SPAWN_EGG = ITEMS.register("tiktaalik_spawn_egg", () -> new PrehistoricSpawnEgg(EntityInit.APHANERAMMA, 0x4a453c, 0xa2b0b3, new Item.Properties().stacksTo(64)));
    public static final RegistryObject<ForgeSpawnEggItem> TRILOBITE_SPAWN_EGG = ITEMS.register("trilobite_spawn_egg", () -> new PrehistoricSpawnEgg(EntityInit.TRILOBITE, 0x212121, 0x9f5405, new Item.Properties().stacksTo(64)));

    //Miscellaneous Items
    public static final RegistryObject<Item> PETRIFIED_BOAT = ITEMS.register("petrified_boat", () -> new PetrifiedBoatItem(false, new Item.Properties().stacksTo(1), BlockSetTypesInit.PETRIFIED_WOOD_TYPE));
    public static final RegistryObject<Item> PETRIFIED_CHEST_BOAT = ITEMS.register("petrified_chest_boat", () -> new PetrifiedBoatItem(true, new Item.Properties().stacksTo(1), BlockSetTypesInit.PETRIFIED_WOOD_TYPE));
    public static final RegistryObject<Item> ROTTEN_EGG = ITEMS.register("rotten_egg", () -> new RottenEggItem(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> SYRINGE = ITEMS.register("syringe", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> NUTRIENT_GOO = ITEMS.register("nutrient_goo", () -> new Item(new Item.Properties().stacksTo(64)));

    //Interactive Items
    public static final RegistryObject<Item> PLASTER = ITEMS.register("plaster", () -> new Plaster(new Item.Properties()));
    public static final RegistryObject<Item> GROWTH_ACCELERATING_HORMONE = ITEMS.register("growth_accelerating_hormone", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> GROWTH_STUNTING_HORMONE = ITEMS.register("growth_stunting_hormone", () -> new Item(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> DINO_SCANNER = ITEMS.register("dino_scanner", () -> new DinoScanner(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CRYOPORTER = ITEMS.register("cryoporter", () -> new Cryoporter(new Item.Properties().stacksTo(1).durability(10)));
    public static final RegistryObject<Item> GENOME_SCANNER = ITEMS.register("genome_scanner", () -> new GenomeScanner(new Item.Properties().stacksTo(1)));

    //Specific Block Items
    public static final RegistryObject<Item> EMBRYONIC_WOMB_ITEM = ITEMS.register("embryonic_womb_item", () -> new EmbryonicWombBlockItem(BlockInit.EMBRYONIC_WOMB.get(), new Item.Properties()));
    public static final RegistryObject<Item> PETRIFIED_SIGN = ITEMS.register("petrified_sign", () -> new SignItem(new Item.Properties().stacksTo(16), BlockInit.PETRIFIED_SIGN.get(), BlockInit.PETRIFIED_SIGN_WALL.get()));

    //Dev Items
    public static final RegistryObject<Item> LOVE_POTION = ITEMS.register("love_potion", () -> new LovePotion(new Item.Properties()));
    public static final RegistryObject<Item> HUNGER_POTION = ITEMS.register("hunger_potion", () -> new HungerPotion(new Item.Properties()));
    public static final RegistryObject<Item> ANGER_POTION = ITEMS.register("anger_potion", () -> new AngerPotion(new Item.Properties()));
    public static final RegistryObject<Item> PACK_POTION = ITEMS.register("pack_potion", () -> new PackPotion(new Item.Properties()));

}
