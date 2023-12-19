package com.kangalia.projectdinosaur.core.init;

import com.kangalia.projectdinosaur.ProjectDinosaur;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = ProjectDinosaur.MODID)
public class EventInit {

    private record tradesRecord (int stage, Item sellItem, int sellCost, Item buyItem, int buyCost, int maxUses, int xp, float multiplier) {
        static tradesRecord create(int stage, Item sellitem, int sellCost, Item buyItem, int buyCost, int maxUses, int xp, float multiplier) {
            return new tradesRecord(stage, sellitem, sellCost, buyItem, buyCost, maxUses, xp, multiplier);
        }

        static tradesRecord createFragment(Item buyItem) {
            return tradesRecord.create(0, Items.EMERALD, 4, buyItem, 1, 10, 3, 0.2f);
        }
    }

    @SubscribeEvent
    public static void addTrades(VillagerTradesEvent event) {
        if(event.getType() == VillagerInit.ARCHEOLOGIST.get()) {
            Random random = new Random();
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            var fragments = List.of(
                    ItemInit.QUATERNARY_FOSSIL_FRAGMENT.get(),
                    ItemInit.NEOGENE_FOSSIL_FRAGMENT.get(),
                    ItemInit.PALEOGENE_FOSSIL_FRAGMENT.get(),
                    ItemInit.CRETACEOUS_FOSSIL_FRAGMENT.get(),
                    ItemInit.JURASSIC_FOSSIL_FRAGMENT.get(),
                    ItemInit.TRIASSIC_FOSSIL_FRAGMENT.get(),
                    ItemInit.PERMIAN_FOSSIL_SPECIMEN.get(),
                    ItemInit.CARBONIFEROUS_FOSSIL_FRAGMENT.get(),
                    ItemInit.DEVONIAN_FOSSIL_FRAGMENT.get(),
                    ItemInit.SILURIAN_FOSSIL_FRAGMENT.get(),
                    ItemInit.ORDOVICIAN_FOSSIL_FRAGMENT.get(),
                    ItemInit.CAMBRIAN_FOSSIL_FRAGMENT.get()
            );
            var specimens = List.of(
                    ItemInit.QUATERNARY_FOSSIL_SPECIMEN.get(),
                    ItemInit.NEOGENE_FOSSIL_SPECIMEN.get(),
                    ItemInit.PALEOGENE_FOSSIL_SPECIMEN.get(),
                    ItemInit.CRETACEOUS_FOSSIL_SPECIMEN.get(),
                    ItemInit.JURASSIC_FOSSIL_SPECIMEN.get(),
                    ItemInit.TRIASSIC_FOSSIL_SPECIMEN.get(),
                    ItemInit.PERMIAN_FOSSIL_SPECIMEN.get(),
                    ItemInit.CARBONIFEROUS_FOSSIL_SPECIMEN.get(),
                    ItemInit.DEVONIAN_FOSSIL_SPECIMEN.get(),
                    ItemInit.SILURIAN_FOSSIL_SPECIMEN.get(),
                    ItemInit.ORDOVICIAN_FOSSIL_SPECIMEN.get(),
                    ItemInit.CAMBRIAN_FOSSIL_SPECIMEN.get()
            );

            var allTrades = List.of(
                    //STAGE 1
                    tradesRecord.create(1, Items.PAPER, 24, Items.EMERALD, 1, 16, 2, 0.05f),
                    tradesRecord.create(1, Items.CLAY_BALL, 16, Items.EMERALD, 1, 16, 3, 0.05f),
                    tradesRecord.create(1, Blocks.WHITE_WOOL.asItem(), 12, Items.EMERALD, 1, 16, 3, 0.05f),
                    tradesRecord.create(1, Items.EMERALD, 1, ItemInit.PLASTER.get(), 4, 8, 2, 0.05f),

                    //STAGE 2
                    tradesRecord.create(2, fragments.get(random.nextInt(fragments.size())), 4, Items.EMERALD, 1, 16, 8, 0.2f),
                    tradesRecord.create(2, Items.EMERALD, 6, ItemInit.IRON_CHISEL.get(), 1, 4, 6, 0.25f),

                    //STAGE 3
                    tradesRecord.create(3, Items.EMERALD, 6, fragments.get(random.nextInt(fragments.size())), 1, 12, 8, 0.2f),

                    //STAGE 4
                    tradesRecord.create(4, specimens.get(random.nextInt(specimens.size())), 2, Items.EMERALD, 1, 6, 10, 0.25f),
                    tradesRecord.create(4, Items.EMERALD, 12, specimens.get(random.nextInt(specimens.size())), 1, 10, 10, 0.25f),

                    //STAGE 5
                    tradesRecord.create(4, Items.EMERALD, 20, ItemInit.DIAMOND_CHISEL.get(), 1, 2, 12, 0.3f)
            );

            allTrades.forEach(stuff -> trades.get(stuff.stage()).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(stuff.sellItem(), stuff.sellCost()),
                    new ItemStack(stuff.buyItem(), stuff.buyCost()),
                    stuff.maxUses(), stuff.xp(), stuff.multiplier())));

        }
        if(event.getType() == VillagerInit.PALEONTOLOGIST.get()) {
            Random random = new Random();
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            var dna = List.of(
                    ItemInit.APHANERAMMA_DNA.get(),
                    ItemInit.ARTHROPLEURA_DNA.get(),
                    ItemInit.AUSTRALOVENATOR_DNA.get(),
                    ItemInit.DIRE_WOLF_DNA.get(),
                    ItemInit.GUIYU_DNA.get(),
                    ItemInit.GASTORNIS_DNA.get(),
                    ItemInit.GORGONOPS_DNA.get(),
                    ItemInit.MEGALODON_DNA.get(),
                    ItemInit.MEGALOGRAPTUS_DNA.get(),
                    ItemInit.SCELIDOSAURUS_DNA.get(),
                    ItemInit.TIKTAALIK_DNA.get(),
                    ItemInit.TRILOBITE_DNA.get()
            );

            var allTrades = List.of(
                    // STAGE 1
                    tradesRecord.create(1, Blocks.GLASS_PANE.asItem(), 11, Items.EMERALD, 1, 16, 4, 0.05f),
                    tradesRecord.create(1, ItemInit.ROTTEN_EGG.get(), 4, Items.EMERALD, 1, 16, 3, 0.05f),
                    tradesRecord.create(1, Items.EMERALD, 1, ItemInit.SYRINGE.get(), 4, 8, 2, 0.05f),

                    //STAGE 2
                    tradesRecord.create(2, dna.get(random.nextInt(dna.size())), 1, Items.EMERALD, 1, 16, 8, 0.2f),
                    tradesRecord.create(2, Items.EMERALD, 1, ItemInit.NUTRIENT_GOO.get(), 4, 4, 6, 0.25f),

                    //STAGE 3
                    tradesRecord.create(3, Items.EMERALD, 8, ItemInit.DINO_SCANNER.get(), 1, 8, 8, 0.2f),

                    //STAGE 4
                    tradesRecord.create(4, Items.EMERALD, 12, ItemInit.GENOME_SCANNER.get(), 1, 6, 10, 0.25f),

                    //STAGE 5
                    tradesRecord.create(5, Items.EMERALD, 24, ItemInit.CRYOPORTER.get(), 1, 4, 12, 0.3f)
            );

            allTrades.forEach(stuff -> trades.get(stuff.stage).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(stuff.sellItem(), stuff.sellCost()),
                    new ItemStack(stuff.buyItem(), stuff.buyCost()),
                    stuff.maxUses(), stuff.xp(), stuff.multiplier())));
        }
        if(event.getType() == VillagerInit.KEEPER.get()) {
            Random random = new Random();
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            var veggies = List.of(
                    Items.CARROT,
                    Items.POTATO,
                    Items.BEETROOT
            );

            var meats = List.of(
                    Items.BEEF,
                    Items.PORKCHOP,
                    Items.CHICKEN,
                    Items.RABBIT,
                    Items.MUTTON,
                    Items.COD,
                    Items.SALMON
            );

            var leaves = List.of(
                    Blocks.OAK_LEAVES.asItem(),
                    Blocks.DARK_OAK_LEAVES.asItem(),
                    Blocks.BIRCH_LEAVES.asItem(),
                    Blocks.JUNGLE_LEAVES.asItem(),
                    Blocks.SPRUCE_LEAVES.asItem(),
                    Blocks.ACACIA_LEAVES.asItem(),
                    Blocks.MANGROVE_LEAVES.asItem(),
                    Blocks.CHERRY_LEAVES.asItem(),
                    Blocks.AZALEA_LEAVES.asItem(),
                    Blocks.FLOWERING_AZALEA_LEAVES.asItem()
            );

            var allTrades = List.of(
                    // STAGE 1
                    tradesRecord.create(1, Items.WHEAT, 20, Items.EMERALD, 1, 16, 2, 0.05f),
                    tradesRecord.create(1, veggies.get(random.nextInt(veggies.size())), 20, Items.EMERALD, 1, 16, 2, 0.05f),
                    tradesRecord.create(1, Items.EMERALD, 3, BlockInit.NEST.get().asItem(), 1, 12, 3, 0.1f),

                    // STAGE 2
                    tradesRecord.create(2, Items.LEATHER, 4, Items.EMERALD, 1, 16, 3, 0.1f),
                    tradesRecord.create(2, meats.get(random.nextInt(meats.size())), 12, Items.EMERALD, 1, 12, 4, 0.2f),
                    tradesRecord.create(2, Items.EMERALD, 3, BlockInit.GROUND_FEEDER.get().asItem(), 1, 12, 3, 0.1f),

                    // STAGE 3
                    tradesRecord.create(3, leaves.get(random.nextInt(leaves.size())), 24, Items.EMERALD, 1, 16, 5, 0.25f),
                    tradesRecord.create(3, Items.EMERALD, 4, ItemInit.GROWTH_STUNTING_HORMONE.get(), 1, 12, 8, 0.25f),

                    // STAGE 4
                    tradesRecord.create(4, Items.EMERALD, 6, ItemInit.GROWTH_ACCELERATING_HORMONE.get(), 1, 12, 9, 0.25f),

                    // STAGE 5
                    tradesRecord.create(5, Items.EMERALD, 20, Items.NAME_TAG, 1, 12, 12, 0.3f)

            );

            allTrades.forEach(stuff -> trades.get(stuff.stage).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(stuff.sellItem(), stuff.sellCost()),
                    new ItemStack(stuff.buyItem(), stuff.buyCost()),
                    stuff.maxUses(), stuff.xp(), stuff.multiplier())));

        }
    }

    @SubscribeEvent
    public static void addWanderingTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        // GENERIC TRADES

        var generics = List.of(
                tradesRecord.create(0, Items.EMERALD, 1, ItemInit.PLASTER.get(), 1, 24, 3, 0.2f)
        );

        generics.forEach(stuff -> genericTrades.add((pTrader, pRandom) ->  new MerchantOffer(
                new ItemStack(stuff.sellItem(), stuff.sellCost()),
                new ItemStack(stuff.buyItem(), stuff.buyCost()),
                stuff.maxUses(), stuff.xp(), stuff.multiplier())));

        // RARE TRADES

        var rares = List.of(
                tradesRecord.createFragment(ItemInit.QUATERNARY_FOSSIL_FRAGMENT.get()),
                tradesRecord.createFragment(ItemInit.NEOGENE_FOSSIL_FRAGMENT.get()),
                tradesRecord.createFragment(ItemInit.PALEOGENE_FOSSIL_FRAGMENT.get()),
                tradesRecord.createFragment(ItemInit.CRETACEOUS_FOSSIL_FRAGMENT.get()),
                tradesRecord.createFragment(ItemInit.JURASSIC_FOSSIL_FRAGMENT.get()),
                tradesRecord.createFragment(ItemInit.TRIASSIC_FOSSIL_FRAGMENT.get()),
                tradesRecord.createFragment(ItemInit.PERMIAN_FOSSIL_SPECIMEN.get()),
                tradesRecord.createFragment(ItemInit.CARBONIFEROUS_FOSSIL_FRAGMENT.get()),
                tradesRecord.createFragment(ItemInit.DEVONIAN_FOSSIL_FRAGMENT.get()),
                tradesRecord.createFragment(ItemInit.SILURIAN_FOSSIL_FRAGMENT.get()),
                tradesRecord.createFragment(ItemInit.ORDOVICIAN_FOSSIL_FRAGMENT.get()),
                tradesRecord.createFragment(ItemInit.CAMBRIAN_FOSSIL_FRAGMENT.get())
        );

        rares.forEach(stuff -> rareTrades.add((pTrader, pRandom) ->  new MerchantOffer(
                new ItemStack(stuff.sellItem(), stuff.sellCost()),
                new ItemStack(stuff.buyItem(), stuff.buyCost()),
                stuff.maxUses(), stuff.xp(), stuff.multiplier())));

    }
}
